-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.31-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dhuoj
--

CREATE DATABASE IF NOT EXISTS dhuoj;
USE dhuoj;

--
-- Definition of table `compileinfo`
--

DROP TABLE IF EXISTS `compileinfo`;
CREATE TABLE `compileinfo` (
  `solution_id` int(10) unsigned NOT NULL DEFAULT '0',
  `error` text,
  PRIMARY KEY (`solution_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `compileinfo`
--

/*!40000 ALTER TABLE `compileinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `compileinfo` ENABLE KEYS */;


--
-- Definition of table `contest`
--

DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest` (
  `contest_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `defunct` tinyint(4) NOT NULL DEFAULT '0',
  `description` text,
  `private` tinyint(4) NOT NULL DEFAULT '0',
  `status` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`contest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contest`
--

/*!40000 ALTER TABLE `contest` DISABLE KEYS */;
INSERT INTO `contest` (`contest_id`,`title`,`start_time`,`end_time`,`defunct`,`description`,`private`,`status`) VALUES 
 (1,'Contest1','2009-05-05 11:36:52',NULL,0,'Test',0,0),
 (2,'Test Contest',NULL,NULL,0,'Test',0,0),
 (3,'Test Contest',NULL,NULL,0,'Test',0,0),
 (4,'Test Contest',NULL,NULL,0,'Test',0,0),
 (5,'Test Contest',NULL,NULL,0,'Test',0,0);
/*!40000 ALTER TABLE `contest` ENABLE KEYS */;


--
-- Definition of table `contest_problem`
--

DROP TABLE IF EXISTS `contest_problem`;
CREATE TABLE `contest_problem` (
  `id` int(10) unsigned NOT NULL DEFAULT '0',
  `contest_id` int(10) unsigned NOT NULL DEFAULT '0',
  `problem_id` int(10) unsigned NOT NULL DEFAULT '0',
  `title` varchar(300) NOT NULL,
  `accepted` int(10) unsigned NOT NULL DEFAULT '0',
  `submit` int(10) unsigned NOT NULL DEFAULT '0',
  `sequence` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contest_problem`
--

/*!40000 ALTER TABLE `contest_problem` DISABLE KEYS */;
/*!40000 ALTER TABLE `contest_problem` ENABLE KEYS */;


--
-- Definition of table `problem`
--

DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem` (
  `problem_id` int(11) NOT NULL DEFAULT '0',
  `source` varchar(100) DEFAULT NULL,
  `title` varchar(200) NOT NULL DEFAULT '',
  `problem_path` varchar(255) DEFAULT NULL,
  `input_path` varchar(255) DEFAULT NULL,
  `output_path` varchar(255) DEFAULT NULL,
  `stdcode_path` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `time_limit` int(11) NOT NULL DEFAULT '0',
  `case_time_limit` int(11) NOT NULL DEFAULT '0',
  `memory_limit` int(11) NOT NULL DEFAULT '0',
  `defunct` tinyint(4) NOT NULL DEFAULT '0',
  `accepted` int(11) DEFAULT '0',
  `submit` int(11) DEFAULT '0',
  `difficulty` tinyint(4) NOT NULL DEFAULT '0',
  `special` tinyint(1) NOT NULL DEFAULT '0',
  `category` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`problem_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `problem`
--

/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` (`problem_id`,`source`,`title`,`problem_path`,`input_path`,`output_path`,`stdcode_path`,`create_date`,`time_limit`,`case_time_limit`,`memory_limit`,`defunct`,`accepted`,`submit`,`difficulty`,`special`,`category`) VALUES 
 (1000,'POJ','A+B Problem','/data/p1000.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,2,0,0,0),
 (1001,'EASY PROBLEM','EASY PROBLEM 1','/data/e1001.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,1,0,0,0),
 (1002,'EASY PROBLEM','奇妙的数字','/data/e1002.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1003,'EASY PROBLEM','求长方形的面积','/data/e1003.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1004,'EASY PROBLEM','数列和','/data/e1004.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1005,'EASY PROBLEM','求绝对值','/data/e1005.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1006,'EASY PROBLEM','求最大公约数','/data/e1006.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1007,'EASY PROBLEM','??????','/data/e1007.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,1,0,0,0),
 (1008,'EASY PROBLEM','求小数位数个数','/data/e1008.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1009,'EASY PROBLEM','判断质数','/data/e1009.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1010,'EASY PROBLEM','求0的个数','/data/e1010.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1011,'EASY PROBLEM','幼儿园分班问题','/data/e1011.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1012,'EASY PROBLEM','门票价格计算','/data/e1012.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1013,'EASY PROBLEM','一个月的天数','/data/e1013.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1014,'EASY PROBLEM','????','/data/e1014.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,1,0,0,0),
 (1015,'EASY PROBLEM','阶乘','/data/e1015.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1016,'EASY PROBLEM','折纸问题','/data/e1016.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1017,'EASY PROBLEM','数字之和','/data/e1017.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1018,'EASY PROBLEM','数字反转','/data/e1018.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1019,'EASY PROBLEM','约数之和','/data/e1019.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1020,'EASY PROBLEM','求第几天','/data/e1020.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1021,'EASY PROBLEM','求最高、最低、平均分','/data/e1021.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1022,'EASY PROBLEM','求斐波拉切数列','/data/e1022.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1023,'EASY PROBLEM','求N!','/data/e1023.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1024,'EASY PROBLEM','繁殖问题','/data/e1024.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1025,'EASY PROBLEM','求总分','/data/e1025.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1026,'EASY PROBLEM','字母的前趋或后继','/data/e1026.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1027,'EASY PROBLEM','函数求值1','/data/e1027.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1028,'EASY PROBLEM','运费计算','/data/e1028.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1029,'EASY PROBLEM','两数比较大小','/data/e1029.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1030,'EASY PROBLEM','整数排序','/data/e1030.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1031,'EASY PROBLEM','星期几问题','/data/e1031.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1032,'EASY PROBLEM','加密','/data/e1032.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1033,'EASY PROBLEM','这个数字用英文怎么说？','/data/e1033.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1034,'EASY PROBLEM','min','/data/e1034.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1035,'EASY PROBLEM','实数运算','/data/e1035.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1036,'EASY PROBLEM','你的成绩如何？','/data/e1036.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1037,'EASY PROBLEM','繁忙的通讯兵','/data/e1037.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1038,'EASY PROBLEM','怪数','/data/e1038.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1039,'EASY PROBLEM','倒序','/data/e1039.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1040,'EASY PROBLEM','求N!','/data/e1040.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1041,'EASY PROBLEM','请给我加密','/data/e1041.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1042,'EASY PROBLEM','排序','/data/e1042.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1043,'EASY PROBLEM','回文问题','/data/e1043.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1044,'EASY PROBLEM','进制转换','/data/e1044.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1045,'EASY PROBLEM','表达式求值','/data/e1045.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1046,'EASY PROBLEM','发牌','/data/e1046.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1047,'EASY PROBLEM','输出*','/data/e1047.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1048,'EASY PROBLEM','输出正整数对应的二进制数','/data/e1048.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1049,'EASY PROBLEM','求三数中最大的数','/data/e1049.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1050,'EASY PROBLEM','递归函数FAC','/data/e1050.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1051,'EASY PROBLEM','汉诺塔','/data/e1051.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1052,'EASY PROBLEM','一道简单题','/data/e1052.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1053,'EASY PROBLEM','求质数','/data/e1053.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1054,'EASY PROBLEM','卖鸭子','/data/e1054.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1055,'EASY PROBLEM','统计字母','/data/e1055.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1056,'EASY PROBLEM','开关灯','/data/e1056.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1057,'EASY PROBLEM','学生信息','/data/e1057.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1058,'EASY PROBLEM','三角','/data/e1058.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1059,'EASY PROBLEM','日期','/data/e1059.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0),
 (1060,'EASY PROBLEM','国际象棋棋盘问题','/data/e1060.htm',NULL,NULL,NULL,'2009-01-01 00:00:00',1000,1000,10000,0,0,0,0,0,0);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;


--
-- Definition of table `solution`
--

DROP TABLE IF EXISTS `solution`;
CREATE TABLE `solution` (
  `solution_id` int(11) NOT NULL AUTO_INCREMENT,
  `problem_id` int(11) NOT NULL DEFAULT '0',
  `user_id` varchar(20) NOT NULL DEFAULT '',
  `runtime` int(11) NOT NULL DEFAULT '0',
  `memory` int(11) NOT NULL DEFAULT '0',
  `submit_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `result` smallint(6) NOT NULL DEFAULT '0',
  `language` tinyint(4) NOT NULL DEFAULT '0',
  `contest_id` int(11) NOT NULL DEFAULT '0',
  `valid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`solution_id`),
  KEY `uid` (`user_id`),
  KEY `pid` (`problem_id`),
  KEY `res` (`result`),
  KEY `cid` (`contest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `solution`
--

/*!40000 ALTER TABLE `solution` DISABLE KEYS */;
INSERT INTO `solution` (`solution_id`,`problem_id`,`user_id`,`runtime`,`memory`,`submit_date`,`result`,`language`,`contest_id`,`valid`) VALUES 
 (51,1000,'test',30,972,'2009-03-31 16:22:02',2,2,0,1),
 (52,1009,'071300722',0,0,'2009-03-31 20:06:25',3,2,0,1),
 (53,1010,'071300722',0,0,'2009-03-31 20:07:05',3,2,0,1),
 (54,1011,'071300722',0,0,'2009-03-31 20:07:53',3,2,0,1),
 (55,1012,'071300722',0,0,'2009-03-31 20:08:18',3,2,0,1),
 (56,1013,'071300722',0,0,'2009-03-31 20:08:39',3,2,0,1),
 (57,1000,'BMP_WANG',0,0,'2009-03-31 20:27:46',7,1,0,1),
 (60,1000,'test',0,0,'2009-04-02 11:09:44',3,2,0,1),
 (58,1000,'BMP_WANG',0,0,'2009-03-31 20:32:45',7,1,0,1),
 (59,1000,'test',10,816,'2009-03-31 21:10:32',2,2,0,1),
 (61,1012,'admin',0,0,'2009-04-03 17:40:27',8,2,0,1),
 (62,1000,'admin',0,0,'2009-04-03 17:48:58',8,2,0,1),
 (63,1000,'admin',0,0,'2009-04-03 17:49:06',8,2,0,1),
 (64,1000,'test',10,972,'2009-04-06 16:04:39',2,2,0,1),
 (65,1001,'test',0,0,'2009-04-10 13:07:40',8,2,0,1),
 (66,1000,'admin',0,0,'2009-04-13 13:37:42',8,2,0,1),
 (67,1001,'admin',0,0,'2009-04-13 13:38:25',8,2,0,1),
 (68,1000,'admin',0,0,'2009-04-13 13:39:08',3,2,0,1),
 (69,1000,'dylan',0,0,'2009-04-13 19:30:46',3,2,0,1),
 (70,1020,'hsw',0,0,'2009-04-13 20:03:16',8,1,0,1),
 (71,1000,'BMP_WANG',0,0,'2009-04-15 10:41:45',8,1,0,1),
 (72,1000,'BMP_WANG',10,816,'2009-04-15 10:43:27',2,1,0,1),
 (73,1000,'BMP_WANG',10,816,'2009-04-15 10:44:01',2,1,0,1),
 (74,1009,'BMP_WANG',0,0,'2009-04-15 10:48:17',3,1,0,1),
 (75,1009,'BMP_WANG',0,0,'2009-04-15 10:51:37',3,1,0,1),
 (76,1005,'BMP_WANG',0,0,'2009-04-15 10:56:45',7,1,0,1),
 (77,1005,'BMP_WANG',0,0,'2009-04-15 10:57:45',7,1,0,1),
 (78,1005,'BMP_WANG',0,0,'2009-04-15 10:58:40',7,1,0,1),
 (79,1009,'BMP_WANG',0,0,'2009-04-15 11:02:03',3,1,0,1),
 (80,1009,'BMP_WANG',0,0,'2009-04-15 11:03:19',3,1,0,1),
 (81,1001,'admin',0,0,'2009-04-17 12:01:56',8,1,0,1),
 (82,1014,'BMP_WANG',0,0,'2009-04-17 20:59:05',7,2,0,1),
 (83,1014,'BMP_WANG',0,0,'2009-04-17 20:59:44',7,1,0,1),
 (84,1000,'hsw',0,0,'2009-04-28 15:34:36',8,1,0,1),
 (85,1007,'BMP_WANG',0,0,'2009-05-01 02:17:25',7,1,0,1),
 (86,1014,'BMP_WANG',0,0,'2009-05-01 02:21:02',7,1,0,1),
 (87,1000,'BMP_WANG',10,816,'2009-05-01 02:22:48',2,1,0,1),
 (88,1000,'BMP_WANG',20,972,'2009-05-01 02:24:49',2,2,0,1),
 (89,1001,'guest',0,0,'2009-05-03 16:16:06',8,3,0,1);
/*!40000 ALTER TABLE `solution` ENABLE KEYS */;


--
-- Definition of table `source_code`
--

DROP TABLE IF EXISTS `source_code`;
CREATE TABLE `source_code` (
  `solution_id` int(10) unsigned NOT NULL DEFAULT '0',
  `source` text NOT NULL,
  PRIMARY KEY (`solution_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `source_code`
--

/*!40000 ALTER TABLE `source_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `source_code` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL DEFAULT '',
  `email` varchar(100) DEFAULT NULL,
  `nick` varchar(100) NOT NULL DEFAULT '',
  `school` varchar(100) NOT NULL DEFAULT '',
  `submit` int(11) DEFAULT '0',
  `solved` int(11) DEFAULT '0',
  `defunct` tinyint(4) NOT NULL DEFAULT '0',
  `ip` varchar(20) NOT NULL DEFAULT '',
  `accesstime` datetime DEFAULT NULL,
  `language` int(11) NOT NULL DEFAULT '1',
  `reg_time` datetime DEFAULT NULL,
  `role` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`,`password`,`email`,`nick`,`school`,`submit`,`solved`,`defunct`,`ip`,`accesstime`,`language`,`reg_time`,`role`) VALUES 
 ('admin','admin','admin@qq.com','admin','dhu',12,10,0,'192.168.0.0',NULL,0,NULL,0),
 ('qq','qq','admin@qq.com','qq','qq',11,11,0,'192.168.0.108',NULL,0,NULL,0),
 ('test','test',NULL,'test','test',11,1,0,'192.168.0.101','2009-05-04 20:53:27',0,NULL,0),
 ('071300621','wang1990',NULL,'robert','dhu',0,0,0,'192.168.0.116',NULL,0,NULL,0),
 ('lock_j','doggie1022',NULL,'lock','dhu',0,0,0,'192.168.0.117',NULL,0,NULL,0),
 ('071300722','123456',NULL,'wyy','dhu',0,0,0,'218.193.155.194',NULL,0,NULL,0),
 ('071300726','52190419',NULL,'zhl','dhu',0,0,0,'218.193.155.194',NULL,0,NULL,0),
 ('hsw','hsw',NULL,'hhh','donghua',0,0,0,'218.83.229.220',NULL,0,NULL,0),
 ('hhh','f',NULL,'f','f',0,0,0,'218.83.229.220',NULL,0,NULL,0),
 ('ff','f',NULL,'f','f',0,0,0,'218.83.229.220',NULL,0,NULL,0),
 ('dhh','hh',NULL,'hh','hh',0,0,0,'192.168.0.114',NULL,0,NULL,0),
 ('dylan','mylove',NULL,'aa','aa',0,0,0,'192.168.0.168',NULL,0,NULL,0),
 ('yy','tt',NULL,'nmm','kk',0,0,0,'192.168.0.114',NULL,0,NULL,0),
 ('adf','1',NULL,'1','1',0,0,0,'192.168.0.115',NULL,0,NULL,0),
 ('BMP_WANG','8933571722',NULL,'BMP_WANG','DHU',0,0,0,'192.168.0.114',NULL,0,NULL,0),
 ('123','123456',NULL,'hao','dhu',0,0,0,'218.193.155.194',NULL,0,NULL,0),
 ('426','123456',NULL,'hao','dhu',0,0,0,'218.193.155.194',NULL,0,NULL,0),
 ('qiang','qiang',NULL,'qiang','dhu',0,0,0,'222.69.246.80',NULL,0,NULL,0),
 ('guest','guest',NULL,'guest','',0,0,0,'',NULL,1,NULL,0),
 ('yhu','yhu','yhu@gmail.com','yhu','dhu',0,0,0,'127.0.0.1',NULL,1,'2009-05-04 00:00:00',0),
 ('zk','zk','zk@gmail.com','zk','dhu',0,0,0,'127.0.0.1',NULL,1,'2009-05-04 00:00:00',0),
 ('yhu2','zk','zk@gmail.com','zk','dhu',0,0,0,'127.0.0.1',NULL,1,'2009-05-04 00:00:00',0),
 ('yhu3','zk','zk@gmail.com','zk','dhu',0,0,0,'127.0.0.1',NULL,1,'2009-05-04 21:46:06',0),
 ('yhu4','zk','zk@gmail.com','zk','dhu',0,0,0,'127.0.0.1',NULL,1,'2009-05-04 21:53:42',0),
 ('yhu5','zk','zk@gmail.com','zk','dhu',0,0,0,'127.0.0.1',NULL,1,'2009-05-04 21:54:42',0),
 ('gjzhu','gjzhu','gjzhu@dhu.com','lao zhu','dhu',0,0,0,'127.0.0.1','2009-05-04 23:31:49',0,'2009-05-04 23:31:49',0),
 ('test1','gjzhu','gjzhu@dhu.com','lao zhu','dhu',0,0,0,'127.0.0.1','2009-05-05 10:54:27',0,'2009-05-05 10:54:27',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
