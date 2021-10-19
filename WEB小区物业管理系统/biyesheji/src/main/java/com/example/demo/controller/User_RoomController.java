package com.example.demo.controller;

import com.example.demo.bean.ResBody;
import com.example.demo.bean.Room;
import com.example.demo.bean.User_Car;
import com.example.demo.bean.User_Room;
import com.example.demo.service.User_CarService;
import com.example.demo.service.User_RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class User_RoomController {
    @Autowired
    User_RoomService service;

    @GetMapping("/api/findRoomRecordById")
    public ResBody findRoomRecordById(@RequestParam int id,@RequestParam int page,@RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getRoomRecordCount(id);
        List<User_Room> list= service.findRoomRecordById(id,page,limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/stopRoomByUserId")
    public ResBody stopRoomByUserId(@RequestParam int id){
        ResBody resBody = new ResBody();
        int i = service.stopRoomByUserId(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("失败");
        }
        return resBody;
    }

    @PostMapping("/api/fenpeiRoom")
    public ResBody fenpeiRoom(@RequestBody User_Room user_room) {
        ResBody resBody = new ResBody();
        System.out.println(user_room);
        user_room.setUser_id(user_room.getId());
        //判断该用户当前有无房间，如果有，先退房再入住。
        int count = service.findRoom(user_room.getUser_id());
        if (count == 1){
            service.outRoom(user_room.getUser_id());
        }
        int i = service.fenpei(user_room.getUser_id(),user_room.getRoom_id());
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }
}
