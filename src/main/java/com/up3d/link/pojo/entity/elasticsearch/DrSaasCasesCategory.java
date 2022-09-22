package com.up3d.link.pojo.entity.elasticsearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * 病例模板类目表
 * @author lichaojie
 * @description 杭州云甲科技
 * @date 2022/08/31
 */
@ApiModel("病例模板类目表")
@Data

public class DrSaasCasesCategory {


    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("父id")
    private Long parentId;

    @ApiModelProperty("公司id user_companyinfo_id")
    private Long companyId;

    @ApiModelProperty("类目名称")
    private String name;

    @ApiModelProperty(value = "创建时间",hidden =true)
    private Integer gmtCreate;

    @ApiModelProperty(value = "是否删除  0、删除  1存在",hidden =true)
    private Boolean isDelete;
}

