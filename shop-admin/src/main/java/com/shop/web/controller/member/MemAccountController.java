package com.shop.web.controller.member;

import com.shop.common.annotation.Log;
import com.shop.common.core.controller.BaseController;
import com.shop.common.core.domain.AjaxResult;
import com.shop.common.core.page.TableDataInfo;
import com.shop.common.enums.BusinessType;
import com.shop.member.domain.MemAccount;
import com.shop.member.service.IMemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member/account")
public class MemAccountController extends BaseController {
    @Autowired
    private IMemAccountService accountService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public TableDataInfo list(MemAccount account) {
        startPage();
        List<MemAccount> list = accountService.selectAccountList(account);
        return getDataTable(list);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @GetMapping(value = "/{accountId}")
    public AjaxResult getInfo(@PathVariable Long accountId) {
        return success(accountService.selectAccountById(accountId));
    }

    /**
     * 新增用户
     * @param account
     * @return
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody MemAccount account) {
        if (!accountService.checkAccountNameUnique(account)) {
            return error("新增用户'" + account.getAccountName() + "'失败，用户名称已存在");
        }
        account.setCreateBy(getUsername());
        return toAjax(accountService.insertAccount(account));
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody MemAccount account) {
        if (!accountService.checkAccountNameUnique(account)) {
            return error("修改用户'" + account.getAccountName() + "'失败，用户名称已存在");
        }
        account.setUpdateBy(getUsername());
        return toAjax(accountService.updateAccount(account));
    }

    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{accountIds}")
    public AjaxResult remove(@PathVariable Long[] accountIds) {
        return toAjax(accountService.deleteAccountByIds(accountIds));
    }
}
