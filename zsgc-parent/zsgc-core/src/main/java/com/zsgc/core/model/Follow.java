package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class Follow extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;
    private java.lang.Integer userId;
    private java.lang.Integer followUserId;
    private java.lang.Integer followUserType;
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

    public java.lang.Integer getUserId() {
        return userId;
    }

    public void setUserId(java.lang.Integer userId) {
        this.userId = userId;
    }

    public void setUserId(java.lang.Integer userId, boolean forceUpdate) {
        setUserId(userId);

        if (forceUpdate) {
          addForceUpdateProperty("userId");
        }
    }

	public java.lang.Integer getFollowUserId() {
		return followUserId;
	}

	public void setFollowUserId(java.lang.Integer followUserId) {
		this.followUserId = followUserId;
	}
	public void setFollowUserId(java.lang.Integer followUserId, boolean forceUpdate) {
		setFollowUserId(followUserId);
		if (forceUpdate) {
	          addForceUpdateProperty("followUserId");
	        }
	}

	public java.lang.Integer getFollowUserType() {
		return followUserType;
	}

	public void setFollowUserType(java.lang.Integer followUserType) {
		this.followUserType = followUserType;
	}
	
	public void setFollowUserType(java.lang.Integer followUserType, boolean forceUpdate) {
		setFollowUserType(followUserType);
		this.followUserType = followUserType;
		if (forceUpdate) {
	          addForceUpdateProperty("followUserType");
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
