package exportRecord;

import com.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by pengshu on 2016/12/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/application.xml"})
public class nuonuobangke {
    @Resource
    MongoTemplate mongoTemplate;

    int backwardDays = 0;//回退天数
    SimpleDateFormat sdf_date = null;
    SimpleDateFormat sdf_time = null;
    String outFilePath = null;

    List<String> date_yMs = null;

    HSSFRow rowout = null;
    HSSFCell cellout = null;

    @Before
    public void init() {
        backwardDays = -7;
        sdf_date = new SimpleDateFormat("MM月dd日");
        sdf_time = new SimpleDateFormat("HH:mm");
        outFilePath = "C:\\Users\\pengshu\\Desktop\\" + new SimpleDateFormat("yyyyMMdd_SSS").format(new Date()) + ".xls";
        date_yMs = get_yms();
    }

    public List<String> get_yms() {
        List<String> date = new ArrayList<String>();

        GregorianCalendar calendar = new GregorianCalendar();
        date.add(sdf_date.format(calendar.getTime()));

        for (int i = 0; i > backwardDays; i--) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            date.add(sdf_date.format(calendar.getTime()));
        }
        Collections.reverse(date);
        return date;
    }

    public Date getStartDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, backwardDays);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    @Test
    public void export() throws IOException {
        MongoClient mongoClient = new MongoClient("10.76.2.2", 27017);
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = new DB(mongoClient, "pengshu");
        DBCollection conn = db.getCollection("debtCollectEvent");

        Date startDate = this.getStartDate();
        DBObject match = new BasicDBObject("$match",
                new BasicDBObject("createDate",
                        new BasicDBObject("$gt", startDate))
                        .append("aOrgId", "00020")//这里是机构ID
                        .append("content", new BasicDBObject("$exists", true)));


        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("createDate", 1));

        DBObject lookup = new BasicDBObject("$lookup",
                new BasicDBObject("from", "debtCase")
                        .append("localField", "caseId")
                        .append("foreignField", "caseId")
                        .append("as", "case"));

        DBObject unwind_1 = new BasicDBObject("$unwind", "$case");
        //查询正在跟进的状态
        DBObject lookupStatus = new BasicDBObject("$lookup",
                new BasicDBObject("from", "caseStatusInfo")
                        .append("localField", "caseId")
                        .append("foreignField", "caseId")
                        .append("as", "caseStatusInfo"));
        DBObject unwindStatus = new BasicDBObject("$unwind", "$caseStatusInfo");
        DBObject matchStatus = new BasicDBObject("$match", new BasicDBObject("caseStatusInfo.caseStatus", "follow_up"));

        DBObject groupFields = new BasicDBObject("_id", "$caseId")
                .append("infos",
                        new BasicDBObject("$push",
                                new BasicDBObject("operatorId", "$operatorId")
                                        .append("content", "$content")
                                        .append("createDate", "$createDate")))
                .append("debtCase", new BasicDBObject("$first", "$case"))
                .append("operatorId", new BasicDBObject("$last", "$operatorId"));

        DBObject group = new BasicDBObject("$group", groupFields);
        DBObject lookup_user = new BasicDBObject("$lookup",
                new BasicDBObject("from", "sysUser")
                        .append("localField", "operatorId")
                        .append("foreignField", "_id")
                        .append("as", "user"));
        DBObject unwind_user = new BasicDBObject("$unwind", "$user");

        //DBObject allowDiskUse = new BasicDBObject("allowDiskUse", true);
        AggregationOutput output = conn.aggregate(Arrays.asList(match, sort, lookup, unwind_1, lookupStatus, unwindStatus, matchStatus, group, lookup_user, unwind_user));
        Iterator<DBObject> it = output.results().iterator();

        FileOutputStream os = new FileOutputStream(outFilePath);
        HSSFWorkbook wbout = new HSSFWorkbook();
        HSSFSheet sheetout = wbout.createSheet("sheet1");
        this.setHeader(sheetout);//表头

        int row_index = 1;
        while (it.hasNext()) {
            HSSFRow row = sheetout.createRow(row_index);

            BasicDBObject caseInfo = (BasicDBObject) it.next();
            List rowData = getRowData(caseInfo);
            int index = 0;
            while (index < rowData.size()) {
                if (index == rowData.size() - 1) {
                    Map<String, String> cellRecords = (Map<String, String>) rowData.get(index);
                    Iterator<String> iterator = cellRecords.keySet().iterator();
                    while (iterator.hasNext()) {
                        String date_yM = iterator.next();
                        int date_index = date_yMs.indexOf(date_yM);
                        HSSFCell cell = row.createCell(index + date_index);
                        cell.setCellValue(cellRecords.get(date_yM));
                    }
                } else {
                    HSSFCell cell = row.createCell(index);
                    if (rowData.get(index) == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(rowData.get(index).toString());
                    }
                }
                index++;
            }
            row_index++;
        }
        wbout.write(os);
    }

    public List getRowData(BasicDBObject caseInfo) {
        List rowData = new ArrayList();

        BasicDBObject debtCase = (BasicDBObject) caseInfo.get("debtCase");
        rowData.add(debtCase.get("caseId"));

        rowData.add(getValue("批次", (List<Map>) debtCase.get("auxInfo")));
        rowData.add(debtCase.get("debtorName"));
        rowData.add(getValue("用户名", (List<Map>) debtCase.get("auxInfo")));
        rowData.add(getValue("标的号", (List<Map>) debtCase.get("auxInfo")));

        BasicDBObject user = (BasicDBObject) caseInfo.get("user");
        rowData.add(user.get("userName"));
        List<BasicDBObject> collectionInfos = (List<BasicDBObject>) caseInfo.get("infos");

        Map<String, String> recodes = new HashMap<>();
        StringBuffer sb = null;

        for (BasicDBObject cellInfo : collectionInfos) {
            Date date = cellInfo.getDate("createDate");
            String date_yM = sdf_date.format(date);

            String recode = recodes.get(date_yM);
            if (StringUtils.isEmpty(recode)) {
                recode = "";
            }
            sb = new StringBuffer(recode);

            String date_Hm = sdf_time.format(date);
            sb.append(date_Hm + ":" + cellInfo.get("content") + ";\n");
            recodes.put(date_yM, sb.toString());
        }
        rowData.add(recodes);

        return rowData;
    }

    //获取额外信息
    public String getValue(String regex, List<Map> auxInfo) {
        Pattern pattern = Pattern.compile(regex);
        for (Map info : auxInfo) {
            if (pattern.matcher(info.get("key").toString()).find()) {
                return info.get("value").toString();
            }
        }
        return "";
    }

    public void setHeader(HSSFSheet sheetout) {
        int i = 0;
        HSSFRow row = sheetout.createRow(0);
        HSSFCell cell = row.createCell(i++);
        cell.setCellValue("caseId");
        cell = row.createCell(i++);
        cell.setCellValue("批次");
        cell = row.createCell(i++);
        cell.setCellValue("姓名");
        cell = row.createCell(i++);
        cell.setCellValue("用户名");
        cell = row.createCell(i++);
        cell.setCellValue("标的号");
        cell = row.createCell(i++);
        cell.setCellValue("催收员");
        for (String date : date_yMs) {
            cell = row.createCell(i++);
            cell.setCellValue(date);
        }
    }

    /**
     * db.caseStatusInfo.aggregate(
     * {$match:{$and:[{aOrgId:"00020"},{caseStatus:"follow_up"}]}},
     * {$project:{_id:0,caseId:1,operatorId:1}},
     * {$lookup:{from:"debtCase",localField:"caseId",foreignField:"caseId",as:"debtCase"}},
     * {$unwind:{path:"$debtCase"}},
     * {$project:{_id:0,caseId:1,operatorId:1,debtorName:"$debtCase.debtorName",debtorPhone:"$debtCase.debtorPhone",debtorCid:"$debtCase.debtorCid"}},
     * {$lookup:{from:"debtCollectEvent",localField:"caseId",foreignField:"caseId",as:"debtCollectEvent"}},
     * {$unwind:{path:"$debtCollectEvent",preserveNullAndEmptyArrays:true}},
     * {$project:{operatorId:1,caseId:1,debtorName:"$debtorName",debtorPhone:"$debtorPhone",debtorCid:"$debtorCid",debtCollectEvent:[{phone:"$debtCollectEvent.phone"},{content:"$debtCollectEvent.content"},
     * {feedback:"$debtCollectEvent.feedback"},{createDate:"$debtCollectEvent.createDate"}]}},
     * //一周以内
     * //	{$match:{$or:[{"debtCollectEvent.createDate":{$gt:ISODate("2016-12-18T00:26:21.277+0000")}},{"debtCollectEvent.createDate":{$exists:false}},{"debtCollectEvent.createDate":""}]}},
     * //一周以前
     * {$match:{"debtCollectEvent.createDate":{$lt:ISODate("2016-12-18T00:26:21.277+0000")}}},
     * {$group:{_id:{caseId:"$caseId",operatorId:"$operatorId",debtorName:"$debtorName",debtorPhone:"$debtorPhone",debtorCid:"$debtorCid"},debtCollectEvent:{$addToSet:"$debtCollectEvent"}}},
     * {$project:{_id:0,"caseId":"$_id.caseId","operatorId":"$_id.operatorId","debtorName":"$_id.debtorName","debtorPhone":"$_id.debtorPhone","debtorCid":"$_id.debtorCid","debtCollectEvent":"$debtCollectEvent"}},
     * {$lookup:{from:"sysUser",localField:"operatorId",foreignField:"_id",as:"sysUser"}},
     * {$unwind:"$sysUser"},
     * {$project:{"caseId":"$caseId","客户姓名":"$debtorName","客户电话":"$debtorPhone","客户身份证":"$debtorCid","debtCollectEvent":"$debtCollectEvent","催收员":"$sysUser.userName"}}
     * <p>
     * )
     *
     * @return
     */
    public Iterable<DBObject> query() {
        DBCollection collection = mongoTemplate.getCollection("caseStatusInfo");
        DBObject match = new BasicDBObject("$match", new BasicDBObject("aOrgId", "00020").append("caseStatus", "follow_up"));
        DBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("caseId", 1).append("operatorId", 1));
        DBObject lookup = new BasicDBObject("$lookup", new BasicDBObject("from", "debtCase").append("localField", "caseId").append("foreignField", "caseId").append("as", "debtCase"));
        DBObject unwind = new BasicDBObject("$unwind", "$debtCase");
        DBObject project2 = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("caseId", 1).append("operatorId", 1).
                append("debtorName", "$debtCase.debtorName").append("debtorPhone", "$debtCase.debtorPhone").append("debtorCid", "$debtCase.debtorCid"));
        DBObject lookup2 = new BasicDBObject("$lookup", new BasicDBObject("from", "debtCollectEvent").append("localField", "caseId").append("foreignField", "caseId").append("as", "debtCollectEvent"));
        DBObject unwind2 = new BasicDBObject("$unwind", new BasicDBObject("path", "$debtCollectEvent").append("preserveNullAndEmptyArrays", true));
        DBObject project3 = new BasicDBObject("operatorId", 1).append("caseId", 1).append("debtorName", "$debtorName").append("debtorPhone", "$debtorPhone").append("debtorCid", "$debtorCid").
                append("debtCollectEvent", new BasicDBObject[]{new BasicDBObject("phone", "$debtCollectEvent.phone"), new BasicDBObject("content", "$debtCollectEvent.content"), new BasicDBObject("feedback", "$debtCollectEvent.feedback"), new BasicDBObject("createDate", "$debtCollectEvent.createDate")});
        //一周以内
        DBObject match2 = new BasicDBObject("$match", new BasicDBObject("$or", new BasicDBObject[]{new BasicDBObject("debtCollectEvent.createDate", new BasicDBObject("$gt", "ISODate('2016-12-18T00:26:21.277+0000')")),
                new BasicDBObject("debtCollectEvent.createDate", new BasicDBObject("$exists", "false")),
                new BasicDBObject("debtCollectEvent.createDate", "")}));
        DBObject group = new BasicDBObject("_id", new BasicDBObject("caseId", "$caseId").append("operatorId", "$operatorId").append("debtorName", "$debtorName").
                append("debtorPhone", "$debtorPhone").append("debtorCid", "$debtorCid")).append("debtCollectEvent", new BasicDBObject("$addToSet", "$debtCollectEvent"));
        DBObject project4 = new BasicDBObject("_id", 0).append("", 1).append("caseId", "$_id.caseId").append("operatorId", "$_id.operatorId").append("debtorName", "$_id.debtorName").append("debtorPhone", "$_id.debtorPhone").append("debtorCid", "$_id.debtorCid").append("debtCollectEvent", "$debtCollectEvent");
        DBObject lookup3 = new BasicDBObject("$lookup", new BasicDBObject("from", "sysUser").append("localField", "operatorId").append("foreignField", "_id").append("as", "sysUser"));
        DBObject unwind3 = new BasicDBObject("$unwind", "$sysUser");
        DBObject project5 = new BasicDBObject("$project", "");
        return null;
    }


}
