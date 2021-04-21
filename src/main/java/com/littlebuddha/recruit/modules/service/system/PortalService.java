package com.littlebuddha.recruit.modules.service.system;

import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import com.littlebuddha.recruit.modules.mapper.system.MenuMapper;
import com.littlebuddha.recruit.modules.service.system.MenuService;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if (roleMenu.getMenu() != null) {
                menus.add(roleMenu.getMenu());
            }
        }
        removeDuplicate(menus);
        List<Menu> target = new ArrayList<>();
        sort(menus,target,menuService.getTopMenu().getId());
        setData(target);
        return target;
    }


    //去重
    public static List removeDuplicate(List<Menu> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getId().equals(list.get(i).getId())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    /**
     * 排序
     *
     * @return
     */
    public List<Menu> sort(List<Menu> sourceList, List<Menu> targetList, String parentId) {
        List<Menu> childrenList = null;
        for (Menu source : sourceList) {
            if (StringUtils.isNotBlank(source.getId()) && source.getParent().getId().equals(parentId)) {
                targetList.add(source);
                // 判断有没有子节点，有则继续追加
                if (source.getHasChildren()) {
                    //排序
                    for (Menu menu : sourceList) {
                        if (menu.getParent().getId().equals(source.getId())) {
                            sort(sourceList, targetList, source.getId());
                            break;
                        }
                    }
                }
            }
        }
        return targetList;
    }

    /**
     * set子集
     *
     * @return
     */
    public List<Menu> setData(List<Menu> menuList) {
        Map<String, String> menuMap = new HashMap<>();
        //用于存放根节点
        Menu root = null;
        //便利列表
        for (Menu source : menuList) {
            if (menuService.getTopMenu().getId().equals(source.getParent().getId())) {
                root = source;
            }
            for (Menu menu : menuList) {
                if (menu.getParent() != null && menu.getParent().getId().equals(source.getId())) {
                    List<Menu> children = source.getChildren();
                    children.add(menu);
                }
            }
        }
        //遍历菜单表，在map以id、name形式存放
        //便利列表，set进父节点
        return menuList;
    }
}
