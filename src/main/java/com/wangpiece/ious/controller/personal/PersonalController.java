package com.wangpiece.ious.controller.personal;

import com.wangpiece.ious.annotation.NeedToken;
import com.wangpiece.ious.common.CommonResult;
import com.wangpiece.ious.controller.BaseController;
import com.wangpiece.ious.service.IPersonalService;
import com.wangpiece.ious.vo.SavePwdVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

}
