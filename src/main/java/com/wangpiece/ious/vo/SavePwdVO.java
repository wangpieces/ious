package com.wangpiece.ious.vo;

import java.io.Serializable;

public class SavePwdVO implements Serializable {

    private String oldPwd;
    private String newPwd;
    //flag 1-登录密码 2-交易密码
    private  Integer flag;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
