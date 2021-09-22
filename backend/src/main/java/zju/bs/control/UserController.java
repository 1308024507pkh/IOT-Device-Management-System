package zju.bs.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zju.bs.domain.*;
import zju.bs.service.UserService;
import zju.bs.token.JwtUtil;

import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode1 register(@RequestBody UserPojo userPojo){
        if(userPojo.getAccount() == null || userPojo.getAccount().trim().equals("")){
            return new ReturnCode1(201, "用户名不能为空！");
        }
        if(!userPojo.getAccount().matches("^[^0-9][\\w_]{5,9}$")){
            return new ReturnCode1(201, "用户名格式不合规！");
        }
        if(userPojo.getPassword() == null || userPojo.getPassword().trim().equals("")){
            return new ReturnCode1(201, "密码不能为空！");
        }
        if(!userPojo.getPassword().matches("^[\\w_]{6,20}$")){
            return new ReturnCode1(201, "密码格式不合规！");
        }
        if(userPojo.getEmail() == null || userPojo.getEmail().trim().equals("")){
            return new ReturnCode1(201, "邮件地址不能为空！");
        }
        if(!userPojo.getEmail().matches("\\w+@\\w+(\\.\\w+)+")){
            return new ReturnCode1(201, "邮件地址不合规！");
        }
        int ret = userService.register(userPojo);
        if(ret == -2){
            return new ReturnCode1(202, "该用户名已注册！");
        }
        else if(ret == -1){
            return new ReturnCode1(203, "该邮箱地址已注册！");
        }
        else if(ret == 0){
            return new ReturnCode1(204, "注册失败！");
        }
        else{
            return new ReturnCode1(200, "注册成功！");
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ReturnCode2 login(@RequestBody UserPojo userPojo){
        if(userPojo.getAccount() == null || userPojo.getAccount().trim().equals("")){
            return new ReturnCode2(201, "账户名或邮件地址不能为空！", "");
        }
        if(userPojo.getPassword() == null || userPojo.getPassword().trim().equals("")){
            return new ReturnCode2(201, "密码不能为空！", "");
        }
        if(userPojo.getAccount().matches("\\w+@\\w+(\\.\\w+)+")){
            userPojo.setEmail(userPojo.getAccount());
            userPojo.setAccount("");
            if(userService.loginValidateByEmail(userPojo) == false)
                return new ReturnCode2(202, "用户名或邮件地址不存在！", "");
            UserPojo up = userService.loginByEmail(userPojo);
            if(up == null)  return new ReturnCode2(202, "密码错误！", "");
            else {
                String token = JwtUtil.sign(up.getAccount(), up.getEmail());
                return new ReturnCode2(200, "登录成功", token);
            }
        }
        else{
            if(userService.loginValidateByAccount(userPojo) == false)
                return new ReturnCode2(202, "用户名或邮件地址不存在！", "");
            UserPojo up = userService.loginByAccount(userPojo);
            if(up == null)  return new ReturnCode2(202, "密码错误！", "");
            else {
                String token = JwtUtil.sign(up.getAccount(), up.getEmail());
                return new ReturnCode2(200, "登录成功", token);
            }
        }
    }

    @RequestMapping("/test1")
    @ResponseBody
    public UserPojo[] test(){
        return userService.testService();
    }


}
