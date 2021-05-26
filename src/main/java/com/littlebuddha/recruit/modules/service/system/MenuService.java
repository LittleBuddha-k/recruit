package com.littlebuddha.recruit.modules.service.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.MenuUtils;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import com.littlebuddha.recruit.modules.mapper.system.MenuMapper;
import com.littlebuddha.recruit.modules.mapper.system.RoleMapper;
import com.littlebuddha.recruit.modules.mapper.system.RoleMenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 菜单业务层
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MenuService extends CrudService<Menu, MenuMapper> {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public Menu get(Menu menu) {
        return super.get(menu);
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

    @Override
    public List<Menu> findList(Menu menu) {
        List<Menu> list = super.findList(menu);
        return list;
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
    @Transactional
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

        // 获取修改前的parentIds，用于更新子节点的parentIds
        String oldParentIds = menu.getParentIds();

        //设置父类信息
        menu.setParent(menuMapper.get(menu.getParent().getId()));
        Menu parent = menu.getParent();
        System.out.println("424152");
        String parentIds = parent.getParentIds();
        String id1 = menu.getParent().getId();
        menu.setParentIds(parentIds + id1 + ",");

        //设置sort
        if (menu.getId() == null || StringUtils.isBlank(menu.getId())) {
            //需要设置sort
            List<Menu> list = new ArrayList<>();
            List<Menu> sourcelist = menuMapper.findAllList(new Menu());
            String id = menu.getParent().getId();
            List<Menu> list2 = new ArrayList<>();
            Menu.sortList(list, sourcelist, id, false);
            if (list.size() > 0) {
                menu.setSort(list.get(list.size() - 1).getSort() + 30);
            }
        }

        // 更新子节点 parentIds
        Menu updateChildren = new Menu();
        updateChildren.setParentIds("%,"+menu.getId()+",%");
        List<Menu> list = menuMapper.findByParentIdsLike(updateChildren);
        for (Menu entity : list){
            entity.setParentIds(entity.getParentIds().replace(oldParentIds, menu.getParentIds()));
            menuMapper.updateParentIds(entity);
        }

        save = super.save(menu);
        return save;
    }

    @Override
    @Transactional
    public int deleteByPhysics(Menu menu) {
        //删除菜单
        int i = super.deleteByPhysics(menu);
        //查找与菜单相关的 角色--菜单相关数据一并删除
        int i1 = roleMenuMapper.deleteByPhysics(new RoleMenu(menu));
        return i;
    }

    @Override
    @Transactional
    public int deleteByLogic(Menu menu) {
        //删除菜单
        int i = super.deleteByLogic(menu);
        //查找与菜单相关的 角色--菜单相关数据一并删除
        int i1 = roleMenuMapper.deleteByPhysics(new RoleMenu(menu));
        return i;
    }

    @Override
    @Transactional
    public int recovery(Menu menu) {
        int recovery = super.recovery(menu);
        return recovery;
    }

    /**
     * 将会返回menu集合-----排序+set子集
     *
     * @return
     */
    public List<Menu> findMenuInfo() {
        //1.查询当前用户的菜单数据list
        List<Menu> menuData = operatorService.getMenusByOperator();
        //3.因为多个角色可能有多个重复的菜单信息，所以对菜单去重
        MenuUtils.removeDuplicate(menuData);
        //4.排序
        List<Menu> afterSort = new ArrayList<>();
        String id = getTopMenu().getId();
        if (id != null && StringUtils.isNotBlank(id)) {
            MenuUtils.sort(menuData, afterSort, id);
        } else {
            MenuUtils.sort(menuData, afterSort, "-1");
        }
        //5.set子集
        MenuUtils.setChildrenList(afterSort);
        //6.需要的结果-----set完子集后去除列表中非一级菜单
        List<Menu> result = new ArrayList<>();
        for (Menu menu : afterSort) {
            if (id.equals(menu.getParent().getId())) {
                result.add(menu);
            }
        }
        return result;
    }
}
