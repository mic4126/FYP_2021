-- --------------------------------------------------------
-- 主機:                           127.0.0.1
-- 伺服器版本:                        10.6.5-MariaDB - mariadb.org binary distribution
-- 伺服器作業系統:                      Win64
-- HeidiSQL 版本:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 傾印 airegistry_test 的資料庫結構
DROP DATABASE IF EXISTS `airegistry_test`;
CREATE DATABASE IF NOT EXISTS `airegistry_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `airegistry_test`;

-- 傾印  資料表 airegistry_test.attachment 結構
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE IF NOT EXISTS `attachment` (
  `projectID` int(10) DEFAULT NULL,
  `detailID` int(11) DEFAULT NULL,
  `fileName` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `attachmentID` int(11) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `orig_filename` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `orig_ext` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`attachmentID`),
  KEY `FKAttachment358512` (`projectID`),
  KEY `FKAttachment933303` (`detailID`),
  CONSTRAINT `FKAttachment358512` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`),
  CONSTRAINT `FKAttachment933303` FOREIGN KEY (`detailID`) REFERENCES `detail` (`detailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry_test.auditlog 結構
DROP TABLE IF EXISTS `auditlog`;
CREATE TABLE IF NOT EXISTS `auditlog` (
  `sequence` int(11) NOT NULL AUTO_INCREMENT,
  `query` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `sourceIP` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sourceAccount` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`sequence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry_test.detail 結構
DROP TABLE IF EXISTS `detail`;
CREATE TABLE IF NOT EXISTS `detail` (
  `projectID` int(10) NOT NULL,
  `detailID` int(11) NOT NULL AUTO_INCREMENT,
  `detailName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `detailDesc` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `detailName_TC` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `detailDesc_TC` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `detailDesc_SC` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deleted` datetime DEFAULT NULL,
  `detailName_SC` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`detailID`),
  KEY `FKDetail290139` (`projectID`),
  CONSTRAINT `FKDetail290139` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry_test.project 結構
DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project` (
  `projectID` int(10) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `desc` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phoneNumber` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `projectName_TC` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `projectName_SC` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `desc_TC` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `desc_SC` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deleted` datetime DEFAULT NULL,
  `url` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department_TC` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department_SC` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry_test.project_user 結構
DROP TABLE IF EXISTS `project_user`;
CREATE TABLE IF NOT EXISTS `project_user` (
  `projectID` int(10) NOT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`projectID`,`username`),
  KEY `FKProject_Us201158` (`username`),
  CONSTRAINT `FKProject_Us201158` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKProject_Us660462` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry_test.tag 結構
DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `projectID` int(10) NOT NULL,
  `tag` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `tag_TC` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tag_SC` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sortIndex` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectID`,`tag`),
  CONSTRAINT `FKTag238295` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry_test.user 結構
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `firstName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastName` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `userType` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `salt` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `new_password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `new_salt` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `email` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 取消選取資料匯出。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
