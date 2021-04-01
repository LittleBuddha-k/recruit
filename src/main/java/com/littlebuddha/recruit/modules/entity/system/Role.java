package com.littlebuddha.recruit.modules.entity.system;


import com.littlebuddha.recruit.modules.base.entity.DataEntity;

import java.util.List;

/**
 *
 */
public class Role extends DataEntity<Role> {

    private String name;    // 角色名称
    private String englishName;//英文名称
    private List<Menu> menus;//一个角色拥有多个菜单

    private Operator operator;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
}
