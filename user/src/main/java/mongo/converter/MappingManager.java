package mongo.converter;

import mongo.function.TwoArg;

import java.util.List;

/**
 * Created by pengshu on 2016/11/5.
 */
public interface MappingManager {
    void mapHeader(List<String> arr) throws Exception;

    void mapValue(List<TwoArg> arr) throws Exception;

    List getResult();
}
