import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * Created by pengshu on 2016/11/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/application.xml"})
public class CloseCase {

    /**
     *
     */
    @Resource
    private static MongoTemplate mongoTemplate;

    private static String batchId = "";

    private static String[] caseId = new String[]{
            "201609021332225853898", "201609021332226304732",
            "201609021332226781318", "201609021332225568748",
            "201609021332226444261", "201609021332227776448",
            "201609021332232366710", "201609021332231591884",
            "201609021332231519045", "201609021332228186004",
            "201609021332228801094", "201609021332232324586"
    };

    public static void closeCase() {
        DBCollection collection = mongoTemplate.getCollection("caseStatusInfo");
        DBObject query = new BasicDBObject("debtCase.batch", batchId).append("caseId", new BasicDBObject("$nin", caseId));
        DBObject update = new BasicDBObject("$set", new BasicDBObject("caseStatus", "cancel"));
        collection.update(query, update, false, true);
    }

    public static void main(String[] args) {
        closeCase();
    }
}
