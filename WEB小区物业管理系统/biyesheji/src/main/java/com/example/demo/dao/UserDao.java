package com.example.demo.dao;

import com.example.demo.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate template;

    public int getCount() {
        int count = template.queryForObject("select count(*) from `user`", Integer.class);
        return count;
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from `user` where username like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<User> getUsers(int page, int limit) {
        List<User> list = template.query("select * from `user` limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(User.class));
        if (list!=null){
            for (User user:list){
                List<Car> car = template.query("SELECT car.id,car.name,car.type,car.`status` FROM car,user,user_car " +
                                "WHERE car.id = car_id and user_id = user.id and user_car.outTime is NULL and user_id = ?" ,
                        new Object[]{user.getId()},new BeanPropertyRowMapper(Car.class));
                List<Room> room = template.query("SELECT room.id,room.name,room.danyuan_id,room.`status` FROM room,user," +
                                "user_room WHERE room.id = room_id and user_id = user.id and user_room.outTime is NULL and user_id = ?" ,
                        new Object[]{(user.getId())},new BeanPropertyRowMapper(Room.class));
                if (!car.isEmpty()){
                    user.setCar(car.get(0));
                }
                if (!room.isEmpty()) {
                    List<Danyuan> danyuan = template.query("select * from danyuan where id = ?" ,new Object[]{room.get(0).getDanyuan_id()},
                            new BeanPropertyRowMapper(Danyuan.class));
                    room.get(0).setDanyuan(danyuan.get(0));
                    user.setRoom(room.get(0));
                }
            }
            return list;
        }else{
            return null;
        }
    }

    public List<User> findUser(int page, int limit, String name) {
        List<User> list = template.query("select * from `user` where username like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(User.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addUser(User user) {
        int count = template.queryForObject("select count(*) from `user` where phone ="+user.getPhone(), Integer.class);
        if (count != 0){
            return 0;
        }
        return template.update("insert into `user` values(null,?,?,?,?,?)",
                user.getUsername(),"123456",user.getPhone(),user.getSex(),1);
    }

    public int updateUser(User user) {
        return template.update("update `user` set `username` = ? ,`phone` = ?,`sex` = ? where id = ?",
                user.getUsername(),user.getPhone(),user.getSex(),user.getId());
    }

    public int delUser(int id) {
        return template.update("DELETE from `user` where id=?",
                id);
    }

    public User loginByPassword(String phone, String password) {
        List<User> list = template.query("select * from `user` where phone = ? and password = ?" ,new Object[]{phone,password},
                new BeanPropertyRowMapper(User.class));
        if (list!=null){
            return list.get(0);
        }else{
            return null;
        }
    }

    public int updatePass(Integer id, String newPsw) {
        return template.update("update `user` set `password` = ? where id = ?",
                newPsw,id);
    }

    public User getUserById(Integer id) {
        List<User> list = template.query("select * from `user` where id = ?" ,new Object[]{id},
                new BeanPropertyRowMapper(User.class));
        if (list!=null){
            for (User user:list){
                List<Car> car = template.query("SELECT car.id,car.name,car.type,car.`status` FROM car,user,user_car " +
                                "WHERE car.id = car_id and user_id = user.id and user_car.outTime is NULL and user_id = ?" ,
                        new Object[]{user.getId()},new BeanPropertyRowMapper(Car.class));
                List<Room> room = template.query("SELECT room.id,room.name,room.danyuan_id,room.`status` FROM room,user," +
                                "user_room WHERE room.id = room_id and user_id = user.id and user_room.outTime is NULL and user_id = ?" ,
                        new Object[]{(user.getId())},new BeanPropertyRowMapper(Room.class));
                if (!car.isEmpty()){
                    user.setCar(car.get(0));
                }
                if (!room.isEmpty()) {
                    List<Danyuan> danyuan = template.query("select * from danyuan where id = ?" ,new Object[]{room.get(0).getDanyuan_id()},
                            new BeanPropertyRowMapper(Danyuan.class));
                    room.get(0).setDanyuan(danyuan.get(0));
                    user.setRoom(room.get(0));
                }
            }
            return list.get(0);
        }else{
            return null;
        }
    }
}
