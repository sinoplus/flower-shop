package com.shop.operation.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.common.annotation.Excel;
import com.shop.common.core.domain.BaseEntity;

import java.util.Date;

public class OperaNotice extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 公告序号
     */
    @Excel(name = "公告序号", cellType = Excel.ColumnType.NUMERIC)
    private Long noticeId;

    /**
     * 公告标题
     */
    @Excel(name = "公告标题")
    private String title;

    /**
     * 公告内容
     */
    @Excel(name = "公告内容")
    private String content;

    /**
     * 发布时间
     */
    @Excel(name = "发布时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date releaseTime;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }
}
