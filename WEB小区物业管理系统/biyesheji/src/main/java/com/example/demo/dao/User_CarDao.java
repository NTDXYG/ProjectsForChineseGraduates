package com.example.demo.dao;

import com.example.demo.bean.Car;
import com.example.demo.bean.User;
import com.example.demo.bean.User_Car;
import com.example.demo.bean.User_Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class User_CarDao {
    @Autowired
    JdbcTemplate template;
    public int getCarRecordCount(int id) {
        int count = template.queryForObject("select count(*) from `user_car` where car_id = ?",new Object[]{id}, Integer.class);
        return count;
    }

    public List<User_Car> findCarRecordById(int id, int page, int limit) {
        List<User_Car> list = template.query("select * from `user_car` where car_id = ? limit ?,?" ,new Object[]{id,(page-1)*limit,limit},
                new BeanPropertyRowMapper(User_Car.class));
        if (list!=null){
            for (User_Car user_car:list){
                List<User> user = template.query("select * from `user` where id = ?" ,new Object[]{user_car.getUser_id()},
                        new BeanPropertyRowMapper(User.class));
                List<Car> car = template.query("select * from `car` where id = ?" ,new Object[]{user_car.getCar_id()},
                        new BeanPropertyRowMapper(Car.class));
                user_car.setUser(user.get(0));
                user_car.setCar(car.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int findCar(Integer user_id) {
        int count = template.queryForObject("SELECT count(*) FROM user_car where user_id = ? and outTime is null",
                new Object[]{user_id}, Integer.class);
        return count;
    }

    public void outCar(Integer user_id) {
        List<User_Car> list = template.query("select * from `user_car` where user_id = ? and  outTime is null" ,new Object[]{user_id},
                new BeanPropertyRowMapper(User_Car.class));
        template.update("update car set `status` = 0 where id = ?",
                list.get(0).getCar_id());
        template.update("update user_car set `outTime` = ? where user_id = ? and outTime is null",
                new Date(),user_id);
    }

    public int fenpei(Integer user_id, Integer car_id) {
        template.update("update car set `status` = 1 where id = ?",
                car_id);
        return template.update("insert into user_car values(null,?,?,?,null)",
                user_id,car_id,new Date());
    }

    public int stopCarByUserId(int id) {
        List<User_Car> list = template.query("select * from `user_car` where user_id = ? and  outTime is null" ,new Object[]{id},
                new BeanPropertyRowMapper(User_Car.class));
        if (list.isEmpty()){
            return 1;
        }
        template.update("update car set `status` = 0 where id = ?",
                list.get(0).getCar_id());
        return template.update("update user_car set `outTime` = ? where user_id = ? and outTime is null",
                new Date(),id);
    }
}
