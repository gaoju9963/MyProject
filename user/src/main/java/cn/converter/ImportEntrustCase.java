package cn.converter;

import com.model.User;

import java.util.List;

/**
 * Created by pengshu on 2016/11/5.
 */
public interface ImportEntrustCase {

    List<User> importExcel(String filePath, String templatePath);
    
}
