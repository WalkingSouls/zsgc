package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class Dict extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer typeId;
    private java.lang.String dictKey;
    private java.lang.String dictValue;
    private java.lang.String dictValue1;
    private java.lang.String dictValue2;
    private java.lang.String dictValue3;
    private java.lang.Integer sortOrder;
    private java.lang.String memo;
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

    public java.lang.Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(java.lang.Integer typeId) {
        this.typeId = typeId;
    }

    public void setTypeId(java.lang.Integer typeId, boolean forceUpdate) {
        setTypeId(typeId);

        if (forceUpdate) {
          addForceUpdateProperty("typeId");
        }
    }

    public java.lang.String getDictKey() {
        return dictKey;
    }

    public void setDictKey(java.lang.String dictKey) {
        this.dictKey = dictKey;
    }

    public void setDictKey(java.lang.String dictKey, boolean forceUpdate) {
        setDictKey(dictKey);

        if (forceUpdate) {
          addForceUpdateProperty("dictKey");
        }
    }

    public java.lang.String getDictValue() {
        return dictValue;
    }

    public void setDictValue(java.lang.String dictValue) {
        this.dictValue = dictValue;
    }

    public void setDictValue(java.lang.String dictValue, boolean forceUpdate) {
        setDictValue(dictValue);

        if (forceUpdate) {
          addForceUpdateProperty("dictValue");
        }
    }

    public java.lang.String getDictValue1() {
        return dictValue1;
    }

    public void setDictValue1(java.lang.String dictValue1) {
        this.dictValue1 = dictValue1;
    }

    public void setDictValue1(java.lang.String dictValue1, boolean forceUpdate) {
        setDictValue1(dictValue1);

        if (forceUpdate) {
          addForceUpdateProperty("dictValue1");
        }
    }

    public java.lang.String getDictValue2() {
        return dictValue2;
    }

    public void setDictValue2(java.lang.String dictValue2) {
        this.dictValue2 = dictValue2;
    }

    public void setDictValue2(java.lang.String dictValue2, boolean forceUpdate) {
        setDictValue2(dictValue2);

        if (forceUpdate) {
          addForceUpdateProperty("dictValue2");
        }
    }

    public java.lang.String getDictValue3() {
        return dictValue3;
    }

    public void setDictValue3(java.lang.String dictValue3) {
        this.dictValue3 = dictValue3;
    }

    public void setDictValue3(java.lang.String dictValue3, boolean forceUpdate) {
        setDictValue3(dictValue3);

        if (forceUpdate) {
          addForceUpdateProperty("dictValue3");
        }
    }

    public java.lang.Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(java.lang.Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setSortOrder(java.lang.Integer sortOrder, boolean forceUpdate) {
        setSortOrder(sortOrder);

        if (forceUpdate) {
          addForceUpdateProperty("sortOrder");
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
