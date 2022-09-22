package com.up3d.link.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-22  15:40
 */
@ApiModel("编辑病历号规则")
@Data
public class CreateMedicalRecordNumberReq {

    @ApiModelProperty("病历号类型")
    private Integer type;

    @ApiModelProperty("病历号前缀")
    private String prefix;

    @ApiModelProperty("下一个流水值")
    private Integer nextValue;

    @ApiModelProperty("自定义流水号值")
    private String customizeString;
}
