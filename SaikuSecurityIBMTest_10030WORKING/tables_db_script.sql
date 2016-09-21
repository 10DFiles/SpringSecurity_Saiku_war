/*
USE saikusecurityibmtest;

CREATE TABLE GpAuthority (id bigint(20) NOT NULL AUTO_INCREMENT, authority varchar(255) DEFAULT NULL, PRIMARY KEY (id)) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO GpAuthority VALUES (1,'GpUser');
INSERT INTO GpAuthority VALUES (2,'GpAdmin');
CREATE TABLE GpUser (id bigint(20) NOT NULL AUTO_INCREMENT,accestype varchar(255) DEFAULT NULL,firstName varchar(255) DEFAULT NULL,languagepreference varchar(255) DEFAULT NULL,lastName varchar(255) DEFAULT NULL,lastaccess datetime DEFAULT NULL,licenseid varchar(255) DEFAULT NULL,middleName varchar(255) DEFAULT NULL,mustresetpassword varchar(255) DEFAULT NULL,newuser bit(1) DEFAULT NULL,password varchar(255) DEFAULT NULL,phonenumber varchar(255) DEFAULT NULL, primaryemail varchar(255) DEFAULT NULL, screenname varchar(255) DEFAULT NULL, startdate datetime DEFAULT NULL, username varchar(255) DEFAULT NULL, PRIMARY KEY (id)) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
INSERT INTO GpUser VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'','12345',NULL,NULL,NULL,NULL,'test');
CREATE TABLE GpUser_GpAuthority ( GpUser_id bigint(20) NOT NULL, roles_id bigint(20) NOT NULL, PRIMARY KEY (GpUser_id,roles_id), CONSTRAINT FK_c7kyumqvtqtl74s6ci1vkhywp FOREIGN KEY (GpUser_id) REFERENCES GpUser (id), CONSTRAINT FK_qqk29t20hlrhy5v62t5jrxdkk FOREIGN KEY (roles_id) REFERENCES GpAuthority (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO GpUser_GpAuthority VALUES (1,1);*/



USE `saikusecurityibmtest`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: saikusecurityibmtest
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `gpauthority`
--

DROP TABLE IF EXISTS `gpauthority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpauthority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpauthority`
--

LOCK TABLES `gpauthority` WRITE;
/*!40000 ALTER TABLE `gpauthority` DISABLE KEYS */;
INSERT INTO `gpauthority` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `gpauthority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpuser`
--

DROP TABLE IF EXISTS `gpuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpuser` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accestype` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `languagepreference` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `lastaccess` datetime DEFAULT NULL,
  `licenseid` varchar(255) DEFAULT NULL,
  `middleName` varchar(255) DEFAULT NULL,
  `mustresetpassword` varchar(255) DEFAULT NULL,
  `newuser` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `primaryemail` varchar(255) DEFAULT NULL,
  `screenname` varchar(255) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpuser`
--

LOCK TABLES `gpuser` WRITE;
/*!40000 ALTER TABLE `gpuser` DISABLE KEYS */;
INSERT INTO `gpuser` VALUES (1,NULL,'testName',NULL,'test',NULL,NULL,'app',NULL,'\0','12345',NULL,NULL,NULL,NULL,'user'),(2,NULL,'Dhina',NULL,'kar',NULL,NULL,'panner',NULL,'\0','12345',NULL,NULL,NULL,NULL,'admin');
/*!40000 ALTER TABLE `gpuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gpuser_gpauthority`
--

DROP TABLE IF EXISTS `gpuser_gpauthority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gpuser_gpauthority` (
  `GpUser_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`GpUser_id`,`roles_id`),
  UNIQUE KEY `UK_qqk29t20hlrhy5v62t5jrxdkk` (`roles_id`),
  CONSTRAINT `FK_c7kyumqvtqtl74s6ci1vkhywp` FOREIGN KEY (`GpUser_id`) REFERENCES `gpuser` (`id`),
  CONSTRAINT `FK_qqk29t20hlrhy5v62t5jrxdkk` FOREIGN KEY (`roles_id`) REFERENCES `gpauthority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gpuser_gpauthority`
--

LOCK TABLES `gpuser_gpauthority` WRITE;
/*!40000 ALTER TABLE `gpuser_gpauthority` DISABLE KEYS */;
INSERT INTO `gpuser_gpauthority` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `gpuser_gpauthority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-19 18:29:26

