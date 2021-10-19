package com.example.demo.service;

import com.example.demo.bean.Building;
import com.example.demo.dao.BuildingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {
    @Autowired
    BuildingDao dao;
    public int getCount() {
        return dao.getCount();
    }

    public List<Building> getAllBuildings(int page, int limit) {
        return dao.getAllBuildings(page,limit);
    }

    public int addBuilding(Building building) {
        return dao.addBuilding(building);
    }

    public int updateBuilding(Building building) {
        return dao.updateBuilding(building);
    }

    public int delBuilding(int id) {
        return dao.delBuilding(id);
    }

    public int getCount(String name) {
        return dao.getCount(name);
    }

    public List<Building> findBuilding(int page, int limit, String name) {
        return dao.findBuilding(page,limit,name);
    }

    public List<Building> getAllBuildings() {
        return dao.getAllBuildings();
    }
}
