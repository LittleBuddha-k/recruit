package com.littlebuddha.recruit.modules.entity.system.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 首页信息
 */
@Component
@ConfigurationProperties(prefix ="home")
public class HomeInfo {

    @Value("${home.title}")
    private String title;

    @Value("${home.href}")
    private String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
