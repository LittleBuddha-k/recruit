package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/operator")
public class OperatorController extends BaseController {

    @Autowired
    private OperatorService operatorService;

    /**
     * 返回用户列表
     * @param operator
     * @param model
     * @param session
     * @return
     */
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
    public Map data(Operator operator){
        List<Operator> list = operatorService.findList(operator);
        return getBootstrapData(list);
    }

    /**
     * 返回表单
     * @param mode
     * @param operator
     * @param model
     * @return
     */
    @GetMapping("/form/{mode}")
    public String form(@PathVariable(name = "mode")String mode,Operator operator,Model model){
        model.addAttribute("operator",operator);
        if("add".equals(mode) || "edit".equals(mode) || "view".equals(mode)){
            return "modules/system/operatorForm";
        }
        return "";
    }

    /**
     * 数据保存
     * @param operator
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(Operator operator){

        return null;
    }
}
