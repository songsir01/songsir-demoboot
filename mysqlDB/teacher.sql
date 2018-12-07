/*
Navicat MySQL Data Transfer

Source Server         : songsir
Source Server Version : 50552
Source Host           : localhost:3306
Source Database       : songsirsdbtwo

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2018-12-07 14:30:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '李四', '33');
INSERT INTO `teacher` VALUES ('2', '张三', '22');
INSERT INTO `teacher` VALUES ('3', '王五', '44');
