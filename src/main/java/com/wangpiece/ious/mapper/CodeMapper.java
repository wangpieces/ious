package com.wangpiece.ious.mapper;

import com.wangpiece.ious.dto.Code;

import java.util.List;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:04
 */
public interface CodeMapper {
    /**
     *  通过pid获取对应的值
     * @param code
     * @return
     */
    List<Code> getSelectValueByPid(Code code);

}
