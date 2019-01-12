package com.wangpiece.ious.service.impl;

import com.google.common.collect.Maps;
import com.wangpiece.ious.bo.IousBO;
import com.wangpiece.ious.bo.PostponeBO;
import com.wangpiece.ious.dto.Code;
import com.wangpiece.ious.dto.Ious;
import com.wangpiece.ious.dto.Postpone;
import com.wangpiece.ious.enums.IousStatusEnum;
import com.wangpiece.ious.mapper.IousMapper;
import com.wangpiece.ious.mapper.PostponeMapper;
import com.wangpiece.ious.service.ICodeService;
import com.wangpiece.ious.service.IPostponeService;
import com.wangpiece.ious.utils.CalculateUtil;
import com.wangpiece.ious.utils.DateUtils;
import com.wangpiece.ious.vo.PostponeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:14
 */
@Service
public class PostponeServiceImpl implements IPostponeService{

    @Autowired
    private PostponeMapper postponeMapper;

    @Autowired
    private ICodeService codeService;

    @Autowired
    private IousMapper iousMapper;

    /**
     * 保存展期信息
     *
     * @param postponeVO
     * @return
     */
    @Override
    public void savePosepone(PostponeVO postponeVO) {

        Ious iousInfo = iousMapper.getIousInfo(postponeVO.getIousId());
        if(iousInfo == null){
            throw new RuntimeException("借条信息不存在，无法发起展期");
        }

        Postpone postpone = new Postpone();
        BeanUtils.copyProperties(postponeVO, postpone);
        postpone.setPostponeStatus(IousStatusEnum.STATUS_UNPAY.type);
        String currentTime = DateUtils.getCurrentDate();
        postpone.setCreateTime(currentTime);
        postpone.setUpdateTime(currentTime);
        postpone.setMoney(iousInfo.getMoney());
        postponeMapper.savePosepone(postpone);
    }

    /**
     * 获取展期历史
     *
     * @param postponeVO
     * @return
     */
    @Override
    public Map<Object,Object> getPostpone(PostponeVO postponeVO) {
        Map<Object,Object> resultMap = Maps.newHashMap();
        List<Postpone> postponeList = postponeMapper.getPostpone(postponeVO);
        if(!CollectionUtils.isEmpty(postponeList)){
            List<PostponeBO> resultList = new ArrayList<>();
            List<Code> rateList = codeService.getSelectValueByPid(6, null);
            for(Postpone postpone : postponeList){
                PostponeBO postponeBO = new PostponeBO();
                BeanUtils.copyProperties(postpone, postponeBO);
                Integer postponeRate = postpone.getPostponeRate();
                for(Code code : rateList){
                    if(code.getValue().equals(postponeRate)){
                        postponeBO.setPostponeRateName(code.getName());
                        break;
                    }
                }
                Integer postponeStatus = postpone.getPostponeStatus();
                postponeBO.setPostponeStatusName(IousStatusEnum.IOUS_STATUS_ENUM_MAP.get(postponeStatus));
                Integer money = postpone.getMoney();
                postponeBO.setMoney(CalculateUtil.divide(money, 100, 2).doubleValue());
                //获取未支付的展期信息，每次发起展期前必须是所有的展期对方确认且支付后才能发起二次展期
                if(IousStatusEnum.STATUS_PAY_SURE.type != postponeStatus && resultMap.get("postpone") == null){
                    resultMap.put("postpone", postponeBO);
                }
                resultList.add(postponeBO);
            }
            resultMap.put("resultList",resultList);
        }
        return resultMap;
    }

    /**
     * 修改展期专题
     *
     * @param postpone
     */
    @Override
    public void updatePostponeStatus(Postpone postpone) {
        postponeMapper.updatePostponeStatus(postpone);
    }

    /**
     * 获取展期新，最新已支付展期信息
     *
     * @param iousId
     * @return
     */
    @Override
    public Postpone getByIousId(Integer iousId) {
        return postponeMapper.getByIousId(iousId);
    }
}
