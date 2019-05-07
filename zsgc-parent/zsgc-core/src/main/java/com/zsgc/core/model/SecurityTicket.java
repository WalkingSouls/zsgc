package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class SecurityTicket extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.String id;
    private java.lang.Integer ticketType;
    private java.lang.String ticketData;
    private java.lang.String ticketOwner;
    private java.util.Date expiryTime;
    private java.lang.Integer remainingCount;
    private java.lang.Integer status;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public void setId(java.lang.String id, boolean forceUpdate) {
        setId(id);

        if (forceUpdate) {
          addForceUpdateProperty("id");
        }
    }

    public java.lang.Integer getTicketType() {
        return ticketType;
    }

    public void setTicketType(java.lang.Integer ticketType) {
        this.ticketType = ticketType;
    }

    public void setTicketType(java.lang.Integer ticketType, boolean forceUpdate) {
        setTicketType(ticketType);

        if (forceUpdate) {
          addForceUpdateProperty("ticketType");
        }
    }

    public java.lang.String getTicketData() {
        return ticketData;
    }

    public void setTicketData(java.lang.String ticketData) {
        this.ticketData = ticketData;
    }

    public void setTicketData(java.lang.String ticketData, boolean forceUpdate) {
        setTicketData(ticketData);

        if (forceUpdate) {
          addForceUpdateProperty("ticketData");
        }
    }

    public java.lang.String getTicketOwner() {
        return ticketOwner;
    }

    public void setTicketOwner(java.lang.String ticketOwner) {
        this.ticketOwner = ticketOwner;
    }

    public void setTicketOwner(java.lang.String ticketOwner, boolean forceUpdate) {
        setTicketOwner(ticketOwner);

        if (forceUpdate) {
          addForceUpdateProperty("ticketOwner");
        }
    }

    public java.util.Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(java.util.Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public void setExpiryTime(java.util.Date expiryTime, boolean forceUpdate) {
        setExpiryTime(expiryTime);

        if (forceUpdate) {
          addForceUpdateProperty("expiryTime");
        }
    }

    public java.lang.Integer getRemainingCount() {
        return remainingCount;
    }

    public void setRemainingCount(java.lang.Integer remainingCount) {
        this.remainingCount = remainingCount;
    }

    public void setRemainingCount(java.lang.Integer remainingCount, boolean forceUpdate) {
        setRemainingCount(remainingCount);

        if (forceUpdate) {
          addForceUpdateProperty("remainingCount");
        }
    }

    public java.lang.Integer getStatus() {
        return status;
    }

    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    public void setStatus(java.lang.Integer status, boolean forceUpdate) {
        setStatus(status);

        if (forceUpdate) {
          addForceUpdateProperty("status");
        }
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt, boolean forceUpdate) {
        setCreatedAt(createdAt);

        if (forceUpdate) {
          addForceUpdateProperty("createdAt");
        }
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt, boolean forceUpdate) {
        setUpdatedAt(updatedAt);

        if (forceUpdate) {
          addForceUpdateProperty("updatedAt");
        }
    }
}
