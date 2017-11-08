-- MySQL dump 10.14  Distrib 5.5.56-MariaDB, for Linux (x86_64)
--
-- Host: mysql2.cs.stonybrook.edu    Database: aspen
-- ------------------------------------------------------
-- Server version	5.5.25

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
-- Current Database: `aspen`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `aspen` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `aspen`;

--
-- Table structure for table `districtboundaries`
--

DROP TABLE IF EXISTS `districtboundaries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `districtboundaries` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Year` year(4) NOT NULL,
  `Lat` decimal(10,6) NOT NULL,
  `Long` decimal(10,6) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districtboundaries`
--

LOCK TABLES `districtboundaries` WRITE;
/*!40000 ALTER TABLE `districtboundaries` DISABLE KEYS */;
/*!40000 ALTER TABLE `districtboundaries` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`aspen`@`%`*/ /*!50003 TRIGGER `aspen`.`districtboundaries_BEFORE_INSERT_check` BEFORE INSERT ON `districtboundaries` FOR EACH ROW
BEGIN
	IF (new.Lat < -90 or new.Lat > 90) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Latitude must be between -90 and 90 degrees';
    END IF;
	IF (new.Long < -180 or new.Long > 180) THEN
        SIGNAL SQLSTATE '45001'
            SET MESSAGE_TEXT = 'Longtitude must be between -180 and 180 degrees';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`aspen`@`%`*/ /*!50003 TRIGGER `aspen`.`districtboundaries_BEFORE_UPDATE_check` BEFORE UPDATE ON `districtboundaries` FOR EACH ROW
BEGIN
	IF (new.Lat < -90 or new.Lat > 90) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Latitude must be between -90 and 90 degrees';
    END IF;
	IF (new.Long < -180 or new.Long > 180) THEN
        SIGNAL SQLSTATE '45001'
            SET MESSAGE_TEXT = 'Longtitude must be between -180 and 180 degrees';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `districtboundarymapping`
--

DROP TABLE IF EXISTS `districtboundarymapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `districtboundarymapping` (
  `DistrictId` int(11) NOT NULL,
  `DistrictBoundaryId` int(11) NOT NULL,
  PRIMARY KEY (`DistrictId`,`DistrictBoundaryId`),
  KEY `FK_DB_ID_idx` (`DistrictBoundaryId`),
  CONSTRAINT `FK_DBM_DISTRICT_ID` FOREIGN KEY (`DistrictId`) REFERENCES `districts` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_DBM_DB_ID` FOREIGN KEY (`DistrictBoundaryId`) REFERENCES `districtboundaries` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districtboundarymapping`
--

LOCK TABLES `districtboundarymapping` WRITE;
/*!40000 ALTER TABLE `districtboundarymapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `districtboundarymapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `districtelectionmapping`
--

DROP TABLE IF EXISTS `districtelectionmapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `districtelectionmapping` (
  `DistrictId` int(11) NOT NULL,
  `ElectionId` int(11) NOT NULL,
  PRIMARY KEY (`ElectionId`,`DistrictId`),
  KEY `FK_DEM_DISTRICT_ID_idx` (`DistrictId`),
  CONSTRAINT `FK_DEM_DISTRICT_ID` FOREIGN KEY (`DistrictId`) REFERENCES `districts` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_DEM_ELECTION_ID` FOREIGN KEY (`ElectionId`) REFERENCES `elections` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districtelectionmapping`
--

LOCK TABLES `districtelectionmapping` WRITE;
/*!40000 ALTER TABLE `districtelectionmapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `districtelectionmapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `districts`
--

DROP TABLE IF EXISTS `districts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `districts` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DistrictNum` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `UNIQUE` (`Id`,`DistrictNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
/*!40000 ALTER TABLE `districts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elections`
--

DROP TABLE IF EXISTS `elections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `elections` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Year` year(4) NOT NULL,
  `Winner` enum('D','R') NOT NULL,
  `TotalVotes` int(10) unsigned NOT NULL,
  `DemVote` int(10) unsigned NOT NULL,
  `RepVote` int(10) unsigned NOT NULL,
  `DemVoteShare` decimal(16,15) unsigned NOT NULL,
  `RepVoteShare` decimal(16,15) unsigned NOT NULL,
  `DemStatus` enum('N/A','Incumbent','Challenger') NOT NULL,
  `RepStatus` enum('N/A','Incumbent','Challenger') NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elections`
--

LOCK TABLES `elections` WRITE;
/*!40000 ALTER TABLE `elections` DISABLE KEYS */;
/*!40000 ALTER TABLE `elections` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`aspen`@`%`*/ /*!50003 TRIGGER `aspen`.`elections_BEFORE_INSERT_check` BEFORE INSERT ON `elections` FOR EACH ROW
BEGIN
	IF (new.demVoteShare > 1 or new.repVoteShare > 1) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Vote share must be less than or equal to 1';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`aspen`@`%`*/ /*!50003 TRIGGER `aspen`.`elections_BEFORE_UPDATE_check` BEFORE UPDATE ON `elections` FOR EACH ROW
BEGIN
	IF (new.demVoteShare > 1 or new.repVoteShare > 1) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Vote share must be less than or equal to 1';
	END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `statedistrictmapping`
--

DROP TABLE IF EXISTS `statedistrictmapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statedistrictmapping` (
  `StateId` int(11) NOT NULL,
  `DistrictId` int(11) NOT NULL,
  PRIMARY KEY (`StateId`,`DistrictId`),
  KEY `FK_DISTRICT_ID_idx` (`DistrictId`),
  CONSTRAINT `FK_SDM_STATE_ID` FOREIGN KEY (`StateId`) REFERENCES `states` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_SDM_DISTRICT_ID` FOREIGN KEY (`DistrictId`) REFERENCES `districts` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statedistrictmapping`
--

LOCK TABLES `statedistrictmapping` WRITE;
/*!40000 ALTER TABLE `statedistrictmapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `statedistrictmapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `states` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Code` char(2) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`aspen`@`%`*/ /*!50003 TRIGGER `aspen`.`states_BEFORE_INSERT_check` BEFORE INSERT ON `states` FOR EACH ROW
BEGIN
	SET new.Code = UPPER(new.Code);
    IF (new.Code not regexp '[A-Z]{2}') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'State code must be a 2-letter alphabetical code';
    END IF;
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`aspen`@`%`*/ /*!50003 TRIGGER `aspen`.`states_BEFORE_UPDATE_check` BEFORE UPDATE ON `states` FOR EACH ROW
BEGIN
	SET new.Code = UPPER(new.Code);
	IF (new.Code not regexp '[A-Z]{2}') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'State code must be a 2-letter alphabetical code';
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-05 21:31:49
