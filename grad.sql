/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50543
Source Host           : localhost:3306
Source Database       : grad

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2018-04-20 20:25:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(20) DEFAULT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `manager` varchar(255) DEFAULT NULL COMMENT '负责人',
  `member` varchar(255) DEFAULT NULL COMMENT '成员',
  `nature` varchar(255) DEFAULT NULL COMMENT '项目性质',
  `desc` varchar(255) DEFAULT NULL COMMENT '项目描述',
  `awards` varchar(255) DEFAULT NULL COMMENT '获奖情况',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `status` int(1) DEFAULT '0' COMMENT '项目状态：0 未审核，1 通过审核，2 未通过',
  `createUser` varchar(255) DEFAULT NULL COMMENT '创建者',
  `checkUser` varchar(255) DEFAULT '' COMMENT '审核人',
  `savePath` varchar(1000) DEFAULT '' COMMENT '材料保存路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '1', '环境项目1', '王老师', '赵钱孙李，周吴郑王', '环境调研', '环境保护相关', '省级奖项', '2018-04-18 14:30:31', '0', '管理员', '李院长', '/projectMaterial/1.txt');
INSERT INTO `project` VALUES ('2', '2', '计算机的飞速发展', '李老师', '一二三四五六，七八节', '计算机调研', '计算机相关科学', '无奖项', '2018-04-18 14:30:34', '0', '管理员', '李院长', '/projectMaterial/2.txt');
INSERT INTO `project` VALUES ('3', '3', '航天飞船的起飞与降落', '刘老师', '赵老师，孙老师', '关于太空载人火箭的研究', '神州八十号', '国家奖项', '2018-04-18 14:31:18', '0', '管理员', '李院长', '/projectMaterial/3.txt');
INSERT INTO `project` VALUES ('4', '4', '夏洛特烦恼', '夏洛', '秋雅，冬梅，大春', '喜剧研究', '电影拍摄等', '最高票房1', '2018-04-18 18:01:23', '0', '管理员', '', '/projectMaterial/4.txt');
INSERT INTO `project` VALUES ('5', '5', '测试的', '测试', '测试的成员', '测试1', '测试', '测试', null, '2', '管理员', '', '/projectMaterial/5.txt');
INSERT INTO `project` VALUES ('6', '6', '测试的', '测试', '测试的成员', '测试', '测试12', '测试', null, '2', '管理员', '李院长', null);
INSERT INTO `project` VALUES ('7', '21', '名称', '入住人', '阿达', '23阿达', '打造成自行车对方', '1', null, '1', '管理员', '李院长', 'project/upload/名称/json1.json');
INSERT INTO `project` VALUES ('10', '111', '李测试', '李测试', '李测试', '李测试', '李测试李测试', '李测试', null, '1', '李院长', '李院长', null);
INSERT INTO `project` VALUES ('11', '1111', '李测试', '李测试', '李测试', '李测试', '李测试', '李测试', null, '1', '李院长', '李院长', null);
INSERT INTO `project` VALUES ('15', '11111', 'asdasd', 'ad', 'adad', 'd', 'adad', 'asa', '2018-04-19 17:20:12', '0', '李院长', '李院长', 'project/upload/asdasd/json1.json');
INSERT INTO `project` VALUES ('16', '1231', '空路径测试', '13213', '21321', '3132', '1321', '3121', '2018-04-20 14:23:58', '0', '管理员', '李院长', '');
INSERT INTO `project` VALUES ('17', '111222', '名称', '2131', '1321', '3', '131', '31321', '2018-04-20 16:43:08', '0', '李院长', '', '');

-- ----------------------------
-- Table structure for systemconfig
-- ----------------------------
DROP TABLE IF EXISTS `systemconfig`;
CREATE TABLE `systemconfig` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '参数名称',
  `value` varchar(255) DEFAULT NULL COMMENT '参数值',
  `type` tinyint(2) DEFAULT NULL COMMENT '参数类型',
  `description` varchar(255) DEFAULT NULL COMMENT '参数描述',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of systemconfig
-- ----------------------------
INSERT INTO `systemconfig` VALUES ('1', 'fileRoot', '/project/', '1', '文件存放根目录', '2018-04-20 16:22:30', '2018-04-20 16:53:17');
INSERT INTO `systemconfig` VALUES ('2', 'uploadConfigDir', 'project/upload/', '1', '文件上传根目录', '2018-04-20 16:24:22', '2018-04-20 16:53:22');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '用户登录名',
  `password` varchar(255) DEFAULT '123456' COMMENT '密码默认123456',
  `realName` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `permission` int(1) DEFAULT '0' COMMENT '0:老师 1:领导 2:管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', '管理员', '2');
INSERT INTO `user` VALUES ('2', 'wang', '123456', '王院长', '1');
INSERT INTO `user` VALUES ('3', 'li', '123456', '李院长', '1');
