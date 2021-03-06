package com.littlebuddha.recruit.modules.controller.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import com.littlebuddha.recruit.modules.entity.manager.ReceivedResume;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import com.littlebuddha.recruit.modules.service.manager.CompanyService;
import com.littlebuddha.recruit.modules.service.manager.ReceivedResumeService;
import com.littlebuddha.recruit.modules.service.manager.RecruitService;
import com.littlebuddha.recruit.modules.service.manager.ResumeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager/recruit")
public class RecruitController extends BaseController {

    @Autowired
    private RecruitService recruitService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private ReceivedResumeService receivedResumeService;

    @ModelAttribute
    public Recruit get(@RequestParam(required = false) String id) {
        Recruit recruit = null;
        if (StringUtils.isNotBlank(id)) {
            recruit = recruitService.get(id);
        }
        if (recruit == null) {
            recruit = new Recruit();
        }
        return recruit;
    }

    /**
     * 返回用户列表
     *
     * @param
     * @param model
     * @param session
     * @return
     */
    //@RequiresPermissions("manager/Recruit/list")
    @GetMapping(value = {"/","/list"})
    public String list(Recruit recruit, Model model, HttpSession session) {
        model.addAttribute("recruit", recruit);
        return "modules/manager/recruit";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(Recruit recruit) {
        PageInfo<Recruit> page = recruitService.findPage(new Page<Recruit>(), recruit);
        return getBootstrapData(page);
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
    public String form(@PathVariable(name = "mode") String mode, Recruit recruit, Model model) {
        List<Company> allCompanyList = companyService.findAllList(new Company());
        model.addAttribute("mode", mode);
        model.addAttribute("allCompanyList", allCompanyList);
        model.addAttribute("recruit", recruit);
        if ("add".equals(mode) || "edit".equals(mode) || "view".equals(mode)) {
            return "modules/manager/recruitForm";
        }
        return "";
    }

    /**
     * 供用户查看招聘信息的详情页
     * 只有超级管理员或者用户可以进入此页面
     * @param
     * @param
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(Recruit recruit, Model model) {
        model.addAttribute("recruit", recruit);
        return "modules/manager/recruitDetail";
    }

    /**
     * 用户投递简历的处理接口
     * 需要将投递申请的用户信息及其投递的岗位信息返回给招聘方
     * @return
     */
    @ResponseBody
    @PostMapping("/applyRecruit")
    public Result applyRecruit(Recruit recruit){
        ReceivedResume receivedResume = new ReceivedResume(recruit);
        int save = receivedResumeService.save(receivedResume);
        if (save > 0){
            return new Result("200","投递简历成功");
        }else {
            return new Result("310","投递简历失败");
        }
    }

    /**
     * 数据保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(Recruit recruit) {
        int save = recruitService.save(recruit);
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
            Recruit recruit = recruitService.get(s);
            if (recruit == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = recruitService.deleteByLogic(recruit);
        }
        return new Result("200", "数据清除成功");
    }

    @ResponseBody
    @PostMapping("/deleteByPhysics")
    public Result deleteByPhysics(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Recruit recruit = recruitService.get(s);
            if (recruit == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = recruitService.deleteByPhysics(recruit);
        }
        return new Result("200", "数据清除成功");
    }

    @GetMapping("/recoveryList")
    public String recoveryList(Recruit recruit,Model model){
        model.addAttribute("recruit",recruit);
        return "modules/recovery/recruitRecovery";
    }

    @ResponseBody
    @PostMapping("/recoveryData")
    public Map recoveryData(Recruit recruit,Model model){
        model.addAttribute("recruit",recruit);
        PageInfo<Recruit> page = recruitService.findRecoveryPage(new Page<Recruit>(), recruit);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/recovery")
    public Result recovery(Recruit recruit){
        int recovery = recruitService.recovery(recruit);
        if(recovery > 0){
            return new Result("200", "数据已恢复");
        }else {
            return new Result("322", "未知错误，数据恢复失败");
        }
    }
}
