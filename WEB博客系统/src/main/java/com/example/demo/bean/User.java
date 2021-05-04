package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id; //id
    private String code; //用户编码
    private String username; //用户名称
    private String password; //用户密码
    private Integer gender;  //性别
    private String phone;   //电话
    private String address; //地址
    private String role;    //用户角色名称
    private String permission;    //用户权限名称
}
