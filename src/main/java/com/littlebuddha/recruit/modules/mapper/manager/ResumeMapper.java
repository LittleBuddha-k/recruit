package com.littlebuddha.recruit.modules.mapper.manager;


import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.manager.Resume;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import org.apache.ibatis.annotations.Mapper;

/**
 * 简历信息mapper层
 */
@Mapper
public interface ResumeMapper extends BaseMapper<Resume> {

    Resume getResumeByOperator(Resume resume);

    Resume getResumeByCurrentOperator(Resume resume);
}
