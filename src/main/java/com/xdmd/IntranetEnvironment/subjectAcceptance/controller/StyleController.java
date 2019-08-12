package com.xdmd.IntranetEnvironment.subjectAcceptance.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("checkApplyStyle")
public class StyleController {
    ResultMap resultMap = new ResultMap();
    @Autowired
    private StyleService styleService;

    //课题验收中的单位性质
    @PostMapping("unitNature")
    @ResponseBody
    public ResultMap UnitNature(){
        resultMap = styleService.unitNature();
        return resultMap;
    }

    //课题验收中的申请验收方式
    @PostMapping("applicationAcceptance")
    @ResponseBody
    public ResultMap applicationAcceptance(){
        resultMap = styleService.applicationAcceptance();
        return resultMap;
    }

    //课题验收中的验收提交资料清单
    @PostMapping("applicationSubmitList")
    @ResponseBody
    public ResultMap applicationSubmitList(){
        resultMap = styleService.applicationSubmitList();
        return resultMap;
    }


}