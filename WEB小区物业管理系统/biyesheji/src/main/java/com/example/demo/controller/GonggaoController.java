package com.example.demo.controller;

import com.example.demo.bean.Admin;
import com.example.demo.bean.Building;
import com.example.demo.bean.Gonggao;
import com.example.demo.bean.ResBody;
import com.example.demo.service.GonggaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class GonggaoController {
    @Autowired
    GonggaoService service;

    @GetMapping("/api/getAllGonggaos")
    public ResBody getAllGonggaos(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Gonggao> list= service.getAllGonggaos(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/getAllShowGonggaos")
    public ResBody getAllShowGonggaos() {
        ResBody resBody = new ResBody();
        List<Gonggao> list= service.getAllShowGonggaos();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addGonggao")
    public ResBody addGonggao(@RequestBody Gonggao gonggao, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        gonggao.setCreateTime(new Date());
        gonggao.setCreateBy(admin.getId());
        int i = service.addGonggao(gonggao);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateGonggao")
    public ResBody updateGonggao(@RequestBody Gonggao gonggao, HttpSession session) {
        ResBody resBody = new ResBody();
        Admin admin = (Admin) session.getAttribute("admin");
        gonggao.setUpdateTime(new Date());
        gonggao.setUpdateBy(admin.getId());
        int i = service.updateGonggao(gonggao);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delGonggao")
    public ResBody delBuilding(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delGonggao(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findGonggao")
    public ResBody findBuilding(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Gonggao> list= service.findGonggao(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
