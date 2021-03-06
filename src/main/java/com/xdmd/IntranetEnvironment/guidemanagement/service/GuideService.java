package com.xdmd.IntranetEnvironment.guidemanagement.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollection;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollectionLimitTime;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideSummary;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface GuideService {

    /**
     * 分页查询指南申报
     * @param guideName
     * @param domain
     * @param category
     * @param fillUnit
     * @param fillContacts
     * @param contactPhone
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultMap getCollectionByParam(String guideName, Integer domain, Integer category, String fillUnit, String fillContacts, String contactPhone, int pageNum, int pageSize);

    /**
     * 获取类别和领域
     * @return
     */
    ResultMap getCategoryAndDomain();

    /**
     * 新增指南申报
     * @param guideCollection
     * @return
     */
    ResultMap insertGuideInfo(String token, HttpServletResponse response,GuideCollection guideCollection);

    /**
     * 更新时间
     * @param guideCollectionLimitTime
     * @return
     */
    ResultMap updateLimitTime(GuideCollectionLimitTime guideCollectionLimitTime);


    /**
     * 新增汇总信息【批量插入】
     * @param guideSummary
     * @return
     */
    ResultMap batchInsertSummary(List<GuideSummary> guideSummary);

    /**
     * 分页查询出所有汇总信息
     * @return
     */
    ResultMap getSummaryByParam(String guideSummaryTitle, String fillUnit, Integer domain, Integer category, String projectTime, String researchContentTechnology, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
    /**
     * 根据汇总创建时间查询出汇总指南
     * @return
     */
    ResultMap getSummaryByCreateTime(@Param("createTime")String createTime);


    /**
     * 根據单位id查詢相应单位的指南申报
     * @param uid
     * @return
     */
    ResultMap getUnitCollection(String token, HttpServletResponse response, String guideName, Integer domain, Integer category, String fillUnit, String fillContacts, String contactPhone, int uid, int pageNum, int pageSize);

    /**
     * [新增]单位关联指南征集
     * @param unitId
     * @param collectionId
     * @return
     */
    ResultMap insertCidAndUid(int unitId, int collectionId);


    /**
     * 根据勾选的指南id获取选相应指南申报信息
     * @param
     * @return

    ResultMap updateIsSelectByIds(List<Long> ids);
    */
}

