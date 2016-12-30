package pengshu.model;

import org.springframework.stereotype.Service;

/**
 * Created by pengshu on 2016/11/3.
 */
@Service
public class NewCase {
    private Integer id;
    private String company;
    private String year;
    private String month;
    private String day;
    private Integer count;
    private Double outsourceAmount;


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getOutsourceAmount() {
        return outsourceAmount;
    }

    public void setOutsourceAmount(Double outsourceAmount) {
        this.outsourceAmount = outsourceAmount;
    }
}
