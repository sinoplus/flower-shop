package com.shop.member.service.impl;

import com.shop.common.constant.UserConstants;
import com.shop.common.utils.StringUtils;
import com.shop.member.domain.MemAccount;
import com.shop.member.mapper.MemAccountMapper;
import com.shop.member.service.IMemAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员管理 服务层实现
 */
@Service
public class MemAccountServiceImpl implements IMemAccountService {
    @Autowired
    private MemAccountMapper accountMapper;

    /**
     * 查询用户信息集合
     *
     * @param account 用户信息
     * @return 用户信息集合
     */
    @Override
    public List<MemAccount> selectAccountList(MemAccount account) {
        return accountMapper.selectAccountList(account);
    }

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    @Override
    public List<MemAccount> selectAccountAll() {
        return accountMapper.selectAccountAll();
    }

    /**
     * 通过用户ID查询用户信息
     *
     * @param accountId 用户ID
     * @return 用户对象信息
     */
    @Override
    public MemAccount selectAccountById(Long accountId) {
        return accountMapper.selectAccountById(accountId);
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param account 用户信息
     * @return 结果
     */
    @Override
    public boolean checkAccountNameUnique(MemAccount account) {
        Long accountId = StringUtils.isNull(account.getAccountId()) ? -1L : account.getAccountId();
        MemAccount info = accountMapper.checkAccountNameUnique(account.getAccountName());
        if (StringUtils.isNotNull(info) && info.getAccountId().longValue() != accountId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 删除用户信息
     *
     * @param accountId 用户ID
     * @return 结果
     */
    @Override
    public int deleteAccountById(Long accountId) {
        return accountMapper.deleteAccountById(accountId);
    }

    /**
     * 批量删除用户信息
     *
     * @param accountIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteAccountByIds(Long[] accountIds) {
        return accountMapper.deleteAccountByIds(accountIds);
    }

    /**
     * 新增保存用户信息
     *
     * @param account 用户信息
     * @return 结果
     */
    @Override
    public int insertAccount(MemAccount account) {
        return accountMapper.insertAccount(account);
    }

    /**
     * 修改保存用户信息
     *
     * @param account 用户信息
     * @return 结果
     */
    @Override
    public int updateAccount(MemAccount account) {
        return accountMapper.updateAccount(account);
    }
}
