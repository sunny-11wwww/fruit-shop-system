CREATE TABLE IF NOT EXISTS t_fruit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fruit_name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    stock INT DEFAULT 0,
    origin VARCHAR(100),
    image_url VARCHAR(255),
    harvest_month VARCHAR(50),
    storage_method VARCHAR(200),
    freshness_days INT,
    best_eat_period VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS t_admin (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);