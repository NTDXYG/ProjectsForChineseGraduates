package com.example.demo.dao;

import com.example.demo.bean.Boke;
import com.example.demo.bean.Cata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BokeDao {
    @Autowired
    JdbcTemplate template;

    public int getCount() {
        int count = template.queryForObject("select count(*) from boke", Integer.class);
        return count;
    }

    public List<Boke> getAllBokes(int page, int limit) {
        List<Boke> list = template.query("select * from boke limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Boke.class));
        if (list!=null){
            for (Boke b:list){
                List<Cata> cataList = template.query("select * from cata where `id` = ?" ,new Object[]{b.getC_id()},
                        new BeanPropertyRowMapper(Cata.class));
                b.setCata(cataList.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int addBoke(Boke boke) {
        return template.update("insert into boke values(null,?,?,?,?)",
                boke.getTitle(),boke.getContent(),boke.getCreate_time(),boke.getC_id());
    }

    public int updateBoke(Boke boke) {
        return template.update("update boke set `title` = ? ,`content` = ?,`create_time` = ?," +
                        "`c_id` = ? where id = ?",
                boke.getTitle(),boke.getContent(),boke.getCreate_time(),boke.getC_id(),boke.getId());
    }

    public int delBoke(int id) {
        return template.update("DELETE from boke where id = ?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from boke where `title` like '%"+name+"%'", Integer.class);
        return count;
    }

    public List<Boke> findBoke(int page, int limit, String name) {
        List<Boke> list = template.query("select * from boke  where `title` like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Boke.class));
        if (list!=null){
            for (Boke b:list){
                List<Cata> cataList = template.query("select * from cata where `id` = ?" ,new Object[]{b.getC_id()},
                        new BeanPropertyRowMapper(Cata.class));
                b.setCata(cataList.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<Boke> getAllBokes() {
        List<Boke> list = template.query("select * from boke ",
                new BeanPropertyRowMapper(Boke.class));
        if (list!=null){
            for (Boke b:list){
                List<Cata> cataList = template.query("select * from cata where `id` = ?" ,new Object[]{b.getC_id()},
                        new BeanPropertyRowMapper(Cata.class));
                b.setCata(cataList.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public Boke getBokeById(int id) {
        List<Boke> list = template.query("select * from boke where id = ?",new Object[]{id} ,
                new BeanPropertyRowMapper(Boke.class));
        if (list!=null){
            for (Boke b:list){
                List<Cata> cataList = template.query("select * from cata where `id` = ?" ,new Object[]{b.getC_id()},
                        new BeanPropertyRowMapper(Cata.class));
                b.setCata(cataList.get(0));
            }
            return list.get(0);
        }else{
            return null;
        }
    }
}
