package com.up3d.link.pojo.req;

import lombok.Data;

import java.util.List;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-17  10:43
 * @Description: strip支付请求参数
 */
@Data
public class ProductReq {

    private List<String> imageUrl;
    private String title;
    private String description;

}
