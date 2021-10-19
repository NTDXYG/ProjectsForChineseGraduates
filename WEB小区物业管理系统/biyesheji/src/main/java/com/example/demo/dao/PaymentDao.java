package com.example.demo.dao;

import com.example.demo.bean.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from payment", Integer.class);
        return count;
    }

    public List<Payment> getAllPayments(int page, int limit) {
        List<Payment> list = template.query("select * from payment limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Payment.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addPayment(Payment payment) {
        return template.update("insert into payment values(null,?)",
                payment.getName());
    }

    public int updatePayment(Payment payment) {
        return template.update("update payment set `name` = ? where id = ?",
                payment.getName(), payment.getId());
    }

    public int delPayment(int id) {
        return template.update("DELETE from payment where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from payment where name like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Payment> findPayment(int page, int limit, String name) {
        List<Payment> list = template.query("select * from payment where name like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Payment.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> list = template.query("select * from payment",
                new BeanPropertyRowMapper(Payment.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }
}
