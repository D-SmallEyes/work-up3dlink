package com.up3d.link.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                //每个group就是一个Api页面
                .groupName("adminApi")
                //Api详细表述信息
                .apiInfo(adminApiInfo())
                .select()
                //采用断言式正则匹配，只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/up3d/.*")))
                .build();
    }

    public ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("excel导入-API文档")
                .description("本文档描述了excel导入系统接口")
                .version("1.0")
                .contact("")
                .build();
    }
}
