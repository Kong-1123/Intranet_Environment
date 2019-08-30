package com.xdmd.IntranetEnvironment.contractmanage.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContractManageDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.ContractManageService;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateStatusException;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 合同管理接口
 */
@Api(tags = "合同管理接口【合同主表】")
@RestController
@RequestMapping(value = "environment/contract")
public class ContractManageController {
    private static final Logger log = LoggerFactory.getLogger(ContractManageController.class);
    @Autowired
    ContractManageService contractManageService;
    ResultMap resultMap = new ResultMap();


    /**
     * 新增合同信息【外网提交】
     *
     * @param contractManageDTO
     * @return
     */
    @ApiOperation(value = "新增合同信息【外网提交】")
    @PostMapping(value = "addContractInfo")
    public ResultMap insertContractInfo(//@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                        @RequestBody ContractManageDTO contractManageDTO) {
        String token = "aaa";
        HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        return resultMap = contractManageService.insert(token, response, contractManageDTO);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据合同id查询【内外网查看】")
    @GetMapping(value = "getManageInfoById")
    public ResultMap getManageInfoById(int id) {
        ContractManageDTO cmDTO = contractManageService.getManageInfoById(id);
        return cmDTO != null ? resultMap.success().message(cmDTO) : resultMap.fail().message("查询失败");
    }


    /**
     * 根据单位id查询本单位的合同
     *
     * @param uid
     * @return
     */
    @ApiOperation(value = "根据单位id查询本单位的合同【外网查看】")
    @GetMapping(value = "getManageInfoByUid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "单位id", required = true),
            @ApiImplicitParam(name = "subjectCategory", value = "课题类别"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectContact", value = "课题联系人"),
            @ApiImplicitParam(name = "subjectContactPhone", value = "课题联系人电话或手机"),
            @ApiImplicitParam(name = "commitmentUnit", value = "承担单位"),
            @ApiImplicitParam(name = "subjectSupervisorDepartment", value = "课题主管部门")
    })
    public ResultMap getManageInfoByUid(int uid, String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit,
                                        String subjectSupervisorDepartment) {
        List<Map> cmMapByUid = contractManageService.getManageInfoByUid(uid, subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartment);
        return cmMapByUid != null ? resultMap.success().message(cmMapByUid) : resultMap.fail().message("查询失败");
    }

    /**
     * 全查
     *
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectCategory", value = "课题类别"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectContact", value = "课题联系人"),
            @ApiImplicitParam(name = "subjectContactPhone", value = "课题联系人电话或手机"),
            @ApiImplicitParam(name = "commitmentUnit", value = "承担单位"),
            @ApiImplicitParam(name = "subjectSupervisorDepartment", value = "课题主管部门"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    @ApiOperation(value = "查询合同主表信息")
    @GetMapping(value = "getAllInfo")
    public ResultMap getAllInfo(String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit,
                                String subjectSupervisorDepartment, int pageNum, int pageSize) {
        return resultMap = contractManageService.getAllInfo(subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartment, pageNum, pageSize);
    }


    ///////////////////////////以下是中期检查///////////////////////////////////


    /**
     * 根据勾选的合同主表id修改相应的中期检查状态【内网中检】
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据勾选的合同主表id修改相应的中期检查记录【内网中检】")
    @PostMapping(value = "updateContractByIds")
    public ResultMap updateContractByIds(@RequestParam("mid") @ApiParam("中检id") int mid, @RequestBody @ApiParam("合同id集合") List<Long> ids) {
        int no = contractManageService.updateContractByIds(mid, ids);
        return no > 0 ? resultMap.success() : resultMap.fail();
    }

    /**
     * 根据中期检查记录查詢相应合同主表【内网中检】
     *
     * @return
     */
    @ApiOperation(value = "根据中期检查记录查詢相应合同主表【内网中检】")
    @GetMapping(value = "getInfoByMidRecord")
    public ResultMap getInfoByMidRecord(@RequestParam("mId") int mId) {
        List<Map> mapList = contractManageService.getInfoByMidRecord(mId);
        return mapList.size() > 0 ? resultMap.success() : resultMap.fail();
    }


    /**
     * [查詢] 根据单位id & 中检记录id查詢本单位的课题【外网中检】
     *
     * @param Uid
     * @return
     */
    @ApiOperation(value = "根据单位id & 中检记录id查詢本单位的课题【外网中检】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Uid", value = "单位id", dataType = "Long"),
            @ApiImplicitParam(name = "Mid", value = "中检记录id", dataType = "Long"),
    })
    @GetMapping(value = "getContractByUid")
    public ResultMap getContractByUid(@RequestParam("Uid") int Uid, @RequestParam("Mid") int Mid) {
        List<Map> maps = contractManageService.getContractByUid(Uid, Mid);
        return maps.size() > 0 ? resultMap.success() : resultMap.fail();
    }

    /**
     * 根据合同id更新相应的附件id
     *
     * @param cid
     * @param midCheckAnnexId
     * @param expertAssessmentAnnexId
     * @return
     */
    @ApiOperation(value = "根据合同id更新相应的附件id【外网中检】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "midCheckAnnexId", value = "中期检查附件id"),
            @ApiImplicitParam(name = "expertAssessmentAnnexId", value = "专家评估附件id"),
            @ApiImplicitParam(name = "subjectSuggestAnnexId", value = "课题意见附件id"),
            @ApiImplicitParam(name = "cid", value = "合同id"),
    })
    @PostMapping(value = "updateContractByCid")
    public ResultMap updateContractByCid(int midCheckAnnexId, int expertAssessmentAnnexId, int subjectSuggestAnnexId, int cid) {
        int num = contractManageService.updateContractByCid(midCheckAnnexId, expertAssessmentAnnexId, subjectSuggestAnnexId, cid);
        return num > 0 ? resultMap.success() : resultMap.fail();
    }

    /**
     * 根据合同id更新合同附件id
     *
     * @param contractAnnexId
     * @param cid
     * @return
     */
    @ApiOperation(value = "根据合同id更新合同附件id【外网提交时上传合同附件】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractAnnexId", value = "合同附件id", paramType = "int"),
            @ApiImplicitParam(name = "cid", value = "合同id", paramType = "int")
    })
    @PostMapping(value = "updateContractAnnexIdByCid")
    int updateContractAnnexIdByCid(int contractAnnexId, @ApiParam("合同id") int cid) {
        return contractManageService.updateContractAnnexIdByCid(contractAnnexId, cid);
    }


    /**
     * 合同附件上传【外网】
     *
     * @param file
     * @param cid
     * @return
     */
    @PostMapping("contractFileUpload")
    @ApiOperation(value = "合同附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "合同id"),
    })
    public String ContractFileUpload(MultipartFile file, int cid) {
        String OK = null;
        try {
            OK = contractManageService.ContractFileUpload(file, cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OK;
    }


    /**
     * 单位管理员审核【外网】
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param oid    审核表id
     * @return
     */
    @PostMapping(value = "contractShenHeByUnitManager")
    @ApiOperation(value = "单位管理员审核【外网】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态", required = true),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", required = false),
            @ApiImplicitParam(name = "oid", value = "审核表id", required = true),
    })
    public ResultMap contractShenHeByUnitManager(//@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                               Boolean type, String reason, Integer oid) {
        String token = "aaa";
        HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            resultMap.fail().message("请先登录");
        }
        try {
            resultMap = contractManageService.contractShenHeByUnitManager(token, response, type, reason, oid);
        } catch (UpdateSqlException e) {
            e.printStackTrace();
            log.error("OpenContractController 中 ContractFileUpload 方法 -- " + e.getMessage());
            resultMap.fail().message("系统异常");
        } catch (InsertSqlException e) {
            e.printStackTrace();
            log.error("OpenContractController 中 ContractFileUpload 方法 -- " + e.getMessage());
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 评估中心审核【内网】
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param oid    审核表id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态", required = true),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", required = false),
            @ApiImplicitParam(name = "oid", value = "审核表id", required = true),
    })
    @PostMapping(value = "contractShenHeByPingGuCenter")
    @ApiOperation(value = "评估中心审核【内网")
    public ResultMap contractShenHeByPingGuCenter(//@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                                Boolean type, String reason, Integer oid) {
        String token = "aaa";
        HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        resultMap = contractManageService.contractShenHeByPingGuCenter(token, response, type, reason, oid);
        return resultMap;
    }
    /**
     * 法规科技处审核【内网】
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param oid    审核表id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态", required = true),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", required = false),
            @ApiImplicitParam(name = "oid", value = "审核表id", required = true),
    })
    @PostMapping(value = "contractShenHeByFaGui")
    @ApiOperation(value = "评估中心审核【内网")
    public ResultMap contractShenHeByFaGui(//@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                                  Boolean type, String reason, Integer oid) {
        String token = "aaa";
        HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        resultMap = contractManageService.contractShenHeByFaGui(token, response, type, reason, oid);
        return resultMap;
    }


    /**
     * 展示所有通过单位管理员审批的 【外网】
     * @return
     */
    @GetMapping(value = "showAllPassContractReviewByUnitManager")
    @ApiOperation(value = "展示所有通过单位管理员审批的")
    public ResultMap showAllPassContractReviewByUnitManager(int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllPassContractReviewByUnitManager(pageNum, pageSize);
    }

    /**
     * 展示所有未通过单位管理员审批的【外网】
     *
     * @return
     */
    @GetMapping(value = "showAllNoPassContractReviewByUnitManager")
    @ApiOperation(value = "展示所有未通过单位管理员审批的")
    public ResultMap showAllNoPassContractReviewByUnitManager(int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllNoPassContractReviewByUnitManager(pageNum, pageSize);
    }

    /**
     * 展示所有通过评估中心审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllPassContractReviewByPingGu")
    @ApiOperation(value = "展示所有通过评估中心审批的")
    public ResultMap showAllPassContractReviewByPingGu(int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllPassContractReviewByPingGu(pageNum, pageSize);
    }

    /**
     * 展示所有未通过评估中心审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllNoPassReviewContractByPingGu")
    @ApiOperation(value = "展示所有未通过评估中心审批的")
    public ResultMap showAllNoPassReviewContractByPingGu(int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllNoPassReviewContractByPingGu(pageNum, pageSize);
    }

    /**
     * 展示所有未通过法规科技处审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllNoPassReviewContractByFaGui")
    @ApiOperation(value = "展示所有未通过法规科技处审批的")
    public ResultMap showAllNoPassReviewContractByFaGui(int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllNoPassReviewContractByFaGui(pageNum, pageSize);
    }

    /**
     * 展示所有通过法规科技处审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllPassContractReviewByFaGui")
    @ApiOperation(value = "展示所有通过法规科技处审批的")
    public ResultMap showAllPassContractReviewByFaGui(int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllPassContractReviewByFaGui(pageNum, pageSize);
    }

    /**
     * 不通过被退回时重新提交[即修改]【外网】
     * @param contractManageDTO
     * @return
     * @throws UpdateStatusException
     * @throws UpdateSqlException
     */
   @PostMapping(value = "updateContractStatusByReturnCommit")
   @ApiOperation(value = "不通过被退回时重新提交[即修改]【外网】")
    public ResultMap updateContractStatusByReturnCommit(//@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                                        @RequestBody ContractManageDTO contractManageDTO) throws UpdateStatusException, UpdateSqlException {

        String token = "aaa";
        HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        return resultMap = contractManageService.updateContractStatusByReturnCommit(token, response,contractManageDTO);
    }






}
