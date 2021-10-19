package com.example.demo.dao;

import com.example.demo.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Repository
public class TousuDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from tousu", Integer.class);
        return count;
    }

    public List<Tousu> getAllTousus(int page, int limit) {
        List<Tousu> list = template.query("select * from tousu limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Tousu.class));
        if (list!=null){
            for (Tousu tousu:list){
                List<User> users = template.query("select * from user where id = ?" ,
                        new Object[]{tousu.getUser_id()}, new BeanPropertyRowMapper(User.class));
                tousu.setUser(users.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int addTousu(Tousu tousu) {
        return template.update("insert into tousu values(null,?,?,?,?,?)",
                tousu.getContent(),tousu.getUser_id(),0,new Date(),tousu.getResult());
    }

    public int updateTousu(Tousu tousu) {
        return template.update("update tousu set `status` = ?,`result` = ?  where id = ?",
                tousu.getStatus(),tousu.getResult(),tousu.getId());
    }

    public int delTousu(int id) {
        return template.update("DELETE from tousu where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from tousu where content like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Tousu> findTousu(int page, int limit, String name) {
        List<Tousu> list = template.query("select * from tousu where status = "+name+" limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Tousu.class));
        if (list!=null){
            for (Tousu tousu:list){
                List<User> users = template.query("select * from user where id = ?" ,
                        new Object[]{tousu.getUser_id()}, new BeanPropertyRowMapper(User.class));
                tousu.setUser(users.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCountByUserId(Integer id) {
        int count = template.queryForObject("select count(*) from tousu where user_id = "+id, Integer.class);
        return count;
    }

    public int getCount(Integer id) {
        int count = template.queryForObject("select count(*) from tousu where user_id = "+id, Integer.class);
        return count;
    }

    public List<Tousu> getAllToususByUser(int page, int limit, Integer id) {
        List<Tousu> list = template.query("select * from tousu where user_id = ? limit ?,?" ,new Object[]{id,(page-1)*limit,limit},
                new BeanPropertyRowMapper(Tousu.class));
        if (!list.isEmpty()){
            return list;
        }else{
            return null;
        }
    }
}
