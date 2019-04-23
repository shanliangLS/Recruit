package com.recruit.controller;

import com.recruit.entity.User;
import com.recruit.service.RedisService;
import com.recruit.service.UserService;
import com.recruit.util.JsonUtil;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";
    private static final String FAIL = "FAIL";

    @Autowired
    private UserService userservice;

    @Autowired
    private RedisService redisservice;


    /**
     * 测试
     */
    @RequestMapping(value = "ceshi", method = RequestMethod.GET)
    public String test() {

        redisservice.setObject("name", "shikaifeng");
        return JsonUtil.returnPackage(OK, "", redisservice.getObject("name"));
    }

    @RequestMapping(value = "ceshi1", method = RequestMethod.GET)
    public String test1() {


        return JsonUtil.returnPackage(OK, "", userservice.getUserNumByEmail("feng45k@qq.com"));
    }

    @RequestMapping(value = "ceshi2", method = RequestMethod.GET)
    public String test2() {

        List<User> users = userservice.getUserList();
        return JsonUtil.returnPackage(OK, null, users);

    }


    /**
     * 根据email查询用户
     *
     * @param email
     * @return json
     */
    @RequestMapping(value = "user/{email}", method = RequestMethod.GET)
    public String getUserByEmail(@PathVariable(value = "email") String email) {
        try {
            User user = userservice.getUserByEmail(email);
            return JsonUtil.returnPackage(OK, "", user);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR, "链接服务器失败", null);
        }
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String getUserList() {
        try {
            List<User> users = userservice.getUserList();


            return JsonUtil.returnPackage(OK, null, users);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR, "链接数据库失败", null);
        }
    }


    /**
     * 根据id删除用户
     *
     * @param email
     * @return json
     */
    @RequestMapping(value = "user/{email}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "email") String email) {
        try {
            int res = userservice.delete(email);
            if (res < 0) {
                return JsonUtil.returnPackage(FAIL, null, null);
            } else {
                return JsonUtil.returnPackage(OK, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR, "链接数据库失败", null);
        }
    }




    /**
     * 根据id修改用户信息
     *
     * @param user
     * @return status
     */
    @RequestMapping(value = "repassword", method = RequestMethod.POST)
    public String update(HttpServletRequest request,@RequestParam("password") String password) {//@PathVariable("email") String email, @RequestBody User user
        try {//
            Object object=request.getSession().getAttribute("id");
            if (object!=null){
                int id=(int)object;
                boolean flag =userservice.rePassword(id,password);
                if (flag){
                    return JsonUtil.returnPackage("OK",null,null);
                }else{
                    return  JsonUtil.returnPackage("ERROR","修改失败",null);
                }
            }else{
                return "/login";
            }
//            int res = userservice.update(email, user);
//            if (res < 0) {
//                return JsonUtil.returnPackage(FAIL, null, null);
//            } else {
//                return JsonUtil.returnPackage(OK, null, null);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR, "链接数据库失败", null);
        }
//        return JsonUtil.returnPackage("OK",null,null);
    }


}