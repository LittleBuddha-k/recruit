package com.littlebuddha.recruit.modules.entity.system;


import com.littlebuddha.recruit.modules.base.entity.DataEntity;

import java.util.List;

/**
 *菜单实体类，一个才对对应具有一种权限
 */
public class Menu extends DataEntity<Menu> {

    private Menu parent;	// 父级菜单
    private String parentIds; // 所有父级编号
    private List<Menu> children;	// 子级菜单列表
    private String name; 	// 名称
    private String href; 	// 链接
    private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）
    private String icon; 	// 图标
    private Integer sort; 	// 排序
    private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）
    private String type; //按钮类型
    private String permission; // 权限标识
    private boolean hasChildren;

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }
}