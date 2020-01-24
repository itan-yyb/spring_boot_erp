/*
Navicat MySQL Data Transfer

Source Server         : Centos7
Source Server Version : 50631
Source Host           : 192.168.241.135:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2020-01-22 17:46:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bus_customer
-- ----------------------------
DROP TABLE IF EXISTS `bus_customer`;
CREATE TABLE `bus_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customername` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `connectionperson` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_customer
-- ----------------------------
INSERT INTO `bus_customer` VALUES ('1', '小张超市', '111', '武汉', '027-9123131', '张大明', '13812312312321312', '中国银行', '654431331343413', '213123@sina.com', '430000', '1');
INSERT INTO `bus_customer` VALUES ('2', '小明超市', '222', '深圳', '0755-9123131', '张小明', '13812312312321312', '中国银行', '654431331343413', '213123@sina.com', '430000', '1');
INSERT INTO `bus_customer` VALUES ('3', '快七超市', '430000', '武汉', '027-11011011', '雷生', '13434134131', '招商银行', '6543123341334133', '6666@66.com', '545341', '1');

-- ----------------------------
-- Table structure for bus_goods
-- ----------------------------
DROP TABLE IF EXISTS `bus_goods`;
CREATE TABLE `bus_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) DEFAULT NULL,
  `produceplace` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `goodspackage` varchar(255) DEFAULT NULL,
  `productcode` varchar(255) DEFAULT NULL,
  `promitcode` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `dangernum` int(11) DEFAULT NULL,
  `goodsimg` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `providerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_sq0btr2v2lq8gt8b4gb8tlk0i` (`providerid`) USING BTREE,
  CONSTRAINT `bus_goods_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_goods
-- ----------------------------
INSERT INTO `bus_goods` VALUES ('1', '娃哈哈', '武汉', '120ML', '瓶', 'PH12345', 'PZ1234', '小孩子都爱的', '2', '1000', '10', '2020-01-19/userface.jpg', '1', '3');
INSERT INTO `bus_goods` VALUES ('2', '旺旺雪饼[小包]', '仙桃', '包', '袋', 'PH12312312', 'PZ12312', '好吃不上火', '4', '80', '10', '2020-01-19/userface.jpg', '1', '1');
INSERT INTO `bus_goods` VALUES ('3', '旺旺大礼包', '仙桃', '盒', '盒', '11', '11', '111', '28', '1000', '100', '2020-01-19/userface.jpg', '1', '1');
INSERT INTO `bus_goods` VALUES ('4', '娃哈哈', '武汉', '200ML', '瓶', '11', '111', '12321', '3', '1000', '10', '2020-01-19/userface.jpg', '1', '3');
INSERT INTO `bus_goods` VALUES ('5', '娃哈哈', '武汉', '300ML', '瓶', '1234', '12321', '22221111', '3', '1000', '100', '2020-01-19/userface.jpg', '1', '3');
INSERT INTO `bus_goods` VALUES ('6', '口香糖', '武汉', '5片/条', '条', 'PH1245451', 'PZ4515', '好甜', '3', '1400', '40', '2020-01-21/18718B3EB39B45BB8EEEFA9C9C29C0E6.jpg', '1', '2');

-- ----------------------------
-- Table structure for bus_inport
-- ----------------------------
DROP TABLE IF EXISTS `bus_inport`;
CREATE TABLE `bus_inport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paytype` varchar(255) DEFAULT NULL,
  `inporttime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `inportprice` double DEFAULT NULL,
  `providerid` int(11) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_1o4bujsyd2kl4iqw48fie224v` (`providerid`) USING BTREE,
  KEY `FK_ho29poeu4o2dxu4rg1ammeaql` (`goodsid`) USING BTREE,
  CONSTRAINT `bus_inport_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`),
  CONSTRAINT `bus_inport_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `bus_goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_inport
-- ----------------------------
INSERT INTO `bus_inport` VALUES ('1', '微信', '2018-05-07 00:00:00', '张三', '100', '备注', '3.5', '1', '1');
INSERT INTO `bus_inport` VALUES ('2', '支付宝', '2018-05-07 00:00:00', '张三', '1000', '无', '2.5', '3', '3');
INSERT INTO `bus_inport` VALUES ('3', '银联', '2018-05-07 00:00:00', '张三', '100', '1231', '111', '3', '3');
INSERT INTO `bus_inport` VALUES ('4', '银联', '2018-05-07 00:00:00', '张三', '1000', '无', '2', '3', '1');
INSERT INTO `bus_inport` VALUES ('5', '银联', '2018-05-07 00:00:00', '张三', '100', '无', '1', '3', '1');
INSERT INTO `bus_inport` VALUES ('6', '银联', '2018-05-07 00:00:00', '张三', '100', '1231', '2.5', '1', '2');
INSERT INTO `bus_inport` VALUES ('8', '支付宝', '2018-05-07 00:00:00', '张三', '100', '', '1', '3', '1');
INSERT INTO `bus_inport` VALUES ('10', '支付宝', '2018-08-07 17:17:57', '超级管理员', '100', 'sadfasfdsa', '1.5', '3', '1');
INSERT INTO `bus_inport` VALUES ('11', '支付宝', '2018-09-17 16:12:25', '超级管理员', '21', '21', '21', '1', '3');
INSERT INTO `bus_inport` VALUES ('12', '微信', '2018-12-25 16:48:24', '超级管理员', '100', '123213213', '12321321', '3', '1');
INSERT INTO `bus_inport` VALUES ('14', '微信', '2020-01-22 06:07:18', '超级管理员', '488', '所属', '1.5', '2', '6');

-- ----------------------------
-- Table structure for bus_outport
-- ----------------------------
DROP TABLE IF EXISTS `bus_outport`;
CREATE TABLE `bus_outport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  `outputtime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `outportprice` double(10,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_outport
-- ----------------------------
INSERT INTO `bus_outport` VALUES ('1', '3', '微信', '2019-08-16 08:19:50', '超级管理员', '12321321.00', '1', '', '1');
INSERT INTO `bus_outport` VALUES ('2', '3', '微信', '2019-08-16 08:26:54', '超级管理员', '12321321.00', '11', '11', '1');
INSERT INTO `bus_outport` VALUES ('3', '2', '微信', '2020-01-22 06:07:32', '超级管理员', '1.50', '88', '是', '6');

-- ----------------------------
-- Table structure for bus_provider
-- ----------------------------
DROP TABLE IF EXISTS `bus_provider`;
CREATE TABLE `bus_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providername` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `connectionperson` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_provider
-- ----------------------------
INSERT INTO `bus_provider` VALUES ('1', '旺旺食品', '434000', '仙桃', '0728-4124312', '小明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', '1');
INSERT INTO `bus_provider` VALUES ('2', '达利园食品', '430000', '汉川', '0728-4124312', '大明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', '1');
INSERT INTO `bus_provider` VALUES ('3', '娃哈哈集团', '513131', '杭州', '21312', '小晨', '12312', '建设银行', '512314141541', '123131', '312312', '1');

-- ----------------------------
-- Table structure for bus_sales
-- ----------------------------
DROP TABLE IF EXISTS `bus_sales`;
CREATE TABLE `bus_sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  `salestime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `saleprice` decimal(10,2) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_sales
-- ----------------------------

-- ----------------------------
-- Table structure for bus_salesback
-- ----------------------------
DROP TABLE IF EXISTS `bus_salesback`;
CREATE TABLE `bus_salesback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  `salesbacktime` datetime DEFAULT NULL,
  `salebackprice` double(10,2) DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_salesback
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `ordernum` int(11) DEFAULT NULL COMMENT '排序码【为了调事显示顺序】',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '总经办', '1', '大BOSS', '深圳', '1', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('2', '1', '销售部', '0', '程序员屌丝', '武汉', '1', '2', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('3', '1', '运营部', '0', '无', '武汉', '1', '3', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('4', '1', '生产部', '0', '无', '武汉', '1', '4', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('5', '2', '销售一部', '0', '销售一部', '武汉', '1', '5', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('6', '2', '销售二部', '0', '销售二部', '武汉', '1', '6', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('7', '3', '运营一部', '0', '运营一部', '武汉', '1', '7', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('8', '2', '销售三部', '0', '销售三部', '11', '1', '8', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('9', '2', '销售四部', '0', '销售四部', '222', '1', '9', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('10', '2', '销售五部', '0', '销售五部', '33', '1', '10', '2019-04-10 14:06:32');

-- ----------------------------
-- Table structure for sys_logInfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_logInfo`;
CREATE TABLE `sys_logInfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) DEFAULT NULL,
  `loginip` varchar(255) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_logInfo
-- ----------------------------
INSERT INTO `sys_logInfo` VALUES ('172', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-08 10:29:52');
INSERT INTO `sys_logInfo` VALUES ('173', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-08 11:17:35');
INSERT INTO `sys_logInfo` VALUES ('174', '超级管理员-system', '127.0.0.1', '2020-01-08 11:44:24');
INSERT INTO `sys_logInfo` VALUES ('175', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-08 12:02:13');
INSERT INTO `sys_logInfo` VALUES ('176', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 04:08:10');
INSERT INTO `sys_logInfo` VALUES ('177', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 04:29:59');
INSERT INTO `sys_logInfo` VALUES ('178', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 04:49:10');
INSERT INTO `sys_logInfo` VALUES ('179', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 04:51:38');
INSERT INTO `sys_logInfo` VALUES ('180', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 04:57:23');
INSERT INTO `sys_logInfo` VALUES ('181', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 05:05:13');
INSERT INTO `sys_logInfo` VALUES ('182', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 05:19:58');
INSERT INTO `sys_logInfo` VALUES ('183', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 05:23:06');
INSERT INTO `sys_logInfo` VALUES ('184', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 05:26:10');
INSERT INTO `sys_logInfo` VALUES ('185', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 05:35:15');
INSERT INTO `sys_logInfo` VALUES ('186', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 06:08:34');
INSERT INTO `sys_logInfo` VALUES ('187', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 06:27:40');
INSERT INTO `sys_logInfo` VALUES ('188', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 07:07:55');
INSERT INTO `sys_logInfo` VALUES ('189', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 11:32:24');
INSERT INTO `sys_logInfo` VALUES ('190', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 11:42:31');
INSERT INTO `sys_logInfo` VALUES ('191', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 12:19:02');
INSERT INTO `sys_logInfo` VALUES ('192', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 12:51:58');
INSERT INTO `sys_logInfo` VALUES ('193', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 12:55:59');
INSERT INTO `sys_logInfo` VALUES ('194', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 14:44:31');
INSERT INTO `sys_logInfo` VALUES ('195', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 14:44:54');
INSERT INTO `sys_logInfo` VALUES ('196', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-09 14:52:07');
INSERT INTO `sys_logInfo` VALUES ('197', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 03:42:49');
INSERT INTO `sys_logInfo` VALUES ('198', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 03:44:53');
INSERT INTO `sys_logInfo` VALUES ('199', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 03:58:07');
INSERT INTO `sys_logInfo` VALUES ('200', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 04:37:28');
INSERT INTO `sys_logInfo` VALUES ('201', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 07:09:16');
INSERT INTO `sys_logInfo` VALUES ('202', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 08:30:55');
INSERT INTO `sys_logInfo` VALUES ('203', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 09:25:43');
INSERT INTO `sys_logInfo` VALUES ('204', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 09:42:56');
INSERT INTO `sys_logInfo` VALUES ('205', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-10 12:20:43');
INSERT INTO `sys_logInfo` VALUES ('206', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 01:09:47');
INSERT INTO `sys_logInfo` VALUES ('207', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 01:38:46');
INSERT INTO `sys_logInfo` VALUES ('208', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 05:16:53');
INSERT INTO `sys_logInfo` VALUES ('209', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 05:44:51');
INSERT INTO `sys_logInfo` VALUES ('210', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 06:50:19');
INSERT INTO `sys_logInfo` VALUES ('211', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 13:06:09');
INSERT INTO `sys_logInfo` VALUES ('212', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 13:18:20');
INSERT INTO `sys_logInfo` VALUES ('213', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 13:21:48');
INSERT INTO `sys_logInfo` VALUES ('214', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 13:25:22');
INSERT INTO `sys_logInfo` VALUES ('215', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-11 13:31:04');
INSERT INTO `sys_logInfo` VALUES ('216', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 00:15:54');
INSERT INTO `sys_logInfo` VALUES ('217', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 01:08:07');
INSERT INTO `sys_logInfo` VALUES ('218', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 02:09:05');
INSERT INTO `sys_logInfo` VALUES ('219', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 02:58:34');
INSERT INTO `sys_logInfo` VALUES ('220', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 03:02:31');
INSERT INTO `sys_logInfo` VALUES ('221', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 03:42:19');
INSERT INTO `sys_logInfo` VALUES ('222', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 03:50:20');
INSERT INTO `sys_logInfo` VALUES ('223', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 04:20:22');
INSERT INTO `sys_logInfo` VALUES ('224', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 04:26:55');
INSERT INTO `sys_logInfo` VALUES ('225', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 05:30:56');
INSERT INTO `sys_logInfo` VALUES ('226', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 05:36:00');
INSERT INTO `sys_logInfo` VALUES ('227', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 05:42:08');
INSERT INTO `sys_logInfo` VALUES ('228', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 05:47:59');
INSERT INTO `sys_logInfo` VALUES ('229', '超级管理员-system', '127.0.0.1', '2020-01-12 05:52:35');
INSERT INTO `sys_logInfo` VALUES ('230', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 06:10:02');
INSERT INTO `sys_logInfo` VALUES ('231', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 06:10:30');
INSERT INTO `sys_logInfo` VALUES ('232', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 06:47:15');
INSERT INTO `sys_logInfo` VALUES ('233', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 06:47:55');
INSERT INTO `sys_logInfo` VALUES ('234', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 07:06:58');
INSERT INTO `sys_logInfo` VALUES ('235', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-12 07:45:23');
INSERT INTO `sys_logInfo` VALUES ('236', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 03:02:20');
INSERT INTO `sys_logInfo` VALUES ('237', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 03:08:28');
INSERT INTO `sys_logInfo` VALUES ('238', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 03:11:05');
INSERT INTO `sys_logInfo` VALUES ('239', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 03:13:40');
INSERT INTO `sys_logInfo` VALUES ('240', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 03:16:00');
INSERT INTO `sys_logInfo` VALUES ('241', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 03:20:27');
INSERT INTO `sys_logInfo` VALUES ('242', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 03:31:34');
INSERT INTO `sys_logInfo` VALUES ('243', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 03:46:27');
INSERT INTO `sys_logInfo` VALUES ('244', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 04:28:35');
INSERT INTO `sys_logInfo` VALUES ('245', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 07:45:11');
INSERT INTO `sys_logInfo` VALUES ('246', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 07:51:39');
INSERT INTO `sys_logInfo` VALUES ('247', '超级管理员-system', '127.0.0.1', '2020-01-13 07:52:20');
INSERT INTO `sys_logInfo` VALUES ('248', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-13 16:32:03');
INSERT INTO `sys_logInfo` VALUES ('249', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 06:01:24');
INSERT INTO `sys_logInfo` VALUES ('250', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 06:26:53');
INSERT INTO `sys_logInfo` VALUES ('251', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 06:29:41');
INSERT INTO `sys_logInfo` VALUES ('252', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 08:02:28');
INSERT INTO `sys_logInfo` VALUES ('253', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 09:34:15');
INSERT INTO `sys_logInfo` VALUES ('254', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 09:41:30');
INSERT INTO `sys_logInfo` VALUES ('255', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 09:47:10');
INSERT INTO `sys_logInfo` VALUES ('256', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 10:19:22');
INSERT INTO `sys_logInfo` VALUES ('257', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 10:29:37');
INSERT INTO `sys_logInfo` VALUES ('258', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 14:06:03');
INSERT INTO `sys_logInfo` VALUES ('259', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 14:27:05');
INSERT INTO `sys_logInfo` VALUES ('260', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 14:40:14');
INSERT INTO `sys_logInfo` VALUES ('261', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 14:52:41');
INSERT INTO `sys_logInfo` VALUES ('262', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-14 15:13:40');
INSERT INTO `sys_logInfo` VALUES ('263', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 09:27:02');
INSERT INTO `sys_logInfo` VALUES ('264', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 09:27:27');
INSERT INTO `sys_logInfo` VALUES ('265', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 09:29:45');
INSERT INTO `sys_logInfo` VALUES ('266', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 09:35:47');
INSERT INTO `sys_logInfo` VALUES ('267', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 10:09:41');
INSERT INTO `sys_logInfo` VALUES ('268', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 10:30:58');
INSERT INTO `sys_logInfo` VALUES ('269', '安然-anran', '0:0:0:0:0:0:0:1', '2020-01-15 10:32:14');
INSERT INTO `sys_logInfo` VALUES ('270', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 10:55:26');
INSERT INTO `sys_logInfo` VALUES ('271', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 11:44:20');
INSERT INTO `sys_logInfo` VALUES ('272', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 11:51:08');
INSERT INTO `sys_logInfo` VALUES ('273', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 11:51:33');
INSERT INTO `sys_logInfo` VALUES ('274', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 11:53:17');
INSERT INTO `sys_logInfo` VALUES ('275', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 11:54:18');
INSERT INTO `sys_logInfo` VALUES ('276', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-15 12:45:21');
INSERT INTO `sys_logInfo` VALUES ('277', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-16 04:01:46');
INSERT INTO `sys_logInfo` VALUES ('278', '孙七-sq', '0:0:0:0:0:0:0:1', '2020-01-16 04:04:28');
INSERT INTO `sys_logInfo` VALUES ('279', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-16 04:09:56');
INSERT INTO `sys_logInfo` VALUES ('280', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-16 04:10:21');
INSERT INTO `sys_logInfo` VALUES ('281', '安然-anran', '0:0:0:0:0:0:0:1', '2020-01-16 04:10:31');
INSERT INTO `sys_logInfo` VALUES ('282', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-16 04:12:00');
INSERT INTO `sys_logInfo` VALUES ('283', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 04:34:44');
INSERT INTO `sys_logInfo` VALUES ('284', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 04:44:04');
INSERT INTO `sys_logInfo` VALUES ('285', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 05:09:56');
INSERT INTO `sys_logInfo` VALUES ('286', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 05:10:29');
INSERT INTO `sys_logInfo` VALUES ('287', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 05:12:31');
INSERT INTO `sys_logInfo` VALUES ('288', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-16 05:17:37');
INSERT INTO `sys_logInfo` VALUES ('289', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 05:17:45');
INSERT INTO `sys_logInfo` VALUES ('290', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-16 05:18:35');
INSERT INTO `sys_logInfo` VALUES ('291', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 05:24:54');
INSERT INTO `sys_logInfo` VALUES ('292', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 10:01:33');
INSERT INTO `sys_logInfo` VALUES ('293', '刘八-lb', '0:0:0:0:0:0:0:1', '2020-01-16 10:03:18');
INSERT INTO `sys_logInfo` VALUES ('294', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-16 10:40:00');
INSERT INTO `sys_logInfo` VALUES ('295', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 03:35:41');
INSERT INTO `sys_logInfo` VALUES ('296', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 03:39:44');
INSERT INTO `sys_logInfo` VALUES ('297', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 03:42:41');
INSERT INTO `sys_logInfo` VALUES ('298', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 03:46:33');
INSERT INTO `sys_logInfo` VALUES ('299', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 04:18:38');
INSERT INTO `sys_logInfo` VALUES ('300', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 04:18:54');
INSERT INTO `sys_logInfo` VALUES ('301', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 08:32:28');
INSERT INTO `sys_logInfo` VALUES ('302', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 08:54:26');
INSERT INTO `sys_logInfo` VALUES ('303', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 09:06:07');
INSERT INTO `sys_logInfo` VALUES ('304', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 09:08:56');
INSERT INTO `sys_logInfo` VALUES ('305', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 09:23:58');
INSERT INTO `sys_logInfo` VALUES ('306', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 09:31:32');
INSERT INTO `sys_logInfo` VALUES ('307', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-17 14:07:53');
INSERT INTO `sys_logInfo` VALUES ('308', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-18 08:31:42');
INSERT INTO `sys_logInfo` VALUES ('309', '超级管理员-system', '127.0.0.1', '2020-01-18 08:34:25');
INSERT INTO `sys_logInfo` VALUES ('310', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-18 08:39:42');
INSERT INTO `sys_logInfo` VALUES ('311', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-18 09:00:09');
INSERT INTO `sys_logInfo` VALUES ('312', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-18 13:47:11');
INSERT INTO `sys_logInfo` VALUES ('313', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-18 14:42:08');
INSERT INTO `sys_logInfo` VALUES ('314', '超级管理员-system', '127.0.0.1', '2020-01-19 05:19:22');
INSERT INTO `sys_logInfo` VALUES ('315', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 05:20:05');
INSERT INTO `sys_logInfo` VALUES ('316', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 05:49:26');
INSERT INTO `sys_logInfo` VALUES ('317', '超级管理员-system', '127.0.0.1', '2020-01-19 05:55:19');
INSERT INTO `sys_logInfo` VALUES ('318', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 05:58:25');
INSERT INTO `sys_logInfo` VALUES ('319', '超级管理员-system', '127.0.0.1', '2020-01-19 06:02:22');
INSERT INTO `sys_logInfo` VALUES ('320', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 06:04:02');
INSERT INTO `sys_logInfo` VALUES ('321', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 08:17:46');
INSERT INTO `sys_logInfo` VALUES ('322', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 08:18:25');
INSERT INTO `sys_logInfo` VALUES ('323', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 16:12:26');
INSERT INTO `sys_logInfo` VALUES ('324', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 16:31:53');
INSERT INTO `sys_logInfo` VALUES ('325', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 16:42:57');
INSERT INTO `sys_logInfo` VALUES ('326', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 17:02:03');
INSERT INTO `sys_logInfo` VALUES ('327', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 17:25:39');
INSERT INTO `sys_logInfo` VALUES ('328', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-19 17:34:01');
INSERT INTO `sys_logInfo` VALUES ('329', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 03:21:01');
INSERT INTO `sys_logInfo` VALUES ('330', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 04:21:40');
INSERT INTO `sys_logInfo` VALUES ('331', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 04:26:40');
INSERT INTO `sys_logInfo` VALUES ('332', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 04:28:03');
INSERT INTO `sys_logInfo` VALUES ('333', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 04:29:45');
INSERT INTO `sys_logInfo` VALUES ('334', '超级管理员-system', '127.0.0.1', '2020-01-21 05:14:44');
INSERT INTO `sys_logInfo` VALUES ('335', '超级管理员-system', '127.0.0.1', '2020-01-21 05:22:28');
INSERT INTO `sys_logInfo` VALUES ('336', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 05:44:15');
INSERT INTO `sys_logInfo` VALUES ('337', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 06:40:43');
INSERT INTO `sys_logInfo` VALUES ('338', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 06:53:39');
INSERT INTO `sys_logInfo` VALUES ('339', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-21 06:57:53');
INSERT INTO `sys_logInfo` VALUES ('340', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 04:22:42');
INSERT INTO `sys_logInfo` VALUES ('341', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 04:27:24');
INSERT INTO `sys_logInfo` VALUES ('342', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 04:27:47');
INSERT INTO `sys_logInfo` VALUES ('343', '李四-ls', '0:0:0:0:0:0:0:1', '2020-01-22 04:59:36');
INSERT INTO `sys_logInfo` VALUES ('344', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 05:13:37');
INSERT INTO `sys_logInfo` VALUES ('345', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 05:17:56');
INSERT INTO `sys_logInfo` VALUES ('346', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 06:06:39');
INSERT INTO `sys_logInfo` VALUES ('347', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 06:26:50');
INSERT INTO `sys_logInfo` VALUES ('348', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 06:32:17');
INSERT INTO `sys_logInfo` VALUES ('349', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 08:54:48');
INSERT INTO `sys_logInfo` VALUES ('350', '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-01-22 09:38:20');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `createtime` datetime DEFAULT NULL,
  `opername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '关于系统V1.3更新公告', '2的广泛地水电费第三方', '2018-08-07 00:00:00', '管理员');
INSERT INTO `sys_notice` VALUES ('10', '关于系统V1.2更新公告', '12312312<img src=\"/resources/layui/images/face/42.gif\" alt=\"[抓狂]\">', '2018-08-07 00:00:00', '超级管理员');
INSERT INTO `sys_notice` VALUES ('11', '关于系统V1.1更新公告', '21321321321<img src=\"/resources/layui/images/face/18.gif\" alt=\"[右哼哼]\">', '2018-08-07 00:00:00', '超级管理员');
INSERT INTO `sys_notice` VALUES ('16', '发的发生', '大大是', '2020-01-10 09:48:16', '超级管理员');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) DEFAULT NULL,
  `percode` varchar(255) DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '0', 'menu', 'itan进销存管理系统', null, '&#xe68e;', '', '', '1', '1', '1');
INSERT INTO `sys_permission` VALUES ('2', '1', 'menu', '基础管理', null, '&#xe857;', '', '', '0', '2', '1');
INSERT INTO `sys_permission` VALUES ('3', '1', 'menu', '进货管理', null, '&#xe645;', '', '', '1', '3', '1');
INSERT INTO `sys_permission` VALUES ('4', '1', 'menu', '销售管理', null, '&#xe611;', '', '', '0', '4', '1');
INSERT INTO `sys_permission` VALUES ('5', '1', 'menu', '系统管理', null, '&#xe614;', '', '', '0', '5', '1');
INSERT INTO `sys_permission` VALUES ('6', '1', 'menu', '其它管理', null, '&#xe628;', '', '', '0', '6', '1');
INSERT INTO `sys_permission` VALUES ('7', '2', 'menu', '客户管理', null, '&#xe651;', '/bus/toCustomerManager', '', '0', '7', '1');
INSERT INTO `sys_permission` VALUES ('8', '2', 'menu', '供应商管理', null, '&#xe658;', '/bus/toProviderManager', '', '0', '8', '1');
INSERT INTO `sys_permission` VALUES ('9', '2', 'menu', '商品管理', null, '&#xe657;', '/bus/toGoodsManager', '', '0', '9', '1');
INSERT INTO `sys_permission` VALUES ('10', '3', 'menu', '商品进货', null, '&#xe756;', '/bus/toInportManager', '', '0', '10', '1');
INSERT INTO `sys_permission` VALUES ('11', '3', 'menu', '商品退货查询', null, '&#xe65a;', '/bus/toOutportManager', '', '0', '11', '1');
INSERT INTO `sys_permission` VALUES ('12', '4', 'menu', '商品销售', null, '&#xe65b;', '', '', '0', '12', '1');
INSERT INTO `sys_permission` VALUES ('13', '4', 'menu', '销售退货查询', null, '&#xe770;', '', '', '0', '13', '1');
INSERT INTO `sys_permission` VALUES ('14', '5', 'menu', '部门管理', null, '&#xe770;', '/sys/toDeptManager', '', '0', '14', '1');
INSERT INTO `sys_permission` VALUES ('15', '5', 'menu', '菜单管理', null, '&#xe857;', '/sys/toMenuManager', '', '0', '15', '1');
INSERT INTO `sys_permission` VALUES ('16', '5', 'menu', '权限管理', '', '&#xe857;', '/sys/toPermissionManager', '', '0', '16', '1');
INSERT INTO `sys_permission` VALUES ('17', '5', 'menu', '角色管理', '', '&#xe650;', '/sys/toRoleManager', '', '0', '17', '1');
INSERT INTO `sys_permission` VALUES ('18', '5', 'menu', '用户管理', '', '&#xe612;', '/sys/toUserManager', '', '0', '18', '1');
INSERT INTO `sys_permission` VALUES ('21', '6', 'menu', '登陆日志', null, '&#xe675;', '/sys/toLoginfo', '', '0', '21', '1');
INSERT INTO `sys_permission` VALUES ('22', '6', 'menu', '系统公告', null, '&#xe756;', '/sys/toNotice', null, '0', '22', '1');
INSERT INTO `sys_permission` VALUES ('30', '14', 'permission', '添加部门', 'dept:create', '', null, null, '0', '24', '1');
INSERT INTO `sys_permission` VALUES ('31', '14', 'permission', '修改部门', 'dept:update', '', null, null, '0', '26', '1');
INSERT INTO `sys_permission` VALUES ('32', '14', 'permission', '删除部门', 'dept:delete', '', null, null, '0', '27', '1');
INSERT INTO `sys_permission` VALUES ('34', '15', 'permission', '添加菜单', 'menu:create', '', '', '', '0', '29', '1');
INSERT INTO `sys_permission` VALUES ('35', '15', 'permission', '修改菜单', 'menu:update', '', null, null, '0', '30', '1');
INSERT INTO `sys_permission` VALUES ('36', '15', 'permission', '删除菜单', 'menu:delete', '', null, null, '0', '31', '1');
INSERT INTO `sys_permission` VALUES ('38', '16', 'permission', '添加权限', 'permission:create', '', null, null, '0', '33', '1');
INSERT INTO `sys_permission` VALUES ('39', '16', 'permission', '修改权限', 'permission:update', '', null, null, '0', '34', '1');
INSERT INTO `sys_permission` VALUES ('40', '16', 'permission', '删除权限', 'permission:delete', '', null, null, '0', '35', '1');
INSERT INTO `sys_permission` VALUES ('42', '17', 'permission', '添加角色', 'role:create', '', null, null, '0', '37', '1');
INSERT INTO `sys_permission` VALUES ('43', '17', 'permission', '修改角色', 'role:update', '', null, null, '0', '38', '1');
INSERT INTO `sys_permission` VALUES ('44', '17', 'permission', '角色删除', 'role:delete', '', null, null, '0', '39', '1');
INSERT INTO `sys_permission` VALUES ('46', '17', 'permission', '分配权限', 'role:selectPermission', '', null, null, '0', '41', '1');
INSERT INTO `sys_permission` VALUES ('47', '18', 'permission', '添加用户', 'user:create', '', null, null, '0', '42', '1');
INSERT INTO `sys_permission` VALUES ('48', '18', 'permission', '修改用户', 'user:update', '', null, null, '0', '43', '1');
INSERT INTO `sys_permission` VALUES ('49', '18', 'permission', '删除用户', 'user:delete', '', null, null, '0', '44', '1');
INSERT INTO `sys_permission` VALUES ('51', '18', 'permission', '用户分配角色', 'user:selectRole', '', null, null, '0', '46', '1');
INSERT INTO `sys_permission` VALUES ('52', '18', 'permission', '重置密码', 'user:resetPwd', null, null, null, '0', '47', '1');
INSERT INTO `sys_permission` VALUES ('53', '14', 'permission', '部门查询', 'dept:view', null, null, null, '0', '48', '1');
INSERT INTO `sys_permission` VALUES ('54', '15', 'permission', '菜单查询', 'menu:view', null, null, null, '0', '49', '1');
INSERT INTO `sys_permission` VALUES ('55', '16', 'permission', '权限查询', 'permission:view', null, null, null, '0', '50', '1');
INSERT INTO `sys_permission` VALUES ('56', '17', 'permission', '角色查询', 'role:view', null, null, null, '0', '51', '1');
INSERT INTO `sys_permission` VALUES ('57', '18', 'permission', '用户查询', 'user:view', null, null, null, '0', '52', '1');
INSERT INTO `sys_permission` VALUES ('68', '7', 'permission', '客户查询', 'customer:view', null, null, null, null, '60', '1');
INSERT INTO `sys_permission` VALUES ('69', '7', 'permission', '客户添加', 'customer:create', null, null, null, null, '61', '1');
INSERT INTO `sys_permission` VALUES ('70', '7', 'permission', '客户修改', 'customer:update', null, null, null, null, '62', '1');
INSERT INTO `sys_permission` VALUES ('71', '7', 'permission', '客户删除', 'customer:delete', null, null, null, null, '63', '1');
INSERT INTO `sys_permission` VALUES ('73', '21', 'permission', '日志查询', 'info:view', null, null, null, null, '65', '1');
INSERT INTO `sys_permission` VALUES ('74', '21', 'permission', '日志删除', 'info:delete', null, null, null, null, '66', '1');
INSERT INTO `sys_permission` VALUES ('75', '21', 'permission', '日志批量删除', 'info:batchdelete', null, null, null, null, '67', '1');
INSERT INTO `sys_permission` VALUES ('76', '22', 'permission', '公告查询', 'notice:view', null, null, null, null, '68', '1');
INSERT INTO `sys_permission` VALUES ('77', '22', 'permission', '公告添加', 'notice:create', null, null, null, null, '69', '1');
INSERT INTO `sys_permission` VALUES ('78', '22', 'permission', '公告修改', 'notice:update', null, null, null, null, '70', '1');
INSERT INTO `sys_permission` VALUES ('79', '22', 'permission', '公告删除', 'notice:delete', null, null, null, null, '71', '1');
INSERT INTO `sys_permission` VALUES ('81', '8', 'permission', '供应商查询', 'provider:view', null, null, null, null, '73', '1');
INSERT INTO `sys_permission` VALUES ('82', '8', 'permission', '供应商添加', 'provider:create', null, null, null, null, '74', '1');
INSERT INTO `sys_permission` VALUES ('83', '8', 'permission', '供应商修改', 'provider:update', null, null, null, null, '75', '1');
INSERT INTO `sys_permission` VALUES ('84', '8', 'permission', '供应商删除', 'provider:delete', null, null, null, null, '76', '1');
INSERT INTO `sys_permission` VALUES ('86', '22', 'permission', '公告查看', 'notice:viewnotice', null, null, null, null, '78', '1');
INSERT INTO `sys_permission` VALUES ('91', '9', 'permission', '商品查询', 'goods:view', null, null, null, '0', '79', '1');
INSERT INTO `sys_permission` VALUES ('92', '9', 'permission', '商品添加', 'goods:create', null, null, null, '0', '80', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '拥有所有菜单权限', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('4', '基础数据管理员', '基础数据管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('5', '仓库管理员', '仓库管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('6', '销售管理员', '销售管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('7', '系统管理员', '系统管理员', '1', '2019-04-10 14:06:32');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`pid`,`rid`) USING BTREE,
  KEY `FK_tcsr9ucxypb3ce1q5qngsfk33` (`rid`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1');
INSERT INTO `sys_role_permission` VALUES ('1', '2');
INSERT INTO `sys_role_permission` VALUES ('1', '3');
INSERT INTO `sys_role_permission` VALUES ('1', '4');
INSERT INTO `sys_role_permission` VALUES ('1', '5');
INSERT INTO `sys_role_permission` VALUES ('1', '6');
INSERT INTO `sys_role_permission` VALUES ('1', '7');
INSERT INTO `sys_role_permission` VALUES ('1', '8');
INSERT INTO `sys_role_permission` VALUES ('1', '9');
INSERT INTO `sys_role_permission` VALUES ('1', '10');
INSERT INTO `sys_role_permission` VALUES ('1', '11');
INSERT INTO `sys_role_permission` VALUES ('1', '12');
INSERT INTO `sys_role_permission` VALUES ('1', '13');
INSERT INTO `sys_role_permission` VALUES ('1', '14');
INSERT INTO `sys_role_permission` VALUES ('1', '15');
INSERT INTO `sys_role_permission` VALUES ('1', '16');
INSERT INTO `sys_role_permission` VALUES ('1', '17');
INSERT INTO `sys_role_permission` VALUES ('1', '18');
INSERT INTO `sys_role_permission` VALUES ('1', '21');
INSERT INTO `sys_role_permission` VALUES ('1', '22');
INSERT INTO `sys_role_permission` VALUES ('1', '30');
INSERT INTO `sys_role_permission` VALUES ('1', '31');
INSERT INTO `sys_role_permission` VALUES ('1', '32');
INSERT INTO `sys_role_permission` VALUES ('1', '34');
INSERT INTO `sys_role_permission` VALUES ('1', '35');
INSERT INTO `sys_role_permission` VALUES ('1', '36');
INSERT INTO `sys_role_permission` VALUES ('1', '38');
INSERT INTO `sys_role_permission` VALUES ('1', '39');
INSERT INTO `sys_role_permission` VALUES ('1', '40');
INSERT INTO `sys_role_permission` VALUES ('1', '42');
INSERT INTO `sys_role_permission` VALUES ('1', '43');
INSERT INTO `sys_role_permission` VALUES ('1', '44');
INSERT INTO `sys_role_permission` VALUES ('1', '46');
INSERT INTO `sys_role_permission` VALUES ('1', '47');
INSERT INTO `sys_role_permission` VALUES ('1', '48');
INSERT INTO `sys_role_permission` VALUES ('1', '49');
INSERT INTO `sys_role_permission` VALUES ('1', '51');
INSERT INTO `sys_role_permission` VALUES ('1', '52');
INSERT INTO `sys_role_permission` VALUES ('1', '53');
INSERT INTO `sys_role_permission` VALUES ('1', '54');
INSERT INTO `sys_role_permission` VALUES ('1', '55');
INSERT INTO `sys_role_permission` VALUES ('1', '56');
INSERT INTO `sys_role_permission` VALUES ('1', '57');
INSERT INTO `sys_role_permission` VALUES ('1', '68');
INSERT INTO `sys_role_permission` VALUES ('1', '69');
INSERT INTO `sys_role_permission` VALUES ('1', '70');
INSERT INTO `sys_role_permission` VALUES ('1', '71');
INSERT INTO `sys_role_permission` VALUES ('1', '73');
INSERT INTO `sys_role_permission` VALUES ('1', '74');
INSERT INTO `sys_role_permission` VALUES ('1', '75');
INSERT INTO `sys_role_permission` VALUES ('1', '76');
INSERT INTO `sys_role_permission` VALUES ('1', '77');
INSERT INTO `sys_role_permission` VALUES ('1', '78');
INSERT INTO `sys_role_permission` VALUES ('1', '79');
INSERT INTO `sys_role_permission` VALUES ('1', '81');
INSERT INTO `sys_role_permission` VALUES ('1', '82');
INSERT INTO `sys_role_permission` VALUES ('1', '83');
INSERT INTO `sys_role_permission` VALUES ('1', '84');
INSERT INTO `sys_role_permission` VALUES ('1', '86');
INSERT INTO `sys_role_permission` VALUES ('1', '91');
INSERT INTO `sys_role_permission` VALUES ('1', '92');
INSERT INTO `sys_role_permission` VALUES ('4', '1');
INSERT INTO `sys_role_permission` VALUES ('4', '2');
INSERT INTO `sys_role_permission` VALUES ('4', '7');
INSERT INTO `sys_role_permission` VALUES ('4', '8');
INSERT INTO `sys_role_permission` VALUES ('4', '9');
INSERT INTO `sys_role_permission` VALUES ('4', '68');
INSERT INTO `sys_role_permission` VALUES ('4', '69');
INSERT INTO `sys_role_permission` VALUES ('4', '70');
INSERT INTO `sys_role_permission` VALUES ('4', '71');
INSERT INTO `sys_role_permission` VALUES ('4', '81');
INSERT INTO `sys_role_permission` VALUES ('4', '82');
INSERT INTO `sys_role_permission` VALUES ('4', '83');
INSERT INTO `sys_role_permission` VALUES ('4', '84');
INSERT INTO `sys_role_permission` VALUES ('4', '91');
INSERT INTO `sys_role_permission` VALUES ('4', '92');
INSERT INTO `sys_role_permission` VALUES ('5', '1');
INSERT INTO `sys_role_permission` VALUES ('5', '3');
INSERT INTO `sys_role_permission` VALUES ('5', '4');
INSERT INTO `sys_role_permission` VALUES ('5', '10');
INSERT INTO `sys_role_permission` VALUES ('5', '11');
INSERT INTO `sys_role_permission` VALUES ('5', '12');
INSERT INTO `sys_role_permission` VALUES ('5', '13');
INSERT INTO `sys_role_permission` VALUES ('6', '1');
INSERT INTO `sys_role_permission` VALUES ('6', '4');
INSERT INTO `sys_role_permission` VALUES ('6', '12');
INSERT INTO `sys_role_permission` VALUES ('6', '13');
INSERT INTO `sys_role_permission` VALUES ('7', '1');
INSERT INTO `sys_role_permission` VALUES ('7', '5');
INSERT INTO `sys_role_permission` VALUES ('7', '6');
INSERT INTO `sys_role_permission` VALUES ('7', '14');
INSERT INTO `sys_role_permission` VALUES ('7', '15');
INSERT INTO `sys_role_permission` VALUES ('7', '16');
INSERT INTO `sys_role_permission` VALUES ('7', '17');
INSERT INTO `sys_role_permission` VALUES ('7', '18');
INSERT INTO `sys_role_permission` VALUES ('7', '21');
INSERT INTO `sys_role_permission` VALUES ('7', '22');
INSERT INTO `sys_role_permission` VALUES ('7', '30');
INSERT INTO `sys_role_permission` VALUES ('7', '31');
INSERT INTO `sys_role_permission` VALUES ('7', '32');
INSERT INTO `sys_role_permission` VALUES ('7', '34');
INSERT INTO `sys_role_permission` VALUES ('7', '35');
INSERT INTO `sys_role_permission` VALUES ('7', '36');
INSERT INTO `sys_role_permission` VALUES ('7', '38');
INSERT INTO `sys_role_permission` VALUES ('7', '39');
INSERT INTO `sys_role_permission` VALUES ('7', '40');
INSERT INTO `sys_role_permission` VALUES ('7', '42');
INSERT INTO `sys_role_permission` VALUES ('7', '43');
INSERT INTO `sys_role_permission` VALUES ('7', '44');
INSERT INTO `sys_role_permission` VALUES ('7', '46');
INSERT INTO `sys_role_permission` VALUES ('7', '47');
INSERT INTO `sys_role_permission` VALUES ('7', '48');
INSERT INTO `sys_role_permission` VALUES ('7', '49');
INSERT INTO `sys_role_permission` VALUES ('7', '51');
INSERT INTO `sys_role_permission` VALUES ('7', '52');
INSERT INTO `sys_role_permission` VALUES ('7', '53');
INSERT INTO `sys_role_permission` VALUES ('7', '54');
INSERT INTO `sys_role_permission` VALUES ('7', '55');
INSERT INTO `sys_role_permission` VALUES ('7', '56');
INSERT INTO `sys_role_permission` VALUES ('7', '57');
INSERT INTO `sys_role_permission` VALUES ('7', '73');
INSERT INTO `sys_role_permission` VALUES ('7', '74');
INSERT INTO `sys_role_permission` VALUES ('7', '75');
INSERT INTO `sys_role_permission` VALUES ('7', '76');
INSERT INTO `sys_role_permission` VALUES ('7', '77');
INSERT INTO `sys_role_permission` VALUES ('7', '78');
INSERT INTO `sys_role_permission` VALUES ('7', '79');
INSERT INTO `sys_role_permission` VALUES ('7', '86');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `rid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`) USING BTREE,
  KEY `FK_203gdpkwgtow7nxlo2oap5jru` (`rid`) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_role_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '2');
INSERT INTO `sys_role_user` VALUES ('1', '10');
INSERT INTO `sys_role_user` VALUES ('4', '6');
INSERT INTO `sys_role_user` VALUES ('4', '10');
INSERT INTO `sys_role_user` VALUES ('5', '4');
INSERT INTO `sys_role_user` VALUES ('5', '5');
INSERT INTO `sys_role_user` VALUES ('5', '10');
INSERT INTO `sys_role_user` VALUES ('6', '5');
INSERT INTO `sys_role_user` VALUES ('7', '3');
INSERT INTO `sys_role_user` VALUES ('7', '6');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `hiredate` datetime DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT '1',
  `ordernum` int(11) DEFAULT NULL,
  `type` int(255) DEFAULT NULL COMMENT '用户类型[0超级管理员1，管理员，2普通用户]',
  `imgpath` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_3rrcpvho2w1mx1sfiuuyir1h` (`deptid`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`deptid`) REFERENCES `sys_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '超级管理员', 'system', '系统深处的男人', '1', '超级管理员', '532ac00e86893901af5f0be6b704dbc7', '1', '2018-06-25 11:06:34', null, '1', '1', '0', '../resources/images/defaultusertitle.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES ('2', '李四', 'ls', '武汉', '0', 'KING', 'b07b848d69e0553b80e601d31571797e', '1', '2018-06-25 11:06:36', null, '1', '2', '1', '../resources/images/defaultusertitle.jpg', 'FC1EE06AE4354D3FBF7FDD15C8FCDA71');
INSERT INTO `sys_user` VALUES ('3', '王五', 'ww', '武汉', '1', '管理员', '3c3f971eae61e097f59d52360323f1c8', '3', '2018-06-25 11:06:38', '2', '1', '3', '1', '../resources/images/defaultusertitle.jpg', '3D5F956E053C4E85B7D2681386E235D2');
INSERT INTO `sys_user` VALUES ('4', '赵六', 'zl', '武汉', '1', '程序员', '2e969742a7ea0c7376e9551d578e05dd', '4', '2018-06-25 11:06:40', '3', '1', '4', '1', '../resources/images/defaultusertitle.jpg', '6480EE1391E34B0886ACADA501E31145');
INSERT INTO `sys_user` VALUES ('5', '孙七', 'sq', '武汉', '1', '程序员', '47b4c1ad6e4b54dd9387a09cb5a03de1', '2', '2018-06-25 11:06:43', '4', '1', '5', '1', '../resources/images/defaultusertitle.jpg', 'FE3476C3E3674E5690C737C269FCBF8E');
INSERT INTO `sys_user` VALUES ('6', '刘八', 'lb', '深圳', '1', '程序员', 'bcee2b05b4b591106829aec69a094806', '4', '2018-08-06 11:21:12', '3', '1', '6', '1', '../resources/images/defaultusertitle.jpg', 'E6CCF54A09894D998225878BBD139B20');
INSERT INTO `sys_user` VALUES ('10', '安然', 'anran', '武汉', '1', '武汉一哥', '48acb32697e4fbdf634df28fa8d800e5', '4', '2020-01-15 10:31:40', '4', '1', '7', '1', null, 'D6B00473902B48E48B4D50DF0D6DA765');
