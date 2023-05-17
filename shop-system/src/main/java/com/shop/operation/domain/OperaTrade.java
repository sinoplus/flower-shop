package com.shop.operation.domain;

import com.shop.common.annotation.Excel;
import com.shop.common.core.domain.BaseEntity;
import com.shop.common.xss.Xss;

/**
 * 商品表 opera_trade
 */
public class OperaTrade extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 商品序号
     */
    @Excel(name = "商品序号", cellType = Excel.ColumnType.NUMERIC)
    private Long tradeId;

    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String tradeName;

    /**
     * 商品主图
     */
    @Excel(name = "商品主图")
    private String mainPic;

    /**
     * 商品副图
     */
    @Excel(name = "商品副图")
    private String vicePic;

    /**
     * 商品价格
     */
    @Excel(name = "商品价格")
    private Double tradePrice;

    /**
     * 商品一级分类
     */
    @Excel(name = "商品一级分类")
    private String firstCategory;

    /**
     * 商品二级分类
     */
    @Excel(name = "商品二级分类")
    private String secondCategory;

    /**
     * 商品折扣
     */
    @Excel(name = "商品折扣")
    private Double discount;

    /**
     * 商品描述
     */
    @Excel(name = "商品描述")
    private String content;

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getMainPic() {
        return mainPic;
    }

    public void setMainPic(String mainPic) {
        this.mainPic = mainPic;
    }

    public String getVicePic() {
        return vicePic;
    }

    public void setVicePic(String vicePic) {
        this.vicePic = vicePic;
    }

    public Double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(String firstCategory) {
        this.firstCategory = firstCategory;
    }

    public String getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(String secondCategory) {
        this.secondCategory = secondCategory;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Xss(message = "公告标题不能包含脚本字符")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
