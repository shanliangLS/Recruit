package com.recruit.dao.Impl;

import com.recruit.dao.UserDao;
import com.recruit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByEmail(String email) {
        List<User> list = jdbcTemplate.query("select * from users where email = ?", new Object[]{email}, new BeanPropertyRowMapper(User.class));
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<User> getUserList() {
        List<User> list = jdbcTemplate.query("SELECT * FROM users", new Object[]{}, new BeanPropertyRowMapper(User.class));
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }
    public List<User> getUserList(String email) {

        List<User> list = jdbcTemplate.query("select * from users where email = ?", new Object[]{email}, new BeanPropertyRowMapper(User.class));
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    @Override
    public int add(User user) {
        return jdbcTemplate.update("INSERT INTO users(email, password) values(?, ?)",
                user.getEmail(), user.getPassword());
    }

    @Override
    public int update(String email, User user) {
        return jdbcTemplate.update("UPDATE users SET password = ? WHERE email=?",
                email, user.getPassword());
    }

    @Override
    public int delete(String email) {
        return jdbcTemplate.update("DELETE FROM users WHERE email = ? ", email);
    }

    @Override
    public int getNum(String str){
//        return jdbcTemplate.query();

        return 0;
    }
}
