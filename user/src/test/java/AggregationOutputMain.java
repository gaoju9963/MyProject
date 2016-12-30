import com.mongodb.*;
import mongo.dao.EmployeeDao;
import mongoUtil.MongoAgra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by pengshu on 2016/11/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/application.xml"})
public class AggregationOutputMain {
    @Resource
    public MongoTemplate mongoTemplate;

    public Iterable<DBObject> find() {

        /**
         *  db.gong.aggregate([
         *  {$match:{"map.key1":"1"}},
         *  {$project:{map1:"$map.key1",newTab:{$filter:{input:"$newTab",as:"tab",cond:{$eq:["$$tab.tab1","测试"]}}}}},
         *  {$unwind:{path:"$newTab",preserveNullAndEmptyArrays:true}},
         *  {$project:{_id:0,"标签":"$newTab.tab1",map1:1}}
         *  ])
         */

        DBCollection collection = mongoTemplate.getCollection("gong");

        DBObject match = new BasicDBObject("$match",
                new BasicDBObject("map.key1", "1"));

        DBObject project = new BasicDBObject("$project",
                new BasicDBObject("map1", "$map.key1")
                        .append("newTab",
                                new BasicDBObject("$filter",
                                        new BasicDBObject("input", "$newTab")
                                                .append("as", "tab").append("cond",
                                                new BasicDBObject("$eq", new String[]{"$$tab.tab1", "测试"})))));

        DBObject unwind = new BasicDBObject("$unwind",
                new BasicDBObject("path", "$newTab")
                        .append("preserveNullAndEmptyArrays", true));

        DBObject project2 = new BasicDBObject("$project",
                new BasicDBObject("_id", 0)
                        .append("标签", "$newTab.tab1")
                        .append("map1", 1));

        AggregationOutput output = collection.aggregate(Arrays.asList(match, project, unwind, project2));
        return output.results();
    }

    public Iterable<DBObject> findDebtCollectEvent() {
        /**
         * db.debtCase.aggregate([
         * {$match:{batch:"d2e6115c829c4b0eac8763ac5043764f_20161102"}},
         * {$project:{caseId:"$caseId",name:"$debtorName"}},
         * {$lookup:{from:"debtCollectEvent",localField:"caseId",foreignField:"caseId",as:"debtCollectEvent"}},
         * {$unwind:{path:"$debtCollectEvent"}},
         * {$project:{_id:0,caseId:"$caseId",name:"$name",feedback:"$debtCollectEvent.feedback",content:"$debtCollectEvent.content",createDate:"$debtCollectEvent.createDate"}}
         * ])
         */
        DBCollection collection = mongoTemplate.getCollection("debtCase");

        DBObject match = new BasicDBObject("$match", new BasicDBObject("batch", "d2e6115c829c4b0eac8763ac5043764f_20161102"));

        DBObject project = new BasicDBObject("$project", new BasicDBObject("caseId", "$caseId").append("name", "$debtorName"));

        DBObject lookUp = new BasicDBObject("$lookup", new BasicDBObject("from", "debtCollectEvent")
                .append("localField", "caseId")
                .append("foreignField", "caseId")
                .append("as", "debtCollectEvent"));
        DBObject unwind = new BasicDBObject("$unwind", new BasicDBObject("path", "$debtCollectEvent"));

        DBObject project2 = new BasicDBObject("$project", new BasicDBObject("_id", 0)
                .append("caseId", "$caseId")
                .append("name", "$name")
                .append("feedback", "$debtCollectEvent.feedback")
                .append("content", "$debtCollectEvent.content")
                .append("createDate", "$debtCollectEvent.createDate")
        );

        AggregationOutput output = collection.aggregate(Arrays.asList(match, project, lookUp, unwind, project2));
        return output.results();
    }

    public void getUsers() {
        DBCollection collection = mongoTemplate.getCollection("users");

        DBObject query = new BasicDBObject("points", new BasicDBObject("$elemMatch", new BasicDBObject("points", new BasicDBObject("$lte", 70))));

        DBCursor cursor = collection.find(query).sort(new BasicDBObject("age", -1));
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }


    @Test
    public void main() {
//        System.out.println(find());

//        System.out.println(findDebtCollectEvent());

        getUsers();
    }

    @Resource
    EmployeeDao employeeDao;


}
