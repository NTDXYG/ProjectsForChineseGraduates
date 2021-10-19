package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Integer id;
    String username;
    String password;
    String phone;
    Integer sex;
    Integer status;
    Car car;
    Room room;
}
