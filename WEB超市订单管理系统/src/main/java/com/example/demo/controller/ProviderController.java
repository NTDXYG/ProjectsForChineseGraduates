package com.example.demo.controller;

import com.example.demo.bean.Bill;
import com.example.demo.bean.Provider;
import com.example.demo.bean.ResBody;
import com.example.demo.bean.User;
import com.example.demo.service.ProviderService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class ProviderController {
    @Autowired
    ProviderService service;

    @GetMapping("ajax/getAllProviders")
    @RequiresAuthentication
    public ResBody ajaxgetAllProviders() {
        ResBody resBody = new ResBody();
        List<Provider> list= service.findAllProviders();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/getAllProviders")
    @RequiresAuthentication
    public ResBody getAllProviders(@RequestParam int page,
                               @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Provider> list= service.findAllProviders(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addProvider")
    @RequiresRoles("admin")
    public ResBody addProvider(@RequestBody Provider provider, HttpSession session) {
        ResBody resBody = new ResBody();
        User user = (User) session.getAttribute("user");
        provider.setCreatedBy(user.getId());
        provider.setCreationDate(new Date());
        int i = service.addProvider(provider);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateProvider")
    @RequiresRoles("admin")
    public ResBody updateProvider(@RequestBody Provider provider, HttpSession session) {
        ResBody resBody = new ResBody();
        User user = (User) session.getAttribute("user");
        Provider p = service.getProviderById(provider.getId());
        provider.setCreatedBy(p.getCreatedBy());
        provider.setCreationDate(p.getCreationDate());
        provider.setModifyBy(user.getId());
        provider.setModifyDate(new Date());
        int i = service.updateProvider(provider);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delProvider")
    @RequiresRoles("admin")
    public ResBody delProvider(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delProvider(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findProvider")
    @RequiresAuthentication
    public ResBody findProvider(@RequestParam int page,
                            @RequestParam int limit,
                            @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Provider> list= service.findProvider(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
