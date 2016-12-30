package pengshu.model;

import org.springframework.stereotype.Service;

/**
 * Created by pengshu on 2016/11/3.
 */
@Service
public class Case {
    private Integer id;
    private String batchId;
    private Integer count;
    private Double outsourceAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
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
