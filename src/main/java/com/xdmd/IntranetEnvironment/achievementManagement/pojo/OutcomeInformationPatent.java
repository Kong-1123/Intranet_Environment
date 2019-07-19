package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

//成果信息专利表
@Data
public class OutcomeInformationPatent {

    //主键id
    @Id
    @GeneratedValue
    private  Integer id;

    //成果信息id
    private  Integer achievementsId;

    //序号
    @NotNull(message = "专利序号不能为空")
    private  String serialNumber;

    //成果名称
    @NotNull(message = "专利名称不能为空")
    private  String outcomeName;

    //专利申请时间
    @NotNull(message = "专利申请时间不能为空")
    private Date patentApplicationTime;

    //专利申请号/专利号
    @NotNull(message = "专利号不能为空")
    private  String patentApplicationNumber;

    public OutcomeInformationPatent(Integer achievementsId, @NotNull(message = "专利需要不能为空") String serialNumber, @NotNull(message = "专利名称不能为空") String outcomeName, @NotNull(message = "专利申请时间不能为空") Date patentApplicationTime, @NotNull(message = "专利号不能为空") String patentApplicationNumber) {
        this.achievementsId = achievementsId;
        this.serialNumber = serialNumber;
        this.outcomeName = outcomeName;
        this.patentApplicationTime = patentApplicationTime;
        this.patentApplicationNumber = patentApplicationNumber;
    }

    public OutcomeInformationPatent() {
    }
}
