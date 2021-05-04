package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.service.CataService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class CataController {
    @Autowired
    CataService service;

    @GetMapping("ajax/getAllCatas")
    @RequiresAuthentication
    public ResBody ajaxgetAllCatas() {
        ResBody resBody = new ResBody();
        List<Cata> list= service.findAllCatas();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/getAllCatas")
    @RequiresAuthentication
    public ResBody getAllCatas(@RequestParam int page,
                               @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Cata> list= service.getAllCatas(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addCata")
    @RequiresRoles("admin")
    public ResBody addCata(@RequestBody Cata cata) {
        ResBody resBody = new ResBody();
        cata.setCreate_time(new Date());
        int i = service.addCata(cata);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateCata")
    @RequiresRoles("admin")
    public ResBody updateCata(@RequestBody Cata cata) {
        ResBody resBody = new ResBody();
        cata.setCreate_time(new Date());
        int i = service.updateCata(cata);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delCata")
    @RequiresRoles("admin")
    public ResBody delCata(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delCata(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }
}
