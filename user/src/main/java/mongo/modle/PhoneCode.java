package mongo.modle;

import java.util.Date;

/**
 * Created by pengshu on 2016/12/9.
 */
public class PhoneCode {
    private String phoneNumber;
    private Integer code;
    private Date createTime;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
