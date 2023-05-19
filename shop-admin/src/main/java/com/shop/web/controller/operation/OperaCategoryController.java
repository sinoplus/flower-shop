package com.shop.web.controller.operation;

import com.shop.common.annotation.Log;
import com.shop.common.core.controller.BaseController;
import com.shop.common.core.domain.AjaxResult;
import com.shop.common.core.page.TableDataInfo;
import com.shop.common.enums.BusinessType;
import com.shop.operation.service.IOperaCategoryService;
import com.shop.operation.domain.OperaCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation/category")
public class OperaCategoryController extends BaseController {
    @Autowired
    private IOperaCategoryService operaCategoryService;

    /**
     * 获取种类列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OperaCategory category) {
        startPage();
        List<OperaCategory> list = operaCategoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 根据种类编号获取详细信息
     */
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable Long categoryId) {
        return success(operaCategoryService.selectCategoryById(categoryId));
    }

    /**
     * 新增种类
     *
     * @param category
     * @return
     */
    @Log(title = "种类管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody OperaCategory category) {
        if (!operaCategoryService.checkCategoryNameUnique(category)) {
            return error("新增种类'" + category.getCategoryName() + "'失败，种类名称已存在");
        } else if (!operaCategoryService.checkCategoryCodeUnique(category)) {
            return error("新增种类'" + category.getCategoryName() + "'失败，种类编码已存在");
        }
        category.setCreateBy(getUsername());
        return toAjax(operaCategoryService.insertCategory(category));
    }

    @Log(title = "种类管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody OperaCategory category) {
        if (!operaCategoryService.checkCategoryNameUnique(category)) {
            return error("修改种类'" + category.getCategoryName() + "'失败，种类名称已存在");
        } else if (!operaCategoryService.checkCategoryCodeUnique(category)) {
            return error("修改种类'" + category.getCategoryName() + "'失败，种类编码已存在");
        }
        category.setUpdateBy(getUsername());
        return toAjax(operaCategoryService.updateCategory(category));
    }

    @Log(title = "种类管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds) {
        return toAjax(operaCategoryService.deleteCategoryByIds(categoryIds));
    }

    /**
     * 获取种类选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect() {
        List<OperaCategory> categories = operaCategoryService.selectCategoryAll();
        return success(categories);
    }
}
