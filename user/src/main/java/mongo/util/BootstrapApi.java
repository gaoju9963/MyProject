package mongo.util;

import java.util.List;

/**
 * Created by pengshu on 2016/12/14.
 */
public class BootstrapApi implements JsonApiResult {
    private int code;
    //private boolean success;
    private String message;
    private Object rows;

    private Long total;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public BootstrapApi() {
        this.code = 0;
        //this.success = true;
        this.message = "OK";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getSuccess(){
        return this.code == 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
