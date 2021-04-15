package com.littlebuddha.recruit.modules.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.littlebuddha.recruit.common.utils.AutoId;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Date;

/**
 * 数据实体继承类
 */
public abstract class DataEntity<E> extends BaseEntity<E>{

    private boolean isNewData = false;//此项数据是否为新数据

    protected Operator createBy;
    protected Date createDate;
    protected Operator updateBy;
    protected Date updateDate;

    protected String remarks;
    protected String delFlag;       //0---正常  1---删除

    public DataEntity() {
        super();
        this.delFlag = DEL_FLAG_NORMAL;
    }

    public DataEntity(String id) {
        super(id);
    }

    @JsonIgnore
    public boolean getIsNewData() {
        return isNewData || StringUtils.isBlank(getId());
    }

    /**
     * 保存数据前的操作，手动调用
     */
    public void preInsert(){
        Subject subject = SecurityUtils.getSubject();
        Operator entity = (Operator)subject.getPrincipal();
        if(!this.isNewData){
            setId(AutoId.getAutoId());
        }
        Date now = new Date();
        this.createBy = entity;
        this.createDate = now;
        this.updateBy = entity;
        this.updateDate = this.createDate;
    }

    /**
     * 更新数据前的操作，手动调用
     */
    public void preUpdate(){
        Subject subject = SecurityUtils.getSubject();
        Operator entity = (Operator)subject.getPrincipal();
        this.updateBy = entity;
        this.updateDate = new Date();
    }

    public Operator getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Operator createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Operator getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Operator updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        updateDate = updateDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
