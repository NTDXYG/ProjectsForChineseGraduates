package com.example.demo.controller;

import com.example.demo.bean.Admin;
import com.example.demo.bean.ResBody;
import com.example.demo.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminService service;
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @PostMapping("/admin/loginByPassword")
    public ResBody loginByPassword(@RequestBody Map<String, Object> params,
                                   HttpSession session) {
        ResBody resBody = new ResBody();
        String email = params.get("email").toString();
        String password = params.get("password").toString();
        Admin admin = service.findAdmin(email,password);
        if (admin == null){
            resBody.setCode(500);
            resBody.setMsg("登录失败，请重新登录");
        }else {
            session.setAttribute("admin",admin);
            LOG.info(admin.toString());
            resBody.setCode(200);
            resBody.setMsg("登录成功");
        }
        return resBody;
    }

    @PostMapping("/admin/updatePass")
    public ResBody updatePass(@RequestBody Map<String, Object> params,
                              HttpSession session) {
        ResBody resBody = new ResBody();
        String newPsw = params.get("newPsw").toString();
        Admin admin = (Admin) session.getAttribute("admin");
        admin.setPassword(newPsw);
        int i = service.updatePass(admin.getId(),newPsw);
        if (i != 1){
            resBody.setCode(500);
            resBody.setMsg("修改失败，后台出错");
        }else {
            session.setAttribute("admin",admin);
            LOG.info(admin.toString());
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }
        return resBody;
    }
}
