package mongo.dao;

import mongo.modle.DropDownMenu;
import mongo.modle.Test;

import java.util.List;

/**
 * Created by pengshu on 2016/12/14.
 */
public interface TestDao extends Basic<Test> {
    boolean addTest(Test test);

    Test updateTest(Test test);

    Boolean deleteTestByRowId(Integer rowId);

    List<DropDownMenu> getDropDownMenus();
}
