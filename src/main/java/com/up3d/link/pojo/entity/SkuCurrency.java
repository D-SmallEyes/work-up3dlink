package com.up3d.link.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-17  15:03
 */
@ApiModel("sku")
@Data
public class SkuCurrency {

    @ApiModelProperty("主键Id")
    private Long id;

    @ApiModelProperty("商品Id")
    private Long spuId;

    @ApiModelProperty("售卖商品Id")
    private Long skuId;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("商品标题-名字")
    private String title;

    @ApiModelProperty("币种id")
    private Integer currencyId;

    @ApiModelProperty(value = "1上架 2下架  3禁用  -1删除 ",hidden =true)
    private Integer status;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date gmtCreate;
}
