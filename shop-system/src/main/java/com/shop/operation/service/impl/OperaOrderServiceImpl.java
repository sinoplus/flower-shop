package com.shop.operation.service.impl;

import com.shop.operation.domain.OperaOrder;
import com.shop.operation.mapper.OperaOrderMapper;
import com.shop.operation.service.IOperaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单管理 服务层实现
 */
@Service
public class OperaOrderServiceImpl implements IOperaOrderService {
    @Autowired
    private OperaOrderMapper orderMapper;

    /**
     * 查询订单信息集合
     *
     * @param order 订单信息
     * @return 订单信息集合
     */
    @Override
    public List<OperaOrder> selectOrderList(OperaOrder order) {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 查询所有订单
     *
     * @return 订单列表
     */
    @Override
    public List<OperaOrder> selectOrderAll() {
        return orderMapper.selectOrderAll();
    }

    /**
     * 通过订单ID查询订单信息
     *
     * @param orderId 订单ID
     * @return 订单对象信息
     */
    @Override
    public OperaOrder selectOrderById(Long orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    /**
     * 删除订单信息
     *
     * @param orderId 订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderById(Long orderId) {
        return orderMapper.deleteOrderById(orderId);
    }

    /**
     * 批量删除订单信息
     *
     * @param orderIds 需要删除的订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(Long[] orderIds) {
        return orderMapper.deleteOrderByIds(orderIds);
    }

    /**
     * 新增保存订单信息
     *
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int insertOrder(OperaOrder order) {
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改保存订单信息
     *
     * @param order 订单信息
     * @return 结果
     */
    @Override
    public int updateOrder(OperaOrder order) {
        return orderMapper.updateOrder(order);
    }
}
