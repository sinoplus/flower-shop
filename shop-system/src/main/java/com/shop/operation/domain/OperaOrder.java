package com.shop.operation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.common.annotation.Excel;
import com.shop.common.core.domain.BaseEntity;

import java.util.Date;

public class OperaOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单序号
     */
    @Excel(name = "订单序号", cellType = Excel.ColumnType.NUMERIC)
    private Long orderId;

    /**
     * 订单号
     */
    @Excel(name = "订单号")
    private String orderNo;

    /**
     * 下单时间
     */
    @Excel(name = "下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;

    /**
     * 总金额
     */
    @Excel(name = "总金额")
    private Double totalPrice;

    /**
     * 下单人
     */
    @Excel(name = "下单人")
    private String orderBy;

    /**
     * 订单状态
     */
    @Excel(name = "订单状态")
    private String status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderNo;
    }

    public void setOrderName(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
