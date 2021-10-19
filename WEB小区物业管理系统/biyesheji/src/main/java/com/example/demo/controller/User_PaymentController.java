package com.example.demo.controller;

import com.example.demo.bean.*;
import com.example.demo.service.User_PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class User_PaymentController {
    @Autowired
    User_PaymentService service;

    @GetMapping("/api/getAllPaymentDetails")
    public ResBody getAllPaymentDetails(@RequestParam int page,
                                        @RequestParam int limit, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int count = 0;
        List<User_Payment> list = new ArrayList<>();
        if (user!=null){
            int user_id=user.getId();
            count = service.getCount(user_id);
            list= service.getAllPaymentDetails(page, limit,user_id);
        }else {
            count = service.getCount();
            list= service.getAllPaymentDetails(page, limit);
        }
        ResBody resBody = new ResBody();
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/findPaymentDetail")
    public ResBody findPaymentDetail(@RequestParam int page,
                                @RequestParam int limit,
                                @RequestParam String name) {
        int count = 0;
        List<User_Payment> list= new ArrayList<>();
        ResBody resBody = new ResBody();
        if (name.isEmpty()){
            count = service.getCount();
            list= service.getAllPaymentDetails(page, limit);
        }else {
            count = service.getCount(name);
            list= service.getAllPaymentDetails(page, limit,name);
        }
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/fenpeiPayment")
    public ResBody fenpeiPayment(@RequestBody User_Payment user_payment) {
        ResBody resBody = new ResBody();
        user_payment.setUser_id(user_payment.getId());
        int i = service.fenpei(user_payment.getUser_id(),user_payment.getPayment_id(),user_payment.getValue());
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("添加成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("添加失败");
        }
        return resBody;
    }

    @GetMapping("/api/jiaofei")
    public ResBody jiaofei(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.jiaofei(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("成功");
        }else {
            resBody.setCode(500);
            resBody.setMsg("失败");
        }
        return resBody;
    }
}
