/*
Navicat MySQL Data Transfer

Source Server         : donm
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-12-27 16:22:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for allborrow
-- ----------------------------
DROP TABLE IF EXISTS `allborrow`;
CREATE TABLE `allborrow` (
  `username` varchar(255) NOT NULL,
  `id` int(11) NOT NULL,
  `borrowtime` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `mark` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`mark`),
  KEY `f_id_allbw` (`id`),
  CONSTRAINT `f_id_allbw` FOREIGN KEY (`id`) REFERENCES `warehouse` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allborrow
-- ----------------------------
INSERT INTO `allborrow` VALUES ('222', '2', '2018-10-21 22:15:48', '1');
INSERT INTO `allborrow` VALUES ('333', '1', '2018-12-21 22:10:27', '2');
INSERT INTO `allborrow` VALUES ('222', '2', '2018-12-21 22:12:19', '3');
INSERT INTO `allborrow` VALUES ('222', '2', '2018-12-21 22:13:24', '4');
INSERT INTO `allborrow` VALUES ('222', '3', '2018-12-21 22:18:05', '5');
INSERT INTO `allborrow` VALUES ('222', '4', '2018-12-21 22:18:08', '6');
INSERT INTO `allborrow` VALUES ('222', '5', '2018-12-21 22:18:11', '7');
INSERT INTO `allborrow` VALUES ('222', '6', '2018-12-21 22:18:15', '8');
INSERT INTO `allborrow` VALUES ('222', '7', '2018-12-21 22:18:18', '9');
INSERT INTO `allborrow` VALUES ('222', '8', '2018-12-21 22:18:21', '10');
INSERT INTO `allborrow` VALUES ('222', '9', '2018-12-21 22:18:25', '11');
INSERT INTO `allborrow` VALUES ('222', '10', '2018-12-21 22:18:29', '12');
INSERT INTO `allborrow` VALUES ('222', '11', '2018-12-21 22:18:32', '13');
INSERT INTO `allborrow` VALUES ('222', '12', '2018-12-21 22:18:36', '14');
INSERT INTO `allborrow` VALUES ('222', '13', '2018-12-21 22:18:40', '15');
INSERT INTO `allborrow` VALUES ('222', '14', '2018-12-21 22:18:44', '16');
INSERT INTO `allborrow` VALUES ('222', '15', '2018-12-21 22:19:48', '17');
INSERT INTO `allborrow` VALUES ('222', '11', '2018-12-21 22:21:17', '18');
INSERT INTO `allborrow` VALUES ('111', '12', '2018-12-22 11:10:46', '19');
INSERT INTO `allborrow` VALUES ('333', '13', '2018-12-22 11:13:26', '20');
INSERT INTO `allborrow` VALUES ('222', '12', '2018-12-25 01:14:23', '21');
INSERT INTO `allborrow` VALUES ('999', '1', '2018-12-25 09:10:00', '22');
INSERT INTO `allborrow` VALUES ('999', '2', '2018-12-25 09:10:20', '23');
INSERT INTO `allborrow` VALUES ('000', '13', '2018-12-26 09:39:31', '24');
INSERT INTO `allborrow` VALUES ('000', '13', '2018-12-26 10:00:22', '25');
INSERT INTO `allborrow` VALUES ('000', '14', '2018-12-27 09:40:04', '26');
INSERT INTO `allborrow` VALUES ('222', '25', '2018-12-27 16:08:43', '29');
INSERT INTO `allborrow` VALUES ('222', '25', '2018-12-27 16:10:00', '30');
INSERT INTO `allborrow` VALUES ('222', '24', '2018-12-27 16:10:12', '31');

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `appointtime` varchar(255) NOT NULL,
  `mark` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`mark`),
  KEY `f_id_appoint` (`id`),
  CONSTRAINT `f_id_appoint` FOREIGN KEY (`id`) REFERENCES `warehouse` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('2', '222', '2018-12-25 08:13:54', '1');
INSERT INTO `appointment` VALUES ('13', '000', '2018-12-26 09:51:25', '2');
INSERT INTO `appointment` VALUES ('12', '000', '2018-12-27 10:01:00', '4');
INSERT INTO `appointment` VALUES ('1', '222', '2018-12-27 15:38:01', '5');

-- ----------------------------
-- Table structure for borrowing
-- ----------------------------
DROP TABLE IF EXISTS `borrowing`;
CREATE TABLE `borrowing` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `borrowtime` varchar(255) NOT NULL,
  `cnt` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowing
-- ----------------------------
INSERT INTO `borrowing` VALUES ('1', '999', '2018-12-25 09:10:00', '60');
INSERT INTO `borrowing` VALUES ('2', '999', '2018-12-25 09:10:20', '60');
INSERT INTO `borrowing` VALUES ('5', '222', '2018-12-21 22:18:11', '30');
INSERT INTO `borrowing` VALUES ('6', '222', '2018-12-21 22:18:15', '30');
INSERT INTO `borrowing` VALUES ('7', '222', '2018-12-21 22:18:18', '30');
INSERT INTO `borrowing` VALUES ('8', '222', '2018-12-21 22:18:21', '30');
INSERT INTO `borrowing` VALUES ('9', '222', '2018-12-21 22:18:25', '30');
INSERT INTO `borrowing` VALUES ('11', '222', '2018-12-21 22:21:17', '30');
INSERT INTO `borrowing` VALUES ('12', '222', '2018-12-26 01:14:23', '30');
INSERT INTO `borrowing` VALUES ('13', '000', '2018-12-25 10:00:22', '30');
INSERT INTO `borrowing` VALUES ('24', '222', '2018-12-27 16:10:12', '30');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `collecttime` varchar(255) NOT NULL,
  `mark` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`mark`),
  KEY `f_id_collect` (`id`),
  CONSTRAINT `f_id_collect` FOREIGN KEY (`id`) REFERENCES `warehouse` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('13', '000', '2018-12-26 09:54:58', '2');
INSERT INTO `collection` VALUES ('12', '000', '2018-12-27 10:01:20', '4');
INSERT INTO `collection` VALUES ('1', '222', '2018-12-27 15:37:55', '9');
INSERT INTO `collection` VALUES ('25', '222', '2018-12-27 16:11:26', '11');

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader` (
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `head` varchar(255) NOT NULL,
  `isManager` tinyint(4) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('000', '*B66FEB4CF5216D971C066EB920EC03B27A5A3F40', 'file:image/10.png', '1');
INSERT INTO `reader` VALUES ('111', '*832EB84CB764129D05D498ED9CA7E5CE9B8F83EB', 'file:image/1.png', '1');
INSERT INTO `reader` VALUES ('222', '*899ECD04E40F745BD52A4C552BE4A818AC65FAF8', 'file:image/2.png', '1');
INSERT INTO `reader` VALUES ('333', '*44019FB6C583EFACD2FB2F1A1960B97F86E36A74', 'file:image/3.png', '1');
INSERT INTO `reader` VALUES ('444', '*D5E7DD112F5BD0AA69328C729634E5855AAD54E0', 'file:image/4.png', '1');
INSERT INTO `reader` VALUES ('555', '*1D096071E42D900FF3B77C6AB23CBBBD6E4F3824', 'file:image/5.png', '1');
INSERT INTO `reader` VALUES ('666', '*007D50CA06F69776D307B1BEC71CD73D0EA0999C', 'file:image/6.png', '1');
INSERT INTO `reader` VALUES ('777', '*E8D868B7DA46FC9F996DC761C1AE01754A4447D5', 'file:image/7.png', '1');
INSERT INTO `reader` VALUES ('888', '*41DDB5DFD213B288EE050BD64DC6AA36815A3486', 'file:image/8.png', '1');
INSERT INTO `reader` VALUES ('999', '*627B3E4116939F447D767EECFB048F52DFBF73A7', 'file:image/9.png', '1');

-- ----------------------------
-- Table structure for returned
-- ----------------------------
DROP TABLE IF EXISTS `returned`;
CREATE TABLE `returned` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `returntime` varchar(255) NOT NULL,
  `mark` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`mark`),
  KEY `f_id_return` (`id`),
  CONSTRAINT `f_id_return` FOREIGN KEY (`id`) REFERENCES `warehouse` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of returned
-- ----------------------------
INSERT INTO `returned` VALUES ('2', '222', '2018-12-21 22:12:27', '1');
INSERT INTO `returned` VALUES ('2', '222', '2018-12-21 22:13:48', '2');
INSERT INTO `returned` VALUES ('15', '222', '2018-12-21 22:20:18', '3');
INSERT INTO `returned` VALUES ('14', '222', '2018-12-21 22:20:22', '4');
INSERT INTO `returned` VALUES ('13', '222', '2018-12-21 22:20:51', '5');
INSERT INTO `returned` VALUES ('12', '222', '2018-12-21 22:20:59', '6');
INSERT INTO `returned` VALUES ('11', '222', '2018-12-21 22:21:08', '7');
INSERT INTO `returned` VALUES ('12', '111', '2018-12-22 11:20:39', '8');
INSERT INTO `returned` VALUES ('13', '333', '2018-12-22 11:23:06', '9');
INSERT INTO `returned` VALUES ('1', '333', '2018-12-22 11:51:17', '10');
INSERT INTO `returned` VALUES ('2', '222', '2018-12-24 10:06:12', '11');
INSERT INTO `returned` VALUES ('13', '000', '2018-12-26 09:40:33', '12');
INSERT INTO `returned` VALUES ('3', '222', '2018-12-26 15:32:41', '13');
INSERT INTO `returned` VALUES ('14', '000', '2018-12-27 09:41:36', '15');
INSERT INTO `returned` VALUES ('25', '222', '2018-12-27 16:08:57', '16');
INSERT INTO `returned` VALUES ('4', '222', '2018-12-27 16:10:51', '17');
INSERT INTO `returned` VALUES ('25', '222', '2018-12-27 16:10:59', '18');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `myname` varchar(255) NOT NULL,
  `mypassword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('111', '222');
INSERT INTO `test` VALUES ('111', '*832EB84CB764129D05D498ED9CA7E5CE9B8F83EB');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` int(11) NOT NULL,
  `bookname` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `booktime` varchar(255) NOT NULL,
  `bookplace` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `isdeleted` tinyint(4) NOT NULL,
  `image` varchar(255) NOT NULL,
  `addtime` varchar(255) NOT NULL,
  `lastchangetime` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('1', '码农翻身', '刘欢', '2018-10-10', '清华大学出版社', '计算机', '好书', '1', 'file:bookimages/1.jpg', '2018-11-21 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('2', 'docker', '小花', '2018-11-11', '人民邮电出版社', '计算机', 'docker入门实战', '1', 'file:bookimages/0.jpg', '2018-11-11 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('3', '斗罗大陆', '唐家三少', '2016-12-12', '电子工业出版社', '小说', '超级好看', '0', 'file:bookimages/20.jpg', '2018-12-01 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('4', '大主宰', '天蚕土豆', '2013-12-13', '人民邮电出版社', '小说', '精彩', '0', 'file:bookimages/23.jpg', '2018-12-02 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('5', '意林', '群星', '2011-12-15', '清华大学出版社', '杂志', '利于学习', '1', 'file:bookimages/33.jpg', '2018-12-03 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('6', '读者', '群星', '2009-08-27', '人民邮电出版社', '杂志', '阅读', '1', 'file:bookimages/34.jpg', '2018-12-04 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('7', '龙族', '江南', '2015-10-10', '清华大学出版社', '小说', '玄幻', '1', 'file:bookimages/22.jpg', '2018-12-05 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('8', '特别关注', '佚名', '2017-11-24', '电子工业出版社', '杂志', '关注', '1', 'file:bookimages/35.jpg', '2018-12-06 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('9', '汇编语言', 'mary', '2016-10-10', '清华大学出版社', '计算机', '汇编', '1', 'file:bookimages/25.jpg', '2018-12-07 22:03:25', '2018-12-21 22:07:57');
INSERT INTO `warehouse` VALUES ('10', 'effective java', 'json', '2015-11-26', '人民邮电出版社', '计算机', 'java方面', '0', 'file:bookimages/8.jpg', '2018-12-08 22:03:25', '2018-12-21 22:06:44');
INSERT INTO `warehouse` VALUES ('11', 'C Primer Plus', '小露', '2017-11-12', '人民邮电出版社', '计算机', 'C语言从入门到精通', '1', 'file:bookimages/10.jpg', '2018-12-09 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('12', 'java编程思想', '小夏', '2016-05-12', '人民邮电出版社', '计算机', 'java书籍', '1', 'file:bookimages/6.jpg', '2018-12-10 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('13', '深入理解计算机系统', '小辉', '2014-01-10', '电子工业出版社', '计算机', '重点推荐', '1', 'file:bookimages/24.jpg', '2018-12-11 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('14', '数据结构', '小泉', '2015-11-25', '人民邮电出版社', '计算机', 'c++描述', '1', 'file:bookimages/26.jpg', '2018-12-12 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('15', '数据库系统概念', '小康', '2016-11-25', '人民邮电出版社', '计算机', '数据库方面的经典之作', '1', 'file:bookimages/28.jpg', '2018-12-13 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('16', '计算机网络', '小黄', '2011-11-29', '机械工业出版社', '计算机', '计算机网络方面的经典书籍', '0', 'file:bookimages/29.jpg', '2018-12-14 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('17', '飞剑问道', '小肖', '2013-11-19', '中华书局', '小说', '精彩+刺激', '0', 'file:bookimages/21.jpg', '2018-12-15 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('18', '战国策', '小苏', '2015-11-11', '中华书局', '历史', '讲述战国', '0', 'file:bookimages/32.jpg', '2018-12-16 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('19', '明朝的那些事儿', '小芳', '2011-10-21', '中华书局', '历史', '讲述明朝', '0', 'file:bookimages/30.jpg', '2018-12-17 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('20', '算法图解', '小柳', '2016-10-14', '清华大学出版社', '计算机', '图解算法', '0', 'file:bookimages/4.jpg', '2018-12-18 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('21', '注册会计师', '小红', '2018-10-17', '清华大学出版社', '其他', 'CPA', '0', 'file:bookimages/36.jpg', '2018-12-19 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('22', '你不可不知的法律常识', '小可', '2016-11-17', '人民邮电出版社', '其他', '法律', '0', 'file:bookimages/37.jpg', '2018-12-20 22:03:25', '2018-12-21 22:08:08');
INSERT INTO `warehouse` VALUES ('23', '三体', '小娜', '2016-11-11', '中华书局', '小说', '挺精彩的', '0', 'file:bookimages/13.jpg', '2018-12-25 09:02:07', '2018-12-25 09:02:07');
INSERT INTO `warehouse` VALUES ('24', '汇编语言', '小晓', '2018-03-11', '清华大学出版社', '计算机', '汇编经典图书', '1', 'file:bookimages/25.jpg', '2018-12-26 15:20:57', '2018-12-26 15:20:57');
INSERT INTO `warehouse` VALUES ('25', '意林', '佚名', '2017-11-12', '中华书局', '杂志', '意林杂志', '0', 'file:bookimages/33.jpg', '2018-12-27 16:05:24', '2018-12-27 16:05:24');

-- ----------------------------
-- View structure for view_allborrow
-- ----------------------------
DROP VIEW IF EXISTS `view_allborrow`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_allborrow` AS select `warehouse`.`id` AS `id`,`warehouse`.`bookname` AS `bookname`,`allborrow`.`username` AS `username`,`allborrow`.`borrowtime` AS `borrowtime` from (`warehouse` join `allborrow` on((`allborrow`.`id` = `warehouse`.`id`))) ;

-- ----------------------------
-- View structure for view_appoint
-- ----------------------------
DROP VIEW IF EXISTS `view_appoint`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_appoint` AS select `warehouse`.`id` AS `id`,`warehouse`.`bookname` AS `bookname`,`appointment`.`username` AS `username`,`appointment`.`appointtime` AS `appointtime` from (`warehouse` join `appointment` on((`appointment`.`id` = `warehouse`.`id`))) ;

-- ----------------------------
-- View structure for view_booktype
-- ----------------------------
DROP VIEW IF EXISTS `view_booktype`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_booktype` AS select `warehouse`.`type` AS `type`,`warehouse`.`id` AS `id` from `warehouse` ;

-- ----------------------------
-- View structure for view_collect
-- ----------------------------
DROP VIEW IF EXISTS `view_collect`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_collect` AS select `warehouse`.`id` AS `id`,`warehouse`.`bookname` AS `bookname`,`collection`.`username` AS `username`,`collection`.`collecttime` AS `collecttime` from (`warehouse` join `collection` on((`collection`.`id` = `warehouse`.`id`))) ;

-- ----------------------------
-- View structure for view_newbook
-- ----------------------------
DROP VIEW IF EXISTS `view_newbook`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_newbook` AS select `warehouse`.`id` AS `id`,`warehouse`.`bookname` AS `bookname`,`warehouse`.`image` AS `image`,`warehouse`.`addtime` AS `addtime` from `warehouse` ;

-- ----------------------------
-- View structure for view_rank
-- ----------------------------
DROP VIEW IF EXISTS `view_rank`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_rank` AS select `warehouse`.`id` AS `id`,`warehouse`.`image` AS `image`,`warehouse`.`bookname` AS `bookname`,`allborrow`.`mark` AS `mark` from (`warehouse` join `allborrow` on((`allborrow`.`id` = `warehouse`.`id`))) ;

-- ----------------------------
-- View structure for view_return
-- ----------------------------
DROP VIEW IF EXISTS `view_return`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_return` AS select `warehouse`.`id` AS `id`,`warehouse`.`bookname` AS `bookname`,`returned`.`username` AS `username`,`returned`.`returntime` AS `returntime` from (`warehouse` join `returned` on((`returned`.`id` = `warehouse`.`id`))) ;

-- ----------------------------
-- View structure for view_searchbook
-- ----------------------------
DROP VIEW IF EXISTS `view_searchbook`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_searchbook` AS select `warehouse`.`id` AS `id`,`warehouse`.`bookname` AS `bookname`,`warehouse`.`author` AS `author`,`warehouse`.`booktime` AS `booktime`,`warehouse`.`bookplace` AS `bookplace`,`warehouse`.`type` AS `type`,`warehouse`.`isdeleted` AS `isdeleted` from `warehouse` ;

-- ----------------------------
-- Procedure structure for printnewbook
-- ----------------------------
DROP PROCEDURE IF EXISTS `printnewbook`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `printnewbook`()
BEGIN
	SELECT * FROM view_newbook WHERE YEAR(NOW())=YEAR(STR_TO_DATE(addtime,"%Y-%m-%d"))ORDER BY addtime DESC;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for printrank
-- ----------------------------
DROP PROCEDURE IF EXISTS `printrank`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `printrank`()
BEGIN
	SELECT id,image,bookname,COUNT(id) AS t FROM view_rank GROUP BY id ORDER BY t DESC,id ASC;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for scantype
-- ----------------------------
DROP PROCEDURE IF EXISTS `scantype`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `scantype`()
BEGIN
	SELECT COUNT(id),type FROM view_booktype GROUP BY type;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for searchbook
-- ----------------------------
DROP PROCEDURE IF EXISTS `searchbook`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchbook`(a varchar(50),b int(2))
BEGIN
   IF b=0 THEN SELECT * from view_searchbook WHERE bookname LIKE a;
   ELSEIF b=1 THEN SELECT * from view_searchbook WHERE id=CAST(a AS UNSIGNED INTEGER);
   ELSEIF b=2 THEN SELECT * from view_searchbook WHERE author=a;
   ELSEIF b=3 THEN SELECT * from view_searchbook WHERE type=a;
   ELSEIF b=4 THEN SELECT * from view_searchbook WHERE bookplace=a;
   ELSE SELECT * from view_searchbook WHERE booktime LIKE a;
   END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for something
-- ----------------------------
DROP PROCEDURE IF EXISTS `something`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `something`(a varchar(50),b int(2))
BEGIN
	IF b=6 THEN SELECT id,bookname,appointtime FROM view_appoint WHERE username=a ORDER BY appointtime,id;
  ELSEIF b=7 THEN SELECT id,bookname,collecttime FROM view_collect WHERE username=a ORDER BY collecttime,id;
  ELSEIF b=8 THEN SELECT id,bookname,returntime FROM view_return WHERE username=a ORDER BY returntime,id;
  ELSE SELECT id,bookname,borrowtime FROM view_allborrow WHERE username=a ORDER BY borrowtime,id;
  END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for changepassword
-- ----------------------------
DROP FUNCTION IF EXISTS `changepassword`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `changepassword`(a varchar(50),b varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
	DECLARE temp INT(1) DEFAULT 0;
  SELECT COUNT(*) INTO temp FROM reader WHERE name=a;
  IF temp<1 THEN RETURN "账号不存在";
  END IF;
  UPDATE reader SET `password`=PASSWORD(b) WHERE `name`=a;
  RETURN "密码修改成功";
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for checkborrow
-- ----------------------------
DROP FUNCTION IF EXISTS `checkborrow`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `checkborrow`(a int(11),b varchar(50),c varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
  IF (SELECT COUNT(id) FROM borrowing WHERE (TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,"%Y-%m-%d")))>cnt AND username=b)>0 THEN RETURN
  "图书存在逾期,请归还后再操作";
  ELSEIF (SELECT COUNT(id) FROM borrowing WHERE username=b)>9 THEN RETURN "图书已达最大可借数量";
	ELSEIF (SELECT COUNT(id) FROM warehouse WHERE id=a)=0 THEN RETURN "图书不存在";
  ELSEIF (SELECT COUNT(id) FROM warehouse WHERE id=a AND isdeleted=0)=0 THEN RETURN "图书处于借阅中";
  END IF;
  INSERT INTO borrowing VALUES(a,b,c,30);
  RETURN "借书成功";
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for checkcollect
-- ----------------------------
DROP FUNCTION IF EXISTS `checkcollect`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `checkcollect`(a int(11),b varchar(50),c int(1),d varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
	DECLARE ans INT(1) DEFAULT 0;
  IF (SELECT COUNT(id) FROM borrowing WHERE (TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,"%Y-%m-%d")))>cnt AND username=b)>0 THEN RETURN
  "图书存在逾期,请归还后再操作";
  ELSEIF (SELECT COUNT(id) FROM warehouse WHERE id=a)=0 THEN RETURN "图书不存在";
  END IF;
  SELECT COUNT(mark) INTO ans FROM collection WHERE id=a AND username=b;
  IF c=1 AND ans>0 THEN RETURN "图书正在收藏中";
  ELSEIF c=1 THEN INSERT INTO collection(id,username,collecttime) VALUES(a,b,d);
  ELSEIF ans=0 THEN RETURN "图书未被收藏";
  ELSE DELETE FROM collection WHERE id=a AND username=b;
  END IF;
  IF c=1 THEN RETURN "图书收藏成功";
  ELSE RETURN "图书取消收藏成功";
  END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for checkcontinue
-- ----------------------------
DROP FUNCTION IF EXISTS `checkcontinue`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `checkcontinue`(a int(11),b varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
  DECLARE ans INT(10) DEFAULT 0;
  IF (SELECT COUNT(id) FROM borrowing WHERE (TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,"%Y-%m-%d")))>cnt AND username=b)>0 THEN RETURN
  "图书存在逾期,请归还后再操作";
  ELSEIF (SELECT COUNT(id) FROM warehouse WHERE id=a)=0 THEN RETURN "图书不存在";
  END IF;
	SELECT cnt INTO ans FROM borrowing WHERE id=a AND username=b;
  IF ans=0 THEN RETURN "图书未被借";
  ELSEIF ans=60 THEN RETURN "图书已达最大续借次数";
  END IF;
  UPDATE borrowing SET cnt=ans+30 WHERE id=a AND username=b;
  RETURN "续借成功";
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for checkmanager
-- ----------------------------
DROP FUNCTION IF EXISTS `checkmanager`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `checkmanager`(a varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
  IF (SELECT COUNT(`name`) FROM reader WHERE `name`=a)=0 THEN RETURN "该用户不存在";
  ELSEIF (SELECT COUNT(`name`) FROM reader WHERE `name`=a AND isManager=1)>0 THEN RETURN "该用户已是管理员";
  END IF;
  UPDATE reader SET isManager=1 WHERE `name`=a;
  RETURN "已成功设置为管理员";
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for checkreader
-- ----------------------------
DROP FUNCTION IF EXISTS `checkreader`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `checkreader`(a varchar(50),b varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
	DECLARE temp INT(1) DEFAULT 0;
  DECLARE temp2 INT(1) DEFAULT 0;
  DECLARE temp3 INT(1) DEFAULT 0;
  SELECT COUNT(`name`) INTO temp FROM reader WHERE `name`=a;
  SELECT COUNT(`name`) INTO temp2 FROM reader WHERE `name`=a AND `password`=PASSWORD(b);
  SELECT ismanager INTO temp3 FROM reader WHERE `name`=a AND `password`=PASSWORD(b);
  IF temp<1 THEN RETURN "账号不存在";
  ELSEIF temp2<1 THEN RETURN "密码错误";
  ELSEIF temp3>0 THEN RETURN CONCAT("管理员",(SELECT head FROM reader WHERE `name`=a));
  END IF;
  RETURN (SELECT head FROM reader WHERE `name`=a);
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for checkreserve
-- ----------------------------
DROP FUNCTION IF EXISTS `checkreserve`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `checkreserve`(a int(11),b varchar(50),c int(1),d varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
	DECLARE ans INT(1) DEFAULT 0;
  IF (SELECT COUNT(id) FROM borrowing WHERE (TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,"%Y-%m-%d")))>cnt AND username=b)>0 THEN RETURN
  "图书存在逾期,请归还后再操作";
  END IF;
  IF (SELECT COUNT(id) FROM warehouse WHERE id=a)=0 THEN RETURN "图书不存在";
  END IF;
  SELECT COUNT(mark) INTO ans FROM appointment WHERE id=a AND username=b;
  IF c=1 AND ans>0 THEN RETURN "图书正在预约中";
  ELSEIF c=1 THEN INSERT INTO appointment(id,username,appointtime) VALUES(a,b,d);
  ELSEIF ans=0 THEN RETURN "图书未被预约";
  ELSE DELETE FROM appointment WHERE id=a AND username=b;
  END IF;
  IF c=1 THEN RETURN "图书预约成功";
  ELSE RETURN "图书取消预约成功";
  END IF;
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for checkreturn
-- ----------------------------
DROP FUNCTION IF EXISTS `checkreturn`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `checkreturn`(a int(11),b varchar(50),c varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
	IF (SELECT COUNT(id) FROM borrowing WHERE (TO_DAYS(NOW())-TO_DAYS(STR_TO_DATE(borrowtime,"%Y-%m-%d")))>cnt AND username=b)>0 THEN RETURN
  "图书存在逾期,请归还后再操作";
  ELSEIF (SELECT COUNT(id) FROM borrowing WHERE id=a AND username=b)=0 THEN RETURN "图书未被借";
  END IF;
  INSERT INTO returned(id,username,returntime) VALUES(a,b,c);
  RETURN "还书成功";
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for dochange
-- ----------------------------
DROP FUNCTION IF EXISTS `dochange`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `dochange`(a varchar(50),b varchar(50),c varchar(50),d varchar(50),e varchar(50),f varchar(50),g varchar(50),h varchar(50),aa int(10)) RETURNS varchar(50) CHARSET utf8
BEGIN
	IF (SELECT COUNT(id) FROM warehouse WHERE id=aa)=0 THEN RETURN "图书不存在";
  ELSEIF (SELECT COUNT(id) FROM warehouse WHERE id=aa AND isdeleted=0)=0 THEN RETURN "图书正在借阅中";
  END IF;
  UPDATE warehouse SET bookname=a,author=b,booktime=c,bookplace=d,type=e,description=f,image=g,lastchangetime=h WHERE id=aa;
  RETURN "图书修改成功";
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for dodelete
-- ----------------------------
DROP FUNCTION IF EXISTS `dodelete`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `dodelete`(a int(10)) RETURNS varchar(50) CHARSET utf8
BEGIN
	IF (SELECT COUNT(id) FROM warehouse WHERE id=a)=0 THEN RETURN "图书未录入";
  ELSEIF (SELECT COUNT(id) FROM warehouse WHERE id=a AND isdeleted=0)=0 THEN RETURN "图书正在借阅中";
  END IF;
  DELETE FROM warehouse WHERE id=a;
  RETURN "图书删除成功";
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for doput
-- ----------------------------
DROP FUNCTION IF EXISTS `doput`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `doput`(a int(10),b varchar(50),c varchar(50),d varchar(50),e varchar(50),f varchar(50),g varchar(50),h varchar(50),i varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
	IF (SELECT COUNT(id) FROM warehouse WHERE id=a)>0 THEN RETURN "图书已存在";
  END IF;
  INSERT INTO warehouse VALUES(a,b,c,d,e,f,g,0,h,i,i);
  RETURN "图书录入成功";
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for registeruser
-- ----------------------------
DROP FUNCTION IF EXISTS `registeruser`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `registeruser`(a varchar(50),b varchar(50),c varchar(50)) RETURNS varchar(50) CHARSET utf8
BEGIN
	DECLARE temp INT(1) DEFAULT 0;
  SELECT COUNT(`name`) INTO temp FROM reader WHERE name=a;
  IF temp>0 THEN RETURN "账号已存在";
  END IF;
  INSERT INTO reader VALUES(a,PASSWORD(b),c,0);
  RETURN "注册成功";
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `insertallborrow`;
DELIMITER ;;
CREATE TRIGGER `insertallborrow` AFTER INSERT ON `borrowing` FOR EACH ROW begin
     insert into allborrow(username,id,borrowtime) values(new.username,new.id,new.borrowtime);
     UPDATE warehouse SET isdeleted=1 WHERE id=new.id;
end
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `updateoperate`;
DELIMITER ;;
CREATE TRIGGER `updateoperate` AFTER INSERT ON `returned` FOR EACH ROW begin
    DELETE FROM borrowing WHERE id=new.id;
    UPDATE warehouse SET isdeleted=0 WHERE id=new.id;
end
;;
DELIMITER ;
