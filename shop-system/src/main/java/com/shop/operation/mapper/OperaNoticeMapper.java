package com.shop.operation.mapper;

import com.shop.operation.domain.OperaNotice;

import java.util.List;

/**
 * 公告管理 数据层
 */
public interface OperaNoticeMapper {
    /**
     * 查询公告数据集合
     *
     * @param notice 公告信息
     * @return 公告数据集合
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
     * 修改公告信息
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(OperaNotice notice);

    /**
     * 新增公告信息
     *
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(OperaNotice notice);
}
