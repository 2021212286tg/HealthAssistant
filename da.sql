-- 创建数据库
DROP DATABASE IF EXISTS health_assistant;
CREATE DATABASE IF NOT EXISTS `health_assistant` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `health_assistant`;

-- 用户表
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
  `gender` ENUM('male', 'female', 'other') NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `height` decimal(5,2) DEFAULT NULL COMMENT '身高(cm)',
  `weight` decimal(5,2) DEFAULT NULL COMMENT '体重(kg)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`),
  UNIQUE KEY `email_unique` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 用户目标表
CREATE TABLE `user_goals` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '目标ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `daily_calorie_goal` int(11) DEFAULT 2000 COMMENT '每日目标卡路里',
  `daily_protein_goal` int(11) DEFAULT 50 COMMENT '每日目标蛋白质(g)',
  `daily_carbs_goal` int(11) DEFAULT 250 COMMENT '每日目标碳水化合物(g)',
  `daily_fat_goal` int(11) DEFAULT 70 COMMENT '每日目标脂肪(g)',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_unique` (`user_id`),
  CONSTRAINT `fk_user_goals_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户目标表';

-- 食物记录表
CREATE TABLE `food_records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `food_name` varchar(100) NOT NULL COMMENT '食物名称',
  `calories` decimal(10,2) NOT NULL COMMENT '卡路里',
  `protein` decimal(10,2) DEFAULT NULL COMMENT '蛋白质(g)',
  `carbs` decimal(10,2) DEFAULT NULL COMMENT '碳水化合物(g)',
  `fat` decimal(10,2) DEFAULT NULL COMMENT '脂肪(g)',
  `meal_type` tinyint(1) DEFAULT NULL COMMENT '餐型: 0-早餐, 1-午餐, 2-晚餐, 3-零食',
  `record_date` date NOT NULL COMMENT '记录日期',
  `image_url` varchar(255) DEFAULT NULL COMMENT '食物图片URL',
  `ai_analysis` text DEFAULT NULL COMMENT 'AI分析结果(JSON格式)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_date` (`user_id`,`record_date`),
  CONSTRAINT `fk_food_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='食物记录表';

-- 每日营养汇总表
CREATE TABLE `daily_nutrition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '汇总ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `date` date NOT NULL COMMENT '日期',
  `total_calories` decimal(10,2) DEFAULT 0 COMMENT '总摄入卡路里',
  `total_protein` decimal(10,2) DEFAULT 0 COMMENT '总蛋白质(g)',
  `total_carbs` decimal(10,2) DEFAULT 0 COMMENT '总碳水化合物(g)',
  `total_fat` decimal(10,2) DEFAULT 0 COMMENT '总脂肪(g)',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_date_unique` (`user_id`,`date`),
  CONSTRAINT `fk_daily_nutrition_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='每日营养汇总表';

-- 健康报告表
CREATE TABLE `health_reports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '报告ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `report_date` date NOT NULL COMMENT '报告日期',
  `total_calories` decimal(10,2) DEFAULT NULL COMMENT '总摄入卡路里',
  `total_protein` decimal(10,2) DEFAULT NULL COMMENT '总蛋白质(g)',
  `total_carbs` decimal(10,2) DEFAULT NULL COMMENT '总碳水化合物(g)',
  `total_fat` decimal(10,2) DEFAULT NULL COMMENT '总脂肪(g)',
  `calorie_goal_percentage` decimal(5,2) DEFAULT NULL COMMENT '卡路里目标完成百分比',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_report_date` (`user_id`,`report_date`),
  CONSTRAINT `fk_report_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='健康报告表';

-- 插入用户数据
INSERT INTO `users` (`username`, `password`, `email`, `gender`, `age`, `height`, `weight`) VALUES
('john_doe', '$2a$10$rOzZzBz3Uz/4Uz/4Uz/4Ue', 'john@example.com', 'male', 28, 175.50, 70.20),
('jane_smith', '$2a$10$rOzZzBz3Uz/4Uz/4Uz/4Ue', 'jane@example.com', 'female', 32, 165.00, 58.50),
('mike_wilson', '$2a$10$rOzZzBz3Uz/4Uz/4Uz/4Ue', 'mike@example.com', 'male', 45, 182.00, 85.00);

-- 插入用户目标数据
INSERT INTO `user_goals` (`user_id`, `daily_calorie_goal`, `daily_protein_goal`, `daily_carbs_goal`, `daily_fat_goal`) VALUES
(1, 2200, 60, 250, 70),
(2, 1800, 50, 200, 60),
(3, 2500, 80, 300, 80);

-- 插入食物记录数据
INSERT INTO `food_records` (`user_id`, `food_name`, `calories`, `protein`, `carbs`, `fat`, `meal_type`, `record_date`) VALUES
(1, '燕麦粥', 150.00, 5.00, 27.00, 3.00, 0, CURDATE() - INTERVAL 2 DAY),
(1, '鸡胸肉', 165.00, 31.00, 0.00, 3.60, 1, CURDATE() - INTERVAL 2 DAY),
(1, '糙米饭', 112.00, 2.60, 23.00, 0.90, 1, CURDATE() - INTERVAL 2 DAY),
(1, '三文鱼', 206.00, 22.00, 0.00, 13.00, 2, CURDATE() - INTERVAL 2 DAY),
(1, '蔬菜沙拉', 85.00, 2.00, 10.00, 4.00, 2, CURDATE() - INTERVAL 2 DAY),

(1, '全麦面包', 140.00, 6.00, 23.00, 2.00, 0, CURDATE() - INTERVAL 1 DAY),
(1, '鸡蛋', 78.00, 6.30, 0.60, 5.30, 0, CURDATE() - INTERVAL 1 DAY),
(1, '牛肉', 250.00, 26.00, 0.00, 15.00, 1, CURDATE() - INTERVAL 1 DAY),
(1, '红薯', 114.00, 2.00, 27.00, 0.10, 1, CURDATE() - INTERVAL 1 DAY),
(1, '希腊酸奶', 100.00, 10.00, 6.00, 3.00, 3, CURDATE() - INTERVAL 1 DAY),

(1, '牛奶', 124.00, 8.00, 12.00, 5.00, 0, CURDATE()),
(1, '香蕉', 105.00, 1.30, 27.00, 0.40, 0, CURDATE()),
(2, '蔬菜汤', 85.00, 2.50, 12.00, 3.00, 1, CURDATE()),
(2, '水果沙拉', 120.00, 1.50, 28.00, 0.50, 3, CURDATE()),
(3, '牛排', 330.00, 30.00, 0.00, 22.00, 2, CURDATE());

-- 插入每日营养汇总数据
INSERT INTO `daily_nutrition` (`user_id`, `date`, `total_calories`, `total_protein`, `total_carbs`, `total_fat`) VALUES
(1, CURDATE() - INTERVAL 2 DAY, 718.00, 62.60, 60.00, 24.50),
(1, CURDATE() - INTERVAL 1 DAY, 682.00, 50.30, 56.60, 25.40),
(1, CURDATE(), 229.00, 9.30, 39.00, 5.40),
(2, CURDATE(), 205.00, 4.00, 40.00, 3.50),
(3, CURDATE(), 330.00, 30.00, 0.00, 22.00);

-- 插入健康报告数据
INSERT INTO `health_reports` (`user_id`, `report_date`, `total_calories`, `total_protein`, `total_carbs`, `total_fat`, `calorie_goal_percentage`) VALUES
(1, CURDATE() - INTERVAL 2 DAY, 718.00, 62.60, 60.00, 24.50, 32.64),
(1, CURDATE() - INTERVAL 1 DAY, 682.00, 50.30, 56.60, 25.40, 31.00),
(1, CURDATE(), 229.00, 9.30, 39.00, 5.40, 10.41),
(2, CURDATE(), 205.00, 4.00, 40.00, 3.50, 11.39),
(3, CURDATE(), 330.00, 30.00, 0.00, 22.00, 13.20);

CREATE TABLE report_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    report_title VARCHAR(200) NOT NULL,
    report_type VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    report_content LONGTEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_report_history_user ON report_history(user_id);
CREATE INDEX idx_report_history_created ON report_history(created_at DESC);
