package com.up3d.link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: dongxuanchen
 * @CreateTime: 2022-09-21  15:20
 * @Description: 启动类
 */
@SpringBootApplication
@ComponentScan("com.up3d.link.*")
public class SaasApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaasApplication.class,args);
    }
}
