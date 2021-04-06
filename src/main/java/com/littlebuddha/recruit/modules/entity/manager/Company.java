package com.littlebuddha.recruit.modules.entity.manager;

import com.littlebuddha.recruit.modules.base.entity.DataEntity;

import java.util.Date;

/**
 * 公司信息
 */
public class Company extends DataEntity<Company> {

    private String legalPerson;//法人代表
    private String chineseName;//中文名称
    private String englishName;//英文名称
    private String companyName;//公司名称
    private String headquartersAddress;//总部地址
    private Date establishDate;//成立日期
    private String phone;//联系电话
    private String introduction;//介绍
    private String ranging;//经营范围
    private String nature;//公司性质
    private String registeredCapital;//注册资本

    private String scale;//规模---人数
    private String pictures;//公司图片宣传
    private String video;//公司视频宣传

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHeadquartersAddress() {
        return headquartersAddress;
    }

    public void setHeadquartersAddress(String headquartersAddress) {
        this.headquartersAddress = headquartersAddress;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRanging() {
        return ranging;
    }

    public void setRanging(String ranging) {
        this.ranging = ranging;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
