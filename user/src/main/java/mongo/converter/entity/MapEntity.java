package mongo.converter.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengshu on 2016/11/5.
 */
public class MapEntity {
    private String key;     //对应DebtCase属性

    private String type;    //标志着this的类型

    private List value = new ArrayList<>();//key包含的可能值

    private Map<Integer, List<MapEntity>> data = new HashMap<>();//联系人集合

    private String param;   //联系人信息(记录name、phone...)

    private Integer col;    //this对应的column

    private String colName;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getValue() {
        return value;
    }

    public void setValue(List value) {
        this.value = value;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Map<Integer, List<MapEntity>> getData() {
        return data;
    }

    public void setData(Map<Integer, List<MapEntity>> data) {
        this.data = data;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

}
