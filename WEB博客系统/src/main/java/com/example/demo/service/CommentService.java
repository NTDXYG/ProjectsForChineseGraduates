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

    public int getCount() {
        return dao.getCount();
    }

    public List<Comment> getAllComments(int page, int limit) {
        return dao.getAllComments(page, limit);
    }

    public List<Comment> getCommentsByBid(int id) {
        return dao.getCommentsByBid(id);
    }

    public int addComment(Comment comment) {
        return dao.addComment(comment);
    }

    public int delComment(int id) {
        return dao.delComment(id);
    }

    public int editComment(int id) {
        return dao.editComment(id);
    }
}
