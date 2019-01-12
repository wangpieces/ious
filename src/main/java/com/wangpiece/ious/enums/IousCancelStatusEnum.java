package com.wangpiece.ious.enums;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-25 19:37
 */
public enum IousCancelStatusEnum {

    CANCEL_STATUS_UNACCOUNT(1, "未销账"),
    CANCEL_STATUS_ACCOUNT(2, "已销账"),
    CANCEL_STATUS_DELETE(3, "已删除");

    public int type;
    public String name;
    IousCancelStatusEnum(int type, String name){
        this.type = type;
        this.name = name;
    }
}
