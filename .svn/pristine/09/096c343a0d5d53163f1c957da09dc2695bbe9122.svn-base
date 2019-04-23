package com.recruit.service.Impl;

import com.recruit.entity.AdminData;
import com.recruit.entity.JobData;
import com.recruit.entity.PartJobData;
import com.recruit.mapper.AdminMapper;
import com.recruit.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImp implements AdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public AdminData getDataByLogin(String name,String password){
        return adminMapper.getDataByLogin(name,password);
    }
    @Override
    public boolean setJob(JobData jobData){
        return adminMapper.setJob(jobData);
    }
    @Override
    public List<JobData> getJob(){
        return adminMapper.getJob();
    }
    @Override
    public boolean deleteJob(int id){
        return adminMapper.deleteJob(id);
    }

    /**
     *
     * @param partjobData
     * @return
     */
    @Override
    public boolean setPartJob(PartJobData partjobData){
        return adminMapper.setPartJob(partjobData);
    }
    @Override
    public List<PartJobData> getPartJob(){
        return adminMapper.getPartJob();
    }
    @Override
    public boolean deletePartJob(int id){
        return adminMapper.deletePartJob(id);
    }
}
