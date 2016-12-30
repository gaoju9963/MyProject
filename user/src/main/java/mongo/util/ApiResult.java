package mongo.util;

/**
 * Created by pengshu on 2016/12/6.
 */
public class ApiResult implements JsonApiResult {
    private int code;
    //private boolean success;
    private String message;
    private Object value;

    public ApiResult() {
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}