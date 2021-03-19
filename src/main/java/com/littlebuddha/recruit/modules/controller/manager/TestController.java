package com.littlebuddha.recruit.modules.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/testData")
    public String testData(){
        return "modules/manager/data";
    }
}
