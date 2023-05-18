package com.shop.operation.service.impl;

import com.shop.operation.domain.OperaMessage;
import com.shop.operation.domain.OperaNotice;
import com.shop.operation.mapper.OperaMessageMapper;
import com.shop.operation.mapper.OperaNoticeMapper;
import com.shop.operation.service.IOperaMessageService;
import com.shop.operation.service.IOperaNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 留言管理 服务层实现
 */
@Service
public class OperaMessageServiceImpl implements IOperaMessageService {
    @Autowired
    private OperaMessageMapper messageMapper;

    /**
     * 查询留言信息集合
     *
     * @param message 留言信息
     * @return 留言信息集合
     */
    @Override
    public List<OperaMessage> selectMessageList(OperaMessage message) {
        return messageMapper.selectMessageList(message);
    }

    /**
     * 查询所有留言
     *
     * @return 留言列表
     */
    @Override
    public List<OperaMessage> selectMessageAll() {
        return messageMapper.selectMessageAll();
    }

    /**
     * 通过留言ID查询留言信息
     *
     * @param messageId 留言ID
     * @return 留言对象信息
     */
    @Override
    public OperaMessage selectMessageById(Long messageId) {
        return messageMapper.selectMessageById(messageId);
    }

    /**
     * 删除留言信息
     *
     * @param messageId 留言ID
     * @return 结果
     */
    @Override
    public int deleteMessageById(Long messageId) {
        return messageMapper.deleteMessageById(messageId);
    }

    /**
     * 批量删除留言信息
     *
     * @param messageIds 需要删除的留言ID
     * @return 结果
     */
    @Override
    public int deleteMessageByIds(Long[] messageIds) {
        return messageMapper.deleteMessageByIds(messageIds);
    }

    /**
     * 新增保存留言信息
     *
     * @param message 留言信息
     * @return 结果
     */
    @Override
    public int insertMessage(OperaMessage message) {
        return messageMapper.insertMessage(message);
    }

    /**
     * 修改保存留言信息
     *
     * @param message 留言信息
     * @return 结果
     */
    @Override
    public int updateMessage(OperaMessage message) {
        return messageMapper.updateMessage(message);
    }
}
