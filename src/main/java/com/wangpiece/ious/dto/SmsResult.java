package com.wangpiece.ious.dto;

public class SmsResult {

    private Integer id;
    private String message;
    private String requestId;
    private String bizId;
    private String code;
    private String responseCode;
    private Integer invalidFlag;
    private String createTime;
    private String invalidTime;
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Integer getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(Integer invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "SmsResult{" +
                "message='" + message + '\'' +
                ", id='" + id + '\'' +
                ", requestId='" + requestId + '\'' +
                ", bizId='" + bizId + '\'' +
                ", code='" + code + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", invalidFlag=" + invalidFlag +
                ", createTime='" + createTime + '\'' +
                ", invalidTime='" + invalidTime + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
