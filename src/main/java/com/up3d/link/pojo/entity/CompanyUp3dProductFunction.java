package com.up3d.link.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-07-28  14:14
 */
@ApiModel("公司购买的云甲产品功能")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonIgnoreProperties({"createUserId","modifiedUserId","gmtCreate","gmtModified","isDelete"})
public class CompanyUp3dProductFunction {


    @ApiModelProperty("")
    private Integer id;

    @ApiModelProperty("")
    private Integer companyId;

    @ApiModelProperty("商品id")
    private Integer up3dProductId;

    @ApiModelProperty("父级id")
    private Integer parentId;

    @ApiModelProperty("功能键名")
    @TableField("`key`")
    private String key;

    @ApiModelProperty("功能id")
    private Integer functionId;

    @ApiModelProperty("开始时间")
    private Integer startTime;

    @ApiModelProperty("结束时间")
    private Integer endTime;

    @ApiModelProperty("次数（先时间，时间不够再按次数）")
    private Integer count;

    @ApiModelProperty(value = "",hidden =true)
    private Integer gmtCreate;

    @ApiModelProperty("1正常，2过期，3禁用")
    private Integer status;

    private Boolean isDelete;

    @TableField(exist = false)
    private List<CompanyUp3dProductFunction> companyUp3dPFChildList;
}

