package mongo.util;

/**
 * Created by pengshu on 2016/12/8.
 */
public class ErrorResult implements JsonApiResult {
    private int code;
    //private boolean success;
    private String message;
    private Object value;

    public ErrorResult() {
        this.code = 0;
        //this.success = true;
        this.message = "error";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getSuccess() {
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
