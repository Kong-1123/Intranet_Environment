package com.xdmd.IntranetEnvironment.guidemanagement.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  guide_declaration
 * @author Kong 2019-07-15
 */
@Data
@ApiModel("指南申报实体类")
public class GuideCollection {

    @ApiModelProperty(name="主键【注:系统默认生成,新增时不用填】",required = false)
    private Integer id;

    @ApiModelProperty("指南建议申报")
    private String guideName;

    @ApiModelProperty("所属领域")
    private Integer domain;

    @ApiModelProperty("所属类别")
    private Integer category;

    @ApiModelProperty("填报单位")
    private String fillUnit;

    @ApiModelProperty("填报联系人")
    private String fillContacts;

    @ApiModelProperty("单位负责人(签字)")
    private String unitPrincipal;

    @ApiModelProperty("建议理由及依据(300字以内)")
    private String reasonBasis;

    @ApiModelProperty("主要研究内容和关键技术(300字以内)")
    private String researchContentTechnology;

    @ApiModelProperty("预期目标和成果（200字以内）")
    private String expectedTargetOutcome;

    @ApiModelProperty("拟出标准、技术规范、法规名称")
    private String standardsSpecificationsRegulatory;

    @ApiModelProperty("研究期限")
    private String researchPeriod;

    @ApiModelProperty("研究经费测算（万元）")
    private BigDecimal researchFund;

    @ApiModelProperty("示范工程规模")
    private String demonstrationScale;

    @ApiModelProperty("示范工程点")
    private String demonstrationPoint;

    @ApiModelProperty("省内从事该领域的主要研究机构")
    private String provinceDomainMechanism;

    @ApiModelProperty("联系电话(手机)")
    private String contactPhone;

    @ApiModelProperty(value ="是否选中（0-未选中；1-已选中）【注:系统默认生成,新增时不用填】",required = false)
    private Integer isSelect;

    @ApiModelProperty(value ="创建时间【注:系统默认生成,新增时不用填】",required = false)
    private Date createTime;



    public GuideCollection() {
    }

}