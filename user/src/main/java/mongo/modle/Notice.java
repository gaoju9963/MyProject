package mongo.modle;

import com.mongodb.BasicDBObject;

import java.util.Date;

/**
 * Created by gaoju on 2017/1/7.
 */
public class Notice {
    private Integer rowId;
    private String title;
    private String content;
    private Date createDate;
    private BasicDBObject publisher;

    public Integer getRowId() {
        return rowId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setRowId(Integer towId) {
        this.rowId = towId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BasicDBObject getPublisher() {
        return publisher;
    }

    public void setPublisher(BasicDBObject publisher) {
        this.publisher = publisher;
    }
}
