package com.example.demo.service;

import com.example.demo.bean.User_Payment;
import com.example.demo.dao.User_PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_PaymentService {
    @Autowired
    User_PaymentDao dao;

    public int fenpei(Integer user_id, Integer payment_id, String value) {
        return dao.fenpei(user_id,payment_id,value);
    }

    public int getCount() {
        return dao.getCount();
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit) {
        return dao.getAllPaymentDetails(page,limit);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit, String name) {
        return dao.getAllPaymentDetails(page,limit,name);
    }

    public List<User_Payment> getAllPaymentDetails(int page, int limit, int user_id) {
        return dao.getAllPaymentDetails(page,limit,user_id);
    }

    public int getFreeCount() {
        return dao.getFreeCount();
    }

    public int getCountByUserId(Integer id) {
        return dao.getCountByUserId(id);
    }

    public int getCount(int user_id) {
        return dao.getCount(user_id);
    }

    public int jiaofei(int id) {return dao.jiaofei(id);
    }
}
