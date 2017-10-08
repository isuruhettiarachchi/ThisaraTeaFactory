-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: testdb
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','admin'),('logintest','login'),('test','test'),('test1','test');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES ('2017-03-01',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-01',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-01',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-01',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-01',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-01',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-01',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-01',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-01',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-01',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-02',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-02',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-02',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-02',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-02',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-02',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-02',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-02',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-02',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-02',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-03',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-03',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-03',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-03',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-03',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-03',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-03',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-03',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-03',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-03',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-04',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-04',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-04',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-04',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-04',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-04',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-04',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-04',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-04',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-04',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-05',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-05',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-05',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-05',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-05',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-05',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-05',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-05',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-05',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-05',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-06',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-06',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-06',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-06',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-06',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-06',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-06',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-06',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-06',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-06',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-07',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-07',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-07',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-07',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-07',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-07',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-07',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-07',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-07',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-07',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-10',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-10',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-10',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-10',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-10',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-10',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-10',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-10',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-10',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-10',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-11',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-11',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-11',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-11',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-11',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-11',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-11',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-11',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-11',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-11',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-12',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-12',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-12',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-12',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-12',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-12',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-12',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-12',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-12',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-12',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-13',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-13',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-13',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-13',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-13',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-13',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-13',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-13',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-13',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-13',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-15',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-15',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-15',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-15',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-15',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-15',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-15',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-15',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-15',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-15',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-16',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-16',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-16',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-16',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-16',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-16',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-16',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-16',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-16',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-16',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-17',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-17',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-17',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-17',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-17',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-17',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-17',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-17',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-17',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-17',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-18',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-18',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-18',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-18',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-18',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-18',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-18',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-18',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-18',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-18',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-23',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-23',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-23',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-23',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-23',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-23',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-23',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-23',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-23',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-23',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-24',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-24',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-24',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-24',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-24',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-24',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-24',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-24',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-24',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-24',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-25',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-25',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-25',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-25',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-25',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-25',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-25',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-25',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-25',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-25',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-26',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-26',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-26',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-26',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-26',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-26',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-26',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-26',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-26',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-26',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-27',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-27',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-27',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-27',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-27',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-27',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-27',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-27',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-27',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-27',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-03-29',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-03-29',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-03-29',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-03-29',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-03-29',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-03-29',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-03-29',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-03-29',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-03-29',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-03-29',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-02',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-02',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-02',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-02',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-02',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-02',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-02',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-02',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-02',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-02',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-03',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-03',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-03',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-03',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-03',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-03',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-03',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-03',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-03',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-03',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-04',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-04',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-04',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-04',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-04',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-04',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-04',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-04',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-04',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-04',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-05',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-05',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-05',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-05',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-05',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-05',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-05',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-05',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-05',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-05',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-06',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-06',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-06',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-06',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-06',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-06',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-06',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-06',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-06',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-06',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-07',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-07',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-07',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-07',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-07',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-07',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-07',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-07',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-07',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-07',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-10',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-10',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-10',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-10',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-10',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-10',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-10',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-10',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-10',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-10',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-11',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-11',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-11',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-11',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-11',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-11',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-11',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-11',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-11',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-11',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-12',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-12',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-12',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-12',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-12',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-12',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-12',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-12',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-12',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-12',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-13',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-13',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-13',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-13',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-13',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-13',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-13',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-13',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-13',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-13',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-15',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-15',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-15',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-15',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-15',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-15',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-15',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-15',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-15',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-15',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-16',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-16',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-16',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-16',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-16',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-16',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-16',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-16',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-16',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-16',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-17',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-17',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-17',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-17',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-17',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-17',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-17',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-17',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-17',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-17',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-18',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-18',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-18',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-18',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-18',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-18',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-18',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-18',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-18',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-18',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-23',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-23',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-23',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-23',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-23',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-23',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-23',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-23',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-23',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-23',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-24',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-24',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-24',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-24',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-24',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-24',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-24',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-24',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-24',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-24',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-25',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-25',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-25',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-25',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-25',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-25',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-25',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-25',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-25',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-25',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-26',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-26',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-26',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-26',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-26',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-26',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-26',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-26',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-26',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-26',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-27',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-27',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-27',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-27',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-27',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-27',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-27',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-27',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-27',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-27',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-04-29',1,'Janith Herath','07:00:00','17:00:00',8,2),('2017-04-29',2,'Ravindu Akalank','07:00:00','17:00:00',8,2),('2017-04-29',3,'Kaushala Karunarathne','07:00:00','17:00:00',8,2),('2017-04-29',4,'Shamodh Hassim','07:00:00','17:00:00',8,2),('2017-04-29',5,'Mihitha Hansani','07:00:00','17:00:00',8,2),('2017-04-29',6,'Dilith Erange','07:00:00','17:00:00',8,2),('2017-04-29',7,'Lushan Sepala','07:00:00','17:00:00',8,2),('2017-04-29',9,'Nipuni Deshani','07:00:00','17:00:00',8,2),('2017-04-29',10,'Rajith Dhanushka','07:00:00','17:00:00',8,2),('2017-04-29',12,'Sanath Jayasooriya','07:00:00','17:00:00',8,2),('2017-05-07',1,'Janith Herath','13:06:52','13:06:55',0,0),('2017-05-07',2,'Ravindu Akalank','13:07:05',NULL,0,0),('2017-05-07',3,'Kaushala Karunarathne',NULL,NULL,0,0),('2017-05-07',4,'Shamodh Hassim',NULL,NULL,0,0),('2017-05-07',5,'Mihitha Hansani',NULL,NULL,0,0),('2017-05-07',6,'Dilith Erange',NULL,NULL,0,0),('2017-05-07',7,'Lushan Sepala',NULL,NULL,0,0),('2017-05-07',9,'Nipuni Deshani',NULL,NULL,0,0),('2017-05-07',10,'Rajith Dhanushka',NULL,NULL,0,0),('2017-05-07',12,'Sanath Jayasooriya',NULL,NULL,0,0),('2017-05-08',1,'Janith Herath','13:52:42','14:48:17',1,0),('2017-05-08',2,'Ravindu Akalank','14:48:19','14:48:23',0,0),('2017-05-08',3,'Kaushala Karunarathne','16:59:27','16:59:30',0,0),('2017-05-08',4,'Shamodh Hassim','18:18:51','18:18:52',0,0),('2017-05-08',5,'Mihitha Hansani','18:18:57','18:18:59',0,0),('2017-05-08',6,'Dilith Erange',NULL,NULL,0,0),('2017-05-08',7,'Lushan Sepala','16:59:48','16:59:49',0,0),('2017-05-08',9,'Nipuni Deshani',NULL,NULL,0,0),('2017-05-08',10,'Rajith Dhanushka',NULL,NULL,0,0),('2017-05-08',12,'Sanath Jayasooriya',NULL,NULL,0,0),('2017-05-09',1,'Janith Herath',NULL,NULL,0,0),('2017-05-09',2,'Ravindu Akalank',NULL,NULL,0,0),('2017-05-09',3,'Kaushala Karunarathne',NULL,NULL,0,0),('2017-05-09',4,'Shamodh Hassim',NULL,NULL,0,0),('2017-05-09',5,'Mihitha Hansani',NULL,NULL,0,0),('2017-05-09',6,'Dilith Erange',NULL,NULL,0,0),('2017-05-09',7,'Lushan Sepala',NULL,NULL,0,0),('2017-05-09',9,'Nipuni Deshani',NULL,NULL,0,0),('2017-05-09',12,'Sanath Jayasooriya',NULL,NULL,0,0),('2017-05-09',13,'Saman Kumara',NULL,NULL,0,0),('2017-05-09',14,'Kamal',NULL,NULL,0,0),('2017-05-09',15,'Saman Kumara',NULL,NULL,0,0),('2017-05-09',17,'Chamara Karunarathne',NULL,NULL,0,0);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `bankexpenses`
--

LOCK TABLES `bankexpenses` WRITE;
/*!40000 ALTER TABLE `bankexpenses` DISABLE KEYS */;
INSERT INTO `bankexpenses` VALUES (1,66,'BOC','kjj',1000,'2017-05-07'),(2,45,'CM','jjj',100,'2017-05-08'),(3,478596,'10023','jhyff',14578,'2017-05-08');
/*!40000 ALTER TABLE `bankexpenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,3,600),(1,3,6,2460),(1,4,3,570),(2,2,2,300),(2,3,2,820),(2,4,2,380),(3,1,4,800),(3,2,4,600),(3,4,4,760),(4,1,2,400),(4,2,3,450),(5,2,3,450),(5,3,3,1230),(5,4,3,570),(6,2,1,150),(7,5,4,1480),(8,2,1,150),(9,2,1,150);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `collectionturn`
--

LOCK TABLES `collectionturn` WRITE;
/*!40000 ALTER TABLE `collectionturn` DISABLE KEYS */;
INSERT INTO `collectionturn` VALUES (1,'2017-05-07',1,2,10,1,1,1,7,'Not Used',NULL,NULL),(2,'2017-05-07',2,2,12,1,0,1,10,'Used',NULL,NULL),(3,'2017-05-07',3,2,16,1,2,1,12,'Used',NULL,NULL),(4,'2017-05-07',4,3,16,2,1,1,12,'Not Used',NULL,NULL),(5,'2017-05-07',5,4,20,2,2,2,14,'Used',NULL,NULL),(6,'2017-05-08',6,5,18,1,1,1,15,'Not Used',NULL,NULL),(7,'2017-05-08',8,2,18,2,1,2,13,'Not Used',NULL,NULL);
/*!40000 ALTER TABLE `collectionturn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `dailyproduct`
--

LOCK TABLES `dailyproduct` WRITE;
/*!40000 ALTER TABLE `dailyproduct` DISABLE KEYS */;
INSERT INTO `dailyproduct` VALUES (1,'2017-05-07',14,5.64,40.285714285714285,6,0.36,1,1,1,3),(3,'2017-05-08',55,8.46,15.381818181818183,9,0.54,2,2,3,2);
/*!40000 ALTER TABLE `dailyproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `designation`
--

LOCK TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;
INSERT INTO `designation` VALUES ('Assistant Driver',1350,210.94),('Assistant Manager',1700,265.63),('Driver',1500,234.38),('Laborer',1500,234.38),('Manager',2000,312.5),('Staff Assistance',1300,203.13);
/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'Kasun Perera','Galle','Main','1548547865','689587745v',714588547),(2,'Ranga Supun','Ambalangoda','Assistant','8948547865','719587845v',724589654),(3,'Amal Fernando','Matara','Main','8754896541','845798856v',785475895),(4,'Kamal Fernando','Matara','Assistant','4784896541','655798856v',717875895),(5,'kasun','col','Main','56898754','954785566v',714589654),(6,'Buddika Silva','Colombo','Main','5689875684','750742255v',774545478);
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `electricity`
--

LOCK TABLES `electricity` WRITE;
/*!40000 ALTER TABLE `electricity` DISABLE KEYS */;
INSERT INTO `electricity` VALUES (1,14,100,'2017-05-07','Paid'),(2,45,100,'2017-05-19','Paid'),(5,45,100,'2017-05-19','Paid'),(6,100,123,'2017-05-10','Paid');
/*!40000 ALTER TABLE `electricity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'910123145V','Janith Herath','Manager','Colombo','male','1991-07-13','0789741415','2017-05-07',NULL,'2017-05-07'),(2,'921456741V','Ravindu Akalank','Assistant Manager','Gampaha','male','1992-01-26','0712412521','2017-01-01',NULL,'2017-01-01'),(3,'892145654V','Kaushala Karunarathne','Assistant Manager','Kuruengala','female','1989-05-31','0741145147','2017-01-03',NULL,'2017-01-03'),(4,'910123456V','Shamodh Hassim','Driver','Mathara','male','1991-07-19','0789521854','2017-01-29',NULL,'2017-01-29'),(5,'750142142V','Mihitha Hansani','Manager','Galle','male','1975-11-22','0714561231','2016-09-26',NULL,'2016-09-26'),(6,'948741562V','Dilith Erange','Laborer','Piliyandala','male','1994-07-11','0713159753','2016-08-16',NULL,'2016-08-16'),(7,'920123456V','Lushan Sepala','Laborer','Rathnapura','male','1992-10-18','0741456123','2016-07-26',NULL,'2016-07-26'),(8,'857456456V','Thenuka Kulathunga','Laborer','Anuradhapura','male','1985-05-10','0741456854','2016-10-05','2017-03-14','2016-10-05'),(9,'885741456V','Nipuni Deshani','Laborer','Galle','male','1988-09-12','0741321547','2016-11-15',NULL,'2016-11-15'),(10,'874854555V','Rajith Dhanushka','Laborer','Panadura','male','1987-08-22','0741456412','2016-10-11','2017-05-08','2016-10-11'),(11,'957874514V','Ravindu Dilshan','Driver','Galle','male','1995-05-16','0712414454','2017-01-23','2017-05-07','2017-01-23'),(12,'658741456V','Sanath Jayasooriya','Driver','Mathara','male','1965-06-27','0711456652','2016-11-15',NULL,'2016-11-15'),(13,'974123456V','Saman Kumara','Driver','Galle	','male','1990-09-12','0712456789','2017-05-08',NULL,'2017-05-09'),(14,'940123456V','Kamal','Manager','Galle','male','1994-08-16','0712123456','2017-05-08',NULL,'2017-05-08'),(15,'890456123V','Saman Kumara','Assistant Manager','Mathara','male','1989-05-08','0714567899','2017-05-08',NULL,'2017-05-08'),(17,'940123789V','Chamara Karunarathne','Driver','Galle','male','1994-05-08','0714789412','2017-05-08',NULL,'2017-05-08');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `finalization`
--

LOCK TABLES `finalization` WRITE;
/*!40000 ALTER TABLE `finalization` DISABLE KEYS */;
INSERT INTO `finalization` VALUES ('2017-05-08',879930,20070,900000,'March',7);
/*!40000 ALTER TABLE `finalization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `finance`
--

LOCK TABLES `finance` WRITE;
/*!40000 ALTER TABLE `finance` DISABLE KEYS */;
INSERT INTO `finance` VALUES (1,'Employee','2017-05-17',1500),(3,'TransportTurn','2017-05-07',2750),(4,'TransportTurn','2017-03-06',1750),(6,'TransportTurn','2017-04-18',3850),(8,'Asset','2017-05-07',100),(9,'Asset','2017-05-07',140),(10,'Asset','2017-05-07',1230),(11,'Asset','2017-05-07',100),(12,'Asset','2017-05-07',1000),(13,'Supplier','2017-05-07',500),(14,'Supplier','2017-05-07',750),(15,'Supplier','2017-05-07',800),(16,'Supplier','2017-05-07',1000),(20,'Supplier','2017-05-07',1150),(21,'Sales','2017-05-08',684390),(22,'Asset','2017-05-08',100),(23,'Wastage','2017-03-31',35490),(24,'Employee','2017-04-30',338171),(25,'TransportTurn','2017-05-08',4750),(26,'TransportTurn','2017-05-08',251),(27,'TransportTurn','2017-05-08',251),(28,'Supplier','2017-05-08',1200),(29,'Asset','2017-05-08',100),(30,'Asset','2017-05-08',120),(31,'Asset','2017-05-08',100),(32,'Asset','2017-05-08',287),(33,'Sales','2017-05-08',100),(34,'Employee','2017-05-08',0),(35,'Employee','2017-05-08',0),(36,'Employee','2017-03-31',404054),(37,'Employee','2017-03-31',404054),(38,'Employee','2017-04-30',0);
/*!40000 ALTER TABLE `finance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `firewood`
--

LOCK TABLES `firewood` WRITE;
/*!40000 ALTER TABLE `firewood` DISABLE KEYS */;
INSERT INTO `firewood` VALUES (1,'kandalanda,paradise,kuruwita','2017-05-07',140),(100,'hjf','2017-05-08',120),(144,'kjhy','2017-05-08',1236);
/*!40000 ALTER TABLE `firewood` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `grocery`
--

LOCK TABLES `grocery` WRITE;
/*!40000 ALTER TABLE `grocery` DISABLE KEYS */;
INSERT INTO `grocery` VALUES (1,'2017-05-07',1,NULL,'Employee',120,'Thisara',3,360,NULL),(2,'2017-05-07',NULL,1,'Supplier',120,'Thisara',5,600,NULL),(3,'2017-05-07',2,NULL,'Employee',60,'Thisara',5,300,NULL),(4,'2017-05-07',NULL,2,'Supplier',120,'Thisara A',30,3600,NULL),(5,'2017-05-07',5,NULL,'Employee',120,'Thisara',2,240,NULL),(6,'2017-05-08',1,NULL,'Employee',234,'Thisara',44,10296,NULL),(7,'2017-05-08',NULL,1,'Supplier',78,'Thisara',560,43680,NULL),(8,'2017-05-08',1,NULL,'Employee',120,'Thisara',45,5400,NULL),(9,'2017-05-08',NULL,2,'Supplier',45,'Thisara A',30,1350,NULL),(10,'2017-05-08',4,NULL,'Employee',50,'Thisara',12,600,NULL);
/*!40000 ALTER TABLE `grocery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `income`
--

LOCK TABLES `income` WRITE;
/*!40000 ALTER TABLE `income` DISABLE KEYS */;
INSERT INTO `income` VALUES ('2016-10-12',1,0,'October',2016,0,4830,0,1725,11500,138,18193),('2016-12-08',2,1,'October',2016,73.54,11760,36.77,4216.55,28000,336,44422.86),('2017-01-02',3,0,'December',2016,0,9450,0,2362.5,15750,270,27832.5),('2017-01-26',4,1,'December',2016,73.54,4935,36.77,1426.55,9400,141,16012.86),('2017-05-07',7,0,'June',2017,0,9450,0,1350,9000,270,20070);
/*!40000 ALTER TABLE `income` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `maintenancecost`
--

LOCK TABLES `maintenancecost` WRITE;
/*!40000 ALTER TABLE `maintenancecost` DISABLE KEYS */;
INSERT INTO `maintenancecost` VALUES (2017,4,7500),(2017,5,1500),(2017,4,7500),(2017,5,1500),(2017,4,7500),(2017,5,1500),(2017,4,7500),(2017,5,1500),(2017,4,7500),(2017,5,12000);
/*!40000 ALTER TABLE `maintenancecost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `manufactureitem`
--

LOCK TABLES `manufactureitem` WRITE;
/*!40000 ALTER TABLE `manufactureitem` DISABLE KEYS */;
INSERT INTO `manufactureitem` VALUES (1,1,'2017-05-07','FertA','2017-05-24',3),(2,1,'2017-05-07','TeaA','2017-05-31',3),(3,5,'2017-05-07','TeaB','2017-06-10',1),(5,1,'2017-05-07','TeaC','2017-05-26',4),(6,1,'2017-05-08','FertC','2017-05-24',3),(7,2,'2017-05-08','FertA','2017-06-01',2),(8,1,'2017-05-08','FertC','2017-05-25',3);
/*!40000 ALTER TABLE `manufactureitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `monthlybulk`
--

LOCK TABLES `monthlybulk` WRITE;
/*!40000 ALTER TABLE `monthlybulk` DISABLE KEYS */;
INSERT INTO `monthlybulk` VALUES (1,1,1,0,7,7,'2017-05-07'),(2,2,1,10,0,10,'2017-05-07'),(3,3,1,12,0,12,'2017-05-07'),(4,4,1,0,12,12,'2017-05-07'),(6,5,1,14,0,14,'2017-05-07'),(7,6,1,0,15,15,'2017-05-08'),(8,8,1,0,13,13,'2017-05-08');
/*!40000 ALTER TABLE `monthlybulk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nominee`
--

LOCK TABLES `nominee` WRITE;
/*!40000 ALTER TABLE `nominee` DISABLE KEYS */;
INSERT INTO `nominee` VALUES ('Dilith Erange',13,13,2500,'Son','Galle'),('Isuru',14,18,1800,'SOn','Galle'),('Sisira Dhramasena',15,63,25000,'Father','Galle');
/*!40000 ALTER TABLE `nominee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `otherexpenses`
--

LOCK TABLES `otherexpenses` WRITE;
/*!40000 ALTER TABLE `otherexpenses` DISABLE KEYS */;
INSERT INTO `otherexpenses` VALUES (1,100,0,0,'2017-05-07'),(100,120,145,22,'2017-05-08'),(145,14587,1230,1000,'2017-05-08');
/*!40000 ALTER TABLE `otherexpenses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `packingdetails`
--

LOCK TABLES `packingdetails` WRITE;
/*!40000 ALTER TABLE `packingdetails` DISABLE KEYS */;
INSERT INTO `packingdetails` VALUES (1,'BOPA','Thisara A',10,'Full',50,5,55,1,'SPPF','2017-05-07',5,NULL),(2,'FBOP','Thisara',20,'Half',120,6,123,2,'SPPF','2017-05-07',3,NULL),(3,'FBOP 1','Thisara A',15,'Full',30,2,34,3,'SPPF','2017-05-07',4,NULL),(4,'OPNO1','Thisara',10,'Half',40,4,43,4,'SPPF','2017-05-07',3,NULL),(5,'OPA','Thisara A',12,'Full',72,6,77,5,'SPPF','2017-05-07',5,NULL),(6,'OP','Thisara A',45,'Full',3150,70,3153,5,'SPPF','2017-05-08',3,NULL),(7,'BOPF','Thisara A',6,'Half',138,23,140,2,'SPPF','2017-05-08',2,NULL);
/*!40000 ALTER TABLE `packingdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'2017-05-07','Electricity',100,NULL,1,NULL,NULL,NULL),(2,'2017-05-07','FireWood',140,NULL,NULL,NULL,1,NULL),(3,'2017-05-07','Telephone',1230,3,NULL,NULL,NULL,NULL),(4,'2017-05-07','Other',100,NULL,NULL,NULL,NULL,1),(5,'2017-05-07','BankExpenses',1000,NULL,NULL,66,NULL,NULL),(6,'2017-05-08','BankExpenses',100,NULL,NULL,45,NULL,NULL),(7,'2017-05-08','Electricity',100,NULL,2,NULL,NULL,NULL),(8,'2017-05-08','FireWood',120,NULL,NULL,NULL,100,NULL),(9,'2017-05-08','Telephone',100,5,NULL,NULL,NULL,NULL),(10,'2017-05-08','Other',287,NULL,NULL,NULL,NULL,100),(11,'2017-05-08','Electricity',100,NULL,5,NULL,NULL,NULL),(12,'2017-05-08','Electricity',123,NULL,6,NULL,NULL,NULL),(13,'2017-05-08','FireWood',1236,NULL,NULL,NULL,144,NULL),(14,'2017-05-08','Telephone',100,145,NULL,NULL,NULL,NULL),(15,'2017-05-08','Other',16817,NULL,NULL,NULL,NULL,145),(16,'2017-05-08','BankExpenses',14578,NULL,NULL,478596,NULL,NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `quality`
--

LOCK TABLES `quality` WRITE;
/*!40000 ALTER TABLE `quality` DISABLE KEYS */;
INSERT INTO `quality` VALUES (1,1,'Thisara A',5,1,1,2),(2,1,'Thisara',5,1,2,2),(4,3,'Thisara A',8,1,3,4),(5,3,'Thisara',8,1,3,4);
/*!40000 ALTER TABLE `quality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `returnsale`
--

LOCK TABLES `returnsale` WRITE;
/*!40000 ALTER TABLE `returnsale` DISABLE KEYS */;
INSERT INTO `returnsale` VALUES ('2016-08-03',1,1,'Low quality',5000),('2016-10-05',2,2,'Transport reasons',4000),('2017-01-03',3,3,'Packing Issues',7000),('2017-01-25',4,4,'Transport reasons',1560),('2017-04-18',5,5,'low quality',5600);
/*!40000 ALTER TABLE `returnsale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `salary`
--

LOCK TABLES `salary` WRITE;
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` VALUES (1,'Janith Herath','2017-03-01','2017-03-31',21,42,175,4410,1653.75,6615,49236.25),(1,'Janith Herath','2017-04-01','2017-04-30',20,40,175,4200,1575,6300,46900),(2,'Ravindu Akalank','2017-03-01','2017-03-31',21,42,175,3748.52,1405.7,5622.78,41877.25),(2,'Ravindu Akalank','2017-04-01','2017-04-30',20,40,175,3570.02,1338.76,5355.03,39891.43),(3,'Kaushala Karunarathne','2017-03-01','2017-03-31',21,42,175,3748.52,1405.7,5622.78,41877.25),(3,'Kaushala Karunarathne','2017-04-01','2017-04-30',20,40,175,3570.02,1338.76,5355.03,39891.43),(4,'Shamodh Hassim','2017-03-01','2017-03-31',21,42,175,3307.52,1240.32,4961.28,36971.13),(4,'Shamodh Hassim','2017-04-01','2017-04-30',20,40,175,3150.02,1181.26,4725.03,35218.93),(5,'Mihitha Hansani','2017-03-01','2017-03-31',21,42,175,4410,1653.75,6615,49236.25),(5,'Mihitha Hansani','2017-04-01','2017-04-30',20,40,175,4200,1575,6300,46900),(6,'Dilith Erange','2017-03-01','2017-03-31',21,42,175,3307.52,1240.32,4961.28,36971.13),(6,'Dilith Erange','2017-04-01','2017-04-30',20,40,175,3150.02,1181.26,4725.03,35218.93),(7,'Lushan Sepala','2017-03-01','2017-03-31',21,42,175,3307.52,1240.32,4961.28,36971.13),(7,'Lushan Sepala','2017-04-01','2017-04-30',20,40,175,3150.02,1181.26,4725.03,35218.93),(9,'Nipuni Deshani','2017-03-01','2017-03-31',21,42,175,3307.52,1240.32,4961.28,36971.13),(9,'Nipuni Deshani','2017-04-01','2017-04-30',20,40,175,3150.02,1181.26,4725.03,35218.93),(10,'Rajith Dhanushka','2017-03-01','2017-03-31',21,42,175,3307.52,1240.32,4961.28,36971.13),(10,'Rajith Dhanushka','2017-04-01','2017-04-30',20,40,175,3150.02,1181.26,4725.03,35218.93),(12,'Sanath Jayasooriya','2017-03-01','2017-03-31',21,42,175,3307.52,1240.32,4961.28,36971.13),(12,'Sanath Jayasooriya','2017-04-01','2017-04-30',20,40,175,3150.02,1181.26,4725.03,35218.93);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `salaryevents`
--

LOCK TABLES `salaryevents` WRITE;
/*!40000 ALTER TABLE `salaryevents` DISABLE KEYS */;
INSERT INTO `salaryevents` VALUES (1,'2017-03-31',354991.78),(2,'2017-04-30',338170.72),(3,'2017-05-08',0),(4,'2017-05-08',0),(5,'2017-03-31',404053.78),(6,'2017-03-31',404053.78),(7,'2017-04-30',0),(8,'2017-04-30',384896.43999999994);
/*!40000 ALTER TABLE `salaryevents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES (1,'Employee','2017-05-07',0.15,3630,1462.5,6726),(2,'Supplier','2017-05-07',0.1,1500,292,2456),(3,'Employee','2017-05-07',0.15,3660,1645,8098),(4,'Customer','2017-05-07',0,4510,1645,15693),(5,'Supplier','2017-05-07',0.1,6760,3436,25266),(6,'Customer','2017-05-08',0,150,0,150),(7,'Supplier','2017-05-08',0.1,1480,148,1332),(8,'Supplier','2017-05-08',0.1,150,15,135),(9,'Supplier','2017-05-08',0.1,150,15,135);
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (1,1,'Kamal  De Silva\nGalle','BOPA',10,500,2300,100,2300,1150000),(2,2,'Nimal Perera\nKandy','FBOP',30,500,5600,150,5600,2800000),(3,3,'Nuwan Vithanage\nMathara','FBOP 1',10,350,4500,50,4500,1575000),(4,4,'Kumara De Silva\nGalle','OPNO1',20,400,2350,100,2350,940000),(5,5,'Sunil Perera\nAlpitiya','OPA',20,200,3500,50,3500,700000),(6,3,'Saman\nMathara','OP',20,300,5600,10,5600,1680000),(7,7,'Kasun Samaraweera\nGalle','BOPF',15,200,4500,100,4500,900000);
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES (1,'Waste Tea','BOPF',50,20,200),(2,'Fertilizer','Cock Fert',100,25,150),(3,'Waste Tea','SF',50,31,410),(4,'Fertilzer','Sect Fert',50,29,190),(5,'Fertilizer','Mal Fert',100,40,370),(8,'Fertilzer','BOPA',200,50,200),(9,'Fertilizer','BOPF',50,50,230);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Nimal Ariyarathne','Galle','SamanState','Galle',12,'910123456v','0714567891'),(2,'Kamal Perera','No 67,Kaduwela','High State','Kaduwela',15,'675487987v','0773454654'),(3,'Sunil Herath','No 76,Malabe','Low State','Kaduwela',20,'564366543v','0774576854'),(4,'Lalith Silva','No 34,Mathara','Middle State','Mathara',15,'456574354v','0774576223'),(5,'Amal Withnage','No 45,Rathnapura','Read State','Rathnapura',20,'564756453v','0712534873'),(6,'Asansa Wickramarathne','Galle','Flower State','Ahungalla',15,'562221752V','0757463844'),(7,'Saman','No 23,Mathara','High Garden','Mathara',16,'946755876v','0771325876'),(8,'Nihal','No89,Mahawa','Low Garden','Mahawa',12,'567488764v','0774563487');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `supplierpayment`
--

LOCK TABLES `supplierpayment` WRITE;
/*!40000 ALTER TABLE `supplierpayment` DISABLE KEYS */;
INSERT INTO `supplierpayment` VALUES (1,'2017-05-07',1,7,90,630,7,637,50,37,50,500,NULL),(2,'2017-05-07',2,10,92,920,0,920,100,20,50,750,NULL),(3,'2017-05-07',3,12,90,1080,0,1080,100,80,100,800,NULL),(4,'2017-05-07',4,12,95,1140,12,1152,50,52,50,1000,NULL),(8,'2017-05-07',5,14,90,1260,0,1260,33,33,4,1190,NULL),(9,'2017-05-08',6,15,90,1350,15,1365,50,65,45,1205,NULL),(10,'2017-05-08',8,13,90,1170,13,1183,10,83,0,1090,NULL);
/*!40000 ALTER TABLE `supplierpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `telephone`
--

LOCK TABLES `telephone` WRITE;
/*!40000 ALTER TABLE `telephone` DISABLE KEYS */;
INSERT INTO `telephone` VALUES (3,'0714798071',1230,'2017-05-17','Paid'),(5,'0714798071',100,'2017-05-12','Paid'),(145,'0712220966',100,'2017-05-18','Paid');
/*!40000 ALTER TABLE `telephone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `totaltransportcost`
--

LOCK TABLES `totaltransportcost` WRITE;
/*!40000 ALTER TABLE `totaltransportcost` DISABLE KEYS */;
INSERT INTO `totaltransportcost` VALUES (2017,3,1500,250,1750),(2017,4,6100,500,6600),(2017,5,5000,730,5730),(2017,3,1500,250,1750),(2017,4,6100,500,6600),(2017,5,5000,730,5730),(2017,3,1500,250,1750),(2017,4,6100,500,6600),(2017,5,9500,980,10480),(2017,3,1500,250,1750),(2017,4,6100,500,6600),(2017,5,12500,1122,13622),(2017,3,1500,250,1750),(2017,4,6100,500,6600),(2017,5,12500,1122,13622);
/*!40000 ALTER TABLE `totaltransportcost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,NULL,'Employee','2017-05-17',1500),(3,2,'TransportTurn','2017-05-07',2750),(4,3,'TransportTurn','2017-03-06',1750),(6,5,'TransportTurn','2017-04-18',3850),(8,1,'Asset','2017-05-07',100),(9,2,'Asset','2017-05-07',140),(10,3,'Asset','2017-05-07',1230),(11,4,'Asset','2017-05-07',100),(12,5,'Asset','2017-05-07',1000),(13,1,'Supplier','2017-05-07',500),(14,2,'Supplier','2017-05-07',750),(15,3,'Supplier','2017-05-07',800),(16,4,'Supplier','2017-05-07',1000),(20,8,'Supplier','2017-05-07',1150),(21,10,'Sales','2017-05-08',684390),(22,6,'Asset','2017-05-08',100),(23,1,'Wastage','2017-03-31',35490),(24,2,'Employee','2017-04-30',338170.72),(25,6,'TransportTurn','2017-05-08',4750),(26,7,'TransportTurn','2017-05-08',251),(27,8,'TransportTurn','2017-05-08',251),(28,9,'Supplier','2017-05-08',1200),(29,7,'Asset','2017-05-08',100),(30,8,'Asset','2017-05-08',120),(31,9,'Asset','2017-05-08',100),(32,10,'Asset','2017-05-08',287),(33,11,'Sales','2017-05-08',100),(34,3,'Employee','2017-05-08',0),(35,4,'Employee','2017-05-08',0),(36,5,'Employee','2017-03-31',404053.78),(37,6,'Employee','2017-03-31',404053.78),(38,7,'Employee','2017-04-30',0),(40,9,'TransportTurn','2017-05-08',2640),(41,10,'Supplier','2017-05-08',1000),(42,10,'Sales','2017-05-08',879930),(43,12,'Asset','2017-05-08',123),(44,13,'Asset','2017-05-08',1236),(45,14,'Asset','2017-05-08',100),(46,15,'Asset','2017-05-08',16817),(47,16,'Asset','2017-05-08',14578);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `turn`
--

LOCK TABLES `turn` WRITE;
/*!40000 ALTER TABLE `turn` DISABLE KEYS */;
INSERT INTO `turn` VALUES (1,1,4,2,'2017-05-05','Supplying','Factory to Colmbo',84,2500,500),(2,1,2,2,'2017-04-18','Supplying','Factory to Colombo',84,2500,250),(3,1,2,3,'2017-03-06','Collecting','Factory to Galle',20,1500,250),(4,1,4,4,'2017-05-07','Other','Factory to Matar',56,2500,230),(5,3,2,3,'2017-04-18','Collecting','Factory to Galle',20,3600,250),(6,1,2,0,'2017-05-08','Supplying','Factory to colombo',90,4500,250),(7,1,4,2,'2017-05-08','Collecting','f to c',500,250,1),(8,1,4,2,'2017-05-08','Collecting','f to c',500,250,1),(9,3,4,2,'2017-05-08','Supplying','Factory to Colombo',95,2500,140);
/*!40000 ALTER TABLE `turn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'Cab','CAB-9876',24587,3500),(2,'Lorry','LR-8987',947856,10066),(3,'Container','CC-1245',124569,17580),(5,'Lorry','Wp-8954',45000,50);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `vehiclemaintenance`
--

LOCK TABLES `vehiclemaintenance` WRITE;
/*!40000 ALTER TABLE `vehiclemaintenance` DISABLE KEYS */;
INSERT INTO `vehiclemaintenance` VALUES (1,'2017-05-07','tyre',1500,4),(2,'2017-04-12','Engine Repair',7500,1),(3,'2017-05-08','engine	',10500,5);
/*!40000 ALTER TABLE `vehiclemaintenance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `waste`
--

LOCK TABLES `waste` WRITE;
/*!40000 ALTER TABLE `waste` DISABLE KEYS */;
INSERT INTO `waste` VALUES (1,'2017-05-07','Maceration','Pekoe dust','inorganic',480),(2,'2017-05-07','Maceration','Gunny Bags','inorganic',200),(3,'2017-05-07','Fermentation','Green Leaf','organic',450),(4,'2017-05-07','Leaf Collection','Green Leaf','organic',500),(6,'2017-05-08','Maceration','Gunny Bags','inorganic',150),(7,'2017-05-08','Withering','Gunny Bags','inorganic',120),(8,'2017-05-07','Withering','Metal Chips','inorganic',300),(9,'2017-05-08','Withering','Metal Chips','inorganic',180),(10,'2017-05-08','Fermentation','Gunny Bags','inorganic',200),(11,'2017-05-08','Withering','Gunny Bags','inorganic',200);
/*!40000 ALTER TABLE `waste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'testdb'
--

--
-- Dumping routines for database 'testdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-09 14:58:12
