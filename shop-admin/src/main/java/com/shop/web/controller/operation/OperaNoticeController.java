package com.shop.web.controller.operation;

import com.shop.common.annotation.Log;
import com.shop.common.core.controller.BaseController;
import com.shop.common.core.domain.AjaxResult;
import com.shop.common.core.page.TableDataInfo;
import com.shop.common.enums.BusinessType;
import com.shop.operation.domain.OperaNotice;
import com.shop.operation.service.IOperaNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation/notice")
public class OperaNoticeController extends BaseController {
    @Autowired
    private IOperaNoticeService noticeService;

    /**
     * 获取公告列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OperaNotice notice) {
        startPage();
        List<OperaNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据公告编号获取详细信息
     */
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId) {
        return success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增公告
     * @param notice
     * @return
     */
    @Log(title = "公告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody OperaNotice notice) {
        notice.setCreateBy(getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 编辑公告
     * @param notice
     * @return
     */
    @Log(title = "公告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody OperaNotice notice) {
        notice.setUpdateBy(getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除公告
     * @param noticeIds
     * @return
     */
    @Log(title = "公告管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}
