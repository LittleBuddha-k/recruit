package com.littlebuddha.recruit.modules.entity.manager;

import com.littlebuddha.recruit.modules.base.entity.DataEntity;
import com.littlebuddha.recruit.modules.entity.system.Operator;

/**
 * 简历实体类
 */
public class Resume extends DataEntity<Resume> {

    private String name;//姓名
    private String age;//年龄
    private String sex;//性别
    private String address;//地址
    private String phone;//电话
    private String email;//邮箱
    private String education;//教育经历
    private String skill;//技能
    private String workExperience;//工作经验
    private String picture;//头像
    private String introduction;//自我介绍

    private Operator operator;//用户外键

    public Resume() {
    }

    public Resume(Operator operator) {
        this.operator = operator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
