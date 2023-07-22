use `book_ticket`;

create table `user`(
	`id` VARCHAR(50) PRIMARY KEY,
    `email` NVARCHAR(100) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(20) DEFAULT NULL,
    `avatar` VARCHAR(200) DEFAULT NULL,
    `name` NVARCHAR(100) DEFAULT NULL,
    `is_active` TINYINT DEFAULT 1
);
create table `increased_price`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `event_name` NVARCHAR(100) DEFAULT NULL,
    `increased_percentage` SMALLINT DEFAULT NULL
);
create table `vehicle`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`seat_capacity` SMALLINT DEFAULT 50,
    `license_plate` NVARCHAR(20) DEFAULT NULL
);
create table `seat`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`status` TINYINT DEFAULT 0,
	`seat_number` SMALLINT DEFAULT 0,
    `vehicle_id` INT DEFAULT NULL,
	KEY `fk_seat_vehicle` (`vehicle_id`),
    CONSTRAINT `fk_seat_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE CASCADE
);
CREATE TABLE `address` (
  `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `town` varchar(255) NOT NULL DEFAULT 'Hồ Chí Minh',
  `district` varchar(255) NOT NULL DEFAULT 'Quận 1',
  `ward` varchar(255) NOT NULL DEFAULT 'Phường Bến Nghé'
);
create table `station`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` NVARCHAR(100) DEFAULT NULL,
    `address_id` INT DEFAULT NULL,
	KEY `fk_station_address` (`address_id`),
    CONSTRAINT `fk_station_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE SET NULL
);
create table `route`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` NVARCHAR(100) DEFAULT NULL,
    `start_station_id` INT DEFAULT NULL,
    `end_station_id` INT DEFAULT NULL,
    `distance` DOUBLE DEFAULT NULL,
    `duration` DOUBLE DEFAULT NULL,
	KEY `fk_route_start_station` (`start_station_id`),
    KEY `fk_route_end_station` (`end_station_id`),
    CONSTRAINT `fk_route_start_station` FOREIGN KEY (`start_station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_route_end_station` FOREIGN KEY (`end_station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE
);
create table `station_route`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `station_id` INT DEFAULT NULL,
	`route_id` INT DEFAULT NULL,
	KEY `fk_station_route_station` (`station_id`),
    KEY `fk_station_route_route` (`route_id`),
    CONSTRAINT `fk_station_route_station` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_station_route_route` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE CASCADE
);
create table `trip`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `departure_time` DATETIME DEFAULT NULL,
	`arrival_time` DATETIME DEFAULT NULL,
	`price` DOUBLE DEFAULT 0,
	`driver_id` VARCHAR(50) DEFAULT NULL,
    `vehicle_id` INT DEFAULT NULL,
    `route_id` INT DEFAULT NULL,
	KEY `fk_trip_driver` (`driver_id`),
    KEY `fk_trip_vehicle` (`vehicle_id`),
    KEY `fk_trip_route` (`route_id`),
    CONSTRAINT `fk_trip_driver` FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
    CONSTRAINT `fk_trip_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE SET NULL,
    CONSTRAINT `fk_trip_route` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE CASCADE
);
create table `feedback`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `content` TEXT DEFAULT NULL,
	`trip_id` INT DEFAULT NULL,
    `user_id` VARCHAR(50) DEFAULT NULL,
	KEY `fk_feedback_trip` (`trip_id`),
    KEY `fk_trip_user` (`user_id`),
    CONSTRAINT `fk_feedback_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_trip_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);
create table `ticket`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `seat` SMALLINT DEFAULT NULL,
	`trip_id` INT DEFAULT NULL,
    `price` DOUBLE DEFAULT NULL,
    `is_active` TINYINT DEFAULT 1,
    `user_id` VARCHAR(50) DEFAULT NULL,
    `increased_price_id` INT DEFAULT NULL,
    `type` VARCHAR(100) DEFAULT NULL,
    `payment` VARCHAR(100) DEFAULT NULL,
    `date` DATETIME DEFAULT NOW(),
    `name` NVARCHAR(100) DEFAULT NULL,
    `employee_name` NVARCHAR(100) DEFAULT NULL,
    `employee_id` VARCHAR(50) DEFAULT NULL,
	KEY `fk_ticket_trip` (`trip_id`),
    KEY `fk_ticket_user` (`user_id`),
    KEY `fk_ticket_employee` (`employee_id`),
    CONSTRAINT `fk_ticket_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_ticket_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
	CONSTRAINT `fk_ticket_employee` FOREIGN KEY (`employee_id`) REFERENCES `user` (`id`) ON DELETE SET NULL
);
create table `refesh_token`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `expiry_date` DATETIME DEFAULT NULL,
	`token` VARCHAR(255) DEFAULT NULL,
    `user_id` VARCHAR(50) UNIQUE DEFAULT NULL,
    KEY `fk_refesh_token_user` (`user_id`),
    CONSTRAINT `fk_refesh_token_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
);
