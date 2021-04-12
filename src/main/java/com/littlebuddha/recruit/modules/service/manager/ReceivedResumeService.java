package com.littlebuddha.recruit.modules.service.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.DateUtils;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import com.littlebuddha.recruit.modules.entity.manager.ReceivedResume;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import com.littlebuddha.recruit.modules.entity.manager.Resume;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.mapper.manager.CompanyMapper;
import com.littlebuddha.recruit.modules.mapper.manager.ReceivedResumeMapper;
import com.littlebuddha.recruit.modules.mapper.manager.RecruitMapper;
import com.littlebuddha.recruit.modules.mapper.manager.ResumeMapper;
import com.littlebuddha.recruit.modules.mapper.system.OperatorMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 申请职位信息业务层
 */
@Service
public class ReceivedResumeService extends CrudService<ReceivedResume, ReceivedResumeMapper> {

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private ReceivedResumeMapper receivedResumeMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private RecruitMapper recruitMapper;

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
        ReceivedResume entity = super.get(receivedResume);
        attributeAssignment(entity);
        System.out.println(entity);
        return entity;
    }

    @Override
    public List<ReceivedResume> findList(ReceivedResume receivedResume) {
        List<ReceivedResume> list = super.findList(receivedResume);
        for (ReceivedResume resume : list) {
            attributeAssignment(resume);
        }
        return list;
    }

    @Override
    public PageInfo<ReceivedResume> findPage(Page<ReceivedResume> page, ReceivedResume receivedResume) {
        PageInfo<ReceivedResume> receivedResumePageInfo = super.findPage(page, receivedResume);
        List<ReceivedResume> list = receivedResumePageInfo.getList();
        List<ReceivedResume> result = new ArrayList<>();
        for (ReceivedResume resume : list) {
            attributeAssignment(resume);
        }
        return receivedResumePageInfo;
    }

    private void attributeAssignment(ReceivedResume resume) {
        if (resume.getOperator() != null){
            Operator operator = operatorMapper.get(resume.getOperator());
            if (operator == null){
                receivedResumeMapper.deleteByPhysics(resume);
            }else {
                resume.setOperator(operator);
            }
        }
        if (resume.getResume() != null){
            Resume resume1 = resumeMapper.get(resume.getResume());
            if (resume1 == null){
                receivedResumeMapper.deleteByPhysics(resume);
            }else {
                resume.setResume(resume1);
            }
        }
        if (resume.getCompany() != null){
            Company company = companyMapper.get(resume.getCompany());
            if (company == null){
                receivedResumeMapper.deleteByPhysics(resume);
            }else {
                resume.setCompany(company);
            }
        }
        if (resume.getRecruit() != null){
            Recruit recruit = recruitMapper.get(resume.getRecruit());
            if (recruit == null){
                receivedResumeMapper.deleteByPhysics(resume);
            }else {
                resume.setRecruit(recruit);
            }
        }
    }

    @Override
    public int deleteByPhysics(ReceivedResume receivedResume) {
        return super.deleteByPhysics(receivedResume);
    }
}
