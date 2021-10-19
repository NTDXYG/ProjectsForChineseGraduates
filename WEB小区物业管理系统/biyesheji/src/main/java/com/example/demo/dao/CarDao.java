package com.example.demo.dao;

import com.example.demo.bean.Car;
import com.example.demo.bean.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from car", Integer.class);
        return count;
    }

    public List<Car> getAllCars(int page, int limit) {
        List<Car> list = template.query("select * from car limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Car.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addCar(Car car) {
        return template.update("insert into car values(null,?,?,?)",
                car.getName(),car.getType(),0);
    }

    public int updateCar(Car car) {
        return template.update("update car set `name` = ? ,`type` = ? where id = ?",
                car.getName(), car.getType(), car.getId());
    }

    public int delCar(int id) {
        return template.update("DELETE from car where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from car where type like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Car> findCar(int page, int limit, String name) {
        List<Car> list = template.query("select * from car where type like '%"+name+"%' limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Car.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public List<Car> getAllFreeCars(int type) {
        List<Car> list = template.query("select * from car where status = 0 and type like '%"+type+"%'",
                new BeanPropertyRowMapper(Car.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int getFreeCount() {
        int count = template.queryForObject("select count(*) from car where status = 0", Integer.class);
        return count;
    }
}
