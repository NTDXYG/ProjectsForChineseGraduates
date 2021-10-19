package com.example.demo.dao;

import com.example.demo.bean.Goods;
import com.example.demo.bean.Order;
import com.example.demo.bean.WX_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    JdbcTemplate template;
    public List getAllOrders(String openid) {
        List<Order> list = template.query("select * from `order` where status = 0 and user_openid = '"+openid+"'"  ,
                new BeanPropertyRowMapper(Order.class));
        if (list!=null){
            for (Order order:list){
                List<WX_User> wx_users = template.query("select * from user where open_id = '"+order.getUser_openid()+"'"  ,
                        new BeanPropertyRowMapper(WX_User.class));
                List<Goods> goods = template.query("select * from goods where _id ="+order.getGoods_id()  ,
                        new BeanPropertyRowMapper(Goods.class));
                order.setWx_user(wx_users.get(0));
                order.setGoods(goods.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public void delOrder(String user_openid) {
        template.update("DELETE from `order` where status = 0 and user_openid=?",user_openid);
    }

    public void addOrder(String user_openid1, Integer goods_id, Integer count) {
        template.update("insert into `order` values(null,?,?,?,?)",
                user_openid1,goods_id,count,0);
    }

    public void addOrderCart(String user_openid, int goods_id) {
        int count = template.queryForObject("select count(*) from `order` where user_openid = ? and goods_id = ? and status = 0",
                new Object[]{user_openid,goods_id},Integer.class);
        if (count!=0){
            template.update("update `order` set count=count+1  where user_openid = ? and goods_id = ? and status = 0",
                    user_openid,goods_id);
        }else{
            template.update("insert into `order` values(null,?,?,?,?)",
                    user_openid,goods_id,1,0);
        }
    }

    public void buyOrderCart(String user_openid, int goods_id) {
        template.update("insert into `order` values(null,?,?,?,?)",
                user_openid,goods_id,1,1);
    }

    public void buyAllOrder(String user_openid1, Integer goods_id, Integer count) {
        int c = template.queryForObject("select count(*) from `order` where user_openid = ? and goods_id = ? and status = 0",
                new Object[]{user_openid1,goods_id},Integer.class);
        if (c!=0){
            template.update("update `order` set count=? ,status = 1 where user_openid = ? and goods_id = ? and status = 0",
                    count,user_openid1,goods_id);
        }else{
            template.update("insert into `order` values(null,?,?,?,?)",
                    user_openid1,goods_id,count,1);
        }
    }

    public List getBuyOrder(String openid) {
        List<Order> list = template.query("select * from `order` where status = 1 and user_openid = '"+openid+"'"  ,
                new BeanPropertyRowMapper(Order.class));
        if (list!=null){
            for (Order order:list){
                List<WX_User> wx_users = template.query("select * from user where open_id = '"+order.getUser_openid()+"'"  ,
                        new BeanPropertyRowMapper(WX_User.class));
                List<Goods> goods = template.query("select * from goods where _id ="+order.getGoods_id()  ,
                        new BeanPropertyRowMapper(Goods.class));
                order.setWx_user(wx_users.get(0));
                order.setGoods(goods.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount() {
        int count = template.queryForObject("select count(*) from  `order` where status = 1", Integer.class);
        return count;
    }

    public List<Order> getAllOrders(int page, int limit) {
        List<Order> list = template.query("select * from `order` where status = 1 limit ?,?" ,new Object[]{(page-1)*limit,limit} ,
                new BeanPropertyRowMapper(Order.class));
        if (list!=null){
            for (Order order:list){
                List<WX_User> wx_users = template.query("select * from user where open_id = '"+order.getUser_openid()+"'"  ,
                        new BeanPropertyRowMapper(WX_User.class));
                List<Goods> goods = template.query("select * from goods where _id ="+order.getGoods_id()  ,
                        new BeanPropertyRowMapper(Goods.class));
                order.setWx_user(wx_users.get(0));
                order.setGoods(goods.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<Order> findOrder(int page, int limit, String name) {
        List<Order> result = new ArrayList<>();
        List<Order> list = template.query("select * from `order` where status = 1 limit ?,?" ,new Object[]{(page-1)*limit,limit} ,
                new BeanPropertyRowMapper(Order.class));
        if (list!=null){
            for (Order order:list){
                List<WX_User> wx_users = template.query("select * from user where open_id = '"+order.getUser_openid()+"'"  ,
                        new BeanPropertyRowMapper(WX_User.class));
                List<Goods> goods = template.query("select * from goods where _id ="+order.getGoods_id() +" and name like '%"+name+"%'" ,
                        new BeanPropertyRowMapper(Goods.class));
                if (!goods.isEmpty()){
                    order.setWx_user(wx_users.get(0));
                    order.setGoods(goods.get(0));
                    result.add(order);
                }
            }
            return result;
        }else{
            return null;
        }
    }

    public int getCount(String name) {
        List<Order> result = new ArrayList<>();
        List<Order> list = template.query("select * from `order` where status = 1",
                new BeanPropertyRowMapper(Order.class));
        if (list!=null){
            for (Order order:list){
                List<WX_User> wx_users = template.query("select * from user where open_id = '"+order.getUser_openid()+"'"  ,
                        new BeanPropertyRowMapper(WX_User.class));
                List<Goods> goods = template.query("select * from goods where _id ="+order.getGoods_id() +" and name like '%"+name+"%'" ,
                        new BeanPropertyRowMapper(Goods.class));
                if (!goods.isEmpty()){
                    order.setWx_user(wx_users.get(0));
                    order.setGoods(goods.get(0));
                    result.add(order);
                }
            }
            return result.size();
        }else{
            return 0;
        }
    }

    public int delOrder(int id) {
        return template.update("DELETE from `order` where id = ?",id);
    }
}
