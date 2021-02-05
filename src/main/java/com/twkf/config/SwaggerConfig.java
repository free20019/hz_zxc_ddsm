package com.twkf.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: xianlehuang
 * @Description:
 * @date: 2020/4/28 - 17:28
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
//@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.twkf"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("服务:杭州自行车扫码——钉钉开发")
                .description("杭州自行车扫码")
//                .termsOfServiceUrl("http://192.168.1.198:10070/platformgroup/ms-admin")
                .version("1.0")
                .contact(new Contact("hxl","https://www.baidu.com","https://www.baidu.com"))
                .build();
    }

}
