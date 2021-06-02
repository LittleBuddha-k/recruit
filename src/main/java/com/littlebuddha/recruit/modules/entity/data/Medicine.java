package com.littlebuddha.recruit.modules.entity.data;

import com.littlebuddha.recruit.modules.base.entity.DataEntity;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 药品实体类
 */
public class Medicine extends DataEntity<Medicine> {

    private String name;            //药名
    private String function;        //功能
    private Double originalPrice;   //原价
    private Double discountPrice;   //折后价
    private String picture;         //图片
    private String rebate;          //折扣
    private String label;           //标签
    private String prescriptionType;//处方类型
    private String basicUnit;       //基本单位
    private String brand;           //品牌
    private String approvalNumber;  //批准文号
    private String placeOfOrigin;   //产地
    private String component;       //成分

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRebate() {
        return rebate;
    }

    public void setRebate(String rebate) {
        this.rebate = rebate;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(String prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public String getBasicUnit() {
        return basicUnit;
    }

    public void setBasicUnit(String basicUnit) {
        this.basicUnit = basicUnit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
}
