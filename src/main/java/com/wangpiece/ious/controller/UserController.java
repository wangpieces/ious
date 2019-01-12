package com.wangpiece.ious.controller;

import com.wangpiece.ious.dto.User;
import com.wangpiece.ious.service.IUserService;
import com.wangpiece.ious.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author wang.xu
 * @desc
 * @date 2018-12-15 23:37
 */
@Controller
@RequestMapping("${ious.context-path}/api/user")
public class UserController extends BaseController{

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    /**
     * 根据openid获取用户信息
     * @author wang.xu
     * @date 2018-12-14
     * @param openId
     * @return
     */
    @GetMapping("/getUserByOpenId")
    @ResponseBody
    public UserVO getUserByOpenId(@RequestParam("openId") String openId) {
        LOGGER.info("获取用户openId入参:" + openId);
        User user = userService.getUserByOpenId(openId);
        UserVO userVO = new UserVO();
        if(user != null){
            BeanUtils.copyProperties(user, userVO);
        }
        return userVO;
    }
}
