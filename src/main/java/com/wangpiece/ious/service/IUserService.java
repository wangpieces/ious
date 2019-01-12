package com.wangpiece.ious.service;


import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.dto.User;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-15 23:33
 */
public interface IUserService {

    /**
     * 通过openid获取用户信息
     * @param openId openid
     * @return
     */
    User getUserByOpenId(String openId);

    /**
     * 通过电话号码获取用户信息
     * @param phone 电话号码
     * @return
     */
    User getUserByPhone(String phone);

    /**
     * 保存用户新
     * @param user 需要保存的用户信息
     */
    void save(User user);

    /**
     * 用户登录
     * @param user 保存用户名密码
     * @return
     */
    User getUserByPwd(User user);

    /**
     * 判断交易密码是否正确
     * @param userBO
     * @return
     */
    User getUserByTradingPwd(UserBO userBO);
}
