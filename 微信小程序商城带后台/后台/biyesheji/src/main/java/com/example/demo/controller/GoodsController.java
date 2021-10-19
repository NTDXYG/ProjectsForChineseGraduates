package com.example.demo.controller;

import com.example.demo.bean.Goods;
import com.example.demo.bean.Recom;
import com.example.demo.bean.ResBody;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    GoodsService service;

    @GetMapping("/api/getAllGoods")
    public ResBody getAllGoods(@RequestParam int page,
                               @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Goods> list= service.getAllGoods(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addGoods")
    public ResBody addGoods(@RequestBody Goods goods) {
        ResBody resBody = new ResBody();
        int i = service.addGoods(goods);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateGoods")
    public ResBody updateGoods(@RequestBody Goods goods) {
        ResBody resBody = new ResBody();
        int i = service.updateGoods(goods);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delGoods")
    public ResBody delGoods(@RequestParam int _id) {
        ResBody resBody = new ResBody();
        int i = service.delGoods(_id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findGoods")
    public ResBody findType(@RequestParam int page,
                            @RequestParam int limit,
                            @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Goods> list= service.findGoods(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/wx/getAllGoods")
    public List WXgetAllGoods(){
        List list = service.getAllGoods();
        return list;
    }

    @GetMapping("/wx/getGoodsById")
    public Goods getGoodsById(@RequestParam int id){
        Goods goods = service.getGoodsById(id);
        return goods;
    }

    @GetMapping("/wx/getRecomList")
    public List<Recom> getRecomList(@RequestParam int id){
        List<Recom> recom = service.getRecomList(id);
        return recom.subList(0,2);
    }

}
