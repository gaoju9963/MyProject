package mongo.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by pengshu on 2016/11/9.
 */
public interface Basic<T> {
    //bootstrap分页查询
    Map bootstrapPage(Class cla, Integer pageNumber, Integer pageSize, String sortOrder, String sortName, String searchText);

    //参数 实体类
    T add(T clazz) throws Exception;

    //插入一组数据
    List<T> add(List<T> clazz);
}
