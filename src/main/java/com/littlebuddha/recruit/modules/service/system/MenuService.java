package com.littlebuddha.recruit.modules.service.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.mapper.system.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单业务层
 */
@Service
public class MenuService extends CrudService<Menu, MenuMapper> {

    @Override
    public int save(Menu menu) {
        return super.save(menu);
    }

    @Override
    public int deleteByPhysics(Menu menu) {
        return super.deleteByPhysics(menu);
    }

    @Override
    public Menu get(Menu menu) {
        return super.get(menu);
    }

    @Override
    public List<Menu> findList(Menu menu) {
        return super.findList(menu);
    }

    @Override
    public List<Menu> findAllList(Menu menu) {
        return super.findAllList(menu);
    }

    @Override
    public PageInfo<Menu> findPage(Page<Menu> page, Menu menu) {
        return super.findPage(page, menu);
    }

    @Override
    public int deleteByLogic(Menu menu) {
        return super.deleteByLogic(menu);
    }

    @Override
    public int recovery(Menu menu) {
        int recovery = super.recovery(menu);
        return recovery;
    }
}
