package service;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengshu on 2016/12/8.
 */
public class HelloService {

    public Map find() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("user");

        MongoCollection<Document> collection = mongoDatabase.getCollection("employee");

        FindIterable<Document> iterable = collection.find();
        MongoCursor cursor = iterable.iterator();
        List<Object> list = new ArrayList<Object>();
        while (cursor.hasNext()) {
            Document obj = (Document) cursor.next();
            list.add(obj);
        }

        Map map = new HashMap();
        map.put("total", collection.count());
        map.put("value", list);

        return map;
    }
}
