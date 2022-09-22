package com.up3d.link.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-17  14:50
 */
@ApiModel("spu")
@Data
public class Spu {

    @ApiModelProperty("主键Id")
    private Long id;

    @ApiModelProperty("租户Id")
    private Integer tenantId;

    @ApiModelProperty("三方sku标识")
    private String thirdSpuNo;

    @ApiModelProperty("我们的sku唯一标识，为组合字段 租户id+类型+skuid")
    private String ourSpuNo;

    @ApiModelProperty("商品标题-名字")
    private String title;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("图片多个使用`,`分隔")
    private String imgs;

    @ApiModelProperty(value = "1上架 2下架  3禁用  -1删除 ",hidden =true)
    private Integer status;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date gmtCreate;

}
