package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.manager.RecruitService;
import com.littlebuddha.recruit.modules.service.system.MenuService;
import com.littlebuddha.recruit.modules.service.system.PortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 门户页controller
 */
@Controller
@RequestMapping("/portal")
public class PortalController extends BaseController {

    @Autowired
    private RecruitService recruitService;

    @Autowired
    private PortalService portalService;

    @Autowired
    private MenuService menuService;

    /**
     * 门户页面
     *
     * @return
     */
    @GetMapping("/list")
    public String portal(Recruit recruit, HttpSession session, Model model) {
        Operator currentUser = UserUtils.getCurrentUser();
        session.setAttribute("currentUser", currentUser);
        //是否显示主页导航条的搜索框
        model.addAttribute("showNavSearch", true);
        //访问主页时查询菜单列表，存入session
        //List<Menu> menus = portalService.findMenus(currentUser);
        //查询一级菜单-----父级id= topMenu的id
        List<Menu> levelOneMenus = menuService.findLevelOneMenus(currentUser);
        model.addAttribute("levelOneMenus", levelOneMenus);
        model.addAttribute("portal", recruit);
        return "modules/system/portal";
    }
}
