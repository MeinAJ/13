CREATE TABLE `t_unique_record` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `unique_value` varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_value` (`unique_value`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

