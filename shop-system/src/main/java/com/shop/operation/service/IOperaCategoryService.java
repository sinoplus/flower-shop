package com.shop.operation.service;

import com.shop.operation.domain.OperaCategory;

import java.util.List;

/**
 * 种类管理 服务层
 */
public interface IOperaCategoryService {
    /**
     * 查询种类信息集合
     *
     * @param category 岗位信息
     * @return 岗位列表
     */
    public List<OperaCategory> selectCategoryList(OperaCategory category);

    /**
     * 查询所有种类
     *
     * @return 岗位列表
     */
    public List<OperaCategory> selectCategoryAll();

    /**
     * 通过种类ID查询岗位信息
     *
     * @param categoryId 种类ID
     * @return 种类对象信息
     */
    public OperaCategory selectCategoryById(Long categoryId);

    /**
     * 校验种类名称
     *
     * @param category 种类信息
     * @return 结果
     */
    public boolean checkCategoryNameUnique(OperaCategory category);

    /**
     * 校验种类编码
     *
     * @param category 种类信息
     * @return 结果
     */
    public boolean checkCategoryCodeUnique(OperaCategory category);

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
     * 批量删除岗位信息
     *
     * @param categoryIds 需要删除的岗位ID
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] categoryIds);

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int insertCategory(OperaCategory post);

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    public int updateCategory(OperaCategory post);
}
