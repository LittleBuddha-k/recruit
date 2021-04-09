/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.littlebuddha.recruit.modules.controller.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.manager.ReceivedResume;
import com.littlebuddha.recruit.modules.service.manager.ReceivedResumeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 公司信息控制层
 */
@Controller
@RequestMapping(value = "/manager/receivedResume")
public class ReceivedResumeController extends BaseController {

    @Autowired
    private ReceivedResumeService receivedResumeService;

    @ModelAttribute
    public ReceivedResume get(@RequestParam(required = false) String id) {
        ReceivedResume receivedResume = null;
        if (StringUtils.isNotBlank(id)) {
            receivedResume = receivedResumeService.get(id);
        }
        if (receivedResume == null) {
            receivedResume = new ReceivedResume();
        }
        return receivedResume;
    }

    /**
     * 返回用户列表
     *
     * @param
     * @param model
     * @param session
     * @return
     */
    //@RequiresPermissions("manager/operator/list")
    @GetMapping("/list")
    public String list(ReceivedResume receivedResume, Model model, HttpSession session) {
        model.addAttribute("receivedResume", receivedResume);
        return "modules/manager/receivedResume";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(ReceivedResume receivedResume) {
        PageInfo<ReceivedResume> page = receivedResumeService.findPage(new Page<ReceivedResume>(), receivedResume);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/allData")
    public List<ReceivedResume> allData(ReceivedResume receivedResume) {
        return receivedResumeService.findAllList(receivedResume);
    }

    /**
     * 返回表单
     *
     * @param mode
     * @param
     * @param model
     * @return
     */
    @GetMapping("/form/{mode}")
    public String form(@PathVariable(name = "mode") String mode, ReceivedResume receivedResume, Model model) {
        model.addAttribute("receivedResume", receivedResume);
        if ("add".equals(mode) || "edit".equals(mode) || "view".equals(mode)) {
            return "modules/manager/receivedResumeForm";
        }
        return "";
    }

    /**
     * 数据保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(ReceivedResume receivedResume) {
        int save = receivedResumeService.save(receivedResume);
        if (save > 0) {
            return new Result("200", "保存成功");
        } else {
            return new Result("310", "未知错误！保存失败");
        }
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result delete(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            ReceivedResume receivedResume = receivedResumeService.get(s);
            if (receivedResume == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = receivedResumeService.deleteByPhysics(receivedResume);
        }
        return new Result("200", "数据清除成功");
    }
}
