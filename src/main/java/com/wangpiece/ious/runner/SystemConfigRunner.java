package com.wangpiece.ious.runner;

import com.google.common.collect.Maps;
import com.wangpiece.ious.dto.SystemConfig;
import com.wangpiece.ious.mapper.SystemConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 系统启动加载短信配置信息
 */
@Component
public class SystemConfigRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemConfigRunner.class);
    public static Map<String,String> SMS_CONFIG_MAP = Maps.newHashMap();

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("加载系统配置信息开始...");
        List<SystemConfig> systemConfigList = systemConfigMapper.getSystemConfigList(1);
        for (SystemConfig config : systemConfigList){
            String key = config.getKey();
            String value = config.getValue();
            SMS_CONFIG_MAP.put(key,value);
        }
        LOGGER.info("加载系统配置信息结束...");
    }
}
