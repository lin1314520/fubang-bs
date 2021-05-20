/*
 Navicat Premium Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : froms_three2

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 19/05/2021 20:14:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for school_college_class_major
-- ----------------------------
DROP TABLE IF EXISTS `school_college_class_major`;
CREATE TABLE `school_college_class_major`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `pid` int(11) NULL DEFAULT NULL COMMENT 'pid',
  `nike_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `is_valid` int(2) NULL DEFAULT 0 COMMENT '是否有效（0-有效，1-无效）',
  `data_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据类型,学校：school，学院：college，班级:class , 专业：major',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学校-学院-班级-专业表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_college_class_major
-- ----------------------------
INSERT INTO `school_college_class_major` VALUES (1, 0, '清华大学', 0, 'school', '2021-05-19 17:44:14');
INSERT INTO `school_college_class_major` VALUES (2, 1, '信息学院', 0, 'college', '2021-05-19 17:44:29');
INSERT INTO `school_college_class_major` VALUES (3, 2, '网络18-21212班', 0, 'class', '2021-05-19 17:44:55');
INSERT INTO `school_college_class_major` VALUES (4, 3, '网络安全专业', 0, 'major', '2021-05-19 17:45:09');

-- ----------------------------
-- Table structure for school_union_user
-- ----------------------------
DROP TABLE IF EXISTS `school_union_user`;
CREATE TABLE `school_union_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `school_id` int(11) NOT NULL COMMENT '学校id',
  `is_valid` int(2) NULL DEFAULT 0 COMMENT '是否有效（0-有效，1-无效）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学校和人员关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_union_user
-- ----------------------------
INSERT INTO `school_union_user` VALUES (1, 1, 3, 0, '2021-05-19 17:49:33');

-- ----------------------------
-- Table structure for sys_user_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login`;
CREATE TABLE `sys_user_login`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(222) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `user_num` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `pass_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `avatar` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `union_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联合id',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信openId',
  `nick_name` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `is_valid` int(2) NULL DEFAULT 0 COMMENT '是否有效（0-有效，1-无效）',
  `user_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户类型,超级管理员：admin，辅导员：counselor，评审员:reviewers',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_login
-- ----------------------------
INSERT INTO `sys_user_login` VALUES (1, '王平安', '13114788', 'wangpingan', '123456', 'https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqBoKrcdMoqKbhHOWDHYhNFgMzu00IqOA5iafATKsZqOGn6Mp7452Yxib8POPV7yic5RUfFD2AEgeMFw/132', NULL, NULL, '牛逼23', 0, 'admin', '2021-05-19 17:37:08');

-- ----------------------------
-- Table structure for user_report
-- ----------------------------
DROP TABLE IF EXISTS `user_report`;
CREATE TABLE `user_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `school_id` int(11) NOT NULL COMMENT '学校id',
  `report_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告名称',
  `img_url` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告图片URL',
  `report_year` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告年份',
  `img_status` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告状态',
  `report_type` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告类别',
  `report_ranking` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告排名',
  `report_credit` varchar(555) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告学分',
  `is_valid` int(2) NULL DEFAULT 0 COMMENT '是否有效（0-有效，1-无效）',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户提交报告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_report
-- ----------------------------
INSERT INTO `user_report` VALUES (1, 1, 1, '省赛报告123', 'http://ceshi-yunmell.oss-cn-beijing.aliyuncs.com/cancreate/2021/5/19/7DA77A30BC474809B5C9BA5CFD0A609A.jpg', '', '公示期', '国家级', '12名', '23分', 0, '2021-05-19 17:17:16');

SET FOREIGN_KEY_CHECKS = 1;
