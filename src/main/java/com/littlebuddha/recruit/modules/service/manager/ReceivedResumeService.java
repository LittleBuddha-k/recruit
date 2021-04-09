package com.littlebuddha.recruit.modules.service.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.DateUtils;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.manager.ReceivedResume;
import com.littlebuddha.recruit.modules.entity.manager.Resume;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.mapper.manager.ReceivedResumeMapper;
import com.littlebuddha.recruit.modules.mapper.manager.ResumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 申请职位信息业务层
 */
@Service
public class ReceivedResumeService extends CrudService<ReceivedResume, ReceivedResumeMapper> {

    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    public int save(ReceivedResume receivedResume) {
        //1.投递简历后只需将当前用户的简历传入相应公司的已接受简历数据中
        Operator currentUser = UserUtils.getCurrentUser();
        receivedResume.setOperator(currentUser);
        //2.简历
        Resume resume = new Resume(currentUser);
        Resume resumeByCurrentOperator = resumeMapper.getResumeByCurrentOperator(resume);
        if (resumeByCurrentOperator != null){
            receivedResume.setResume(resumeByCurrentOperator);
        }else {
            //就要抛出异常，提示该用户没有创建简历

        }
        //3.在什么时候
        String receivedTime = DateUtils.getFullDate(new Date());
        receivedResume.setReceivedTime(receivedTime);
        //4.投递给了谁,投递的是什么职位
        receivedResume.setCompany(receivedResume.getRecruit().getCompany());
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
