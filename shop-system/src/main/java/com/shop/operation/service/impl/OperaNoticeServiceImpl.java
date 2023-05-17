package com.shop.operation.service.impl;

import com.shop.operation.domain.OperaNotice;
import com.shop.operation.mapper.OperaNoticeMapper;
import com.shop.operation.service.IOperaNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公告管理 服务层实现
 */
@Service
public class OperaNoticeServiceImpl implements IOperaNoticeService {
    @Autowired
    private OperaNoticeMapper noticeMapper;

    /**
     * 查询公告信息集合
     *
     * @param notice 公告信息
     * @return 公告信息集合
     */
    @Override
    public List<OperaNotice> selectNoticeList(OperaNotice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 查询所有公告
     *
     * @return 公告列表
     */
    @Override
    public List<OperaNotice> selectNoticeAll() {
        return noticeMapper.selectNoticeAll();
    }

    /**
     * 通过公告ID查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告对象信息
     */
    @Override
    public OperaNotice selectNoticeById(Long noticeId) {
        return noticeMapper.selectNoticeById(noticeId);
    }

    /**
     * 删除公告信息
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId) {
        return noticeMapper.deleteNoticeById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        return noticeMapper.deleteNoticeByIds(noticeIds);
    }

    /**
     * 新增保存公告信息
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(OperaNotice notice) {
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改保存公告信息
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(OperaNotice notice) {
        return noticeMapper.updateNotice(notice);
    }
}
