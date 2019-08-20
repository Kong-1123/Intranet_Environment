package com.xdmd.IntranetEnvironment.guidemanagement.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollection;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollectionLimitTime;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideSummary;
import org.apache.ibatis.annotations.Param;

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
    ResultMap insertGuideInfo(GuideCollection guideCollection);

    /**
     * 更新时间
     * @param guideCollectionLimitTime
     * @return
     */
    ResultMap updateLimitTime(GuideCollectionLimitTime guideCollectionLimitTime);

    /**
     * 新增汇总信息【单条插入】
     * @param guideSummary
     * @return
     */
    ResultMap insertSummary(GuideSummary guideSummary);
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
     * 根据汇总标题查询出汇总指南
     * @return
     */
    ResultMap getSummaryByGuideSummaryTitle(@Param("guideSummaryTitle") String guideSummaryTitle);

    /**
     * 根據单位id查詢相应单位的指南申报
     * @param Uid
     * @return
     */
    ResultMap getCollectionByUid(String guideName, Integer domain, Integer category, String fillUnit, String fillContacts, String contactPhone, int Uid, int pageNum, int pageSize);

    /**
     * [新增]单位关联指南征集【暂不做】
     * @param unitId
     * @param collectionId
     * @return
     */
    ResultMap insert(int unitId, int collectionId);
    /**
     * 根据勾选的指南id获取选相应指南申报信息
     * @param
     * @return
     */
    ResultMap getCollectionByIds(List<Long> ids);
}

