package mongo.controller;

import mongo.dao.SysUserDao;
import mongo.modle.PhoneCode;
import mongo.modle.SysUser;
import mongo.util.BaseController;
import mongo.util.JsonApiResult;
import mongo.util.MD5;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;

/**
 * Created by pengshu on 2016/12/9.
 */
@Controller
@Scope("prototype")
public class UserController extends BaseController {

    @Resource
    SysUserDao sysUserDao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/register");
        return modelAndView;
    }

    @RequestMapping(value = "/gotoLogin", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    @RequestMapping(value = "/homePage", method = RequestMethod.GET)
    public ModelAndView homePage(String mobileOrUserName) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home_page");
        modelAndView.addObject("userName", sysUserDao.getNameByMobileOrUserName(mobileOrUserName));//把用户名传给主页面
        return modelAndView;
    }

    @RequestMapping(value = "/addPhoneCode", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult addPhoneCode(String phoneNumber) throws IOException {
        if (phoneNumber == null || phoneNumber == "") {
            return errorApiRult("手机号不能为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setMobile(phoneNumber);
        if (sysUserDao.findSysUser(sysUser) != null) {
            return errorApiRult("该手机已注册。。。");
        }
        PhoneCode phoneCode = new PhoneCode();
        phoneCode.setPhoneNumber(phoneNumber);
        phoneCode.setCode(123456);//这里应该是随机数字
        phoneCode.setCreateTime(new Date());
        return apiRult(sysUserDao.AddPhoneCode(phoneCode));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult register(String phoneNumber, Integer code, String userName, String password) {
        if (phoneNumber == null || phoneNumber == "") {
            return errorApiRult("手机不能为空");
        }
        SysUser sysUser = new SysUser();
        sysUser.setMobile(phoneNumber);
        if (sysUserDao.findSysUser(sysUser) != null) {
            return errorApiRult("该手机已注册，请直接登录。。。");
        }
        PhoneCode phoneCode = new PhoneCode();
        phoneCode.setPhoneNumber(phoneNumber);
        if (sysUserDao.findPhoneCode(phoneCode) == null) {
            return errorApiRult("请先获取验证码");
        }
        if (code == null) {
            return errorApiRult("验证码不能为空");
        }
        phoneCode.setCode(code);
        if (sysUserDao.findPhoneCode(phoneCode) == null) {
            return errorApiRult("验证码有误！请重新输入。。。");
        }
        if (userName == null || userName == "") {
            return errorApiRult("用户名不能为空");
        }
        if (password == null || password == "") {
            return errorApiRult("密码不能为空");
        }
        sysUser.setUserName(userName);
        sysUser.setPassword(MD5.createMD5(password));//对用户密码加密处理
        sysUser.setCreateDate(new Date());
        sysUser.setUpdateDate(new Date());
        return apiRult(sysUserDao.register(sysUser));
    }

    @RequestMapping(value = "/userNameIsHave", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult userNameIsHave(String userName) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        return apiRult(sysUserDao.findSysUser(sysUser));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult login(String mobileOrUserName, String password) {
        SysUser sysUser = new SysUser();
        sysUser.setMobile(mobileOrUserName);
        if (sysUserDao.login(sysUser) == null) {
            return errorApiRult("用户不存在");
        }
        sysUser.setPassword(password);
        SysUser result = sysUserDao.login(sysUser);
        if (result == null) {
            return errorApiRult("用户名和密码不匹配");
        }
        return apiRult(result);
    }

}
