package mongo.dao;

import mongo.modle.PhoneCode;
import mongo.modle.SysUser;

import java.io.IOException;

/**
 * Created by pengshu on 2016/12/8.
 */
public interface SysUserDao extends Basic<SysUser>{

    boolean AddPhoneCode(PhoneCode phoneCode) throws IOException;

    PhoneCode findPhoneCode(PhoneCode phoneCode);

    SysUser findSysUser(SysUser sysUser);

    Object register(SysUser sysUser);

    SysUser login(SysUser sysUser);

    String getNameByMobileOrUserName(String mobileOrUserNames);

}
