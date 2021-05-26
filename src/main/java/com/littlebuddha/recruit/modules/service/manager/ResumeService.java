package com.littlebuddha.recruit.modules.service.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.manager.Resume;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.mapper.manager.ResumeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ResumeService extends CrudService<Resume, ResumeMapper> {

    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    public int save(Resume entity) {
        judgeOperator(entity);
        return super.save(entity);
    }

    @Override
    public int deleteByPhysics(Resume entity) {
        judgeOperator(entity);
        return super.deleteByPhysics(entity);
    }

    @Override
    public Resume get(Resume entity) {
        judgeOperator(entity);
        return super.get(entity);
    }

    @Override
    public Resume get(String id) {
        Resume resume = super.get(id);
        judgeOperator(resume);
        return resume;
    }

    @Override
    public List<Resume> findList(Resume entity) {
        judgeOperator(entity);
        return super.findList(entity);
    }

    @Override
    public List<Resume> findAllList(Resume entity) {
        judgeOperator(entity);
        return super.findAllList(entity);
    }

    @Override
    public PageInfo<Resume> findPage(Page<Resume> page, Resume entity) {
        judgeOperator(entity);
        return super.findPage(page, entity);
    }

    private static void judgeOperator(Resume entity) {
        Operator currentUser = UserUtils.getCurrentUser();
        entity.setOperator(currentUser);
    }

    /**
     *
     * @param resume
     * @param operator 点击我的简历时会自动传递当前用户
     * @return
     */
    public Resume getResumeByOperator(Resume resume,Operator operator) {
        resume.setOperator(operator);
        Resume result = resumeMapper.getResumeByOperator(resume);
        return result;
    }

    public Resume getResumeByCurrentOperator(Resume resume,Operator operator) {
        resume.setOperator(operator);
        Resume result = resumeMapper.getResumeByCurrentOperator(resume);
        return result;
    }

    @Override
    public int deleteByLogic(Resume entity) {
        return super.deleteByLogic(entity);
    }

    @Override
    public int recovery(Resume entity) {
        int recovery = super.recovery(entity);
        return recovery;
    }
}
