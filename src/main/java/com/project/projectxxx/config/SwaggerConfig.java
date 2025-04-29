package com.project.projectxxx.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI openAPI (){
        return new OpenAPI().components(new Components()).info(apiInfo());
    }
    private Info apiInfo(){
        return new Info()
                .title("project").description("projectxxx").version("1.0.0");
    }
}
