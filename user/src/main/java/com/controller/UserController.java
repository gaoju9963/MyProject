package com.controller;

import com.dao.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by pengshu on 2016/11/2.
 */
@Controller
@Scope("prototype")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ModelAndView getUsers() {
        List<User> list = userDao.getUsers();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user");
        mv.addObject("list", list);
        return mv;
    }
}
