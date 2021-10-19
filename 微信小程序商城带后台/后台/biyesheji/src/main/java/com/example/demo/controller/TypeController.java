package com.example.demo.controller;

import com.example.demo.bean.ResBody;
import com.example.demo.bean.Type;
import com.example.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class TypeController {
    @Autowired
    TypeService service;

    @GetMapping("/api/getAllTypes")
    public ResBody getAllTypes(@RequestParam int page,
                                   @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Type> list= service.getAllTypes(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addType")
    public ResBody addType(@RequestBody Type type) {
        ResBody resBody = new ResBody();
        int i = service.addType(type);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateType")
    public ResBody updateType(@RequestBody Type type) {
        ResBody resBody = new ResBody();
        int i = service.updateType(type);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delType")
    public ResBody delType(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delType(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findType")
    public ResBody findType(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Type> list= service.findType(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/ajax/getAllTypes")
    public ResBody getAllPayments() {
        ResBody resBody = new ResBody();
        List<Type> list= service.getAllTypes();
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/wx/getAllTypes")
    public List WXgetAllPayments() {
        List<Type> list= service.getAllTypes();
        List result = new ArrayList();
        for (Type type:list){
            HashMap map = new HashMap();
            map.put("id",type.getId());
            map.put("title",type.getName());
            map.put("sortOrder",type.getId());
            map.put("picUrl","https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg");
            map.put("children",new ArrayList<>());
            result.add(map);
        }
        return result;
    }
}
