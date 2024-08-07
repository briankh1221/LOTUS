package com.project.lotus.common;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import java.util.Arrays;

@OpenAPIDefinition(info =
@Info(title = "LOTUS PROJECT", description = "LOTUS 중고거래 사이트 API 명세입니다.", version = "v1"))
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi getAuthApi() {

        return GroupedOpenApi.builder()
                .group("AUTH (회원가입, 로그인 관련 API)")
                .pathsToMatch("/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getAdminApi() {

        return GroupedOpenApi.builder()
                .group("ADMIN (관리자 관련 API)")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getUserApi() {

        return GroupedOpenApi.builder()
                .group("USER (이용자 관련 API)")
                .pathsToMatch("/user/**")
                .build();
    }

    @Bean
    public GroupedOpenApi getProductApi() {

        return GroupedOpenApi.builder()
                .group("PRODUCT (중고 물품 관련 API)")
                .pathsToMatch("/product/**")
                .build();
    }

    @Bean
    public OpenAPI openAPI(){

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Authorization", securityScheme))
                .security(Arrays.asList(securityRequirement));
    }
}
