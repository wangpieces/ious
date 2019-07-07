package com.wangpiece.ious.service;

import com.wangpiece.ious.dto.SmsResult;
import org.apache.ibatis.annotations.Param;

public interface ISmsService {

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    String sendSms(String phone);

    /**
     * 保存验证码信息
     * @param smsResult
     */
    void save(SmsResult smsResult);

    /**
     * 获取验证码信息
     * @param phone 电话号码
     * @return
     */
    SmsResult getSmsInfoByPhone(@Param("phone") String phone);

    /**
     * 更新是否失效状态
     * @param id
     */
    void updateSmsInvalidFlag(Integer id);
}
