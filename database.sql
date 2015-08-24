CREATE DATABASE  IF NOT EXISTS `restapp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `restapp`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: restapp
-- ------------------------------------------------------
-- Server version	5.6.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `position` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `zip` int(10) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Amit','asb634@nyu.edu','New_pass1','7188092179','owner','10115 77 th street','New York','New York',11416);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `reserve_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `reserve_date` date NOT NULL,
  `time_from` time NOT NULL,
  `time_to` time NOT NULL,
  `reserve_table_id` int(10) NOT NULL,
  `no_of_people` int(10) DEFAULT NULL,
  `special_arrangement` varchar(100) DEFAULT NULL,
  `created_by` enum('user','owner') DEFAULT NULL,
  PRIMARY KEY (`reserve_id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (14,1,'2015-08-12','19:00:00','21:00:00',6,6,NULL,'user'),(15,1,'2015-08-26','22:00:00','24:00:00',5,6,NULL,'user'),(16,1,'2015-08-25','22:00:00','24:00:00',10,6,NULL,'user'),(17,2,'2015-08-25','22:00:00','24:00:00',7,8,'birthday','user'),(18,2,'2015-08-13','22:00:00','24:00:00',5,8,'anniversary','user'),(19,2,'2015-08-13','00:00:00','02:00:00',5,3,'','user'),(20,2,'2015-08-20','00:00:00','02:00:00',2,3,'','user'),(21,3,'2015-08-22','20:00:00','22:00:00',7,10,'date','user'),(22,4,'2015-08-25','22:00:00','24:00:00',8,10,'date','user'),(23,4,'2015-08-17','22:00:00','24:00:00',7,4,'date','user'),(24,5,'2015-08-11','22:00:00','24:00:00',2,3,'date','user'),(25,5,'2015-08-28','22:00:00','24:00:00',8,3,'date','user'),(28,17,'2015-08-15','06:00:00','08:00:00',10,4,'birthday','user'),(29,17,'2015-08-15','07:00:00','09:00:00',7,4,'birthday','user'),(30,17,'2015-08-15','08:00:00','10:00:00',10,4,'birthday','user'),(31,17,'2015-08-15','09:00:00','11:00:00',2,4,'birthday','user'),(32,17,'2015-08-15','10:00:00','12:00:00',2,4,'birthday','user'),(33,17,'2015-08-15','11:00:00','13:00:00',2,4,'birthday','user'),(34,17,'2015-08-15','12:00:00','14:00:00',10,4,'birthday','user'),(35,17,'2015-08-15','13:00:00','15:00:00',9,4,'birthday','user'),(36,17,'2015-08-15','14:00:00','16:00:00',2,4,'birthday','user'),(37,17,'2015-08-15','15:00:00','17:00:00',10,4,'birthday','user'),(38,17,'2015-08-15','16:00:00','18:00:00',8,4,'birthday','user'),(39,17,'2015-08-15','05:00:00','07:00:00',2,4,'birthday','user'),(40,17,'2015-08-15','06:00:00','08:00:00',5,4,'birthday','user'),(41,17,'2015-08-15','18:00:00','20:00:00',1,4,'birthday','user'),(42,17,'2015-08-15','19:00:00','21:00:00',8,4,'birthday','user'),(44,17,'2015-08-15','21:00:00','23:00:00',2,4,'birthday','user'),(45,17,'2015-08-15','22:00:00','24:00:00',5,4,'birthday','user'),(47,17,'2015-08-15','00:00:00','02:00:00',10,4,'birthday','user'),(48,17,'2015-08-15','01:00:00','03:00:00',10,4,'birthday','user'),(49,17,'2015-08-15','02:00:00','04:00:00',7,4,'birthday','user'),(50,17,'2015-08-15','03:00:00','05:00:00',1,4,'birthday','user'),(51,17,'2015-08-15','04:00:00','06:00:00',1,4,'birthday','user'),(52,17,'2015-08-15','17:00:00','19:00:00',10,4,'birthday','user'),(53,17,'2015-08-27','08:00:00','09:00:00',2,4,'birthday','owner'),(55,3,'2015-08-21','22:00:00','23:00:00',4,5,'party','user');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_table`
--

DROP TABLE IF EXISTS `reservation_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation_table` (
  `reserve_id` int(11) NOT NULL,
  `table_id` int(11) NOT NULL,
  PRIMARY KEY (`reserve_id`,`table_id`),
  KEY `reservation_table_ibfk_2` (`table_id`),
  CONSTRAINT `reservation_table_ibfk_1` FOREIGN KEY (`reserve_id`) REFERENCES `reservation` (`reserve_id`) ON UPDATE CASCADE,
  CONSTRAINT `reservation_table_ibfk_2` FOREIGN KEY (`table_id`) REFERENCES `table_info` (`table_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_table`
--

LOCK TABLES `reservation_table` WRITE;
/*!40000 ALTER TABLE `reservation_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_info`
--

DROP TABLE IF EXISTS `table_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table_info` (
  `table_id` int(11) NOT NULL AUTO_INCREMENT,
  `table_name` varchar(100) NOT NULL,
  `table_capacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`table_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_info`
--

LOCK TABLES `table_info` WRITE;
/*!40000 ALTER TABLE `table_info` DISABLE KEYS */;
INSERT INTO `table_info` VALUES (1,'Table 1',4),(2,'Table 2',4),(3,'Table 3',6),(4,'Table 4',6),(5,'Table 5',8),(6,'Table 6',8),(7,'Table 7',10),(8,'Table 8',10),(9,'Table 9',12),(10,'Table 10',12);
/*!40000 ALTER TABLE `table_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_reservation`
--

DROP TABLE IF EXISTS `user_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_reservation` (
  `user_id` int(11) NOT NULL,
  `reserve_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`reserve_id`),
  KEY `user_reservation_ibfk_2` (`reserve_id`),
  CONSTRAINT `user_reservation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON UPDATE CASCADE,
  CONSTRAINT `user_reservation_ibfk_2` FOREIGN KEY (`reserve_id`) REFERENCES `reservation` (`reserve_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_reservation`
--

LOCK TABLES `user_reservation` WRITE;
/*!40000 ALTER TABLE `user_reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'amit123@gmail.com','Amit Bhandari','2365876854'),(2,'anup123@gmail.com','Anup Deshmukh','7593568308'),(3,'avanish123@gmail.com','Avanish Upadhyay','8794574976'),(4,'manjul123@gmail.com','Manjul Mishra','6759893456'),(5,'hardik123@gmail.com','Hardik Gohil','5456986753'),(6,'vishal123@gmail.com','Vishal Mirza','7575456786'),(7,'tanmay123@gmail.com','Tanmay Grover','6564569876'),(8,'nipun123@gmail.com','Nipun mishra','7675535497'),(15,'asb123@gmail.com','Amit Bhandari','7188092179'),(17,'asb634@nyu.edu','Amit Bhandari','7188092179');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-09 15:10:42
