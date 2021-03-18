package com.littlebuddha.recruit.modules.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.littlebuddha.recruit.common.utils.AutoId;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import org.apache.commons.lang3.StringUtils;

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

    @JsonIgnore
    public boolean getIsNewData() {
        return isNewData || StringUtils.isBlank(getId());
    }

    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(){
        if (!this.isNewRecord){
            setId(AutoId.getAutoId());
        }
        //Operator operator = UserUtils.getCurrentUser();
        //if (StringUtils.isNotBlank(operator.getId())){
        //    this.updateBy = operator;
        //    this.createBy = operator;
        //}
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(){
        //User user = UserUtils.getUser();
        //if (StringUtils.isNotBlank(user.getId())){
        //    this.updateBy = user;
        //}
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
