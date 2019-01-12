package com.wangpiece.ious.bo;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-15 0:18
 */
public class UserBO implements Serializable{


    private static final long serialVersionUID = -318444372334354627L;
    private Integer	id;
    /**
     * 用户名
     */
    private String	userName;
    /**
     * 真实姓名
     */
    private String	name;
    /**
     * 交易密码
     */
    private String tradingPassword;

    /**
     * 电话号码
     */
    private String	phone;
    private String	openId;
    private String	createTime;
    private String	updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTradingPassword() {
        return tradingPassword;
    }

    public void setTradingPassword(String tradingPassword) {
        this.tradingPassword = tradingPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", tradingPassword='" + tradingPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", openId='" + openId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
