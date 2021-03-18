package com.littlebuddha.recruit.common.config.shiro.realms;

import com.littlebuddha.recruit.common.utils.ApplicationContextUtils;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        OperatorService operatorService = (OperatorService)ApplicationContextUtils.getBean("operatorService");
        String principal = (String) authenticationToken.getPrincipal();
        List<Operator> operatorByName = operatorService.findOperatorByName(new Operator(principal));
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(principal,"admin",this.getName());
        return simpleAuthenticationInfo;
    }

}
