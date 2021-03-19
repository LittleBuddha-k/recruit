package com.littlebuddha.recruit.modules.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system/operator")
public class OperatorController {

    @RequiresPermissions("system/operator/list")
    @RequestMapping("/list")
    public String list(){
     return null;
    }
}
