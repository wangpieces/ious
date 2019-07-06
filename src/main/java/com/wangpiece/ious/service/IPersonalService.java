package com.wangpiece.ious.service;

import com.wangpiece.ious.bo.UserBO;
import com.wangpiece.ious.dto.Code;
import com.wangpiece.ious.vo.RegisterInfoVO;
import com.wangpiece.ious.vo.SavePwdVO;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:10
 */
public interface IPersonalService {


   void savePwd(UserBO userBO, SavePwdVO savePwdVO);

   Boolean saveRegister(RegisterInfoVO registerInfoVO) throws Exception;
}
