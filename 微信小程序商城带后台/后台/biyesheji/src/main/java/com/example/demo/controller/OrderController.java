package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Goods;
import com.example.demo.bean.Order;
import com.example.demo.bean.ResBody;
import com.example.demo.bean.Type;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService service;

    @GetMapping("/api/getAllOrders")
    public ResBody getAllOrders(@RequestParam int page,
                             @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Order> list= service.getAllOrders(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/findOrder")
    public ResBody findOrder(@RequestParam int page,
                            @RequestParam int limit,
                            @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Order> list= service.findOrder(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/delOrder")
    public ResBody delOrder(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delOrder(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/wx/getAllOrders")
    public List WXgetAllOrders(@RequestParam String openid){
        List list = service.getAllOrders(openid);
        return list;
    }

    @GetMapping("/wx/getBuyOrder")
    public List WXgetBuyOrder(@RequestParam String openid){
        List list = service.getBuyOrder(openid);
        return list;
    }

    @GetMapping("/wx/updateOrder")
    public int WXupdateOrder(@RequestParam String list,@RequestParam String openid){
        JSONArray jsonArray = JSONArray.parseArray(list);
        List<Order> orders = JSONObject.parseArray(jsonArray.toJSONString(), Order.class);
        if (orders.isEmpty()){
            service.delOrder(openid);
        }else {
            String user_openid = orders.get(0).getUser_openid();
            service.delOrder(user_openid);
            for (Order order:orders){
                Integer goods_id = order.getGoods_id();
                String user_openid1 = order.getUser_openid();
                Integer count = order.getCount();
                service.addOrder(user_openid1,goods_id,count);
            }
        }
        return 200;
    }

    @GetMapping("/wx/buyAllOrder")
    public int buyAllOrder(@RequestParam String list,@RequestParam String openid){
        JSONArray jsonArray = JSONArray.parseArray(list);
        List<Order> orders = JSONObject.parseArray(jsonArray.toJSONString(), Order.class);
        if (orders.isEmpty()){
            return 200;
        }else {
            for (Order order:orders){
                Integer goods_id = order.getGoods_id();
                String user_openid1 = order.getUser_openid();
                Integer count = order.getCount();
                service.buyAllOrder(user_openid1,goods_id,count);
            }
        }
        return 200;
    }

    @GetMapping("/wx/addOrder")
    public int addOrder(@RequestParam String user_openid,@RequestParam int goods_id){
        service.addOrderCart(user_openid,goods_id);
        return 200;
    }

    @GetMapping("/wx/buyOrder")
    public int buyOrder(@RequestParam String user_openid,@RequestParam int goods_id){
        service.buyOrderCart(user_openid,goods_id);
        return 200;
    }
}
