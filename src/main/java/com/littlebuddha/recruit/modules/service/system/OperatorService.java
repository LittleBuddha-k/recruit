package com.littlebuddha.recruit.modules.service.system;

import com.littlebuddha.recruit.common.utils.AutoId;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.mapper.system.OperatorMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

@Service
public class OperatorService extends CrudService<Operator, OperatorMapper> {

    @Override
    public int save(Operator entity) {
        return super.save(entity);
    }

    public int register(Operator operator) {
        //获取盐值
        String splicing = AutoId.getSplicing(16);
        Md5Hash md5Hash = new Md5Hash(operator.getPassword(),splicing,1024);
        operator.setSalt(splicing);
        operator.setPassword(md5Hash.toString());
        int save = super.save(operator);
        return save;
    }
}
