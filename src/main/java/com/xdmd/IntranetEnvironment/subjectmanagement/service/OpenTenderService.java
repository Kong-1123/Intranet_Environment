package com.xdmd.IntranetEnvironment.subjectmanagement.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 招标业务
 * @author Kong
 * @date 2019/07/26
 */
public interface OpenTenderService {
    /**
     * 新增招标信息
     * @param openTender
     * @return
     */
    ResultMap insertTender(OpenTender openTender);

    /**
     * 根據单位id查詢筛选本单位招标公告
     * @param uid
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    ResultMap getTenderByUid(int uid, String projectName, String subjectName, String subjectLeader, String leaderContact,int pagenNum,int pageSize);

    /**
     * 根據id查詢筛选招标公告详情
     * @param id
     * @return
     */
    ResultMap getTenderById(int id);

    /**
     * 分页筛选查询招标信息
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    ResultMap getTenderPageList(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize);


    /**
     * 根据招标备案id更新相应的附件id
     * @param winningFileAttachmentId
     * @param announcementTransactionAnnouncementId
     * @param dealNotificationAttachmentId
     * @param oid
     * @return
     */
    ResultMap updateTenderByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId,int responseFileAttachmentId, int oid);


    /**
     * 获取最新招标信息
     */
    OpenTender getNewData();

    /**
     * 招标附件上传
     * @param file
     * @param oid
     * @return
     */
    String tenderMultiUpload(MultipartFile file, String fileType, int oid) throws IOException;


    /**
     * 招标备案审核
     * @param type 审核状态
     * @param reason 审核不通过原因
    //* @param fileType 文件类型
     * @param cid 审核表id
    // * @param winningDocument 中标文件附件
    // * @param transactionAnnouncement 成交公告附件
    // * @param noticeTransaction 成交通知书附件
    //* @param responseFile 响应文件附件
     * @return
     */
    //ResultMap tenderShenHe(Boolean type, String reason, String fileType, Integer cid, MultipartFile winningDocument, MultipartFile transactionAnnouncement, MultipartFile noticeTransaction, MultipartFile responseFile);
    ResultMap tenderShenHe(Boolean type, String reason, Integer cid);

}
