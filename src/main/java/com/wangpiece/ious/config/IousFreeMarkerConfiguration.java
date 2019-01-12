package com.wangpiece.ious.config;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * The type Ksudi free marker configuration.
 *
 * @author Tony
 * @date 2018 -04-02 16:35:14
 */
@Component
@EnableConfigurationProperties(ServerProperties.class)
public class IousFreeMarkerConfiguration {
    /**
     * The Configuration.
     */
    @Autowired
    private Configuration configuration;

    @Value("${ious.context-path}")
    private String iousContextPath;

    @Value("${ious.request-url}")
    private String iousRequestUrl;

    /**
     * Sets shared variable.
     *
     * @author Tony
     * @date 2018 -04-02 16:35:14
     */
    @PostConstruct
    public void setSharedVariable() throws TemplateModelException {
        configuration.setSharedVariable("iousContextPath", iousContextPath);
        configuration.setSharedVariable("iousRequestUrl", iousRequestUrl);
    }

}
