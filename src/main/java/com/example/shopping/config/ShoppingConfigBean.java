package com.example.shopping.config;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
public class ShoppingConfigBean {
    private String var1;
    private String var2;
    private String var3;
}
