package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    Integer id;
    String name;
    String area;
    Integer status;
    Integer danyuan_id;
    Danyuan danyuan;
}
