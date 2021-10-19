package com.example.demo.controller;

import com.example.demo.bean.Danyuan;
import com.example.demo.bean.ResBody;
import com.example.demo.bean.Room;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoomController {
    @Autowired
    RoomService service;

    @GetMapping("/api/getAllRooms")
    public ResBody getAllRooms(@RequestParam int page,
                                  @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Room> list= service.getAllRooms(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addRoom")
    public ResBody addRoom(@RequestBody Room room) {
        ResBody resBody = new ResBody();
        int i = service.addRoom(room);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateRoom")
    public ResBody updateDanyuan(@RequestBody Room room) {
        ResBody resBody = new ResBody();
        int i = service.updateRoom(room);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delRoom")
    public ResBody delRoom(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delRoom(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findRoom")
    public ResBody findBuilding(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        int count = 0;
        List<Room> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        if (name.isEmpty()){
            count = service.getCount();
            list= service.getAllRooms(page, limit);
        }else {
            count = service.getCount(name);
            list= service.findRoom(page, limit,name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllFreeRooms")
    public ResBody getAllFreeRooms(@RequestParam int danyuan_id) {
        ResBody resBody = new ResBody();
        List<Room> list = service.getAllFreeRooms(danyuan_id);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }
}
