package com.wangpiece.ious.bo;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-20 22:47
 */
public class IousBO implements Serializable{

    private static final long serialVersionUID = -994683392934709255L;
    private Integer	id;
    private Integer	lendUserId;
    private Integer	loanUserId;
    private Double	money;
    private String	loanTime;
    private String	returnTime;
    private Integer	rate;
    private String	lender;
    private String	loaner;
    private Integer	type;
    private Integer	purpose;
    private String	moreInfo;
    private Integer	status;
    private Integer	cancelStatus;
    private String	createTime;
    private String	updateTime;

    /**
     * 借款百分比
     */
    private String rateName;

    /**
     * 借款用途
     */
    private String purposeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLendUserId() {
        return lendUserId;
    }

    public void setLendUserId(Integer lendUserId) {
        this.lendUserId = lendUserId;
    }

    public Integer getLoanUserId() {
        return loanUserId;
    }

    public void setLoanUserId(Integer loanUserId) {
        this.loanUserId = loanUserId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getLender() {
        return lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
    }

    public String getLoaner() {
        return loaner;
    }

    public void setLoaner(String loaner) {
        this.loaner = loaner;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Integer cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
    }

    @Override
    public String toString() {
        return "IousBO{" +
                "id=" + id +
                ", lendUserId=" + lendUserId +
                ", loanUserId=" + loanUserId +
                ", money=" + money +
                ", loanTime='" + loanTime + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", rate=" + rate +
                ", lender='" + lender + '\'' +
                ", loaner='" + loaner + '\'' +
                ", type=" + type +
                ", purpose=" + purpose +
                ", moreInfo='" + moreInfo + '\'' +
                ", status=" + status +
                ", cancelStatus=" + cancelStatus +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", rateName='" + rateName + '\'' +
                ", purposeName='" + purposeName + '\'' +
                '}';
    }
}
