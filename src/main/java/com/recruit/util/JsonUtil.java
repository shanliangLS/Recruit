package com.recruit.util;

import com.google.gson.Gson;

import java.util.HashMap;

public class JsonUtil {
    private static Gson gson = new Gson();

    /**
     * 返回JSON格式
     *
     * @param status 状态
     * @param reason 原因
     * @param result 结果
     * @return json
     */
    public static String returnPackage(String status, String reason, Object result) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);
        if (null != reason && !"".equals(reason)) {
            map.put("reason", reason);
        }
        if (null != result && !"".equals(result)) {
            map.put("data", result);
        }
        return gson.toJson(map);
    }
}
