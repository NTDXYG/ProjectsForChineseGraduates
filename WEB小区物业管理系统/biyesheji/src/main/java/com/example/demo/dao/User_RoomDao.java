package com.example.demo.dao;

import com.example.demo.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class User_RoomDao {
    @Autowired
    JdbcTemplate template;
    public int getRoomRecordCount(int id) {
        int count = template.queryForObject("select count(*) from `user_room` where room_id = ?",new Object[]{id}, Integer.class);
        return count;
    }

    public List<User_Room> findRoomRecordById(int id, int page, int limit) {
        List<User_Room> list = template.query("select * from `user_room` where room_id = ? limit ?,?" ,new Object[]{id,(page-1)*limit,limit},
                new BeanPropertyRowMapper(User_Room.class));
        if (list!=null){
            for (User_Room user_room:list){
                List<User> user = template.query("select * from `user` where id = ?" ,new Object[]{user_room.getUser_id()},
                        new BeanPropertyRowMapper(User.class));
                List<Room> room = template.query("select * from `room` where id = ?" ,new Object[]{user_room.getRoom_id()},
                        new BeanPropertyRowMapper(Room.class));
                user_room.setUser(user.get(0));
                user_room.setRoom(room.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int findRoom(Integer user_id) {
        int count = template.queryForObject("SELECT count(*) FROM user_room where user_id = ? and outTime is null",
                new Object[]{user_id}, Integer.class);
        return count;
    }

    public void outRoom(Integer user_id) {
        List<User_Room> list = template.query("select * from `user_room` where user_id = ? and  outTime is null" ,new Object[]{user_id},
                new BeanPropertyRowMapper(User_Room.class));
        template.update("update room set `status` = 0 where id = ?",
                list.get(0).getRoom_id());
        template.update("update user_room set `outTime` = ? where user_id = ? and outTime is null",
                new Date(),user_id);
    }

    public int fenpei(Integer user_id, Integer room_id) {
        template.update("update room set `status` = 1 where id = ?",
                room_id);
        return template.update("insert into user_room values(null,?,?,?,null)",
                user_id,room_id,new Date());
    }

    public int stopRoomByUserId(int id) {
        List<User_Room> list = template.query("select * from `user_room` where user_id = ? and  outTime is null" ,new Object[]{id},
                new BeanPropertyRowMapper(User_Room.class));
        if (list.isEmpty()){
            return 1;
        }
        template.update("update room set `status` = 0 where id = ?",
                list.get(0).getRoom_id());
        return template.update("update user_room set `outTime` = ? where user_id = ? and outTime is null",
                new Date(),id);
    }
}
