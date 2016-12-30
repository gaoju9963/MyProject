package pengshu.service.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import pengshu.model.Case;
import pengshu.model.NewCase;
import pengshu.service.CaseService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pengshu on 2016/11/3.
 */
@Service
public class CaseServiceImpl implements CaseService {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    NewCase newCase;

    //批次的数量
    private int sum;

    @Override
    public List<Case> getCase() {
        String sql = "SELECT * FROM `case`";

        List<Case> caseList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Case.class));
        System.out.println("共有" + caseList.size() + "条数据");

        //遍历获取的机构
        for (Case c : caseList) {
            //获取批次号，如果批次号为空，机构、年、月、日都为空
            if (c.getBatchId().equals("")) {
                newCase.setCompany("");
                newCase.setYear("");
                newCase.setMonth("");
                newCase.setDay("");
            } else {
                //批次号不为空，分割
                String[] batchId = c.getBatchId().split("_");
                newCase.setCompany(batchId[0]);
                newCase.setYear(batchId[1].substring(0, 4));
                newCase.setMonth(batchId[1].substring(4, 6));
                newCase.setDay(batchId[1].substring(6, 8));
            }
            //把分割后的数据存入newcase表
            String newCaseSql = "insert into newcase (`company`,`year`,`month`,`day`,`count`,`outsourceAmount`) values (?,?,?,?,?,?)";
            int result = jdbcTemplate.update(newCaseSql,
                    newCase.getCompany(), newCase.getYear(), newCase.getMonth(), newCase.getDay(),
                    c.getCount(), c.getOutsourceAmount());

            if (result == 1) {
                System.out.println("公司" + " " + "年" + " " + "月" + " " + "日" + " " + "总量" + " " + "委外金额");
                System.out.println(newCase.getCompany() + " " + newCase.getYear() + " " + newCase.getMonth() + " " + newCase.getDay() + " " + c.getCount() + " " + c.getOutsourceAmount());
                sum++;
            }
        }
        System.out.println("执行的条数：" + sum + "条");
        return caseList;
    }

    @Override
    public boolean truncateTableNewCase() {
        String truncateTableNewCaseSql = "TRUNCATE TABLE newcase";
        jdbcTemplate.update(truncateTableNewCaseSql);
        return true;
    }

    @Override
    public boolean truncateTableCase() {
        String truncateTableCaseSql = "TRUNCATE TABLE `case`";
        jdbcTemplate.update(truncateTableCaseSql);
        return true;
    }
}
