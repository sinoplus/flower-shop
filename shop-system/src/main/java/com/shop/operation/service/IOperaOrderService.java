package com.shop.operation.service;

import com.shop.operation.domain.OperaOrder;

import java.util.List;

/**
 * 订单管理 服务层
 */
public interface IOperaOrderService {
    /**
     * 查询订单信息集合
     *
     * @param order 订单信息
     * @return 订单列表
     */
    public List<OperaOrder> selectOrderList(OperaOrder order);

    /**
     * 查询所有订单
     *
     * @return 订单列表
     */
    public List<OperaOrder> selectOrderAll();

    /**
     * 通过订单ID查询订单信息
     *
     * @param orderId 订单ID
     * @return 订单对象信息
     */
    public OperaOrder selectOrderById(Long orderId);

    /**
     * 删除订单信息
     *
     * @param orderId 订单ID
     * @return 结果
     */
    public int deleteOrderById(Long orderId);

    /**
     * 批量删除订单信息
     *
     * @param orderIds 需要删除的订单ID
     * @return 结果
     */
    public int deleteOrderByIds(Long[] orderIds);

    /**
     * 新增保存订单信息
     *
     * @param order 订单信息
     * @return 结果
     */
    public int insertOrder(OperaOrder order);

    /**
     * 修改保存订单信息
     *
     * @param order 订单信息
     * @return 结果
     */
    public int updateOrder(OperaOrder order);
}
