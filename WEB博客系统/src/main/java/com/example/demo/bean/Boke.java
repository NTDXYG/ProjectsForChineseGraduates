package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boke {
    int id;
    String title;
    String content;
    Date create_time;
    int c_id;
    Cata cata;
}
