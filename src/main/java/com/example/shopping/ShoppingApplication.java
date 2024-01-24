package com.example.shopping;

import com.example.shopping.config.ShoppingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class ShoppingApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShoppingApplication.class, args);
	}

}
