package com.up3d.link.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 云甲产品功能表
 * </p>
 *
 * @author 董小眼
 * @since 2022-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Up3dProductFunction对象", description="云甲产品功能表")
public class Up3dProductFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父级id")
    private Integer parentId;

    @ApiModelProperty(value = "云甲产品id")
    private Integer up3dProductId;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "key值，本地服务用")
    @TableField("`key`")
    private String key;

    @ApiModelProperty(value = "创建时间")
    private Integer gmtCreate;

    @TableField("is_delete")
    private Boolean isDelete;


    @TableField(exist = false)
    private List<Up3dProductFunction> childrenList;


}
