package mongo.dao;

import mongo.modle.Notice;

import java.util.List;

/**
 * Created by gaoju on 2017/1/7.
 */
public interface NoticeDao extends Basic<Notice> {
    boolean creatNotice(Notice notice);

    List<Notice> getNotices();
}
