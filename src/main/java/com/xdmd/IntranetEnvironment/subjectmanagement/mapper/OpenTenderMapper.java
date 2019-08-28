package com.xdmd.IntranetEnvironment.subjectmanagement.mapper;

import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/07/26
 * @description:
 */
@Repository
public interface OpenTenderMapper {
    /**
     * 新增招标备案【waiwang】
     * @param openTender
     * @return
     */
    @Insert(value = "INSERT INTO open_tender(\n" +
            "project_no,\n" +
            "project_name,\n" +
            "tender_no,\n" +
            "subcontracting_no,\n" +
            "subject_name,\n" +
            "responsible_unit,\n" +
            "bidders,\n" +
            "subject_leader,\n" +
            "leader_contact,\n" +
            "join_tender_units,\n" +
            "operator,\n" +
            "operator_contact,\n" +
            "winning_amount,\n" +
            "supporting_funds,\n" +
            "remark)"+
            "VALUES(" +
            "#{projectNo},\n" +
            "#{projectName},\n" +
            "#{tenderNo},\n" +
            "#{subcontractingNo},\n" +
            "#{subjectName}," +
            "#{responsibleUnit}," +
            "#{bidders},\n" +
            "#{subjectLeader},\n" +
            "#{leaderContact},\n" +
            "#{joinTenderUnits},\n" +
            "#{operator},\n" +
            "#{operatorContact},\n" +
            "#{winningAmount},\n" +
            "#{supportingFunds},\n" +
            "#{remark})")
    int insertTender(OpenTender openTender);

    /**
     * 获取最新的id【waiwang-课题编号】
     * @return
     */
    @Select(value = "SELECT id,project_no FROM open_tender ORDER BY id DESC LIMIT 1")
    OpenTender getNewData();



    /**
     * [查詢] 根據单位id查詢相应单位的课题【waiwang】
     * @author Kong
     * @date 2019/07/26
     **/
    @Select(value = "<script>" +
            "SELECT\n" +
            "ot.id," +
            "ot.project_name as projectName,\n" +
            "ot.subject_name as subjectName,\n" +
            "ot.winning_amount as winningAmount,\n" +
            "ot.supporting_funds as supportingFunds,\n" +
            "ot.subject_leader as subjectLeader,\n" +
            "ot.leader_contact as leaderContact,\n" +
            "ot.operator,\n" +
            "ot.operator_contact as operatorContact\n" +
            "FROM\n" +
            "open_tender ot,\n" +
            "unit_tender ut\n" +
            "WHERE\n" +
            "ot.id = ut.tender_id\n" +
            "AND ut.unit_id =#{uid}\n" +
            "<if test ='null != projectName'>\n" +
            "AND project_name like CONCAT('%',#{projectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectLeader'>\n" +
            "AND subject_leader like CONCAT('%',#{subjectLeader},'%')\n" +
            "</if>\n" +
            "<if test ='null != leaderContact'>\n" +
            "AND leader_contact like CONCAT('%',#{leaderContact},'%')\n" +
            "</if>" +
            "</script>")
    List<Map> getTenderByUid(int uid, String projectName, String subjectName, String subjectLeader, String leaderContact);


    /**
     * [查詢] 根據id查詢相应单位的招标备案详情【内外网】
     * @param id
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\n" +
            "id," +
            "project_no as projectNo,\n" +
            "project_name as projectName,\n" +
            "tender_no as tenderNo,\n" +
            "subcontracting_no as subcontractingNo,\n" +
            "subject_name as subjectName,\n" +
            "responsible_unit as responsibleUnit,\n" +
            "bidders,\n" +
            "subject_leader as subjectLeader,\n" +
            "leader_contact as leaderContact,\n" +
            "join_tender_units as joinTenderUnits,\n" +
            "operator,\n" +
            "operator_contact as operatorContact,\n" +
            "winning_amount as winningAmount,\n" +
            "supporting_funds as supportingFunds,\n" +
            "remark\t" +
            "FROM\n" +
            "open_tender\n" +
            "WHERE id =#{id}\n" +
            "</script>")
        Map getTenderById(int id);


    /**
     * [查詢] 分頁查詢【内网】
     * @author Kong
     * @date 2019/07/26
     **/
    @Select(value = "<script>" +
            "SELECT\n" +
            "ot.id," +
            "ot.project_name as projectName,\n" +
            "ot.subject_name as subjectName,\n" +
            "ot.winning_amount as winningAmount,\n" +
            "ot.supporting_funds as supportingFunds,\n" +
            "ot.subject_leader as subjectLeader,\n" +
            "ot.leader_contact as leaderContact,\n" +
            "ot.operator,\n" +
            "ot.operator_contact as operatorContact\n" +
            "FROM\n" +
            "open_tender AS ot\n" +
            "<where>" +
            "<if test ='null != projectName'>\n" +
            "project_name like CONCAT('%',#{projectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectLeader'>\n" +
            "AND subject_leader like CONCAT('%',#{subjectLeader},'%')\n" +
            "</if>\n" +
            "<if test ='null != leaderContact'>\n" +
            "AND leader_contact like CONCAT('%',#{leaderContact},'%')\n" +
            "</if></where>" +
            "</script>")
        List<Map> getTenderPageList(String projectName, String subjectName, String subjectLeader, String leaderContact);

    /**
     * 根据招标备案id更新相应的附件id【外网上传附件】
     * @param winningFileAttachmentId
     * @param announcementTransactionAnnouncementId
     * @param dealNotificationAttachmentId
     * @param
     * @return
     */
    @Update(value = "UPDATE open_tender \n" +
            "SET winning_file_attachment_id = #{winningFileAttachmentId},\n" +
            "announcement_transaction_announcement_id = #{announcementTransactionAnnouncementId},\n" +
            "deal_notification_attachment_id = #{dealNotificationAttachmentId} \n" +
            "response_file_attachment_id = #{responseFileAttachmentId} \n" +
            "WHERE id = #{oid}")
    int updateAnnexByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId,int responseFileAttachmentId, int oid);


    /////////////////招标备案审核//////////////////////////////////

    /**
     * 根据数据的id，把处理人，审核状态，审核内容内容，处理时间更新
     * @param id
     * @param uname
     * @param state
     * @param handleContent
     * @param nowTime
     * @return
     */
    @Update("update tender_contract_shenhejilu set state =#{state},second_handler =#{uname} ,handle_content = #{handleContent} ,second_handle_time = #{date} where id = #{id} order by first_handle_time desc limit 1")
    int updateOpenTenderStateRecord(@Param("id") Integer id, @Param("uname") String uname, @Param("state") String state, @Param("handleContent") String handleContent, @Param("date") String nowTime);

    /**
     * 新增下一条的数据状态
     * @param id
     * @param uname
     * @param auditStep
     * @param nowTime
     * @param newState
     * @return
     */
    @Insert("INSERT INTO tender_contract_shenhejilu(shenhe_table_id, fist_handler, audit_step, first_handle_time, state) VALUES (#{id},#{uname},#{auditStep},#{nowTime},#{newState});")
    int insertNewOpenTenderStateRecord(@Param("id") Integer id, @Param("uname") String uname, @Param("auditStep") String auditStep, @Param("nowTime") String nowTime, @Param("newState") String newState);




    /**
     * 招标备案审核状态【0-单位员工待提交(或不通过被退回时重新提交) 1-单位管理员待审批 2-评估中心员工待审批 3-评估中心员工已审批】
     * @return
     */
    @Update(value = "update open_tender set audit_status=#{auditStatus} where id=#{id}")
    int updateTenderStatus(@Param("auditStatus") int auditStatus,@Param("id") int id);

    /**
     * 评估中心审核通过【备用】
     * @return
    @Update(value = "update open_tender set audit_status=3 where audit_status=2 and id=#{id}")
    int updateTenderStatusPassByPingGu(@Param("id") int id);
     */

    /**
     * 不通过被退回时重新提交
     * @return
     */
    @Update(value = "update open_tender set audit_status=1 where audit_status=0 and id=#{id}")
    int updateTenderStatusByReturnCommit(@Param("id") int id);


    /**
     * 展示所有未通过审批的
     * @return
     */
    @Select("select * from open_tender where audit_status<2")
    OpenTender showAllPassReviewTender();

    /**
     * 展示所有评估中心通过审批的
     * @return
     */
    @Select("select * from open_tender where audit_status=3")
    OpenTender showAllNoPassReviewTender();

    /**
     * 根据招标备案表的id 获取该单位的名字
     * @param oid
     * @return
     */
    @Select("select responsible_unit from open_tender where id = #{oid}")
    String queryUnitNameByOid(@Param("id") Integer oid);
}

