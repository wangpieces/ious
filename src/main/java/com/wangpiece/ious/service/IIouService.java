package com.wangpiece.ious.service;

import com.wangpiece.ious.bo.IousBO;
import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.dto.Ious;
import com.wangpiece.ious.vo.GetIousListVO;
import com.wangpiece.ious.vo.IousVO;

import java.util.List;
import java.util.Map;

/**
 * @author wang.xu
 * @desc 借条相关业务
 * @date 2018-12-25 18:22
 */
public interface IIouService {
    /**
     * 保存借条信息
     * @param iousBO
     * @param userBO
     */
    Integer save(IousBO iousBO, UserBO userBO);

    /**
     * 通过id获取借条详细信息
     * @param id
     * @return
     */
    IousBO getIousInfo(Integer id);

    /**
     * 通过id获取借条详细信息
     * @param id
     * @return
     */
    IousVO getIousVOInfo(Integer id,Integer type);

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

    /**
     * 获取借条金额信息
     * @param loadType 加载数据类型 1-借款人获取借条信息 4-出借人获取借条信息（这个值和type初始值一样）
     * @param userInfo
     * @return
     */
    Map<String,String> getIousMoney(Integer loadType,UserBO userInfo);

    /**
     * 更新借条是否支付，其实只要点击支付图片就默认是支付了，没哟支付接口只能这样
     * @param id
     */
    void updatePayStatus(Integer id);

}
