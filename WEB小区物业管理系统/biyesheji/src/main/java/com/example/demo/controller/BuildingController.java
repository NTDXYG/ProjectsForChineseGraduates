package com.example.demo.controller;

import com.example.demo.bean.Building;
import com.example.demo.bean.ResBody;
import com.example.demo.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class BuildingController {
    @Autowired
    BuildingService service;

    @GetMapping("/api/getAllBuildings")
    public ResBody getAllBuildings(@RequestParam int page,
                               @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Building> list= service.getAllBuildings(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addBuilding")
    public ResBody addBuilding(@RequestBody Building building) {
        ResBody resBody = new ResBody();
        int i = service.addBuilding(building);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateBuilding")
    public ResBody updateBuilding(@RequestBody Building building) {
        ResBody resBody = new ResBody();
        int i = service.updateBuilding(building);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delBuilding")
    public ResBody delBuilding(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delBuilding(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findBuilding")
    public ResBody findBuilding(@RequestParam int page,
                            @RequestParam int limit,
                            @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Building> list= service.findBuilding(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllBuildings")
    public ResBody getAllDanyuans() {
        ResBody resBody = new ResBody();
        List<Building> list= service.getAllBuildings();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
