package com.example.casio.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("watch shop API")
                        .version("v0.0.1")
                        .description("API for Casio shop")
                        .termsOfService("http://swagger.io/terms/")
                        .contact(new Contact()
                                .name("Casio")
                                .email("casio@example.com")
                                .url("https://www.casio.com/"))
                );
    }
}
