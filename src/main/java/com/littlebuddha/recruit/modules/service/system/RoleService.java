package com.littlebuddha.recruit.modules.service.system;

import com.littlebuddha.recruit.common.utils.AutoId;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.mapper.system.OperatorMapper;
import com.littlebuddha.recruit.modules.mapper.system.RoleMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends CrudService<Role, RoleMapper> {

}
