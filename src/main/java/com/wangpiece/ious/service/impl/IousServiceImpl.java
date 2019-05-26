package com.wangpiece.ious.service.impl;

import com.wangpiece.ious.bo.IousBO;
import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.dto.Code;
import com.wangpiece.ious.dto.Ious;
import com.wangpiece.ious.dto.Postpone;
import com.wangpiece.ious.enums.IousCancelStatusEnum;
import com.wangpiece.ious.enums.IousStatusEnum;
import com.wangpiece.ious.mapper.IousMapper;
import com.wangpiece.ious.service.ICodeService;
import com.wangpiece.ious.service.IIouService;
import com.wangpiece.ious.service.IPostponeService;
import com.wangpiece.ious.utils.CalculateUtil;
import com.wangpiece.ious.utils.DateUtils;
import com.wangpiece.ious.vo.GetIousListVO;
import com.wangpiece.ious.vo.IousVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-25 18:23
 */
@Service
public class IousServiceImpl implements IIouService{

    @Autowired
    private IousMapper iousMapper;
    @Autowired
    private ICodeService codeService;
    @Autowired
    private IPostponeService postponeService;

    @Override
    @Transactional
    public Integer save(IousBO iousBO, UserBO userBO) {
        Ious ious = new Ious();
        BeanUtils.copyProperties(iousBO, ious);
        Integer type = iousBO.getType();
        //type=1为借款 则说明发起人是借款人
        if(type == 1){
            ious.setLoanUserId(userBO.getId());
            ious.setLendUserId(0);
            //数据库里存的真是姓名
            //借款人
            ious.setLoaner(userBO.getName());
        }else {
            //出借
            ious.setLoanUserId(0);
            ious.setLendUserId(userBO.getId());
            //数据库里存的真是姓名
            //出借人
            ious.setLender(userBO.getName());
        }
        //将金额转换为分
        ious.setMoney(CalculateUtil.multiply(iousBO.getMoney(), 100, 0).intValue());
        ious.setStatus(IousStatusEnum.STATUS_UNPAY.type);
        ious.setCancelStatus(IousCancelStatusEnum.CANCEL_STATUS_UNACCOUNT.type);
        ious.setCreateTime(DateUtils.getCurrentDate());
        ious.setUpdateTime(DateUtils.getCurrentDate());
        iousMapper.save(ious);
        return ious.getId();
    }

    /**
     * 通过id获取借条详细信息
     * @param id
     * @return
     */
    @Override
    public IousBO getIousInfo(Integer id) {
        Ious ious = iousMapper.getIousInfo(id);
        IousBO iousBO = null;
        if(ious != null) {
            iousBO = new IousBO();
            BeanUtils.copyProperties(ious, iousBO);
            Integer rate = ious.getRate();
            List<Code> rateList = codeService.getSelectValueByPid(6, rate);
            if(!CollectionUtils.isEmpty(rateList)){
                Code code = rateList.get(0);
                iousBO.setRateName(code.getName());
            }

            Integer purpose = ious.getPurpose();
            List<Code> purposeList = codeService.getSelectValueByPid(1, purpose);
            if(!CollectionUtils.isEmpty(purposeList)){
                Code code = purposeList.get(0);
                iousBO.setPurposeName(code.getName());
            }
            iousBO.setMoreInfo(StringUtils.isEmpty(ious.getMoreInfo()) ? "无" : ious.getMoreInfo());

            Integer money = ious.getMoney();
            iousBO.setMoney(CalculateUtil.divide(money, 100,2).doubleValue());
        }
        return iousBO;
    }

    /**
     * 通过id获取借条详细信息
     *
     * @param id
     * @return
     */
    @Override
    public IousVO getIousVOInfo(Integer id,Integer type) {
        Ious ious = iousMapper.getIousInfo(id);
        IousVO iousVO = null;
        if(ious != null) {
            iousVO = new IousVO();
            BeanUtils.copyProperties(ious, iousVO);
            Integer rate = ious.getRate();
            List<Code> rateList = codeService.getSelectValueByPid(6, rate);
            if(!CollectionUtils.isEmpty(rateList)){
                Code code = rateList.get(0);
                iousVO.setRate(code.getName());
            }

            Integer purpose = ious.getPurpose();
            List<Code> purposeList = codeService.getSelectValueByPid(1, purpose);
            if(!CollectionUtils.isEmpty(purposeList)){
                Code code = purposeList.get(0);
                iousVO.setPurposeName(code.getName());
            }
            Postpone postpone = postponeService.getByIousId(id);
            String returnTime = ious.getReturnTime();
            if(postpone != null){
                returnTime = postpone.getPostponeTime();
            }
            iousVO.setRemainTime(DateUtils.getDays(returnTime));

            //1-借款 2-出借

            if(type==1){//获取出借人姓名
                iousVO.setName(ious.getLender());
            }else if(type==2){
                //获取借款人姓名
                iousVO.setName(ious.getLoaner());
            }else{
                iousVO.setName("");
            }

            Integer money = ious.getMoney();
            iousVO.setMoney(CalculateUtil.divide(money, 100,2).doubleValue());
        }
        return iousVO;
    }

    /**
     * 获取借款用户id和支付状态获取所有借款借条信息
     *
     * @param iousBO
     * @return
     */
    @Override
    public List<Ious> getIousByLoanIousInfo(IousBO iousBO) {
        return iousMapper.getIousByLoanIousInfo(iousBO);
    }

    /**
     * 通过出借用户id和支付状态获取所有出借借条信息
     *
     * @param iousBO
     * @return
     */
    @Override
    public List<Ious> getIousByLendIousInfo(IousBO iousBO) {
        return iousMapper.getIousByLendIousInfo(iousBO);
    }

    /**
     * 删除借条信息,只是更改标志位
     * @param iousBO
     */
    @Override
    @Transactional
    public void updateCancelStatus(IousBO iousBO) {
        iousMapper.updateCancelStatus(iousBO);
    }

    /**
     * 修改借条的支付状态
     *
     * @param iousBO 借条信息
     */
    @Override
    @Transactional
    public void updateStatus(IousBO iousBO) {
        iousMapper.updateStatus(iousBO);
    }

    /**
     * 更改用户借条信息，将该借条与该用户绑定
     *
     * @param iousBO 借条信息
     */
    @Override
    @Transactional
    public void updateLoanUserId(IousBO iousBO) {
        iousMapper.updateLoanUserId(iousBO);
    }

    /**
     * 出借人/借款人获取借条信息（已经确认的借条）
     * @author wang.xu
     * @date 2018-12-18
     * @param getIousListVO
     * @return
     */
    @Override
    public List<Ious> getIousList(GetIousListVO getIousListVO) {
        return iousMapper.getIousList(getIousListVO);
    }

    /**
     * 出借人/借款人获取借条信息（已经确认的借条）,和展期表关联查询
     *
     * @param getIousListVO
     * @return
     */
    @Override
    public List<Ious> getIousAndPostponeList(GetIousListVO getIousListVO) {
        return iousMapper.getIousAndPostponeList(getIousListVO);
    }

    /**
     * 获取借条金额信息
     * @param loadType 加载数据类型 1-借款人获取借条信息 4-出借人获取借条信息（这个值和type初始值一样）
     * @param userInfo
     * @return
     */
    @Override
    public Map<String, String> getIousMoney(Integer loadType, UserBO userInfo) {
        Map<String, String> resultMap = new HashMap<>();
        GetIousListVO getIousListVO = new GetIousListVO();
        getIousListVO.setLoadType(loadType);
        getIousListVO.setLoadTimeType(1);//1-表示加载全部
        getIousListVO.setLendUserId(userInfo.getId());
        getIousListVO.setLoanUserId(userInfo.getId());

        List<Ious> iousList = this.getIousAndPostponeList(getIousListVO);
//        List<Ious> iousList = this.getIousList(getIousListVO);
        if(!CollectionUtils.isEmpty(iousList)){
            Integer allMoney = 0;
            Integer day7Money = 0;
            Integer day30Money = 0;
            for(Ious ious : iousList){

                Postpone postpone = ious.getPostpone();
                String returnTime = ious.getReturnTime();
                if(postpone != null){
                     returnTime = postpone.getPostponeTime();
                }
                String loanTime = ious.getLoanTime();
                Integer days = DateUtils.getDays(loanTime, returnTime);
                Integer money = ious.getMoney();
                allMoney += money;
                if(days == 7){
                    day7Money += money;
                }else{
                    day30Money += money;
                }
            }
            resultMap.put("allMoney",CalculateUtil.divide(allMoney, 100,2).doubleValue() +"");
            resultMap.put("day7Money",CalculateUtil.divide(day7Money, 100,2).doubleValue() +"");
            resultMap.put("day30Money",CalculateUtil.divide(day30Money, 100,2).doubleValue() +"");
        }else{
            resultMap.put("allMoney","0.00");
            resultMap.put("day7Money","0.00");
            resultMap.put("day30Money","0.00");
        }
        return resultMap;
    }
}
