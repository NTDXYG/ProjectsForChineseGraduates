package com.example.demo.dao;

import com.example.demo.bean.Comment;
import com.example.demo.bean.Goods;
import com.example.demo.bean.Order;
import com.example.demo.bean.WX_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CommentDao {
    @Autowired
    JdbcTemplate template;
    public int getCommentCountById(String id) {
        int count = template.queryForObject("select count(*) from comment where goods_id = "+id, Integer.class);
        return count;
    }

    public List getAllCommentsById(String id) {
        List<Comment> list = template.query("select * from `comment` where goods_id = " + id ,
                new BeanPropertyRowMapper(Comment.class));
        if (list!=null){
            for (Comment comment:list){
                List<WX_User> wx_users = template.query("select * from user where open_id = '"+comment.getUser_openid()+"'"  ,
                        new BeanPropertyRowMapper(WX_User.class));
                List<Goods> goods = template.query("select * from goods where _id ="+comment.getGoods_id()  ,
                        new BeanPropertyRowMapper(Goods.class));
                comment.setWx_user(wx_users.get(0));
                comment.setGoods(goods.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public void addComment(String openid, int goods_id, String content) {
        template.update("insert into comment values(null,?,?,?,?)",
                openid,goods_id,content,new Date());
    }

    public int getCount() {
        int count = template.queryForObject("select count(*) from  `comment`", Integer.class);
        return count;
    }

    public List<Comment> getAllComments(int page, int limit) {
        List<Comment> list = template.query("select * from `comment` limit ?,?" ,new Object[]{(page-1)*limit,limit} ,
                new BeanPropertyRowMapper(Comment.class));
        if (list!=null){
            for (Comment comment:list){
                List<WX_User> wx_users = template.query("select * from user where open_id = '"+comment.getUser_openid()+"'"  ,
                        new BeanPropertyRowMapper(WX_User.class));
                List<Goods> goods = template.query("select * from goods where _id ="+comment.getGoods_id()  ,
                        new BeanPropertyRowMapper(Goods.class));
                comment.setWx_user(wx_users.get(0));
                comment.setGoods(goods.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int delComment(int id) {
        return template.update("DELETE from `comment` where id = ?",id);
    }
}
