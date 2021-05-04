package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao dao;
    public User getUser(String code) {
        return dao.getUser(code);
    }

    public int updatePass(Integer id, String newPsw) {
        return dao.updatePass(id,newPsw);
    }

    public User findById(int id) {
        return dao.findById(id);
    }

    public int getCount() {
        return dao.getCount();
    }

    public List<User> findAllUsers(int page, int limit) {
        return dao.findAllUsers(page,limit);
    }

    public int addUser(User user) {
        return dao.addUser(user);
    }

    public int updateUser(User user) {
        return dao.updateUser(user);
    }

    public int delUser(int id) {
        return dao.delUser(id);
    }

    public int getCount(String role) {
        return dao.getCount(role);
    }

    public List<User> findUser(int page, int limit, String role) {
        return dao.findUser(page,limit,role);
    }

    public void resetPass(int id) {
        dao.resetPass(id);
    }
}
