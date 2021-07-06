-- MariaDB dump 10.19  Distrib 10.4.18-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: deverse
-- ------------------------------------------------------
-- Server version	10.4.18-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `TB_BBSCTT_LIST`
--

DROP TABLE IF EXISTS `TB_BBSCTT_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_BBSCTT_LIST` (
  `BBSCTT_NO` int(11) NOT NULL AUTO_INCREMENT COMMENT '게시글_번호',
  `CTGRY_SE_CD` char(2) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '카테고리_구분_코드',
  `WRTER_USER_NO` int(11) NOT NULL COMMENT '작성자_회원_번호',
  `BBSCTT_REGIST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '게시글_등록_일시',
  `BBSCTT_TITLE` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '게시글_제목',
  `BBSCTT_CN` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '게시글_내용',
  `DEL_YN` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`BBSCTT_NO`),
  KEY `TB_BBSCTT_LIST_IDX01` (`CTGRY_SE_CD`) USING BTREE,
  KEY `TB_BBSCTT_LIST_IDX02` (`WRTER_USER_NO`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='게시글_목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_BBSCTT_LIST`
--

LOCK TABLES `TB_BBSCTT_LIST` WRITE;
/*!40000 ALTER TABLE `TB_BBSCTT_LIST` DISABLE KEYS */;
INSERT INTO `TB_BBSCTT_LIST` VALUES (1,'04',4,'2021-05-07 07:56:57','회사는 학원이 아니다.','https://okky.kr/article/929494\n\n','N','2021-05-07 07:56:57','google_115782293701208171772',NULL,NULL),(2,'04',5,'2021-05-07 14:53:46','여기는 뭐하는 곳 인가요?','# 안녕하세요. 신규회원 박지호 입니다\n* 여기는 뭐하는 곳인가요','N','2021-05-07 14:53:46','github_81128129',NULL,NULL),(3,'04',1,'2021-05-12 13:47:00','여기에 질문 올려도 되나요?','하이 자바 공부법 알려주세요','N','2021-05-12 13:47:00','github_81127695',NULL,NULL);
/*!40000 ALTER TABLE `TB_BBSCTT_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_BBSCTT_RCMD_LIST`
--

DROP TABLE IF EXISTS `TB_BBSCTT_RCMD_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_BBSCTT_RCMD_LIST` (
  `RCMD_NO` int(11) NOT NULL AUTO_INCREMENT COMMENT '추천_번호',
  `BBSCTT_NO` int(11) NOT NULL COMMENT '게시글_번호',
  `RCMD_USER_NO` int(11) NOT NULL COMMENT '회원_번호',
  `RCMD_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '추천_일시',
  `DEL_YN` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`RCMD_NO`),
  KEY `TB_BBSCTT_RCMD_LIST_IDX01` (`BBSCTT_NO`) USING BTREE,
  KEY `TB_BBSCTT_RCMD_LIST_IDX02` (`RCMD_USER_NO`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='게시글_추천_목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_BBSCTT_RCMD_LIST`
--

LOCK TABLES `TB_BBSCTT_RCMD_LIST` WRITE;
/*!40000 ALTER TABLE `TB_BBSCTT_RCMD_LIST` DISABLE KEYS */;
INSERT INTO `TB_BBSCTT_RCMD_LIST` VALUES (1,1,4,'2021-05-07 07:57:53','N','2021-05-07 07:57:53','google_115782293701208171772',NULL,NULL),(2,1,5,'2021-05-07 14:53:58','N','2021-05-07 14:53:58','github_81128129',NULL,NULL),(3,2,5,'2021-05-07 15:16:43','N','2021-05-07 15:16:43','github_81128129',NULL,NULL),(4,1,1,'2021-05-07 15:25:43','Y','2021-05-07 15:25:43','github_81127695','2021-05-10 13:03:10','github_81127695'),(5,2,1,'2021-05-10 13:02:35','Y','2021-05-10 13:02:35','github_81127695','2021-05-10 13:59:32','github_81127695'),(6,1,1,'2021-05-10 13:03:16','N','2021-05-10 13:03:16','github_81127695',NULL,NULL),(7,1,6,'2021-05-10 13:10:15','N','2021-05-10 13:10:15','google_105677305100597669424',NULL,NULL),(8,2,6,'2021-05-10 13:10:20','N','2021-05-10 13:10:20','google_105677305100597669424',NULL,NULL),(9,3,8,'2021-05-13 19:53:15','N','2021-05-13 19:53:15','github_35980235',NULL,NULL);
/*!40000 ALTER TABLE `TB_BBSCTT_RCMD_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_BBSCTT_TAG_LIST`
--

DROP TABLE IF EXISTS `TB_BBSCTT_TAG_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_BBSCTT_TAG_LIST` (
  `BBSCTT_NO` int(11) NOT NULL COMMENT '게시글_번호',
  `TAG_NO` int(11) NOT NULL COMMENT '태그_번호',
  `DEL_YN` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`BBSCTT_NO`,`TAG_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='게시글_태그_목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_BBSCTT_TAG_LIST`
--

LOCK TABLES `TB_BBSCTT_TAG_LIST` WRITE;
/*!40000 ALTER TABLE `TB_BBSCTT_TAG_LIST` DISABLE KEYS */;
INSERT INTO `TB_BBSCTT_TAG_LIST` VALUES (2,9,'N','2021-05-07 14:53:46','github_81128129',NULL,NULL),(3,1,'N','2021-05-12 13:47:00','github_81127695',NULL,NULL);
/*!40000 ALTER TABLE `TB_BBSCTT_TAG_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_CMMN_CD`
--

DROP TABLE IF EXISTS `TB_CMMN_CD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_CMMN_CD` (
  `CD_TYPE_ID` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '코드_유형_ID',
  `CD` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '코드',
  `CD_NM` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '코드_명',
  `CD_ENG_NM` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '코드_영문_명',
  `DEL_YN` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`CD_TYPE_ID`,`CD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='공통_코드';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_CMMN_CD`
--

LOCK TABLES `TB_CMMN_CD` WRITE;
/*!40000 ALTER TABLE `TB_CMMN_CD` DISABLE KEYS */;
INSERT INTO `TB_CMMN_CD` VALUES ('CSC001','01','트렌드','TREND','N','2021-03-23 17:19:02','admin',NULL,NULL),('CSC001','02','전문가','EXPERT','N','2021-03-23 17:19:02','admin',NULL,NULL),('CSC001','03','삶','LIFE','N','2021-03-23 17:19:02','admin',NULL,NULL),('CSC001','04','질의응답','QNA','N','2021-03-23 17:19:02','admin',NULL,NULL);
/*!40000 ALTER TABLE `TB_CMMN_CD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_CMMN_CD_TYPE`
--

DROP TABLE IF EXISTS `TB_CMMN_CD_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_CMMN_CD_TYPE` (
  `CD_TYPE_ID` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '코드_유형_ID',
  `CD_TYPE_NM` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '코드_유형_명',
  `CD_TYPE_ENG_NM` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '코드_유형_영문_명',
  `DEL_YN` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`CD_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='공통_코드_유형';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_CMMN_CD_TYPE`
--

LOCK TABLES `TB_CMMN_CD_TYPE` WRITE;
/*!40000 ALTER TABLE `TB_CMMN_CD_TYPE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_CMMN_CD_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_REPLY_LIST`
--

DROP TABLE IF EXISTS `TB_REPLY_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_REPLY_LIST` (
  `REPLY_NO` int(11) NOT NULL AUTO_INCREMENT COMMENT '댓글_번호',
  `BBSCTT_NO` int(11) NOT NULL COMMENT '게시글_번호',
  `REPLY_REGIST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '댓글_등록_일시',
  `WRTER_USER_NO` int(11) NOT NULL COMMENT '작성자_회원_번호',
  `REPLY_CN` mediumtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '댓글_내용',
  `DEL_YN` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`REPLY_NO`),
  KEY `TB_REPLY_LIST_IDX01` (`BBSCTT_NO`,`REPLY_REGIST_DT`) USING BTREE,
  KEY `TB_REPLY_LIST_IDX02` (`WRTER_USER_NO`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='댓글_목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_REPLY_LIST`
--

LOCK TABLES `TB_REPLY_LIST` WRITE;
/*!40000 ALTER TABLE `TB_REPLY_LIST` DISABLE KEYS */;
INSERT INTO `TB_REPLY_LIST` VALUES (1,1,'2021-05-07 07:57:40',4,'회사는 뭐하는 곳일까?','N','2021-05-07 07:57:40','google_115782293701208171772',NULL,NULL),(2,3,'2021-05-13 19:53:34',8,'열심히 해야죠...','Y','2021-05-13 19:53:34','github_35980235','2021-06-06 17:45:36','github_35980235'),(3,3,'2021-06-06 17:45:44',8,'열심히 해야죠...','N','2021-06-06 17:45:44','github_35980235',NULL,NULL);
/*!40000 ALTER TABLE `TB_REPLY_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_REPLY_RCMD_LIST`
--

DROP TABLE IF EXISTS `TB_REPLY_RCMD_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_REPLY_RCMD_LIST` (
  `RCMD_NO` int(11) NOT NULL AUTO_INCREMENT COMMENT '추천_번호',
  `REPLY_NO` int(11) NOT NULL COMMENT '댓글_번호',
  `RCMD_USER_NO` int(11) NOT NULL COMMENT '회원_번호',
  `RCMD_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '추천_일시',
  `DEL_YN` char(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`RCMD_NO`),
  KEY `TB_REPLY_RCMD_LIST_IDX01` (`REPLY_NO`) USING BTREE,
  KEY `TB_REPLY_RCMD_LIST_IDX02` (`RCMD_USER_NO`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='댓글_추천_목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_REPLY_RCMD_LIST`
--

LOCK TABLES `TB_REPLY_RCMD_LIST` WRITE;
/*!40000 ALTER TABLE `TB_REPLY_RCMD_LIST` DISABLE KEYS */;
INSERT INTO `TB_REPLY_RCMD_LIST` VALUES (1,1,5,'2021-05-07 14:54:00','N','2021-05-07 14:54:00','github_81128129',NULL,NULL),(2,2,8,'2021-05-13 19:53:40','N','2021-05-13 19:53:40','github_35980235',NULL,NULL),(3,3,8,'2021-06-06 17:45:46','N','2021-06-06 17:45:46','github_35980235',NULL,NULL);
/*!40000 ALTER TABLE `TB_REPLY_RCMD_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_TAG_LIST`
--

DROP TABLE IF EXISTS `TB_TAG_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_TAG_LIST` (
  `TAG_NO` int(11) NOT NULL AUTO_INCREMENT COMMENT '태그_번호',
  `TAG_NM` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '태그_명',
  `BBSCTT_REGIST_CNT` int(11) NOT NULL DEFAULT 0 COMMENT '게시글_등록_건수',
  `USER_REGIST_CNT` int(11) NOT NULL DEFAULT 0 COMMENT '사용자_등록_건수',
  `DEL_YN` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`TAG_NO`),
  UNIQUE KEY `TB_TAG_LIST_UN` (`TAG_NM`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='태그_목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_TAG_LIST`
--

LOCK TABLES `TB_TAG_LIST` WRITE;
/*!40000 ALTER TABLE `TB_TAG_LIST` DISABLE KEYS */;
INSERT INTO `TB_TAG_LIST` VALUES (1,'#java',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(2,'#javascript',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(3,'#c',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(4,'#python',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(5,'#html',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(6,'#webdev',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(7,'#css',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(8,'#beginner',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(9,'#tutorial',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(10,'#machine learing',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(11,'#deep learing',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(12,'#ruby',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(13,'#docker',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(14,'#kubernetis',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(15,'#network',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(16,'#system',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(17,'#cloud',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(18,'#spring',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(19,'#spring boot',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(20,'#flask',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(21,'#django',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(22,'#node',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(23,'#vue.js',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(24,'#react',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(25,'#angular',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(26,'#scalar',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(27,'#cpp',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(28,'#pandas',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(29,'#numpy',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(30,'#linux',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(31,'#centos',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL),(32,'#windows',0,0,'N','2021-05-04 17:55:34','system',NULL,NULL);
/*!40000 ALTER TABLE `TB_TAG_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_USER_LIST`
--

DROP TABLE IF EXISTS `TB_USER_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_USER_LIST` (
  `USER_NO` int(11) NOT NULL AUTO_INCREMENT COMMENT '회원_번호',
  `USER_ID` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '회원_ID',
  `OAUTH_PROVIDER` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'OAUTH_제공자',
  `USER_NM` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '회원_명',
  `USER_NICK` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '회원 닉네임',
  `USER_EMAIL` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '회원_이메일',
  `USER_AVATAR_URL` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '회원_아바타_URL',
  `USER_REGIST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '회원_등록_일시',
  `DEL_YN` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  `UPDT_DT` datetime DEFAULT NULL COMMENT '수정_일시',
  `UPDT_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '수정_ID',
  PRIMARY KEY (`USER_NO`),
  KEY `TB_USER_LIST_USER_ID_IDX` (`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='회원_목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_USER_LIST`
--

LOCK TABLES `TB_USER_LIST` WRITE;
/*!40000 ALTER TABLE `TB_USER_LIST` DISABLE KEYS */;
INSERT INTO `TB_USER_LIST` VALUES (1,'github_81127695','github',NULL,'dataus-clearstar',NULL,'https://avatars.githubusercontent.com/u/81127695?v=4','2021-05-04 17:45:40','N','2021-05-04 17:45:40','github_81127695',NULL,NULL),(2,'github_55748886','github',NULL,'gygy7151',NULL,'https://avatars.githubusercontent.com/u/55748886?v=4','2021-05-04 17:49:11','N','2021-05-04 17:49:11','github_55748886',NULL,NULL),(3,'google_112026936170004073430','google','김진천','cheecro1811','cheecro1811@gmail.com','https://lh3.googleusercontent.com/a/AATXAJzz0NHmTrG6k6yO5zoVgkuQcHhDD0JnM1C4kbJ5=s96-c','2021-05-06 16:20:35','N','2021-05-06 16:20:35','google_112026936170004073430',NULL,NULL),(4,'google_115782293701208171772','google','우광명','delbertwoo','delbertwoo@gmail.com','https://lh3.googleusercontent.com/a/AATXAJynz1gK_t-Q1kIDmaGSmNaKf8qLApGuepdOXgDH=s96-c','2021-05-07 07:55:09','N','2021-05-07 07:55:09','google_115782293701208171772',NULL,NULL),(5,'github_81128129','github',NULL,'dataus-parkjh',NULL,'https://avatars.githubusercontent.com/u/81128129?v=4','2021-05-07 14:52:44','N','2021-05-07 14:52:44','github_81128129',NULL,NULL),(6,'google_105677305100597669424','google','박지호','iks15174','iks15174@gmail.com','https://lh3.googleusercontent.com/a/AATXAJxdwgBEeCzTvovZ-fZIOub4Hf3gA0mbueNUwMFY=s96-c','2021-05-10 13:09:45','N','2021-05-10 13:09:45','google_105677305100597669424',NULL,NULL),(7,'github_4122286','github',NULL,'delbertwoo','delbertwoo@gmail.com','https://avatars.githubusercontent.com/u/4122286?v=4','2021-05-11 07:08:48','N','2021-05-11 07:08:48','github_4122286',NULL,NULL),(8,'github_35980235','github',NULL,'iks15174',NULL,'https://avatars.githubusercontent.com/u/35980235?v=4','2021-05-13 19:51:42','N','2021-05-13 19:51:42','github_35980235',NULL,NULL);
/*!40000 ALTER TABLE `TB_USER_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TB_USER_TAG_LIST`
--

DROP TABLE IF EXISTS `TB_USER_TAG_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TB_USER_TAG_LIST` (
  `USER_NO` int(11) NOT NULL COMMENT '사용자_번호',
  `TAG_NO` int(11) NOT NULL COMMENT '태그_번호',
  `DEL_YN` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'N' COMMENT '삭제_여부',
  `INST_DT` datetime NOT NULL DEFAULT current_timestamp() COMMENT '입력_일시',
  `INST_ID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '입력_ID',
  PRIMARY KEY (`USER_NO`,`TAG_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='사용자_태그_목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TB_USER_TAG_LIST`
--

LOCK TABLES `TB_USER_TAG_LIST` WRITE;
/*!40000 ALTER TABLE `TB_USER_TAG_LIST` DISABLE KEYS */;
/*!40000 ALTER TABLE `TB_USER_TAG_LIST` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-17 10:45:18
