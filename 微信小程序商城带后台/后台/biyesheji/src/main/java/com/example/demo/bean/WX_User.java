package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WX_User {
    String openId;
    Date createTime;
    Date lastVisitTime;
    String sessionKey;
    String city;
    String province;
    String country;
    String avatarUrl;
    Integer gender;
    String nickName;
}
