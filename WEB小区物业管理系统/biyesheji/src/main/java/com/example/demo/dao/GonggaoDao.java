package com.example.demo.dao;

import com.example.demo.bean.Admin;
import com.example.demo.bean.Gonggao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GonggaoDao {
    @Autowired
    JdbcTemplate template;

    public int getCount() {
        int count = template.queryForObject("select count(*) from gonggao", Integer.class);
        return count;
    }

    public List<Gonggao> getAllGonggaos(int page, int limit) {
        List<Gonggao> list = template.query("select * from gonggao limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Gonggao.class));
        if (list!=null){
            for (Gonggao gonggao:list){
                List<Admin> admin = template.query("select * from admin where id = ?" ,
                        new Object[]{gonggao.getCreateBy()}, new BeanPropertyRowMapper(Admin.class));
                gonggao.setCreate_admin(admin.get(0));
                if (gonggao.getUpdateBy()!=null){
                    List<Admin> admins = template.query("select * from admin where id = ?" ,
                            new Object[]{gonggao.getUpdateBy()}, new BeanPropertyRowMapper(Admin.class));
                    gonggao.setUpdate_admin(admins.get(0));
                }
            }
            return list;
        }else{
            return null;
        }
    }

    public int addGonggao(Gonggao gonggao) {
        return template.update("insert into gonggao values(null,?,?,?,?,?,?,?)",
                gonggao.getTitle(),gonggao.getContent(),gonggao.getCreateBy(),gonggao.getCreateTime(),
                gonggao.getUpdateBy(),gonggao.getUpdateTime(),gonggao.getStatus());
    }

    public int updateGonggao(Gonggao gonggao) {
        return template.update("update gonggao set `title` = ? ,`content` = ? ,`status` = ?,`updateBy` = ? ,`updateTime` = ? where id = ?",
                gonggao.getTitle(),gonggao.getContent(),gonggao.getStatus(),gonggao.getUpdateBy(),gonggao.getUpdateTime(),gonggao.getId());
    }

    public int delGonggao(int id) {
        return template.update("DELETE from gonggao where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from gonggao where title like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Gonggao> findGonggao(int page, int limit, String name) {
        List<Gonggao> list = template.query("select * from gonggao where title like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Gonggao.class));
        if (list!=null){
            for (Gonggao gonggao:list){
                List<Admin> admin = template.query("select * from admin where id = ?" ,
                        new Object[]{gonggao.getCreateBy()}, new BeanPropertyRowMapper(Admin.class));
                gonggao.setCreate_admin(admin.get(0));
                if (gonggao.getUpdateBy()!=null){
                    List<Admin> admins = template.query("select * from admin where id = ?" ,
                            new Object[]{gonggao.getUpdateBy()}, new BeanPropertyRowMapper(Admin.class));
                    gonggao.setUpdate_admin(admins.get(0));
                }
            }
            return list;
        }else{
            return null;
        }
    }

    public Gonggao getGonggao() {
        List<Gonggao> list = template.query("select * from gonggao where status = 0",
                new BeanPropertyRowMapper(Gonggao.class));
        if (!list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }

    public List<Gonggao> getAllShowGonggaos() {
        List<Gonggao> list = template.query("select * from gonggao where status = 0",
                new BeanPropertyRowMapper(Gonggao.class));
        return list;
    }
}
