package com.littlebuddha.recruit.modules.service.system;

import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.mapper.system.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class OperatorRoleService extends CrudService<Role, RoleMapper> {

}
