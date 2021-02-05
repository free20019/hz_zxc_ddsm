package com.twkf.service;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import com.twkf.dao.CommonDao;
import com.twkf.entity.BackMessage;
import com.twkf.helper.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/10/26 - 19:20
 */
@Service
@Slf4j
public class CommonService {

    @Autowired
    CommonDao commonDao;

    private Map<String,Object> getParameterToMap(HttpServletRequest request, String... parameters){
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        for(String parameter : parameters){
            map.put(parameter,request.getParameter(parameter)==null?"":request.getParameter(parameter));
        }
        return map;
    }

    public Map<String, Object> getDdingPersonInfo(HttpServletRequest httpServletRequest,String code) {
        log.info("-----------------------用户---------------------------");
        log.info("code:" + code);
        Map<String, Object> map = new HashMap<>();
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("dingbqu6k9grkgdwbsts");
        request.setAppsecret("2mXWZtO8U2azk0TmDl8nMThN2KHnBtRIHvj8Wa-E5IvmpG0ApYFatA_MPvgILAMq");
        request.setHttpMethod("GET");
        //获取access_token
        String access_token = "";
        try {
            OapiGettokenResponse response = client.execute(request);
            long errCode = response.getErrcode();
            if(errCode != 0) {
                map.put("code", 1);
                map.put("msg", response.getErrmsg());
                log.info("map1="+map);
                return map;
            }
            access_token = response.getAccessToken();
            log.info("access_token="+access_token);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "获取access_token错误");
            log.info("map2="+map);
            return map;
        }
        //获取user_id
        String userId = "";
        DingTalkClient client1 = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
        OapiUserGetuserinfoRequest request1 = new OapiUserGetuserinfoRequest();
        request1.setCode(code);
        request1.setHttpMethod("GET");
        OapiUserGetuserinfoResponse response = null;
        try {
            response = client1.execute(request1, access_token);
            if(response.getErrcode() !=0) {
                map.put("code", 1);
                map.put("msg", response.getErrmsg());
                log.info("map3="+map);
                return map;
            }
            userId = response.getUserid();
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "获取user_id错误");
            log.info("map4="+map);
            return map;
        }
        //指定用户的所有上级父部门路径
        String department = "";
        try {
            DingTalkClient clientDept = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list_parent_depts");
            OapiDepartmentListParentDeptsRequest requestDept = new OapiDepartmentListParentDeptsRequest();
            requestDept.setUserId(userId);
            requestDept.setHttpMethod("GET");
            OapiDepartmentListParentDeptsResponse responseDept = clientDept.execute(requestDept, access_token);
            department = responseDept.getDepartment();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        //获取子级部门名
        ArrayList<String> dept_names = new ArrayList<>();
        if(department.length()>0){
            List<ArrayList> arrayLists = JSON.parseArray(department, ArrayList.class);
            for (ArrayList arrayList : arrayLists) {
                Object dept_id = "";
                if(arrayList.size()>0){
                    dept_id = arrayList.get(0);
                    try {
                        DingTalkClient clientDept = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/get");
                        OapiDepartmentGetRequest requestDept = new OapiDepartmentGetRequest();
                        requestDept.setId(dept_id.toString());
                        requestDept.setHttpMethod("GET");
                        OapiDepartmentGetResponse responseDept = clientDept.execute(requestDept, access_token);
                        dept_names.add(responseDept.getName());
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        map.put("dept", StringUtils.join(dept_names, ","));
        //获取用户信息
        DingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
        OapiUserGetRequest request2 = new OapiUserGetRequest();
        request2.setUserid(userId);
        request2.setHttpMethod("GET");
        try {
            OapiUserGetResponse response2 = client2.execute(request2, access_token);
            log.info("response2="+response2);
            if(response2.getErrcode() != 0) {
                map.put("code", 1);
                map.put("msg", response.getErrmsg());
                log.info("map5="+map);
                return map;
            }
            map.put("code", 0);
            map.put("name",response2.getName());
            map.put("phone",response2.getMobile());
            log.info("map6="+map);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "获取用户信息错误");
            log.info("map7="+map);
            return map;
        }
    }

    public Map<String, Object> getDdingCompanyInfo(HttpServletRequest httpServletRequest,String code) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("---------------------企业-----------------------------");
        log.info("code:" + code);
        Map<String, Object> map = new HashMap<>();
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey("dingbqu6k9grkgdwbsts");
        request.setAppsecret("2mXWZtO8U2azk0TmDl8nMThN2KHnBtRIHvj8Wa-E5IvmpG0ApYFatA_MPvgILAMq");
        request.setHttpMethod("GET");
        //获取access_token
        String access_token = "";
        try {
            OapiGettokenResponse response = client.execute(request);
            long errCode = response.getErrcode();
            if(errCode != 0) {
                map.put("code", 1);
                map.put("msg", response.getErrmsg());
                log.info("map1="+map);
                return map;
            }
            access_token = response.getAccessToken();
            log.info("access_token="+access_token);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "获取access_token错误");
            log.info("map2="+map);
            return map;
        }
//        //获取user_id
//        String deptId = "";
//        DingTalkClient client1 = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
//        OapiUserGetuserinfoRequest request1 = new OapiUserGetuserinfoRequest();
//        request1.setCode(code);
//        request1.setHttpMethod("GET");
//        OapiUserGetuserinfoResponse response = null;
//        try {
//            response = client1.execute(request1, access_token);
//            if(response.getErrcode() !=0) {
//                map.put("code", 1);
//                map.put("msg", response.getErrmsg());
//                log.info("map3="+map);
//                return map;
//            }
//            deptId = response.getUserid();
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("code", 1);
//            map.put("msg", "获取user_id错误");
//            log.info("map4="+map);
//            return map;
//        }

        DingTalkClient client2 = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/get");
        OapiDepartmentGetRequest request2 = new OapiDepartmentGetRequest();
        request2.setId("1");
        request2.setHttpMethod("GET");
        try {
            OapiDepartmentGetResponse response2 = client2.execute(request2, access_token);
            log.info("response2="+response2);
            if(response2.getErrcode() != 0) {
                map.put("code", 1);
                map.put("msg", response2.getErrmsg());
                log.info("map5="+map);
                return map;
            }
            map.put("code", 0);
            map.put("name",response2.getName());
            log.info("map6="+map);
            return map;
        } catch (ApiException e) {
            e.printStackTrace();
            map.put("code", 1);
            map.put("msg", "获取部门信息错误");
            log.info("map7="+map);
            return map;
        }
    }

    public BackMessage<Object> bicycleScan(HttpServletRequest request) {
        Map<String, Object> map = getParameterToMap(request,"uri","latitude","longitude","area","user");
        try{
            Map<String, Object> requestMap = new LinkedHashMap<String,Object>();
            requestMap.put("compName","dingding");
            requestMap.put("token","77C282E36AE84069917ACD45F682CB81");
            requestMap.put("body",map);
            //测试
//            Map<String, Object> result = UrlUtils.post("http://122.224.163.75:55010/ibike/common/scanCount", requestMap, Map.class);
            //正式
            Map<String, Object> result = UrlUtils.post("https://bike.tongwoo.cn/ibike/common/scanCount", requestMap, Map.class);
            log.info("扫码结果:"+result);
            return new BackMessage<Object>(200, "成功", result);
        }catch (Exception e){
            e.printStackTrace();
            return new BackMessage<Object>(400,"错误", e.getMessage());
        }
    }


}


