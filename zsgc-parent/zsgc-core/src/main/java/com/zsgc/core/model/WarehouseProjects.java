package com.zsgc.core.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarehouseProjects {
    private Integer pId;

    private String projectName;

    private String intentionalCity;

    private String investmentAmount;

    private Integer warehouseType;

    private String city;

    private String industry;

    private Integer state;
    
    private Integer delState;

    private Date createP;

    private Date updateP;

    private Integer uId;
    
    private Byte delCauseNum;

    private String delCause;
    
    private BigDecimal score;

    private Integer top;

    private Date topTime;

    private Integer collectionNum;

    private Integer followNum;

    private Integer commentNum;
    
    private Integer commentState;
    
    private String commentContent;
    
    private Byte sharedBook;
    
	private List<ProjectDescription> list  = new ArrayList<>();
    
    private	List<CustomerProfile> listCP = new ArrayList<>();
    
    public Byte getSharedBook() {
		return sharedBook;
	}

	public void setSharedBook(Byte sharedBook) {
		this.sharedBook = sharedBook;
	}

	public List<ProjectDescription> getList() {
		return list;
	}

	public void setList(List<ProjectDescription> list) {
		this.list = list;
	}

	public List<CustomerProfile> getListCP() {
		return listCP;
	}

	public void setListCP(List<CustomerProfile> listCP) {
		this.listCP = listCP;
	}

	public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getIntentionalCity() {
        return intentionalCity;
    }

    public void setIntentionalCity(String intentionalCity) {
        this.intentionalCity = intentionalCity;
    }

    public String getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(String investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public Integer getWarehouseType() {
        return warehouseType;
    }

    public void setWarehouseType(Integer warehouseType) {
        this.warehouseType = warehouseType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	public Integer getDelState() {
        return delState;
    }

    public void setDelState(Integer delState) {
        this.delState = delState;
    }

    public Date getCreateP() {
        return createP;
    }

    public void setCreateP(Date createP) {
        this.createP = createP;
    }

    public Date getUpdateP() {
        return updateP;
    }

    public void setUpdateP(Date updateP) {
        this.updateP = updateP;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }
    
    public Byte getDelCauseNum() {
		return delCauseNum;
	}

	public void setDelCauseNum(Byte delCauseNum) {
		this.delCauseNum = delCauseNum;
	}

	public String getDelCause() {
        return delCause;
    }

    public void setDelCause(String delCause) {
        this.delCause = delCause;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Date getTopTime() {
        return topTime;
    }

    public void setTopTime(Date topTime) {
        this.topTime = topTime;
    }

    public Integer getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(Integer collectionNum) {
        this.collectionNum = collectionNum;
    }

    public Integer getFollowNum() {
        return followNum;
    }

    public void setFollowNum(Integer followNum) {
        this.followNum = followNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

	public Integer getCommentState() {
		return commentState;
	}

	public void setCommentState(Integer commentState) {
		this.commentState = commentState;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}  
}
    
   
    