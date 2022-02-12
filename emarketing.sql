/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : emarketing

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2022-02-12 18:22:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `active`
-- ----------------------------
DROP TABLE IF EXISTS `active`;
CREATE TABLE `active` (
  `active_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '活动id',
  `active_name` varchar(255) DEFAULT NULL COMMENT '活动名字',
  `active_content` varchar(255) DEFAULT NULL COMMENT '活动简介',
  `full_price` decimal(10,2) DEFAULT NULL COMMENT '活动满减价格（满）',
  `minus_price` decimal(10,2) DEFAULT NULL COMMENT '活动满减价格（减）',
  `active_path` varchar(255) DEFAULT NULL COMMENT '活动图片',
  `delete_flag` int(1) DEFAULT NULL COMMENT '是否启用 0：启用 1：禁用',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`active_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of active
-- ----------------------------
INSERT INTO `active` VALUES ('1', '活动2', '活动简介', '99.99', '9.99', 'http://localhost:8090/static/upload/壁纸.jpg', '0', '2021-04-16 22:54:36');
INSERT INTO `active` VALUES ('3', 'summer sale', '办公用品狂欢季', '200.00', '20.00', 'http://localhost:8090/static/upload/活动2-恢复的.jpg', '0', '2021-05-09 23:26:27');
INSERT INTO `active` VALUES ('4', '活动3', '活动简介', '99.99', '9.99', 'http://localhost:8090/static/upload/壁纸.jpg', '0', '2021-04-21 13:28:15');
INSERT INTO `active` VALUES ('5', '活动2', '活动简介', '99.99', '9.99', 'http://localhost:8090/static/upload/壁纸.jpg', '1', '2021-04-16 23:05:03');
INSERT INTO `active` VALUES ('6', '3.8女王节', '3.8女王节，换醒真我', '500.00', '60.00', 'http://localhost:8090/static/upload/活动1.jpg', '1', '2021-05-09 23:27:17');
INSERT INTO `active` VALUES ('7', '1212狂欢盛典', '双十二大促', '400.00', '50.00', 'http://localhost:8090/static/upload/活动3.jpg', '0', '2021-05-09 23:25:54');
INSERT INTO `active` VALUES ('8', '双十一', '点亮双十一', '400.00', '50.00', 'http://localhost:8090/static/upload/活动4.jpg', '0', '2021-05-11 21:46:08');

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(255) DEFAULT NULL COMMENT '管理员名字',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for `commend`
-- ----------------------------
DROP TABLE IF EXISTS `commend`;
CREATE TABLE `commend` (
  `commend_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `good_id` int(255) DEFAULT NULL COMMENT '商品id',
  `comment` varchar(255) DEFAULT NULL COMMENT '留言内容',
  `user_id` int(255) DEFAULT NULL COMMENT '用户id',
  `commend_time` datetime DEFAULT NULL COMMENT '留言时间',
  `reply` varchar(255) DEFAULT NULL COMMENT '商家回复',
  `reply_time` datetime DEFAULT NULL COMMENT '商家回复时间',
  `order_num` varchar(255) DEFAULT NULL COMMENT '订单编号',
  PRIMARY KEY (`commend_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of commend
-- ----------------------------
INSERT INTO `commend` VALUES ('6', '2', '暖hi', '6', '2021-04-27 23:26:34', '', null, '142154052321546240');
INSERT INTO `commend` VALUES ('7', '2', '回家哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈', '6', '2021-04-27 23:26:49', 'nuhaoakhjkahhskjahdasd', '2021-04-27 23:27:31', '142154275546599420');
INSERT INTO `commend` VALUES ('8', '3', '非常好用', '6', '2021-05-12 23:10:48', '感谢支持', '2021-05-14 21:05:42', '142470059883892740');
INSERT INTO `commend` VALUES ('9', '2', '我评论他', '6', '2021-05-12 23:43:25', '', null, '142470059883892740');
INSERT INTO `commend` VALUES ('10', '2', '文件抽屉非常好用', '26', '2021-05-14 15:50:43', '', null, '148202843831144450');
INSERT INTO `commend` VALUES ('11', '3', '真好用', '28', '2021-05-14 21:14:06', '', null, '148283528642170880');
INSERT INTO `commend` VALUES ('12', '20', 'zhehaoyong', '6', '2021-05-15 13:43:06', '', null, '148533073775038460');

-- ----------------------------
-- Table structure for `dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `dictionary_id` int(11) NOT NULL,
  `dictionary_name` varchar(255) DEFAULT NULL,
  `dictionary_value` decimal(5,2) DEFAULT NULL,
  `dictionary_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dictionary_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of dictionary
-- ----------------------------
INSERT INTO `dictionary` VALUES ('1', 'cash_proportion', '0.10', '消费金额兑换积分比例');
INSERT INTO `dictionary` VALUES ('2', 'integral_proportion', '0.02', '积分抵扣现金比例');

-- ----------------------------
-- Table structure for `good`
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `good_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `good_name` varchar(255) DEFAULT NULL COMMENT '商品名字',
  `good_path` varchar(255) DEFAULT NULL COMMENT '商品图片路径',
  `good_price` decimal(10,2) DEFAULT NULL COMMENT '商品价格',
  `delete_flag` int(1) DEFAULT NULL COMMENT '是否启用 0：启用 1：禁用',
  `sale_num` int(11) DEFAULT NULL COMMENT '销量',
  `type` int(255) DEFAULT NULL COMMENT '商品种类 0：办公设备 1：办公耗材 2：办公纸类 3：笔类 4：记事本类 5：装订器材 6：文件夹类 7：办公刀具 8：财会用品',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `good_description` varchar(255) DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`good_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES ('2', '文件抽屉', 'http://localhost:8090/static/upload/869095469.jpg', '10.00', '0', '22', '0', '2021-05-11 21:28:12', '文件抽屉');
INSERT INTO `good` VALUES ('3', '封装胶带', 'http://localhost:8090/static/upload/583066721.jpg', '5.20', '0', '17', '1', '2021-05-11 22:01:09', '封装胶带');
INSERT INTO `good` VALUES ('4', '图钉', 'http://localhost:8090/static/upload/1225853925.jpg', '10.25', '0', '7', '0', '2021-05-11 21:26:57', '图钉');
INSERT INTO `good` VALUES ('5', '长尾夹', 'http://localhost:8090/static/upload/tb_image_share_1620736728787.jpg', '10.25', '1', '0', '0', '2021-05-11 21:26:25', '彩色混合长尾夹');
INSERT INTO `good` VALUES ('6', '彩色铅笔', 'http://localhost:8090/static/upload/1920250347.jpg', '12.00', '1', '0', '3', '2021-05-11 21:29:39', '彩色铅笔');
INSERT INTO `good` VALUES ('7', '黑色签字笔', 'http://localhost:8090/static/upload/-1894374015.jpg', '10.00', '0', '1', '3', '2021-05-11 21:36:41', '学生都爱用');
INSERT INTO `good` VALUES ('8', '笔记本', 'http://localhost:8090/static/upload/-1244767083.jpg', '15.00', '0', '6', '4', '2021-05-11 21:25:05', '给孩子买一本吧');
INSERT INTO `good` VALUES ('9', '美纹胶带', 'http://localhost:8090/static/upload/596585063.jpg', '50.00', '0', '0', '1', '2021-05-11 21:38:07', '美纹胶带');
INSERT INTO `good` VALUES ('10', '美工刀', 'http://localhost:8090/static/upload/14877148.jpg', '10.00', '0', '0', '7', '2021-05-11 21:38:37', '美工刀');
INSERT INTO `good` VALUES ('11', '回形针', 'http://localhost:8090/static/upload/tb_image_share_1620736683766.jpg', '30.89', '0', '0', '5', '2021-05-11 21:39:52', '回形针');
INSERT INTO `good` VALUES ('12', '资料夹', 'http://localhost:8090/static/upload/225613978.jpg', '18.90', '0', '0', '6', '2021-05-11 21:40:26', '资料夹');
INSERT INTO `good` VALUES ('13', '计算器', 'http://localhost:8090/static/upload/tb_image_share_1620736254881.jpg', '24.90', '0', '0', '8', '2021-05-11 21:41:01', '计算器');
INSERT INTO `good` VALUES ('14', '单据', 'http://localhost:8090/static/upload/tb_image_share_1620736277956.jpg', '58.78', '0', '3', '8', '2021-05-11 21:51:28', '单据');
INSERT INTO `good` VALUES ('15', '铅笔', 'http://localhost:8090/static/upload/2080572324.jpg', '3.50', '0', '0', '3', '2021-05-11 21:42:47', '2B铅笔');
INSERT INTO `good` VALUES ('16', '胶棒', 'http://localhost:8090/static/upload/tb_image_share_1620736757617.jpg', '14.89', '0', '0', '5', '2021-05-11 21:43:32', '胶棒');
INSERT INTO `good` VALUES ('17', '立体文件夹', 'http://localhost:8090/static/upload/1596305197.jpg', '34.89', '0', '0', '6', '2021-05-11 21:44:13', '立体文件夹');
INSERT INTO `good` VALUES ('18', '打印纸', 'http://localhost:8090/static/upload/tb_image_share_1620736155220.jpg', '55.79', '0', '0', '2', '2021-05-11 21:52:26', 'A4打印纸');
INSERT INTO `good` VALUES ('19', '便利贴', 'http://localhost:8090/static/upload/tb_image_share_1620736073356.jpg', '17.50', '0', '0', '2', '2021-05-11 22:00:53', '便利贴');
INSERT INTO `good` VALUES ('20', '按动圆珠笔', 'http://localhost:8090/static/upload/-682300202.jpg', '35.69', '0', '1', '3', '2021-05-11 22:00:41', '按动圆珠笔');
INSERT INTO `good` VALUES ('21', '小笔记本', 'http://localhost:8090/static/upload/1174792371.jpg', '3.50', '0', '1', '4', '2021-05-11 21:54:32', '小笔记本');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_num` varchar(255) DEFAULT NULL COMMENT '订单编号',
  `user_id` int(255) DEFAULT NULL COMMENT '用户id',
  `order_time` datetime DEFAULT NULL COMMENT '订单完成时间',
  `order_money` decimal(10,2) DEFAULT NULL COMMENT '交易额',
  `integral` varchar(255) DEFAULT NULL COMMENT '积分',
  `active_id` int(11) DEFAULT NULL COMMENT '参与的活动id',
  `preferential_amount` decimal(65,0) DEFAULT NULL COMMENT '优惠金额',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('38', '147597788979531780', '6', '2021-05-12 23:45:30', '65.60', '7', '3', '0');
INSERT INTO `order` VALUES ('39', '147598398470623230', '6', '2021-05-12 23:47:51', '3.50', '0', '3', '0');
INSERT INTO `order` VALUES ('40', '148202843831144450', '26', '2021-05-14 15:49:51', '20.00', '2', '3', '0');
INSERT INTO `order` VALUES ('41', '148283528642170880', '28', '2021-05-14 21:11:18', '10.40', '1', '7', '0');
INSERT INTO `order` VALUES ('42', '148319641406672900', '6', '2021-05-14 23:33:52', '122.76', '12', '7', '0');
INSERT INTO `order` VALUES ('43', '148322669962596350', '6', '2021-05-14 23:45:58', '122.76', '12', '7', '0');
INSERT INTO `order` VALUES ('44', '148323857185509380', '6', '2021-05-14 23:50:41', '10.25', '1', '7', '0');
INSERT INTO `order` VALUES ('45', '148533073775038460', '6', '2021-05-15 13:41:56', '35.69', '4', '7', '0');

-- ----------------------------
-- Table structure for `order_item`
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `order_item_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int(11) NOT NULL COMMENT '订单表id',
  `good_id` int(11) DEFAULT NULL COMMENT '商品id',
  `good_num` int(11) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`order_item_id`,`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('27', '38', '2', '5');
INSERT INTO `order_item` VALUES ('28', '38', '3', '3');
INSERT INTO `order_item` VALUES ('29', '39', '21', '1');
INSERT INTO `order_item` VALUES ('30', '40', '2', '2');
INSERT INTO `order_item` VALUES ('31', '41', '3', '2');
INSERT INTO `order_item` VALUES ('32', '42', '14', '1');
INSERT INTO `order_item` VALUES ('33', '42', '3', '2');
INSERT INTO `order_item` VALUES ('34', '43', '3', '1');
INSERT INTO `order_item` VALUES ('35', '43', '14', '2');
INSERT INTO `order_item` VALUES ('36', '43', '4', '1');
INSERT INTO `order_item` VALUES ('37', '44', '4', '1');
INSERT INTO `order_item` VALUES ('38', '45', '20', '1');

-- ----------------------------
-- Table structure for `recommendation`
-- ----------------------------
DROP TABLE IF EXISTS `recommendation`;
CREATE TABLE `recommendation` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `good_ids` varchar(255) DEFAULT NULL COMMENT '商品id',
  `introduction` longtext COMMENT '简介内容',
  `active_id` int(11) DEFAULT NULL COMMENT '推荐活动id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of recommendation
-- ----------------------------
INSERT INTO `recommendation` VALUES ('1', '18,4,7', '<p style=\"text-align: center;\">简介</p>\n<p class=\"MsoNormal\" style=\"text-indent: 21.0000pt; mso-char-indent-count: 2.0000; page-break-after: avoid; line-height: 20.0000pt; mso-line-height-rule: exactly;\"><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt;\"><span style=\"font-family: 宋体;\">办公用品行业是典型的</span><span style=\"font-family: 宋体;\">&ldquo;小商品、大市场&rdquo;行业。近年来，随着我国国民经济的快速发展、商务活动日益频繁以及消费者购买力的增强，办公用品行业发展非常迅速，相关统计研究发现我国每年的企业办公用品采购的市场规模已达2300亿元左右，年保持25%以上的增长率</span></span><sup><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt; vertical-align: super;\"><span style=\"font-family: 宋体;\">[3</span></span></sup><sup><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt; vertical-align: super;\"><span style=\"font-family: 宋体;\">]</span></span></sup><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt;\">，我国目前已经成为了世界上最大的办公用品消耗国。</span></p>\n<p class=\"MsoNormal\" style=\"text-indent: 21.0000pt; mso-char-indent-count: 2.0000; page-break-after: avoid; line-height: 20.0000pt; mso-line-height-rule: exactly;\"><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt;\">目前得力、史泰、晨光等多个办公用品品牌有了长足的发展，在市场中站稳了脚跟，逐步成为我国办公用品市场中的互联网品牌</span><sup><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt; vertical-align: super;\"><span style=\"font-family: 宋体;\">[3]</span></span></sup><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt;\"><span style=\"font-family: 宋体;\">。在传统的办公用品销售市场中，小型店铺与大型企业相比</span><span style=\"font-family: 宋体;\">, 普遍存在各种人才不足、客源短缺、资金不到位等问题, 因此也限制了很多店铺的发展。随着网络经济时代的到来, 网络营销不仅改变了传统的经营模式, 而且使得小型商铺也拥有了大型企业所具备的优势</span></span><sup><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt; vertical-align: super;\"><span style=\"font-family: 宋体;\">[4]</span></span></sup><span style=\"mso-spacerun: \'yes\'; font-family: 宋体; font-size: 10.5000pt; mso-font-kerning: 1.0000pt;\">。办公用品营销网站的信息化建设对于提高办公用品品牌知名度，促进销售业绩提高，产品竞争力具有重要意义。以上内容为简介</span></p>\n<p><img src=\"static/upload/背景4.jpg\" alt=\"\" width=\"600\" height=\"600\" /></p>', '7');

-- ----------------------------
-- Table structure for `reviews`
-- ----------------------------
DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews` (
  `reviews_id` int(11) NOT NULL AUTO_INCREMENT,
  `reviews_content` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  PRIMARY KEY (`reviews_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of reviews
-- ----------------------------
INSERT INTO `reviews` VALUES ('8', '网站留言test1', '12', '2021-05-14 12:51:48', '你好', '2021-05-14 16:05:06');
INSERT INTO `reviews` VALUES ('9', '网站留言test2', '6', '2021-05-14 16:04:23', '', '2021-05-14 21:07:21');

-- ----------------------------
-- Table structure for `shopping`
-- ----------------------------
DROP TABLE IF EXISTS `shopping`;
CREATE TABLE `shopping` (
  `shopping_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `good_id` int(255) DEFAULT NULL COMMENT '商品id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `good_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`shopping_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of shopping
-- ----------------------------
INSERT INTO `shopping` VALUES ('9', '3', '1', '2', '2021-04-04 13:49:49');
INSERT INTO `shopping` VALUES ('10', '4', '1', '3', '2021-04-04 19:14:19');
INSERT INTO `shopping` VALUES ('11', '3', '2', '1', '2021-04-21 03:16:08');
INSERT INTO `shopping` VALUES ('28', '2', '6', '5', '2021-05-14 15:52:13');
INSERT INTO `shopping` VALUES ('29', '3', '6', '2', '2021-05-19 23:09:39');
INSERT INTO `shopping` VALUES ('31', '19', '26', '1', '2021-05-14 15:51:16');
INSERT INTO `shopping` VALUES ('32', '4', '6', '1', '2021-05-14 23:45:44');
INSERT INTO `shopping` VALUES ('38', '18', '28', '3', '2021-05-14 21:35:04');
INSERT INTO `shopping` VALUES ('39', '17', '28', '1', '2021-05-14 21:29:09');
INSERT INTO `shopping` VALUES ('40', '16', '28', '2', '2021-05-14 21:32:02');
INSERT INTO `shopping` VALUES ('43', '14', '6', '2', '2021-05-14 23:33:01');
INSERT INTO `shopping` VALUES ('44', '20', '6', '1', '2021-05-15 13:41:37');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `idcard` varchar(255) DEFAULT NULL COMMENT '身份证号码',
  `path` varchar(255) DEFAULT NULL COMMENT '头像图片路径',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `telephone` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `gender` int(1) DEFAULT NULL COMMENT '性别 1：男 2：女',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `integral` int(255) DEFAULT NULL COMMENT '用户积分',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6', 'user2', '321323199805114118', 'http://localhost:8090/static/upload/9.jpg', '1212', '15052130946', '1154333284@qq.com', '江苏省无锡市新吴区正大万物城14单元102室', '2', '22', '105', '葛星星', '2021-05-14 00:00:00', '2021-05-19 23:09:30');
INSERT INTO `user` VALUES ('12', 'user6', '3221323199805114118', 'http://localhost:8090/static/upload/2 (2).jpg', '123456', '15052130946', '1154333284@qq.com', '北京市市辖区东城区无', '1', '20', '85', '葛飞', '2021-05-14 00:00:00', '2021-05-14 12:47:27');
INSERT INTO `user` VALUES ('26', 'user1', '341221199805017567', 'http://localhost:8090/static/upload/3.jpg', '123456', '15855480345', '123@qq.com', '北京市市辖区西城区111', '1', '22', '0', '冷冷', null, '2021-05-14 15:23:15');
INSERT INTO `user` VALUES ('28', 'user3', '341221199805017567', 'http://localhost:8090/static/upload/4.jpg', '123456', '15855480345', '2383920976@qq.com', '安徽省滁州市琅琊区滁州学院', '2', '23', '0', '梁小兰', null, '2021-05-14 21:03:33');
INSERT INTO `user` VALUES ('29', 'user4', '341221199805017567', 'http://localhost:8090/static/upload/8.jpg', '123456', '15855480445', '123@qq.com', '河北省石家庄市市辖区会更好', '2', '23', '0', '框架', null, '2021-05-14 23:44:40');
