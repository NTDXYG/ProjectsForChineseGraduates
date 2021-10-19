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
public class RepairDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from repair", Integer.class);
        return count;
    }

    public List<Repair> getAllRepairs(int page, int limit) {
        List<Repair> list = template.query("select * from repair limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Repair.class));
        if (list!=null){
            for (Repair repair:list){
                List<User> users = template.query("select * from user where id = ?" ,
                        new Object[]{repair.getUser_id()}, new BeanPropertyRowMapper(User.class));
                repair.setUser(users.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int addRepair(Repair repair) {
        return template.update("insert into repair values(null,?,?,?,?,?)",
                repair.getContent(),repair.getUser_id(),0,new Date(),repair.getResult());

    }

    public int updateRepair(Repair repair) {
        return template.update("update repair set `status` = ?,`result` = ?   where id = ?",
                repair.getStatus(),repair.getResult(),repair.getId());
    }

    public int delRepair(int id) {
        return template.update("DELETE from repair where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from repair where content like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Repair> findRepair(int page, int limit, String name) {
        List<Repair> list = template.query("select * from repair where status = "+name+" limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Repair.class));
        if (list!=null){
            for (Repair repair:list){
                List<User> users = template.query("select * from user where id = ?" ,
                        new Object[]{repair.getUser_id()}, new BeanPropertyRowMapper(User.class));
                repair.setUser(users.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCountByUserId(Integer id) {
        int count = template.queryForObject("select count(*) from repair where user_id = "+id, Integer.class);
        return count;
    }

    public List<Repair> getAllRepairsByUser(int page, int limit, Integer id) {
        List<Repair> list = template.query("select * from repair where user_id = ? limit ?,?" ,new Object[]{id,(page-1)*limit,limit},
                new BeanPropertyRowMapper(Repair.class));
        if (!list.isEmpty()){
            return list;
        }else{
            return null;
        }
    }
}
