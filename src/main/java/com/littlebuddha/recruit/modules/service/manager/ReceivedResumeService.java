package com.littlebuddha.recruit.modules.service.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.manager.ReceivedResume;
import com.littlebuddha.recruit.modules.mapper.manager.ReceivedResumeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请职位信息业务层
 */
@Service
public class ReceivedResumeService extends CrudService<ReceivedResume, ReceivedResumeMapper> {

    @Override
    public int save(ReceivedResume receivedResume) {
        return super.save(receivedResume);
    }

    @Override
    public ReceivedResume get(ReceivedResume receivedResume) {
        return super.get(receivedResume);
    }

    @Override
    public List<ReceivedResume> findList(ReceivedResume receivedResume) {
        return super.findList(receivedResume);
    }

    @Override
    public PageInfo<ReceivedResume> findPage(Page<ReceivedResume> page, ReceivedResume receivedResume) {
        return super.findPage(page, receivedResume);
    }

    @Override
    public int deleteByPhysics(ReceivedResume receivedResume) {
        return super.deleteByPhysics(receivedResume);
    }
}
