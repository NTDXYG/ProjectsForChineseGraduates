package com.example.demo.service;

import com.example.demo.bean.Goods;
import com.example.demo.bean.Recom;
import com.example.demo.dao.GoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsDao dao;

    public List getAllGoods() {
        return dao.getAllGoods();
    }

    public int getCount() {
        return dao.getCount();
    }

    public List<Goods> getAllGoods(int page, int limit) {
        return dao.getAllGoods(page,limit);
    }

    public int addGoods(Goods goods) {
        return dao.addGoods(goods);
    }

    public int updateGoods(Goods goods) {
        return dao.updateGoods(goods);
    }

    public int delGoods(int id) {
        return dao.delGoods(id);
    }

    public List<Goods> findGoods(int page, int limit, String name) {
        return dao.findGoods(page,limit,name);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public Goods getGoodsById(int id) {
        return dao.getGoodsById(id);
    }

    public List<Recom> getRecomList(int id) {
        return dao.getRecomList(id);
    }
}
