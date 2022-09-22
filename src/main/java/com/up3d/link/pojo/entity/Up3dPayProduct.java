package com.up3d.link.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-17  10:14
 */
@ApiModel("云甲支付产品表")
@Data
@EqualsAndHashCode(callSuper = false)
public class Up3dPayProduct {

    @ApiModelProperty("主键Id")
    private Integer id;

    @ApiModelProperty("对外销售的产品id")
    private Integer saleVersionId;

    @ApiModelProperty("第三方平台产品唯一主键id")
    private String outProductId;

    @ApiModelProperty("产品名")
    private String productName;

    @ApiModelProperty("产品介绍")
    private String describe;

    @ApiModelProperty("产品图片url")
    private String images;

    @ApiModelProperty(value = "更新时间",hidden =true)
    private Long updatedTime;

    @ApiModelProperty(value = "此产品的可公开访问的网页的URL",hidden = true)
    private Long publicUrl;

    @ApiModelProperty(value = "是否支持购买  0、不支持    1、支持", hidden = true)
    private Boolean active;

    @ApiModelProperty(value = "是否删除  0、删除    1、存在", hidden = true)
    private Boolean isDeleted;


}
