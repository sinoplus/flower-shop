package com.shop.operation.domain;

import com.shop.common.annotation.Excel;
import com.shop.common.annotation.Excel.ColumnType;
import com.shop.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 种类表 opera_category
 */
public class OperaCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 种类序号
     */
    @Excel(name = "种类序号", cellType = ColumnType.NUMERIC)
    private Long categoryId;

    /**
     * 父级种类序号
     */
    @Excel(name = "种类序号", cellType = ColumnType.NUMERIC)
    private Long pid;

    /**
     * 种类编码
     */
    @Excel(name = "种类编码")
    private String categoryCode;

    /**
     * 种类名称
     */
    @Excel(name = "种类名称")
    private String categoryName;

    /**
     * 用户是否存在此岗位标识 默认不存在
     */
    private boolean flag = false;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @NotBlank(message = "种类编码不能为空")
    @Size(min = 0, max = 64, message = "种类编码长度不能超过64个字符")
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @NotBlank(message = "种类名称不能为空")
    @Size(min = 0, max = 50, message = "种类名称长度不能超过50个字符")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("categoryId", getCategoryId())
                .append("pId", getPid())
                .append("categoryCode", getCategoryCode())
                .append("categoryName", getCategoryName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
