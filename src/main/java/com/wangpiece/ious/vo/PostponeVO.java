package com.wangpiece.ious.vo;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2019-01-09 22:47
 */
public class PostponeVO implements Serializable {

    private static final long serialVersionUID = -1274690428007986402L;
    private Integer	iousId;
    private Integer	postponeRate;
    private String	postponeTime;

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
}
