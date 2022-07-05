/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 05/07/2022 15:24:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `accountid` int NOT NULL AUTO_INCREMENT,
  `balance` double NULL DEFAULT NULL,
  PRIMARY KEY (`accountid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` (`accountid`, `balance`) VALUES (1, 7961.4), (2, 27900);
COMMIT;

-- ----------------------------
-- Table structure for mybooks
-- ----------------------------
DROP TABLE IF EXISTS `mybooks`;
CREATE TABLE `mybooks`  (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Author` varchar(30) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Title` varchar(30) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `Price` float NOT NULL,
  `Publishingdate` date NOT NULL,
  `Salesamount` int NOT NULL,
  `Storenumber` int NOT NULL,
  `Remark` text CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of mybooks
-- ----------------------------
BEGIN;
INSERT INTO `mybooks` (`Id`, `Author`, `Title`, `Price`, `Publishingdate`, `Salesamount`, `Storenumber`, `Remark`) VALUES (1, 'Joshua Bloch', 'Effective Java', 50, '2022-06-22', 44, 156, 'Good Java Book'), (2, '周志明', '深入理解Java虚拟机', 60.2, '2022-06-22', 21, 79, 'Good Java Book'), (3, '埃克尔', 'Java编程思想', 50.5, '2022-06-22', 16, 184, 'Good Java Book'), (4, 'Cay S.Horstmann', 'Java核心技术', 51, '2022-06-22', 1, 0, 'Good Java Book'), (5, 'Brian Goetz', 'Java并发编程实战', 52, '2022-06-22', 3, 147, 'Good Java Book'), (6, 'Kathy Sierra', 'Head First Java', 53, '2022-06-22', 0, 100, 'Good Java Book'), (7, '结城浩', 'Java多线程设计模式', 54, '2022-06-22', 2, 12, 'Good Java Book'), (8, '杨冠宝', '阿里巴巴Java开发手册', 55.9, '2022-06-22', 2, 13, 'Good Java Book'), (9, 'Budi Kurniawan', '深入剖析Tomcat', 56, '2022-06-22', 0, 16, 'Good Java Book'), (10, '拉佛 / 计晓云 ', 'Java数据结构和算法', 57, '2022-06-22', 0, 17, 'Good Java Book'), (11, 'Mark Allen Weiss', '数据结构与算法分析', 58.7, '2022-06-22', 0, 0, 'Good Java Book'), (12, 'Craig Walls', 'Spring实战', 59, '2022-06-22', 0, 19, 'Good Java Book'), (13, 'Kenneth L. Calvert', 'Java TCP/IP Socket', 60, '2022-06-22', 6, 14, 'Good Java Book'), (14, '李刚', '疯狂Java讲义', 61, '2022-06-22', 9, 12, 'Good Java Book'), (15, '翟陆续、薛宾田', 'Java并发编程之美', 62, '2022-06-22', 8, 14, 'Good Java Book'), (16, 'Benjamin J. Evans', 'Java程序员修炼之道', 63, '2022-06-22', 0, 23, 'Good Java Book'), (17, '克雷格沃斯', 'Spring Boot', 64, '2022-06-22', 0, 24, 'Good Java Book'), (18, '高性能MySQL', '高性能MySQL', 65.1, '2022-06-22', 0, 25, 'Good Java Book'), (19, 'Sasba Pacbev', 'MySQL核心技术', 66, '2022-06-22', 0, 26, 'Good Java Book'), (20, 'Jon Bentley', '编程珠玑', 67, '2022-06-22', 0, 27, 'Good Java Book'), (21, 'Robert C. Martin', '代码整洁之道', 68.9, '2022-06-22', 0, 28, 'Good Java Book'), (22, 'Randal E.Bryant', '深入理解计算机系统', 69, '2022-06-22', 5, 24, 'Good Java Book'), (23, 'Brian W. Kernighan', 'C程序设计语言', 70, '2022-06-22', 5, 25, 'Good Java Book'), (24, '俞甲子、石凡、潘爱民 ', '程序员的自我修养 ', 71, '2022-06-22', 5, 26, 'Good Java Book'), (25, 'Remzi H. Arpaci-Dusseau', '操作系统导论', 72.8, '2022-06-22', 0, 32, 'Good Java Book'), (26, '艾伦 A. A. 多诺万', 'Go程序设计语言', 73, '2022-06-22', 0, 33, 'Good Java Book'), (27, '左程云', '程序员代码面试指南', 74.5, '2022-06-22', 0, 34, 'Good Java Book'), (28, '斯基恩纳', '算法设计手册', 75, '2022-06-22', 0, 35, 'Good Java Book'), (29, 'W.Richard Stevens', 'TCP/IP详解', 76, '2022-06-22', 0, 36, 'Good Java Book'), (30, 'Alexander A. Stepanov', '数学与泛型编程', 77, '2022-06-22', 0, 100, 'Good Java Book'), (31, 'Robert Sedgewick', '算法（第4版）', 78.9, '2022-06-22', 0, 100, 'Good Java Book'), (32, 'David Flanagan', 'JavaScript权威指南', 79.9, '2022-06-22', 20, 80, 'Good Java Book');
COMMIT;

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade`  (
  `tradeid` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `tradetime` datetime NOT NULL,
  `delivery_state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '已下单',
  PRIMARY KEY (`tradeid`) USING BTREE,
  INDEX `user_id_fk`(`userid` ASC) USING BTREE,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of trade
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tradeitem
-- ----------------------------
DROP TABLE IF EXISTS `tradeitem`;
CREATE TABLE `tradeitem`  (
  `itemid` int NOT NULL AUTO_INCREMENT,
  `bookid` int NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `tradeid` int NULL DEFAULT NULL,
  `delivery_state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '已下单',
  PRIMARY KEY (`itemid`) USING BTREE,
  INDEX `book_id_fk`(`bookid` ASC) USING BTREE,
  INDEX `trade_id_fk`(`tradeid` ASC) USING BTREE,
  CONSTRAINT `book_id_fk` FOREIGN KEY (`bookid`) REFERENCES `mybooks` (`Id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `trade_id_fk` FOREIGN KEY (`tradeid`) REFERENCES `trade` (`tradeid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tradeitem
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '12345',
  `isAdmin` tinyint NULL DEFAULT 0,
  `accountid` int NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE,
  INDEX `account_id_fk`(`accountid` ASC) USING BTREE,
  CONSTRAINT `account_id_fk` FOREIGN KEY (`accountid`) REFERENCES `account` (`accountid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
BEGIN;
INSERT INTO `userinfo` (`userid`, `username`, `password`, `isAdmin`, `accountid`) VALUES (1, 'Tom', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 1, 1), (2, 'Jack', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 0, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
