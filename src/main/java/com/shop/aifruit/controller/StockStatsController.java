package com.shop.aifruit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.aifruit.entity.Fruit;
import com.shop.aifruit.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/stats/stock")
public class StockStatsController {

    @Autowired
    private FruitService fruitService;

    @GetMapping
    public String stockStats(@RequestParam(defaultValue = "1") Long pageNum,
                             @RequestParam(defaultValue = "5") Long pageSize,
                             Model model, HttpSession session) {
        model.addAttribute("username", session.getAttribute("username"));

        long totalFruits = fruitService.count();
        model.addAttribute("totalFruits", totalFruits);

        long totalStock = fruitService.list().stream()
                .mapToInt(Fruit::getStock)
                .sum();
        model.addAttribute("totalStock", totalStock);

        long lowStockCount = fruitService.count(
                new LambdaQueryWrapper<Fruit>().le(Fruit::getStock, 30)
        );
        model.addAttribute("lowStockCount", lowStockCount);

        long avgStock = totalFruits > 0 ? totalStock / totalFruits : 0;
        model.addAttribute("avgStock", avgStock);

        Page<Fruit> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Fruit> wrapper = new LambdaQueryWrapper<>();
        wrapper.le(Fruit::getStock, 30).orderByAsc(Fruit::getStock);
        Page<Fruit> lowStockPage = fruitService.page(page, wrapper);

        model.addAttribute("lowStockPage", lowStockPage);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", lowStockPage.getPages());

        return "stock-stats";
    }
}