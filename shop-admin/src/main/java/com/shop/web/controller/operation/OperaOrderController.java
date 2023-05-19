package com.shop.web.controller.operation;

import com.shop.common.annotation.Log;
import com.shop.common.core.controller.BaseController;
import com.shop.common.core.domain.AjaxResult;
import com.shop.common.core.page.TableDataInfo;
import com.shop.common.enums.BusinessType;
import com.shop.operation.domain.OperaOrder;
import com.shop.operation.service.IOperaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation/order")
public class OperaOrderController extends BaseController {
    @Autowired
    private IOperaOrderService orderService;

    /**
     * 获取订单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OperaOrder order) {
        startPage();
        List<OperaOrder> list = orderService.selectOrderList(order);
        return getDataTable(list);
    }

    /**
     * 根据订单编号获取详细信息
     */
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable Long orderId) {
        return success(orderService.selectOrderById(orderId));
    }

    /**
     * 新增订单
     *
     * @param order
     * @return
     */
    @Log(title = "订单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody OperaOrder order) {
        order.setCreateBy(getUsername());
        return toAjax(orderService.insertOrder(order));
    }

    /**
     * 编辑订单
     *
     * @param order
     * @return
     */
    @Log(title = "订单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody OperaOrder order) {
        order.setUpdateBy(getUsername());
        return toAjax(orderService.updateOrder(order));
    }

    /**
     * 删除订单
     *
     * @param orderIds
     * @return
     */
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds) {
        return toAjax(orderService.deleteOrderByIds(orderIds));
    }
}
