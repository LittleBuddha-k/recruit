package com.littlebuddha.recruit.modules.mapper.system;

import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperatorMapper extends BaseMapper<Operator> {

    Operator getOperatorByName(Operator operator);

    Operator getRolesByOperator(Operator operator);
}
