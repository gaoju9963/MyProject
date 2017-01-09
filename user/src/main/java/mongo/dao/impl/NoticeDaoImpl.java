package mongo.dao.impl;

import com.mongodb.DBCollection;
import mongo.dao.NoticeDao;
import mongo.modle.Notice;
import mongo.util.MongoCreateId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gaoju on 2017/1/7.
 */
@Service
public class NoticeDaoImpl extends BasicImpl<Notice> implements NoticeDao {

    @Resource
    MongoTemplate mongoTemplate;

    @Override
    public boolean creatNotice(Notice notice) {
        DBCollection collection = mongoTemplate.getCollection("notice");
        notice.setRowId(MongoCreateId.createId(collection));
        mongoTemplate.insert(notice);
        return true;
    }

    @Override
    public List<Notice> getNotices() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "creatDate"));
        query.limit(10);
        return mongoTemplate.find(query,Notice.class);
    }
}
