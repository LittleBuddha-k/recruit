package com.littlebuddha.recruit.modules.controller.data;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.DateUtils;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.common.utils.excel.ExcelExport;
import com.littlebuddha.recruit.common.utils.excel.ExcelImport;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.data.Medicine;
import com.littlebuddha.recruit.modules.entity.manager.Company;
import com.littlebuddha.recruit.modules.entity.manager.ReceivedResume;
import com.littlebuddha.recruit.modules.entity.manager.Recruit;
import com.littlebuddha.recruit.modules.service.data.MedicineService;
import com.littlebuddha.recruit.modules.service.manager.CompanyService;
import com.littlebuddha.recruit.modules.service.manager.ReceivedResumeService;
import com.littlebuddha.recruit.modules.service.manager.RecruitService;
import com.littlebuddha.recruit.modules.service.manager.ResumeService;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/data/medicine")
public class MedicineController extends BaseController {

    @Autowired
    private MedicineService medicineService;

    @ModelAttribute
    public Medicine get(@RequestParam(required = false) String id) {
        Medicine medicine = null;
        if (StringUtils.isNotBlank(id)) {
            medicine = medicineService.get(id);
        }
        if (medicine == null) {
            medicine = new Medicine();
        }
        return medicine;
    }

    /**
     * 返回药品列表
     *
     * @param
     * @param model
     * @param session
     * @return
     */
    //@RequiresPermissions("system/Medicine/list")
    @GetMapping(value = {"/", "/list"})
    public String list(Medicine medicine, Model model, HttpSession session) {
        model.addAttribute("medicine", medicine);
        return "modules/data/medicine";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/data")
    public Map data(Medicine medicine) {
        PageInfo<Medicine> page = medicineService.findPage(new Page<Medicine>(), medicine);
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
    public String form(@PathVariable(name = "mode") String mode, Medicine medicine, Model model) {
        model.addAttribute("medicine", medicine);
        return "modules/data/medicineForm";
    }

    /**
     * 数据保存
     *
     * @param
     * @return
     */
    @ResponseBody
    @PostMapping("/save")
    public Result save(Medicine medicine) {
        int save = medicineService.save(medicine);
        if (save > 0) {
            return new Result("200", "保存成功");
        } else {
            return new Result("310", "未知错误！保存失败");
        }
    }

    @ResponseBody
    @GetMapping("/importTemplate")
    public Result importTemplate(HttpServletResponse response){
        Result result = new Result();
        try {
            String fileName = "药品模板.xlsx";
            List<Medicine> list = Lists.newArrayList();
            new ExcelExport("药品数据", Medicine.class, 1).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            result.setCode("200");
            result.setMsg( "导入模板下载失败！失败信息："+e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @PostMapping("/importFile")
    public Result importFile(@RequestParam("file") MultipartFile file, HttpServletResponse response, HttpServletRequest request){
        Result result = new Result();
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ExcelImport ei = new ExcelImport(file, 1, 0);
            List<Medicine> list = ei.getDataList(Medicine.class);
            for (Medicine mayApplyCost : list){
                try{
                    medicineService.save(mayApplyCost);
                    successNum++;
                }catch (Exception ex) {
                    failureNum++;
                }
            }
            if (failureNum>0){
                failureMsg.insert(0, "，失败 "+failureNum+" 条药品记录。");
            }
            result.setMsg( "已成功导入 "+successNum+" 条药品记录"+failureMsg);
        } catch (Exception e) {
            result.setCode("200");
            result.setMsg("导入药品失败！失败信息："+e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @GetMapping("/exportFile")
    public Result exportFile(Medicine medicine, HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            String fileName = "药品"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            PageInfo<Medicine> page = medicineService.findPage(new Page<Medicine>(), medicine);
            new ExcelExport("药品", Medicine.class).setDataList(page.getList()).write(response, fileName).dispose();
            result.setCode("200");
            result.setMsg("导出成功！");
            return result;
        } catch (Exception e) {
            result.setCode("433");
            result.setMsg("导出药品记录失败！失败信息："+e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @PostMapping("/delete")
    public Result delete(String ids) {
        System.out.println("ids:" + ids);
        String[] split = ids.split(",");
        for (String s : split) {
            Medicine medicine = medicineService.get(s);
            if (medicine == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = medicineService.deleteByLogic(medicine);
        }
        return new Result("200", "数据清除成功");
    }

    @ResponseBody
    @PostMapping("/deleteByPhysics")
    public Result deleteByPhysics(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            Medicine medicine = medicineService.get(s);
            if (medicine == null) {
                return new Result("311", "数据不存在,或已被删除，请刷新试试！");
            }
            int i = medicineService.deleteByPhysics(medicine);
        }
        return new Result("200", "数据清除成功");
    }

    @GetMapping("/recoveryList")
    public String recoveryList(Medicine medicine, Model model) {
        model.addAttribute("medicine", medicine);
        return "modules/recovery/medicineRecovery";
    }

    @ResponseBody
    @PostMapping("/recoveryData")
    public Map recoveryData(Medicine medicine, Model model) {
        model.addAttribute("medicine", medicine);
        PageInfo<Medicine> page = medicineService.findRecoveryPage(new Page<Medicine>(), medicine);
        return getBootstrapData(page);
    }

    @ResponseBody
    @PostMapping("/recovery")
    public Result recovery(Medicine medicine) {
        int recovery = medicineService.recovery(medicine);
        if (recovery > 0) {
            return new Result("200", "数据已恢复");
        } else {
            return new Result("322", "未知错误，数据恢复失败");
        }
    }
}
