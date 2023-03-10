/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 5.7.25-log : Database - music
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`music` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `music`;

/*Table structure for table `musiclist` */

DROP TABLE IF EXISTS `musiclist`;

CREATE TABLE `musiclist` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `songName` varchar(16) DEFAULT NULL,
  `singer` varchar(16) DEFAULT NULL,
  `songUrl` varchar(32) DEFAULT NULL,
  `songTime` varchar(16) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `musiclist` */

insert  into `musiclist`(`id`,`songName`,`singer`,`songUrl`,`songTime`,`type`) values 
(3,'是但求其爱','陈奕迅 ','1640770686873.mp3','2021/12/29','流行'),
(4,'爱的代价','李宗盛 ','1640773195438.mp3','2021/12/29','流行'),
(7,'成都','赵雷 ','1640773638079.mp3','2021/12/29','民谣'),
(8,'斑马，斑马','宋冬野 ','1640773693970.mp3','2021/12/29','民谣'),
(10,'梦回唐朝','唐朝乐队','1640773802241.mp3','2021/12/29','摇滚'),
(11,'无地自容','黑豹乐队 ','1640773846206.mp3','2021/12/29','摇滚'),
(12,'枫桥夜泊','陈亮 ','1640773893400.mp3','2021/12/29','电子'),
(14,'这世界那么多人','莫文蔚 ','1640774007694.mp3','2021/12/29','华语'),
(15,'最冷一天','张国荣 ','1640774041128.mp3','2021/12/29','华语'),
(16,'无题','陈亮 ','1640774371747.mp3','2021/12/29','电子');

/*Table structure for table `mymusic` */

DROP TABLE IF EXISTS `mymusic`;

CREATE TABLE `mymusic` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `songName` varchar(16) DEFAULT NULL,
  `singer` varchar(16) DEFAULT NULL,
  `songUrl` varchar(32) DEFAULT NULL,
  `userName` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `mymusic` */

insert  into `mymusic`(`id`,`songName`,`singer`,`songUrl`,`userName`) values 
(23,'最冷一天','张国荣 ','1640774041128.mp3','2'),
(24,'无地自容','黑豹乐队 ','1640773846206.mp3','2'),
(26,'爱的代价','李宗盛 ','1640773195438.mp3','2'),
(32,'是但求其爱','陈奕迅 ','1640770686873.mp3','1'),
(34,'成都','赵雷 ','1640773638079.mp3','庞'),
(36,'斑马，斑马','宋冬野 ','1640773693970.mp3','庞');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`) values 
(1,'1','1'),
(2,'5','5'),
(3,'2','2'),
(4,'庞','123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
