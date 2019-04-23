package com.recruit.service;

import com.recruit.entity.IntensionData;

public interface IntensionService {

    IntensionData getDataByEmail(String emial);



    boolean insertData(String email, IntensionData intensionData);

    boolean updateData(String email, IntensionData intensionData);
}
