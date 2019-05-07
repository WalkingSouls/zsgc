package com.zsgc.core.model;

import java.math.BigDecimal;

public class UserExtend {
    private Integer id;

    private Integer uId;

    private Integer pId;

    private Integer isCollection;

    private Integer isScore;

    private BigDecimal score;

    private Integer nowPId;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Integer isCollection) {
        this.isCollection = isCollection;
    }

    public Integer getIsScore() {
        return isScore;
    }

    public void setIsScore(Integer isScore) {
        this.isScore = isScore;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getNowPId() {
        return nowPId;
    }

    public void setNowPId(Integer nowPId) {
        this.nowPId = nowPId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}