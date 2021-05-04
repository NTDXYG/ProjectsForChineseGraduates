package com.example.demo.dao;

import com.example.demo.bean.Bill;
import com.example.demo.bean.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProviderDao {
    @Autowired
    JdbcTemplate template;

    public List<Provider> findAllProviders() {
        List<Provider> list = template.query("select * from smbms_provider" ,
                new BeanPropertyRowMapper(Provider.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int getCount() {
        int count = template.queryForObject("select count(*) from smbms_provider", Integer.class);
        return count;
    }

    public List<Provider> findAllProviders(int page, int limit) {
        List<Provider> list = template.query("select * from smbms_provider limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Provider.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int addProvider(Provider provider) {
        return template.update("insert into smbms_provider values(null,?,?,?,?,?,?,?,?,?,?,?)",
                provider.getProCode(),provider.getProName(),provider.getProDesc(),provider.getProContact(),
                provider.getProPhone(),provider.getProAddress(),provider.getProFax(),provider.getCreatedBy(),
                provider.getCreationDate(),provider.getModifyBy(),provider.getModifyDate()
                );
    }

    public int updateProvider(Provider provider) {
        return template.update("update smbms_provider set `proCode` = ? ,`proName` = ?,`proDesc` = ?," +
                        "`proContact` = ?,`proPhone` = ?,`proAddress` = ?,`proFax` = ?,`createdBy` = ?,`creationDate` = ?," +
                        "`modifyBy` = ?, `modifyDate` = ? where id = ?",
                provider.getProCode(),provider.getProName(),provider.getProDesc(),provider.getProContact(),
                provider.getProPhone(),provider.getProAddress(),provider.getProFax(),provider.getCreatedBy(),
                provider.getCreationDate(),provider.getModifyBy(),provider.getModifyDate(),provider.getId()
                );
    }

    public int delProvider(int id) {
        return template.update("DELETE from smbms_provider where id=?",id);
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from smbms_provider where `proName` like '%"+name+"%'", Integer.class);
        return count;
    }

    public List<Provider> findProvider(int page, int limit, String name) {
        List<Provider> list = template.query("select * from smbms_provider where `proName` like '%"+name+"%'  limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Provider.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public Provider getProviderById(Integer id) {
        List<Provider> list = template.query("select * from smbms_provider where `id` = ?" ,new Object[]{id},
                new BeanPropertyRowMapper(Provider.class));
        if (list!=null){
            return list.get(0);
        }else{
            return null;
        }
    }
}
