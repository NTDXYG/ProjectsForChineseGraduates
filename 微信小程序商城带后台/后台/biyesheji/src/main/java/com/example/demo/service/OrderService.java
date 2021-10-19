package com.example.demo.service;

import com.example.demo.bean.Goods;
import com.example.demo.bean.Order;
import com.example.demo.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDao dao;

    public List getAllOrders(String openid) {
        return dao.getAllOrders(openid);
    }

    public void delOrder(String user_openid) {
        dao.delOrder(user_openid);
    }

    public void addOrder(String user_openid1, Integer goods_id, Integer count) {
        dao.addOrder(user_openid1,goods_id,count);
    }

    public void addOrderCart(String user_openid, int goods_id) {
        dao.addOrderCart(user_openid,goods_id);
    }

    public void buyOrderCart(String user_openid, int goods_id) { dao.buyOrderCart(user_openid,goods_id);
    }

    public void buyAllOrder(String user_openid1, Integer goods_id, Integer count) {
        dao.buyAllOrder(user_openid1,goods_id,count);
    }

    public List getBuyOrder(String openid) {
        return dao.getBuyOrder(openid);
    }

    public int getCount() {
        return dao.getCount();
    }

    public List<Order> getAllOrders(int page, int limit) {
        return dao.getAllOrders(page,limit);
    }

    public List<Order> findOrder(int page, int limit, String name) {
        return dao.findOrder(page,limit,name);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public int delOrder(int id) {
        return dao.delOrder(id);
    }
}
