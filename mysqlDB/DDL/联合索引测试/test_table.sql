/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : songsri

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2019-11-16 19:03:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for test_table
-- ----------------------------
DROP TABLE IF EXISTS `test_table`;
CREATE TABLE `test_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `namee` varchar(255) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `bitthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `aihao` varchar(255) DEFAULT NULL COMMENT '爱好',
  `xuehao` varchar(255) DEFAULT NULL COMMENT '学号',
  `mingci` int(11) DEFAULT NULL COMMENT '名词',
  PRIMARY KEY (`id`),
  KEY `lianhe` (`namee`,`xuehao`,`mingci`)
) ENGINE=InnoDB AUTO_INCREMENT=5002 DEFAULT CHARSET=utf8;
