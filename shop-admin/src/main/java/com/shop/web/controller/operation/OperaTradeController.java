package com.shop.web.controller.operation;

import com.shop.common.annotation.Log;
import com.shop.common.core.controller.BaseController;
import com.shop.common.core.domain.AjaxResult;
import com.shop.common.core.page.TableDataInfo;
import com.shop.common.enums.BusinessType;
import com.shop.operation.domain.OperaTrade;
import com.shop.operation.service.IOperaTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation/trade")
public class OperaTradeController extends BaseController {
    @Autowired
    private IOperaTradeService tradeService;

    /**
     * 获取商品列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OperaTrade trade) {
        startPage();
        List<OperaTrade> list = tradeService.selectTradeList(trade);
        return getDataTable(list);
    }

    /**
     * 根据商品编号获取详细信息
     */
    @GetMapping(value = "/{tradeId}")
    public AjaxResult getInfo(@PathVariable Long tradeId) {
        return success(tradeService.selectTradeById(tradeId));
    }

    /**
     * 新增商品
     * @param trade
     * @return
     */
    @Log(title = "商品管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody OperaTrade trade) {
        trade.setCreateBy(getUsername());
        return toAjax(tradeService.insertTrade(trade));
    }

    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody OperaTrade trade) {
        trade.setUpdateBy(getUsername());
        return toAjax(tradeService.updateTrade(trade));
    }

    @Log(title = "商品管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tradeIds}")
    public AjaxResult remove(@PathVariable Long[] tradeIds) {
        return toAjax(tradeService.deleteTradeByIds(tradeIds));
    }
}
