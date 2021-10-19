package com.example.demo.dao;

import com.example.demo.bean.Building;
import com.example.demo.bean.Danyuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DanyuanDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from danyuan", Integer.class);
        return count;
    }

    public List<Danyuan> getAllDanyuans(int page, int limit) {
        List<Danyuan> list = template.query("select * from danyuan limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Danyuan.class));
        if (list!=null){
            for (Danyuan danyuan:list){
                List<Building> building = template.query("select * from building where id = ?" ,new Object[]{danyuan.getBuilding_id()},
                        new BeanPropertyRowMapper(Building.class));
                danyuan.setBuilding(building.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int addDanyuan(Danyuan danyuan) {
        return template.update("insert into danyuan values(null,?,?)",
                danyuan.getName(),danyuan.getBuilding_id());
    }

    public int updateDanyuan(Danyuan danyuan) {
        return template.update("update danyuan set `name` = ? ,`building_id` = ? where id = ?",
                danyuan.getName(),danyuan.getBuilding_id(),danyuan.getId());
    }

    public int delDanyuan(int id) {
        return template.update("DELETE from danyuan where id=?",id);
    }

    public List<Danyuan> findDanyuan(int page, int limit, String name) {
        List<Danyuan> list = template.query("select * from danyuan where name like '%"+name+"%'  limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Danyuan.class));
        if (list!=null){
            for (Danyuan danyuan:list){
                List<Building> building = template.query("select * from building where id = ?" ,new Object[]{danyuan.getBuilding_id()},
                        new BeanPropertyRowMapper(Building.class));
                danyuan.setBuilding(building.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from danyuan where name like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Danyuan> getAllDanyuans() {
        List<Danyuan> list = template.query("select * from danyuan" ,
                new BeanPropertyRowMapper(Danyuan.class));
        if (list!=null){
            for (Danyuan danyuan:list){
                List<Building> building = template.query("select * from building where id = ?" ,new Object[]{danyuan.getBuilding_id()},
                        new BeanPropertyRowMapper(Building.class));
                danyuan.setBuilding(building.get(0));
            }
            return list;
        }else{
            return null;
        }
    }
}
