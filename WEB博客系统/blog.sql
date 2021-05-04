/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.193.128
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.193.128:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 04/05/2021 15:25:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for boke
-- ----------------------------
DROP TABLE IF EXISTS `boke`;
CREATE TABLE `boke`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `c_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `c_id`(`c_id`) USING BTREE,
  CONSTRAINT `c_id` FOREIGN KEY (`c_id`) REFERENCES `cata` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of boke
-- ----------------------------
INSERT INTO `boke` VALUES (1, '测试', '测试<b>内容</b>', '2020-07-31 09:24:44', 2);
INSERT INTO `boke` VALUES (2, '222', '<p>大赛<img src=\"http://localhost:8081/assets/libs/layui/images/face/48.gif\" alt=\"[伤心]\"><img src=\"http://localhost:8081/upload/imgs/20200731/1596187721782_717.png\" alt=\"1596187721782_717.png\"></p>', '2020-08-01 01:18:48', 1);

-- ----------------------------
-- Table structure for cata
-- ----------------------------
DROP TABLE IF EXISTS `cata`;
CREATE TABLE `cata`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cata
-- ----------------------------
INSERT INTO `cata` VALUES (1, '休闲日记', '2020-07-31 10:20:49');
INSERT INTO `cata` VALUES (2, '技术记录', '2020-07-31 10:21:01');
INSERT INTO `cata` VALUES (3, '家人生活', '2020-07-31 10:21:14');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nick` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `b_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `b_id`(`b_id`) USING BTREE,
  CONSTRAINT `b_id` FOREIGN KEY (`b_id`) REFERENCES `boke` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 'hello', '744621980@qq.com', 'yg', '2020-07-31 17:57:45', 1);
INSERT INTO `comment` VALUES (2, '测试下', '744621980@qq.com', '测试', '2020-08-01 01:15:36', 1);
INSERT INTO `comment` VALUES (4, '测试 ', '744621980@qq.com', '测试 ', '2020-08-01 01:17:04', 1);
INSERT INTO `comment` VALUES (5, '测试 ', '744621980@qq.com', '测试 ', '2020-08-01 01:17:56', 1);
INSERT INTO `comment` VALUES (6, '****', '744621980@qq.com', '测试 ', '2020-08-01 01:18:12', 2);
INSERT INTO `comment` VALUES (7, 'ssadsaa', '744621980@qq.com', 'yg', '2020-08-01 01:29:07', 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `code` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户编码',
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
  `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户密码',
  `gender` int(10) NULL DEFAULT NULL COMMENT '性别（1:女、 2:男）',
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机',
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '系统管理员', '123456', 2, '13688889999', '北京市海淀区成府路207号', 'admin', 'view,edit');
INSERT INTO `user` VALUES (2, 'liming', '李明', '123456', 1, '13688884457', '北京市东城区前门东大街9号', 'user', 'view');

SET FOREIGN_KEY_CHECKS = 1;
