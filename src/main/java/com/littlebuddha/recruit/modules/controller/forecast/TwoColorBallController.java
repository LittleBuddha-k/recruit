package com.littlebuddha.recruit.modules.controller.forecast;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.littlebuddha.recruit.common.utils.DateUtils;
import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.common.utils.TreeResult;
import com.littlebuddha.recruit.common.utils.excel.ExportExcel;
import com.littlebuddha.recruit.common.utils.excel.ImportExcel;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.forecast.TwoColorBall;
import com.littlebuddha.recruit.modules.service.forecast.TwoColorBallService;
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
        return "modules/forecast/twoColorBall";
    }

    /**
     * 返回数据
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/data")
    public TreeResult data(TwoColorBall twoColorBall) {
        PageInfo<TwoColorBall> page = twoColorBallService.findPage(new Page<TwoColorBall>(), twoColorBall);
        return getLayUiData(page);
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
        return "modules/forecast/twoColorBallForm";
    }

    /**
     * 个人设置
     *
     * @param
     * @param twoColorBall
     * @param model
     * @return
     */
    @GetMapping("/setting")
    public String setting(TwoColorBall twoColorBall, Model model) {
        model.addAttribute("twoColorBall", twoColorBall);
        return "modules/forecast/twoColorBallSetting";
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
    @GetMapping("/importTemplate")
    public Result importTemplate(HttpServletResponse response){
        Result result = new Result();
        try {
            String fileName = "双色球模板.xlsx";
            List<TwoColorBall> list = Lists.newArrayList();
            new ExportExcel("双色球数据", TwoColorBall.class, 1).setDataList(list).write(response, fileName).dispose();
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
            ImportExcel ei = new ImportExcel(file, 1, 0);
            List<TwoColorBall> list = ei.getDataList(TwoColorBall.class);
            for (TwoColorBall mayApplyCost : list){
                try{
                    twoColorBallService.save(mayApplyCost);
                    successNum++;
                }catch (Exception ex) {
                    failureNum++;
                }
            }
            if (failureNum>0){
                failureMsg.insert(0, "，失败 "+failureNum+" 条双色球记录。");
            }
            result.setMsg( "已成功导入 "+successNum+" 条双色球记录"+failureMsg);
        } catch (Exception e) {
            result.setCode("200");
            result.setMsg("导入双色球失败！失败信息："+e.getMessage());
        }
        return result;
    }

    @ResponseBody
    @GetMapping("/exportFile")
    public Result exportFile(TwoColorBall twoColorBall, HttpServletRequest request, HttpServletResponse response){
        Result result = new Result();
        try {
            String fileName = "双色球"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            PageInfo<TwoColorBall> page = twoColorBallService.findPage(new Page<TwoColorBall>(), twoColorBall);
            new ExportExcel("双色球", TwoColorBall.class).setDataList(page.getList()).write(response, fileName).dispose();
            result.setCode("200");
            result.setMsg("导出成功！");
            return result;
        } catch (Exception e) {
            result.setCode("433");
            result.setMsg("导出双色球记录失败！失败信息："+e.getMessage());
        }
        return result;
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
