package com.up3d.link.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.param.ProductCreateParams;
import com.up3d.link.mapper.SpuMapper;
import com.up3d.link.pojo.entity.Spu;
import com.up3d.link.pojo.req.ProductReq;
import com.up3d.link.service.SpuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-17  15:30
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {

    public static final String API_KEY = "sk_test_51LTGwYAKQ4ll3SWpXjFHC2BNEVX6SE3h9XC9S88b1PKBKQ3y29r8keSnVtpx9j6CUtCMFoPKDEoAVcWJuZshGrAG00XWHZ5rrc";

    @Resource
    private SpuMapper spuMapper;

    /**
     * 创建套餐商品
     *
     * @param productReq
     * @return 第三方存储的产品唯一主键Id
     */
    @Override
    public String createProduct(ProductReq productReq) {
        Stripe.apiKey = API_KEY;
        Product product = null;
        try {
            ProductCreateParams params = ProductCreateParams.builder()
                    .setName(productReq.getTitle())
                    .setDescription(productReq.getDescription())
                    .addAllImage(productReq.getImageUrl())
                    .build();
            product = Product.create(params);
            //保存自己系统的产品信息
            insertOrUpdateSpu(product);
        } catch (StripeException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }catch (Exception ee){
            log.error(ee.getMessage());
        }
        return product.getId();
    }

    private void insertOrUpdateSpu(Product product) {
        //参数校验
        if (ObjectUtils.isEmpty(product)){
            throw new RuntimeException("Failure to create a product");
        }
        //判断是否存在
        QueryWrapper<Spu> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("third_spu_no",product.getId());
        Spu spu = spuMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(spu)){
            //新增商品
        }else{
            //修改商品
        }

    }
}
