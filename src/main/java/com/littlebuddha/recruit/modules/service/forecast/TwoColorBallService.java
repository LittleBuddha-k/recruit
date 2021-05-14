package com.littlebuddha.recruit.modules.service.forecast;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.forecast.TwoColorBall;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.mapper.forecast.TwoColorBallMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单业务层
 */
@Service
public class TwoColorBallService extends CrudService<TwoColorBall, TwoColorBallMapper> {

    @Override
    public int save(TwoColorBall entity) {
        return super.save(entity);
    }

    @Override
    public TwoColorBall get(TwoColorBall entity) {
        return super.get(entity);
    }

    @Override
    public List<TwoColorBall> findList(TwoColorBall entity) {
        return super.findList(entity);
    }

    @Override
    public List<TwoColorBall> findAllList(TwoColorBall entity) {
        return super.findAllList(entity);
    }

    @Override
    public PageInfo<TwoColorBall> findPage(Page<TwoColorBall> page, TwoColorBall entity) {
        return super.findPage(page, entity);
    }

    @Override
    public int deleteByPhysics(TwoColorBall entity) {
        return super.deleteByPhysics(entity);
    }

    @Override
    public int deleteByLogic(TwoColorBall entity) {
        return super.deleteByLogic(entity);
    }

    @Override
    public int recovery(TwoColorBall entity) {
        return super.recovery(entity);
    }

    @Override
    public PageInfo<TwoColorBall> findRecoveryPage(Page<TwoColorBall> page, TwoColorBall entity) {
        return super.findRecoveryPage(page, entity);
    }
}
