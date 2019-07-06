package com.wangpiece.ious.vo;

import java.io.Serializable;

public class RegisterInfoVO implements Serializable {

    private String password;
    private String surePassword;
    private String tradingPassword;
    private String sureTradingPassword;
    private String name;
    private String code;
    private String phone;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurePassword() {
        return surePassword;
    }

    public void setSurePassword(String surePassword) {
        this.surePassword = surePassword;
    }

    public String getTradingPassword() {
        return tradingPassword;
    }

    public void setTradingPassword(String tradingPassword) {
        this.tradingPassword = tradingPassword;
    }

    public String getSureTradingPassword() {
        return sureTradingPassword;
    }

    public void setSureTradingPassword(String sureTradingPassword) {
        this.sureTradingPassword = sureTradingPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "RegisterInfoVO{" +
                "password='" + password + '\'' +
                ", surePassword='" + surePassword + '\'' +
                ", tradingPassword='" + tradingPassword + '\'' +
                ", sureTradingPassword='" + sureTradingPassword + '\'' +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
