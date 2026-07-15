package com.shop.aifruit.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.aifruit.entity.Fruit;
import com.shop.aifruit.mapper.FruitMapper;
import com.shop.aifruit.service.FruitService;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl extends ServiceImpl<FruitMapper, Fruit> implements FruitService {

}