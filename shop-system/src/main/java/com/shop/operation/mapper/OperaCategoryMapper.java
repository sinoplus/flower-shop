package com.shop.operation.mapper;

import com.shop.operation.domain.OperaCategory;

import java.util.List;

/**
 * 种类信息 数据层
 */
public interface OperaCategoryMapper {
    /**
     * 查询种类数据集合
     *
     * @param category 种类信息
     * @return 种类数据集合
     */
    public List<OperaCategory> selectCategoryList(OperaCategory category);

    /**
     * 查询所有种类
     *
     * @return 种类列表
     */
    public List<OperaCategory> selectCategoryAll();

    /**
     * 通过种类ID查询种类信息
     *
     * @param categoryId 种类ID
     * @return 种类对象信息
     */
    public OperaCategory selectCategoryById(Long categoryId);

    /**
     * 删除种类信息
     *
     * @param categoryId 种类ID
     * @return 结果
     */
    public int deleteCategoryById(Long categoryId);

    /**
     * 根据父种类id删除种类信息
     *
     * @param pid 父种类id
     * @return 结果
     */
    public int deleteCategoryByPid(Long pid);

    /**
     * 批量删除种类信息
     *
     * @param categoryIds 需要删除的种类ID
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] categoryIds);

    /**
     * 修改种类信息
     *
     * @param category 种类信息
     * @return 结果
     */
    public int updateCategory(OperaCategory category);

    /**
     * 新增种类信息
     *
     * @param category 种类信息
     * @return 结果
     */
    public int insertCategory(OperaCategory category);

    /**
     * 校验种类名称
     *
     * @param categoryName 种类名称
     * @return 结果
     */
    public OperaCategory checkCategoryNameUnique(String categoryName);

    /**
     * 校验种类编码
     *
     * @param categoryCode 种类编码
     * @return 结果
     */
    public OperaCategory checkCategoryCodeUnique(String categoryCode);
}
