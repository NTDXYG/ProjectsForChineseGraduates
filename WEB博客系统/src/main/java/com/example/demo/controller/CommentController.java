package com.example.demo.controller;

import com.example.demo.bean.Boke;
import com.example.demo.bean.Comment;
import com.example.demo.bean.ResBody;
import com.example.demo.service.CommentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService service;

    @GetMapping("/api/getAllComments")
    @RequiresAuthentication
    public ResBody getAllComments(@RequestParam int page,
                               @RequestParam int limit) {
        ResBody resBody = new ResBody();
        int count = service.getCount();
        List<Comment> list= service.getAllComments(page, limit);
        resBody.setCount(count);
        resBody.setData(list);
        resBody.setCode(0);
        return resBody;
    }

    @PostMapping("/api/addComment")
    public ResBody addComment(@RequestBody Comment comment) {
        ResBody resBody = new ResBody();
        comment.setCreate_time(new Date());
        int i = service.addComment(comment);
        if (i == 1){
            resBody.setCode(200);
            resBody.setMsg("评论成功");
        }else{
            resBody.setCode(500);
            resBody.setMsg("评论失败");
        }
        return resBody;
    }

    @GetMapping("/api/delComment")
    @RequiresRoles("admin")
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

    @GetMapping("/api/editComment")
    @RequiresRoles("admin")
    public ResBody editComment(@RequestParam int id) {
        ResBody resBody = new ResBody();
        int i = service.editComment(id);
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
