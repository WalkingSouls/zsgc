package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class Withdraw extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.String number;
    private java.lang.Integer uid;
    private java.math.BigDecimal cash;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;
    private java.util.Date endTime;
    private java.lang.Integer bid;
    private java.lang.Integer status;
    private java.lang.Integer clientType;
    private java.lang.String accountNumber;
    private java.lang.String accountHolder;
    private java.lang.String memo;
    private java.math.BigDecimal handleFee;
    private java.math.BigDecimal actualCash;
    private java.lang.Integer transferTime;
    private java.lang.Integer transerUid;
    private java.lang.Integer adminId;
    private java.lang.String remark;
    private java.util.Date operateAt;

    public java.lang.Integer getId() {
        return id;
    }

    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    public void setId(java.lang.Integer id, boolean forceUpdate) {
        setId(id);

        if (forceUpdate) {
          addForceUpdateProperty("id");
        }
    }

    public java.lang.String getNumber() {
        return number;
    }

    public void setNumber(java.lang.String number) {
        this.number = number;
    }

    public void setNumber(java.lang.String number, boolean forceUpdate) {
        setNumber(number);

        if (forceUpdate) {
          addForceUpdateProperty("number");
        }
    }

    public java.lang.Integer getUid() {
        return uid;
    }

    public void setUid(java.lang.Integer uid) {
        this.uid = uid;
    }

    public void setUid(java.lang.Integer uid, boolean forceUpdate) {
        setUid(uid);

        if (forceUpdate) {
          addForceUpdateProperty("uid");
        }
    }

    public java.math.BigDecimal getCash() {
        return cash;
    }

    public void setCash(java.math.BigDecimal cash) {
        this.cash = cash;
    }

    public void setCash(java.math.BigDecimal cash, boolean forceUpdate) {
        setCash(cash);

        if (forceUpdate) {
          addForceUpdateProperty("cash");
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

    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(java.util.Date endTime, boolean forceUpdate) {
        setEndTime(endTime);

        if (forceUpdate) {
          addForceUpdateProperty("endTime");
        }
    }

    public java.lang.Integer getBid() {
        return bid;
    }

    public void setBid(java.lang.Integer bid) {
        this.bid = bid;
    }

    public void setBid(java.lang.Integer bid, boolean forceUpdate) {
        setBid(bid);

        if (forceUpdate) {
          addForceUpdateProperty("bid");
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

    public java.lang.Integer getClientType() {
        return clientType;
    }

    public void setClientType(java.lang.Integer clientType) {
        this.clientType = clientType;
    }

    public void setClientType(java.lang.Integer clientType, boolean forceUpdate) {
        setClientType(clientType);

        if (forceUpdate) {
          addForceUpdateProperty("clientType");
        }
    }

    public java.lang.String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountNumber(java.lang.String accountNumber, boolean forceUpdate) {
        setAccountNumber(accountNumber);

        if (forceUpdate) {
          addForceUpdateProperty("accountNumber");
        }
    }

    public java.lang.String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(java.lang.String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setAccountHolder(java.lang.String accountHolder, boolean forceUpdate) {
        setAccountHolder(accountHolder);

        if (forceUpdate) {
          addForceUpdateProperty("accountHolder");
        }
    }

    public java.lang.String getMemo() {
        return memo;
    }

    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }

    public void setMemo(java.lang.String memo, boolean forceUpdate) {
        setMemo(memo);

        if (forceUpdate) {
          addForceUpdateProperty("memo");
        }
    }

    public java.math.BigDecimal getHandleFee() {
        return handleFee;
    }

    public void setHandleFee(java.math.BigDecimal handleFee) {
        this.handleFee = handleFee;
    }

    public void setHandleFee(java.math.BigDecimal handleFee, boolean forceUpdate) {
        setHandleFee(handleFee);

        if (forceUpdate) {
          addForceUpdateProperty("handleFee");
        }
    }

    public java.math.BigDecimal getActualCash() {
        return actualCash;
    }

    public void setActualCash(java.math.BigDecimal actualCash) {
        this.actualCash = actualCash;
    }

    public void setActualCash(java.math.BigDecimal actualCash, boolean forceUpdate) {
        setActualCash(actualCash);

        if (forceUpdate) {
          addForceUpdateProperty("actualCash");
        }
    }

    public java.lang.Integer getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(java.lang.Integer transferTime) {
        this.transferTime = transferTime;
    }

    public void setTransferTime(java.lang.Integer transferTime, boolean forceUpdate) {
        setTransferTime(transferTime);

        if (forceUpdate) {
          addForceUpdateProperty("transferTime");
        }
    }

    public java.lang.Integer getTranserUid() {
        return transerUid;
    }

    public void setTranserUid(java.lang.Integer transerUid) {
        this.transerUid = transerUid;
    }

    public void setTranserUid(java.lang.Integer transerUid, boolean forceUpdate) {
        setTranserUid(transerUid);

        if (forceUpdate) {
          addForceUpdateProperty("transerUid");
        }
    }

    public java.lang.Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(java.lang.Integer adminId) {
        this.adminId = adminId;
    }

    public void setAdminId(java.lang.Integer adminId, boolean forceUpdate) {
        setAdminId(adminId);

        if (forceUpdate) {
          addForceUpdateProperty("adminId");
        }
    }

    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public void setRemark(java.lang.String remark, boolean forceUpdate) {
        setRemark(remark);

        if (forceUpdate) {
          addForceUpdateProperty("remark");
        }
    }

    public java.util.Date getOperateAt() {
        return operateAt;
    }

    public void setOperateAt(java.util.Date operateAt) {
        this.operateAt = operateAt;
    }

    public void setOperateAt(java.util.Date operateAt, boolean forceUpdate) {
        setOperateAt(operateAt);

        if (forceUpdate) {
          addForceUpdateProperty("operateAt");
        }
    }
}
