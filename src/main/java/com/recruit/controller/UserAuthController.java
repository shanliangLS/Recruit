package com.recruit.controller;

import com.recruit.entity.User;
import com.recruit.service.PersonalDataService;
import com.recruit.service.UserService;
import com.recruit.util.JsonUtil;
import com.recruit.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/auth")
@Controller
public class UserAuthController {
    private static final String OK = "OK";
    private static final String ERROR = "ERROR";
    private static final String FAIL = "FAIL";

    @Autowired
    private UserService userService;

    @Autowired
    private PersonalDataService personalDataService;
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)//, produces = "application/json;charset=utf-8"
    public String userLogin(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) {
        User user = new User(0, email, password); // id无用
        if (!UserUtil.isUser(user)) {
            return JsonUtil.returnPackage(ERROR, "邮箱或密码格式错误", null);
        }
//        String email = user.getEmail();
        User user1;
        try {
            user1 = userService.getUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR, "链接数据库失败", null);
        }
        if (UserUtil.equals(user, user1)) {

            if(personalDataService.getDateByEmail(email)==null){

                request.getSession().setAttribute("id", user1.getId());
                request.getSession().setAttribute("email", user1.getEmail());
                request.getSession().setAttribute("isInfo", "0");
                request.getSession().setAttribute("model", "0");//求职者登录
                request.getSession().setMaxInactiveInterval(3600 * 5);
                return JsonUtil.returnPackage(OK, null, null);
            }else{
                request.getSession().setAttribute("id", user1.getId());
                request.getSession().setAttribute("email", user1.getEmail());
                request.getSession().setAttribute("isInfo", "1");
                request.getSession().setAttribute("model", "0");//求职者登录
                request.getSession().setMaxInactiveInterval(3600 * 5);
                return JsonUtil.returnPackage(OK, null, null);
            }

        } else {
            return JsonUtil.returnPackage(FAIL, "账号名或密码错误", null);
        }
    }

    @RequestMapping(value = "/logout")
    public String userLogout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
