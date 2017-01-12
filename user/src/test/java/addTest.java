
import mongo.admin_dao.AdminDao;
import mongo.admin_modle.Administrator;
import mongo.modle.DropDownMenu;
import mongo.modle.Test;
import mongo.util.MongoCreateId;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by pengshu on 2016/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/application.xml"})
public class addTest {

    @Resource
    public MongoTemplate mongoTemplate;

    @org.junit.Test
    public void add() {

        for (int i = 0; i < 10; i++) {
            Test test = new Test();
            test.setRowId(MongoCreateId.createId(mongoTemplate.getCollection("test")));
            test.setName("傻了");
            test.setSex("男");
            test.setAge(23);
            mongoTemplate.insert(test);
        }
    }

    @org.junit.Test
    public void dropDownMenu() {
        for (int i = 0; i < 10; i++) {
            DropDownMenu dropDownMenu = new DropDownMenu();
            dropDownMenu.setRowId(MongoCreateId.createId(mongoTemplate.getCollection("dropDownMenu")));
            dropDownMenu.setKey(Integer.toString(i));
            dropDownMenu.setValue("测试" + i);
            mongoTemplate.insert(dropDownMenu);
        }
    }

    @Resource
    AdminDao adminDao;

    @org.junit.Test
    public void addAdmin() throws Exception {
        Administrator administrator = new Administrator();
        administrator.setName("admin");
        administrator.setPassword("123456");
        administrator.setCreateDate(new Date());
        adminDao.add(administrator);
    }

}
