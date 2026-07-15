package com.shop.aifruit.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shop.aifruit.entity.Fruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIKnowledgeService {

    private Map<String, String> knowledgeBase = new HashMap<>();
    private Map<String, List<String>> fruitKnowledge = new HashMap<>();

    private final FruitService fruitService;

    @Autowired
    public AIKnowledgeService(FruitService fruitService) {
        this.fruitService = fruitService;
        initKnowledgeBase();
    }

    private void initKnowledgeBase() {
        knowledgeBase.put("苹果产地", "苹果主要产自中国山东、陕西、河南等地，其中山东烟台苹果最为著名。");
        knowledgeBase.put("苹果成熟", "苹果一般在每年8-10月成熟上市，不同品种成熟期有所差异。");
        knowledgeBase.put("苹果储存", "苹果适合冷藏储存，温度控制在0-4°C，可保鲜约30天。");
        knowledgeBase.put("苹果食用", "苹果最佳食用期限为采摘后7-14天，不宜空腹食用，糖尿病患者适量食用。");
        
        knowledgeBase.put("香蕉产地", "香蕉主要产自中国海南、广东、广西以及东南亚地区。");
        knowledgeBase.put("香蕉成熟", "香蕉全年均可上市，主要集中在9-12月。");
        knowledgeBase.put("香蕉储存", "香蕉适合常温储存，温度控制在13-15°C，可保鲜约10天，不宜冷藏。");
        knowledgeBase.put("香蕉食用", "香蕉最佳食用期限为3-5天，可空腹食用，适合运动后补充能量。");
        
        knowledgeBase.put("橙子产地", "橙子主要产自中国江西、湖南、广东、广西等地。");
        knowledgeBase.put("橙子成熟", "橙子一般在11月至次年2月成熟上市。");
        knowledgeBase.put("橙子储存", "橙子适合冷藏储存，温度控制在4-8°C，可保鲜约45天。");
        knowledgeBase.put("橙子食用", "橙子最佳食用期限为10-20天，富含维生素C，不宜与牛奶同食。");
        
        knowledgeBase.put("葡萄产地", "葡萄主要产自中国新疆、山东、河北等地，新疆葡萄品质最佳。");
        knowledgeBase.put("葡萄成熟", "葡萄一般在7-9月成熟上市。");
        knowledgeBase.put("葡萄储存", "葡萄适合冷藏储存，温度控制在0-2°C，可保鲜约15天。");
        knowledgeBase.put("葡萄食用", "葡萄最佳食用期限为3-7天，含糖量较高，糖尿病患者慎食。");
        
        knowledgeBase.put("西瓜产地", "西瓜主要产自中国宁夏、新疆、山东、河南等地。");
        knowledgeBase.put("西瓜成熟", "西瓜一般在6-8月成熟上市。");
        knowledgeBase.put("西瓜储存", "西瓜适合冷藏储存，温度控制在8-10°C，可保鲜约20天。");
        knowledgeBase.put("西瓜食用", "西瓜最佳食用期限为5-10天，含水量高，脾胃虚寒者不宜多食。");
        
        knowledgeBase.put("草莓产地", "草莓主要产自中国辽宁丹东、四川、浙江等地。");
        knowledgeBase.put("草莓成熟", "草莓一般在1-3月成熟上市。");
        knowledgeBase.put("草莓储存", "草莓适合冷藏储存，温度控制在0-2°C，可保鲜约7天。");
        knowledgeBase.put("草莓食用", "草莓最佳食用期限为2-3天，富含花青素，不宜久放。");
        
        knowledgeBase.put("芒果产地", "芒果主要产自中国广西、海南、广东以及泰国等地。");
        knowledgeBase.put("芒果成熟", "芒果一般在5-8月成熟上市。");
        knowledgeBase.put("芒果储存", "芒果适合常温储存，温度控制在12-15°C，可保鲜约15天。");
        knowledgeBase.put("芒果食用", "芒果最佳食用期限为3-5天，过敏体质者慎食。");
        
        knowledgeBase.put("榴莲产地", "榴莲主要产自泰国、马来西亚以及中国海南等地。");
        knowledgeBase.put("榴莲成熟", "榴莲一般在6-8月成熟上市。");
        knowledgeBase.put("榴莲储存", "榴莲适合常温储存，温度控制在18-22°C，可保鲜约7天。");
        knowledgeBase.put("榴莲食用", "榴莲最佳食用期限为2-3天，热量较高，不宜过量食用。");
        
        knowledgeBase.put("樱桃产地", "樱桃主要产自中国辽宁大连、山东烟台等地。");
        knowledgeBase.put("樱桃成熟", "樱桃一般在5-6月成熟上市。");
        knowledgeBase.put("樱桃储存", "樱桃适合冷藏储存，温度控制在0-1°C，可保鲜约10天。");
        knowledgeBase.put("樱桃食用", "樱桃最佳食用期限为3-5天，富含铁元素，适合女性食用。");
        
        knowledgeBase.put("蓝莓产地", "蓝莓主要产自中国吉林、黑龙江以及智利等地。");
        knowledgeBase.put("蓝莓成熟", "蓝莓一般在11月至次年2月成熟上市。");
        knowledgeBase.put("蓝莓储存", "蓝莓适合冷藏储存，温度控制在0-2°C，可保鲜约14天。");
        knowledgeBase.put("蓝莓食用", "蓝莓最佳食用期限为5-7天，富含花青素，护眼佳品。");

        knowledgeBase.put("保鲜方法", "大部分水果适合冷藏储存，低温可减缓腐烂速度。香蕉、芒果等热带水果不宜冷藏。");
        knowledgeBase.put("储存温度", "冷藏水果一般建议温度0-8°C，热带水果常温12-22°C。");
        knowledgeBase.put("食用禁忌", "糖尿病患者慎食高糖水果，过敏体质者注意芒果、菠萝等易过敏水果。");
        knowledgeBase.put("水果营养", "水果富含维生素、矿物质和膳食纤维，建议每天食用200-350克。");
    }

    public String query(String question) {
        if (question == null || question.trim().isEmpty()) {
            return "请输入您的问题。";
        }
        String originalQuestion = question.trim();
        String lowerQuestion = originalQuestion.toLowerCase();
        
        String[] fruits = {"苹果", "香蕉", "橙子", "葡萄", "西瓜", "草莓", "芒果", "榴莲", "樱桃", "蓝莓"};
        
        for (String fruit : fruits) {
            if (originalQuestion.contains(fruit)) {
                if (originalQuestion.contains("多少钱") || originalQuestion.contains("价格") || 
                    originalQuestion.contains("单价") || originalQuestion.contains("售价")) {
                    Fruit f = getFruitFromDB(fruit);
                    if (f != null) {
                        return fruit + "的当前单价是 ¥" + f.getPrice() + "。";
                    } else {
                        return fruit + "的价格信息暂时无法获取，请稍后再试。";
                    }
                }
                
                if (originalQuestion.contains("库存") || originalQuestion.contains("数量")) {
                    Fruit f = getFruitFromDB(fruit);
                    if (f != null) {
                        return fruit + "的当前库存数量是 " + f.getStock() + " 件。";
                    } else {
                        return fruit + "的库存信息暂时无法获取，请稍后再试。";
                    }
                }
            }
        }
        
        for (String key : knowledgeBase.keySet()) {
            if (lowerQuestion.contains(key.toLowerCase())) {
                return knowledgeBase.get(key);
            }
        }

        String[] keywords = {"产地", "成熟", "储存", "食用", "保鲜", "上市", "运输", "采摘"};
        
        for (String fruit : fruits) {
            for (String keyword : keywords) {
                if (originalQuestion.contains(fruit) && originalQuestion.contains(keyword)) {
                    String queryKey = fruit + keyword;
                    if (knowledgeBase.containsKey(queryKey)) {
                        return knowledgeBase.get(queryKey);
                    }
                }
            }
        }

        for (String fruit : fruits) {
            if (originalQuestion.contains(fruit)) {
                Fruit dbFruit = getFruitFromDB(fruit);
                StringBuilder result = new StringBuilder(fruit + "的相关信息：\n");
                
                if (dbFruit != null) {
                    result.append("当前单价：¥").append(dbFruit.getPrice()).append("\n");
                    result.append("当前库存：").append(dbFruit.getStock()).append(" 件\n");
                }
                
                for (String keyword : keywords) {
                    String queryKey = fruit + keyword;
                    if (knowledgeBase.containsKey(queryKey)) {
                        result.append(knowledgeBase.get(queryKey)).append("\n");
                    }
                }
                if (result.length() > (fruit.length() + 7)) {
                    return result.toString();
                }
            }
        }

        return "抱歉，我暂时无法回答这个问题。您可以询问水果的产地、成熟时间、储存方法、保鲜时长、价格、库存或食用注意事项等方面的问题。";
    }

    private Fruit getFruitFromDB(String fruitName) {
        if (fruitService == null) {
            return null;
        }
        LambdaQueryWrapper<Fruit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Fruit::getFruitName, fruitName);
        List<Fruit> fruits = fruitService.list(wrapper);
        if (fruits != null && !fruits.isEmpty()) {
            return fruits.get(0);
        }
        wrapper.clear();
        wrapper.like(Fruit::getFruitName, fruitName);
        fruits = fruitService.list(wrapper);
        if (fruits != null && !fruits.isEmpty()) {
            return fruits.get(0);
        }
        return null;
    }
}