package listener;

/**
 * Created by pengshu on 2016/11/11.
 * 积分管理器
 */
public class ScoreManager implements EntryListener {
    /**
     * 博客文章被创建
     *
     * @param entryevent
     */
    @Override
    public void entryAdded(EntryEvent entryevent) {
        System.out.println("ScoreManager 处理 博客文章被创建事件。");
    }

    /**
     * 博客文章被删除
     *
     * @param entryevent
     */
    @Override
    public void entryDeleted(EntryEvent entryevent) {
        System.out.println("ScoreManager 处理 博客文章被删除事件。");
    }

    /**
     * 博客文章被修改
     *
     * @param entryevent
     */
    @Override
    public void entryModified(EntryEvent entryevent) {
        System.out.println("ScoreManager 处理 博客文章被修改事件。");
    }
}
