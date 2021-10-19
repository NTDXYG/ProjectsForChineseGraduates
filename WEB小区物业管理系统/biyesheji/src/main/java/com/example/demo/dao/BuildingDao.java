package com.example.demo.dao;

import com.example.demo.bean.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuildingDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from building", Integer.class);
        return count;
    }

    public List<Building> getAllBuildings(int page, int limit) {
        List<Building> list = template.query("select * from building limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Building.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addBuilding(Building building) {
        int count = template.queryForObject("select count(*) from building where `name` = '" + building.getName() + "'", Integer.class);
        if (count>0){
            return -1;
        } else {
            return template.update("insert into building values(null,?,?)",
                    building.getName(),building.getType());
        }

    }

    public int updateBuilding(Building building) {
        return template.update("update building set `name` = ? ,`type` = ? where id = ?",
                building.getName(),building.getType(),building.getId());
    }

    public int delBuilding(int id) {
        return template.update("DELETE from building where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from building where name like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Building> findBuilding(int page, int limit, String name) {
        List<Building> list = template.query("select * from building where name like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Building.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<Building> getAllBuildings() {
        List<Building> list = template.query("select * from building",
                new BeanPropertyRowMapper(Building.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }
}
