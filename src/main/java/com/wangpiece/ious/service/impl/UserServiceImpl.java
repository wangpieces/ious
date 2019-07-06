package com.wangpiece.ious.service.impl;

import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.dto.User;
import com.wangpiece.ious.mapper.UserMapper;
import com.wangpiece.ious.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-15 23:33
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通过openid获取用户信息
     * @param openId openid
     * @return
     */
    @Override
    public User getUserByOpenId(String openId) {
        return userMapper.getUserByOpenId(openId);
    }

    /**
     * 通过电话号码获取用户信息
     *
     * @param phone 电话号码
     * @return
     */
    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    /**
     * 保存用户新
     * @param user 需要保存的用户信息
     */
    @Override
    @Transactional
    public void save(User user) {
        userMapper.save(user);
    }


    /**
     * 用户登录
     * @param user 保存用户名密码
     * @return
     */
    @Override
    public User getUserByPwd(User user) {
        return userMapper.getUserByPwd(user);
    }

    /**
     * 判断交易密码是否正确
     *
     * @param userBO
     * @return
     */
    @Override
    public User getUserByTradingPwd(UserBO userBO) {
        return userMapper.getUserByTradingPwd(userBO);
    }

    /**
     * 注册用户
     *
     * @param user
     */
    @Override
    public void registerUser(User user) {
        userMapper.registerUser(user);
    }
}
