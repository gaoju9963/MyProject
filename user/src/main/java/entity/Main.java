package entity;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * Created by pengshu on 2016/11/14.
 */
public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("user");
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");

        User user = new User();
        user.setName("李白");
        user.setSex("男");
        user.setAge(24);
        user.setCreateDate(new Date());

        collection.insertOne(testReflect(user));
    }


    public static Document testReflect(Object model) throws SecurityException, NoSuchMethodException,
            IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Document document = new Document();
        // 获取实体类的所有属性，返回Field数组
        Field[] field = model.getClass().getDeclaredFields();

        for (int i = 0; i < field.length; i++) {
            // 获取属性的名字
            String name = field[i].getName();
            // 获取属性类型
            String type = field[i].getGenericType().toString();
            //关键。。。可访问私有变量
            field[i].setAccessible(true);
            // 将属性的首字母大写
            String UpperName = name.replaceFirst(name.substring(0, 1), name.substring(0, 1).toUpperCase());

            if (type.equals("class java.lang.String")) {
                // 如果type是类类型，则前面包含"class "，后面跟类名
                Method m = model.getClass().getMethod("get" + UpperName);
                // 调用getter方法获取属性值
                String value = (String) m.invoke(model);
                if (value != null) {
                    document.put(name, value);
                }
            }
            if (type.equals("class java.lang.Integer")) {
                Method m = model.getClass().getMethod("get" + UpperName);
                Integer value = (Integer) m.invoke(model);
                if (value != null) {
                    document.put(name, value);
                }
            }
            if (type.equals("class java.lang.Short")) {
                Method m = model.getClass().getMethod("get" + UpperName);
                Short value = (Short) m.invoke(model);
                if (value != null) {
                    document.put(name, value);
                }
            }
            if (type.equals("class java.lang.Double")) {
                Method m = model.getClass().getMethod("get" + UpperName);
                Double value = (Double) m.invoke(model);
                if (value != null) {
                    document.put(name, value);
                }
            }
            if (type.equals("class java.lang.Boolean")) {
                Method m = model.getClass().getMethod("get" + UpperName);
                Boolean value = (Boolean) m.invoke(model);
                if (value != null) {
                    document.put(name, value);
                }
            }
            if (type.equals("class java.util.Date")) {
                Method m = model.getClass().getMethod("get" + UpperName);
                Date value = (Date) m.invoke(model);
                if (value != null) {
                    document.put(name, value);
                }
            }
        }
        return document;
    }
}
