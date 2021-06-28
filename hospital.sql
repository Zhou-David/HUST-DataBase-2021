/*
 Navicat MySQL Data Transfer

 Source Server         : MySQL-Test
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : hospital

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 28/06/2021 18:57:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 入院信息
-- ----------------------------
DROP TABLE IF EXISTS `入院信息`;
CREATE TABLE `入院信息`  (
  `id` int NOT NULL,
  `入住时间` datetime NOT NULL,
  `病人` int NULL DEFAULT NULL,
  `办理人员` int NOT NULL,
  `病房` int NOT NULL,
  `state` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_入院办理人员`(`办理人员`) USING BTREE,
  INDEX `fk_入住病房`(`病房`) USING BTREE,
  INDEX `入院信息_fk`(`病人`) USING BTREE,
  CONSTRAINT `fk_入住病房` FOREIGN KEY (`病房`) REFERENCES `病房信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_入院办理人员` FOREIGN KEY (`办理人员`) REFERENCES `医生信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `入院信息_fk` FOREIGN KEY (`病人`) REFERENCES `病人信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 入院信息
-- ----------------------------
INSERT INTO `入院信息` VALUES (1, '2021-06-01 18:04:02', 5, 1, 3, 1);
INSERT INTO `入院信息` VALUES (2, '2021-06-15 18:07:15', 5, 1, 2, 1);
INSERT INTO `入院信息` VALUES (3, '2021-06-16 19:24:59', 6, 1, 3, 1);
INSERT INTO `入院信息` VALUES (4, '2021-06-18 14:34:52', 1, 2, 2, 1);
INSERT INTO `入院信息` VALUES (5, '2021-06-18 14:35:10', 7, 2, 5, 0);

-- ----------------------------
-- Table structure for 出院信息
-- ----------------------------
DROP TABLE IF EXISTS `出院信息`;
CREATE TABLE `出院信息`  (
  `id` int NOT NULL,
  `病人` int NOT NULL,
  `办理人员` int NOT NULL,
  `出院时间` datetime NOT NULL,
  `费用` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_出院病人`(`病人`) USING BTREE,
  INDEX `fk_出院办理人员`(`办理人员`) USING BTREE,
  CONSTRAINT `fk_出院办理人员` FOREIGN KEY (`办理人员`) REFERENCES `医生信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_出院病人` FOREIGN KEY (`病人`) REFERENCES `病人信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 出院信息
-- ----------------------------
INSERT INTO `出院信息` VALUES (1, 6, 1, '2021-06-16 19:31:02', 220);

-- ----------------------------
-- Table structure for 医生信息
-- ----------------------------
DROP TABLE IF EXISTS `医生信息`;
CREATE TABLE `医生信息`  (
  `id` int NOT NULL,
  `姓名` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `性别` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `出生日期` date NULL DEFAULT NULL,
  `入职日期` date NULL DEFAULT NULL,
  `所属科室` int NULL DEFAULT NULL,
  `职务` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `是否为专家` int NULL DEFAULT NULL,
  `电话号码` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `电子邮箱` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `挂号费` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_医生所在科室`(`所属科室`) USING BTREE,
  CONSTRAINT `fk_医生所在科室` FOREIGN KEY (`所属科室`) REFERENCES `科室信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 医生信息
-- ----------------------------
INSERT INTO `医生信息` VALUES (1, '周大伟', '男', '1986-06-02', '2008-06-17', 1, '主任医师', 1, '15607219051', 'u201815553@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (2, '李波', '男', '1984-06-09', '2007-06-01', 2, '主任医师', 1, '15607216232', 'u201812345@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (3, '杨平', '女', '1992-10-19', '2019-03-01', 3, '主任医师', 1, '13267540091', 'u201812346@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (4, '杨海清', '女', '1990-09-12', '2018-11-12', 4, '主任医师', 1, '13209874301', 'u201812347@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (5, '王越建', '男', '1983-03-19', '2006-02-01', 5, '主任医师', 1, '15234678900', 'u201815556@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (6, '王利祥', '男', '1980-07-11', '2007-02-19', 6, '主任医师', 1, '13267940091', 'u201812378@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (7, '丁冬晴', '女', '1981-02-12', '2008-09-11', 7, '主任医师', 1, '15467890211', 'u201823671@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (8, '张丽红', '女', '1982-05-11', '2007-09-24', 8, '主任医师', 1, '12719238872', 'u201812987@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (9, '孙淼云', '女', '1983-07-22', '2006-06-23', 9, '主任医师', 1, '13426789901', 'u201823468@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (10, '孙立军', '男', '1989-03-14', '2010-08-17', 10, '主任医师', 1, '15782906631', 'u201817802@hust.edu.com', 100);
INSERT INTO `医生信息` VALUES (11, '李文渊', '男', '1989-06-03', '2007-06-14', 1, '普通医师', 0, '15687900023', 'u201816578@hust.edu.com', 20);
INSERT INTO `医生信息` VALUES (12, '未绑定信息', '男', '2021-06-16', '2021-06-16', 1, '未绑定信息', 0, '未绑定信息', '未绑定信息', 0);

-- ----------------------------
-- Table structure for 取药单
-- ----------------------------
DROP TABLE IF EXISTS `取药单`;
CREATE TABLE `取药单`  (
  `id` int NOT NULL,
  `取药时间` datetime NULL DEFAULT NULL,
  `费用` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 取药单
-- ----------------------------
INSERT INTO `取药单` VALUES (1, '2021-06-15 16:41:44', 40.5);
INSERT INTO `取药单` VALUES (2, '2021-06-15 16:59:56', 246);
INSERT INTO `取药单` VALUES (3, '2021-06-15 17:07:40', 43.5);
INSERT INTO `取药单` VALUES (4, '2021-06-15 18:04:02', 260);
INSERT INTO `取药单` VALUES (5, '2021-06-15 18:07:15', 260);
INSERT INTO `取药单` VALUES (6, '2021-06-16 19:24:59', 58);
INSERT INTO `取药单` VALUES (7, '2021-06-18 14:34:52', 246);
INSERT INTO `取药单` VALUES (8, '2021-06-18 14:35:10', 43);
INSERT INTO `取药单` VALUES (9, '2021-06-18 14:57:44', 261);

-- ----------------------------
-- Table structure for 挂号信息
-- ----------------------------
DROP TABLE IF EXISTS `挂号信息`;
CREATE TABLE `挂号信息`  (
  `id` int NOT NULL,
  `就诊病人` int NOT NULL,
  `医生` int NOT NULL,
  `挂号时间` datetime NOT NULL,
  `挂号费` int NOT NULL,
  `是否为专家号` tinyint(1) NULL DEFAULT NULL,
  `状态` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_挂号病人`(`就诊病人`) USING BTREE,
  INDEX `fk_挂号医生`(`医生`) USING BTREE,
  CONSTRAINT `fk_挂号医生` FOREIGN KEY (`医生`) REFERENCES `医生信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_挂号病人` FOREIGN KEY (`就诊病人`) REFERENCES `病人信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 挂号信息
-- ----------------------------
INSERT INTO `挂号信息` VALUES (1, 1, 2, '2021-06-06 15:25:57', 200, 1, 1);
INSERT INTO `挂号信息` VALUES (2, 1, 2, '2021-06-06 15:41:40', 200, 1, 1);
INSERT INTO `挂号信息` VALUES (3, 2, 1, '2021-06-06 15:44:35', 200, 1, 1);
INSERT INTO `挂号信息` VALUES (4, 2, 1, '2021-06-06 16:23:57', 200, 1, 1);
INSERT INTO `挂号信息` VALUES (5, 3, 1, '2021-06-06 17:19:31', 200, 1, 1);
INSERT INTO `挂号信息` VALUES (6, 4, 1, '2021-06-06 18:42:23', 200, 1, 1);
INSERT INTO `挂号信息` VALUES (7, 5, 1, '2021-06-07 11:31:26', 200, 1, 1);
INSERT INTO `挂号信息` VALUES (8, 6, 1, '2021-06-16 19:23:55', 100, 1, 1);
INSERT INTO `挂号信息` VALUES (9, 7, 2, '2021-06-18 14:23:19', 100, 1, 1);
INSERT INTO `挂号信息` VALUES (10, 8, 1, '2021-06-18 14:54:17', 100, 1, 1);
INSERT INTO `挂号信息` VALUES (11, 9, 1, '2021-06-18 14:57:18', 100, 1, 0);
INSERT INTO `挂号信息` VALUES (12, 10, 1, '2021-06-19 15:40:33', 100, 1, 0);

-- ----------------------------
-- Table structure for 用户
-- ----------------------------
DROP TABLE IF EXISTS `用户`;
CREATE TABLE `用户`  (
  `id` int NOT NULL,
  `用户名` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `密码` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `权限等级` int NULL DEFAULT NULL,
  `医师` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `用户名`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 用户
-- ----------------------------
INSERT INTO `用户` VALUES (1, '001', '123', 0, 1);
INSERT INTO `用户` VALUES (2, '002', '123', 0, 2);
INSERT INTO `用户` VALUES (3, '003', '123', 0, 3);
INSERT INTO `用户` VALUES (4, '004', '123', 0, 4);
INSERT INTO `用户` VALUES (5, '005', '123', 0, 5);
INSERT INTO `用户` VALUES (6, '006', '123', 0, 6);
INSERT INTO `用户` VALUES (7, '007', '123', 0, 7);
INSERT INTO `用户` VALUES (8, '008', '123', 0, 8);
INSERT INTO `用户` VALUES (9, '009', '123', 0, 9);
INSERT INTO `用户` VALUES (10, '010', '123', 0, 10);
INSERT INTO `用户` VALUES (11, '100', '123', 1, 0);
INSERT INTO `用户` VALUES (12, '012', '123', 0, 11);
INSERT INTO `用户` VALUES (13, '013', '123', 0, 12);

-- ----------------------------
-- Table structure for 病人信息
-- ----------------------------
DROP TABLE IF EXISTS `病人信息`;
CREATE TABLE `病人信息`  (
  `id` int NOT NULL,
  `证件号` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `姓名` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `性别` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `出生日期` date NULL DEFAULT NULL,
  `联系方式` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `病人信息_证件号_uindex`(`证件号`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 病人信息
-- ----------------------------
INSERT INTO `病人信息` VALUES (1, '421023199901012319', '孟彦康', '男', '1999-01-01', '12345678901');
INSERT INTO `病人信息` VALUES (2, '421023200011241923', '李萌萌', '女', '2000-11-24', '12345678901');
INSERT INTO `病人信息` VALUES (3, '421023199908192319', '刘美丽', '女', '1999-08-19', '12345678901');
INSERT INTO `病人信息` VALUES (4, '421023200006232314', '陈梦雷', '男', '2000-06-23', '12345678901');
INSERT INTO `病人信息` VALUES (5, '421023200001011234', '李浩嘉', '男', '2000-01-01', '12345678901');
INSERT INTO `病人信息` VALUES (6, '421023199902031233', '吴亦凡', '男', '1999-02-03', '12345678901');
INSERT INTO `病人信息` VALUES (7, '420123200909011023', '梨花', '女', '2009-09-01', '12345678901');
INSERT INTO `病人信息` VALUES (8, '421023200102032012', '张三', '男', '2001-02-03', '12345678901');
INSERT INTO `病人信息` VALUES (9, '401023199909082011', 'aaa', '男', '1999-09-08', '12345678901');
INSERT INTO `病人信息` VALUES (10, '420021200009011002', '李四', '男', '2000-09-01', '13276889062');

-- ----------------------------
-- Table structure for 病房信息
-- ----------------------------
DROP TABLE IF EXISTS `病房信息`;
CREATE TABLE `病房信息`  (
  `id` int NOT NULL,
  `病房号` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `病房容量` int NULL DEFAULT NULL,
  `房间类型` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `入住人数` int NULL DEFAULT NULL,
  `备注` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 病房信息
-- ----------------------------
INSERT INTO `病房信息` VALUES (1, 'N101', 5, '普通', 0, 'null');
INSERT INTO `病房信息` VALUES (2, 'N102', 4, '普通', 1, '停用');
INSERT INTO `病房信息` VALUES (3, 'N103', 4, '普通', 0, NULL);
INSERT INTO `病房信息` VALUES (4, 'N104', 1, '重症监护', 0, 'COVID19');
INSERT INTO `病房信息` VALUES (5, 'S101', 4, '普通', 1, NULL);
INSERT INTO `病房信息` VALUES (6, 'S102', 4, '普通', 0, NULL);
INSERT INTO `病房信息` VALUES (7, 'S103', 5, '普通', 0, 'null');
INSERT INTO `病房信息` VALUES (8, 'S104', 1, '重症监护', 0, NULL);

-- ----------------------------
-- Table structure for 科室信息
-- ----------------------------
DROP TABLE IF EXISTS `科室信息`;
CREATE TABLE `科室信息`  (
  `id` int NOT NULL,
  `科室名称` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `系主任` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_系主任`(`系主任`) USING BTREE,
  CONSTRAINT `fk_系主任` FOREIGN KEY (`系主任`) REFERENCES `医生信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 科室信息
-- ----------------------------
INSERT INTO `科室信息` VALUES (1, '神经内科', 1);
INSERT INTO `科室信息` VALUES (2, '心血管内科', 2);
INSERT INTO `科室信息` VALUES (3, '呼吸内科', 3);
INSERT INTO `科室信息` VALUES (4, '消化内科', 4);
INSERT INTO `科室信息` VALUES (5, '普外科', 5);
INSERT INTO `科室信息` VALUES (6, '神经外科', 6);
INSERT INTO `科室信息` VALUES (7, '骨科', 7);
INSERT INTO `科室信息` VALUES (8, '妇科', 8);
INSERT INTO `科室信息` VALUES (9, '儿科综合', 9);
INSERT INTO `科室信息` VALUES (10, '皮肤病科', 10);

-- ----------------------------
-- Table structure for 药品信息
-- ----------------------------
DROP TABLE IF EXISTS `药品信息`;
CREATE TABLE `药品信息`  (
  `id` int NOT NULL,
  `名称` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `剂型` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `规格` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `使用说明` char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `参考价格` double NULL DEFAULT NULL,
  `类型` char(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 药品信息
-- ----------------------------
INSERT INTO `药品信息` VALUES (1, '感冒灵颗粒', '颗粒剂', '每袋重10g(含对乙酰氨基酚0.2g)', '开水冲服，一次10克(1袋)，一日3次。', 15, '中药');
INSERT INTO `药品信息` VALUES (2, '苯磺酸氨氯地平片', '片剂', '5mg*7s', '通常本品治疗高血压的起始剂量为5mg，每日一次，最大剂量为10mg，每日一次', 26.5, '化学药品');
INSERT INTO `药品信息` VALUES (3, '利伐沙班片', '片剂', '20mg*7s', '推荐剂量为口服利伐沙班10mg，每日1次', 246, '化学药品');
INSERT INTO `药品信息` VALUES (4, '硝苯地平控释片', '片剂(控释)', '30mg*7s', '治疗时应尽可能按个体情况用药。依据患者的临床情况，给予不同的基础用药剂量', 39, '化学药品');
INSERT INTO `药品信息` VALUES (5, '阿司匹林肠溶片', '片剂', '100mg*30s', '口服，肠溶片应饭前用适量水送服。本品为肠溶片，必须整片服用。在治疗心肌梗死时，第一片应捣碎或嚼碎后服用', 16.5, '化学药品');
INSERT INTO `药品信息` VALUES (6, '阿托伐他汀钙片', '片剂', '10mg*7s*2板', '常用的起始剂量为10mg每日一次。剂量调整时间间隔应为4周或更长。本品最大剂量为80mg每日一次', 22, '化学药品');
INSERT INTO `药品信息` VALUES (7, '辛伐他汀片', '片剂', '20mg*14s', '一般起始剂量为每天10mg，晚间顿服。对于胆固醇水平轻至中度升高的患者，起始剂量为每天5mg', 4.8, '化学药品');
INSERT INTO `药品信息` VALUES (8, '阿卡波糖片', '片剂', '50mg*30s', '用餐前即刻整片吞服或与前几口食物一起咀嚼服用，剂量因人而异。一般推荐剂量为：起始剂量为每次50mg，每日3次。以后逐渐增加至每次0.1g，每日3次。', 27, '化学药品');
INSERT INTO `药品信息` VALUES (9, '六味地黄丸', '丸剂(浓缩丸)', '每8丸重1.44克(每8丸相当于饮片3g)', '口服。一次8丸，一日3次。', 19, '中药');
INSERT INTO `药品信息` VALUES (10, '疏肝益阳胶囊', '胶囊剂', '0.25g*36粒', '口服。一次4粒，一日3次，4周为一疗程。', 49, '中药');

-- ----------------------------
-- Table structure for 药物清单
-- ----------------------------
DROP TABLE IF EXISTS `药物清单`;
CREATE TABLE `药物清单`  (
  `id` int NOT NULL,
  `单号` int NOT NULL,
  `药品` int NOT NULL,
  `费用` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_所取药品`(`药品`) USING BTREE,
  INDEX `药物清单_fk_取药单号`(`单号`) USING BTREE,
  CONSTRAINT `fk_所取药品` FOREIGN KEY (`药品`) REFERENCES `药品信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `药物清单_fk_取药单号` FOREIGN KEY (`单号`) REFERENCES `取药单` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 药物清单
-- ----------------------------
INSERT INTO `药物清单` VALUES (1, 1, 1, 14);
INSERT INTO `药物清单` VALUES (2, 1, 2, 26.5);
INSERT INTO `药物清单` VALUES (3, 2, 3, 246);
INSERT INTO `药物清单` VALUES (4, 3, 5, 16.5);
INSERT INTO `药物清单` VALUES (5, 3, 8, 27);
INSERT INTO `药物清单` VALUES (6, 4, 1, 14);
INSERT INTO `药物清单` VALUES (7, 4, 3, 246);
INSERT INTO `药物清单` VALUES (8, 5, 1, 14);
INSERT INTO `药物清单` VALUES (9, 5, 3, 246);
INSERT INTO `药物清单` VALUES (10, 6, 1, 15);
INSERT INTO `药物清单` VALUES (11, 6, 2, 26.5);
INSERT INTO `药物清单` VALUES (12, 6, 5, 16.5);
INSERT INTO `药物清单` VALUES (13, 7, 3, 246);
INSERT INTO `药物清单` VALUES (14, 8, 2, 26.5);
INSERT INTO `药物清单` VALUES (15, 8, 5, 16.5);
INSERT INTO `药物清单` VALUES (16, 9, 1, 15);
INSERT INTO `药物清单` VALUES (17, 9, 3, 246);

-- ----------------------------
-- Table structure for 诊断信息
-- ----------------------------
DROP TABLE IF EXISTS `诊断信息`;
CREATE TABLE `诊断信息`  (
  `id` int NOT NULL,
  `病人` int NOT NULL,
  `医生` int NOT NULL,
  `诊断使时间` datetime NULL DEFAULT NULL,
  `开药单号` int NULL DEFAULT NULL,
  `入院单号` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_就诊病人`(`病人`) USING BTREE,
  INDEX `fk_诊断医生`(`医生`) USING BTREE,
  INDEX `fk_所开药方`(`开药单号`) USING BTREE,
  INDEX `fk_建议入院`(`入院单号`) USING BTREE,
  CONSTRAINT `fk_就诊病人` FOREIGN KEY (`病人`) REFERENCES `病人信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_建议入院` FOREIGN KEY (`入院单号`) REFERENCES `入院信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_所开药方` FOREIGN KEY (`开药单号`) REFERENCES `取药单` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_诊断医生` FOREIGN KEY (`医生`) REFERENCES `医生信息` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 诊断信息
-- ----------------------------
INSERT INTO `诊断信息` VALUES (1, 2, 1, '2021-06-15 16:41:45', 1, NULL);
INSERT INTO `诊断信息` VALUES (2, 4, 1, '2021-06-15 17:07:40', 3, NULL);
INSERT INTO `诊断信息` VALUES (3, 5, 1, '2021-06-15 18:05:57', 4, 1);
INSERT INTO `诊断信息` VALUES (4, 5, 1, '2021-06-15 18:07:15', 5, 2);
INSERT INTO `诊断信息` VALUES (5, 6, 1, '2021-06-16 19:24:59', 6, 3);
INSERT INTO `诊断信息` VALUES (6, 1, 2, '2021-06-18 14:34:52', 7, 4);
INSERT INTO `诊断信息` VALUES (7, 1, 2, '2021-06-18 14:35:00', NULL, NULL);
INSERT INTO `诊断信息` VALUES (8, 7, 2, '2021-06-18 14:35:10', 8, 5);
INSERT INTO `诊断信息` VALUES (9, 8, 1, '2021-06-18 14:57:44', 9, NULL);

SET FOREIGN_KEY_CHECKS = 1;
