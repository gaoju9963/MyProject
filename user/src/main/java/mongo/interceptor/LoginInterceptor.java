package mongo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by pengshu on 2016/12/30.
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI(); //获取请求的URL
        //以下URL可以公开访问
        if (url.indexOf("/user/login") >= 0) {
            return true;
        }
        if (url.indexOf("/user/addPhoneCode") >= 0) {
            return true;
        }
        if (url.indexOf("/user/register") >= 0) {
            return true;
        }
        if (url.indexOf("/user/userNameIsHave") >= 0) {
            return true;
        }
        if (url.indexOf("/user/register") >= 0) {
            return true;
        }
        HttpSession session = request.getSession();//获取Session
        String username = (String) session.getAttribute("userName");
        if (username != null) {
            return true;
        }
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response); //不符合条件的，跳转到登录界面
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
