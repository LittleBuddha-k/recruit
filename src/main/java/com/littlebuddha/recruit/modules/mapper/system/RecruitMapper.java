package com.littlebuddha.recruit.modules.mapper.system;


import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Recruit;
import com.littlebuddha.recruit.modules.entity.system.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *招聘信息mapper层
 */
@Mapper
public interface RecruitMapper extends BaseMapper<Recruit> {

}
