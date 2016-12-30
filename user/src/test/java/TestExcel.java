/**
 * Created by pengshu on 2016/11/5.
 */


import cn.converter.impl.ImportEntrustCaseImpl;
import cn.service.UserService;
import com.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:/application.xml"})
public class TestExcel {


    String filePath = null;
    String templatePath = null;
    ImportEntrustCaseImpl importEntrustCase = new ImportEntrustCaseImpl();

    @Resource
    UserService userService;

    @Before
    public void init() {
        filePath = "E:\\zzz.xlsx";
//        templatePath = "src\\main\\resources\\excelMapper.json"; //映射模板
        templatePath = this.getClass().getResource("/excelMapper.json").getPath();
    }

    @Test
    public void main() {
        List<User> users = importEntrustCase.importExcel(filePath, templatePath);
        for (User user : users) {
            userService.setUser(user);
        }
    }
}
