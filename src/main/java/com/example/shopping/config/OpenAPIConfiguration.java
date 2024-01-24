package com.example.shopping.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

  @Bean
  public OpenAPI customApiDocsInfo() {
    return new OpenAPI().info(new Info().version("1").title("Shopping sample API"));
  }


}
