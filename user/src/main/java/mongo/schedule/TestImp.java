package mongo.schedule;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by pengshu on 2016/11/28.
 */
@Service
public class TestImp {

    @Resource
    MongoTemplate mongoTemplate;

    @Scheduled(cron = "0 10 15 ? * *")
    public void fixTime() {
        DBCollection collection = mongoTemplate.getCollection("scheduled");

        DBObject object = new BasicDBObject();
        object.put("scheduled", "测试定时任务");
        object.put("creatTime", new Date());

        collection.insert(object);

    }
}
