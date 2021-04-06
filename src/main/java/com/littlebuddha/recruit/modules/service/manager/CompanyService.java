package com.littlebuddha.recruit.modules.service.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import com.littlebuddha.recruit.modules.mapper.manager.CompanyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService extends CrudService<Company, CompanyMapper> {

    @Override
    public int save(Company company) {
        return super.save(company);
    }

    @Override
    public Company get(Company entity) {
        return super.get(entity);
    }

    @Override
    public List<Company> findList(Company entity) {
        return super.findList(entity);
    }

    @Override
    public PageInfo<Company> findPage(Page<Company> page, Company entity) {
        return super.findPage(page, entity);
    }

    @Override
    public int deleteByPhysics(Company entity) {
        return super.deleteByPhysics(entity);
    }
}
