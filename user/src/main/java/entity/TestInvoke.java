package entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by pengshu on 2016/11/15.
 */
public class TestInvoke {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        User user = new User();
//        Class c = user.getClass();
        Class c1 = Class.forName("entity.User");
        //访问私有变量
//        Field field = c.getDeclaredField("name");
//        field.setAccessible(true);//抑制Java对修饰符的检测
//        field.set(user, "ni");
//        System.out.println(user.getName());
        //
        Method method = user.getClass().getDeclaredMethod("get");
        method.setAccessible(true); // 抑制Java的访问控制检查
        method.invoke(user);


    }
}
