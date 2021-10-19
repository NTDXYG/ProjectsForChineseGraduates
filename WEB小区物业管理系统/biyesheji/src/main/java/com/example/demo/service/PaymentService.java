package com.example.demo.service;

import com.example.demo.bean.Payment;
import com.example.demo.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Payment> getAllPayments(int page, int limit) {
        return dao.getAllPayments(page,limit);
    }

    public int addPayment(Payment payment) {
        return dao.addPayment(payment);
    }

    public int updatePayment(Payment payment) {
        return dao.updatePayment(payment);
    }

    public int delPayment(int id) {
        return dao.delPayment(id);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Payment> findPayment(int page, int limit, String name) {
        return dao.findPayment(page,limit,name);
    }

    public List<Payment> getAllPayments() {
        return dao.getAllPayments();
    }
}
