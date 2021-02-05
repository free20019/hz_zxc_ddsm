package com.twkf.controller;

import com.twkf.entity.BackMessage;
import com.twkf.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/10/26 - 19:18
 */

@Controller
@RequestMapping("/common")
@Slf4j
@Api(tags = "自行车扫码")
public class CommonController {

    @Autowired
    CommonService commonService;

    @PostMapping("/getDdingPersonInfo")
    @ResponseBody
    @ApiOperation(value = "获取钉钉个人信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code" ,value = "免登授权码",required = true,dataType = "String"),
    })
    public Map<String, Object> getDdingPersonInfo(HttpServletRequest request, @RequestParam("code") String code) {
        return commonService.getDdingPersonInfo(request, code);
    }

    @PostMapping("/getDdingCompanyInfo")
    @ResponseBody
    @ApiOperation(value = "获取钉钉公司信息", hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code" ,value = "免登授权码",required = true,dataType = "String"),
    })
    public Map<String, Object> getDdingCompanyInfo(HttpServletRequest request, @RequestParam("code") String code) {
        return commonService.getDdingCompanyInfo(request, code);
    }

    @PostMapping("/bicycleScan")
    @ResponseBody
    @ApiOperation(value = "扫码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uri" ,value = "扫码解析地址",required = true,dataType = "String"),
            @ApiImplicitParam(name = "latitude" ,value = "纬度",required = true,dataType = "String"),
            @ApiImplicitParam(name = "longitude" ,value = "经度",required = true,dataType = "String"),
            @ApiImplicitParam(name = "area" ,value = "扫码时所在区域",required = true,dataType = "String"),
            @ApiImplicitParam(name = "user" ,value = "扫码人",required = true,dataType = "String"),
    })
    public BackMessage<Object> bicycleScan(HttpServletRequest request) {
        return commonService.bicycleScan(request);
    }
}
