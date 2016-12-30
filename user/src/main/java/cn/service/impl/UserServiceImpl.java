package cn.service.impl;

import cn.service.UserService;
import com.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pengshu on 2016/11/5.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean setUser(User user) {
        String sql = "INSERT INTO `user` (`name`,`age`) VALUES (?,?)";
        int result = jdbcTemplate.update(sql,user.getName(),user.getAge());
        if (result == 1){
            return true;
        }
        return false;
    }
}
