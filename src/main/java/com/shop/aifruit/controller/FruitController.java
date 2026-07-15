package com.shop.aifruit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.aifruit.entity.Fruit;
import com.shop.aifruit.service.FruitService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fruit")
public class FruitController {
    @Resource
    private FruitService fruitService;

    @GetMapping("page")
    public Page<Fruit> getFruitPage(
            @RequestParam(defaultValue = "1") Long pageNum,
            @RequestParam(defaultValue = "5") Long pageSize
    ){
        Page<Fruit> page = new Page<>(pageNum, pageSize);
        return fruitService.page(page, new LambdaQueryWrapper<>());
    }
}