package com.example.demo.controller;

import com.example.demo.bean.ResBody;
import com.example.demo.bean.Room;
import com.example.demo.bean.Tousu;
import com.example.demo.bean.User;
import com.example.demo.service.TousuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TousuController {
    @Autowired
    TousuService service;

    @GetMapping("/api/getAllTousus")
    public ResBody getAllTousus(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Tousu> list= service.getAllTousus(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/getAllToususByUser")
    public ResBody getAllToususByUser(@RequestParam int page,
                                      @RequestParam int limit, HttpSession session){
        ResBody resBody = new ResBody();
        User user = (User) session.getAttribute("user");
        int count = service.getCount(user.getId());
        List<Tousu> list= service.getAllToususByUser(page, limit,user.getId());
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addTousu")
    public ResBody addTousu(@RequestBody Tousu tousu,HttpSession session) {
        ResBody resBody = new ResBody();
        User user = (User) session.getAttribute("user");
        if (user!=null){
            tousu.setUser_id(user.getId());
        }
        int i = service.addTousu(tousu);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateTousu")
    public ResBody updateTousu(@RequestBody Tousu tousu) {
        ResBody resBody = new ResBody();
        int i = service.updateTousu(tousu);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delTousu")
    public ResBody delTousu(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delTousu(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findTousu")
    public ResBody findTousu(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = 0;
        List<Tousu> list= new ArrayList<>();
        if (name.isEmpty()){
            count = service.getCount();
            list= service.getAllTousus(page, limit);
        }else {
            count = service.getCount(name);
            list= service.findTousu(page, limit,name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

}
