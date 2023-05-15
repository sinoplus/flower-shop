package com.shop.operation.service.impl;

import com.shop.common.constant.UserConstants;
import com.shop.common.utils.StringUtils;
import com.shop.operation.domain.OperaCategory;
import com.shop.operation.mapper.OperaCategoryMapper;
import com.shop.operation.service.IOperaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 种类信息 服务层处理
 */
@Service
public class OperaCategoryServiceImpl implements IOperaCategoryService {
    @Autowired
    private OperaCategoryMapper categoryMapper;

    /**
     * 查询种类信息集合
     *
     * @param category 种类信息
     * @return 岗位信息集合
     */
    @Override
    public List<OperaCategory> selectCategoryList(OperaCategory category) {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 查询所有种类
     *
     * @return 种类列表
     */
    @Override
    public List<OperaCategory> selectCategoryAll() {
        return categoryMapper.selectCategoryAll();
    }

    /**
     * 通过种类ID查询种类信息
     *
     * @param categoryId 种类ID
     * @return 种类对象信息
     */
    @Override
    public OperaCategory selectCategoryById(Long categoryId) {
        return categoryMapper.selectCategoryById(categoryId);
    }

    /**
     * 校验种类名称是否唯一
     *
     * @param category 种类信息
     * @return 结果
     */
    @Override
    public boolean checkCategoryNameUnique(OperaCategory category) {
        Long categoryId = StringUtils.isNull(category.getCategoryId()) ? -1L : category.getCategoryId();
        OperaCategory info = categoryMapper.checkCategoryNameUnique(category.getCategoryName());
        if (StringUtils.isNotNull(info) && info.getCategoryId().longValue() != categoryId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验种类编码是否唯一
     *
     * @param category 种类信息
     * @return 结果
     */
    @Override
    public boolean checkCategoryCodeUnique(OperaCategory category) {
        Long categoryId = StringUtils.isNull(category.getCategoryId()) ? -1L : category.getCategoryId();
        OperaCategory info = categoryMapper.checkCategoryCodeUnique(category.getCategoryCode());
        if (StringUtils.isNotNull(info) && info.getCategoryId().longValue() != categoryId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 删除种类信息
     *
     * @param categoryId 岗位ID
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long categoryId) {
        return categoryMapper.deleteCategoryById(categoryId);
    }

    /**
     * 根据父种类id删除种类信息
     *
     * @param pid 父种类id
     * @return 结果
     */
    @Override
    public int deleteCategoryByPid(Long pid) {
        return categoryMapper.deleteCategoryByPid(pid);
    }

    /**
     * 批量删除种类信息
     *
     * @param categoryIds 需要删除的种类ID
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(Long[] categoryIds) {
        return categoryMapper.deleteCategoryByIds(categoryIds);
    }

    /**
     * 新增保存种类信息
     *
     * @param category 种类信息
     * @return 结果
     */
    @Override
    public int insertCategory(OperaCategory category) {
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改保存种类信息
     *
     * @param category 种类信息
     * @return 结果
     */
    @Override
    public int updateCategory(OperaCategory category) {
        return categoryMapper.updateCategory(category);
    }
}
