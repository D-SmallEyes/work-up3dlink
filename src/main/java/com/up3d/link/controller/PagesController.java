package com.up3d.link.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-08-16  13:53
 * @Description: stripe支付回调测试接口
 */
@Controller
@RequestMapping("/up3d/pages")
public class PagesController {


    @GetMapping("/test")
    public String index(){
        return  "index";
    }


    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @GetMapping("cancel")
    public String cancel(){
        return "cancel";
    }
}
