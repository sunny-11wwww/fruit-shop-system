package com.shop.aifruit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.aifruit.entity.Fruit;
import com.shop.aifruit.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private FruitService fruitService;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);

        long totalFruits = fruitService.count();
        model.addAttribute("totalFruits", totalFruits);

        long totalStock = fruitService.list().stream()
                .mapToInt(Fruit::getStock)
                .sum();
        model.addAttribute("totalStock", totalStock);

        long inStockCount = fruitService.count(
                new LambdaQueryWrapper<Fruit>().gt(Fruit::getStock, 0)
        );
        model.addAttribute("inStockCount", inStockCount);

        long lowStockCount = fruitService.count(
                new LambdaQueryWrapper<Fruit>().le(Fruit::getStock, 30)
        );
        model.addAttribute("lowStockCount", lowStockCount);

        Page<Fruit> page = new Page<>(1, 5);
        Page<Fruit> fruitPage = fruitService.page(page, new LambdaQueryWrapper<Fruit>().orderByDesc(Fruit::getId));
        model.addAttribute("fruitList", fruitPage.getRecords());

        return "index";
    }
}