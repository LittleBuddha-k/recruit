package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import com.littlebuddha.recruit.modules.entity.system.Menu;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.system.Portal;
import com.littlebuddha.recruit.modules.entity.system.utils.LogoInfo;
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
    @GetMapping(value = {"/","/list"})
    public String portal(Recruit recruit, HttpSession session, Model model,LogoInfo logoInfo) {
        Operator currentUser = UserUtils.getCurrentUser();
        session.setAttribute("currentUser", currentUser);
        //是否显示主页导航条的搜索框
        model.addAttribute("showNavSearch", true);
        model.addAttribute("portal", recruit);
        System.out.println(logoInfo);
        return "modules/system/portal";
    }
}
