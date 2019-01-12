package com.wangpiece.ious.service;

import com.wangpiece.ious.bo.IousBO;
import com.wangpiece.ious.bo.PostponeBO;
import com.wangpiece.ious.dto.Code;
import com.wangpiece.ious.dto.Ious;
import com.wangpiece.ious.dto.Postpone;
import com.wangpiece.ious.vo.PostponeVO;

import java.util.List;
import java.util.Map;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:10
 */
public interface IPostponeService {

    /**
     *  保存展期信息
     * @param postponeVO
     * @return
     */
    void savePosepone(PostponeVO postponeVO);

    /**
     * 获取展期历史
     * @param postponeVO
     * @return
     */
    Map<Object,Object> getPostpone(PostponeVO postponeVO);

    /**
     * 修改展期专题
     * @param postpone
     */
    void updatePostponeStatus(Postpone postpone);

    /**
     * 获取展期新，最新已支付展期信息
     * @param iousId
     * @return
     */
    Postpone getByIousId(Integer iousId);
}
