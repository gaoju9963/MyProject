package mongo.controller;


import mongo.converter.impl.ImportEntrustCaseImpl;
import mongo.dao.EmployeeDao;
import mongo.modle.Employee;
import mongo.schedule.TaskImpl;
import mongo.util.ApiResult;
import mongo.util.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pengshu on 2016/11/15.
 */
@Controller
@Scope("prototype")
public class EmployeeController extends BaseController {

    @Resource
    EmployeeDao employeeDao;
    @Resource
    TaskImpl taskImpl;

    @RequestMapping(value = "/employeeView", method = RequestMethod.GET)
    public ModelAndView employeeView() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/employee");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int time = 10;
        System.out.println(time + "秒之后将会存入scheduled表中一条数据：" + dateFormat.format(new Date()));
        Timer timer = new Timer();
        timer.schedule(taskImpl, time * 1000);

        return modelAndView;
    }

    @RequestMapping(value = "/getEmployee", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult employeeList(@RequestBody Map map) {
        Integer pageNumber = (Integer) map.get("pageNumber");
        Integer pageSize = (Integer) map.get("pageSize");
        String name = (String)map.get("name");
        System.out.println(apiRult(employeeDao.getEmploee(pageNumber, pageSize, name)));
        return apiRult(employeeDao.getEmploee(pageNumber, pageSize, name));
    }

    @RequestMapping(value = "/getEmployeeById", method = RequestMethod.POST)
    @ResponseBody
    public Employee getEmployeeById(Integer id) {
        Employee Employees = employeeDao.getEmploee(id);
        Employees.setCreateDate(new Date());
        return Employees;
    }

    @RequestMapping(value = "/delEmployeeById", method = RequestMethod.POST)
    @ResponseBody
    public Map del(Integer id) {
        Employee employee = new Employee();
        employee.setEmpId(id);
        Map map = new HashMap();
        map.put("employee", employee);
        if (employeeDao.deleteById(employee) == true) {
            return map;
        }
        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map editEmployeeById(Integer empId, String name, String sex, Integer age) throws IllegalAccessException {
        Employee employee = new Employee();
        employee.setEmpId(empId);
        employee.setName(name);
        employee.setSex(sex);
        employee.setAge(age);

        Map map = new HashMap();
        map.put("result", employeeDao.update(employee));
        return map;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map add(String name, String sex, Integer age) throws Exception {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSex(sex);
        employee.setAge(age);
        employee.setCreateDate(new Date());
        Map map = new HashMap();
        map.put("result", employeeDao.add(employee));
        return map;
    }

    @RequestMapping(value = "/employeeOperateView", method = RequestMethod.GET)
    public ModelAndView employeeOperateView(Integer id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/employeeOperate");
        modelAndView.addObject("id", id);
        return modelAndView;
    }


    @RequestMapping(value = "/tankuang", method = RequestMethod.GET)
    public ModelAndView tankuang() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/tankuang");
        return modelAndView;
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public ModelAndView importData() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/import");
        return modelAndView;
    }

    //导入excel数据
    @RequestMapping(value = "/importEmployee", method = RequestMethod.POST)
    @ResponseBody
    public Map importEmployee(String filePath) throws Exception {
        ImportEntrustCaseImpl importEntrustCase = new ImportEntrustCaseImpl();
//        String templatePath = "src\\main\\resources\\excelMapper.json"; //映射模板
        String templatePath = this.getClass().getResource("/excelMapper.json").getFile();

        List<Employee> employees = importEntrustCase.importExcel(filePath, templatePath);
        for (Employee employee : employees) {
            employee.setCreateDate(new Date());
            employeeDao.add(employee);
        }
        Map map = new HashMap();
        map.put("result", "true");
        return map;
    }

//    @Resource
//    UserService userService;
//    @RequestMapping(value = "/importUser", method = RequestMethod.POST)
//    public Map importUser() {
//        String filePath = "E:\\zzz.xlsx";
////        String  templatePath = "src\\main\\resources\\excelMapper.json"; //映射模板
//        String templatePath = this.getClass().getResource("/excelMapper.json").getPath();
//        ImportEntrustCaseImpl importEntrustCase = new ImportEntrustCaseImpl();
//        List<User> users = importEntrustCase.importExcel(filePath, templatePath);
//        for (User user : users) {
//            userService.setUser(user);
//        }
//
//        Map map = new HashMap();
//        map.put("result", true);
//        return map;
//    }

    @RequestMapping(value = "/scheduled", method = RequestMethod.GET)
    public ModelAndView scheduled() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/scheduled");
        return modelAndView;
    }

}
