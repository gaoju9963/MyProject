package mongo.dao.impl;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import mongo.dao.EmployeeDao;
import mongo.modle.DropDownMenu;
import mongo.modle.Employee;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by pengshu on 2016/11/7.
 */
@Service
public class EmployeeDaoImpl extends BasicImpl<Employee> implements EmployeeDao {


    @Override
    public Employee add(Employee employee) {
        employee.setEmpId(createId());
        return super.add(employee);
    }

    public int createId() {
        Integer empId = 1;

        DBCollection collection = mongoTemplate.getCollection("employee");
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("empId", -1));
        DBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("empId", "$empId"));
        DBObject limit = new BasicDBObject("$limit", 1);

        AggregationOutput output = collection.aggregate(Arrays.asList(sort, project, limit));
        if (output != null) {
            Iterator<DBObject> it = output.results().iterator();
            if (it.hasNext()) {
                return Integer.parseInt(it.next().get("empId").toString()) + 1;
            }
        }
        return empId;
    }


    @Override
    @ResponseBody
    public Employee getEmploee(Integer id) {
        Query query = new Query(Criteria.where("empId").is(id));
        return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public List<Employee> getEmploee() {
        return mongoTemplate.findAll(Employee.class);
    }

    @Override
    public Map getEmploee(Integer pageNum, Integer pageSize, String name) {

        Query query = new Query();

        //获取总条数
        long totalCount;
        if (name == null || name == "") {
            totalCount = mongoTemplate.count(null, Employee.class);
        } else {
            //模糊查询
            query = new Query(Criteria.where("name").regex(name));
            totalCount = mongoTemplate.count(query, Employee.class);
        }
        //总页数
        int totalPage = totalCount % pageSize == 0 ? (int) (totalCount / pageSize) : (int) (totalCount / pageSize) + 1;
        query.skip((pageNum - 1) * pageSize).limit(pageSize);

        Map map = new HashMap();
        map.put("totalPage", totalPage);
        map.put("list", mongoTemplate.find(query, Employee.class));
        return map;
    }

    @Override
    public Employee update(Employee employee) throws IllegalAccessException {
        Query query = new Query(Criteria.where("empId").is(employee.getEmpId()));

        Update update = new Update();
        update.set("name", employee.getName());
        update.set("age", employee.getAge());
        update.set("sex", employee.getSex());

        mongoTemplate.updateFirst(query, update, Employee.class);
        return mongoTemplate.findOne(query, Employee.class);
    }

    @Override
    public Boolean deleteById(Employee employee) {
        Query query = new Query(Criteria.where("empId").is(employee.getEmpId()));
        mongoTemplate.remove(query, Employee.class);
        return true;
    }

}
