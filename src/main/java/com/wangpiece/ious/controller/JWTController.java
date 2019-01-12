package com.wangpiece.ious.controller;

import com.wangpiece.ious.dto.User;
import com.wangpiece.ious.utils.JWTUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wang.xu
 * @desc 用户获取token
 * @date 2018-12-15 17:29
 */
@Controller
@RequestMapping("${ious.context-path}/api/jwt")
public class JWTController extends BaseController{

    @GetMapping("/getToken")
    @ResponseBody
    public String getToken(@RequestParam("openId") String openId) {
        User user = new User();
        user.setUserName(openId);
        return JWTUtils.getToken(user);
    }
}
