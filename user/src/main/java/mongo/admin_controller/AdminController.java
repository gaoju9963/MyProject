package mongo.admin_controller;

import mongo.admin_dao.AdminDao;
import mongo.admin_modle.Administrator;
import mongo.util.ApiResult;
import mongo.util.BaseController;
import mongo.util.JsonApiResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by pengshu on 2017/1/11.
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Resource
    AdminDao adminDao;

    @RequestMapping(value = "/admin_index", method = RequestMethod.GET)
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/admin/admin_index");
        return modelAndView;
    }

    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public JsonApiResult adminLogin(String name, String password) {
        Administrator administrator = new Administrator();
        administrator.setName(name);
        administrator.setPassword(password);
        Administrator result = adminDao.adminLogin(administrator);
        if (result == null) {
            return errorApiRult("用户名和密码不匹配");
        }
        return apiRult(result);
    }

    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult addAdmin(String name, String password) {
        Administrator administrator = new Administrator();
        administrator.setName(name);
        administrator.setPassword(password);
        return apiRult(adminDao.addAdministrator(administrator));
    }

}
