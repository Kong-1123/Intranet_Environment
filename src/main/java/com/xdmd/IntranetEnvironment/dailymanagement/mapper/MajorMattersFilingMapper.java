package com.xdmd.IntranetEnvironment.dailymanagement.mapper;


import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustTypeDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustmentMattersDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 重大事项管理
 */
@Repository
public interface MajorMattersFilingMapper {
    /**
     * [新增]重大事项变更
     * @author Kong
     * @date 2019/08/19
     **/
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert("INSERT INTO major_matters_filing (\n" +
            "subject_name,\n" +
            "commitment_unit,\n" +
            "unit_head,\n" +
            "unit_head_phone," +
            "project_no,\n" +
            "adjust_type_id,\n" +
            "adjustment_matters_id,\n" +
            "specific_facts)\n" +
            "VALUES(\n" +
            "#{subjectName},\n" +
            "#{commitmentUnit},\n" +
            "#{unitHead},\n" +
            "#{unitHeadPhone}," +
            "#{projectNo},\n" +
            "#{adjustTypeId},\n" +
            "#{adjustmentMattersId},\n" +
            "#{specificFacts})")
    int insert(MajorMattersFilingDTO majorMattersFiling);


    /**
     * [查詢] 根据单位id分頁筛选查詢【外网】
     * @author Kong
     * @date 2019/08/19
     **/
    @Select("<script>" +
            "SELECT\n" +
            "mmf.id," +
            "mmf.subject_name as subjectName,\n" +
            "mmf.commitment_unit as commitmentUnit,\n" +
            "adt.adjust_type AS adjustType,\n" +
            "am.adjustment_matters AS adjustmentMatters,\n" +
            "mmf.unit_head AS unitHead,\n" +
            "mmf.shenhe_status as shenheStatus\t" +
            "FROM\n" +
            "major_matters_filing as mmf,adjust_type as adt,adjustment_matters as am,unit_major um\n" +
            "<where>\n" +
            "mmf.adjust_type_id=adt.id and mmf.adjustment_matters_id=am.id and adt.id=am.adjust_type_id and mmf.id=major_id and um.unit_id=#{uid}\n" +
            "<if test ='null != subjectName'>\n" +
            "AND mmf.subject_name like CONCAT('%',#{subjectName},'%')"+
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND mmf.commitment_unit like CONCAT('%',#{commitmentUnit},'%')" +
            "</if>\n" +
            "<if test ='null != adjustTypeId'>\n" +
            "AND mmf.adjust_type_id =#{adjustTypeId}" +
            "</if>\n" +
            "<if test ='null != adjustmentMattersId'>" +
            "AND mmf.adjustment_matters_id =#{adjustmentMattersId}" +
            "</if></where>" +
            "order by mmf.id desc" +
            "</script>")
    List<Map> getAllMajorInfoByUid(@Param("uid") int uid, @Param("subjectName") String subjectName, @Param("commitmentUnit")String commitmentUnit,
                                   @Param("adjustTypeId") Integer adjustTypeId, @Param("adjustmentMattersId") Integer adjustmentMattersId);


    /**
     * [更新]重大事项附件id【外网】
     * @author Kong
     * @date 2019/08/19
     **/
    @Update("UPDATE major_matters_filing\t" +
            "SET change_application_attachment_id = #{changeApplicationAttachmentId}," +
            "expert_argumentation_attachment_id = #{expertArgumentationAttachmentId}," +
            "filing_application_attachment_id = #{filingApplicationAttachmentId}," +
            "approval_documents_attachment_id = #{approvalDocumentsAttachmentId}\t" +
            "WHERE id=#{id}")
    int updateMajorAnnexId(int changeApplicationAttachmentId, int expertArgumentationAttachmentId, int filingApplicationAttachmentId, int approvalDocumentsAttachmentId, int id);

    /**
     * [查詢] 根據主鍵 id 查詢【内外网】
     * @author Kong
     * @date 2019/08/19
     **/
    @Select("SELECT * FROM major_matters_filing WHERE id= #{id}")
    MajorMattersFilingDTO getMajorById(@Param("id") int id);


    /**
     * [查詢] 分頁筛选查詢【内网】
     * @author Kong
     * @date 2019/08/19
     **/
    @Select("<script>" +
            "SELECT\n" +
            "mmf.id," +
            "mmf.subject_name as subjectName,\n" +
            "mmf.commitment_unit as commitmentUnit,\n" +
            "adt.adjust_type AS adjustType,\n" +
            "am.adjustment_matters AS adjustmentMatters,\n" +
            "mmf.unit_head AS unitHead,\n" +
            "mmf.shenhe_status\n" +
            "FROM \n" +
            "major_matters_filing as mmf,adjust_type as adt,adjustment_matters as am\n" +
            "<where>\n" +
            "mmf.adjust_type_id=adt.id and mmf.adjustment_matters_id=am.id and adt.id=am.adjust_type_id\t" +
            "<if test ='null != subjectName'>\n" +
            "AND mmf.subject_name like CONCAT('%',#{subjectName},'%')"+
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND mmf.commitment_unit like CONCAT('%',#{commitmentUnit},'%')" +
            "</if>\n" +
            "<if test ='null != adjustTypeId'>\n" +
            "AND mmf.adjust_type_id = #{adjustTypeId}" +
            "</if>\n" +
            "<if test ='null != adjustmentMattersId'>" +
            "AND mmf.adjustment_matters_id = #{adjustmentMattersId}" +
            "</if></where>\n" +
            "order by mmf.id desc" +
            "</script>")
    List<Map> getAllMajorInfo(@Param("subjectName") String subjectName, @Param("commitmentUnit")String commitmentUnit,
                              @Param("adjustTypeId") Integer adjustTypeId, @Param("adjustmentMattersId") Integer adjustmentMattersId);




    /**
     * 更新重大事项的审核状态【内网】
     * @param id
     * @return
     */
    @Update("update major_matters_filing set shenhe_status=1 where shenhe_status=0 and id=#{id}")
    int updateMajorStatus(@Param("id") int id);


    /**
     * 查询所有调整类型
     * @return
     */
    @Select("select * from adjust_type")
    List<AdjustTypeDTO>  getAllAdjustType();

    /**
     * 查询所有调整事項
     * @return
     */
    @Select("select * from adjustment_matters")
    List<AdjustmentMattersDTO>  getAllAdjustmentMatters();


    /**
     * 单位关联重大事项主表
     * @param unitId
     * @param majorId
     * @return
     */
    @Insert(value = "INSERT INTO unit_major(unit_id,major_id)VALUES(#{unitId},#{majorId})")
    int insertMidAndUid(@Param("unitId") int unitId, @Param("majorId") int majorId);
}
