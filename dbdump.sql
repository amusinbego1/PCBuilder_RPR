CREATE DATABASE  IF NOT EXISTS `sql7614977` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sql7614977`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: sql7614977
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `graphcards`
--

DROP TABLE IF EXISTS `graphcards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `graphcards` (
  `graphcard_id` int NOT NULL AUTO_INCREMENT,
  `graphcard_name` varchar(40) NOT NULL,
  `manufacturer` varchar(40) NOT NULL,
  `shop_url` text,
  `img_url` text,
  `graphcard_desc` text,
  `price` decimal(7,2) NOT NULL,
  PRIMARY KEY (`graphcard_id`),
  CONSTRAINT `valid_graphcard_price` CHECK ((`price` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `graphcards`
--

LOCK TABLES `graphcards` WRITE;
/*!40000 ALTER TABLE `graphcards` DISABLE KEYS */;
INSERT INTO `graphcards` VALUES (1,'51RISC Radeon RX 580 8GB','51RISC','https://www.amazon.com/51RISC-RX-580-8GB-DisplayPort/dp/B0BNBN8HQF/ref=sr_1_1_sspa?keywords=graphics%2Bcard&qid=1682894689&sprefix=graph%2Caps%2C227&sr=8-1-spons&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUExR0haRU5LR1A2NzJGJmVuY3J5cHRlZElkPUEwODk3MjUxWDdCVEhTOElVNk0mZW5jcnlwdGVkQWRJZD1BMDUxNjk3MTNUMjFRNDNUM0JXUzgmd2lkZ2V0TmFtZT1zcF9hdGYmYWN0aW9uPWNsaWNrUmVkaXJlY3QmZG9Ob3RMb2dDbGljaz10cnVl&th=1','https://m.media-amazon.com/images/I/71O7lZ00aBL._AC_SX679_.jpg','Chipset: AMD Radeon RX 580, GPU Chip: Ellesmere, Video Memory: 8 GB GDDR5, GPU Clock: 1284 MHz, Output: HDMI x 1/ DisplayPort x 3 / DVI-D x 1. What you get: 1x RX 580 8GB graphics card, 1x User manual, 2-Year Limited warranty, professional after-sale service.',120.00),(2,'Maxsun AMD Radeon RX 550 4GB','Maxsun','https://www.amazon.com/RX-550-Computer-Graphics-DisplayPort/dp/B08VHWFWSD/ref=sr_1_2_sspa?keywords=graphics+card&qid=1682894886&sprefix=graph%2Caps%2C227&sr=8-2-spons&psc=1&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUExUEcxMjBQUzVMVVImZW5jcnlwdGVkSWQ9QTA3NDQwNDAzQTJJNERKNVZDTzVNJmVuY3J5cHRlZEFkSWQ9QTEwMTk0NzkyNVlNRFhXRUtQQjg2JndpZGdldE5hbWU9c3BfYXRmJmFjdGlvbj1jbGlja1JlZGlyZWN0JmRvTm90TG9nQ2xpY2s9dHJ1ZQ==','https://m.media-amazon.com/images/I/61-PIBx43BL._AC_SX679_.jpg','AMD Radeon RX 550 Chipset, Silver plated PCB & all solid capacitors provide lower temperature, higher efficiency & stability. GPU Boost Clock / Memory Speed : up to 1183 MHz / 4GB GDDR5 / 6000 MHz Memory, Stream Processors 512, Perfect for 3D CAD/CAM working, video and photo editing, Video Games @1080p',110.00),(3,'MSI Gaming GeForce RTX 3060 12GB','MSI','https://www.amazon.com/MSI-GeForce-RTX-3060-12G/dp/B08WPRMVWB/ref=sr_1_3?keywords=graphics%2Bcard&qid=1682894886&sprefix=graph%2Caps%2C227&sr=8-3&th=1','https://m.media-amazon.com/images/I/71tduSp8ooL._AC_SX679_.jpg','Video Memory: 12GB GDDR6.Output: DisplayPort x 3 (v1.4a) / HDMI 2.1 x 1.Avoid using unofficial software. Digital maximum resolution: 7680 x 4320',460.00);
/*!40000 ALTER TABLE `graphcards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pcs`
--

DROP TABLE IF EXISTS `pcs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pcs` (
  `pc_id` int NOT NULL AUTO_INCREMENT,
  `price` decimal(8,2) NOT NULL,
  `ram_id` int NOT NULL,
  `processor_id` int NOT NULL,
  `graphcard_id` int NOT NULL,
  PRIMARY KEY (`pc_id`),
  KEY `ram_id` (`ram_id`),
  KEY `processor_id` (`processor_id`),
  KEY `graphcard_id` (`graphcard_id`),
  CONSTRAINT `pcs_ibfk_1` FOREIGN KEY (`ram_id`) REFERENCES `rams` (`ram_id`) ON DELETE CASCADE,
  CONSTRAINT `pcs_ibfk_2` FOREIGN KEY (`processor_id`) REFERENCES `processors` (`processor_id`) ON DELETE CASCADE,
  CONSTRAINT `pcs_ibfk_3` FOREIGN KEY (`graphcard_id`) REFERENCES `graphcards` (`graphcard_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pcs`
--

LOCK TABLES `pcs` WRITE;
/*!40000 ALTER TABLE `pcs` DISABLE KEYS */;
/*!40000 ALTER TABLE `pcs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processors`
--

DROP TABLE IF EXISTS `processors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `processors` (
  `processor_id` int NOT NULL AUTO_INCREMENT,
  `processor_name` varchar(40) NOT NULL,
  `manufacturer` varchar(40) NOT NULL,
  `shop_url` text,
  `img_url` text,
  `processor_desc` text,
  `price` decimal(7,2) NOT NULL,
  PRIMARY KEY (`processor_id`),
  CONSTRAINT `valid_processor_price` CHECK ((`price` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processors`
--

LOCK TABLES `processors` WRITE;
/*!40000 ALTER TABLE `processors` DISABLE KEYS */;
INSERT INTO `processors` VALUES (1,'Intel Core i9-13900KF','Intel','https://www.amazon.com/Intel-i9-13900KF-Desktop-Processor-P-cores/dp/B0BCFM3CJ4/ref=sr_1_1_sspa?crid=3K15EYJ5DCV68&keywords=processors&qid=1682893345&sprefix=processors%2Caps%2C204&sr=8-1-spons&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUFEVVFWMDEyTUlGNk8mZW5jcnlwdGVkSWQ9QTA4NzI3NjcxVTBKU1lQQlJBUUsmZW5jcnlwdGVkQWRJZD1BMDczMDMzNjFENzRQWjdJNTVSWlcmd2lkZ2V0TmFtZT1zcF9hdGYmYWN0aW9uPWNsaWNrUmVkaXJlY3QmZG9Ob3RMb2dDbGljaz10cnVl&th=1','https://m.media-amazon.com/images/I/614oZrSkrCL.__AC_SX300_SY300_QL70_FMwebp_.jpg','24 cores (8 P-cores + 16 E-cores) and 32 threads. Performance hybrid architecture integrates two core microarchitectures, prioritizing and distributing workloads to optimize performance. Up to 5.8 GHz unlocked. 36M Cache.',600.00),(2,'AMD Ryyen 9 5900X','AMD','https://www.amazon.com/AMD-Ryzen-5900X-24-Thread-Processor/dp/B08164VTWH/ref=sr_1_5?crid=3K15EYJ5DCV68&keywords=processors&qid=1682893862&sprefix=processors%2Caps%2C204&sr=8-5&th=1','https://m.media-amazon.com/images/I/616VM20+AzL._AC_SX679_.jpg','The world\'s best gaming desktop processor, with 12 cores and 24 processing threads. Can deliver elite 100-plus FPS performance in the world\'s most popular games. 4.8 GHz Max Boost, unlocked for overclocking, 70 MB of cache, DDR-3200 support.',570.00),(3,'AMD Ryzen 7 5700G','AMD','https://www.amazon.com/AMD-Ryzen-5700G-16-Thread-Processor/dp/B091J3NYVF/ref=sr_1_11?crid=3K15EYJ5DCV68&keywords=processors&qid=1682893862&sprefix=processors%2Caps%2C204&sr=8-11&th=1','https://m.media-amazon.com/images/I/51D3DrDmwkL.__AC_SX300_SY300_QL70_FMwebp_.jpg','Play some of the most popular games at 1080p with the fastest processor graphics in the world, no graphics card required. 8 Cores and 16 processing threads, bundled with the AMD Wraith Stealth cooler. 4.6 GHz Max Boost, unlocked for overclocking, 20 MB cache, DDR4-3200 support',360.00);
/*!40000 ALTER TABLE `processors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rams`
--

DROP TABLE IF EXISTS `rams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rams` (
  `ram_id` int NOT NULL AUTO_INCREMENT,
  `ram_name` varchar(40) NOT NULL,
  `manufacturer` varchar(40) NOT NULL,
  `shop_url` text,
  `img_url` text,
  `ram_desc` text,
  `price` decimal(7,2) NOT NULL,
  PRIMARY KEY (`ram_id`),
  CONSTRAINT `valid_ram_price` CHECK ((`price` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rams`
--

LOCK TABLES `rams` WRITE;
/*!40000 ALTER TABLE `rams` DISABLE KEYS */;
INSERT INTO `rams` VALUES (1,'Corsair Vengeance LPX 16GB','Corsair','https://www.amazon.com/Corsair-Vengeance-PC4-25600-Desktop-Memory/dp/B07RS1G6XW/ref=sr_1_5?crid=21QZEARTYY4H4&keywords=RAM&qid=1682894207&sprefix=ram%2Caps%2C241&sr=8-5','https://m.media-amazon.com/images/I/51gLnYN1W7L._AC_SX679_PIbundle-2,TopRight,0,0_SH20_.jpg','Hand-sorted memory chips ensure high performance with generous overclocking headroom. VENGEANCE LPX is optimized for wide compatibility with the latest Intel and AMD DDR4 motherboards, A low-profile height of just 34mm ensures that VENGEANCE LPX even fits in most small-form-factor builds',65.00),(2,'Timetec 16GB DDR3 1600MHz','Timetec','https://www.amazon.com/Timetec-1600MHz-PC3L-12800-Unbuffered-Desktop/dp/B00IV19HZE/ref=sxin_15_ac_d_hl?ac_md=2-1-U21hbGwgQnVzaW5lc3MgQnJhbmQ%3D-ac_d_hl_hl_sb&content-id=amzn1.sym.ea5a3043-3172-4e81-bcc4-eb7524db4f7c%3Aamzn1.sym.ea5a3043-3172-4e81-bcc4-eb7524db4f7c&crid=21QZEARTYY4H4&cv_ct_cx=RAM&keywords=RAM&pd_rd_i=B00IV19HZE&pd_rd_r=af1d91dc-a72f-4b02-b7dd-35ec0f29c11a&pd_rd_w=ATPdR&pd_rd_wg=tQHfm&pf_rd_p=ea5a3043-3172-4e81-bcc4-eb7524db4f7c&pf_rd_r=5Y8P93F2W8XRHVJDV23V&qid=1682894392&sbo=RZvfv%2F%2FHxDF%2BO5021pAnSA%3D%3D&sprefix=ram%2Caps%2C241&sr=1-2-25fd44b4-555a-4528-b40c-891e95133f20&th=1','https://m.media-amazon.com/images/I/71gbuSPAJsL._AC_SX679_.jpg','DDR3L / DDR3 1600MHz PC3L-12800 / PC3-12800 240-Pin Unbuffered Non-ECC 1.35V / 1.5V CL11 Dual Rank 2Rx8 based 512x8. Module Size: 16GB KIT(2x8GB Modules) Package: 2x8GB ; JEDEC standard 1.35V, this is a dual voltage piece and can operate at 1.35V or 1.5V',24.00),(3,'Kingston FURY Beast RGB 64GB','Kingston','https://www.amazon.com/Kingston-FURY-3200MHz-KF432C16BBAK4-64/dp/B097HNG6Z7/ref=sr_1_1_sspa?crid=21QZEARTYY4H4&keywords=RAM&qid=1682894392&sprefix=ram%2Caps%2C241&sr=8-1-spons&spLa=ZW5jcnlwdGVkUXVhbGlmaWVyPUEzUkNaNTNHMTlZVkhKJmVuY3J5cHRlZElkPUEwNTQwMDM0MU1TSU5XMEc0MUlFSCZlbmNyeXB0ZWRBZElkPUEwMTA4NDM3M0c4Nk8yVTJaQjlUTSZ3aWRnZXROYW1lPXNwX2F0ZiZhY3Rpb249Y2xpY2tSZWRpcmVjdCZkb05vdExvZ0NsaWNrPXRydWU&th=1','https://m.media-amazon.com/images/I/714nmnxvuHL._AC_SX679_.jpg','3200MT/s DDR4 CL16 Desktop Memory Kit of 4 | Infrared Syncing | Intel XMP | AMD Ryzen | Plug n Play | KF432C16BBAK4/64',170.00);
/*!40000 ALTER TABLE `rams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sql7614977'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-01  1:08:36
