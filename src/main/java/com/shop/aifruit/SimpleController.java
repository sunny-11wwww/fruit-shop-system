package com.shop.aifruit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/hello")
    public String hello(){
        return "项目正常运行成功！";
    }
}