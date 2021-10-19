package com.example.demo.service;

import com.example.demo.bean.WX_User;
import com.example.demo.dao.WX_UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WX_UserService {
    @Autowired
    WX_UserDao dao;
    public WX_User selectById(String openid) {
        return dao.selectById(openid);
    }

    public void insert(WX_User user) {
        dao.insert(user);
    }

    public void updateById(WX_User user) {
        dao.updateById(user);
    }

    public int getCount() {
        return dao.getCount();
    }

    public List<WX_User> getUsers(int page, int limit) {
        return dao.getUsers(page,limit);
    }
}
