package mongo.service.impl;

import mongo.dao.EmployeeDao;
import mongo.modle.Employee;
import mongo.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by pengshu on 2016/11/7.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Resource
    EmployeeDao employeeDao;

    @Override
    public boolean insert() {
    Employee employee = new Employee();

        employee.setName("李白");
        employee.setAge(24);
        employeeDao.add(employee);
        return true;
    }
}
