package com.example.demo.dao;

import com.example.demo.bean.Admin;
import com.example.demo.bean.Goods;
import com.example.demo.bean.Type;
import com.example.demo.bean.WX_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WX_UserDao {
    @Autowired
    JdbcTemplate template;
    public WX_User selectById(String openid) {
        List<WX_User> list = template.query("select * from user where open_id = ?" ,
                new Object[]{openid}, new BeanPropertyRowMapper(WX_User.class));
        if (!list.isEmpty()){
            return list.get(0);
        }else{
            return null;
        }
    }

    public void insert(WX_User user) {
        template.update("insert into user values(?,?,?,?,?,?,?,?,?,?)",
                user.getOpenId(),user.getCreateTime(),user.getLastVisitTime(),
                user.getSessionKey(),user.getCity(),user.getProvince(),user.getCountry(),
                user.getAvatarUrl(),user.getGender(),user.getNickName());
    }

    public void updateById(WX_User user) {
        template.update("update user set `last_visit_time` = ? where open_id = ?",
                user.getLastVisitTime(),user.getOpenId());
    }

    public int getCount() {
        int count = template.queryForObject("select count(*) from user", Integer.class);
        return count;
    }

    public List<WX_User> getUsers(int page, int limit) {
        List<WX_User> list = template.query("select * from user limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(WX_User.class));
        if (!list.isEmpty()){
            return list;
        }else{
            return null;
        }
    }
}
