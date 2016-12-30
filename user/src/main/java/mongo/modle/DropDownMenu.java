package mongo.modle;

/**
 * Created by pengshu on 2016/12/29.
 */
public class DropDownMenu {
    private Integer rowId;
    private String key;
    private String value;

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DropDownMenu{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
