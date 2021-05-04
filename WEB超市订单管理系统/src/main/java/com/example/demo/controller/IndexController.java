package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("/tpl-reader-password")
    public String reader_password(){
        return "page/tpl/tpl-reader-password";
    }
    @GetMapping("/tpl-note")
    public String note(){
        return "page/tpl/tpl-note";
    }

    @GetMapping("/user-info")
    public String userinfo(){
        return "page/template/user-info";
    }

    @GetMapping("/bill")
    public String book(){
        return "page/template/bill";
    }

    @GetMapping("/provider")
    public String provider(){
        return "page/template/provider";
    }

    @GetMapping("/user")
    public String user(){
        return "page/template/user";
    }
}
