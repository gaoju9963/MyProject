package com.action;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by pengshu on 2016/11/2.
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        UserDao userDao = (UserDao) context.getBean("userDaoImpl");
//        List<User> list =  userDao.getUsers();
//        for(User u : list){
//            System.out.println(u.getId()+"---"+u.getName());
//        }

        User user = (User) context.getBean("user");
        user.setName("施祺");
        user.setAge(22);
        userDao.addUser(user);

    }
}
