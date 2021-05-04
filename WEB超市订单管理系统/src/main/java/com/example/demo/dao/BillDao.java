package com.example.demo.dao;

import com.example.demo.bean.Bill;
import com.example.demo.bean.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillDao {
    @Autowired
    JdbcTemplate template;

    public int getCount() {
        int count = template.queryForObject("select count(*) from smbms_bill", Integer.class);
        return count;
    }

    public List<Bill> findAllBills(int page, int limit) {
        List<Bill> list = template.query("select * from smbms_bill limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Bill.class));
        if (list!=null){
            for (Bill bill:list){
                List<Provider> provider = template.query("select * from smbms_provider where `id` = ?" ,new Object[]{bill.getProviderId()},
                        new BeanPropertyRowMapper(Provider.class));
                String pname = provider.get(0).getProName();
                bill.setProviderName(pname);
            }
            return list;
        }else{
            return null;
        }
    }

    public int addBill(Bill bill) {
        return template.update("insert into smbms_bill values(null,?,?,?,?,?,?,?,?,?,?,?,?)",
                bill.getBillCode(),bill.getProductName(),bill.getProductDesc(),bill.getProductUnit(),
                bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),bill.getCreatedBy(),
                bill.getCreationDate(),bill.getModifyBy(),bill.getModifyDate(),bill.getProviderId());
    }

    public int updateBill(Bill bill) {
        return template.update("update smbms_bill set `billCode` = ? ,`productName` = ?,`productDesc` = ?," +
                        "`productUnit` = ?,`productCount` = ?,`totalPrice` = ?,`isPayment` = ?,`createdBy` = ?,`creationDate` = ?," +
                        "`modifyBy` = ?, `modifyDate` = ? ,`providerId` = ? where id = ?",
                bill.getBillCode(),bill.getProductName(),bill.getProductDesc(),bill.getProductUnit(),
                bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),bill.getCreatedBy(),
                bill.getCreationDate(),bill.getModifyBy(),bill.getModifyDate(),bill.getProviderId(),
                bill.getId());
    }

    public int delBill(int id) {
        return template.update("DELETE from smbms_bill where id=?",id);
    }

    public List<Bill> findBill(int page, int limit, String name) {
        List<Bill> list = template.query("select * from smbms_bill where `billCode` like '%"+name+"%'  limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Bill.class));
        if (list!=null){
            for (Bill bill:list){
                List<Provider> provider = template.query("select * from smbms_provider where `id` = ?" ,new Object[]{bill.getProviderId()},
                        new BeanPropertyRowMapper(Provider.class));
                String pname = provider.get(0).getProName();
                bill.setProviderName(pname);
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from smbms_bill where `billCode` like '%"+name+"%'", Integer.class);
        return count;
    }

    public Bill getBillById(Integer id) {
        List<Bill> list = template.query("select * from smbms_bill where `id` = ?" ,new Object[]{id},
                new BeanPropertyRowMapper(Bill.class));
        if (list!=null){
            return list.get(0);
        }else{
            return null;
        }
    }
}
