CREATE DATABASE  IF NOT EXISTS `KittyDB` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `KittyDB`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 34.41.117.214    Database: KittyDB
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `response_name` varchar(255) DEFAULT NULL,
  `kitty_id` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `like_num` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES ('240416141386313629696','Meow Mate 1','none','240416136685641793536','none','2024-04-16',1,'so cute!',2),('240416141557978103808','Meow Mate 1','none','240416136682275864576','none','2024-04-16',1,'So sweet!',0),('240416141604058824704','Meow Mate 1','none','240416136687738945536','none','2024-04-16',1,'Looks nice',0),('240416141638091407360','Meow Mate 1','none','240416136692327514112','none','2024-04-16',1,'Cute cute cute',1),('240416141677729677312','Meow Mate 1','none','240416136695206903808','none','2024-04-16',1,'Pretty ',0),('240416141714910085120','Meow Mate 1','none','240416136689878040576','none','2024-04-16',1,'I like her',0),('24041615717197938688','Meow Mate 1','none','24041614118044041216','none','2024-04-16',1,'Yeah, so pretty!!!',4),('24041616169532653568','Meow Mate 2','Meow Mate 1','24041614118044041216','24041615717197938688','2024-04-16',1,'I agreeeee.',2),('24041616493104332800','Meow Mate 3','Meow Mate 2','24041614118044041216','24041615717197938688','2024-04-16',1,'yes yes!',1),('24041616521864675328','Meow Mate 4','none','24041614118044041216','none','2024-04-16',1,'I want to adopt her!',1),('24041651141742166016','Meow Mate 5','Meow Mate 4','24041614118044041216','24041616521864675328','2024-04-16',1,'Nice',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funds`
--

DROP TABLE IF EXISTS `funds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funds` (
  `id` varchar(255) NOT NULL,
  `payment` int DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `month` int DEFAULT NULL,
  `day` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funds`
--

LOCK TABLES `funds` WRITE;
/*!40000 ALTER TABLE `funds` DISABLE KEYS */;
INSERT INTO `funds` VALUES ('24041605898829299712',100,'Donation From LYZ','I donate because of Pom Pom.','2024-04-16',1,4,16),('24041606282081730560',-35,'Cat Food','We bought cat food.','2024-04-16',1,4,16),('24041607019826249728',-20,'Canned Cat Food','We bought canned cat food.','2024-04-17',1,4,17);
/*!40000 ALTER TABLE `funds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kitty_info`
--

DROP TABLE IF EXISTS `kitty_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kitty_info` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `kitty_sex` varchar(255) DEFAULT NULL,
  `kitty_health` varchar(255) DEFAULT NULL,
  `hair_color` varchar(255) DEFAULT NULL,
  `kitty_sterilization_time` varchar(255) DEFAULT NULL,
  `kitty_sterilization` varchar(255) DEFAULT NULL,
  `kitty_birthday` varchar(255) DEFAULT NULL,
  `first_seen` varchar(255) DEFAULT NULL,
  `kitty_character` varchar(255) DEFAULT NULL,
  `kitty_appearance` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `heart_num` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kitty_info`
--

LOCK TABLES `kitty_info` WRITE;
/*!40000 ALTER TABLE `kitty_info` DISABLE KEYS */;
INSERT INTO `kitty_info` VALUES ('240416136682275864576','Katy','https://lyuanzhi.com:8084/kitty_240416136682275864576.jpg','https://lyuanzhi.com:8084/avatar_240416136682275864576.jpg','female','healthy','blue','last month','Yes','2 year','last month','gentle, friendly, quiet','pretty','2024-04-16',1,3),('240416136685641793536','Goldilocks','https://lyuanzhi.com:8084/kitty_240416136685641793536.jpg','https://lyuanzhi.com:8084/avatar_240416136685641793536.jpg','female','healthy','brown','last month','Yes','2 month','last month','sweet','pretty','2024-04-16',1,4),('240416136687738945536','Katniss','https://lyuanzhi.com:8084/kitty_240416136687738945536.jpg','https://lyuanzhi.com:8084/avatar_240416136687738945536.jpg','female','healthy','grey','last month','Yes','1 year','last month','shy','pretty','2024-04-16',1,6),('240416136689878040576','Celeste','https://lyuanzhi.com:8084/kitty_240416136689878040576.jpg','https://lyuanzhi.com:8084/avatar_240416136689878040576.jpg','female','healthy','grey & white','last month','Yes','4 year','last month','gentle and soft','pretty','2024-04-16',1,4),('240416136692327514112','Danica','https://lyuanzhi.com:8084/kitty_240416136692327514112.jpg','https://lyuanzhi.com:8084/avatar_240416136692327514112.jpg','female','healthy','grey','last month','Yes','6 month','last month','sweet and playful','pretty','2024-04-16',1,5),('240416136695206903808','Monty','https://lyuanzhi.com:8084/kitty_240416136695206903808.jpg','https://lyuanzhi.com:8084/avatar_240416136695206903808.jpg','male','healthy','white','last month','Yes','4 year','last month','gentle, friendly','pretty','2024-04-16',1,2),('24041614118044041216','Pom Pom','https://lyuanzhi.com:8084/kitty_24041614118044041216.jpg','https://lyuanzhi.com:8084/avatar_24041614118044041216.jpg','female','healthy','white','last month','Yes','2 year','last month','sweet and soft','pretty','2024-04-16',1,11);
/*!40000 ALTER TABLE `kitty_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-16 15:39:30
