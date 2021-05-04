package com.example.demo.service;

import com.example.demo.bean.Bill;
import com.example.demo.dao.BillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    BillDao dao;

    public int getCount() {
        return dao.getCount();
    }

    public List<Bill> findAllBills(int page, int limit) {
        return dao.findAllBills(page,limit);
    }

    public int addBill(Bill bill) {
        return dao.addBill(bill);
    }

    public int updateBill(Bill bill) {
        return dao.updateBill(bill);
    }

    public int delBill(int id) {
        return dao.delBill(id);
    }

    public List<Bill> findBill(int page, int limit, String name) {
        return dao.findBill(page,limit,name);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public Bill getBillById(Integer id) {
        return dao.getBillById(id);
    }
}
