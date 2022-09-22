package com.up3d.link.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-17  14:50
 */
@ApiModel("sku")
@Data
public class Sku {

    @ApiModelProperty("主键Id")
    private Long id;

    @ApiModelProperty("商品的唯一id")
    private Long spuId;

    @ApiModelProperty("我们的sku唯一标识，为组合字段 租户id+类型+skuid")
    private String ourSkuNo;

    @ApiModelProperty("三方sku标识")
    private String thirdSkuNo;

    @ApiModelProperty("订单标题")
    private String title;

    @ApiModelProperty("产品图片url")
    private String imgs;

    @ApiModelProperty(value = "1上架 2下架  3禁用  -1删除 ",hidden =true)
    private Integer status;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date gmtCreate;


}
