CREATE DATABASE  IF NOT EXISTS `arche` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `arche`;

CREATE USER 'ArchE'@'%' IDENTIFIED BY 'dumbledore';
GRANT ALL PRIVILEGES ON *.* TO 'ArchE'@'%' WITH GRANT OPTION; 

-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: arche
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `__factsets__`
--

DROP TABLE IF EXISTS `__factsets__`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `__factsets__` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `setName` varchar(45) NOT NULL DEFAULT '',
  `factType` varchar(255) NOT NULL DEFAULT '',
  `group` tinyint(3) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `setName` (`setName`),
  KEY `sort` (`group`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `__factsets__`
--

LOCK TABLES `__factsets__` WRITE;
/*!40000 ALTER TABLE `__factsets__` DISABLE KEYS */;
INSERT INTO `__factsets__` VALUES (3,'Project','MAIN::Scenarios',1),(4,'Project','MAIN::Responsibilities',1),(5,'Project','MAIN::Function',1),(6,'Project','MAIN::SequenceRelation',2),(7,'Project','MAIN::TranslationRelation',2),(8,'Project','MAIN::FunctionRefinementRelation',2),(9,'Project','MAIN::ResponsibilityToResponsibilityRelation',2),(10,'Project','MAIN::ResponsibilityRefinementRelation',2),(11,'Project','MAIN::ScenarioRefinementRelation',2),(12,'Project','Planner::C_AddTranslationRelation',2),(13,'Project','Planner::C_AddResponsibilityRelation',2),(14,'Project','Planner::C_AddScenario',1),(15,'Project','Planner::C_AddFunction',1),(18,'Project','Design::VariationPoint',3),(19,'Project','Design::ModuleInterface',3),(20,'Project','Design::Module',3),(21,'Project','Design::Wrapper',3),(22,'Project','Design::Responsibility',3),(23,'Project','Design::SharedResource',3),(24,'Project','Design::VPInputValue',3),(25,'Project','Design::ResponsibilityToModuleRelation',4),(26,'Project','Design::ModuleRefinementRelation',4),(27,'Project','Design::ModuleDependencyRelation',4),(28,'Project','Design::RealizeRelation',4),(29,'Project','Design::SharedResourceRelation',4),(62,'Design','MAIN::Scenarios',1),(63,'Design','MAIN::Responsibilities',1),(64,'Design','MAIN::TranslationRelation',2),(65,'Design','MAIN::SequenceRelation',2),(68,'Design','MAIN::ResponsibilityToResponsibilityRelation',2),(69,'Design','MAIN::ResponsibilityRefinementRelation',2),(77,'Design','Design::VariationPoint',3),(78,'Design','Design::ModuleInterface',3),(79,'Design','Design::Module',3),(80,'Design','Design::Wrapper',3),(82,'Design','Design::SharedResource',3),(83,'Design','Design::VPInputValue',3),(84,'Design','Design::ResponsibilityToModuleRelation',4),(85,'Design','Design::ModuleRefinementRelation',4),(86,'Design','Design::ModuleDependencyRelation',4),(87,'Design','Design::RealizeRelation',4),(88,'Design','Design::SharedResourceRelation',4),(105,'Project','Design::Process',3),(106,'Project','Design::Thread',3),(107,'Project','Design::UnitOfConcurrency',3),(108,'Design','Design::Process',3),(109,'Design','Design::Thread',3),(110,'Design','Design::UnitOfConcurrency',3),(111,'EvaluationMatrix','Seeker::EvaluationResult',6),(112,'Project','ChangeImpactModifiabilityRF::ResponsibilityDependencyRelation',2),(113,'Design','ChangeImpactModifiabilityRF::ResponsibilityDependencyRelation',2),(114,'Project','ChangeImpactModifiabilityRF::P_CostOfChange',5),(115,'Design','ChangeImpactModifiabilityRF::P_CostOfChange',5),(116,'Project','ChangeImpactModifiabilityRF::P_BooleanTest',5),(117,'Design','ChangeImpactModifiabilityRF::P_BooleanTest',5),(118,'Project','ChangeImpactModifiabilityRF::P_ProbabilityIncoming',5),(119,'Design','ChangeImpactModifiabilityRF::P_ProbabilityIncoming',5),(120,'Project','ChangeImpactModifiabilityRF::P_ProbabilityOutgoing',5),(121,'Design','ChangeImpactModifiabilityRF::P_ProbabilityOutgoing',5),(122,'Project','ChangeImpactModifiabilityRF::Node_Responsibility',3),(123,'Design','ChangeImpactModifiabilityRF::Node_Responsibility',3),(124,'Project','ChangeImpactModifiabilityRF::Arc_Relation',4),(125,'Design','ChangeImpactModifiabilityRF::Arc_Relation',4),(126,'Project','ChangeImpactModifiabilityRF::ResponsibilityToModuleRelation',4),(127,'Design','ChangeImpactModifiabilityRF::ResponsibilityToModuleRelation',4),(128,'Project','ICMPerformanceRF::ResponsibilityReactionRelation',2),(129,'Design','ICMPerformanceRF::ResponsibilityReactionRelation',2),(130,'Project','ICMPerformanceRF::P_ExecutionTime',5),(131,'Design','ICMPerformanceRF::P_ExecutionTime',5),(132,'Project','ICMPerformanceRF::AssemblyInstance',3),(133,'Design','ICMPerformanceRF::AssemblyInstance',3),(134,'Project','MyFirstRF::TestResponsibilityRelation',2),(135,'Design','MyFirstRF::TestResponsibilityRelation',2),(136,'Project','MyFirstRF::P_TestResponsibilityParameter',5),(137,'Design','MyFirstRF::P_TestResponsibilityParameter',5),(138,'Project','MyFirstRF::P_TestRelationParameter',5),(139,'Design','MyFirstRF::P_TestRelationParameter',5);
/*!40000 ALTER TABLE `__factsets__` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `__mod_solver_interface__`
--

DROP TABLE IF EXISTS `__mod_solver_interface__`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `__mod_solver_interface__` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FactSet` varchar(100) NOT NULL DEFAULT '',
  `Version` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `__mod_solver_interface__`
--

LOCK TABLES `__mod_solver_interface__` WRITE;
/*!40000 ALTER TABLE `__mod_solver_interface__` DISABLE KEYS */;
INSERT INTO `__mod_solver_interface__` VALUES (1,'ModifiabilityModel','ModModel');
/*!40000 ALTER TABLE `__mod_solver_interface__` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `__tool_command_interface__`
--

DROP TABLE IF EXISTS `__tool_command_interface__`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `__tool_command_interface__` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ToolName` varchar(100) NOT NULL DEFAULT '',
  `Direction` varchar(3) NOT NULL DEFAULT '' COMMENT 'Content=out: Command is to the tool, content=in: Command to ArchE',
  `FactSet` varchar(100) NOT NULL DEFAULT '',
  `Version` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `__tool_command_interface__`
--

LOCK TABLES `__tool_command_interface__` WRITE;
/*!40000 ALTER TABLE `__tool_command_interface__` DISABLE KEYS */;
INSERT INTO `__tool_command_interface__` VALUES (1,'ModelSolver','out','ModifiabilityModel','ModModel-sagar'),(2,'AADLConverter','out','Design','Architecture1'),(46,'ModelSolver','in','ModifiabilityResult','ModResult-sagar');
/*!40000 ALTER TABLE `__tool_command_interface__` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `__versions__`
--

DROP TABLE IF EXISTS `__versions__`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `__versions__` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version_name` varchar(200) NOT NULL DEFAULT '',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_read` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `storage_type` varchar(45) NOT NULL DEFAULT '',
  `factSet` varchar(45) NOT NULL DEFAULT '',
  `parent` int(10) unsigned DEFAULT NULL,
  `max_fact_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16301 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `__versions__`
--

LOCK TABLES `__versions__` WRITE;
/*!40000 ALTER TABLE `__versions__` DISABLE KEYS */;
INSERT INTO `__versions__` VALUES (12029,'RMAModel1','2007-10-09 18:45:48','0000-00-00 00:00:00','temp','RMAModel',NULL,NULL),(12041,'UserModModel','2007-10-09 19:16:53','0000-00-00 00:00:00','temp','ModifiabilityModel',NULL,NULL),(13250,'test','2007-12-14 18:57:47','0000-00-00 00:00:00','project','Project',NULL,NULL),(15314,'ctas_sample','2008-06-13 12:49:23','0000-00-00 00:00:00','project','Project',NULL,NULL),(16298,'test34','2014-07-15 15:12:42','0000-00-00 00:00:00','project','Project',NULL,NULL),(16300,'Architecture1','2014-07-15 15:12:43','0000-00-00 00:00:00','temp','Design',16298,5093);
/*!40000 ALTER TABLE `__versions__` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_arc_relation`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_arc_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_arc_relation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `source` varchar(20) DEFAULT NULL,
  `target` varchar(20) DEFAULT NULL,
  `probability` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_arc_relation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_arc_relation`
--

LOCK TABLES `changeimpactmodifiabilityrf_arc_relation` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_arc_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_arc_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_module`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_module` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `costOfChange` double DEFAULT NULL,
  `complexity` double DEFAULT NULL,
  `source` text,
  `status` int(10) DEFAULT NULL,
  `id` int(10) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_module` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_module`
--

LOCK TABLES `changeimpactmodifiabilityrf_module` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_moduledependencyrelation`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_moduledependencyrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_moduledependencyrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `source` text,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `probability` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_moduledependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_moduledependencyrelation`
--

LOCK TABLES `changeimpactmodifiabilityrf_moduledependencyrelation` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_moduledependencyrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_moduledependencyrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_node_responsibility`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_node_responsibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_node_responsibility` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `name` text,
  `cost` double DEFAULT NULL,
  `cumulativeprob` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_node_responsibility` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_node_responsibility`
--

LOCK TABLES `changeimpactmodifiabilityrf_node_responsibility` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_node_responsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_node_responsibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_p_booleantest`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_p_booleantest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_p_booleantest` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_p_booleantest` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_p_booleantest`
--

LOCK TABLES `changeimpactmodifiabilityrf_p_booleantest` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_booleantest` DISABLE KEYS */;
INSERT INTO `changeimpactmodifiabilityrf_p_booleantest` VALUES (124,16298,'<Fact-13>','FALSE','<Fact-7>','User',''),(125,16298,'<Fact-14>','TRUE','<Fact-8>','User',''),(128,16300,'<Fact-13>','FALSE','<Fact-7>','User',''),(129,16300,'<Fact-14>','TRUE','<Fact-8>','User','');
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_booleantest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_p_costofchange`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_p_costofchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_p_costofchange` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_p_costofchange` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39234 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_p_costofchange`
--

LOCK TABLES `changeimpactmodifiabilityrf_p_costofchange` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_costofchange` DISABLE KEYS */;
INSERT INTO `changeimpactmodifiabilityrf_p_costofchange` VALUES (32465,15314,'<Fact-258>',5.5,'<Fact-24>','ArchE','none'),(32466,15314,'<Fact-259>',5.5,'<Fact-20>','ArchE','none'),(32467,15314,'<Fact-260>',5.5,'<Fact-18>','ArchE','none'),(32468,15314,'<Fact-261>',5.5,'<Fact-16>','ArchE','none'),(32469,15314,'<Fact-262>',5.5,'<Fact-26>','ArchE','none'),(32470,15314,'<Fact-263>',5.5,'<Fact-21>','ArchE','none'),(32471,15314,'<Fact-264>',5.5,'<Fact-17>','ArchE','none'),(32472,15314,'<Fact-265>',5.5,'<Fact-15>','ArchE','none'),(32473,15314,'<Fact-266>',5.5,'<Fact-25>','ArchE','none'),(32474,15314,'<Fact-267>',5.5,'<Fact-23>','ArchE','none'),(32475,15314,'<Fact-268>',5.5,'<Fact-19>','ArchE','none'),(32476,15314,'<Fact-269>',5.5,'<Fact-22>','ArchE','none'),(32477,15314,'<Fact-270>',5.5,'<Fact-27>','ArchE','none'),(32478,15314,'<Fact-271>',5.5,'<Fact-28>','ArchE','none'),(39225,16298,'<Fact-10>',2,'<Fact-7>','User',''),(39226,16298,'<Fact-11>',77,'<Fact-8>','User',''),(39227,16298,'<Fact-12>',2,'<Fact-9>','User',''),(39231,16300,'<Fact-10>',2,'<Fact-7>','User',''),(39232,16300,'<Fact-11>',77,'<Fact-8>','User',''),(39233,16300,'<Fact-12>',2,'<Fact-9>','User','');
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_costofchange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_p_probabilityincoming`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_p_probabilityincoming`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_p_probabilityincoming` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` text,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_p_probabilityincoming` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_p_probabilityincoming`
--

LOCK TABLES `changeimpactmodifiabilityrf_p_probabilityincoming` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_probabilityincoming` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_probabilityincoming` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_p_probabilityoutgoing`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_p_probabilityoutgoing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_p_probabilityoutgoing` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` text NOT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_p_probabilityoutgoing` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_p_probabilityoutgoing`
--

LOCK TABLES `changeimpactmodifiabilityrf_p_probabilityoutgoing` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_probabilityoutgoing` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_probabilityoutgoing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_responsibilitydependencyrelation`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_responsibilitydependencyrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_responsibilitydependencyrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_responsibilitydependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33132 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_responsibilitydependencyrelation`
--

LOCK TABLES `changeimpactmodifiabilityrf_responsibilitydependencyrelation` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_responsibilitydependencyrelation` DISABLE KEYS */;
INSERT INTO `changeimpactmodifiabilityrf_responsibilitydependencyrelation` VALUES (33117,15314,'<Fact-226>','','User','<Fact-25>','<Fact-22>'),(33118,15314,'<Fact-227>','','User','<Fact-17>','<Fact-25>'),(33119,15314,'<Fact-228>','','User','<Fact-17>','<Fact-22>'),(33120,15314,'<Fact-229>','','User','<Fact-25>','<Fact-15>'),(33121,15314,'<Fact-230>','','User','<Fact-25>','<Fact-23>'),(33122,15314,'<Fact-231>','','User','<Fact-23>','<Fact-24>'),(33123,15314,'<Fact-232>','','User','<Fact-21>','<Fact-15>'),(33124,15314,'<Fact-233>','','User','<Fact-21>','<Fact-17>'),(33125,15314,'<Fact-234>','','User','<Fact-21>','<Fact-25>'),(33126,15314,'<Fact-235>','','User','<Fact-21>','<Fact-18>'),(33127,15314,'<Fact-236>','','User','<Fact-18>','<Fact-25>'),(33128,15314,'<Fact-237>','','User','<Fact-18>','<Fact-22>'),(33129,15314,'<Fact-238>','','User','<Fact-26>','<Fact-25>'),(33130,15314,'<Fact-239>','','User','<Fact-19>','<Fact-20>'),(33131,15314,'<Fact-240>','','User','<Fact-19>','<Fact-17>');
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_responsibilitydependencyrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `changeimpactmodifiabilityrf_responsibilitytomodulerelation`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_responsibilitytomodulerelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `changeimpactmodifiabilityrf_responsibilitytomodulerelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `source` text,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `parentType` text,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_responsibilitytomodulerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29145 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `changeimpactmodifiabilityrf_responsibilitytomodulerelation`
--

LOCK TABLES `changeimpactmodifiabilityrf_responsibilitytomodulerelation` WRITE;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_responsibilitytomodulerelation` DISABLE KEYS */;
INSERT INTO `changeimpactmodifiabilityrf_responsibilitytomodulerelation` VALUES (29134,15314,'<Fact-247>',0,'ArchE','<Fact-241>','<Fact-19>','Module'),(29135,15314,'<Fact-248>',0,'ArchE','<Fact-241>','<Fact-15>','Module'),(29136,15314,'<Fact-249>',0,'ArchE','<Fact-242>','<Fact-23>','Module'),(29137,15314,'<Fact-250>',0,'ArchE','<Fact-242>','<Fact-20>','Module'),(29138,15314,'<Fact-251>',0,'ArchE','<Fact-242>','<Fact-25>','Module'),(29139,15314,'<Fact-252>',0,'ArchE','<Fact-242>','<Fact-18>','Module'),(29140,15314,'<Fact-253>',0,'ArchE','<Fact-242>','<Fact-24>','Module'),(29141,15314,'<Fact-254>',0,'ArchE','<Fact-242>','<Fact-22>','Module'),(29142,15314,'<Fact-255>',0,'ArchE','<Fact-242>','<Fact-17>','Module'),(29143,15314,'<Fact-256>',0,'ArchE','<Fact-243>','<Fact-26>','Module'),(29144,15314,'<Fact-257>',0,'ArchE','<Fact-243>','<Fact-21>','Module');
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_responsibilitytomodulerelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_module`
--

DROP TABLE IF EXISTS `design_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_module` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` varchar(200) DEFAULT NULL,
  `costOfChange` double DEFAULT NULL,
  `complexity` double DEFAULT NULL,
  `name` text,
  `source` varchar(200) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_design_module` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3246 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_module`
--

LOCK TABLES `design_module` WRITE;
/*!40000 ALTER TABLE `design_module` DISABLE KEYS */;
INSERT INTO `design_module` VALUES (3243,15314,'<Fact-241>',NULL,2,0,'(M) View - A','ArchE',0),(3244,15314,'<Fact-242>',NULL,4,0,'(M) Model - B','ArchE',0),(3245,15314,'<Fact-243>',NULL,5,0,'(M) Controller - C','ArchE',0);
/*!40000 ALTER TABLE `design_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_moduledependencyrelation`
--

DROP TABLE IF EXISTS `design_moduledependencyrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_moduledependencyrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `source` text,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `probability` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_design_moduledependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5506 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_moduledependencyrelation`
--

LOCK TABLES `design_moduledependencyrelation` WRITE;
/*!40000 ALTER TABLE `design_moduledependencyrelation` DISABLE KEYS */;
INSERT INTO `design_moduledependencyrelation` VALUES (5503,15314,'<Fact-244>',0,'ArchE','<Fact-242>','<Fact-241>',0),(5504,15314,'<Fact-245>',0,'ArchE','<Fact-243>','<Fact-241>',0),(5505,15314,'<Fact-246>',0,'ArchE','<Fact-243>','<Fact-242>',0);
/*!40000 ALTER TABLE `design_moduledependencyrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_moduleinterface`
--

DROP TABLE IF EXISTS `design_moduleinterface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_moduleinterface` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `encapsulationLevel` double DEFAULT NULL,
  `name` text,
  `source` varchar(200) DEFAULT NULL,
  `costOfChange` double DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_moduleinterface` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_moduleinterface`
--

LOCK TABLES `design_moduleinterface` WRITE;
/*!40000 ALTER TABLE `design_moduleinterface` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_moduleinterface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_modulerefinementrelation`
--

DROP TABLE IF EXISTS `design_modulerefinementrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_modulerefinementrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_modulerefinementrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_modulerefinementrelation`
--

LOCK TABLES `design_modulerefinementrelation` WRITE;
/*!40000 ALTER TABLE `design_modulerefinementrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_modulerefinementrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_process`
--

DROP TABLE IF EXISTS `design_process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_process` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `scenario` varchar(20) DEFAULT NULL,
  `stimulusType` varchar(200) DEFAULT NULL,
  `stimulusValue` text,
  `taskType` varchar(200) DEFAULT NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `time_blocked` text,
  `priority` text,
  `source` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_process` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_process`
--

LOCK TABLES `design_process` WRITE;
/*!40000 ALTER TABLE `design_process` DISABLE KEYS */;
INSERT INTO `design_process` VALUES (33,965,'<Fact-113>','Process-aaaaaaaaa','<Fact-78>','periodic','9.999','HardDeadline','6.999','17.0',NULL,NULL,NULL,'ArchE'),(34,965,'<Fact-124>','Process-fsadfdsa','<Fact-76>','periodic','9.999','HardDeadline','9.999','9.0',NULL,NULL,NULL,'ArchE');
/*!40000 ALTER TABLE `design_process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_realizerelation`
--

DROP TABLE IF EXISTS `design_realizerelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_realizerelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `childType` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_realizerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_realizerelation`
--

LOCK TABLES `design_realizerelation` WRITE;
/*!40000 ALTER TABLE `design_realizerelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_realizerelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_responsibility`
--

DROP TABLE IF EXISTS `design_responsibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_responsibility` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `owner` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_responsibility` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_responsibility`
--

LOCK TABLES `design_responsibility` WRITE;
/*!40000 ALTER TABLE `design_responsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_responsibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_responsibilitytomodulerelation`
--

DROP TABLE IF EXISTS `design_responsibilitytomodulerelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_responsibilitytomodulerelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `parentType` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_responsibilitytomodulerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_responsibilitytomodulerelation`
--

LOCK TABLES `design_responsibilitytomodulerelation` WRITE;
/*!40000 ALTER TABLE `design_responsibilitytomodulerelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_responsibilitytomodulerelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_sharedresource`
--

DROP TABLE IF EXISTS `design_sharedresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_sharedresource` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `name` text,
  `description` text,
  `source` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_sharedresource` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_sharedresource`
--

LOCK TABLES `design_sharedresource` WRITE;
/*!40000 ALTER TABLE `design_sharedresource` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_sharedresource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_sharedresourcerelation`
--

DROP TABLE IF EXISTS `design_sharedresourcerelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_sharedresourcerelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `design_sharedresourcerelation_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_sharedresourcerelation`
--

LOCK TABLES `design_sharedresourcerelation` WRITE;
/*!40000 ALTER TABLE `design_sharedresourcerelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_sharedresourcerelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_thread`
--

DROP TABLE IF EXISTS `design_thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_thread` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `scenario` varchar(20) DEFAULT NULL,
  `stimulusType` varchar(200) DEFAULT NULL,
  `stimulusValue` text,
  `taskType` varchar(200) DEFAULT NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `time_blocked` text,
  `priority` text,
  `source` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_thread` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_thread`
--

LOCK TABLES `design_thread` WRITE;
/*!40000 ALTER TABLE `design_thread` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_unitofconcurrency`
--

DROP TABLE IF EXISTS `design_unitofconcurrency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_unitofconcurrency` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `task` varchar(20) DEFAULT NULL,
  `scenario` varchar(20) DEFAULT NULL,
  `responsibility` varchar(20) DEFAULT NULL,
  `MutualExclusion` varchar(200) DEFAULT NULL,
  `SharedResourceID` varchar(20) DEFAULT NULL,
  `taskType` varchar(200) DEFAULT NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `priority` text,
  `source` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_unitofconcurrency` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_unitofconcurrency`
--

LOCK TABLES `design_unitofconcurrency` WRITE;
/*!40000 ALTER TABLE `design_unitofconcurrency` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_unitofconcurrency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_variationpoint`
--

DROP TABLE IF EXISTS `design_variationpoint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_variationpoint` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `name` text,
  `value` text,
  `mechanism` text,
  `condition` text,
  `alternatives` text,
  `howTo` text,
  `cost` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_variationpoint` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_variationpoint`
--

LOCK TABLES `design_variationpoint` WRITE;
/*!40000 ALTER TABLE `design_variationpoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_variationpoint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_vpinputvalue`
--

DROP TABLE IF EXISTS `design_vpinputvalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_vpinputvalue` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `name` text,
  `value` text,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_vpinputvalue` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_vpinputvalue`
--

LOCK TABLES `design_vpinputvalue` WRITE;
/*!40000 ALTER TABLE `design_vpinputvalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_vpinputvalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `design_wrapper`
--

DROP TABLE IF EXISTS `design_wrapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `design_wrapper` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `encapsulationLevel` double DEFAULT NULL,
  `name` text,
  `source` varchar(200) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_wrapper` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `design_wrapper`
--

LOCK TABLES `design_wrapper` WRITE;
/*!40000 ALTER TABLE `design_wrapper` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_wrapper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `externalinteraction_responsibilitydependencyrelation`
--

DROP TABLE IF EXISTS `externalinteraction_responsibilitydependencyrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `externalinteraction_responsibilitydependencyrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varbinary(100) DEFAULT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varbinary(100) DEFAULT NULL,
  `child` varbinary(100) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_externalinteraction_responsibilitydependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `externalinteraction_responsibilitydependencyrelation`
--

LOCK TABLES `externalinteraction_responsibilitydependencyrelation` WRITE;
/*!40000 ALTER TABLE `externalinteraction_responsibilitydependencyrelation` DISABLE KEYS */;
INSERT INTO `externalinteraction_responsibilitydependencyrelation` VALUES (45,11673,'<Fact-144>',NULL,NULL,'ArchE','<Fact-22>','<Fact-23>');
/*!40000 ALTER TABLE `externalinteraction_responsibilitydependencyrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `externalinteraction_responsibilityinteractionrelation`
--

DROP TABLE IF EXISTS `externalinteraction_responsibilityinteractionrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `externalinteraction_responsibilityinteractionrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varbinary(100) DEFAULT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varbinary(100) DEFAULT NULL,
  `child` varbinary(100) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_externalinteraction_responsibilityinteractionrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `externalinteraction_responsibilityinteractionrelation`
--

LOCK TABLES `externalinteraction_responsibilityinteractionrelation` WRITE;
/*!40000 ALTER TABLE `externalinteraction_responsibilityinteractionrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `externalinteraction_responsibilityinteractionrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `externalinteraction_responsibilityreactionrelation`
--

DROP TABLE IF EXISTS `externalinteraction_responsibilityreactionrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `externalinteraction_responsibilityreactionrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varbinary(100) DEFAULT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varbinary(100) DEFAULT NULL,
  `child` varbinary(100) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_externalinteraction_responsibilityreactionrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `externalinteraction_responsibilityreactionrelation`
--

LOCK TABLES `externalinteraction_responsibilityreactionrelation` WRITE;
/*!40000 ALTER TABLE `externalinteraction_responsibilityreactionrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `externalinteraction_responsibilityreactionrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icmperformancerf_assemblyinstance`
--

DROP TABLE IF EXISTS `icmperformancerf_assemblyinstance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icmperformancerf_assemblyinstance` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `icmFilename` text,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_assemblyinstance` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icmperformancerf_assemblyinstance`
--

LOCK TABLES `icmperformancerf_assemblyinstance` WRITE;
/*!40000 ALTER TABLE `icmperformancerf_assemblyinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `icmperformancerf_assemblyinstance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icmperformancerf_p_executiontime`
--

DROP TABLE IF EXISTS `icmperformancerf_p_executiontime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icmperformancerf_p_executiontime` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_p_executiontime` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11487 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icmperformancerf_p_executiontime`
--

LOCK TABLES `icmperformancerf_p_executiontime` WRITE;
/*!40000 ALTER TABLE `icmperformancerf_p_executiontime` DISABLE KEYS */;
INSERT INTO `icmperformancerf_p_executiontime` VALUES (107,12974,'<Fact-188>',1,'<Fact-59>','ArchE','none'),(108,12974,'<Fact-189>',1,'<Fact-33>','ArchE','none'),(8213,15314,'<Fact-170>',1,'<Fact-18>','ArchE','none'),(8214,15314,'<Fact-171>',1,'<Fact-21>','ArchE','none'),(8215,15314,'<Fact-172>',1,'<Fact-16>','ArchE','none'),(8216,15314,'<Fact-173>',1,'<Fact-24>','ArchE','none'),(8217,15314,'<Fact-174>',1,'<Fact-25>','ArchE','none'),(8218,15314,'<Fact-175>',1,'<Fact-26>','ArchE','none'),(8219,15314,'<Fact-176>',1,'<Fact-20>','ArchE','none'),(8220,15314,'<Fact-177>',1,'<Fact-17>','ArchE','none'),(8221,15314,'<Fact-178>',1,'<Fact-15>','ArchE','none'),(8222,15314,'<Fact-179>',1,'<Fact-23>','ArchE','none'),(8223,15314,'<Fact-180>',1,'<Fact-22>','ArchE','none'),(8224,15314,'<Fact-181>',1,'<Fact-19>','ArchE','none'),(11481,16298,'<Fact-15>',56,'<Fact-7>','User',''),(11482,16298,'<Fact-16>',77,'<Fact-8>','User',''),(11485,16300,'<Fact-15>',56,'<Fact-7>','User',''),(11486,16300,'<Fact-16>',77,'<Fact-8>','User','');
/*!40000 ALTER TABLE `icmperformancerf_p_executiontime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icmperformancerf_p_taskpriority`
--

DROP TABLE IF EXISTS `icmperformancerf_p_taskpriority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icmperformancerf_p_taskpriority` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` int(10) DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_p_taskpriority` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icmperformancerf_p_taskpriority`
--

LOCK TABLES `icmperformancerf_p_taskpriority` WRITE;
/*!40000 ALTER TABLE `icmperformancerf_p_taskpriority` DISABLE KEYS */;
INSERT INTO `icmperformancerf_p_taskpriority` VALUES (107,12974,'<Fact-190>',1,'<Fact-59>','ArchE','none'),(108,12974,'<Fact-191>',1,'<Fact-33>','ArchE','none');
/*!40000 ALTER TABLE `icmperformancerf_p_taskpriority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icmperformancerf_responsibilityinteractionrelation`
--

DROP TABLE IF EXISTS `icmperformancerf_responsibilityinteractionrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icmperformancerf_responsibilityinteractionrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) DEFAULT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_responsibilityinteractionrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icmperformancerf_responsibilityinteractionrelation`
--

LOCK TABLES `icmperformancerf_responsibilityinteractionrelation` WRITE;
/*!40000 ALTER TABLE `icmperformancerf_responsibilityinteractionrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `icmperformancerf_responsibilityinteractionrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icmperformancerf_responsibilityreactionrelation`
--

DROP TABLE IF EXISTS `icmperformancerf_responsibilityreactionrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icmperformancerf_responsibilityreactionrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_responsibilityreactionrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1869 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icmperformancerf_responsibilityreactionrelation`
--

LOCK TABLES `icmperformancerf_responsibilityreactionrelation` WRITE;
/*!40000 ALTER TABLE `icmperformancerf_responsibilityreactionrelation` DISABLE KEYS */;
INSERT INTO `icmperformancerf_responsibilityreactionrelation` VALUES (5,12974,'<Fact-131>',NULL,'User','<Fact-59>','<Fact-33>'),(1863,15314,'<Fact-103>','','User','<Fact-26>','<Fact-25>'),(1864,15314,'<Fact-104>','','User','<Fact-20>','<Fact-19>'),(1865,15314,'<Fact-105>','','User','<Fact-21>','<Fact-17>'),(1866,15314,'<Fact-106>','','User','<Fact-21>','<Fact-18>'),(1867,15314,'<Fact-107>','','User','<Fact-21>','<Fact-15>'),(1868,15314,'<Fact-108>','','User','<Fact-15>','<Fact-25>');
/*!40000 ALTER TABLE `icmperformancerf_responsibilityreactionrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `leave_responsibilities`
--

DROP TABLE IF EXISTS `leave_responsibilities`;
/*!50001 DROP VIEW IF EXISTS `leave_responsibilities`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `leave_responsibilities` (
  `uid` tinyint NOT NULL,
  `version` tinyint NOT NULL,
  `fact-id` tinyint NOT NULL,
  `id` tinyint NOT NULL,
  `name` tinyint NOT NULL,
  `description` tinyint NOT NULL,
  `source` tinyint NOT NULL,
  `version_name` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `main_dataflow`
--

DROP TABLE IF EXISTS `main_dataflow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_dataflow` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `producer` varchar(20) DEFAULT NULL,
  `consumer` varchar(20) DEFAULT NULL,
  `dataitem` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_dataflow` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_dataflow`
--

LOCK TABLES `main_dataflow` WRITE;
/*!40000 ALTER TABLE `main_dataflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_dataflow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_dataitems`
--

DROP TABLE IF EXISTS `main_dataitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_dataitems` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL DEFAULT '0',
  `fact-id` varchar(20) NOT NULL DEFAULT '',
  `name` text,
  `description` text,
  `source` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_main_dataitems_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_dataitems`
--

LOCK TABLES `main_dataitems` WRITE;
/*!40000 ALTER TABLE `main_dataitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_dataitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_function`
--

DROP TABLE IF EXISTS `main_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_function` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_function` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1321 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_function`
--

LOCK TABLES `main_function` WRITE;
/*!40000 ALTER TABLE `main_function` DISABLE KEYS */;
INSERT INTO `main_function` VALUES (961,13250,'<Fact-31>','1','Show Itinerary'),(962,13250,'<Fact-32>','10','Manage user Profile'),(963,13250,'<Fact-33>','10.1','Create user Profile'),(964,13250,'<Fact-34>','10.2','Modify user Profile'),(965,13250,'<Fact-35>','2','Attach to Model'),(966,13250,'<Fact-36>','3','Register Views'),(967,13250,'<Fact-37>','4','Handle user Interactions'),(968,13250,'<Fact-38>','6','Save Data'),(969,13250,'<Fact-39>','7','Query for Data'),(970,13250,'<Fact-40>','8','Locate Service'),(971,13250,'<Fact-41>','9','Manage Itinerary'),(972,13250,'<Fact-42>','5','Manage External Devices'),(1309,15314,'<Fact-27>','1','Show Itinerary'),(1310,15314,'<Fact-28>','10','Manage user Profile'),(1311,15314,'<Fact-29>','10.1','Create user Profile'),(1312,15314,'<Fact-30>','10.2','Modify user Profile'),(1313,15314,'<Fact-31>','2','Attach to Model'),(1314,15314,'<Fact-32>','3','Register Views'),(1315,15314,'<Fact-33>','4','Handle user Interactions'),(1316,15314,'<Fact-34>','6','Save Data'),(1317,15314,'<Fact-35>','7','Query for Data'),(1318,15314,'<Fact-36>','8','Locate Service'),(1319,15314,'<Fact-37>','9','Manage Itinerary'),(1320,15314,'<Fact-38>','5','Manage External Devices');
/*!40000 ALTER TABLE `main_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_functionrefinementrelation`
--

DROP TABLE IF EXISTS `main_functionrefinementrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_functionrefinementrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_functionrefinementrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_functionrefinementrelation`
--

LOCK TABLES `main_functionrefinementrelation` WRITE;
/*!40000 ALTER TABLE `main_functionrefinementrelation` DISABLE KEYS */;
INSERT INTO `main_functionrefinementrelation` VALUES (161,13250,'<Fact-76>',NULL,'User','<Fact-32>','<Fact-33>'),(162,13250,'<Fact-77>',NULL,'User','<Fact-32>','<Fact-34>'),(219,15314,'<Fact-69>',NULL,'User','<Fact-28>','<Fact-29>'),(220,15314,'<Fact-70>',NULL,'User','<Fact-28>','<Fact-30>');
/*!40000 ALTER TABLE `main_functionrefinementrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_leafresponsibilitydependencyrelation`
--

DROP TABLE IF EXISTS `main_leafresponsibilitydependencyrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_leafresponsibilitydependencyrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `incoming` double DEFAULT NULL,
  `outgoing` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_leafresponsibilitydependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_leafresponsibilitydependencyrelation`
--

LOCK TABLES `main_leafresponsibilitydependencyrelation` WRITE;
/*!40000 ALTER TABLE `main_leafresponsibilitydependencyrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_leafresponsibilitydependencyrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_p_analysisresult`
--

DROP TABLE IF EXISTS `main_p_analysisresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_p_analysisresult` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `quality` varchar(200) DEFAULT NULL,
  `reasoningFramework` varchar(200) DEFAULT NULL,
  `isSatisfied` varchar(200) DEFAULT NULL,
  `utility` double DEFAULT NULL,
  `value` double DEFAULT NULL,
  `oldValue` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_p_analysisresult` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_p_analysisresult`
--

LOCK TABLES `main_p_analysisresult` WRITE;
/*!40000 ALTER TABLE `main_p_analysisresult` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_p_analysisresult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_responsibilities`
--

DROP TABLE IF EXISTS `main_responsibilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_responsibilities` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` varchar(200) DEFAULT NULL,
  `name` text,
  `description` text,
  `source` varchar(200) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_main_responsibilities` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75969 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_responsibilities`
--

LOCK TABLES `main_responsibilities` WRITE;
/*!40000 ALTER TABLE `main_responsibilities` DISABLE KEYS */;
INSERT INTO `main_responsibilities` VALUES (67126,15314,'<Fact-15>','gen228','Show Itinerary','Show Itinerary','ArchE',0),(67127,15314,'<Fact-16>','gen227','Manage User Profile','Manage user Profile','ArchE',0),(67128,15314,'<Fact-17>','gen226','Create User Profile','Create user Profile','ArchE',0),(67129,15314,'<Fact-18>','gen225','Modify User Profile','Modify user Profile','ArchE',0),(67130,15314,'<Fact-19>','gen224','Attach to Model','Attach to Model','ArchE',0),(67131,15314,'<Fact-20>','gen223','Register Views','Register Views','ArchE',0),(67132,15314,'<Fact-21>','gen222','Handle User Interactions','Handle user Interactions','ArchE',0),(67133,15314,'<Fact-22>','gen220','Save Data','Save Data','ArchE',0),(67134,15314,'<Fact-23>','gen219','Query for Data','Query for Data','ArchE',0),(67135,15314,'<Fact-24>','gen241','Locate Service','Locate Service','ArchE',0),(67136,15314,'<Fact-25>','gen240','Manage Itinerary','Manage Itinerary','ArchE',0),(67137,15314,'<Fact-26>','gen558','Manage External Devices','Manage External Devices','ArchE',0),(75957,16298,'<Fact-7>','gen60','Nouvelle Resp','Desc Nouvelle Resp','User',0),(75958,16298,'<Fact-8>','gen76','Nouvelle Resp 2','Desc 2','User',0),(75959,16298,'<Fact-9>','gen62','test3','test3','User',0),(75960,16298,'<Fact-44>','gen30','asad',NULL,'User',0),(75965,16300,'<Fact-7>','gen60','Nouvelle Resp','Desc Nouvelle Resp','User',0),(75966,16300,'<Fact-8>','gen76','Nouvelle Resp 2','Desc 2','User',0),(75967,16300,'<Fact-9>','gen62','test3','test3','User',0),(75968,16300,'<Fact-44>','gen30','asad',NULL,'User',0);
/*!40000 ALTER TABLE `main_responsibilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_responsibilityrefinementrelation`
--

DROP TABLE IF EXISTS `main_responsibilityrefinementrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_responsibilityrefinementrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_responsibilityrefinementrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17126 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_responsibilityrefinementrelation`
--

LOCK TABLES `main_responsibilityrefinementrelation` WRITE;
/*!40000 ALTER TABLE `main_responsibilityrefinementrelation` DISABLE KEYS */;
INSERT INTO `main_responsibilityrefinementrelation` VALUES (12855,13250,'<Fact-93>',NULL,'ArchE','<Fact-20>','<Fact-21>'),(12856,13250,'<Fact-94>',NULL,'ArchE','<Fact-20>','<Fact-22>'),(17124,15314,'<Fact-86>',NULL,'ArchE','<Fact-16>','<Fact-17>'),(17125,15314,'<Fact-87>',NULL,'ArchE','<Fact-16>','<Fact-18>');
/*!40000 ALTER TABLE `main_responsibilityrefinementrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_responsibilitytoresponsibilityrelation`
--

DROP TABLE IF EXISTS `main_responsibilitytoresponsibilityrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_responsibilitytoresponsibilityrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_responsibilitytoresponsibilityrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47535 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_responsibilitytoresponsibilityrelation`
--

LOCK TABLES `main_responsibilitytoresponsibilityrelation` WRITE;
/*!40000 ALTER TABLE `main_responsibilitytoresponsibilityrelation` DISABLE KEYS */;
INSERT INTO `main_responsibilitytoresponsibilityrelation` VALUES (34005,13250,'<Fact-78>','gen302','User','<Fact-23>','<Fact-24>'),(34006,13250,'<Fact-79>','gen301','User','<Fact-21>','<Fact-22>'),(34007,13250,'<Fact-80>','gen300','User','<Fact-21>','<Fact-26>'),(34008,13250,'<Fact-81>','gen299','User','<Fact-25>','<Fact-21>'),(34009,13250,'<Fact-82>','gen298','User','<Fact-25>','<Fact-29>'),(34010,13250,'<Fact-83>','gen297','User','<Fact-25>','<Fact-22>'),(34011,13250,'<Fact-84>','gen296','User','<Fact-25>','<Fact-19>'),(34012,13250,'<Fact-85>','gen294','User','<Fact-29>','<Fact-27>'),(34013,13250,'<Fact-86>','gen293','User','<Fact-29>','<Fact-26>'),(34014,13250,'<Fact-87>','gen292','User','<Fact-29>','<Fact-19>'),(34015,13250,'<Fact-88>','gen291','User','<Fact-22>','<Fact-29>'),(34016,13250,'<Fact-89>','gen289','User','<Fact-27>','<Fact-28>'),(34017,13250,'<Fact-90>','gen290','User','<Fact-22>','<Fact-26>'),(34018,13250,'<Fact-91>','gen872','User','<Fact-30>','<Fact-29>'),(34019,13250,'<Fact-92>','gen2123','User','<Fact-25>','<Fact-24>'),(47520,15314,'<Fact-71>','gen302','User','<Fact-19>','<Fact-20>'),(47521,15314,'<Fact-72>','gen301','User','<Fact-17>','<Fact-18>'),(47522,15314,'<Fact-73>','gen300','User','<Fact-17>','<Fact-22>'),(47523,15314,'<Fact-74>','gen299','User','<Fact-21>','<Fact-17>'),(47524,15314,'<Fact-75>','gen298','User','<Fact-21>','<Fact-25>'),(47525,15314,'<Fact-76>','gen297','User','<Fact-21>','<Fact-18>'),(47526,15314,'<Fact-77>','gen296','User','<Fact-21>','<Fact-15>'),(47527,15314,'<Fact-78>','gen294','User','<Fact-25>','<Fact-23>'),(47528,15314,'<Fact-79>','gen293','User','<Fact-25>','<Fact-22>'),(47529,15314,'<Fact-80>','gen292','User','<Fact-25>','<Fact-15>'),(47530,15314,'<Fact-81>','gen291','User','<Fact-18>','<Fact-25>'),(47531,15314,'<Fact-82>','gen289','User','<Fact-23>','<Fact-24>'),(47532,15314,'<Fact-83>','gen290','User','<Fact-18>','<Fact-22>'),(47533,15314,'<Fact-84>','gen872','User','<Fact-26>','<Fact-25>'),(47534,15314,'<Fact-85>','gen2123','User','<Fact-21>','<Fact-20>');
/*!40000 ALTER TABLE `main_responsibilitytoresponsibilityrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_scenariorefinementrelation`
--

DROP TABLE IF EXISTS `main_scenariorefinementrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_scenariorefinementrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_scenariorefinementrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_scenariorefinementrelation`
--

LOCK TABLES `main_scenariorefinementrelation` WRITE;
/*!40000 ALTER TABLE `main_scenariorefinementrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_scenariorefinementrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_scenarios`
--

DROP TABLE IF EXISTS `main_scenarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_scenarios` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `description` text,
  `quality` varchar(200) DEFAULT NULL,
  `stimulusText` text,
  `stimulusType` varchar(200) DEFAULT NULL,
  `stimulusUnit` varchar(200) DEFAULT NULL,
  `stimulusValue` double DEFAULT NULL,
  `sourceText` text,
  `sourceType` varchar(200) DEFAULT NULL,
  `sourceUnit` varchar(200) DEFAULT NULL,
  `sourceValue` double DEFAULT NULL,
  `artifactText` text,
  `artifactType` varchar(200) DEFAULT NULL,
  `artifactUnit` varchar(200) DEFAULT NULL,
  `artifactValue` double DEFAULT NULL,
  `environmentText` text,
  `environmentType` varchar(200) DEFAULT NULL,
  `environmentUnit` varchar(200) DEFAULT NULL,
  `environmentValue` double DEFAULT NULL,
  `responseText` text,
  `responseType` varchar(200) DEFAULT NULL,
  `responseUnit` varchar(200) DEFAULT NULL,
  `responseValue` double DEFAULT NULL,
  `measureText` text,
  `measureType` varchar(200) DEFAULT NULL,
  `measureUnit` varchar(200) DEFAULT NULL,
  `measureValue` double DEFAULT NULL,
  `state` text,
  `reasoningFramework` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_main_scenarios` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27755 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_scenarios`
--

LOCK TABLES `main_scenarios` WRITE;
/*!40000 ALTER TABLE `main_scenarios` DISABLE KEYS */;
INSERT INTO `main_scenarios` VALUES (27747,15314,'<Fact-7>','gen237','P1 - The system has to manage the external devices under normal load and handle the operations in under 5 seconds.','ICMPerformance','external devices','periodic','milliseconds',40,'external devices','System',NULL,NULL,'system','System',NULL,NULL,'normal','NormalCondition',NULL,NULL,'handles the operation','TaskLatency',NULL,NULL,'under 5 seconds','WorstCase','msec',15,NULL,'ICMPerformanceRF'),(27748,15314,'<Fact-8>','gen244','P2 - A view wishes to attach to the model under normal conditions and do so in under 1 second.','ICMPerformance','attach to model','periodic','milliseconds',42,'view','System',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'view is attached to model','TaskLatency',NULL,NULL,'in under 1 second','WorstCase','msec',39,NULL,'ICMPerformanceRF'),(27749,15314,'<Fact-9>','gen248','P3 - The user modifies their profile under normal conditions and the profile is modified in under 5 seconds.','ICMPerformance','modify profile','periodic','milliseconds',64,'user','External',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'profile is modified','TaskLatency',NULL,NULL,'in under 5 seconds','WorstCase','msec',18,NULL,'ICMPerformanceRF'),(27750,15314,'<Fact-10>','gen252','P4 - The user asks to show the itinerary under normal conditions and the itinerary is shown in under 5 seconds.','ICMPerformance','show itinerary','periodic','milliseconds',55,'user','External',NULL,NULL,'system','System',NULL,NULL,'Normal conditions','NormalCondition',NULL,NULL,'itinerary is shown','TaskLatency',NULL,NULL,'in under 5 seconds','WorstCase','msec',16,NULL,'ICMPerformanceRF'),(27751,15314,'<Fact-11>','gen256','P5 - The user asks to save the current data on the screen under normal conditions and the data is saved in under 10 seconds.','ICMPerformance','save data','periodic','milliseconds',18,'user','External',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'data is saved','TaskLatency',NULL,NULL,'in under 10 seconds','WorstCase','msec',4,NULL,'ICMPerformanceRF'),(27752,15314,'<Fact-12>','gen309','M1 - Adding a new feature requires to change the storage format. The implementation of the new format has to be done within 3.5 days','ChangeImpactModifiability','Adding a new feature',NULL,NULL,NULL,NULL,'developer',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'The implementation of the new format has to be done',NULL,NULL,NULL,'within 3.5 days','CostConstraint','days',3,NULL,'ChangeImpactModifiabilityRF'),(27753,15314,'<Fact-13>','gen318','M2 - A new variable to the user profile has to be added within 5 days of effort','ChangeImpactModifiability',NULL,NULL,NULL,NULL,NULL,'endUser',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Type response measure here','CostConstraint','days',15,NULL,'ChangeImpactModifiabilityRF'),(27754,15314,'<Fact-14>','gen433','M3 - The driver for a new external device has to be added by a developer within 10 days','ChangeImpactModifiability','Adding a new device',NULL,NULL,NULL,NULL,'developer',NULL,NULL,'The driver for a new external device',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'has to be added',NULL,NULL,NULL,'within 10 days','CostConstraint','days',5.5,NULL,'ChangeImpactModifiabilityRF');
/*!40000 ALTER TABLE `main_scenarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_sequencerelation`
--

DROP TABLE IF EXISTS `main_sequencerelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_sequencerelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) DEFAULT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_sequencerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_sequencerelation`
--

LOCK TABLES `main_sequencerelation` WRITE;
/*!40000 ALTER TABLE `main_sequencerelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_sequencerelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_translationrelation`
--

DROP TABLE IF EXISTS `main_translationrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_translationrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parentType` varchar(200) DEFAULT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_main_translationrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101622 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_translationrelation`
--

LOCK TABLES `main_translationrelation` WRITE;
/*!40000 ALTER TABLE `main_translationrelation` DISABLE KEYS */;
INSERT INTO `main_translationrelation` VALUES (101593,15314,'<Fact-40>','Functionality',NULL,'ArchE','<Fact-27>','<Fact-15>'),(101594,15314,'<Fact-41>','Functionality',NULL,'ArchE','<Fact-28>','<Fact-16>'),(101595,15314,'<Fact-42>','Functionality',NULL,'ArchE','<Fact-29>','<Fact-17>'),(101596,15314,'<Fact-43>','Functionality',NULL,'ArchE','<Fact-30>','<Fact-18>'),(101597,15314,'<Fact-44>','Functionality',NULL,'ArchE','<Fact-31>','<Fact-19>'),(101598,15314,'<Fact-45>','Functionality',NULL,'ArchE','<Fact-32>','<Fact-20>'),(101599,15314,'<Fact-46>','Functionality',NULL,'ArchE','<Fact-33>','<Fact-21>'),(101600,15314,'<Fact-47>','Functionality',NULL,'ArchE','<Fact-34>','<Fact-22>'),(101601,15314,'<Fact-48>','Functionality',NULL,'ArchE','<Fact-35>','<Fact-23>'),(101602,15314,'<Fact-49>','Functionality',NULL,'ArchE','<Fact-36>','<Fact-24>'),(101603,15314,'<Fact-50>','Functionality',NULL,'ArchE','<Fact-37>','<Fact-25>'),(101604,15314,'<Fact-51>','Functionality',NULL,'ArchE','<Fact-38>','<Fact-26>'),(101605,15314,'<Fact-52>','Scenario',NULL,'User','<Fact-14>','<Fact-26>'),(101606,15314,'<Fact-53>','Scenario',NULL,'User','<Fact-7>','<Fact-26>'),(101607,15314,'<Fact-54>','Scenario',NULL,'User','<Fact-12>','<Fact-23>'),(101608,15314,'<Fact-55>','Scenario',NULL,'User','<Fact-12>','<Fact-22>'),(101609,15314,'<Fact-56>','Scenario',NULL,'User','<Fact-13>','<Fact-17>'),(101610,15314,'<Fact-57>','Scenario',NULL,'User','<Fact-13>','<Fact-18>'),(101611,15314,'<Fact-58>','Scenario',NULL,'User','<Fact-9>','<Fact-21>'),(101612,15314,'<Fact-59>','Scenario',NULL,'User','<Fact-11>','<Fact-17>'),(101613,15314,'<Fact-60>','Scenario',NULL,'User','<Fact-7>','<Fact-25>'),(101614,15314,'<Fact-61>','Scenario',NULL,'User','<Fact-8>','<Fact-20>'),(101615,15314,'<Fact-62>','Scenario',NULL,'User','<Fact-8>','<Fact-19>'),(101616,15314,'<Fact-63>','Scenario',NULL,'User','<Fact-9>','<Fact-17>'),(101617,15314,'<Fact-64>','Scenario',NULL,'User','<Fact-9>','<Fact-18>'),(101618,15314,'<Fact-65>','Scenario',NULL,'User','<Fact-10>','<Fact-21>'),(101619,15314,'<Fact-66>','Scenario',NULL,'User','<Fact-10>','<Fact-15>'),(101620,15314,'<Fact-67>','Scenario',NULL,'User','<Fact-10>','<Fact-25>'),(101621,15314,'<Fact-68>','Scenario',NULL,'User','<Fact-11>','<Fact-22>');
/*!40000 ALTER TABLE `main_translationrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilitylearn_modifiabilitylearnvalues`
--

DROP TABLE IF EXISTS `modifiabilitylearn_modifiabilitylearnvalues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilitylearn_modifiabilitylearnvalues` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `value` double DEFAULT NULL,
  `history1` double DEFAULT NULL,
  `history2` double DEFAULT NULL,
  `history3` double DEFAULT NULL,
  `history4` double DEFAULT NULL,
  `history5` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilitylearn_modifiabilitylearnvalues` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilitylearn_modifiabilitylearnvalues`
--

LOCK TABLES `modifiabilitylearn_modifiabilitylearnvalues` WRITE;
/*!40000 ALTER TABLE `modifiabilitylearn_modifiabilitylearnvalues` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilitylearn_modifiabilitylearnvalues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_arc_relation`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_arc_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_arc_relation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `source` varchar(20) DEFAULT NULL,
  `target` varchar(20) DEFAULT NULL,
  `probability` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_arc_relation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_arc_relation`
--

LOCK TABLES `modifiabilityreasoningframeworks_arc_relation` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_arc_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_arc_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_node_affected`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_node_affected`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_node_affected` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) DEFAULT NULL,
  `responsibilityId` varchar(20) DEFAULT NULL,
  `nodeId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_node_affected` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_node_affected`
--

LOCK TABLES `modifiabilityreasoningframeworks_node_affected` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_node_affected` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_node_affected` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_node_responsibility`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_node_responsibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_node_responsibility` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `name` text,
  `cost` double DEFAULT NULL,
  `cumulativeprob` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_node_responsibility` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_node_responsibility`
--

LOCK TABLES `modifiabilityreasoningframeworks_node_responsibility` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_node_responsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_node_responsibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_p_abstractiontranslator`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_abstractiontranslator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_p_abstractiontranslator` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_p_abstractiontranslator` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_p_abstractiontranslator`
--

LOCK TABLES `modifiabilityreasoningframeworks_p_abstractiontranslator` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_abstractiontranslator` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_abstractiontranslator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_p_costofchange`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_costofchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_p_costofchange` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_p_costofchange` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_p_costofchange`
--

LOCK TABLES `modifiabilityreasoningframeworks_p_costofchange` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_costofchange` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_costofchange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_p_encapsulationlevel`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_encapsulationlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_p_encapsulationlevel` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `modifiabilityreasoningframeworks_p_encapsulationlevel_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_p_encapsulationlevel`
--

LOCK TABLES `modifiabilityreasoningframeworks_p_encapsulationlevel` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_encapsulationlevel` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_encapsulationlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_p_interfacerouter`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_interfacerouter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_p_interfacerouter` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_p_interfacerouter` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_p_interfacerouter`
--

LOCK TABLES `modifiabilityreasoningframeworks_p_interfacerouter` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_interfacerouter` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_interfacerouter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_p_preparedforchange`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_preparedforchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_p_preparedforchange` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `modifiabilityreasoningframeworks_p_preparedforchange_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_p_preparedforchange`
--

LOCK TABLES `modifiabilityreasoningframeworks_p_preparedforchange` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_preparedforchange` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_preparedforchange` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_p_probabilityincoming`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_probabilityincoming`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_p_probabilityincoming` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `modifiabilityreasoningframeworks_p_probabilityincoming_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_p_probabilityincoming`
--

LOCK TABLES `modifiabilityreasoningframeworks_p_probabilityincoming` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_probabilityincoming` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_probabilityincoming` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_p_probabilityoutgoing`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_probabilityoutgoing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_p_probabilityoutgoing` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `modifiabilityreasoningframeworks_p_probabilityoutgoing_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_p_probabilityoutgoing`
--

LOCK TABLES `modifiabilityreasoningframeworks_p_probabilityoutgoing` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_probabilityoutgoing` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_probabilityoutgoing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_response_measures`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_response_measures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_response_measures` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) DEFAULT NULL,
  `responseMeasure` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_response_measures` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_response_measures`
--

LOCK TABLES `modifiabilityreasoningframeworks_response_measures` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_response_measures` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_response_measures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modifiabilityreasoningframeworks_tactics`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_tactics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modifiabilityreasoningframeworks_tactics` (
  `type` varchar(30) NOT NULL DEFAULT '',
  `node_id` int(10) unsigned NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `sol_num` int(10) unsigned NOT NULL DEFAULT '0',
  `ToolName` varchar(100) NOT NULL,
  `loc_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`type`,`node_id`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modifiabilityreasoningframeworks_tactics`
--

LOCK TABLES `modifiabilityreasoningframeworks_tactics` WRITE;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_tactics` DISABLE KEYS */;
INSERT INTO `modifiabilityreasoningframeworks_tactics` VALUES ('LOC_ENC',684,2014,0,'ModelSolver',0),('LOC_ENC',695,2014,0,'ModelSolver',0),('LOC_ENC',700,2014,0,'ModelSolver',0),('LOC_ENC',703,2014,0,'ModelSolver',0);
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_tactics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_dependency`
--

DROP TABLE IF EXISTS `patterns_dependency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_dependency` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `pattern` varchar(20) DEFAULT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_dependency` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_dependency`
--

LOCK TABLES `patterns_dependency` WRITE;
/*!40000 ALTER TABLE `patterns_dependency` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_dependency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_interfacerealization`
--

DROP TABLE IF EXISTS `patterns_interfacerealization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_interfacerealization` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `pattern` varchar(20) DEFAULT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_interfacerealization` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_interfacerealization`
--

LOCK TABLES `patterns_interfacerealization` WRITE;
/*!40000 ALTER TABLE `patterns_interfacerealization` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_interfacerealization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_isarelation`
--

DROP TABLE IF EXISTS `patterns_isarelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_isarelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `pattern` varchar(20) DEFAULT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_isarelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_isarelation`
--

LOCK TABLES `patterns_isarelation` WRITE;
/*!40000 ALTER TABLE `patterns_isarelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_isarelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_pattern`
--

DROP TABLE IF EXISTS `patterns_pattern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_pattern` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `group` text,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_pattern` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_pattern`
--

LOCK TABLES `patterns_pattern` WRITE;
/*!40000 ALTER TABLE `patterns_pattern` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_pattern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_patternconnector`
--

DROP TABLE IF EXISTS `patterns_patternconnector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_patternconnector` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `type` text,
  `owner` varchar(20) DEFAULT NULL,
  `valueAbs` text,
  `valueFactor` text,
  `pattern` varchar(20) DEFAULT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patternconnector` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_patternconnector`
--

LOCK TABLES `patterns_patternconnector` WRITE;
/*!40000 ALTER TABLE `patterns_patternconnector` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patternconnector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_patterncontainer`
--

DROP TABLE IF EXISTS `patterns_patterncontainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_patterncontainer` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `pattern` varchar(20) DEFAULT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patterncontainer` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_patterncontainer`
--

LOCK TABLES `patterns_patterncontainer` WRITE;
/*!40000 ALTER TABLE `patterns_patterncontainer` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patterncontainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_patternelement`
--

DROP TABLE IF EXISTS `patterns_patternelement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_patternelement` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `pattern` varchar(20) DEFAULT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patternelement` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_patternelement`
--

LOCK TABLES `patterns_patternelement` WRITE;
/*!40000 ALTER TABLE `patterns_patternelement` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patternelement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_patternelementinterface`
--

DROP TABLE IF EXISTS `patterns_patternelementinterface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_patternelementinterface` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `pattern` varchar(20) DEFAULT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patternelementinterface` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_patternelementinterface`
--

LOCK TABLES `patterns_patternelementinterface` WRITE;
/*!40000 ALTER TABLE `patterns_patternelementinterface` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patternelementinterface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_patternitemproperty`
--

DROP TABLE IF EXISTS `patterns_patternitemproperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_patternitemproperty` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `owner` varchar(20) DEFAULT NULL,
  `valueAbs` text,
  `valueFactor` text,
  `pattern` varchar(20) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patternitemproperty` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_patternitemproperty`
--

LOCK TABLES `patterns_patternitemproperty` WRITE;
/*!40000 ALTER TABLE `patterns_patternitemproperty` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patternitemproperty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patterns_refinement`
--

DROP TABLE IF EXISTS `patterns_refinement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patterns_refinement` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `pattern` varchar(20) DEFAULT NULL,
  `name` text,
  `description` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_refinement` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patterns_refinement`
--

LOCK TABLES `patterns_refinement` WRITE;
/*!40000 ALTER TABLE `patterns_refinement` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_refinement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performancereasoningframeworks_p_executiontime`
--

DROP TABLE IF EXISTS `performancereasoningframeworks_p_executiontime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performancereasoningframeworks_p_executiontime` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_performancereasoningframeworks_p_executiontime` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performancereasoningframeworks_p_executiontime`
--

LOCK TABLES `performancereasoningframeworks_p_executiontime` WRITE;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_executiontime` DISABLE KEYS */;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_executiontime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performancereasoningframeworks_p_mutualexclusion`
--

DROP TABLE IF EXISTS `performancereasoningframeworks_p_mutualexclusion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performancereasoningframeworks_p_mutualexclusion` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_performancereasoningframeworks_p_mutualexclusion` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performancereasoningframeworks_p_mutualexclusion`
--

LOCK TABLES `performancereasoningframeworks_p_mutualexclusion` WRITE;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_mutualexclusion` DISABLE KEYS */;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_mutualexclusion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performancereasoningframeworks_p_sharedresourceasked`
--

DROP TABLE IF EXISTS `performancereasoningframeworks_p_sharedresourceasked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performancereasoningframeworks_p_sharedresourceasked` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_performancereasoningframeworks_p_sharedresourceasked` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performancereasoningframeworks_p_sharedresourceasked`
--

LOCK TABLES `performancereasoningframeworks_p_sharedresourceasked` WRITE;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_sharedresourceasked` DISABLE KEYS */;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_sharedresourceasked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planner_c_addfunction`
--

DROP TABLE IF EXISTS `planner_c_addfunction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planner_c_addfunction` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `functionID` text,
  `description` text,
  `state` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_planner_c_addfunction` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planner_c_addfunction`
--

LOCK TABLES `planner_c_addfunction` WRITE;
/*!40000 ALTER TABLE `planner_c_addfunction` DISABLE KEYS */;
/*!40000 ALTER TABLE `planner_c_addfunction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planner_c_addresponsibilityrelation`
--

DROP TABLE IF EXISTS `planner_c_addresponsibilityrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planner_c_addresponsibilityrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `type` text,
  `state` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_planner_c_addresponsibilityrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planner_c_addresponsibilityrelation`
--

LOCK TABLES `planner_c_addresponsibilityrelation` WRITE;
/*!40000 ALTER TABLE `planner_c_addresponsibilityrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `planner_c_addresponsibilityrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planner_c_addscenario`
--

DROP TABLE IF EXISTS `planner_c_addscenario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planner_c_addscenario` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `description` text,
  `quality` varchar(200) DEFAULT NULL,
  `stimulusText` text,
  `stimulusType` varchar(200) DEFAULT NULL,
  `stimulusUnit` varchar(200) DEFAULT NULL,
  `stimulusValue` double DEFAULT NULL,
  `sourceText` text,
  `sourceType` varchar(200) DEFAULT NULL,
  `sourceUnit` varchar(200) DEFAULT NULL,
  `sourceValue` double DEFAULT NULL,
  `artifactText` text,
  `artifactType` varchar(200) DEFAULT NULL,
  `artifactUnit` varchar(200) DEFAULT NULL,
  `artifactValue` double DEFAULT NULL,
  `environmentText` text,
  `environmentType` varchar(200) DEFAULT NULL,
  `environmentUnit` varchar(200) DEFAULT NULL,
  `environmentValue` double DEFAULT NULL,
  `responseText` text,
  `responseType` varchar(200) DEFAULT NULL,
  `responseUnit` varchar(200) DEFAULT NULL,
  `responseValue` double DEFAULT NULL,
  `measureText` text,
  `measureType` varchar(200) DEFAULT NULL,
  `measureUnit` varchar(200) DEFAULT NULL,
  `measureValue` double DEFAULT NULL,
  `state` text,
  `reasoningFramework` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_planner_c_addscenario` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planner_c_addscenario`
--

LOCK TABLES `planner_c_addscenario` WRITE;
/*!40000 ALTER TABLE `planner_c_addscenario` DISABLE KEYS */;
INSERT INTO `planner_c_addscenario` VALUES (81,13250,'<Fact-43>','The driver for a new external device has to be added by a developer within 10 days','Modifiability','Adding a new device',NULL,NULL,NULL,NULL,'developer',NULL,NULL,'The driver for a new external device',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'has to be added',NULL,NULL,NULL,'within 10 days','CostConstraint','days',10,'final',NULL),(110,15314,'<Fact-39>','The driver for a new external device has to be added by a developer within 10 days','Modifiability','Adding a new device',NULL,NULL,NULL,NULL,'developer',NULL,NULL,'The driver for a new external device',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'has to be added',NULL,NULL,NULL,'within 10 days','CostConstraint','days',10,'final',NULL);
/*!40000 ALTER TABLE `planner_c_addscenario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planner_c_addtranslationrelation`
--

DROP TABLE IF EXISTS `planner_c_addtranslationrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `planner_c_addtranslationrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  `type` text,
  `state` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_planner_c_addtranslationrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planner_c_addtranslationrelation`
--

LOCK TABLES `planner_c_addtranslationrelation` WRITE;
/*!40000 ALTER TABLE `planner_c_addtranslationrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `planner_c_addtranslationrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmamodel_p_latency`
--

DROP TABLE IF EXISTS `rmamodel_p_latency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmamodel_p_latency` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `utilization` text,
  `value` text,
  `time_blocked` text,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_rmamodel_p_latency` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmamodel_p_latency`
--

LOCK TABLES `rmamodel_p_latency` WRITE;
/*!40000 ALTER TABLE `rmamodel_p_latency` DISABLE KEYS */;
INSERT INTO `rmamodel_p_latency` VALUES (238,11152,'<Fact-1602>','0.010416666666666666','250.0','0.0','<Fact-1583>',NULL,NULL),(239,11152,'<Fact-1603>','0.010416666666666666','500.0','0.0','<Fact-1598>',NULL,NULL),(240,11152,'<Fact-1604>','0.010416666666666666','5050.0','0.0','<Fact-1586>',NULL,NULL);
/*!40000 ALTER TABLE `rmamodel_p_latency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmamodel_subtask`
--

DROP TABLE IF EXISTS `rmamodel_subtask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmamodel_subtask` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `task` varchar(20) DEFAULT NULL,
  `scenario` varchar(20) DEFAULT NULL,
  `responsibility` varchar(20) DEFAULT NULL,
  `MutualExclusion` varchar(200) DEFAULT NULL,
  `SharedResourceID` varchar(20) DEFAULT NULL,
  `taskType` varchar(200) DEFAULT NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `priority` text,
  `source` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_rmamodel_subtask` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=961 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmamodel_subtask`
--

LOCK TABLES `rmamodel_subtask` WRITE;
/*!40000 ALTER TABLE `rmamodel_subtask` DISABLE KEYS */;
INSERT INTO `rmamodel_subtask` VALUES (949,11152,'<Fact-1585>','SubTask-UnitOfConcurrency-Manage External Devices','<Fact-1583>','<Fact-46>','<Fact-65>','FALSE','<Fact-0>',NULL,'1000.0','250.0',NULL,'498',NULL),(950,11152,'<Fact-1588>','SubTask-UnitOfConcurrency-Create user Profile','<Fact-1586>',NULL,'<Fact-56>','FALSE','<Fact-0>',NULL,'500000','150.0',NULL,'500',NULL),(951,11152,'<Fact-1589>','SubTask-UnitOfConcurrency-Attach to Model','<Fact-1586>',NULL,'<Fact-58>','FALSE','<Fact-0>',NULL,'500000','100.0',NULL,'500',NULL),(952,11152,'<Fact-1590>','SubTask-UnitOfConcurrency-Modify user Profile','<Fact-1586>',NULL,'<Fact-57>','FALSE','<Fact-0>',NULL,'500000','150.0',NULL,'500',NULL),(953,11152,'<Fact-1591>','SubTask-UnitOfConcurrency-Handle user Interactions','<Fact-1586>',NULL,'<Fact-60>','FALSE','<Fact-0>',NULL,'500000','500.0',NULL,'500',NULL),(954,11152,'<Fact-1592>','SubTask-UnitOfConcurrency-Show Itinerary','<Fact-1586>',NULL,'<Fact-54>','FALSE','<Fact-0>',NULL,'500000','450.0',NULL,'500',NULL),(955,11152,'<Fact-1593>','SubTask-UnitOfConcurrency-Locate Service','<Fact-1586>',NULL,'<Fact-63>','FALSE','<Fact-0>',NULL,'500000','1000.0',NULL,'500',NULL),(956,11152,'<Fact-1594>','SubTask-UnitOfConcurrency-Register Views','<Fact-1586>',NULL,'<Fact-59>','FALSE','<Fact-0>',NULL,'500000','100.0',NULL,'500',NULL),(957,11152,'<Fact-1595>','SubTask-UnitOfConcurrency-Manage Itinerary','<Fact-1586>',NULL,'<Fact-64>','FALSE','<Fact-0>',NULL,'500000','800.0',NULL,'500',NULL),(958,11152,'<Fact-1596>','SubTask-UnitOfConcurrency-Query for Data','<Fact-1586>',NULL,'<Fact-62>','FALSE','<Fact-0>',NULL,'500000','700.0',NULL,'500',NULL),(959,11152,'<Fact-1597>','SubTask-UnitOfConcurrency-Save Data','<Fact-1586>',NULL,'<Fact-61>','FALSE','<Fact-0>',NULL,'500000','600.0',NULL,'500',NULL),(960,11152,'<Fact-1600>','SubTask-UnitOfConcurrency-Manage External Devices','<Fact-1598>','<Fact-45>','<Fact-65>','FALSE','<Fact-0>',NULL,'5000.0','250.0',NULL,'499',NULL);
/*!40000 ALTER TABLE `rmamodel_subtask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmamodel_task`
--

DROP TABLE IF EXISTS `rmamodel_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmamodel_task` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `scenario` varchar(20) DEFAULT NULL,
  `stimulusType` varchar(200) DEFAULT NULL,
  `stimulusValue` text,
  `taskType` varchar(200) DEFAULT NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `time_blocked` text,
  `priority` text,
  `source` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_rmamodel_task` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=377 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmamodel_task`
--

LOCK TABLES `rmamodel_task` WRITE;
/*!40000 ALTER TABLE `rmamodel_task` DISABLE KEYS */;
INSERT INTO `rmamodel_task` VALUES (136,9949,'<Fact-2512>','Task-Thread-Background',NULL,'periodic','5000','HardDeadline','5000','7195.0',NULL,NULL,'500',NULL),(374,11152,'<Fact-1583>','Task-Thread-A view wishes to attach to the model under normal conditions and do so in under 1 second.','<Fact-46>','sporadic','120000.0','HardDeadline','1000.0','250.0','250.0','0.0','498',NULL),(375,11152,'<Fact-1586>','Task-Thread-Background',NULL,'periodic','500000','HardDeadline','500000','4550.0',NULL,NULL,'500',NULL),(376,11152,'<Fact-1598>','Task-Thread-The system has to manage the external devices under normal load and handle the operations in under 5 seconds.','<Fact-45>','periodic','30000.0','HardDeadline','5000.0','250.0','500.0','0.0','499',NULL);
/*!40000 ALTER TABLE `rmamodel_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmaperformancerf_p_executiontime`
--

DROP TABLE IF EXISTS `rmaperformancerf_p_executiontime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmaperformancerf_p_executiontime` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_p_executiontime` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmaperformancerf_p_executiontime`
--

LOCK TABLES `rmaperformancerf_p_executiontime` WRITE;
/*!40000 ALTER TABLE `rmaperformancerf_p_executiontime` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_p_executiontime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmaperformancerf_p_shared`
--

DROP TABLE IF EXISTS `rmaperformancerf_p_shared`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmaperformancerf_p_shared` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_p_shared` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmaperformancerf_p_shared`
--

LOCK TABLES `rmaperformancerf_p_shared` WRITE;
/*!40000 ALTER TABLE `rmaperformancerf_p_shared` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_p_shared` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmaperformancerf_p_taskpriority`
--

DROP TABLE IF EXISTS `rmaperformancerf_p_taskpriority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmaperformancerf_p_taskpriority` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` int(10) DEFAULT NULL,
  `owner` varchar(20) DEFAULT NULL,
  `source` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_p_taskpriority` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmaperformancerf_p_taskpriority`
--

LOCK TABLES `rmaperformancerf_p_taskpriority` WRITE;
/*!40000 ALTER TABLE `rmaperformancerf_p_taskpriority` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_p_taskpriority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmaperformancerf_resource`
--

DROP TABLE IF EXISTS `rmaperformancerf_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmaperformancerf_resource` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `name` text,
  `source` text,
  `status` int(10) DEFAULT NULL,
  `shared` text,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_resource` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=287 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmaperformancerf_resource`
--

LOCK TABLES `rmaperformancerf_resource` WRITE;
/*!40000 ALTER TABLE `rmaperformancerf_resource` DISABLE KEYS */;
INSERT INTO `rmaperformancerf_resource` VALUES (276,12805,'<Fact-151>',NULL,'(R) Query for Data','ArchE',0,NULL),(277,12805,'<Fact-152>',NULL,'(R) Register Views','ArchE',0,NULL),(278,12805,'<Fact-153>',NULL,'(R) Manage Itinerary','ArchE',0,NULL),(279,12805,'<Fact-154>',NULL,'(R) Modify user Profile','ArchE',0,NULL),(280,12805,'<Fact-155>',NULL,'(R) Handle user Interactions','ArchE',0,NULL),(281,12805,'<Fact-156>',NULL,'(R) Save Data','ArchE',0,NULL),(282,12805,'<Fact-157>',NULL,'(R) Show Itinerary','ArchE',0,NULL),(283,12805,'<Fact-158>',NULL,'(R) Locate Service','ArchE',0,NULL),(284,12805,'<Fact-159>',NULL,'(R) Manage External Devices','ArchE',0,NULL),(285,12805,'<Fact-160>',NULL,'(R) Attach to Model','ArchE',0,NULL),(286,12805,'<Fact-161>',NULL,'(R) Create user Profile','ArchE',0,NULL);
/*!40000 ALTER TABLE `rmaperformancerf_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmaperformancerf_resourceusagerelation`
--

DROP TABLE IF EXISTS `rmaperformancerf_resourceusagerelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmaperformancerf_resourceusagerelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `source` text,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_resourceusagerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=370 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmaperformancerf_resourceusagerelation`
--

LOCK TABLES `rmaperformancerf_resourceusagerelation` WRITE;
/*!40000 ALTER TABLE `rmaperformancerf_resourceusagerelation` DISABLE KEYS */;
INSERT INTO `rmaperformancerf_resourceusagerelation` VALUES (358,12805,'<Fact-162>',NULL,'ArchE','<Fact-150>','<Fact-151>'),(359,12805,'<Fact-164>',NULL,'ArchE','<Fact-150>','<Fact-153>'),(360,12805,'<Fact-165>',NULL,'ArchE','<Fact-150>','<Fact-154>'),(361,12805,'<Fact-166>',NULL,'ArchE','<Fact-150>','<Fact-155>'),(362,12805,'<Fact-167>',NULL,'ArchE','<Fact-150>','<Fact-156>'),(363,12805,'<Fact-168>',NULL,'ArchE','<Fact-150>','<Fact-157>'),(364,12805,'<Fact-169>',NULL,'ArchE','<Fact-150>','<Fact-158>'),(365,12805,'<Fact-170>',NULL,'ArchE','<Fact-150>','<Fact-159>'),(366,12805,'<Fact-172>',NULL,'ArchE','<Fact-150>','<Fact-161>'),(367,12805,'<Fact-210>',NULL,'ArchE','<Fact-149>','<Fact-152>'),(368,12805,'<Fact-247>',NULL,'ArchE','<Fact-149>','<Fact-160>'),(369,12805,'<Fact-323>',NULL,'ArchE','<Fact-285>','<Fact-152>');
/*!40000 ALTER TABLE `rmaperformancerf_resourceusagerelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmaperformancerf_thread`
--

DROP TABLE IF EXISTS `rmaperformancerf_thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmaperformancerf_thread` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `name` text,
  `period` double DEFAULT NULL,
  `source` text,
  `status` int(10) DEFAULT NULL,
  `scenario` text,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_thread` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmaperformancerf_thread`
--

LOCK TABLES `rmaperformancerf_thread` WRITE;
/*!40000 ALTER TABLE `rmaperformancerf_thread` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmaperformancerf_threadtouocrelation`
--

DROP TABLE IF EXISTS `rmaperformancerf_threadtouocrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmaperformancerf_threadtouocrelation` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `source` text,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_threadtouocrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmaperformancerf_threadtouocrelation`
--

LOCK TABLES `rmaperformancerf_threadtouocrelation` WRITE;
/*!40000 ALTER TABLE `rmaperformancerf_threadtouocrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_threadtouocrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rmaperformancerf_unitofconcurrency`
--

DROP TABLE IF EXISTS `rmaperformancerf_unitofconcurrency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rmaperformancerf_unitofconcurrency` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) DEFAULT NULL,
  `name` text,
  `executionTime` double DEFAULT NULL,
  `source` text,
  `status` int(10) DEFAULT NULL,
  `responsibility` text,
  PRIMARY KEY (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_unitofconcurrency` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rmaperformancerf_unitofconcurrency`
--

LOCK TABLES `rmaperformancerf_unitofconcurrency` WRITE;
/*!40000 ALTER TABLE `rmaperformancerf_unitofconcurrency` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_unitofconcurrency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seeker_evaluationresult`
--

DROP TABLE IF EXISTS `seeker_evaluationresult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seeker_evaluationresult` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) DEFAULT NULL,
  `tacticScenario` varchar(20) DEFAULT NULL,
  `reasoningFramework` varchar(200) DEFAULT NULL,
  `tactic` varchar(200) DEFAULT NULL,
  `tacticDescription` text,
  `result` double DEFAULT NULL,
  `utility` double DEFAULT NULL,
  `change` double DEFAULT NULL,
  `relevance` double DEFAULT NULL,
  `tacticId` varchar(200) DEFAULT NULL,
  `index` int(10) DEFAULT NULL,
  `nresult` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_seeker_evaluationresult` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seeker_evaluationresult`
--

LOCK TABLES `seeker_evaluationresult` WRITE;
/*!40000 ALTER TABLE `seeker_evaluationresult` DISABLE KEYS */;
/*!40000 ALTER TABLE `seeker_evaluationresult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tactics`
--

DROP TABLE IF EXISTS `tactics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tactics` (
  `type` varchar(30) NOT NULL DEFAULT '',
  `node_id` int(10) unsigned NOT NULL,
  `name` int(10) unsigned DEFAULT NULL,
  `version` int(10) unsigned NOT NULL DEFAULT '0',
  `level` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`type`,`node_id`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tactics`
--

LOCK TABLES `tactics` WRITE;
/*!40000 ALTER TABLE `tactics` DISABLE KEYS */;
/*!40000 ALTER TABLE `tactics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_interface`
--

DROP TABLE IF EXISTS `test_interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_interface` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `moduleName` text,
  `costOfChange` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_interface` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_interface`
--

LOCK TABLES `test_interface` WRITE;
/*!40000 ALTER TABLE `test_interface` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_interface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_leave_dependencies`
--

DROP TABLE IF EXISTS `test_leave_dependencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_leave_dependencies` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_leave_dependencies` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_leave_dependencies`
--

LOCK TABLES `test_leave_dependencies` WRITE;
/*!40000 ALTER TABLE `test_leave_dependencies` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_leave_dependencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_model_arcs`
--

DROP TABLE IF EXISTS `test_model_arcs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_model_arcs` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  `probability` double DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_model_arcs` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_model_arcs`
--

LOCK TABLES `test_model_arcs` WRITE;
/*!40000 ALTER TABLE `test_model_arcs` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_model_arcs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_model_nodes`
--

DROP TABLE IF EXISTS `test_model_nodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_model_nodes` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `node` text,
  `costOfChange` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_model_nodes` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_model_nodes`
--

LOCK TABLES `test_model_nodes` WRITE;
/*!40000 ALTER TABLE `test_model_nodes` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_model_nodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_model_nodes_affected`
--

DROP TABLE IF EXISTS `test_model_nodes_affected`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_model_nodes_affected` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_model_nodes_affected` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_model_nodes_affected`
--

LOCK TABLES `test_model_nodes_affected` WRITE;
/*!40000 ALTER TABLE `test_model_nodes_affected` DISABLE KEYS */;
INSERT INTO `test_model_nodes_affected` VALUES (1,2118,'T-8256483','Modifiability 1','(M) C'),(2,2118,'T-08935487','Modifiability 1','(M) B'),(3,2118,'T-863758','Modifiability 2','(M) E'),(4,2118,'T-9782544648','Modifiability 3','(M) EA'),(6,3903,'T-8256483','Modifiability 1','(M) C'),(7,3903,'T-08935487','Modifiability 1','(M) B'),(8,3903,'T-863758','Modifiability 2','(M) E'),(9,3903,'T-9782544648','Modifiability 3','(M) EA'),(11,2607,'T-8256483','Modifiability 1','(M) C'),(12,2607,'T-08935487','Modifiability 1','(M) B'),(13,2607,'T-863758','Modifiability 2','(M) New Name'),(14,2607,'T-9782544648','Modifiability 3','(M) EA'),(16,2195,'T-8256483','Modifiability 1','(M) C'),(17,2195,'T-08935487','Modifiability 1','(M) B'),(18,2195,'T-863758','Modifiability 2','(M) EA'),(19,2195,'T-9782544648','Modifiability 3','(M) EA'),(20,2195,'T-9887989','Modifiability 2','(M) EBA'),(21,2195,'T-35284538732','Modifiability 2','(M) EBB'),(22,4478,'T-8256483','Modifiability 1','(M) C'),(23,4478,'T-08935487','Modifiability 1','(M) B'),(24,4478,'T-863758','Modifiability 2','(M) EA'),(25,4478,'T-9782544648','Modifiability 3','(M) EA'),(26,4478,'T-9887989','Modifiability 2','(M) EBA'),(27,4478,'T-35284538732','Modifiability 2','(M) EBB'),(28,4641,'T-8256483','Modifiability 1','(M) C'),(29,4641,'T-08935487','Modifiability 1','(M) B'),(30,4641,'T-863758','Modifiability 2','(M) New Name'),(31,4641,'T-9782544648','Modifiability 3','(M) EA'),(32,4667,'T-8256483','Modifiability 1','(M) C'),(33,4667,'T-08935487','Modifiability 1','(M) B'),(34,4667,'T-863758','Modifiability 2','(M) New Name'),(35,4667,'T-9782544648','Modifiability 3','(M) EA');
/*!40000 ALTER TABLE `test_model_nodes_affected` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_module`
--

DROP TABLE IF EXISTS `test_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_module` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `costOfChange` double DEFAULT NULL,
  `name` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_module` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_module`
--

LOCK TABLES `test_module` WRITE;
/*!40000 ALTER TABLE `test_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_module_dependencies`
--

DROP TABLE IF EXISTS `test_module_dependencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_module_dependencies` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  `childType` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_module_dependencies` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_module_dependencies`
--

LOCK TABLES `test_module_dependencies` WRITE;
/*!40000 ALTER TABLE `test_module_dependencies` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_module_dependencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_module_responsibilities`
--

DROP TABLE IF EXISTS `test_module_responsibilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_module_responsibilities` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `module` text,
  `responsibility` text,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_module_responsibilities` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_module_responsibilities`
--

LOCK TABLES `test_module_responsibilities` WRITE;
/*!40000 ALTER TABLE `test_module_responsibilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_module_responsibilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_responsibilities`
--

DROP TABLE IF EXISTS `test_responsibilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_responsibilities` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_responsibilities` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_responsibilities`
--

LOCK TABLES `test_responsibilities` WRITE;
/*!40000 ALTER TABLE `test_responsibilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_responsibilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_responsibility_refinement`
--

DROP TABLE IF EXISTS `test_responsibility_refinement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_responsibility_refinement` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_responsibility_refinement` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_responsibility_refinement`
--

LOCK TABLES `test_responsibility_refinement` WRITE;
/*!40000 ALTER TABLE `test_responsibility_refinement` DISABLE KEYS */;
INSERT INTO `test_responsibility_refinement` VALUES (1,2118,'T-53274836','A','AA'),(2,2118,'T-2656478','A','AB'),(3,2118,'T-36384823','A','Ac'),(4,2118,'T-238764237','AA','AAA'),(5,2118,'T-8783276372','AA','AAB'),(6,2118,'T-287328787','Ac','ACA'),(7,2118,'T-238742987','Ac','ACB'),(8,2118,'T-87676526521','E','EA'),(9,2118,'T-537483264','E','EB'),(10,2118,'T-7676767123','EB','EBA'),(11,2118,'T-236276476','EB','EBB'),(56,2195,'T-53274836','A','AA'),(57,2195,'T-2656478','A','AB'),(58,2195,'T-36384823','A','Ac'),(59,2195,'T-238764237','AA','AAA'),(60,2195,'T-8783276372','AA','AAB'),(61,2195,'T-287328787','Ac','ACA'),(62,2195,'T-238742987','Ac','ACB'),(63,2195,'T-87676526521','E','EA'),(64,2195,'T-537483264','E','EB'),(65,2195,'T-7676767123','EB','EBA'),(66,2195,'T-236276476','EB','EBB'),(67,2607,'T-53274836','A','AA'),(68,2607,'T-2656478','A','AB'),(69,2607,'T-36384823','A','Ac'),(70,2607,'T-238764237','AA','AAA'),(71,2607,'T-8783276372','AA','AAB'),(72,2607,'T-287328787','Ac','ACA'),(73,2607,'T-238742987','Ac','ACB'),(74,2607,'T-87676526521','E','EA'),(75,2607,'T-537483264','E','EB'),(76,2607,'T-7676767123','EB','EBA'),(77,2607,'T-236276476','EB','EBB'),(78,2607,'T-7645276452','EA','EA - remaining'),(79,2607,'T-17632447825','EA','EA - extracted'),(80,2607,'T-7284627839','EBA','EBA - remaining'),(81,2607,'T-1653284652','EBA','EBA - extracted'),(82,2607,'T-4283621454','EBB','EBB - remaining'),(83,2607,'T-2648265485','EBB','EBB - extracted'),(84,3903,'T-53274836','A','AA'),(85,3903,'T-2656478','A','AB'),(86,3903,'T-36384823','A','Ac'),(87,3903,'T-238764237','AA','AAA'),(88,3903,'T-8783276372','AA','AAB'),(89,3903,'T-287328787','Ac','ACA'),(90,3903,'T-238742987','Ac','ACB'),(91,3903,'T-87676526521','E','EA'),(92,3903,'T-537483264','E','EB'),(93,3903,'T-7676767123','EB','EBA'),(94,3903,'T-236276476','EB','EBB'),(95,3903,'T-7645276452','EA','EA - remaining'),(96,3903,'T-17632447825','EA','EA - extracted'),(97,3903,'T-7284627839','EBA','EBA - remaining'),(98,3903,'T-1653284652','EBA','EBA - extracted'),(99,3903,'T-4283621454','EBB','EBB - remaining'),(100,3903,'T-2648265485','EBB','EBB - extracted'),(118,3903,'T-08658365','B','B - remaining'),(119,3903,'T-983554839','B','B - extracted'),(120,3903,'T-27846583','C','C - extracted'),(121,3903,'T-9264859','C','C - remaining'),(122,4478,'T-53274836','A','AA'),(123,4478,'T-2656478','A','AB'),(124,4478,'T-36384823','A','Ac'),(125,4478,'T-238764237','AA','AAA'),(126,4478,'T-8783276372','AA','AAB'),(127,4478,'T-287328787','Ac','ACA'),(128,4478,'T-238742987','Ac','ACB'),(129,4478,'T-87676526521','E','EA'),(130,4478,'T-537483264','E','EB'),(131,4478,'T-7676767123','EB','EBA'),(132,4478,'T-236276476','EB','EBB'),(133,4641,'T-53274836','A','AA'),(134,4641,'T-2656478','A','AB'),(135,4641,'T-36384823','A','Ac'),(136,4641,'T-238764237','AA','AAA'),(137,4641,'T-8783276372','AA','AAB'),(138,4641,'T-287328787','Ac','ACA'),(139,4641,'T-238742987','Ac','ACB'),(140,4641,'T-87676526521','E','EA'),(141,4641,'T-537483264','E','EB'),(142,4641,'T-7676767123','EB','EBA'),(143,4641,'T-236276476','EB','EBB'),(144,4641,'T-7645276452','EA','EA - remaining'),(145,4641,'T-17632447825','EA','EA - extracted'),(146,4641,'T-7284627839','EBA','EBA - remaining'),(147,4641,'T-1653284652','EBA','EBA - extracted'),(148,4641,'T-4283621454','EBB','EBB - remaining'),(149,4641,'T-2648265485','EBB','EBB - extracted'),(150,4667,'T-53274836','A','AA'),(151,4667,'T-2656478','A','AB'),(152,4667,'T-36384823','A','Ac'),(153,4667,'T-238764237','AA','AAA'),(154,4667,'T-8783276372','AA','AAB'),(155,4667,'T-287328787','Ac','ACA'),(156,4667,'T-238742987','Ac','ACB'),(157,4667,'T-87676526521','E','EA'),(158,4667,'T-537483264','E','EB'),(159,4667,'T-7676767123','EB','EBA'),(160,4667,'T-236276476','EB','EBB'),(161,4667,'T-7645276452','EA','EA - remaining'),(162,4667,'T-17632447825','EA','EA - extracted'),(163,4667,'T-7284627839','EBA','EBA - remaining'),(164,4667,'T-1653284652','EBA','EBA - extracted'),(165,4667,'T-4283621454','EBB','EBB - remaining'),(166,4667,'T-2648265485','EBB','EBB - extracted'),(167,4667,'T-882637256347','C','C - extracted'),(168,4667,'T-11253652762','C','C - remaining'),(169,4667,'T-217253426','B','B - extracted'),(170,4667,'T-2263542363','B','B - remaining');
/*!40000 ALTER TABLE `test_responsibility_refinement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_factset`
--

DROP TABLE IF EXISTS `v_factset`;
/*!50001 DROP VIEW IF EXISTS `v_factset`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `v_factset` (
  `factType` tinyint NOT NULL,
  `setName` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `variabilityreasoningframework_variabilitymechanismresponsibility`
--

DROP TABLE IF EXISTS `variabilityreasoningframework_variabilitymechanismresponsibility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `variabilityreasoningframework_variabilitymechanismresponsibility` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) DEFAULT NULL,
  `parent` varchar(20) DEFAULT NULL,
  `child` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `variabilityrf_variabilitymechanismresponsibility_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variabilityreasoningframework_variabilitymechanismresponsibility`
--

LOCK TABLES `variabilityreasoningframework_variabilitymechanismresponsibility` WRITE;
/*!40000 ALTER TABLE `variabilityreasoningframework_variabilitymechanismresponsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `variabilityreasoningframework_variabilitymechanismresponsibility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variabilityreasoningframework_vpconfiguration`
--

DROP TABLE IF EXISTS `variabilityreasoningframework_vpconfiguration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `variabilityreasoningframework_vpconfiguration` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `inputs` text,
  PRIMARY KEY (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `variabilityreasoningframework_vpconfiguration_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variabilityreasoningframework_vpconfiguration`
--

LOCK TABLES `variabilityreasoningframework_vpconfiguration` WRITE;
/*!40000 ALTER TABLE `variabilityreasoningframework_vpconfiguration` DISABLE KEYS */;
/*!40000 ALTER TABLE `variabilityreasoningframework_vpconfiguration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `leave_responsibilities`
--

/*!50001 DROP TABLE IF EXISTS `leave_responsibilities`*/;
/*!50001 DROP VIEW IF EXISTS `leave_responsibilities`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `leave_responsibilities` AS select `res`.`uid` AS `uid`,`res`.`version` AS `version`,`res`.`fact-id` AS `fact-id`,`res`.`id` AS `id`,`res`.`name` AS `name`,`res`.`description` AS `description`,`res`.`source` AS `source`,`v`.`version_name` AS `version_name` from ((`main_responsibilities` `res` join `__versions__` `v` on((`v`.`ID` = `res`.`version`))) left join `main_responsibilityrefinementrelation` `d` on(((`res`.`version` = `d`.`version`) and (`res`.`fact-id` = `d`.`parent`)))) where isnull(`d`.`parent`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_factset`
--

/*!50001 DROP TABLE IF EXISTS `v_factset`*/;
/*!50001 DROP VIEW IF EXISTS `v_factset`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_factset` AS select `__factsets__`.`factType` AS `factType`,`__factsets__`.`setName` AS `setName` from `__factsets__` order by `__factsets__`.`group`,`__factsets__`.`ID` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-15 11:17:21
