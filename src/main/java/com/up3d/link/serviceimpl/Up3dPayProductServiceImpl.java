package com.up3d.link.serviceimpl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.param.ProductCreateParams;
import com.up3d.link.mapper.Up3dPayProductMapper;
import com.up3d.link.pojo.entity.Up3dPayProduct;
import com.up3d.link.pojo.req.ProductReq;
import com.up3d.link.service.SysProductService;
import com.up3d.link.service.Up3dPayProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-17  10:35
 */
@Service
@Log4j2
public class Up3dPayProductServiceImpl extends ServiceImpl<Up3dPayProductMapper, Up3dPayProduct> implements Up3dPayProductService {

    public static final String API_KEY = "sk_test_51LTGwYAKQ4ll3SWpXjFHC2BNEVX6SE3h9XC9S88b1PKBKQ3y29r8keSnVtpx9j6CUtCMFoPKDEoAVcWJuZshGrAG00XWHZ5rrc";

    @Resource
    private Up3dPayProductMapper up3dPayProductMapper;

    @Autowired
    private SysProductService sysProductService;


    /**
     * 创建套餐商品
     *
     * @param productReq
     * @return 第三方存储的产品唯一主键Id
     */
    @Override
    public List<String> createProduct(ProductReq productReq) {
        Stripe.apiKey = API_KEY;
        Product product = null;
        try {
            ProductCreateParams params = ProductCreateParams.builder()
                    .setDescription(productReq.getDescription())
                    .build();
            product = Product.create(params);
            //保存自己系统的产品信息
        } catch (StripeException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }catch (Exception ee){
            log.error(ee.getMessage());
        }


        return null;
    }

    private void insertUpe3dProduct(Product product,Integer saleVersion) {
        //参数校验
        if (ObjectUtils.isEmpty(product)){
            throw new RuntimeException( "Failure to create a product");
        }
        //判断产品是否存在

        //封装自己的产品数据
        Up3dPayProduct up3dPayProduct = new Up3dPayProduct();
        up3dPayProduct.setActive(product.getActive());
        up3dPayProduct.setSaleVersionId(saleVersion);
        up3dPayProduct.setProductName(product.getName());
        if (product.getImages() != null){
            up3dPayProduct.setImages(product.getImages().get(0));
        }
        up3dPayProduct.setOutProductId(product.getId());
        if (product.getDescription() != null){
            up3dPayProduct.setDescribe(product.getDescription());
        }
        if (product.getUpdated() != null){
            up3dPayProduct.setUpdatedTime(product.getUpdated());
        }
    }
}
