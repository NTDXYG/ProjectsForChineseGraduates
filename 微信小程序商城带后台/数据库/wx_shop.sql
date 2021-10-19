/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 192.168.85.132:3306
 Source Schema         : wx_shop

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 07/03/2020 18:07:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123456', '744621980@qq.com', '17805054444', 0);

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_id` int(10) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `1111`(`user_openid`) USING BTREE,
  INDEX `1112`(`goods_id`) USING BTREE,
  CONSTRAINT `1111` FOREIGN KEY (`user_openid`) REFERENCES `user` (`open_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `1112` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 1, '不错不错', '2020-03-07 11:31:47');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `_id` int(10) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type_id` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`_id`) USING BTREE,
  INDEX `type`(`type_id`) USING BTREE,
  CONSTRAINT `type` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'http://localhost:8081/upload/imgs/20200306/1583493859933_151.jpg', '游戏1', '90.99', 1);
INSERT INTO `goods` VALUES (2, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏2', '90.99', 1);
INSERT INTO `goods` VALUES (3, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏3', '90.99', 1);
INSERT INTO `goods` VALUES (4, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏4', '90.99', 1);
INSERT INTO `goods` VALUES (5, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏5', '90.99', 1);
INSERT INTO `goods` VALUES (6, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏6', '90.99', 1);
INSERT INTO `goods` VALUES (7, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏7', '90.99', 1);
INSERT INTO `goods` VALUES (8, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏8', '90.99', 1);
INSERT INTO `goods` VALUES (9, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏9', '90.99', 1);
INSERT INTO `goods` VALUES (10, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏10', '90.99', 1);
INSERT INTO `goods` VALUES (11, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏11', '90.99', 1);
INSERT INTO `goods` VALUES (12, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏12', '90.99', 1);
INSERT INTO `goods` VALUES (13, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏13', '90.99', 1);
INSERT INTO `goods` VALUES (14, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏14', '90.99', 1);
INSERT INTO `goods` VALUES (15, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏15', '90.99', 1);
INSERT INTO `goods` VALUES (16, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏16', '90.99', 1);
INSERT INTO `goods` VALUES (17, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏17', '90.99', 1);
INSERT INTO `goods` VALUES (18, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏18', '90.99', 1);
INSERT INTO `goods` VALUES (19, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏19', '90.99', 1);
INSERT INTO `goods` VALUES (20, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏20', '90.99', 1);
INSERT INTO `goods` VALUES (21, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏21', '90.99', 2);
INSERT INTO `goods` VALUES (22, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏22', '90.99', 2);
INSERT INTO `goods` VALUES (23, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏23', '90.99', 2);
INSERT INTO `goods` VALUES (24, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏24', '90.99', 2);
INSERT INTO `goods` VALUES (25, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏25', '90.99', 2);
INSERT INTO `goods` VALUES (26, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏26', '90.99', 2);
INSERT INTO `goods` VALUES (27, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏27', '90.99', 2);
INSERT INTO `goods` VALUES (28, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏28', '90.99', 2);
INSERT INTO `goods` VALUES (29, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏29', '90.99', 2);
INSERT INTO `goods` VALUES (30, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏30', '90.99', 2);
INSERT INTO `goods` VALUES (31, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏31', '90.99', 2);
INSERT INTO `goods` VALUES (32, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏32', '90.99', 2);
INSERT INTO `goods` VALUES (33, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏33', '90.99', 2);
INSERT INTO `goods` VALUES (34, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏34', '90.99', 2);
INSERT INTO `goods` VALUES (35, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏35', '90.99', 2);
INSERT INTO `goods` VALUES (36, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏36', '90.99', 2);
INSERT INTO `goods` VALUES (37, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏37', '90.99', 2);
INSERT INTO `goods` VALUES (38, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏38', '90.99', 2);
INSERT INTO `goods` VALUES (39, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏39', '90.99', 2);
INSERT INTO `goods` VALUES (40, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏40', '90.99', 2);
INSERT INTO `goods` VALUES (41, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏41', '90.99', 3);
INSERT INTO `goods` VALUES (42, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏42', '90.99', 3);
INSERT INTO `goods` VALUES (43, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏43', '90.99', 3);
INSERT INTO `goods` VALUES (44, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏44', '90.99', 3);
INSERT INTO `goods` VALUES (45, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏45', '90.99', 3);
INSERT INTO `goods` VALUES (46, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏46', '90.99', 3);
INSERT INTO `goods` VALUES (47, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏47', '90.99', 3);
INSERT INTO `goods` VALUES (48, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏48', '90.99', 3);
INSERT INTO `goods` VALUES (49, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏49', '90.99', 3);
INSERT INTO `goods` VALUES (50, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏50', '90.99', 3);
INSERT INTO `goods` VALUES (51, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏51', '90.99', 3);
INSERT INTO `goods` VALUES (52, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏52', '90.99', 3);
INSERT INTO `goods` VALUES (53, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏53', '90.99', 3);
INSERT INTO `goods` VALUES (54, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏54', '90.99', 3);
INSERT INTO `goods` VALUES (55, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏55', '90.99', 3);
INSERT INTO `goods` VALUES (56, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏56', '90.99', 3);
INSERT INTO `goods` VALUES (57, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏57', '90.99', 3);
INSERT INTO `goods` VALUES (58, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏58', '90.99', 3);
INSERT INTO `goods` VALUES (59, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏59', '90.99', 3);
INSERT INTO `goods` VALUES (60, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏60', '90.99', 3);
INSERT INTO `goods` VALUES (61, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏61', '90.99', 4);
INSERT INTO `goods` VALUES (62, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏62', '90.99', 4);
INSERT INTO `goods` VALUES (63, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏63', '90.99', 4);
INSERT INTO `goods` VALUES (64, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏64', '90.99', 4);
INSERT INTO `goods` VALUES (65, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏65', '90.99', 4);
INSERT INTO `goods` VALUES (66, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏66', '90.99', 4);
INSERT INTO `goods` VALUES (67, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏67', '90.99', 4);
INSERT INTO `goods` VALUES (68, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏68', '90.99', 4);
INSERT INTO `goods` VALUES (69, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏69', '90.99', 4);
INSERT INTO `goods` VALUES (70, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏70', '90.99', 4);
INSERT INTO `goods` VALUES (71, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏71', '90.99', 4);
INSERT INTO `goods` VALUES (72, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏72', '90.99', 4);
INSERT INTO `goods` VALUES (73, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏73', '90.99', 4);
INSERT INTO `goods` VALUES (74, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏74', '90.99', 4);
INSERT INTO `goods` VALUES (75, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏75', '90.99', 4);
INSERT INTO `goods` VALUES (76, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏76', '90.99', 4);
INSERT INTO `goods` VALUES (77, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏77', '90.99', 4);
INSERT INTO `goods` VALUES (78, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏78', '90.99', 4);
INSERT INTO `goods` VALUES (79, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏79', '90.99', 4);
INSERT INTO `goods` VALUES (80, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏80', '90.99', 4);
INSERT INTO `goods` VALUES (81, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏81', '90.99', 5);
INSERT INTO `goods` VALUES (82, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏82', '90.99', 5);
INSERT INTO `goods` VALUES (83, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏83', '90.99', 5);
INSERT INTO `goods` VALUES (84, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏84', '90.99', 5);
INSERT INTO `goods` VALUES (85, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏85', '90.99', 5);
INSERT INTO `goods` VALUES (86, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏86', '90.99', 5);
INSERT INTO `goods` VALUES (87, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏87', '90.99', 5);
INSERT INTO `goods` VALUES (88, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏88', '90.99', 5);
INSERT INTO `goods` VALUES (89, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏89', '90.99', 5);
INSERT INTO `goods` VALUES (90, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏90', '90.99', 5);
INSERT INTO `goods` VALUES (91, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏91', '90.99', 5);
INSERT INTO `goods` VALUES (92, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏92', '90.99', 5);
INSERT INTO `goods` VALUES (93, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏93', '90.99', 5);
INSERT INTO `goods` VALUES (94, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏94', '90.99', 5);
INSERT INTO `goods` VALUES (95, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏95', '90.99', 5);
INSERT INTO `goods` VALUES (96, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏96', '90.99', 5);
INSERT INTO `goods` VALUES (97, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏97', '90.99', 5);
INSERT INTO `goods` VALUES (98, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏98', '90.99', 5);
INSERT INTO `goods` VALUES (99, 'https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg', '游戏99', '90.99', 5);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_openid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `goods_id` int(10) NOT NULL,
  `count` int(10) NOT NULL,
  `status` int(10) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`user_openid`) USING BTREE,
  INDEX `goodsid`(`goods_id`) USING BTREE,
  CONSTRAINT `goodsid` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userid` FOREIGN KEY (`user_openid`) REFERENCES `user` (`open_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (31, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 7, 1, 1);
INSERT INTO `order` VALUES (32, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 1, 3, 1);
INSERT INTO `order` VALUES (33, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 2, 2, 1);
INSERT INTO `order` VALUES (34, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 6, 2, 1);
INSERT INTO `order` VALUES (35, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 1, 1, 1);
INSERT INTO `order` VALUES (36, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 1, 1, 1);
INSERT INTO `order` VALUES (37, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 6, 1, 1);
INSERT INTO `order` VALUES (38, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 1, 2, 1);
INSERT INTO `order` VALUES (39, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 2, 1, 1);
INSERT INTO `order` VALUES (40, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 1, 1, 1);
INSERT INTO `order` VALUES (41, 'oFjV55Ikf-vSF-1iIiNkkgfY0geA', 5, 1, 1);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '单机游戏');
INSERT INTO `type` VALUES (2, '手机游戏');
INSERT INTO `type` VALUES (3, '网络游戏');
INSERT INTO `type` VALUES (4, '游戏外设');
INSERT INTO `type` VALUES (5, '测试游戏');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_visit_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后登录时间',
  `session_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'session_key',
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `province` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` tinyint(11) NULL DEFAULT NULL COMMENT '性别',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网名',
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('oFjV55Ikf-vSF-1iIiNkkgfY0geA', '2020-03-06 04:14:12', '2020-03-07 05:25:24', 'MBtagRx0S5iK0aCWx6uNEA==', 'Nantong', 'Jiangsu', 'China', 'https://wx.qlogo.cn/mmopen/vi_32/SBM3xSGBhsSx6cPIBWia5mjypaKkiauVIcYwKVcDoD5wXWDooRMBZBBovKvCeCUcicc3VMME7mQjJealr0VaEmo2w/132', 1, '杨光');

-- ----------------------------
-- Procedure structure for add_gu_memory
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_gu_memory`;
delimiter ;;
CREATE DEFINER=`root`@`%` PROCEDURE `add_gu_memory`()
BEGIN  
  DECLARE i INT DEFAULT 81;
	SET i = 81;
    WHILE (i <= 99 ) DO
			INSERT into `goods`(`image`,`name`,`price`,`type_id` ) VALUEs ('https://yanxuan.nosdn.127.net/cae820a8e9ff3b2a79965e2d00ea44b0.jpeg',CONCAT('游戏',i),90.99,5);
			set i=i+1;
    END WHILE;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
