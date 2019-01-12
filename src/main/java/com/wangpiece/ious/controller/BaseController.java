package com.wangpiece.ious.controller;

import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.dto.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-18 20:09
 */
public abstract class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取用户信息
     * @return
     */
    public UserBO getUserInfo(){
        UserBO userBO = (UserBO)request.getSession().getAttribute("user");
        return  userBO;
    }
}
