package com.littlebuddha.recruit.modules.base.entity;

import com.github.pagehelper.Page;
import com.littlebuddha.recruit.modules.entity.system.Operator;

import java.io.Serializable;

/**
 *基础实体继承类
 */
public abstract class BaseEntity<E> implements Serializable {

    private String id;
    protected Operator currentUser;
    //private String page;

    protected boolean isNewRecord = false;

    /**
     * 当前实体分页对象
     * @return
     */
    private Page<E> page;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Operator getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Operator currentUser) {
        this.currentUser = currentUser;
    }

    public Page<E> getPage() {
        return page;
    }

    public void setPage(Page<E> page) {
        this.page = page;
    }

    /**
     * 插入之前执行方法，子类实现
     */
    public abstract void preInsert();

    /**
     * 更新之前执行方法，子类实现
     */
    public abstract void preUpdate();

    /**
     * 删除标记（0：正常；1：删除；2：审核；）
     */
    public static final String DEL_FLAG_NORMAL = "0";
    public static final String DEL_FLAG_DELETE = "1";
    public static final String DEL_FLAG_AUDIT = "2";
}
