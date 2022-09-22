package com.up3d.link.pojo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-22  15:16
 * @Description: 病历号回显信息
 */
@ApiModel("病历号回显")
@Data
public class MedicalRecordNumberResp {

    @ApiModelProperty("")
    private Long id;

    @ApiModelProperty("病历号配置类型")
    private Integer type;

    @ApiModelProperty("遍历号前缀")
    private String prefix;

    @ApiModelProperty("年号")
    private String year;

    @ApiModelProperty("月份")
    private String month;

    @ApiModelProperty("当月几号")
    private String day;

    @ApiModelProperty("流水号")
    private String serialNumber;

    @ApiModelProperty("下一位流水值")
    private Integer next;

    @ApiModelProperty("当前病历号")
    private String medicalRecordNumber;

    @ApiModelProperty("是否为当前公司的系统配置 0-不是 1-是")
    private Boolean isSys;

}
