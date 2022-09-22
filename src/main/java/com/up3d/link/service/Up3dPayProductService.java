package com.up3d.link.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.up3d.link.pojo.entity.Up3dPayProduct;
import com.up3d.link.pojo.req.ProductReq;

import java.util.List;

/**
 * @author dongxuchen
 */
public interface Up3dPayProductService extends IService<Up3dPayProduct> {


    /**
     * 创建套餐商品
     * @param productReq
     * @return 第三方存储的产品唯一主键Id
     */
    List<String> createProduct(ProductReq productReq);

}
