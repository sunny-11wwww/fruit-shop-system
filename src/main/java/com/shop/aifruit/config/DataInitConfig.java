package com.shop.aifruit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataInitConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataInitConfig.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            try {
                ensureTablesExist();
                
                Long fruitCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_fruit", Long.class);
                if (fruitCount == null || fruitCount == 0) {
                    logger.info("初始化水果数据...");
                    insertFruitData();
                } else {
                    logger.info("水果表已有数据，跳过初始化");
                }

                Long adminCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_admin", Long.class);
                if (adminCount == null || adminCount == 0) {
                    logger.info("初始化管理员数据...");
                    jdbcTemplate.execute("INSERT INTO t_admin (username, password) VALUES ('admin', 'admin123')");
                } else {
                    logger.info("管理员表已有数据，跳过初始化");
                }
                
                logger.info("数据初始化完成");
            } catch (Exception e) {
                logger.error("数据初始化失败: {}", e.getMessage(), e);
            }
        };
    }

    private void ensureTablesExist() {
        try {
            jdbcTemplate.execute("SELECT COUNT(*) FROM t_fruit");
            jdbcTemplate.execute("SELECT COUNT(*) FROM t_admin");
            logger.info("数据表已存在");
        } catch (Exception e) {
            logger.warn("数据表不存在，开始创建表结构: {}", e.getMessage());
            createTables();
        }
    }

    private void createTables() {
        try {
            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS t_fruit (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "fruit_name VARCHAR(100) NOT NULL, " +
                    "price DECIMAL(10,2) NOT NULL, " +
                    "stock INT DEFAULT 0, " +
                    "origin VARCHAR(100), " +
                    "image_url VARCHAR(500), " +
                    "harvest_month VARCHAR(50), " +
                    "storage_method VARCHAR(200), " +
                    "freshness_days INT, " +
                    "best_eat_period VARCHAR(100)" +
                    ")");

            jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS t_admin (" +
                    "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) NOT NULL UNIQUE, " +
                    "password VARCHAR(100) NOT NULL" +
                    ")");
            
            logger.info("表结构创建成功");
        } catch (Exception e) {
            logger.error("创建表结构失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    private void insertFruitData() {
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
    }
}