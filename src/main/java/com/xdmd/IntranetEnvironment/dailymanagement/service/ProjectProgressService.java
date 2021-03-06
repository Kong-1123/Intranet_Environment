package com.xdmd.IntranetEnvironment.dailymanagement.service;


import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProjectProgressService {
    /**
     * [新增] 课题进展主体
     * @author Kong
     * @date 2019/08/14
     **/
    ResultMap insert(String token, HttpServletResponse response, ProjectProgressDTO progressDTO);

    /**
     * [查詢] 根據主鍵 id 查詢
     * @author Kong
     * @date 2019/08/14
     **/
    ResultMap getInfoById(int id);


    /**
     * 根据单位id查询课题进展
     * @param subjectName
     * @param bearerUnit
     * @param progress
     * @return
     */
    ResultMap getProgressInfoByUid(String token, HttpServletResponse response, String subjectName, String bearerUnit, Integer progress, int pageNum, int pageSize);

    /**
     * [查詢] 分页筛选查询课题进展
     *
     * @param token
     * @param response
     * @param subjectName
     * @param bearerUnit
     * @param progress
     * @return
     */
    ResultMap getInfoByParam(String token, HttpServletResponse response, String subjectName, String bearerUnit, Integer progress, int pageNum, int pageSize);


    /**
     * [新增] 合同要求研发任务【课题进展第一部分】
     * @param contractResearchDevelopmentTasks
     * @return
     */
    ResultMap insertCRDT(List<ContractResearchDevelopmentTasksDTO> contractResearchDevelopmentTasks);

    /**
     * [查詢] 根據课题进展id查詢【课题进展第一部分】
     * @param Pid
     * @return
     */
    ResultMap getCRDTByPid(int Pid);


    /**
     * [新增]目前进展情况【课题进展第二部分】
     * @param currentProgress
     * @return
     */
    ResultMap insertCP(List<CurrentProgressDTO> currentProgress);

    /**
     * [查詢] 根據课题进展id查詢
     * @param Pid
     * @return
     */
    ResultMap getCPByPid(@Param("Pid") int Pid);
    /**
     * [新增] 课题实施中存在的主要问题【课题进展第四部分】
     * @param projectMainProblems
     * @return
     */
    ResultMap insertPMP(List<ProjectMainProblemsDTO> projectMainProblems);

    /**
     * [查詢] 根據课题进展id查詢
     * @param Pid
     * @return
     */
    ResultMap getPMPByPid(@Param("Pid") int Pid);

    /**
     * [新增] 下一步工作计划【课题进展第五部分】
     * @param nextWorkPlan
     * @return
     */
    ResultMap insertNWP(List<NextWorkPlanDTO> nextWorkPlan);

    /**
     * [查詢] 根據课题进展id查詢
     * @param Pid
     * @return
     */
    ResultMap getNWPByPid(@Param("Pid") int Pid);

    /**
     * 根据课题进展主表id更新上传附件id
     * @param openReportAnnexId
     * @param subjectProgressAnnexId
     * @param fundProgressAnnexId
     * @param expertSuggestAnnexId
     * @param pid
     * @return
     */
    ResultMap updateSubjectProgressByPid(int openReportAnnexId, int subjectProgressAnnexId, int fundProgressAnnexId, int expertSuggestAnnexId, int pid);



    /**
     * 课题进展附件上传
     *
     * @param pid
     * @param openReportAnnex     开题报告附件
     * @param expertSuggestAnnex 专家意见附件
     * @param subjectProgressAnnex 课题进展附件
     * @param fundProgressAnnex   进度经费使用情况附件
     * @return
     */
    ResultMap ProgressMultiUpload(String token, HttpServletResponse response, int pid, MultipartFile openReportAnnex, MultipartFile expertSuggestAnnex, MultipartFile subjectProgressAnnex, MultipartFile fundProgressAnnex) throws FileUploadException;



    /**
     * 单位关联课题进度主表
     * @param uid
     * @param pid
     * @return
     */
    ResultMap insertPidAndUid(int uid, int pid);


    /**
     * 获取课题进展附件文件路径和文件名
     * @param pid
     * @return
     */
    ResultMap getfileInfo(int pid);

}
