package mongo.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by pengshu on 2016/11/8.
 */
public class GlobTools {
    public static <T> DBObject bean2DBObject(T bean) throws IllegalArgumentException,
            IllegalAccessException {
        if (bean == null) {
            return null;
        }
        DBObject dbObject = new BasicDBObject();
        // 获取对象对应类中的所有属性域
        List<Field> fieldList = new ArrayList<>();
        Collections.addAll(fieldList, bean.getClass().getDeclaredFields());
        Class clazz = bean.getClass().getSuperclass();
        if (clazz != null)
            Collections.addAll(fieldList, clazz.getDeclaredFields());
        for (Field field : fieldList) {
            // 获取属性名
            String varName = field.getName();
            if (varName.equals("serialVersionUID"))
                continue;
            // 修改访问控制权限
            boolean accessFlag = field.isAccessible();
            if (!accessFlag) {
                field.setAccessible(true);
            }
            Object param = field.get(bean);
            if (param == null) {
                continue;
            } else if (param instanceof Integer) {//判断变量的类型
                int value = (Integer) param;
                dbObject.put(varName, value);
            } else if (param instanceof String) {
                String value = (String) param;
                dbObject.put(varName, value);
            } else if (param instanceof Double) {
                double value = (Double) param;
                dbObject.put(varName, value);
            } else if (param instanceof Float) {
                float value = (Float) param;
                dbObject.put(varName, value);
            } else if (param instanceof Long) {
                long value = (Long) param;
                dbObject.put(varName, value);
            } else if (param instanceof Boolean) {
                boolean value = (Boolean) param;
                dbObject.put(varName, value);
            } else if (param instanceof Date) {
                Date value = (Date) param;
                dbObject.put(varName, value);
            } else if (param instanceof List) {
                List value = (List) param;
                dbObject.put(varName, value);
            } else if (param instanceof Map) {
                Map value = (Map) param;
                dbObject.put(varName, value);
            } else if (param instanceof Enum) {
                String value = param.toString();
                dbObject.put(varName, value);
            }

            // 恢复访问控制权限
            field.setAccessible(accessFlag);
        }
        return dbObject;
    }
}
