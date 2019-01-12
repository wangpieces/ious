package com.wangpiece.ious.vo;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-15 23:39
 */
public class UserVO implements Serializable{

    private static final long serialVersionUID = -1853094729706158033L;

    private Integer	id;
    /**
     * 用户名
     */
    private String	userName;
    /**
     * 真实姓名
     */
    private String	name;
    private String openId;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }
}
