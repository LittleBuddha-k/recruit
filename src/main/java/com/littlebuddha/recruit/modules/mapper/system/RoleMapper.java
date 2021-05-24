package com.littlebuddha.recruit.modules.mapper.system;


import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 通过用户名查询角色列表
     * @return
     */
    List<Role> getRolesByOperator(Role role);
}
