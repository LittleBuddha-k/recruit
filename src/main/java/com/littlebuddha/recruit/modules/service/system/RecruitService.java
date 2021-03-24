package com.littlebuddha.recruit.modules.service.system;

import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Recruit;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.mapper.system.RecruitMapper;
import com.littlebuddha.recruit.modules.mapper.system.RoleMapper;
import org.springframework.stereotype.Service;

@Service
public class RecruitService extends CrudService<Recruit, RecruitMapper> {

}
