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
        ReceivedResume entity = receivedResumeService.get(receivedResume);
        model.addAttribute("receivedResume", entity);
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

    /**
     * 修改投递----状态
     * @param receivedResume
     * @return
     */
    @ResponseBody
    @PostMapping("/status/{action}")
    public Result status(@PathVariable(name = "action")String action, ReceivedResume receivedResume){
        int row = receivedResumeService.modifyStatus(receivedResume);
        if (row > 0){
            return new Result("200",action + "成功");
        }else {
            return new Result("321","未知错误");
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
            int i = receivedResumeService.deleteByLogic(receivedResume);
        }
        return new Result("200", "数据清除成功");
    }

    @GetMapping("/recoveryList")
    public String recoveryList(ReceivedResume receivedResume,Model model){
        model.addAttribute("receivedResume",receivedResume);
        return "modules/manager/receivedResumeRecovery";
    }

    @ResponseBody
    @PostMapping("/recoveryData")
    public Map recoveryData(ReceivedResume receivedResume,Model model){
        model.addAttribute("receivedResume",receivedResume);
        PageInfo<ReceivedResume> page = receivedResumeService.findRecoveryPage(new Page<ReceivedResume>(), receivedResume);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/recovery")
    public Result recovery(ReceivedResume receivedResume){
        int recovery = receivedResumeService.recovery(receivedResume);
        if(recovery > 0){
            return new Result("200", "数据已恢复");
        }else {
            return new Result("322", "未知错误，数据恢复失败");
        }
    }
}
