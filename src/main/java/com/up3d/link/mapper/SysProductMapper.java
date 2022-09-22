package com.up3d.link.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.up3d.link.pojo.entity.SysProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysProductMapper extends BaseMapper<SysProduct> {

    /**
     * 获取所有公司id
     * @return
     */
    @Select("SELECT id FROM `sys_product`")
    List<Integer> getAllId();

}
