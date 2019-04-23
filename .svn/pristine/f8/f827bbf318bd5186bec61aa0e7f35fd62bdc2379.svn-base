package com.recruit.mapper;

import com.recruit.entity.DeliverResume;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeliverResumeMapper {

    @Select("SELECT R.uid,R.rid,R.deliver_time,R.deliver_status,RI.job,RI.annual_salary,RI.location,RI.experience," +
            "RI.education,RI.release_time,CI.name as companyName,CI.industry " +
            "FROM resume R,recruit_info RI,company_info CI WHERE R.uid = #{uid} AND " +
            "R.rid = RI.id AND RI.cid = CI.cid ORDER BY R.deliver_status ASC")
    List<DeliverResume> getDeliverResumeListByUid(int uid);

    @Insert("INSERT resume(uid,rid,deliver_time,deliver_status) VALUES(#{uid},#{rid},NOW(),0)")
    boolean insertDeliverResumeByUidRid(@Param("uid") int uid, @Param("rid") int rid);

    @Select("SELECT uid,rid,deliver_time,deliver_status " +
            "FROM resume WHERE uid = #{uid} AND rid = #{rid} AND DATEDIFF(deliver_time,NOW()) < 7 AND " +
            "(deliver_status = 0 OR deliver_status = 1)")
    DeliverResume getDeliverResumeByUidRid(@Param("uid") int uid, @Param("rid") int rid);
}
