package com.example.shopping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingConfig {

    @Value("${shopping.var1}")
    private String var1;
    @Value("${shopping.var2}")
    private String var2;
    @Value("${shopping.var3}")
    private String var3;

    @Bean
    ShoppingConfigBean getConfig() {
        ShoppingConfigBean config =
          ShoppingConfigBean
            .builder()
            .withVar1(var1)
            .withVar2(var2)
            .withVar3(var3)
            .build();
        return config;
    }
}
