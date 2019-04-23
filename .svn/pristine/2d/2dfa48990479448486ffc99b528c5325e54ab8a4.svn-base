package com.recruit.controller;

import com.recruit.entity.UserWithCode;
import com.recruit.service.RedisService;
import com.recruit.service.UserService;
import com.recruit.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/activation")
public class ActivationController {
    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/email")
    public String activationByEmail(@RequestParam("email") String email, @RequestParam("code") String code) {
        if (UserUtil.isEmail(email)) {
            try {
                UserWithCode userWithCode = (UserWithCode) redisService.getObject(email);
                if (userWithCode == null) {
                    return "/login";
                }
                if (userWithCode.getCode().equals(code)) {
                    userService.add(userWithCode.getUser());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "/login";
    }
}
