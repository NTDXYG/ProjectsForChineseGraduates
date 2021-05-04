package com.example.demo.service;

import com.example.demo.bean.Boke;
import com.example.demo.dao.BokeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BokeService {
    @Autowired
    BokeDao dao;

    public int getCount() {
        return dao.getCount();
    }

    public List<Boke> getAllBokes(int page, int limit) {
        return dao.getAllBokes(page,limit);
    }

    public int addBoke(Boke boke) {
        return dao.addBoke(boke);
    }

    public int updateBoke(Boke boke) {
        return dao.updateBoke(boke);
    }

    public int delBoke(int id) {
        return dao.delBoke(id);
    }

    public List<Boke> findBoke(int page, int limit, String name) {
        return dao.findBoke(page,limit,name);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Boke> getAllBokes() {
        return dao.getAllBokes();
    }

    public Boke getBokeById(int id) {
        return dao.getBokeById(id);
    }
}
