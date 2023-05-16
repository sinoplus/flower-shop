package com.shop.member.domain;

import com.shop.common.annotation.Excel;
import com.shop.common.core.domain.BaseEntity;

public class MemAccount extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    @Excel(name = "用户主键", cellType = Excel.ColumnType.NUMERIC)
    private Long accountId;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String accountName;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phoneNumber;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realName;

    /**
     * 性别
     */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String sex;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
