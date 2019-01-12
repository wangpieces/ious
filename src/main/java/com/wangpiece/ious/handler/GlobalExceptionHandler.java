package com.wangpiece.ious.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wang.xu
 * @desc 全局异常处理
 * @date 2018-12-31 12:21
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @Value("${ious.context-path}")
    private String iousContextPath;

    @ExceptionHandler(Exception.class)
    public ModelAndView globalExceptionHandler(HttpServletRequest request, Exception e){

        ModelAndView modelAndView = new ModelAndView();
        LOGGER.info("全局异常处理，请求地址", request.getRequestURL());
        LOGGER.info("全局异常处理，"+e.getMessage(), e);
//        return iousContextPath+"/pages/error/error";
        modelAndView.setViewName("/pages/error/error");
        return modelAndView;
    }
}
