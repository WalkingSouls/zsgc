package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class BankBranch extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer pid;
    private java.lang.String name;
    private java.lang.String province;
    private java.lang.String city;

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

    public java.lang.Integer getPid() {
        return pid;
    }

    public void setPid(java.lang.Integer pid) {
        this.pid = pid;
    }

    public void setPid(java.lang.Integer pid, boolean forceUpdate) {
        setPid(pid);

        if (forceUpdate) {
          addForceUpdateProperty("pid");
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
}
