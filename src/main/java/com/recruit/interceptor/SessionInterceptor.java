package com.recruit.interceptor;

import com.recruit.entity.PersonalData;
import com.recruit.service.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionInterceptor implements HandlerInterceptor {
//    @Autowired
//    private 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器启动");
        String url= String.valueOf(request.getRequestURL());
        System.out.println(url);
        //验证是哪个模式登录
        //求职者登录-》model=0
        //管理员登录-》model=1
        String model=(String) request.getSession().getAttribute("model");
        System.out.println(model);
        if (model==null){
            response.sendRedirect("/login");//跳转到管理员登录的页面
            System.out.println("该请求被拦截");
            return false;
        }
        if (model.equals("0")){//求职者登录
            // 验证session 是否存在
            Object object = request.getSession().getAttribute("id");
            if (object == null) {
                response.sendRedirect("/login");
                System.out.println("该请求被拦截");

                return false;
            } else {
                //验证是否完善了信息
                String isInfo=(String) request.getSession().getAttribute("isInfo");

                if (isInfo.equals("0")){
                    if (url.equals("http://localhost:8080/jobseeker/resume")){
                        return true;
                    }else if(url.equals("http://localhost:8080/getData")){
                        return true;
                    }else if(url.equals("http://localhost:8080/setData")){
                        return  true;
                    }else{
                        response.sendRedirect("/jobseeker/resume");
                        System.out.println("该请求被拦截(登录后)");
                        return false;
                    }

                }else{
                    System.out.println("sadsaasd");
                    return true;
                }

            }
        }else if (model.equals("1")){//管理员登录
            // 验证session 是否存在
            Object object = request.getSession().getAttribute("adminId");
            if (object == null) {
                response.sendRedirect("/H-ui/login");//跳转到管理员登录的页面
                System.out.println("该请求被拦截");
                return false;
            } else {
                System.out.println("sadsa");
                return true;
            }
        }else {//未登录
            System.out.println("该请求被拦截");
            return false;
        }

    }

//    @Override
//    public boolean loginHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
//        System.out.println("拦截跳转启动");
//        System.out.println(request.getRequestURL());
//
//    }
}
