package com.zsgc.core.model;

import java.util.Date;

public class CustUserMsg {
    private Long id;

    private Integer userId;

    private Byte msgType;

    private String msgTitle;

    private String msgContent;

    private String msgClient;

    private Byte msgStatus;

    private Date msgReadAt;

    private Date createdAt;

    private Date updatedAt;

    private Byte msgPushStatus;

    private Date msgPushStartAt;

    private Date msgPushEndAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getMsgType() {
        return msgType;
    }

    public void setMsgType(Byte msgType) {
        this.msgType = msgType;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgClient() {
        return msgClient;
    }

    public void setMsgClient(String msgClient) {
        this.msgClient = msgClient;
    }

    public Byte getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Byte msgStatus) {
        this.msgStatus = msgStatus;
    }

    public Date getMsgReadAt() {
        return msgReadAt;
    }

    public void setMsgReadAt(Date msgReadAt) {
        this.msgReadAt = msgReadAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Byte getMsgPushStatus() {
        return msgPushStatus;
    }

    public void setMsgPushStatus(Byte msgPushStatus) {
        this.msgPushStatus = msgPushStatus;
    }

    public Date getMsgPushStartAt() {
        return msgPushStartAt;
    }

    public void setMsgPushStartAt(Date msgPushStartAt) {
        this.msgPushStartAt = msgPushStartAt;
    }

    public Date getMsgPushEndAt() {
        return msgPushEndAt;
    }

    public void setMsgPushEndAt(Date msgPushEndAt) {
        this.msgPushEndAt = msgPushEndAt;
    }

	@Override
	public String toString() {
		return "CustUserMsg [id=" + id + ", userId=" + userId + ", msgType=" + msgType + ", msgTitle=" + msgTitle
				+ ", msgContent=" + msgContent + ", msgClient=" + msgClient + ", msgStatus=" + msgStatus
				+ ", msgReadAt=" + msgReadAt + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", msgPushStatus=" + msgPushStatus + ", msgPushStartAt=" + msgPushStartAt + ", msgPushEndAt="
				+ msgPushEndAt + "]";
	}
    
}