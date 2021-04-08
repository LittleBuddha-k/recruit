package com.littlebuddha.recruit.modules.service.system;

import com.littlebuddha.recruit.common.utils.AutoId;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.OperatorRole;
import com.littlebuddha.recruit.modules.mapper.system.OperatorMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperatorService extends CrudService<Operator, OperatorMapper> {

    @Autowired
    private OperatorMapper operatorMapper;

    @Override
    public int save(Operator operator) {
        int operatorRow;
        //这一步保存的是operator数据
        if (operator.getIsNewData()){
            System.out.println("执行新增操作");
            operator.preInsert();
            //获取盐值
            String splicing = AutoId.getSplicing(16);
            Md5Hash md5Hash = new Md5Hash(operator.getPassword(), splicing, 1024);
            operator.setSalt(splicing);
            operator.setPassword(md5Hash.toHex());
            operatorRow = operatorMapper.insert(operator);

            //这里应该将operator form填写的roles数据单独保存到operator-role表格
            List<Role> roles = operator.getRoles();
            OperatorRole util = null;
            if(roles != null && !roles.isEmpty()){
                for (Role role : roles) {
                    util = new OperatorRole(operator,role);
                    util.preInsert();
                    int i = operatorMapper.insertOperatorRole(util);
                }
            }
        }else{
            System.out.println("执行更新操作");
            operator.preUpdate();
            operatorRow = operatorMapper.update(operator);

            //这里应该将operator form填写的roles数据单独保存到operator-role表格
            List<Role> roles = operator.getRoles();
            OperatorRole util = null;
            if(roles != null && !roles.isEmpty()){
                for (Role role : roles) {
                    util = new OperatorRole(operator,role);
                    OperatorRole  operatorRole = operatorMapper.getOperatorRole(util);
                    if(operatorRole == null){
                        util.preInsert();
                        int i = operatorMapper.insertOperatorRole(util);
                    }else {
                        util.preUpdate();
                        int i = operatorMapper.updateOperatorRole(util);
                    }
                }
            }
        }
        return operatorRow;
    }

    public int register(Operator operator) {
        int save;
        Operator operatorByName = findOperatorByName(operator);
        if (operatorByName != null) {
            save = 0;
            return save;
        } else {
            //获取盐值
            String splicing = AutoId.getSplicing(16);
            Md5Hash md5Hash = new Md5Hash(operator.getPassword(), splicing, 1024);
            operator.setSalt(splicing);
            operator.setPassword(md5Hash.toHex());
            save = super.save(operator);
            return save;
        }
    }

    public Operator findOperatorByName(Operator operator) {
        return operatorMapper.getOperatorByName(operator);
    }

    public Operator findRolesByOperator(Operator operator) {
        Operator rolesByOperator = operatorMapper.getRolesByOperator(operator);
        return rolesByOperator;
    }

    @Override
    public int deleteByPhysics(Operator entity) {
        int i = super.deleteByPhysics(entity);
        String operatorId = entity.getId();
        OperatorRole operatorRole = new OperatorRole();
        operatorRole.setOperator(entity);
        operatorMapper.deleteOperatorRole(operatorRole);
        return i;
    }
}
