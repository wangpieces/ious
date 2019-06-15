package com.wangpiece.ious.controller.login;

import com.wangpiece.ious.bo.LoginUserBO;
import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.controller.BaseController;
import com.wangpiece.ious.dto.User;
import com.wangpiece.ious.service.IUserService;
import com.wangpiece.ious.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-18 21:14
 *
 */
@Controller
@RequestMapping("${ious.context-path}")
public class LoginController extends BaseController{

    public static final String BASEDIR = "pages/login";
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private IUserService userService;

    @Autowired
    private HttpServletRequest request;

    @Value("${ious.context-path}")
    private String iousContextPath;

    /**
     * 到登录页面
     * @return
     */
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false,value = "iousId") Integer iousId,
            @RequestParam(required = false,value = "fromtype") Integer fromtype,
            @RequestParam(required = false,value = "qrcode") String qrcode){
        if(iousId != null && fromtype!=null){
            //确定借条
            String sureIousUrl = "/business/detail?iousId="+iousId+"&fromtype="+fromtype+"&qrcode="+qrcode+"&from=index";
            model.addAttribute("sureIousUrl", sureIousUrl);
        }
        return BASEDIR + "/login";
    }

    /**
     * 用户登录
     * @return
     */
    @PostMapping("/index")
    public String index(Model model, LoginUserBO loginUserBO){

        String password = "";
        String phone = "";
        try {
            if(loginUserBO == null){
                LOGGER.info("登录信息为空");
                model.addAttribute("phone","");
                model.addAttribute("errorMsg","用户名或密码不能为空");
                return BASEDIR + "/login";
            }

            password = loginUserBO.getPassword();
            phone = loginUserBO.getPhone();
            if(StringUtils.isEmpty(password) || StringUtils.isEmpty(phone)){
                LOGGER.info("用户名或密码为空");
                model.addAttribute("phone", !StringUtils.isEmpty(password) ? phone : "");
                model.addAttribute("errorMsg","用户名或密码不能为空");
                return BASEDIR + "/login";
            }
            //base64解密
            byte[] tempPwdBytes = Base64Utils.decodeFromString(password);
            String tempPwd = new String(tempPwdBytes,"UTF-8");
            //md5加密
            String md5Pwd = DigestUtils.md5DigestAsHex(tempPwd.getBytes("UTF-8"));

            User user = new User();
            user.setPhone(phone);
            user.setPassword(md5Pwd);
            //判断用户密密码是否正确
            User userInfo = userService.getUserByPwd(user);
            if(userInfo == null){
                LOGGER.info("用户名或密码不对");
                model.addAttribute("phone", !StringUtils.isEmpty(password) ? phone : "");
                model.addAttribute("errorMsg","用户名或密码不对");
                return BASEDIR + "/login";
            }

            //将用户信息存入session
            UserBO userBO = new UserBO();
            BeanUtils.copyProperties(userInfo, userBO);
            userBO.setTradingPassword(null);
            Object tempuser = request.getSession().getAttribute("user");
            if(tempuser != null){
                request.getSession().removeAttribute("user");
                request.getSession().invalidate();
            }
            request.getSession().setAttribute("user", userBO);

            String token = JWTUtils.getToken(userInfo);
            request.getSession().setAttribute("token", token);

            String sureIousUrl = loginUserBO.getSureIousUrl();
            if(!StringUtils.isEmpty(sureIousUrl)){
                return "redirect:"+iousContextPath + sureIousUrl;
            }
        } catch (Exception e){
            LOGGER.error("内部出错", e);
            model.addAttribute("phone", !StringUtils.isEmpty(password) ? phone : "");
            model.addAttribute("errorMsg","用户名或密码不能为空");
            return BASEDIR + "/login";
        }

        return "redirect:"+iousContextPath + "/business/index";
    }

    /**
     * 用户退出
     * @return
     */
    @GetMapping("/logout")
    public String logout(Model model, LoginUserBO loginUserBO){
        HttpSession session = request.getSession();
        if(session != null){
            session.invalidate();
        }
        return BASEDIR + "/login";
    }

}
