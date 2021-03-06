package mongo.admin_dao;

import mongo.admin_modle.Administrator;
import mongo.dao.Basic;

import java.util.List;

/**
 * Created by pengshu on 2017/1/11.
 */
public interface AdminDao extends Basic<Administrator> {
    boolean addAdministrator(Administrator administrator);

    Administrator adminLogin(Administrator administrator);

    List<Administrator> listAdmin();
}
