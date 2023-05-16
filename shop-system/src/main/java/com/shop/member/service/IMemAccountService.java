package com.shop.member.service;

import com.shop.member.domain.MemAccount;

import java.util.List;

public interface IMemAccountService {
    /**
     * 查询用户信息集合
     *
     * @param account 用户信息
     * @return 用户列表
     */
    public List<MemAccount> selectAccountList(MemAccount account);

    /**
     * 查询所有用户
     *
     * @return 用户列表
     */
    public List<MemAccount> selectAccountAll();

    /**
     * 通过用户ID查询用户信息
     *
     * @param accountId 用户ID
     * @return 用户对象信息
     */
    public MemAccount selectAccountById(Long accountId);

    /**
     * 校验用户名称
     *
     * @param account 用户信息
     * @return 结果
     */
    public boolean checkAccountNameUnique(MemAccount account);

    /**
     * 删除用户信息
     *
     * @param accountId 用户ID
     * @return 结果
     */
    public int deleteAccountById(Long accountId);

    /**
     * 批量删除用户信息
     *
     * @param accountIds 需要删除的用户ID
     * @return 结果
     */
    public int deleteAccountByIds(Long[] accountIds);

    /**
     * 新增保存用户信息
     *
     * @param account 用户信息
     * @return 结果
     */
    public int insertAccount(MemAccount account);

    /**
     * 修改保存用户信息
     *
     * @param account 用户信息
     * @return 结果
     */
    public int updateAccount(MemAccount account);
}
