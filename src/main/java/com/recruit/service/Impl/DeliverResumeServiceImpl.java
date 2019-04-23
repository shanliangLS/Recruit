package com.recruit.service.Impl;

import com.recruit.entity.DeliverResume;
import com.recruit.mapper.DeliverResumeMapper;
import com.recruit.service.DeliverResumeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeliverResumeServiceImpl implements DeliverResumeService {

    @Resource
    private DeliverResumeMapper deliverResumeMapper;

    @Override
    public List<DeliverResume> getDeliverResumeListByUid(int uid) {
        return deliverResumeMapper.getDeliverResumeListByUid(uid);
    }

    @Override
    public boolean insertDeliverResumeByUidRid(int uid,int rid) {
        return deliverResumeMapper.insertDeliverResumeByUidRid(uid,rid);
    }

    @Override
    public DeliverResume getDeliverResumeByUidRid(int uid,int rid) {
        return deliverResumeMapper.getDeliverResumeByUidRid(uid,rid);
    }
}
