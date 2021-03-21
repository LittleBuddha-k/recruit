package com.littlebuddha.recruit.modules.entity.system;


import com.littlebuddha.recruit.modules.base.entity.DataEntity;

/**
 *
 */
public class Role extends DataEntity<Role> {

    private String name;    // 角色名称
    private String englishName;//英文名称

    public Role() {
    }

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
}
