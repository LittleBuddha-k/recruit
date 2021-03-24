package com.littlebuddha.recruit.modules.entity.system;

import com.littlebuddha.recruit.modules.base.entity.DataEntity;

public class Company extends DataEntity<Company> {

    private String name;
    private String phone;
    private String address;
    private String introduction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
