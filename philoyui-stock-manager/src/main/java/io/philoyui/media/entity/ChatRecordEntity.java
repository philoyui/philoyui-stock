package io.philoyui.media.entity;

import io.philoyui.media.entity.enu.SendType;

import javax.persistence.Entity;
import java.io.Serializable;

//@Entity
public class ChatRecordEntity implements Serializable {

    private Long id;

    /**
     * 绑定消息ID
     */
    private String messageId;

    /**
     * 目标用户ID
     */
    private String targetUserId;

    /**
     * 发送时间
     */
    private String sendTime;

    /**
     * 发送类型
     */
    private SendType sendType;

    /**
     * 发送正文
     */
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(String targetUserId) {
        this.targetUserId = targetUserId;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public SendType getSendType() {
        return sendType;
    }

    public void setSendType(SendType sendType) {
        this.sendType = sendType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
