package com.wangpiece.ious.dto;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-20 22:48
 */
public class Postpone implements Serializable{

    private static final long serialVersionUID = 8935360728381288516L;
    private Integer	id;
    private Integer	iousId;
    private Integer	postponeRate;
    private String	postponeTime;
    private Integer	postponeStatus;
    private Integer money;
    private String	createTime;
    private String	updateTime;

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

    public String getPostponeTime() {
        return postponeTime;
    }

    public void setPostponeTime(String postponeTime) {
        this.postponeTime = postponeTime;
    }

    public Integer getPostponeStatus() {
        return postponeStatus;
    }

    public void setPostponeStatus(Integer postponeStatus) {
        this.postponeStatus = postponeStatus;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
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

    @Override
    public String toString() {
        return "Postpone{" +
                "id=" + id +
                ", iousId=" + iousId +
                ", postponeRate=" + postponeRate +
                ", postponeTime='" + postponeTime + '\'' +
                ", postponeStatus=" + postponeStatus +
                ", money=" + money +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
