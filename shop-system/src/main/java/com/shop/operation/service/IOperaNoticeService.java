package com.shop.operation.service;

import com.shop.operation.domain.OperaNotice;

import java.util.List;

/**
 * 公告管理 服务层
 */
public interface IOperaNoticeService {
    /**
     * 查询公告信息集合
     *
     * @param notice 公告信息
     * @return 公告列表
     */
    public List<OperaNotice> selectNoticeList(OperaNotice notice);

    /**
     * 查询所有公告
     *
     * @return 公告列表
     */
    public List<OperaNotice> selectNoticeAll();

    /**
     * 通过公告ID查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告对象信息
     */
    public OperaNotice selectNoticeById(Long noticeId);

    /**
     * 删除公告信息
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);

    /**
     * 新增保存公告信息
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(OperaNotice notice);

    /**
     * 修改保存公告信息
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(OperaNotice notice);
}
