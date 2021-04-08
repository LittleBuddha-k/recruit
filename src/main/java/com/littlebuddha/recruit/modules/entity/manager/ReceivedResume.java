package com.littlebuddha.recruit.modules.entity.manager;

import com.littlebuddha.recruit.modules.base.entity.DataEntity;
import com.littlebuddha.recruit.modules.entity.system.Operator;

/**
 * 申请职位信息
 */
public class ReceivedResume extends DataEntity<ReceivedResume> {

    private Operator operator;//谁
    private Resume resume;//递交的简历
    private String receivedTime;//接收到的时间
    private Company company;//给谁--给哪家公司的
    private Recruit recruit;//申请的哪个职位

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
}
