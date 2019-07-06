package com.wangpiece.ious.controller.ious;

import com.wangpiece.ious.bo.IousBO;
import com.wangpiece.ious.bo.PostponeBO;
import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.controller.BaseController;
import com.wangpiece.ious.dto.Code;
import com.wangpiece.ious.dto.Ious;
import com.wangpiece.ious.dto.Postpone;
import com.wangpiece.ious.service.ICodeService;
import com.wangpiece.ious.service.IIouService;
import com.wangpiece.ious.service.IPostponeService;
import com.wangpiece.ious.utils.CalculateUtil;
import com.wangpiece.ious.utils.DateUtils;
import com.wangpiece.ious.vo.GetIousListVO;
import com.wangpiece.ious.vo.IousVO;
import com.wangpiece.ious.vo.PostponeVO;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author wang.xu
 * @desc 核心业务类
 * @date 2018-12-18 20:45
 */
@Controller
@RequestMapping("${ious.context-path}/business")
public class BusinessController extends BaseController{

    public static final String BASEDIR = "pages/business";
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);
    @Autowired
    private IIouService iouService;

    @Autowired
    private ICodeService codeService;

    @Autowired
    private IPostponeService postponeService;
    /**
     * 到主页
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/index")
    public String gotoIndex() {
        return BASEDIR + "/index";
    }

    /**
     * 到我是借款人页面
     * @author wang.xu
     * @date 2018-12-18
     * @param model
     * @return
     */
    @GetMapping("/loan")
    public String gotoLoan(Model model) {
        String beginTime = DateUtils.getCurrentDate(DateUtils.PATTERN_SHORT);
        String endTime = DateUtils.getPlusDays(7);
        model.addAttribute("beginTime", beginTime);
        model.addAttribute("endTime", endTime);
        //借款用途
        List<Code> purposeList = codeService.getSelectValueByPid(1,null);
        //借款利率
        List<Code> rateList = codeService.getSelectValueByPid(6,null);
        model.addAttribute("purposeList", purposeList);
        model.addAttribute("rateList", rateList);
        return BASEDIR + "/loan";
    }

    /**
     * 到详细页面
     * @author wang.xu
     * @date 2018-12-18
     * @param model
     * @param iousId 借条id
     * @param fromtype 1-借款页面 2-出借页面
     * @param from 从哪里看的详细信息，index表示从主页进去的
     * @return
     */
    @GetMapping("/detail")
    public String gotoDetail( Model model, @RequestParam("iousId") Integer iousId,
                              @RequestParam("fromtype")Integer fromtype,
                              @RequestParam(required = false, value = "from") String from,
                              @RequestParam(required = false, value = "qrcode") String qrcode) {
        IousBO iousBO = iouService.getIousInfo(iousId);
        model.addAttribute("fromtype", fromtype);
        model.addAttribute("from", StringUtils.isEmpty(from) ? "" : from);
        if(iousBO == null){
            return BASEDIR + "/not_found_detail";
        }
        UserBO userInfo = getUserInfo();
        Integer userId = userInfo.getId();
        Integer type = iousBO.getType();
        //type=2 or type =5 借条已经确认且生效,则只有借款人双方可以看到
        //type==4借款人已经确认借条，则说明出借人和借款人已经确定
        if(type == 2 || type == 5 || type == 4){
            Integer loanUserId = iousBO.getLoanUserId();
            Integer lendUserId = iousBO.getLendUserId();
            if(!userId.equals(loanUserId) && !userId.equals(lendUserId)){
                return BASEDIR + "/not_found_detail";
            }

          /*  if(fromtype == 1){
                //查看该数据的为借款人，如果当前登录人id和当前借款人id相等则可以查看
                Integer loanUserId = iousBO.getLoanUserId();
                if(!userId.equals(loanUserId)){
                    return BASEDIR + "/not_found_detail";
                }
            }else if(fromtype == 2){
                //查看该数据的为出借人，如果当前登录人id和出借人id相等则可以查看
                Integer lendUserId = iousBO.getLendUserId();
                if(!userId.equals(lendUserId)){
                    return BASEDIR + "/not_found_detail";
                }
            }else{
                return BASEDIR + "/not_found_detail";
            }*/
        }else if(type == 1){
            //借款人发起的借条 如果是fromtype=1表示发起人查看，则需要当前登录人与借款人id相同才可以查看
            if(fromtype == 1 && !"qrcode".equals(qrcode)){
                //从列表看详情
                Integer loanUserId = iousBO.getLoanUserId();
                if(!userId.equals(loanUserId)){
                    return BASEDIR + "/not_found_detail";
                }
            }
        }else{//type==3
            //出借人发起的借条,如果是fromtype=2表示发起人查看，则需要当前登录人与出借人id相同才可以查看
            if(fromtype == 2 && !"qrcode".equals(qrcode)){
                //从列表看详情
                Integer lendUserId = iousBO.getLendUserId();
                if(!userId.equals(lendUserId)){
                    return BASEDIR + "/not_found_detail";
                }
            }
        }

        model.addAttribute("ious", iousBO);
        return BASEDIR + "/detail";
    }

    /**
     * 到我是出借人页面
     * @author wang.xu
     * @date 2018-12-18
     * @param model
     * @return
     */
    @GetMapping("/lend")
    public String gotoLend(Model model) {
        String beginTime = DateUtils.getCurrentDate(DateUtils.PATTERN_SHORT);
        String endTime = DateUtils.getPlusDays(7);

        model.addAttribute("beginTime", beginTime);
        model.addAttribute("endTime", endTime);
        //借款用途
        List<Code> purposeList = codeService.getSelectValueByPid(1, null);
        //借款利率
        List<Code> rateList = codeService.getSelectValueByPid(6,null);
        model.addAttribute("purposeList", purposeList);
        model.addAttribute("rateList", rateList);
        return BASEDIR + "/lend";
    }
    /**
     *  借条确认
     * @author wang.xu
     * @date 2018-12-18
     * @param model
     * @return
     */
    @GetMapping("/sureious")
    public String sureious(Model model, @RequestParam("iousId")Integer iousId, @RequestParam("fromtype")Integer fromtype) {
        System.out.println(iousId +"===="+fromtype);
        IousBO iousBO = iouService.getIousInfo(iousId);
        model.addAttribute("ious", iousBO);
        model.addAttribute("fromtype", fromtype);
        model.addAttribute("from", "index");
        return BASEDIR + "/detail";
    }

    /**
     *  待收金额列表
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/lendList")
    public String gotoLendList(Model model) {
        Map<String, String> iousMoney = iouService.getIousMoney(4, getUserInfo());
        model.addAttribute("iousMoney",iousMoney);
        return BASEDIR + "/lend_list";
    }

    /**
     *  待收金额详细信息
     * @author wang.xu
     * @param iousId id
     * @param type 1-借款 2-出借
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/lendListItem")
    public String gotoLendListItem(Model model,@RequestParam("iousId") Integer iousId,
                                   @RequestParam("type") Integer type) {
        IousVO iousInfo = iouService.getIousVOInfo(iousId,type);
        if(iousInfo  == null){
            return BASEDIR + "/not_found_detail";
        }
        UserBO userInfo = getUserInfo();
        Integer userId = userInfo.getId();
        Integer loanUserId = iousInfo.getLoanUserId();
        Integer lendUserId = iousInfo.getLendUserId();
        if(!userId.equals(loanUserId) && !userId.equals(lendUserId)){
            return BASEDIR + "/not_found_detail";
        }

        model.addAttribute("ious",iousInfo);
        return BASEDIR + "/lend_list_item";
    }


    /**
     * 查看出借借条详细页面
     * @author wang.xu
     * @param iousId id
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/lendItemDetail")
    public String gotoLendItemDetail(Model model,@RequestParam("iousId") Integer iousId) {
        IousBO iousInfo = iouService.getIousInfo(iousId);
        if(iousInfo  == null){
            return BASEDIR + "/not_found_detail";
        }
        UserBO userInfo = getUserInfo();
        Integer userId = userInfo.getId();
        Integer loanUserId = iousInfo.getLoanUserId();
        Integer lendUserId = iousInfo.getLendUserId();
        if(!userId.equals(loanUserId) && !userId.equals(lendUserId)){
            return BASEDIR + "/not_found_detail";
        }
        //展示最初的借款时间
//        Postpone postpone = postponeService.getByIousId(iousId);
//        if(postpone != null){
//            iousInfo.setReturnTime(postpone.getPostponeTime());
//        }

        model.addAttribute("ious",iousInfo);
        return BASEDIR + "/lend_list_item_detail";
    }

    /**
     *  待还金额列表
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/loanList")
    public String gotoLoanList(Model model) {
        Map<String, String> iousMoney = iouService.getIousMoney(1, getUserInfo());
        model.addAttribute("iousMoney",iousMoney);
        return BASEDIR + "/loan_list";
    }

    /**
     *  待还金额详细信息
     * @author wang.xu
     * @param iousId id
     * @param type 1-借款 2-出借
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/loanListItem")
    public String gotoLoanListItem(Model model,@RequestParam("iousId") Integer iousId,
                                   @RequestParam("type") Integer type) {
        IousVO iousInfo = iouService.getIousVOInfo(iousId,type);
        if(iousInfo  == null){
            return BASEDIR + "/not_found_detail";
        }
        UserBO userInfo = getUserInfo();
        Integer userId = userInfo.getId();
        Integer loanUserId = iousInfo.getLoanUserId();
        Integer lendUserId = iousInfo.getLendUserId();
        if(!userId.equals(loanUserId) && !userId.equals(lendUserId)){
            return BASEDIR + "/not_found_detail";
        }
        model.addAttribute("ious",iousInfo);

        //获取展期历史
        PostponeVO postponeVO = new PostponeVO();
        postponeVO.setIousId(iousId);
        Map<Object, Object> resultMap = postponeService.getPostpone(postponeVO);
        List<PostponeBO> postponeBOList = (List<PostponeBO>)resultMap.get("resultList");
        model.addAttribute("postponeList", postponeBOList);
        PostponeBO postponeBO = (PostponeBO)resultMap.get("postpone");
        model.addAttribute("postpone", postponeBO);

        return BASEDIR + "/loan_list_item";
    }

    /**
     * 查看借款借条详细页面
     * @author wang.xu
     * @param iousId id
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/loanItemDetail")
    public String gotoLoanItemDetail(Model model,@RequestParam("iousId") Integer iousId) {
        IousBO iousInfo = iouService.getIousInfo(iousId);
        if(iousInfo  == null){
            return BASEDIR + "/not_found_detail";
        }
        UserBO userInfo = getUserInfo();
        Integer userId = userInfo.getId();
        Integer loanUserId = iousInfo.getLoanUserId();
        Integer lendUserId = iousInfo.getLendUserId();
        if(!userId.equals(loanUserId) && !userId.equals(lendUserId)){
            return BASEDIR + "/not_found_detail";
        }
        model.addAttribute("ious",iousInfo);
        return BASEDIR + "/loan_list_item_detail";
    }

    /**
     * 查看借款借条详细页面
     * @author wang.xu
     * @param iousId id
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/postpone")
    public String gotoPostpone(Model model,@RequestParam("iousId") Integer iousId) {
        IousBO iousInfo = iouService.getIousInfo(iousId);
        if(iousInfo  == null){
            return BASEDIR + "/not_found_detail";
        }
        UserBO userInfo = getUserInfo();
        Integer userId = userInfo.getId();
//        Integer loanUserId = iousInfo.getLoanUserId();
        Integer lendUserId = iousInfo.getLendUserId();
        if(!userId.equals(lendUserId)){
            return BASEDIR + "/not_found_detail";
        }
        model.addAttribute("ious",iousInfo);

        String dateTime = DateUtils.getCurrentDate("YYYY-MM-dd");
        model.addAttribute("dateTime", dateTime);

        List<Code> rateList = codeService.getSelectValueByPid(6,null);
        model.addAttribute("rateList", rateList);

        //获取展期历史
        PostponeVO postponeVO = new PostponeVO();
        postponeVO.setIousId(iousId);
        Map<Object, Object> resultMap = postponeService.getPostpone(postponeVO);
        List<PostponeBO> postponeBOList = (List<PostponeBO>)resultMap.get("resultList");
        model.addAttribute("postponeList", postponeBOList);
        PostponeBO postponeBO = (PostponeBO)resultMap.get("postpone");
        model.addAttribute("postpone", postponeBO);

        return BASEDIR + "/postpone";
    }

    /**
     * 到确认展期信息
     * @author wang.xu
     * @param iousId id
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/surepostpone")
    public String gotoSurePostpone(Model model,@RequestParam("iousId") Integer iousId) {
        IousBO iousInfo = iouService.getIousInfo(iousId);
        if(iousInfo  == null){
            return BASEDIR + "/not_found_detail";
        }
        UserBO userInfo = getUserInfo();
        Integer userId = userInfo.getId();
        Integer loanUserId = iousInfo.getLoanUserId();
//        Integer lendUserId = iousInfo.getLendUserId();
        if(!userId.equals(loanUserId)){
            return BASEDIR + "/not_found_detail";
        }
        model.addAttribute("ious",iousInfo);

        //获取展期历史
        PostponeVO postponeVO = new PostponeVO();
        postponeVO.setIousId(iousId);
        Map<Object, Object> resultMap = postponeService.getPostpone(postponeVO);
        PostponeBO postponeBO = (PostponeBO)resultMap.get("postpone");
        if(postponeBO == null){
            return BASEDIR + "/not_found_detail";
        }
        model.addAttribute("postpone", postponeBO);

        return BASEDIR + "/surepostpone";
    }
}
