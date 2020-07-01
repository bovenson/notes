-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.7.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cnvd`
--

DROP TABLE IF EXISTS `cnvd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cnvd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `CNVD_ID` varchar(100) DEFAULT NULL COMMENT 'CNVD-ID',
  `vulnerability_name` varchar(100) DEFAULT NULL COMMENT '漏洞名称',
  `discoverer` varchar(100) DEFAULT NULL COMMENT '报送者',
  `release_date` varchar(100) DEFAULT NULL COMMENT '发布时间',
  `level` varchar(10) DEFAULT NULL COMMENT '危险级别',
  `device` varchar(1000) DEFAULT NULL COMMENT '影响产品',
  `CVE_ID` varchar(100) DEFAULT NULL COMMENT 'CVE ID\n',
  `description` varchar(3000) DEFAULT NULL COMMENT '漏洞描述',
  `refer_link` varchar(500) DEFAULT NULL COMMENT '参考链接',
  `solution` varchar(3000) DEFAULT NULL COMMENT '漏洞解决方案',
  `vendor` varchar(100) DEFAULT NULL COMMENT '漏洞发现者',
  `patch` varchar(500) DEFAULT NULL COMMENT '厂商补丁',
  `verification_info` varchar(500) DEFAULT NULL COMMENT '验证信息',
  `submission_date` varchar(100) DEFAULT NULL COMMENT '报送时间',
  `record_date` varchar(100) DEFAULT NULL COMMENT '收录时间',
  `update_date` varchar(100) DEFAULT NULL COMMENT '更新时间',
  `attachment` varchar(500) DEFAULT NULL COMMENT '附件',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CNVD_ID_UNIQUE` (`CNVD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cnvd`
--

LOCK TABLES `cnvd` WRITE;
/*!40000 ALTER TABLE `cnvd` DISABLE KEYS */;
INSERT INTO `cnvd` VALUES (1,'CNVD-2017-32177','ABB FOX515T信息泄露漏洞','恒安嘉新(北京)科技股份公司','2017-11-01','中','ABB FOX515T 1.0','CVE-2017-14025','FOX515是基于TDM技术（时分复用）的通用通信平台。 ABB FOX515T 1.0版本中存在信息泄露漏洞，允许本地攻击者为该应用程序未验证的脚本提供恶意参数，使攻击者能够检索服务器上的任何文件。','https://ics-cert.us-cert.gov/advisories/ICSA-17-304-01','厂商尚未提供漏洞修补方案，请关注厂商主页及时更新： http://new.abb.com/about/technology/cyber-security/alerts-and-notifications','Ketan Bali','(无补丁信息)','(暂无验证信息)','2017-11-01','2017-11-01','2017-11-01','(无附件)'),(2,'CNVD-2017-31343','Boston Scientific ZOOM LATITUDE PRM错误加密漏洞','恒安嘉新(北京)科技股份公司','2017-10-24','中','Boston Scientific ZOOM LATITUDE PRM Model 3120','CVE-2017-14012','ZOOM LATITUDE PRMs是波士顿科学公司的一套用于与植入式心脏起搏器和除颤器通信的便携式心律管理系统，部署在医疗卫生和公共卫生领域。 Boston Scientific ZOOM LATITUDE PRM存在错误加密漏洞，漏洞可使受影响的设备不会在静止时加密PHI。','https://ics-cert.us-cert.gov/advisories/ICSMA-17-292-01','厂商尚未提供漏洞修补方案，请关注厂商主页及时更新： http://www.bostonscientific.com/en-US/Home.html','Jonathan Butts and Billy Rios of Whitescope','(无补丁信息)','(暂无验证信息)','2017-10-23','2017-10-24','2017-10-24','(无附件)'),(3,'CNVD-2017-32169','Trihedral Engineering Limited VTScada未授权访问漏洞','恒安嘉新(北京)科技股份公司','2017-11-01','高','Trihedral Engineering Limited VTScada <=11.3.03','CVE-2017-14031','VTScada是加拿大Trihedral Engineering公司的一套基于Windows平台提供Web界面选项的SCADA系统。 Trihedral Engineering Limited VTScada存在未授权访问漏洞，本地非管理员用户可读取和写入目标计算机的文件系统。','https://ics-cert.us-cert.gov/advisories/ICSA-17-304-02','目前厂商已经发布了升级补丁以修复此安全问题，补丁获取链接： ftp://ftp.trihedral.com/VTS/VTScada 11.3版本/','Karn Ganeshen和Mark Cross','Trihedral Engineering Limited VTScada未授权访问漏洞的补丁','(暂无验证信息)','2017-11-01','2017-11-01','2017-11-01','(无附件)'),(4,'CNVD-2017-24367','OPW Fuel Management Systems SiteSentinel Integra和SiteSentinel iSite权限提升漏洞','恒安嘉新(北京)科技股份公司','2017-09-01','高','OPW Fuel Management Systems SiteSentinel iSite ATG <V175 OPW Fuel Management Systems SiteSentinel iSite ATG V175-V189 OPW Fuel Management Systems SiteSentinel iSite ATG V191-V195 OPW Fuel Management Systems SiteSentinel iSite ATG V16Q3.1 OPW Fuel Management Systems SiteSentinel Integra 100 <V175 OPW Fuel Management Systems SiteSentinel Integra 100 V175-V189 OPW Fuel Management Systems SiteSentinel Integra 100 V191-V195 OPW Fuel Management Systems SiteSentinel Integra 100 V16Q3.1 OPW Fuel Management Systems SiteSentinel Integra 500 <V175 OPW Fuel Management Systems SiteSentinel Integra 500 V175-V189 OPW Fuel Management Systems SiteSentinel Integra 500 V191-V195 OPW Fuel Management Systems SiteSentinel Integra 500 V16Q3.1','CVE-2017-12733','SiteSentinel Integra 100、SiteSentinel Integra 500和SiteSentinel iSite ATG都是为OPW燃料管理系统提供油罐监控功能的产品。 OPW Fuel Management Systems SiteSentinel Integra和SiteSentinel iSite存在权限提升漏洞，攻击者可通过创建一个应用程序的用户帐户利用漏洞获取管理权限。','https://ics-cert.us-cert.gov/advisories/ICSA-17-243-04','用户可参考如下厂商提供的安全公告获取补丁以修复该漏洞： http://www.opwglobal.com/docs/libraries/manuals/electronic-systems/opw-fms-manuals/m00-20-4438-integra-software-upgrade.pdf?sfvrsn=14','Semen Rozhkov of Kaspersky Lab','OPW Fuel Management Systems SiteSentinel Integra和SiteSentinel iSite权限提升漏洞的补丁','(暂无验证信息)','2017-09-01','2017-09-02','2017-09-02','(无附件)'),(5,'CNVD-2017-30755','Wireshark Profinet I/O解析器拒绝服务漏洞','恒安嘉新(北京)科技股份公司','2017-10-20','中','Wireshark Wireshark 2.4.0 Wireshark Wireshark >=2.2.0，<=2.2.8','CVE-2017-13766','Wireshark是Wireshark团队开发的一套网络数据包分析软件。 Wireshark Profinet I/O解析器存在安全漏洞，允许攻击者利用漏洞提交特殊的请求，进行拒绝服务攻击。','https://nvd.nist.gov/vuln/detail/CVE-2017-13766','用户可参考如下厂商提供的安全补丁以修复该漏洞： https://bugs.wireshark.org/bugzilla/show_bug.cgi?id=13847','ulf33286','Wireshark Profinet I/O解析器拒绝服务漏洞的补丁','(暂无验证信息)','2017-08-31','2017-10-20','2017-10-20','(无附件)'),(6,'CNVD-2017-29998','ProMinent MultiFLEX M10a Controller密码更改漏洞','恒安嘉新(北京)科技股份公司','2017-10-13','高','ProMinent MultiFLEX M10a Controller','CVE-2017-14005','MultiFLEX M10a Controller是一款水处理控制器。 ProMinent MultiFLEX M10a Controller存在密码更改漏洞，为用户设置新密码时，应用程序不需要用户知道原始密码，经过身份验证的攻击者可利用漏洞更改用户密码，从而访问和更改配置。','https://ics-cert.us-cert.gov/advisories/ICSA-17-285-01','厂商尚未提供漏洞修补方案，请关注厂商主页及时更新： https://ics-cert.us-cert.gov/advisories/ICSA-17-285-01','Maxim Rupp','(无补丁信息)','(暂无验证信息)','2017-10-13','2017-10-13','2017-10-13','(无附件)'),(7,'CNVD-2017-23000','多款NXP i.MX产品验证绕过漏洞','恒安嘉新(北京)科技股份公司','2017-08-26','中','NXP Semiconductors i.MX 50 NXP Semiconductors i.MX 53 NXP Semiconductors i.MX 6ULL NXP Semiconductors i.MX 6UltraLite NXP Semiconductors i.MX 6SoloLite NXP Semiconductors i.MX 6Solo NXP Semiconductors i.MX 6DualLite NXP Semiconductors i.MX 6Quad NXP Semiconductors i.MX 6SoloX NXP Semiconductors i.MX 6Dual NXP Semiconductors i.MX 6DualPlus NXP Semiconductors i.MX 6QuadPlus NXP Semiconductors Vybrid VF3xx NXP Semiconductors Vybrid VF5xx NXP Semiconductors Vybrid VF6xx NXP Semiconductors i.MX 28 NXP Semiconductors i.MX 7Solo NXP Semiconductors i.MX 7Dual','CVE-2017-7932','NXP i.MX 50等都是荷兰NXP Semiconductors公司的不同系列的微型处理器产品。 多款NXP i.MX产品中存在安全漏洞，由于程序未能正确验证证书。攻击者可利用该漏洞绕过签名验证，使设备加载未授权的图片，影响安全启动。','https://ics-cert.us-cert.gov/advisories/ICSA-17-152-02 http://www.securityfocus.com/bid/99966','目前厂商已发布升级补丁以修复漏洞，补丁获取链接： https://community.nxp.com/docs/DOC-334996','Quarkslab.','多款NXP i.MX产品验证绕过漏洞的补丁','(暂无验证信息)','2017-08-03','2017-08-26','2017-08-26','(无附件)');
/*!40000 ALTER TABLE `cnvd` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-22 15:40:39
