package com.wangpiece.ious.mapper;


import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.dto.SmsResult;
import com.wangpiece.ious.dto.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-15 23:26
 */
public interface SmsMapper {

    /**
     * 保存验证码信息
     * @param smsResult
     */
    Integer save(SmsResult smsResult);

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
