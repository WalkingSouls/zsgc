package com.zsgc.core.model;

import java.util.Date;

public class TaskMsg {
    private Long id;

    private Integer tId;

    private Long mId;

    private Byte invalid;

    private Date creatAt;

    private Date updateAt;

    private Date pushAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Byte getInvalid() {
        return invalid;
    }

    public void setInvalid(Byte invalid) {
        this.invalid = invalid;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getPushAt() {
        return pushAt;
    }

    public void setPushAt(Date pushAt) {
        this.pushAt = pushAt;
    }
}