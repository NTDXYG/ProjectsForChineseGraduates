package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    Integer id;
    String user_openid;
    Integer goods_id;
    String content;
    Date time;
    WX_User wx_user;
    Goods goods;
}
