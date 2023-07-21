CREATE DATABASE  IF NOT EXISTS `clothes_shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clothes_shop`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: clothes_shop
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
  `user_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_address_user` (`user_id`),
  CONSTRAINT `fk_address_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Hồ Chí Minh','Quận 1','Phường Bến Nghé','81e995ab-f61a-11ed-bb65-00d861e72b9f');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Áo'),(4,'Đầm'),(5,'Khác'),(2,'Quần'),(3,'Váy');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `root_comment` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_user` (`user_id`),
  KEY `fk_comment_product` (`product_id`),
  KEY `fk_comment_rootcomment` (`root_comment`),
  CONSTRAINT `fk_comment_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_rootcomment` FOREIGN KEY (`root_comment`) REFERENCES `comment` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_orderdetail_product` (`product_id`),
  KEY `fk_orderdetail_order` (`order_id`),
  CONSTRAINT `fk_orderdetail_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_orderdetail_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,1,200,1),(3,1,5,2),(1,2,20,3),(3,3,30,4),(5,5,50,8),(4,4,40,9),(5,5,50,10),(6,6,60,11),(7,7,70,12),(8,8,80,13),(9,9,90,14),(10,10,100,15),(1,11,10,16),(2,12,20,17),(3,13,30,18),(4,14,40,19),(5,15,50,20),(6,16,60,21),(7,17,70,22),(8,18,80,23),(9,19,90,24),(10,20,100,25),(3,23,30,26),(4,24,40,27),(5,25,50,28),(6,26,60,29),(7,27,70,30),(8,28,80,31),(9,29,90,32),(10,30,100,33),(1,21,10,34),(2,22,20,35),(3,33,30,36),(4,34,40,37),(5,35,50,38),(6,36,60,39),(7,37,70,40),(8,38,80,41),(9,39,90,42),(10,30,100,43),(1,31,10,44),(2,32,20,45),(10,40,100,46),(3,43,30,47),(4,44,40,48),(5,45,50,49),(6,46,60,50),(7,47,70,51),(8,48,80,52),(9,49,90,53),(10,50,100,54),(1,41,10,55),(2,42,20,56);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) DEFAULT NULL,
  `order_date` date NOT NULL,
  `total_price` double DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `receive_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_user` (`user_id`),
  CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2023-05-19',100000,'Đặt hàng',NULL),(2,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2022-04-20',29000,'Đặt hàng',NULL),(3,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2022-09-20',100000,'Đặt hàng',NULL),(4,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2022-11-20',29000,'Đặt hàng',NULL),(5,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2020-04-20',29000,'Đặt hàng',NULL),(6,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2012-04-20',29000,'Đặt hàng',NULL),(7,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2021-02-20',29000,'Đặt hàng',NULL),(8,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2013-03-20',29000,'Đặt hàng',NULL),(9,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2012-12-20',29000,'Đặt hàng',NULL),(10,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2022-08-20',29000,'Đặt hàng',NULL),(11,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2023-05-20',29000,'Đặt hàng',NULL),(12,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2002-03-20',29000,'Đặt hàng',NULL),(13,'81e995ab-f61a-11ed-bb65-00d861e72b9f','2003-10-20',29000,'Đặt hàng',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) NOT NULL DEFAULT b'1',
  `price` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `category_id` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `fk_product_category` (`category_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Bánh ngô',_binary '',50000,'Bánh được làm từ ngô',100,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(2,'Bánh trái',_binary '',100000,'Bánh do trái',100,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(3,'Bánh 1',_binary '',20000,'Bánh 1',200,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(4,'Bánh 2',_binary '',20000,'Bánh 2',200,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(5,'Bánh 3',_binary '',20000,'Bánh 3',200,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(6,'Bánh 4',_binary '',20000,'Bánh 4',200,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(7,'Bánh 5',_binary '',20000,'Bánh 5',200,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(8,'Bánh 6',_binary '',20000,'Bánh 6',200,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(9,'Bánh 7',_binary '',20000,'Bánh 7',200,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(10,'Bánh 8',_binary '',20000,'Bánh 8',200,3,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(11,'Cơm cháy 1',_binary '',20000,'Cơm cháy 1',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(12,'Cơm cháy 2',_binary '',20000,'Cơm cháy 2',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(13,'Cơm cháy 3',_binary '',20000,'Cơm cháy 3',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(14,'Cơm cháy 4',_binary '',20000,'Cơm cháy 4',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(15,'Cơm cháy 5',_binary '',20000,'Cơm cháy 5',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(16,'Cơm cháy 6',_binary '',20000,'Cơm cháy 6',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(17,'Cơm cháy 7',_binary '',20000,'Cơm cháy 7',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(18,'Cơm cháy 8',_binary '',20000,'Cơm cháy 8',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(19,'Cơm cháy 9',_binary '',20000,'Cơm cháy 9',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(20,'Cơm cháy 10',_binary '',20000,'Cơm cháy 10',200,2,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(21,'Khô 1',_binary '',20000,'Khô 1',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(22,'Khô 2',_binary '',20000,'Khô 2',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(23,'Khô 3',_binary '',20000,'Khô 3',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(24,'Khô 4',_binary '',20000,'Khô 4',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(25,'Khô 5',_binary '',20000,'Khô 5',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(26,'Khô 6',_binary '',20000,'Khô 6',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(27,'Khô 7',_binary '',20000,'Khô 7',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(28,'Khô 8',_binary '',20000,'Khô 8',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(29,'Khô 9',_binary '',20000,'Khô 9',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(30,'Khô 10',_binary '',20000,'Khô 10',200,4,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(31,'Khác 1',_binary '',20000,'Khác 1',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(32,'Khác 2',_binary '',20000,'Khác 2',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(33,'Khác 3',_binary '',20000,'Khác 3',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(34,'Khác 4',_binary '',20000,'Khác 4',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(35,'Khác 5',_binary '',20000,'Khác 5',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(36,'Khác 6',_binary '',20000,'Khác 6',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(37,'Khác 7',_binary '',20000,'Khác 7',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(38,'Khác 8',_binary '',20000,'Khác 8',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(39,'Khác 9',_binary '',20000,'Khác 9',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(40,'Khác 10',_binary '',20000,'Khác 10',200,5,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(41,'Sữa chua 1',_binary '',20000,'Sữa chua 1',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(42,'Sữa chua 2',_binary '',20000,'Sữa chua 2',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(43,'Sữa chua 3',_binary '',20000,'Sữa chua 3',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(44,'Sữa chua 4',_binary '',20000,'Sữa chua 4',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(45,'Sữa chua 5',_binary '',20000,'Sữa chua 5',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(46,'Sữa chua 6',_binary '',20000,'Sữa chua 6',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(47,'Sữa chua 7',_binary '',20000,'Sữa chua 7',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(48,'Sữa chua 8',_binary '',20000,'Sữa chua 8',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(49,'Sữa chua 9',_binary '',20000,'Sữa chua 9',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn'),(50,'Sữa chua 10',_binary '',20000,'Sữa chua 10',200,1,'https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_img`
--

DROP TABLE IF EXISTS `product_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_img` (
  `image` varchar(255) DEFAULT NULL,
  `product_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_productimg_product` (`product_id`),
  CONSTRAINT `fk_productimg_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_img`
--

LOCK TABLES `product_img` WRITE;
/*!40000 ALTER TABLE `product_img` DISABLE KEYS */;
INSERT INTO `product_img` VALUES ('https://oldsailor.com.vn/vnt_upload/product/05_2023/ea5942b6c9c4179a4ed533.jpg',1,1),('https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn',1,2),('https://oldsailor.com.vn/vnt_upload/product/05_2023/ea5942b6c9c4179a4ed533.jpg',1,3),('https://down-vn.img.susercontent.com/file/91f7db11ccb0be1390948e154c56331a_tn',1,4),('https://oldsailor.com.vn/vnt_upload/product/05_2023/ea5942b6c9c4179a4ed533.jpg',1,5),('https://oldsailor.com.vn/vnt_upload/product/05_2023/ea5942b6c9c4179a4ed533.jpg',1,6);
/*!40000 ALTER TABLE `product_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refesh_token`
--

DROP TABLE IF EXISTS `refesh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refesh_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nxc3pv5r2ydtky2laiw6e5qyc` (`user_id`),
  CONSTRAINT `FKpe372415k3iqhd7xyej8ekf7q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refesh_token`
--

LOCK TABLES `refesh_token` WRITE;
/*!40000 ALTER TABLE `refesh_token` DISABLE KEYS */;
INSERT INTO `refesh_token` VALUES (3,'2023-07-08 10:31:38.267197','66810ee8-3094-4dea-bcda-dfd968e2e05c','3b842419-1526-49b1-beba-b644853085dc'),(12,'2023-07-20 13:58:01.246000','2cc4d1f1-2f01-40cd-80ea-f84c3055a30e','7b6895d7-5116-42a8-b51e-82dc10251c09');
/*!40000 ALTER TABLE `refesh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('116ebd3b-b67e-4646-b6ad-9020959dc144','phu3','123','phu3@gmail.com','CUSTOMER','02312312312'),('344099f5-7bb5-4b95-b05b-dbeefba7835c','phu2','123','phu2@gmail.com','CUSTOMER','01238912'),('3b842419-1526-49b1-beba-b644853085dc','phu4','$2a$10$KfeRpCcOLHGBSeNOEGLLTucv80Q.BykklOLoZnnmD1FFBFGit6RUG','phu4@gmail.com','CUSTOMER','0987652721'),('6fad7295-577c-4d89-8ee4-c5b421ab1c26','phu1','123','phu1@gmail.com','CUSTOMER','0987654321'),('7b6895d7-5116-42a8-b51e-82dc10251c09','phu7','$2a$10$3.p1gRcaA3v4ZZYy3zOwqeLzazI1hEkyEzPJIHoGKuc.ICktGMGOe','phu7@gmail.com','CUSTOMER','012938192'),('81e97a85-f61a-11ed-bb65-00d861e72b9f','admin','123','admin@gmail.com','Admin',NULL),('81e995ab-f61a-11ed-bb65-00d861e72b8u','phu6','$2a$10$inCa1vXj8z9PGvYhHWBIkeiuFPulZECXRMp/DwBXVlCgdXMyTSt.m','phu6@gmail.com','CUSTOMER','023123198'),('81e995ab-f61a-11ed-bb65-00d861e72b9f','phu','123','phu@gmail.com','User',NULL),('d544c9c2-9595-4aad-a95f-99dc534dbe41','phu5','$2a$10$inCa1vXj8z9PGvYhHWBIkeiuFPulZECXRMp/DwBXVlCgdXMyTSt.m','phu5@gmail.com','CUSTOMER','023123198');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-21 14:37:58
