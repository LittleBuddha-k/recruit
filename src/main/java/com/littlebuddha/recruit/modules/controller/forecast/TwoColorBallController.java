package com.littlebuddha.recruit.modules.controller.forecast;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.forecast.TwoColorBall;
import com.littlebuddha.recruit.modules.service.forecast.TwoColorBallService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/forecast/twoColorBall")
public class TwoColorBallController extends BaseController {

    @Autowired
    private TwoColorBallService twoColorBallService;

    @ModelAttribute
    public TwoColorBall get(@RequestParam(required = false) String id) {
        TwoColorBall twoColorBall = null;
        if (StringUtils.isNotBlank(id)) {
            twoColorBall = twoColorBallService.get(id);
        }
        if (twoColorBall == null) {
            twoColorBall = new TwoColorBall();
        }
        return twoColorBall;
    }

    /**
     * 返回用户列表
     *
     * @param twoColorBall
     * @param model
     * @param session
     * @return
     */
    //@RequiresPermissions("system/twoColorBall/list")
    @GetMapping(value = {"/", "/list"})
    public String list(TwoColorBall twoColorBall, Model model, HttpSession session) {
        model.addAttribute("twoColorBall", twoColorBall);
        return "modules/system/twoColorBall";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(TwoColorBall twoColorBall) {
        PageInfo<TwoColorBall> page = twoColorBallService.findPage(new Page<TwoColorBall>(), twoColorBall);
        return getBootstrapData(page);
    }

    /**
     * 返回表单
     *
     * @param mode
     * @param twoColorBall
     * @param model
     * @return
     */
    @GetMapping("/form/{mode}")
    public String form(@PathVariable(name = "mode") String mode, TwoColorBall twoColorBall, Model model) {
        model.addAttribute("twoColorBall", twoColorBall);
        return "modules/system/twoColorBallForm";
    }

    /**
     * 个人设置
     *
     * @param mode
     * @param twoColorBall
     * @param model
     * @return
     */
    @GetMapping("/setting")
    public String setting(TwoColorBall twoColorBall, Model model) {
        model.addAttribute("twoColorBall", twoColorBall);
        return "modules/system/twoColorBallSetting";
    }

    /**
     * 数据保存
     *
     * @param twoColorBall
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(TwoColorBall twoColorBall) {
        int save = twoColorBallService.save(twoColorBall);
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
            TwoColorBall twoColorBall = twoColorBallService.get(s);
            if (twoColorBall == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = twoColorBallService.deleteByLogic(twoColorBall);
        }
        return new Result("200", "数据清除成功");
    }

    @ResponseBody
    @PostMapping("/deleteByPhysics")
    public Result deleteByPhysics(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            TwoColorBall twoColorBall = twoColorBallService.get(s);
            if (twoColorBall == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = twoColorBallService.deleteByPhysics(twoColorBall);
        }
        return new Result("200", "数据清除成功");
    }

    @GetMapping("/recoveryList")
    public String recoveryList(TwoColorBall twoColorBall, Model model) {
        model.addAttribute("twoColorBall", twoColorBall);
        return "modules/recovery/twoColorBallRecovery";
    }

    @ResponseBody
    @PostMapping("/recoveryData")
    public Map recoveryData(TwoColorBall twoColorBall, Model model) {
        model.addAttribute("twoColorBall", twoColorBall);
        PageInfo<TwoColorBall> page = twoColorBallService.findRecoveryPage(new Page<TwoColorBall>(), twoColorBall);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/recovery")
    public Result recovery(TwoColorBall twoColorBall) {
        int recovery = twoColorBallService.recovery(twoColorBall);
        if (recovery > 0) {
            return new Result("200", "数据已恢复");
        } else {
            return new Result("322", "未知错误，数据恢复失败");
        }
    }
}
