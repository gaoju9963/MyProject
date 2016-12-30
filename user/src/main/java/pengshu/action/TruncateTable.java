package pengshu.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pengshu.service.CaseService;
import pengshu.service.impl.CaseServiceImpl;

/**
 * Created by pengshu on 2016/11/3.
 * <p>
 * 删除并重置表格
 */
public class TruncateTable {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        CaseService caseService = context.getBean(CaseServiceImpl.class);

        //删除重置case表
        if (caseService.truncateTableCase()) {
            System.out.println("case表重置成功！");
        }

        //删除重置newcase表
        if (caseService.truncateTableNewCase()) {
            System.out.println("newcase表重置成功！");
        }
    }
}
