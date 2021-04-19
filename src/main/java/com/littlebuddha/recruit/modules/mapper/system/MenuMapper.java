package com.littlebuddha.recruit.modules.mapper.system;

import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单MAPPER接口
 * @author jeeplus
 * @version 2017-05-16
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<RoleMenu> getRoleMenusByRole(RoleMenu roleMenu);
}
