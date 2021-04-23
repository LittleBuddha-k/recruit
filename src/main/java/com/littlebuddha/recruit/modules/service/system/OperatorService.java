package com.littlebuddha.recruit.modules.service.system;

import com.littlebuddha.recruit.common.utils.AutoId;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.service.CrudService;
import com.littlebuddha.recruit.modules.entity.system.*;
import com.littlebuddha.recruit.modules.mapper.system.MenuMapper;
import com.littlebuddha.recruit.modules.mapper.system.OperatorMapper;
import com.littlebuddha.recruit.modules.mapper.system.RoleMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperatorService extends CrudService<Operator, OperatorMapper> {

    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int save(Operator operator) {
        int operatorRow;
        //这一步保存的是operator数据
        if (operator.getIsNewData()) {
            System.out.println("执行新增操作");
            operator.preInsert();
            //获取盐值
            String splicing = AutoId.getSplicing(16);
            Md5Hash md5Hash = new Md5Hash(operator.getPassword(), splicing, 1024);
            operator.setSalt(splicing);
            operator.setPassword(md5Hash.toHex());
            operatorRow = operatorMapper.insert(operator);

            //这里应该将operator form填写的roles数据单独保存到operator-role表格
            List<Role> roles = operator.getRoles();
            OperatorRole util = null;
            if (roles != null && !roles.isEmpty()) {
                for (Role role : roles) {
                    util = new OperatorRole(operator, role);
                    util.preInsert();
                    int i = operatorMapper.insertOperatorRole(util);
                }
            }
        } else {
            System.out.println("执行更新操作");
            operator.preUpdate();
            operatorRow = operatorMapper.update(operator);

            //这里应该将operator form填写的roles数据单独保存到operator-role表格
            List<Role> roles = operator.getRoles();
            OperatorRole util = null;
            if (roles != null && !roles.isEmpty()) {
                for (Role role : roles) {
                    if (StringUtils.isNotBlank(role.getId())) {
                        util = new OperatorRole(operator, role);
                        OperatorRole operatorRole = operatorMapper.getOperatorRole(util);
                        if (operatorRole == null) {
                            util.preInsert();
                            int i = operatorMapper.insertOperatorRole(util);
                        } else {
                            util.preUpdate();
                            int i = operatorMapper.updateOperatorRole(util);
                        }
                    }
                }
            }
        }
        return operatorRow;
    }

    public int register(Operator operator) {
        int save;
        Operator operatorByName = findOperatorByName(operator);
        if (operatorByName != null) {
            save = 0;
            return save;
        } else {
            //获取盐值
            String splicing = AutoId.getSplicing(16);
            Md5Hash md5Hash = new Md5Hash(operator.getPassword(), splicing, 1024);
            operator.setSalt(splicing);
            operator.setPassword(md5Hash.toHex());
            save = super.save(operator);
            return save;
        }
    }

    public Operator findOperatorByName(Operator operator) {
        return operatorMapper.getOperatorByName(operator);
    }

    public Operator findRolesByOperator(Operator operator) {
        Operator rolesByOperator = operatorMapper.getRolesByOperator(operator);
        return rolesByOperator;
    }

    public List<Menu> findMenusByRole(Role role) {
        List<Menu> menus = operatorMapper.getMenusByRole(role);
        return menus;
    }

    @Override
    public int deleteByPhysics(Operator entity) {
        int i = super.deleteByPhysics(entity);
        String operatorId = entity.getId();
        OperatorRole operatorRole = new OperatorRole();
        operatorRole.setOperator(entity);
        operatorMapper.deleteOperatorRole(operatorRole);
        return i;
    }

    @Override
    public int deleteByLogic(Operator entity) {
        return super.deleteByLogic(entity);
    }

    @Override
    public int recovery(Operator entity) {
        int recovery = super.recovery(entity);
        return recovery;
    }

    public List<RoleMenu> findRoleMenusByRole(Role role) {
        List<RoleMenu> roleMenus = operatorMapper.getRoleMenusByRole(new RoleMenu(role));
        for (RoleMenu roleMenu : roleMenus) {
            if (roleMenu.getRole() != null && StringUtils.isNotBlank(roleMenu.getRole().getId())) {

            }
            if (roleMenu.getMenu() != null && StringUtils.isNotBlank(roleMenu.getMenu().getId())) {
                Menu menu = menuMapper.get(roleMenu.getMenu());
                roleMenu.setMenu(menu);
            }
        }
        return roleMenus;
    }


    public List<Menu> getMenusByOperator() {
        Operator currentUser = UserUtils.getCurrentUser();
        Operator rolesByOperator = findRolesByOperator(currentUser);
        List<Role> roles = rolesByOperator.getRoles();
        List<Menu> menuData = new ArrayList<>();
        List<RoleMenu> roleMenusByRole = null;
        //1.查询当前用户的所有角色菜单信息
        for (Role role : roles) {
            roleMenusByRole = menuMapper.getRoleMenusByRole(new RoleMenu(role));
        }
        //2.将所有1得到的menu放入menuList
        for (RoleMenu roleMenu : roleMenusByRole) {
            if (roleMenu != null && StringUtils.isNotBlank(roleMenu.getMenu().getId())){
                Menu menu = menuMapper.get(roleMenu.getMenu());
                menuData.add(menu);
            }
        }
        return menuData;
    }
}
