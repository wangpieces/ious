package com.wangpiece.ious.service;

import com.wangpiece.ious.dto.Code;

import java.util.List;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:10
 */
public interface ICodeService {

    /**
     *  通过pid获取对应的值
     * @param pid
     * @param value
     * @return
     */
    List<Code> getSelectValueByPid(Integer pid,Integer value);
}
