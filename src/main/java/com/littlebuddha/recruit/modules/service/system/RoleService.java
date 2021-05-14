package com.littlebuddha.recruit.modules.service.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import com.littlebuddha.recruit.modules.entity.system.utils.RoleMenuTDO;
import com.littlebuddha.recruit.modules.mapper.system.MenuMapper;
import com.littlebuddha.recruit.modules.mapper.system.RoleMapper;
import com.littlebuddha.recruit.modules.mapper.system.RoleMenuMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends CrudService<Role, RoleMapper> {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public int save(Role entity) {
        return super.save(entity);
    }

    @Override
    public int deleteByPhysics(Role entity) {
        return super.deleteByPhysics(entity);
    }

    @Override
    public Role get(Role entity) {
        return super.get(entity);
    }

    @Override
    public List<Role> findList(Role entity) {
        return super.findList(entity);
    }

    @Override
    public List<Role> findAllList(Role entity) {
        return super.findAllList(entity);
    }

    @Override
    public PageInfo<Role> findPage(Page<Role> page, Role entity) {
        return super.findPage(page, entity);
    }

    @Override
    public int deleteByLogic(Role entity) {
        return super.deleteByLogic(entity);
    }

    @Override
    public int recovery(Role entity) {
        int recovery = super.recovery(entity);
        return recovery;
    }

    public int addPermission(RoleMenuTDO roleMenuTDO) {
        Menu menu = null;
        Role role = null;
        RoleMenu roleMenu = null;
        int result = 0;
        if (roleMenuTDO != null && StringUtils.isNotBlank(roleMenuTDO.getMenuIds())) {
            String menuIds = roleMenuTDO.getMenuIds();
            String[] split = menuIds.split(",");
            for (String menuId : split) {
                if (menuId != null && StringUtils.isNotBlank(menuId)) {
                    menu = menuMapper.get(new Menu(menuId));
                    role = roleMenuTDO.getRole();
                    roleMenu = new RoleMenu(role, menu);
                    RoleMenu byRoleMenu = roleMenuMapper.getByRoleMenu(roleMenu);
                    if (byRoleMenu == null) {
                        result = roleMenuService.save(roleMenu);
                    }
                }
            }
        }
        return result;
    }
}
