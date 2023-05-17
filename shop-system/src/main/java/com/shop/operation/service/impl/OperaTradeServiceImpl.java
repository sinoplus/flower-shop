package com.shop.operation.service.impl;

import com.shop.operation.domain.OperaTrade;
import com.shop.operation.mapper.OperaTradeMapper;
import com.shop.operation.service.IOperaTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理 服务层实现
 */
@Service
public class OperaTradeServiceImpl implements IOperaTradeService {
    @Autowired
    private OperaTradeMapper tradeMapper;

    /**
     * 查询商品信息集合
     *
     * @param trade 商品信息
     * @return 商品信息集合
     */
    @Override
    public List<OperaTrade> selectTradeList(OperaTrade trade) {
        return tradeMapper.selectTradeList(trade);
    }

    /**
     * 查询所有商品
     *
     * @return 商品列表
     */
    @Override
    public List<OperaTrade> selectTradeAll() {
        return tradeMapper.selectTradeAll();
    }

    /**
     * 通过商品ID查询商品信息
     *
     * @param tradeId 商品ID
     * @return 商品对象信息
     */
    @Override
    public OperaTrade selectTradeById(Long tradeId) {
        return tradeMapper.selectTradeById(tradeId);
    }

    /**
     * 删除商品信息
     *
     * @param tradeId 商品ID
     * @return 结果
     */
    @Override
    public int deleteTradeById(Long tradeId) {
        return tradeMapper.deleteTradeById(tradeId);
    }

    /**
     * 批量删除商品信息
     *
     * @param tradeIds 需要删除的商品ID
     * @return 结果
     */
    @Override
    public int deleteTradeByIds(Long[] tradeIds) {
        return tradeMapper.deleteTradeByIds(tradeIds);
    }

    /**
     * 新增保存商品信息
     *
     * @param trade 商品信息
     * @return 结果
     */
    @Override
    public int insertTrade(OperaTrade trade) {
        return tradeMapper.insertTrade(trade);
    }

    /**
     * 修改保存商品信息
     *
     * @param trade 商品信息
     * @return 结果
     */
    @Override
    public int updateTrade(OperaTrade trade) {
        return tradeMapper.updateTrade(trade);
    }
}
