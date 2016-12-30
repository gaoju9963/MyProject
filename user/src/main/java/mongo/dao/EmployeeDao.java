package mongo.dao;

import mongo.modle.DropDownMenu;
import mongo.modle.Employee;

import java.util.List;
import java.util.Map;

/**
 * Created by pengshu on 2016/11/7.
 */
public interface EmployeeDao extends Basic<Employee>{
    Employee add(Employee employee);

    Employee getEmploee(Integer id);

    List<Employee> getEmploee();

    Map getEmploee(Integer pageNum, Integer pageSize,String name);

    Employee update(Employee employee) throws IllegalAccessException;

    Boolean deleteById(Employee employee);

}
