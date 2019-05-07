package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class Mybank extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer uid;
    private java.lang.Integer bid;
    private java.lang.String name;
    private java.lang.String number;
    private java.lang.String mobile;
    private java.lang.String province;
    private java.lang.String city;
    private java.lang.String branch;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

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

    public java.lang.String getMobile() {
        return mobile;
    }

    public void setMobile(java.lang.String mobile) {
        this.mobile = mobile;
    }

    public void setMobile(java.lang.String mobile, boolean forceUpdate) {
        setMobile(mobile);

        if (forceUpdate) {
          addForceUpdateProperty("mobile");
        }
    }

    public java.lang.String getProvince() {
        return province;
    }

    public void setProvince(java.lang.String province) {
        this.province = province;
    }

    public void setProvince(java.lang.String province, boolean forceUpdate) {
        setProvince(province);

        if (forceUpdate) {
          addForceUpdateProperty("province");
        }
    }

    public java.lang.String getCity() {
        return city;
    }

    public void setCity(java.lang.String city) {
        this.city = city;
    }

    public void setCity(java.lang.String city, boolean forceUpdate) {
        setCity(city);

        if (forceUpdate) {
          addForceUpdateProperty("city");
        }
    }

    public java.lang.String getBranch() {
        return branch;
    }

    public void setBranch(java.lang.String branch) {
        this.branch = branch;
    }

    public void setBranch(java.lang.String branch, boolean forceUpdate) {
        setBranch(branch);

        if (forceUpdate) {
          addForceUpdateProperty("branch");
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
