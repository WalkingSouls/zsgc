package com.zsgc.core.model;

import com.calanger.common.dao.AbstractPO;

public class UcenterMember extends AbstractPO {
    private static final long serialVersionUID = 1L;

    private java.lang.Integer id;             //用户id
    private java.lang.String username;        //用户账户名
    private java.lang.String password;        //用户密码
    private java.lang.String tradingPassword; //
    private java.lang.Integer isAuthenticated;//是否身份认证
    private java.lang.Integer isCheck         ;//是否认证
    private Integer checkType;                 //认证类型
    private String  company;                   //公司
    private String  organize;                  //组织
    private String  zone;                      //园区
    private java.lang.Integer isBindBankCard; //
    private java.lang.String name;            //用户姓名
    private java.lang.String avatarPath;
    private java.lang.String email;           //
    private java.lang.String mobile;          //手机号
    private java.util.Date createdAt;         //创建日期
    private java.lang.String regIp;
    private java.util.Date lastLoginTime;     //首次登录时间
    private java.lang.String lastLoginIp;     //首次登录IP
    private java.util.Date updatedAt;         //更新时间
    private java.lang.Integer status;         //状态
    private java.lang.Integer registerFrom;
    private java.lang.Integer friendIncomeStatus;
    private java.lang.Integer sex;            //性别
    private java.util.Date birthday;          //生日
    private java.lang.String qq;              //QQ号
    private java.lang.Integer score;          //积分
    private java.lang.Integer experience;     //经验值
    private java.lang.String signature;       //个性签名
    private java.lang.Integer continuLogin;   //连续登录次数
    private java.lang.Integer login;          //登录次数
    private java.lang.Integer type;           //
    private java.lang.Integer manager;
    private java.lang.Integer salesmanager;
    private java.lang.String registerId;
    private java.lang.String sid;
    private java.lang.String material;
    private java.lang.Integer typeid;
    private java.lang.Integer lx;
    private java.lang.Integer referrerId;
    private java.lang.Integer referrerType;
    private java.lang.String referrerUrl;
    private java.lang.String packageName;
    private java.lang.String queryStr;
    private java.lang.String area;             //行政区划
    private java.lang.String invitationCode;   //邀请码
    private java.util.Date firstInvestAt;
    private java.lang.Integer errLoginTimes;

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

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public void setUsername(java.lang.String username, boolean forceUpdate) {
        setUsername(username);

        if (forceUpdate) {
          addForceUpdateProperty("username");
        }
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    public void setPassword(java.lang.String password, boolean forceUpdate) {
        setPassword(password);

        if (forceUpdate) {
          addForceUpdateProperty("password");
        }
    }

    public java.lang.String getTradingPassword() {
        return tradingPassword;
    }

    public void setTradingPassword(java.lang.String tradingPassword) {
        this.tradingPassword = tradingPassword;
    }

    public void setTradingPassword(java.lang.String tradingPassword, boolean forceUpdate) {
        setTradingPassword(tradingPassword);

        if (forceUpdate) {
          addForceUpdateProperty("tradingPassword");
        }
    }

    public java.lang.Integer getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(java.lang.Integer isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public void setIsAuthenticated(java.lang.Integer isAuthenticated, boolean forceUpdate) {
        setIsAuthenticated(isAuthenticated);

        if (forceUpdate) {
          addForceUpdateProperty("isAuthenticated");
        }
    }

    
    public java.lang.Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(java.lang.Integer isCheck) {
		this.isCheck = isCheck;
	}
	public void setIsCheck(java.lang.Integer isCheck, boolean forceUpdate) {
		setIsCheck(isCheck);
        if (forceUpdate) {
          addForceUpdateProperty("isCheck");
        }
    }
	
	
	public String getOrganize() {
		return organize;
	}

	public void setOrganize(String organize) {
		this.organize = organize;
	}
	public void setOrganize(String organize, boolean forceUpdate) {
		setOrganize(organize);
		if (forceUpdate) {
	          addForceUpdateProperty("organize");
	        }
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}
	public void setCheckType(Integer checkType, boolean forceUpdate) {
		setCheckType( checkType);
		if (forceUpdate) {
	         addForceUpdateProperty("checkType");
	     }
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
    
	public void setCompany(String company, boolean forceUpdate) {
		setCompany(company);
		if (forceUpdate) {
	         addForceUpdateProperty("company");
	     }
	}
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
	
	public void setZone(String zone, boolean forceUpdate) {
		setZone(zone);
		if (forceUpdate) {
	         addForceUpdateProperty("zone");
	     }
	}

	public java.lang.Integer getIsBindBankCard() {
        return isBindBankCard;
    }

    public void setIsBindBankCard(java.lang.Integer isBindBankCard) {
        this.isBindBankCard = isBindBankCard;
    }

    public void setIsBindBankCard(java.lang.Integer isBindBankCard, boolean forceUpdate) {
        setIsBindBankCard(isBindBankCard);

        if (forceUpdate) {
          addForceUpdateProperty("isBindBankCard");
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

    public java.lang.String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(java.lang.String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public void setAvatarPath(java.lang.String avatarPath, boolean forceUpdate) {
        setAvatarPath(avatarPath);

        if (forceUpdate) {
          addForceUpdateProperty("avatarPath");
        }
    }

    public java.lang.String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public void setEmail(java.lang.String email, boolean forceUpdate) {
        setEmail(email);

        if (forceUpdate) {
          addForceUpdateProperty("email");
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

    public java.lang.String getRegIp() {
        return regIp;
    }

    public void setRegIp(java.lang.String regIp) {
        this.regIp = regIp;
    }

    public void setRegIp(java.lang.String regIp, boolean forceUpdate) {
        setRegIp(regIp);

        if (forceUpdate) {
          addForceUpdateProperty("regIp");
        }
    }

    public java.util.Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(java.util.Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public void setLastLoginTime(java.util.Date lastLoginTime, boolean forceUpdate) {
        setLastLoginTime(lastLoginTime);

        if (forceUpdate) {
          addForceUpdateProperty("lastLoginTime");
        }
    }

    public java.lang.String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(java.lang.String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public void setLastLoginIp(java.lang.String lastLoginIp, boolean forceUpdate) {
        setLastLoginIp(lastLoginIp);

        if (forceUpdate) {
          addForceUpdateProperty("lastLoginIp");
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

    public java.lang.Integer getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(java.lang.Integer registerFrom) {
        this.registerFrom = registerFrom;
    }

    public void setRegisterFrom(java.lang.Integer registerFrom, boolean forceUpdate) {
        setRegisterFrom(registerFrom);

        if (forceUpdate) {
          addForceUpdateProperty("registerFrom");
        }
    }

    public java.lang.Integer getFriendIncomeStatus() {
        return friendIncomeStatus;
    }

    public void setFriendIncomeStatus(java.lang.Integer friendIncomeStatus) {
        this.friendIncomeStatus = friendIncomeStatus;
    }

    public void setFriendIncomeStatus(java.lang.Integer friendIncomeStatus, boolean forceUpdate) {
        setFriendIncomeStatus(friendIncomeStatus);

        if (forceUpdate) {
          addForceUpdateProperty("friendIncomeStatus");
        }
    }

    public java.lang.Integer getSex() {
        return sex;
    }

    public void setSex(java.lang.Integer sex) {
        this.sex = sex;
    }

    public void setSex(java.lang.Integer sex, boolean forceUpdate) {
        setSex(sex);

        if (forceUpdate) {
          addForceUpdateProperty("sex");
        }
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(java.util.Date birthday, boolean forceUpdate) {
        setBirthday(birthday);

        if (forceUpdate) {
          addForceUpdateProperty("birthday");
        }
    }

    public java.lang.String getQq() {
        return qq;
    }

    public void setQq(java.lang.String qq) {
        this.qq = qq;
    }

    public void setQq(java.lang.String qq, boolean forceUpdate) {
        setQq(qq);

        if (forceUpdate) {
          addForceUpdateProperty("qq");
        }
    }

    public java.lang.Integer getScore() {
        return score;
    }

    public void setScore(java.lang.Integer score) {
        this.score = score;
    }


	public void setScore(java.lang.Integer score, boolean forceUpdate) {
        setScore(score);

        if (forceUpdate) {
          addForceUpdateProperty("score");
        }
    }

    
    public java.lang.Integer getExperience() {
		return experience;
	}

	public void setExperience(java.lang.Integer experience) {
		this.experience = experience;
	}
	public void setExperience(java.lang.Integer experience, boolean forceUpdate) {
		setExperience(experience);
		if (forceUpdate) {
	          addForceUpdateProperty("experience");
	        }
	}

	
    public java.lang.String getSignature() {
		return signature;
	}

	public void setSignature(java.lang.String signature) {
		this.signature = signature;
	}
	public void setSignature(java.lang.String signature, boolean forceUpdate) {
		setSignature(signature);
		if (forceUpdate) {
	          addForceUpdateProperty("signature");
	        }
	}

	public java.lang.Integer getLogin() {
        return login;
    }

    public void setLogin(java.lang.Integer login) {
        this.login = login;
    }

    public void setLogin(java.lang.Integer login, boolean forceUpdate) {
        setLogin(login);

        if (forceUpdate) {
          addForceUpdateProperty("login");
        }
    }
    

    public java.lang.Integer getContinuLogin() {
		return continuLogin;
	}

	public void setContinuLogin(java.lang.Integer continuLogin) {
		this.continuLogin = continuLogin;
	}
	public void setContinuLogin(java.lang.Integer continuLogin, boolean forceUpdate) {
		setContinuLogin(continuLogin);
		if (forceUpdate) {
	          addForceUpdateProperty("continuLogin");
	       }
	}

	public java.lang.Integer getType() {
        return type;
    }

    public void setType(java.lang.Integer type) {
        this.type = type;
    }

    public void setType(java.lang.Integer type, boolean forceUpdate) {
        setType(type);

        if (forceUpdate) {
          addForceUpdateProperty("type");
        }
    }

    public java.lang.Integer getManager() {
        return manager;
    }

    public void setManager(java.lang.Integer manager) {
        this.manager = manager;
    }

    public void setManager(java.lang.Integer manager, boolean forceUpdate) {
        setManager(manager);

        if (forceUpdate) {
          addForceUpdateProperty("manager");
        }
    }

    public java.lang.Integer getSalesmanager() {
        return salesmanager;
    }

    public void setSalesmanager(java.lang.Integer salesmanager) {
        this.salesmanager = salesmanager;
    }

    public void setSalesmanager(java.lang.Integer salesmanager, boolean forceUpdate) {
        setSalesmanager(salesmanager);

        if (forceUpdate) {
          addForceUpdateProperty("salesmanager");
        }
    }

    public java.lang.String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(java.lang.String registerId) {
        this.registerId = registerId;
    }

    public void setRegisterId(java.lang.String registerId, boolean forceUpdate) {
        setRegisterId(registerId);

        if (forceUpdate) {
          addForceUpdateProperty("registerId");
        }
    }

    public java.lang.String getSid() {
        return sid;
    }

    public void setSid(java.lang.String sid) {
        this.sid = sid;
    }

    public void setSid(java.lang.String sid, boolean forceUpdate) {
        setSid(sid);

        if (forceUpdate) {
          addForceUpdateProperty("sid");
        }
    }

    public java.lang.String getMaterial() {
        return material;
    }

    public void setMaterial(java.lang.String material) {
        this.material = material;
    }

    public void setMaterial(java.lang.String material, boolean forceUpdate) {
        setMaterial(material);

        if (forceUpdate) {
          addForceUpdateProperty("material");
        }
    }

    public java.lang.Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(java.lang.Integer typeid) {
        this.typeid = typeid;
    }

    public void setTypeid(java.lang.Integer typeid, boolean forceUpdate) {
        setTypeid(typeid);

        if (forceUpdate) {
          addForceUpdateProperty("typeid");
        }
    }

    public java.lang.Integer getLx() {
        return lx;
    }

    public void setLx(java.lang.Integer lx) {
        this.lx = lx;
    }

    public void setLx(java.lang.Integer lx, boolean forceUpdate) {
        setLx(lx);

        if (forceUpdate) {
          addForceUpdateProperty("lx");
        }
    }

    public java.lang.Integer getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(java.lang.Integer referrerId) {
        this.referrerId = referrerId;
    }

    public void setReferrerId(java.lang.Integer referrerId, boolean forceUpdate) {
        setReferrerId(referrerId);

        if (forceUpdate) {
          addForceUpdateProperty("referrerId");
        }
    }

    public java.lang.Integer getReferrerType() {
        return referrerType;
    }

    public void setReferrerType(java.lang.Integer referrerType) {
        this.referrerType = referrerType;
    }

    public void setReferrerType(java.lang.Integer referrerType, boolean forceUpdate) {
        setReferrerType(referrerType);

        if (forceUpdate) {
          addForceUpdateProperty("referrerType");
        }
    }

    public java.lang.String getReferrerUrl() {
        return referrerUrl;
    }

    public void setReferrerUrl(java.lang.String referrerUrl) {
        this.referrerUrl = referrerUrl;
    }

    public void setReferrerUrl(java.lang.String referrerUrl, boolean forceUpdate) {
        setReferrerUrl(referrerUrl);

        if (forceUpdate) {
          addForceUpdateProperty("referrerUrl");
        }
    }

    public java.lang.String getPackageName() {
        return packageName;
    }

    public void setPackageName(java.lang.String packageName) {
        this.packageName = packageName;
    }

    public void setPackageName(java.lang.String packageName, boolean forceUpdate) {
        setPackageName(packageName);

        if (forceUpdate) {
          addForceUpdateProperty("packageName");
        }
    }

    public java.lang.String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(java.lang.String queryStr) {
        this.queryStr = queryStr;
    }

    public void setQueryStr(java.lang.String queryStr, boolean forceUpdate) {
        setQueryStr(queryStr);

        if (forceUpdate) {
          addForceUpdateProperty("queryStr");
        }
    }

    public java.lang.String getArea() {
        return area;
    }

    public void setArea(java.lang.String area) {
        this.area = area;
    }

    public void setArea(java.lang.String area, boolean forceUpdate) {
        setArea(area);

        if (forceUpdate) {
          addForceUpdateProperty("area");
        }
    }

    public java.lang.String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(java.lang.String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public void setInvitationCode(java.lang.String invitationCode, boolean forceUpdate) {
        setInvitationCode(invitationCode);

        if (forceUpdate) {
          addForceUpdateProperty("invitationCode");
        }
    }

    public java.util.Date getFirstInvestAt() {
        return firstInvestAt;
    }

    public void setFirstInvestAt(java.util.Date firstInvestAt) {
        this.firstInvestAt = firstInvestAt;
    }

    public void setFirstInvestAt(java.util.Date firstInvestAt, boolean forceUpdate) {
        setFirstInvestAt(firstInvestAt);

        if (forceUpdate) {
          addForceUpdateProperty("firstInvestAt");
        }
    }
    
    public java.lang.Integer getErrLoginTimes() {
        return errLoginTimes;
    }

    public void setErrLoginTimes(java.lang.Integer errLoginTimes) {
        this.errLoginTimes = errLoginTimes;
    }

    public void setErrLoginTimes(java.lang.Integer errLoginTimes, boolean forceUpdate) {
        setErrLoginTimes(errLoginTimes);

        if (forceUpdate) {
          addForceUpdateProperty("errLoginTimes");
        }
    }
}
