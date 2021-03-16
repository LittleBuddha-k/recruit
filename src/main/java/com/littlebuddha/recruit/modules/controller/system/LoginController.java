package com.littlebuddha.recruit.modules.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录控制器
 */
@Controller
@RequestMapping("/system")
public class LoginController {

    @GetMapping("/loginPage")
    public String loginPage(){
        return "modules/system/loginPage";
    }

    @GetMapping("/registerPage")
    public String registerPage(){
        return "modules/system/registerPage";
    }
}
