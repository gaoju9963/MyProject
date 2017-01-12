package mongo.admin_controller;

import mongo.admin_dao.AdminDao;
import mongo.util.BaseController;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

import static org.apache.xmlbeans.impl.store.Public2.test;

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

}
