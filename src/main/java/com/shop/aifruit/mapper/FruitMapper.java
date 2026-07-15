package com.shop.aifruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.aifruit.entity.Fruit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FruitMapper extends BaseMapper<Fruit> {
}