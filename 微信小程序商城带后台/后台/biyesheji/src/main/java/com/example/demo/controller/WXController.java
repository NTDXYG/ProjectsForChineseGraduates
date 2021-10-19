package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.ResBody;
import com.example.demo.bean.Type;
import com.example.demo.bean.WX_User;
import com.example.demo.service.WX_UserService;
import com.example.demo.util.WechatUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class WXController {
    @Autowired
    WX_UserService service;

    @GetMapping("/api/getUsers")
    public ResBody getUsers(@RequestParam int page,
                               @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<WX_User> list= service.getUsers(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("wx/login")
    public ResBody user_login(@RequestParam(value = "code", required = false) String code,
                              @RequestParam(value = "rawData", required = false) String rawData,
                              @RequestParam(value = "signature", required = false) String signature,
                              @RequestParam(value = "encrypteData", required = false) String encrypteData,
                              @RequestParam(value = "iv", required = false) String iv) {
        // 用户非敏感信息：rawData
        // 签名：signature
        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appi + appsecret + code
        JSONObject SessionKeyOpenId = WechatUtil.getSessionKeyOrOpenId(code);
        // 3.接收微信接口服务 获取返回的参数
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");
        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            return new ResBody(500, "签名校验失败",0, null);
        }
        // 5.根据返回的User实体类，判断用户是否是新用户，是的话，将用户信息存到数据库；不是的话，更新最新登录时间
        WX_User user = service.selectById(openid);
        if (user == null) {
            // 用户信息入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");

            user = new WX_User();
            user.setOpenId(openid);
            user.setCreateTime(new Date());
            user.setLastVisitTime(new Date());
            user.setSessionKey(sessionKey);
            user.setCity(city);
            user.setProvince(province);
            user.setCountry(country);
            user.setAvatarUrl(avatarUrl);
            user.setGender(Integer.parseInt(gender));
            user.setNickName(nickName);

            service.insert(user);
        } else {
            // 已存在，更新用户登录时间
            user.setLastVisitTime(new Date());
            service.updateById(user);
        }

        ResBody result = new ResBody(200, openid, 0, null );
        return result;
    }

}
