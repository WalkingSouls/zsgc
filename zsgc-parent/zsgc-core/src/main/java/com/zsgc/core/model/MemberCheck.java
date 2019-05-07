package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class MemberCheck extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer uid;
    private java.lang.String place;
    private java.lang.String name;
    private java.lang.String idCard;
    private java.lang.Integer status;
    private java.lang.Integer adminId;
    private java.lang.String picFront;
    private java.lang.String picBeside;
    private java.lang.String picHand;
    private java.lang.String note;
    private java.util.Date createdAt;
    private java.util.Date checkTime;
    private java.util.Date updatedAt;
    private java.lang.String esignAccountId;
    private java.lang.String esignSealData;

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

    public java.lang.String getPlace() {
        return place;
    }

    public void setPlace(java.lang.String place) {
        this.place = place;
    }

    public void setPlace(java.lang.String place, boolean forceUpdate) {
        setPlace(place);

        if (forceUpdate) {
          addForceUpdateProperty("place");
        }
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public void setName(java.lang.String name, boolean forceUpdate) {
        setName(name);

        if (forceUpdate) {
          addForceUpdateProperty("name");
        }
    }

    public java.lang.String getIdCard() {
        return idCard;
    }

    public void setIdCard(java.lang.String idCard) {
        this.idCard = idCard;
    }

    public void setIdCard(java.lang.String idCard, boolean forceUpdate) {
        setIdCard(idCard);

        if (forceUpdate) {
          addForceUpdateProperty("idCard");
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

    public java.lang.String getPicFront() {
        return picFront;
    }

    public void setPicFront(java.lang.String picFront) {
        this.picFront = picFront;
    }

    public void setPicFront(java.lang.String picFront, boolean forceUpdate) {
        setPicFront(picFront);

        if (forceUpdate) {
          addForceUpdateProperty("picFront");
        }
    }

    public java.lang.String getPicBeside() {
        return picBeside;
    }

    public void setPicBeside(java.lang.String picBeside) {
        this.picBeside = picBeside;
    }

    public void setPicBeside(java.lang.String picBeside, boolean forceUpdate) {
        setPicBeside(picBeside);

        if (forceUpdate) {
          addForceUpdateProperty("picBeside");
        }
    }

    public java.lang.String getPicHand() {
        return picHand;
    }

    public void setPicHand(java.lang.String picHand) {
        this.picHand = picHand;
    }

    public void setPicHand(java.lang.String picHand, boolean forceUpdate) {
        setPicHand(picHand);

        if (forceUpdate) {
          addForceUpdateProperty("picHand");
        }
    }

    public java.lang.String getNote() {
        return note;
    }

    public void setNote(java.lang.String note) {
        this.note = note;
    }

    public void setNote(java.lang.String note, boolean forceUpdate) {
        setNote(note);

        if (forceUpdate) {
          addForceUpdateProperty("note");
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

    public java.util.Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(java.util.Date checkTime) {
        this.checkTime = checkTime;
    }

    public void setCheckTime(java.util.Date checkTime, boolean forceUpdate) {
        setCheckTime(checkTime);

        if (forceUpdate) {
          addForceUpdateProperty("checkTime");
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

    public java.lang.String getEsignAccountId() {
        return esignAccountId;
    }

    public void setEsignAccountId(java.lang.String esignAccountId) {
        this.esignAccountId = esignAccountId;
    }

    public void setEsignAccountId(java.lang.String esignAccountId, boolean forceUpdate) {
        setEsignAccountId(esignAccountId);

        if (forceUpdate) {
          addForceUpdateProperty("esignAccountId");
        }
    }

    public java.lang.String getEsignSealData() {
        return esignSealData;
    }

    public void setEsignSealData(java.lang.String esignSealData) {
        this.esignSealData = esignSealData;
    }

    public void setEsignSealData(java.lang.String esignSealData, boolean forceUpdate) {
        setEsignSealData(esignSealData);

        if (forceUpdate) {
          addForceUpdateProperty("esignSealData");
        }
    }
}
