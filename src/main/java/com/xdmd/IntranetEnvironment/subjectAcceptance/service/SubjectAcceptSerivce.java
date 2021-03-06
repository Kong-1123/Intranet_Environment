package com.xdmd.IntranetEnvironment.subjectAcceptance.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateAcceptancePhaseException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupComment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface SubjectAcceptSerivce {
    ResultMap SubjectAcceptQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total);

//    ResultMap SubjectAcceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile, Integer acceptanceFinalResultId) throws Exception;

    ResultMap SubjectAcceptStateExpertGroup(String token, HttpServletResponse response, Boolean type, Integer id, ExpertGroupComment expertGroupComment) throws Exception;

    ResultMap SubjectAcceptSave(String token, HttpServletResponse response, Boolean type, String reason, Integer id, Integer acceptanceFinalResultId, ExpertGroupComment expertGroupComment, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile);

    ResultMap SubjectAcceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile, Integer acceptanceFinalResultId, ExpertGroupComment expertGroupComment) throws Exception;
}
