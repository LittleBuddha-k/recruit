package com.littlebuddha.recruit.modules.mapper.system;


import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.system.OperatorRole;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 */
@Mapper
public interface OperatorRoleMapper extends BaseMapper<OperatorRole> {

    /**
     * 同时满足用户id和角色id的用户-角色关联信息
     * @param operatorRole
     * @return
     */
    OperatorRole getByOperatorAndRole(OperatorRole operatorRole);
}
