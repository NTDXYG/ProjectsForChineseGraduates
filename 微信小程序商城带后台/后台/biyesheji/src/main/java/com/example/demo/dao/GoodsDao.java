package com.example.demo.dao;

import com.example.demo.bean.Goods;
import com.example.demo.bean.Recom;
import com.example.demo.bean.Type;
import com.example.demo.util.Similarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Repository
public class GoodsDao {
    @Autowired
    JdbcTemplate template;
    public List getAllGoods() {
        List<Goods> list = template.query("select * from goods "  ,
                new BeanPropertyRowMapper(Goods.class));
        if (!list.isEmpty()){
            for (Goods good:list){
                List<Type> types = template.query("select * from type where id = "+good.getType_id()  ,
                        new BeanPropertyRowMapper(Type.class));
                good.setType(types.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount() {
        int count = template.queryForObject("select count(*) from goods", Integer.class);
        return count;
    }

    public List<Goods> getAllGoods(int page, int limit) {
        List<Goods> list = template.query("select * from goods limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Goods.class));
        if (!list.isEmpty()){
            for (Goods good:list){
                List<Type> types = template.query("select * from type where id = "+good.getType_id()  ,
                        new BeanPropertyRowMapper(Type.class));
                good.setType(types.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int addGoods(Goods goods) {
        return template.update("insert into goods values(null,?,?,?,?)",
                goods.getImage(),goods.getName(),goods.getPrice(),goods.getType_id());
    }

    public int updateGoods(Goods goods) {
        return template.update("update goods set `image` = ? ,`name` = ? ,`price` = ? ,`type_id` = ?  where _id = ?",
                goods.getImage(),goods.getName(),goods.getPrice(),goods.getType_id(),goods.get_id());
    }

    public int delGoods(int id) {
        return template.update("DELETE from goods where _id=?",id);
    }

    public List<Goods> findGoods(int page, int limit, String name) {
        List<Goods> list = template.query("select * from goods where name like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Goods.class));
        if (!list.isEmpty()){
            for (Goods good:list){
                List<Type> types = template.query("select * from type where id = "+good.getType_id()  ,
                        new BeanPropertyRowMapper(Type.class));
                good.setType(types.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from goods where name like '%"+name+"%'", Integer.class);
        return count;
    }

    public Goods getGoodsById(int id) {
        List<Goods> list = template.query("select * from goods where _id = ?" ,new Object[]{id},
                new BeanPropertyRowMapper(Goods.class));
        if (!list.isEmpty()){
            for (Goods good:list){
                List<Type> types = template.query("select * from type where id = "+good.getType_id()  ,
                        new BeanPropertyRowMapper(Type.class));
                good.setType(types.get(0));
            }
            return list.get(0);
        }else{
            return null;
        }
    }

    public List<Recom> getRecomList(int id) {
        List<Recom> recomList = new ArrayList<>();
        List<Goods> goodsList = template.query("select * from goods where _id = ?" ,new Object[]{id},
                new BeanPropertyRowMapper(Goods.class));
        Goods goods = goodsList.get(0);
        List<Goods> list = template.query("select * from goods where _id != ?" ,new Object[]{id},
                new BeanPropertyRowMapper(Goods.class));
        for(Goods g:list){
            recomList.add(new Recom(Similarity.getSimilarity(goods.getName(),g.getName()),g));
        }
        Collections.sort(recomList);
        return recomList;
    }
}
