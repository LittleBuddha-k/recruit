package com.littlebuddha.recruit.common.config.shiro.realms;

import com.littlebuddha.recruit.common.utils.ApplicationContextUtils;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomerRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //拿到的是个用户名
        String primaryPrincipal = (String)principalCollection.getPrimaryPrincipal();
        OperatorService operatorService = (OperatorService)ApplicationContextUtils.getBean("operatorService");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        OperatorService operatorService = (OperatorService)ApplicationContextUtils.getBean("operatorService");
        String principal = (String) authenticationToken.getPrincipal();
        Operator operator = operatorService.findOperatorByName(new Operator(principal));

        if(!ObjectUtils.isEmpty(operator)){
           return  new SimpleAuthenticationInfo(operator,operator.getPassword(), ByteSource.Util.bytes(operator.getSalt()),this.getName());
        }
        return null;
    }

}
