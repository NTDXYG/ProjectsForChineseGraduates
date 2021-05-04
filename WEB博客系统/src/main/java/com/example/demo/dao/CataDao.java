package com.example.demo.dao;

import com.example.demo.bean.Cata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CataDao {
    @Autowired
    JdbcTemplate template;

    public int getCount() {
        int count = template.queryForObject("select count(*) from cata", Integer.class);
        return count;
    }

    public List<Cata> getAllCatas(int page, int limit) {
        List<Cata> list = template.query("select * from cata limit ?,?" ,new Object[]{(page-1)*limit,limit} ,
                new BeanPropertyRowMapper(Cata.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addCata(Cata cata) {
        return template.update("insert into cata values(null,?,?)",
                cata.getName(),cata.getCreate_time()
        );
    }

    public int updateCata(Cata cata) {
        return template.update("update cata set `name` = ? ,`create_time` = ? where id = ?",
                cata.getName(), cata.getCreate_time(), cata.getId()
        );
    }

    public int delCata(int id) {
        return template.update("DELETE from cata where id= ? ",id);
    }

    public List<Cata> findAllCatas() {
        List<Cata> list = template.query("select * from cata" ,
                new BeanPropertyRowMapper(Cata.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }
}
