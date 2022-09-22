package com.up3d.link.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 *
 * 云甲产品表
 * @author lichaojie
 * @description 杭州云甲科技
 * @date 2021/06/24
 */
@ApiModel("云甲产品表")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysProduct {


    @ApiModelProperty("")
    private Integer id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("logo")
    private String logo;

    @ApiModelProperty("介绍key-sys_international")
    @JsonIgnore
    private String introduceKey;

    @ApiModelProperty("注册表key")
    private Integer registryKey;

    @ApiModelProperty(value = "创建时间",hidden =true)
    private Integer gmtCreate;

    @ApiModelProperty(value = "更新时间",hidden =true)
    private Integer gmtModified;

    @ApiModelProperty(value = "创建人")
    private Long createUserId;

    @ApiModelProperty(value = "更新者")
    private Long modifiedUserId;

    @ApiModelProperty(value = "是否删除  0、删除    1、存在", hidden = true)
    private Boolean isDelete;

    @ApiModelProperty("产品介绍英文")
    private String introduceEn;

    @ApiModelProperty("产品介绍中文")
    private String introduceZh;

}

