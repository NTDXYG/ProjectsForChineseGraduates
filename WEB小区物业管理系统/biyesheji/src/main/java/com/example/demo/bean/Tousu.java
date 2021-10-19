package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tousu {
    Integer id;
    String content;
    Integer status;
    Date time;
    Integer user_id;
    User user;
    String result;
}
