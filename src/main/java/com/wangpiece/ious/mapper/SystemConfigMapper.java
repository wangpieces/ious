package com.wangpiece.ious.mapper;

import com.wangpiece.ious.dto.SystemConfig;

import java.util.List;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-30 22:04
 */
public interface SystemConfigMapper {
    /**
     *  获取系统配置参数
     * @param type
     * @return
     */
    List<SystemConfig> getSystemConfigList(Integer type);

}
