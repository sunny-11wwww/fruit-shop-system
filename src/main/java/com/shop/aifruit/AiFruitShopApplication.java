package com.shop.aifruit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shop.aifruit.mapper")
public class AiFruitShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiFruitShopApplication.class, args);
    }
}