CREATE TABLE IF NOT EXISTS `invoices` (
     `id` bigint NOT NULL AUTO_INCREMENT,
     `total_itens` int NOT NULL,
     `payment_method` varchar(255) NOT NULL,
    `total_value` decimal(20,2) NOT NULL,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
