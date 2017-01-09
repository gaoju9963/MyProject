package mongo.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import mongo.dao.NoticeDao;
import mongo.modle.Notice;
import mongo.util.BaseController;
import mongo.util.JsonApiResult;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by gaoju on 2017/1/7.
 */

@Controller
@Scope("prototype")
public class NoticeController extends BaseController {
    @Resource
    NoticeDao noticeDao;

    @RequestMapping(value = "/createNotice", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult creatNotice() {
        String title = "自嘲";
        String content = "运交华盖欲何求？未敢翻身已碰头。" + "\n" +
                "破帽遮颜过闹市，漏船载酒泛中流。" + "\n" +
                "横眉冷对千夫指，俯首甘为孺子牛。" + "\n" +
                "躲进小楼成一统，管他冬夏与春秋。";
        BasicDBObject publisher = new BasicDBObject("userId", 1).append("userName", "admin");
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setCreateDate(new Date());
        notice.setPublisher(publisher);

        return apiRult(noticeDao.creatNotice(notice));
    }

    @RequestMapping(value = "/getNotices", method = RequestMethod.POST)
    @ResponseBody
    public JsonApiResult getNotices() {
        return apiRult(noticeDao.getNotices());
    }

}
