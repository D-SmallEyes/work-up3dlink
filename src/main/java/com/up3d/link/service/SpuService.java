package com.up3d.link.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.up3d.link.pojo.entity.Spu;
import com.up3d.link.pojo.req.ProductReq;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-17  15:29
 */
public interface SpuService extends IService<Spu> {

    /**
     * 创建套餐商品
     * @param productReq
     * @return 第三方存储的产品唯一主键Id
     */
    String createProduct(ProductReq productReq);
}
