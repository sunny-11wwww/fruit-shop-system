package com.shop.aifruit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataInitConfig {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            jdbcTemplate.execute("DELETE FROM t_fruit");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('苹果', 5.99, 100, '山东', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20red%20apple%20on%20white%20background&image_size=square', '8-10月', '冷藏0-4°C', 30, '7-14天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('香蕉', 3.50, 80, '海南', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=yellow%20ripe%20banana%20bunch%20on%20white%20background&image_size=square', '9-12月', '常温13-15°C', 10, '3-5天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('橙子', 4.99, 120, '江西', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20orange%20citrus%20fruit%20on%20white%20background&image_size=square', '11-2月', '冷藏4-8°C', 45, '10-20天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('葡萄', 8.99, 60, '新疆', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20purple%20grapes%20bunch%20on%20white%20background&image_size=square', '7-9月', '冷藏0-2°C', 15, '3-7天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('西瓜', 2.99, 50, '宁夏', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20watermelon%20cut%20in%20half%20on%20white%20background&image_size=square', '6-8月', '冷藏8-10°C', 20, '5-10天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('草莓', 12.99, 40, '丹东', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20red%20strawberries%20on%20white%20background&image_size=square', '1-3月', '冷藏0-2°C', 7, '2-3天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('芒果', 9.99, 70, '广西', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20yellow%20mango%20fruit%20on%20white%20background&image_size=square', '5-8月', '常温12-15°C', 15, '3-5天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('榴莲', 39.99, 20, '泰国', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20durian%20fruit%20on%20white%20background&image_size=square', '6-8月', '常温18-22°C', 7, '2-3天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('樱桃', 15.99, 30, '大连', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20red%20cherries%20on%20white%20background&image_size=square', '5-6月', '冷藏0-1°C', 10, '3-5天')");
            jdbcTemplate.execute("INSERT INTO t_fruit (fruit_name, price, stock, origin, image_url, harvest_month, storage_method, freshness_days, best_eat_period) VALUES ('蓝莓', 18.99, 25, '智利', 'https://trae-api-cn.mchost.guru/api/ide/v1/text_to_image?prompt=fresh%20blueberries%20on%20white%20background&image_size=square', '11-2月', '冷藏0-2°C', 14, '5-7天')");
            
            Long adminCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_admin", Long.class);
            if (adminCount == null || adminCount == 0) {
                jdbcTemplate.execute("INSERT INTO t_admin (username, password) VALUES ('admin', 'admin123')");
            }
        };
    }
}