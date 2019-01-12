package com.wangpiece.ious.mapper;

import com.wangpiece.ious.dto.Postpone;
import com.wangpiece.ious.vo.PostponeVO;

import java.util.List;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:04
 */
public interface PostponeMapper {
    /**
     *  保存展期信息
     * @param postpone
     * @return
     */
    void savePosepone(Postpone postpone);
    /**
     * 获取展期历史
     * @param postponeVO
     * @return
     */
    List<Postpone> getPostpone(PostponeVO postponeVO);

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
