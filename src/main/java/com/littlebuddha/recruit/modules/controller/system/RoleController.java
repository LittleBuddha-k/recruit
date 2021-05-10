/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.littlebuddha.recruit.modules.controller.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.entity.system.RoleMenu;
import com.littlebuddha.recruit.modules.service.system.MenuService;
import com.littlebuddha.recruit.modules.service.system.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping(value = "/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @ModelAttribute
    public Role get(@RequestParam(required = false) String id) {
        Role role = null;
        if (StringUtils.isNotBlank(id)) {
            role = roleService.get(id);
        }
        if (role == null) {
            role = new Role();
        }
        return role;
    }

    /**
     * 返回用户列表
     *
     * @param
     * @param model
     * @param session
     * @return
     */
    //@RequiresPermissions("system/operator/list")
    @GetMapping(value = {"/","/list"})
    public String list(Role role, Model model, HttpSession session) {
        model.addAttribute("role", role);
        return "modules/system/role";
    }

    /**
     * 返回权限选择菜单树形目录
     * @param
     * @return
     */
    @GetMapping("/permissionPage")
    public String permissionPage(String id,Menu menu, Model model){
        Role role = roleService.get(new Role(id));
        model.addAttribute("menu",menu);
        model.addAttribute("role",role);
        RoleMenu roleMenu = new RoleMenu(role, menu);
        model.addAttribute("roleMenu",roleMenu);
        return "modules/system/permissionPage";
    }

    @ResponseBody
    @PostMapping("/addPermission")
    public Result addPermission(String ids){
        Result result = null;
        if (StringUtils.isNotBlank(ids)){
            int row = roleService.addPermission(ids);
            if (row > 0){
                result = new Result("200","权限设置成功");
            }else {
                result = new Result("450","未知错误，权限设置失败！！！");
            }
        }
        return result;
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(Role role) {
        PageInfo<Role> page = roleService.findPage(new Page<Role>(), role);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/allData")
    public List<Role> allData(Role role){
        return roleService.findAllList(role);
    }

    /**
     * 返回表单
     *
     * @param mode
     * @param
     * @param model
     * @return
     */
    @GetMapping("/form/{mode}")
    public String form(@PathVariable(name = "mode") String mode, Role role, Model model) {
        model.addAttribute("role", role);
        if ("add".equals(mode) || "edit".equals(mode) || "view".equals(mode)) {
            return "modules/system/roleForm";
        }
        return "";
    }

    /**
     * 数据保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(Role role) {
        int save = roleService.save(role);
        if (save > 0) {
            return new Result("200", "保存成功");
        } else {
            return new Result("310", "未知错误！保存失败");
        }
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result delete(String ids) {
        System.out.println("ids:" + ids);
        String[] split = ids.split(",");
        for (String s : split) {
            Role role = roleService.get(s);
            if (role == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = roleService.deleteByLogic(role);
        }
        return new Result("200", "数据清除成功");
    }

    @ResponseBody
    @PostMapping("/deleteByPhysics")
    public Result deleteByPhysics(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Role role = roleService.get(s);
            if (role == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = roleService.deleteByPhysics(role);
        }
        return new Result("200", "数据清除成功");
    }

    @GetMapping("/recoveryList")
    public String recoveryList(Role role,Model model){
        model.addAttribute("role",role);
        return "modules/recovery/roleRecovery";
    }

    @ResponseBody
    @PostMapping("/recoveryData")
    public Map recoveryData(Role role,Model model){
        model.addAttribute("role",role);
        PageInfo<Role> page = roleService.findRecoveryPage(new Page<Role>(), role);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/recovery")
    public Result recovery(Role role){
        int recovery = roleService.recovery(role);
        if(recovery > 0){
            return new Result("200", "数据已恢复");
        }else {
            return new Result("322", "未知错误，数据恢复失败");
        }
    }
}
