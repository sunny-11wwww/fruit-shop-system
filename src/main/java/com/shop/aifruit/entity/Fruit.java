package com.shop.aifruit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

@Data
@TableName("t_fruit")
public class Fruit {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String fruitName;
    private BigDecimal price;
    private Integer stock;
    private String origin;
    private String imageUrl;
    private String harvestMonth;
    private String storageMethod;
    private Integer freshnessDays;
    private String bestEatPeriod;
}