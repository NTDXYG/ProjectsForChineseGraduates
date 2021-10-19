package com.example.demo.service;

import com.example.demo.bean.Comment;
import com.example.demo.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDao dao;

    public int getCommentCountById(String id) {
        return dao.getCommentCountById(id);
    }

    public List getAllCommentsById(String id) {
        return dao.getAllCommentsById(id);
    }

    public void addComment(String openid, int goods_id, String content) {
        dao.addComment(openid,goods_id,content);
    }

    public int getCount() {
        return dao.getCount();
    }

    public List<Comment> getAllComments(int page, int limit) {
        return dao.getAllComments(page,limit);
    }

    public int delComment(int id) {
        return dao.delComment(id);
    }
}
