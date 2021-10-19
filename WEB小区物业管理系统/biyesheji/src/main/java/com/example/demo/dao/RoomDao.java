package com.example.demo.dao;

import com.example.demo.bean.Danyuan;
import com.example.demo.bean.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomDao {
    @Autowired
    JdbcTemplate template;
    public int getCount() {
        int count = template.queryForObject("select count(*) from room", Integer.class);
        return count;
    }

    public List<Room> getAllRooms(int page, int limit) {
        List<Room> list = template.query("select * from room limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Room.class));
        if (list!=null){
            for (Room room:list){
                List<Danyuan> danyuan = template.query("select * from danyuan where id = ?" ,new Object[]{room.getDanyuan_id()},
                        new BeanPropertyRowMapper(Danyuan.class));
                room.setDanyuan(danyuan.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int addRoom(Room room) {
        return template.update("insert into room values(null,?,?,0,?)",
                room.getName(),room.getArea(),room.getDanyuan_id());
    }

    public int updateRoom(Room room) {
        return template.update("update room set `name` = ? ,`area` = ? ,`danyuan_id` = ? where id = ?",
                room.getName(),room.getArea(),room.getDanyuan_id(),room.getId());
    }

    public int delRoom(int id) {
        return template.update("DELETE from room where id=?",id);
    }

    public List<Room> findRoom(int page, int limit, String name) {
        List<Room> list = template.query("select * from room where status = "+Integer.parseInt(name)+" limit ?,?" ,new Object[]{(page-1)*limit,limit},
                new BeanPropertyRowMapper(Room.class));
        if (list!=null){
            for (Room room:list){
                List<Danyuan> danyuan = template.query("select * from danyuan where id = ?" ,new Object[]{room.getDanyuan_id()},
                        new BeanPropertyRowMapper(Danyuan.class));
                room.setDanyuan(danyuan.get(0));
            }
            return list;
        }else{
            return null;
        }
    }

    public int getCount(String name) {
        int count = template.queryForObject("select count(*) from room where name like '%"+name+"%' ", Integer.class);
        return count;
    }

    public List<Room> getAllFreeRooms(int danyuan_id) {
        List<Room> list = template.query("select * from room where status = 0 and danyuan_id = " +danyuan_id,
                new BeanPropertyRowMapper(Room.class));
        if (list!=null){
            return list;
        }else{
            return null;
        }
    }

    public int getFreeCount() {
        int count = template.queryForObject("select count(*) from room where status = 0", Integer.class);
        return count;
    }
}
