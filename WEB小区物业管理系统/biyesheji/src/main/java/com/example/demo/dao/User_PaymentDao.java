package com.example.demo.dao;

import com.example.demo.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class User_PaymentDao {
    @Autowired
    JdbcTemplate template;
    public int fenpei(Integer user_id, Integer payment_id, String value) {
        return template.update("insert into user_payment values(null,?,?,?,?,0)",
                user_id,payment_id,value,new Date());
    }

    public int getCount() {
        int count = template.queryForObject("select count(*) from user_payment", Integer.class);
        return count;
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from user_payment where status like '%"+name +"%'", Integer.class);
        return count;
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit) {
        List<User_Payment> list = template.query("select * from `user_payment` limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(User_Payment.class));
        if (list!=null){
            for (User_Payment user_payment:list){
                List<User> user = template.query("select * from `user` where id = ?" ,new Object[]{user_payment.getUser_id()},
                        new BeanPropertyRowMapper(User.class));
                List<Payment> payment = template.query("select * from `payment` where id = ?" ,new Object[]{user_payment.getPayment_id()},
                        new BeanPropertyRowMapper(Payment.class));
                user_payment.setUser(user.get(0));
                user_payment.setPayment(payment.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit, String name) {
        List<User_Payment> list = template.query("select * from `user_payment` where status like '%"+name +"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(User_Payment.class));
        if (list!=null){
            for (User_Payment user_payment:list){
                List<User> user = template.query("select * from `user` where id = ?" ,new Object[]{user_payment.getUser_id()},
                        new BeanPropertyRowMapper(User.class));
                List<Payment> payment = template.query("select * from `payment` where id = ?" ,new Object[]{user_payment.getPayment_id()},
                        new BeanPropertyRowMapper(Payment.class));
                user_payment.setUser(user.get(0));
                user_payment.setPayment(payment.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit, int user_id) {
        List<User_Payment> list = template.query("select * from `user_payment` where user_id = ? limit ?,?" ,new Object[]{user_id,(page-1)*limit,limit},
                new BeanPropertyRowMapper(User_Payment.class));
        if (list!=null){
            for (User_Payment user_payment:list){
                List<User> user = template.query("select * from `user` where id = ?" ,new Object[]{user_payment.getUser_id()},
                        new BeanPropertyRowMapper(User.class));
                List<Payment> payment = template.query("select * from `payment` where id = ?" ,new Object[]{user_payment.getPayment_id()},
                        new BeanPropertyRowMapper(Payment.class));
                user_payment.setUser(user.get(0));
                user_payment.setPayment(payment.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getFreeCount() {
        int count = template.queryForObject("select count(*) from user_payment where status = 0", Integer.class);
        return count;
    }

    public int getCountByUserId(Integer id) {
        int count = template.queryForObject("select count(*) from user_payment where user_id = "+id, Integer.class);
        return count;
    }

    public int getCount(int user_id) {
        int count = template.queryForObject("select count(*) from user_payment where user_id = "+user_id, Integer.class);
        return count;
    }

    public int jiaofei(int id) {
        return template.update("update user_payment set status = 1 where id = ?",
                id);
    }
}
