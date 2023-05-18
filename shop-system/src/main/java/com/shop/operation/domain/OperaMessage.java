package com.shop.operation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.common.annotation.Excel;
import com.shop.common.core.domain.BaseEntity;

import java.util.Date;

public class OperaMessage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 留言序号
     */
    @Excel(name = "留言序号", cellType = Excel.ColumnType.NUMERIC)
    private Long messageId;

    /**
     * 留言人姓名
     */
    @Excel(name = "留言人姓名")
    private String name;

    /**
     * 手机号
     */
    @Excel(name = "手机号")
    private String phone;

    /**
     * 留言内容
     */
    @Excel(name = "留言内容")
    private String content;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
