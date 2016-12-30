package mongo.converter;

import com.model.User;
import mongo.modle.Employee;

import java.util.List;

/**
 * Created by pengshu on 2016/11/5.
 */
public interface ImportEntrustCase {

    List<Employee> importExcel(String filePath, String templatePath);
    
}
