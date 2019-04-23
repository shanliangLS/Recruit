package com.recruit.controller;

import com.recruit.entity.UserInfo;
import com.recruit.service.UserInfoService;
import com.recruit.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";
    private static final String FAIL = "FAIL";

    /*@Autowired
    private UserInfoService userInfoService;*/

    /**
     * 根据uid查询用户信息
     * @param uid
     * @return
     */
    /*@RequestMapping(value = "userInfo/{uid}", method = RequestMethod.GET)
    public String getUserInfoByUid(@PathVariable(value = "uid") int uid) {
        try {
            UserInfo userInfo = userInfoService.getUserInfoByUid(uid);
            return JsonUtil.returnPackage(OK, "", userInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR, "连接服务器失败", null);
        }
    }*/
}
