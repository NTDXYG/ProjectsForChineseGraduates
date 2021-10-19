package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    Integer id;
    Integer goods_id;
    String user_openid;
    Integer count;
    Goods goods;
    WX_User wx_user;
}
