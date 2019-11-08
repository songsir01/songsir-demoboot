/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : songsri

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2019-11-08 16:53:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test_table
-- ----------------------------
DROP TABLE IF EXISTS `test_table`;
CREATE TABLE `test_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `namee` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `bitthday` varchar(255) DEFAULT NULL,
  `aihao` varchar(255) DEFAULT NULL,
  `xuehao` varchar(255) DEFAULT NULL,
  `mingci` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lianhe` (`namee`,`xuehao`,`mingci`)
) ENGINE=InnoDB AUTO_INCREMENT=5002 DEFAULT CHARSET=utf8;
