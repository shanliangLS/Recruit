package com.recruit.mapper;


import com.recruit.entity.AdminData;
import com.recruit.entity.JobData;
import com.recruit.entity.PartJobData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {

    @Select("select * from admin where name=#{name} and password=#{password}")
    public AdminData getDataByLogin(@Param("name") String name, @Param("password") String password);


    /**
     *
     * @param jobData
     * @return
     */
    @Insert("insert into job_fair(title,content,release_time) values(#{title},#{content},#{release_time})")
    public boolean setJob(JobData jobData);

    @Select("select * from job_fair")
    public List<JobData> getJob();
    @Delete("delete from job_fair where id=#{id}")
    public boolean deleteJob(int id);

    /**
     *partjobdata
     */
    @Insert("insert into part_time_job(title,content,release_time) values(#{title},#{content},#{release_time})")
    public boolean setPartJob(PartJobData partjobData);
    @Select("select * from part_time_job")
    public List<PartJobData> getPartJob();
    @Delete("delete from part_time_job where id=#{id}")
    public boolean deletePartJob(int id);
}
