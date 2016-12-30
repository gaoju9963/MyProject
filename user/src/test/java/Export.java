import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by pengshu on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/application.xml"})
public class Export {

    @Resource
    MongoTemplate mongoTemplate;

    String outFilePath = "E:\\ceshi.xls";

    HSSFRow rowout = null;
    HSSFCell cellout = null;

    @Test
    public void export() throws IOException {
        FileOutputStream os = new FileOutputStream(outFilePath);
        HSSFWorkbook wbout = new HSSFWorkbook();
        HSSFSheet sheetout = wbout.createSheet("sheet1");

        Iterator<DBObject> it = query().iterator();

        int row = 0;
        while (it.hasNext()) {
            BasicDBObject queryResult = (BasicDBObject) it.next();

            if (row == 0) {//表头
                rowout = sheetout.createRow(row);
                rowout.createCell(0).setCellValue("caseId");
                rowout.createCell(1).setCellValue("催收员Id");
                rowout.createCell(2).setCellValue("客户");
                rowout.createCell(3).setCellValue("手机");
                rowout.createCell(4).setCellValue("身份证");
                rowout.createCell(5).setCellValue("创建时间");
                rowout.createCell(6).setCellValue("手机号");
                rowout.createCell(7).setCellValue("内容");
                rowout.createCell(8).setCellValue("feed");


            }
            rowout = sheetout.createRow(++row);
            rowout.createCell(0).setCellValue(queryResult.getString("caseId"));
            rowout.createCell(1).setCellValue(queryResult.getString("operatorId"));
            rowout.createCell(2).setCellValue(queryResult.getString("debtorName"));
            rowout.createCell(3).setCellValue(queryResult.getString("debtorPhone"));
            rowout.createCell(4).setCellValue(queryResult.getString("debtorCid"));
//            rowout.createCell(5).setCellValue(queryResult.getInt("createDate"));
            rowout.createCell(6).setCellValue(queryResult.getString("phone"));
            rowout.createCell(7).setCellValue(queryResult.getString("content"));
            rowout.createCell(8).setCellValue(queryResult.getString("feedback"));
        }
        wbout.write(os);
    }

    /*
    *   db.caseStatusInfo.aggregate({$match:{$and:[{aOrgId:"00020"},{caseStatus:"follow_up"}]}},
        {$project:{_id:0,caseId:1,operatorId:1}},
        {$lookup:{from:"debtCase",localField:"caseId",foreignField:"caseId",as:"debtCase"}},
        {$unwind:{path:"$debtCase"}},
        {$project:{_id:0,caseId:1,operatorId:1,debtorName:"$debtCase.debtorName",debtorPhone:"$debtCase.debtorPhone",debtorCid:"$debtCase.debtorCid"}},
        {$lookup:{from:"debtCollectEvent",localField:"caseId",foreignField:"caseId",as:"debtCollectEvent"}},
        {$project:{_id:0,caseId:1,operatorId:1,debtorName:"$debtorName",debtorPhone:"$debtorPhone",debtorCid:"$debtorCid",createDate:{$filter: {
         input:"$debtCollectEvent",
         as: "debtCollectEvent",
         cond: {$or:[ {$gt:["$$debtCollectEvent.createDate",ISODate("2016-12-10T00:26:21.277+0000")]},{$eq:["$$debtCollectEvent.createDate",null]},{$eq:["$$debtCollectEvent.createDate",""]}]}
      }},phone:"$debtCollectEvent.phone",content:"$debtCollectEvent.content",feedback:"$debtCollectEvent.feedback"}})
    * */
    public Iterable<DBObject> query() {
//        DBCollection collection = mongoTemplate.getCollection("user");
//        DBObject query = new BasicDBObject("$match", new BasicDBObject("age", 25));
//        AggregationOutput output = collection.aggregate(Arrays.asList(query));
//        return output.results();

        String date = "ISODate('2016-12-10T00:26:21.277+0000')";

        ArrayList arrayList = new ArrayList();
        arrayList.add("$$debtCollectEvent.createDate");
        arrayList.add(new Date());

        Integer pageNumber = 2;
        Integer pageSize = 4000;

            DBCollection collection = mongoTemplate.getCollection("caseStatusInfo");
        DBObject skip = new BasicDBObject("$skip",(pageNumber - 1) * pageSize);
        DBObject limit = new BasicDBObject("$limit",pageSize);

        DBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("caseId", 1).append("operatorId", 1));

        DBObject lookup = new BasicDBObject("$lookup", new BasicDBObject("from", "debtCase").append("localField", "caseId").append("foreignField", "caseId").append("as", "debtCase"));
        DBObject unwind = new BasicDBObject("$unwind", "$debtCase");
        DBObject project2 = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("caseId", 1).append("operatorId", 1).
                append("debtorName", "$debtCase.debtorName").append("debtorPhone", "$debtCase.debtorPhone").append("debtorCid", "$debtCase.debtorCid"));
        DBObject lookup2 = new BasicDBObject("$lookup", new BasicDBObject("from", "debtCollectEvent").append("localField", "caseId").append("foreignField", "caseId").append("as", "debtCollectEvent"));
        DBObject project3 = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("caseId", 1).append("debtorName", "$debtorName").append("debtorPhone", "$debtorPhone").
                append("debtorCid", "$debtorCid").append("phone", "$debtCollectEvent.phone").append("content", "$debtCollectEvent.content").append("feedback", "$debtCollectEvent.feedback"));

        AggregationOutput output = collection.aggregate(Arrays.asList(skip,limit,project, lookup, unwind, project2, lookup2, project3));
        return output.results();
    }

}
