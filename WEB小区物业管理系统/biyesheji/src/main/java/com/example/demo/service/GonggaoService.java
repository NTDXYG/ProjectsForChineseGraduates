package com.example.demo.service;

import com.example.demo.bean.Gonggao;
import com.example.demo.dao.GonggaoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GonggaoService {
    @Autowired
    GonggaoDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Gonggao> getAllGonggaos(int page, int limit) {
        return dao.getAllGonggaos(page,limit);
    }

    public int addGonggao(Gonggao gonggao) {
        return dao.addGonggao(gonggao);
    }

    public int updateGonggao(Gonggao gonggao) {
        return dao.updateGonggao(gonggao);
    }

    public int delGonggao(int id) {
        return dao.delGonggao(id);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Gonggao> findGonggao(int page, int limit, String name) {
        return dao.findGonggao(page,limit,name);
    }

    public Gonggao getGonggao() {
        return dao.getGonggao();
    }

    public List<Gonggao> getAllShowGonggaos() {
        return dao.getAllShowGonggaos();
    }
}
