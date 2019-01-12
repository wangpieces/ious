package com.wangpiece.ious.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-31 13:00
 */
@Controller
@RequestMapping("${ious.context-path}")
public class ErrorController{

    public static final String BASEDIR = "pages/error";

    @GetMapping("/error")
    public String errorPage404() {
        return BASEDIR +"/error";
    }
}
