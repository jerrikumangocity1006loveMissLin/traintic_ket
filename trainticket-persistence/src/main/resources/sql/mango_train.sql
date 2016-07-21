DROP DATABASE IF EXISTS `mango_train`;

CREATE DATABASE  `mango_train` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

--CREATE USER 'train_user'@'localhost' IDENTIFIED BY  'train654321';

USE `mango_train`;

/*
Navicat MySQL Data Transfer

Source Server         : 火车票库
Source Database       : mango_train

Target Server Type    : MYSQL
Date: 2016-07-14 10:06:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_train_account
-- ----------------------------
DROP TABLE IF EXISTS `t_train_account`;
CREATE TABLE `t_train_account` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键  自动自增',
  `account` varchar(20) DEFAULT NULL COMMENT '12306账号',
  `password` varchar(20) DEFAULT NULL COMMENT '12306密码',
  `type` varchar(2) DEFAULT NULL COMMENT '类型  0-公有 1-私有 2-统一',
  `account_status` varchar(2) DEFAULT NULL COMMENT '12306账号状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train_applicant
-- ----------------------------
DROP TABLE IF EXISTS `t_train_applicant`;
CREATE TABLE `t_train_applicant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '申请人ID',
  `name` varchar(64) DEFAULT NULL COMMENT '申请人姓名',
  `telephone` varchar(20) DEFAULT NULL COMMENT '申请人手机',
  `tel_type` varchar(2) DEFAULT NULL COMMENT '是否境外手机，0=否，1=是',
  `mobile` varchar(20) DEFAULT NULL COMMENT '电话',
  `operator` varchar(20) DEFAULT NULL COMMENT '操作者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train_charge
-- ----------------------------
DROP TABLE IF EXISTS `t_train_charge`;
CREATE TABLE `t_train_charge` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '服务费ID',
  `order_item_id` bigint(10) DEFAULT NULL COMMENT '订购项ID',
  `name` varchar(100) DEFAULT NULL COMMENT '服务费名称',
  `price` decimal(10,2) DEFAULT NULL COMMENT '销售价',
  `type` varchar(20) DEFAULT NULL COMMENT '服务类型',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='服务费';

-- ----------------------------
-- Table structure for t_train_contact
-- ----------------------------
DROP TABLE IF EXISTS `t_train_contact`;
CREATE TABLE `t_train_contact` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '联系人ID',
  `order_id` bigint(10) DEFAULT NULL COMMENT '订单ID',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `telephone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `mail` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `fax` varchar(20) DEFAULT NULL COMMENT '传真',
  `foreign_mobile` varchar(20) DEFAULT NULL COMMENT '境外手机',
  `is_foreign` tinyint(1) DEFAULT NULL COMMENT '是否境外手机',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=402 DEFAULT CHARSET=utf8 COMMENT='联系人';

-- ----------------------------
-- Table structure for t_train_delivery
-- ----------------------------
DROP TABLE IF EXISTS `t_train_delivery`;
CREATE TABLE `t_train_delivery` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '配送ID',
  `delivery_cn` varchar(100) DEFAULT NULL COMMENT '配送单号',
  `order_item_id` bigint(10) DEFAULT NULL COMMENT '订购项ID',
  `type` varchar(10) DEFAULT NULL COMMENT '配送类型',
  `status` varchar(10) DEFAULT NULL COMMENT '配送状态',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '配送费用',
  `city` varchar(100) DEFAULT NULL COMMENT '配送城市',
  `address` varchar(255) DEFAULT NULL COMMENT '配送地址',
  `delivery_time` timestamp NULL DEFAULT NULL COMMENT '配送开始时间',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='配送';

-- ----------------------------
-- Table structure for t_train_frequent_traveller
-- ----------------------------
DROP TABLE IF EXISTS `t_train_frequent_traveller`;
CREATE TABLE `t_train_frequent_traveller` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键  自动自增',
  `account_id` bigint(10) NOT NULL COMMENT '12306账号表id',
  `name` varchar(20) DEFAULT NULL COMMENT '常旅客姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话手机',
  `certificate_type` varchar(5) DEFAULT NULL COMMENT '证件类型',
  `certificate` varchar(20) DEFAULT NULL COMMENT '证件',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `passenger_type` varchar(5) DEFAULT NULL COMMENT '旅客类型 1-成人 2-儿童 3-学生 4-残军人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train_insurance
-- ----------------------------
DROP TABLE IF EXISTS `t_train_insurance`;
CREATE TABLE `t_train_insurance` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '保险ID',
  `ticket_id` bigint(10) DEFAULT NULL COMMENT '火车票ID',
  `type` varchar(10) DEFAULT NULL COMMENT '保险类型',
  `num` bigint(10) DEFAULT NULL COMMENT '保险份数',
  `descp` varchar(255) DEFAULT NULL COMMENT '保险说明',
  `status` varchar(10) DEFAULT NULL COMMENT '投保状态',
  `price` decimal(10,2) DEFAULT NULL COMMENT '销售价',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8 COMMENT='保险';

-- ----------------------------
-- Table structure for t_train_invoice
-- ----------------------------
DROP TABLE IF EXISTS `t_train_invoice`;
CREATE TABLE `t_train_invoice` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `invoice_head` varchar(50) DEFAULT NULL COMMENT '发票抬头',
  `address` varchar(100) DEFAULT NULL COMMENT '发票地址',
  `invoice_unit` varchar(50) DEFAULT NULL COMMENT '开票单位',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '发票金额',
  `drawer` varchar(50) DEFAULT NULL COMMENT '开票人',
  `order_item_id` int(10) DEFAULT NULL COMMENT '订单项id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COMMENT='发票';

-- ----------------------------
-- Table structure for t_train_message_receiver
-- ----------------------------
DROP TABLE IF EXISTS `t_train_message_receiver`;
CREATE TABLE `t_train_message_receiver` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `receiver` varchar(16) DEFAULT NULL,
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `mail` varchar(32) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `order_id` bigint(10) DEFAULT NULL COMMENT '订单Id',
  `isSend` smallint(1) DEFAULT NULL COMMENT '消息是否发送 0-未发送 1-已发送',
  `affirm` varchar(2) DEFAULT NULL COMMENT '是否接收确认短信 N-否 Y-是',
  `issut` varchar(2) DEFAULT NULL COMMENT '是否接收出票短信 N-否 Y-是',
  `approval_mail` varchar(2) DEFAULT NULL COMMENT '是否接收收审批邮件  Y-是 N-否',
  `trip_mail` varchar(2) DEFAULT NULL COMMENT '是否接受行程单邮件',
  `send_model` varchar(4) DEFAULT NULL COMMENT '邮件发送方式 zs=主送 cc=抄送',
  `memberCd` varchar(16) DEFAULT NULL COMMENT '会员编号',
  `identity` varchar(10) DEFAULT NULL COMMENT '身份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train_message_template
-- ----------------------------
DROP TABLE IF EXISTS `t_train_message_template`;
CREATE TABLE `t_train_message_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL COMMENT '消息类型(短信，邮件，传真)',
  `template_num` int(10) DEFAULT NULL COMMENT '消息模板编号',
  `subject` varchar(50) DEFAULT NULL COMMENT '标题',
  `message_content` varchar(2000) DEFAULT NULL COMMENT '消息内容',
  PRIMARY KEY (`id`),
  UNIQUE KEY `template_union` (`template_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='消息模板';

-- ----------------------------
-- Records of t_train_message_template
-- ----------------------------
INSERT INTO `t_train_message_template` VALUES ('1', 'SMS', '10', null, '我们已经帮你购票成功，取票订单号为$itm.ticketNo$,$itm.startDate$$itm.origStationName$$itm.startTime$ $itm.trainCn$ $itm.endDate$$itm.destStationName$$itm.endTime$ $itm.trainType$票  $itm.name$ $itm.cxin$');
INSERT INTO `t_train_message_template` VALUES ('2', 'MAIL', '11', null, '<?xml version=\"1.0\" encoding=\"GB2312\"?>\r\n<?xml-stylesheet type=\"text/xsl\" href=\"trainTicketConfirm.xsl\"?> \r\n<trainTicketConfirm>\r\n	<url>$itm.url$</url>\r\n	<orderCn>$itm.orderCn$</orderCn><!-- 订单 -->\r\n	<ticketList>\r\n	</ticketList>\r\n        <allPrice>$itm.allPrice$</allPrice>\r\n</trainTicketConfirm>');
INSERT INTO `t_train_message_template` VALUES ('3', 'MAIL', '12', null, '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<?xml-stylesheet type=\"text/xsl\" href=\"trainTicketOrder.xsl\"?> \r\n<trainTicketOrder>\r\n	<company>$itm.company$</company><!-- 公司 -->\r\n	<branch>$itm.branch$</branch><!-- 公司分部 -->\r\n	<operater>$itm.operater$</operater><!-- 操作者 -->\r\n	<telephone>$itm.telephone$</telephone><!-- 电话 -->\r\n	<reasonCode>$itm.reasonCode$</reasonCode><!-- 原因代码 -->\r\n	<costCenter>$itm.costCenter$</costCenter><!-- 成本中心 -->\r\n	<orderCn>$itm.orderCn$</orderCn><!-- 订单 -->\r\n	<ticketList>\r\n	</ticketList>\r\n	<allPrice>$itm.allPrice$</allPrice>\r\n</trainTicketOrder>');
INSERT INTO `t_train_message_template` VALUES ('4', 'SMS', '13', null, '我们已经帮你购票成功，您的订单 $itm.startDate$ $itm.origStationName$$itm.startTime$ $itm.trainCn$ $itm.endDate$ $itm.destStationName$ $itm.endTime$, 乘客 $ticketlist:{arg1|$arg1$,}$,已出票成功! 如有疑问请致电400662008');
INSERT INTO `t_train_message_template` VALUES ('5', 'APPROVAL_SMS', '14', null, '尊敬的审批确认人，$bookMember$预订的 $ticketlist:{arg1|$arg1$}$,$itm.startDate$ $itm.startTime$ $itm.origStationName$-$itm.destStationName$ $seatType$ $tmPrice$服务费$fee$元/人的差旅行程(审批单$approvalId$),订单$orderCn$应付金额$totalPrice$元。如有问题请致4006620088，请在20分钟内完成审批');


-- ----------------------------
-- Table structure for t_train_order
-- ----------------------------
DROP TABLE IF EXISTS `t_train_order`;
CREATE TABLE `t_train_order` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_cn` varchar(20) DEFAULT NULL COMMENT '订单号',
  `status` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `member_cn` varchar(64) DEFAULT NULL COMMENT '会员号',
  `order_from` varchar(20) DEFAULT NULL COMMENT '订单来源',
  `company_code` varchar(64) DEFAULT NULL COMMENT '公司代码',
  `book_member` varchar(128) DEFAULT NULL COMMENT '预订会员',
  `cost_center` varchar(128) DEFAULT NULL COMMENT '成本中心',
  `travel_type` varchar(16) DEFAULT NULL COMMENT '出行性质',
  `project_code` varchar(64) DEFAULT NULL COMMENT '项目代码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL,
  `payment_status` varchar(10) DEFAULT NULL COMMENT '支付状态',
  `order_audit` varchar(10) DEFAULT NULL COMMENT '审批方式',
  `order_channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `out_method` varchar(10) DEFAULT NULL COMMENT '出票方式',
  `order_method` varchar(20) DEFAULT NULL COMMENT '预订方式',
  `front_remark` varchar(100) DEFAULT NULL COMMENT '前台备注',
  `mid_remark` varchar(100) DEFAULT NULL COMMENT '中台备注',
  `route_type` varchar(10) DEFAULT NULL COMMENT '行程类型 1-单程  2-往返',
  `settlement` varchar(32) DEFAULT NULL COMMENT '结算部门',
  `department` varchar(32) DEFAULT NULL COMMENT '部门',
  `is_need_audit` smallint(1) DEFAULT NULL COMMENT '是否需要审批',
  `payment_method` varchar(10) DEFAULT NULL COMMENT '支付方式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=487 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for t_train_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_train_order_item`;
CREATE TABLE `t_train_order_item` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '订购项ID',
  `order_id` bigint(10) DEFAULT NULL COMMENT '订单ID',
  `goods_type` varchar(10) DEFAULT NULL COMMENT '商品类型',
  `price` decimal(10,2) DEFAULT NULL COMMENT '订购项价格',
  `order_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '订购日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=556 DEFAULT CHARSET=utf8 COMMENT='订购项';

-- ----------------------------
-- Table structure for t_train_passenger
-- ----------------------------
DROP TABLE IF EXISTS `t_train_passenger`;
CREATE TABLE `t_train_passenger` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '乘客ID',
  `name` varchar(100) DEFAULT NULL COMMENT '乘客姓名',
  `cert_type` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `cert_cn` varchar(64) DEFAULT NULL COMMENT '证件号',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `mobile` varchar(20) DEFAULT NULL COMMENT '乘客手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COMMENT='乘客';

-- ----------------------------
-- Table structure for t_train_pay
-- ----------------------------
DROP TABLE IF EXISTS `t_train_pay`;
CREATE TABLE `t_train_pay` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `pay_info_id` bigint(10) NOT NULL COMMENT '支付会话ID',
  `order_cd` varchar(20) NOT NULL COMMENT '订单号',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `status` varchar(10) NOT NULL COMMENT '支付状态：0=待支付，1=在线支付中，2=支付失败，3=部分支付，4=已支付',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train_pay_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_train_pay_detail`;
CREATE TABLE `t_train_pay_detail` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `pay_id` bigint(10) NOT NULL COMMENT '支付ID',
  `pay_mode` varchar(50) NOT NULL COMMENT '支付方式',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `status` varchar(10) DEFAULT NULL COMMENT '支付状态：0=待支付，1=在线支付中，2=支付失败，3=部分支付，4=已支付',
  `pay_numer` varchar(100) DEFAULT NULL COMMENT '支付流水号',
  `voucher_cd` varchar(50) DEFAULT NULL COMMENT '代金券编号',
  `integral_num` int(10) DEFAULT NULL COMMENT '积分兑换数',
  `hb_order_id` varchar(32) DEFAULT NULL COMMENT '产品Id',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8 COMMENT='支付明细';

-- ----------------------------
-- Table structure for t_train_pay_info
-- ----------------------------
DROP TABLE IF EXISTS `t_train_pay_info`;
CREATE TABLE `t_train_pay_info` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(10) NOT NULL COMMENT '订单Id',
  `order_cd` varchar(20) NOT NULL COMMENT '订单编号',
  `status` varchar(10) NOT NULL COMMENT '支付状态：0=待支付，1=在线支付中，2=支付失败，3=部分支付，4=已支付',
  `type` varchar(10) DEFAULT NULL COMMENT '支付类型：0付款，1退款',
  `start_time` datetime DEFAULT NULL COMMENT '支付创建时间',
  `end_time` datetime DEFAULT NULL COMMENT '支付结束时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COMMENT='支付会话信息';

-- ----------------------------
-- Table structure for t_train_pay_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_train_pay_order_item`;
CREATE TABLE `t_train_pay_order_item` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `pay_info_id` bigint(10) NOT NULL COMMENT '支付会话信息',
  `goods_id` bigint(10) NOT NULL COMMENT '产品ID',
  `goods_type` varchar(10) DEFAULT NULL COMMENT '产品类型',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8 COMMENT='支付产品订单项ID表';

-- ----------------------------
-- Table structure for t_train_purchase
-- ----------------------------
DROP TABLE IF EXISTS `t_train_purchase`;
CREATE TABLE `t_train_purchase` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `order_cd` varchar(20) NOT NULL COMMENT '订单号',
  `amount` decimal(10,2) NOT NULL COMMENT '采购总金额',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL COMMENT '采购结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=331 DEFAULT CHARSET=utf8 COMMENT='采购';

-- ----------------------------
-- Table structure for t_train_purchase_info
-- ----------------------------
DROP TABLE IF EXISTS `t_train_purchase_info`;
CREATE TABLE `t_train_purchase_info` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `purchase_item_id` bigint(10) NOT NULL COMMENT '采购项ID',
  `type` varchar(20) DEFAULT NULL COMMENT '采购类别',
  `price_type` varchar(10) DEFAULT NULL COMMENT '价格类型---0销售价格，1服务费，2配送费，3保险费',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '采购金额',
  `acount` int(10) DEFAULT NULL COMMENT '采购数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1070 DEFAULT CHARSET=utf8 COMMENT='采购价格明细';

-- ----------------------------
-- Table structure for t_train_purchase_item
-- ----------------------------
DROP TABLE IF EXISTS `t_train_purchase_item`;
CREATE TABLE `t_train_purchase_item` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `purchase_id` bigint(10) NOT NULL COMMENT '采购ID',
  `goods_id` bigint(10) NOT NULL COMMENT '产品ID',
  `goods_type` varchar(10) DEFAULT NULL COMMENT '产品类型',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '采购金额',
  `supplier` varchar(100) DEFAULT NULL COMMENT '采购供应商',
  `hb_order_id` varchar(32) DEFAULT NULL COMMENT '号百订单号',
  `train_no` varchar(32) DEFAULT NULL COMMENT '票号',
  `train_seat` varchar(32) DEFAULT NULL COMMENT '车厢座位',
  `order_no` varchar(32) DEFAULT NULL COMMENT '12306单号',
  `create_time` datetime DEFAULT NULL,
  `hb_price` decimal(10,0) DEFAULT NULL COMMENT '号百返回票价金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=538 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_train_ticket`;
CREATE TABLE `t_train_ticket` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '火车票ID',
  `order_item_id` bigint(10) DEFAULT NULL COMMENT '订购项ID',
  `passenger_id` bigint(10) DEFAULT NULL COMMENT '乘客ID',
  `route_type` varchar(10) DEFAULT NULL COMMENT '行程类型',
  `train_cn` varchar(32) DEFAULT NULL COMMENT '车次',
  `orig_station` varchar(100) DEFAULT NULL COMMENT '出发站',
  `dest_station` varchar(100) DEFAULT NULL COMMENT '到达站',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '乘车日期',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '到达时间',
  `train_type` varchar(10) DEFAULT NULL COMMENT '票种',
  `seat_type` varchar(10) DEFAULT NULL COMMENT '席别类型',
  `ticket_parent_id` bigint(10) DEFAULT NULL COMMENT '父行程ID',
  `sale_price` decimal(10,2) DEFAULT NULL COMMENT '销售价',
  `fee` decimal(10,2) DEFAULT NULL COMMENT '服务费',
  `status` varchar(10) DEFAULT NULL COMMENT '状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `hb_order_id` varchar(32) DEFAULT NULL COMMENT '号百返回orderId',
  `orig_station_name` varchar(16) DEFAULT NULL COMMENT '出发站名称',
  `dest_station_name` varchar(16) DEFAULT NULL COMMENT '到站名称',
  `ticket_no` varchar(32) DEFAULT NULL COMMENT '票号',
  `cxin` varchar(32) DEFAULT NULL COMMENT '车厢座位',
  `type` varchar(10) DEFAULT NULL COMMENT '类型 0-订票 1-改签 2-退票',
  `ddsj` varchar(16) DEFAULT NULL COMMENT '到达时间',
  `ccsj` varchar(16) DEFAULT NULL COMMENT '乘车时间',
  `gap_price` decimal(10,0) DEFAULT NULL COMMENT '差价：小于0表示退款，大于0表示扣款',
  `book_account` varchar(32) DEFAULT NULL COMMENT '预订时的12306账号',
  `request_no` varchar(32) DEFAULT NULL COMMENT '请求流水号',
  `applicant_id` bigint(20) DEFAULT NULL COMMENT '申请人ID',
  `refund_reason` varchar(100) DEFAULT NULL COMMENT '退票原因',
  `refund_desc` varchar(200) DEFAULT NULL COMMENT '退改备注',
  `verify_desc` varchar(200) DEFAULT NULL COMMENT '审核备注',
  `transaction_id` varchar(20) DEFAULT NULL COMMENT '号百交易编号',
  `order_number` varchar(20) DEFAULT NULL COMMENT '12306票号',
  `hb_price` decimal(10,2) DEFAULT NULL COMMENT '号百返回票价',
  `tmc_price` decimal(10,2) DEFAULT NULL COMMENT '商旅票价',
  `hb_seat_type` varchar(10) DEFAULT NULL COMMENT '号百返回座位类型',
  `operator` varchar(64) DEFAULT NULL COMMENT '操作者',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=660 DEFAULT CHARSET=utf8 COMMENT='火车票';
