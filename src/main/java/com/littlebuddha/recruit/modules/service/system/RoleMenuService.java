package com.littlebuddha.recruit.modules.service.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.mapper.system.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuService extends CrudService<Role, RoleMapper> {

}
