package com.xdmd.IntranetEnvironment.contractmanage.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 课题课题参与单位及课题负责人【合同子表二】
 */
@Data
@ApiModel("课题课题参与单位及课题负责人【合同子表二】")
public class SubjectParticipatingUnitDTO {
    @ApiModelProperty(name="主键【注:系统默认生成,新增时不用填】",required = false)
    private Integer id;

    @ApiModelProperty("合同主表id")
    private Integer contractId;

    @ApiModelProperty("课题承担单位")
    private String bearingUnits;

    @ApiModelProperty("课题参加单位")
    private String participatingUnits;

    @ApiModelProperty("境外合作单位")
    private String overseasCooperationUnits;

    @ApiModelProperty("国家或地区")
    private String country;

    @ApiModelProperty("课题负责人姓名")
    private String leaderName;

    @ApiModelProperty("所在单位")
    private String unitName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("职称")
    private String professionalTitle;

    @ApiModelProperty("从事专业")
    private String professional;

    @ApiModelProperty("本课题中承担工作")
    private String workTask;

    @ApiModelProperty("为本课题工作时间（%）")
    private Double workingTime;


    public SubjectParticipatingUnitDTO() {
    }
}
