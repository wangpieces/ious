package com.wangpiece.ious.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-16 23:52
 */
@Controller
@RequestMapping("${ious.context-path}/test")
public class TestController extends BaseController{

    @GetMapping("/gotoPage")
    public String gotoPage() {
        return "test";
    }
}
