package mongo.admin_dao.impl;

import com.mongodb.DBCollection;
import mongo.admin_dao.AdminDao;
import mongo.admin_modle.Administrator;
import mongo.dao.impl.BasicImpl;
import mongo.util.MongoCreateId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public Administrator adminLogin(Administrator administrator) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(administrator.getName()))
                .addCriteria(Criteria.where("password").is(administrator.getPassword()));
        return mongoTemplate.findOne(query, Administrator.class);
    }

    @Override
    public List<Administrator> listAdmin() {
        return mongoTemplate.find(null,Administrator.class);
    }
}
