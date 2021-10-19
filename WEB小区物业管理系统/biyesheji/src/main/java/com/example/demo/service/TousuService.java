package com.example.demo.service;

import com.example.demo.bean.Repair;
import com.example.demo.bean.Tousu;
import com.example.demo.dao.RepairDao;
import com.example.demo.dao.TousuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TousuService {
    @Autowired
    TousuDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Tousu> getAllTousus(int page, int limit) {
        return dao.getAllTousus(page,limit);
    }

    public int addTousu(Tousu tousu) {
        return dao.addTousu(tousu);
    }

    public int updateTousu(Tousu tousu) {
        return dao.updateTousu(tousu);
    }

    public int delTousu(int id) {
        return dao.delTousu(id);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Tousu> findTousu(int page, int limit, String name) {
        return dao.findTousu(page,limit,name);
    }

    public int getCountByUserId(Integer id) {
        return dao.getCountByUserId(id);
    }

    public int getCount(Integer id) {
        return dao.getCount(id);
    }

    public List<Tousu> getAllToususByUser(int page, int limit, Integer id) {
        return dao.getAllToususByUser(page,limit,id);
    }
}