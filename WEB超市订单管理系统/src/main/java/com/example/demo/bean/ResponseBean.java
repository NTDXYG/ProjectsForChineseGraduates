package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean {
    // http 状态码
    private int code;
    // 返回信息
    private String msg;
    // 返回的数据
    private Object data;

}
