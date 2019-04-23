package com.recruit.service.Impl;

import com.recruit.entity.Left_H_Data;
import com.recruit.entity.PersonalData;
import com.recruit.entity.User;
import com.recruit.mapper.PersonalDataMapper;
import com.recruit.mapper.UserMapper;
import com.recruit.service.PersonalDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    @Resource
    PersonalDataMapper personalDataMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public PersonalData getDateByEmail(String email){
        return personalDataMapper.getPersonalDataByEmail(email);
    }
    @Override
    public Left_H_Data getDateByUid(int uid){
        return personalDataMapper.getPersonalDataByUid(uid);
    }
    @Override
    public boolean insertData(String email,PersonalData personalData){
        long uid= (long) userMapper.getPara("id",email);
        return personalDataMapper.insertData(uid,personalData);
    }

    @Override
    public boolean updateData(String email,PersonalData personalData){
        long uid= (long) userMapper.getPara("id",email);
        return personalDataMapper.updateData(uid,personalData);
    }
}
