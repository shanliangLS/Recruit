package com.recruit.config;

import com.recruit.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludes = new String[]{"/login", "/static/**", "/","/H-ui/login",
                "/auth/login", "/auth/logout", "/register", "/activation/email/**","/adminLogin"};//不需要登录就可以查看的,"/jobseeker/homepage","/getPersonalData" ,"/jobseeker/accountsetting","/returnAccountSetting"
        String[] tests = new String[]{};
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(excludes).excludePathPatterns(tests);
        super.addInterceptors(registry);
    }
}
