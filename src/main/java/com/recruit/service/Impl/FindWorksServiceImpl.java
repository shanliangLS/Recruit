package com.recruit.service.Impl;

import com.recruit.entity.FindWorks;
import com.recruit.mapper.FindWorksMapper;
import com.recruit.service.FindWorksService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FindWorksServiceImpl implements FindWorksService {

    @Resource
    private FindWorksMapper findWorksMapper;

    @Override
    public List<FindWorks> getFindWorksListByIndustry(String industry) {
        return findWorksMapper.getFindWorksListByIndustry(industry);
    }
}
