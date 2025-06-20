package com.example.springsecurity6.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("내 API 문서")
                        .version("v1.0")
                        .description("Spring Boot 3.5에서 사용하는 Swagger 문서입니다."));
    }

}
