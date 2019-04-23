package com.recruit.controller;

import com.recruit.entity.FindWorks;
import com.recruit.service.FindWorksService;
import com.recruit.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class FindWorksController {

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";
    private static final String FAIL = "FAIL";

    @Autowired
    private FindWorksService findWorksService;

    @ResponseBody
    @RequestMapping(value = "/findWorks", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String getFindWorksListByIndustry(@RequestParam("industry") String industry) {
        try {
            List<FindWorks> findWorks = findWorksService.getFindWorksListByIndustry(industry);
            return JsonUtil.returnPackage(OK,null,findWorks);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR,"链接数据库失败",null);
        }
    }
}
