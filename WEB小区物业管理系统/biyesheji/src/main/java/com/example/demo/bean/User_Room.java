package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Room {
    Integer id;
    Integer user_id;
    Integer room_id;
    Date inTime;
    Date outTime;
    User user;
    Room room;
}
