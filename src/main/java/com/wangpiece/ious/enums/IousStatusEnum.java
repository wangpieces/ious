package com.wangpiece.ious.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-25 19:37
 */
public enum IousStatusEnum {

    STATUS_UNPAY(1, "未支付"),
    STATUS_PAY_NOSURE(2, "已支付未确认"),
    STATUS_PAY_SURE(3, "支付已确认");

    public int type;
    public String name;
    IousStatusEnum(int type, String name){
        this.type = type;
        this.name = name;
    }

    public static Map<Integer, String> IOUS_STATUS_ENUM_MAP = new HashMap<>();
    static {
        IOUS_STATUS_ENUM_MAP.put(STATUS_UNPAY.type, STATUS_UNPAY.name);
        IOUS_STATUS_ENUM_MAP.put(STATUS_PAY_NOSURE.type, STATUS_PAY_NOSURE.name);
        IOUS_STATUS_ENUM_MAP.put(STATUS_PAY_SURE.type, STATUS_PAY_SURE.name);
    }
}
