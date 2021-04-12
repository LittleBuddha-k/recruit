package com.littlebuddha.recruit.modules.entity.manager;

import com.littlebuddha.recruit.modules.base.entity.DataEntity;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import org.apache.commons.lang3.StringUtils;

/**
 * 申请职位信息
 */
public class ReceivedResume extends DataEntity<ReceivedResume> {

    private Operator operator;//谁
    private Resume resume;//递交的简历
    private String receivedTime;//接收到的时间
    private Company company;//给谁--给哪家公司的
    private Recruit recruit;//申请的哪个职位

    private String status="待读";//消息传递状态

    public ReceivedResume() {
    }

    public ReceivedResume(Recruit recruit) {
        this.recruit = recruit;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Company getCompany() {
        /*if(recruit != null && recruit.getCompany() != null ){
            company = recruit.getCompany();
        }*/
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Recruit getRecruit() {
        return recruit;
    }

    public void setRecruit(Recruit recruit) {
        this.recruit = recruit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
