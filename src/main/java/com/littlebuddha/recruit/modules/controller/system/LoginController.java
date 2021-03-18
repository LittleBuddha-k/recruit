package com.littlebuddha.recruit.modules.controller.system;

import com.littlebuddha.recruit.common.utils.Result;
import com.littlebuddha.recruit.modules.base.controller.BaseController;
import com.littlebuddha.recruit.modules.entity.system.Operator;
import com.littlebuddha.recruit.modules.service.system.OperatorService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录控制器
 */
@Controller
@RequestMapping("/system")
public class LoginController extends BaseController {

    @Autowired
    private OperatorService operatorService;

    @GetMapping("/loginPage")
    public String loginPage() {
        return "modules/system/loginPage";
    }

    /**
     * 处理登录数据请求
     * @param username
     * @param password
     */
    @ResponseBody
    @PostMapping("/login")
    public Result login(String username, String password){
        Result result = null;
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(new UsernamePasswordToken(username,password));
            result = new Result("200","登录成功");
            return result;
        }catch (UnknownAccountException unknownAccountException){
            result = new Result("301","用户名错误");
            return result;
        }catch (IncorrectCredentialsException incorrectCredentialsException){
            result = new Result("302","密码错误");
            return result;
        }
    }

    @GetMapping("/registerPage")
    public String registerPage() {
        return "modules/system/registerPage";
    }

    @ResponseBody
    @PostMapping("/register")
    public Result register(Operator operator){
        Result result = null;
        int save = operatorService.register(operator);
        if(save != 0){
            return new Result("200","注册成功");
        }else {
            return new Result("304","注册失败");
        }
    }

    @ResponseBody
    @PostMapping("/logout")
    public Result logout(){
        Result result = null;
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        result = new Result("303","退出成功");
        return result;
    }
}
