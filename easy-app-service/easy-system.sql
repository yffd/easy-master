/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : easy

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-10-24 17:54:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_function`
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `VERSION` int(11) NOT NULL,
  `CREATER` varchar(50) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EDITOR` varchar(50) DEFAULT NULL COMMENT '修改人',
  `EDIT_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `DEL_FLAG` char(1) DEFAULT NULL COMMENT '删除标记，1：删除、0：未删除',
  `FUNC_NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `FUNC_CODE` varchar(100) DEFAULT NULL COMMENT '编号',
  `P_CODE` varchar(100) DEFAULT NULL COMMENT '编号',
  `URL` varchar(200) DEFAULT NULL COMMENT '链接地址',
  `TYPE` char(1) DEFAULT NULL COMMENT 'M:MENU,O:OPERATION',
  `ACTIVE` char(1) DEFAULT NULL COMMENT 'A:ACTIVE,I:INACTIVE',
  `SORT` float(4,1) DEFAULT NULL,
  `ICONCLS` varchar(100) DEFAULT NULL COMMENT '图标样式',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1043 DEFAULT CHARSET=utf8 COMMENT='系统 》 功能表';

-- ----------------------------
-- Records of sys_function
-- ----------------------------
INSERT INTO `sys_function` VALUES ('1004', '0', 'admin', '2017-10-18 17:52:47', 'admin', '2017-10-18 17:52:47', '0', '一级_0', 'code_0', '-1', 'www.baidu.com', 'M', 'I', '1.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1005', '0', 'admin', '2017-10-18 17:52:47', 'admin', '2017-10-18 17:52:47', '0', '二级_0_0', 'code_0_0', 'code_0', 'www.baidu.com', 'M', 'I', '1.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1006', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_0_1', 'code_0_1', 'code_0', 'www.baidu.com', 'M', 'I', '1.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1007', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_0_2', 'code_0_2', 'code_0', 'www.baidu.com', 'M', 'I', '1.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1008', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_0_3', 'code_0_3', 'code_0', 'www.baidu.com', 'M', 'I', '1.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1009', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_0_4', 'code_0_4', 'code_0', 'www.baidu.com', 'M', 'I', '1.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1010', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '一级_1', 'code_1', '-1', 'www.baidu.com', 'M', 'I', '2.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1011', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_1_0', 'code_1_0', 'code_1', 'www.baidu.com', 'M', 'I', '2.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1012', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_1_1', 'code_1_1', 'code_1', 'www.baidu.com', 'M', 'I', '2.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1013', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_1_2', 'code_1_2', 'code_1', 'www.baidu.com', 'M', 'I', '2.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1014', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_1_3', 'code_1_3', 'code_1', 'www.baidu.com', 'M', 'I', '2.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1015', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_1_4', 'code_1_4', 'code_1', 'www.baidu.com', 'M', 'I', '2.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1016', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '一级_2', 'code_2', '-1', 'www.baidu.com', 'M', 'I', '3.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1017', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_2_0', 'code_2_0', 'code_2', 'www.baidu.com', 'M', 'I', '3.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1018', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_2_1', 'code_2_1', 'code_2', 'www.baidu.com', 'M', 'I', '3.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1019', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_2_2', 'code_2_2', 'code_2', 'www.baidu.com', 'M', 'I', '3.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1020', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_2_3', 'code_2_3', 'code_2', 'www.baidu.com', 'M', 'I', '3.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1021', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_2_4', 'code_2_4', 'code_2', 'www.baidu.com', 'M', 'I', '3.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1022', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '一级_3', 'code_3', '-1', 'www.baidu.com', 'M', 'I', '4.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1023', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_3_0', 'code_3_0', 'code_3', 'www.baidu.com', 'M', 'I', '4.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1024', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_3_1', 'code_3_1', 'code_3', 'www.baidu.com', 'M', 'I', '4.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1025', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_3_2', 'code_3_2', 'code_3', 'www.baidu.com', 'M', 'I', '4.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1026', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_3_3', 'code_3_3', 'code_3', 'www.baidu.com', 'M', 'I', '4.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1027', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_3_4', 'code_3_4', 'code_3', 'www.baidu.com', 'M', 'I', '4.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1028', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '一级_4', 'code_4', '-1', 'www.baidu.com', 'M', 'I', '5.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1029', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_4_0', 'code_4_0', 'code_4', 'www.baidu.com', 'M', 'I', '5.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1030', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_4_1', 'code_4_1', 'code_4', 'www.baidu.com', 'M', 'I', '5.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1031', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_4_2', 'code_4_2', 'code_4', 'www.baidu.com', 'M', 'I', '5.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1032', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_4_3', 'code_4_3', 'code_4', 'www.baidu.com', 'M', 'I', '5.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1033', '0', 'admin', '2017-10-18 17:52:48', 'admin', '2017-10-18 17:52:48', '0', '二级_4_4', 'code_4_4', 'code_4', 'www.baidu.com', 'M', 'I', '5.1', 'icon-sys', null);
INSERT INTO `sys_function` VALUES ('1034', '0', 'admin', '2017-10-19 11:18:47', 'admin', '2017-10-19 11:18:47', '0', '系统管理', 'sys', '-1', 'javascript:void(0);', 'M', 'A', '1.0', 'icon-sys', '系统管理 》 菜单');
INSERT INTO `sys_function` VALUES ('1035', '2', 'admin', '2017-10-19 11:18:47', 'admin', '2017-10-19 16:52:33', '0', '功能管理', 'sys-func', 'sys', 'jsp/system/function/functionMain.jsp', 'M', 'A', '1.1', 'icon-sys', '系统管理 》 菜单');
INSERT INTO `sys_function` VALUES ('1036', '8', 'admin', '2017-10-19 16:17:26', 'admin', '2017-10-19 16:52:45', '0', '机构管理', 'sys-org', 'sys', 'jsp/system/organization/organizationMain.jsp', 'M', 'A', '1.2', 'icon-sys', '系统管理 》 菜单');
INSERT INTO `sys_function` VALUES ('1040', '2', 'admin', '2017-10-19 17:42:13', 'admin', '2017-10-19 17:44:35', '0', '用户管理', 'sys-user', 'sys', 'jsp/system/user/userMain.jsp', 'M', 'A', '1.3', 'icon-sys', '系统管理 》 菜单');
INSERT INTO `sys_function` VALUES ('1041', '1', 'admin', '2017-10-20 16:49:40', 'admin', '2017-10-20 16:50:00', '0', '角色管理', 'sys-role', 'sys', 'jsp/system/role/roleMain.jsp', 'M', 'A', '1.4', 'icon-sys', '系统管理 》 菜单');
INSERT INTO `sys_function` VALUES ('1042', '0', 'admin', '2017-10-24 15:06:26', 'admin', '2017-10-24 15:06:26', '0', '权限管理', 'sys-pms', 'sys', 'jsp/system/permission/permissionMain.jsp', 'M', 'A', '1.5', 'icon-sys', '系统管理 》  菜单');

-- ----------------------------
-- Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `VERSION` int(11) NOT NULL,
  `CREATER` varchar(50) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EDITOR` varchar(50) DEFAULT NULL COMMENT '修改人',
  `EDIT_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `DEL_FLAG` char(1) NOT NULL COMMENT '删除标记，1：删除、0：未删除',
  `ORG_NAME` varchar(255) DEFAULT NULL COMMENT '组织名称',
  `ORG_CODE` varchar(25) DEFAULT NULL COMMENT '组织编码',
  `P_CODE` varchar(25) DEFAULT NULL COMMENT '父组织编码',
  `FIRST_MANAGER_CODE` varchar(25) DEFAULT NULL COMMENT '主负责人',
  `SECOND_MANAGER_CODE` varchar(25) DEFAULT NULL COMMENT '副负责人',
  `TEL` varchar(50) DEFAULT NULL COMMENT '电话',
  `FAX` varchar(50) DEFAULT NULL COMMENT '传真',
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 COMMENT='组织机构表';

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('126', '0', 'admin', '2017-10-20 09:47:28', 'admin', '2017-10-20 09:47:28', '0', '一级机构_0', '1000', '-1', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('127', '0', 'admin', '2017-10-20 09:47:28', 'admin', '2017-10-20 09:47:28', '0', '一级机构_1', '1001', '-1', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('128', '0', 'admin', '2017-10-20 09:47:28', 'admin', '2017-10-20 09:47:28', '0', '一级机构_2', '1002', '-1', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('129', '0', 'admin', '2017-10-20 09:47:28', 'admin', '2017-10-20 09:47:28', '0', '一级机构_3', '1003', '-1', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('130', '0', 'admin', '2017-10-20 09:47:28', 'admin', '2017-10-20 09:47:28', '0', '一级机构_4', '1004', '-1', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('131', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_0_0', '2000', '1000', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('132', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_0_1', '2001', '1000', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('133', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_0_2', '2002', '1000', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('134', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_0_3', '2003', '1000', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('135', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_0_4', '2004', '1000', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('136', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_1_0', '3000', '1001', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('137', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_1_1', '3001', '1001', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('138', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_1_2', '3002', '1001', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('139', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_1_3', '3003', '1001', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('140', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_1_4', '3004', '1001', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('141', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_2_0', '4000', '1002', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('142', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_2_1', '4001', '1002', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('143', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_2_2', '4002', '1002', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('144', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_2_3', '4003', '1002', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('145', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_2_4', '4004', '1002', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('146', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_3_0', '5000', '1003', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('147', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_3_1', '5001', '1003', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('148', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_3_2', '5002', '1003', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('149', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_3_3', '5003', '1003', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('150', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_3_4', '5004', '1003', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('151', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_4_0', '6000', '1004', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('152', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_4_1', '6001', '1004', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('153', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_4_2', '6002', '1004', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('154', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_4_3', '6003', '1004', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('155', '0', 'admin', '2017-10-20 09:47:29', 'admin', '2017-10-20 09:47:29', '0', '二级机构_4_4', '6004', '1004', null, null, null, null, null);
INSERT INTO `sys_organization` VALUES ('156', '0', 'admin', '2017-10-20 15:46:08', 'admin', '2017-10-20 15:46:08', '0', 'aa', 'aa', '2000', 'aa', 'aa', '', '', 'aa');

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `version` int(11) NOT NULL,
  `creater` varchar(50) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `editor` varchar(50) DEFAULT NULL COMMENT '修改人',
  `edit_time` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` char(1) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL COMMENT '删除标记，1：删除、0：未删除',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(100) DEFAULT NULL COMMENT '编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1041 DEFAULT CHARSET=utf8 COMMENT='系统 》 权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1000', '0', 'admin', '2017-10-12 15:39:24', 'admin', '2017-10-12 15:39:24', '0', null, '系统管理-菜单-查看_0', 'pms:menu:view_0');
INSERT INTO `sys_permission` VALUES ('1001', '0', 'admin', '2017-10-12 15:39:24', 'admin', '2017-10-12 15:39:24', '0', null, '系统管理-菜单-查看_1', 'pms:menu:view_1');
INSERT INTO `sys_permission` VALUES ('1002', '0', 'admin', '2017-10-12 15:39:24', 'admin', '2017-10-12 15:39:24', '0', null, '系统管理-菜单-查看_2', 'pms:menu:view_2');
INSERT INTO `sys_permission` VALUES ('1003', '0', 'admin', '2017-10-12 15:39:24', 'admin', '2017-10-12 15:39:24', '0', null, '系统管理-菜单-查看_3', 'pms:menu:view_3');
INSERT INTO `sys_permission` VALUES ('1004', '0', 'admin', '2017-10-12 15:39:24', 'admin', '2017-10-12 15:39:24', '0', null, '系统管理-菜单-查看_4', 'pms:menu:view_4');
INSERT INTO `sys_permission` VALUES ('1005', '0', 'admin', '2017-10-12 15:39:24', 'admin', '2017-10-12 15:39:24', '0', null, '系统管理-菜单-查看_5', 'pms:menu:view_5');
INSERT INTO `sys_permission` VALUES ('1009', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_9', 'pms:menu:view_9');
INSERT INTO `sys_permission` VALUES ('1010', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_10', 'pms:menu:view_10');
INSERT INTO `sys_permission` VALUES ('1011', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_11', 'pms:menu:view_11');
INSERT INTO `sys_permission` VALUES ('1012', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_12', 'pms:menu:view_12');
INSERT INTO `sys_permission` VALUES ('1013', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_13', 'pms:menu:view_13');
INSERT INTO `sys_permission` VALUES ('1014', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_14', 'pms:menu:view_14');
INSERT INTO `sys_permission` VALUES ('1015', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_15', 'pms:menu:view_15');
INSERT INTO `sys_permission` VALUES ('1016', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_16', 'pms:menu:view_16');
INSERT INTO `sys_permission` VALUES ('1017', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_17', 'pms:menu:view_17');
INSERT INTO `sys_permission` VALUES ('1018', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_18', 'pms:menu:view_18');
INSERT INTO `sys_permission` VALUES ('1019', '0', 'admin', '2017-10-12 15:39:25', 'admin', '2017-10-12 15:39:25', '0', null, '系统管理-菜单-查看_19', 'pms:menu:view_19');
INSERT INTO `sys_permission` VALUES ('1036', '0', 'admin', '2017-10-16 11:50:40', 'admin', '2017-10-16 11:50:40', '0', 'ss', 'ss', 'sys:db');
INSERT INTO `sys_permission` VALUES ('1037', '0', 'admin', '2017-10-16 13:50:34', 'admin', '2017-10-16 13:50:34', '0', 'ss', 'asd', 'ss');
INSERT INTO `sys_permission` VALUES ('1038', '0', 'admin', '2017-10-16 13:51:43', 'admin', '2017-10-16 13:51:43', '0', 'ssd', 'sssss', 'ssssdf');
INSERT INTO `sys_permission` VALUES ('1039', '7', 'admin', '2017-10-16 13:51:52', 'admin', '2017-10-16 15:01:11', '0', 'ssdsss矮点sss', 'sssssa水电费', 'ssssdfdd');
INSERT INTO `sys_permission` VALUES ('1040', '5', 'admin', '2017-10-16 15:01:37', 'admin', '2017-10-16 15:12:04', '0', '对对对ddddsss', '对对对', 'sdf');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `VERSION` int(11) NOT NULL,
  `CREATER` varchar(50) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EDITOR` varchar(50) DEFAULT NULL COMMENT '修改人',
  `EDIT_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `DEL_FLAG` char(1) DEFAULT NULL COMMENT '删除标记，1：删除、0：未删除',
  `ROLE_NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `ROLE_CODE` varchar(100) DEFAULT NULL COMMENT '编号',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1072 DEFAULT CHARSET=utf8 COMMENT='系统 》 角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1035', '0', 'admin', '2017-10-23 11:41:39', 'admin', '2017-10-23 11:41:39', '0', 'role_0', 'code_0', '备注');
INSERT INTO `sys_role` VALUES ('1036', '0', 'admin', '2017-10-23 11:41:39', 'admin', '2017-10-23 11:41:39', '0', 'role_1', 'code_1', '备注');
INSERT INTO `sys_role` VALUES ('1037', '0', 'admin', '2017-10-23 11:41:39', 'admin', '2017-10-23 11:41:39', '0', 'role_2', 'code_2', '备注');
INSERT INTO `sys_role` VALUES ('1038', '0', 'admin', '2017-10-23 11:41:39', 'admin', '2017-10-23 11:41:39', '0', 'role_3', 'code_3', '备注');
INSERT INTO `sys_role` VALUES ('1039', '0', 'admin', '2017-10-23 11:41:39', 'admin', '2017-10-23 11:41:39', '0', 'role_4', 'code_4', '备注');
INSERT INTO `sys_role` VALUES ('1040', '0', 'admin', '2017-10-23 11:41:39', 'admin', '2017-10-23 11:41:39', '0', 'role_5', 'code_5', '备注');
INSERT INTO `sys_role` VALUES ('1041', '0', 'admin', '2017-10-23 11:41:39', 'admin', '2017-10-23 11:41:39', '0', 'role_6', 'code_6', '备注');
INSERT INTO `sys_role` VALUES ('1042', '0', 'admin', '2017-10-23 11:41:39', 'admin', '2017-10-23 11:41:39', '0', 'role_7', 'code_7', '备注');
INSERT INTO `sys_role` VALUES ('1043', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_8', 'code_8', '备注');
INSERT INTO `sys_role` VALUES ('1044', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_9', 'code_9', '备注');
INSERT INTO `sys_role` VALUES ('1045', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_10', 'code_10', '备注');
INSERT INTO `sys_role` VALUES ('1046', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_11', 'code_11', '备注');
INSERT INTO `sys_role` VALUES ('1047', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_12', 'code_12', '备注');
INSERT INTO `sys_role` VALUES ('1048', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_13', 'code_13', '备注');
INSERT INTO `sys_role` VALUES ('1049', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_14', 'code_14', '备注');
INSERT INTO `sys_role` VALUES ('1050', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_15', 'code_15', '备注');
INSERT INTO `sys_role` VALUES ('1051', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_16', 'code_16', '备注');
INSERT INTO `sys_role` VALUES ('1052', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_17', 'code_17', '备注');
INSERT INTO `sys_role` VALUES ('1053', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_18', 'code_18', '备注');
INSERT INTO `sys_role` VALUES ('1054', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_19', 'code_19', '备注');
INSERT INTO `sys_role` VALUES ('1055', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_20', 'code_20', '备注');
INSERT INTO `sys_role` VALUES ('1056', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_21', 'code_21', '备注');
INSERT INTO `sys_role` VALUES ('1057', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_22', 'code_22', '备注');
INSERT INTO `sys_role` VALUES ('1058', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_23', 'code_23', '备注');
INSERT INTO `sys_role` VALUES ('1059', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_24', 'code_24', '备注');
INSERT INTO `sys_role` VALUES ('1060', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_25', 'code_25', '备注');
INSERT INTO `sys_role` VALUES ('1061', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_26', 'code_26', '备注');
INSERT INTO `sys_role` VALUES ('1062', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_27', 'code_27', '备注');
INSERT INTO `sys_role` VALUES ('1063', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_28', 'code_28', '备注');
INSERT INTO `sys_role` VALUES ('1064', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_29', 'code_29', '备注');
INSERT INTO `sys_role` VALUES ('1065', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_30', 'code_30', '备注');
INSERT INTO `sys_role` VALUES ('1066', '0', 'admin', '2017-10-23 11:41:40', 'admin', '2017-10-23 11:41:40', '0', 'role_31', 'code_31', '备注');
INSERT INTO `sys_role` VALUES ('1067', '0', 'admin', '2017-10-23 11:41:41', 'admin', '2017-10-23 11:41:41', '0', 'role_32', 'code_32', '备注');
INSERT INTO `sys_role` VALUES ('1068', '0', 'admin', '2017-10-23 11:41:41', 'admin', '2017-10-23 11:41:41', '0', 'role_33', 'code_33', '备注');
INSERT INTO `sys_role` VALUES ('1069', '0', 'admin', '2017-10-23 11:41:41', 'admin', '2017-10-23 11:41:41', '0', 'role_34', 'code_34', '备注');
INSERT INTO `sys_role` VALUES ('1071', '1', 'admin', '2017-10-23 14:37:11', 'admin', '2017-10-23 14:38:32', '0', 'ssff', 'ss', 'sssfff');

-- ----------------------------
-- Table structure for `sys_role_func`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_func`;
CREATE TABLE `sys_role_func` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `VERSION` int(11) NOT NULL,
  `CREATER` varchar(50) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EDITOR` varchar(50) DEFAULT NULL COMMENT '修改人',
  `EDIT_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `DEL_FLAG` char(1) DEFAULT NULL COMMENT '删除标记，1：删除、0：未删除',
  `ROLE_CODE` varchar(100) DEFAULT NULL COMMENT '角色编号',
  `FUNC_CODE` varchar(100) DEFAULT NULL COMMENT '功能编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1030 DEFAULT CHARSET=utf8 COMMENT='系统 》 角色-功能关系表';

-- ----------------------------
-- Records of sys_role_func
-- ----------------------------
INSERT INTO `sys_role_func` VALUES ('1003', '0', 'admin', '2017-10-23 17:40:52', null, null, '0', 'code_0', 'code_0_0');
INSERT INTO `sys_role_func` VALUES ('1004', '0', 'admin', '2017-10-23 17:40:52', null, null, '0', 'code_0', 'code_0_2');
INSERT INTO `sys_role_func` VALUES ('1005', '0', 'admin', '2017-10-23 17:40:52', null, null, '0', 'code_0', 'code_0_3');
INSERT INTO `sys_role_func` VALUES ('1006', '0', 'admin', '2017-10-23 17:40:52', null, null, '0', 'code_0', 'code_0_4');
INSERT INTO `sys_role_func` VALUES ('1013', '0', 'admin', '2017-10-24 14:48:58', null, null, '0', 'code_32', 'sys');
INSERT INTO `sys_role_func` VALUES ('1014', '0', 'admin', '2017-10-24 14:48:58', null, null, '0', 'code_32', 'sys-func');
INSERT INTO `sys_role_func` VALUES ('1015', '0', 'admin', '2017-10-24 14:48:58', null, null, '0', 'code_32', 'sys-org');
INSERT INTO `sys_role_func` VALUES ('1016', '0', 'admin', '2017-10-24 14:50:40', null, null, '0', 'code_33', 'sys');
INSERT INTO `sys_role_func` VALUES ('1017', '0', 'admin', '2017-10-24 14:50:40', null, null, '0', 'code_33', 'sys-role');
INSERT INTO `sys_role_func` VALUES ('1024', '0', 'admin', '2017-10-24 14:57:01', null, null, '0', 'ss', 'code_0');
INSERT INTO `sys_role_func` VALUES ('1025', '0', 'admin', '2017-10-24 14:57:01', null, null, '0', 'ss', 'code_0_0');
INSERT INTO `sys_role_func` VALUES ('1026', '0', 'admin', '2017-10-24 14:57:01', null, null, '0', 'ss', 'code_0_1');
INSERT INTO `sys_role_func` VALUES ('1027', '0', 'admin', '2017-10-24 14:57:01', null, null, '0', 'ss', 'code_0_3');
INSERT INTO `sys_role_func` VALUES ('1028', '0', 'admin', '2017-10-24 14:57:01', null, null, '0', 'ss', 'code_1');
INSERT INTO `sys_role_func` VALUES ('1029', '0', 'admin', '2017-10-24 14:57:01', null, null, '0', 'ss', 'code_1_1');

-- ----------------------------
-- Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `VERSION` int(11) NOT NULL,
  `CREATER` varchar(50) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EDITOR` varchar(50) DEFAULT NULL COMMENT '修改人',
  `EDIT_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `DEL_FLAG` char(1) DEFAULT NULL COMMENT '删除标记，1：删除、0：未删除',
  `ROLE_CODE` varchar(100) DEFAULT NULL COMMENT '角色编号',
  `USER_CODE` varchar(100) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1010 DEFAULT CHARSET=utf8 COMMENT='系统 》 角色-用户关系表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1002', '0', 'admin', '2017-10-24 17:20:35', null, null, '0', 'code_32', 'zhangsan');
INSERT INTO `sys_role_user` VALUES ('1003', '0', 'admin', '2017-10-24 17:20:35', null, null, '0', 'code_33', 'zhangsan');
INSERT INTO `sys_role_user` VALUES ('1004', '0', 'admin', '2017-10-24 17:20:35', null, null, '0', 'code_34', 'zhangsan');
INSERT INTO `sys_role_user` VALUES ('1005', '0', 'admin', '2017-10-24 17:25:12', null, null, '0', 'code_32', 'zhangsan');
INSERT INTO `sys_role_user` VALUES ('1006', '0', 'admin', '2017-10-24 17:25:12', null, null, '0', 'code_33', 'zhangsan');
INSERT INTO `sys_role_user` VALUES ('1007', '0', 'admin', '2017-10-24 17:25:12', null, null, '0', 'code_34', 'zhangsan');
INSERT INTO `sys_role_user` VALUES ('1008', '0', 'admin', '2017-10-24 17:25:12', null, null, '0', 'code_8', 'zhangsan');
INSERT INTO `sys_role_user` VALUES ('1009', '0', 'admin', '2017-10-24 17:25:12', null, null, '0', 'code_92', 'zhangsan');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `VERSION` int(11) NOT NULL,
  `CREATER` varchar(50) NOT NULL COMMENT '创建人',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `EDITOR` varchar(50) DEFAULT NULL COMMENT '修改人',
  `EDIT_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `DEL_FLAG` char(1) DEFAULT NULL COMMENT '删除标记，1：删除、0：未删除',
  `USER_NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `USER_CODE` varchar(100) DEFAULT NULL COMMENT '编号',
  `ACCOUNT` varchar(50) DEFAULT NULL COMMENT '账号',
  `PASSWORD` varchar(128) DEFAULT NULL COMMENT '密码',
  `ORG_CODE` varchar(200) DEFAULT NULL COMMENT '机构编号',
  `ACTIVE` char(1) DEFAULT NULL COMMENT 'A:ACTIVE,I:INACTIVE',
  `TEL` varchar(30) DEFAULT NULL COMMENT '用户电话',
  `EMAIL` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COMMENT='系统 》 用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('41', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_0', 'code_0', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('42', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_1', 'code_1', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('43', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_2', 'code_2', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('44', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_3', 'code_3', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('45', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_4', 'code_4', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('46', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_5', 'code_5', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('47', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_6', 'code_6', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('48', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_7', 'code_7', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('49', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_8', 'code_8', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('50', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_9', 'code_9', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('51', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_10', 'code_10', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('52', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_11', 'code_11', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('53', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_12', 'code_12', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('54', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_13', 'code_13', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('55', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_14', 'code_14', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('56', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_15', 'code_15', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('57', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_16', 'code_16', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('58', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_17', 'code_17', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('59', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_18', 'code_18', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('60', '0', 'admin', '2017-10-20 10:32:31', 'admin', '2017-10-20 10:32:31', '0', 'user_19', 'code_19', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('61', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_20', 'code_20', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('62', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_21', 'code_21', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('63', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_22', 'code_22', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('64', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_23', 'code_23', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('65', '1', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-23 11:53:54', '0', 'user_24', 'code_24', 'admin', '123456', '1000', 'A', '12345678', 'test@com', '备注ss');
INSERT INTO `sys_user` VALUES ('66', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_25', 'code_25', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('67', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_26', 'code_26', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('68', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_27', 'code_27', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('69', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_28', 'code_28', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('70', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_29', 'code_29', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('71', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_30', 'code_30', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('72', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_31', 'code_31', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('73', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_32', 'code_32', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('74', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_33', 'code_33', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('75', '0', 'admin', '2017-10-20 10:32:32', 'admin', '2017-10-20 10:32:32', '0', 'user_34', 'code_34', 'admin', '123456', '2000', 'A', '12345678', 'test@com', '备注');
INSERT INTO `sys_user` VALUES ('76', '1', 'admin', '2017-10-20 15:49:30', 'admin', '2017-10-23 11:53:32', '0', 'zhangsan1', 'zhangsan', 'zhangsan', 'zhangsan', '1000', 'A', '', '', 'zhangsan1');
