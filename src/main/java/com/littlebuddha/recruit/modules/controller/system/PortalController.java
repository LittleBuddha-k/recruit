package com.littlebuddha.recruit.modules.controller.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import com.littlebuddha.recruit.modules.service.manager.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 门户页controller
 */
@Controller
@RequestMapping("/portal")
public class PortalController extends BaseController {

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
        //是否显示主页导航条的搜索框
        model.addAttribute("showNavSearch", true);

        model.addAttribute("portal", recruit);
        return "modules/system/portal";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(Recruit recruit) {
        PageInfo<Recruit> page = recruitService.findPage(new Page<Recruit>(), recruit);
        return getBootstrapData(page);
    }
}
