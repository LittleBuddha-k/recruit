package com.littlebuddha.recruit.modules.mapper.system;

import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.OperatorRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperatorMapper extends BaseMapper<Operator> {

    Operator getOperatorByName(Operator operator);

    Operator getRolesByOperator(Operator operator);

    /**
     * 对operator---role表的操作
     * @param operatorRole
     * @return
     */
    //int insertOperatorRole(@Param("operatorId") String operatorId,@Param("roleId") String roleId);
    int insertOperatorRole(OperatorRole operatorRole);

    /**
     * 根据用户更新operator-role中间表数据
     * @param operatorRole
     * @return
     */
    int updateOperatorRole(OperatorRole operatorRole);

    /**
     * 根据用户删除operator-role中间表数据
     * @param operatorRole
     * @return
     */
    int deleteOperatorRole(OperatorRole operatorRole);
}
