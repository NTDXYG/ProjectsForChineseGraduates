/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.85.132:3306
 Source Schema         : wuye

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 07/03/2020 18:17:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(10) NOT NULL COMMENT '管理员',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机',
  `sex` int(10) NULL DEFAULT NULL COMMENT '性别',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '17805054371', 0, '744621980@qq.com');

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type` int(10) NULL DEFAULT NULL COMMENT '0：电梯；1：楼梯',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, 'A', 0);
INSERT INTO `building` VALUES (2, 'B', 0);
INSERT INTO `building` VALUES (3, 'C', 1);
INSERT INTO `building` VALUES (4, 'D', 1);

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '停车位名称',
  `type` int(10) NULL DEFAULT NULL COMMENT '地面或者地下',
  `status` int(10) NULL DEFAULT NULL COMMENT '是否为空',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES (1, '车位1', 0, 1);
INSERT INTO `car` VALUES (2, '车位2', 0, 0);
INSERT INTO `car` VALUES (3, '车位3', 0, 1);
INSERT INTO `car` VALUES (4, '车位4', 0, 0);
INSERT INTO `car` VALUES (5, '车位5', 0, 0);
INSERT INTO `car` VALUES (6, '车位6', 0, 0);
INSERT INTO `car` VALUES (7, '车位7', 0, 0);
INSERT INTO `car` VALUES (8, '车位8', 0, 0);
INSERT INTO `car` VALUES (9, '车位9', 0, 0);
INSERT INTO `car` VALUES (10, '车位10', 0, 0);
INSERT INTO `car` VALUES (11, '车位11', 0, 0);
INSERT INTO `car` VALUES (12, '车位12', 0, 0);
INSERT INTO `car` VALUES (13, '车位13', 0, 0);
INSERT INTO `car` VALUES (14, '车位14', 0, 0);
INSERT INTO `car` VALUES (15, '车位15', 0, 0);
INSERT INTO `car` VALUES (16, '车位16', 0, 0);
INSERT INTO `car` VALUES (17, '车位17', 0, 0);
INSERT INTO `car` VALUES (18, '车位18', 0, 0);
INSERT INTO `car` VALUES (19, '车位19', 0, 0);
INSERT INTO `car` VALUES (20, '车位20', 1, 1);
INSERT INTO `car` VALUES (21, '车位21', 1, 0);
INSERT INTO `car` VALUES (22, '车位22', 1, 0);
INSERT INTO `car` VALUES (23, '车位23', 1, 0);
INSERT INTO `car` VALUES (24, '车位24', 1, 0);
INSERT INTO `car` VALUES (25, '车位25', 1, 0);
INSERT INTO `car` VALUES (26, '车位26', 1, 0);
INSERT INTO `car` VALUES (27, '车位27', 1, 0);
INSERT INTO `car` VALUES (28, '车位28', 1, 0);
INSERT INTO `car` VALUES (29, '车位29', 1, 0);
INSERT INTO `car` VALUES (30, '车位30', 1, 0);
INSERT INTO `car` VALUES (31, '车位31', 1, 0);
INSERT INTO `car` VALUES (32, '车位32', 1, 0);
INSERT INTO `car` VALUES (33, '车位33', 1, 0);
INSERT INTO `car` VALUES (34, '车位34', 1, 0);
INSERT INTO `car` VALUES (35, '车位35', 1, 0);
INSERT INTO `car` VALUES (36, '车位36', 1, 0);
INSERT INTO `car` VALUES (37, '车位37', 1, 0);
INSERT INTO `car` VALUES (38, '车位38', 1, 0);
INSERT INTO `car` VALUES (39, '车位39', 1, 0);
INSERT INTO `car` VALUES (40, '车位40', 1, 0);
INSERT INTO `car` VALUES (41, '车位41', 1, 0);
INSERT INTO `car` VALUES (42, '车位42', 1, 0);
INSERT INTO `car` VALUES (43, '车位43', 1, 0);
INSERT INTO `car` VALUES (44, '车位44', 1, 0);
INSERT INTO `car` VALUES (45, '车位45', 1, 0);
INSERT INTO `car` VALUES (46, '车位46', 1, 0);
INSERT INTO `car` VALUES (47, '车位47', 1, 0);
INSERT INTO `car` VALUES (48, '车位48', 1, 0);
INSERT INTO `car` VALUES (49, '车位49', 1, 0);
INSERT INTO `car` VALUES (50, '车位50', 1, 0);
INSERT INTO `car` VALUES (51, '车位51', 1, 0);
INSERT INTO `car` VALUES (52, '车位52', 1, 0);
INSERT INTO `car` VALUES (53, '车位53', 1, 0);
INSERT INTO `car` VALUES (54, '车位54', 1, 0);
INSERT INTO `car` VALUES (55, '车位55', 1, 0);
INSERT INTO `car` VALUES (56, '车位56', 1, 0);
INSERT INTO `car` VALUES (57, '车位57', 1, 0);
INSERT INTO `car` VALUES (58, '车位58', 1, 0);
INSERT INTO `car` VALUES (59, '车位59', 1, 0);
INSERT INTO `car` VALUES (60, '车位60', 1, 0);

-- ----------------------------
-- Table structure for danyuan
-- ----------------------------
DROP TABLE IF EXISTS `danyuan`;
CREATE TABLE `danyuan`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `building_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `building`(`building_id`) USING BTREE,
  CONSTRAINT `building` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of danyuan
-- ----------------------------
INSERT INTO `danyuan` VALUES (1, 'A1单元', 1);
INSERT INTO `danyuan` VALUES (2, 'A2单元', 1);
INSERT INTO `danyuan` VALUES (3, 'B1单元', 2);
INSERT INTO `danyuan` VALUES (4, 'B2单元', 2);
INSERT INTO `danyuan` VALUES (5, 'C1单元', 3);
INSERT INTO `danyuan` VALUES (6, 'C2单元', 3);
INSERT INTO `danyuan` VALUES (7, 'D1单元', 4);
INSERT INTO `danyuan` VALUES (8, 'D2单元', 4);

-- ----------------------------
-- Table structure for gonggao
-- ----------------------------
DROP TABLE IF EXISTS `gonggao`;
CREATE TABLE `gonggao`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公告标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公告内容',
  `createBy` int(10) NULL DEFAULT NULL COMMENT '创建者',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateBy` int(10) NULL DEFAULT NULL COMMENT '修改者',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(10) NULL DEFAULT NULL COMMENT '是否展示',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `create`(`createBy`) USING BTREE,
  INDEX `update`(`updateBy`) USING BTREE,
  CONSTRAINT `create` FOREIGN KEY (`createBy`) REFERENCES `admin` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `update` FOREIGN KEY (`updateBy`) REFERENCES `admin` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gonggao
-- ----------------------------
INSERT INTO `gonggao` VALUES (1, '公告一', '测试一下', 1, '2020-03-03 09:39:46', 1, '2020-03-03 06:11:25', 0);
INSERT INTO `gonggao` VALUES (2, '公告二', '再测试以下', 1, '2020-03-03 09:40:06', 1, '2020-03-03 01:59:44', 1);

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (1, '水电');
INSERT INTO `payment` VALUES (2, '煤气');
INSERT INTO `payment` VALUES (3, '物业');
INSERT INTO `payment` VALUES (4, '停车费');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int(10) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否解决',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `5`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (1, '测试1', 1, '0', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (2, '测试2', 2, '1', '2020-03-03 03:34:40', '完美解决');
INSERT INTO `repair` VALUES (3, '测试3', 3, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (4, '测试4', 4, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (5, '测试5', 5, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (6, '测试6', 6, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (7, '测试7', 7, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (8, '测试8', 8, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (9, '测试9', 9, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (10, '测试10', 10, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (11, '测试11', 11, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (12, '测试12', 12, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (13, '测试13', 13, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (14, '测试14', 14, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (15, '测试15', 15, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (16, '测试16', 16, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (17, '测试17', 17, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (18, '测试18', 18, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (19, '测试19', 19, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (20, '测试20', 20, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (21, '测试21', 21, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (22, '测试22', 22, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (23, '测试23', 23, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (24, '测试24', 24, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (25, '测试25', 25, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (26, '测试26', 26, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (27, '测试27', 27, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (28, '测试28', 28, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (29, '测试29', 29, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (30, '测试30', 30, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (31, '测试31', 31, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (32, '测试32', 32, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (33, '测试33', 33, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (34, '测试34', 34, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (35, '测试35', 35, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (36, '测试36', 36, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (37, '测试37', 37, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (38, '测试38', 38, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (39, '测试39', 39, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (40, '测试40', 40, '1', '2020-03-03 03:34:40', NULL);
INSERT INTO `repair` VALUES (41, '我要维修水龙头。', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '房屋名称',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '房屋面积',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否为空',
  `danyuan_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `danyuan`(`danyuan_id`) USING BTREE,
  CONSTRAINT `danyuan` FOREIGN KEY (`danyuan_id`) REFERENCES `danyuan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 148 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (50, '101', '97', '1', 1);
INSERT INTO `room` VALUES (51, '102', '96', '1', 1);
INSERT INTO `room` VALUES (52, '201', '96', '1', 1);
INSERT INTO `room` VALUES (53, '202', '96', '1', 1);
INSERT INTO `room` VALUES (54, '301', '96', '0', 1);
INSERT INTO `room` VALUES (55, '302', '96', '0', 1);
INSERT INTO `room` VALUES (56, '401', '96', '0', 1);
INSERT INTO `room` VALUES (57, '402', '96', '0', 1);
INSERT INTO `room` VALUES (58, '501', '96', '0', 1);
INSERT INTO `room` VALUES (59, '502', '96', '0', 1);
INSERT INTO `room` VALUES (60, '601', '96', '0', 1);
INSERT INTO `room` VALUES (61, '602', '96', '0', 1);
INSERT INTO `room` VALUES (62, '101', '96', '0', 2);
INSERT INTO `room` VALUES (63, '102', '96', '0', 2);
INSERT INTO `room` VALUES (64, '201', '96', '0', 2);
INSERT INTO `room` VALUES (65, '202', '96', '0', 2);
INSERT INTO `room` VALUES (66, '301', '96', '0', 2);
INSERT INTO `room` VALUES (67, '302', '96', '0', 2);
INSERT INTO `room` VALUES (68, '401', '96', '0', 2);
INSERT INTO `room` VALUES (69, '402', '96', '0', 2);
INSERT INTO `room` VALUES (70, '501', '96', '0', 2);
INSERT INTO `room` VALUES (71, '502', '96', '0', 2);
INSERT INTO `room` VALUES (72, '601', '96', '0', 2);
INSERT INTO `room` VALUES (73, '602', '96', '0', 2);
INSERT INTO `room` VALUES (74, '101', '96', '0', 3);
INSERT INTO `room` VALUES (75, '102', '96', '0', 3);
INSERT INTO `room` VALUES (76, '201', '96', '0', 3);
INSERT INTO `room` VALUES (77, '202', '96', '0', 3);
INSERT INTO `room` VALUES (78, '301', '96', '0', 3);
INSERT INTO `room` VALUES (79, '302', '96', '0', 3);
INSERT INTO `room` VALUES (80, '401', '96', '0', 3);
INSERT INTO `room` VALUES (81, '402', '96', '0', 3);
INSERT INTO `room` VALUES (82, '501', '96', '0', 3);
INSERT INTO `room` VALUES (83, '502', '96', '0', 3);
INSERT INTO `room` VALUES (84, '601', '96', '0', 3);
INSERT INTO `room` VALUES (85, '602', '96', '0', 3);
INSERT INTO `room` VALUES (86, '101', '96', '0', 4);
INSERT INTO `room` VALUES (87, '102', '96', '0', 4);
INSERT INTO `room` VALUES (88, '201', '96', '0', 4);
INSERT INTO `room` VALUES (89, '202', '96', '0', 4);
INSERT INTO `room` VALUES (90, '301', '96', '0', 4);
INSERT INTO `room` VALUES (91, '302', '96', '0', 4);
INSERT INTO `room` VALUES (92, '401', '96', '0', 4);
INSERT INTO `room` VALUES (93, '402', '96', '0', 4);
INSERT INTO `room` VALUES (94, '501', '96', '0', 4);
INSERT INTO `room` VALUES (95, '502', '96', '0', 4);
INSERT INTO `room` VALUES (96, '601', '96', '0', 4);
INSERT INTO `room` VALUES (97, '602', '96', '0', 4);
INSERT INTO `room` VALUES (98, '101', '96', '0', 5);
INSERT INTO `room` VALUES (99, '102', '96', '0', 5);
INSERT INTO `room` VALUES (100, '201', '96', '0', 5);
INSERT INTO `room` VALUES (101, '202', '96', '0', 5);
INSERT INTO `room` VALUES (102, '301', '96', '0', 5);
INSERT INTO `room` VALUES (103, '302', '96', '0', 5);
INSERT INTO `room` VALUES (104, '401', '96', '0', 5);
INSERT INTO `room` VALUES (105, '402', '96', '0', 5);
INSERT INTO `room` VALUES (106, '501', '96', '0', 5);
INSERT INTO `room` VALUES (107, '502', '96', '0', 5);
INSERT INTO `room` VALUES (108, '601', '96', '0', 5);
INSERT INTO `room` VALUES (109, '602', '96', '0', 5);
INSERT INTO `room` VALUES (110, '101', '96', '0', 6);
INSERT INTO `room` VALUES (111, '102', '96', '0', 6);
INSERT INTO `room` VALUES (112, '201', '96', '0', 6);
INSERT INTO `room` VALUES (113, '202', '96', '0', 6);
INSERT INTO `room` VALUES (114, '301', '96', '0', 6);
INSERT INTO `room` VALUES (115, '302', '96', '0', 6);
INSERT INTO `room` VALUES (116, '401', '96', '0', 6);
INSERT INTO `room` VALUES (117, '402', '96', '0', 6);
INSERT INTO `room` VALUES (118, '501', '96', '0', 6);
INSERT INTO `room` VALUES (119, '502', '96', '0', 6);
INSERT INTO `room` VALUES (120, '601', '96', '0', 6);
INSERT INTO `room` VALUES (121, '602', '96', '0', 6);
INSERT INTO `room` VALUES (122, '101', '96', '0', 7);
INSERT INTO `room` VALUES (123, '102', '96', '0', 7);
INSERT INTO `room` VALUES (124, '201', '96', '0', 7);
INSERT INTO `room` VALUES (125, '202', '96', '0', 7);
INSERT INTO `room` VALUES (126, '301', '96', '0', 7);
INSERT INTO `room` VALUES (127, '302', '96', '0', 7);
INSERT INTO `room` VALUES (128, '401', '96', '0', 7);
INSERT INTO `room` VALUES (129, '402', '96', '0', 7);
INSERT INTO `room` VALUES (130, '501', '96', '0', 7);
INSERT INTO `room` VALUES (131, '502', '96', '0', 7);
INSERT INTO `room` VALUES (132, '601', '96', '0', 7);
INSERT INTO `room` VALUES (133, '602', '96', '0', 7);
INSERT INTO `room` VALUES (134, '101', '96', '0', 8);
INSERT INTO `room` VALUES (135, '102', '96', '0', 8);
INSERT INTO `room` VALUES (136, '201', '96', '0', 8);
INSERT INTO `room` VALUES (137, '202', '96', '0', 8);
INSERT INTO `room` VALUES (138, '301', '96', '0', 8);
INSERT INTO `room` VALUES (139, '302', '96', '0', 8);
INSERT INTO `room` VALUES (140, '401', '96', '0', 8);
INSERT INTO `room` VALUES (141, '402', '96', '0', 8);
INSERT INTO `room` VALUES (142, '501', '96', '0', 8);
INSERT INTO `room` VALUES (143, '502', '96', '0', 8);
INSERT INTO `room` VALUES (144, '601', '96', '0', 8);
INSERT INTO `room` VALUES (145, '602', '96', '0', 8);

-- ----------------------------
-- Table structure for tousu
-- ----------------------------
DROP TABLE IF EXISTS `tousu`;
CREATE TABLE `tousu`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `user_id` int(10) NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `6`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tousu
-- ----------------------------
INSERT INTO `tousu` VALUES (1, '测试1', 1, 1, '2020-03-03 03:34:48', '已经解决了');
INSERT INTO `tousu` VALUES (2, '测试2', 2, 1, '2020-03-03 03:34:48', '213e131');
INSERT INTO `tousu` VALUES (3, '测试3', 3, 0, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (4, '测试4', 4, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (5, '测试5', 5, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (6, '测试6', 6, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (7, '测试7', 7, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (8, '测试8', 8, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (9, '测试9', 9, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (10, '测试10', 10, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (11, '测试11', 11, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (12, '测试12', 12, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (13, '测试13', 13, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (14, '测试14', 14, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (15, '测试15', 15, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (16, '测试16', 16, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (17, '测试17', 17, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (18, '测试18', 18, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (19, '测试19', 19, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (20, '测试20', 20, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (21, '测试21', 21, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (22, '测试22', 22, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (23, '测试23', 23, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (24, '测试24', 24, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (25, '测试25', 25, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (26, '测试26', 26, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (27, '测试27', 27, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (28, '测试28', 28, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (29, '测试29', 29, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (30, '测试30', 30, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (31, '测试31', 31, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (32, '测试32', 32, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (33, '测试33', 33, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (34, '测试34', 34, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (35, '测试35', 35, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (36, '测试36', 36, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (37, '测试37', 37, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (38, '测试38', 38, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (39, '测试39', 39, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (40, '测试40', 40, 1, '2020-03-03 03:34:48', NULL);
INSERT INTO `tousu` VALUES (42, '我要投诉！！', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `sex` int(10) NULL DEFAULT NULL COMMENT '性别',
  `status` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1', '123456', '17805052221', 1, 1);
INSERT INTO `user` VALUES (2, '2', '123456', '17805052222', 0, 1);
INSERT INTO `user` VALUES (3, '3', '123456', '17805052223', 0, 1);
INSERT INTO `user` VALUES (4, '4', '123456', '17805052224', 0, 1);
INSERT INTO `user` VALUES (5, '5', '123456', '17805052225', 0, 1);
INSERT INTO `user` VALUES (6, '6', '123456', '17805052226', 0, 1);
INSERT INTO `user` VALUES (7, '7', '123456', '17805052227', 0, 1);
INSERT INTO `user` VALUES (8, '8', '123456', '17805052228', 0, 1);
INSERT INTO `user` VALUES (9, '9', '123456', '17805052229', 0, 1);
INSERT INTO `user` VALUES (10, '10', '123456', '17805051110', 0, 1);
INSERT INTO `user` VALUES (11, '11', '123456', '17805051111', 0, 1);
INSERT INTO `user` VALUES (12, '12', '123456', '17805051112', 0, 1);
INSERT INTO `user` VALUES (13, '13', '123456', '17805051113', 0, 1);
INSERT INTO `user` VALUES (14, '14', '123456', '17805051114', 0, 1);
INSERT INTO `user` VALUES (15, '15', '123456', '17805051115', 0, 1);
INSERT INTO `user` VALUES (16, '16', '123456', '17805051116', 0, 1);
INSERT INTO `user` VALUES (17, '17', '123456', '17805051117', 0, 1);
INSERT INTO `user` VALUES (18, '18', '123456', '17805051118', 0, 1);
INSERT INTO `user` VALUES (19, '19', '123456', '17805051119', 0, 1);
INSERT INTO `user` VALUES (20, '20', '123456', '17805051120', 0, 1);
INSERT INTO `user` VALUES (21, '21', '123456', '17805051121', 0, 1);
INSERT INTO `user` VALUES (22, '22', '123456', '17805051122', 0, 1);
INSERT INTO `user` VALUES (23, '23', '123456', '17805051123', 0, 1);
INSERT INTO `user` VALUES (24, '24', '123456', '17805051124', 0, 1);
INSERT INTO `user` VALUES (25, '25', '123456', '17805051125', 0, 1);
INSERT INTO `user` VALUES (26, '26', '123456', '17805051126', 0, 1);
INSERT INTO `user` VALUES (27, '27', '123456', '17805051127', 0, 1);
INSERT INTO `user` VALUES (28, '28', '123456', '17805051128', 0, 1);
INSERT INTO `user` VALUES (29, '29', '123456', '17805051129', 0, 1);
INSERT INTO `user` VALUES (30, '30', '123456', '17805051130', 0, 1);
INSERT INTO `user` VALUES (31, '31', '123456', '17805051131', 0, 1);
INSERT INTO `user` VALUES (32, '32', '123456', '17805051132', 0, 1);
INSERT INTO `user` VALUES (33, '33', '123456', '17805051133', 0, 1);
INSERT INTO `user` VALUES (34, '34', '123456', '17805051134', 0, 1);
INSERT INTO `user` VALUES (35, '35', '123456', '17805051135', 0, 1);
INSERT INTO `user` VALUES (36, '36', '123456', '17805051136', 0, 1);
INSERT INTO `user` VALUES (37, '37', '123456', '17805051137', 0, 1);
INSERT INTO `user` VALUES (38, '38', '123456', '17805051138', 0, 1);
INSERT INTO `user` VALUES (39, '39', '123456', '17805051139', 0, 1);
INSERT INTO `user` VALUES (40, '40', '123456', '17805051140', 0, 1);
INSERT INTO `user` VALUES (41, '41', '123456', '17805051141', 0, 1);
INSERT INTO `user` VALUES (42, '42', '123456', '17805051142', 0, 1);
INSERT INTO `user` VALUES (43, '43', '123456', '17805051143', 0, 1);
INSERT INTO `user` VALUES (44, '44', '123456', '17805051144', 0, 1);
INSERT INTO `user` VALUES (45, '45', '123456', '17805051145', 0, 1);
INSERT INTO `user` VALUES (46, '46', '123456', '17805051146', 0, 1);
INSERT INTO `user` VALUES (47, '47', '123456', '17805051147', 0, 1);
INSERT INTO `user` VALUES (48, '48', '123456', '17805051148', 0, 1);
INSERT INTO `user` VALUES (49, '49', '123456', '17805051149', 0, 1);
INSERT INTO `user` VALUES (50, '50', '123456', '17805051150', 0, 1);
INSERT INTO `user` VALUES (51, '51', '123456', '17805051151', 0, 1);
INSERT INTO `user` VALUES (52, '52', '123456', '17805051152', 0, 1);
INSERT INTO `user` VALUES (53, '53', '123456', '17805051153', 0, 1);
INSERT INTO `user` VALUES (54, '54', '123456', '17805051154', 0, 1);
INSERT INTO `user` VALUES (55, '55', '123456', '17805051155', 0, 1);
INSERT INTO `user` VALUES (56, '56', '123456', '17805051156', 0, 1);
INSERT INTO `user` VALUES (57, '57', '123456', '17805051157', 0, 1);
INSERT INTO `user` VALUES (58, '58', '123456', '17805051158', 0, 1);
INSERT INTO `user` VALUES (59, '59', '123456', '17805051159', 0, 1);
INSERT INTO `user` VALUES (60, '60', '123456', '17805051160', 0, 1);
INSERT INTO `user` VALUES (61, '61', '123456', '17805051161', 0, 1);
INSERT INTO `user` VALUES (62, '62', '123456', '17805051162', 0, 1);
INSERT INTO `user` VALUES (63, '63', '123456', '17805051163', 0, 1);
INSERT INTO `user` VALUES (64, '64', '123456', '17805051164', 0, 1);
INSERT INTO `user` VALUES (65, '65', '123456', '17805051165', 0, 1);
INSERT INTO `user` VALUES (66, '66', '123456', '17805051166', 0, 1);
INSERT INTO `user` VALUES (67, '67', '123456', '17805051167', 0, 1);
INSERT INTO `user` VALUES (68, '68', '123456', '17805051168', 0, 1);
INSERT INTO `user` VALUES (69, '69', '123456', '17805051169', 0, 1);
INSERT INTO `user` VALUES (70, '70', '123456', '17805051170', 0, 1);
INSERT INTO `user` VALUES (71, '71', '123456', '17805051171', 0, 1);
INSERT INTO `user` VALUES (72, '72', '123456', '17805051172', 0, 1);
INSERT INTO `user` VALUES (73, '73', '123456', '17805051173', 0, 1);
INSERT INTO `user` VALUES (74, '74', '123456', '17805051174', 0, 1);
INSERT INTO `user` VALUES (75, '75', '123456', '17805051175', 0, 1);
INSERT INTO `user` VALUES (76, '76', '123456', '17805051176', 0, 1);
INSERT INTO `user` VALUES (77, '77', '123456', '17805051177', 0, 1);
INSERT INTO `user` VALUES (78, '78', '123456', '17805051178', 0, 1);
INSERT INTO `user` VALUES (79, '79', '123456', '17805051179', 0, 1);
INSERT INTO `user` VALUES (80, '80', '123456', '17805051180', 0, 1);
INSERT INTO `user` VALUES (81, 'demo', '123456', '17805051181', 0, 1);

-- ----------------------------
-- Table structure for user_car
-- ----------------------------
DROP TABLE IF EXISTS `user_car`;
CREATE TABLE `user_car`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL,
  `car_id` int(10) NULL DEFAULT NULL,
  `inTime` datetime(0) NULL DEFAULT NULL,
  `outTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `2`(`car_id`) USING BTREE,
  INDEX `1`(`user_id`) USING BTREE,
  CONSTRAINT `1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `2` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_car
-- ----------------------------
INSERT INTO `user_car` VALUES (1, 1, 1, '2020-03-03 19:37:44', '2020-03-03 19:37:54');
INSERT INTO `user_car` VALUES (2, 2, 2, '2020-03-03 19:37:56', '2020-03-04 05:25:08');
INSERT INTO `user_car` VALUES (3, 1, 4, '2020-03-04 04:37:44', '2020-03-04 04:38:37');
INSERT INTO `user_car` VALUES (4, 1, 3, '2020-03-04 04:38:37', '2020-03-04 04:41:53');
INSERT INTO `user_car` VALUES (5, 1, 1, '2020-03-04 04:41:53', '2020-03-04 04:44:12');
INSERT INTO `user_car` VALUES (6, 1, 3, '2020-03-04 04:44:12', '2020-03-04 05:23:24');
INSERT INTO `user_car` VALUES (7, 1, 1, '2020-03-04 05:25:22', NULL);
INSERT INTO `user_car` VALUES (8, 2, 20, '2020-03-04 05:25:26', NULL);
INSERT INTO `user_car` VALUES (9, 3, 2, '2020-03-04 12:11:22', '2020-03-04 12:11:28');
INSERT INTO `user_car` VALUES (10, 3, 3, '2020-03-04 12:11:28', NULL);

-- ----------------------------
-- Table structure for user_payment
-- ----------------------------
DROP TABLE IF EXISTS `user_payment`;
CREATE TABLE `user_payment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL,
  `payment_id` int(10) NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `11`(`user_id`) USING BTREE,
  INDEX `12`(`payment_id`) USING BTREE,
  CONSTRAINT `11` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `12` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_payment
-- ----------------------------
INSERT INTO `user_payment` VALUES (1, 1, 1, '100', '2020-03-04 05:47:03', 1);
INSERT INTO `user_payment` VALUES (2, 1, 3, '1100', '2020-03-04 07:49:42', 1);
INSERT INTO `user_payment` VALUES (3, 1, 2, '100', '2020-03-04 12:11:42', 0);

-- ----------------------------
-- Table structure for user_room
-- ----------------------------
DROP TABLE IF EXISTS `user_room`;
CREATE TABLE `user_room`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户',
  `room_id` int(10) NULL DEFAULT NULL COMMENT '房屋',
  `inTime` datetime(0) NULL DEFAULT NULL COMMENT '入住时间',
  `outTime` datetime(0) NULL DEFAULT NULL COMMENT '离去时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `3`(`user_id`) USING BTREE,
  INDEX `4`(`room_id`) USING BTREE,
  CONSTRAINT `4` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_room
-- ----------------------------
INSERT INTO `user_room` VALUES (1, 1, 50, '2020-03-03 20:16:05', '2020-03-04 05:21:10');
INSERT INTO `user_room` VALUES (2, 2, 51, '2020-03-03 20:16:13', '2020-03-04 05:25:06');
INSERT INTO `user_room` VALUES (3, 3, 52, '2020-03-04 04:10:23', '2020-03-04 12:10:54');
INSERT INTO `user_room` VALUES (4, 1, 53, '2020-03-04 04:13:28', '2020-03-04 04:44:25');
INSERT INTO `user_room` VALUES (5, 4, 50, '2020-03-04 04:13:43', '2020-03-04 08:43:42');
INSERT INTO `user_room` VALUES (6, 1, 54, '2020-03-04 04:44:25', '2020-03-04 05:23:27');
INSERT INTO `user_room` VALUES (7, 2, 51, '2020-03-04 05:25:14', NULL);
INSERT INTO `user_room` VALUES (8, 1, 50, '2020-03-04 05:25:18', '2020-03-04 08:44:30');
INSERT INTO `user_room` VALUES (9, 4, 53, '2020-03-04 08:44:26', NULL);
INSERT INTO `user_room` VALUES (10, 1, 50, '2020-03-04 08:44:34', NULL);
INSERT INTO `user_room` VALUES (11, 3, 52, '2020-03-04 12:11:12', NULL);

-- ----------------------------
-- Procedure structure for add_gu_memory
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_gu_memory`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `add_gu_memory`()
BEGIN  
  DECLARE i INT DEFAULT 1;
	SET i = 2;
    WHILE (i <= 60 ) DO
			INSERT into `car`(`name`,`type`,`status` ) VALUEs (CONCAT('车位',i),0,0);
			set i=i+1;
    END WHILE;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
