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

 Date: 06/07/2022 00:12:41
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
BEGIN;
INSERT INTO `account` (`accountid`, `balance`) VALUES (1, 7342), (2, 27603.9);
COMMIT;

-- ----------------------------
-- Table structure for mybooks
-- ----------------------------
DROP TABLE IF EXISTS `mybooks`;
CREATE TABLE `mybooks`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(30) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `book_name` varchar(30) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `price` float NOT NULL,
  `publish_date` date NOT NULL,
  `sales_amount` int NOT NULL,
  `stock` int NOT NULL,
  `info` text CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of mybooks
-- ----------------------------
BEGIN;
INSERT INTO `mybooks` (`id`, `author`, `book_name`, `price`, `publish_date`, `sales_amount`, `stock`, `info`) VALUES (1, 'Joshua Bloch', 'Effective Java', 50, '2022-06-22', 46, 154, 'Good Java Book'), (2, '周志明', '深入理解Java虚拟机', 60.2, '2022-06-22', 22, 78, 'Good Java Book'), (3, '埃克尔', 'Java编程思想', 50.5, '2022-06-22', 17, 183, 'Good Java Book'), (4, 'Cay S.Horstmann', 'Java核心技术', 51, '2022-06-22', 1, 0, 'Good Java Book'), (5, 'Brian Goetz', 'Java并发编程实战', 52, '2022-06-22', 8, 142, 'Good Java Book'), (6, 'Kathy Sierra', 'Head First Java', 53, '2022-06-22', 0, 100, 'Good Java Book'), (7, '结城浩', 'Java多线程设计模式', 54, '2022-06-22', 2, 12, 'Good Java Book'), (8, '杨冠宝', '阿里巴巴Java开发手册', 55.9, '2022-06-22', 3, 12, 'Good Java Book'), (9, 'Budi Kurniawan', '深入剖析Tomcat', 56, '2022-06-22', 0, 16, 'Good Java Book'), (10, '拉佛 / 计晓云 ', 'Java数据结构和算法', 57, '2022-06-22', 0, 17, 'Good Java Book'), (11, 'Mark Allen Weiss', '数据结构与算法分析', 58.7, '2022-06-22', 0, 0, 'Good Java Book'), (12, 'Craig Walls', 'Spring实战', 59, '2022-06-22', 1, 18, 'Good Java Book'), (13, 'Kenneth L. Calvert', 'Java TCP/IP Socket', 60, '2022-06-22', 7, 13, 'Good Java Book'), (14, '李刚', '疯狂Java讲义', 61, '2022-06-22', 9, 12, 'Good Java Book'), (15, '翟陆续、薛宾田', 'Java并发编程之美', 62, '2022-06-22', 9, 13, 'Good Java Book'), (16, 'Benjamin J. Evans', 'Java程序员修炼之道', 63, '2022-06-22', 0, 23, 'Good Java Book'), (17, '克雷格沃斯', 'Spring Boot', 64, '2022-06-22', 0, 24, 'Good Java Book'), (18, '高性能MySQL', '高性能MySQL', 65.1, '2022-06-22', 1, 24, 'Good Java Book'), (19, 'Sasba Pacbev', 'MySQL核心技术', 66, '2022-06-22', 0, 26, 'Good Java Book'), (20, 'Jon Bentley', '编程珠玑', 67, '2022-06-22', 0, 27, 'Good Java Book'), (21, 'Robert C. Martin', '代码整洁之道', 68.9, '2022-06-22', 0, 28, 'Good Java Book'), (22, 'Randal E.Bryant', '深入理解计算机系统', 69, '2022-06-22', 5, 24, 'Good Java Book'), (23, 'Brian W. Kernighan', 'C程序设计语言', 70, '2022-06-22', 6, 24, 'Good Java Book'), (24, '俞甲子、石凡、潘爱民 ', '程序员的自我修养 ', 71, '2022-06-22', 5, 26, 'Good Java Book'), (25, 'Remzi H. Arpaci-Dusseau', '操作系统导论', 72.8, '2022-06-22', 1, 31, 'Good Java Book'), (26, '艾伦 A. A. 多诺万', 'Go程序设计语言', 73, '2022-06-22', 0, 33, 'Good Java Book'), (27, '左程云', '程序员代码面试指南', 74.5, '2022-06-22', 0, 34, 'Good Java Book'), (28, '斯基恩纳', '算法设计手册', 75, '2022-06-22', 0, 35, 'Good Java Book'), (29, 'W.Richard Stevens', 'TCP/IP详解', 76, '2022-06-22', 0, 36, 'Good Java Book'), (30, 'Alexander A. Stepanov', '数学与泛型编程', 77, '2022-06-22', 0, 100, 'Good Java Book'), (31, 'Robert Sedgewick', '算法（第4版）', 78.9, '2022-06-22', 0, 100, 'Good Java Book'), (32, 'David Flanagan', 'JavaScript权威指南', 79.9, '2022-06-22', 20, 80, 'Good Java Book');
COMMIT;

-- ----------------------------
-- Table structure for trade
-- ----------------------------
DROP TABLE IF EXISTS `trade`;
CREATE TABLE `trade`  (
  `tradeid` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `tradetime` datetime NOT NULL,
  PRIMARY KEY (`tradeid`) USING BTREE,
  INDEX `user_id_fk`(`userid` ASC) USING BTREE,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`userid`) REFERENCES `userinfo` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of trade
-- ----------------------------
BEGIN;
INSERT INTO `trade` (`tradeid`, `userid`, `tradetime`) VALUES (1, 1, '2022-07-05 17:09:56'), (2, 1, '2022-07-05 17:12:06'), (3, 2, '2022-07-05 20:23:43'), (4, 2, '2022-07-05 21:33:07'), (5, 1, '2022-07-05 22:30:56'), (6, 1, '2022-07-05 23:53:35');
COMMIT;

-- ----------------------------
-- Table structure for tradeitem
-- ----------------------------
DROP TABLE IF EXISTS `tradeitem`;
CREATE TABLE `tradeitem`  (
  `itemid` int NOT NULL AUTO_INCREMENT,
  `tradeid` int NULL DEFAULT NULL,
  `bookid` int NULL DEFAULT NULL,
  `order_serial_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `delivery_state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '已下单',
  PRIMARY KEY (`itemid`) USING BTREE,
  INDEX `book_id_fk`(`bookid` ASC) USING BTREE,
  INDEX `trade_id_fk`(`tradeid` ASC) USING BTREE,
  CONSTRAINT `book_id_fk` FOREIGN KEY (`bookid`) REFERENCES `mybooks` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `trade_id_fk` FOREIGN KEY (`tradeid`) REFERENCES `trade` (`tradeid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of tradeitem
-- ----------------------------
BEGIN;
INSERT INTO `tradeitem` (`itemid`, `tradeid`, `bookid`, `order_serial_number`, `quantity`, `delivery_state`) VALUES (1, 1, 1, '123456789111315', 1, '已下单'), (2, 1, 2, '123456789111316', 1, '已下单'), (3, 1, 3, '123456789111317', 1, '已下单'), (4, 2, 5, '123456789111318', 5, '已下单'), (5, 3, 18, '123456789111319', 1, '已下单'), (6, 3, 12, '123456789111320', 1, '已下单'), (7, 3, 15, '123456789111321', 1, '已下单'), (8, 4, 1, '123456789111322', 1, '已下单'), (9, 4, 13, '123456789111323', 1, '已下单'), (10, 5, 8, '123456789111324', 1, '已下单'), (11, 6, 23, '202207052318644', 1, '已下单'), (12, 6, 25, '202207052313696', 1, '已下单');
COMMIT;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET gb2312 COLLATE gb2312_chinese_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '12345',
  `is_admin` tinyint NULL DEFAULT 0,
  `accountid` int NULL DEFAULT NULL,
  PRIMARY KEY (`userid`) USING BTREE,
  INDEX `account_id_fk`(`accountid` ASC) USING BTREE,
  CONSTRAINT `account_id_fk` FOREIGN KEY (`accountid`) REFERENCES `account` (`accountid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
BEGIN;
INSERT INTO `userinfo` (`userid`, `username`, `password`, `is_admin`, `accountid`) VALUES (1, 'Tom', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 1, 1), (2, 'Jack', '96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e', 0, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
