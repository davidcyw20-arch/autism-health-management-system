package com.autismhealth.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI 配置。
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("孤独症儿童健康信息管理系统接口文档")
                .version("1.0.0")
                .description("孤独症儿童健康信息管理系统后端接口文档"));
    }
}
