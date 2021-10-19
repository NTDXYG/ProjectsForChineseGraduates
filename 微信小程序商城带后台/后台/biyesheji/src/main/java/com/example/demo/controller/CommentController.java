package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Comment;
import com.example.demo.bean.Order;
import com.example.demo.bean.ResBody;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService service;

    @GetMapping("/wx/getCommentCountById")
    public int getCommentCountById(@RequestParam String id){
        int count = service.getCommentCountById(id);
        return count;
    }

    @GetMapping("/wx/getAllCommentsById")
    public List getAllCommentsById(@RequestParam String id){
        List list = service.getAllCommentsById(id);
        return list;
    }

    @GetMapping("/wx/addComment")
    public int addComment(@RequestParam String openid,@RequestParam int goods_id,
                           @RequestParam String content){
        service.addComment(openid,goods_id,content);
        return 200;
    }

    @GetMapping("/api/getAllComments")
    public ResBody getAllComments(@RequestParam int page,
                                @RequestParam int limit){
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Comment> list= service.getAllComments(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @GetMapping("/api/delComment")
    public ResBody delComment(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.delComment(id);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("删除成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("删除失败");
        }
        return resBody;
    }

}
