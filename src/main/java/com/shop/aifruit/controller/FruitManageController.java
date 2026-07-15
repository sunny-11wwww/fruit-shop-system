package com.shop.aifruit.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.aifruit.entity.Fruit;
import com.shop.aifruit.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/fruit/manage")
public class FruitManageController {

    @Autowired
    private FruitService fruitService;

    @GetMapping
    public String manage(@RequestParam(defaultValue = "1") Long pageNum,
                         @RequestParam(defaultValue = "5") Long pageSize,
                         @RequestParam(required = false) String keyword,
                         Model model, HttpSession session) {
        model.addAttribute("username", session.getAttribute("username"));
        
        LambdaQueryWrapper<Fruit> wrapper = new LambdaQueryWrapper<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            wrapper.like(Fruit::getFruitName, keyword).or().like(Fruit::getOrigin, keyword);
            model.addAttribute("keyword", keyword);
        }
        
        Page<Fruit> page = new Page<>(pageNum, pageSize);
        Page<Fruit> fruitPage = fruitService.page(page, wrapper.orderByDesc(Fruit::getId));
        
        model.addAttribute("fruitPage", fruitPage);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", fruitPage.getPages());
        
        return "fruit-manage";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addFruit(@RequestBody Fruit fruit) {
        fruitService.save(fruit);
        return "success";
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateFruit(@RequestBody Fruit fruit) {
        fruitService.updateById(fruit);
        return "success";
    }

    @GetMapping("/delete/{id}")
    public String deleteFruit(@PathVariable Long id) {
        fruitService.removeById(id);
        return "redirect:/fruit/manage";
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Fruit getFruit(@PathVariable Long id) {
        return fruitService.getById(id);
    }
}