/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.littlebuddha.recruit.modules.controller.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.system.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 菜单控制层
 */
@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @ModelAttribute
    public Menu get(@RequestParam(required = false) String id) {
        Menu menu = null;
        if (StringUtils.isNotBlank(id)) {
            menu = menuService.get(id);
        }
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
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
    public String list(Menu menu, Model model, HttpSession session) {
        model.addAttribute("menu", menu);
        return "modules/system/menu";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(Menu menu) {
        PageInfo<Menu> page = menuService.findPage(new Page<Menu>(), menu);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/allData")
    public List<Menu> allData(Menu menu) {
        return menuService.findAllList(menu);
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
    public String form(@PathVariable(name = "mode") String mode, Menu menu, Model model) {
        //当查看的菜单为师祖级菜单
        if (menu.getParent() == null || StringUtils.isBlank(menu.getParent().getId())) {
            menu.setParent(menuService.getTopMenu());
        }
        //为其设置parent
        if (menu.getParent() != null && StringUtils.isNotBlank(menu.getParent().getId())) {
            Menu entity = menuService.get(new Menu(menu.getParent().getId()));
            menu.setParent(entity);
        }
        //前端下拉选项数据
        List<Menu> allList = menuService.findAllList(new Menu());
        model.addAttribute("allList", allList);
        model.addAttribute("menu", menu);
        return "modules/system/menuForm";
    }

    /**
     * 数据保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(Menu menu) {
        int save = menuService.save(menu);
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
            Menu menu = menuService.get(s);
            if (menu == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = menuService.deleteByLogic(menu);
        }
        return new Result("200", "数据清除成功");
    }

    @ResponseBody
    @PostMapping("/deleteByPhysics")
    public Result deleteByPhysics(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Menu menu = menuService.get(s);
            if (menu == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = menuService.deleteByPhysics(menu);
        }
        return new Result("200", "数据清除成功");
    }

    @GetMapping("/recoveryList")
    public String recoveryList(Menu menu, Model model) {
        model.addAttribute("menu", menu);
        return "modules/recovery/menuRecovery";
    }

    @ResponseBody
    @PostMapping("/recoveryData")
    public Map recoveryData(Menu menu, Model model) {
        model.addAttribute("menu", menu);
        PageInfo<Menu> page = menuService.findRecoveryPage(new Page<Menu>(), menu);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/recovery")
    public Result recovery(Menu menu) {
        int recovery = menuService.recovery(menu);
        if (recovery > 0) {
            return new Result("200", "数据已恢复");
        } else {
            return new Result("322", "未知错误，数据恢复失败");
        }
    }
}
