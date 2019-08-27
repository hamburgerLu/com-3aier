/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80017
Source Host           : localhost:3306
Source Database       : 3aier

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2019-08-28 00:46:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_3aier_article
-- ----------------------------
DROP TABLE IF EXISTS `t_3aier_article`;
CREATE TABLE `t_3aier_article` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `content` text COLLATE utf8mb4_bin,
  `creater` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updater` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `page_view` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_3aier_article
-- ----------------------------

-- ----------------------------
-- Table structure for t_3aier_article_file
-- ----------------------------
DROP TABLE IF EXISTS `t_3aier_article_file`;
CREATE TABLE `t_3aier_article_file` (
  `id` int(11) NOT NULL,
  `article_id` int(11) DEFAULT NULL,
  `file_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `file_address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `delete_flag` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_3aier_article_file
-- ----------------------------

-- ----------------------------
-- Table structure for t_3aier_user
-- ----------------------------
DROP TABLE IF EXISTS `t_3aier_user`;
CREATE TABLE `t_3aier_user` (
  `id` int(11) NOT NULL,
  `user_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `pass_word` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `delete_flag` smallint(6) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of t_3aier_user
-- ----------------------------
