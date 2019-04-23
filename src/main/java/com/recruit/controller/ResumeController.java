package com.recruit.controller;

import com.recruit.entity.IntensionData;
import com.recruit.entity.Left_H_Data;
import com.recruit.entity.PersonalData;
import com.recruit.service.IntensionService;
import com.recruit.service.PersonalDataService;
import com.recruit.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class ResumeController {


    private static final String OK = "OK";



    @Autowired
    PersonalDataService PersonalDataService;

    @Autowired
    IntensionService intensionService;

    @RequestMapping(value = "getData",method = RequestMethod.POST)
//    @RequestParam("email") String email, @RequestParam("password") String password
    public String getData(HttpServletRequest request){//@PathVariable(value = "email") String email
        try{
            String email= (String) request.getSession().getAttribute("email");
            if(email!=null){
                if (request.getSession().getAttribute("isInfo").equals("0")){
                    return JsonUtil.returnPackage("NULL","无数据",null);
                }else{
                    HashMap<String, Object> map = new HashMap<>();
                    PersonalData PersonDate=PersonalDataService.getDateByEmail(email);
                    IntensionData intensionData=intensionService.getDataByEmail(email);
                    map.put("personal",PersonDate);
                    map.put("intension",intensionData);
//            List<Object> list=PersonDate;
                    if (PersonDate==null){
                        return JsonUtil.returnPackage("NULL","无数据",map);
                    }else{
                        return JsonUtil.returnPackage(OK,null,map);
                    }
                }

            }else{
                return "/login";
            }


        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage("ERROR", "链接服务器失败", null);
        }

    }
    @RequestMapping(value = "setData",method = RequestMethod.POST)
    public String setData(HttpServletRequest request,@RequestParam("name") String name,@RequestParam("college") String college,@RequestParam("education") String education,@RequestParam("major") String major,@RequestParam("birthday") String birthday,@RequestParam("work") String work,@RequestParam("annual_salary") String annual_salary,@RequestParam("desired_industry") String desired_industry,@RequestParam("desired_location") String desired_location,@RequestParam("desired_job") String desired_job,@RequestParam("flag") String flag){

        try {
            String email= (String) request.getSession().getAttribute("email");
            if (email!=null){
                PersonalData personalData=new PersonalData();
//            ,
                personalData.setData(1,1,name,birthday,education,college,major,work);


                IntensionData intensionData=new IntensionData();
                intensionData.setData(1,1,annual_salary,desired_industry,desired_location,desired_job);
                if (flag.equals("insert")){
                    boolean flag1=PersonalDataService.insertData(email,personalData);
                    boolean flag2=intensionService.insertData(email,intensionData);
                    if (flag1&&flag2){
                        request.getSession().setAttribute("isInfo","1");
                        return JsonUtil.returnPackage("OK",null,null);
                    }else{
                        return JsonUtil.returnPackage("ERROR",null,null);
                    }
                }else if(flag.equals("update")){
                    boolean flag1=PersonalDataService.updateData(email,personalData);
                    boolean flag2=intensionService.updateData(email,intensionData);
                    if (flag1&&flag2){
                        return JsonUtil.returnPackage("OK",null,null);
                    }else{
                        return JsonUtil.returnPackage("ERROR",null,null);
                    }

                }else{
                    return JsonUtil.returnPackage("ERROR","未知错误",null);
                }

            }else{
                return "/login";
            }



        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.returnPackage("ERROR","数据库错误",null);
        }

    }

    @RequestMapping(value = "/getPersonalData",method = RequestMethod.POST)
    public String getPersonalData(HttpServletRequest request){//
//        long uid=1;
        try {
            Object object= request.getSession().getAttribute("id");
            if (object!=null){
                int uid= (int) object;
                System.out.println(uid);
                Left_H_Data left_h_data=PersonalDataService.getDateByUid(uid);
                if (left_h_data==null){
                    return JsonUtil.returnPackage("ERROR","数据库中无数据",null);
                }else{

                    return JsonUtil.returnPackage("OK",null,left_h_data);
                }
            }else{
                return "/login";
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.returnPackage("ERROR","数据库查询错误",null);
        }

    }
}
