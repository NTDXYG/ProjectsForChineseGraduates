package com.example.demo.dao;


import com.example.demo.bean.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDao {
    @Autowired
    JdbcTemplate template;

    public Admin findAdmin(String email, String password) {
        List<Admin> list = template.query("select * from admin where email = ? && password = ?" ,
                new Object[]{email,password}, new BeanPropertyRowMapper(Admin.class));
        if (list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public int updatePass(int id, String newPsw) {
        return template.update("update admin set password = ? where id = ?",
                newPsw,id);
    }
}
