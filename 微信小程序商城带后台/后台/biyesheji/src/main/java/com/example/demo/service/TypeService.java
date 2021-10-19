package com.example.demo.service;

import com.example.demo.bean.Type;
import com.example.demo.dao.TypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    TypeDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Type> getAllTypes(int page, int limit) {
        return dao.getAllTypes(page,limit);
    }

    public List<Type> findType(int page, int limit, String name) {
        return dao.findType(page,limit,name);
    }

    public List<Type> getAllTypes() {
        return dao.getAllTypes();
    }

    public int addType(Type type) {
        return dao.addType(type);
    }

    public int updateType(Type type) {
        return dao.updateType(type);
    }

    public int delType(int id) {
        return dao.delType(id);
    }
}
