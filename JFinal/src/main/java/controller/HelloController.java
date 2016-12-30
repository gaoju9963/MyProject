package controller;

import com.jfinal.core.Controller;
import service.HelloService;

/**
 * Created by pengshu on 2016/12/8.
 */
public class HelloController extends Controller {
    public void index() {
        renderText("Hello JFinal World.");
    }

    public void add() {
        HelloService service = new HelloService();
        renderJson(service.find());
    }
}