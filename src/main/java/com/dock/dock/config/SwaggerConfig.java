package com.dock.dock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Swagger Desafio Dock")
                        .description("API's de um cenário de banco.")
                        .contact(new Contact()
                                .name("Vinícius Amorim da Silva")
                                .email("viniciusamorim2000@outlook.com")
                                .url("https://www.linkedin.com/in/vinicius-amorim-da-silva/"))
                        .version("1.0.0"));
    }
}
