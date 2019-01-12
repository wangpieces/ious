package com.wangpiece.ious.vo;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-22 18:17
 */
public class IousVO implements Serializable{
    private static final long serialVersionUID = 7923534180782793015L;

    private Integer id;
    /**
     * 订单专题
     */
    private String statusStr;
    /**
     * 名字
     */
    private String name;
    /**
     * 金额
     */
    private Double money;
    /**
     * 借款时长
     */
    private String timeLength;
    /**
     * 剩余天数
     */
    private Integer remainTime;
    /**
     * 借款利率
     */
    private String rate;

    /**
     * 借款用途
     */
    private String purposeName;

    private Integer	lendUserId;
    private Integer	loanUserId;

    private Integer	cancelStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength;
    }

    public Integer getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(Integer remainTime) {
        this.remainTime = remainTime;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPurposeName() {
        return purposeName;
    }

    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
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

    public Integer getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(Integer cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    @Override
    public String toString() {
        return "IousVO{" +
                "id=" + id +
                ", statusStr='" + statusStr + '\'' +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", timeLength='" + timeLength + '\'' +
                ", remainTime='" + remainTime + '\'' +
                ", rate='" + rate + '\'' +
                ", purposeName='" + purposeName + '\'' +
                ", lendUserId=" + lendUserId +
                ", loanUserId=" + loanUserId +
                ", cancelStatus=" + cancelStatus +
                '}';
    }
}
