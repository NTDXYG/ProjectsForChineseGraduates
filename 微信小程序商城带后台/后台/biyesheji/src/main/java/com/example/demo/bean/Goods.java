package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    Integer _id;
    String image;
    String name;
    String price;
    Integer type_id;
    Type type;
}
