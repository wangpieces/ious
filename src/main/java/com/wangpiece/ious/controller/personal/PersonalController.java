package com.wangpiece.ious.controller.personal;

import com.wangpiece.ious.annotation.NeedToken;
import com.wangpiece.ious.common.CommonResult;
import com.wangpiece.ious.controller.BaseController;
import com.wangpiece.ious.dto.SmsResult;
import com.wangpiece.ious.dto.User;
import com.wangpiece.ious.service.IPersonalService;
import com.wangpiece.ious.service.ISmsService;
import com.wangpiece.ious.service.IUserService;
import com.wangpiece.ious.vo.RegisterInfoVO;
import com.wangpiece.ious.vo.SavePwdVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-18 21:14
 *
 */
@Controller
@RequestMapping("${ious.context-path}")
public class PersonalController extends BaseController{

    public static final String BASEDIR = "pages/personal";
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonalController.class);

    @Autowired
    private IPersonalService personalService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISmsService smsService;

    /**
     * 到个人中心
     * @return
     */
    @GetMapping("/personal")
    public String login(Model model){
        return BASEDIR + "/personal";
    }

    /**
     * 到联系我们
     * @return
     */
    @GetMapping("/contactUs")
    public String contactUs(Model model){
        return BASEDIR + "/contact_us";
    }

    /**
     * 用户注册
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return BASEDIR + "/register";
    }

    /**
     * 用户注册
     * @return
     */
    @GetMapping("/forgetPassword")
    public String forgetPassword(){
        return BASEDIR + "/forget_password";
    }

    /**
     * 到修改登录密码页面
     * @param  flag =1 登录密码 flag = 2交易密码
     * @return
     */
    @GetMapping("/modifyPassword/{flag}")
    public String modifyPassword(Model model, @PathVariable("flag") Integer flag){
        if(flag == 1){
            model.addAttribute("name","登录");
            model.addAttribute("placeholder","6-20位字母、数字、特殊符号组合");
        }else{
            model.addAttribute("name","交易");
            model.addAttribute("placeholder","输入6位数字");
        }
        model.addAttribute("flag",flag);
        return BASEDIR + "/modify_password";
    }

    /**
     * 保存密码
     * @param savePwdVO
     * @return
     */
    @PostMapping("/api/savePassword")
    @ResponseBody
    @NeedToken
    public CommonResult<Boolean> savePassword(SavePwdVO savePwdVO){

        CommonResult<Boolean> result = new CommonResult<>();
        try{
            personalService.savePwd(getUserInfo(), savePwdVO);
            result.setSuccessful(true);
            result.setMessage("修改成功");
        }catch (Exception e){
            result.setSuccessful(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
     * 用户注册
     * @param registerInfoVO
     * @return
     */
    @PostMapping("/saveRegister")
    public String saveRegister(Model model,RegisterInfoVO registerInfoVO){

        String phone = registerInfoVO.getPhone();
        String code = registerInfoVO.getCode();
        String name = registerInfoVO.getName();
        String password = registerInfoVO.getPassword();
        String surePassword = registerInfoVO.getSurePassword();
        String tradingPassword = registerInfoVO.getTradingPassword();
        String sureTradingPassword = registerInfoVO.getSureTradingPassword();

        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code) || StringUtils.isEmpty(name)
                || StringUtils.isEmpty(password) || StringUtils.isEmpty(surePassword)
                || StringUtils.isEmpty(tradingPassword) || StringUtils.isEmpty(sureTradingPassword)){
            LOGGER.info("信息不完整", registerInfoVO);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","信息不完整，请将信息填写完整");
            return BASEDIR + "/register";
        }

        if(!password.equals(surePassword)){
            LOGGER.info("确认登录密码与登录密码不一致", registerInfoVO);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","确认登录密码与登录密码不一致");
            return BASEDIR + "/register";
        }

        if(!tradingPassword.equals(sureTradingPassword)){
            LOGGER.info("确认交易密码与交易密码不一致", registerInfoVO);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","确认交易密码与交易密码不一致");
            return BASEDIR + "/register";
        }

        User user = userService.getUserByPhone(phone);
        if(user != null){
            LOGGER.info("用户已经存在，不能重复注册", registerInfoVO);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","该手机号已经注册过，不能重复注册");
            return BASEDIR + "/register";
        }
        //@TODO校验验证码对不对
        SmsResult smsResult = smsService.getSmsInfoByPhone(phone);
        if(smsResult == null){
            LOGGER.info("验证码错或已经过期,请重试", smsResult);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","验证码错或已经过期,请重试");
            return BASEDIR + "/register";
        }else{
            String tempCode = smsResult.getCode();
            if(!code.equals(tempCode)){
                LOGGER.info("验证码错或已经过期,请重试", smsResult);
                model.addAttribute("errorMsg","验证码错或已经过期,请重试");
                model.addAttribute("registerInfo", registerInfoVO);
                return BASEDIR + "/register";
            }
        }

        try{
            //md5加密，登录密码
            byte[] tempPwdBytes = Base64Utils.decodeFromString(password);
            String tempPwd = new String(tempPwdBytes,"UTF-8");
            String md5Pwd = DigestUtils.md5DigestAsHex(tempPwd.getBytes("UTF-8"));

            //md5加密,交易密码
            byte[] tempTradingPwdBytes = Base64Utils.decodeFromString(tradingPassword);
            String tempTradingPwd = new String(tempTradingPwdBytes,"UTF-8");
            String md5TradingPwd = DigestUtils.md5DigestAsHex(tempTradingPwd.getBytes("UTF-8"));

            registerInfoVO.setPassword(md5Pwd);
            registerInfoVO.setTradingPassword(md5TradingPwd);
            personalService.saveRegister(registerInfoVO);
            smsService.updateSmsInvalidFlag(smsResult.getId());
        }catch (Exception e){
            LOGGER.error("内部出错", e);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","网络出错，请刷新重试");
            return BASEDIR + "/register";
        }
        return "pages/login/login";
    }

    /**
     * 忘记密码
     * @param registerInfoVO
     * @return
     */
    @PostMapping("/saveForgetPassword")
    public String saveForgetPassword(Model model,RegisterInfoVO registerInfoVO){

        String code = registerInfoVO.getCode();
        String phone = registerInfoVO.getPhone();
        String password = registerInfoVO.getPassword();
        String surePassword = registerInfoVO.getSurePassword();

        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)
                || StringUtils.isEmpty(password) || StringUtils.isEmpty(surePassword)){
            LOGGER.info("信息不完整", registerInfoVO);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","信息不完整，请将信息填写完整");
            return BASEDIR + "/forget_password";
        }

        if(!password.equals(surePassword)){
            LOGGER.info("确认新密码与新密码不一致", registerInfoVO);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","确认新密码与新密码不一致");
            return BASEDIR + "/forget_password";
        }


        User user = userService.getUserByPhone(phone);
        if(user == null){
            LOGGER.info("用户不存在存在", registerInfoVO);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","该手机号未注册，请重试");
            return BASEDIR + "/forget_password";
        }
        //@TODO校验验证码对不对
        SmsResult smsResult = smsService.getSmsInfoByPhone(phone);
        if(smsResult == null){
            LOGGER.info("验证码错或已经过期,请重试", smsResult);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","验证码错或已经过期,请重试");
            return BASEDIR + "/register";
        }else{
            String tempCode = smsResult.getCode();
            if(!code.equals(tempCode)){
                LOGGER.info("验证码错或已经过期,请重试", smsResult);
                model.addAttribute("registerInfo", registerInfoVO);
                model.addAttribute("errorMsg","验证码错或已经过期,请重试");
                return BASEDIR + "/register";
            }
        }

        try{
            //md5加密，登录密码
            byte[] tempPwdBytes = Base64Utils.decodeFromString(password);
            String tempPwd = new String(tempPwdBytes,"UTF-8");
            String md5Pwd = DigestUtils.md5DigestAsHex(tempPwd.getBytes("UTF-8"));
            registerInfoVO.setPassword(md5Pwd);
            personalService.saveForgetPassword(registerInfoVO);
            smsService.updateSmsInvalidFlag(smsResult.getId());
        }catch (Exception e){
            LOGGER.error("内部出错", e);
            model.addAttribute("registerInfo", registerInfoVO);
            model.addAttribute("errorMsg","网络出错，请刷新重试");
            return BASEDIR + "/forget_password";
        }
        return "pages/login/login";
    }

}
