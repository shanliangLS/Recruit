package com.recruit.service.Impl;

import com.recruit.entity.IntensionData;
import com.recruit.mapper.IntensionDataMapper;
import com.recruit.mapper.UserMapper;
import com.recruit.service.IntensionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IntensionServiceImpl implements IntensionService {

    @Resource
    IntensionDataMapper intensionDataMapper;

    @Resource
    UserMapper userMapper;
    @Override
    public IntensionData getDataByEmail(String email){

        return intensionDataMapper.getDataByEmail(email);
    }

    @Override
    public boolean insertData(String email, IntensionData intensionData){
        long uid= (long) userMapper.getPara("id",email);
        return intensionDataMapper.insertData(uid,intensionData);
    }
    @Override
    public boolean updateData(String email, IntensionData intensionData){
        long uid= (long) userMapper.getPara("id",email);
        return intensionDataMapper.updateData(uid,intensionData);
    }
}
