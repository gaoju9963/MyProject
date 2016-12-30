package pachong;

import java.util.Date;

/**
 * Created by pengshu on 2016/11/10.
 */
public class ForeignExchange {
    private String currency;//货币名称
    private Double spotPurchase;//现汇买入价
    private Double casePurchase;//现钞买入价
    private Double spotSell;//现汇卖出价
    private Double caseSell;//现钞卖出价
    private Double DiscountPrice;//中行折算价
    private Date releaseDate;//发布日期
    private Date releaseTime;//发布时间

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getSpotPurchase() {
        return spotPurchase;
    }

    public void setSpotPurchase(Double spotPurchase) {
        this.spotPurchase = spotPurchase;
    }

    public Double getCasePurchase() {
        return casePurchase;
    }

    public void setCasePurchase(Double casePurchase) {
        this.casePurchase = casePurchase;
    }

    public Double getSpotSell() {
        return spotSell;
    }

    public void setSpotSell(Double spotSell) {
        this.spotSell = spotSell;
    }

    public Double getCaseSell() {
        return caseSell;
    }

    public void setCaseSell(Double caseSell) {
        this.caseSell = caseSell;
    }

    public Double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        DiscountPrice = discountPrice;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
}
