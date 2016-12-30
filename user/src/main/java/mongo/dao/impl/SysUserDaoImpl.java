package mongo.dao.impl;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import jdk.nashorn.internal.objects.annotations.Where;
import mongo.dao.SysUserDao;
import mongo.modle.PhoneCode;
import mongo.modle.SysUser;
import mongo.util.MD5;
import mongo.util.SendMsg;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import static mongo.util.MD5.createMD5;

/**
 * Created by pengshu on 2016/12/8.
 */
@Service
public class SysUserDaoImpl extends BasicImpl<SysUser> implements SysUserDao {

    @Override
    public boolean AddPhoneCode(PhoneCode phoneCode) throws IOException {
        /**
         * 注释掉的为发送短信验证码，调用的是中国网建的SMS短信接口，费用好像是一条一毛钱。。。#_#
         */
//        int code = (int) ((Math.random() * 9 + 1) * 100000);
//        phoneCode.setCode(code);
        mongoTemplate.insert(phoneCode);
//        String message = "注册验证码：" + code;
//        SendMsg.sendMessage(phoneCode.getPhoneNumber(), message);
        return true;
    }

    @Override
    public PhoneCode findPhoneCode(PhoneCode phoneCode) {
        Query query = new Query(Criteria.where("phoneNumber").is(phoneCode.getPhoneNumber()));
        if (phoneCode.getCode() != null) {
            query.addCriteria(Criteria.where("code").is(phoneCode.getCode()));
        }
        return mongoTemplate.findOne(query, PhoneCode.class);
    }

    @Override
    public SysUser findSysUser(SysUser sysUser) {
        Query query = new Query();
        Criteria cr = new Criteria();
        query.addCriteria(cr.orOperator(
                Criteria.where("mobile").is(sysUser.getMobile()),
                Criteria.where("userName").is(sysUser.getUserName()))
        );
        return mongoTemplate.findOne(query, SysUser.class);
    }

    @Override
    public SysUser register(SysUser sysUser) {
        sysUser.setUserId(createId());
        return super.add(sysUser);
    }

    public int createId() {
        Integer userId = 1;

        DBCollection collection = mongoTemplate.getCollection("sysUser");
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("userId", -1));
        DBObject project = new BasicDBObject("$project", new BasicDBObject("_id", 0).append("userId", "$userId"));
        DBObject limit = new BasicDBObject("$limit", 1);

        AggregationOutput output = collection.aggregate(Arrays.asList(sort, project, limit));
        if (output != null) {
            Iterator<DBObject> it = output.results().iterator();
            if (it.hasNext()) {
                return Integer.parseInt(it.next().get("userId").toString()) + 1;
            }
        }
        return userId;
    }

    @Override
    public SysUser login(SysUser sysUser) {
        Query query = new Query();
        Criteria cr = new Criteria();
        query.addCriteria(cr.orOperator(
                Criteria.where("mobile").is(sysUser.getMobile()),
                Criteria.where("userName").is(sysUser.getMobile()))
        );
        if (sysUser.getPassword() != null && sysUser.getPassword() != "") {
            String mD5 = MD5.createMD5(sysUser.getPassword());//用户密码转为MD5，由转换后的MD5对比数据库
            query.addCriteria(Criteria.where("password").is(mD5));
        }
        return mongoTemplate.findOne(query, SysUser.class);
    }

    @Override
    public String getNameByMobileOrUserName(String mobileOrUserNames) {
        Query query = new Query();
        Criteria cr = new Criteria();
        query.addCriteria(cr.orOperator(
                Criteria.where("mobile").is(mobileOrUserNames),
                Criteria.where("userName").is(mobileOrUserNames))
        );
        return mongoTemplate.findOne(query, SysUser.class).getUserName();
    }
}
