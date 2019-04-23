package com.recruit.service;

import com.recruit.entity.DeliverResume;

import java.util.List;

public interface DeliverResumeService {

    List<DeliverResume> getDeliverResumeListByUid(int uid);

    boolean insertDeliverResumeByUidRid(int uid,int rid);

    DeliverResume getDeliverResumeByUidRid(int uid,int rid);
}
