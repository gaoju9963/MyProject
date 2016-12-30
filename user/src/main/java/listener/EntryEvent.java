package listener;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * Created by pengshu on 2016/11/11.
 * <p>
 * 微博文章事件
 *
 * 微博文章事件类 属于事件对象
 * 其中定义了事件的类型和事件源，事件参数
 *
 */
public class EntryEvent {
    public static final int ENTRY_ADDED = 100; //事件类型:文章被创建
    public static final int ENTRY_DELETED = 101;//事件类型:文章被删除
    public static final int ENTRY_MODIFIED = 102;//事件类型:文章被修改

    private int eventType; //事件类型
    private Entry entry; //微博文章对象
    private Date date; //事件触发日期
    private Map params; //事件辅助参数

    public EntryEvent(int eventType, Entry entry, Map params) {
        this.eventType = eventType;
        this.entry = entry;
        this.params = params != null ? Collections.unmodifiableMap(params) : null;
        date = new Date();
    }

    public int getEventType() {
        return eventType;
    }

    public Entry getEntry() {
        return entry;
    }

    public Date getDate() {
        return date;
    }

    public Map getParams() {
        return params;
    }
}
