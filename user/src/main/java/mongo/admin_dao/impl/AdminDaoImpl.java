package mongo.admin_dao.impl;

import com.mongodb.DBCollection;
import mongo.admin_dao.AdminDao;
import mongo.admin_modle.Administrator;
import mongo.dao.impl.BasicImpl;
import mongo.util.MongoCreateId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pengshu on 2017/1/11.
 */
@Service
public class AdminDaoImpl extends BasicImpl<Administrator> implements AdminDao {

    @Resource
    MongoTemplate mongoTemplate;

    @Override
    public boolean addAdministrator(Administrator administrator) {
        DBCollection collection = mongoTemplate.getCollection("administrator");
        administrator.setRowId(MongoCreateId.createId(collection));
        mongoTemplate.insert(administrator);
        return true;
    }
}
