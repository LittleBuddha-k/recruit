package com.littlebuddha.recruit.modules.mapper.system;


import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 查询是否已经存在这个权限设置
     * @param roleMenu
     * @return
     */
    public RoleMenu getByRoleMenu(RoleMenu roleMenu);

    List<Menu> getRoleMenu(RoleMenu roleMenu);

    void deleteOutByRole(String roleId);
}
