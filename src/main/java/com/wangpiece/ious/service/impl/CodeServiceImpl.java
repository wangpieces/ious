package com.wangpiece.ious.service.impl;

import com.wangpiece.ious.dto.Code;
import com.wangpiece.ious.mapper.CodeMapper;
import com.wangpiece.ious.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:14
 */
@Service
public class CodeServiceImpl implements ICodeService{

    @Autowired
    private CodeMapper codeMapper;

    /**
     * 通过pid获取对应的值
     *
     * @param pid
     * @return
     */
    @Override
    public List<Code> getSelectValueByPid(Integer pid,Integer value){
        Code code = new Code();
        code.setPid(pid);
        code.setValue(value);
        return codeMapper.getSelectValueByPid(code);
    }
}
