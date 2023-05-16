package com.shop.member.mapper;

import com.shop.member.domain.MemAccount;

import java.util.List;

/**
 * 用户信息 数据层
 */
public interface MemAccountMapper {
    /**
     * 查询用户数据集合
     *
     * @param account 用户信息
     * @return 用户数据集合
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
     * 修改用户信息
     *
     * @param account 用户信息
     * @return 结果
     */
    public int updateAccount(MemAccount account);

    /**
     * 新增用户信息
     *
     * @param account 用户信息
     * @return 结果
     */
    public int insertAccount(MemAccount account);

    /**
     * 校验用户名称
     *
     * @param accountName 用户名称
     * @return 结果
     */
    public MemAccount checkAccountNameUnique(String accountName);
}
