package mongo.controller;

import mongo.dao.TestDao;
import mongo.modle.Test;
import mongo.util.BaseController;
import mongo.util.JsonApiResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by gaoju on 2016/12/11.
 */
@Controller
@Scope("prototype")
public class TestController extends BaseController {
    @Resource
    TestDao testDao;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/main/main");
        return modelAndView;
    }

    @RequestMapping(value = "/testEmployeePage", method = RequestMethod.GET)
    public ModelAndView testEmployeePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/testEmployee/testEmployeePage");
        modelAndView.addObject("dropDownMenus", testDao.getDropDownMenus());
        return modelAndView;
    }

    @RequestMapping(value = "/getEmployeeTest", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult employeeList(@RequestBody Map map) {
        Integer pageNumber = (Integer) map.get("pageNumber");
        Integer pageSize = (Integer) map.get("pageSize");
        String sortOrder = (String) map.get("sortOrder");
        String sortName = (String) map.get("sortName");
        String searchText = (String) map.get("searchText");
        return bootstrapApiRult(testDao.bootstrapPage(Test.class, pageNumber, pageSize, sortOrder, sortName, searchText));
    }

    @RequestMapping(value = "/addTest", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult addTest(String name, String sex, Integer age) {
        Test test = new Test();
        test.setName(name);
        test.setSex(sex);
        test.setAge(age);
        return apiRult(testDao.addTest(test));
    }

    @RequestMapping(value = "/updateTest", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult updateTest(Integer rowId, String name, String sex, Integer age) {
        Test test = new Test();
        test.setRowId(rowId);
        test.setName(name);
        test.setSex(sex);
        test.setAge(age);
        return apiRult(testDao.updateTest(test));
    }

    @RequestMapping(value = "/deleteTestByRowId", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult deleteTestByRowId(Integer rowId) {
        return apiRult(testDao.deleteTestByRowId(rowId));
    }
}
