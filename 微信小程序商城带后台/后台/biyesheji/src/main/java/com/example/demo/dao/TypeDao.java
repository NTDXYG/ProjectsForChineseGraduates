package com.example.demo.dao;

import com.example.demo.bean.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeDao {
    @Autowired
    JdbcTemplate template;

    public int getCount() {
        int count = template.queryForObject("select count(*) from type", Integer.class);
        return count;
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from type where name like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Type> getAllTypes(int page, int limit) {
        List<Type> list = template.query("select * from type limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Type.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<Type> findType(int page, int limit, String name) {
        List<Type> list = template.query("select * from type where name like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Type.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<Type> getAllTypes() {
        List<Type> list = template.query("select * from type "  ,
                new BeanPropertyRowMapper(Type.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addType(Type type) {
        return template.update("insert into type values(null,?)",
                type.getName());
    }

    public int updateType(Type type) {
        return template.update("update type set `name` = ? where id = ?",
                type.getName(), type.getId());
    }

    public int delType(int id) {
        return template.update("DELETE from type where id=?",id);
    }
}
