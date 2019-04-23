package com.recruit.mapper;

import com.recruit.entity.IntensionData;
import com.recruit.entity.Left_H_Data;
import com.recruit.entity.PersonalData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PersonalDataMapper {




    @Select("SELECT user_info.name,user_info.birthday,user_info.education,user_info.college,user_info.major,user_info.work FROM users,user_info WHERE users.email = #{email} and users.id=user_info.uid")
    public PersonalData getPersonalDataByEmail(String email);


    //    @Insert("insert into users(email, password) values(#{email}, #{password})")
//    public int add(User user);



    @Insert("insert into user_info(uid,name,birthday,education,college,major,work) values(#{uid},#{personalData.name},#{personalData.birthday},#{personalData.education},#{personalData.college},#{personalData.major},#{personalData.work})")
//    where uid=(select id from users where email=#{email})
    public boolean insertData(@Param("uid") long uid,@Param("personalData") PersonalData personalData);

    @Update("update user_info set name=#{personalData.name},birthday=#{personalData.birthday},education=#{personalData.education},college=#{personalData.college},major=#{personalData.major},work=#{personalData.work} where uid=#{uid}")
    public boolean updateData(@Param("uid") long uid,@Param("personalData") PersonalData personalData);

    @Select("select users.email,user_info.name,(YEAR(CURDATE())-YEAR(birthday)) AS age,education,college,major,work from users,user_info where users.id=#{uid} and user_info.uid=#{uid}")
    public Left_H_Data getPersonalDataByUid(int uid);
}
