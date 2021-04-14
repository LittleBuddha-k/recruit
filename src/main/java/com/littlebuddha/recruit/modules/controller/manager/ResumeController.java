package com.littlebuddha.recruit.modules.controller.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.common.utils.UserUtils;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.manager.Resume;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.manager.ResumeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 简历控制层
 */
@Controller
@RequestMapping("/manager/resume")
public class ResumeController extends BaseController {

    @Autowired
    private ResumeService resumeService;

    @ModelAttribute
    public Resume get(@RequestParam(required = false) String id) {
        Resume resume = null;
        if (StringUtils.isNotBlank(id)) {
            resume = resumeService.get(id);
        }
        if (resume == null) {
            resume = new Resume();
        }
        return resume;
    }

    /**
     * 返回简历列表
     *
     * @param
     * @param model
     * @param session
     * @return
     */
    //@RequiresPermissions("manager/Resume/list")
    @GetMapping("/list")
    public String list(Resume resume, Model model, HttpSession session) {
        model.addAttribute("resume", resume);
        return "modules/manager/resume";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(Resume resume) {
        PageInfo<Resume> page = resumeService.findPage(new Page<Resume>(), resume);
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
    public String form(@PathVariable(name = "mode") String mode, Resume resume, Model model) {
        model.addAttribute("resume", resume);
        if ("add".equals(mode) || "edit".equals(mode) || "view".equals(mode)) {
            return "modules/manager/resumeForm";
        }
        return "";
    }

    /**
     * 供简历查看招聘信息的详情页
     * 只有超级管理员或者简历可以进入此页面
     * @param
     * @param
     * @param model
     * @return
     */
    @GetMapping("/detail")
    public String detail(Resume resume,Model model) {
        Operator currentUser = UserUtils.getCurrentUser();
        Resume result = null;
        if (currentUser != null){
            result = resumeService.getResumeByOperator(resume,currentUser);
        }
        String mode = "view";
        model.addAttribute("mode", mode);
        model.addAttribute("resume", result);
        return "modules/manager/resumeDetail";
    }

    /**
     * 数据保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(Resume resume) {
        int save = resumeService.save(resume);
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
            Resume resume = resumeService.get(s);
            if (resume == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = resumeService.deleteByLogic(resume);
        }
        return new Result("200", "数据清除成功");
    }

    @ResponseBody
    @PostMapping("/recovery")
    public Result recovery(Resume resume){
        int recovery = resumeService.recovery(resume);
        if(recovery > 0){
            return new Result("200", "数据已恢复");
        }else {
            return new Result("322", "未知错误，数据恢复失败");
        }
    }
}
