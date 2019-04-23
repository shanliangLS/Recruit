package com.recruit.service;

import com.recruit.entity.AdminData;
import com.recruit.entity.JobData;
import com.recruit.entity.PartJobData;

import java.util.List;

public interface AdminService {

    public AdminData getDataByLogin(String name,String password);

    /**
     *
     * @param jobData
     * @return
     */
    public boolean setJob(JobData jobData);
    public List<JobData> getJob();
    public boolean deleteJob(int id);

    /**
     *
     * @param partjobData
     * @return
     */
    public boolean setPartJob(PartJobData partjobData);
    public List<PartJobData> getPartJob();
    public boolean deletePartJob(int id);
}
