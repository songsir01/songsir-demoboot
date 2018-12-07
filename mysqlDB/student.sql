/*
Navicat MySQL Data Transfer

Source Server         : songsir
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : songsirsdb

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2018-12-07 14:29:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `sid` int(10) NOT NULL AUTO_INCREMENT,
  `sname` varchar(10) DEFAULT NULL,
  `sage` datetime DEFAULT NULL,
  `ssex` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '小明', '1995-01-01 00:00:00', '男');
INSERT INTO `student` VALUES ('2', '小亮', '1995-12-21 00:00:00', '男');
INSERT INTO `student` VALUES ('3', '小雅', '1995-05-20 00:00:00', '女');
