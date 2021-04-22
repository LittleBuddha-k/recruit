package com.littlebuddha.recruit.modules.entity.system.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogoInfo {

    @Value("${logoInfo.title}")
    private String title;
    @Value("${logoInfo.image}")
    private String image;
    @Value("${logoInfo.href}")
    private String href;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
