package com.up3d.link.pojo.entity.elasticsearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

/**
 *
 * 处置项目表
 * @author lichaojie
 * @description 杭州云甲科技
 * @date 2022/09/01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "dr_saas_pinyin_search")
public class DrSaasDisposalItemSearch {

    @Field(store = true,type = FieldType.Keyword)
    private Long id;

    @ApiModelProperty("公司id 0系统")
    private Long companyId;

    @ApiModelProperty("处置编码")
    private String code;

    @ApiModelProperty("处置名称")
    @Field(store = true,type = FieldType.Text,analyzer = "ik_max_word")
    private String name;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("单价")
    private BigDecimal unitPrice;

    @ApiModelProperty("费用类型 dr_saas_dictionary_id (type: 7)")
    private Long feeType;

    @ApiModelProperty("费用计算方式 0-按牙齿数计费 1-按其他收费")
    private Boolean priceCalculateMode;

    @ApiModelProperty("处置备注")
    private String remark;

    @ApiModelProperty("处置状态 0：停用 1-启用")
    private Boolean status;

}

