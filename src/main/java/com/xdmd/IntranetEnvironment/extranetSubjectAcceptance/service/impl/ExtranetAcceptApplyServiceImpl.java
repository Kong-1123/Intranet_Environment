package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl;

import com.xdmd.IntranetEnvironment.common.FileSuffixJudgeUtil;
import com.xdmd.IntranetEnvironment.common.FileUploadUtil;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.exception.MysqlErrorException;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.mapper.ExtranetAcceptApplyMapper;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.*;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.ExtranetAcceptApplyService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.utils.IntegrationFile;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class ExtranetAcceptApplyServiceImpl implements ExtranetAcceptApplyService {
    @Autowired
    private ExtranetTokenService extranetTokenService;
    @Autowired
    private ExtranetAcceptApplyMapper acceptApplyMapper;
    ResultMap resultMap = new ResultMap();
    PageBean pageBean = new PageBean();
    ExtranetCheckApplyState extranetCheckApplyState = new ExtranetCheckApplyState();
    //打印日志
    private static Logger log = LoggerFactory.getLogger(ExtranetAcceptApplyServiceImpl.class);

    //企业填写验收申请表
    @Transactional(rollbackFor = Exception.class)
    public ResultMap AddAcceptApply(ExtranetCheckApply extranetCheckApply, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, String createname) throws MysqlErrorException {
        //新增验收申请表
        acceptApplyMapper.addAcceptApply(extranetCheckApply);

        //更新验收申请表的状态
        //新增第一条验收数据
        int cid = extranetCheckApply.getId();//获取验收申请表的id
        String firstHandler = createname;   //提交的人名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        String state = "待处理";

        extranetCheckApplyState.setCheckApplyId(cid);
        extranetCheckApplyState.setFistHandler(firstHandler);
        extranetCheckApplyState.setAuditStep("公司审批");
        extranetCheckApplyState.setFirstHandleTime(nowTime);
        extranetCheckApplyState.setState("待处理");


        //新增验收审核状态
        acceptApplyMapper.insertCheckApplyState(extranetCheckApplyState);

        return resultMap.success().message("新增成功");


//        int number = 0;
//        int number2 = 0;
//        int number3 = 0;
//        int number4 = 0;
//        try {
//            number = acceptApplyMapper.insertSelective(extranetCheckApply);
//
//            //把上传的文件上传到文件表
//            //验收申请表的上传
//            IntegrationFile applicationAcceptanceIntegrationFile = new IntegrationFile();
//            UploadFile applicationUploadFile = applicationAcceptanceIntegrationFile.IntegrationFile(applicationAcceptanceFile, extranetCheckApply.getId(), extranetCheckApply.getApplicationAcceptanceUrl(), "验收申请表", createname);
//            number2 = acceptApplyFileUploadMapper.insertSelective(applicationUploadFile);
//
//            //成果附件的上传
//            IntegrationFile achievementsUploadFile = new IntegrationFile();
//            UploadFile achievementsUploadFile2 = achievementsUploadFile.IntegrationFile(achievementsFile, extranetCheckApply.getId(), extranetCheckApply.getAchievementsUrl(), "成果附件", createname);
//            number3 = acceptApplyFileUploadMapper.insertSelective(achievementsUploadFile2);
//
//            //提交清单的上传
//            IntegrationFile submitInventoryUploadFile = new IntegrationFile();
//            UploadFile submitUploadFile = submitInventoryUploadFile.IntegrationFile(submitInventoryFile, extranetCheckApply.getId(), extranetCheckApply.getSubmitInventoryUrl(), "提交清单", createname);
//            number4 = acceptApplyFileUploadMapper.insertSelective(submitUploadFile);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("ExtranetAcceptApplyServiceImpl -- AddAcceptApply 中查询语句出错");
//            throw new MysqlErrorException();
//        }
//        if (number == 0 || number2 == 0 || number3 == 0 || number4 == 0) {
//            resultMap.fail().message("新增失败");
//        } else {
//            resultMap.success().message("新增成功");
//        }
//        return resultMap;
    }

    //企业修改验收申请表
    @Transactional(rollbackFor = Exception.class)
    public ResultMap updateAcceptApply(ExtranetCheckApply extranetCheckApply, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, String createname) throws MysqlErrorException {
        int number = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        try {
            number = acceptApplyMapper.updateByPrimaryKey(extranetCheckApply);

//            //把上传的文件上传到文件表
//            //验收申请表的上传
//            IntegrationFile applicationAcceptanceIntegrationFile = new IntegrationFile();
//            UploadFile applicationUploadFile = applicationAcceptanceIntegrationFile.IntegrationFile(applicationAcceptanceFile, extranetCheckApply.getId(), extranetCheckApply.getApplicationAcceptanceUrl(), "验收申请表", createname, extranetCheckApply.getApplicationId());
//            number2 = acceptApplyFileUploadMapper.updateByPrimaryKey(applicationUploadFile);
//
//            //成果附件的上传
//            IntegrationFile achievementsUploadFile = new IntegrationFile();
//            UploadFile achievementsUploadFile2 = achievementsUploadFile.IntegrationFile(achievementsFile, extranetCheckApply.getId(), extranetCheckApply.getAchievementsUrl(), "成果附件", createname, extranetCheckApply.getAchievementId());
//            number3 = acceptApplyFileUploadMapper.updateByPrimaryKey(achievementsUploadFile2);
//
//            //提交清单的上传
//            IntegrationFile submitInventoryUploadFile = new IntegrationFile();
//            UploadFile submitUploadFile = submitInventoryUploadFile.IntegrationFile(submitInventoryFile, extranetCheckApply.getId(), extranetCheckApply.getSubmitInventoryUrl(), "提交清单", createname, extranetCheckApply.getSubmitId());
//            number4 = acceptApplyFileUploadMapper.updateByPrimaryKey(submitUploadFile);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyServiceImpl -- updateAcceptApply 中更新语句出错");
            throw new MysqlErrorException();
        }
        if (number == 0 || number2 == 0 || number3 == 0 || number4 == 0) {
            resultMap.fail().message("更新失败");
        } else {
            resultMap.success().message("更新成功");
        }
        return resultMap;
    }

//    //验收申请表的查询
//    public ResultMap queryAcceptApply(String subjectName, String projectLeader, Integer page, Integer total) throws StringToDateException {
//        //页数
//        int newpage = 0;
//        if (page == 1) {
//            newpage = page - 1;
//        } else {
//            newpage = (page - 1) * total;
//        }
//
//        List<ExtranetCheckApply> checkApplyList = acceptApplyMapper.queryAcceptApply(subjectName, projectLeader, newpage, total);
//        Integer alltotal = acceptApplyMapper.queryAllTotal(subjectName, projectLeader, newpage, total);
//
//        List<JSONObject> jsonObjectList = new ArrayList<>();
//
//        //判断根据用户输入的筛选条件是否有内容
//        if (alltotal == null) {
//            return resultMap.success().message(jsonObjectList);
//        }
//
//        //判断用户输入的页数是否超过总页数
//        int allPage = 0;
//        if (alltotal % page == 0) {
//            allPage = alltotal / page;
//        } else {
//            allPage = (alltotal / page) + 1;
//        }
//        if (page > allPage) {
//            return resultMap.fail().message("页数超过总页数");
//        }
//
//
//        for (ExtranetCheckApply checkApply : checkApplyList) {
//            //对查询出来的日期进行处理
//            Date agreementStartTime = checkApply.getAgreementStartTime();
//            String agreementStartTimeString = SqlDateToString.dateToString(agreementStartTime);
//
//            Date agreementEndTime = checkApply.getAgreementEndTime();
//            String agreementEndTimeString = SqlDateToString.dateToString(agreementEndTime);
//
//            Date applicationAcceptanceTime = checkApply.getApplicationAcceptanceTime();
//            String applicationAcceptanceTimeString = SqlDateToString.dateToString(applicationAcceptanceTime);
//
//            //获取申请表的主键
//            Integer id = checkApply.getId();
//
//            String AcceptApplyFile = "验收申请表";
//            String submitInventoryFile = "提交清单";
//            String achievementsFile = "成果附件";
//
//
//            //验收申请表
//            UploadFile uploadFile1 = acceptApplyFileUploadMapper.queryFileUrl(id, AcceptApplyFile);
//            checkApply.setApplicationId(uploadFile1.getId());
//            checkApply.setApplicationAcceptanceUrl(uploadFile1.getUploadFileAddress());
//
//            //提交清单
//            UploadFile uploadFile2 = acceptApplyFileUploadMapper.queryFileUrl(id, submitInventoryFile);
//            checkApply.setSubmitId(uploadFile2.getId());
//            checkApply.setSubmitInventoryUrl(uploadFile2.getUploadFileAddress());
//
//            //成果附件
//            UploadFile uploadFile3 = acceptApplyFileUploadMapper.queryFileUrl(id, achievementsFile);
//            checkApply.setAchievementId(uploadFile3.getId());
//            checkApply.setAchievementsUrl(uploadFile3.getUploadFileAddress());
//
//
//            //通过AliBaBa fastJson工具 把实体类中不需要的字段去除
//            JSONObject jsonObject = JSON.parseObject(checkApply.toString());
//            jsonObject.put("agreementStartTimeString", agreementStartTimeString);
//            jsonObject.put("agreementEndTimeString", agreementEndTimeString);
//            jsonObject.put("applicationAcceptanceTimeString", applicationAcceptanceTimeString);
//
//            jsonObjectList.add(jsonObject);
//
//        }
//        PageBean<Object> objectPageBean = new PageBean<>();
//        objectPageBean.setData(jsonObjectList);
//        objectPageBean.setCount(alltotal);
//
//
//        resultMap.success().message(objectPageBean);
//        return resultMap;
//    }

    //根据公司的id，查询公司的名字
    @Override
    public String queryCompanyNameByCid(Integer cid) {
        String companyName = acceptApplyMapper.queryCompanyNameByCid(cid);
        return companyName;
    }

    //对文件进行上传
    @Override
    public void uploadFile(UploadFile uploadBusinessFile) {
        acceptApplyMapper.uploadFile(uploadBusinessFile);
    }


    //公司管理员进行验收审核查询     其中查询的是，正在审核过程中的内容
    @Override
    public ResultMap query(String token, HttpServletResponse response, String topicName, String topicNumber, Integer page, Integer total) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取本公司的申请验收信息总条数
        int alltotal = 0;
        alltotal = acceptApplyMapper.queryAllExpert(cid, topicName, topicNumber);
        if (alltotal == 0) {
            return resultMap.fail().message(null);
        }

        //查询该单位正在审核过程中的信息
        List<ExtranetCheckApply> extranetCheckApplyList = acceptApplyMapper.queryAcceptApply(cid, topicName, topicNumber, newpage, total);

        //遍历验收申请的表格，获取每条信息的审核记录
        for (ExtranetCheckApply extranetCheckApply : extranetCheckApplyList) {
            Integer id = extranetCheckApply.getId();   //获取验收申请表的id
            //通过验收申请表的id，获取到对应的审核状态
            List<ExtranetCheckApplyState> extranetCheckApplyStateList = acceptApplyMapper.queryCheckApplyState(id);
            //把验收申请表的内容存放到checkApply中
            extranetCheckApply.setExtranetCheckApplyStateList(extranetCheckApplyStateList);

            //通过验收状态的id，查询出验收审核的状态
            String acceotancePhaseName = acceptApplyMapper.queryAcceptancePhaseName(extranetCheckApply.getAcceptancePhaseId());
            extranetCheckApply.setAcceptancePhaseName(acceotancePhaseName);

            //根据验收申请表的id，查询出验收申请表的Url
            String applicationFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getApplicationUrlId());
            extranetCheckApply.setApplicationAcceptanceUrl(applicationFileUrl);
            //获取验收申请表Url的名称
            String applicationFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getApplicationUrlId());
            extranetCheckApply.setApplicationAcceptanceUrlName(applicationFileName);

            //获取成果附件的URL 与 名称
            String achievementFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAchievementUrlId());
            extranetCheckApply.setAchievementsUrl(achievementFileUrl);
            //获取成果附件Url的名称
            String achievementName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAchievementUrlId());
            extranetCheckApply.setAchievementsName(achievementName);

            //获取提交清单的URL 与 名称
            String submitFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getSubmitUrlId());
            extranetCheckApply.setSubmitInventoryUrl(submitFileUrl);
            //获取提交清单Url的名称
            String submitFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getSubmitUrlId());
            extranetCheckApply.setSubmitInventoryUrlName(submitFileName);

            //获取审计报告的Url 与 名称
            String auditReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAuditReportUrlId());
            extranetCheckApply.setAuditReportUrl(auditReportFileUrl);
            //获取审计报告Url的名称
            String auditReportFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAuditReportUrlId());
            extranetCheckApply.setAuditReportUrlName(auditReportFileName);

            //获取初审报告的Url 与 名称
            String firstInspectionReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getFirstInspectionReportUrlId());
            extranetCheckApply.setFirstInspectionReportUrl(firstInspectionReportFileUrl);
            //获取初审报告Url的名称
            String firstInspectionReportFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getFirstInspectionReportUrlId());
            extranetCheckApply.setFirstInspectionReportUrlName(firstInspectionReportFileName);

            //获取专家组意见的Url与名称
            String expertGroupCommentsFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getExpertGroupCommentsUrlId());
            extranetCheckApply.setExpertGroupCommentsUrl(expertGroupCommentsFileUrl);
            //获取专家组意见Url的名称
            String expertGroupCommentsFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getExpertGroupCommentsUrlId());
            extranetCheckApply.setExpertGroupCommentsUrlName(expertGroupCommentsFileName);

            //获取专家组评议表的Url与名称
            String expertAcceptanceFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getExpertAcceptanceFormId());
            extranetCheckApply.setExpertAcceptanceFormUrl(expertAcceptanceFileUrl);
            //获取专家组评议表Url的名称
            String expertAcceptanceFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getExpertAcceptanceFormId());
            extranetCheckApply.setExpertAcceptanceFormUrlName(expertAcceptanceFileName);

            //获取最终验收证书的URL与名称
            String acceptanceCertificateFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAcceptanceCertificateId());
            extranetCheckApply.setAcceptanceCertificateUrl(acceptanceCertificateFileUrl);
            //获取最终验收证书Url的名称
            String acceptanceCertificateFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAcceptanceCertificateId());
            extranetCheckApply.setAcceptanceCertificateUrlName(acceptanceCertificateFileName);
        }

        pageBean.setCount(alltotal);
        pageBean.setData(extranetCheckApplyList);
        return resultMap.success().message(pageBean);

    }

    //管理员进行审核
    @Override
    public ResultMap examine(String token, HttpServletResponse response, Boolean type, String reason, Integer id) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        //审核状态分为两种
        if (type) {
            //此时审核通过，相当于把信息提交给内网
            //更新上一条员工的提交信息状态
            String state = "已处理";
            String handleContent = "审核通过";
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);

            //根据数据的id，把处理人，审核状态，审核内容内容，处理时间更新
            acceptApplyMapper.updateCheckApplyState(id, uname, state, handleContent, nowTime);

            //新增下一条的数据状态
            String auditStep = "等待验收初审";
            String newState = "等待处理";
            acceptApplyMapper.addNewCheckApplyState(id, uname, auditStep, nowTime, newState);

            //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
            int acceptancePhaseNum = 3;
            acceptApplyMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
        } else {
            //此时审核没有通过，退回给企业员工进行操作
            String state = "已退回";
            String handleContent = reason;
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowTime = sdf.format(date);

            //更新上一条数据
            acceptApplyMapper.updateCheckApplyState(id, uname, state, handleContent, nowTime);

            //新增下一条数据
            String auditStep = "等待员工提交";
            String newState = "等待处理";
            acceptApplyMapper.addNewCheckApplyState(id, uname, auditStep, nowTime, newState);

            //当审核状态表更新完成后，更新验收申请表中的验收审核状态
            int acceptancePhaseNum = 1;
            acceptApplyMapper.updateAcceptancePhaseById(id, acceptancePhaseNum);
        }

        return resultMap.success().message("审核通过");
    }

    //查询最后的验收情况   只有结题 不通过验收 与通过验收这三种
    @Override
    public ResultMap queryResult(String token, HttpServletResponse response, String topicName, String topicNumber, Integer page, Integer total) {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取本公司验收已经结束的信息条数
        int alltotal = 0;
        alltotal = acceptApplyMapper.queryAllResultCheckApply(cid, topicName, topicNumber);
        if (alltotal == 0) {
            return resultMap.fail().message(null);
        }

        //查询所有的结题 通过验收 没通过验收 的信息内容
        List<ExtranetCheckApply> extranetCheckApplyList = acceptApplyMapper.queryResultCheckApply(cid, topicName, topicNumber, page, total);

        for (ExtranetCheckApply extranetCheckApply : extranetCheckApplyList) {
            Integer id = extranetCheckApply.getId();   //获取验收申请表的id
            //通过验收申请表的id，获取到对应的审核状态
            List<ExtranetCheckApplyState> extranetCheckApplyStateList = acceptApplyMapper.queryCheckApplyState(id);
            //把验收申请表的内容存放到checkApply中
            extranetCheckApply.setExtranetCheckApplyStateList(extranetCheckApplyStateList);

            //通过验收状态的id，查询出验收审核的状态
            String acceotancePhaseName = acceptApplyMapper.queryAcceptancePhaseName(extranetCheckApply.getAcceptancePhaseId());
            extranetCheckApply.setAcceptancePhaseName(acceotancePhaseName);

            //根据验收申请表的id，查询出验收申请表的Url
            String applicationFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getApplicationUrlId());
            extranetCheckApply.setApplicationAcceptanceUrl(applicationFileUrl);
            //获取验收申请表Url的名称
            String applicationFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getApplicationUrlId());
            extranetCheckApply.setApplicationAcceptanceUrlName(applicationFileName);

            //获取成果附件的URL 与 名称
            String achievementFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAchievementUrlId());
            extranetCheckApply.setAchievementsUrl(achievementFileUrl);
            //获取成果附件Url的名称
            String achievementName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAchievementUrlId());
            extranetCheckApply.setAchievementsName(achievementName);

            //获取提交清单的URL 与 名称
            String submitFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getSubmitUrlId());
            extranetCheckApply.setSubmitInventoryUrl(submitFileUrl);
            //获取提交清单Url的名称
            String submitFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getSubmitUrlId());
            extranetCheckApply.setSubmitInventoryUrlName(submitFileName);

            //获取审计报告的Url 与 名称
            String auditReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAuditReportUrlId());
            extranetCheckApply.setAuditReportUrl(auditReportFileUrl);
            //获取审计报告Url的名称
            String auditReportFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAuditReportUrlId());
            extranetCheckApply.setAuditReportUrlName(auditReportFileName);

            //获取初审报告的Url 与 名称
            String firstInspectionReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getFirstInspectionReportUrlId());
            extranetCheckApply.setFirstInspectionReportUrl(firstInspectionReportFileUrl);
            //获取初审报告Url的名称
            String firstInspectionReportFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getFirstInspectionReportUrlId());
            extranetCheckApply.setFirstInspectionReportUrlName(firstInspectionReportFileName);

            //获取专家组意见的Url与名称
            String expertGroupCommentsFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getExpertGroupCommentsUrlId());
            extranetCheckApply.setExpertGroupCommentsUrl(expertGroupCommentsFileUrl);
            //获取专家组意见Url的名称
            String expertGroupCommentsFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getExpertGroupCommentsUrlId());
            extranetCheckApply.setExpertGroupCommentsUrlName(expertGroupCommentsFileName);

            //获取专家组评议表的Url与名称
            String expertAcceptanceFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getExpertAcceptanceFormId());
            extranetCheckApply.setExpertAcceptanceFormUrl(expertAcceptanceFileUrl);
            //获取专家组评议表Url的名称
            String expertAcceptanceFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getExpertAcceptanceFormId());
            extranetCheckApply.setExpertAcceptanceFormUrlName(expertAcceptanceFileName);

            //获取最终验收证书的URL与名称
            String acceptanceCertificateFileUrl = acceptApplyMapper.queryFileUrlByFileId(extranetCheckApply.getAcceptanceCertificateId());
            extranetCheckApply.setAcceptanceCertificateUrl(acceptanceCertificateFileUrl);
            //获取最终验收证书Url的名称
            String acceptanceCertificateFileName = acceptApplyMapper.queryFileNameByFileId(extranetCheckApply.getAcceptanceCertificateId());
            extranetCheckApply.setAcceptanceCertificateUrlName(acceptanceCertificateFileName);
        }

        PageBean pageBean = new PageBean();
        pageBean.setCount(alltotal);
        pageBean.setData(extranetCheckApplyList);
        return resultMap.success().message(pageBean);
    }

    //提交最终验收报告
    @Override
    public ResultMap submitLastReport(String token, HttpServletResponse response, Integer caId, MultipartFile lastReport, AcceptanceCertificate acceptanceCertificate) throws Exception {
//        JwtInformation jwtInformation = new JwtInformation();
//        try {
//            jwtInformation = extranetTokenService.compare(response, token);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (UserNameNotExistentException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (ClaimsNullException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("MenuServiceImpl 中 TokenService 出现问题");
//            return resultMap.message("系统异常");
//        }
//
//        Integer uid = jwtInformation.getUid();
//        String uname = jwtInformation.getUsername();
//        Integer cid = jwtInformation.getCid();
//        String cname = jwtInformation.getCompanyName();

        String cname = "王六公司";
        String uname = "王六";
        Integer cid =20;

        //判断验收证书后缀名是否正确
        List<String> acceptanceCertificateSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
        String lastReportFilename = lastReport.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(lastReportFilename, acceptanceCertificateSuffixList);
        if (aBoolean == false) {
            return resultMap.fail().message("请上传正确的验收证书格式");
        }

        //对验收证书进行文件上传
        String lastReportFileUrl = FileUploadUtil.fileUpload(lastReport, cname, "验收证书");
        //把验收证书上传到upload_file中
        UploadFile uploadLastReportFile = IntegrationFile.IntegrationFile(lastReport, lastReportFileUrl, "验收证书", uname);
        acceptApplyMapper.uploadFile(uploadLastReportFile);//对文件进行上传
        //根据验收申请表的id，新增最终验收报告的id
        acceptApplyMapper.updateAcceptanceFinalResultIdById(caId, uploadLastReportFile.getId());
        //修改验收证书的状态
        acceptApplyMapper.updateAcceptancePhaseById(cid, 7);

        //新增最终验收报告表单
        acceptanceCertificate.setCid(caId);
        //新增最终验收报告的主表
        acceptApplyMapper.addAcceptanceCertificate(acceptanceCertificate);
        //新增最终验收报告的专利表
        List<AcceptanceCertificatePatent> acceptanceCertificatePatentList = acceptanceCertificate.getAcceptanceCertificatePatentList();
        for (AcceptanceCertificatePatent acceptanceCertificatePatent : acceptanceCertificatePatentList) {
            acceptanceCertificatePatent.setAcceptanceCertificateId(acceptanceCertificate.getId());
            acceptApplyMapper.addAcceptanceCertificatePatent(acceptanceCertificatePatent);
        }

        //新增最终验收报告的主要参加人员
        List<AcceptanceCertificatePrincipalPersonnel> acceptanceCertificatePrincipalPersonnelList = acceptanceCertificate.getAcceptanceCertificatePrincipalPersonnelList();
        for (AcceptanceCertificatePrincipalPersonnel acceptanceCertificatePrincipalPersonnel : acceptanceCertificatePrincipalPersonnelList) {
            acceptanceCertificatePrincipalPersonnel.setAcceptanceCertificateId(acceptanceCertificate.getId());
            acceptApplyMapper.addAcceptanceCertificatePrincipalPersonnel(acceptanceCertificatePrincipalPersonnel);
        }

        //新增验收证书的课题负责人
        List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList = acceptanceCertificate.getAcceptanceCertificateSubjectPeopleList();
        for (AcceptanceCertificateSubjectPeople acceptanceCertificateSubjectPeople : acceptanceCertificateSubjectPeopleList) {
            acceptanceCertificateSubjectPeople.setAcceptanceCertificateId(acceptanceCertificate.getId());
            acceptApplyMapper.addAcceptanceCertificateSubjectPeople(acceptanceCertificateSubjectPeople);
        }


        //把验收申请的状态表进行修改
        //首先更新上一条表的状态
        String state = "已处理";
        String handleContent = "审核通过";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        //根据数据的id，把处理人，审核状态，审核内容，处理时间更新
        acceptApplyMapper.updateCheckApplyState(cid, uname, state, handleContent, nowTime);

        //新增下一条状态数据
        String auditStep = "审核最终验收报告";
        String newState = "等待审核";
        acceptApplyMapper.addNewCheckApplyState(cid, uname, auditStep, nowTime, newState);

        return resultMap.success().message("提交成功");
    }

    //上传专家组意见信息 与专家组意见文件与专家组评议表文件
    @Override
    public ResultMap submitExpertGroup(String token, HttpServletResponse response, Integer caId, ExtranetExpertGroupComment extranetExpertGroupComment, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile) throws Exception {
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }

        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();


        //判断专家组文件类型是否正确
        ArrayList<String> expertGroupCommentsFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
        String expertGroupCommentsFileName = expertGroupCommentsFile.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(expertGroupCommentsFileName, expertGroupCommentsFileSuffixList);
        if (aBoolean == false) {
            return resultMap.fail().message("请上传正确的专家组意见文件格式");
        }

        //判断专家组评议表文件是否正确
        ArrayList<String> expertAcceptanceFormFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z"));
        String expertAcceptanceFormFilename = expertAcceptanceFormFile.getOriginalFilename();
        Boolean bBoolean = FileSuffixJudgeUtil.SuffixJudge(expertAcceptanceFormFilename, expertAcceptanceFormFileSuffixList);
        if (bBoolean == false) {
            return resultMap.fail().message("请上传正确的验收证书格式");
        }

        //对专家组意见文件进行上传
        String expertGroupCommentsFileUrl = FileUploadUtil.fileUpload(expertGroupCommentsFile, cname, "专家组意见");
        //把专家组意见上传到upload_file中
        UploadFile uploadExpertGroupCommentsFile = IntegrationFile.IntegrationFile(expertGroupCommentsFile, expertGroupCommentsFileUrl, "专家组意见", uname);
        acceptApplyMapper.uploadFile(uploadExpertGroupCommentsFile);//对文件进行上传
        //根据专家组意见文件的id，新增最终验收报告中专家组意见的id
        acceptApplyMapper.updateExpertGroupFileId(caId, uploadExpertGroupCommentsFile.getId());

        //对专家组评议表文件进行上传
        String expertAcceptanceFormFileUrl = FileUploadUtil.fileUpload(expertAcceptanceFormFile, cname, "专家组评议");
        //把专家组评议上传到upload_file中
        UploadFile uploadExpertAcceptanceFormFileUrl = IntegrationFile.IntegrationFile(expertAcceptanceFormFile, expertAcceptanceFormFileUrl, "专家组评议", uname);
        acceptApplyMapper.uploadFile(uploadExpertAcceptanceFormFileUrl);//对文件进行上传
        //根据专家组评议文件的id，新增最终验收报告中专家组评议的id
        acceptApplyMapper.updateExpertAcceptanceFormFileId(caId, uploadExpertAcceptanceFormFileUrl.getId());

        extranetExpertGroupComment.setCreateAuthor(uname);  //存入创建人名
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        extranetExpertGroupComment.setCreateTime(nowTime);//存入创建时间

        //把专家组主表信息存储到数据库中
        acceptApplyMapper.addExpertGroupComment(extranetExpertGroupComment);

        //把专家组从表存储到数据库中
        List<ExtranetExpertGroupCommentsName> extranetExpertGroupCommentsNameList = extranetExpertGroupComment.getExtranetExpertGroupCommentsNameList();
        for (ExtranetExpertGroupCommentsName extranetExpertGroupCommentsName : extranetExpertGroupCommentsNameList) {
            acceptApplyMapper.addExpertGroupCommentName(extranetExpertGroupComment.getEgcId(), extranetExpertGroupCommentsName);
        }

        //更新验收申请的状态表

        //首先更新上一条表的状态
        String state = "已处理";
        String handleContent = "审核通过";
        //根据数据的id，把处理人，审核状态，审核内容，处理时间更新
        acceptApplyMapper.updateCheckApplyState(caId, uname, state, handleContent, nowTime);

        //新增下一条状态数据
        String auditStep = "等待审核公司上传的专家文件";
        String newState = "等待审核";
        acceptApplyMapper.addNewCheckApplyState(caId, uname, auditStep, nowTime, newState);

        return resultMap.success().message("提交成功");
    }

    //验收申请的修改
    @Override
    public ResultMap modifyApply(String token, HttpServletResponse response, String oldSubmitInventoryFileUrl, String oldAchievementsFileUrl, String oldApplicationAcceptanceFileUrl, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, ExtranetCheckApply extranetCheckApply) throws Exception {
//        JwtInformation jwtInformation = new JwtInformation();
//        try {
//            jwtInformation = tokenService.compare(response, token);
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (UserNameNotExistentException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (ClaimsNullException e) {
//            e.printStackTrace();
//            return resultMap.fail().message("请先登录");
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("MenuServiceImpl 中 TokenService 出现问题");
//            return resultMap.message("系统异常");
//        }
//
//        Integer uid = jwtInformation.getUid();
//        String uname = jwtInformation.getUsername();
//        Integer cid = jwtInformation.getCid();
//        String cname = jwtInformation.getCompanyName();

        String uname = "修改人名";
        String cname = "修改公司名";

        //判断三个旧文件是否为空
        if(oldSubmitInventoryFileUrl!=null){
            //提交清单不为空
            //判断文件输入的格式是否正确
            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String submitInventoryFileName = submitInventoryFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(submitInventoryFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的提交清单格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldSubmitInventoryFileUrl);
            file.delete();

            //对新的提交清单进行上传
            String submitInventoryFileUrl = FileUploadUtil.fileUpload(submitInventoryFile, cname, "提交清单");
            //把提交清单文件上传到upload_file中
            UploadFile uploadSubmitInventoryFile = IntegrationFile.IntegrationFile(submitInventoryFile, submitInventoryFileUrl, "提交清单", uname);
            acceptApplyMapper.uploadFile(uploadSubmitInventoryFile);//对文件进行上传
            //把上传文件的id，存入checkApply中
            extranetCheckApply.setSubmitUrlId(uploadSubmitInventoryFile.getId());
        }

        if(oldAchievementsFileUrl!=null){
            //旧的成果附件不为null时
            //判断文件输入的格式是否正确
            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String achievementsFileName = achievementsFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(achievementsFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的成果附件格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldAchievementsFileUrl);
            file.delete();

            //对新的提交清单进行上传
            String achievementsFileUrl = FileUploadUtil.fileUpload(achievementsFile, cname, "成果附件");
            //把提交清单文件上传到upload_file中
            UploadFile uploadAchievementsFile = IntegrationFile.IntegrationFile(achievementsFile, achievementsFileUrl, "成果附件", uname);
            acceptApplyMapper.uploadFile(uploadAchievementsFile);//对文件进行上传
            //把上传文件的id，存入checkApply中
            extranetCheckApply.setAchievementUrlId(uploadAchievementsFile.getId());
        }

        if(oldApplicationAcceptanceFileUrl!=null){
            //旧的验收申请不为null时
            ArrayList<String> idCardFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".rar", ".zip", ".7z", ".pdf"));
            String applicationAcceptanceFileName = applicationAcceptanceFile.getOriginalFilename();
            Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(applicationAcceptanceFileName, idCardFileSuffixList);
            if (aBoolean == false) {
                return resultMap.fail().message("请上传正确的成果附件格式");
            }
            //再根据旧的文件地址，先把文件给删除掉
            File file = new File(oldApplicationAcceptanceFileUrl);
            file.delete();

            //对新的提交清单进行上传
            String applicationAcceptanceFileUrl = FileUploadUtil.fileUpload(applicationAcceptanceFile, cname, "验收申请表");
            //把提交清单文件上传到upload_file中
            UploadFile uploadApplicationAcceptanceFile = IntegrationFile.IntegrationFile(applicationAcceptanceFile, applicationAcceptanceFileUrl, "验收申请表", uname);
            acceptApplyMapper.uploadFile(uploadApplicationAcceptanceFile);//对文件进行上传
            //把上传文件的id，存入checkApply中
            extranetCheckApply.setAchievementUrlId(uploadApplicationAcceptanceFile.getId());
        }

        acceptApplyMapper.updateCheckApply(extranetCheckApply);
        return resultMap.fail().message("修改成功");
    }
}