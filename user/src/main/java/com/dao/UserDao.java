package com.dao;

import com.model.User;

import java.util.List;

/**
 * Created by pengshu on 2016/11/2.
 */

public interface UserDao {
    List<User> getUsers();

    Boolean addUser(User user);

}
