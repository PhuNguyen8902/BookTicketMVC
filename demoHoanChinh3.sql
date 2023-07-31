CREATE DATABASE  IF NOT EXISTS `book_ticket` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `book_ticket`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: book_ticket
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `town` varchar(255) NOT NULL DEFAULT 'Hồ Chí Minh',
  `district` varchar(255) NOT NULL DEFAULT 'Quận 1',
  `ward` varchar(255) NOT NULL DEFAULT 'Phường Bến Nghé',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Hồ Chí Minh','Quận 1','Phường Bến Nghé'),(2,'Hồ Chí Minh','Quận 2','Phường Bến Nghé');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text,
  `trip_id` int DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feedback_trip` (`trip_id`),
  KEY `fk_trip_user` (`user_id`),
  CONSTRAINT `fk_feedback_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_trip_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'hay quá',3,'81e97a85-f61a-11ed-bb65-00d861e72b9f');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `increased_price`
--

DROP TABLE IF EXISTS `increased_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `increased_price` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `increased_percentage` smallint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `increased_price`
--

LOCK TABLES `increased_price` WRITE;
/*!40000 ALTER TABLE `increased_price` DISABLE KEYS */;
INSERT INTO `increased_price` VALUES (1,'tết',50);
/*!40000 ALTER TABLE `increased_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refesh_token`
--

DROP TABLE IF EXISTS `refesh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refesh_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `fk_refesh_token_user` (`user_id`),
  CONSTRAINT `fk_refesh_token_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refesh_token`
--

LOCK TABLES `refesh_token` WRITE;
/*!40000 ALTER TABLE `refesh_token` DISABLE KEYS */;
INSERT INTO `refesh_token` VALUES (139,'2023-07-30 14:51:42','58906dd1-7086-4904-b916-fece607170fc','e64c97ec-eeb6-40c4-9473-d50535a5b7b9'),(143,'2023-07-31 09:45:18','4852bb88-4ec9-4f55-8a07-3db69cf3d93f','81e97a85-f61a-11ed-bb65-00d861e72b9f'),(145,'2023-07-31 11:01:58','7ce0e7d6-bc5c-4254-86e6-a26f4c47a1c9','2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177');
/*!40000 ALTER TABLE `refesh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `route`
--

DROP TABLE IF EXISTS `route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `start_station_id` int DEFAULT NULL,
  `end_station_id` int DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `duration` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_route_start_station` (`start_station_id`),
  KEY `fk_route_end_station` (`end_station_id`),
  CONSTRAINT `fk_route_end_station` FOREIGN KEY (`end_station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_route_start_station` FOREIGN KEY (`start_station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'Tuyến 1',1,2,200,2);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` tinyint DEFAULT '0',
  `seat_number` smallint DEFAULT '0',
  `vehicle_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_seat_vehicle` (`vehicle_id`),
  CONSTRAINT `fk_seat_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (1,0,1,1);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_station_address` (`address_id`),
  CONSTRAINT `fk_station_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Bến 1',1),(2,'Bến 2',2);
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station_route`
--

DROP TABLE IF EXISTS `station_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station_route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `station_id` int DEFAULT NULL,
  `route_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_station_route_station` (`station_id`),
  KEY `fk_station_route_route` (`route_id`),
  CONSTRAINT `fk_station_route_route` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_station_route_station` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_route`
--

LOCK TABLES `station_route` WRITE;
/*!40000 ALTER TABLE `station_route` DISABLE KEYS */;
INSERT INTO `station_route` VALUES (1,1,1);
/*!40000 ALTER TABLE `station_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seat` smallint DEFAULT NULL,
  `trip_id` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `is_active` tinyint DEFAULT '1',
  `user_id` varchar(50) DEFAULT NULL,
  `increased_price_id` int DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `payment` varchar(100) DEFAULT NULL,
  `date` datetime DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `employee_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `employee_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ticket_trip` (`trip_id`),
  KEY `fk_ticket_user` (`user_id`),
  KEY `fk_ticket_employee` (`employee_id`),
  CONSTRAINT `fk_ticket_employee` FOREIGN KEY (`employee_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_ticket_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_ticket_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (2,1,3,20000000,1,'81e97a85-f61a-11ed-bb65-00d861e72b9f',1,NULL,NULL,'2023-07-22 00:00:00','user','employee','81e97a85-f61a-11ed-bb65-00d861e72b9f');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trip`
--

DROP TABLE IF EXISTS `trip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip` (
  `id` int NOT NULL AUTO_INCREMENT,
  `departure_time` datetime DEFAULT NULL,
  `arrival_time` datetime DEFAULT NULL,
  `price` double DEFAULT '0',
  `driver_id` varchar(50) DEFAULT NULL,
  `vehicle_id` int DEFAULT NULL,
  `route_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_trip_driver` (`driver_id`),
  KEY `fk_trip_vehicle` (`vehicle_id`),
  KEY `fk_trip_route` (`route_id`),
  CONSTRAINT `fk_trip_driver` FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_trip_route` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_trip_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (3,'2023-06-20 00:00:00','2023-07-20 00:00:00',20000000,'81e97a85-f61a-11ed-bb65-00d861e72b9f',1,1);
/*!40000 ALTER TABLE `trip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(50) NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `avatar` varchar(200) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_active` tinyint DEFAULT '1',
  `role` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177','phu2@gmail.com','$2a$10$B5y3Sr719BiOIns2i5NTtOPUCjZ7O4fx2cxNswdNGXWsR32dvrNo2','0987654312','https://res.cloudinary.com/dqdspcxhq/image/upload/v1690776055/vqgtrfqcnehzcvtye6qr.jpg','phu2',1,'ROLE_CUSTOMER'),('81e97a85-f61a-11ed-bb65-00d861e72b9f','admin@gmail.com','$2a$10$nxrkmDEz827P6Bcu1JvWU.9gEjZ.dZpJmTq.//ROjsM0JpEReikPy','0987652721',NULL,'admin',1,'ROLE_ADMIN'),('e64c97ec-eeb6-40c4-9473-d50535a5b7b9','phu@gmail.com','$2a$10$nxrkmDEz827P6Bcu1JvWU.9gEjZ.dZpJmTq.//ROjsM0JpEReikPy','012938192',NULL,'phu',1,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seat_capacity` smallint DEFAULT '50',
  `license_plate` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,50,NULL);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-31 11:05:29
