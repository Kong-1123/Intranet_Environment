/*
 Navicat Premium Data Transfer

 Source Server         : Xdmd-Test
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : intranethbkj

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 24/07/2019 18:00:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for check_apply
-- ----------------------------
DROP TABLE IF EXISTS `check_apply`;
CREATE TABLE `check_apply`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `topic_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题名称',
  `topic_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题编号',
  `subject_undertaking_unit_id` int(10) NOT NULL COMMENT '课题承担单位id',
  `unit_nature` int(10) NOT NULL COMMENT '单位性质',
  `project_leader` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题负责人',
  `project_leader_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题负责人联系电话',
  `project_leader_mail` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题负责人联系邮箱',
  `postal_address` varchar(51) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通讯地址',
  `agreement_start_time` date NOT NULL COMMENT '合同开始时间',
  `agreement_end_time` date NOT NULL COMMENT '合同结束时间',
  `application_acceptance_time` date NOT NULL COMMENT '申请验收时间',
  `application_acceptance_mode` int(5) NOT NULL COMMENT '申请验收方式',
  `application_acceptance_place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请验收地点',
  `acceptance_contact` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验收联系人',
  `acceptance_contact_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验收联系人联系电话',
  `main_content_situation` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主要研究内容完成情况',
  `submission_achievements_situation` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交成果情况',
  `subject_undertaking_unit_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题承担单位意见',
  `environmental_departments_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所在环保部门意见',
  `province_assessment_center_opinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省生态环境评估中心初审意见',
  `competent_department_oinion` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省环保厅主管部门意见',
  `submit_inventory` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交资料清单',
  `acceptance_phase` int(50) NOT NULL COMMENT '验收审核状态（0：等待验收初审 ， 1：等待课题验收  2：课题结束）',
  `create_time` datetime(0) NOT NULL COMMENT '该表创建时间',
  `create_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建该表的人',
  `achievement_url_id` int(11) NULL DEFAULT NULL COMMENT '成果附件上传文件的id',
  `submit_url_id` int(11) NULL DEFAULT NULL COMMENT '提交清单上传文件的id',
  `audit_report_url_id` int(11) NULL DEFAULT NULL COMMENT '审计报告上传文件的id',
  `first_inspection_report_url_id` int(11) NULL DEFAULT NULL COMMENT '初审报告上传文件的id',
  `expert_group_comments_url_id` int(11) NULL DEFAULT NULL COMMENT '专家组意见上传文件的id',
  `expert_acceptance_form_id` int(11) NULL DEFAULT NULL COMMENT '专家验收评议表上传文件的id',
  `application_url_id` int(11) NULL DEFAULT NULL COMMENT '验收申请表上传文件的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_apply
-- ----------------------------
INSERT INTO `check_apply` VALUES (50, '课题名称1', '123', 4, 3, '项目负责人名1', '15588865919', '158965512@qq.com', '公司地址1', '2019-07-01', '2019-07-03', '2019-07-04', 6, '申请验收地点', '验收联系人1', '13566678482', '主要研究内容完成情况1', '提交成果情况1', '课题承担单位意见', '所在环保部门意见1', '省生态环境评估中心初审意见1', '省环保厅主管部门意见1', '提交资料清单1', 0, '2019-07-18 10:15:20', '创建人1', 77, 78, NULL, NULL, NULL, NULL, 76);

-- ----------------------------
-- Table structure for check_apply_state
-- ----------------------------
DROP TABLE IF EXISTS `check_apply_state`;
CREATE TABLE `check_apply_state`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `check_apply_id` int(11) NULL DEFAULT NULL COMMENT '对应验收申请表的id',
  `fist_handler` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '交办人',
  `second_handler` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理人',
  `audit_step` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核步骤',
  `first_handle_time` datetime(0) NULL DEFAULT NULL COMMENT '交办时间',
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `handle_content` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理内容',
  `second_handle_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_apply_state
-- ----------------------------
INSERT INTO `check_apply_state` VALUES (1, 50, '交办人1', '处理人1', '公司提交', '2019-07-11 14:21:21', '已处理', '审核通过', '2019-07-24 14:05:00');
INSERT INTO `check_apply_state` VALUES (2, 50, '处理人1', '处理人2', '验收初审科室', '2019-07-24 14:05:00', '等待处理', NULL, NULL);

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `classification` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类',
  `content` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `classification_id` int(11) NOT NULL COMMENT '分类id',
  `content_id` int(11) NOT NULL COMMENT '内容id',
  `state` int(5) NOT NULL COMMENT '启用（0:逻辑删除  1：启用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES (1, '单位性质', '大专院校', 1, 1, 1);
INSERT INTO `dictionary` VALUES (2, '单位性质', '科研院所', 1, 2, 1);
INSERT INTO `dictionary` VALUES (3, '单位性质', '企业', 1, 3, 1);
INSERT INTO `dictionary` VALUES (4, '单位性质', '其他', 1, 4, 1);
INSERT INTO `dictionary` VALUES (5, '申请验收形式', '会评', 2, 1, 1);
INSERT INTO `dictionary` VALUES (6, '申请验收形式', '函评', 2, 2, 1);
INSERT INTO `dictionary` VALUES (7, '验收提交资料清单', '课题验收申请表', 3, 1, 1);
INSERT INTO `dictionary` VALUES (8, '验收提交资料清单', '课题合同书', 3, 2, 1);
INSERT INTO `dictionary` VALUES (9, '验收提交资料清单', '课题设计书', 3, 3, 1);
INSERT INTO `dictionary` VALUES (10, '验收提交资料清单', '课题工作报告', 3, 4, 1);
INSERT INTO `dictionary` VALUES (11, '验收提交资料清单', '课题经费决算表', 3, 5, 1);
INSERT INTO `dictionary` VALUES (12, '验收提交资料清单', '课题技术报告', 3, 6, 1);
INSERT INTO `dictionary` VALUES (13, '验收提交资料清单', '其他（发表学术论文、用户意见、监测报告等）', 3, 7, 1);
INSERT INTO `dictionary` VALUES (14, '成果形式', '论文著作', 4, 1, 1);
INSERT INTO `dictionary` VALUES (15, '成果形式', '研究（咨询）报告', 4, 2, 1);
INSERT INTO `dictionary` VALUES (16, '成果形式', '新产品', 4, 3, 1);
INSERT INTO `dictionary` VALUES (17, '成果形式', '新装置', 4, 4, 1);
INSERT INTO `dictionary` VALUES (18, '成果形式', '新材料', 4, 5, 1);
INSERT INTO `dictionary` VALUES (19, '成果形式', '新工艺(或新方法、新模式)', 4, 6, 1);
INSERT INTO `dictionary` VALUES (20, '成果形式', '计算机软件', 4, 7, 1);
INSERT INTO `dictionary` VALUES (21, '成果形式', '技术标准', 4, 8, 1);
INSERT INTO `dictionary` VALUES (22, '成果形式', '专利', 4, 9, 1);
INSERT INTO `dictionary` VALUES (23, '成果形式', '其他', 4, 10, 1);
INSERT INTO `dictionary` VALUES (24, '成果水平', '国际领先', 5, 1, 1);
INSERT INTO `dictionary` VALUES (25, '成果水平', '国际先进', 5, 2, 1);
INSERT INTO `dictionary` VALUES (26, '成果水平', '国际领先', 5, 3, 1);
INSERT INTO `dictionary` VALUES (27, '成果水平', '国内先进', 5, 4, 1);
INSERT INTO `dictionary` VALUES (28, '成果水平', '省内领先', 5, 5, 1);
INSERT INTO `dictionary` VALUES (29, '成果水平', '其他', 5, 6, 1);
INSERT INTO `dictionary` VALUES (30, '工作性质', '科研(教育)', 6, 1, 1);
INSERT INTO `dictionary` VALUES (31, '工作性质', '咨询(设计、规划)', 6, 2, 1);
INSERT INTO `dictionary` VALUES (32, '工作性质', '管理及其他', 6, 3, 1);
INSERT INTO `dictionary` VALUES (33, '专业领域', '水环境治理', 7, 1, 1);
INSERT INTO `dictionary` VALUES (34, '专业领域', '大气环境保护', 7, 2, 1);
INSERT INTO `dictionary` VALUES (35, '专业领域', '土壤环境治理', 7, 3, 1);
INSERT INTO `dictionary` VALUES (36, '专业领域', '自然生态保护', 7, 4, 1);
INSERT INTO `dictionary` VALUES (37, '专业领域', '环境监测监控', 7, 5, 1);
INSERT INTO `dictionary` VALUES (38, '专业领域', '核与辐射防治', 7, 6, 1);
INSERT INTO `dictionary` VALUES (39, '专业领域', '规划与战略研究', 7, 7, 1);
INSERT INTO `dictionary` VALUES (40, '专业领域', '污染预防与控制(清洁生产)', 7, 8, 1);
INSERT INTO `dictionary` VALUES (41, '课题按合同计划进度执行情况', '超额完成', 8, 1, 1);
INSERT INTO `dictionary` VALUES (42, '课题按合同计划进度执行情况', '完成', 8, 2, 1);
INSERT INTO `dictionary` VALUES (43, '课题按合同计划进度执行情况', '基本完成', 8, 3, 1);
INSERT INTO `dictionary` VALUES (44, '课题按合同计划进度执行情况', '未完成', 8, 4, 1);
INSERT INTO `dictionary` VALUES (45, '课题进展情况类型', '超前', 9, 1, 1);
INSERT INTO `dictionary` VALUES (46, '课题进展情况类型', '正常', 9, 2, 1);
INSERT INTO `dictionary` VALUES (47, '课题进展情况类型', '之后', 9, 3, 1);
INSERT INTO `dictionary` VALUES (48, '课题未按时完成原因', '技术变化', 10, 1, 1);
INSERT INTO `dictionary` VALUES (49, '课题未按时完成原因', '经费未落实', 10, 2, 1);
INSERT INTO `dictionary` VALUES (50, '课题未按时完成原因', '项目负责人或技术骨干变动', 10, 3, 1);
INSERT INTO `dictionary` VALUES (51, '课题未按时完成原因', '协作关系影响', 10, 4, 1);
INSERT INTO `dictionary` VALUES (52, '课题未按时完成原因', '其他原因', 10, 5, 1);
INSERT INTO `dictionary` VALUES (53, '汇报情况', '内容清楚', 11, 1, 1);
INSERT INTO `dictionary` VALUES (54, '汇报情况', '内容基本清楚', 11, 2, 1);
INSERT INTO `dictionary` VALUES (55, '汇报情况', '内容不够清楚', 11, 3, 1);
INSERT INTO `dictionary` VALUES (56, '进度执行情况', '超额完成', 12, 1, 1);
INSERT INTO `dictionary` VALUES (57, '进度执行情况', '完成', 12, 2, 1);
INSERT INTO `dictionary` VALUES (58, '进度执行情况', '基本完成', 12, 3, 1);
INSERT INTO `dictionary` VALUES (59, '进度执行情况', '未完成', 12, 4, 1);
INSERT INTO `dictionary` VALUES (60, '课题实施所需条件', '落实', 13, 1, 1);
INSERT INTO `dictionary` VALUES (61, '课题实施所需条件', '基本落实', 13, 2, 1);
INSERT INTO `dictionary` VALUES (62, '课题实施所需条件', '未落实', 13, 3, 1);
INSERT INTO `dictionary` VALUES (63, '技术/经济考核指标', '达到合同规定', 14, 1, 1);
INSERT INTO `dictionary` VALUES (64, '技术/经济考核指标', '基本达到合同规定', 14, 2, 1);
INSERT INTO `dictionary` VALUES (65, '技术/经济考核指标', '未达合同规定', 14, 3, 1);
INSERT INTO `dictionary` VALUES (66, '经费执行情况', '合理', 15, 1, 1);
INSERT INTO `dictionary` VALUES (67, '经费执行情况', '基本合理', 15, 2, 1);
INSERT INTO `dictionary` VALUES (68, '经费执行情况', '不合理', 15, 3, 1);
INSERT INTO `dictionary` VALUES (69, '下一步工作计划', '可行', 16, 1, 1);
INSERT INTO `dictionary` VALUES (70, '下一步工作计划', '基本可性', 16, 2, 1);
INSERT INTO `dictionary` VALUES (71, '下一步工作计划', '不可行', 16, 3, 1);
INSERT INTO `dictionary` VALUES (72, '评级等次', '优秀', 17, 1, 1);
INSERT INTO `dictionary` VALUES (73, '评级等次', '良好', 17, 2, 1);
INSERT INTO `dictionary` VALUES (74, '评级等次', '一般', 17, 3, 1);
INSERT INTO `dictionary` VALUES (75, '评级等次', '较差', 17, 4, 1);
INSERT INTO `dictionary` VALUES (76, '所属类别', '综合师范类', 18, 1, 1);
INSERT INTO `dictionary` VALUES (77, '所属类别', '技术研发类', 18, 2, 1);
INSERT INTO `dictionary` VALUES (78, '所属类别', '重大技术攻关类', 18, 3, 1);
INSERT INTO `dictionary` VALUES (79, '所属领域', '水污染防治领域', 19, 1, 1);
INSERT INTO `dictionary` VALUES (80, '所属领域', '大气污染防治领域', 19, 2, 1);
INSERT INTO `dictionary` VALUES (81, '所属领域', '土壤及地下水污染防治领域', 19, 3, 1);
INSERT INTO `dictionary` VALUES (82, '所属领域', '固废与辐射污染防治领域', 19, 4, 1);
INSERT INTO `dictionary` VALUES (83, '所属领域', '自然与生态领域', 19, 5, 1);
INSERT INTO `dictionary` VALUES (84, '所属领域', '标准政策法规', 19, 6, 1);
INSERT INTO `dictionary` VALUES (85, '所属领域', '其他', 19, 7, 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `is_father` int(10) NULL DEFAULT NULL COMMENT '是否是一级菜单 0：一级 1：二级菜单',
  `father_id` int(11) NULL DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '指南管理', 0, 0);
INSERT INTO `menu` VALUES (2, '指南征集', 1, 1);
INSERT INTO `menu` VALUES (3, '指南汇总', 1, 1);
INSERT INTO `menu` VALUES (4, '指南发布', 1, 1);
INSERT INTO `menu` VALUES (5, '课题立项', 0, 0);
INSERT INTO `menu` VALUES (6, '招标备案', 1, 5);
INSERT INTO `menu` VALUES (7, '课题审批', 1, 5);
INSERT INTO `menu` VALUES (8, '合同管理', 0, 0);
INSERT INTO `menu` VALUES (9, '合同中心', 1, 8);
INSERT INTO `menu` VALUES (10, '合同审批', 1, 8);
INSERT INTO `menu` VALUES (11, '日常管理', 0, 0);
INSERT INTO `menu` VALUES (12, '中期检查发起', 1, 11);
INSERT INTO `menu` VALUES (13, '中期检查查询', 1, 11);
INSERT INTO `menu` VALUES (14, '进展报告', 1, 11);
INSERT INTO `menu` VALUES (15, '重大事项管理', 1, 11);
INSERT INTO `menu` VALUES (16, '课题验收', 0, 0);
INSERT INTO `menu` VALUES (17, '验收申请', 1, 16);
INSERT INTO `menu` VALUES (18, '验收审核', 1, 16);
INSERT INTO `menu` VALUES (19, '课题验收', 1, 16);
INSERT INTO `menu` VALUES (20, '验收结束', 1, 16);
INSERT INTO `menu` VALUES (21, '成果管理', 0, 0);
INSERT INTO `menu` VALUES (22, '成果新增', 1, 21);
INSERT INTO `menu` VALUES (23, '成果库', 1, 21);
INSERT INTO `menu` VALUES (24, '专家管理', 0, 0);
INSERT INTO `menu` VALUES (25, '专家库', 1, 24);
INSERT INTO `menu` VALUES (26, '专家新增', 1, 24);
INSERT INTO `menu` VALUES (27, '信用管理', 0, 0);
INSERT INTO `menu` VALUES (28, '承担单位信用', 1, 27);
INSERT INTO `menu` VALUES (29, '责任人信用', 1, 27);
INSERT INTO `menu` VALUES (30, '专家信用', 1, 27);
INSERT INTO `menu` VALUES (31, '统计分析', 0, 0);
INSERT INTO `menu` VALUES (32, '年度环保科研统计分析', 1, 31);
INSERT INTO `menu` VALUES (33, '成果统计分析', 1, 31);
INSERT INTO `menu` VALUES (34, '课题项目分布', 1, 31);
INSERT INTO `menu` VALUES (35, '通知公告', 0, 0);
INSERT INTO `menu` VALUES (36, '通知公告', 1, 35);
INSERT INTO `menu` VALUES (37, '后台管理', 0, 0);
INSERT INTO `menu` VALUES (38, '个人信息管理', 1, 37);
INSERT INTO `menu` VALUES (39, '管理员管理', 1, 37);

-- ----------------------------
-- Table structure for shiro_company_name
-- ----------------------------
DROP TABLE IF EXISTS `shiro_company_name`;
CREATE TABLE `shiro_company_name`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_company_name
-- ----------------------------
INSERT INTO `shiro_company_name` VALUES (1, '希11');
INSERT INTO `shiro_company_name` VALUES (2, '希1122');
INSERT INTO `shiro_company_name` VALUES (3, '小溪迈德');
INSERT INTO `shiro_company_name` VALUES (4, '小溪迈德2');
INSERT INTO `shiro_company_name` VALUES (5, '小溪迈德888');
INSERT INTO `shiro_company_name` VALUES (6, '小溪迈德8889');
INSERT INTO `shiro_company_name` VALUES (7, '小溪迈德88891');
INSERT INTO `shiro_company_name` VALUES (8, '小溪迈德888912');
INSERT INTO `shiro_company_name` VALUES (9, '小溪迈德888912333');
INSERT INTO `shiro_company_name` VALUES (12, '新溪卖得');
INSERT INTO `shiro_company_name` VALUES (13, '新溪卖得1');

-- ----------------------------
-- Table structure for shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_permission`;
CREATE TABLE `shiro_permission`  (
  `id` int(11) NOT NULL COMMENT '主键id',
  `permission_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role`  (
  `id` int(1) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES (1, '管理员');

-- ----------------------------
-- Table structure for shiro_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_menu`;
CREATE TABLE `shiro_role_menu`  (
  `rid` int(11) NOT NULL COMMENT '角色id',
  `mid` int(11) NOT NULL COMMENT '菜单id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_role_menu
-- ----------------------------
INSERT INTO `shiro_role_menu` VALUES (1, 1);
INSERT INTO `shiro_role_menu` VALUES (1, 5);
INSERT INTO `shiro_role_menu` VALUES (1, 8);
INSERT INTO `shiro_role_menu` VALUES (1, 11);
INSERT INTO `shiro_role_menu` VALUES (1, 16);
INSERT INTO `shiro_role_menu` VALUES (1, 21);
INSERT INTO `shiro_role_menu` VALUES (1, 24);
INSERT INTO `shiro_role_menu` VALUES (1, 27);
INSERT INTO `shiro_role_menu` VALUES (1, 31);
INSERT INTO `shiro_role_menu` VALUES (1, 35);
INSERT INTO `shiro_role_menu` VALUES (1, 37);

-- ----------------------------
-- Table structure for shiro_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_permission`;
CREATE TABLE `shiro_role_permission`  (
  `rid` int(11) NOT NULL COMMENT '角色id',
  `pid` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职位',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(5) NULL DEFAULT NULL COMMENT '是否启用 0：禁止 1：启用',
  `status` int(5) NULL DEFAULT NULL COMMENT '身份判断 0：管理员 1：部长 2：员工',
  `modify` int(5) NULL DEFAULT NULL COMMENT '员工修改登陆名次数 默认是1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES (1, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '2019-07-23 14:16:24', 1, 0, NULL);

-- ----------------------------
-- Table structure for shiro_user_role
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role`  (
  `uid` int(11) NOT NULL COMMENT '用户id',
  `rid` int(11) NOT NULL COMMENT '角色id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES (1, 1);

-- ----------------------------
-- Table structure for upload_file
-- ----------------------------
DROP TABLE IF EXISTS `upload_file`;
CREATE TABLE `upload_file`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `upload_surface_id` int(20) NOT NULL COMMENT '上传文件表Id',
  `upload_file_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件地址',
  `upload_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件名',
  `date_file_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日期文件名',
  `upload_file_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件类型',
  `upload_suffix_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件后缀名',
  `file_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件大小',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of upload_file
-- ----------------------------
INSERT INTO `upload_file` VALUES (49, 40, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-19//成果附件测试.docx', '成果附件测试.docx', '2019-07-19成果附件测试.docx', '验收申请表', 'docx', '11121', '2019-07-19 14:39:36', '创建人');
INSERT INTO `upload_file` VALUES (50, 40, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-19//成果附件测试2.docx', '成果附件测试2.docx', '2019-07-19成果附件测试2.docx', '成果附件', 'docx', '0', '2019-07-19 14:39:36', '创建人');
INSERT INTO `upload_file` VALUES (51, 40, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-19//提交清单3.zip', '提交清单3.zip', '2019-07-19提交清单3.zip', '提交清单', 'zip', '22', '2019-07-19 14:39:36', '创建人');
INSERT INTO `upload_file` VALUES (52, 41, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:24', '创建人');
INSERT INTO `upload_file` VALUES (53, 41, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:24', '创建人');
INSERT INTO `upload_file` VALUES (54, 41, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:24', '创建人');
INSERT INTO `upload_file` VALUES (55, 42, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:26', '创建人');
INSERT INTO `upload_file` VALUES (56, 42, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:26', '创建人');
INSERT INTO `upload_file` VALUES (57, 42, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:26', '创建人');
INSERT INTO `upload_file` VALUES (58, 43, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (59, 43, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (60, 43, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (61, 44, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (62, 44, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (63, 44, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:27', '创建人');
INSERT INTO `upload_file` VALUES (64, 45, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (65, 45, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (66, 45, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (67, 46, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (68, 46, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (69, 46, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:28', '创建人');
INSERT INTO `upload_file` VALUES (70, 47, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (71, 47, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (72, 47, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (73, 48, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (74, 48, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (75, 48, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:29', '创建人');
INSERT INTO `upload_file` VALUES (76, 49, 'D://xdmd_environment//Extranet//公司名//验收申请表//2019-07-18//验收申请表3.docx', '验收申请表3.docx', '2019-07-18验收申请表3.docx', '验收申请表', 'docx', '0', '2019-07-18 16:16:30', '创建人');
INSERT INTO `upload_file` VALUES (77, 49, 'D://xdmd_environment//Extranet//公司名//成果附件//2019-07-18//成果附件3.docx', '成果附件3.docx', '2019-07-18成果附件3.docx', '成果附件', 'docx', '0', '2019-07-18 16:16:30', '创建人');
INSERT INTO `upload_file` VALUES (78, 49, 'D://xdmd_environment//Extranet//公司名//提交清单文件//2019-07-18//提交清单测试.zip', '提交清单测试.zip', '2019-07-18提交清单测试.zip', '提交清单', 'zip', '22', '2019-07-18 16:16:30', '创建人');

SET FOREIGN_KEY_CHECKS = 1;
