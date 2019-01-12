package com.wangpiece.ious.dto;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-20 22:49
 */
public class Complaint implements Serializable{


    private static final long serialVersionUID = -6126090519168731874L;
    private Integer	id;
    private Integer	iousId;
    private String	username;
    private String	reason;
    private String	content;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Complaint{" +
                "id=" + id +
                ", iousId=" + iousId +
                ", username='" + username + '\'' +
                ", reason='" + reason + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
