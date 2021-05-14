package com.littlebuddha.recruit.modules.service.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import com.littlebuddha.recruit.modules.mapper.system.RoleMenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuService extends CrudService<RoleMenu, RoleMenuMapper> {

    @Override
    public RoleMenu get(RoleMenu entity) {
        return super.get(entity);
    }

    @Override
    public List<RoleMenu> findList(RoleMenu entity) {
        return super.findList(entity);
    }

    @Override
    public List<RoleMenu> findAllList(RoleMenu entity) {
        return super.findAllList(entity);
    }

    @Override
    public PageInfo<RoleMenu> findPage(Page<RoleMenu> page, RoleMenu entity) {
        return super.findPage(page, entity);
    }

    @Override
    public int save(RoleMenu entity) {
        return super.save(entity);
    }

    @Override
    public int deleteByPhysics(RoleMenu entity) {
        return super.deleteByPhysics(entity);
    }

    @Override
    public int deleteByLogic(RoleMenu entity) {
        return super.deleteByLogic(entity);
    }

    @Override
    public int recovery(RoleMenu entity) {
        return super.recovery(entity);
    }
}
