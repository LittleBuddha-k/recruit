package com.littlebuddha.recruit.common.config.shiro;

import com.littlebuddha.recruit.common.config.shiro.realms.CustomerRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro配置文件
 */
@Configuration
public class ShiroConfig {

    //创建ShiroFilter----负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> map = new HashMap<>();
        //配置系统公共资源
        map.put("/system/login","anon");
        map.put("/system/registerPage","anon");
        map.put("/system/register","anon");
        map.put("/system/logout","anon");
        map.put("/css/**","anon");
        map.put("/fonts/**","anon");
        map.put("/jquery/**","anon");
        map.put("/js/**","anon");
        //配置系统受限资源
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/system/loginPage");
        return shiroFilterFactoryBean;
    }

    //创建SecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //创建Realm
    @Bean
    public Realm getRealm(){
        return new CustomerRealm();
    }

}
