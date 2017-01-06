package exportRecord;

import com.mongodb.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.awt.SystemColor.info;

/**
 * Created by pengshu on 2016/12/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/application.xml"})
public class NNBK_NinCaseId {
    @Resource
    MongoTemplate mongoTemplate;

    int backwardDays = 0;//回退天数
    SimpleDateFormat sdf_date = null;
    SimpleDateFormat sdf_time = null;
    String outFilePath = null;

    List<String> date_yMs = null;

    @Before
    public void init() {
        backwardDays = -7;
        sdf_date = new SimpleDateFormat("MM月dd日");
        sdf_time = new SimpleDateFormat("HH:mm");
        outFilePath = "C:\\Users\\pengshu\\Desktop\\nin" + new SimpleDateFormat("yyyyMMdd_SSS").format(new Date()) + ".xls";
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

    public AggregationOutput query() {

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
        return conn.aggregate(Arrays.asList(match, sort, lookup, unwind_1,lookupStatus,unwindStatus,matchStatus, group, lookup_user, unwind_user));
    }


    public List<String> getCaseIds() {
        List<String> caseIds = new ArrayList<String>();
        Iterator<DBObject> it = query().results().iterator();

        int inx = 0;
        while (it.hasNext()) {
            BasicDBObject caseInfo = (BasicDBObject) it.next();
            BasicDBObject debtCase = (BasicDBObject) caseInfo.get("debtCase");
            caseIds.add((String) debtCase.get("caseId"));
        }
        return caseIds;
    }

    @Test
    public void export() throws IOException {
        MongoClient mongoClient = new MongoClient("10.76.2.2", 27017);
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = new DB(mongoClient, "pengshu");
        DBCollection conn = db.getCollection("caseStatusInfo");

        DBObject match = new BasicDBObject("$match", new BasicDBObject("aOrgId", "00020").append("caseStatus", "follow_up"));
        System.out.println(Arrays.asList(getCaseIds()));
        DBObject matchCaseId = new BasicDBObject("$match", new BasicDBObject("caseId", new BasicDBObject("$nin", getCaseIds())));
        DBObject project = new BasicDBObject("$project", new BasicDBObject("caseId", 1).append("operatorId", 1).append("debtCase.debtorName", 1).
                append("auxInfo", new BasicDBObject("$filter", new BasicDBObject("input", "$debtCase.auxInfo").append("as", "auxInfo").append("cond", new BasicDBObject("$eq", new String[]{"$$auxInfo.key", "贷款标的号"})))).
                append("newBatch", new BasicDBObject("$filter", new BasicDBObject("input", "$debtCase.auxInfo").append("as", "newBatch").append("cond", new BasicDBObject("$or",
                        new BasicDBObject[]{new BasicDBObject("$eq", new String[]{"$$newBatch.key", "批次"}), new BasicDBObject("$eq", new String[]{"$$newBatch.key", "委案批次"})})))).
                append("nuonuoName", new BasicDBObject("$filter", new BasicDBObject("input", "$debtCase.auxInfo").append("as", "nuonuoName").append("cond", new BasicDBObject("$or",
                        new BasicDBObject[]{new BasicDBObject("$eq", new String[]{"$$nuonuoName.key", "用户名"}), new BasicDBObject("$eq", new String[]{"$$nuonuoName.key", "诺诺镑客用户名"}), new BasicDBObject("$eq", new String[]{"$$nuonuoName.key", "诺诺用户名"}), new BasicDBObject("$eq", new String[]{"$$nuonuoName.key", "NNBK用户名"})}))))
        );
        DBObject unwind1 = new BasicDBObject("$unwind", new BasicDBObject("path", "$newBatch").append("preserveNullAndEmptyArrays", true));
        DBObject unwind2 = new BasicDBObject("$unwind", new BasicDBObject("path", "$auxInfo").append("preserveNullAndEmptyArrays", true));
        DBObject unwind3 = new BasicDBObject("$unwind", new BasicDBObject("path", "$nuonuoName").append("preserveNullAndEmptyArrays", true));
        DBObject lookup = new BasicDBObject("$lookup", new BasicDBObject("from", "sysUser").append("localField", "operatorId").append("foreignField", "_id").append("as", "sysUser"));
        DBObject unwind4 = new BasicDBObject("$unwind", "$sysUser");
        DBObject project2 = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("caseId", 1).append("debtorName", "$debtCase.debtorName").append("markNum", "$auxInfo.value").append("batch", "$newBatch.value").append("nnName", "$nuonuoName.value").append("userName", "$sysUser.userName"));
        AggregationOutput output = conn.aggregate(Arrays.asList(match, matchCaseId, project, unwind1, unwind2, unwind3, lookup, unwind4, project2));
        Iterator<DBObject> it = output.results().iterator();

        FileOutputStream os = new FileOutputStream(outFilePath);
        HSSFWorkbook wbout = new HSSFWorkbook();
        HSSFSheet sheetout = wbout.createSheet("sheet1");
        this.setHeader(sheetout);//表头

        int row_index = 1;
        while (it.hasNext()) {
            HSSFRow row = sheetout.createRow(row_index);
            BasicDBObject object = (BasicDBObject) it.next();

            List info = getInfoAsList(object);

            int index = 0;
            while (index < info.size()) {
                HSSFCell cell = row.createCell(index);
                if (info.get(index) == null) {
                    cell.setCellValue("");
                } else {
                    cell.setCellValue(info.get(index).toString());
                }
                index++;
            }
            row_index++;
        }
        wbout.write(os);
    }

    public List getInfoAsList(BasicDBObject object) {
        List<String> data = new ArrayList();
        data.add(object.get("caseId") != null ? (String) object.get("caseId") : "");
        data.add(object.get("batch") != null ? (String) object.get("batch") : "");
        data.add(object.get("debtorName") != null ? (String) object.get("debtorName") : "");
        data.add(object.get("nnName") != null ? (String) object.get("nnName") : "");
        data.add(object.get("markNum") != null ? (String) object.get("markNum") : "");
        data.add(object.get("userName") != null ? (String) object.get("userName") : "");
        return data;
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
    }

}
