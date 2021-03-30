package com.littlebuddha.recruit.modules.service.system;

import com.littlebuddha.recruit.common.utils.AutoId;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Operator;
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
        System.out.println("id:"+operator.getId());
        int row;
        if (operator.getIsNewData()){
            System.out.println("执行新增操作");
            operator.preInsert();
            //获取盐值
            String splicing = AutoId.getSplicing(16);
            Md5Hash md5Hash = new Md5Hash(operator.getPassword(), splicing, 1024);
            operator.setSalt(splicing);
            operator.setPassword(md5Hash.toHex());
            row = operatorMapper.insert(operator);
        }else{
            System.out.println("执行更新操作");
            operator.preUpdate();
            row = operatorMapper.update(operator);
        }
        return row;
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
}
