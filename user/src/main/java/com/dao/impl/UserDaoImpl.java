package com.dao.impl;

import com.dao.UserDao;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengshu on 2016/11/2.
 */
@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUsers() {

        String sql = "select * from user";

        return (List<User>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));

    }

    @Override
    public Boolean addUser(User user) {

        String sql = "insert into user (name,age) values (?,?)";
        jdbcTemplate.update(sql, new Object[]{user.getName(),user.getAge()});
        return true;
    }
}
