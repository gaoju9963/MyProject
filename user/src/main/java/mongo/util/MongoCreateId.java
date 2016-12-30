package mongo.util;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by pengshu on 2016/12/15.
 */

public class MongoCreateId {

    public static Integer createId(DBCollection collection) {
        Integer rowId = 1;
//        DBCollection collection = mongoTemplate.getCollection("employee");
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("rowId", -1));
        DBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("rowId", "$rowId"));
        DBObject limit = new BasicDBObject("$limit", 1);

        AggregationOutput output = collection.aggregate(Arrays.asList(sort, project, limit));
        if (output != null) {
            Iterator<DBObject> it = output.results().iterator();
            if (it.hasNext()) {
                return Integer.parseInt(it.next().get("rowId").toString()) + 1;
            }
        }
        return rowId;
    }
}
