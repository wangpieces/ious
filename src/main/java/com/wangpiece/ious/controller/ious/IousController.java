package com.wangpiece.ious.controller.ious;

import com.wangpiece.ious.annotation.NeedToken;
import com.wangpiece.ious.bo.IousBO;
import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.common.CommonResult;
import com.wangpiece.ious.controller.BaseController;
import com.wangpiece.ious.dto.Code;
import com.wangpiece.ious.dto.Ious;
import com.wangpiece.ious.dto.Postpone;
import com.wangpiece.ious.dto.User;
import com.wangpiece.ious.enums.IousCancelStatusEnum;
import com.wangpiece.ious.enums.IousStatusEnum;
import com.wangpiece.ious.service.ICodeService;
import com.wangpiece.ious.service.IIouService;
import com.wangpiece.ious.service.IPostponeService;
import com.wangpiece.ious.service.IUserService;
import com.wangpiece.ious.utils.CalculateUtil;
import com.wangpiece.ious.utils.DateUtils;
import com.wangpiece.ious.vo.GetIousListVO;
import com.wangpiece.ious.vo.IousVO;
import com.wangpiece.ious.vo.PostponeVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author wang.xu
 * @desc 核心业务类
 * @date 2018-12-18 20:45
 */
@Controller
@RequestMapping("${ious.context-path}/api/business")
public class IousController extends BaseController{

    public static final String BASEDIR = "pages/business";

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);

    @Autowired
    private IIouService iouService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICodeService codeService;
    @Autowired
    private IPostponeService postponeService;
    /**
     * 获取待还金额和代收金额
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/getMoneys")
    @ResponseBody
    @NeedToken
    public CommonResult<Map<String, String>> getMoneys() {
        CommonResult<Map<String, String>> result = new CommonResult<>();
        try {
            Map<String, String> resultMap = new HashMap<>();
            UserBO userInfo = getUserInfo();
            Integer userId = userInfo.getId();

            IousBO iousBO = new IousBO();
            iousBO.setLendUserId(userId);
            iousBO.setStatus(null);
            List<Ious> lendIousList = iouService.getIousByLendIousInfo(iousBO);
            if(CollectionUtils.isEmpty(lendIousList)){
                resultMap.put("lendMoney", "0.00");//出借的钱
            }else{
                int lendMoney = 0;
                for(Ious ious :  lendIousList){
                    Integer money = ious.getMoney();
                    if(money != null){
                        lendMoney += money;
                    }
                }
                resultMap.put("lendMoney", CalculateUtil.divide(lendMoney,100,2).stripTrailingZeros().toPlainString());//出借的钱
            }

            iousBO = new IousBO();
            iousBO.setLoanUserId(userId);
            iousBO.setStatus(null);
            List<Ious> loanIousList = iouService.getIousByLoanIousInfo(iousBO);
            if(CollectionUtils.isEmpty(loanIousList)){
                resultMap.put("loanMoney", "0.00");//借的钱
            }else{
                int loanMoney = 0;
                for(Ious ious :  loanIousList){
                    Integer money = ious.getMoney();
                    if(money != null){
                        loanMoney += money;
                    }
                }
                resultMap.put("loanMoney", CalculateUtil.divide(loanMoney,100,2).stripTrailingZeros().toPlainString());//借的钱
            }
            result.setData(resultMap);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("获取待还金额和代收金额", e);
            result.setSuccessful(false);
            result.setMessage("获取待还金额和代收金额失败");
        }
        return result;
    }

    /**
     * 获取出借金额
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/lendMoneyList")
    @ResponseBody
    @NeedToken
    public CommonResult<List<IousVO>> lendMoneyList() {
        CommonResult<List<IousVO>> result = new CommonResult<>();
        try{
            List<IousVO> resultList = new ArrayList<>();
            UserBO userInfo = getUserInfo();
            Integer userId = userInfo.getId();

            IousBO iousBO = new IousBO();
            iousBO.setLendUserId(userId);
            iousBO.setStatus(IousStatusEnum.STATUS_PAY_SURE.type);
            List<Ious> lendIousList = iouService.getIousByLendIousInfo(iousBO);
            if(!CollectionUtils.isEmpty(lendIousList)){
                for(Ious ious : lendIousList){
                    IousVO iousVO = new IousVO();
                    iousVO.setId(ious.getId());
                    iousVO.setName(ious.getLoaner());
                    iousVO.setMoney(CalculateUtil.divide(ious.getMoney(), 100,2).doubleValue());
                    iousVO.setRate(ious.getRate() + "");
                    iousVO.setStatusStr(IousStatusEnum.IOUS_STATUS_ENUM_MAP.get(ious.getStatus()));
                    String returnTime = ious.getReturnTime();
                    String loanTime = ious.getLoanTime();
                    iousVO.setTimeLength(DateUtils.getDays(loanTime, returnTime)+"");
                    iousVO.setRemainTime(DateUtils.getDays(returnTime));
                    resultList.add(iousVO);
                }
            }
            result.setData(resultList);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("获取出借金额", e);
            result.setSuccessful(false);
            result.setMessage("获取出借金额失败");
        }
        return result;
    }

    /**
     * 获取借款金额
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/loanMoneyList")
    @ResponseBody
    @NeedToken
    public  CommonResult<List<IousVO>> loanMoneyList() {
        CommonResult<List<IousVO>> result = new CommonResult<>();
        try{
            List<IousVO> resultList = new ArrayList<>();
            UserBO userInfo = getUserInfo();
            Integer userId = userInfo.getId();

            IousBO iousBO = new IousBO();
            iousBO.setLoanUserId(userId);
            iousBO.setStatus(IousStatusEnum.STATUS_PAY_SURE.type);
            List<Ious> loadIousList = iouService.getIousByLoanIousInfo(iousBO);
            if(!CollectionUtils.isEmpty(loadIousList)){
                for(Ious ious : loadIousList){
                    IousVO iousVO = new IousVO();
                    iousVO.setId(ious.getId());
                    iousVO.setName(ious.getLender());
                    iousVO.setMoney(CalculateUtil.divide(ious.getMoney(), 100,2).doubleValue());
                    iousVO.setRate(ious.getRate() + "");
                    iousVO.setStatusStr(IousStatusEnum.IOUS_STATUS_ENUM_MAP.get(ious.getStatus()));
                    String returnTime = ious.getReturnTime();
                    String loanTime = ious.getLoanTime();
                    iousVO.setTimeLength(DateUtils.getDays(loanTime, returnTime)+"");
                    iousVO.setRemainTime(DateUtils.getDays(returnTime));
                    resultList.add(iousVO);
                }
            }
            result.setData(resultList);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("获取借款金额", e);
            result.setSuccessful(false);
            result.setMessage("获取借款金额失败");
        }
        return result;
    }

    /**
     * 判断交易密码是否正确
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/checkTradingPassword")
    @ResponseBody
    @NeedToken
    public CommonResult<Boolean> checkTradingPassword(@RequestParam("tradingPassword") String tradingPassword) {
        //@TODO 前台传过来的密码需要加解密
        CommonResult<Boolean> result = new CommonResult<>();
        try{
            if(StringUtils.isEmpty(tradingPassword)){
                LOGGER.info("交易密码为空");
                result.setSuccessful(false);
                result.setMessage("交易密码为空");
                return result;
            }
            //base64解密
            byte[] tempPwdBytes = Base64Utils.decodeFromString(tradingPassword);
            String tempPwd = new String(tempPwdBytes,"UTF-8");
            //md5加密
            String md5Pwd = DigestUtils.md5DigestAsHex(tempPwd.getBytes("UTF-8"));

            UserBO userInfo = getUserInfo();
            UserBO userBO = new UserBO();
            userBO.setId(userInfo.getId());
            userBO.setTradingPassword(md5Pwd);
            User tempUser = userService.getUserByTradingPwd(userBO);
            if(tempUser == null){
                result.setSuccessful(false);
                result.setMessage("交易密码不对，请重试");
                return result;
            }
            result.setSuccessful(true);
            return result;
        }catch (Exception e){
            LOGGER.error("判断交易密码是否正确", e);
            result.setSuccessful(false);
            result.setMessage("操作失败，请刷新页面重试");
            return result;
        }
    }

    /**
     * 保存借条信息
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @PostMapping("/saveIousData")
    @ResponseBody
    public CommonResult<Integer> saveIousData(IousBO iousBO) {

        CommonResult<Integer> result = new CommonResult<>();
        try{
            UserBO userBO = getUserInfo();
            Integer id = iouService.save(iousBO,userBO);
            result.setSuccessful(true);
            result.setData(id);
        }catch (Exception e){
            LOGGER.error("保存借条信息", e);
            result.setSuccessful(false);
            result.setMessage("保存借条信息失败");
        }
        return result;
    }

    /**
     * 删除借条信息,将cancelStatus设置为3
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    @NeedToken
    public CommonResult<Boolean> delete(@RequestParam("id") Integer id) {
        CommonResult<Boolean> result = new CommonResult<>();
        try{
            IousBO iousBO = new IousBO();
            iousBO.setId(id);
            iousBO.setCancelStatus(IousCancelStatusEnum.CANCEL_STATUS_DELETE.type);
            iouService.updateCancelStatus(iousBO);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("删除借条信息", e);
            result.setSuccessful(false);
            result.setMessage("删除借条信息失败");
        }
        return result;
    }
    /**
     * 修改借条的支付状态
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/updateStatus")
    @ResponseBody
    @NeedToken
    public CommonResult<Boolean> updateStatus(@RequestParam("id") Integer id,@RequestParam("type") Integer type) {
        CommonResult<Boolean> result = new CommonResult<>();
        try{
            UserBO userInfo = getUserInfo();
            IousBO iousBO = new IousBO();
            iousBO.setId(id);
            iousBO.setLendUserId(userInfo.getId());
            iousBO.setStatus(IousStatusEnum.STATUS_PAY_SURE.type);
            iousBO.setType(type);
            iousBO.setUpdateTime(DateUtils.getCurrentDate());
            iouService.updateStatus(iousBO);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("修改借条的支付状态", e);
            result.setSuccessful(false);
            result.setMessage("修改借条的支付状态失败");
        }
        return result;
    }
    /**
     * 更改用户借条信息，将该借条与该用户绑定
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/updateLoanUserId")
    @ResponseBody
    @NeedToken
    public CommonResult<Boolean> updateLoanUserId(@RequestParam("id") Integer id) {
        CommonResult<Boolean> result = new CommonResult<>();
        try{
            UserBO userInfo = getUserInfo();

            IousBO iousBO = iouService.getIousInfo(id);
            if(iousBO == null){
                LOGGER.info("获取借条信息失败");
                result.setSuccessful(false);
                result.setMessage("操作失败，请刷新页面后重试");
                return result;
            }

            Integer loanUserId = iousBO.getLoanUserId();
            if(loanUserId != null && loanUserId != 0 && loanUserId != userInfo.getId()){
                LOGGER.info("该借条已经被其他用户确认，请与对方确认后重试");
                result.setSuccessful(false);
                result.setMessage("该借条已经被其他用户确认，请与对方确认后重试");
                return result;
            }

            iousBO = new IousBO();
            iousBO.setId(id);
            iousBO.setLoanUserId(userInfo.getId());
            iousBO.setType(4);
            iousBO.setUpdateTime(DateUtils.getCurrentDate());
            iouService.updateLoanUserId(iousBO);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("更改用户借条信息", e);
            result.setSuccessful(false);
            result.setMessage("操作失败，请刷新页面后重试");
        }
        return result;
    }


    /**
     * 出借人/借款人获取借条信息（已经确认的借条）
     * @author wang.xu
     * @date 2018-12-18
     * @param loadType 加载数据类型 1-借款人获取借条信息 4-出借人获取借条信息（这个值和type初始值一样）
     * @param loadTimeType 1-获取全部 2-获取借款/出借时间为7天 3-获取借款/出借时间为30天
     * @param keyWord 搜索关键字，目前通过名称搜索
     * @return
     */
    @GetMapping("/getIousList")
    @ResponseBody
    @NeedToken
    public  CommonResult<List<IousVO>> getIousList(@RequestParam("loadType")Integer loadType,
                                                   @RequestParam("loadTimeType")Integer loadTimeType,
                                                   @RequestParam("keyWord") String keyWord) {
        CommonResult<List<IousVO>> result = new CommonResult<>();
        try{
            List<IousVO> resultList = new ArrayList<>();
            UserBO userInfo = getUserInfo();
            Integer userId = userInfo.getId();

            GetIousListVO getIousListVO = new GetIousListVO();
            getIousListVO.setLoadType(loadType);
            getIousListVO.setLoadTimeType(loadTimeType);
            getIousListVO.setLendUserId(userId);
            getIousListVO.setLoanUserId(userId);
            getIousListVO.setKeyWord(keyWord);
            //List<Ious> iousList = iouService.getIousList(getIousListVO);
            List<Ious> iousList = iouService.getIousAndPostponeList(getIousListVO);
            if(!CollectionUtils.isEmpty(iousList)){
                List<Code> purposeList = codeService.getSelectValueByPid(1, null);
                for(Ious ious : iousList){
                    IousVO iousVO = new IousVO();
                    iousVO.setId(ious.getId());
                    if(loadType == 1) {
                        iousVO.setName(ious.getLender());
                    }else{
                        iousVO.setName(ious.getLoaner());
                    }
                    iousVO.setMoney(CalculateUtil.divide(ious.getMoney(), 100,2).doubleValue());
                    String returnTime = ious.getReturnTime();
                    Postpone postpone = ious.getPostpone();
                    if(postpone != null){
                        returnTime = postpone.getPostponeTime();
                    }
                    iousVO.setRemainTime(DateUtils.getDays(returnTime));

                    Integer purpose = ious.getPurpose();
                    if(!CollectionUtils.isEmpty(purposeList)){
                       for(Code code : purposeList){
                           if(code.getValue().equals(purpose)){
                               iousVO.setPurposeName(code.getName());
                               break;
                           }
                       }
                    }else{
                        iousVO.setPurposeName("");
                    }
                    resultList.add(iousVO);
                }
            }
            result.setData(resultList);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("获取出借人/借款人获取借条信息（已经确认的借条）", e);
            result.setSuccessful(false);
            result.setMessage("获取借条信息失败");
        }
        return result;
    }

    /**
     * 销账,将cancelStatus设置为2
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/cancel")
    @ResponseBody
    @NeedToken
    public CommonResult<Boolean> cancel(@RequestParam("id") Integer id) {
        CommonResult<Boolean> result = new CommonResult<>();
        try{
            IousBO iousBO = new IousBO();
            iousBO.setId(id);
            iousBO.setCancelStatus(IousCancelStatusEnum.CANCEL_STATUS_ACCOUNT.type);
            iouService.updateCancelStatus(iousBO);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("销账", e);
            result.setSuccessful(false);
            result.setMessage("销账失败，请重试");
        }
        return result;
    }

    /**
     * 发起展期
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @PostMapping("/postpone")
    @ResponseBody
    @NeedToken
    public CommonResult<Void> postpone(PostponeVO postponeVO) {
        CommonResult<Void> result = new CommonResult<>();
        try{
            postponeService.savePosepone(postponeVO);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("发起展期", e);
            result.setSuccessful(false);
            result.setMessage("发起展期失败，请重试");
        }
        return result;
    }

    /**
     * 修改展期支付状态
     * @author wang.xu
     * @date 2018-12-18
     * @return
     */
    @GetMapping("/updatePostponeStatus")
    @ResponseBody
    @NeedToken
    public CommonResult<Boolean> updatePostponeStatus(@RequestParam("postponeId") Integer postponeId) {
        CommonResult<Boolean> result = new CommonResult<>();
        try{
            Postpone  postpone = new Postpone();
            postpone.setId(postponeId);
            postpone.setPostponeStatus(IousStatusEnum.STATUS_PAY_SURE.type);
            postpone.setUpdateTime(DateUtils.getCurrentDate());
            postponeService.updatePostponeStatus(postpone);
            result.setSuccessful(true);
        }catch (Exception e){
            LOGGER.error("修改展期支付状态", e);
            result.setSuccessful(false);
            result.setMessage("修改展期支付状态失败");
        }
        return result;
    }

}
