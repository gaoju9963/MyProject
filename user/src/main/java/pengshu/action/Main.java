package pengshu.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pengshu.service.CaseService;
import pengshu.service.impl.CaseServiceImpl;

/**
 * Created by pengshu on 2016/11/3.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        CaseService caseService = context.getBean(CaseServiceImpl.class);
        caseService.getCase();

    }
}
