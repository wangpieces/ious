package com.wangpiece.ious.controller.wechat;

import com.wangpiece.ious.bo.LoginUserBO;
import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.config.WechatConfigs;
import com.wangpiece.ious.controller.BaseController;
import com.wangpiece.ious.dto.User;
import com.wangpiece.ious.service.IUserService;
import com.wangpiece.ious.utils.DateUtils;
import com.wangpiece.ious.utils.HttpRequestUtils;
import com.wangpiece.ious.utils.JWTUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-18 21:14
 *
 */
@Controller
@RequestMapping("${ious.context-path}/wechat")
public class WechatController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatController.class);
    @Autowired
    private IUserService userService;

    @Value("${ious.context-path}")
    private String iousContextPath;

    /**
     * 用户登录获取code
     * @param response
     */
    @GetMapping("/wechatLogin")
    public void wechatLogin(HttpServletResponse response) {

        String url = String.format(WechatConfigs.GET_AUTH_CODE_URL,WechatConfigs.APPID,WechatConfigs.REDIRECT_URL);
        LOGGER.info("url="+url);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            LOGGER.error("微信重定向失败", e);
            e.printStackTrace();
        }
    }

    /**
     * 获取openid
     * @param code
     * @param request
     */
    @GetMapping("/auth")
    public String auth(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) {

        LOGGER.info("微信用户获取的code="+code);
        HttpSession session = request.getSession();
        Object openId = session.getAttribute("openId");
        if(ObjectUtils.isEmpty(openId)){
            String url = String.format(WechatConfigs.AUTH_ACCESS_TOKEN_URL,WechatConfigs.APPID,WechatConfigs.APP_SECRET,code);
            JSONObject jsonObject = HttpRequestUtils.httpGet(url);
            LOGGER.info("jsonObject="+jsonObject);
            openId = jsonObject.get("openid");
            if(!ObjectUtils.isEmpty(openId)) {
                session.setAttribute("openId", openId);
                //判断用户是否存在，不存在则新建用户
                User userInfo = userService.getUserByOpenId(openId + "");
                if (userInfo == null) {
                    String currentDate = DateUtils.getCurrentDate();
                    User user = new User();
                    user.setOpenId(openId + "");
                    user.setCreateTime(currentDate);
                    user.setUpdateTime(currentDate);
                    userService.save(user);
                }
            }else {
                //浏览器测试时用 @TODO
                openId = "o2nI91BuknAUY4Z_kBvZs-DzpMg4";
                session.setAttribute("openId", openId);
                LOGGER.info("获取openid为空!");
            }
        }

        Object token = session.getAttribute("token");
        if(ObjectUtils.isEmpty(token)){
            User user = new User();
            user.setOpenId(openId + "");
            token = JWTUtils.getToken(user);
            session.setAttribute("token", token);
        }

        //@TODO
        UserBO userBO = (UserBO)session.getAttribute("user");
        if(ObjectUtils.isEmpty(userBO)) {
            User userInfo = userService.getUserByOpenId(openId + "");
            userBO = new UserBO();
            BeanUtils.copyProperties(userInfo, userBO);
            session.setAttribute("user", userBO);
        }

        return "redirect:/"+iousContextPath+"/business/index";
    }

    @PostMapping("/login")
    public String login(LoginUserBO loginUserBO){


        return null;
    }

}
