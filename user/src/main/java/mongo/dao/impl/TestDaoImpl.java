package mongo.dao.impl;

import mongo.dao.TestDao;
import mongo.modle.DropDownMenu;
import mongo.modle.Test;
import mongo.util.MongoCreateId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pengshu on 2016/12/14.
 */
@Service
public class TestDaoImpl extends BasicImpl<Test> implements TestDao {

    @Override
    public boolean addTest(Test test) {
        test.setRowId(MongoCreateId.createId(mongoTemplate.getCollection("test")));
        test.setCreateDate(new Date());
        test.setUpdateDate(new Date());
        mongoTemplate.insert(test);
        return true;
    }

    @Override
    public Test updateTest(Test test) {
        Query query = new Query(Criteria.where("rowId").is(test.getRowId()));
        Update update = new Update();
        update.set("name", test.getName());
        update.set("sex", test.getSex());
        update.set("age", test.getAge());
        update.set("updateDate", new Date());

        mongoTemplate.updateFirst(query, update, Test.class);

        return mongoTemplate.findOne(query, Test.class);
    }

    @Override
    public Boolean deleteTestByRowId(Integer rowId) {
        Query query = new Query(Criteria.where("rowId").is(rowId));
        mongoTemplate.remove(query, Test.class);
        return true;
    }


    @Override
    public List<DropDownMenu> getDropDownMenus() {
        return mongoTemplate.find(null,DropDownMenu.class);
    }
}
