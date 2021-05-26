package com.littlebuddha.recruit.modules.service.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import com.littlebuddha.recruit.modules.mapper.manager.CompanyMapper;
import com.littlebuddha.recruit.modules.mapper.manager.RecruitMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RecruitService extends CrudService<Recruit, RecruitMapper> {

    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public int save(Recruit entity) {
        judgeCompany(entity, companyMapper);
        return super.save(entity);
    }

    @Override
    public int deleteByPhysics(Recruit entity) {
        judgeCompany(entity, companyMapper);
        return super.deleteByPhysics(entity);
    }

    @Override
    public Recruit get(Recruit entity) {
        judgeCompany(entity, companyMapper);
        return super.get(entity);
    }

    @Override
    public Recruit get(String id) {
        Recruit recruit = super.get(id);
        judgeCompany(recruit,companyMapper);
        return recruit;
    }

    @Override
    public List<Recruit> findList(Recruit entity) {
        judgeCompany(entity, companyMapper);
        return super.findList(entity);
    }

    @Override
    public List<Recruit> findAllList(Recruit entity) {
        judgeCompany(entity, companyMapper);
        return super.findAllList(entity);
    }

    @Override
    public PageInfo<Recruit> findPage(Page<Recruit> page, Recruit entity) {
        judgeCompany(entity, companyMapper);
        return super.findPage(page, entity);
    }

    private static void judgeCompany(Recruit entity, CompanyMapper companyMapper) {
        if(entity.getCompany() != null && StringUtils.isNotBlank(entity.getCompany().getId())){
            Company company = companyMapper.get(entity.getCompany().getId());
            entity.setCompany(company);
        }
    }

    @Override
    public int deleteByLogic(Recruit entity) {
        return super.deleteByLogic(entity);
    }

    @Override
    public int recovery(Recruit entity) {
        int recovery = super.recovery(entity);
        return recovery;
    }
}
