package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import com.littlebuddha.recruit.modules.service.system.RecruitService;
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
public class PortalController {

    @Autowired
    private RecruitService recruitService;

    /**
     * 门户页面
     * @return
     */
    @GetMapping("/list")
    public String portal(Recruit recruit, HttpSession session, Model model){
        Operator currentUser = UserUtils.getCurrentUser();
        session.setAttribute("currentUser",currentUser);
        List<Recruit> recruitList = recruitService.findList(recruit);
        model.addAttribute("recruitList",recruitList);
        return "modules/system/portal";
    }
}
