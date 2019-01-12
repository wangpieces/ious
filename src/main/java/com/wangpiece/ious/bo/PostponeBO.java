package com.wangpiece.ious.bo;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2019-01-09 22:47
 */
public class PostponeBO implements Serializable {

    private static final long serialVersionUID = -1274690428007986402L;
    private Integer	id;
    private Integer	iousId;
    private Integer	postponeRate;
    private String	poastponeTime;
    private Integer	postponeStatus;
    private String	createTime;
    private String	updateTime;
    //利率名称
    private String postponeRateName;
    //状态
    private String postponeStatusName;
    //展期金额
    private Double money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIousId() {
        return iousId;
    }

    public void setIousId(Integer iousId) {
        this.iousId = iousId;
    }

    public Integer getPostponeRate() {
        return postponeRate;
    }

    public void setPostponeRate(Integer postponeRate) {
        this.postponeRate = postponeRate;
    }

    public String getPoastponeTime() {
        return poastponeTime;
    }

    public void setPoastponeTime(String poastponeTime) {
        this.poastponeTime = poastponeTime;
    }

    public Integer getPostponeStatus() {
        return postponeStatus;
    }

    public void setPostponeStatus(Integer postponeStatus) {
        this.postponeStatus = postponeStatus;
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

    public String getPostponeRateName() {
        return postponeRateName;
    }

    public void setPostponeRateName(String postponeRateName) {
        this.postponeRateName = postponeRateName;
    }

    public String getPostponeStatusName() {
        return postponeStatusName;
    }

    public void setPostponeStatusName(String postponeStatusName) {
        this.postponeStatusName = postponeStatusName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
