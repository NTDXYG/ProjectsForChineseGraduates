package com.example.demo.controller;

import com.example.demo.bean.Gonggao;
import com.example.demo.bean.User;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    IndexService service;
    @Autowired
    GonggaoService gonggaoService;
    @Autowired
    RepairService repairService;
    @Autowired
    TousuService tousuService;
    @Autowired
    UserService userService;
    @Autowired
    User_PaymentService user_paymentService;
    @Autowired
    RoomService roomService;
    @Autowired
    CarService carService;

    @GetMapping("/login")
    public String login() {
        return "page/template/login";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/console")
    public String console(Model model) {
        int gonggaoCount = gonggaoService.getCount();
        int repairCount = repairService.getCount();
        int tousuCount = tousuService.getCount();
        int userCount = userService.getCount();
        int roomCount = roomService.getCount();
        int roomFreeCount = roomService.getFreeCount();
        int carCount = carService.getCount();
        int carFreeCount = carService.getFreeCount();
        int paymentCount = user_paymentService.getCount();
        int paymentFreeCount = user_paymentService.getFreeCount();
        model.addAttribute("gonggaoCount",gonggaoCount);
        model.addAttribute("repairCount",repairCount);
        model.addAttribute("tousuCount",tousuCount);
        model.addAttribute("userCount",userCount);
        model.addAttribute("roomCount",roomCount);
        model.addAttribute("roomFreeCount",roomFreeCount);
        model.addAttribute("carCount",carCount);
        model.addAttribute("carFreeCount",carFreeCount);
        model.addAttribute("paymentCount",paymentCount);
        model.addAttribute("paymentFreeCount",paymentFreeCount);
        return "page/console/console";
    }

    @GetMapping("/tpl-theme")
    public String tpl(){
        return "page/tpl/tpl-theme";
    }

    @GetMapping("/tpl-password")
    public String password(){
        return "page/tpl/tpl-password";
    }

    @GetMapping("/tpl-user-password")
    public String reader_password(){
        return "page/tpl/tpl-user-password";
    }

    @GetMapping("/tpl-note")
    public String note(){
        return "page/tpl/tpl-note";
    }

    @GetMapping("/user-info")
    public String userinfo(){
        return "page/template/user-info";
    }

    @GetMapping("/building")
    public String building(){
        return "page/template/building";
    }

    @GetMapping("/danyuan")
    public String danyuan(){
        return "page/template/danyuan";
    }

    @GetMapping("/room")
    public String room(){
        return "page/template/room";
    }

    @GetMapping("/gonggao")
    public String gonggao(){
        return "page/template/gonggao";
    }

    @GetMapping("/repair")
    public String repair(){
        return "page/template/repair";
    }

    @GetMapping("/tousu")
    public String tousu(){
        return "page/template/tousu";
    }

    @GetMapping("/payment")
    public String payment(){
        return "page/template/payment";
    }

    @GetMapping("/car")
    public String car(){
        return "page/template/car";
    }

    @GetMapping("/user")
    public String user(){
        return "page/template/user";
    }

    @GetMapping("/user/login")
    public String userlogin(){
        return "page/system/login";
    }

    @GetMapping("/user/index")
    public String userindex(){
        return "page/system/index";
    }

    @GetMapping("/user/console")
    public String userconsole(Model model,HttpSession session){
        User user = (User) session.getAttribute("user");
        int sex = user.getSex();
        if (sex == 0){
            model.addAttribute("sex","男");
        }else {
            model.addAttribute("sex","女");
        }
        User userInfo = userService.getUserById(user.getId());
        if (userInfo.getCar()!=null){
            model.addAttribute("car",userInfo.getCar().getName());
        } else {
            model.addAttribute("car","暂无");
        }
        if (userInfo.getRoom()!=null){
            model.addAttribute("room",userInfo.getRoom().getDanyuan().getName()+userInfo.getRoom().getName());
        } else {
            model.addAttribute("room","暂无");
        }
        Gonggao gonggao = gonggaoService.getGonggao();
        int repairCount = repairService.getCount();
        int tousuCount = tousuService.getCount();
        int paymentCount = user_paymentService.getCount();
        int userRepair = repairService.getCountByUserId(user.getId());
        int userTousu = tousuService.getCountByUserId(user.getId());
        int userPayment = user_paymentService.getCountByUserId(user.getId());
        model.addAttribute("gonggao",gonggao);
        model.addAttribute("userTousu",userTousu);
        model.addAttribute("userRepair",userRepair);
        model.addAttribute("userPayment",userPayment);
        model.addAttribute("repairCount",repairCount);
        model.addAttribute("tousuCount",tousuCount);
        model.addAttribute("paymentCount",paymentCount);
        return "page/system/console";
    }

    @GetMapping("/paymentDetail")
    public String paymentDetail(){
        return "page/template/paymentDetail";
    }
}
