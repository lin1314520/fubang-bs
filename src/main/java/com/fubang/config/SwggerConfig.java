package com.fubang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @auth lwl
 * @since 2021/1/26
 */
@Configuration
@EnableSwagger2
public class SwggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/api/**"))
//                .build()
//                //分组名称
//                .groupName("卫康士前台API")
//                .pathMapping("/")
//                .apiInfo(apiInfo());
//    }

    @Bean
    public Docket admin_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/admin/**"))
                .build()
                .groupName("卫康士后台API")
                .pathMapping("/")
                .apiInfo(adminApiInfo());
    }
//
//    @Bean
//    public Docket ops_api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.ant("/ops/**"))
//                .build()
//                .groupName("卫康士门诊后台API")
//                .pathMapping("/")
//                .apiInfo(adminApiInfo());
//    }

/*    private ApiInfo opsApiInfo() {
        return new ApiInfoBuilder()
                .title("卫康士门诊后台API文档")
                .version("1.0.0")
                .build();
    }*/

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("卫康士API文档")
                .version("1.0.0")
                .build();
    }

/*    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("卫康士用户API文档")
                .version("1.0.0")
                .build();
    }*/
}
