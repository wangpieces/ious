package com.wangpiece.ious.config;

import com.wangpiece.ious.interceptor.AuthenticationInterceptor;
import com.wangpiece.ious.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-15 16:56
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${ious.context-path}")
    private String iousContextPath;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;

    //映射静态资源路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(iousContextPath + "/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
          //如果有后台则需要这个过滤器
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/"+iousContextPath+"/login","/"+iousContextPath+"/index")
                .excludePathPatterns("/"+iousContextPath+"/register","/"+iousContextPath+"/forgetPassword")
                .excludePathPatterns("/"+iousContextPath+"/saveRegister","/"+iousContextPath+"/saveForgetPassword")
                .excludePathPatterns("/"+iousContextPath+"/api/business/sendSms","/"+iousContextPath+"/api/business/checkSmsCode")
                .excludePathPatterns("/"+iousContextPath+"/css/**","/"+iousContextPath+"/image/**","/"+iousContextPath+"/img/**","/"+iousContextPath+"/js/**","/"+iousContextPath+"/lib/**");

        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/"+iousContextPath+"/api/**")
                .excludePathPatterns("/api/wechat/getOpenId","/api/jwt/getToken","/admin/**");
    }


}
