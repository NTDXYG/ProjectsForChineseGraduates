package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    int id;
    String comment;
    String email;
    String nick;
    Date create_time;
    int b_id;
    Boke boke;
}
