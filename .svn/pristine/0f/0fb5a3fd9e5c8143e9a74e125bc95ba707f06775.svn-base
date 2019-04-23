package com.recruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping("/login")
    public String toLogin() {
        return "/login";
    }

    @RequestMapping("/")
    public String toIndex() {
        return "/login";
    }

    @RequestMapping("/jobseeker/homepage")
    public String toJobSeekerHomePage() {
        return "/jobseeker/homepage";
    }

    @RequestMapping("/jobseeker/resume")
    public String toJobSeekerResume() {
        return "/jobseeker/resume";
    }

    @RequestMapping("/jobseeker/work_search")
    public String toJobSeekerWorkSearch() {
        return "/jobseeker/work_search";
    }

    @RequestMapping("/jobseeker/guide")
    public String toGuide() {
        return "/jobseeker/guide";
    }

    @RequestMapping("/jobseeker/job_fair")
    public String toJobFair() {
        return "/jobseeker/job_fair";
    }

    @RequestMapping("/jobseeker/part_time_job")
    public String toPartTimeJob() {
        return "/jobseeker/part_time_job";
    }

    @RequestMapping("/jobseeker/article")
    public String toArticle() {
        return "/jobseeker/article";
    }

    @RequestMapping("/H-ui/index")
    public String toHuiIndex() {
        return "/H-ui/index";
    }

    @RequestMapping("/H-ui/login")
    public String toHuiLogin() {
        return "/H-ui/login";
    }

    @RequestMapping("/H-ui/welcome")
    public String toHuiWelcome() {
        return "/H-ui/welcome";
    }

    @RequestMapping("/H-ui/job-fair")
    public String toHuiJobFair() {
        return "/H-ui/job-fair";
    }

    @RequestMapping("/H-ui/part-time-job")
    public String toHuiPartTimeJob() {
        return "/H-ui/part-time-job";
    }

    /***
     * 跳转到accountsetting页面
     */
    @RequestMapping(value = "returnAccountSetting", method = RequestMethod.GET)
    public String returnAccountSetting() {
        return "jobseeker/accountsetting";
    }

    /***
     * 跳转到accountsetting页面
     */
    @RequestMapping(value = "/returnResume")
    public String  returnResume(){
        return "/jobseeker/resume";
    }
}
