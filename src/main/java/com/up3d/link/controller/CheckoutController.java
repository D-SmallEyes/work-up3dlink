package com.up3d.link.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.up3d.link.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-16  10:19
 */
@Api(tags = "stripe支付管理")
@RestController
@RequestMapping("/up3d/checkout")
public class CheckoutController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutController.class);

    static final String YOUR_DOMAIN = "https://whole-carpets-leave-34-150-12-140.loca.lt/up3d/pages/";

    private static final String PK_KEY = "pk_test_51LTGwYAKQ4ll3SWpwmB1Y2UfQ3AVn5heJxgk9nUihf3lTq2XCmLkaMp0eXhSMaSj4EDnyg7gL7APnSvtC32RtPM900BmJntWhh";

    private static final String API_KEY = "sk_test_51LTGwYAKQ4ll3SWpXjFHC2BNEVX6SE3h9XC9S88b1PKBKQ3y29r8keSnVtpx9j6CUtCMFoPKDEoAVcWJuZshGrAG00XWHZ5rrc";

    /**
     * 获取pkKey
     * @return
     */
    @ApiOperation("获取pkKay")
    @GetMapping("/pkKey")
    public Result<String> getPkKey() {
        return Result.success(PK_KEY);
    }

    /**
     * 创建订单
     * @return
     */
    @ApiOperation("创建订单")
    @PostMapping("/createSession")
    public Result<Map<String, String>> createFastSession(@RequestBody Map<String, Object> reqMap) {
        Stripe.apiKey = API_KEY;
        String productName =(String) reqMap.get("productName");
        BigDecimal price = new BigDecimal((String) reqMap.get("price"));
        // 创建一个结账会话
        SessionCreateParams params = SessionCreateParams.builder()
                // 指定付款方式
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                // 指定结账模式
                .setMode(SessionCreateParams.Mode.PAYMENT)
                // 设置支付成功页面
                .setSuccessUrl(YOUR_DOMAIN + "success")
                // 设置支付取消页面
                .setCancelUrl(YOUR_DOMAIN + "cancel")
                // 定义订单项
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                // 数量
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                // 货币
                                                .setCurrency("usd")
                                                // 设置金额
                                                .setUnitAmountDecimal(price)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                // 设置商品名称
                                                                .setName(productName)
                                                                .build())
                                                .build())
                                .build())
                .build();

        Map<String, String> responseData = new HashMap<>();
        try {
            // 生成sessionId，返回给页面
            Session session = Session.create(params);
            responseData.put("id", session.getId());
        } catch (StripeException e) {
            LOGGER.error(e.toString(), e);
        }

        // 这里可以生成系统订单，看自己业务
        return Result.success(responseData);
    }

    /**
     * 检索订单
     * @param sessionId
     * @return
     */
    @ApiOperation("检索订单")
    @GetMapping("/retrieve")
    public Result<Map> retrieve(String sessionId) {
        Stripe.apiKey = API_KEY;
        // 检索订单，可以根据sessionId获取到该订单最新的信息，付款成功还是未付款来着，这里不作过多描述，可以根据自己业务，对着官方文档，去找字段
        try {
            // Map<String, Object> params = new HashMap<>();
            // params.put("limit", 5);
            // 获取订单项商品信息
            // LineItemCollection lineItems = session.listLineItems(params);
            // 根据sessionId获取最后的操作信息
            Session session = Session.retrieve(sessionId);
            System.out.println(session);
            // 订单信息
            JSONObject jsonObject = JSON.parseObject(session.getLastResponse().body());
            Map map = JSONObject.toJavaObject(jsonObject, Map.class);
            /**
             * lineItems : 订单项信息
             * amount_total : 金额
             * currency : 货币
             * customer_details : 用户信息
             * payment_status : 支付状态
             *          paid : The payment funds are available in your account.
             *          unpaid : The payment funds are not yet available in your account.
             *          no_payment_required : The Checkout Session is in setup mode and doesn’t require a payment at this time.
             *
             */
            return Result.success(map);
        } catch (StripeException e) {
            LOGGER.error(e.toString(), e);
        }

        return Result.success();
    }



}
