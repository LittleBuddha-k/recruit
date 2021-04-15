/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.littlebuddha.recruit.modules.controller.manager;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import com.littlebuddha.recruit.modules.service.manager.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/manager/company")
public class CompanyController extends BaseController {

    private final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @ModelAttribute
    public Company get(@RequestParam(required = false) String id) {
        Company company = null;
        if (StringUtils.isNotBlank(id)) {
            company = companyService.get(id);
        }
        if (company == null) {
            company = new Company();
        }
        return company;
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
    public String list(Company company, Model model, HttpSession session) {
        model.addAttribute("company", company);
        return "modules/manager/company";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public Map data(Company Company) {
        PageInfo<Company> page = companyService.findPage(new Page<Company>(), Company);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/allData")
    public List<Company> allData(Company company) {
        return companyService.findAllList(company);
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
    public String form(@PathVariable(name = "mode") String mode, Company company, Model model) {
        model.addAttribute("company", company);
        if ("add".equals(mode) || "edit".equals(mode) || "view".equals(mode)) {
            return "modules/manager/companyForm";
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
    public Result save(Company company) {
        int save = companyService.save(company);
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
            Company company = companyService.get(s);
            if (company == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = companyService.deleteByLogic(company);
        }
        return new Result("200", "数据清除成功");
    }

    @ResponseBody
    @PostMapping("/deleteByPhysics")
    public Result deleteByPhysics(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Company company = companyService.get(s);
            if (company == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = companyService.deleteByPhysics(company);
        }
        return new Result("200", "数据清除成功");
    }

    @GetMapping("/recoveryList")
    public String recoveryList(Company company,Model model){
        model.addAttribute("company",company);
        return "modules/recovery/companyRecovery";
    }

    @ResponseBody
    @PostMapping("/recoveryData")
    public Map recoveryData(Company company,Model model){
        model.addAttribute("company",company);
        PageInfo<Company> page = companyService.findRecoveryPage(new Page<Company>(), company);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/recovery")
    public Result recovery(Company company){
        int recovery = companyService.recovery(company);
        if(recovery > 0){
            return new Result("200", "数据已恢复");
        }else {
            return new Result("322", "未知错误，数据恢复失败");
        }
    }
}
