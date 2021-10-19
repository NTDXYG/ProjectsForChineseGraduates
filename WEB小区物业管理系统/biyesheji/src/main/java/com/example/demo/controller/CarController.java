package com.example.demo.controller;

import com.example.demo.bean.Building;
import com.example.demo.bean.Car;
import com.example.demo.bean.Danyuan;
import com.example.demo.bean.ResBody;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    @Autowired
    CarService service;

    @GetMapping("/api/getAllCars")
    public ResBody getAllCars(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Car> list= service.getAllCars(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addCar")
    public ResBody addBuilding(@RequestBody Car car) {
        ResBody resBody = new ResBody();
        int i = service.addCar(car);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateCar")
    public ResBody updateCar(@RequestBody Car car) {
        ResBody resBody = new ResBody();
        int i = service.updateCar(car);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delCar")
    public ResBody delCar(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delCar(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findCar")
    public ResBody findCar(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Car> list= service.findCar(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllFreeCars")
    public ResBody getAllDanyuans(@RequestParam int type) {
        ResBody resBody = new ResBody();
        List<Car> list= service.getAllFreeCars(type);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
