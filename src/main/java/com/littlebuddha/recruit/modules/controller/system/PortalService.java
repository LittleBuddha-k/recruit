package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import com.littlebuddha.recruit.modules.mapper.system.MenuMapper;
import com.littlebuddha.recruit.modules.service.system.MenuService;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PortalService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OperatorService operatorService;

    public List<Menu> findMenus(Operator currentUser) {
        Operator rolesByOperator = operatorService.findRolesByOperator(currentUser);
        List<Role> roles = rolesByOperator.getRoles();
        List<RoleMenu> translate = new ArrayList<>();
        List<Menu> menus = new ArrayList<>();
        for (Role role : roles) {
            List<RoleMenu> roleMenusByRole = menuService.findRoleMenusByRole(role);
            translate.addAll(roleMenusByRole);
        }
        for (RoleMenu roleMenu : translate) {
            if(roleMenu.getMenu() != null){
                menus.add(roleMenu.getMenu());
            }
        }
        removeDuplicate(menus);
        System.out.println("地方都是");
        return menus;
    }


    //去重
    public   static   List  removeDuplicate(List<Menu> list)  {
        for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
            for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
                if  (list.get(j).getId().equals(list.get(i).getId()))  {
                    list.remove(j);
                }
            }
        }
        return list;
    }
}
