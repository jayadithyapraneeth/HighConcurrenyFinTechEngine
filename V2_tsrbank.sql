-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: tsrbank
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admindetails`
--

DROP TABLE IF EXISTS `admindetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admindetails` (
  `EmpId` varchar(20) NOT NULL,
  `Password` varchar(10) DEFAULT NULL,
  `FirstName` varchar(20) DEFAULT NULL,
  `MiddleName` varchar(20) DEFAULT NULL,
  `LastName` varchar(20) DEFAULT NULL,
  `PhoneNo` decimal(10,0) NOT NULL,
  PRIMARY KEY (`EmpId`),
  UNIQUE KEY `PhoneNo` (`PhoneNo`),
  UNIQUE KEY `PhoneNo_2` (`PhoneNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admindetails`
--

LOCK TABLES `admindetails` WRITE;
/*!40000 ALTER TABLE `admindetails` DISABLE KEYS */;
INSERT INTO `admindetails` VALUES ('O201051','0115','Jayadithya','Praneeth','Machavarapu',9553587305),('O210250','1808','Mayif','','Shaik',9392757489),('O210855','0855','Khadeer','','Shaik',8142551668);
/*!40000 ALTER TABLE `admindetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `adminlogin`
--

DROP TABLE IF EXISTS `adminlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adminlogin` (
  `EmpId` varchar(20) NOT NULL,
  `Password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`EmpId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminlogin`
--

LOCK TABLES `adminlogin` WRITE;
/*!40000 ALTER TABLE `adminlogin` DISABLE KEYS */;
INSERT INTO `adminlogin` VALUES ('O201051','0115'),('O210250','1808'),('O210855','0855');
/*!40000 ALTER TABLE `adminlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autoloanapplication`
--

DROP TABLE IF EXISTS `autoloanapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autoloanapplication` (
  `LoanId` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `FullName` varchar(60) NOT NULL,
  `AccountNo` decimal(16,0) NOT NULL,
  `CarModel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CarPrice` int NOT NULL,
  `Term` int NOT NULL,
  `EmploymentStatus` varchar(20) NOT NULL,
  `PhoneNo` decimal(10,0) NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`LoanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autoloanapplication`
--

LOCK TABLES `autoloanapplication` WRITE;
/*!40000 ALTER TABLE `autoloanapplication` DISABLE KEYS */;
INSERT INTO `autoloanapplication` VALUES ('2222123456Suz','Machavarapu Jayadithya Praneeth',1234567890123456,'Suziki WagonR',800000,24,'employed',0,'2025-07-09 23:50:41');
/*!40000 ALTER TABLE `autoloanapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerdetails`
--

DROP TABLE IF EXISTS `customerdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerdetails` (
  `AccountNo` decimal(16,0) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `FirstName` varchar(20) NOT NULL,
  `MiddleName` varchar(20) DEFAULT '',
  `LastName` varchar(20) NOT NULL,
  `Gender` varchar(15) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `PhoneNo` decimal(10,0) DEFAULT NULL,
  `Nationality` varchar(20) NOT NULL,
  `State` varchar(20) NOT NULL,
  `District` varchar(20) NOT NULL,
  `Village` varchar(20) DEFAULT NULL,
  `Zipcode` varchar(10) NOT NULL,
  `AadharNo` decimal(12,0) NOT NULL,
  `EmailAddress` varchar(50) DEFAULT NULL,
  `YearlyIncome` mediumint NOT NULL,
  `AccountBalance` bigint DEFAULT NULL,
  `DateCreated` datetime DEFAULT NULL,
  `ProfilePicture` blob NOT NULL,
  PRIMARY KEY (`AccountNo`),
  UNIQUE KEY `AadharNo` (`AadharNo`),
  UNIQUE KEY `AadharNo_2` (`AadharNo`),
  UNIQUE KEY `PhoneNo` (`PhoneNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerdetails`
--

LOCK TABLES `customerdetails` WRITE;
/*!40000 ALTER TABLE `customerdetails` DISABLE KEYS */;
INSERT INTO `customerdetails` VALUES (1111111111111646,'1111','vamsi','krishna','chittiboina','male','2005-10-16',6306306302,'Indian','Andhra Pradesh','Prakasam','ongole','523001',111111111111,'mudra@gmail.com',500000,10900,'2024-11-04 12:02:33',''),(1122334455553779,'1122','venkata saida','chary','jayavarapu','male','2004-08-28',9912522816,'Indian','Andhra Pradesh','Prakasam','ongole','532001',112233445555,'asdf@gmail.com',1000000,1057,'2024-11-04 11:56:06',''),(1234560987658769,'1234','Teja','','Katari','male','2006-02-01',9704131964,'Indian','Andhra Pradesh','Prakasam','Ongole','518003',123456098765,'o210449@rguktong.ac.in',60000,93755,'2024-10-31 20:45:57',''),(1234567890123456,'0115','Jayadithya','Praneeth','Machavarapu',NULL,NULL,9553587305,'Indian','Andhra Pradesh','East Godavari',NULL,'534342',639054127214,'machavarapujayadithyapraneeth@gmail.com',2400000,4979665,'2024-10-27 22:10:56',''),(1234567890123457,'1808','MAYIF','','Shaik',NULL,NULL,9392757489,'Indian','Andhra Pradesh','Guntur',NULL,'522019',338489665127,NULL,10000,105994,'2024-10-27 22:10:56',''),(1234567890125787,'1234','Nikhil','Kumar','Kandlagunta','male','2006-10-25',7793912959,'Indian','Andhra Pradesh','Prakasam','Vetapalem','523184',123456789012,'o210235@rguktong.ac.in',60000,0,'2024-10-27 22:10:56',''),(1808738029129681,'1808','sreekanth','','tanguturu','male','1983-01-01',9180873800,'Indian','Andhra Pradesh','Prakasam','tanguturu','512009',180873802912,'sreekanth@gmail.com',500000,0,'2024-11-02 10:45:29',''),(2528063605056879,'@budamma!','shanmukha','sasi','sadineni','male','2006-10-20',7396025334,'Indian','Andhra Pradesh','Guntur','perecherla','522438',252806360505,'sadinenisasi@gmail.com',60000,5043,'2024-11-02 12:52:03',''),(3422630099899121,'chaitu519','chaitanya ','','puliga','male','2006-03-13',8639644778,'Indian','Andhra Pradesh','Vishakapatnam','narsipatnam','531116',342263009989,'chaitanyapuliga@gmail.com',99000,6250,'2024-11-07 01:16:13',''),(4173027793726252,'4173','Karthik','','Chitrala','male','2005-01-01',9849128417,'Indian','Andhra Pradesh','Prakasam','Ongole','518003',417302779372,'chitralakarthik9@outlook.com',60000,0,'2024-11-04 19:30:08',''),(4356782189071953,'4356','Praneeth','Kumar','Magapu','male','2003-01-12',9876543210,'Indian','Andhra Pradesh','Palnadu','Piduguralla','518003',435678218907,'magapupraneethkumar@gmail.com',70000,15455,'2024-11-01 19:45:51',''),(5647453289071782,'5647','Sai','','Nandyala','male','2006-01-01',9867895643,'Indian','Andhra Pradesh','Guntur','guntur','522890',564745328907,'sainandyala7@outlook.com',100000,5000,'2024-11-05 11:38:02',''),(6390541272227432,'3256','sambasiva','rao','machaavarapu','male','1970-01-05',9963407692,'Indian','Andhra Pradesh','East Godavari','ipangidi','534342',639054127222,'',100000,1310,'2025-05-17 05:59:31',''),(8325715383594261,'8325','Arya','','Meenuga','male','2005-06-27',8555877868,'Indian','Andhra Pradesh','Prakasam','Ongole','518003',832571538359,'o210241@rguktong.ac.in',60000,0,'2024-11-01 19:45:51',''),(8877665544333472,'8877','shareef','','shaik','male','2005-07-31',9160337680,'Indian','Andhra Pradesh','Guntur','guntur','522109',887766554433,'shareef@gmail.com',5000000,9990,'2024-11-02 11:46:24',''),(9782789728101242,'9782','Joseph','','Meesala','male','2005-01-01',9156283727,'Indian','Andhra Pradesh','Guntur','Guntur','522618',978278972810,'meesalajoseph@rguktong.ac.in',10000,0,'2024-11-01 19:45:51',''),(9876543210982130,'rockstar','vaishnavi','','tavanam','female','2005-10-21',8309633026,'Indian','Andhra Pradesh','Palnadu','chandrajupalem','522411',987654321098,'',60000,0,'2024-11-07 12:05:32','');
/*!40000 ALTER TABLE `customerdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerlogin`
--

DROP TABLE IF EXISTS `customerlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerlogin` (
  `AccountNo` decimal(16,0) NOT NULL,
  `Password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerlogin`
--

LOCK TABLES `customerlogin` WRITE;
/*!40000 ALTER TABLE `customerlogin` DISABLE KEYS */;
INSERT INTO `customerlogin` VALUES (1111111111111646,'1111'),(1122334455553779,'1122'),(1234560987658769,'1234'),(1234567890123456,'0115'),(1234567890123457,'1808'),(1234567890125787,'1234'),(1808738029129681,'1808'),(2528063605056879,'@budamma!'),(3422630099899121,'chaitu519'),(4173027793726252,'4173'),(4356782189071953,'4356'),(5647453289071782,'5647'),(6390541272227432,'3256'),(8325715383594261,'8325'),(8877665544333472,'8877'),(9782789728101242,'9782'),(9876543210982130,'rockstar');
/*!40000 ALTER TABLE `customerlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homeinsurancedetails`
--

DROP TABLE IF EXISTS `homeinsurancedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homeinsurancedetails` (
  `OwnerAccountNo` decimal(16,0) NOT NULL,
  `PropertyType` varchar(20) NOT NULL,
  `YearBuilt` decimal(4,0) NOT NULL,
  `PropertyAreaInSqft` int NOT NULL,
  `PropertyAddress` text NOT NULL,
  `CurrentMarketValue` int NOT NULL,
  `DateCurrentMarketValue` date NOT NULL,
  `ValueOfOtherPartsOfTheHome` int DEFAULT NULL,
  `OwnerOfTheHome` varchar(60) NOT NULL,
  `PhoneNo` decimal(10,0) NOT NULL,
  `EmailId` varchar(40) DEFAULT NULL,
  `CoverageType` varchar(20) NOT NULL,
  `AdditionalProtections` varchar(25) DEFAULT NULL,
  `EstimatedAnnualPremium` int NOT NULL,
  `SecuritySystemsAvailable` varchar(30) DEFAULT NULL,
  `FireExtinguishers` tinyint(1) NOT NULL,
  `PolicyStartDate` date NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`OwnerAccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homeinsurancedetails`
--

LOCK TABLES `homeinsurancedetails` WRITE;
/*!40000 ALTER TABLE `homeinsurancedetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `homeinsurancedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lifeinsurancedetails`
--

DROP TABLE IF EXISTS `lifeinsurancedetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lifeinsurancedetails` (
  `AccountNo` decimal(16,0) NOT NULL,
  `FullName` varchar(60) DEFAULT NULL,
  `Weight` int NOT NULL,
  `Smoker` tinyint(1) NOT NULL,
  `CoverageAmount` int NOT NULL,
  `Term` int NOT NULL,
  `PolicyType` varchar(50) DEFAULT NULL,
  `EstimatedPremium` int NOT NULL,
  `BeneficiaryFullName` varchar(60) DEFAULT NULL,
  `RelationshipWithBeneficiary` varchar(10) DEFAULT NULL,
  `DOB` date NOT NULL,
  `Gender` varchar(15) DEFAULT NULL,
  `Occupation` varchar(30) NOT NULL,
  `AnnualIncome` int NOT NULL,
  `Address` text NOT NULL,
  `Height` int NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`AccountNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lifeinsurancedetails`
--

LOCK TABLES `lifeinsurancedetails` WRITE;
/*!40000 ALTER TABLE `lifeinsurancedetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `lifeinsurancedetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mortgageloanapplication`
--

DROP TABLE IF EXISTS `mortgageloanapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mortgageloanapplication` (
  `LoanId` decimal(14,0) NOT NULL,
  `FullName` varchar(60) NOT NULL,
  `AccountNo` decimal(16,0) NOT NULL,
  `PropertyValue` int NOT NULL,
  `DownPayment` int NOT NULL,
  `Term` int NOT NULL,
  `PhoneNo` decimal(10,0) NOT NULL,
  `EmailId` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`LoanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mortgageloanapplication`
--

LOCK TABLES `mortgageloanapplication` WRITE;
/*!40000 ALTER TABLE `mortgageloanapplication` DISABLE KEYS */;
INSERT INTO `mortgageloanapplication` VALUES (1111123456500,'sambasiva rao machaavarapu',1234567890123456,50000,25000,15,9963407692,'machavarapujayadithyapraneeth@gmail.com','2025-07-10 18:21:26');
/*!40000 ALTER TABLE `mortgageloanapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalloanapplication`
--

DROP TABLE IF EXISTS `personalloanapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalloanapplication` (
  `LoanId` decimal(14,0) NOT NULL,
  `FullName` varchar(60) NOT NULL,
  `AccountNo` decimal(16,0) NOT NULL,
  `LoanPurpose` varchar(30) NOT NULL,
  `LoanAmount` int NOT NULL,
  `PhoneNo` decimal(10,0) NOT NULL,
  `EmailId` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`LoanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalloanapplication`
--

LOCK TABLES `personalloanapplication` WRITE;
/*!40000 ALTER TABLE `personalloanapplication` DISABLE KEYS */;
INSERT INTO `personalloanapplication` VALUES (12345610072025,'Guna Teliyadu',1234567890123456,'home',1000000,9754564658,'gunasrichaithyanya20@gmail.com','2025-07-10 18:45:28'),(12345611072025,'Shaik Khadeer',1234567890123456,'medical',46588,9848325675,'khadee123@gmail.com','2025-07-11 21:24:26');
/*!40000 ALTER TABLE `personalloanapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentloanapplication`
--

DROP TABLE IF EXISTS `studentloanapplication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentloanapplication` (
  `LoanId` decimal(14,0) NOT NULL,
  `FullName` varchar(60) NOT NULL,
  `AccountNo` decimal(16,0) NOT NULL,
  `UniversityName` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `GraduationProgramName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `LoanAmount` int NOT NULL,
  `ExpectedGraduationYear` decimal(4,0) NOT NULL,
  `PhoneNo` decimal(10,0) NOT NULL,
  `EmailId` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`LoanId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentloanapplication`
--

LOCK TABLES `studentloanapplication` WRITE;
/*!40000 ALTER TABLE `studentloanapplication` DISABLE KEYS */;
INSERT INTO `studentloanapplication` VALUES (3333123456900,'Machavarapu Jayadithya Praneeth',1234567890123456,'Rajiv Gandhi University of Knowledge and Technologies ','Masters in Technology',900000,2030,9553587305,'machavarapujayadithyapraneeth@gmail.com','2025-07-10 18:10:42');
/*!40000 ALTER TABLE `studentloanapplication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporarycustomerdetails`
--

DROP TABLE IF EXISTS `temporarycustomerdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `temporarycustomerdetails` (
  `FirstName` varchar(20) NOT NULL,
  `MiddleName` varchar(20) DEFAULT NULL,
  `LastName` varchar(20) NOT NULL,
  `Gender` varchar(15) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Nationality` varchar(30) NOT NULL,
  `PhoneNo` decimal(10,0) DEFAULT NULL,
  `EmailAddress` varchar(50) DEFAULT NULL,
  `State` varchar(30) NOT NULL,
  `District` varchar(30) NOT NULL,
  `Village` varchar(30) NOT NULL,
  `Zipcode` varchar(10) NOT NULL,
  `AadharNo` decimal(12,0) NOT NULL,
  `YearlyIncome` mediumint NOT NULL,
  `DateSubmitted` datetime NOT NULL,
  PRIMARY KEY (`AadharNo`),
  UNIQUE KEY `PhoneNo` (`PhoneNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporarycustomerdetails`
--

LOCK TABLES `temporarycustomerdetails` WRITE;
/*!40000 ALTER TABLE `temporarycustomerdetails` DISABLE KEYS */;
INSERT INTO `temporarycustomerdetails` VALUES ('Guna','','Teliyadu','male','2005-11-20','Indian',9754564658,'','Andhra Pradesh','East Godavari','Rajamahendravaram','534342',485915267373,789456,'2025-07-04 23:32:30');
/*!40000 ALTER TABLE `temporarycustomerdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionhistory`
--

DROP TABLE IF EXISTS `transactionhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactionhistory` (
  `TransactionId` int NOT NULL AUTO_INCREMENT,
  `TransactionType` varchar(20) DEFAULT NULL,
  `FromAccountNo` decimal(16,0) NOT NULL,
  `ToAccountNo` decimal(16,0) NOT NULL,
  `Amount` bigint NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`TransactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionhistory`
--

LOCK TABLES `transactionhistory` WRITE;
/*!40000 ALTER TABLE `transactionhistory` DISABLE KEYS */;
INSERT INTO `transactionhistory` VALUES (1,'Transfers',1234567890123456,1234567890123457,10000,'2024-01-12 00:00:00'),(2,'Transfers',1234567890123456,1234567890123457,10000,'2024-01-13 00:00:00'),(3,'Transfers',1234567890123456,1234567890123457,6909,'2024-10-31 21:49:28'),(4,'Transfers',1234567890123456,1234567890123457,9009,'2024-10-31 21:54:29'),(5,'Transfers',1234567890123457,1234567890123456,1000,'2024-10-31 21:58:47'),(6,'Transfers',1234567890123457,1234567890123456,1000,'2024-10-31 22:00:35'),(7,'Transfers',1234567890123457,1234567890123456,300,'2024-11-01 16:33:49'),(8,'Transfers',1234567890123456,1234567890123457,10000,'2024-11-01 17:14:54'),(9,'Transfers',1234567890123457,1234567890123456,300,'2024-11-01 18:23:38'),(10,'Transfers',1234567890123456,1234560987658769,10000,'2024-11-02 01:14:59'),(11,'Transfers',1234567890123456,1234560987658769,100000,'2024-11-02 01:23:36'),(12,'Transfers',1234567890123456,1234560987658769,5000,'2024-11-02 01:26:03'),(13,'Transfers',1234560987658769,4356782189071953,10000,'2024-11-02 04:43:02'),(14,'Transfers',1234560987658769,1234567890123456,10000,'2024-11-02 04:55:21'),(15,'Transfers',1234560987658769,4356782189071953,5455,'2024-11-02 05:20:46'),(16,'Transfers',1234567890123456,1234560987658769,3000,'2024-11-02 09:18:52'),(17,'Transfers',1234560987658769,1234567890123457,300,'2024-11-02 09:49:33'),(18,'Transfers',1234560987658769,1234567890123457,100,'2024-11-02 09:52:21'),(19,'Transfers',1234567890123457,1234560987658769,1000,'2024-11-02 10:40:45'),(20,'Transfers',1234567890123456,8877665544333472,10000,'2024-11-02 11:47:43'),(21,'Transfers',8877665544333472,1234567890123457,10,'2024-11-02 11:49:20'),(22,'Transfers',1234567890123456,2528063605056879,500,'2024-11-02 12:53:20'),(23,'Transfers',2528063605056879,1234567890123457,500,'2024-11-02 12:55:16'),(24,'Transfers',1234567890123456,1234560987658769,500,'2024-11-02 15:51:19'),(25,'Transfers',1234567890123457,1234560987658769,300,'2024-11-02 15:55:19'),(26,'Transfers',1234567890123457,1234560987658769,300,'2024-11-02 15:56:58'),(27,'Deposit',0,2528063605056879,305,'2024-11-04 03:44:02'),(28,'Deposit',0,2528063605056879,1116,'2024-11-04 04:09:18'),(29,'Withdraw',0,2528063605056879,16,'2024-11-04 04:12:45'),(30,'Deposit',0,1122334455553779,500,'2024-11-04 11:57:24'),(31,'Transfers',1122334455553779,1234567890123457,200,'2024-11-04 11:59:17'),(32,'Deposit',0,1111111111111646,10000,'2024-11-04 12:03:43'),(33,'Transfers',1111111111111646,1122334455553779,60,'2024-11-04 12:04:30'),(34,'Withdraw',1111111111111646,0,940,'2024-11-04 17:39:29'),(35,'Deposit',0,1122334455553779,500,'2024-11-04 17:45:52'),(36,'Transfers',1122334455553779,1234567890123457,10,'2024-11-04 19:18:50'),(37,'Withdraw',0,1122334455553779,10,'2024-11-04 19:19:47'),(38,'Withdraw',0,1122334455553779,10,'2024-11-04 19:20:17'),(39,'Transfers',1234567890123456,1234567890123457,555,'2024-11-04 19:27:00'),(40,'Withdraw',1234567890123457,0,555,'2024-11-04 19:27:42'),(41,'Deposit',0,2528063605056879,890,'2024-11-04 19:28:08'),(42,'Transfers',1234567890123457,1122334455553779,925,'2024-11-05 11:32:27'),(43,'Withdraw',0,1234567890123457,10000,'2024-11-05 11:34:00'),(44,'Deposit',0,5647453289071782,5000,'2024-11-05 11:40:22'),(45,'Withdraw',2528063605056879,0,1002,'2024-11-07 00:24:02'),(46,'Deposit',0,1122334455553779,1002,'2024-11-07 00:24:28'),(47,'Withdraw',1122334455553779,0,600,'2024-11-07 00:25:43'),(48,'Deposit',0,1122334455553779,800,'2024-11-07 00:31:41'),(49,'Deposit',0,3422630099899121,10000,'2024-11-07 01:19:15'),(50,'Transfers',3422630099899121,2528063605056879,3750,'2024-11-07 01:20:13'),(51,'Withdraw',1234567890123457,0,5000,'2024-11-07 11:59:31'),(52,'Deposit',0,1234567890123457,6000,'2024-11-07 12:00:50'),(53,'Deposit',0,9876543210982130,50000,'2024-11-07 12:06:44'),(54,'Transfers',9876543210982130,1234567890123457,49999,'2024-11-07 12:10:55'),(55,'Deposit',0,9876543210982130,1,'2024-11-07 23:38:30'),(56,'Withdraw',9876543210982130,0,1,'2024-11-07 23:38:55'),(57,'Withdraw',9876543210982130,0,1,'2024-11-07 23:56:37'),(58,'Transfers',1234567890123456,6390541272227432,10000,'2025-05-17 06:02:24'),(59,'Deposit',0,6390541272227432,3000,'2025-05-17 06:07:46'),(60,'Withdraw',6390541272227432,0,12000,'2025-05-17 06:08:39'),(61,'Transfers',1234567890123456,6390541272227432,120,'2025-07-04 23:02:32'),(62,'Deposit',0,1234567890123456,156000,'2025-07-04 23:20:23'),(63,'Withdraw',1234567890123456,0,190,'2025-07-04 23:28:52'),(64,'Transfers',1234567890123456,6390541272227432,190,'2025-07-09 22:07:11'),(65,'Transfers',1234567890123456,1234567890123456,120,'2025-07-10 20:41:03'),(66,'Transfers',1122334455553779,1111111111111646,1900,'2026-02-26 22:41:14');
/*!40000 ALTER TABLE `transactionhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicleinsurance`
--

DROP TABLE IF EXISTS `vehicleinsurance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicleinsurance` (
  `AccountNo` decimal(16,0) NOT NULL,
  `OwnerFullName` varchar(60) NOT NULL,
  `Make` varchar(10) DEFAULT NULL,
  `Model` varchar(20) NOT NULL,
  `YearManufatured` decimal(4,0) NOT NULL,
  `RegistrationNo` varchar(10) NOT NULL,
  `CurrentMarketValue` int NOT NULL,
  `OwnerDrivingLicenseNumber` varchar(16) NOT NULL,
  `OwnerAddress` text NOT NULL,
  `CoverageType` varchar(25) NOT NULL,
  `AddonCovers` varchar(30) DEFAULT NULL,
  `PreviousClaims` int DEFAULT NULL,
  `NoClaimBonusPercentage` int DEFAULT NULL,
  `PolicyStartDate` date DEFAULT NULL,
  PRIMARY KEY (`AccountNo`),
  UNIQUE KEY `RegistrationNo` (`RegistrationNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicleinsurance`
--

LOCK TABLES `vehicleinsurance` WRITE;
/*!40000 ALTER TABLE `vehicleinsurance` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicleinsurance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tsrbank'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-27 15:18:30
