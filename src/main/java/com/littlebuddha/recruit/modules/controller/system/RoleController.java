/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.system.Role;
import com.littlebuddha.recruit.modules.service.system.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping(value = "/system/role")
public class RoleController extends BaseController {

    Result result = null;

    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public String list(Role role, Model model){
        model.addAttribute("role",role);
        List<Role> list = roleService.findList(role);
        model.addAttribute("list",list);
        return "modules/system/role";
    }

    @ResponseBody
    @PostMapping("/data")
    public Result<List> data(Role role){
        List<Role> data = roleService.findList(role);
        result = new Result("200",data);
        return result;
    }

    @GetMapping("/form/{mode}")
    public String form(@PathVariable(name = "mode")String mode, Role role,Model model){
        model.addAttribute("role",role);
        model.addAttribute("mode",mode);
        return "modules/system/roleForm";
    }

    @ResponseBody
    @PostMapping("/save")
    public Result save(Role role){
        int save = roleService.save(role);
        if (save > 0){
            return new Result("200","保存成功");
        }else {
            return new Result("306","未知错误，保存失败");
        }
    }

    /**
     * 物理删除
     * @return
     */
    @ResponseBody
    @PostMapping("/deleteByPhysics")
    public Result deleteByPhysics(Role role){
        int delete =  roleService.deleteByPhysics(role);
        if (delete > 0){
            return new Result("200","删除成功");
        }else {
            return new Result("306","未知错误，删除失败");
        }
    }
}
