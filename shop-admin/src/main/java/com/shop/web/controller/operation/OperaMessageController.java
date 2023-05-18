package com.shop.web.controller.operation;

import com.shop.common.annotation.Log;
import com.shop.common.core.controller.BaseController;
import com.shop.common.core.domain.AjaxResult;
import com.shop.common.core.page.TableDataInfo;
import com.shop.common.enums.BusinessType;
import com.shop.operation.domain.OperaMessage;
import com.shop.operation.service.IOperaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation/message")
public class OperaMessageController extends BaseController {
    @Autowired
    private IOperaMessageService messageService;

    /**
     * 获取留言列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OperaMessage message) {
        startPage();
        List<OperaMessage> list = messageService.selectMessageList(message);
        return getDataTable(list);
    }

    /**
     * 根据留言编号获取详细信息
     */
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable Long messageId) {
        return success(messageService.selectMessageById(messageId));
    }

    /**
     * 新增留言
     * @param message
     * @return
     */
    @Log(title = "留言管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody OperaMessage message) {
        message.setCreateBy(getUsername());
        return toAjax(messageService.insertMessage(message));
    }

    /**
     * 编辑留言
     * @param message
     * @return
     */
    @Log(title = "留言管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody OperaMessage message) {
        message.setUpdateBy(getUsername());
        return toAjax(messageService.updateMessage(message));
    }

    /**
     * 删除留言
     * @param messageIds
     * @return
     */
    @Log(title = "留言管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds) {
        return toAjax(messageService.deleteMessageByIds(messageIds));
    }
}
