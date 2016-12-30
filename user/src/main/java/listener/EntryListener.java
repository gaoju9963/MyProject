package listener;

/**
 * Created by pengshu on 2016/11/11.
 * 博客文章监听器接口
 */
public interface EntryListener {
    /**
     * 博客文章被创建
     * @param entryevent
     */
    public abstract void entryAdded(EntryEvent entryevent);

    /**
     * 博客文章被删除
     * @param entryevent
     */

    public abstract void entryDeleted(EntryEvent entryevent);


    /**
     * 博客文章被修改
     * @param entryevent
     */
    public abstract void entryModified(EntryEvent entryevent);
}
