package com.example.demo.dao;

import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate template;

    public User getUser(String code) {
        List<User> list = template.query("select * from `user` where code = ?" ,
                new Object[]{code}, new BeanPropertyRowMapper(User.class));
        if (list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public int updatePass(Integer id, String newPsw) {
        return template.update("update `user` set password = ? where id = ?",
                newPsw,id);
    }

    public User findById(int id) {
        List<User> list = template.query("select * from `user` where id = ?" ,
                new Object[]{id}, new BeanPropertyRowMapper(User.class));
        if (list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    public int getCount() {
        int count = template.queryForObject("select count(*) from `user`", Integer.class);
        return count;
    }

    public List<User> findAllUsers(int page, int limit) {
        List<User> list = template.query("select * from `user` limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(User.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addUser(User user) {
        return template.update("insert into `user` values(null,?,?,?,?,?,?,?,?)",
                user.getCode(),user.getUsername(),"123456",user.getGender(),
                user.getPhone(),user.getAddress(),user.getRole(),user.getPermission()
        );
    }

    public int updateUser(User user) {
        return template.update("update `user` set `code` = ? ,`username` = ?,`gender` = ?," +
                        "`phone` = ?,`address` = ?,`role` = ? where id = ?",
                user.getCode(),user.getUsername(),user.getGender(),user.getPhone(),
                user.getAddress(),user.getRole(),user.getId()
        );
    }

    public int delUser(int id) {
        return template.update("DELETE from `user` where id=?",id);
    }

    public int getCount(String role) {
        int count = template.queryForObject("select count(*) from `user` where `role`=?",
                new Object[]{role},Integer.class);
        return count;
    }

    public List<User> findUser(int page, int limit, String role) {
        List<User> list = template.query("select * from `user` where `role` = ?  limit ?,?" ,new Object[]{role,(page-1)*limit,limit},
                new BeanPropertyRowMapper(User.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public void resetPass(int id) {
        template.update("update `user` set `password` = ? where id = ?",
                "123456",id
        );
    }
}
