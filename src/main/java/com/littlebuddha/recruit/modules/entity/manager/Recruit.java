package com.littlebuddha.recruit.modules.entity.manager;

import com.littlebuddha.recruit.modules.base.entity.DataEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 招聘信息实体类
 */
public class Recruit extends DataEntity<Recruit> {

    private String position;           //招聘职位
    private String salary;             //薪资
    private String workingYears;       //工作年限
    private String qualifications;     //学历
    private String employCount;        //招聘人数
    private Date publicDate;           //发布日期
    private String type;//类型
    private String address;//招聘公司的工作地址

    private Company company;           //公司
    private String companyId;          //公司id
    private String companyName;        //公司名称
    private String headquartersAddress;//地址
    private String phone;              //公司联系电话
    private String jobInformation;     //工作信息

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getEmployCount() {
        return employCount;
    }

    public void setEmployCount(String employCount) {
        this.employCount = employCount;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCompanyId() {
        if(company != null && StringUtils.isNotBlank(company.getId())){
            this.companyId = company.getId();
        }
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        if(company != null && StringUtils.isNotBlank(company.getCompanyName())){
            this.companyName = company.getCompanyName();
        }
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getHeadquartersAddress() {
        if(company != null && StringUtils.isNotBlank(company.getHeadquartersAddress())){
            this.headquartersAddress = company.getHeadquartersAddress();
        }
        return headquartersAddress;
    }

    public void setHeadquartersAddress(String headquartersAddress) {
        this.headquartersAddress = headquartersAddress;
    }

    public String getPhone() {
        if(company != null && StringUtils.isNotBlank(company.getPhone())){
            this.phone = company.getPhone();
        }
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobInformation() {
        return jobInformation;
    }

    public void setJobInformation(String jobInformation) {
        this.jobInformation = jobInformation;
    }
}
