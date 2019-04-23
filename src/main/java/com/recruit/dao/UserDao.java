package com.recruit.dao;

import com.recruit.entity.User;

import java.util.List;

public interface UserDao {

    User getUserByEmail(String email);

    public List<User> getUserList();

    public List<User> getUserList(String email);

    public int add(User user);

    public int update(String email, User user);

    public int delete(String email);

    public int getNum(String str);
}
