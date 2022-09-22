package com.up3d.link.controller;

import com.up3d.link.common.result.Result;
import com.up3d.link.service.SysProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-15  15:09
 */
@Api(tags = "stripe支付流程")
@RestController
@RequestMapping("/up3d/stripePay")
public class StripePayController {

    @Autowired
    private SysProductService sysProductService;

    /**
     *
     * @return
     */
    @GetMapping("/createProduct")
    public Result<List<String>> createProduct(){
//        Stripe.apiKey = "sk_test_51LTGwYAKQ4ll3SWpXjFHC2BNEVX6SE3h9XC9S88b1PKBKQ3y29r8keSnVtpx9j6CUtCMFoPKDEoAVcWJuZshGrAG00XWHZ5rrc";
//        List<SysProduct> sysProductList = sysProductService.getAll();
//        List<String> productList = new ArrayList<>();
//        sysProductList.stream().forEach(sysProduct -> {
//            ProductCreateParams params = ProductCreateParams.builder()
//                    .setName(sysProduct.getName())
//                    .setDescription(sysProduct.getIntroduceEn())
//                    .addImage(sysProduct.getLogo())
//                    .build();
//            Product product = null;
//            try {
//                product = Product.create(params);
//            } catch (StripeException e) {
//                e.printStackTrace();
//            }
//            productList.add(product.getId());
//        });
//        return R.<List<String>>getSuccessWrapper().data(productList).build();
        return null;
    }

}
