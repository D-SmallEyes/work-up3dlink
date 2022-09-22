package com.up3d.link.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * 病历号规则表
 * @author dongxuanchen
 * @description 杭州云甲科技
 * @date 2022/09/22
 */
@ApiModel("病历号规则表")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties({"createUserId","modifiedUserId","gmtCreate","gmtModified","isDelete"})
public class DrSaasMedicalRecordNumber {


    @ApiModelProperty("")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("公司id")
    private Long companyId;

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

    @ApiModelProperty(value = "创建时间",hidden =true)
    private Integer gmtCreate;
}

