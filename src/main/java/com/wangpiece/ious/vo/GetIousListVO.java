package com.wangpiece.ious.vo;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc 获取借条信息VO
 * @date 2019-01-03 23:23
 */
public class GetIousListVO implements Serializable{
    private static final long serialVersionUID = 683500715668818457L;

    private Integer	lendUserId;
    private Integer	loanUserId;
    // 加载数据类型 1-借款人获取借条信息 4-出借人获取借条信息（这个值和type初始值一样）
    private Integer loadType;
    // 1-获取全部 2-获取借款/出借时间为7天 3-获取借款/出借时间为30天
    private Integer loadTimeType;
    //搜索关键字
    private String keyWord;

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

    public Integer getLoadType() {
        return loadType;
    }

    public void setLoadType(Integer loadType) {
        this.loadType = loadType;
    }

    public Integer getLoadTimeType() {
        return loadTimeType;
    }

    public void setLoadTimeType(Integer loadTimeType) {
        this.loadTimeType = loadTimeType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
