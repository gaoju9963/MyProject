package entity;

import java.util.Date;

/**
 * Created by pengshu on 2016/11/14.
 */
public class User {
    private String name;
    private String sex;
    private Integer age;
    private Date createDate;


    private void get() {
        System.out.println("这是一个私有方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
