-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: seng300
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `allaccounts`
--

DROP TABLE IF EXISTS `allaccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allaccounts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allaccounts`
--

LOCK TABLES `allaccounts` WRITE;
/*!40000 ALTER TABLE `allaccounts` DISABLE KEYS */;
INSERT INTO `allaccounts` VALUES (1,'admin','123','admin'),(3,'gogli123','123gogli','patient'),(5,'tonilol','lol123','nurse'),(7,'qwerty','password','doctor'),(8,'rotting','qwerty','doctor'),(9,'tomhank','qwertyui','doctor'),(10,'tonyismyname','password','doctor');
/*!40000 ALTER TABLE `allaccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `appointmentid` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `appointment_day` int NOT NULL,
  `appointment_time_1` tinyint NOT NULL DEFAULT '0',
  `appointment_time_2` tinyint NOT NULL DEFAULT '0',
  `appointment_time_3` tinyint NOT NULL DEFAULT '0',
  `appointment_time_4` tinyint NOT NULL DEFAULT '0',
  `appointment_time_5` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`appointmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (8,3,9,0,1,0,0,0,0),(9,3,9,0,0,1,0,0,0),(10,3,10,0,1,0,0,0,0),(11,1,9,2,1,0,0,0,0),(12,1,9,4,0,0,0,1,0);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctorschedule`
--

DROP TABLE IF EXISTS `doctorschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctorschedule` (
  `doctorId` int NOT NULL,
  `dayWorking` varchar(255) NOT NULL,
  PRIMARY KEY (`doctorId`),
  UNIQUE KEY `dayWorking_UNIQUE` (`dayWorking`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctorschedule`
--

LOCK TABLES `doctorschedule` WRITE;
/*!40000 ALTER TABLE `doctorschedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctorschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab_appointments`
--

DROP TABLE IF EXISTS `lab_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lab_appointments` (
  `lab_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `lab_day` int NOT NULL,
  `lab_time1` tinyint(1) NOT NULL DEFAULT '0',
  `lab_time2` tinyint(1) NOT NULL DEFAULT '0',
  `lab_time3` tinyint(1) NOT NULL DEFAULT '0',
  `lab_time4` tinyint(1) NOT NULL DEFAULT '0',
  `lab_time5` tinyint(1) NOT NULL DEFAULT '0',
  `lab_referral` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`lab_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_appointments`
--

LOCK TABLES `lab_appointments` WRITE;
/*!40000 ALTER TABLE `lab_appointments` DISABLE KEYS */;
/*!40000 ALTER TABLE `lab_appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referral`
--

DROP TABLE IF EXISTS `referral`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `referral` (
  `referralCode` int NOT NULL,
  `patientID` int DEFAULT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`referralCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referral`
--

LOCK TABLES `referral` WRITE;
/*!40000 ALTER TABLE `referral` DISABLE KEYS */;
INSERT INTO `referral` VALUES (330357,3,'tachycardia','Cardiologist'),(331529,3,'hypokalemia','Nephrologist'),(364166,3,'hyperkalemia','Nephrologist'),(395667,3,'atrial fibrillation','Cardiologist'),(551114,3,'test','Cardiologist'),(754325,3,'high blood pressure','Cardiologist'),(907721,3,'hypocalcemia','Nephrologist'),(997144,3,'atrial fibrillation','Cardiologist');
/*!40000 ALTER TABLE `referral` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(2555) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `DOB` varchar(255) NOT NULL,
  `speciality` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'admin','time','admin.ca','lolipop','123123123','1997','General','admin'),(3,'moni','popi','lolimoni','123 ullolo','435738957','19978034','Nephrologist','patient'),(5,'Sharon','maroon','sharon.24.ca','123 ponitail NW','987687777','19970302','General','nurse'),(7,'Dddd','kkkk','holicow','321 holicow road','4356788766','19970806','Nephrologist','doctor'),(8,'Dheeraj','Kumar','pasta@123','12 pasta rd','4035692345','19970805','Neurologist','doctor'),(9,'boring','daalph','dalph','123 dred','2359804853','19234023','Cardiologist','doctor'),(10,'Tony','rockstar','rockstar@gmail.com','123 rockstar way','3331112222','19950406','Cardiologist','doctor');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-11 17:13:12
