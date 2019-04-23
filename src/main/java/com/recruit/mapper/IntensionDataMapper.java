package com.recruit.mapper;

import com.recruit.entity.IntensionData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IntensionDataMapper {
//    annual_salary desired_industry desired_location desired_job
    @Select("select intension.annual_salary,intension.desired_industry,intension.desired_location,intension.desired_job from users,intension where users.email=#{emial} and users.id=intension.uid")
    public IntensionData getDataByEmail(String email);
//    annual_salary,desired_industry,desired_location,desired_job
    @Insert("insert into intension(uid,annual_salary,desired_industry,desired_location,desired_job) values(#{uid},#{intensionData.annual_salary},#{intensionData.desired_industry},#{intensionData.desired_location},#{intensionData.desired_job})")
    public boolean insertData(@Param("uid") long uid, @Param("intensionData") IntensionData intensionData);

    @Update("update intension set annual_salary=#{intensionData.annual_salary},desired_industry=#{intensionData.desired_industry},desired_location=#{intensionData.desired_location},desired_job=#{intensionData.desired_job} where uid=#{uid}")
    public boolean updateData(@Param("uid") long uid,@Param("intensionData") IntensionData intensionData);
}
