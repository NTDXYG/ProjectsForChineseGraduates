package com.example.demo.service;

import com.example.demo.bean.Provider;
import com.example.demo.dao.ProviderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {
    @Autowired
    ProviderDao dao;

    public int getCount() {
        return dao.getCount();
    }

    public List<Provider> findAllProviders(int page, int limit) {
        return dao.findAllProviders(page,limit);
    }

    public int addProvider(Provider provider) {
        return dao.addProvider(provider);
    }

    public int updateProvider(Provider provider) {
        return dao.updateProvider(provider);
    }

    public int delProvider(int id) {
        return dao.delProvider(id);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Provider> findProvider(int page, int limit, String name) {
        return dao.findProvider(page,limit,name);
    }

    public List<Provider> findAllProviders() {
        return dao.findAllProviders();
    }

    public Provider getProviderById(Integer id) {
        return dao.getProviderById(id);
    }
}
