package com.wangpiece.ious.mapper;

import com.wangpiece.ious.bo.IousBO;
import com.wangpiece.ious.dto.Ious;
import com.wangpiece.ious.vo.GetIousListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-25 18:26
 */
public interface IousMapper {

    /**
     * 保存借条信息
     * @param ious
     */
    void save(Ious ious);

    /**
     * 获取借条信息
     * @param id
     * @return
     */
    Ious getIousInfo(Integer id);

    /**
     * 获取借款用户id和支付状态获取所有借款借条信息
     * @param iousBO
     * @return
     */
    List<Ious> getIousByLoanIousInfo(IousBO iousBO);

    /**
     * 通过出借用户id和支付状态获取所有出借借条信息
     * @param iousBO
     * @return
     */
    List<Ious> getIousByLendIousInfo(IousBO iousBO);

    /**
     * 修改借条的标志位信息
     * @param iousBO 借条信息
     */
    void updateCancelStatus(IousBO iousBO);

    /**
     * 修改借条的支付状态
     * @param iousBO 借条信息
     */
    void updateStatus(IousBO iousBO);

    /**
     * 更改用户借条信息，将该借条与该用户绑定
     * @param iousBO 借条信息
     */
    void updateLoanUserId(IousBO iousBO);

    /**
     * 修改借条上的展期信息
     * @param iousId
     * @param postponeTime
     * @param postponeRate
     */
    void updateIousPostone(@Param("iousId") Integer iousId, @Param("postponeTime") String postponeTime, @Param("postponeRate") Integer postponeRate);

    /**
     * 出借人/借款人获取借条信息（已经确认的借条）
     * @author wang.xu
     * @date 2018-12-18
     * @param getIousListVO
     * @return
     */
    List<Ious> getIousList(GetIousListVO getIousListVO);

    /**
     * 出借人/借款人获取借条信息（已经确认的借条）,和展期表关联查询
     * @param getIousListVO
     * @return
     */
    List<Ious> getIousAndPostponeList(GetIousListVO getIousListVO);
}
