package com.recruit.mapper;

import com.recruit.entity.IntensionData;
import com.recruit.entity.PersonalData;
import com.recruit.entity.User;
import org.apache.ibatis.annotations.Insert;

public interface ResumeMapper {


//    @Insert("insert into users(email, password) values(#{email}, #{password})")
//    public int add(User user);
    @Insert("")
    public boolean insertData(PersonalData personalData, IntensionData intensionData);
}
