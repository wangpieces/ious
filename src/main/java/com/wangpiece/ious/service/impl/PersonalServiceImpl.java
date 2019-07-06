package com.wangpiece.ious.service.impl;

import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.dto.User;
import com.wangpiece.ious.service.IPersonalService;
import com.wangpiece.ious.service.IUserService;
import com.wangpiece.ious.utils.DateUtils;
import com.wangpiece.ious.vo.RegisterInfoVO;
import com.wangpiece.ious.vo.SavePwdVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:14
 */
@Service
public class PersonalServiceImpl implements IPersonalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalServiceImpl.class);

    @Autowired
    private IUserService userService;

    @Override
    @Transactional
    public void savePwd(UserBO userBO, SavePwdVO savePwdVO) {

        User user = userService.getUserByPhone(userBO.getPhone());
        if(user == null){
            throw new RuntimeException("获取不到用户信息，清刷新重试");
        }
        Integer flag = savePwdVO.getFlag();

        String oldPwd = savePwdVO.getOldPwd();

        String md5Pwd = "";
        try {
            //base64解密
            byte[] tempPwdBytes = Base64Utils.decodeFromString(oldPwd);
            String tempPwd = new String(tempPwdBytes,"UTF-8");
            //md5加密
             md5Pwd = DigestUtils.md5DigestAsHex(tempPwd.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
        }

        String newPwd = savePwdVO.getNewPwd();
        if(flag == 1){
            String password = user.getPassword();
            if(!md5Pwd.equals(password)){
                throw new RuntimeException("原登录密码不对");
            }
            user.setPassword(newPwd);
            userService.save(user);
        }else{
            String tradingPassword = user.getTradingPassword();
            if(!md5Pwd.equals(tradingPassword)){
                throw new RuntimeException("原交易密码不对");
            }
            user.setTradingPassword(newPwd);
            userService.save(user);
        }

    }

    @Override
    @Transactional
    public Boolean saveRegister(RegisterInfoVO registerInfoVO) throws Exception {

        User user = new User();
        String currentTime = DateUtils.getCurrentDate();
        BeanUtils.copyProperties(registerInfoVO, user);
        user.setUpdateTime(currentTime);
        user.setCreateTime(currentTime);
        userService.registerUser(user);
        return true;
    }
}
