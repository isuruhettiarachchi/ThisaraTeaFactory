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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance` (
  `Date` date NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `InTime` time DEFAULT NULL,
  `OutTime` time DEFAULT NULL,
  `WorkedHours` int(11) DEFAULT NULL,
  `OTHours` int(11) DEFAULT NULL,
  PRIMARY KEY (`Date`,`EmployeeID`),
  KEY `FK1_Attendance` (`EmployeeID`),
  CONSTRAINT `FK1_Attendance` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bankexpenses`
--

DROP TABLE IF EXISTS `bankexpenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bankexpenses` (
  `BID` int(11) NOT NULL AUTO_INCREMENT,
  `AccountNo` int(11) NOT NULL,
  `BankName` varchar(50) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Cost` double DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`BID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `Bill_No` int(11) NOT NULL,
  `ProductID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  PRIMARY KEY (`Bill_No`,`ProductID`),
  KEY `fk2_cart` (`ProductID`),
  CONSTRAINT `fk1_cart` FOREIGN KEY (`Bill_No`) REFERENCES `sale` (`Bill_No`),
  CONSTRAINT `fk2_cart` FOREIGN KEY (`ProductID`) REFERENCES `stock` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `collectionturn`
--

DROP TABLE IF EXISTS `collectionturn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collectionturn` (
  `TurnNo` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `SupplierID` int(11) NOT NULL,
  `NoOfContainers` int(11) NOT NULL,
  `GrossWeight` double NOT NULL,
  `WeightOfContainer` double DEFAULT NULL,
  `WaterWeight` double DEFAULT NULL,
  `MatureLeavesWeight` double DEFAULT NULL,
  `NetWeight` double NOT NULL,
  `TransportUsed` varchar(10) DEFAULT NULL,
  `Bulkno` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TurnNo`),
  KEY `FK1_CollectionTurn` (`Bulkno`),
  KEY `FK2_CollectionTurn` (`ProductID`),
  KEY `FK3_CollectionTurn` (`SupplierID`),
  CONSTRAINT `FK1_CollectionTurn` FOREIGN KEY (`Bulkno`) REFERENCES `monthlybulk` (`Bulkno`),
  CONSTRAINT `FK2_CollectionTurn` FOREIGN KEY (`ProductID`) REFERENCES `dailyproduct` (`ProductID`),
  CONSTRAINT `FK3_CollectionTurn` FOREIGN KEY (`SupplierID`) REFERENCES `supplier` (`SupplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dailyproduct`
--

DROP TABLE IF EXISTS `dailyproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dailyproduct` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `GreenLeavesWeight` double NOT NULL,
  `MadeTeaWeight` double NOT NULL,
  `OutTurn` double NOT NULL,
  `FiredTea` double NOT NULL,
  `RefusedTea` double NOT NULL,
  `Dhool1` double DEFAULT NULL,
  `Dhool2` double DEFAULT NULL,
  `Dhool3` double DEFAULT NULL,
  `Dhool4` double DEFAULT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation` (
  `designation` varchar(25) NOT NULL,
  `DailyRate` double DEFAULT NULL,
  `OTRate` double DEFAULT NULL,
  PRIMARY KEY (`designation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `DriverID` int(11) NOT NULL AUTO_INCREMENT,
  `DriverName` varchar(50) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `LicenseNo` varchar(15) NOT NULL,
  `NIC` varchar(12) NOT NULL,
  `MobileNo` int(11) NOT NULL,
  PRIMARY KEY (`DriverID`),
  UNIQUE KEY `DriverID` (`DriverID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `electricity`
--

DROP TABLE IF EXISTS `electricity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `electricity` (
  `BillNo` int(11) NOT NULL,
  `Units` int(11) NOT NULL,
  `Amount` double DEFAULT NULL,
  `Date` date NOT NULL,
  `Pay` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`BillNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `EmployeeID` int(11) NOT NULL AUTO_INCREMENT,
  `NIC` varchar(12) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Designation` varchar(25) DEFAULT NULL,
  `Address` varchar(250) DEFAULT NULL,
  `Gender` enum('female','male') DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  `Phone` varchar(10) DEFAULT NULL,
  `DateAppointed` date NOT NULL,
  `DateResigned` date DEFAULT NULL,
  `EPFETFDate` date DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  UNIQUE KEY `EmployeeID` (`EmployeeID`),
  UNIQUE KEY `NIC` (`NIC`),
  KEY `FK1_Employee` (`Designation`),
  CONSTRAINT `FK1_Employee` FOREIGN KEY (`Designation`) REFERENCES `designation` (`designation`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `finalization`
--

DROP TABLE IF EXISTS `finalization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finalization` (
  `Date` date NOT NULL,
  `TotalNetProceed` double NOT NULL,
  `TotalDeduction` double NOT NULL,
  `TotalGrossProceed` double NOT NULL,
  `Month` varchar(40) NOT NULL,
  `LotNo` int(11) NOT NULL,
  PRIMARY KEY (`Date`),
  KEY `FK1_FInalization` (`LotNo`),
  CONSTRAINT `FK1_FInalization` FOREIGN KEY (`LotNo`) REFERENCES `sales` (`LotNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `finance`
--

DROP TABLE IF EXISTS `finance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finance` (
  `TransID` int(11) NOT NULL,
  `Type` varchar(15) NOT NULL,
  `Date` date NOT NULL,
  `Amount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `firewood`
--

DROP TABLE IF EXISTS `firewood`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `firewood` (
  `VoucherNo` int(11) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `Date` date NOT NULL,
  `Cost` double DEFAULT NULL,
  PRIMARY KEY (`VoucherNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grocery`
--

DROP TABLE IF EXISTS `grocery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grocery` (
  `GroceryID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  `SupplierID` int(11) DEFAULT NULL,
  `BuyerType` varchar(10) NOT NULL,
  `UnitPrice` double NOT NULL,
  `Quality` varchar(10) NOT NULL,
  `Qty` double NOT NULL,
  `TotalPrice` double NOT NULL,
  `IndexNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`GroceryID`),
  KEY `FK1_Grocery` (`IndexNo`,`Quality`),
  KEY `FK3_Grocery` (`EmployeeID`),
  KEY `FK4_Grocery` (`SupplierID`),
  CONSTRAINT `FK1_Grocery` FOREIGN KEY (`IndexNo`, `Quality`) REFERENCES `quality` (`IndexNo`, `Quality`),
  CONSTRAINT `FK3_Grocery` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`),
  CONSTRAINT `FK4_Grocery` FOREIGN KEY (`SupplierID`) REFERENCES `supplier` (`SupplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `income` (
  `Date` date NOT NULL,
  `LotNo` int(11) NOT NULL,
  `NoOfLots` int(11) NOT NULL,
  `Month` varchar(20) NOT NULL,
  `Year` int(11) NOT NULL,
  `PSE` double NOT NULL,
  `HChg` double NOT NULL,
  `ChamberChg` double NOT NULL,
  `VAT` double NOT NULL,
  `Brokerage` double NOT NULL,
  `FurtherIns` double NOT NULL,
  `TotalDeduction` double NOT NULL,
  PRIMARY KEY (`Date`),
  KEY `FK1_Income` (`LotNo`),
  CONSTRAINT `FK1_Income` FOREIGN KEY (`LotNo`) REFERENCES `sales` (`LotNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maintenancecost`
--

DROP TABLE IF EXISTS `maintenancecost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maintenancecost` (
  `Year` int(11) NOT NULL,
  `Month` int(11) NOT NULL,
  `maintenancecost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `manufactureitem`
--

DROP TABLE IF EXISTS `manufactureitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufactureitem` (
  `ManufactureID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` int(11) NOT NULL,
  `ManufactureDate` date NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `ExpiartDate` date NOT NULL,
  `WasteID` int(11) NOT NULL,
  PRIMARY KEY (`ManufactureID`),
  KEY `fk1_manufactureItem` (`ProductID`),
  KEY `fk2_manufactureItem` (`WasteID`),
  CONSTRAINT `fk1_manufactureItem` FOREIGN KEY (`ProductID`) REFERENCES `stock` (`ProductID`),
  CONSTRAINT `fk2_manufactureItem` FOREIGN KEY (`WasteID`) REFERENCES `waste` (`WasteID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `monthlybulk`
--

DROP TABLE IF EXISTS `monthlybulk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthlybulk` (
  `Bulkno` int(11) NOT NULL AUTO_INCREMENT,
  `SupplierID` int(11) NOT NULL,
  `NoOfDays` int(11) DEFAULT NULL,
  `TransportUsed` double DEFAULT NULL,
  `TransportNotUsed` double DEFAULT NULL,
  `Totalbulk` double NOT NULL,
  `Month` date DEFAULT NULL,
  PRIMARY KEY (`Bulkno`),
  KEY `FK1_MonthlyBulk` (`SupplierID`),
  CONSTRAINT `FK1_MonthlyBulk` FOREIGN KEY (`SupplierID`) REFERENCES `supplier` (`SupplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `nominee`
--

DROP TABLE IF EXISTS `nominee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nominee` (
  `Name` varchar(100) NOT NULL,
  `EmployeeID` int(11) NOT NULL,
  `Age` int(11) NOT NULL,
  `ShareAmount` double DEFAULT NULL,
  `Relationship` varchar(15) NOT NULL,
  `Address` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Name`,`EmployeeID`),
  UNIQUE KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `FK1_Nominee` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `otherexpenses`
--

DROP TABLE IF EXISTS `otherexpenses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `otherexpenses` (
  `VoucherNo` int(11) NOT NULL,
  `Fuel` double DEFAULT NULL,
  `OverateAdditionalPayment` double DEFAULT NULL,
  `SupplierLoan` double DEFAULT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`VoucherNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `packingdetails`
--

DROP TABLE IF EXISTS `packingdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packingdetails` (
  `InvoiceNo` int(11) NOT NULL AUTO_INCREMENT,
  `Grade` varchar(10) NOT NULL,
  `Quality` varchar(10) NOT NULL,
  `NoOfContainers` int(11) NOT NULL,
  `FullHalf` varchar(10) DEFAULT NULL,
  `NetTotalQty` double NOT NULL,
  `NetWeightEach` double NOT NULL,
  `TotalGrossWeight` double NOT NULL,
  `ContainerNo` int(11) NOT NULL,
  `TypeOfPacking` varchar(10) NOT NULL,
  `DateOfPacking` date NOT NULL,
  `SampleAllowed` double NOT NULL,
  `IndexNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`InvoiceNo`),
  KEY `FK1_PackingDetails` (`IndexNo`,`Quality`),
  CONSTRAINT `FK1_PackingDetails` FOREIGN KEY (`IndexNo`, `Quality`) REFERENCES `quality` (`IndexNo`, `Quality`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `PaymentID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Amount` double NOT NULL,
  `BillNo` int(11) DEFAULT NULL,
  `EBillNo` int(11) DEFAULT NULL,
  `AccountNo` int(11) DEFAULT NULL,
  `FVoucherNo` int(11) DEFAULT NULL,
  `OVoucherNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `quality`
--

DROP TABLE IF EXISTS `quality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quality` (
  `IndexNo` int(11) NOT NULL AUTO_INCREMENT,
  `ProductID` int(11) NOT NULL,
  `Quality` varchar(10) NOT NULL,
  `GrossWeight` double NOT NULL,
  `SampleSize` double NOT NULL,
  `NetWeight` double NOT NULL,
  `QualityWeight` double DEFAULT NULL,
  PRIMARY KEY (`IndexNo`,`Quality`),
  KEY `FK1_Quality` (`ProductID`),
  CONSTRAINT `FK1_Quality` FOREIGN KEY (`ProductID`) REFERENCES `dailyproduct` (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `returnsale`
--

DROP TABLE IF EXISTS `returnsale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `returnsale` (
  `Date` date NOT NULL,
  `LotNo` int(11) NOT NULL,
  `InvoiceNo` int(11) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `Weight` double NOT NULL,
  PRIMARY KEY (`Date`),
  KEY `FK1_ReturnSale` (`LotNo`),
  KEY `FK2_ReturnSale` (`InvoiceNo`),
  CONSTRAINT `FK1_ReturnSale` FOREIGN KEY (`LotNo`) REFERENCES `sales` (`LotNo`),
  CONSTRAINT `FK2_ReturnSale` FOREIGN KEY (`InvoiceNo`) REFERENCES `sales` (`InvoiceNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salary`
--

DROP TABLE IF EXISTS `salary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salary` (
  `EmployeeID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `WorkedDays` int(11) NOT NULL,
  `OTHours` int(11) DEFAULT NULL,
  `AttendanceBonus` double DEFAULT NULL,
  `EPFDeduction` double DEFAULT NULL,
  `ETFDeduction` double DEFAULT NULL,
  `CompanyETF` double DEFAULT NULL,
  `Salary` double NOT NULL,
  PRIMARY KEY (`EmployeeID`,`StartDate`,`EndDate`),
  CONSTRAINT `FK1_Salary` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `salaryevents`
--

DROP TABLE IF EXISTS `salaryevents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salaryevents` (
  `SalaryID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`SalaryID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale` (
  `Bill_No` int(11) NOT NULL AUTO_INCREMENT,
  `Buyers_Type` varchar(20) DEFAULT NULL,
  `Date` date NOT NULL,
  `Discount` double DEFAULT NULL,
  `Cost` double DEFAULT NULL,
  `DiscountAmount` double DEFAULT NULL,
  `Totalcost` double DEFAULT NULL,
  PRIMARY KEY (`Bill_No`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `LotNo` int(11) NOT NULL AUTO_INCREMENT,
  `InvoiceNo` int(11) NOT NULL,
  `Buyerdetails` varchar(50) NOT NULL,
  `Grade` varchar(10) NOT NULL,
  `chest` int(11) NOT NULL,
  `Price` double NOT NULL,
  `GrossWeight` double NOT NULL,
  `Sampleweight` double NOT NULL,
  `NetWeight` double NOT NULL,
  `GrossProceed` double NOT NULL,
  PRIMARY KEY (`LotNo`),
  UNIQUE KEY `Grade` (`Grade`),
  KEY `FK1_Sales` (`InvoiceNo`),
  CONSTRAINT `FK1_Sales` FOREIGN KEY (`InvoiceNo`) REFERENCES `packingdetails` (`InvoiceNo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock` (
  `ProductID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(40) NOT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Weight` int(11) DEFAULT NULL,
  `Qty` int(11) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `SupplierID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `LandName` varchar(100) DEFAULT NULL,
  `GramaNiladhariDivision` varchar(50) DEFAULT NULL,
  `LandArea` double DEFAULT NULL,
  `NIC` varchar(12) DEFAULT NULL,
  `TelephoneNo` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `supplierpayment`
--

DROP TABLE IF EXISTS `supplierpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplierpayment` (
  `BillNo` int(11) NOT NULL AUTO_INCREMENT,
  `PaidDate` date NOT NULL,
  `SupplierID` int(11) NOT NULL,
  `SuppliedLeaves` double NOT NULL,
  `Rate` double DEFAULT NULL,
  `PayAmount` double DEFAULT NULL,
  `Transport` double DEFAULT NULL,
  `GrossPayment` double DEFAULT NULL,
  `TeaDeduction` double DEFAULT NULL,
  `CashInAdvance` double DEFAULT NULL,
  `Fertilizer` double DEFAULT NULL,
  `NetPayment` double DEFAULT NULL,
  `Bulkno` int(11) DEFAULT NULL,
  PRIMARY KEY (`BillNo`),
  KEY `FK1_SupplierPayment` (`SupplierID`),
  KEY `FK3_SupplierPayment` (`Bulkno`),
  CONSTRAINT `FK1_SupplierPayment` FOREIGN KEY (`SupplierID`) REFERENCES `supplier` (`SupplierID`),
  CONSTRAINT `FK3_SupplierPayment` FOREIGN KEY (`Bulkno`) REFERENCES `monthlybulk` (`Bulkno`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `telephone`
--

DROP TABLE IF EXISTS `telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telephone` (
  `BillNo` int(11) NOT NULL,
  `Number` varchar(10) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Pay` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`BillNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `totaltransportcost`
--

DROP TABLE IF EXISTS `totaltransportcost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `totaltransportcost` (
  `Year` int(11) NOT NULL,
  `Month` int(11) NOT NULL,
  `FuelCost` double DEFAULT NULL,
  `OtherExpenses` double DEFAULT NULL,
  `MaintenanceCost` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `TransactionID` int(11) NOT NULL AUTO_INCREMENT,
  `RefID` int(11) DEFAULT NULL,
  `Type` varchar(25) NOT NULL,
  `Date` date NOT NULL,
  `Amount` double NOT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `turn`
--

DROP TABLE IF EXISTS `turn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turn` (
  `turn_id` int(11) NOT NULL AUTO_INCREMENT,
  `driver_id` int(11) NOT NULL,
  `asstdrv_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `type` varchar(25) NOT NULL,
  `route` varchar(50) NOT NULL,
  `distance` double NOT NULL,
  `fuelcost` double NOT NULL,
  `otherexp` double NOT NULL,
  PRIMARY KEY (`turn_id`),
  KEY `fk_drv` (`driver_id`),
  KEY `fk_asst` (`asstdrv_id`),
  KEY `fk_vid` (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `VehicleID` int(11) NOT NULL AUTO_INCREMENT,
  `VehicleType` varchar(20) NOT NULL,
  `RegNo` varchar(30) NOT NULL,
  `StorageCapacity` double NOT NULL,
  `Milage` double NOT NULL,
  PRIMARY KEY (`VehicleID`),
  UNIQUE KEY `VehicleID` (`VehicleID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vehiclemaintenance`
--

DROP TABLE IF EXISTS `vehiclemaintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehiclemaintenance` (
  `MaintenanceID` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `MaintenanceType` varchar(50) NOT NULL,
  `Cost` double NOT NULL,
  `VehicleID` int(11) NOT NULL,
  PRIMARY KEY (`MaintenanceID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `waste`
--

DROP TABLE IF EXISTS `waste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `waste` (
  `WasteID` int(11) NOT NULL AUTO_INCREMENT,
  `RecieveDate` date DEFAULT NULL,
  `Source` varchar(40) DEFAULT NULL,
  `WasteName` varchar(70) DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL,
  `Amount` double DEFAULT NULL,
  PRIMARY KEY (`WasteID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2017-05-09 14:58:27
