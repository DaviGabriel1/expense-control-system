CREATE TABLE IF NOT EXISTS `product` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `total_price` decimal(20,2) NOT NULL,
    `unit_price` decimal(20,2) NOT NULL,
    `quantity` int DEFAULT NULL,
    `code` varchar(255) DEFAULT NULL,
    `measure` varchar(255) NOT NULL,
    `created_at` timestamp NULL DEFAULT NULL,
    `invoice_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_product_invoice` (`invoice_id`),
    CONSTRAINT `fk_product_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `invoices` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

