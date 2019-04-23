package com.recruit.controller;

import com.recruit.entity.DeliverResume;
import com.recruit.service.DeliverResumeService;
import com.recruit.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class DeliverResumeController {

    private static final String OK = "OK";
    private static final String ERROR = "ERROR";
    private static final String FAIL = "FAIL";

    @Autowired
    private DeliverResumeService deliverResumeService;

    /**
     * 根据uid查询求职者所投简历
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deliverResumes", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String getDeliverResumeListByUid(HttpServletRequest request) {
        try {
            int uid = Integer.parseInt(request.getSession().getAttribute("id").toString());
            List<DeliverResume> deliverResumes = deliverResumeService.getDeliverResumeListByUid(uid);
            return JsonUtil.returnPackage(OK,null,deliverResumes);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR,"链接数据库失败",null);
        }
    }

    /**
     * 根据uid和rid插入求职者投简历
     * @param request
     * @param rid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertDeliverResume", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String insertDeliverResumeByUidRid(HttpServletRequest request, @RequestParam("rid") int rid) {
        try {
            int uid = Integer.parseInt(request.getSession().getAttribute("id").toString());
            DeliverResume getDeliverResume = deliverResumeService.getDeliverResumeByUidRid(uid,rid);
            if (getDeliverResume == null) {
                boolean insertDeliverResume = deliverResumeService.insertDeliverResumeByUidRid(uid,rid);
                return JsonUtil.returnPackage(OK,null,insertDeliverResume);
            } else {
                if (getDeliverResume.getDeliver_status() == 0) {
                    return JsonUtil.returnPackage(ERROR, "已投递简历，请耐心等待审核", null);
                } else if (getDeliverResume.getDeliver_status() == 1) {
                    return JsonUtil.returnPackage(ERROR, "简历已通过，请前往首页查看", null);
                }
            }
            return JsonUtil.returnPackage(ERROR,"投递简历失败",null);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.returnPackage(ERROR,"链接数据库失败",null);
        }
    }
}
