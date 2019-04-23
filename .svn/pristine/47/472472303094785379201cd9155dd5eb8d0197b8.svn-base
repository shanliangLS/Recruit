package com.recruit.service.Impl;

import com.recruit.entity.User;
import com.recruit.mapper.UserMapper;
import com.recruit.service.UserService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName cn.saytime.service.impl.UserServiceImpl
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

//    public List<User> getUserList(String email) {
//        return userMapper.getUserList(email);
//    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int update(String email, User user) {
        return userMapper.update(email, user);
    }

    @Override
    public int delete(String email) {
        return userMapper.delete(email);
    }

    @Override
    public int getUserNumByEmail(String email) {
        if (userMapper.getUserListByEmail(email)==null){
            return 0;
        }else{
            return userMapper.getUserListByEmail(email).size();
        }

    }

    @Override
    public boolean rePassword(int id,String password){
        return  userMapper.rePassword(id, password);
    }
}
