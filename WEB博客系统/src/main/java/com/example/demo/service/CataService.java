package com.example.demo.service;

import com.example.demo.bean.Cata;
import com.example.demo.dao.CataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CataService {
    @Autowired
    CataDao dao;

    public int getCount() {
        return dao.getCount();
    }

    public List<Cata> getAllCatas(int page, int limit) {
        return dao.getAllCatas(page,limit);
    }

    public int addCata(Cata cata) {
        return dao.addCata(cata);
    }

    public int updateCata(Cata cata) {
        return dao.updateCata(cata);
    }

    public int delCata(int id) {
        return dao.delCata(id);
    }

    public List<Cata> findAllCatas() {
        return dao.findAllCatas();
    }
}
