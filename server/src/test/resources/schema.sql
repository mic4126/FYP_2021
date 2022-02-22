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


-- 傾印 airegistry 的資料庫結構
SET DATABASE SQL SYNTAX MYS TRUE;
-- CREATE DATABASE IF NOT EXISTS `airegistry` /*!40100 DEFAULT CHARACTER SET utf8mb4  */;
-- USE `airegistry`;

-- 傾印  資料表 airegistry.attachment 結構
CREATE TABLE IF NOT EXISTS `attachment` (
  `projectID` int(10) DEFAULT NULL,
  `detailID` int(11) DEFAULT NULL,
  `fileName` text  NOT NULL,
  `attachmentID` int(11) NOT NULL AUTO_INCREMENT,
  `deleted` datetime DEFAULT NULL,
  `orig_filename` text  DEFAULT NULL,
  `orig_ext` varchar(255)  DEFAULT NULL,
  PRIMARY KEY (`attachmentID`),
  KEY `FKAttachment358512` (`projectID`),
  KEY `FKAttachment933303` (`detailID`),
  CONSTRAINT `FKAttachment358512` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`),
  CONSTRAINT `FKAttachment933303` FOREIGN KEY (`detailID`) REFERENCES `detail` (`detailID`)
);

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry.auditlog 結構
CREATE TABLE IF NOT EXISTS `auditlog` (
  `sequence` int(11) NOT NULL AUTO_INCREMENT,
  `query` text  NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `sourceIP` varchar(255)  NOT NULL,
  `sourceAccount` varchar(255)  NOT NULL,
  PRIMARY KEY (`sequence`)
);

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry.detail 結構
CREATE TABLE IF NOT EXISTS `detail` (
  `projectID` int(10) NOT NULL,
  `detailID` int(11) NOT NULL AUTO_INCREMENT,
  `detailName` varchar(255)  NOT NULL,
  `detailDesc` text  DEFAULT NULL,
  `detailName_TC` varchar(255)  DEFAULT NULL,
  `detailDesc_TC` text  DEFAULT NULL,
  `detailDesc_SC` text  DEFAULT NULL,
  `deleted` datetime DEFAULT NULL,
  `detailName_SC` varchar(255)  DEFAULT NULL,
  PRIMARY KEY (`detailID`),
  KEY `FKDetail290139` (`projectID`),
  CONSTRAINT `FKDetail290139` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry.project 結構
CREATE TABLE IF NOT EXISTS `project` (
  `projectID` int(10) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(255)  NOT NULL,
  `desc` text  DEFAULT NULL,
  `email` varchar(255)  DEFAULT NULL,
  `phoneNumber` varchar(255)  DEFAULT NULL,
  `department` text  DEFAULT NULL,
  `projectName_TC` varchar(255)  DEFAULT NULL,
  `projectName_SC` varchar(255)  DEFAULT NULL,
  `desc_TC` text  DEFAULT NULL,
  `desc_SC` text  DEFAULT NULL,
  `deleted` datetime DEFAULT NULL,
  `url` text  DEFAULT NULL,
  `department_TC` varchar(255)  DEFAULT NULL,
  `department_SC` varchar(255)  DEFAULT NULL,
  PRIMARY KEY (`projectID`)
) ;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry.project_user 結構
CREATE TABLE IF NOT EXISTS `project_user` (
  `projectID` int(10) NOT NULL,
  `username` varchar(255)  NOT NULL,
  PRIMARY KEY (`projectID`,`username`),
  KEY `FKProject_Us201158` (`username`),
  CONSTRAINT `FKProject_Us201158` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKProject_Us660462` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry.tag 結構
CREATE TABLE IF NOT EXISTS `tag` (
  `projectID` int(10) NOT NULL,
  `tag` varchar(255)  NOT NULL,
  `tag_TC` text  DEFAULT NULL,
  `tag_SC` text  DEFAULT NULL,
  `sortIndex` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectID`,`tag`),
  CONSTRAINT `FKTag238295` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`)
) ;

-- 取消選取資料匯出。

-- 傾印  資料表 airegistry.user 結構
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(255)  NOT NULL,
  `firstName` varchar(255)  NOT NULL,
  `lastName` varchar(255)  NOT NULL,
  `userType` varchar(255)  NOT NULL,
  `password` varchar(255)  NOT NULL,
  `salt` varchar(255)  NOT NULL,
  `new_password` varchar(255)  DEFAULT NULL,
  `new_salt` varchar(255)  DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `email` text  DEFAULT NULL,
  PRIMARY KEY (`username`)
) ;

-- 取消選取資料匯出。

-- 傾印  觸發器 airegistry.AttachmentInsertTrigger 結構
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

-- 傾印  觸發器 airegistry.AttachmentUpdateTrigger 結構
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

-- 傾印  觸發器 airegistry.DetailInsertTrigger 結構
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

-- 傾印  觸發器 airegistry.DetailUpdateTrigger 結構
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

-- 傾印  觸發器 airegistry.ProjectInsertTrigger 結構
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

-- 傾印  觸發器 airegistry.ProjectUpdateTrigger 結構
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

-- 傾印  觸發器 airegistry.ProjectUserDeleteTrigger 結構
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

-- 傾印  觸發器 airegistry.ProjectUserInsertTrigger 結構
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

-- 傾印  觸發器 airegistry.ProjectUserUpdateTrigger 結構
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

-- 傾印  觸發器 airegistry.TagInsertTrigger 結構
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

-- 傾印  觸發器 airegistry.TagUpdateTrigger 結構
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

-- 傾印  觸發器 airegistry.UserInsertTrigger1 結構
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

-- 傾印  觸發器 airegistry.UserUpdateTrigger 結構
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
