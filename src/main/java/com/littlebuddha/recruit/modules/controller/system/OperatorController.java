package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    //@RequiresPermissions("system/operator/list")
    @RequestMapping("/list")
    public String list(Operator operator, Model model){
        model.addAttribute("operator",operator);
        return "modules/system/operator";
    }
}
