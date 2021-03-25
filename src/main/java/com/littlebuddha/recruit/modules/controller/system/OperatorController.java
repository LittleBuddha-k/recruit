package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/system/operator")
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    //@RequiresPermissions("system/operator/list")
    @GetMapping("/list")
    public String list(Operator operator, Model model, HttpSession session){
        Operator currentUser = (Operator) session.getAttribute("currentUser");
        model.addAttribute("operator",operator);
        return "modules/system/operator";
    }

    /**
     * 返回数据
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Result data(Operator operator){
        List<Operator> list = operatorService.findList(operator);
        return new Result("200",list);
    }
}
