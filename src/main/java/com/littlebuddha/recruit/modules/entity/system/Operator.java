package com.littlebuddha.recruit.modules.entity.system;

import com.littlebuddha.recruit.modules.base.entity.DataEntity;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import com.littlebuddha.recruit.modules.entity.manager.Resume;

import java.util.List;

/**
 * 登录用户实体类
 */
public class Operator extends DataEntity<Operator> {

    private String username;          //名字
    private String password;      //密码
    private String salt;          //盐值
    private String sex;           //性别
    private String age;           //年龄
    private String address;       //家庭住址
    private String phone;         //个人联系电话
    private Resume resume;        //简历

    private List<Role> roles;            //角色信息

    private Role role;          //角色外键

    private Company company;//当前用户所属公司

    private String rolesId; //工具使用变量，重新修改角色属性时使用

    public Operator() {
    }

    public Operator(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRolesId() {
        return rolesId;
    }

    public void setRolesId(String rolesId) {
        this.rolesId = rolesId;
    }
}
