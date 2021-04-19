package com.littlebuddha.recruit.modules.service.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import com.littlebuddha.recruit.modules.mapper.system.MenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单业务层
 */
@Service
public class MenuService extends CrudService<Menu, MenuMapper> {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int save(Menu menu) {
        int save = 0;
        List<Menu> allList = menuMapper.findAllList(new Menu());
        //当数据库没有菜单数据时，需要设置第一条数据为最高级菜单
        if (allList != null && allList.isEmpty()) {
            Menu parent = new Menu();
            parent.setId("-1");
            menu.setParent(parent);
            menu.setParentIds("-1");
            menu.setSort(0);
            save = super.save(menu);
        }
        //当数据库开始有菜单后--获取最顶端的父级菜单
        Menu topMenu = getTopMenu();

        menu.setParent(topMenu);
        String parentIds = menu.getParentIds();
        menu.setParentIds(menu.getParent().getParentIds() + menu.getParent().getId() + ",");

        //如果新建菜单----父级菜单为师祖级菜单
        if (menu.getId() == null || StringUtils.isBlank(menu.getId())) {
            //需要设置sort
            List<Menu> list = new ArrayList<>();
            List<Menu> sourcelist = menuMapper.findAllList(new Menu());
            Menu.sortList(list, sourcelist, menu.getParent().getId(), false);
            if (list.size() > 0) {
                menu.setSort(list.get(list.size() - 1).getSort() + 30);
            }
        }
        save = super.save(menu);
        return save;
    }

    @Override
    public int deleteByPhysics(Menu menu) {
        return super.deleteByPhysics(menu);
    }

    @Override
    public Menu get(Menu menu) {
        return super.get(menu);
    }

    @Override
    public List<Menu> findList(Menu menu) {
        return super.findList(menu);
    }

    @Override
    public List<Menu> findAllList(Menu menu) {
        return super.findAllList(menu);
    }

    @Override
    public PageInfo<Menu> findPage(Page<Menu> page, Menu menu) {
        return super.findPage(page, menu);
    }

    @Override
    public int deleteByLogic(Menu menu) {
        return super.deleteByLogic(menu);
    }

    @Override
    public int recovery(Menu menu) {
        int recovery = super.recovery(menu);
        return recovery;
    }

    /**
     * 获取最顶级的一个菜单
     *
     * @param
     * @return
     */
    public Menu getTopMenu() {
        Menu menu = new Menu();
        menu.setId("-1");
        Menu topMenu = get(menu);
        return topMenu;
    }

    /**
     * 通过角色查询菜单
     * @param role
     * @return
     */
    public List<RoleMenu> findRoleMenusByRole(Role role) {
        List<RoleMenu> roleMenus = menuMapper.getRoleMenusByRole(new RoleMenu(role));
        for (RoleMenu roleMenu : roleMenus) {
            if(roleMenu.getRole() != null && StringUtils.isNotBlank(roleMenu.getRole().getId())){

            }
            if(roleMenu.getMenu() != null && StringUtils.isNotBlank(roleMenu.getMenu().getId())){
                Menu menu = menuMapper.get(roleMenu.getMenu());
                roleMenu.setMenu(menu);
            }
        }
        return roleMenus;
    }
}
