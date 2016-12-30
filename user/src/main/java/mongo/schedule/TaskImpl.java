package mongo.schedule;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

/**
 * Created by pengshu on 2016/12/1.
 */
@Service
@Scope("prototype")//非单例模式
public class TaskImpl extends TimerTask {
    @Resource
    MongoTemplate mongoTemplate;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        DBCollection collection = mongoTemplate.getCollection("scheduled");

        DBObject object = new BasicDBObject();
        object.put("scheduled", "测试定时任务");
        object.put("creatTime", new Date());
        collection.insert(object);

        DBObject query = new BasicDBObject();
        query.put("scheduled", "测试定时任务");
//        collection.find(query);
        DBCursor cursor = collection.find(query);
        cursor.size();
        while (cursor.hasNext()) {
            System.out.println(dateFormat.format(cursor.next().get("creatTime")));
        }

        System.out.println("定时方法执行完成：" + dateFormat.format(new Date()));
    }
}
