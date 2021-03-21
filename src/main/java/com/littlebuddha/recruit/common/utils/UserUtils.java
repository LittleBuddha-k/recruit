package com.littlebuddha.recruit.common.utils;

import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * 与用户相关操作的帮助类
 */
public class UserUtils {

    public static Operator getCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        Operator currentUser = (Operator) subject.getPrincipal();
        return currentUser;
    }

    /**
     * 获取当前登录者对象
     */
    public static Operator getPrincipal(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Operator principal = (Operator)subject.getPrincipal();
            if (principal != null){
                return principal;
            }
//			subject.logout();
        }catch (UnavailableSecurityManagerException e) {

        }catch (InvalidSessionException e){

        }
        return null;
    }

    /**
     * 获取当前用户菜单列表
     */
    public List<Menu> getCurrentUserMenu(){
        Subject subject = SecurityUtils.getSubject();
        Operator principal = (Operator) subject.getPrincipal();
        return null;
    }
}
