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

    public int getCount() {
        return dao.getCount();
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<User> getUsers(int page, int limit) {
        return dao.getUsers(page,limit);
    }

    public List<User> findUser(int page, int limit, String name) {
        return dao.findUser(page,limit,name);
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

    public User loginByPassword(String phone, String password) {return dao.loginByPassword(phone,password);
    }

    public int updatePass(Integer id, String newPsw) {
        return dao.updatePass(id,newPsw);
    }

    public User getUserById(Integer id) {
        return dao.getUserById(id);
    }
}
