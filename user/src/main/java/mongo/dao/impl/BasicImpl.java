package mongo.dao.impl;

import mongo.dao.Basic;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengshu on 2016/11/9.
 */
@Service
public abstract class BasicImpl<T> implements Basic<T> {

    //实体类
    private Class<T> clazz;

    //数据库表名
    protected String collectionName = "";

    @Resource
    protected MongoTemplate mongoTemplate;

    public BasicImpl() {
        ParameterizedType type = (ParameterizedType) getClass()
                .getGenericSuperclass();

        clazz = (Class<T>) type.getActualTypeArguments()[0];

        if (Character.isLowerCase(clazz.getSimpleName().charAt(0))) {
            collectionName = clazz.getSimpleName();
        } else {
            collectionName = (new StringBuilder())
                    .append(Character.toLowerCase(clazz.getSimpleName().charAt(0)))
                    .append(clazz.getSimpleName().substring(1)).toString();
        }
    }

    @Override
    public Map bootstrapPage(Class cla, Integer pageNumber, Integer pageSize, String sortOrder, String sortName, String searchText) {

        Query query = new Query();
        if (sortName != null) {
            if (sortOrder.equals("desc")) {
                query.with(new Sort(Sort.Direction.DESC, sortName));
            } else {
                query.with(new Sort(Sort.Direction.ASC, sortName));
            }
        }
        if (searchText != null) {
            query.addCriteria(Criteria.where("name").regex(searchText));//模糊查询
        }
        long totalCount = mongoTemplate.count(query, cla);
        query.skip((pageNumber - 1) * pageSize).limit(pageSize);

        Map map = new HashMap();
        map.put("total", totalCount);
        map.put("result", mongoTemplate.find(query, cla));
        return map;
    }

    @Override
    public T add(T clazz) {
        mongoTemplate.insert(clazz);
        return clazz;
    }

    @Override
    public List<T> add(List<T> clazz) {
        mongoTemplate.insertAll(clazz);
        return clazz;
    }
}
