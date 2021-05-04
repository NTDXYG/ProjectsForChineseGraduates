package com.example.demo.controller;

import com.example.demo.bean.Bill;
import com.example.demo.bean.ResBody;
import com.example.demo.bean.User;
import com.example.demo.service.BillService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class BillController {
    @Autowired
    BillService service;

    @GetMapping("/api/getAllBills")
    @RequiresAuthentication
    public ResBody getAllBills(@RequestParam int page,
                               @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Bill> list= service.findAllBills(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addBill")
    @RequiresRoles("admin")
    public ResBody addBill(@RequestBody Bill bill,HttpSession session) {
        ResBody resBody = new ResBody();
        User user = (User) session.getAttribute("user");
        bill.setCreatedBy(user.getId());
        bill.setCreationDate(new Date());
        int i = service.addBill(bill);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @PostMapping("/api/updateBill")
    @RequiresRoles("admin")
    public ResBody updateBill(@RequestBody Bill bill, HttpSession session) {
        ResBody resBody = new ResBody();
        User user = (User) session.getAttribute("user");
        Bill b = service.getBillById(bill.getId());
        bill.setCreatedBy(b.getCreatedBy());
        bill.setCreationDate(b.getCreationDate());
        bill.setModifyBy(user.getId());
        bill.setModifyDate(new Date());
        int i = service.updateBill(bill);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("修改成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("修改失败");
        }
        return resBody;
    }

    @GetMapping("/api/delBill")
    @RequiresRoles("admin")
    public ResBody delBill(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delBill(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

    @GetMapping("/api/findBill")
    @RequiresAuthentication
    public ResBody findBill(@RequestParam int page,
                            @RequestParam int limit,
                            @RequestParam String name) {
        ResBody resBody = new ResBody();
        int count = service.getCount(name);
        List<Bill> list= service.findBill(page, limit,name);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

}
