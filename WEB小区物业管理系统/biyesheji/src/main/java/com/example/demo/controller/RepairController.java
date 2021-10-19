package com.example.demo.controller;

import com.example.demo.bean.Repair;
import com.example.demo.bean.ResBody;
import com.example.demo.bean.Tousu;
import com.example.demo.bean.User;
import com.example.demo.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RepairController {
    @Autowired
    RepairService service;

    @GetMapping("/api/getAllRepairs")
    public ResBody getAllRepairs(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Repair> list= service.getAllRepairs(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/getAllRepairsByUser")
    public ResBody getAllRepairsByUser(@RequestParam int page,
                                      @RequestParam int limit, HttpSession session){
        ResBody resBody = new ResBody();
        User user = (User) session.getAttribute("user");
        int count = service.getCountByUserId(user.getId());
        List<Repair> list= service.getAllRepairsByUser(page, limit,user.getId());
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addRepair")
    public ResBody addRepair(@RequestBody Repair repair,HttpSession session) {
        ResBody resBody = new ResBody();
        User user = (User) session.getAttribute("user");
        if (user!=null){
            repair.setUser_id(user.getId());
        }
        int i = service.addRepair(repair);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateRepair")
    public ResBody updateRepair(@RequestBody Repair repair) {
        ResBody resBody = new ResBody();
        int i = service.updateRepair(repair);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delRepair")
    public ResBody delRepair(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delRepair(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findRepair")
    public ResBody findRepair(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = 0;
        List<Repair> list= new ArrayList<>();
        if (name.isEmpty()){
            count = service.getCount();
            list= service.getAllRepairs(page, limit);
        }else {
            count = service.getCount(name);
            list= service.findRepair(page, limit,name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
