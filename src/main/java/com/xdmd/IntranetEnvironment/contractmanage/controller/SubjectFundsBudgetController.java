package com.xdmd.IntranetEnvironment.contractmanage.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectFundsBudgetDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.SubjectFundsBudgetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description: 课题经费预算接口
 */
@Api(tags = "课题经费预算【合同子表四】")
@RestController
@Controller
@RequestMapping(value = "environment/contract/subjectfundbudget")
public class SubjectFundsBudgetController {
    private static final Logger log = LoggerFactory.getLogger(SubjectFundsBudgetController.class);
    @Autowired
    SubjectFundsBudgetService subjectFundsBudgetService;

    ResultMap resultMap=new ResultMap();
    /**
     * 新增
     * @param subjectFundsBudgetDTO
     * @return
     */
    @ApiOperation(value = "新增课题预算信息")
    @PostMapping(value = "insertInfo")
    public ResultMap insert(@RequestBody SubjectFundsBudgetDTO subjectFundsBudgetDTO) {
        int sfb=subjectFundsBudgetService.insert(subjectFundsBudgetDTO);
        return sfb>0?resultMap.success().message("新增成功"):resultMap.fail().message("新增失败");

    }
    /**
     * [查詢] 根據合同 id 查詢
     * @param id
     * @return
     */
    @ApiOperation(value = "根據合同id查詢课题预算信息")
    @GetMapping(value = "getInfoById")
    public ResultMap getInfoById(@RequestParam("id") int id) {
        SubjectFundsBudgetDTO sfbDTO= subjectFundsBudgetService.getBudgetInfoById(id);
        return sfbDTO!=null?resultMap.success().message(sfbDTO):resultMap.fail().message("查询失败");


    }
    /**
     * [查詢] 获取全部预算信息
     * @return
     */
    @ApiOperation(value = "获取全部预算信息")
    @GetMapping(value = "getAllInfo")
    public ResultMap getAllInfo() {
        List<SubjectFundsBudgetDTO>  sfbList=subjectFundsBudgetService.getAllInfo();
        return sfbList!=null?resultMap.success().message(sfbList):resultMap.fail().message("查询失败");

    }



    /**
     * 修改
     * @param subjectFundsBudgetDTO
     * @return
     */
    @ApiOperation(value = "不通过被退回时重新提交[修改]")
    @PostMapping(value = "UpdateSubjectFundsBudget")
    public int UpdateSubjectFundsBudget(SubjectFundsBudgetDTO subjectFundsBudgetDTO) {
        return subjectFundsBudgetService.UpdateSubjectFundsBudget(subjectFundsBudgetDTO);
    }
}
