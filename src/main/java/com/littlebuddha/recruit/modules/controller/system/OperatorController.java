package com.littlebuddha.recruit.modules.controller.system;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/system/operator")
public class OperatorController extends BaseController {

    @Autowired
    private OperatorService operatorService;

    @ModelAttribute
    public Operator get(@RequestParam(required = false) String id) {
        Operator operator = null;
        if (StringUtils.isNotBlank(id)) {
            operator = operatorService.get(id);
        }
        if (operator == null) {
            operator = new Operator();
        }
        return operator;
    }

    /**
     * 返回用户列表
     *
     * @param operator
     * @param model
     * @param session
     * @return
     */
    //@RequiresPermissions("system/operator/list")
    @GetMapping("/list")
    public String list(Operator operator, Model model, HttpSession session) {
        model.addAttribute("operator", operator);
        return "modules/system/operator";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(Operator operator) {
        PageInfo<Operator> page = operatorService.findPage(new Page<Operator>(), operator);
        return getBootstrapData(page);
    }

    /**
     * 返回表单
     *
     * @param mode
     * @param operator
     * @param model
     * @return
     */
    @GetMapping("/form/{mode}")
    public String form(@PathVariable(name = "mode") String mode, Operator operator, Model model) {
        model.addAttribute("operator", operator);
        if ("add".equals(mode) || "edit".equals(mode) || "view".equals(mode)) {
            return "modules/system/operatorForm";
        }
        return "";
    }

    /**
     * 数据保存
     *
     * @param operator
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(Operator operator) {
        int save = operatorService.save(operator);
        if (save > 0) {
            return new Result("200", "保存成功");
        } else {
            return new Result("310", "未知错误！保存失败");
        }
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result delete(String ids) {
        System.out.println("ids:" + ids);
        String[] split = ids.split(",");
        for (String s : split) {
            Operator operator = operatorService.get(s);
            if (operator == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = operatorService.deleteByPhysics(operator);
        }
        return new Result("200", "数据清除成功");
    }
}
