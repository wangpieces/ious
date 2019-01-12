package com.wangpiece.ious.controller;

import com.wangpiece.ious.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @author wang.xu
 * @desc 后台接口预留
 * @date 2018-12-16 20:22
 */
@Controller
@RequestMapping("${ious.context-path}/admin")
public class AdminController extends BaseController{

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody User user){

        return null;
    }

}
