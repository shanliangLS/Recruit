package com.recruit.controller;

import com.recruit.entity.AdminData;
import com.recruit.entity.JobData;
import com.recruit.entity.PartJobData;
import com.recruit.service.AdminService;
import com.recruit.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;
    @RequestMapping(value = "/adminLogin",method = RequestMethod.POST)
    public String adminLogin(HttpServletRequest request, @RequestParam("name") String name, @RequestParam("password") String password){

        try{

            AdminData adminData=adminService.getDataByLogin(name,password);

            if (adminData==null){

                return JsonUtil.returnPackage("FAIL","账号或密码错误",null);
            }else{

                request.getSession().setAttribute("adminId",adminData.getId());
                request.getSession().setAttribute("adminName",adminData.getName());
                request.getSession().setAttribute("adminPassword",adminData.getPassword());
                request.getSession().setAttribute("model","1");

                return JsonUtil.returnPackage("OK",null,null);
            }

//            return JsonUtil.returnPackage("OK",null,null);
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.returnPackage("ERROR","数据库错误",null);
        }
    }
//    @RequestMapping(value = "/addGuide",method = RequestMethod.POST)
//    public String addGuide(HttpServletRequest request,@RequestParam("")){
//
//    }



    /**
     * job_fair
     * @return
     */
    @RequestMapping(value = "/addJobFair",method = RequestMethod.POST)
    public String addJob (@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("release_time") String release_time){
        JobData jobData=new JobData();
        jobData.setData(1,title,content,release_time);
        boolean flag=adminService.setJob(jobData);
        if (flag){
            return JsonUtil.returnPackage("OK",null,null);
        }else{
            return JsonUtil.returnPackage("ERROR","插入失败",null);
        }
    }


    @RequestMapping(value = "/getJobFairs",method = RequestMethod.POST)
    public String getData(){
        List<JobData> jobData=adminService.getJob();
        return JsonUtil.returnPackage("OK",null,jobData);
    }
    @RequestMapping(value = "/deleteJobFair",method = RequestMethod.POST)
    public String deleteJob(@RequestParam("id") int id){
        boolean flag=adminService.deleteJob(id);
        if (flag){
            return JsonUtil.returnPackage("OK",null,null);
        }else{
            return JsonUtil.returnPackage("ERROR","删除失败",null);
        }
    }

    /**
     * part_time_job
     */
    @RequestMapping(value = "/addPartJob",method = RequestMethod.POST)
    public String addPartJob(@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("release_time") String release_time){
        PartJobData partjobData=new PartJobData();
        partjobData.setData(1,title,content,release_time);
        boolean flag=adminService.setPartJob(partjobData);
        if (flag){
            return JsonUtil.returnPackage("OK",null,null);
        }else{
            return JsonUtil.returnPackage("ERROR","插入失败",null);
        }
    }
    @RequestMapping(value = "/getPartJobs",method = RequestMethod.POST)
    public String getPartJobData(){
        List<PartJobData> partjobData=adminService.getPartJob();
        return JsonUtil.returnPackage("OK",null,partjobData);
    }
    @RequestMapping(value = "/deletePartJob",method = RequestMethod.POST)
    public String deletePartJob(@RequestParam("id") int id){
        boolean flag=adminService.deletePartJob(id);
        if (flag){
            return JsonUtil.returnPackage("OK",null,null);
        }else{
            return JsonUtil.returnPackage("ERROR","删除失败",null);
        }
    }
}
