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
  `is_active` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Hồ Chí Minh','Quận 1','Phường Bến Nghé',1),(2,'Hồ Chí Minh','Quận 2','Phường Bến Nghé',1),(3,'Unknow','Q1','Unknow',1),(4,'Unknow','Q4','Unknow',1),(5,'Unknow','Q3','Unknow',NULL),(6,'Unknow','Q3','know',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'hay quá',3,'81e97a85-f61a-11ed-bb65-00d861e72b9f'),(2,'chuyen xe tuyet voi qua',9,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177'),(3,'chuyen xe tuyet voi quadi',9,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177'),(4,'hay a',9,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177'),(5,'chuyen di hay do',3,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177');
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
  `is_active` tinyint DEFAULT '1',
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `increased_price`
--

LOCK TABLES `increased_price` WRITE;
/*!40000 ALTER TABLE `increased_price` DISABLE KEYS */;
INSERT INTO `increased_price` VALUES (1,'tết',30,1,'2023-09-23 00:00:00','2023-09-24 00:00:00'),(2,'none',0,1,NULL,NULL),(3,'special date 1',20,1,'2023-09-08 00:00:00','2023-09-13 00:00:00'),(4,'special date 2',30,1,'2023-09-08 00:00:00','2023-09-11 00:00:00'),(5,'special date 3',20,1,'2023-09-13 00:00:00','2023-09-30 00:00:00');
/*!40000 ALTER TABLE `increased_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_online`
--

DROP TABLE IF EXISTS `order_online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_online` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payment` varchar(100) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `ticket_id` int DEFAULT NULL,
  `emp_id` varchar(50) DEFAULT NULL,
  `order_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `increased_price_id` int DEFAULT NULL,
  `price` double DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_order_user` (`user_id`),
  KEY `fk_order_emp` (`emp_id`),
  KEY `fk_order_ticket` (`ticket_id`),
  KEY `fk_order_increase` (`increased_price_id`),
  CONSTRAINT `fk_order_emp` FOREIGN KEY (`emp_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_order_increase` FOREIGN KEY (`increased_price_id`) REFERENCES `increased_price` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_order_ticket` FOREIGN KEY (`ticket_id`) REFERENCES `ticket2` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_online`
--

LOCK TABLES `order_online` WRITE;
/*!40000 ALTER TABLE `order_online` DISABLE KEYS */;
INSERT INTO `order_online` VALUES (1,'COUNTER','2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',10,NULL,'2023-09-09 11:32:00',5,50020),(2,'COUNTER','2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',11,NULL,'2023-09-09 11:34:54',5,50020),(3,'MOMO','2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',12,NULL,'2023-09-09 11:35:12',5,50020),(4,'COUNTER','2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',13,NULL,'2023-09-09 12:17:54',5,360000);
/*!40000 ALTER TABLE `order_online` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=170 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refesh_token`
--

LOCK TABLES `refesh_token` WRITE;
/*!40000 ALTER TABLE `refesh_token` DISABLE KEYS */;
INSERT INTO `refesh_token` VALUES (148,'2023-08-08 11:56:15','6a77ae71-fc22-4344-b2d2-2bc48f911d0b','e64c97ec-eeb6-40c4-9473-d50535a5b7b9'),(159,'2023-08-14 15:58:11','65735d91-3dfe-4e55-a26a-83a839a80b53','c3c4731a-c171-4c06-91f0-ff2c5b34c281'),(160,'2023-08-15 10:43:11','6f15f9c3-4b59-4169-8467-9061d17ce0f2','e71d2f7d-5e96-4c9e-82d8-cb52133f7c89'),(163,'2023-08-15 21:05:38','4f9d3337-4cd1-4c25-97fb-1f91e2b0a52a','f4e2ef15-3726-40c8-80f4-2b0642d48c42'),(164,'2023-08-15 21:37:56','958fa599-bc23-4c63-89ca-c018660a7e75','a4b0ee9b-eee8-4b84-b30a-5f22630c80a0'),(168,'2023-09-08 13:59:56','ede21bf6-c3ac-4623-8eb2-b193743500e4','81e97a85-f61a-11ed-bb65-00d861e72b9f'),(169,'2023-09-09 12:54:20','47da78e1-7c9b-46b1-aafb-7b91dc62c5bc','2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177');
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
  `is_active` tinyint DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_route_start_station` (`start_station_id`),
  KEY `fk_route_end_station` (`end_station_id`),
  CONSTRAINT `fk_route_end_station` FOREIGN KEY (`end_station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_route_start_station` FOREIGN KEY (`start_station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `route`
--

LOCK TABLES `route` WRITE;
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
INSERT INTO `route` VALUES (1,'Tuyến 1',2,1,765,9,1),(2,'Tuyến 2',3,1,320,450,1),(3,'Tuyến 3',3,2,250,2,0),(4,'Tuyến 4',2,3,300,5,1),(5,'Tuyến 5',1,3,123,3,1),(6,'Tuyến 6',1,2,234,3,1),(7,'Tuyến 7',3,2,234,2132,1),(8,'Tuyến 8',3,1,234,2132,1),(9,'Tuyến 9',3,1,123,123,0),(10,'Tuyến 10',2,1,765,9,0),(11,'Tuyến 11',2,1,324,32,1),(12,'Tuyến 12',3,1,123,23,1),(13,'Tuyến 13',1,3,2,3,0),(14,'special Route 1',1,2,400,32,0),(15,'',1,2,400,32,0),(16,'phan',1,2,400,23,0),(17,'phfefef',2,1,400,232,0);
/*!40000 ALTER TABLE `route` ENABLE KEYS */;
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
  `description` varchar(200) DEFAULT NULL,
  `is_active` tinyint DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_station_address` (`address_id`),
  CONSTRAINT `fk_station_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Bến 1',1,'Station 1 is a pretty and the most prominent station',1),(2,'Bến 2',4,NULL,1),(3,'Bến 3',1,'Station 1 is a pretty and the most prominent station',1),(4,'Special Station 1',1,NULL,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station_route`
--

LOCK TABLES `station_route` WRITE;
/*!40000 ALTER TABLE `station_route` DISABLE KEYS */;
INSERT INTO `station_route` VALUES (1,2,1),(2,2,10),(3,1,10),(4,1,1),(5,3,2),(6,1,2),(7,3,3),(8,2,3),(9,3,4),(10,2,4),(11,1,5),(12,3,5),(13,1,6),(14,2,6),(15,3,7),(16,2,7),(17,3,8),(18,1,8),(19,3,9),(20,1,9),(21,2,11),(22,1,11),(23,3,12),(24,1,12),(25,1,13),(26,3,13),(27,1,14),(28,2,14),(29,1,15),(30,2,15),(31,1,16),(32,2,16),(33,2,17),(34,1,17);
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
  `employee_id` varchar(50) DEFAULT NULL,
  `is_get` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_ticket_trip` (`trip_id`),
  KEY `fk_ticket_user` (`user_id`),
  KEY `fk_ticket_employee` (`employee_id`),
  KEY `ticket_increased_price_id_increased_price` (`increased_price_id`),
  CONSTRAINT `fk_ticket_employee` FOREIGN KEY (`employee_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_ticket_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_ticket_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `ticket_increased_price_id_increased_price` FOREIGN KEY (`increased_price_id`) REFERENCES `increased_price` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (2,1,3,130000,1,'b24e30d3-6ee7-4a86-8685-9a6067da2413',1,'onl','','2023-07-22 00:00:00',NULL,'81e97a85-f61a-11ed-bb65-00d861e72b9f',0),(3,1,4,140161013,1,'b24e30d3-6ee7-4a86-8685-9a6067da2413',1,'onl','','2023-07-22 00:00:00',NULL,'a4b0ee9b-eee8-4b84-b30a-5f22630c80a0',0),(4,3,4,4521323,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',2,'onl','','2023-07-22 00:00:00',NULL,'81e97a85-f61a-11ed-bb65-00d861e72b9f',0),(5,2,4,140161013,1,NULL,1,'off','','2023-07-22 00:00:00','Phu','81e97a85-f61a-11ed-bb65-00d861e72b9f',0),(6,5,4,140161013,1,'b24e30d3-6ee7-4a86-8685-9a6067da2413',1,'onl','','2023-07-22 00:00:00',NULL,'81e97a85-f61a-11ed-bb65-00d861e72b9f',0),(7,11,3,130000,1,NULL,1,'off',NULL,'2023-09-06 15:50:57','phan','81e97a85-f61a-11ed-bb65-00d861e72b9f',0),(8,22,3,130000,1,NULL,1,'off',NULL,'2023-09-06 16:27:09','phan','c3c4731a-c171-4c06-91f0-ff2c5b34c281',0),(9,24,3,130000,1,NULL,1,'off',NULL,'2023-09-06 16:35:38','phan','c3c4731a-c171-4c06-91f0-ff2c5b34c281',0),(11,3,3,100000,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',2,'onl','Pay at the counter','2023-09-07 10:34:04',NULL,NULL,0),(12,9,3,100000,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',2,'onl','Pay at the counter','2023-09-07 10:46:56',NULL,NULL,0),(13,7,3,100000,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',2,'onl','Pay at the counter','2023-09-07 10:47:27',NULL,NULL,0),(14,10,3,100000,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',2,'onl','Pay at the counter','2023-09-07 10:48:02',NULL,NULL,0),(18,8,3,100000,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',2,'onl','Pay at the counter','2023-09-07 10:56:34',NULL,NULL,0),(19,14,3,100000,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',2,'onl','Momo','2023-09-07 12:29:31',NULL,NULL,0),(20,35,3,50000,1,'81e97a85-f61a-11ed-bb65-00d861e72b9f',3,'onl','Momo','2023-09-08 12:02:07',NULL,NULL,0),(21,15,3,50000,1,'81e97a85-f61a-11ed-bb65-00d861e72b9f',3,'onl','Pay at the counter','2023-09-08 12:03:52',NULL,NULL,0),(22,13,9,50000,0,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',3,'onl','Pay at the counter','2023-09-08 13:19:02',NULL,NULL,0),(23,16,3,NULL,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',3,'onl','Pay at the counter','2023-09-08 22:17:30',NULL,NULL,0),(24,18,3,NULL,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',3,'onl','Pay at the counter','2023-09-08 22:19:16',NULL,NULL,0),(25,17,3,NULL,1,'2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177',3,'onl','Pay at the counter','2023-09-08 22:21:36',NULL,NULL,0);
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket2`
--

DROP TABLE IF EXISTS `ticket2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket2` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seat` smallint DEFAULT NULL,
  `trip_id` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `ticket_type` tinyint DEFAULT '1',
  `cus_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `is_get` tinyint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_ticket2_trip` (`trip_id`),
  CONSTRAINT `fk_ticket2_trip` FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket2`
--

LOCK TABLES `ticket2` WRITE;
/*!40000 ALTER TABLE `ticket2` DISABLE KEYS */;
INSERT INTO `ticket2` VALUES (10,6,3,50020,1,'phu2',0),(11,5,9,50020,1,'phu2',0),(12,8,3,50020,1,'phu2',0),(13,48,9,180000,0,'phu2',0);
/*!40000 ALTER TABLE `ticket2` ENABLE KEYS */;
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
  `is_active` tinyint DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `fk_trip_driver` (`driver_id`),
  KEY `fk_trip_vehicle` (`vehicle_id`),
  KEY `fk_trip_route` (`route_id`),
  CONSTRAINT `fk_trip_driver` FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_trip_route` FOREIGN KEY (`route_id`) REFERENCES `route` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_trip_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip`
--

LOCK TABLES `trip` WRITE;
/*!40000 ALTER TABLE `trip` DISABLE KEYS */;
INSERT INTO `trip` VALUES (3,'2023-09-06 18:00:00','2023-09-07 00:00:00',50000,'e71d2f7d-5e96-4c9e-82d8-cb52133f7c89',1,1,1),(4,'2022-05-22 00:00:00','2023-05-20 00:00:00',4521323,'e71d2f7d-5e96-4c9e-82d8-cb52133f7c89',1,4,1),(5,'2023-08-14 12:36:00','2023-08-14 13:05:00',2000,'e64c97ec-eeb6-40c4-9473-d50535a5b7b9',3,4,1),(7,'2023-09-26 17:52:00','2023-09-27 13:53:00',300000,'e64c97ec-eeb6-40c4-9473-d50535a5b7b9',1,2,1),(8,'2023-09-20 16:30:00','2023-09-21 16:30:00',3000,'e71d2f7d-5e96-4c9e-82d8-cb52133f7c89',6,8,1),(9,'2023-09-28 16:31:00','2023-09-29 16:32:00',300000,'e64c97ec-eeb6-40c4-9473-d50535a5b7b9',1,1,1);
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
INSERT INTO `user` VALUES ('2f5bc8ea-c6e1-4db9-b9b6-1dd941b73177','phu2@gmail.com','$2a$10$B5y3Sr719BiOIns2i5NTtOPUCjZ7O4fx2cxNswdNGXWsR32dvrNo2','0987654312','https://res.cloudinary.com/dqdspcxhq/image/upload/v1690776055/vqgtrfqcnehzcvtye6qr.jpg','phu2',1,'ROLE_CUSTOMER'),('81e97a85-f61a-11ed-bb65-00d861e72b9f','admin@gmail.com','$2a$10$nxrkmDEz827P6Bcu1JvWU.9gEjZ.dZpJmTq.//ROjsM0JpEReikPy','0987652721','https://res.cloudinary.com/dqdspcxhq/image/upload/v1690776055/vqgtrfqcnehzcvtye6qr.jpg','admin',1,'ROLE_ADMIN'),('a4b0ee9b-eee8-4b84-b30a-5f22630c80a0','phu7@gmail.com','$2a$10$3zf7sYk2tHJT1MVu76T4euj6PVNNf2qe6SXzKxTCiWMtIty.x8ZmS','0908091530','https://res.cloudinary.com/dqdspcxhq/image/upload/v1692023891/qrzfsx7yugiz1i5fee9p.png','phu4',1,'ROLE_EMPLOYEE'),('b24e30d3-6ee7-4a86-8685-9a6067da2413','phu5@gmail.com','$2a$10$gKoSmNY3YO1nV36aqaFbz.8QrkvJND88BBqKY86eiM4PoU5OeXpCG','0923812317','https://res.cloudinary.com/dqdspcxhq/image/upload/v1691996484/jiqzkxvsqugbhftegaig.png','phu5',1,'ROLE_CUSTOMER'),('c3c4731a-c171-4c06-91f0-ff2c5b34c281','phu3@gmail.com','$2a$10$26NxVlLHF0ubACVF.ade3upUj0/AAPbpNvpSk7M6mgPlt70IwQdim','0975674830','https://res.cloudinary.com/dqdspcxhq/image/upload/v1691382684/je2qlz19armccpkzini3.jpg','phu3',1,'ROLE_EMPLOYEE'),('e64c97ec-eeb6-40c4-9473-d50535a5b7b9','phu@gmail.com','$2a$10$nxrkmDEz827P6Bcu1JvWU.9gEjZ.dZpJmTq.//ROjsM0JpEReikPy','012938192',NULL,'phu',1,'ROLE_DRIVER'),('e71d2f7d-5e96-4c9e-82d8-cb52133f7c89','phu4@gmail.com','$2a$10$.d7xhKTN8Y7taOSuGKtji.pf5roPIXvW.Xc8Mnc8//HLVehiwIxLS','0987654322','https://res.cloudinary.com/dqdspcxhq/image/upload/v1691984605/alsulpiqevy3qwcnouot.jpg','phu4',1,'ROLE_DRIVER'),('f4e2ef15-3726-40c8-80f4-2b0642d48c42','phu6@gmail.com','$2a$10$u6.qCzV4RSq1EagrqgbWbugvcGXGFB3fmEVxiZPN8X6U/3TykYfBC','0987428364','https://res.cloudinary.com/dqdspcxhq/image/upload/v1692021953/dhgxr2k8gaxhmuywmlny.jpg','phu6',1,'ROLE_EMPLOYEE');
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
  `is_active` tinyint DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,50,'None',1),(2,55,'None',0),(3,48,'None',1),(6,47,'None',1),(7,41,'None',1),(8,49,'None',1);
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

-- Dump completed on 2023-09-09 13:40:04
