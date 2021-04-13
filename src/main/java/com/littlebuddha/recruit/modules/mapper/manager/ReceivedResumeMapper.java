package com.littlebuddha.recruit.modules.mapper.manager;


import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import com.littlebuddha.recruit.modules.entity.manager.ReceivedResume;
import org.apache.ibatis.annotations.Mapper;

/**
 *申请职位信息信息mapper层
 */
@Mapper
public interface ReceivedResumeMapper extends BaseMapper<ReceivedResume> {

    int modifyStatus(ReceivedResume receivedResume);
}
