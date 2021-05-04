package com.example.demo.controller;

import com.example.demo.bean.ResBody;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService service;
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value="用户登录", notes="根据用户名，密码进行登录")
    @PostMapping("/user/loginByPassword")
    public ResBody loginByPassword(@RequestBody Map<String, Object> params,
                                   HttpSession session) {
        ResBody resBody = new ResBody();
        String code = params.get("code").toString();
        String password = params.get("password").toString();
        User user = service.getUser(code);
        if (password.equals(user.getPassword())){
            session.setAttribute("user",user);
            session.setAttribute("token", JWTUtil.sign(code, password));
            resBody.setCode(200);
            resBody.setMsg("登录成功");
        }else {
            resBody.setCode(500);
            resBody.setMsg("登录失败，请重新登录");
        }
        return resBody;
    }

    @ApiOperation(value="修改密码")
    @PostMapping("/user/updatePass")
    @RequiresAuthentication
    public ResBody updatePass(@RequestBody Map<String, Object> params,
                              HttpSession session) {
        ResBody resBody = new ResBody();
        String newPsw = params.get("newPsw").toString();
        User user = (User) session.getAttribute("user");
        user.setPassword(newPsw);
        int i = service.updatePass(user.getId(),newPsw);
        if (i != 1){
            resBody.setCode(500);
            resBody.setMsg("修改失败，后台出错");
        }else {
            session.setAttribute("user",user);
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }
        return resBody;
    }

    @GetMapping("/ajax/getUser")
    @RequiresAuthentication
    public ResBody getUser(@RequestParam int id) {
        ResBody resBody = new ResBody();
        User user = service.findById(id);
        resBody.setCode(200);
        resBody.setMsg(user.getUsername());
        return resBody;
    }

    @GetMapping("/api/getAllUsers")
    @RequiresAuthentication
    public ResBody getAllUsers(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<User> list= service.findAllUsers(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addUser")
    @RequiresRoles("admin")
    public ResBody addUser(@RequestBody User user) {
        ResBody resBody = new ResBody();
        int i = service.addUser(user);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateUser")
    @RequiresRoles("admin")
    public ResBody updateUser(@RequestBody User user) {
        ResBody resBody = new ResBody();
        int i = service.updateUser(user);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delUser")
    @RequiresRoles("admin")
    public ResBody delUser(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delUser(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findUser")
    @RequiresAuthentication
    public ResBody findUser(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String role) {
        ResBody resBody = new ResBody();
        int count = service.getCount(role);
        List<User> list= new ArrayList<>();
        if (role.isEmpty()){
            list= service.findAllUsers(page, limit);
            count = service.getCount();
        }else {
            list= service.findUser(page, limit,role);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/resetPass")
    @RequiresRoles("admin")
    public ResBody resetPass(@RequestParam int id) {
        ResBody resBody = new ResBody();
        service.resetPass(id);
        resBody.setCode(0);
        return resBody;
    }
}
