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

-- 正在傾印表格  airegistry_test.attachment 的資料：~8 rows (近似值)
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` (`projectID`, `detailID`, `fileName`, `attachmentID`, `deleted`, `orig_filename`, `orig_ext`) VALUES
	(15, 1, '82f63e68-3daa-475d-85d2-b9350e3130c9', 6, '2022-01-14 01:10:38', 'AMDRM_Install', 'log'),
	(15, 1, 'aba654c0-cf55-4161-9b16-c0027bf1fc1d', 7, '2022-01-14 01:32:52', 'AMD_RyzenMaster', 'log'),
	(15, NULL, '53a99aaa-88d4-4002-addd-81db5f807c84', 8, '2022-01-14 04:16:13', '繪圖', 'png'),
	(15, NULL, 'f3f1c10b-d1dd-42fe-bdff-766f422f31b5', 9, '2022-01-14 04:16:11', '擷取_33', 'PNG'),
	(15, NULL, '0d13b42c-1871-4a5d-a2a5-57e2ffef76c6', 10, NULL, '繪圖', 'png'),
	(15, NULL, 'fd7a8422-2ed5-456b-bec9-dbe596d61995', 11, '2022-01-14 04:45:17', 'Purcahse order gen -1', 'jpg'),
	(15, 1, 'b124a8ad-d4bc-41cc-b43d-7412d0c7e41e', 12, NULL, 'q2', 'PNG'),
	(15, 1, '9ee92673-61b8-4913-b210-0256c9b7751c', 13, '2022-02-14 03:10:29', 'q2', 'PNG');
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;

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

-- 正在傾印表格  airegistry_test.auditlog 的資料：~128 rows (近似值)
/*!40000 ALTER TABLE `auditlog` DISABLE KEYS */;
INSERT INTO `auditlog` (`sequence`, `query`, `timestamp`, `sourceIP`, `sourceAccount`) VALUES
	(3, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'admin3\', \'admin1\', \'nimda\', \'admin\',\n\'$2a$10$FwvA4.gIC7lmNnUlLR8aOOHfCFFKoCk8juSsYi.AXe2GeNxviGAxG\', \'1d6tbcEY8A50guV0H-gxewYKtTc\', \'mic4126@gmail.com\')', '2022-01-01 21:05:49', 'localhost:63230', 'root'),
	(4, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'dev\', \'admin1\', \'nimda\', \'dev\',\n\'$2a$10$UhNkLp0gHbUjxiTtY66GOu7BAFzxs9A1ITvFfxcgEdHh0ze9OsJDu\', \'zNqo5oXjHebPmpkW18MUXngv2Go\', \'mic4126@gmail.com\')', '2022-01-02 02:31:01', 'localhost:55348', 'root'),
	(5, 'UPDATE user SET\ndelete_date = NOW()\nWHERE\nusername = \'admin\'', '2022-01-02 02:36:17', 'localhost:61098', 'root'),
	(6, 'UPDATE user SET\ndelete_date = NOW()\nWHERE\nusername = \'admin\'', '2022-01-02 02:51:22', 'localhost:51336', 'root'),
	(20, 'INSERT INTO project (projectname) VALUES (\'test\')', '2022-01-02 03:27:20', 'localhost:62940', 'root'),
	(27, 'INSERT INTO PROJECT (projectName) VALUE (\'test\')', '2022-01-02 03:48:17', 'localhost:53053', 'root'),
	(28, 'INSERT INTO Project_User (projectID,username) VALUES\n (  \n    12, \'dev\'\n )', '2022-01-02 03:48:28', 'localhost:53053', 'root'),
	(29, 'INSERT INTO PROJECT (projectName) VALUE (\'test1\')', '2022-01-02 03:49:59', 'localhost:53053', 'root'),
	(30, 'INSERT INTO Project_User (projectID,username) VALUES\n (  \n    13, \'dev\'\n )', '2022-01-02 03:50:02', 'localhost:53053', 'root'),
	(31, 'INSERT INTO PROJECT (projectName) VALUE (\'test1\')', '2022-01-02 03:51:52', 'localhost:53110', 'root'),
	(32, 'INSERT INTO Project_User (projectID,username) VALUES\n (  \n    14, \'dev\'\n )', '2022-01-02 03:51:54', 'localhost:53110', 'root'),
	(33, 'DELETE FROM project_user', '2022-01-02 03:52:53', 'localhost:62940', 'root'),
	(34, 'DELETE FROM project_user', '2022-01-02 03:52:53', 'localhost:62940', 'root'),
	(35, 'DELETE FROM project_user', '2022-01-02 03:52:53', 'localhost:62940', 'root'),
	(36, 'INSERT INTO PROJECT (projectName) VALUE (\'test1\')', '2022-01-02 04:14:05', 'localhost:54176', 'root'),
	(37, 'INSERT INTO Project_User (projectID,username) VALUES\n (  \n    15, \'dev\'\n )', '2022-01-02 04:14:05', 'localhost:54176', 'root'),
	(38, 'INSERT INTO PROJECT (projectName) VALUE (\'test1\')', '2022-01-02 04:25:25', 'localhost:52486', 'root'),
	(39, 'INSERT INTO Project_User (projectID,username) VALUES\n (  \n    16, \'dev\'\n )', '2022-01-02 04:25:27', 'localhost:52486', 'root'),
	(40, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'dev1\', \'admin1\', \'nimda\', \'dev\',\n\'$2a$10$TPKKFzBe.AhziOroEFGcGufDMWLi5DP.mVEx5/CAj77misk3q.pf2\', \'tTAePyc1xErMsvL2d52hnzFIlnE\', \'mic4126@gmail.com\')', '2022-01-02 04:36:11', 'localhost:54421', 'root'),
	(42, 'INSERT INTO PROJECT (projectName) VALUE (\'test2\')', '2022-01-02 04:37:49', 'localhost:54449', 'root'),
	(43, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (18, \'dev\')\n , \n    (18, \'dev1\')', '2022-01-02 04:37:51', 'localhost:54449', 'root'),
	(44, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (18, \'dev\')\n , \n    (18, \'dev1\')', '2022-01-02 04:37:51', 'localhost:54449', 'root'),
	(45, 'UPDATE project SET\n    `desc` = \'Lorem_ipsum\'\n    WHERE\n    projectId = 15', '2022-01-02 04:46:50', 'localhost:60132', 'root'),
	(46, 'UPDATE user\r\n	SET\r\n		`PASSWORD` = (SELECT `password` FROM user WHERE username = \'admin\') ,\r\n		salt= (SELECT `salt` FROM user WHERE username = \'admin\')\r\n	WHERE username = \'dev\'', '2022-01-06 01:32:37', 'localhost:56680', 'root'),
	(47, 'UPDATE user SET delete_date = NULL', '2022-01-06 01:33:32', 'localhost:56680', 'root'),
	(48, 'UPDATE user SET delete_date = NULL', '2022-01-06 01:33:32', 'localhost:56680', 'root'),
	(49, 'UPDATE user SET delete_date = NULL', '2022-01-06 01:33:32', 'localhost:56680', 'root'),
	(50, 'UPDATE user SET delete_date = NULL', '2022-01-06 01:33:32', 'localhost:56680', 'root'),
	(51, 'UPDATE user SET delete_date = NULL', '2022-01-06 01:33:32', 'localhost:56680', 'root'),
	(52, 'UPDATE user SET delete_date = NULL', '2022-01-06 01:33:32', 'localhost:56680', 'root'),
	(53, 'UPDATE user SET\npassword = \'$2a$10$HYhvrB8XmTy8GYyX2bknbOrSX9/q8xKNFtvvxlVqHCBNONh3CGKCa\',\nsalt = \'BpL9haZNuuXqKXHBXE2yzUKPcck\',\nnew_password = NULL,\nnew_salt = NULL\nWHERE\nusername = \'dev\'', '2022-01-06 01:34:41', 'localhost:50405', 'root'),
	(54, 'UPDATE user SET\npassword = \'$2a$10$nj9qqfgiY2c0za0LyBvdReAxxWBEqzSeYOBYvYxQKlO26Ee3Mt/rK\',\nsalt = \'zXXexa0BgxcNRWu3tR5lUUKvaZo\',\nnew_password = NULL,\nnew_salt = NULL\nWHERE\nusername = \'dev\'', '2022-01-06 01:35:02', 'localhost:50405', 'root'),
	(55, 'UPDATE user SET `password` = \'$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG\' , salt = \'\'', '2022-01-06 03:41:12', 'localhost:56680', 'root'),
	(56, 'UPDATE user SET `password` = \'$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG\' , salt = \'\'', '2022-01-06 03:41:12', 'localhost:56680', 'root'),
	(57, 'UPDATE user SET `password` = \'$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG\' , salt = \'\'', '2022-01-06 03:41:12', 'localhost:56680', 'root'),
	(58, 'UPDATE user SET `password` = \'$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG\' , salt = \'\'', '2022-01-06 03:41:12', 'localhost:56680', 'root'),
	(59, 'UPDATE user SET `password` = \'$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG\' , salt = \'\'', '2022-01-06 03:41:12', 'localhost:56680', 'root'),
	(60, 'UPDATE user SET `password` = \'$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG\' , salt = \'\'', '2022-01-06 03:41:12', 'localhost:56680', 'root'),
	(61, 'UPDATE user SET\nnew_password = \'$2a$10$nQ1UKH6wVxBCKAx6ur3xp.t2OnQ5hiz.ai5QvtMXkPy0MTGDHBKaG\',\nnew_salt = \'\'\nWHERE\nusername = \'admin\'\nAND email = \'mic4126@gmail.com\'', '2022-01-08 23:27:39', 'localhost:60661', 'root'),
	(62, 'UPDATE user SET\nnew_password = \'$2a$10$yrUqAVCyt089KNXVh3PCSOd11Hs2JpUnmBSzYu6dvxHxCPBNYPGiS\',\nnew_salt = \'\'\nWHERE\nusername = \'admin\'\nAND email = \'mic4126@gmail.com\'', '2022-01-08 23:45:39', 'localhost:60663', 'root'),
	(63, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'admin5\', \'admin\', \'nimda\', \'admin\',\n\'$2a$10$Y68z1KAwP8TxaOQs8BpB2uKih/01l8rX.H3phAumeDflZrfP9/VRa\', \'7xZKVNpa8GsZHggfWnv62SN5Z_c\', \'mic4126@gmail.com\')', '2022-01-09 05:48:32', 'localhost:49831', 'root'),
	(64, 'UPDATE user SET `password` = \'$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG\' , salt = \'\' WHERE username = \'admin5\'', '2022-01-09 05:50:34', 'localhost:56680', 'root'),
	(65, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'admin4\', \'admin\', \'nimda\', \'admin\',\n\'$2a$10$ZNcjX6a6hqkcLkq.Crf0ge/XclDF0pf6RqowhOjTY89uAcy1Ma6gW\', \'\', \'mic4126@gmail.com\')', '2022-01-09 05:51:07', 'localhost:50281', 'root'),
	(67, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'dev6\', \'admin\', \'nimda\', \'dev\',\n\'$2a$10$xA5VhIc4u9sl9Mmyqrpj6uX78npxUvTdegkM1uArGzYSczQc6mVX6\', \'\', \'mic4126@gmail.com\')', '2022-01-09 05:57:58', 'localhost:63442', 'root'),
	(69, 'UPDATE user SET `new_password` = `password` , new_salt = salt WHERE username = \'admin5\'', '2022-01-10 00:35:21', 'localhost:56680', 'root'),
	(70, 'INSERT INTO PROJECT (projectName) VALUE (\'test5\')', '2022-01-10 01:49:42', 'localhost:61938', 'root'),
	(71, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (19, \'dev\')\n , \n    (19, \'dev1\')', '2022-01-10 01:49:42', 'localhost:61938', 'root'),
	(72, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (19, \'dev\')\n , \n    (19, \'dev1\')', '2022-01-10 01:49:42', 'localhost:61938', 'root'),
	(73, 'INSERT INTO PROJECT (projectName) VALUE (\'test3\')', '2022-01-10 01:51:17', 'localhost:61938', 'root'),
	(74, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (20, \'dev\')\n , \n    (20, \'dev1\')\n , \n    (20, \'dev6\')', '2022-01-10 01:51:17', 'localhost:61938', 'root'),
	(75, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (20, \'dev\')\n , \n    (20, \'dev1\')\n , \n    (20, \'dev6\')', '2022-01-10 01:51:17', 'localhost:61938', 'root'),
	(76, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (20, \'dev\')\n , \n    (20, \'dev1\')\n , \n    (20, \'dev6\')', '2022-01-10 01:51:17', 'localhost:61938', 'root'),
	(77, 'UPDATE user SET\ndelete_date = NOW()\nWHERE\nusername = \'admin5\'', '2022-01-10 03:08:37', 'localhost:58994', 'root'),
	(78, 'UPDATE project SET\n    `desc` = \'Lorem_ipsum\'\n    WHERE\n    projectId = 15', '2022-01-10 04:22:18', 'localhost:52694', 'root'),
	(80, 'INSERT INTO Project_User (projectID,username) VALUES (15,\'dev6\')', '2022-01-10 04:24:04', 'localhost:52758', 'root'),
	(81, 'DELETE FROM Project_User where projectId = 15 AND username = \'dev6\'', '2022-01-10 04:24:46', 'localhost:52758', 'root'),
	(82, 'DELETE FROM Project_User where projectId = 20 AND username = \'dev6\'', '2022-01-10 05:28:51', 'localhost:62288', 'root'),
	(83, 'INSERT INTO Project_User (projectID,username) VALUES (19,\'dev6\')', '2022-01-10 05:29:09', 'localhost:62288', 'root'),
	(84, 'DELETE FROM Project_User where projectId = 19 AND username = \'dev6\'', '2022-01-10 05:30:23', 'localhost:62290', 'root'),
	(85, 'DELETE FROM Project_User where projectId = 19 AND username = \'dev1\'', '2022-01-10 05:30:24', 'localhost:62288', 'root'),
	(86, 'DELETE FROM Project_User where projectId = 19 AND username = \'dev\'', '2022-01-10 05:30:30', 'localhost:62290', 'root'),
	(87, 'INSERT INTO Project_User (projectID,username) VALUES (19,\'dev\')', '2022-01-10 05:30:31', 'localhost:62288', 'root'),
	(88, 'INSERT INTO Project_User (projectID,username) VALUES (19,\'dev1\')', '2022-01-10 05:30:32', 'localhost:62288', 'root'),
	(89, 'INSERT INTO Project_User (projectID,username) VALUES (19,\'dev6\')', '2022-01-10 05:30:32', 'localhost:62288', 'root'),
	(90, 'UPDATE project SET\n    `desc` = \'test \'\n    WHERE\n    projectId = 20', '2022-01-12 01:52:33', 'localhost:51736', 'root'),
	(91, 'UPDATE project SET\n    `desc` = \'test projectID 20 eng\'\n    WHERE\n    projectId = 20', '2022-01-12 01:52:51', 'localhost:51741', 'root'),
	(92, 'UPDATE project SET\n    `desc_TC` = \'test projectID 20 tc\'\n    WHERE\n    projectId = 20', '2022-01-12 01:52:59', 'localhost:51749', 'root'),
	(93, 'UPDATE project SET\n    `desc_SC` = \'test projectID 20 sc\'\n    WHERE\n    projectId = 20', '2022-01-12 01:53:06', 'localhost:51749', 'root'),
	(94, 'UPDATE `project` SET\n`projectName` = \'test1\'\nWHERE\n`projectId` = 15', '2022-01-12 03:21:27', 'localhost:49417', 'root'),
	(95, 'UPDATE `project` SET\n`projectName_SC` = \'測試\'\nWHERE\n`projectId` = 15', '2022-01-12 03:21:27', 'localhost:49416', 'root'),
	(96, 'UPDATE `project` SET\n`projectName` = \'測試\'\nWHERE\n`projectId` = 15', '2022-01-12 03:21:27', 'localhost:49416', 'root'),
	(97, 'UPDATE `project` SET\n`projectName_TC` = \'測試\'\nWHERE\n`projectId` = 15', '2022-01-12 03:21:27', 'localhost:49415', 'root'),
	(98, 'UPDATE `project` SET\n`projectName` = \'測試\'\nWHERE\n`projectId` = 15', '2022-01-12 03:21:28', 'localhost:49415', 'root'),
	(99, 'UPDATE project SET\n    `desc_TC` = \'測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試\'\n    WHERE\n    projectId = 15', '2022-01-12 03:22:06', 'localhost:49415', 'root'),
	(100, 'UPDATE project SET\n    `desc_SC` = \'測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試測試\'\n    WHERE\n    projectId = 15', '2022-01-12 03:22:11', 'localhost:49415', 'root'),
	(101, 'UPDATE project\r\n	SET\r\n		email=\'a@b.com\',\r\n		phoneNumber=\'123\',\r\n		department=\'321\'\r\n	WHERE projectID = 15', '2022-01-12 04:36:26', 'localhost:50461', 'root'),
	(102, 'UPDATE project SET\nemail = \'a@b.com\',\nphoneNumber = \'123\',\ndepartment = \'321\'\nWHERE\nprojectId = 15', '2022-01-12 04:45:45', 'localhost:51671', 'root'),
	(103, 'UPDATE project SET\nemail = \'ab.com@abc.com\',\nphoneNumber = \'123\',\ndepartment = \'321\'\nWHERE\nprojectId = 15', '2022-01-12 04:46:06', 'localhost:51670', 'root'),
	(104, 'UPDATE project SET\nemail = \'ab.com@abc.com\',\nphoneNumber = \'123\',\ndepartment = \'321\'\nWHERE\nprojectId = 15', '2022-01-17 04:48:30', 'localhost:56933', 'root'),
	(105, 'UPDATE project SET\n    `desc_SC` = \'簡中\'\n    WHERE\n    projectId = 15', '2022-01-17 04:50:51', 'localhost:56903', 'root'),
	(106, 'UPDATE project SET\n    `desc_TC` = \'繁中測試\'\n    WHERE\n    projectId = 15', '2022-01-17 04:50:52', 'localhost:56912', 'root'),
	(107, 'UPDATE project SET\n    `desc_SC` = \'簡中\'\n    WHERE\n    projectId = 15', '2022-01-17 04:50:57', 'localhost:56967', 'root'),
	(108, 'UPDATE project SET\nemail = \'ab.com@abc.com\',\nphoneNumber = \'123\',\ndepartment = \'321\',\ndepartment_TC = \'\',\ndepartment_SC = \'\',\nurl = \'\'\nWHERE\nprojectId = 15', '2022-01-17 04:55:43', 'localhost:57707', 'root'),
	(109, 'UPDATE project SET\nemail = \'ab.com@abc.com\',\nphoneNumber = \'123\',\ndepartment = \'321\',\ndepartment_TC = \'123\',\ndepartment_SC = \'456\',\nurl = \'example.com\'\nWHERE\nprojectId = 15', '2022-01-17 05:00:39', 'localhost:57995', 'root'),
	(110, 'INSERT INTO Project_User (projectID,username) VALUES (16,\'dev1\')', '2022-01-19 09:55:54', 'localhost:59977', 'root'),
	(111, 'INSERT INTO Project_User (projectID,username) VALUES (16,\'dev6\')', '2022-01-19 09:55:55', 'localhost:59977', 'root'),
	(112, 'DELETE FROM Project_User where projectId = 16 AND username = \'dev1\'', '2022-01-19 09:55:56', 'localhost:59977', 'root'),
	(113, 'DELETE FROM Project_User where projectId = 16 AND username = \'dev6\'', '2022-01-19 09:55:56', 'localhost:59977', 'root'),
	(114, 'INSERT INTO PROJECT (projectName) VALUE (\'TESTPROJECT\')', '2022-01-27 05:22:51', 'localhost:56167', 'root'),
	(115, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (21, \'dev1\')\n , \n    (21, \'dev\')', '2022-01-27 05:22:51', 'localhost:56167', 'root'),
	(116, 'INSERT INTO Project_User (projectID,username) VALUES\n   \n    (21, \'dev1\')\n , \n    (21, \'dev\')', '2022-01-27 05:22:51', 'localhost:56167', 'root'),
	(117, 'UPDATE user SET\ndelete_date = NOW()\nWHERE\nusername = \'admin4\'', '2022-01-27 05:38:11', 'localhost:57710', 'root'),
	(118, 'UPDATE project SET\nemail = \'ab.com@abc.com\',\nphoneNumber = \'12345678\',\ndepartment = \'321\',\ndepartment_TC = \'123\',\ndepartment_SC = \'456\',\nurl = \'example.com\'\nWHERE\nprojectId = 15', '2022-01-28 04:17:48', 'localhost:52762', 'root'),
	(119, 'UPDATE `project` SET\n`projectName_SC` = \'測試\'\nWHERE\n`projectId` = 15', '2022-01-28 04:31:52', 'localhost:54001', 'root'),
	(120, 'UPDATE `project` SET\n`projectName` = \'測試\'\nWHERE\n`projectId` = 15', '2022-01-28 04:31:52', 'localhost:54001', 'root'),
	(121, 'UPDATE `project` SET\n`projectName_TC` = \'測試\'\nWHERE\n`projectId` = 15', '2022-01-28 04:31:52', 'localhost:54002', 'root'),
	(122, 'UPDATE `project` SET\n`projectName` = \'測試\'\nWHERE\n`projectId` = 15', '2022-01-28 04:31:52', 'localhost:54002', 'root'),
	(123, 'UPDATE `project` SET\n`projectName` = \'Test\'\nWHERE\n`projectId` = 15', '2022-01-28 04:31:52', 'localhost:54004', 'root'),
	(124, 'UPDATE user SET\nfirstName = \'nimda\',\nlastName = \'1234\',\nemail = \'mic4126@gmail.com\'\nWHERE\nusername = \'admin\'', '2022-01-28 08:02:44', 'localhost:49520', 'root'),
	(126, 'UPDATE user SET\npassword = \'$2a$10$ldqAVFvC4wG31WCMSb.h3uqUeO3QcKsH6p4Xh.tttktWLQZFREmNG\',\nsalt = \'\',\nnew_password = NULL,\nnew_salt = NULL\nWHERE\nusername = \'admin\'', '2022-01-28 08:25:59', 'localhost:49521', 'root'),
	(127, 'UPDATE user SET\npassword = \'$2a$10$Qou2yS/S70VwquIn/tkWaORE.UtaIZjuuI.4K/JE6z4NTaRFZmCmy\',\nsalt = \'\',\nnew_password = NULL,\nnew_salt = NULL\nWHERE\nusername = \'admin\'', '2022-01-28 08:26:25', 'localhost:49521', 'root'),
	(128, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'admin99\', \'admin1\', \'nimda\', \'admin\',\n\'$2a$10$L7JvVq2iJdQVRz.EFQohCuyrJwURZqB0NkB323oPyItN3N6N2V79G\', \'\', \'mic4126@gmail.com\')', '2022-02-12 16:33:23', 'localhost:51674', 'root'),
	(129, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'admin99\', \'admin1\', \'nimda\', \'admin\',\n\'$2a$10$0r4/3ZnPza0velWD/3sv0.PPV6uvwTchUlg4iQzX06piDC.KeGu2C\', \'\', \'mic4126@gmail.com\')', '2022-02-12 16:36:23', 'localhost:51674', 'root'),
	(130, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'admin99\', \'admin1\', \'nimda\', \'admin\',\n\'$2a$10$nIekYJUxu6BP7wNJ2Okj.uZHMEfpQyBbtou99W19peTxWJp/rfyvW\', \'\', \'mic4126@gmail.com\')', '2022-02-12 16:37:11', 'localhost:51674', 'root'),
	(131, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'admin99\', \'admin1\', \'nimda\', \'admin\',\n\'$2a$10$e5tMw.Tq5Z7exobV3OFGC.Tip28TyLorcN4hOdfbZkEue63MiH/rO\', \'\', \'mic4126@gmail.com\')', '2022-02-12 16:37:51', 'localhost:51762', 'root'),
	(132, 'INSERT INTO `attachment`\n(`filename`,`orig_filename`,`orig_ext`,`detailId`, `projectId`)\nVALUES\n(\'9ee92673-61b8-4913-b210-0256c9b7751c\',\'q2\',\'PNG\',1,15)', '2022-02-14 03:08:57', 'localhost:58812', 'root'),
	(133, 'UPDATE ATTACHMENT SET\ndeleted = NOW()\nWHERE attachmentID = 13', '2022-02-14 03:10:29', 'localhost:59151', 'root'),
	(134, 'UPDATE `detail` SET\n`detailName` = \'Test Detail\',\n`detailDesc` = \'Test\',\n`detailName_TC` = \'測試\',\n`detailDesc_TC` = \'測試測試測試測試\',\n`detailName_SC` = \'測試\',\n`detailDesc_SC` = \'測試\'\nWHERE `detailId` = 1', '2022-02-14 04:22:17', 'localhost:63021', 'root'),
	(135, 'UPDATE `detail` SET\n`detailName` = \'Test Detail\',\n`detailDesc` = \'Test\',\n`detailName_TC` = \'測試\',\n`detailDesc_TC` = \'測試測試測試測試\',\n`detailName_SC` = \'測試\',\n`detailDesc_SC` = \'測試\'\nWHERE `detailId` = 1', '2022-02-14 04:24:12', 'localhost:63020', 'root'),
	(136, 'UPDATE `detail` SET\n`detailName` = \'Test Detail\',\n`detailDesc` = \'Test\',\n`detailName_TC` = \'測試\',\n`detailDesc_TC` = \'測試測試測試測試\',\n`detailName_SC` = \'測試\',\n`detailDesc_SC` = \'測試\'\nWHERE `detailId` = 1', '2022-02-14 04:24:14', 'localhost:63020', 'root'),
	(137, 'UPDATE `detail` SET\n`detailName` = \'Test Detail\',\n`detailDesc` = \'TestTEST\',\n`detailName_TC` = \'測試\',\n`detailDesc_TC` = \'測試測試測試測試\',\n`detailName_SC` = \'測試\',\n`detailDesc_SC` = \'測試\'\nWHERE `detailId` = 1', '2022-02-14 04:26:05', 'localhost:51743', 'root'),
	(138, 'UPDATE user SET\nfirstName = \'admin1\',\nlastName = \'admin\',\nemail = \'mic4126@gmail.com\'\nWHERE\nusername = \'dev\'', '2022-02-14 13:49:31', 'localhost:52946', 'root'),
	(139, 'UPDATE user SET\npassword = \'$2a$10$TogJFzIz8ftAO986/y5Rg.w0BW.HF2Oq7qHnYFj0B/RRqCUNs8b.q\',\nsalt = \'\',\nnew_password = NULL,\nnew_salt = NULL\nWHERE\nusername = \'dev\'', '2022-02-14 13:51:23', 'localhost:53090', 'root'),
	(140, 'UPDATE `project` SET\n`projectName_TC` = \'測試\'\nWHERE\n`projectId` = 15', '2022-02-15 01:23:41', 'localhost:55222', 'root'),
	(141, 'UPDATE `project` SET\n`projectName` = \'測試\'\nWHERE\n`projectId` = 15', '2022-02-15 01:23:41', 'localhost:55222', 'root'),
	(142, 'UPDATE `project` SET\n`projectName` = \'Test\'\nWHERE\n`projectId` = 15', '2022-02-15 01:23:41', 'localhost:55224', 'root'),
	(143, 'UPDATE `project` SET\n`projectName_SC` = \'測試測試\'\nWHERE\n`projectId` = 15', '2022-02-15 01:23:41', 'localhost:55225', 'root'),
	(144, 'UPDATE `project` SET\n`projectName` = \'測試測試\'\nWHERE\n`projectId` = 15', '2022-02-15 01:23:41', 'localhost:55225', 'root'),
	(145, 'UPDATE `detail` SET\n`detailName` = \'Test Detail\',\n`detailDesc` = \'TestTESTT\',\n`detailName_TC` = \'測試\',\n`detailDesc_TC` = \'測試測試測試測試\',\n`detailName_SC` = \'測試\',\n`detailDesc_SC` = \'測試\'\nWHERE `detailId` = 1', '2022-02-15 01:28:34', 'localhost:55399', 'root'),
	(146, 'UPDATE `detail` SET\n`detailName` = \'Test Detail\',\n`detailDesc` = \'TestTESTTTTTTT\',\n`detailName_TC` = \'測試\',\n`detailDesc_TC` = \'測試測試測試測試\',\n`detailName_SC` = \'測試\',\n`detailDesc_SC` = \'測試\'\nWHERE `detailId` = 1', '2022-02-15 01:29:55', 'localhost:55399', 'root'),
	(147, 'UPDATE `project` SET\n`projectName` = \'TestTestTest\'\nWHERE\n`projectId` = 15', '2022-02-15 01:32:43', 'localhost:52999', 'root'),
	(148, 'UPDATE `project` SET\n`projectName_TC` = \'測試\'\nWHERE\n`projectId` = 15', '2022-02-15 01:32:43', 'localhost:52998', 'root'),
	(149, 'UPDATE `project` SET\n`projectName` = \'測試\'\nWHERE\n`projectId` = 15', '2022-02-15 01:32:43', 'localhost:52998', 'root'),
	(150, 'UPDATE `project` SET\n`projectName_SC` = \'測試測試\'\nWHERE\n`projectId` = 15', '2022-02-15 01:32:43', 'localhost:52997', 'root'),
	(151, 'UPDATE `project` SET\n`projectName` = \'測試測試\'\nWHERE\n`projectId` = 15', '2022-02-15 01:32:43', 'localhost:52997', 'root'),
	(152, 'UPDATE project SET\n    `desc_SC` = \'簡中簡中簡中\'\n    WHERE\n    projectId = 15', '2022-02-15 01:38:10', 'localhost:53002', 'root'),
	(153, 'INSERT INTO `Tag` (`projectId`,`tag`,`tag_TC`,`tag_SC`)\n    VALUES\n    (15,\'TTT\',\'TTT\',\'TTT\')', '2022-02-15 01:38:41', 'localhost:52997', 'root'),
	(154, 'DELETE FROM `Tag` WHERE projectId = 15 AND `tag` = \'TTT\'', '2022-02-15 01:38:44', 'localhost:52999', 'root'),
	(185, 'INSERT INTO user\n(username, firstName, lastName, userType, password, salt, email)\nVALUES (\'test\', \'test\', \'test\', \'dev\',\n\'\', \'\', \'a@b.com\')', '2022-02-18 00:29:59', 'localhost:64627', 'root'),
	(186, 'UPDATE user SET\nnew_password = \'newHash\',\nnew_salt = \'newSalt\'\nWHERE\nusername = \'test\'\nAND email = \'a@b.com\'', '2022-02-18 00:29:59', 'localhost:64627', 'root');
/*!40000 ALTER TABLE `auditlog` ENABLE KEYS */;

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

-- 正在傾印表格  airegistry_test.detail 的資料：~4 rows (近似值)
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` (`projectID`, `detailID`, `detailName`, `detailDesc`, `detailName_TC`, `detailDesc_TC`, `detailDesc_SC`, `deleted`, `detailName_SC`) VALUES
	(15, 1, 'Test Detail', 'TestTESTTTTTTT', '測試', '測試測試測試測試', '測試', NULL, '測試'),
	(15, 2, 'Test Detail 1', NULL, 'ttttt', NULL, 'tttttttttt', '2022-01-13 04:03:15', 'tttttttttt'),
	(15, 3, 'Test Detail 2', '測試測試測試測試測試測試', NULL, NULL, NULL, '2022-01-13 04:06:31', NULL),
	(15, 4, 'Test Detail 3', '測試測試測試測試測試測試測試測試', '測試測試測試測試測試測試測試測試', '測試測試測試測試測試測試測試測試', '測試測試測試測試測試測試測試測試', NULL, '測試測試測試測試測試測試測試測試');
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;

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

-- 正在傾印表格  airegistry_test.project 的資料：~6 rows (近似值)
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`projectID`, `projectName`, `desc`, `email`, `phoneNumber`, `department`, `projectName_TC`, `projectName_SC`, `desc_TC`, `desc_SC`, `deleted`, `url`, `department_TC`, `department_SC`) VALUES
	(15, '測試測試', 'Lorem_ipsum', 'ab.com@abc.com', '12345678', '321', '測試', '測試測試', '繁中測試', '簡中簡中簡中', NULL, 'example.com', '123', '456'),
	(16, 'test1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(18, 'test2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(19, 'test5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(20, 'test3', 'test projectID 20 eng', NULL, NULL, NULL, NULL, NULL, 'test projectID 20 tc', 'test projectID 20 sc', NULL, NULL, NULL, NULL),
	(21, 'TESTPROJECT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;

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

-- 正在傾印表格  airegistry_test.project_user 的資料：~11 rows (近似值)
/*!40000 ALTER TABLE `project_user` DISABLE KEYS */;
INSERT INTO `project_user` (`projectID`, `username`) VALUES
	(15, 'dev'),
	(16, 'dev'),
	(18, 'dev'),
	(18, 'dev1'),
	(19, 'dev'),
	(19, 'dev1'),
	(19, 'dev6'),
	(20, 'dev'),
	(20, 'dev1'),
	(21, 'dev'),
	(21, 'dev1');
/*!40000 ALTER TABLE `project_user` ENABLE KEYS */;

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

-- 正在傾印表格  airegistry_test.tag 的資料：~2 rows (近似值)
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`projectID`, `tag`, `tag_TC`, `tag_SC`, `sortIndex`) VALUES
	(15, 'T', 'TT', 'TTT', NULL),
	(16, 'T', 'TT', 'TTT', NULL);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;

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

-- 正在傾印表格  airegistry_test.user 的資料：~10 rows (近似值)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`, `firstName`, `lastName`, `userType`, `password`, `salt`, `new_password`, `new_salt`, `delete_date`, `email`) VALUES
	('admin', 'nimda', '1234', 'admin', '$2a$10$Qou2yS/S70VwquIn/tkWaORE.UtaIZjuuI.4K/JE6z4NTaRFZmCmy', '', NULL, NULL, NULL, 'mic4126@gmail.com'),
	('admin1', 'admin1', 'nimda', 'admin', '$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG', '', NULL, NULL, NULL, 'mic4126@gmail.com'),
	('admin2', 'admin1', 'nimda', 'admin', '$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG', '', NULL, NULL, NULL, 'mic4126@gmail.com'),
	('admin3', 'admin1', 'nimda', 'admin', '$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG', '', NULL, NULL, NULL, 'mic4126@gmail.com'),
	('admin4', 'admin', 'nimda', 'admin', '$2a$10$ZNcjX6a6hqkcLkq.Crf0ge/XclDF0pf6RqowhOjTY89uAcy1Ma6gW', '', NULL, NULL, '2022-01-27 05:38:11', 'mic4126@gmail.com'),
	('admin5', 'admin', 'nimda', 'admin', '$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG', '', '$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG', '', '2022-01-10 03:08:37', 'mic4126@gmail.com'),
	('admin99', 'admin1', 'nimda', 'admin', '$2a$10$e5tMw.Tq5Z7exobV3OFGC.Tip28TyLorcN4hOdfbZkEue63MiH/rO', '', NULL, NULL, NULL, 'mic4126@gmail.com'),
	('dev', 'admin1', 'admin', 'dev', '$2a$10$TogJFzIz8ftAO986/y5Rg.w0BW.HF2Oq7qHnYFj0B/RRqCUNs8b.q', '', NULL, NULL, NULL, 'mic4126@gmail.com'),
	('dev1', 'admin1', 'nimda', 'dev', '$2a$10$038HL8V.Znv/ualSNQw1POOMrrRHT/9Fy2Rgc2dGUNCSq0pHIeuKG', '', NULL, NULL, NULL, 'mic4126@gmail.com'),
	('dev6', 'admin', 'nimda', 'dev', '$2a$10$xA5VhIc4u9sl9Mmyqrpj6uX78npxUvTdegkM1uArGzYSczQc6mVX6', '', NULL, NULL, NULL, 'mic4126@gmail.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 傾印  觸發器 airegistry_test.AttachmentInsertTrigger 結構
DROP TRIGGER IF EXISTS `AttachmentInsertTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS AttachmentInsertTrigger BEFORE INSERT ON `Attachment`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.AttachmentUpdateTrigger 結構
DROP TRIGGER IF EXISTS `AttachmentUpdateTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS AttachmentUpdateTrigger BEFORE UPDATE ON `Attachment`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.DetailInsertTrigger 結構
DROP TRIGGER IF EXISTS `DetailInsertTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS DetailInsertTrigger BEFORE INSERT ON `Detail`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.DetailUpdateTrigger 結構
DROP TRIGGER IF EXISTS `DetailUpdateTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS DetailUpdateTrigger BEFORE UPDATE ON `Detail`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.ProjectInsertTrigger 結構
DROP TRIGGER IF EXISTS `ProjectInsertTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS ProjectInsertTrigger BEFORE INSERT ON `Project`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.ProjectUpdateTrigger 結構
DROP TRIGGER IF EXISTS `ProjectUpdateTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS ProjectUpdateTrigger BEFORE UPDATE ON `Project`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.ProjectUserDeleteTrigger 結構
DROP TRIGGER IF EXISTS `ProjectUserDeleteTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS ProjectUserDeleteTrigger BEFORE DELETE ON `Project_User`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.ProjectUserInsertTrigger 結構
DROP TRIGGER IF EXISTS `ProjectUserInsertTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS ProjectUserInsertTrigger BEFORE INSERT ON `Project_User`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.ProjectUserUpdateTrigger 結構
DROP TRIGGER IF EXISTS `ProjectUserUpdateTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS ProjectUserUpdateTrigger BEFORE UPDATE ON `Project_User`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.TagInsertTrigger 結構
DROP TRIGGER IF EXISTS `TagInsertTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS TagInsertTrigger BEFORE INSERT ON `Tag`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.TagUpdateTrigger 結構
DROP TRIGGER IF EXISTS `TagUpdateTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER IF NOT EXISTS TagUpdateTrigger BEFORE DELETE ON `Tag`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.UserInsertTrigger1 結構
DROP TRIGGER IF EXISTS `UserInsertTrigger1`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER UserInsertTrigger1 BEFORE INSERT ON `user`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- 傾印  觸發器 airegistry_test.UserUpdateTrigger 結構
DROP TRIGGER IF EXISTS `UserUpdateTrigger`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER UserUpdateTrigger BEFORE UPDATE ON `user`
FOR EACH ROW
BEGIN
    DECLARE original_query text;
    DECLARE orig_sourceIP VARCHAR(255);
    DECLARE orig_user VARCHAR(255);
    SET original_query = (SELECT info FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_sourceIP = (SELECT `host` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());
    SET orig_user = (SELECT `user` FROM INFORMATION_SCHEMA.PROCESSLIST WHERE id = CONNECTION_ID());    
    INSERT INTO `auditlog`(`query`,TIMESTAMP,sourceIP,sourceAccount) VALUES (original_query,NOW(),orig_sourceIP,orig_user);
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
