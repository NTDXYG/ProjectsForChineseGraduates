package com.example.demo.service;

import com.example.demo.bean.User_Car;
import com.example.demo.dao.User_CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_CarService {
    @Autowired
    User_CarDao dao;

    public int getCarRecordCount(int id) {
        return dao.getCarRecordCount(id);
    }

    public List<User_Car> findCarRecordById(int id, int page, int limit) {
        return dao.findCarRecordById(id,page,limit);
    }

    public int findCar(Integer user_id) {
        return dao.findCar(user_id);
    }

    public void outCar(Integer user_id) {
        dao.outCar(user_id);
    }

    public int fenpei(Integer user_id, Integer car_id) {
        return dao.fenpei(user_id,car_id);
    }

    public int stopCarByUserId(int id) {
        return dao.stopCarByUserId(id);
    }
}
