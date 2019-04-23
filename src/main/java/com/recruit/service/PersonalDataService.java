package com.recruit.service;

import com.recruit.entity.Left_H_Data;
import com.recruit.entity.PersonalData;

public interface PersonalDataService {

    PersonalData getDateByEmail(String email);
    boolean insertData(String email,PersonalData personalData);
    boolean updateData(String email,PersonalData personalData);
    public Left_H_Data getDateByUid(int uid);
}
