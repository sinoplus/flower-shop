package com.shop.operation.service;

import com.shop.operation.domain.OperaTrade;

import java.util.List;

/**
 * 商品管理 服务层
 */
public interface IOperaTradeService {
    /**
     * 查询商品信息集合
     *
     * @param trade 商品信息
     * @return 商品列表
     */
    public List<OperaTrade> selectTradeList(OperaTrade trade);

    /**
     * 查询所有商品
     *
     * @return 商品列表
     */
    public List<OperaTrade> selectTradeAll();

    /**
     * 通过商品ID查询商品信息
     *
     * @param tradeId 商品ID
     * @return 商品对象信息
     */
    public OperaTrade selectTradeById(Long tradeId);

    /**
     * 删除商品信息
     *
     * @param tradeId 商品ID
     * @return 结果
     */
    public int deleteTradeById(Long tradeId);

    /**
     * 批量删除商品信息
     *
     * @param tradeIds 需要删除的商品ID
     * @return 结果
     */
    public int deleteTradeByIds(Long[] tradeIds);

    /**
     * 新增保存商品信息
     *
     * @param trade 商品信息
     * @return 结果
     */
    public int insertTrade(OperaTrade trade);

    /**
     * 修改保存商品信息
     *
     * @param trade 商品信息
     * @return 结果
     */
    public int updateTrade(OperaTrade trade);
}
