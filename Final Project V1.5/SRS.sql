-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: srs
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `courseNo` varchar(32) NOT NULL,
  `courseName` varchar(50) DEFAULT NULL,
  `credits` double DEFAULT NULL,
  PRIMARY KEY (`courseNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` varchar(15) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `major` varchar(45) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `degree` varchar(32) DEFAULT NULL,
  `title` varchar(40) DEFAULT NULL,
  `department` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prerequisites`
--

DROP TABLE IF EXISTS `prerequisites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prerequisites` (
  `id` int(11) NOT NULL,
  `precourseNo` varchar(32) DEFAULT NULL,
  `courseNo` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courseNo_idx` (`courseNo`),
  KEY `courseNo_idx1` (`precourseNo`),
  CONSTRAINT `courseNo_courseNo` FOREIGN KEY (`courseNo`) REFERENCES `course` (`courseNo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `precourseNo_courseNo` FOREIGN KEY (`precourseNo`) REFERENCES `course` (`courseNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prerequisites`
--

LOCK TABLES `prerequisites` WRITE;
/*!40000 ALTER TABLE `prerequisites` DISABLE KEYS */;
/*!40000 ALTER TABLE `prerequisites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scheduleofclasses`
--

DROP TABLE IF EXISTS `scheduleofclasses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scheduleofclasses` (
  `id` int(11) NOT NULL,
  `semester` varchar(32) DEFAULT NULL,
  `sectionNo` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sectionNo_sectionNo_idx` (`sectionNo`),
  CONSTRAINT `sectionNo_sectionNo` FOREIGN KEY (`sectionNo`) REFERENCES `section` (`sectionNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scheduleofclasses`
--

LOCK TABLES `scheduleofclasses` WRITE;
/*!40000 ALTER TABLE `scheduleofclasses` DISABLE KEYS */;
/*!40000 ALTER TABLE `scheduleofclasses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `sectionNo` varchar(32) NOT NULL,
  `dayOfWeek` varchar(10) DEFAULT NULL,
  `timeOfDay` varchar(12) DEFAULT NULL,
  `room` varchar(45) DEFAULT NULL,
  `seatingCapacity` int(11) DEFAULT NULL,
  `courseNo` varchar(32) DEFAULT NULL,
  `professorId` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`sectionNo`),
  KEY `scourseNo_courseNo_idx` (`courseNo`),
  KEY `professorId_id_idx` (`professorId`),
  CONSTRAINT `professorId_id` FOREIGN KEY (`professorId`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `scourseNo_courseNo` FOREIGN KEY (`courseNo`) REFERENCES `course` (`courseNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transcript`
--

DROP TABLE IF EXISTS `transcript`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transcript` (
  `transcriptEntryId` varchar(50) NOT NULL,
  `transcriptId` varchar(45) DEFAULT NULL,
  `grade` varchar(8) DEFAULT NULL,
  `sectionNo` varchar(32) DEFAULT NULL,
  `studentId` varchar(15) DEFAULT NULL,
  `socId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`transcriptEntryId`),
  UNIQUE KEY `transcriptEntryId_UNIQUE` (`transcriptEntryId`),
  KEY `tsectionNo_sectionNo_idx` (`sectionNo`),
  KEY `studentId_id_idx` (`studentId`),
  CONSTRAINT `studentId_id` FOREIGN KEY (`studentId`) REFERENCES `person` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tsectionNo_sectionNo` FOREIGN KEY (`sectionNo`) REFERENCES `section` (`sectionNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transcript`
--

LOCK TABLES `transcript` WRITE;
/*!40000 ALTER TABLE `transcript` DISABLE KEYS */;
/*!40000 ALTER TABLE `transcript` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-02 18:43:47
