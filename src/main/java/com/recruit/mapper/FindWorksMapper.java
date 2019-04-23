package com.recruit.mapper;

import com.recruit.entity.FindWorks;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FindWorksMapper {

    @Select("SELECT RI.id as riId,RI.cid,RI.job,RI.annual_salary,RI.location,RI.experience,RI.education," +
            "RI.job_info,RI.release_time,CI.name as companyName,CI.industry,CI.linkman,CI.phone " +
            "FROM company_info CI,recruit_info RI WHERE RI.cid = CI.cid AND CI.industry LIKE #{industry}")
    List<FindWorks> getFindWorksListByIndustry(String industry);
}
