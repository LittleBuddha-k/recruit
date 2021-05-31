package com.littlebuddha.recruit.modules.mapper.data;


import com.littlebuddha.recruit.modules.base.mapper.BaseMapper;
import com.littlebuddha.recruit.modules.entity.data.Medicine;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import org.apache.ibatis.annotations.Mapper;

/**
 *药品mapper层
 */
@Mapper
public interface MedicineMapper extends BaseMapper<Medicine> {

}
