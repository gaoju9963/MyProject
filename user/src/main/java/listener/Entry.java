package listener;

/**
 * Created by pengshu on 2016/11/11.
 * <p>
 * 微博文章
 */
public class Entry {
    private long id; // 编号
    private String name; //标题

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
