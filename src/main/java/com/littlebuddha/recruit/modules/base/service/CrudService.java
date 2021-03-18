package com.littlebuddha.recruit.modules.base.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.modules.base.entity.DataEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

/**
 * @author ck
 * @date 2020/7/13 9:23
 */
public abstract class CrudService<E extends DataEntity,M extends com.littlebuddha.recruit.modules.base.mapper.BaseMapper<E>> {

    @Autowired
    private M mapper;

    /**
     * 新增数据业务层
     * 包含了update数据操作
     * @param entity
     * @return
     */
    public int save(E entity){
        int row = 0;
        if (entity.getIsNewData()){
            entity.preInsert();
            row = mapper.insert(entity);
        }else{
            entity.preUpdate();
            row = mapper.update(entity);
        }
        return row;
    }

    /**
     * 物理删除业务层
     * @param entity
     * @return
     */
    public int deleteByPhysics(E entity){
        int row = mapper.deleteByPhysics(entity);
        return row;
    }

    /**
     * 逻辑删除业务层
     * @param entity
     * @return
     */
    public int deleteByLogic(E entity){
        int row = mapper.deleteByLogic(entity);
        return row;
    }

    /**
     * 物理删除全部业务层
     * @param entities
     * @return
     */
    public int deleteAllByPhysics(Collection<E> entities){
        int result = 0;
        for (E entity : entities) {
            int row = mapper.deleteByPhysics(entity);
            result = result + row;
        }
        return result;
    }

    /**
     * 逻辑删除全部业务层
     * @param entities
     * @return
     */
    public int deleteAllByLogic(Collection<E> entities){
        int result = 0;
        for (E entity : entities) {
            int row = mapper.deleteByLogic(entity);
            result = result + row;
        }
        return result;
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public E get(String id){
        return mapper.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public E get(E entity){
        return mapper.get(entity);
    }

    /**
     * 根据条件查询数据列表
     * @param entity
     * @return
     */
    public List<E> findList(E entity){
        return mapper.findList(entity);
    }

    /**
     * 查询所有数据列表
     * @param entity
     * @return
     */
    public List<E> findAllList(E entity){
        return mapper.findAllList(entity);
    }

    /**
     * 单条逻辑删除数据的恢复
     * @param entity
     */
    public void recovery(E entity){
        int row = mapper.recovery(entity);
    }

    /**
     * 多条逻辑删除数据的恢复
     * @param entities
     */
    public void recovery(Collection<E> entities){
        for (E entity : entities) {
            mapper.recovery(entity);
        }
    }
}
