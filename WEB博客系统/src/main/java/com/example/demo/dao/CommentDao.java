package com.example.demo.dao;

import com.example.demo.bean.Boke;
import com.example.demo.bean.Cata;
import com.example.demo.bean.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDao {
    @Autowired
    JdbcTemplate template;

    public int getCount() {
        int count = template.queryForObject("select count(*) from comment", Integer.class);
        return count;
    }

    public List<Comment> getAllComments(int page, int limit) {
        List<Comment> list = template.query("select * from comment limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Comment.class));
        if (list!=null){
            for (Comment c:list){
                List<Boke> bokeList = template.query("select * from boke where `id` = ?" ,new Object[]{c.getB_id()},
                        new BeanPropertyRowMapper(Boke.class));
                c.setBoke(bokeList.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public List<Comment> getCommentsByBid(int id) {
        List<Comment> list = template.query("select * from comment where b_id = ?" ,new Object[]{id},
                new BeanPropertyRowMapper(Comment.class));
        if (list != null){
            return list;
        }else {
            return null;
        }
    }

    public int addComment(Comment comment) {
        return template.update("insert into comment values(null,?,?,?,?,?)",
                comment.getComment(),comment.getEmail(),comment.getNick(),comment.getCreate_time(),comment.getB_id());
    }

    public int delComment(int id) {
        return template.update("DELETE from comment where id = ?",id);
    }

    public int editComment(int id) {
        return template.update("update comment set `comment` = ? where id = ?",
                "****",id);
    }
}
