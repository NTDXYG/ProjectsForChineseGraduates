package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

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

    @GetMapping("/goods")
    public String goods(){
        return "page/template/goods";
    }

    @GetMapping("/type")
    public String type(){
        return "page/template/type";
    }

    @GetMapping("/order")
    public String order(){
        return "page/template/order";
    }

    @GetMapping("/user")
    public String user(){
        return "page/template/user";
    }

    @GetMapping("/comment")
    public String comment(){
        return "page/template/comment";
    }
}
