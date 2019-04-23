package com.recruit.controller;

import com.recruit.entity.User;
import com.recruit.entity.UserWithCode;
import com.recruit.service.MailService;
import com.recruit.service.RedisService;
import com.recruit.service.UserService;
import com.recruit.util.JavaUtil;
import com.recruit.util.JsonUtil;
import com.recruit.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";
    private static final String FAIL = "FAIL";

    @Autowired
    private UserService userservice;

    @Autowired
    private MailService mail;

    @Autowired
    private RedisService redisservice;

    /**
     * 添加用户
     *
     * @param email
     * @param password
     * @return json
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String add(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = new User(0, email, password); // id无用

        if (UserUtil.isEmail(user.getEmail())) {
            if (UserUtil.isPassword(user.getPassword())) {
                //邮箱和密码都合法
                if (userservice.getUserNumByEmail(user.getEmail()) != 0) {//邮箱已经被注册
                    return JsonUtil.returnPackage(FAIL, "该邮箱已经被注册过了!", null);
                } else {//邮箱未被注册
                    try {
                        String code = JavaUtil.getUUID();
                        UserWithCode userWithCode = new UserWithCode(user, code);
                        redisservice.setObject(email, userWithCode);
                        mail.sendActivationMail(user.getEmail(), user.getEmail(), code);//发送邮件
                    } catch (Exception e) {
                        e.printStackTrace();
                        return JsonUtil.returnPackage(FAIL, "邮件发送失败", null);
                    }
                }
            } else {//密码不合法
                return JsonUtil.returnPackage(FAIL, "邮箱不合法", null);
            }
        } else {//email不合法
            return JsonUtil.returnPackage(FAIL, "密码不合法", null);
        }
        return JsonUtil.returnPackage(OK, null, null);
    }
}
