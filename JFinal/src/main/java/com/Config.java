package com;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import controller.HelloController;

/**
 * Created by pengshu on 2016/12/8.
 */
public class Config extends JFinalConfig {
    /**
     * com.Config constant
     *
     * @param me
     */
    public void configConstant(Constants me) {
        me.setDevMode(true);
    }

    /**
     * com.Config route
     *
     * @param me
     */
    public void configRoute(Routes me) {
        System.out.println(123);
        me.add("/hello", HelloController.class);
    }

    /**
     * com.Config plugin
     *
     * @param me
     */
    public void configPlugin(Plugins me) {

    }

    /**
     * com.Config interceptor applied to all actions.
     *
     * @param me
     */
    public void configInterceptor(Interceptors me) {

    }

    /**
     * com.Config handler
     *
     * @param me
     */
    public void configHandler(Handlers me) {

    }


    //这个main入口，虽然能启动项目，但是很蛋疼，访问路径404，Tomcat没有这个问题
    public static void main(String[] args) {
        JFinal.start("/src/main/webapp", 80, "/", 5);
    }
}
