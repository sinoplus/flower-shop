package com.shop.operation.mapper;

import com.shop.operation.domain.OperaMessage;

import java.util.List;

/**
 * 留言管理 数据层
 */
public interface OperaMessageMapper {
    /**
     * 查询留言数据集合
     *
     * @param message 留言信息
     * @return 留言数据集合
     */
    public List<OperaMessage> selectMessageList(OperaMessage message);

    /**
     * 查询所有留言
     *
     * @return 留言列表
     */
    public List<OperaMessage> selectMessageAll();

    /**
     * 通过留言ID查询留言信息
     *
     * @param messageId 留言ID
     * @return 留言对象信息
     */
    public OperaMessage selectMessageById(Long messageId);

    /**
     * 删除留言信息
     *
     * @param messageId 留言ID
     * @return 结果
     */
    public int deleteMessageById(Long messageId);

    /**
     * 批量删除留言信息
     *
     * @param messageIds 需要删除的留言ID
     * @return 结果
     */
    public int deleteMessageByIds(Long[] messageIds);

    /**
     * 修改留言信息
     *
     * @param message 留言信息
     * @return 结果
     */
    public int updateMessage(OperaMessage message);

    /**
     * 新增留言信息
     *
     * @param message 留言信息
     * @return 结果
     */
    public int insertMessage(OperaMessage message);
}
