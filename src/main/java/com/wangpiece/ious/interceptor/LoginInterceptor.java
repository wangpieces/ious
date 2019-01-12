package com.wangpiece.ious.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author wang.xu
 * @desc 判断用户是否登录
 * @date 2018-12-16 20:09
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{

    @Value("${ious.context-path}")
    private String iousContextPath;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        System.out.println("判断是否登录拦截器" );
        StringBuffer requestURL = request.getRequestURL();
        System.out.println(requestURL.toString());
        Object user = request.getSession().getAttribute("user");
        if(user == null){

            //通过扫描二维码查看借条信息需要特殊处理
            if(requestURL != null && requestURL.toString().contains("/ious/business/detail")){
                String iousId = request.getParameter("iousId");
                String fromtype = request.getParameter("fromtype");
                String qrcode = request.getParameter("qrcode");
                response.sendRedirect(request.getContextPath()+iousContextPath+"/login?iousId="+iousId+"&fromtype="+fromtype+"&qrcode="+qrcode);
            }else{
                response.sendRedirect(request.getContextPath()+iousContextPath+"/login");
            }
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {

    }
}
