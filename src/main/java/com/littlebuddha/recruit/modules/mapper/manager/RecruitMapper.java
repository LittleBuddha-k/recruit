package com.littlebuddha.recruit.modules.mapper.manager;


import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import org.apache.ibatis.annotations.Mapper;

/**
 *招聘信息mapper层
 */
@Mapper
public interface RecruitMapper extends BaseMapper<Recruit> {

}
