package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

import java.util.Date;

public class MemberCouponRule extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer uid;
    private Integer type;
    private java.util.Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
