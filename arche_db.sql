-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.18-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema arche
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ arche;
USE arche;

--
-- Table structure for table `arche`.`__factsets__`
--

DROP TABLE IF EXISTS `__factsets__`;
CREATE TABLE `__factsets__` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `setName` varchar(45) NOT NULL default '',
  `factType` varchar(255) NOT NULL default '',
  `group` tinyint(3) unsigned NOT NULL default '1',
  PRIMARY KEY  (`ID`),
  KEY `setName` (`setName`),
  KEY `sort` (`group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`__factsets__`
--

/*!40000 ALTER TABLE `__factsets__` DISABLE KEYS */;
INSERT INTO `__factsets__` (`ID`,`setName`,`factType`,`group`) VALUES 
 (3,'Project','MAIN::Scenarios',1),
 (4,'Project','MAIN::Responsibilities',1),
 (5,'Project','MAIN::Function',1),
 (6,'Project','MAIN::SequenceRelation',2),
 (7,'Project','MAIN::TranslationRelation',2),
 (8,'Project','MAIN::FunctionRefinementRelation',2),
 (9,'Project','MAIN::ResponsibilityToResponsibilityRelation',2),
 (10,'Project','MAIN::ResponsibilityRefinementRelation',2),
 (11,'Project','MAIN::ScenarioRefinementRelation',2),
 (12,'Project','Planner::C_AddTranslationRelation',2),
 (13,'Project','Planner::C_AddResponsibilityRelation',2),
 (14,'Project','Planner::C_AddScenario',1),
 (15,'Project','Planner::C_AddFunction',1),
 (18,'Project','Design::VariationPoint',3),
 (19,'Project','Design::ModuleInterface',3),
 (20,'Project','Design::Module',3),
 (21,'Project','Design::Wrapper',3),
 (22,'Project','Design::Responsibility',3),
 (23,'Project','Design::SharedResource',3),
 (24,'Project','Design::VPInputValue',3),
 (25,'Project','Design::ResponsibilityToModuleRelation',4),
 (26,'Project','Design::ModuleRefinementRelation',4);
INSERT INTO `__factsets__` (`ID`,`setName`,`factType`,`group`) VALUES 
 (27,'Project','Design::ModuleDependencyRelation',4),
 (28,'Project','Design::RealizeRelation',4),
 (29,'Project','Design::SharedResourceRelation',4),
 (62,'Design','MAIN::Scenarios',1),
 (63,'Design','MAIN::Responsibilities',1),
 (64,'Design','MAIN::TranslationRelation',2),
 (65,'Design','MAIN::SequenceRelation',2),
 (68,'Design','MAIN::ResponsibilityToResponsibilityRelation',2),
 (69,'Design','MAIN::ResponsibilityRefinementRelation',2),
 (77,'Design','Design::VariationPoint',3),
 (78,'Design','Design::ModuleInterface',3),
 (79,'Design','Design::Module',3),
 (80,'Design','Design::Wrapper',3),
 (82,'Design','Design::SharedResource',3),
 (83,'Design','Design::VPInputValue',3),
 (84,'Design','Design::ResponsibilityToModuleRelation',4),
 (85,'Design','Design::ModuleRefinementRelation',4),
 (86,'Design','Design::ModuleDependencyRelation',4),
 (87,'Design','Design::RealizeRelation',4),
 (88,'Design','Design::SharedResourceRelation',4),
 (105,'Project','Design::Process',3);
INSERT INTO `__factsets__` (`ID`,`setName`,`factType`,`group`) VALUES 
 (106,'Project','Design::Thread',3),
 (107,'Project','Design::UnitOfConcurrency',3),
 (108,'Design','Design::Process',3),
 (109,'Design','Design::Thread',3),
 (110,'Design','Design::UnitOfConcurrency',3),
 (111,'EvaluationMatrix','Seeker::EvaluationResult',6),
 (112,'Project','ChangeImpactModifiabilityRF::ResponsibilityDependencyRelation',2),
 (113,'Design','ChangeImpactModifiabilityRF::ResponsibilityDependencyRelation',2),
 (114,'Project','ChangeImpactModifiabilityRF::P_CostOfChange',5),
 (115,'Design','ChangeImpactModifiabilityRF::P_CostOfChange',5),
 (116,'Project','ChangeImpactModifiabilityRF::P_BooleanTest',5),
 (117,'Design','ChangeImpactModifiabilityRF::P_BooleanTest',5),
 (118,'Project','ChangeImpactModifiabilityRF::P_ProbabilityIncoming',5),
 (119,'Design','ChangeImpactModifiabilityRF::P_ProbabilityIncoming',5),
 (120,'Project','ChangeImpactModifiabilityRF::P_ProbabilityOutgoing',5),
 (121,'Design','ChangeImpactModifiabilityRF::P_ProbabilityOutgoing',5),
 (122,'Project','ChangeImpactModifiabilityRF::Node_Responsibility',3);
INSERT INTO `__factsets__` (`ID`,`setName`,`factType`,`group`) VALUES 
 (123,'Design','ChangeImpactModifiabilityRF::Node_Responsibility',3),
 (124,'Project','ChangeImpactModifiabilityRF::Arc_Relation',4),
 (125,'Design','ChangeImpactModifiabilityRF::Arc_Relation',4),
 (126,'Project','ChangeImpactModifiabilityRF::ResponsibilityToModuleRelation',4),
 (127,'Design','ChangeImpactModifiabilityRF::ResponsibilityToModuleRelation',4),
 (128,'Project','ICMPerformanceRF::ResponsibilityReactionRelation',2),
 (129,'Design','ICMPerformanceRF::ResponsibilityReactionRelation',2),
 (130,'Project','ICMPerformanceRF::P_ExecutionTime',5),
 (131,'Design','ICMPerformanceRF::P_ExecutionTime',5),
 (132,'Project','ICMPerformanceRF::AssemblyInstance',3),
 (133,'Design','ICMPerformanceRF::AssemblyInstance',3),
 (134,'Project','MyFirstRF::TestResponsibilityRelation',2),
 (135,'Design','MyFirstRF::TestResponsibilityRelation',2),
 (136,'Project','MyFirstRF::P_TestResponsibilityParameter',5),
 (137,'Design','MyFirstRF::P_TestResponsibilityParameter',5),
 (138,'Project','MyFirstRF::P_TestRelationParameter',5);
INSERT INTO `__factsets__` (`ID`,`setName`,`factType`,`group`) VALUES 
 (139,'Design','MyFirstRF::P_TestRelationParameter',5);
/*!40000 ALTER TABLE `__factsets__` ENABLE KEYS */;


--
-- Table structure for table `arche`.`__mod_solver_interface__`
--

DROP TABLE IF EXISTS `__mod_solver_interface__`;
CREATE TABLE `__mod_solver_interface__` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `FactSet` varchar(100) NOT NULL default '',
  `Version` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`__mod_solver_interface__`
--

/*!40000 ALTER TABLE `__mod_solver_interface__` DISABLE KEYS */;
INSERT INTO `__mod_solver_interface__` (`ID`,`FactSet`,`Version`) VALUES 
 (1,'ModifiabilityModel','ModModel');
/*!40000 ALTER TABLE `__mod_solver_interface__` ENABLE KEYS */;


--
-- Table structure for table `arche`.`__tool_command_interface__`
--

DROP TABLE IF EXISTS `__tool_command_interface__`;
CREATE TABLE `__tool_command_interface__` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `ToolName` varchar(100) NOT NULL default '',
  `Direction` varchar(3) NOT NULL default '' COMMENT 'Content=out: Command is to the tool, content=in: Command to ArchE',
  `FactSet` varchar(100) NOT NULL default '',
  `Version` varchar(255) NOT NULL default '',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`__tool_command_interface__`
--

/*!40000 ALTER TABLE `__tool_command_interface__` DISABLE KEYS */;
INSERT INTO `__tool_command_interface__` (`ID`,`ToolName`,`Direction`,`FactSet`,`Version`) VALUES 
 (1,'ModelSolver','out','ModifiabilityModel','ModModel-sagar'),
 (2,'AADLConverter','out','Design','Architecture1'),
 (46,'ModelSolver','in','ModifiabilityResult','ModResult-sagar');
/*!40000 ALTER TABLE `__tool_command_interface__` ENABLE KEYS */;


--
-- Table structure for table `arche`.`__versions__`
--

DROP TABLE IF EXISTS `__versions__`;
CREATE TABLE `__versions__` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `version_name` varchar(200) NOT NULL default '',
  `date_created` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `last_read` timestamp NOT NULL default '0000-00-00 00:00:00',
  `storage_type` varchar(45) NOT NULL default '',
  `factSet` varchar(45) NOT NULL default '',
  `parent` int(10) unsigned default NULL,
  `max_fact_id` int(10) unsigned default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`__versions__`
--

/*!40000 ALTER TABLE `__versions__` DISABLE KEYS */;
INSERT INTO `__versions__` (`ID`,`version_name`,`date_created`,`last_read`,`storage_type`,`factSet`,`parent`,`max_fact_id`) VALUES 
 (12029,'RMAModel1','2007-10-09 14:45:48','0000-00-00 00:00:00','temp','RMAModel',NULL,NULL),
 (12041,'UserModModel','2007-10-09 15:16:53','0000-00-00 00:00:00','temp','ModifiabilityModel',NULL,NULL),
 (13250,'test','2007-12-14 13:57:47','0000-00-00 00:00:00','project','Project',NULL,NULL),
 (15314,'ctas_sample','2008-06-13 08:49:23','0000-00-00 00:00:00','project','Project',NULL,NULL),
 (15649,'Architecture1','2008-08-08 10:37:12','0000-00-00 00:00:00','temp','Design',15314,5242);
/*!40000 ALTER TABLE `__versions__` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_arc_relation`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_arc_relation`;
CREATE TABLE `changeimpactmodifiabilityrf_arc_relation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `source` varchar(20) default NULL,
  `target` varchar(20) default NULL,
  `probability` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_arc_relation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_arc_relation`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_arc_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_arc_relation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_module`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_module`;
CREATE TABLE `changeimpactmodifiabilityrf_module` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `costOfChange` double default NULL,
  `complexity` double default NULL,
  `source` text,
  `status` int(10) default NULL,
  `id` int(10) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_module` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_module`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_module` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_moduledependencyrelation`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_moduledependencyrelation`;
CREATE TABLE `changeimpactmodifiabilityrf_moduledependencyrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `source` text,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `probability` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_moduledependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_moduledependencyrelation`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_moduledependencyrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_moduledependencyrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_node_responsibility`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_node_responsibility`;
CREATE TABLE `changeimpactmodifiabilityrf_node_responsibility` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `name` text,
  `cost` double default NULL,
  `cumulativeprob` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_node_responsibility` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_node_responsibility`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_node_responsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_node_responsibility` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_p_booleantest`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_p_booleantest`;
CREATE TABLE `changeimpactmodifiabilityrf_p_booleantest` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_p_booleantest` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_p_booleantest`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_booleantest` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_booleantest` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_p_costofchange`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_p_costofchange`;
CREATE TABLE `changeimpactmodifiabilityrf_p_costofchange` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_p_costofchange` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_p_costofchange`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_costofchange` DISABLE KEYS */;
INSERT INTO `changeimpactmodifiabilityrf_p_costofchange` (`uid`,`version`,`fact-id`,`value`,`owner`,`source`,`status`) VALUES 
 (32465,15314,'<Fact-258>',5.5,'<Fact-24>','ArchE','none'),
 (32466,15314,'<Fact-259>',5.5,'<Fact-20>','ArchE','none'),
 (32467,15314,'<Fact-260>',5.5,'<Fact-18>','ArchE','none'),
 (32468,15314,'<Fact-261>',5.5,'<Fact-16>','ArchE','none'),
 (32469,15314,'<Fact-262>',5.5,'<Fact-26>','ArchE','none'),
 (32470,15314,'<Fact-263>',5.5,'<Fact-21>','ArchE','none'),
 (32471,15314,'<Fact-264>',5.5,'<Fact-17>','ArchE','none'),
 (32472,15314,'<Fact-265>',5.5,'<Fact-15>','ArchE','none'),
 (32473,15314,'<Fact-266>',5.5,'<Fact-25>','ArchE','none'),
 (32474,15314,'<Fact-267>',5.5,'<Fact-23>','ArchE','none'),
 (32475,15314,'<Fact-268>',5.5,'<Fact-19>','ArchE','none'),
 (32476,15314,'<Fact-269>',5.5,'<Fact-22>','ArchE','none'),
 (32477,15314,'<Fact-270>',5.5,'<Fact-27>','ArchE','none'),
 (32478,15314,'<Fact-271>',5.5,'<Fact-28>','ArchE','none'),
 (33569,15649,'<Fact-126>',5.5,'<Fact-24>','ArchE','none'),
 (33570,15649,'<Fact-127>',5.5,'<Fact-20>','ArchE','none');
INSERT INTO `changeimpactmodifiabilityrf_p_costofchange` (`uid`,`version`,`fact-id`,`value`,`owner`,`source`,`status`) VALUES 
 (33571,15649,'<Fact-128>',5.5,'<Fact-18>','ArchE','none'),
 (33572,15649,'<Fact-129>',5.5,'<Fact-16>','ArchE','none'),
 (33573,15649,'<Fact-130>',5.5,'<Fact-26>','ArchE','none'),
 (33574,15649,'<Fact-131>',5.5,'<Fact-21>','ArchE','none'),
 (33575,15649,'<Fact-132>',5.5,'<Fact-17>','ArchE','none'),
 (33576,15649,'<Fact-133>',5.5,'<Fact-15>','ArchE','none'),
 (33577,15649,'<Fact-134>',5.5,'<Fact-25>','ArchE','none'),
 (33578,15649,'<Fact-135>',5.5,'<Fact-23>','ArchE','none'),
 (33579,15649,'<Fact-136>',5.5,'<Fact-19>','ArchE','none'),
 (33580,15649,'<Fact-137>',5.5,'<Fact-22>','ArchE','none'),
 (33581,15649,'<Fact-138>',5.5,'<Fact-27>','ArchE','none'),
 (33582,15649,'<Fact-139>',5.5,'<Fact-28>','ArchE','none');
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_costofchange` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_p_probabilityincoming`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_p_probabilityincoming`;
CREATE TABLE `changeimpactmodifiabilityrf_p_probabilityincoming` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` text,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_p_probabilityincoming` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_p_probabilityincoming`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_probabilityincoming` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_probabilityincoming` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_p_probabilityoutgoing`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_p_probabilityoutgoing`;
CREATE TABLE `changeimpactmodifiabilityrf_p_probabilityoutgoing` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` text NOT NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_p_probabilityoutgoing` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_p_probabilityoutgoing`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_probabilityoutgoing` DISABLE KEYS */;
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_p_probabilityoutgoing` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_responsibilitydependencyrelation`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_responsibilitydependencyrelation`;
CREATE TABLE `changeimpactmodifiabilityrf_responsibilitydependencyrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_responsibilitydependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_responsibilitydependencyrelation`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_responsibilitydependencyrelation` DISABLE KEYS */;
INSERT INTO `changeimpactmodifiabilityrf_responsibilitydependencyrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (33117,15314,'<Fact-226>','','User','<Fact-25>','<Fact-22>'),
 (33118,15314,'<Fact-227>','','User','<Fact-17>','<Fact-25>'),
 (33119,15314,'<Fact-228>','','User','<Fact-17>','<Fact-22>'),
 (33120,15314,'<Fact-229>','','User','<Fact-25>','<Fact-15>'),
 (33121,15314,'<Fact-230>','','User','<Fact-25>','<Fact-23>'),
 (33122,15314,'<Fact-231>','','User','<Fact-23>','<Fact-24>'),
 (33123,15314,'<Fact-232>','','User','<Fact-21>','<Fact-15>'),
 (33124,15314,'<Fact-233>','','User','<Fact-21>','<Fact-17>'),
 (33125,15314,'<Fact-234>','','User','<Fact-21>','<Fact-25>'),
 (33126,15314,'<Fact-235>','','User','<Fact-21>','<Fact-18>'),
 (33127,15314,'<Fact-236>','','User','<Fact-18>','<Fact-25>'),
 (33128,15314,'<Fact-237>','','User','<Fact-18>','<Fact-22>'),
 (33129,15314,'<Fact-238>','','User','<Fact-26>','<Fact-25>'),
 (33130,15314,'<Fact-239>','','User','<Fact-19>','<Fact-20>'),
 (33131,15314,'<Fact-240>','','User','<Fact-19>','<Fact-17>');
INSERT INTO `changeimpactmodifiabilityrf_responsibilitydependencyrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (34381,15649,'<Fact-88>','','User','<Fact-25>','<Fact-22>'),
 (34382,15649,'<Fact-89>','','User','<Fact-17>','<Fact-25>'),
 (34383,15649,'<Fact-90>','','User','<Fact-17>','<Fact-22>'),
 (34384,15649,'<Fact-91>','','User','<Fact-25>','<Fact-15>'),
 (34385,15649,'<Fact-92>','','User','<Fact-25>','<Fact-23>'),
 (34386,15649,'<Fact-93>','','User','<Fact-23>','<Fact-24>'),
 (34387,15649,'<Fact-94>','','User','<Fact-21>','<Fact-15>'),
 (34388,15649,'<Fact-95>','','User','<Fact-21>','<Fact-17>'),
 (34389,15649,'<Fact-96>','','User','<Fact-21>','<Fact-25>'),
 (34390,15649,'<Fact-97>','','User','<Fact-21>','<Fact-18>'),
 (34391,15649,'<Fact-98>','','User','<Fact-18>','<Fact-25>'),
 (34392,15649,'<Fact-99>','','User','<Fact-18>','<Fact-22>'),
 (34393,15649,'<Fact-100>','','User','<Fact-26>','<Fact-25>'),
 (34394,15649,'<Fact-101>','','User','<Fact-19>','<Fact-20>'),
 (34395,15649,'<Fact-102>','','User','<Fact-19>','<Fact-17>');
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_responsibilitydependencyrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`changeimpactmodifiabilityrf_responsibilitytomodulerelation`
--

DROP TABLE IF EXISTS `changeimpactmodifiabilityrf_responsibilitytomodulerelation`;
CREATE TABLE `changeimpactmodifiabilityrf_responsibilitytomodulerelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `source` text,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `parentType` text,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_changeimpactmodifiabilityrf_responsibilitytomodulerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`changeimpactmodifiabilityrf_responsibilitytomodulerelation`
--

/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_responsibilitytomodulerelation` DISABLE KEYS */;
INSERT INTO `changeimpactmodifiabilityrf_responsibilitytomodulerelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`,`parentType`) VALUES 
 (29134,15314,'<Fact-247>',0,'ArchE','<Fact-241>','<Fact-19>','Module'),
 (29135,15314,'<Fact-248>',0,'ArchE','<Fact-241>','<Fact-15>','Module'),
 (29136,15314,'<Fact-249>',0,'ArchE','<Fact-242>','<Fact-23>','Module'),
 (29137,15314,'<Fact-250>',0,'ArchE','<Fact-242>','<Fact-20>','Module'),
 (29138,15314,'<Fact-251>',0,'ArchE','<Fact-242>','<Fact-25>','Module'),
 (29139,15314,'<Fact-252>',0,'ArchE','<Fact-242>','<Fact-18>','Module'),
 (29140,15314,'<Fact-253>',0,'ArchE','<Fact-242>','<Fact-24>','Module'),
 (29141,15314,'<Fact-254>',0,'ArchE','<Fact-242>','<Fact-22>','Module'),
 (29142,15314,'<Fact-255>',0,'ArchE','<Fact-242>','<Fact-17>','Module'),
 (29143,15314,'<Fact-256>',0,'ArchE','<Fact-243>','<Fact-26>','Module'),
 (29144,15314,'<Fact-257>',0,'ArchE','<Fact-243>','<Fact-21>','Module'),
 (30034,15649,'<Fact-115>',0,'ArchE','<Fact-109>','<Fact-19>','Module'),
 (30035,15649,'<Fact-116>',0,'ArchE','<Fact-109>','<Fact-15>','Module');
INSERT INTO `changeimpactmodifiabilityrf_responsibilitytomodulerelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`,`parentType`) VALUES 
 (30036,15649,'<Fact-117>',0,'ArchE','<Fact-110>','<Fact-23>','Module'),
 (30037,15649,'<Fact-118>',0,'ArchE','<Fact-110>','<Fact-20>','Module'),
 (30038,15649,'<Fact-119>',0,'ArchE','<Fact-110>','<Fact-25>','Module'),
 (30039,15649,'<Fact-120>',0,'ArchE','<Fact-110>','<Fact-18>','Module'),
 (30040,15649,'<Fact-121>',0,'ArchE','<Fact-110>','<Fact-24>','Module'),
 (30041,15649,'<Fact-122>',0,'ArchE','<Fact-110>','<Fact-22>','Module'),
 (30042,15649,'<Fact-123>',0,'ArchE','<Fact-110>','<Fact-17>','Module'),
 (30043,15649,'<Fact-124>',0,'ArchE','<Fact-111>','<Fact-26>','Module'),
 (30044,15649,'<Fact-125>',0,'ArchE','<Fact-111>','<Fact-21>','Module');
/*!40000 ALTER TABLE `changeimpactmodifiabilityrf_responsibilitytomodulerelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_module`
--

DROP TABLE IF EXISTS `design_module`;
CREATE TABLE `design_module` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` varchar(200) default NULL,
  `costOfChange` double default NULL,
  `complexity` double default NULL,
  `name` text,
  `source` varchar(200) default NULL,
  `status` int(10) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_design_module` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`design_module`
--

/*!40000 ALTER TABLE `design_module` DISABLE KEYS */;
INSERT INTO `design_module` (`uid`,`version`,`fact-id`,`id`,`costOfChange`,`complexity`,`name`,`source`,`status`) VALUES 
 (3243,15314,'<Fact-241>',NULL,2,0,'(M) View - A','ArchE',0),
 (3244,15314,'<Fact-242>',NULL,4,0,'(M) Model - B','ArchE',0),
 (3245,15314,'<Fact-243>',NULL,5,0,'(M) Controller - C','ArchE',0),
 (3361,15649,'<Fact-109>',NULL,2,0,'(M) View - A','ArchE',0),
 (3362,15649,'<Fact-110>',NULL,4,0,'(M) Model - B','ArchE',0),
 (3363,15649,'<Fact-111>',NULL,5,0,'(M) Controller - C','ArchE',0);
/*!40000 ALTER TABLE `design_module` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_moduledependencyrelation`
--

DROP TABLE IF EXISTS `design_moduledependencyrelation`;
CREATE TABLE `design_moduledependencyrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `source` text,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `probability` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_design_moduledependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`design_moduledependencyrelation`
--

/*!40000 ALTER TABLE `design_moduledependencyrelation` DISABLE KEYS */;
INSERT INTO `design_moduledependencyrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`,`probability`) VALUES 
 (5503,15314,'<Fact-244>',0,'ArchE','<Fact-242>','<Fact-241>',0),
 (5504,15314,'<Fact-245>',0,'ArchE','<Fact-243>','<Fact-241>',0),
 (5505,15314,'<Fact-246>',0,'ArchE','<Fact-243>','<Fact-242>',0),
 (5787,15649,'<Fact-112>',0,'ArchE','<Fact-110>','<Fact-109>',0),
 (5788,15649,'<Fact-113>',0,'ArchE','<Fact-111>','<Fact-109>',0),
 (5789,15649,'<Fact-114>',0,'ArchE','<Fact-111>','<Fact-110>',0);
/*!40000 ALTER TABLE `design_moduledependencyrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_moduleinterface`
--

DROP TABLE IF EXISTS `design_moduleinterface`;
CREATE TABLE `design_moduleinterface` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `encapsulationLevel` double default NULL,
  `name` text,
  `source` varchar(200) default NULL,
  `costOfChange` double default NULL,
  `status` int(10) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_moduleinterface` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_moduleinterface`
--

/*!40000 ALTER TABLE `design_moduleinterface` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_moduleinterface` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_modulerefinementrelation`
--

DROP TABLE IF EXISTS `design_modulerefinementrelation`;
CREATE TABLE `design_modulerefinementrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_modulerefinementrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_modulerefinementrelation`
--

/*!40000 ALTER TABLE `design_modulerefinementrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_modulerefinementrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_process`
--

DROP TABLE IF EXISTS `design_process`;
CREATE TABLE `design_process` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `scenario` varchar(20) default NULL,
  `stimulusType` varchar(200) default NULL,
  `stimulusValue` text,
  `taskType` varchar(200) default NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `time_blocked` text,
  `priority` text,
  `source` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_process` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`design_process`
--

/*!40000 ALTER TABLE `design_process` DISABLE KEYS */;
INSERT INTO `design_process` (`uid`,`version`,`fact-id`,`name`,`scenario`,`stimulusType`,`stimulusValue`,`taskType`,`taskValue`,`executionTime`,`latency`,`time_blocked`,`priority`,`source`) VALUES 
 (33,965,'<Fact-113>','Process-aaaaaaaaa','<Fact-78>','periodic','9.999','HardDeadline','6.999','17.0',NULL,NULL,NULL,'ArchE'),
 (34,965,'<Fact-124>','Process-fsadfdsa','<Fact-76>','periodic','9.999','HardDeadline','9.999','9.0',NULL,NULL,NULL,'ArchE');
/*!40000 ALTER TABLE `design_process` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_realizerelation`
--

DROP TABLE IF EXISTS `design_realizerelation`;
CREATE TABLE `design_realizerelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `childType` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_realizerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_realizerelation`
--

/*!40000 ALTER TABLE `design_realizerelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_realizerelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_responsibility`
--

DROP TABLE IF EXISTS `design_responsibility`;
CREATE TABLE `design_responsibility` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `owner` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_responsibility` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_responsibility`
--

/*!40000 ALTER TABLE `design_responsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_responsibility` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_responsibilitytomodulerelation`
--

DROP TABLE IF EXISTS `design_responsibilitytomodulerelation`;
CREATE TABLE `design_responsibilitytomodulerelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `parentType` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_responsibilitytomodulerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_responsibilitytomodulerelation`
--

/*!40000 ALTER TABLE `design_responsibilitytomodulerelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_responsibilitytomodulerelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_sharedresource`
--

DROP TABLE IF EXISTS `design_sharedresource`;
CREATE TABLE `design_sharedresource` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `name` text,
  `description` text,
  `source` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_sharedresource` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_sharedresource`
--

/*!40000 ALTER TABLE `design_sharedresource` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_sharedresource` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_sharedresourcerelation`
--

DROP TABLE IF EXISTS `design_sharedresourcerelation`;
CREATE TABLE `design_sharedresourcerelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `design_sharedresourcerelation_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_sharedresourcerelation`
--

/*!40000 ALTER TABLE `design_sharedresourcerelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_sharedresourcerelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_thread`
--

DROP TABLE IF EXISTS `design_thread`;
CREATE TABLE `design_thread` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `scenario` varchar(20) default NULL,
  `stimulusType` varchar(200) default NULL,
  `stimulusValue` text,
  `taskType` varchar(200) default NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `time_blocked` text,
  `priority` text,
  `source` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_thread` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_thread`
--

/*!40000 ALTER TABLE `design_thread` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_thread` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_unitofconcurrency`
--

DROP TABLE IF EXISTS `design_unitofconcurrency`;
CREATE TABLE `design_unitofconcurrency` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `task` varchar(20) default NULL,
  `scenario` varchar(20) default NULL,
  `responsibility` varchar(20) default NULL,
  `MutualExclusion` varchar(200) default NULL,
  `SharedResourceID` varchar(20) default NULL,
  `taskType` varchar(200) default NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `priority` text,
  `source` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_unitofconcurrency` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_unitofconcurrency`
--

/*!40000 ALTER TABLE `design_unitofconcurrency` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_unitofconcurrency` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_variationpoint`
--

DROP TABLE IF EXISTS `design_variationpoint`;
CREATE TABLE `design_variationpoint` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `name` text,
  `value` text,
  `mechanism` text,
  `condition` text,
  `alternatives` text,
  `howTo` text,
  `cost` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_variationpoint` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_variationpoint`
--

/*!40000 ALTER TABLE `design_variationpoint` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_variationpoint` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_vpinputvalue`
--

DROP TABLE IF EXISTS `design_vpinputvalue`;
CREATE TABLE `design_vpinputvalue` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `name` text,
  `value` text,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_vpinputvalue` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_vpinputvalue`
--

/*!40000 ALTER TABLE `design_vpinputvalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_vpinputvalue` ENABLE KEYS */;


--
-- Table structure for table `arche`.`design_wrapper`
--

DROP TABLE IF EXISTS `design_wrapper`;
CREATE TABLE `design_wrapper` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `encapsulationLevel` double default NULL,
  `name` text,
  `source` varchar(200) default NULL,
  `status` int(10) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_design_wrapper` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`design_wrapper`
--

/*!40000 ALTER TABLE `design_wrapper` DISABLE KEYS */;
/*!40000 ALTER TABLE `design_wrapper` ENABLE KEYS */;


--
-- Table structure for table `arche`.`externalinteraction_responsibilitydependencyrelation`
--

DROP TABLE IF EXISTS `externalinteraction_responsibilitydependencyrelation`;
CREATE TABLE `externalinteraction_responsibilitydependencyrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varbinary(100) default NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varbinary(100) default NULL,
  `child` varbinary(100) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_externalinteraction_responsibilitydependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`externalinteraction_responsibilitydependencyrelation`
--

/*!40000 ALTER TABLE `externalinteraction_responsibilitydependencyrelation` DISABLE KEYS */;
INSERT INTO `externalinteraction_responsibilitydependencyrelation` (`uid`,`version`,`fact-id`,`scenario`,`id`,`source`,`parent`,`child`) VALUES 
 (45,11673,'<Fact-144>',NULL,NULL,'ArchE','<Fact-22>','<Fact-23>');
/*!40000 ALTER TABLE `externalinteraction_responsibilitydependencyrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`externalinteraction_responsibilityinteractionrelation`
--

DROP TABLE IF EXISTS `externalinteraction_responsibilityinteractionrelation`;
CREATE TABLE `externalinteraction_responsibilityinteractionrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varbinary(100) default NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varbinary(100) default NULL,
  `child` varbinary(100) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_externalinteraction_responsibilityinteractionrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`externalinteraction_responsibilityinteractionrelation`
--

/*!40000 ALTER TABLE `externalinteraction_responsibilityinteractionrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `externalinteraction_responsibilityinteractionrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`externalinteraction_responsibilityreactionrelation`
--

DROP TABLE IF EXISTS `externalinteraction_responsibilityreactionrelation`;
CREATE TABLE `externalinteraction_responsibilityreactionrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varbinary(100) default NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varbinary(100) default NULL,
  `child` varbinary(100) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_externalinteraction_responsibilityreactionrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`externalinteraction_responsibilityreactionrelation`
--

/*!40000 ALTER TABLE `externalinteraction_responsibilityreactionrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `externalinteraction_responsibilityreactionrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`icmperformancerf_assemblyinstance`
--

DROP TABLE IF EXISTS `icmperformancerf_assemblyinstance`;
CREATE TABLE `icmperformancerf_assemblyinstance` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `icmFilename` text,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_assemblyinstance` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`icmperformancerf_assemblyinstance`
--

/*!40000 ALTER TABLE `icmperformancerf_assemblyinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `icmperformancerf_assemblyinstance` ENABLE KEYS */;


--
-- Table structure for table `arche`.`icmperformancerf_p_executiontime`
--

DROP TABLE IF EXISTS `icmperformancerf_p_executiontime`;
CREATE TABLE `icmperformancerf_p_executiontime` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_p_executiontime` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`icmperformancerf_p_executiontime`
--

/*!40000 ALTER TABLE `icmperformancerf_p_executiontime` DISABLE KEYS */;
INSERT INTO `icmperformancerf_p_executiontime` (`uid`,`version`,`fact-id`,`value`,`owner`,`source`,`status`) VALUES 
 (107,12974,'<Fact-188>',1,'<Fact-59>','ArchE','none'),
 (108,12974,'<Fact-189>',1,'<Fact-33>','ArchE','none'),
 (8213,15314,'<Fact-170>',1,'<Fact-18>','ArchE','none'),
 (8214,15314,'<Fact-171>',1,'<Fact-21>','ArchE','none'),
 (8215,15314,'<Fact-172>',1,'<Fact-16>','ArchE','none'),
 (8216,15314,'<Fact-173>',1,'<Fact-24>','ArchE','none'),
 (8217,15314,'<Fact-174>',1,'<Fact-25>','ArchE','none'),
 (8218,15314,'<Fact-175>',1,'<Fact-26>','ArchE','none'),
 (8219,15314,'<Fact-176>',1,'<Fact-20>','ArchE','none'),
 (8220,15314,'<Fact-177>',1,'<Fact-17>','ArchE','none'),
 (8221,15314,'<Fact-178>',1,'<Fact-15>','ArchE','none'),
 (8222,15314,'<Fact-179>',1,'<Fact-23>','ArchE','none'),
 (8223,15314,'<Fact-180>',1,'<Fact-22>','ArchE','none'),
 (8224,15314,'<Fact-181>',1,'<Fact-19>','ArchE','none'),
 (8909,15649,'<Fact-202>',1,'<Fact-18>','ArchE','none'),
 (8910,15649,'<Fact-203>',1,'<Fact-21>','ArchE','none'),
 (8911,15649,'<Fact-204>',1,'<Fact-16>','ArchE','none');
INSERT INTO `icmperformancerf_p_executiontime` (`uid`,`version`,`fact-id`,`value`,`owner`,`source`,`status`) VALUES 
 (8912,15649,'<Fact-205>',1,'<Fact-24>','ArchE','none'),
 (8913,15649,'<Fact-206>',1,'<Fact-25>','ArchE','none'),
 (8914,15649,'<Fact-207>',1,'<Fact-26>','ArchE','none'),
 (8915,15649,'<Fact-208>',1,'<Fact-20>','ArchE','none'),
 (8916,15649,'<Fact-209>',1,'<Fact-17>','ArchE','none'),
 (8917,15649,'<Fact-210>',1,'<Fact-15>','ArchE','none'),
 (8918,15649,'<Fact-211>',1,'<Fact-23>','ArchE','none'),
 (8919,15649,'<Fact-212>',1,'<Fact-22>','ArchE','none'),
 (8920,15649,'<Fact-213>',1,'<Fact-19>','ArchE','none');
/*!40000 ALTER TABLE `icmperformancerf_p_executiontime` ENABLE KEYS */;


--
-- Table structure for table `arche`.`icmperformancerf_p_taskpriority`
--

DROP TABLE IF EXISTS `icmperformancerf_p_taskpriority`;
CREATE TABLE `icmperformancerf_p_taskpriority` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` int(10) default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_p_taskpriority` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`icmperformancerf_p_taskpriority`
--

/*!40000 ALTER TABLE `icmperformancerf_p_taskpriority` DISABLE KEYS */;
INSERT INTO `icmperformancerf_p_taskpriority` (`uid`,`version`,`fact-id`,`value`,`owner`,`source`,`status`) VALUES 
 (107,12974,'<Fact-190>',1,'<Fact-59>','ArchE','none'),
 (108,12974,'<Fact-191>',1,'<Fact-33>','ArchE','none');
/*!40000 ALTER TABLE `icmperformancerf_p_taskpriority` ENABLE KEYS */;


--
-- Table structure for table `arche`.`icmperformancerf_responsibilityinteractionrelation`
--

DROP TABLE IF EXISTS `icmperformancerf_responsibilityinteractionrelation`;
CREATE TABLE `icmperformancerf_responsibilityinteractionrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) default NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_responsibilityinteractionrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`icmperformancerf_responsibilityinteractionrelation`
--

/*!40000 ALTER TABLE `icmperformancerf_responsibilityinteractionrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `icmperformancerf_responsibilityinteractionrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`icmperformancerf_responsibilityreactionrelation`
--

DROP TABLE IF EXISTS `icmperformancerf_responsibilityreactionrelation`;
CREATE TABLE `icmperformancerf_responsibilityreactionrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_icmperformancerf_responsibilityreactionrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`icmperformancerf_responsibilityreactionrelation`
--

/*!40000 ALTER TABLE `icmperformancerf_responsibilityreactionrelation` DISABLE KEYS */;
INSERT INTO `icmperformancerf_responsibilityreactionrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (5,12974,'<Fact-131>',NULL,'User','<Fact-59>','<Fact-33>'),
 (1863,15314,'<Fact-103>','','User','<Fact-26>','<Fact-25>'),
 (1864,15314,'<Fact-104>','','User','<Fact-20>','<Fact-19>'),
 (1865,15314,'<Fact-105>','','User','<Fact-21>','<Fact-17>'),
 (1866,15314,'<Fact-106>','','User','<Fact-21>','<Fact-18>'),
 (1867,15314,'<Fact-107>','','User','<Fact-21>','<Fact-15>'),
 (1868,15314,'<Fact-108>','','User','<Fact-15>','<Fact-25>'),
 (2211,15649,'<Fact-196>','','User','<Fact-26>','<Fact-25>'),
 (2212,15649,'<Fact-197>','','User','<Fact-20>','<Fact-19>'),
 (2213,15649,'<Fact-198>','','User','<Fact-21>','<Fact-17>'),
 (2214,15649,'<Fact-199>','','User','<Fact-21>','<Fact-18>'),
 (2215,15649,'<Fact-200>','','User','<Fact-21>','<Fact-15>'),
 (2216,15649,'<Fact-201>','','User','<Fact-15>','<Fact-25>');
/*!40000 ALTER TABLE `icmperformancerf_responsibilityreactionrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_dataflow`
--

DROP TABLE IF EXISTS `main_dataflow`;
CREATE TABLE `main_dataflow` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `producer` varchar(20) default NULL,
  `consumer` varchar(20) default NULL,
  `dataitem` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_dataflow` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_dataflow`
--

/*!40000 ALTER TABLE `main_dataflow` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_dataflow` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_dataitems`
--

DROP TABLE IF EXISTS `main_dataitems`;
CREATE TABLE `main_dataitems` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL default '0',
  `fact-id` varchar(20) NOT NULL default '',
  `name` text,
  `description` text,
  `source` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_main_dataitems_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_dataitems`
--

/*!40000 ALTER TABLE `main_dataitems` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_dataitems` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_function`
--

DROP TABLE IF EXISTS `main_function`;
CREATE TABLE `main_function` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_function` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_function`
--

/*!40000 ALTER TABLE `main_function` DISABLE KEYS */;
INSERT INTO `main_function` (`uid`,`version`,`fact-id`,`id`,`description`) VALUES 
 (961,13250,'<Fact-31>','1','Show Itinerary'),
 (962,13250,'<Fact-32>','10','Manage user Profile'),
 (963,13250,'<Fact-33>','10.1','Create user Profile'),
 (964,13250,'<Fact-34>','10.2','Modify user Profile'),
 (965,13250,'<Fact-35>','2','Attach to Model'),
 (966,13250,'<Fact-36>','3','Register Views'),
 (967,13250,'<Fact-37>','4','Handle user Interactions'),
 (968,13250,'<Fact-38>','6','Save Data'),
 (969,13250,'<Fact-39>','7','Query for Data'),
 (970,13250,'<Fact-40>','8','Locate Service'),
 (971,13250,'<Fact-41>','9','Manage Itinerary'),
 (972,13250,'<Fact-42>','5','Manage External Devices'),
 (1309,15314,'<Fact-27>','1','Show Itinerary'),
 (1310,15314,'<Fact-28>','10','Manage user Profile'),
 (1311,15314,'<Fact-29>','10.1','Create user Profile'),
 (1312,15314,'<Fact-30>','10.2','Modify user Profile'),
 (1313,15314,'<Fact-31>','2','Attach to Model'),
 (1314,15314,'<Fact-32>','3','Register Views'),
 (1315,15314,'<Fact-33>','4','Handle user Interactions'),
 (1316,15314,'<Fact-34>','6','Save Data');
INSERT INTO `main_function` (`uid`,`version`,`fact-id`,`id`,`description`) VALUES 
 (1317,15314,'<Fact-35>','7','Query for Data'),
 (1318,15314,'<Fact-36>','8','Locate Service'),
 (1319,15314,'<Fact-37>','9','Manage Itinerary'),
 (1320,15314,'<Fact-38>','5','Manage External Devices');
/*!40000 ALTER TABLE `main_function` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_functionrefinementrelation`
--

DROP TABLE IF EXISTS `main_functionrefinementrelation`;
CREATE TABLE `main_functionrefinementrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_functionrefinementrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_functionrefinementrelation`
--

/*!40000 ALTER TABLE `main_functionrefinementrelation` DISABLE KEYS */;
INSERT INTO `main_functionrefinementrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (161,13250,'<Fact-76>',NULL,'User','<Fact-32>','<Fact-33>'),
 (162,13250,'<Fact-77>',NULL,'User','<Fact-32>','<Fact-34>'),
 (219,15314,'<Fact-69>',NULL,'User','<Fact-28>','<Fact-29>'),
 (220,15314,'<Fact-70>',NULL,'User','<Fact-28>','<Fact-30>');
/*!40000 ALTER TABLE `main_functionrefinementrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_leafresponsibilitydependencyrelation`
--

DROP TABLE IF EXISTS `main_leafresponsibilitydependencyrelation`;
CREATE TABLE `main_leafresponsibilitydependencyrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `incoming` double default NULL,
  `outgoing` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_leafresponsibilitydependencyrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_leafresponsibilitydependencyrelation`
--

/*!40000 ALTER TABLE `main_leafresponsibilitydependencyrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_leafresponsibilitydependencyrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_p_analysisresult`
--

DROP TABLE IF EXISTS `main_p_analysisresult`;
CREATE TABLE `main_p_analysisresult` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `quality` varchar(200) default NULL,
  `reasoningFramework` varchar(200) default NULL,
  `isSatisfied` varchar(200) default NULL,
  `utility` double default NULL,
  `value` double default NULL,
  `oldValue` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_p_analysisresult` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_p_analysisresult`
--

/*!40000 ALTER TABLE `main_p_analysisresult` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_p_analysisresult` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_responsibilities`
--

DROP TABLE IF EXISTS `main_responsibilities`;
CREATE TABLE `main_responsibilities` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` varchar(200) default NULL,
  `name` text,
  `description` text,
  `source` varchar(200) default NULL,
  `status` int(10) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_main_responsibilities` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_responsibilities`
--

/*!40000 ALTER TABLE `main_responsibilities` DISABLE KEYS */;
INSERT INTO `main_responsibilities` (`uid`,`version`,`fact-id`,`id`,`name`,`description`,`source`,`status`) VALUES 
 (67126,15314,'<Fact-15>','gen228','Show Itinerary','Show Itinerary','ArchE',0),
 (67127,15314,'<Fact-16>','gen227','Manage User Profile','Manage user Profile','ArchE',0),
 (67128,15314,'<Fact-17>','gen226','Create User Profile','Create user Profile','ArchE',0),
 (67129,15314,'<Fact-18>','gen225','Modify User Profile','Modify user Profile','ArchE',0),
 (67130,15314,'<Fact-19>','gen224','Attach to Model','Attach to Model','ArchE',0),
 (67131,15314,'<Fact-20>','gen223','Register Views','Register Views','ArchE',0),
 (67132,15314,'<Fact-21>','gen222','Handle User Interactions','Handle user Interactions','ArchE',0),
 (67133,15314,'<Fact-22>','gen220','Save Data','Save Data','ArchE',0),
 (67134,15314,'<Fact-23>','gen219','Query for Data','Query for Data','ArchE',0),
 (67135,15314,'<Fact-24>','gen241','Locate Service','Locate Service','ArchE',0),
 (67136,15314,'<Fact-25>','gen240','Manage Itinerary','Manage Itinerary','ArchE',0),
 (67137,15314,'<Fact-26>','gen558','Manage External Devices','Manage External Devices','ArchE',0);
INSERT INTO `main_responsibilities` (`uid`,`version`,`fact-id`,`id`,`name`,`description`,`source`,`status`) VALUES 
 (70552,15649,'<Fact-15>','gen228','Show Itinerary','Show Itinerary','ArchE',0),
 (70553,15649,'<Fact-16>','gen227','Manage User Profile','Manage user Profile','ArchE',0),
 (70554,15649,'<Fact-17>','gen226','Create User Profile','Create user Profile','ArchE',0),
 (70555,15649,'<Fact-18>','gen225','Modify User Profile','Modify user Profile','ArchE',0),
 (70556,15649,'<Fact-19>','gen224','Attach to Model','Attach to Model','ArchE',0),
 (70557,15649,'<Fact-20>','gen223','Register Views','Register Views','ArchE',0),
 (70558,15649,'<Fact-21>','gen222','Handle User Interactions','Handle user Interactions','ArchE',0),
 (70559,15649,'<Fact-22>','gen220','Save Data','Save Data','ArchE',0),
 (70560,15649,'<Fact-23>','gen219','Query for Data','Query for Data','ArchE',0),
 (70561,15649,'<Fact-24>','gen241','Locate Service','Locate Service','ArchE',0),
 (70562,15649,'<Fact-25>','gen240','Manage Itinerary','Manage Itinerary','ArchE',0),
 (70563,15649,'<Fact-26>','gen558','Manage External Devices','Manage External Devices','ArchE',0);
/*!40000 ALTER TABLE `main_responsibilities` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_responsibilityrefinementrelation`
--

DROP TABLE IF EXISTS `main_responsibilityrefinementrelation`;
CREATE TABLE `main_responsibilityrefinementrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_responsibilityrefinementrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_responsibilityrefinementrelation`
--

/*!40000 ALTER TABLE `main_responsibilityrefinementrelation` DISABLE KEYS */;
INSERT INTO `main_responsibilityrefinementrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (12855,13250,'<Fact-93>',NULL,'ArchE','<Fact-20>','<Fact-21>'),
 (12856,13250,'<Fact-94>',NULL,'ArchE','<Fact-20>','<Fact-22>'),
 (17124,15314,'<Fact-86>',NULL,'ArchE','<Fact-16>','<Fact-17>'),
 (17125,15314,'<Fact-87>',NULL,'ArchE','<Fact-16>','<Fact-18>'),
 (17876,15649,'<Fact-86>',NULL,'ArchE','<Fact-16>','<Fact-17>'),
 (17877,15649,'<Fact-87>',NULL,'ArchE','<Fact-16>','<Fact-18>');
/*!40000 ALTER TABLE `main_responsibilityrefinementrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_responsibilitytoresponsibilityrelation`
--

DROP TABLE IF EXISTS `main_responsibilitytoresponsibilityrelation`;
CREATE TABLE `main_responsibilitytoresponsibilityrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_responsibilitytoresponsibilityrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_responsibilitytoresponsibilityrelation`
--

/*!40000 ALTER TABLE `main_responsibilitytoresponsibilityrelation` DISABLE KEYS */;
INSERT INTO `main_responsibilitytoresponsibilityrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (34005,13250,'<Fact-78>','gen302','User','<Fact-23>','<Fact-24>'),
 (34006,13250,'<Fact-79>','gen301','User','<Fact-21>','<Fact-22>'),
 (34007,13250,'<Fact-80>','gen300','User','<Fact-21>','<Fact-26>'),
 (34008,13250,'<Fact-81>','gen299','User','<Fact-25>','<Fact-21>'),
 (34009,13250,'<Fact-82>','gen298','User','<Fact-25>','<Fact-29>'),
 (34010,13250,'<Fact-83>','gen297','User','<Fact-25>','<Fact-22>'),
 (34011,13250,'<Fact-84>','gen296','User','<Fact-25>','<Fact-19>'),
 (34012,13250,'<Fact-85>','gen294','User','<Fact-29>','<Fact-27>'),
 (34013,13250,'<Fact-86>','gen293','User','<Fact-29>','<Fact-26>'),
 (34014,13250,'<Fact-87>','gen292','User','<Fact-29>','<Fact-19>'),
 (34015,13250,'<Fact-88>','gen291','User','<Fact-22>','<Fact-29>'),
 (34016,13250,'<Fact-89>','gen289','User','<Fact-27>','<Fact-28>'),
 (34017,13250,'<Fact-90>','gen290','User','<Fact-22>','<Fact-26>'),
 (34018,13250,'<Fact-91>','gen872','User','<Fact-30>','<Fact-29>');
INSERT INTO `main_responsibilitytoresponsibilityrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (34019,13250,'<Fact-92>','gen2123','User','<Fact-25>','<Fact-24>'),
 (47520,15314,'<Fact-71>','gen302','User','<Fact-19>','<Fact-20>'),
 (47521,15314,'<Fact-72>','gen301','User','<Fact-17>','<Fact-18>'),
 (47522,15314,'<Fact-73>','gen300','User','<Fact-17>','<Fact-22>'),
 (47523,15314,'<Fact-74>','gen299','User','<Fact-21>','<Fact-17>'),
 (47524,15314,'<Fact-75>','gen298','User','<Fact-21>','<Fact-25>'),
 (47525,15314,'<Fact-76>','gen297','User','<Fact-21>','<Fact-18>'),
 (47526,15314,'<Fact-77>','gen296','User','<Fact-21>','<Fact-15>'),
 (47527,15314,'<Fact-78>','gen294','User','<Fact-25>','<Fact-23>'),
 (47528,15314,'<Fact-79>','gen293','User','<Fact-25>','<Fact-22>'),
 (47529,15314,'<Fact-80>','gen292','User','<Fact-25>','<Fact-15>'),
 (47530,15314,'<Fact-81>','gen291','User','<Fact-18>','<Fact-25>'),
 (47531,15314,'<Fact-82>','gen289','User','<Fact-23>','<Fact-24>'),
 (47532,15314,'<Fact-83>','gen290','User','<Fact-18>','<Fact-22>');
INSERT INTO `main_responsibilitytoresponsibilityrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (47533,15314,'<Fact-84>','gen872','User','<Fact-26>','<Fact-25>'),
 (47534,15314,'<Fact-85>','gen2123','User','<Fact-21>','<Fact-20>'),
 (48390,15649,'<Fact-71>','gen302','User','<Fact-19>','<Fact-20>'),
 (48391,15649,'<Fact-72>','gen301','User','<Fact-17>','<Fact-18>'),
 (48392,15649,'<Fact-73>','gen300','User','<Fact-17>','<Fact-22>'),
 (48393,15649,'<Fact-74>','gen299','User','<Fact-21>','<Fact-17>'),
 (48394,15649,'<Fact-75>','gen298','User','<Fact-21>','<Fact-25>'),
 (48395,15649,'<Fact-76>','gen297','User','<Fact-21>','<Fact-18>'),
 (48396,15649,'<Fact-77>','gen296','User','<Fact-21>','<Fact-15>'),
 (48397,15649,'<Fact-78>','gen294','User','<Fact-25>','<Fact-23>'),
 (48398,15649,'<Fact-79>','gen293','User','<Fact-25>','<Fact-22>'),
 (48399,15649,'<Fact-80>','gen292','User','<Fact-25>','<Fact-15>'),
 (48400,15649,'<Fact-81>','gen291','User','<Fact-18>','<Fact-25>'),
 (48401,15649,'<Fact-82>','gen289','User','<Fact-23>','<Fact-24>');
INSERT INTO `main_responsibilitytoresponsibilityrelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (48402,15649,'<Fact-83>','gen290','User','<Fact-18>','<Fact-22>'),
 (48403,15649,'<Fact-84>','gen872','User','<Fact-26>','<Fact-25>'),
 (48404,15649,'<Fact-85>','gen2123','User','<Fact-21>','<Fact-20>');
/*!40000 ALTER TABLE `main_responsibilitytoresponsibilityrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_scenariorefinementrelation`
--

DROP TABLE IF EXISTS `main_scenariorefinementrelation`;
CREATE TABLE `main_scenariorefinementrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_scenariorefinementrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_scenariorefinementrelation`
--

/*!40000 ALTER TABLE `main_scenariorefinementrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_scenariorefinementrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_scenarios`
--

DROP TABLE IF EXISTS `main_scenarios`;
CREATE TABLE `main_scenarios` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `description` text,
  `quality` varchar(200) default NULL,
  `stimulusText` text,
  `stimulusType` varchar(200) default NULL,
  `stimulusUnit` varchar(200) default NULL,
  `stimulusValue` double default NULL,
  `sourceText` text,
  `sourceType` varchar(200) default NULL,
  `sourceUnit` varchar(200) default NULL,
  `sourceValue` double default NULL,
  `artifactText` text,
  `artifactType` varchar(200) default NULL,
  `artifactUnit` varchar(200) default NULL,
  `artifactValue` double default NULL,
  `environmentText` text,
  `environmentType` varchar(200) default NULL,
  `environmentUnit` varchar(200) default NULL,
  `environmentValue` double default NULL,
  `responseText` text,
  `responseType` varchar(200) default NULL,
  `responseUnit` varchar(200) default NULL,
  `responseValue` double default NULL,
  `measureText` text,
  `measureType` varchar(200) default NULL,
  `measureUnit` varchar(200) default NULL,
  `measureValue` double default NULL,
  `state` text,
  `reasoningFramework` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_main_scenarios` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_scenarios`
--

/*!40000 ALTER TABLE `main_scenarios` DISABLE KEYS */;
INSERT INTO `main_scenarios` (`uid`,`version`,`fact-id`,`id`,`description`,`quality`,`stimulusText`,`stimulusType`,`stimulusUnit`,`stimulusValue`,`sourceText`,`sourceType`,`sourceUnit`,`sourceValue`,`artifactText`,`artifactType`,`artifactUnit`,`artifactValue`,`environmentText`,`environmentType`,`environmentUnit`,`environmentValue`,`responseText`,`responseType`,`responseUnit`,`responseValue`,`measureText`,`measureType`,`measureUnit`,`measureValue`,`state`,`reasoningFramework`) VALUES 
 (27747,15314,'<Fact-7>','gen237','P1 - The system has to manage the external devices under normal load and handle the operations in under 5 seconds.','ICMPerformance','external devices','periodic','milliseconds',40,'external devices','System',NULL,NULL,'system','System',NULL,NULL,'normal','NormalCondition',NULL,NULL,'handles the operation','TaskLatency',NULL,NULL,'under 5 seconds','WorstCase','msec',15,NULL,'ICMPerformanceRF'),
 (27748,15314,'<Fact-8>','gen244','P2 - A view wishes to attach to the model under normal conditions and do so in under 1 second.','ICMPerformance','attach to model','periodic','milliseconds',42,'view','System',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'view is attached to model','TaskLatency',NULL,NULL,'in under 1 second','WorstCase','msec',39,NULL,'ICMPerformanceRF'),
 (27749,15314,'<Fact-9>','gen248','P3 - The user modifies their profile under normal conditions and the profile is modified in under 5 seconds.','ICMPerformance','modify profile','periodic','milliseconds',64,'user','External',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'profile is modified','TaskLatency',NULL,NULL,'in under 5 seconds','WorstCase','msec',18,NULL,'ICMPerformanceRF');
INSERT INTO `main_scenarios` (`uid`,`version`,`fact-id`,`id`,`description`,`quality`,`stimulusText`,`stimulusType`,`stimulusUnit`,`stimulusValue`,`sourceText`,`sourceType`,`sourceUnit`,`sourceValue`,`artifactText`,`artifactType`,`artifactUnit`,`artifactValue`,`environmentText`,`environmentType`,`environmentUnit`,`environmentValue`,`responseText`,`responseType`,`responseUnit`,`responseValue`,`measureText`,`measureType`,`measureUnit`,`measureValue`,`state`,`reasoningFramework`) VALUES 
 (27750,15314,'<Fact-10>','gen252','P4 - The user asks to show the itinerary under normal conditions and the itinerary is shown in under 5 seconds.','ICMPerformance','show itinerary','periodic','milliseconds',55,'user','External',NULL,NULL,'system','System',NULL,NULL,'Normal conditions','NormalCondition',NULL,NULL,'itinerary is shown','TaskLatency',NULL,NULL,'in under 5 seconds','WorstCase','msec',16,NULL,'ICMPerformanceRF'),
 (27751,15314,'<Fact-11>','gen256','P5 - The user asks to save the current data on the screen under normal conditions and the data is saved in under 10 seconds.','ICMPerformance','save data','periodic','milliseconds',18,'user','External',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'data is saved','TaskLatency',NULL,NULL,'in under 10 seconds','WorstCase','msec',4,NULL,'ICMPerformanceRF'),
 (27752,15314,'<Fact-12>','gen309','M1 - Adding a new feature requires to change the storage format. The implementation of the new format has to be done within 3.5 days','ChangeImpactModifiability','Adding a new feature',NULL,NULL,NULL,NULL,'developer',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'The implementation of the new format has to be done',NULL,NULL,NULL,'within 3.5 days','CostConstraint','days',3,NULL,'ChangeImpactModifiabilityRF');
INSERT INTO `main_scenarios` (`uid`,`version`,`fact-id`,`id`,`description`,`quality`,`stimulusText`,`stimulusType`,`stimulusUnit`,`stimulusValue`,`sourceText`,`sourceType`,`sourceUnit`,`sourceValue`,`artifactText`,`artifactType`,`artifactUnit`,`artifactValue`,`environmentText`,`environmentType`,`environmentUnit`,`environmentValue`,`responseText`,`responseType`,`responseUnit`,`responseValue`,`measureText`,`measureType`,`measureUnit`,`measureValue`,`state`,`reasoningFramework`) VALUES 
 (27753,15314,'<Fact-13>','gen318','M2 - A new variable to the user profile has to be added within 5 days of effort','ChangeImpactModifiability',NULL,NULL,NULL,NULL,NULL,'endUser',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Type response measure here','CostConstraint','days',15,NULL,'ChangeImpactModifiabilityRF'),
 (27754,15314,'<Fact-14>','gen433','M3 - The driver for a new external device has to be added by a developer within 10 days','ChangeImpactModifiability','Adding a new device',NULL,NULL,NULL,NULL,'developer',NULL,NULL,'The driver for a new external device',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'has to be added',NULL,NULL,NULL,'within 10 days','CostConstraint','days',5.5,NULL,'ChangeImpactModifiabilityRF'),
 (28399,15649,'<Fact-7>','gen237','P1 - The system has to manage the external devices under normal load and handle the operations in under 5 seconds.','ICMPerformance','external devices','periodic','milliseconds',40,'external devices','System',NULL,NULL,'system','System',NULL,NULL,'normal','NormalCondition',NULL,NULL,'handles the operation','TaskLatency',NULL,NULL,'under 5 seconds','WorstCase','msec',15,NULL,'ICMPerformanceRF');
INSERT INTO `main_scenarios` (`uid`,`version`,`fact-id`,`id`,`description`,`quality`,`stimulusText`,`stimulusType`,`stimulusUnit`,`stimulusValue`,`sourceText`,`sourceType`,`sourceUnit`,`sourceValue`,`artifactText`,`artifactType`,`artifactUnit`,`artifactValue`,`environmentText`,`environmentType`,`environmentUnit`,`environmentValue`,`responseText`,`responseType`,`responseUnit`,`responseValue`,`measureText`,`measureType`,`measureUnit`,`measureValue`,`state`,`reasoningFramework`) VALUES 
 (28400,15649,'<Fact-8>','gen244','P2 - A view wishes to attach to the model under normal conditions and do so in under 1 second.','ICMPerformance','attach to model','periodic','milliseconds',42,'view','System',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'view is attached to model','TaskLatency',NULL,NULL,'in under 1 second','WorstCase','msec',39,NULL,'ICMPerformanceRF'),
 (28401,15649,'<Fact-9>','gen248','P3 - The user modifies their profile under normal conditions and the profile is modified in under 5 seconds.','ICMPerformance','modify profile','periodic','milliseconds',64,'user','External',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'profile is modified','TaskLatency',NULL,NULL,'in under 5 seconds','WorstCase','msec',18,NULL,'ICMPerformanceRF'),
 (28402,15649,'<Fact-10>','gen252','P4 - The user asks to show the itinerary under normal conditions and the itinerary is shown in under 5 seconds.','ICMPerformance','show itinerary','periodic','milliseconds',55,'user','External',NULL,NULL,'system','System',NULL,NULL,'Normal conditions','NormalCondition',NULL,NULL,'itinerary is shown','TaskLatency',NULL,NULL,'in under 5 seconds','WorstCase','msec',16,NULL,'ICMPerformanceRF');
INSERT INTO `main_scenarios` (`uid`,`version`,`fact-id`,`id`,`description`,`quality`,`stimulusText`,`stimulusType`,`stimulusUnit`,`stimulusValue`,`sourceText`,`sourceType`,`sourceUnit`,`sourceValue`,`artifactText`,`artifactType`,`artifactUnit`,`artifactValue`,`environmentText`,`environmentType`,`environmentUnit`,`environmentValue`,`responseText`,`responseType`,`responseUnit`,`responseValue`,`measureText`,`measureType`,`measureUnit`,`measureValue`,`state`,`reasoningFramework`) VALUES 
 (28403,15649,'<Fact-11>','gen256','P5 - The user asks to save the current data on the screen under normal conditions and the data is saved in under 10 seconds.','ICMPerformance','save data','periodic','milliseconds',18,'user','External',NULL,NULL,'system','System',NULL,NULL,'normal conditions','NormalCondition',NULL,NULL,'data is saved','TaskLatency',NULL,NULL,'in under 10 seconds','WorstCase','msec',4,NULL,'ICMPerformanceRF'),
 (28404,15649,'<Fact-12>','gen309','M1 - Adding a new feature requires to change the storage format. The implementation of the new format has to be done within 3.5 days','ChangeImpactModifiability','Adding a new feature',NULL,NULL,NULL,NULL,'developer',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'The implementation of the new format has to be done',NULL,NULL,NULL,'within 3.5 days','CostConstraint','days',3,NULL,'ChangeImpactModifiabilityRF'),
 (28405,15649,'<Fact-13>','gen318','M2 - A new variable to the user profile has to be added within 5 days of effort','ChangeImpactModifiability',NULL,NULL,NULL,NULL,NULL,'endUser',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Type response measure here','CostConstraint','days',15,NULL,'ChangeImpactModifiabilityRF');
INSERT INTO `main_scenarios` (`uid`,`version`,`fact-id`,`id`,`description`,`quality`,`stimulusText`,`stimulusType`,`stimulusUnit`,`stimulusValue`,`sourceText`,`sourceType`,`sourceUnit`,`sourceValue`,`artifactText`,`artifactType`,`artifactUnit`,`artifactValue`,`environmentText`,`environmentType`,`environmentUnit`,`environmentValue`,`responseText`,`responseType`,`responseUnit`,`responseValue`,`measureText`,`measureType`,`measureUnit`,`measureValue`,`state`,`reasoningFramework`) VALUES 
 (28406,15649,'<Fact-14>','gen433','M3 - The driver for a new external device has to be added by a developer within 10 days','ChangeImpactModifiability','Adding a new device',NULL,NULL,NULL,NULL,'developer',NULL,NULL,'The driver for a new external device',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'has to be added',NULL,NULL,NULL,'within 10 days','CostConstraint','days',5.5,NULL,'ChangeImpactModifiabilityRF');
/*!40000 ALTER TABLE `main_scenarios` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_sequencerelation`
--

DROP TABLE IF EXISTS `main_sequencerelation`;
CREATE TABLE `main_sequencerelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) default NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_main_sequencerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_sequencerelation`
--

/*!40000 ALTER TABLE `main_sequencerelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `main_sequencerelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`main_translationrelation`
--

DROP TABLE IF EXISTS `main_translationrelation`;
CREATE TABLE `main_translationrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parentType` varchar(200) default NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_main_translationrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`main_translationrelation`
--

/*!40000 ALTER TABLE `main_translationrelation` DISABLE KEYS */;
INSERT INTO `main_translationrelation` (`uid`,`version`,`fact-id`,`parentType`,`id`,`source`,`parent`,`child`) VALUES 
 (101593,15314,'<Fact-40>','Functionality',NULL,'ArchE','<Fact-27>','<Fact-15>'),
 (101594,15314,'<Fact-41>','Functionality',NULL,'ArchE','<Fact-28>','<Fact-16>'),
 (101595,15314,'<Fact-42>','Functionality',NULL,'ArchE','<Fact-29>','<Fact-17>'),
 (101596,15314,'<Fact-43>','Functionality',NULL,'ArchE','<Fact-30>','<Fact-18>'),
 (101597,15314,'<Fact-44>','Functionality',NULL,'ArchE','<Fact-31>','<Fact-19>'),
 (101598,15314,'<Fact-45>','Functionality',NULL,'ArchE','<Fact-32>','<Fact-20>'),
 (101599,15314,'<Fact-46>','Functionality',NULL,'ArchE','<Fact-33>','<Fact-21>'),
 (101600,15314,'<Fact-47>','Functionality',NULL,'ArchE','<Fact-34>','<Fact-22>'),
 (101601,15314,'<Fact-48>','Functionality',NULL,'ArchE','<Fact-35>','<Fact-23>'),
 (101602,15314,'<Fact-49>','Functionality',NULL,'ArchE','<Fact-36>','<Fact-24>'),
 (101603,15314,'<Fact-50>','Functionality',NULL,'ArchE','<Fact-37>','<Fact-25>'),
 (101604,15314,'<Fact-51>','Functionality',NULL,'ArchE','<Fact-38>','<Fact-26>');
INSERT INTO `main_translationrelation` (`uid`,`version`,`fact-id`,`parentType`,`id`,`source`,`parent`,`child`) VALUES 
 (101605,15314,'<Fact-52>','Scenario',NULL,'User','<Fact-14>','<Fact-26>'),
 (101606,15314,'<Fact-53>','Scenario',NULL,'User','<Fact-7>','<Fact-26>'),
 (101607,15314,'<Fact-54>','Scenario',NULL,'User','<Fact-12>','<Fact-23>'),
 (101608,15314,'<Fact-55>','Scenario',NULL,'User','<Fact-12>','<Fact-22>'),
 (101609,15314,'<Fact-56>','Scenario',NULL,'User','<Fact-13>','<Fact-17>'),
 (101610,15314,'<Fact-57>','Scenario',NULL,'User','<Fact-13>','<Fact-18>'),
 (101611,15314,'<Fact-58>','Scenario',NULL,'User','<Fact-9>','<Fact-21>'),
 (101612,15314,'<Fact-59>','Scenario',NULL,'User','<Fact-11>','<Fact-17>'),
 (101613,15314,'<Fact-60>','Scenario',NULL,'User','<Fact-7>','<Fact-25>'),
 (101614,15314,'<Fact-61>','Scenario',NULL,'User','<Fact-8>','<Fact-20>'),
 (101615,15314,'<Fact-62>','Scenario',NULL,'User','<Fact-8>','<Fact-19>'),
 (101616,15314,'<Fact-63>','Scenario',NULL,'User','<Fact-9>','<Fact-17>'),
 (101617,15314,'<Fact-64>','Scenario',NULL,'User','<Fact-9>','<Fact-18>');
INSERT INTO `main_translationrelation` (`uid`,`version`,`fact-id`,`parentType`,`id`,`source`,`parent`,`child`) VALUES 
 (101618,15314,'<Fact-65>','Scenario',NULL,'User','<Fact-10>','<Fact-21>'),
 (101619,15314,'<Fact-66>','Scenario',NULL,'User','<Fact-10>','<Fact-15>'),
 (101620,15314,'<Fact-67>','Scenario',NULL,'User','<Fact-10>','<Fact-25>'),
 (101621,15314,'<Fact-68>','Scenario',NULL,'User','<Fact-11>','<Fact-22>'),
 (108001,15649,'<Fact-40>','Functionality',NULL,'ArchE','<Fact-27>','<Fact-15>'),
 (108002,15649,'<Fact-41>','Functionality',NULL,'ArchE','<Fact-28>','<Fact-16>'),
 (108003,15649,'<Fact-42>','Functionality',NULL,'ArchE','<Fact-29>','<Fact-17>'),
 (108004,15649,'<Fact-43>','Functionality',NULL,'ArchE','<Fact-30>','<Fact-18>'),
 (108005,15649,'<Fact-44>','Functionality',NULL,'ArchE','<Fact-31>','<Fact-19>'),
 (108006,15649,'<Fact-45>','Functionality',NULL,'ArchE','<Fact-32>','<Fact-20>'),
 (108007,15649,'<Fact-46>','Functionality',NULL,'ArchE','<Fact-33>','<Fact-21>'),
 (108008,15649,'<Fact-47>','Functionality',NULL,'ArchE','<Fact-34>','<Fact-22>'),
 (108009,15649,'<Fact-48>','Functionality',NULL,'ArchE','<Fact-35>','<Fact-23>');
INSERT INTO `main_translationrelation` (`uid`,`version`,`fact-id`,`parentType`,`id`,`source`,`parent`,`child`) VALUES 
 (108010,15649,'<Fact-49>','Functionality',NULL,'ArchE','<Fact-36>','<Fact-24>'),
 (108011,15649,'<Fact-50>','Functionality',NULL,'ArchE','<Fact-37>','<Fact-25>'),
 (108012,15649,'<Fact-51>','Functionality',NULL,'ArchE','<Fact-38>','<Fact-26>'),
 (108013,15649,'<Fact-52>','Scenario',NULL,'User','<Fact-14>','<Fact-26>'),
 (108014,15649,'<Fact-53>','Scenario',NULL,'User','<Fact-7>','<Fact-26>'),
 (108015,15649,'<Fact-54>','Scenario',NULL,'User','<Fact-12>','<Fact-23>'),
 (108016,15649,'<Fact-55>','Scenario',NULL,'User','<Fact-12>','<Fact-22>'),
 (108017,15649,'<Fact-56>','Scenario',NULL,'User','<Fact-13>','<Fact-17>'),
 (108018,15649,'<Fact-57>','Scenario',NULL,'User','<Fact-13>','<Fact-18>'),
 (108019,15649,'<Fact-58>','Scenario',NULL,'User','<Fact-9>','<Fact-21>'),
 (108020,15649,'<Fact-59>','Scenario',NULL,'User','<Fact-11>','<Fact-17>'),
 (108021,15649,'<Fact-60>','Scenario',NULL,'User','<Fact-7>','<Fact-25>'),
 (108022,15649,'<Fact-61>','Scenario',NULL,'User','<Fact-8>','<Fact-20>');
INSERT INTO `main_translationrelation` (`uid`,`version`,`fact-id`,`parentType`,`id`,`source`,`parent`,`child`) VALUES 
 (108023,15649,'<Fact-62>','Scenario',NULL,'User','<Fact-8>','<Fact-19>'),
 (108024,15649,'<Fact-63>','Scenario',NULL,'User','<Fact-9>','<Fact-17>'),
 (108025,15649,'<Fact-64>','Scenario',NULL,'User','<Fact-9>','<Fact-18>'),
 (108026,15649,'<Fact-65>','Scenario',NULL,'User','<Fact-10>','<Fact-21>'),
 (108027,15649,'<Fact-66>','Scenario',NULL,'User','<Fact-10>','<Fact-15>'),
 (108028,15649,'<Fact-67>','Scenario',NULL,'User','<Fact-10>','<Fact-25>'),
 (108029,15649,'<Fact-68>','Scenario',NULL,'User','<Fact-11>','<Fact-22>');
/*!40000 ALTER TABLE `main_translationrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilitylearn_modifiabilitylearnvalues`
--

DROP TABLE IF EXISTS `modifiabilitylearn_modifiabilitylearnvalues`;
CREATE TABLE `modifiabilitylearn_modifiabilitylearnvalues` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` varchar(200) default NULL,
  `value` double default NULL,
  `history1` double default NULL,
  `history2` double default NULL,
  `history3` double default NULL,
  `history4` double default NULL,
  `history5` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilitylearn_modifiabilitylearnvalues` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilitylearn_modifiabilitylearnvalues`
--

/*!40000 ALTER TABLE `modifiabilitylearn_modifiabilitylearnvalues` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilitylearn_modifiabilitylearnvalues` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_arc_relation`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_arc_relation`;
CREATE TABLE `modifiabilityreasoningframeworks_arc_relation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `source` varchar(20) default NULL,
  `target` varchar(20) default NULL,
  `probability` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_arc_relation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_arc_relation`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_arc_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_arc_relation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_node_affected`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_node_affected`;
CREATE TABLE `modifiabilityreasoningframeworks_node_affected` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) default NULL,
  `responsibilityId` varchar(20) default NULL,
  `nodeId` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_node_affected` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_node_affected`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_node_affected` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_node_affected` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_node_responsibility`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_node_responsibility`;
CREATE TABLE `modifiabilityreasoningframeworks_node_responsibility` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `name` text,
  `cost` double default NULL,
  `cumulativeprob` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_node_responsibility` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_node_responsibility`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_node_responsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_node_responsibility` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_p_abstractiontranslator`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_abstractiontranslator`;
CREATE TABLE `modifiabilityreasoningframeworks_p_abstractiontranslator` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_p_abstractiontranslator` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_p_abstractiontranslator`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_abstractiontranslator` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_abstractiontranslator` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_p_costofchange`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_costofchange`;
CREATE TABLE `modifiabilityreasoningframeworks_p_costofchange` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_p_costofchange` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_p_costofchange`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_costofchange` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_costofchange` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_p_encapsulationlevel`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_encapsulationlevel`;
CREATE TABLE `modifiabilityreasoningframeworks_p_encapsulationlevel` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `modifiabilityreasoningframeworks_p_encapsulationlevel_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_p_encapsulationlevel`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_encapsulationlevel` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_encapsulationlevel` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_p_interfacerouter`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_interfacerouter`;
CREATE TABLE `modifiabilityreasoningframeworks_p_interfacerouter` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_p_interfacerouter` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_p_interfacerouter`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_interfacerouter` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_interfacerouter` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_p_preparedforchange`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_preparedforchange`;
CREATE TABLE `modifiabilityreasoningframeworks_p_preparedforchange` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `modifiabilityreasoningframeworks_p_preparedforchange_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_p_preparedforchange`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_preparedforchange` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_preparedforchange` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_p_probabilityincoming`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_probabilityincoming`;
CREATE TABLE `modifiabilityreasoningframeworks_p_probabilityincoming` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `modifiabilityreasoningframeworks_p_probabilityincoming_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_p_probabilityincoming`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_probabilityincoming` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_probabilityincoming` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_p_probabilityoutgoing`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_p_probabilityoutgoing`;
CREATE TABLE `modifiabilityreasoningframeworks_p_probabilityoutgoing` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `modifiabilityreasoningframeworks_p_probabilityoutgoing_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_p_probabilityoutgoing`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_probabilityoutgoing` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_p_probabilityoutgoing` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_response_measures`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_response_measures`;
CREATE TABLE `modifiabilityreasoningframeworks_response_measures` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) default NULL,
  `responseMeasure` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_modifiabilityreasoningframeworks_response_measures` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_response_measures`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_response_measures` DISABLE KEYS */;
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_response_measures` ENABLE KEYS */;


--
-- Table structure for table `arche`.`modifiabilityreasoningframeworks_tactics`
--

DROP TABLE IF EXISTS `modifiabilityreasoningframeworks_tactics`;
CREATE TABLE `modifiabilityreasoningframeworks_tactics` (
  `type` varchar(30) NOT NULL default '',
  `node_id` int(10) unsigned NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `sol_num` int(10) unsigned NOT NULL default '0',
  `ToolName` varchar(100) NOT NULL,
  `loc_id` int(10) unsigned default NULL,
  PRIMARY KEY  (`type`,`node_id`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`modifiabilityreasoningframeworks_tactics`
--

/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_tactics` DISABLE KEYS */;
INSERT INTO `modifiabilityreasoningframeworks_tactics` (`type`,`node_id`,`version`,`sol_num`,`ToolName`,`loc_id`) VALUES 
 ('LOC_ENC',684,2014,0,'ModelSolver',0),
 ('LOC_ENC',695,2014,0,'ModelSolver',0),
 ('LOC_ENC',700,2014,0,'ModelSolver',0),
 ('LOC_ENC',703,2014,0,'ModelSolver',0);
/*!40000 ALTER TABLE `modifiabilityreasoningframeworks_tactics` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_dependency`
--

DROP TABLE IF EXISTS `patterns_dependency`;
CREATE TABLE `patterns_dependency` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `pattern` varchar(20) default NULL,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_dependency` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_dependency`
--

/*!40000 ALTER TABLE `patterns_dependency` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_dependency` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_interfacerealization`
--

DROP TABLE IF EXISTS `patterns_interfacerealization`;
CREATE TABLE `patterns_interfacerealization` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `pattern` varchar(20) default NULL,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_interfacerealization` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_interfacerealization`
--

/*!40000 ALTER TABLE `patterns_interfacerealization` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_interfacerealization` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_isarelation`
--

DROP TABLE IF EXISTS `patterns_isarelation`;
CREATE TABLE `patterns_isarelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `pattern` varchar(20) default NULL,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_isarelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_isarelation`
--

/*!40000 ALTER TABLE `patterns_isarelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_isarelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_pattern`
--

DROP TABLE IF EXISTS `patterns_pattern`;
CREATE TABLE `patterns_pattern` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `group` text,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_pattern` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_pattern`
--

/*!40000 ALTER TABLE `patterns_pattern` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_pattern` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_patternconnector`
--

DROP TABLE IF EXISTS `patterns_patternconnector`;
CREATE TABLE `patterns_patternconnector` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `type` text,
  `owner` varchar(20) default NULL,
  `valueAbs` text,
  `valueFactor` text,
  `pattern` varchar(20) default NULL,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patternconnector` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_patternconnector`
--

/*!40000 ALTER TABLE `patterns_patternconnector` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patternconnector` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_patterncontainer`
--

DROP TABLE IF EXISTS `patterns_patterncontainer`;
CREATE TABLE `patterns_patterncontainer` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `pattern` varchar(20) default NULL,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patterncontainer` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_patterncontainer`
--

/*!40000 ALTER TABLE `patterns_patterncontainer` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patterncontainer` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_patternelement`
--

DROP TABLE IF EXISTS `patterns_patternelement`;
CREATE TABLE `patterns_patternelement` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `pattern` varchar(20) default NULL,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patternelement` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_patternelement`
--

/*!40000 ALTER TABLE `patterns_patternelement` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patternelement` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_patternelementinterface`
--

DROP TABLE IF EXISTS `patterns_patternelementinterface`;
CREATE TABLE `patterns_patternelementinterface` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `pattern` varchar(20) default NULL,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patternelementinterface` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_patternelementinterface`
--

/*!40000 ALTER TABLE `patterns_patternelementinterface` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patternelementinterface` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_patternitemproperty`
--

DROP TABLE IF EXISTS `patterns_patternitemproperty`;
CREATE TABLE `patterns_patternitemproperty` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `owner` varchar(20) default NULL,
  `valueAbs` text,
  `valueFactor` text,
  `pattern` varchar(20) default NULL,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_patternitemproperty` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_patternitemproperty`
--

/*!40000 ALTER TABLE `patterns_patternitemproperty` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_patternitemproperty` ENABLE KEYS */;


--
-- Table structure for table `arche`.`patterns_refinement`
--

DROP TABLE IF EXISTS `patterns_refinement`;
CREATE TABLE `patterns_refinement` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `pattern` varchar(20) default NULL,
  `name` text,
  `description` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_patterns_refinement` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`patterns_refinement`
--

/*!40000 ALTER TABLE `patterns_refinement` DISABLE KEYS */;
/*!40000 ALTER TABLE `patterns_refinement` ENABLE KEYS */;


--
-- Table structure for table `arche`.`performancereasoningframeworks_p_executiontime`
--

DROP TABLE IF EXISTS `performancereasoningframeworks_p_executiontime`;
CREATE TABLE `performancereasoningframeworks_p_executiontime` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_performancereasoningframeworks_p_executiontime` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`performancereasoningframeworks_p_executiontime`
--

/*!40000 ALTER TABLE `performancereasoningframeworks_p_executiontime` DISABLE KEYS */;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_executiontime` ENABLE KEYS */;


--
-- Table structure for table `arche`.`performancereasoningframeworks_p_mutualexclusion`
--

DROP TABLE IF EXISTS `performancereasoningframeworks_p_mutualexclusion`;
CREATE TABLE `performancereasoningframeworks_p_mutualexclusion` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_performancereasoningframeworks_p_mutualexclusion` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`performancereasoningframeworks_p_mutualexclusion`
--

/*!40000 ALTER TABLE `performancereasoningframeworks_p_mutualexclusion` DISABLE KEYS */;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_mutualexclusion` ENABLE KEYS */;


--
-- Table structure for table `arche`.`performancereasoningframeworks_p_sharedresourceasked`
--

DROP TABLE IF EXISTS `performancereasoningframeworks_p_sharedresourceasked`;
CREATE TABLE `performancereasoningframeworks_p_sharedresourceasked` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_performancereasoningframeworks_p_sharedresourceasked` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`performancereasoningframeworks_p_sharedresourceasked`
--

/*!40000 ALTER TABLE `performancereasoningframeworks_p_sharedresourceasked` DISABLE KEYS */;
/*!40000 ALTER TABLE `performancereasoningframeworks_p_sharedresourceasked` ENABLE KEYS */;


--
-- Table structure for table `arche`.`planner_c_addfunction`
--

DROP TABLE IF EXISTS `planner_c_addfunction`;
CREATE TABLE `planner_c_addfunction` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `functionID` text,
  `description` text,
  `state` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_planner_c_addfunction` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`planner_c_addfunction`
--

/*!40000 ALTER TABLE `planner_c_addfunction` DISABLE KEYS */;
/*!40000 ALTER TABLE `planner_c_addfunction` ENABLE KEYS */;


--
-- Table structure for table `arche`.`planner_c_addresponsibilityrelation`
--

DROP TABLE IF EXISTS `planner_c_addresponsibilityrelation`;
CREATE TABLE `planner_c_addresponsibilityrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `type` text,
  `state` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_planner_c_addresponsibilityrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`planner_c_addresponsibilityrelation`
--

/*!40000 ALTER TABLE `planner_c_addresponsibilityrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `planner_c_addresponsibilityrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`planner_c_addscenario`
--

DROP TABLE IF EXISTS `planner_c_addscenario`;
CREATE TABLE `planner_c_addscenario` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `description` text,
  `quality` varchar(200) default NULL,
  `stimulusText` text,
  `stimulusType` varchar(200) default NULL,
  `stimulusUnit` varchar(200) default NULL,
  `stimulusValue` double default NULL,
  `sourceText` text,
  `sourceType` varchar(200) default NULL,
  `sourceUnit` varchar(200) default NULL,
  `sourceValue` double default NULL,
  `artifactText` text,
  `artifactType` varchar(200) default NULL,
  `artifactUnit` varchar(200) default NULL,
  `artifactValue` double default NULL,
  `environmentText` text,
  `environmentType` varchar(200) default NULL,
  `environmentUnit` varchar(200) default NULL,
  `environmentValue` double default NULL,
  `responseText` text,
  `responseType` varchar(200) default NULL,
  `responseUnit` varchar(200) default NULL,
  `responseValue` double default NULL,
  `measureText` text,
  `measureType` varchar(200) default NULL,
  `measureUnit` varchar(200) default NULL,
  `measureValue` double default NULL,
  `state` text,
  `reasoningFramework` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_planner_c_addscenario` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`planner_c_addscenario`
--

/*!40000 ALTER TABLE `planner_c_addscenario` DISABLE KEYS */;
INSERT INTO `planner_c_addscenario` (`uid`,`version`,`fact-id`,`description`,`quality`,`stimulusText`,`stimulusType`,`stimulusUnit`,`stimulusValue`,`sourceText`,`sourceType`,`sourceUnit`,`sourceValue`,`artifactText`,`artifactType`,`artifactUnit`,`artifactValue`,`environmentText`,`environmentType`,`environmentUnit`,`environmentValue`,`responseText`,`responseType`,`responseUnit`,`responseValue`,`measureText`,`measureType`,`measureUnit`,`measureValue`,`state`,`reasoningFramework`) VALUES 
 (81,13250,'<Fact-43>','The driver for a new external device has to be added by a developer within 10 days','Modifiability','Adding a new device',NULL,NULL,NULL,NULL,'developer',NULL,NULL,'The driver for a new external device',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'has to be added',NULL,NULL,NULL,'within 10 days','CostConstraint','days',10,'final',NULL),
 (110,15314,'<Fact-39>','The driver for a new external device has to be added by a developer within 10 days','Modifiability','Adding a new device',NULL,NULL,NULL,NULL,'developer',NULL,NULL,'The driver for a new external device',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'has to be added',NULL,NULL,NULL,'within 10 days','CostConstraint','days',10,'final',NULL);
/*!40000 ALTER TABLE `planner_c_addscenario` ENABLE KEYS */;


--
-- Table structure for table `arche`.`planner_c_addtranslationrelation`
--

DROP TABLE IF EXISTS `planner_c_addtranslationrelation`;
CREATE TABLE `planner_c_addtranslationrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  `type` text,
  `state` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_planner_c_addtranslationrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`planner_c_addtranslationrelation`
--

/*!40000 ALTER TABLE `planner_c_addtranslationrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `planner_c_addtranslationrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmamodel_p_latency`
--

DROP TABLE IF EXISTS `rmamodel_p_latency`;
CREATE TABLE `rmamodel_p_latency` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `utilization` text,
  `value` text,
  `time_blocked` text,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_rmamodel_p_latency` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmamodel_p_latency`
--

/*!40000 ALTER TABLE `rmamodel_p_latency` DISABLE KEYS */;
INSERT INTO `rmamodel_p_latency` (`uid`,`version`,`fact-id`,`utilization`,`value`,`time_blocked`,`owner`,`source`,`status`) VALUES 
 (238,11152,'<Fact-1602>','0.010416666666666666','250.0','0.0','<Fact-1583>',NULL,NULL),
 (239,11152,'<Fact-1603>','0.010416666666666666','500.0','0.0','<Fact-1598>',NULL,NULL),
 (240,11152,'<Fact-1604>','0.010416666666666666','5050.0','0.0','<Fact-1586>',NULL,NULL);
/*!40000 ALTER TABLE `rmamodel_p_latency` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmamodel_subtask`
--

DROP TABLE IF EXISTS `rmamodel_subtask`;
CREATE TABLE `rmamodel_subtask` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `task` varchar(20) default NULL,
  `scenario` varchar(20) default NULL,
  `responsibility` varchar(20) default NULL,
  `MutualExclusion` varchar(200) default NULL,
  `SharedResourceID` varchar(20) default NULL,
  `taskType` varchar(200) default NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `priority` text,
  `source` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_rmamodel_subtask` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmamodel_subtask`
--

/*!40000 ALTER TABLE `rmamodel_subtask` DISABLE KEYS */;
INSERT INTO `rmamodel_subtask` (`uid`,`version`,`fact-id`,`name`,`task`,`scenario`,`responsibility`,`MutualExclusion`,`SharedResourceID`,`taskType`,`taskValue`,`executionTime`,`latency`,`priority`,`source`) VALUES 
 (949,11152,'<Fact-1585>','SubTask-UnitOfConcurrency-Manage External Devices','<Fact-1583>','<Fact-46>','<Fact-65>','FALSE','<Fact-0>',NULL,'1000.0','250.0',NULL,'498',NULL),
 (950,11152,'<Fact-1588>','SubTask-UnitOfConcurrency-Create user Profile','<Fact-1586>',NULL,'<Fact-56>','FALSE','<Fact-0>',NULL,'500000','150.0',NULL,'500',NULL),
 (951,11152,'<Fact-1589>','SubTask-UnitOfConcurrency-Attach to Model','<Fact-1586>',NULL,'<Fact-58>','FALSE','<Fact-0>',NULL,'500000','100.0',NULL,'500',NULL),
 (952,11152,'<Fact-1590>','SubTask-UnitOfConcurrency-Modify user Profile','<Fact-1586>',NULL,'<Fact-57>','FALSE','<Fact-0>',NULL,'500000','150.0',NULL,'500',NULL),
 (953,11152,'<Fact-1591>','SubTask-UnitOfConcurrency-Handle user Interactions','<Fact-1586>',NULL,'<Fact-60>','FALSE','<Fact-0>',NULL,'500000','500.0',NULL,'500',NULL),
 (954,11152,'<Fact-1592>','SubTask-UnitOfConcurrency-Show Itinerary','<Fact-1586>',NULL,'<Fact-54>','FALSE','<Fact-0>',NULL,'500000','450.0',NULL,'500',NULL);
INSERT INTO `rmamodel_subtask` (`uid`,`version`,`fact-id`,`name`,`task`,`scenario`,`responsibility`,`MutualExclusion`,`SharedResourceID`,`taskType`,`taskValue`,`executionTime`,`latency`,`priority`,`source`) VALUES 
 (955,11152,'<Fact-1593>','SubTask-UnitOfConcurrency-Locate Service','<Fact-1586>',NULL,'<Fact-63>','FALSE','<Fact-0>',NULL,'500000','1000.0',NULL,'500',NULL),
 (956,11152,'<Fact-1594>','SubTask-UnitOfConcurrency-Register Views','<Fact-1586>',NULL,'<Fact-59>','FALSE','<Fact-0>',NULL,'500000','100.0',NULL,'500',NULL),
 (957,11152,'<Fact-1595>','SubTask-UnitOfConcurrency-Manage Itinerary','<Fact-1586>',NULL,'<Fact-64>','FALSE','<Fact-0>',NULL,'500000','800.0',NULL,'500',NULL),
 (958,11152,'<Fact-1596>','SubTask-UnitOfConcurrency-Query for Data','<Fact-1586>',NULL,'<Fact-62>','FALSE','<Fact-0>',NULL,'500000','700.0',NULL,'500',NULL),
 (959,11152,'<Fact-1597>','SubTask-UnitOfConcurrency-Save Data','<Fact-1586>',NULL,'<Fact-61>','FALSE','<Fact-0>',NULL,'500000','600.0',NULL,'500',NULL),
 (960,11152,'<Fact-1600>','SubTask-UnitOfConcurrency-Manage External Devices','<Fact-1598>','<Fact-45>','<Fact-65>','FALSE','<Fact-0>',NULL,'5000.0','250.0',NULL,'499',NULL);
/*!40000 ALTER TABLE `rmamodel_subtask` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmamodel_task`
--

DROP TABLE IF EXISTS `rmamodel_task`;
CREATE TABLE `rmamodel_task` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `scenario` varchar(20) default NULL,
  `stimulusType` varchar(200) default NULL,
  `stimulusValue` text,
  `taskType` varchar(200) default NULL,
  `taskValue` text,
  `executionTime` text,
  `latency` text,
  `time_blocked` text,
  `priority` text,
  `source` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_rmamodel_task` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmamodel_task`
--

/*!40000 ALTER TABLE `rmamodel_task` DISABLE KEYS */;
INSERT INTO `rmamodel_task` (`uid`,`version`,`fact-id`,`name`,`scenario`,`stimulusType`,`stimulusValue`,`taskType`,`taskValue`,`executionTime`,`latency`,`time_blocked`,`priority`,`source`) VALUES 
 (136,9949,'<Fact-2512>','Task-Thread-Background',NULL,'periodic','5000','HardDeadline','5000','7195.0',NULL,NULL,'500',NULL),
 (374,11152,'<Fact-1583>','Task-Thread-A view wishes to attach to the model under normal conditions and do so in under 1 second.','<Fact-46>','sporadic','120000.0','HardDeadline','1000.0','250.0','250.0','0.0','498',NULL),
 (375,11152,'<Fact-1586>','Task-Thread-Background',NULL,'periodic','500000','HardDeadline','500000','4550.0',NULL,NULL,'500',NULL),
 (376,11152,'<Fact-1598>','Task-Thread-The system has to manage the external devices under normal load and handle the operations in under 5 seconds.','<Fact-45>','periodic','30000.0','HardDeadline','5000.0','250.0','500.0','0.0','499',NULL);
/*!40000 ALTER TABLE `rmamodel_task` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmaperformancerf_p_executiontime`
--

DROP TABLE IF EXISTS `rmaperformancerf_p_executiontime`;
CREATE TABLE `rmaperformancerf_p_executiontime` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` double default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_p_executiontime` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmaperformancerf_p_executiontime`
--

/*!40000 ALTER TABLE `rmaperformancerf_p_executiontime` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_p_executiontime` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmaperformancerf_p_shared`
--

DROP TABLE IF EXISTS `rmaperformancerf_p_shared`;
CREATE TABLE `rmaperformancerf_p_shared` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` varchar(200) default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_p_shared` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmaperformancerf_p_shared`
--

/*!40000 ALTER TABLE `rmaperformancerf_p_shared` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_p_shared` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmaperformancerf_p_taskpriority`
--

DROP TABLE IF EXISTS `rmaperformancerf_p_taskpriority`;
CREATE TABLE `rmaperformancerf_p_taskpriority` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `value` int(10) default NULL,
  `owner` varchar(20) default NULL,
  `source` varchar(200) default NULL,
  `status` varchar(200) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_p_taskpriority` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmaperformancerf_p_taskpriority`
--

/*!40000 ALTER TABLE `rmaperformancerf_p_taskpriority` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_p_taskpriority` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmaperformancerf_resource`
--

DROP TABLE IF EXISTS `rmaperformancerf_resource`;
CREATE TABLE `rmaperformancerf_resource` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `name` text,
  `source` text,
  `status` int(10) default NULL,
  `shared` text,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_resource` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmaperformancerf_resource`
--

/*!40000 ALTER TABLE `rmaperformancerf_resource` DISABLE KEYS */;
INSERT INTO `rmaperformancerf_resource` (`uid`,`version`,`fact-id`,`id`,`name`,`source`,`status`,`shared`) VALUES 
 (276,12805,'<Fact-151>',NULL,'(R) Query for Data','ArchE',0,NULL),
 (277,12805,'<Fact-152>',NULL,'(R) Register Views','ArchE',0,NULL),
 (278,12805,'<Fact-153>',NULL,'(R) Manage Itinerary','ArchE',0,NULL),
 (279,12805,'<Fact-154>',NULL,'(R) Modify user Profile','ArchE',0,NULL),
 (280,12805,'<Fact-155>',NULL,'(R) Handle user Interactions','ArchE',0,NULL),
 (281,12805,'<Fact-156>',NULL,'(R) Save Data','ArchE',0,NULL),
 (282,12805,'<Fact-157>',NULL,'(R) Show Itinerary','ArchE',0,NULL),
 (283,12805,'<Fact-158>',NULL,'(R) Locate Service','ArchE',0,NULL),
 (284,12805,'<Fact-159>',NULL,'(R) Manage External Devices','ArchE',0,NULL),
 (285,12805,'<Fact-160>',NULL,'(R) Attach to Model','ArchE',0,NULL),
 (286,12805,'<Fact-161>',NULL,'(R) Create user Profile','ArchE',0,NULL);
/*!40000 ALTER TABLE `rmaperformancerf_resource` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmaperformancerf_resourceusagerelation`
--

DROP TABLE IF EXISTS `rmaperformancerf_resourceusagerelation`;
CREATE TABLE `rmaperformancerf_resourceusagerelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `source` text,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_resourceusagerelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmaperformancerf_resourceusagerelation`
--

/*!40000 ALTER TABLE `rmaperformancerf_resourceusagerelation` DISABLE KEYS */;
INSERT INTO `rmaperformancerf_resourceusagerelation` (`uid`,`version`,`fact-id`,`id`,`source`,`parent`,`child`) VALUES 
 (358,12805,'<Fact-162>',NULL,'ArchE','<Fact-150>','<Fact-151>'),
 (359,12805,'<Fact-164>',NULL,'ArchE','<Fact-150>','<Fact-153>'),
 (360,12805,'<Fact-165>',NULL,'ArchE','<Fact-150>','<Fact-154>'),
 (361,12805,'<Fact-166>',NULL,'ArchE','<Fact-150>','<Fact-155>'),
 (362,12805,'<Fact-167>',NULL,'ArchE','<Fact-150>','<Fact-156>'),
 (363,12805,'<Fact-168>',NULL,'ArchE','<Fact-150>','<Fact-157>'),
 (364,12805,'<Fact-169>',NULL,'ArchE','<Fact-150>','<Fact-158>'),
 (365,12805,'<Fact-170>',NULL,'ArchE','<Fact-150>','<Fact-159>'),
 (366,12805,'<Fact-172>',NULL,'ArchE','<Fact-150>','<Fact-161>'),
 (367,12805,'<Fact-210>',NULL,'ArchE','<Fact-149>','<Fact-152>'),
 (368,12805,'<Fact-247>',NULL,'ArchE','<Fact-149>','<Fact-160>'),
 (369,12805,'<Fact-323>',NULL,'ArchE','<Fact-285>','<Fact-152>');
/*!40000 ALTER TABLE `rmaperformancerf_resourceusagerelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmaperformancerf_thread`
--

DROP TABLE IF EXISTS `rmaperformancerf_thread`;
CREATE TABLE `rmaperformancerf_thread` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `name` text,
  `period` double default NULL,
  `source` text,
  `status` int(10) default NULL,
  `scenario` text,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_thread` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmaperformancerf_thread`
--

/*!40000 ALTER TABLE `rmaperformancerf_thread` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_thread` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmaperformancerf_threadtouocrelation`
--

DROP TABLE IF EXISTS `rmaperformancerf_threadtouocrelation`;
CREATE TABLE `rmaperformancerf_threadtouocrelation` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `source` text,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_threadtouocrelation` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmaperformancerf_threadtouocrelation`
--

/*!40000 ALTER TABLE `rmaperformancerf_threadtouocrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_threadtouocrelation` ENABLE KEYS */;


--
-- Table structure for table `arche`.`rmaperformancerf_unitofconcurrency`
--

DROP TABLE IF EXISTS `rmaperformancerf_unitofconcurrency`;
CREATE TABLE `rmaperformancerf_unitofconcurrency` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` int(10) default NULL,
  `name` text,
  `executionTime` double default NULL,
  `source` text,
  `status` int(10) default NULL,
  `responsibility` text,
  PRIMARY KEY  (`uid`),
  KEY `version` (`version`),
  KEY `fact-id` (`fact-id`),
  CONSTRAINT `FK_rmaperformancerf_unitofconcurrency` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`rmaperformancerf_unitofconcurrency`
--

/*!40000 ALTER TABLE `rmaperformancerf_unitofconcurrency` DISABLE KEYS */;
/*!40000 ALTER TABLE `rmaperformancerf_unitofconcurrency` ENABLE KEYS */;


--
-- Table structure for table `arche`.`seeker_evaluationresult`
--

DROP TABLE IF EXISTS `seeker_evaluationresult`;
CREATE TABLE `seeker_evaluationresult` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `scenario` varchar(20) default NULL,
  `tacticScenario` varchar(20) default NULL,
  `reasoningFramework` varchar(200) default NULL,
  `tactic` varchar(200) default NULL,
  `tacticDescription` text,
  `result` double default NULL,
  `utility` double default NULL,
  `change` double default NULL,
  `relevance` double default NULL,
  `tacticId` varchar(200) default NULL,
  `index` int(10) default NULL,
  `nresult` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_seeker_evaluationresult` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`seeker_evaluationresult`
--

/*!40000 ALTER TABLE `seeker_evaluationresult` DISABLE KEYS */;
/*!40000 ALTER TABLE `seeker_evaluationresult` ENABLE KEYS */;


--
-- Table structure for table `arche`.`tactics`
--

DROP TABLE IF EXISTS `tactics`;
CREATE TABLE `tactics` (
  `type` varchar(30) NOT NULL default '',
  `node_id` int(10) unsigned NOT NULL,
  `name` int(10) unsigned default NULL,
  `version` int(10) unsigned NOT NULL default '0',
  `level` int(10) unsigned NOT NULL default '0',
  PRIMARY KEY  (`type`,`node_id`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `arche`.`tactics`
--

/*!40000 ALTER TABLE `tactics` DISABLE KEYS */;
/*!40000 ALTER TABLE `tactics` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_interface`
--

DROP TABLE IF EXISTS `test_interface`;
CREATE TABLE `test_interface` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `moduleName` text,
  `costOfChange` double NOT NULL default '0',
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_interface` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_interface`
--

/*!40000 ALTER TABLE `test_interface` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_interface` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_leave_dependencies`
--

DROP TABLE IF EXISTS `test_leave_dependencies`;
CREATE TABLE `test_leave_dependencies` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_leave_dependencies` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_leave_dependencies`
--

/*!40000 ALTER TABLE `test_leave_dependencies` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_leave_dependencies` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_model_arcs`
--

DROP TABLE IF EXISTS `test_model_arcs`;
CREATE TABLE `test_model_arcs` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  `probability` double default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_model_arcs` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_model_arcs`
--

/*!40000 ALTER TABLE `test_model_arcs` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_model_arcs` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_model_nodes`
--

DROP TABLE IF EXISTS `test_model_nodes`;
CREATE TABLE `test_model_nodes` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `node` text,
  `costOfChange` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_model_nodes` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_model_nodes`
--

/*!40000 ALTER TABLE `test_model_nodes` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_model_nodes` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_model_nodes_affected`
--

DROP TABLE IF EXISTS `test_model_nodes_affected`;
CREATE TABLE `test_model_nodes_affected` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_model_nodes_affected` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_model_nodes_affected`
--

/*!40000 ALTER TABLE `test_model_nodes_affected` DISABLE KEYS */;
INSERT INTO `test_model_nodes_affected` (`uid`,`version`,`fact-id`,`parent`,`child`) VALUES 
 (1,2118,'T-8256483','Modifiability 1','(M) C'),
 (2,2118,'T-08935487','Modifiability 1','(M) B'),
 (3,2118,'T-863758','Modifiability 2','(M) E'),
 (4,2118,'T-9782544648','Modifiability 3','(M) EA'),
 (6,3903,'T-8256483','Modifiability 1','(M) C'),
 (7,3903,'T-08935487','Modifiability 1','(M) B'),
 (8,3903,'T-863758','Modifiability 2','(M) E'),
 (9,3903,'T-9782544648','Modifiability 3','(M) EA'),
 (11,2607,'T-8256483','Modifiability 1','(M) C'),
 (12,2607,'T-08935487','Modifiability 1','(M) B'),
 (13,2607,'T-863758','Modifiability 2','(M) New Name'),
 (14,2607,'T-9782544648','Modifiability 3','(M) EA'),
 (16,2195,'T-8256483','Modifiability 1','(M) C'),
 (17,2195,'T-08935487','Modifiability 1','(M) B'),
 (18,2195,'T-863758','Modifiability 2','(M) EA'),
 (19,2195,'T-9782544648','Modifiability 3','(M) EA'),
 (20,2195,'T-9887989','Modifiability 2','(M) EBA'),
 (21,2195,'T-35284538732','Modifiability 2','(M) EBB'),
 (22,4478,'T-8256483','Modifiability 1','(M) C');
INSERT INTO `test_model_nodes_affected` (`uid`,`version`,`fact-id`,`parent`,`child`) VALUES 
 (23,4478,'T-08935487','Modifiability 1','(M) B'),
 (24,4478,'T-863758','Modifiability 2','(M) EA'),
 (25,4478,'T-9782544648','Modifiability 3','(M) EA'),
 (26,4478,'T-9887989','Modifiability 2','(M) EBA'),
 (27,4478,'T-35284538732','Modifiability 2','(M) EBB'),
 (28,4641,'T-8256483','Modifiability 1','(M) C'),
 (29,4641,'T-08935487','Modifiability 1','(M) B'),
 (30,4641,'T-863758','Modifiability 2','(M) New Name'),
 (31,4641,'T-9782544648','Modifiability 3','(M) EA'),
 (32,4667,'T-8256483','Modifiability 1','(M) C'),
 (33,4667,'T-08935487','Modifiability 1','(M) B'),
 (34,4667,'T-863758','Modifiability 2','(M) New Name'),
 (35,4667,'T-9782544648','Modifiability 3','(M) EA');
/*!40000 ALTER TABLE `test_model_nodes_affected` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_module`
--

DROP TABLE IF EXISTS `test_module`;
CREATE TABLE `test_module` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `costOfChange` double default NULL,
  `name` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_module` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_module`
--

/*!40000 ALTER TABLE `test_module` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_module` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_module_dependencies`
--

DROP TABLE IF EXISTS `test_module_dependencies`;
CREATE TABLE `test_module_dependencies` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  `childType` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_module_dependencies` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_module_dependencies`
--

/*!40000 ALTER TABLE `test_module_dependencies` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_module_dependencies` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_module_responsibilities`
--

DROP TABLE IF EXISTS `test_module_responsibilities`;
CREATE TABLE `test_module_responsibilities` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `module` text,
  `responsibility` text,
  `type` varchar(50) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_module_responsibilities` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_module_responsibilities`
--

/*!40000 ALTER TABLE `test_module_responsibilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_module_responsibilities` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_responsibilities`
--

DROP TABLE IF EXISTS `test_responsibilities`;
CREATE TABLE `test_responsibilities` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_responsibilities` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_responsibilities`
--

/*!40000 ALTER TABLE `test_responsibilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `test_responsibilities` ENABLE KEYS */;


--
-- Table structure for table `arche`.`test_responsibility_refinement`
--

DROP TABLE IF EXISTS `test_responsibility_refinement`;
CREATE TABLE `test_responsibility_refinement` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `parent` text,
  `child` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `FK_test_responsibility_refinement` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`test_responsibility_refinement`
--

/*!40000 ALTER TABLE `test_responsibility_refinement` DISABLE KEYS */;
INSERT INTO `test_responsibility_refinement` (`uid`,`version`,`fact-id`,`parent`,`child`) VALUES 
 (1,2118,'T-53274836','A','AA'),
 (2,2118,'T-2656478','A','AB'),
 (3,2118,'T-36384823','A','Ac'),
 (4,2118,'T-238764237','AA','AAA'),
 (5,2118,'T-8783276372','AA','AAB'),
 (6,2118,'T-287328787','Ac','ACA'),
 (7,2118,'T-238742987','Ac','ACB'),
 (8,2118,'T-87676526521','E','EA'),
 (9,2118,'T-537483264','E','EB'),
 (10,2118,'T-7676767123','EB','EBA'),
 (11,2118,'T-236276476','EB','EBB'),
 (56,2195,'T-53274836','A','AA'),
 (57,2195,'T-2656478','A','AB'),
 (58,2195,'T-36384823','A','Ac'),
 (59,2195,'T-238764237','AA','AAA'),
 (60,2195,'T-8783276372','AA','AAB'),
 (61,2195,'T-287328787','Ac','ACA'),
 (62,2195,'T-238742987','Ac','ACB'),
 (63,2195,'T-87676526521','E','EA'),
 (64,2195,'T-537483264','E','EB'),
 (65,2195,'T-7676767123','EB','EBA'),
 (66,2195,'T-236276476','EB','EBB'),
 (67,2607,'T-53274836','A','AA'),
 (68,2607,'T-2656478','A','AB'),
 (69,2607,'T-36384823','A','Ac'),
 (70,2607,'T-238764237','AA','AAA'),
 (71,2607,'T-8783276372','AA','AAB');
INSERT INTO `test_responsibility_refinement` (`uid`,`version`,`fact-id`,`parent`,`child`) VALUES 
 (72,2607,'T-287328787','Ac','ACA'),
 (73,2607,'T-238742987','Ac','ACB'),
 (74,2607,'T-87676526521','E','EA'),
 (75,2607,'T-537483264','E','EB'),
 (76,2607,'T-7676767123','EB','EBA'),
 (77,2607,'T-236276476','EB','EBB'),
 (78,2607,'T-7645276452','EA','EA - remaining'),
 (79,2607,'T-17632447825','EA','EA - extracted'),
 (80,2607,'T-7284627839','EBA','EBA - remaining'),
 (81,2607,'T-1653284652','EBA','EBA - extracted'),
 (82,2607,'T-4283621454','EBB','EBB - remaining'),
 (83,2607,'T-2648265485','EBB','EBB - extracted'),
 (84,3903,'T-53274836','A','AA'),
 (85,3903,'T-2656478','A','AB'),
 (86,3903,'T-36384823','A','Ac'),
 (87,3903,'T-238764237','AA','AAA'),
 (88,3903,'T-8783276372','AA','AAB'),
 (89,3903,'T-287328787','Ac','ACA'),
 (90,3903,'T-238742987','Ac','ACB'),
 (91,3903,'T-87676526521','E','EA'),
 (92,3903,'T-537483264','E','EB'),
 (93,3903,'T-7676767123','EB','EBA'),
 (94,3903,'T-236276476','EB','EBB'),
 (95,3903,'T-7645276452','EA','EA - remaining');
INSERT INTO `test_responsibility_refinement` (`uid`,`version`,`fact-id`,`parent`,`child`) VALUES 
 (96,3903,'T-17632447825','EA','EA - extracted'),
 (97,3903,'T-7284627839','EBA','EBA - remaining'),
 (98,3903,'T-1653284652','EBA','EBA - extracted'),
 (99,3903,'T-4283621454','EBB','EBB - remaining'),
 (100,3903,'T-2648265485','EBB','EBB - extracted'),
 (118,3903,'T-08658365','B','B - remaining'),
 (119,3903,'T-983554839','B','B - extracted'),
 (120,3903,'T-27846583','C','C - extracted'),
 (121,3903,'T-9264859','C','C - remaining'),
 (122,4478,'T-53274836','A','AA'),
 (123,4478,'T-2656478','A','AB'),
 (124,4478,'T-36384823','A','Ac'),
 (125,4478,'T-238764237','AA','AAA'),
 (126,4478,'T-8783276372','AA','AAB'),
 (127,4478,'T-287328787','Ac','ACA'),
 (128,4478,'T-238742987','Ac','ACB'),
 (129,4478,'T-87676526521','E','EA'),
 (130,4478,'T-537483264','E','EB'),
 (131,4478,'T-7676767123','EB','EBA'),
 (132,4478,'T-236276476','EB','EBB'),
 (133,4641,'T-53274836','A','AA'),
 (134,4641,'T-2656478','A','AB'),
 (135,4641,'T-36384823','A','Ac');
INSERT INTO `test_responsibility_refinement` (`uid`,`version`,`fact-id`,`parent`,`child`) VALUES 
 (136,4641,'T-238764237','AA','AAA'),
 (137,4641,'T-8783276372','AA','AAB'),
 (138,4641,'T-287328787','Ac','ACA'),
 (139,4641,'T-238742987','Ac','ACB'),
 (140,4641,'T-87676526521','E','EA'),
 (141,4641,'T-537483264','E','EB'),
 (142,4641,'T-7676767123','EB','EBA'),
 (143,4641,'T-236276476','EB','EBB'),
 (144,4641,'T-7645276452','EA','EA - remaining'),
 (145,4641,'T-17632447825','EA','EA - extracted'),
 (146,4641,'T-7284627839','EBA','EBA - remaining'),
 (147,4641,'T-1653284652','EBA','EBA - extracted'),
 (148,4641,'T-4283621454','EBB','EBB - remaining'),
 (149,4641,'T-2648265485','EBB','EBB - extracted'),
 (150,4667,'T-53274836','A','AA'),
 (151,4667,'T-2656478','A','AB'),
 (152,4667,'T-36384823','A','Ac'),
 (153,4667,'T-238764237','AA','AAA'),
 (154,4667,'T-8783276372','AA','AAB'),
 (155,4667,'T-287328787','Ac','ACA'),
 (156,4667,'T-238742987','Ac','ACB'),
 (157,4667,'T-87676526521','E','EA'),
 (158,4667,'T-537483264','E','EB');
INSERT INTO `test_responsibility_refinement` (`uid`,`version`,`fact-id`,`parent`,`child`) VALUES 
 (159,4667,'T-7676767123','EB','EBA'),
 (160,4667,'T-236276476','EB','EBB'),
 (161,4667,'T-7645276452','EA','EA - remaining'),
 (162,4667,'T-17632447825','EA','EA - extracted'),
 (163,4667,'T-7284627839','EBA','EBA - remaining'),
 (164,4667,'T-1653284652','EBA','EBA - extracted'),
 (165,4667,'T-4283621454','EBB','EBB - remaining'),
 (166,4667,'T-2648265485','EBB','EBB - extracted'),
 (167,4667,'T-882637256347','C','C - extracted'),
 (168,4667,'T-11253652762','C','C - remaining'),
 (169,4667,'T-217253426','B','B - extracted'),
 (170,4667,'T-2263542363','B','B - remaining');
/*!40000 ALTER TABLE `test_responsibility_refinement` ENABLE KEYS */;


--
-- Table structure for table `arche`.`variabilityreasoningframework_variabilitymechanismresponsibility`
--

DROP TABLE IF EXISTS `variabilityreasoningframework_variabilitymechanismresponsibility`;
CREATE TABLE `variabilityreasoningframework_variabilitymechanismresponsibility` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `id` text,
  `source` varchar(200) default NULL,
  `parent` varchar(20) default NULL,
  `child` varchar(20) default NULL,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `variabilityrf_variabilitymechanismresponsibility_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`variabilityreasoningframework_variabilitymechanismresponsibility`
--

/*!40000 ALTER TABLE `variabilityreasoningframework_variabilitymechanismresponsibility` DISABLE KEYS */;
/*!40000 ALTER TABLE `variabilityreasoningframework_variabilitymechanismresponsibility` ENABLE KEYS */;


--
-- Table structure for table `arche`.`variabilityreasoningframework_vpconfiguration`
--

DROP TABLE IF EXISTS `variabilityreasoningframework_vpconfiguration`;
CREATE TABLE `variabilityreasoningframework_vpconfiguration` (
  `uid` int(10) unsigned NOT NULL auto_increment,
  `version` int(10) unsigned NOT NULL,
  `fact-id` varchar(20) NOT NULL,
  `name` text,
  `inputs` text,
  PRIMARY KEY  (`uid`),
  KEY `fact-id` (`fact-id`),
  KEY `version` (`version`),
  CONSTRAINT `variabilityreasoningframework_vpconfiguration_ibfk_1` FOREIGN KEY (`version`) REFERENCES `__versions__` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `arche`.`variabilityreasoningframework_vpconfiguration`
--

/*!40000 ALTER TABLE `variabilityreasoningframework_vpconfiguration` DISABLE KEYS */;
/*!40000 ALTER TABLE `variabilityreasoningframework_vpconfiguration` ENABLE KEYS */;


--
-- View structure for view `arche`.`leave_responsibilities`
--

DROP VIEW IF EXISTS `leave_responsibilities`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `leave_responsibilities` AS select `res`.`uid` AS `uid`,`res`.`version` AS `version`,`res`.`fact-id` AS `fact-id`,`res`.`id` AS `id`,`res`.`name` AS `name`,`res`.`description` AS `description`,`res`.`source` AS `source`,`v`.`version_name` AS `version_name` from ((`main_responsibilities` `res` join `__versions__` `v` on((`v`.`ID` = `res`.`version`))) left join `main_responsibilityrefinementrelation` `d` on(((`res`.`version` = `d`.`version`) and (`res`.`fact-id` = `d`.`parent`)))) where isnull(`d`.`parent`);


--
-- View structure for view `arche`.`v_factset`
--

DROP VIEW IF EXISTS `v_factset`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_factset` AS select `__factsets__`.`factType` AS `factType`,`__factsets__`.`setName` AS `setName` from `__factsets__` order by `__factsets__`.`group`,`__factsets__`.`ID`;

--
-- Create schema information_schema
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ information_schema;
USE information_schema;

--
-- Table structure for table `information_schema`.`CHARACTER_SETS`
--

DROP TABLE IF EXISTS `CHARACTER_SETS`;
CREATE TEMPORARY TABLE `CHARACTER_SETS` (
  `CHARACTER_SET_NAME` varchar(64) NOT NULL default '',
  `DEFAULT_COLLATE_NAME` varchar(64) NOT NULL default '',
  `DESCRIPTION` varchar(60) NOT NULL default '',
  `MAXLEN` bigint(3) NOT NULL default '0'
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`CHARACTER_SETS`
--

/*!40000 ALTER TABLE `CHARACTER_SETS` DISABLE KEYS */;
INSERT INTO `CHARACTER_SETS` (`CHARACTER_SET_NAME`,`DEFAULT_COLLATE_NAME`,`DESCRIPTION`,`MAXLEN`) VALUES 
 ('big5','big5_chinese_ci','Big5 Traditional Chinese',2),
 ('dec8','dec8_swedish_ci','DEC West European',1),
 ('cp850','cp850_general_ci','DOS West European',1),
 ('hp8','hp8_english_ci','HP West European',1),
 ('koi8r','koi8r_general_ci','KOI8-R Relcom Russian',1),
 ('latin1','latin1_swedish_ci','cp1252 West European',1),
 ('latin2','latin2_general_ci','ISO 8859-2 Central European',1),
 ('swe7','swe7_swedish_ci','7bit Swedish',1),
 ('ascii','ascii_general_ci','US ASCII',1),
 ('ujis','ujis_japanese_ci','EUC-JP Japanese',3),
 ('sjis','sjis_japanese_ci','Shift-JIS Japanese',2),
 ('hebrew','hebrew_general_ci','ISO 8859-8 Hebrew',1),
 ('tis620','tis620_thai_ci','TIS620 Thai',1),
 ('euckr','euckr_korean_ci','EUC-KR Korean',2),
 ('koi8u','koi8u_general_ci','KOI8-U Ukrainian',1),
 ('gb2312','gb2312_chinese_ci','GB2312 Simplified Chinese',2),
 ('greek','greek_general_ci','ISO 8859-7 Greek',1),
 ('cp1250','cp1250_general_ci','Windows Central European',1);
INSERT INTO `CHARACTER_SETS` (`CHARACTER_SET_NAME`,`DEFAULT_COLLATE_NAME`,`DESCRIPTION`,`MAXLEN`) VALUES 
 ('gbk','gbk_chinese_ci','GBK Simplified Chinese',2),
 ('latin5','latin5_turkish_ci','ISO 8859-9 Turkish',1),
 ('armscii8','armscii8_general_ci','ARMSCII-8 Armenian',1),
 ('utf8','utf8_general_ci','UTF-8 Unicode',3),
 ('ucs2','ucs2_general_ci','UCS-2 Unicode',2),
 ('cp866','cp866_general_ci','DOS Russian',1),
 ('keybcs2','keybcs2_general_ci','DOS Kamenicky Czech-Slovak',1),
 ('macce','macce_general_ci','Mac Central European',1),
 ('macroman','macroman_general_ci','Mac West European',1),
 ('cp852','cp852_general_ci','DOS Central European',1),
 ('latin7','latin7_general_ci','ISO 8859-13 Baltic',1),
 ('cp1251','cp1251_general_ci','Windows Cyrillic',1),
 ('cp1256','cp1256_general_ci','Windows Arabic',1),
 ('cp1257','cp1257_general_ci','Windows Baltic',1),
 ('binary','binary','Binary pseudo charset',1),
 ('geostd8','geostd8_general_ci','GEOSTD8 Georgian',1),
 ('cp932','cp932_japanese_ci','SJIS for Windows Japanese',2),
 ('eucjpms','eucjpms_japanese_ci','UJIS for Windows Japanese',3);
/*!40000 ALTER TABLE `CHARACTER_SETS` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`COLLATIONS`
--

DROP TABLE IF EXISTS `COLLATIONS`;
CREATE TEMPORARY TABLE `COLLATIONS` (
  `COLLATION_NAME` varchar(64) NOT NULL default '',
  `CHARACTER_SET_NAME` varchar(64) NOT NULL default '',
  `ID` bigint(11) NOT NULL default '0',
  `IS_DEFAULT` varchar(3) NOT NULL default '',
  `IS_COMPILED` varchar(3) NOT NULL default '',
  `SORTLEN` bigint(3) NOT NULL default '0'
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`COLLATIONS`
--

/*!40000 ALTER TABLE `COLLATIONS` DISABLE KEYS */;
INSERT INTO `COLLATIONS` (`COLLATION_NAME`,`CHARACTER_SET_NAME`,`ID`,`IS_DEFAULT`,`IS_COMPILED`,`SORTLEN`) VALUES 
 ('big5_chinese_ci','big5',1,'Yes','Yes',1),
 ('big5_bin','big5',84,'','Yes',1),
 ('dec8_swedish_ci','dec8',3,'Yes','',0),
 ('dec8_bin','dec8',69,'','',0),
 ('cp850_general_ci','cp850',4,'Yes','',0),
 ('cp850_bin','cp850',80,'','',0),
 ('hp8_english_ci','hp8',6,'Yes','',0),
 ('hp8_bin','hp8',72,'','',0),
 ('koi8r_general_ci','koi8r',7,'Yes','',0),
 ('koi8r_bin','koi8r',74,'','',0),
 ('latin1_german1_ci','latin1',5,'','',0),
 ('latin1_swedish_ci','latin1',8,'Yes','Yes',1),
 ('latin1_danish_ci','latin1',15,'','',0),
 ('latin1_german2_ci','latin1',31,'','Yes',2),
 ('latin1_bin','latin1',47,'','Yes',1),
 ('latin1_general_ci','latin1',48,'','',0),
 ('latin1_general_cs','latin1',49,'','',0),
 ('latin1_spanish_ci','latin1',94,'','',0),
 ('latin2_czech_cs','latin2',2,'','Yes',4),
 ('latin2_general_ci','latin2',9,'Yes','',0),
 ('latin2_hungarian_ci','latin2',21,'','',0),
 ('latin2_croatian_ci','latin2',27,'','',0),
 ('latin2_bin','latin2',77,'','',0);
INSERT INTO `COLLATIONS` (`COLLATION_NAME`,`CHARACTER_SET_NAME`,`ID`,`IS_DEFAULT`,`IS_COMPILED`,`SORTLEN`) VALUES 
 ('swe7_swedish_ci','swe7',10,'Yes','',0),
 ('swe7_bin','swe7',82,'','',0),
 ('ascii_general_ci','ascii',11,'Yes','',0),
 ('ascii_bin','ascii',65,'','',0),
 ('ujis_japanese_ci','ujis',12,'Yes','Yes',1),
 ('ujis_bin','ujis',91,'','Yes',1),
 ('sjis_japanese_ci','sjis',13,'Yes','Yes',1),
 ('sjis_bin','sjis',88,'','Yes',1),
 ('hebrew_general_ci','hebrew',16,'Yes','',0),
 ('hebrew_bin','hebrew',71,'','',0),
 ('tis620_thai_ci','tis620',18,'Yes','Yes',4),
 ('tis620_bin','tis620',89,'','Yes',1),
 ('euckr_korean_ci','euckr',19,'Yes','Yes',1),
 ('euckr_bin','euckr',85,'','Yes',1),
 ('koi8u_general_ci','koi8u',22,'Yes','',0),
 ('koi8u_bin','koi8u',75,'','',0),
 ('gb2312_chinese_ci','gb2312',24,'Yes','Yes',1),
 ('gb2312_bin','gb2312',86,'','Yes',1),
 ('greek_general_ci','greek',25,'Yes','',0),
 ('greek_bin','greek',70,'','',0),
 ('cp1250_general_ci','cp1250',26,'Yes','',0),
 ('cp1250_czech_cs','cp1250',34,'','Yes',2),
 ('cp1250_croatian_ci','cp1250',44,'','',0);
INSERT INTO `COLLATIONS` (`COLLATION_NAME`,`CHARACTER_SET_NAME`,`ID`,`IS_DEFAULT`,`IS_COMPILED`,`SORTLEN`) VALUES 
 ('cp1250_bin','cp1250',66,'','',0),
 ('gbk_chinese_ci','gbk',28,'Yes','Yes',1),
 ('gbk_bin','gbk',87,'','Yes',1),
 ('latin5_turkish_ci','latin5',30,'Yes','',0),
 ('latin5_bin','latin5',78,'','',0),
 ('armscii8_general_ci','armscii8',32,'Yes','',0),
 ('armscii8_bin','armscii8',64,'','',0),
 ('utf8_general_ci','utf8',33,'Yes','Yes',1),
 ('utf8_bin','utf8',83,'','Yes',1),
 ('utf8_unicode_ci','utf8',192,'','Yes',8),
 ('utf8_icelandic_ci','utf8',193,'','Yes',8),
 ('utf8_latvian_ci','utf8',194,'','Yes',8),
 ('utf8_romanian_ci','utf8',195,'','Yes',8),
 ('utf8_slovenian_ci','utf8',196,'','Yes',8),
 ('utf8_polish_ci','utf8',197,'','Yes',8),
 ('utf8_estonian_ci','utf8',198,'','Yes',8),
 ('utf8_spanish_ci','utf8',199,'','Yes',8),
 ('utf8_swedish_ci','utf8',200,'','Yes',8),
 ('utf8_turkish_ci','utf8',201,'','Yes',8),
 ('utf8_czech_ci','utf8',202,'','Yes',8),
 ('utf8_danish_ci','utf8',203,'','Yes',8),
 ('utf8_lithuanian_ci','utf8',204,'','Yes',8);
INSERT INTO `COLLATIONS` (`COLLATION_NAME`,`CHARACTER_SET_NAME`,`ID`,`IS_DEFAULT`,`IS_COMPILED`,`SORTLEN`) VALUES 
 ('utf8_slovak_ci','utf8',205,'','Yes',8),
 ('utf8_spanish2_ci','utf8',206,'','Yes',8),
 ('utf8_roman_ci','utf8',207,'','Yes',8),
 ('utf8_persian_ci','utf8',208,'','Yes',8),
 ('utf8_esperanto_ci','utf8',209,'','Yes',8),
 ('ucs2_general_ci','ucs2',35,'Yes','Yes',1),
 ('ucs2_bin','ucs2',90,'','Yes',1),
 ('ucs2_unicode_ci','ucs2',128,'','Yes',8),
 ('ucs2_icelandic_ci','ucs2',129,'','Yes',8),
 ('ucs2_latvian_ci','ucs2',130,'','Yes',8),
 ('ucs2_romanian_ci','ucs2',131,'','Yes',8),
 ('ucs2_slovenian_ci','ucs2',132,'','Yes',8),
 ('ucs2_polish_ci','ucs2',133,'','Yes',8),
 ('ucs2_estonian_ci','ucs2',134,'','Yes',8),
 ('ucs2_spanish_ci','ucs2',135,'','Yes',8),
 ('ucs2_swedish_ci','ucs2',136,'','Yes',8),
 ('ucs2_turkish_ci','ucs2',137,'','Yes',8),
 ('ucs2_czech_ci','ucs2',138,'','Yes',8),
 ('ucs2_danish_ci','ucs2',139,'','Yes',8),
 ('ucs2_lithuanian_ci','ucs2',140,'','Yes',8),
 ('ucs2_slovak_ci','ucs2',141,'','Yes',8),
 ('ucs2_spanish2_ci','ucs2',142,'','Yes',8);
INSERT INTO `COLLATIONS` (`COLLATION_NAME`,`CHARACTER_SET_NAME`,`ID`,`IS_DEFAULT`,`IS_COMPILED`,`SORTLEN`) VALUES 
 ('ucs2_roman_ci','ucs2',143,'','Yes',8),
 ('ucs2_persian_ci','ucs2',144,'','Yes',8),
 ('ucs2_esperanto_ci','ucs2',145,'','Yes',8),
 ('cp866_general_ci','cp866',36,'Yes','',0),
 ('cp866_bin','cp866',68,'','',0),
 ('keybcs2_general_ci','keybcs2',37,'Yes','',0),
 ('keybcs2_bin','keybcs2',73,'','',0),
 ('macce_general_ci','macce',38,'Yes','',0),
 ('macce_bin','macce',43,'','',0),
 ('macroman_general_ci','macroman',39,'Yes','',0),
 ('macroman_bin','macroman',53,'','',0),
 ('cp852_general_ci','cp852',40,'Yes','',0),
 ('cp852_bin','cp852',81,'','',0),
 ('latin7_estonian_cs','latin7',20,'','',0),
 ('latin7_general_ci','latin7',41,'Yes','',0),
 ('latin7_general_cs','latin7',42,'','',0),
 ('latin7_bin','latin7',79,'','',0),
 ('cp1251_bulgarian_ci','cp1251',14,'','',0),
 ('cp1251_ukrainian_ci','cp1251',23,'','',0),
 ('cp1251_bin','cp1251',50,'','',0),
 ('cp1251_general_ci','cp1251',51,'Yes','',0),
 ('cp1251_general_cs','cp1251',52,'','',0);
INSERT INTO `COLLATIONS` (`COLLATION_NAME`,`CHARACTER_SET_NAME`,`ID`,`IS_DEFAULT`,`IS_COMPILED`,`SORTLEN`) VALUES 
 ('cp1256_general_ci','cp1256',57,'Yes','',0),
 ('cp1256_bin','cp1256',67,'','',0),
 ('cp1257_lithuanian_ci','cp1257',29,'','',0),
 ('cp1257_bin','cp1257',58,'','',0),
 ('cp1257_general_ci','cp1257',59,'Yes','',0),
 ('binary','binary',63,'Yes','Yes',1),
 ('geostd8_general_ci','geostd8',92,'Yes','',0),
 ('geostd8_bin','geostd8',93,'','',0),
 ('cp932_japanese_ci','cp932',95,'Yes','Yes',1),
 ('cp932_bin','cp932',96,'','Yes',1),
 ('eucjpms_japanese_ci','eucjpms',97,'Yes','Yes',1),
 ('eucjpms_bin','eucjpms',98,'','Yes',1);
/*!40000 ALTER TABLE `COLLATIONS` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`COLLATION_CHARACTER_SET_APPLICABILITY`
--

DROP TABLE IF EXISTS `COLLATION_CHARACTER_SET_APPLICABILITY`;
CREATE TEMPORARY TABLE `COLLATION_CHARACTER_SET_APPLICABILITY` (
  `COLLATION_NAME` varchar(64) NOT NULL default '',
  `CHARACTER_SET_NAME` varchar(64) NOT NULL default ''
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`COLLATION_CHARACTER_SET_APPLICABILITY`
--

/*!40000 ALTER TABLE `COLLATION_CHARACTER_SET_APPLICABILITY` DISABLE KEYS */;
INSERT INTO `COLLATION_CHARACTER_SET_APPLICABILITY` (`COLLATION_NAME`,`CHARACTER_SET_NAME`) VALUES 
 ('big5_chinese_ci','big5'),
 ('big5_bin','big5'),
 ('dec8_swedish_ci','dec8'),
 ('dec8_bin','dec8'),
 ('cp850_general_ci','cp850'),
 ('cp850_bin','cp850'),
 ('hp8_english_ci','hp8'),
 ('hp8_bin','hp8'),
 ('koi8r_general_ci','koi8r'),
 ('koi8r_bin','koi8r'),
 ('latin1_german1_ci','latin1'),
 ('latin1_swedish_ci','latin1'),
 ('latin1_danish_ci','latin1'),
 ('latin1_german2_ci','latin1'),
 ('latin1_bin','latin1'),
 ('latin1_general_ci','latin1'),
 ('latin1_general_cs','latin1'),
 ('latin1_spanish_ci','latin1'),
 ('latin2_czech_cs','latin2'),
 ('latin2_general_ci','latin2'),
 ('latin2_hungarian_ci','latin2'),
 ('latin2_croatian_ci','latin2'),
 ('latin2_bin','latin2'),
 ('swe7_swedish_ci','swe7'),
 ('swe7_bin','swe7'),
 ('ascii_general_ci','ascii'),
 ('ascii_bin','ascii'),
 ('ujis_japanese_ci','ujis'),
 ('ujis_bin','ujis'),
 ('sjis_japanese_ci','sjis'),
 ('sjis_bin','sjis'),
 ('hebrew_general_ci','hebrew'),
 ('hebrew_bin','hebrew');
INSERT INTO `COLLATION_CHARACTER_SET_APPLICABILITY` (`COLLATION_NAME`,`CHARACTER_SET_NAME`) VALUES 
 ('tis620_thai_ci','tis620'),
 ('tis620_bin','tis620'),
 ('euckr_korean_ci','euckr'),
 ('euckr_bin','euckr'),
 ('koi8u_general_ci','koi8u'),
 ('koi8u_bin','koi8u'),
 ('gb2312_chinese_ci','gb2312'),
 ('gb2312_bin','gb2312'),
 ('greek_general_ci','greek'),
 ('greek_bin','greek'),
 ('cp1250_general_ci','cp1250'),
 ('cp1250_czech_cs','cp1250'),
 ('cp1250_croatian_ci','cp1250'),
 ('cp1250_bin','cp1250'),
 ('gbk_chinese_ci','gbk'),
 ('gbk_bin','gbk'),
 ('latin5_turkish_ci','latin5'),
 ('latin5_bin','latin5'),
 ('armscii8_general_ci','armscii8'),
 ('armscii8_bin','armscii8'),
 ('utf8_general_ci','utf8'),
 ('utf8_bin','utf8'),
 ('utf8_unicode_ci','utf8'),
 ('utf8_icelandic_ci','utf8'),
 ('utf8_latvian_ci','utf8'),
 ('utf8_romanian_ci','utf8'),
 ('utf8_slovenian_ci','utf8'),
 ('utf8_polish_ci','utf8'),
 ('utf8_estonian_ci','utf8'),
 ('utf8_spanish_ci','utf8'),
 ('utf8_swedish_ci','utf8'),
 ('utf8_turkish_ci','utf8'),
 ('utf8_czech_ci','utf8');
INSERT INTO `COLLATION_CHARACTER_SET_APPLICABILITY` (`COLLATION_NAME`,`CHARACTER_SET_NAME`) VALUES 
 ('utf8_danish_ci','utf8'),
 ('utf8_lithuanian_ci','utf8'),
 ('utf8_slovak_ci','utf8'),
 ('utf8_spanish2_ci','utf8'),
 ('utf8_roman_ci','utf8'),
 ('utf8_persian_ci','utf8'),
 ('utf8_esperanto_ci','utf8'),
 ('ucs2_general_ci','ucs2'),
 ('ucs2_bin','ucs2'),
 ('ucs2_unicode_ci','ucs2'),
 ('ucs2_icelandic_ci','ucs2'),
 ('ucs2_latvian_ci','ucs2'),
 ('ucs2_romanian_ci','ucs2'),
 ('ucs2_slovenian_ci','ucs2'),
 ('ucs2_polish_ci','ucs2'),
 ('ucs2_estonian_ci','ucs2'),
 ('ucs2_spanish_ci','ucs2'),
 ('ucs2_swedish_ci','ucs2'),
 ('ucs2_turkish_ci','ucs2'),
 ('ucs2_czech_ci','ucs2'),
 ('ucs2_danish_ci','ucs2'),
 ('ucs2_lithuanian_ci','ucs2'),
 ('ucs2_slovak_ci','ucs2'),
 ('ucs2_spanish2_ci','ucs2'),
 ('ucs2_roman_ci','ucs2'),
 ('ucs2_persian_ci','ucs2'),
 ('ucs2_esperanto_ci','ucs2'),
 ('cp866_general_ci','cp866'),
 ('cp866_bin','cp866'),
 ('keybcs2_general_ci','keybcs2'),
 ('keybcs2_bin','keybcs2'),
 ('macce_general_ci','macce');
INSERT INTO `COLLATION_CHARACTER_SET_APPLICABILITY` (`COLLATION_NAME`,`CHARACTER_SET_NAME`) VALUES 
 ('macce_bin','macce'),
 ('macroman_general_ci','macroman'),
 ('macroman_bin','macroman'),
 ('cp852_general_ci','cp852'),
 ('cp852_bin','cp852'),
 ('latin7_estonian_cs','latin7'),
 ('latin7_general_ci','latin7'),
 ('latin7_general_cs','latin7'),
 ('latin7_bin','latin7'),
 ('cp1251_bulgarian_ci','cp1251'),
 ('cp1251_ukrainian_ci','cp1251'),
 ('cp1251_bin','cp1251'),
 ('cp1251_general_ci','cp1251'),
 ('cp1251_general_cs','cp1251'),
 ('cp1256_general_ci','cp1256'),
 ('cp1256_bin','cp1256'),
 ('cp1257_lithuanian_ci','cp1257'),
 ('cp1257_bin','cp1257'),
 ('cp1257_general_ci','cp1257'),
 ('binary','binary'),
 ('geostd8_general_ci','geostd8'),
 ('geostd8_bin','geostd8'),
 ('cp932_japanese_ci','cp932'),
 ('cp932_bin','cp932'),
 ('eucjpms_japanese_ci','eucjpms'),
 ('eucjpms_bin','eucjpms');
/*!40000 ALTER TABLE `COLLATION_CHARACTER_SET_APPLICABILITY` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`COLUMNS`
--

DROP TABLE IF EXISTS `COLUMNS`;
CREATE TEMPORARY TABLE `COLUMNS` (
  `TABLE_CATALOG` varchar(512) default NULL,
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `TABLE_NAME` varchar(64) NOT NULL default '',
  `COLUMN_NAME` varchar(64) NOT NULL default '',
  `ORDINAL_POSITION` bigint(21) NOT NULL default '0',
  `COLUMN_DEFAULT` varchar(64) default NULL,
  `IS_NULLABLE` varchar(3) NOT NULL default '',
  `DATA_TYPE` varchar(64) NOT NULL default '',
  `CHARACTER_MAXIMUM_LENGTH` bigint(21) default NULL,
  `CHARACTER_OCTET_LENGTH` bigint(21) default NULL,
  `NUMERIC_PRECISION` bigint(21) default NULL,
  `NUMERIC_SCALE` bigint(21) default NULL,
  `CHARACTER_SET_NAME` varchar(64) default NULL,
  `COLLATION_NAME` varchar(64) default NULL,
  `COLUMN_TYPE` longtext NOT NULL,
  `COLUMN_KEY` varchar(3) NOT NULL default '',
  `EXTRA` varchar(20) NOT NULL default '',
  `PRIVILEGES` varchar(80) NOT NULL default '',
  `COLUMN_COMMENT` varchar(255) NOT NULL default ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`COLUMNS`
--

/*!40000 ALTER TABLE `COLUMNS` DISABLE KEYS */;
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','CHARACTER_SETS','CHARACTER_SET_NAME',1,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','CHARACTER_SETS','DEFAULT_COLLATE_NAME',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','CHARACTER_SETS','DESCRIPTION',3,'','NO','varchar',60,180,NULL,NULL,'utf8','utf8_general_ci','varchar(60)','','','select',''),
 (NULL,'information_schema','CHARACTER_SETS','MAXLEN',4,'0','NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(3)','','','select',''),
 (NULL,'information_schema','COLLATIONS','COLLATION_NAME',1,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLLATIONS','CHARACTER_SET_NAME',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','COLLATIONS','ID',3,'0','NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(11)','','','select',''),
 (NULL,'information_schema','COLLATIONS','IS_DEFAULT',4,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','COLLATIONS','IS_COMPILED',5,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','COLLATIONS','SORTLEN',6,'0','NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(3)','','','select',''),
 (NULL,'information_schema','COLLATION_CHARACTER_SET_APPLICABILITY','COLLATION_NAME',1,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLLATION_CHARACTER_SET_APPLICABILITY','CHARACTER_SET_NAME',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','COLUMNS','TABLE_CATALOG',1,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','COLUMNS','TABLE_SCHEMA',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMNS','TABLE_NAME',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMNS','COLUMN_NAME',4,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMNS','ORDINAL_POSITION',5,'0','NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','COLUMNS','COLUMN_DEFAULT',6,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','COLUMNS','IS_NULLABLE',7,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','COLUMNS','DATA_TYPE',8,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMNS','CHARACTER_MAXIMUM_LENGTH',9,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','COLUMNS','CHARACTER_OCTET_LENGTH',10,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','COLUMNS','NUMERIC_PRECISION',11,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','COLUMNS','NUMERIC_SCALE',12,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','COLUMNS','CHARACTER_SET_NAME',13,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMNS','COLLATION_NAME',14,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMNS','COLUMN_TYPE',15,'','NO','longtext',4294967295,4294967295,NULL,NULL,'utf8','utf8_general_ci','longtext','','','select',''),
 (NULL,'information_schema','COLUMNS','COLUMN_KEY',16,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','COLUMNS','EXTRA',17,'','NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select',''),
 (NULL,'information_schema','COLUMNS','PRIVILEGES',18,'','NO','varchar',80,240,NULL,NULL,'utf8','utf8_general_ci','varchar(80)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','COLUMNS','COLUMN_COMMENT',19,'','NO','varchar',255,765,NULL,NULL,'utf8','utf8_general_ci','varchar(255)','','','select',''),
 (NULL,'information_schema','COLUMN_PRIVILEGES','GRANTEE',1,'','NO','varchar',81,243,NULL,NULL,'utf8','utf8_general_ci','varchar(81)','','','select',''),
 (NULL,'information_schema','COLUMN_PRIVILEGES','TABLE_CATALOG',2,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','COLUMN_PRIVILEGES','TABLE_SCHEMA',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMN_PRIVILEGES','TABLE_NAME',4,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMN_PRIVILEGES','COLUMN_NAME',5,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','COLUMN_PRIVILEGES','PRIVILEGE_TYPE',6,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','COLUMN_PRIVILEGES','IS_GRANTABLE',7,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','CONSTRAINT_CATALOG',1,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','CONSTRAINT_SCHEMA',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','CONSTRAINT_NAME',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','TABLE_CATALOG',4,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','KEY_COLUMN_USAGE','TABLE_SCHEMA',5,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','TABLE_NAME',6,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','COLUMN_NAME',7,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','ORDINAL_POSITION',8,'0','NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(10)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','POSITION_IN_UNIQUE_CONSTRAINT',9,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(10)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','REFERENCED_TABLE_SCHEMA',10,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','KEY_COLUMN_USAGE','REFERENCED_TABLE_NAME',11,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','KEY_COLUMN_USAGE','REFERENCED_COLUMN_NAME',12,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','ROUTINES','SPECIFIC_NAME',1,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','ROUTINES','ROUTINE_CATALOG',2,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','ROUTINES','ROUTINE_SCHEMA',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','ROUTINES','ROUTINE_NAME',4,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','ROUTINES','ROUTINE_TYPE',5,'','NO','varchar',9,27,NULL,NULL,'utf8','utf8_general_ci','varchar(9)','','','select',''),
 (NULL,'information_schema','ROUTINES','DTD_IDENTIFIER',6,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','ROUTINES','ROUTINE_BODY',7,'','NO','varchar',8,24,NULL,NULL,'utf8','utf8_general_ci','varchar(8)','','','select',''),
 (NULL,'information_schema','ROUTINES','ROUTINE_DEFINITION',8,'','NO','longtext',4294967295,4294967295,NULL,NULL,'utf8','utf8_general_ci','longtext','','','select',''),
 (NULL,'information_schema','ROUTINES','EXTERNAL_NAME',9,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','ROUTINES','EXTERNAL_LANGUAGE',10,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','ROUTINES','PARAMETER_STYLE',11,'','NO','varchar',8,24,NULL,NULL,'utf8','utf8_general_ci','varchar(8)','','','select',''),
 (NULL,'information_schema','ROUTINES','IS_DETERMINISTIC',12,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','ROUTINES','SQL_DATA_ACCESS',13,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','ROUTINES','SQL_PATH',14,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','ROUTINES','SECURITY_TYPE',15,'','NO','varchar',7,21,NULL,NULL,'utf8','utf8_general_ci','varchar(7)','','','select',''),
 (NULL,'information_schema','ROUTINES','CREATED',16,'0000-00-00 00:00:00','NO','datetime',NULL,NULL,NULL,NULL,NULL,NULL,'datetime','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','ROUTINES','LAST_ALTERED',17,'0000-00-00 00:00:00','NO','datetime',NULL,NULL,NULL,NULL,NULL,NULL,'datetime','','','select',''),
 (NULL,'information_schema','ROUTINES','SQL_MODE',18,'','NO','longtext',4294967295,4294967295,NULL,NULL,'utf8','utf8_general_ci','longtext','','','select',''),
 (NULL,'information_schema','ROUTINES','ROUTINE_COMMENT',19,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','ROUTINES','DEFINER',20,'','NO','varchar',77,231,NULL,NULL,'utf8','utf8_general_ci','varchar(77)','','','select',''),
 (NULL,'information_schema','SCHEMATA','CATALOG_NAME',1,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','SCHEMATA','SCHEMA_NAME',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','SCHEMATA','DEFAULT_CHARACTER_SET_NAME',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','SCHEMATA','DEFAULT_COLLATION_NAME',4,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','SCHEMATA','SQL_PATH',5,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','SCHEMA_PRIVILEGES','GRANTEE',1,'','NO','varchar',81,243,NULL,NULL,'utf8','utf8_general_ci','varchar(81)','','','select',''),
 (NULL,'information_schema','SCHEMA_PRIVILEGES','TABLE_CATALOG',2,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','SCHEMA_PRIVILEGES','TABLE_SCHEMA',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','SCHEMA_PRIVILEGES','PRIVILEGE_TYPE',4,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','SCHEMA_PRIVILEGES','IS_GRANTABLE',5,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','STATISTICS','TABLE_CATALOG',1,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','STATISTICS','TABLE_SCHEMA',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','STATISTICS','TABLE_NAME',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','STATISTICS','NON_UNIQUE',4,'0','NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(1)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','STATISTICS','INDEX_SCHEMA',5,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','STATISTICS','INDEX_NAME',6,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','STATISTICS','SEQ_IN_INDEX',7,'0','NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(2)','','','select',''),
 (NULL,'information_schema','STATISTICS','COLUMN_NAME',8,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','STATISTICS','COLLATION',9,NULL,'YES','varchar',1,3,NULL,NULL,'utf8','utf8_general_ci','varchar(1)','','','select',''),
 (NULL,'information_schema','STATISTICS','CARDINALITY',10,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','STATISTICS','SUB_PART',11,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(3)','','','select',''),
 (NULL,'information_schema','STATISTICS','PACKED',12,NULL,'YES','varchar',10,30,NULL,NULL,'utf8','utf8_general_ci','varchar(10)','','','select',''),
 (NULL,'information_schema','STATISTICS','NULLABLE',13,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','STATISTICS','INDEX_TYPE',14,'','NO','varchar',16,48,NULL,NULL,'utf8','utf8_general_ci','varchar(16)','','','select',''),
 (NULL,'information_schema','STATISTICS','COMMENT',15,NULL,'YES','varchar',16,48,NULL,NULL,'utf8','utf8_general_ci','varchar(16)','','','select',''),
 (NULL,'information_schema','TABLES','TABLE_CATALOG',1,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TABLES','TABLE_SCHEMA',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLES','TABLE_NAME',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLES','TABLE_TYPE',4,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLES','ENGINE',5,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLES','VERSION',6,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','TABLES','ROW_FORMAT',7,NULL,'YES','varchar',10,30,NULL,NULL,'utf8','utf8_general_ci','varchar(10)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TABLES','TABLE_ROWS',8,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','TABLES','AVG_ROW_LENGTH',9,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','TABLES','DATA_LENGTH',10,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','TABLES','MAX_DATA_LENGTH',11,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','TABLES','INDEX_LENGTH',12,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','TABLES','DATA_FREE',13,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TABLES','AUTO_INCREMENT',14,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select',''),
 (NULL,'information_schema','TABLES','CREATE_TIME',15,NULL,'YES','datetime',NULL,NULL,NULL,NULL,NULL,NULL,'datetime','','','select',''),
 (NULL,'information_schema','TABLES','UPDATE_TIME',16,NULL,'YES','datetime',NULL,NULL,NULL,NULL,NULL,NULL,'datetime','','','select',''),
 (NULL,'information_schema','TABLES','CHECK_TIME',17,NULL,'YES','datetime',NULL,NULL,NULL,NULL,NULL,NULL,'datetime','','','select',''),
 (NULL,'information_schema','TABLES','TABLE_COLLATION',18,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLES','CHECKSUM',19,NULL,'YES','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(21)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TABLES','CREATE_OPTIONS',20,NULL,'YES','varchar',255,765,NULL,NULL,'utf8','utf8_general_ci','varchar(255)','','','select',''),
 (NULL,'information_schema','TABLES','TABLE_COMMENT',21,'','NO','varchar',80,240,NULL,NULL,'utf8','utf8_general_ci','varchar(80)','','','select',''),
 (NULL,'information_schema','TABLE_CONSTRAINTS','CONSTRAINT_CATALOG',1,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','TABLE_CONSTRAINTS','CONSTRAINT_SCHEMA',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLE_CONSTRAINTS','CONSTRAINT_NAME',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLE_CONSTRAINTS','TABLE_SCHEMA',4,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TABLE_CONSTRAINTS','TABLE_NAME',5,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLE_CONSTRAINTS','CONSTRAINT_TYPE',6,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLE_PRIVILEGES','GRANTEE',1,'','NO','varchar',81,243,NULL,NULL,'utf8','utf8_general_ci','varchar(81)','','','select',''),
 (NULL,'information_schema','TABLE_PRIVILEGES','TABLE_CATALOG',2,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','TABLE_PRIVILEGES','TABLE_SCHEMA',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLE_PRIVILEGES','TABLE_NAME',4,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TABLE_PRIVILEGES','PRIVILEGE_TYPE',5,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TABLE_PRIVILEGES','IS_GRANTABLE',6,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','TRIGGERS','TRIGGER_CATALOG',1,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','TRIGGERS','TRIGGER_SCHEMA',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TRIGGERS','TRIGGER_NAME',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TRIGGERS','EVENT_MANIPULATION',4,'','NO','varchar',6,18,NULL,NULL,'utf8','utf8_general_ci','varchar(6)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TRIGGERS','EVENT_OBJECT_CATALOG',5,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','TRIGGERS','EVENT_OBJECT_SCHEMA',6,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TRIGGERS','EVENT_OBJECT_TABLE',7,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TRIGGERS','ACTION_ORDER',8,'0','NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(4)','','','select',''),
 (NULL,'information_schema','TRIGGERS','ACTION_CONDITION',9,NULL,'YES','longtext',4294967295,4294967295,NULL,NULL,'utf8','utf8_general_ci','longtext','','','select',''),
 (NULL,'information_schema','TRIGGERS','ACTION_STATEMENT',10,'','NO','longtext',4294967295,4294967295,NULL,NULL,'utf8','utf8_general_ci','longtext','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TRIGGERS','ACTION_ORIENTATION',11,'','NO','varchar',9,27,NULL,NULL,'utf8','utf8_general_ci','varchar(9)','','','select',''),
 (NULL,'information_schema','TRIGGERS','ACTION_TIMING',12,'','NO','varchar',6,18,NULL,NULL,'utf8','utf8_general_ci','varchar(6)','','','select',''),
 (NULL,'information_schema','TRIGGERS','ACTION_REFERENCE_OLD_TABLE',13,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TRIGGERS','ACTION_REFERENCE_NEW_TABLE',14,NULL,'YES','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','TRIGGERS','ACTION_REFERENCE_OLD_ROW',15,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','TRIGGERS','ACTION_REFERENCE_NEW_ROW',16,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','TRIGGERS','CREATED',17,NULL,'YES','datetime',NULL,NULL,NULL,NULL,NULL,NULL,'datetime','','','select',''),
 (NULL,'information_schema','TRIGGERS','SQL_MODE',18,'','NO','longtext',4294967295,4294967295,NULL,NULL,'utf8','utf8_general_ci','longtext','','','select',''),
 (NULL,'information_schema','TRIGGERS','DEFINER',19,'','NO','longtext',4294967295,4294967295,NULL,NULL,'utf8','utf8_general_ci','longtext','','','select',''),
 (NULL,'information_schema','VIEWS','TABLE_CATALOG',1,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','VIEWS','TABLE_SCHEMA',2,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','VIEWS','TABLE_NAME',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','VIEWS','VIEW_DEFINITION',4,'','NO','longtext',4294967295,4294967295,NULL,NULL,'utf8','utf8_general_ci','longtext','','','select',''),
 (NULL,'information_schema','VIEWS','CHECK_OPTION',5,'','NO','varchar',8,24,NULL,NULL,'utf8','utf8_general_ci','varchar(8)','','','select',''),
 (NULL,'information_schema','VIEWS','IS_UPDATABLE',6,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'information_schema','VIEWS','DEFINER',7,'','NO','varchar',77,231,NULL,NULL,'utf8','utf8_general_ci','varchar(77)','','','select',''),
 (NULL,'information_schema','VIEWS','SECURITY_TYPE',8,'','NO','varchar',7,21,NULL,NULL,'utf8','utf8_general_ci','varchar(7)','','','select',''),
 (NULL,'information_schema','USER_PRIVILEGES','GRANTEE',1,'','NO','varchar',81,243,NULL,NULL,'utf8','utf8_general_ci','varchar(81)','','','select','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'information_schema','USER_PRIVILEGES','TABLE_CATALOG',2,NULL,'YES','varchar',512,1536,NULL,NULL,'utf8','utf8_general_ci','varchar(512)','','','select',''),
 (NULL,'information_schema','USER_PRIVILEGES','PRIVILEGE_TYPE',3,'','NO','varchar',64,192,NULL,NULL,'utf8','utf8_general_ci','varchar(64)','','','select',''),
 (NULL,'information_schema','USER_PRIVILEGES','IS_GRANTABLE',4,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select',''),
 (NULL,'arche','__factsets__','ID',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','__factsets__','setName',2,'','NO','varchar',45,135,NULL,NULL,'utf8','utf8_general_ci','varchar(45)','MUL','','select,insert,update,references',''),
 (NULL,'arche','__factsets__','factType',3,'','NO','varchar',255,765,NULL,NULL,'utf8','utf8_general_ci','varchar(255)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','__factsets__','group',4,'1','NO','tinyint',NULL,NULL,3,0,NULL,NULL,'tinyint(3) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','__mod_solver_interface__','ID',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','__mod_solver_interface__','FactSet',2,'','NO','varchar',100,300,NULL,NULL,'utf8','utf8_general_ci','varchar(100)','','','select,insert,update,references',''),
 (NULL,'arche','__mod_solver_interface__','Version',3,'','NO','varchar',255,765,NULL,NULL,'utf8','utf8_general_ci','varchar(255)','','','select,insert,update,references',''),
 (NULL,'arche','__tool_command_interface__','ID',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','__tool_command_interface__','ToolName',2,'','NO','varchar',100,300,NULL,NULL,'utf8','utf8_general_ci','varchar(100)','','','select,insert,update,references',''),
 (NULL,'arche','__tool_command_interface__','Direction',3,'','NO','varchar',3,9,NULL,NULL,'utf8','utf8_general_ci','varchar(3)','','','select,insert,update,references','Content=out: Command is to the tool, content=in: Command to ArchE'),
 (NULL,'arche','__tool_command_interface__','FactSet',4,'','NO','varchar',100,300,NULL,NULL,'utf8','utf8_general_ci','varchar(100)','','','select,insert,update,references',''),
 (NULL,'arche','__tool_command_interface__','Version',5,'','NO','varchar',255,765,NULL,NULL,'utf8','utf8_general_ci','varchar(255)','','','select,insert,update,references',''),
 (NULL,'arche','__versions__','ID',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','__versions__','version_name',2,'','NO','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','__versions__','date_created',3,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'arche','__versions__','last_read',4,'0000-00-00 00:00:00','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'arche','__versions__','storage_type',5,'','NO','varchar',45,135,NULL,NULL,'utf8','utf8_general_ci','varchar(45)','','','select,insert,update,references',''),
 (NULL,'arche','__versions__','factSet',6,'','NO','varchar',45,135,NULL,NULL,'utf8','utf8_general_ci','varchar(45)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','__versions__','parent',7,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','__versions__','max_fact_id',8,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation','source',4,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation','target',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation','probability',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_module','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module','costOfChange',5,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module','complexity',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module','source',7,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_module','status',8,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module','id',9,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','source',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','parent',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','child',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','probability',8,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','cost',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','cumulativeprob',7,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','value',4,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','value',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','value',4,NULL,'NO','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','source',5,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','parent',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','child',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','source',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','parent',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','child',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','parentType',8,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','columns_priv','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'arche','columns_priv','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','columns_priv','User',3,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'arche','columns_priv','Table_name',4,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','columns_priv','Column_name',5,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','columns_priv','Timestamp',6,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','columns_priv','Column_priv',7,'','NO','set',31,93,NULL,NULL,'utf8','utf8_general_ci','set(\'Select\',\'Insert\',\'Update\',\'References\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'arche','db','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','db','User',3,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'arche','db','Select_priv',4,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Insert_priv',5,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','db','Update_priv',6,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Delete_priv',7,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Create_priv',8,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Drop_priv',9,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Grant_priv',10,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','References_priv',11,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','db','Index_priv',12,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Alter_priv',13,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Create_tmp_table_priv',14,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Lock_tables_priv',15,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Create_view_priv',16,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Show_view_priv',17,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','db','Create_routine_priv',18,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Alter_routine_priv',19,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','db','Execute_priv',20,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','design_module','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_module','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_module','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_module','id',4,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_module','costOfChange',5,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','design_module','complexity',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','design_module','name',7,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_module','source',8,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_module','status',9,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_moduledependencyrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_moduledependencyrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_moduledependencyrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_moduledependencyrelation','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','design_moduledependencyrelation','source',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_moduledependencyrelation','parent',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_moduledependencyrelation','child',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_moduledependencyrelation','probability',8,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','design_moduleinterface','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_moduleinterface','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_moduleinterface','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_moduleinterface','encapsulationLevel',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','design_moduleinterface','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_moduleinterface','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_moduleinterface','costOfChange',7,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_moduleinterface','status',8,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','design_modulerefinementrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_modulerefinementrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_modulerefinementrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_modulerefinementrelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_modulerefinementrelation','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_modulerefinementrelation','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_modulerefinementrelation','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_process','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_process','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_process','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_process','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_process','scenario',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_process','stimulusType',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_process','stimulusValue',7,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_process','taskType',8,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_process','taskValue',9,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_process','executionTime',10,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_process','latency',11,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_process','time_blocked',12,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_process','priority',13,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_process','source',14,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_realizerelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_realizerelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_realizerelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_realizerelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_realizerelation','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_realizerelation','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_realizerelation','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_realizerelation','childType',8,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_responsibility','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_responsibility','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_responsibility','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_responsibility','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_responsibility','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_responsibilitytomodulerelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_responsibilitytomodulerelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_responsibilitytomodulerelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_responsibilitytomodulerelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_responsibilitytomodulerelation','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_responsibilitytomodulerelation','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_responsibilitytomodulerelation','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_responsibilitytomodulerelation','parentType',8,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresource','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_sharedresource','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_sharedresource','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresource','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresource','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresource','description',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresource','source',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_sharedresourcerelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_sharedresourcerelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresourcerelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresourcerelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresourcerelation','parent',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_sharedresourcerelation','child',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_sharedresourcerelation','source',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_thread','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_thread','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_thread','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','scenario',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','stimulusType',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','stimulusValue',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','taskType',8,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_thread','taskValue',9,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','executionTime',10,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','latency',11,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','time_blocked',12,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','priority',13,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_thread','source',14,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_unitofconcurrency','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','task',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_unitofconcurrency','scenario',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','responsibility',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','MutualExclusion',8,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','SharedResourceID',9,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','taskType',10,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_unitofconcurrency','taskValue',11,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','executionTime',12,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','latency',13,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','priority',14,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_unitofconcurrency','source',15,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_variationpoint','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_variationpoint','value',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','mechanism',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','condition',8,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','alternatives',9,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','howTo',10,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_variationpoint','cost',11,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','owner',12,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','source',13,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_variationpoint','status',14,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_vpinputvalue','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_vpinputvalue','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_vpinputvalue','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_vpinputvalue','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_vpinputvalue','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_vpinputvalue','value',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_vpinputvalue','owner',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_vpinputvalue','source',8,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_vpinputvalue','status',9,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_wrapper','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','design_wrapper','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','design_wrapper','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','design_wrapper','encapsulationLevel',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','design_wrapper','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','design_wrapper','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','design_wrapper','status',7,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','scenario',4,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','id',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','parent',7,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','child',8,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','scenario',4,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','id',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','parent',7,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','child',8,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','scenario',4,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','id',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','parent',7,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','child',8,NULL,'YES','varbinary',100,100,NULL,NULL,NULL,NULL,'varbinary(100)','','','select,insert,update,references',''),
 (NULL,'arche','func','name',1,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','func','ret',2,'0','NO','tinyint',NULL,NULL,3,0,NULL,NULL,'tinyint(1)','','','select,insert,update,references',''),
 (NULL,'arche','func','dl',3,'','NO','char',128,384,NULL,NULL,'utf8','utf8_bin','char(128)','','','select,insert,update,references',''),
 (NULL,'arche','func','type',4,NULL,'NO','enum',9,27,NULL,NULL,'utf8','utf8_general_ci','enum(\'function\',\'aggregate\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','help_category','help_category_id',1,NULL,'NO','smallint',NULL,NULL,5,0,NULL,NULL,'smallint(5) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','help_category','name',2,NULL,'NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','UNI','','select,insert,update,references',''),
 (NULL,'arche','help_category','parent_category_id',3,NULL,'YES','smallint',NULL,NULL,5,0,NULL,NULL,'smallint(5) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','help_category','url',4,NULL,'NO','char',128,384,NULL,NULL,'utf8','utf8_general_ci','char(128)','','','select,insert,update,references',''),
 (NULL,'arche','help_keyword','help_keyword_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','help_keyword','name',2,NULL,'NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','UNI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','help_relation','help_topic_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','help_relation','help_keyword_id',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','help_topic','help_topic_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','help_topic','name',2,NULL,'NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','UNI','','select,insert,update,references',''),
 (NULL,'arche','help_topic','help_category_id',3,NULL,'NO','smallint',NULL,NULL,5,0,NULL,NULL,'smallint(5) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','help_topic','description',4,NULL,'NO','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','help_topic','example',5,NULL,'NO','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','help_topic','url',6,NULL,'NO','char',128,384,NULL,NULL,'utf8','utf8_general_ci','char(128)','','','select,insert,update,references',''),
 (NULL,'arche','host','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'arche','host','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','host','Select_priv',3,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Insert_priv',4,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','host','Update_priv',5,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Delete_priv',6,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Create_priv',7,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Drop_priv',8,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Grant_priv',9,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','References_priv',10,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','host','Index_priv',11,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Alter_priv',12,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Create_tmp_table_priv',13,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Lock_tables_priv',14,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Create_view_priv',15,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Show_view_priv',16,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','host','Create_routine_priv',17,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Alter_routine_priv',18,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','host','Execute_priv',19,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_assemblyinstance','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_assemblyinstance','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_assemblyinstance','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_assemblyinstance','icmFilename',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_executiontime','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_executiontime','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_executiontime','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_p_executiontime','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_executiontime','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_executiontime','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_executiontime','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_taskpriority','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_p_taskpriority','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_taskpriority','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_taskpriority','value',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_taskpriority','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_p_taskpriority','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_p_taskpriority','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','scenario',4,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','id',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','parent',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','child',8,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation','source',5,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation','parent',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation','child',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','leave_responsibilities','uid',1,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','leave_responsibilities','version',2,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','leave_responsibilities','fact-id',3,'','NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','leave_responsibilities','id',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','leave_responsibilities','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','leave_responsibilities','description',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','leave_responsibilities','source',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','leave_responsibilities','version_name',8,'','NO','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_dataflow','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_dataflow','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_dataflow','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_dataflow','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_dataflow','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_dataflow','producer',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_dataflow','consumer',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_dataflow','dataitem',8,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_dataitems','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_dataitems','version',2,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_dataitems','fact-id',3,'','NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_dataitems','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_dataitems','description',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_dataitems','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_function','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_function','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_function','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_function','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_function','description',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_functionrefinementrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_functionrefinementrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_functionrefinementrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_functionrefinementrelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_functionrefinementrelation','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_functionrefinementrelation','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_functionrefinementrelation','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_leafresponsibilitydependencyrelation','id',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation','incoming',8,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_leafresponsibilitydependencyrelation','outgoing',9,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','quality',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_p_analysisresult','reasoningFramework',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','isSatisfied',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','utility',7,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','value',8,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','oldValue',9,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_p_analysisresult','owner',10,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','source',11,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_p_analysisresult','status',12,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilities','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_responsibilities','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_responsibilities','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilities','id',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilities','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilities','description',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilities','source',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_responsibilities','status',8,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilityrefinementrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_responsibilityrefinementrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilityrefinementrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilityrefinementrelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_responsibilityrefinementrelation','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilityrefinementrelation','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilityrefinementrelation','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_responsibilitytoresponsibilityrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_scenariorefinementrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_scenariorefinementrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_scenariorefinementrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_scenariorefinementrelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenariorefinementrelation','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_scenariorefinementrelation','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenariorefinementrelation','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_scenarios','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','description',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','quality',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','stimulusText',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','stimulusType',8,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','stimulusUnit',9,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_scenarios','stimulusValue',10,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','sourceText',11,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','sourceType',12,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','sourceUnit',13,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','sourceValue',14,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_scenarios','artifactText',15,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','artifactType',16,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','artifactUnit',17,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','artifactValue',18,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','environmentText',19,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_scenarios','environmentType',20,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','environmentUnit',21,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','environmentValue',22,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','responseText',23,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','responseType',24,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_scenarios','responseUnit',25,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','responseValue',26,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','measureText',27,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','measureType',28,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','measureUnit',29,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_scenarios','measureValue',30,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','state',31,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_scenarios','reasoningFramework',32,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_sequencerelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_sequencerelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_sequencerelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_sequencerelation','scenario',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_sequencerelation','id',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_sequencerelation','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_sequencerelation','parent',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_sequencerelation','child',8,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_translationrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','main_translationrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_translationrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','main_translationrelation','parentType',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','main_translationrelation','id',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','main_translationrelation','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','main_translationrelation','parent',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','main_translationrelation','child',8,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','name',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','value',5,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','history1',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','history2',7,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','history3',8,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','history4',9,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','history5',10,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation','source',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation','target',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation','probability',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected','scenario',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected','responsibilityId',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected','nodeId',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','cost',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','cumulativeprob',7,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','value',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','value',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures','scenario',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures','responseMeasure',5,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics','type',1,'','NO','varchar',30,90,NULL,NULL,'utf8','utf8_general_ci','varchar(30)','PRI','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics','node_id',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics','version',3,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_tactics','sol_num',4,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics','ToolName',5,NULL,'NO','varchar',100,300,NULL,NULL,'utf8','utf8_general_ci','varchar(100)','','','select,insert,update,references',''),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics','loc_id',6,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','myfirstrf_p_testrelationparameter','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter','value',4,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','myfirstrf_testresponsibilityrelation','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation','source',5,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation','parent',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation','child',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_dependency','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_dependency','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_dependency','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_dependency','parent',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_dependency','child',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_dependency','pattern',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_dependency','name',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_dependency','description',8,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_interfacerealization','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','patterns_interfacerealization','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_interfacerealization','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_interfacerealization','parent',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_interfacerealization','child',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_interfacerealization','pattern',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_interfacerealization','name',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_interfacerealization','description',8,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_isarelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','patterns_isarelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_isarelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_isarelation','parent',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_isarelation','child',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_isarelation','pattern',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_isarelation','name',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_isarelation','description',8,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_pattern','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','patterns_pattern','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_pattern','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_pattern','group',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_pattern','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_pattern','description',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternconnector','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_patternconnector','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternconnector','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternconnector','type',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternconnector','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternconnector','valueAbs',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_patternconnector','valueFactor',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternconnector','pattern',8,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternconnector','name',9,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternconnector','description',10,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patterncontainer','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_patterncontainer','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patterncontainer','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patterncontainer','pattern',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patterncontainer','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patterncontainer','description',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_patternelement','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelement','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelement','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelement','pattern',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelement','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_patternelement','description',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelementinterface','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelementinterface','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelementinterface','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelementinterface','pattern',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_patternelementinterface','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternelementinterface','description',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternitemproperty','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','patterns_patternitemproperty','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternitemproperty','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_patternitemproperty','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternitemproperty','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternitemproperty','valueAbs',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternitemproperty','valueFactor',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_patternitemproperty','pattern',8,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_patternitemproperty','description',9,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_refinement','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','patterns_refinement','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_refinement','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','patterns_refinement','parent',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','patterns_refinement','child',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_refinement','pattern',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','patterns_refinement','name',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','patterns_refinement','description',8,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_executiontime','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','performancereasoningframeworks_p_executiontime','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_executiontime','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_executiontime','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_executiontime','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_executiontime','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','performancereasoningframeworks_p_executiontime','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion','value',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','value',4,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','owner',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','source',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','status',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addfunction','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','planner_c_addfunction','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addfunction','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addfunction','functionID',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addfunction','description',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addfunction','state',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addresponsibilityrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','planner_c_addresponsibilityrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addresponsibilityrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addresponsibilityrelation','parent',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addresponsibilityrelation','child',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addresponsibilityrelation','type',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addresponsibilityrelation','state',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addscenario','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','description',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','quality',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','stimulusText',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','stimulusType',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addscenario','stimulusUnit',8,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','stimulusValue',9,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','sourceText',10,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','sourceType',11,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','sourceUnit',12,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addscenario','sourceValue',13,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','artifactText',14,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','artifactType',15,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','artifactUnit',16,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','artifactValue',17,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addscenario','environmentText',18,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','environmentType',19,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','environmentUnit',20,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','environmentValue',21,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','responseText',22,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addscenario','responseType',23,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','responseUnit',24,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','responseValue',25,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','measureText',26,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','measureType',27,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addscenario','measureUnit',28,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','measureValue',29,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','state',30,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addscenario','reasoningFramework',31,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addtranslationrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addtranslationrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addtranslationrelation','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addtranslationrelation','parent',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addtranslationrelation','child',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','planner_c_addtranslationrelation','type',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addtranslationrelation','state',7,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','proc','db',1,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','proc','name',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','proc','type',3,NULL,'NO','enum',9,27,NULL,NULL,'utf8','utf8_general_ci','enum(\'FUNCTION\',\'PROCEDURE\')','PRI','','select,insert,update,references',''),
 (NULL,'arche','proc','specific_name',4,'','NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','','','select,insert,update,references',''),
 (NULL,'arche','proc','language',5,'SQL','NO','enum',3,9,NULL,NULL,'utf8','utf8_general_ci','enum(\'SQL\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','proc','sql_data_access',6,'CONTAINS_SQL','NO','enum',17,51,NULL,NULL,'utf8','utf8_general_ci','enum(\'CONTAINS_SQL\',\'NO_SQL\',\'READS_SQL_DATA\',\'MODIFIES_SQL_DATA\')','','','select,insert,update,references',''),
 (NULL,'arche','proc','is_deterministic',7,'NO','NO','enum',3,9,NULL,NULL,'utf8','utf8_general_ci','enum(\'YES\',\'NO\')','','','select,insert,update,references',''),
 (NULL,'arche','proc','security_type',8,'DEFINER','NO','enum',7,21,NULL,NULL,'utf8','utf8_general_ci','enum(\'INVOKER\',\'DEFINER\')','','','select,insert,update,references',''),
 (NULL,'arche','proc','param_list',9,NULL,'NO','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'arche','proc','returns',10,'','NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','proc','body',11,NULL,'NO','longblob',4294967295,4294967295,NULL,NULL,NULL,NULL,'longblob','','','select,insert,update,references',''),
 (NULL,'arche','proc','definer',12,'','NO','char',77,231,NULL,NULL,'utf8','utf8_bin','char(77)','','','select,insert,update,references',''),
 (NULL,'arche','proc','created',13,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'arche','proc','modified',14,'0000-00-00 00:00:00','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'arche','proc','sql_mode',15,'','NO','set',431,1293,NULL,NULL,'utf8','utf8_general_ci','set(\'REAL_AS_FLOAT\',\'PIPES_AS_CONCAT\',\'ANSI_QUOTES\',\'IGNORE_SPACE\',\'NOT_USED\',\'ONLY_FULL_GROUP_BY\',\'NO_UNSIGNED_SUBTRACTION\',\'NO_DIR_IN_CREATE\',\'POSTGRESQL\',\'ORACLE\',\'MSSQL\',\'DB2\',\'MAXDB\',\'NO_KEY_OPTIONS\',\'NO_TABLE_OPTIONS\',\'NO_FIELD_OPTIONS\',\'MYSQL323\',\'MYSQL40\',\'ANSI\',\'NO_AUTO_VALUE_ON_ZERO\',\'NO_BACKSLASH_ESCAPES\',\'STRICT_TRANS_TABLES\',\'STRICT_ALL_TABLES\',\'NO_ZERO_IN_DATE\',\'NO_ZERO_DATE\',\'INVALID_DATES\',\'ERROR_FOR_DIVISION_BY_ZERO\',\'TRADITIONAL\',\'NO_AUTO_CREATE_USER\',\'HIGH_NOT_PRECEDENCE\')','','','select,insert,update,references',''),
 (NULL,'arche','proc','comment',16,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','procs_priv','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'arche','procs_priv','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','procs_priv','User',3,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'arche','procs_priv','Routine_name',4,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','procs_priv','Routine_type',5,NULL,'NO','enum',9,27,NULL,NULL,'utf8','utf8_bin','enum(\'FUNCTION\',\'PROCEDURE\')','PRI','','select,insert,update,references',''),
 (NULL,'arche','procs_priv','Grantor',6,'','NO','char',77,231,NULL,NULL,'utf8','utf8_bin','char(77)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','procs_priv','Proc_priv',7,'','NO','set',27,81,NULL,NULL,'utf8','utf8_general_ci','set(\'Execute\',\'Alter Routine\',\'Grant\')','','','select,insert,update,references',''),
 (NULL,'arche','procs_priv','Timestamp',8,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_p_latency','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmamodel_p_latency','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_p_latency','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmamodel_p_latency','utilization',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_p_latency','value',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_p_latency','time_blocked',6,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_p_latency','owner',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_p_latency','source',8,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmamodel_p_latency','status',9,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmamodel_subtask','task',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','scenario',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','responsibility',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','MutualExclusion',8,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','SharedResourceID',9,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmamodel_subtask','taskType',10,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','taskValue',11,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','executionTime',12,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','latency',13,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_subtask','priority',14,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmamodel_subtask','source',15,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','scenario',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmamodel_task','stimulusType',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','stimulusValue',7,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','taskType',8,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','taskValue',9,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','executionTime',10,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmamodel_task','latency',11,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','time_blocked',12,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','priority',13,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmamodel_task','source',14,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_executiontime','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_p_executiontime','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_executiontime','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_executiontime','value',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_executiontime','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_executiontime','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_p_executiontime','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_shared','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_shared','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_shared','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_shared','value',4,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_p_shared','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_shared','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_shared','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_p_taskpriority','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority','value',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority','owner',5,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority','source',6,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority','status',7,NULL,'YES','varchar',200,200,NULL,NULL,'latin1','latin1_swedish_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_resource','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resource','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resource','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resource','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resource','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_resource','source',6,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resource','status',7,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resource','shared',8,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resourceusagerelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resourceusagerelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_resourceusagerelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resourceusagerelation','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resourceusagerelation','source',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resourceusagerelation','parent',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_resourceusagerelation','child',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_thread','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_thread','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_thread','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_thread','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_thread','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_thread','period',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_thread','source',7,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_thread','status',8,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_thread','scenario',9,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_threadtouocrelation','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation','source',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation','parent',6,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation','child',7,NULL,'YES','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_unitofconcurrency','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency','fact-id',3,NULL,'NO','varchar',20,20,NULL,NULL,'latin1','latin1_swedish_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency','id',4,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_unitofconcurrency','executionTime',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency','source',7,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency','status',8,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency','responsibility',9,NULL,'YES','text',65535,65535,NULL,NULL,'latin1','latin1_swedish_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','seeker_evaluationresult','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','scenario',4,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','tacticScenario',5,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','reasoningFramework',6,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','seeker_evaluationresult','tactic',7,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','tacticDescription',8,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','result',9,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','utility',10,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','change',11,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','seeker_evaluationresult','relevance',12,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','tacticId',13,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','index',14,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10)','','','select,insert,update,references',''),
 (NULL,'arche','seeker_evaluationresult','nresult',15,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','tables_priv','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'arche','tables_priv','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','tables_priv','User',3,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'arche','tables_priv','Table_name',4,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','tables_priv','Grantor',5,'','NO','char',77,231,NULL,NULL,'utf8','utf8_bin','char(77)','MUL','','select,insert,update,references',''),
 (NULL,'arche','tables_priv','Timestamp',6,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'arche','tables_priv','Table_priv',7,'','NO','set',90,270,NULL,NULL,'utf8','utf8_general_ci','set(\'Select\',\'Insert\',\'Update\',\'Delete\',\'Create\',\'Drop\',\'Grant\',\'References\',\'Index\',\'Alter\',\'Create View\',\'Show view\')','','','select,insert,update,references',''),
 (NULL,'arche','tables_priv','Column_priv',8,'','NO','set',31,93,NULL,NULL,'utf8','utf8_general_ci','set(\'Select\',\'Insert\',\'Update\',\'References\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','tactics','type',1,'','NO','varchar',30,30,NULL,NULL,'latin1','latin1_swedish_ci','varchar(30)','PRI','','select,insert,update,references',''),
 (NULL,'arche','tactics','node_id',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','tactics','name',3,NULL,'YES','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','tactics','version',4,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','tactics','level',5,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','test_interface','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_interface','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_interface','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_interface','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_interface','moduleName',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_interface','costOfChange',6,'0','NO','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','test_leave_dependencies','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_leave_dependencies','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_leave_dependencies','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_leave_dependencies','parent',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_leave_dependencies','child',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_model_arcs','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_model_arcs','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_model_arcs','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_model_arcs','parent',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_model_arcs','child',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_model_arcs','probability',6,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','test_model_nodes','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_model_nodes','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_model_nodes','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_model_nodes','node',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_model_nodes','costOfChange',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_model_nodes_affected','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','test_model_nodes_affected','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_model_nodes_affected','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_model_nodes_affected','parent',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_model_nodes_affected','child',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_module','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','test_module','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_module','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_module','costOfChange',4,NULL,'YES','double',NULL,NULL,22,NULL,NULL,NULL,'double','','','select,insert,update,references',''),
 (NULL,'arche','test_module','name',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_module_dependencies','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','test_module_dependencies','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_module_dependencies','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_module_dependencies','parent',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_module_dependencies','child',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_module_dependencies','childType',6,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_module_responsibilities','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','test_module_responsibilities','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_module_responsibilities','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_module_responsibilities','module',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_module_responsibilities','responsibility',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_module_responsibilities','type',6,NULL,'YES','varchar',50,150,NULL,NULL,'utf8','utf8_general_ci','varchar(50)','','','select,insert,update,references',''),
 (NULL,'arche','test_responsibilities','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_responsibilities','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_responsibilities','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_responsibilities','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_responsibility_refinement','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','test_responsibility_refinement','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','test_responsibility_refinement','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','test_responsibility_refinement','parent',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','test_responsibility_refinement','child',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','time_zone','Time_zone_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','time_zone','Use_leap_seconds',2,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'Y\',\'N\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','time_zone_leap_second','Transition_time',1,NULL,'NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(20)','PRI','','select,insert,update,references',''),
 (NULL,'arche','time_zone_leap_second','Correction',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(11)','','','select,insert,update,references',''),
 (NULL,'arche','time_zone_name','Name',1,NULL,'NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'arche','time_zone_name','Time_zone_id',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','time_zone_transition','Time_zone_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','time_zone_transition','Transition_time',2,NULL,'NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(20)','PRI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','time_zone_transition','Transition_type_id',3,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','time_zone_transition_type','Time_zone_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','time_zone_transition_type','Transition_type_id',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'arche','time_zone_transition_type','Offset',3,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11)','','','select,insert,update,references',''),
 (NULL,'arche','time_zone_transition_type','Is_DST',4,'0','NO','tinyint',NULL,NULL,3,0,NULL,NULL,'tinyint(3) unsigned','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','time_zone_transition_type','Abbreviation',5,'','NO','char',8,24,NULL,NULL,'utf8','utf8_general_ci','char(8)','','','select,insert,update,references',''),
 (NULL,'arche','user','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'arche','user','User',2,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'arche','user','Password',3,'','NO','char',41,41,NULL,NULL,'latin1','latin1_bin','char(41)','','','select,insert,update,references',''),
 (NULL,'arche','user','Select_priv',4,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Insert_priv',5,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','user','Update_priv',6,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Delete_priv',7,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Create_priv',8,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Drop_priv',9,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Reload_priv',10,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Shutdown_priv',11,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','user','Process_priv',12,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','File_priv',13,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Grant_priv',14,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','References_priv',15,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Index_priv',16,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Alter_priv',17,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','user','Show_db_priv',18,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Super_priv',19,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Create_tmp_table_priv',20,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Lock_tables_priv',21,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Execute_priv',22,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Repl_slave_priv',23,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','user','Repl_client_priv',24,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Create_view_priv',25,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Show_view_priv',26,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Create_routine_priv',27,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','Alter_routine_priv',28,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','user','Create_user_priv',29,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'arche','user','ssl_type',30,'','NO','enum',9,27,NULL,NULL,'utf8','utf8_general_ci','enum(\'\',\'ANY\',\'X509\',\'SPECIFIED\')','','','select,insert,update,references',''),
 (NULL,'arche','user','ssl_cipher',31,NULL,'NO','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'arche','user','x509_issuer',32,NULL,'NO','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'arche','user','x509_subject',33,NULL,'NO','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'arche','user','max_questions',34,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11) unsigned','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','user','max_updates',35,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','user','max_connections',36,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','user','max_user_connections',37,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11) unsigned','','','select,insert,update,references',''),
 (NULL,'arche','user_info','User',1,NULL,'NO','varchar',16,48,NULL,NULL,'utf8','utf8_bin','varchar(16)','PRI','','select,insert,update,references',''),
 (NULL,'arche','user_info','Full_name',2,NULL,'YES','varchar',60,180,NULL,NULL,'utf8','utf8_bin','varchar(60)','MUL','','select,insert,update,references',''),
 (NULL,'arche','user_info','Description',3,NULL,'YES','varchar',255,765,NULL,NULL,'utf8','utf8_bin','varchar(255)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','user_info','Email',4,NULL,'YES','varchar',80,240,NULL,NULL,'utf8','utf8_bin','varchar(80)','','','select,insert,update,references',''),
 (NULL,'arche','user_info','Contact_information',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_bin','text','','','select,insert,update,references',''),
 (NULL,'arche','user_info','Icon',6,NULL,'YES','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'arche','v_factset','factType',1,'','NO','varchar',255,765,NULL,NULL,'utf8','utf8_general_ci','varchar(255)','','','select,insert,update,references',''),
 (NULL,'arche','v_factset','setName',2,'','NO','varchar',45,135,NULL,NULL,'utf8','utf8_general_ci','varchar(45)','','','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','id',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','source',5,NULL,'YES','varchar',200,600,NULL,NULL,'utf8','utf8_general_ci','varchar(200)','','','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','parent',6,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','child',7,NULL,'YES','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','','','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration','uid',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration','version',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','MUL','','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration','fact-id',3,NULL,'NO','varchar',20,60,NULL,NULL,'utf8','utf8_general_ci','varchar(20)','MUL','','select,insert,update,references',''),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration','name',4,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'arche','variabilityreasoningframework_vpconfiguration','inputs',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'mysql','columns_priv','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','columns_priv','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','columns_priv','User',3,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','columns_priv','Table_name',4,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','columns_priv','Column_name',5,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','columns_priv','Timestamp',6,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'mysql','columns_priv','Column_priv',7,'','NO','set',31,93,NULL,NULL,'utf8','utf8_general_ci','set(\'Select\',\'Insert\',\'Update\',\'References\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','db','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','db','User',3,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','db','Select_priv',4,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','db','Insert_priv',5,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Update_priv',6,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Delete_priv',7,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Create_priv',8,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Drop_priv',9,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Grant_priv',10,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','db','References_priv',11,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Index_priv',12,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Alter_priv',13,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Create_tmp_table_priv',14,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Lock_tables_priv',15,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Create_view_priv',16,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','db','Show_view_priv',17,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Create_routine_priv',18,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Alter_routine_priv',19,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','db','Execute_priv',20,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','func','name',1,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','func','ret',2,'0','NO','tinyint',NULL,NULL,3,0,NULL,NULL,'tinyint(1)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','func','dl',3,'','NO','char',128,384,NULL,NULL,'utf8','utf8_bin','char(128)','','','select,insert,update,references',''),
 (NULL,'mysql','func','type',4,NULL,'NO','enum',9,27,NULL,NULL,'utf8','utf8_general_ci','enum(\'function\',\'aggregate\')','','','select,insert,update,references',''),
 (NULL,'mysql','help_category','help_category_id',1,NULL,'NO','smallint',NULL,NULL,5,0,NULL,NULL,'smallint(5) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'mysql','help_category','name',2,NULL,'NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','UNI','','select,insert,update,references',''),
 (NULL,'mysql','help_category','parent_category_id',3,NULL,'YES','smallint',NULL,NULL,5,0,NULL,NULL,'smallint(5) unsigned','','','select,insert,update,references',''),
 (NULL,'mysql','help_category','url',4,NULL,'NO','char',128,384,NULL,NULL,'utf8','utf8_general_ci','char(128)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','help_keyword','help_keyword_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'mysql','help_keyword','name',2,NULL,'NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','UNI','','select,insert,update,references',''),
 (NULL,'mysql','help_relation','help_topic_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'mysql','help_relation','help_keyword_id',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'mysql','help_topic','help_topic_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'mysql','help_topic','name',2,NULL,'NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','UNI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','help_topic','help_category_id',3,NULL,'NO','smallint',NULL,NULL,5,0,NULL,NULL,'smallint(5) unsigned','','','select,insert,update,references',''),
 (NULL,'mysql','help_topic','description',4,NULL,'NO','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'mysql','help_topic','example',5,NULL,'NO','text',65535,65535,NULL,NULL,'utf8','utf8_general_ci','text','','','select,insert,update,references',''),
 (NULL,'mysql','help_topic','url',6,NULL,'NO','char',128,384,NULL,NULL,'utf8','utf8_general_ci','char(128)','','','select,insert,update,references',''),
 (NULL,'mysql','host','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','host','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','host','Select_priv',3,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Insert_priv',4,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Update_priv',5,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Delete_priv',6,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Create_priv',7,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Drop_priv',8,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','host','Grant_priv',9,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','References_priv',10,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Index_priv',11,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Alter_priv',12,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Create_tmp_table_priv',13,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Lock_tables_priv',14,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','host','Create_view_priv',15,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Show_view_priv',16,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Create_routine_priv',17,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Alter_routine_priv',18,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','host','Execute_priv',19,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','proc','db',1,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','proc','name',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','proc','type',3,NULL,'NO','enum',9,27,NULL,NULL,'utf8','utf8_general_ci','enum(\'FUNCTION\',\'PROCEDURE\')','PRI','','select,insert,update,references',''),
 (NULL,'mysql','proc','specific_name',4,'','NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','','','select,insert,update,references',''),
 (NULL,'mysql','proc','language',5,'SQL','NO','enum',3,9,NULL,NULL,'utf8','utf8_general_ci','enum(\'SQL\')','','','select,insert,update,references',''),
 (NULL,'mysql','proc','sql_data_access',6,'CONTAINS_SQL','NO','enum',17,51,NULL,NULL,'utf8','utf8_general_ci','enum(\'CONTAINS_SQL\',\'NO_SQL\',\'READS_SQL_DATA\',\'MODIFIES_SQL_DATA\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','proc','is_deterministic',7,'NO','NO','enum',3,9,NULL,NULL,'utf8','utf8_general_ci','enum(\'YES\',\'NO\')','','','select,insert,update,references',''),
 (NULL,'mysql','proc','security_type',8,'DEFINER','NO','enum',7,21,NULL,NULL,'utf8','utf8_general_ci','enum(\'INVOKER\',\'DEFINER\')','','','select,insert,update,references',''),
 (NULL,'mysql','proc','param_list',9,'','NO','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'mysql','proc','returns',10,'','NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','','','select,insert,update,references',''),
 (NULL,'mysql','proc','body',11,'','NO','longblob',4294967295,4294967295,NULL,NULL,NULL,NULL,'longblob','','','select,insert,update,references',''),
 (NULL,'mysql','proc','definer',12,'','NO','char',77,231,NULL,NULL,'utf8','utf8_bin','char(77)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','proc','created',13,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'mysql','proc','modified',14,'0000-00-00 00:00:00','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'mysql','proc','sql_mode',15,'','NO','set',431,1293,NULL,NULL,'utf8','utf8_general_ci','set(\'REAL_AS_FLOAT\',\'PIPES_AS_CONCAT\',\'ANSI_QUOTES\',\'IGNORE_SPACE\',\'NOT_USED\',\'ONLY_FULL_GROUP_BY\',\'NO_UNSIGNED_SUBTRACTION\',\'NO_DIR_IN_CREATE\',\'POSTGRESQL\',\'ORACLE\',\'MSSQL\',\'DB2\',\'MAXDB\',\'NO_KEY_OPTIONS\',\'NO_TABLE_OPTIONS\',\'NO_FIELD_OPTIONS\',\'MYSQL323\',\'MYSQL40\',\'ANSI\',\'NO_AUTO_VALUE_ON_ZERO\',\'NO_BACKSLASH_ESCAPES\',\'STRICT_TRANS_TABLES\',\'STRICT_ALL_TABLES\',\'NO_ZERO_IN_DATE\',\'NO_ZERO_DATE\',\'INVALID_DATES\',\'ERROR_FOR_DIVISION_BY_ZERO\',\'TRADITIONAL\',\'NO_AUTO_CREATE_USER\',\'HIGH_NOT_PRECEDENCE\')','','','select,insert,update,references',''),
 (NULL,'mysql','proc','comment',16,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','procs_priv','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','procs_priv','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','procs_priv','User',3,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','procs_priv','Routine_name',4,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','procs_priv','Routine_type',5,NULL,'NO','enum',9,27,NULL,NULL,'utf8','utf8_bin','enum(\'FUNCTION\',\'PROCEDURE\')','PRI','','select,insert,update,references',''),
 (NULL,'mysql','procs_priv','Grantor',6,'','NO','char',77,231,NULL,NULL,'utf8','utf8_bin','char(77)','MUL','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','procs_priv','Proc_priv',7,'','NO','set',27,81,NULL,NULL,'utf8','utf8_general_ci','set(\'Execute\',\'Alter Routine\',\'Grant\')','','','select,insert,update,references',''),
 (NULL,'mysql','procs_priv','Timestamp',8,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'mysql','tables_priv','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','tables_priv','Db',2,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','tables_priv','User',3,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','tables_priv','Table_name',4,'','NO','char',64,192,NULL,NULL,'utf8','utf8_bin','char(64)','PRI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','tables_priv','Grantor',5,'','NO','char',77,231,NULL,NULL,'utf8','utf8_bin','char(77)','MUL','','select,insert,update,references',''),
 (NULL,'mysql','tables_priv','Timestamp',6,'CURRENT_TIMESTAMP','YES','timestamp',NULL,NULL,NULL,NULL,NULL,NULL,'timestamp','','','select,insert,update,references',''),
 (NULL,'mysql','tables_priv','Table_priv',7,'','NO','set',90,270,NULL,NULL,'utf8','utf8_general_ci','set(\'Select\',\'Insert\',\'Update\',\'Delete\',\'Create\',\'Drop\',\'Grant\',\'References\',\'Index\',\'Alter\',\'Create View\',\'Show view\')','','','select,insert,update,references',''),
 (NULL,'mysql','tables_priv','Column_priv',8,'','NO','set',31,93,NULL,NULL,'utf8','utf8_general_ci','set(\'Select\',\'Insert\',\'Update\',\'References\')','','','select,insert,update,references',''),
 (NULL,'mysql','time_zone','Time_zone_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','auto_increment','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','time_zone','Use_leap_seconds',2,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'Y\',\'N\')','','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_leap_second','Transition_time',1,NULL,'NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(20)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_leap_second','Correction',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(11)','','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_name','Name',1,NULL,'NO','char',64,192,NULL,NULL,'utf8','utf8_general_ci','char(64)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_name','Time_zone_id',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_transition','Time_zone_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','time_zone_transition','Transition_time',2,NULL,'NO','bigint',NULL,NULL,19,0,NULL,NULL,'bigint(20)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_transition','Transition_type_id',3,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_transition_type','Time_zone_id',1,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_transition_type','Transition_type_id',2,NULL,'NO','int',NULL,NULL,10,0,NULL,NULL,'int(10) unsigned','PRI','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_transition_type','Offset',3,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','time_zone_transition_type','Is_DST',4,'0','NO','tinyint',NULL,NULL,3,0,NULL,NULL,'tinyint(3) unsigned','','','select,insert,update,references',''),
 (NULL,'mysql','time_zone_transition_type','Abbreviation',5,'','NO','char',8,24,NULL,NULL,'utf8','utf8_general_ci','char(8)','','','select,insert,update,references',''),
 (NULL,'mysql','user','Host',1,'','NO','char',60,180,NULL,NULL,'utf8','utf8_bin','char(60)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','user','User',2,'','NO','char',16,48,NULL,NULL,'utf8','utf8_bin','char(16)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','user','Password',3,'','NO','char',41,41,NULL,NULL,'latin1','latin1_bin','char(41)','','','select,insert,update,references',''),
 (NULL,'mysql','user','Select_priv',4,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','user','Insert_priv',5,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Update_priv',6,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Delete_priv',7,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Create_priv',8,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Drop_priv',9,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Reload_priv',10,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','user','Shutdown_priv',11,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Process_priv',12,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','File_priv',13,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Grant_priv',14,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','References_priv',15,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Index_priv',16,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','user','Alter_priv',17,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Show_db_priv',18,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Super_priv',19,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Create_tmp_table_priv',20,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Lock_tables_priv',21,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Execute_priv',22,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','user','Repl_slave_priv',23,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Repl_client_priv',24,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Create_view_priv',25,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Show_view_priv',26,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Create_routine_priv',27,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','Alter_routine_priv',28,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','user','Create_user_priv',29,'N','NO','enum',1,3,NULL,NULL,'utf8','utf8_general_ci','enum(\'N\',\'Y\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','ssl_type',30,'','NO','enum',9,27,NULL,NULL,'utf8','utf8_general_ci','enum(\'\',\'ANY\',\'X509\',\'SPECIFIED\')','','','select,insert,update,references',''),
 (NULL,'mysql','user','ssl_cipher',31,NULL,'NO','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'mysql','user','x509_issuer',32,NULL,'NO','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'mysql','user','x509_subject',33,NULL,'NO','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references',''),
 (NULL,'mysql','user','max_questions',34,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11) unsigned','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','user','max_updates',35,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11) unsigned','','','select,insert,update,references',''),
 (NULL,'mysql','user','max_connections',36,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11) unsigned','','','select,insert,update,references',''),
 (NULL,'mysql','user','max_user_connections',37,'0','NO','int',NULL,NULL,10,0,NULL,NULL,'int(11) unsigned','','','select,insert,update,references',''),
 (NULL,'mysql','user_info','User',1,NULL,'NO','varchar',16,48,NULL,NULL,'utf8','utf8_bin','varchar(16)','PRI','','select,insert,update,references',''),
 (NULL,'mysql','user_info','Full_name',2,NULL,'YES','varchar',60,180,NULL,NULL,'utf8','utf8_bin','varchar(60)','MUL','','select,insert,update,references',''),
 (NULL,'mysql','user_info','Description',3,NULL,'YES','varchar',255,765,NULL,NULL,'utf8','utf8_bin','varchar(255)','','','select,insert,update,references','');
INSERT INTO `COLUMNS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`COLUMN_DEFAULT`,`IS_NULLABLE`,`DATA_TYPE`,`CHARACTER_MAXIMUM_LENGTH`,`CHARACTER_OCTET_LENGTH`,`NUMERIC_PRECISION`,`NUMERIC_SCALE`,`CHARACTER_SET_NAME`,`COLLATION_NAME`,`COLUMN_TYPE`,`COLUMN_KEY`,`EXTRA`,`PRIVILEGES`,`COLUMN_COMMENT`) VALUES 
 (NULL,'mysql','user_info','Email',4,NULL,'YES','varchar',80,240,NULL,NULL,'utf8','utf8_bin','varchar(80)','','','select,insert,update,references',''),
 (NULL,'mysql','user_info','Contact_information',5,NULL,'YES','text',65535,65535,NULL,NULL,'utf8','utf8_bin','text','','','select,insert,update,references',''),
 (NULL,'mysql','user_info','Icon',6,NULL,'YES','blob',65535,65535,NULL,NULL,NULL,NULL,'blob','','','select,insert,update,references','');
/*!40000 ALTER TABLE `COLUMNS` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`COLUMN_PRIVILEGES`
--

DROP TABLE IF EXISTS `COLUMN_PRIVILEGES`;
CREATE TEMPORARY TABLE `COLUMN_PRIVILEGES` (
  `GRANTEE` varchar(81) NOT NULL default '',
  `TABLE_CATALOG` varchar(512) default NULL,
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `TABLE_NAME` varchar(64) NOT NULL default '',
  `COLUMN_NAME` varchar(64) NOT NULL default '',
  `PRIVILEGE_TYPE` varchar(64) NOT NULL default '',
  `IS_GRANTABLE` varchar(3) NOT NULL default ''
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`COLUMN_PRIVILEGES`
--

/*!40000 ALTER TABLE `COLUMN_PRIVILEGES` DISABLE KEYS */;
/*!40000 ALTER TABLE `COLUMN_PRIVILEGES` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`KEY_COLUMN_USAGE`
--

DROP TABLE IF EXISTS `KEY_COLUMN_USAGE`;
CREATE TEMPORARY TABLE `KEY_COLUMN_USAGE` (
  `CONSTRAINT_CATALOG` varchar(512) default NULL,
  `CONSTRAINT_SCHEMA` varchar(64) NOT NULL default '',
  `CONSTRAINT_NAME` varchar(64) NOT NULL default '',
  `TABLE_CATALOG` varchar(512) default NULL,
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `TABLE_NAME` varchar(64) NOT NULL default '',
  `COLUMN_NAME` varchar(64) NOT NULL default '',
  `ORDINAL_POSITION` bigint(10) NOT NULL default '0',
  `POSITION_IN_UNIQUE_CONSTRAINT` bigint(10) default NULL,
  `REFERENCED_TABLE_SCHEMA` varchar(64) default NULL,
  `REFERENCED_TABLE_NAME` varchar(64) default NULL,
  `REFERENCED_COLUMN_NAME` varchar(64) default NULL
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`KEY_COLUMN_USAGE`
--

/*!40000 ALTER TABLE `KEY_COLUMN_USAGE` DISABLE KEYS */;
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','__factsets__','ID',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','__mod_solver_interface__','ID',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','__tool_command_interface__','ID',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','__versions__','ID',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_arc_relation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_arc_relation',NULL,'arche','changeimpactmodifiabilityrf_arc_relation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_module','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_module',NULL,'arche','changeimpactmodifiabilityrf_module','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_moduledependencyrelation',NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_node_responsibility',NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_p_booleantest',NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_changeimpactmodifiabilityrf_p_costofchange',NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_p_probabilityincoming',NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_p_probabilityoutgoing',NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_changeimpactmodifiabilityrf_responsibilitydependencyrelation',NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_responsibilitytomodulerelation',NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','columns_priv','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','columns_priv','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','columns_priv','User',3,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','columns_priv','Table_name',4,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','columns_priv','Column_name',5,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','db','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','db','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','db','User',3,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','design_module','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_module',NULL,'arche','design_module','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_moduledependencyrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_moduledependencyrelation',NULL,'arche','design_moduledependencyrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_moduleinterface','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_design_moduleinterface',NULL,'arche','design_moduleinterface','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_modulerefinementrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_modulerefinementrelation',NULL,'arche','design_modulerefinementrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_process','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_process',NULL,'arche','design_process','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_realizerelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_realizerelation',NULL,'arche','design_realizerelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_responsibility','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_design_responsibility',NULL,'arche','design_responsibility','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_responsibilitytomodulerelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_responsibilitytomodulerelation',NULL,'arche','design_responsibilitytomodulerelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_sharedresource','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_sharedresource',NULL,'arche','design_sharedresource','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_sharedresourcerelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','design_sharedresourcerelation_ibfk_1',NULL,'arche','design_sharedresourcerelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_thread','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_design_thread',NULL,'arche','design_thread','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_unitofconcurrency','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_unitofconcurrency',NULL,'arche','design_unitofconcurrency','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_variationpoint','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_variationpoint',NULL,'arche','design_variationpoint','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_vpinputvalue','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_design_vpinputvalue',NULL,'arche','design_vpinputvalue','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','design_wrapper','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_design_wrapper',NULL,'arche','design_wrapper','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','externalinteraction_responsibilitydependencyrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_externalinteraction_responsibilitydependencyrelation',NULL,'arche','externalinteraction_responsibilitydependencyrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','externalinteraction_responsibilityinteractionrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_externalinteraction_responsibilityinteractionrelation',NULL,'arche','externalinteraction_responsibilityinteractionrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','externalinteraction_responsibilityreactionrelation','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_externalinteraction_responsibilityreactionrelation',NULL,'arche','externalinteraction_responsibilityreactionrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','func','name',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','help_category','help_category_id',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','name',NULL,'arche','help_category','name',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','help_keyword','help_keyword_id',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','name',NULL,'arche','help_keyword','name',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','help_relation','help_keyword_id',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','help_relation','help_topic_id',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','help_topic','help_topic_id',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','name',NULL,'arche','help_topic','name',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','host','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','host','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','icmperformancerf_assemblyinstance','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_icmperformancerf_assemblyinstance',NULL,'arche','icmperformancerf_assemblyinstance','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','icmperformancerf_p_executiontime','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_icmperformancerf_p_executiontime',NULL,'arche','icmperformancerf_p_executiontime','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','icmperformancerf_p_taskpriority','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_icmperformancerf_p_taskpriority',NULL,'arche','icmperformancerf_p_taskpriority','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','icmperformancerf_responsibilityinteractionrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_icmperformancerf_responsibilityinteractionrelation',NULL,'arche','icmperformancerf_responsibilityinteractionrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','icmperformancerf_responsibilityreactionrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_icmperformancerf_responsibilityreactionrelation',NULL,'arche','icmperformancerf_responsibilityreactionrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_dataflow','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_main_dataflow',NULL,'arche','main_dataflow','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_dataitems','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_dataitems_1',NULL,'arche','main_dataitems','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_function','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_function',NULL,'arche','main_function','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_functionrefinementrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_functionrefinementrelation',NULL,'arche','main_functionrefinementrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_leafresponsibilitydependencyrelation','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_main_leafresponsibilitydependencyrelation',NULL,'arche','main_leafresponsibilitydependencyrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_p_analysisresult','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_p_analysisresult',NULL,'arche','main_p_analysisresult','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_responsibilities','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_responsibilities',NULL,'arche','main_responsibilities','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_responsibilityrefinementrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_responsibilityrefinementrelation',NULL,'arche','main_responsibilityrefinementrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_responsibilitytoresponsibilityrelation','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_main_responsibilitytoresponsibilityrelation',NULL,'arche','main_responsibilitytoresponsibilityrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_scenariorefinementrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_scenariorefinementrelation',NULL,'arche','main_scenariorefinementrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_scenarios','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_scenarios',NULL,'arche','main_scenarios','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_sequencerelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_main_sequencerelation',NULL,'arche','main_sequencerelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','main_translationrelation','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_main_translationrelation',NULL,'arche','main_translationrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_modifiabilitylearn_modifiabilitylearnvalues',NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_arc_relation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_arc_relation',NULL,'arche','modifiabilityreasoningframeworks_arc_relation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_node_affected','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_node_affected',NULL,'arche','modifiabilityreasoningframeworks_node_affected','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_node_responsibility',NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_p_abstractiontranslator',NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_p_costofchange',NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel_ibfk_1',NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_p_interfacerouter',NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange_ibfk_1',NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming_ibfk_1',NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing_ibfk_1',NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_response_measures','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_response_measures',NULL,'arche','modifiabilityreasoningframeworks_response_measures','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_tactics','type',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_tactics','node_id',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','modifiabilityreasoningframeworks_tactics','version',3,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','myfirstrf_p_testrelationparameter','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_myfirstrf_p_testrelationparameter',NULL,'arche','myfirstrf_p_testrelationparameter','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','myfirstrf_p_testresponsibilityparameter','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_myfirstrf_p_testresponsibilityparameter',NULL,'arche','myfirstrf_p_testresponsibilityparameter','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','myfirstrf_testresponsibilityrelation','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_myfirstrf_testresponsibilityrelation',NULL,'arche','myfirstrf_testresponsibilityrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_dependency','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_patterns_dependency',NULL,'arche','patterns_dependency','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_interfacerealization','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_patterns_interfacerealization',NULL,'arche','patterns_interfacerealization','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_isarelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_patterns_isarelation',NULL,'arche','patterns_isarelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_pattern','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_patterns_pattern',NULL,'arche','patterns_pattern','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_patternconnector','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_patterns_patternconnector',NULL,'arche','patterns_patternconnector','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_patterncontainer','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_patterns_patterncontainer',NULL,'arche','patterns_patterncontainer','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_patternelement','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_patterns_patternelement',NULL,'arche','patterns_patternelement','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_patternelementinterface','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_patterns_patternelementinterface',NULL,'arche','patterns_patternelementinterface','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_patternitemproperty','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_patterns_patternitemproperty',NULL,'arche','patterns_patternitemproperty','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','patterns_refinement','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_patterns_refinement',NULL,'arche','patterns_refinement','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','performancereasoningframeworks_p_executiontime','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_performancereasoningframeworks_p_executiontime',NULL,'arche','performancereasoningframeworks_p_executiontime','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','performancereasoningframeworks_p_mutualexclusion','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_performancereasoningframeworks_p_mutualexclusion',NULL,'arche','performancereasoningframeworks_p_mutualexclusion','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_performancereasoningframeworks_p_sharedresourceasked',NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','planner_c_addfunction','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_planner_c_addfunction',NULL,'arche','planner_c_addfunction','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','planner_c_addresponsibilityrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_planner_c_addresponsibilityrelation',NULL,'arche','planner_c_addresponsibilityrelation','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','planner_c_addscenario','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_planner_c_addscenario',NULL,'arche','planner_c_addscenario','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','planner_c_addtranslationrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_planner_c_addtranslationrelation',NULL,'arche','planner_c_addtranslationrelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','proc','db',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','proc','name',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','proc','type',3,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','procs_priv','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','procs_priv','Db',2,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','procs_priv','User',3,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','procs_priv','Routine_name',4,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','procs_priv','Routine_type',5,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','rmamodel_p_latency','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmamodel_p_latency',NULL,'arche','rmamodel_p_latency','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','rmamodel_subtask','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmamodel_subtask',NULL,'arche','rmamodel_subtask','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','rmamodel_task','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmamodel_task',NULL,'arche','rmamodel_task','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','rmaperformancerf_p_executiontime','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmaperformancerf_p_executiontime',NULL,'arche','rmaperformancerf_p_executiontime','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','rmaperformancerf_p_shared','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmaperformancerf_p_shared',NULL,'arche','rmaperformancerf_p_shared','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','rmaperformancerf_p_taskpriority','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmaperformancerf_p_taskpriority',NULL,'arche','rmaperformancerf_p_taskpriority','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','rmaperformancerf_resource','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_rmaperformancerf_resource',NULL,'arche','rmaperformancerf_resource','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','rmaperformancerf_resourceusagerelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmaperformancerf_resourceusagerelation',NULL,'arche','rmaperformancerf_resourceusagerelation','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','rmaperformancerf_thread','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmaperformancerf_thread',NULL,'arche','rmaperformancerf_thread','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','rmaperformancerf_threadtouocrelation','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmaperformancerf_threadtouocrelation',NULL,'arche','rmaperformancerf_threadtouocrelation','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','rmaperformancerf_unitofconcurrency','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_rmaperformancerf_unitofconcurrency',NULL,'arche','rmaperformancerf_unitofconcurrency','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','seeker_evaluationresult','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_seeker_evaluationresult',NULL,'arche','seeker_evaluationresult','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','tables_priv','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','tables_priv','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','tables_priv','User',3,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','tables_priv','Table_name',4,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','tactics','type',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','tactics','node_id',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','tactics','version',3,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','test_interface','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_interface',NULL,'arche','test_interface','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','test_leave_dependencies','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_leave_dependencies',NULL,'arche','test_leave_dependencies','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','test_model_arcs','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_model_arcs',NULL,'arche','test_model_arcs','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','test_model_nodes','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_model_nodes',NULL,'arche','test_model_nodes','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','test_model_nodes_affected','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_model_nodes_affected',NULL,'arche','test_model_nodes_affected','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','test_module','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_module',NULL,'arche','test_module','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','test_module_dependencies','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_module_dependencies',NULL,'arche','test_module_dependencies','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','test_module_responsibilities','uid',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','FK_test_module_responsibilities',NULL,'arche','test_module_responsibilities','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','test_responsibilities','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_responsibilities',NULL,'arche','test_responsibilities','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','test_responsibility_refinement','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','FK_test_responsibility_refinement',NULL,'arche','test_responsibility_refinement','version',1,1,'arche','__versions__','ID'),
 (NULL,'arche','PRIMARY',NULL,'arche','time_zone','Time_zone_id',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','time_zone_leap_second','Transition_time',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','time_zone_name','Name',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','time_zone_transition','Time_zone_id',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','time_zone_transition','Transition_time',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','time_zone_transition_type','Time_zone_id',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','time_zone_transition_type','Transition_type_id',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','user','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','user','User',2,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','user_info','User',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','PRIMARY',NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','variabilityrf_variabilitymechanismresponsibility_ibfk_1',NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','version',1,1,'arche','__versions__','ID');
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'arche','PRIMARY',NULL,'arche','variabilityreasoningframework_vpconfiguration','uid',1,NULL,NULL,NULL,NULL),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration_ibfk_1',NULL,'arche','variabilityreasoningframework_vpconfiguration','version',1,1,'arche','__versions__','ID'),
 (NULL,'mysql','PRIMARY',NULL,'mysql','columns_priv','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','columns_priv','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','columns_priv','User',3,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','columns_priv','Table_name',4,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','columns_priv','Column_name',5,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','db','Host',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'mysql','PRIMARY',NULL,'mysql','db','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','db','User',3,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','func','name',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','help_category','help_category_id',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','name',NULL,'mysql','help_category','name',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','help_keyword','help_keyword_id',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','name',NULL,'mysql','help_keyword','name',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','help_relation','help_keyword_id',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','help_relation','help_topic_id',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','help_topic','help_topic_id',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'mysql','name',NULL,'mysql','help_topic','name',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','host','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','host','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','proc','db',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','proc','name',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','proc','type',3,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','procs_priv','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','procs_priv','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','procs_priv','User',3,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','procs_priv','Routine_name',4,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','procs_priv','Routine_type',5,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'mysql','PRIMARY',NULL,'mysql','tables_priv','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','tables_priv','Db',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','tables_priv','User',3,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','tables_priv','Table_name',4,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','time_zone','Time_zone_id',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','time_zone_leap_second','Transition_time',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','time_zone_name','Name',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','time_zone_transition','Time_zone_id',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','time_zone_transition','Transition_time',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','time_zone_transition_type','Time_zone_id',1,NULL,NULL,NULL,NULL);
INSERT INTO `KEY_COLUMN_USAGE` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`COLUMN_NAME`,`ORDINAL_POSITION`,`POSITION_IN_UNIQUE_CONSTRAINT`,`REFERENCED_TABLE_SCHEMA`,`REFERENCED_TABLE_NAME`,`REFERENCED_COLUMN_NAME`) VALUES 
 (NULL,'mysql','PRIMARY',NULL,'mysql','time_zone_transition_type','Transition_type_id',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','user','Host',1,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','user','User',2,NULL,NULL,NULL,NULL),
 (NULL,'mysql','PRIMARY',NULL,'mysql','user_info','User',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `KEY_COLUMN_USAGE` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`ROUTINES`
--

DROP TABLE IF EXISTS `ROUTINES`;
CREATE TEMPORARY TABLE `ROUTINES` (
  `SPECIFIC_NAME` varchar(64) NOT NULL default '',
  `ROUTINE_CATALOG` varchar(512) default NULL,
  `ROUTINE_SCHEMA` varchar(64) NOT NULL default '',
  `ROUTINE_NAME` varchar(64) NOT NULL default '',
  `ROUTINE_TYPE` varchar(9) NOT NULL default '',
  `DTD_IDENTIFIER` varchar(64) default NULL,
  `ROUTINE_BODY` varchar(8) NOT NULL default '',
  `ROUTINE_DEFINITION` longtext NOT NULL,
  `EXTERNAL_NAME` varchar(64) default NULL,
  `EXTERNAL_LANGUAGE` varchar(64) default NULL,
  `PARAMETER_STYLE` varchar(8) NOT NULL default '',
  `IS_DETERMINISTIC` varchar(3) NOT NULL default '',
  `SQL_DATA_ACCESS` varchar(64) NOT NULL default '',
  `SQL_PATH` varchar(64) default NULL,
  `SECURITY_TYPE` varchar(7) NOT NULL default '',
  `CREATED` datetime NOT NULL default '0000-00-00 00:00:00',
  `LAST_ALTERED` datetime NOT NULL default '0000-00-00 00:00:00',
  `SQL_MODE` longtext NOT NULL,
  `ROUTINE_COMMENT` varchar(64) NOT NULL default '',
  `DEFINER` varchar(77) NOT NULL default ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`ROUTINES`
--

/*!40000 ALTER TABLE `ROUTINES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ROUTINES` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`SCHEMATA`
--

DROP TABLE IF EXISTS `SCHEMATA`;
CREATE TEMPORARY TABLE `SCHEMATA` (
  `CATALOG_NAME` varchar(512) default NULL,
  `SCHEMA_NAME` varchar(64) NOT NULL default '',
  `DEFAULT_CHARACTER_SET_NAME` varchar(64) NOT NULL default '',
  `DEFAULT_COLLATION_NAME` varchar(64) NOT NULL default '',
  `SQL_PATH` varchar(512) default NULL
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`SCHEMATA`
--

/*!40000 ALTER TABLE `SCHEMATA` DISABLE KEYS */;
INSERT INTO `SCHEMATA` (`CATALOG_NAME`,`SCHEMA_NAME`,`DEFAULT_CHARACTER_SET_NAME`,`DEFAULT_COLLATION_NAME`,`SQL_PATH`) VALUES 
 (NULL,'information_schema','utf8','utf8_general_ci',NULL),
 (NULL,'arche','latin1','latin1_swedish_ci',NULL),
 (NULL,'mysql','latin1','latin1_swedish_ci',NULL),
 (NULL,'test','latin1','latin1_swedish_ci',NULL);
/*!40000 ALTER TABLE `SCHEMATA` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`SCHEMA_PRIVILEGES`
--

DROP TABLE IF EXISTS `SCHEMA_PRIVILEGES`;
CREATE TEMPORARY TABLE `SCHEMA_PRIVILEGES` (
  `GRANTEE` varchar(81) NOT NULL default '',
  `TABLE_CATALOG` varchar(512) default NULL,
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `PRIVILEGE_TYPE` varchar(64) NOT NULL default '',
  `IS_GRANTABLE` varchar(3) NOT NULL default ''
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`SCHEMA_PRIVILEGES`
--

/*!40000 ALTER TABLE `SCHEMA_PRIVILEGES` DISABLE KEYS */;
INSERT INTO `SCHEMA_PRIVILEGES` (`GRANTEE`,`TABLE_CATALOG`,`TABLE_SCHEMA`,`PRIVILEGE_TYPE`,`IS_GRANTABLE`) VALUES 
 ('\'ArchE\'@\'%\'',NULL,'arche','SELECT','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','INSERT','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','UPDATE','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','DELETE','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','CREATE','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','DROP','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','REFERENCES','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','INDEX','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','ALTER','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','CREATE TEMPORARY TABLES','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','LOCK TABLES','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','EXECUTE','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','CREATE VIEW','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','SHOW VIEW','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','CREATE ROUTINE','YES'),
 ('\'ArchE\'@\'%\'',NULL,'arche','ALTER ROUTINE','YES');
/*!40000 ALTER TABLE `SCHEMA_PRIVILEGES` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`STATISTICS`
--

DROP TABLE IF EXISTS `STATISTICS`;
CREATE TEMPORARY TABLE `STATISTICS` (
  `TABLE_CATALOG` varchar(512) default NULL,
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `TABLE_NAME` varchar(64) NOT NULL default '',
  `NON_UNIQUE` bigint(1) NOT NULL default '0',
  `INDEX_SCHEMA` varchar(64) NOT NULL default '',
  `INDEX_NAME` varchar(64) NOT NULL default '',
  `SEQ_IN_INDEX` bigint(2) NOT NULL default '0',
  `COLUMN_NAME` varchar(64) NOT NULL default '',
  `COLLATION` varchar(1) default NULL,
  `CARDINALITY` bigint(21) default NULL,
  `SUB_PART` bigint(3) default NULL,
  `PACKED` varchar(10) default NULL,
  `NULLABLE` varchar(3) NOT NULL default '',
  `INDEX_TYPE` varchar(16) NOT NULL default '',
  `COMMENT` varchar(16) default NULL
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`STATISTICS`
--

/*!40000 ALTER TABLE `STATISTICS` DISABLE KEYS */;
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','__factsets__',0,'arche','PRIMARY',1,'ID','A',77,NULL,NULL,'','BTREE',''),
 (NULL,'arche','__factsets__',1,'arche','setName',1,'setName','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','__factsets__',1,'arche','sort',1,'group','A',12,NULL,NULL,'','BTREE',''),
 (NULL,'arche','__mod_solver_interface__',0,'arche','PRIMARY',1,'ID','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'arche','__tool_command_interface__',0,'arche','PRIMARY',1,'ID','A',3,NULL,NULL,'','BTREE',''),
 (NULL,'arche','__versions__',0,'arche','PRIMARY',1,'ID','A',5,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_module',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_module',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange',0,'arche','PRIMARY',1,'uid','A',28,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange',1,'arche','version',1,'version','A',5,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange',1,'arche','fact-id',1,'fact-id','A',28,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation',0,'arche','PRIMARY',1,'uid','A',30,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation',1,'arche','version',1,'version','A',7,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation',1,'arche','fact-id',1,'fact-id','A',30,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation',0,'arche','PRIMARY',1,'uid','A',22,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation',1,'arche','version',1,'version','A',7,NULL,NULL,'','BTREE',''),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation',1,'arche','fact-id',1,'fact-id','A',22,NULL,NULL,'','BTREE',''),
 (NULL,'arche','columns_priv',0,'arche','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','columns_priv',0,'arche','PRIMARY',2,'Db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','columns_priv',0,'arche','PRIMARY',3,'User','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','columns_priv',0,'arche','PRIMARY',4,'Table_name','A',NULL,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','columns_priv',0,'arche','PRIMARY',5,'Column_name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','db',0,'arche','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','db',0,'arche','PRIMARY',2,'Db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','db',0,'arche','PRIMARY',3,'User','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'arche','db',1,'arche','User',1,'User','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_module',0,'arche','PRIMARY',1,'uid','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_module',1,'arche','version',1,'version','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_module',1,'arche','fact-id',1,'fact-id','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_moduledependencyrelation',0,'arche','PRIMARY',1,'uid','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_moduledependencyrelation',1,'arche','version',1,'version','A',6,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','design_moduledependencyrelation',1,'arche','fact-id',1,'fact-id','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_moduleinterface',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_moduleinterface',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_moduleinterface',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_modulerefinementrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_modulerefinementrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_modulerefinementrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_process',0,'arche','PRIMARY',1,'uid','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_process',1,'arche','fact-id',1,'fact-id','A',2,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','design_process',1,'arche','version',1,'version','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_realizerelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_realizerelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_realizerelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_responsibility',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_responsibility',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_responsibility',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_responsibilitytomodulerelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_responsibilitytomodulerelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','design_responsibilitytomodulerelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_sharedresource',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_sharedresource',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_sharedresource',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_sharedresourcerelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_sharedresourcerelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_sharedresourcerelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_thread',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_thread',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','design_thread',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_unitofconcurrency',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_unitofconcurrency',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_unitofconcurrency',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_variationpoint',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_variationpoint',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_variationpoint',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_vpinputvalue',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_vpinputvalue',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','design_vpinputvalue',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_wrapper',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_wrapper',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','design_wrapper',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation',0,'arche','PRIMARY',1,'uid','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation',1,'arche','fact-id',1,'fact-id','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation',1,'arche','version',1,'version','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','func',0,'arche','PRIMARY',1,'name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','help_category',0,'arche','PRIMARY',1,'help_category_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','help_category',0,'arche','name',1,'name','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','help_keyword',0,'arche','PRIMARY',1,'help_keyword_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','help_keyword',0,'arche','name',1,'name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','help_relation',0,'arche','PRIMARY',1,'help_keyword_id','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','help_relation',0,'arche','PRIMARY',2,'help_topic_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','help_topic',0,'arche','PRIMARY',1,'help_topic_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','help_topic',0,'arche','name',1,'name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','host',0,'arche','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','host',0,'arche','PRIMARY',2,'Db','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_assemblyinstance',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_assemblyinstance',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_assemblyinstance',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_p_executiontime',0,'arche','PRIMARY',1,'uid','A',26,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_p_executiontime',1,'arche','version',1,'version','A',8,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_p_executiontime',1,'arche','fact-id',1,'fact-id','A',26,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_p_taskpriority',0,'arche','PRIMARY',1,'uid','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_p_taskpriority',1,'arche','version',1,'version','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_p_taskpriority',1,'arche','fact-id',1,'fact-id','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation',0,'arche','PRIMARY',1,'uid','A',13,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation',1,'arche','version',1,'version','A',13,NULL,NULL,'','BTREE',''),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation',1,'arche','fact-id',1,'fact-id','A',13,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_dataflow',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_dataflow',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_dataflow',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','main_dataitems',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_dataitems',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_dataitems',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_function',0,'arche','PRIMARY',1,'uid','A',24,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_function',1,'arche','fact-id',1,'fact-id','A',24,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_function',1,'arche','version',1,'version','A',4,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_functionrefinementrelation',0,'arche','PRIMARY',1,'uid','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_functionrefinementrelation',1,'arche','fact-id',1,'fact-id','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_functionrefinementrelation',1,'arche','version',1,'version','A',2,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','main_leafresponsibilitydependencyrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_leafresponsibilitydependencyrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_p_analysisresult',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_p_analysisresult',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_p_analysisresult',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_responsibilities',0,'arche','PRIMARY',1,'uid','A',24,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_responsibilities',1,'arche','version',1,'version','A',4,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_responsibilities',1,'arche','fact-id',1,'fact-id','A',24,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','main_responsibilityrefinementrelation',0,'arche','PRIMARY',1,'uid','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_responsibilityrefinementrelation',1,'arche','fact-id',1,'fact-id','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_responsibilityrefinementrelation',1,'arche','version',1,'version','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation',0,'arche','PRIMARY',1,'uid','A',45,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation',1,'arche','fact-id',1,'fact-id','A',45,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_responsibilitytoresponsibilityrelation',1,'arche','version',1,'version','A',6,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_scenariorefinementrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_scenariorefinementrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','main_scenariorefinementrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_scenarios',0,'arche','PRIMARY',1,'uid','A',16,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_scenarios',1,'arche','version',1,'version','A',4,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_scenarios',1,'arche','fact-id',1,'fact-id','A',16,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_sequencerelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_sequencerelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_sequencerelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_translationrelation',0,'arche','PRIMARY',1,'uid','A',58,NULL,NULL,'','BTREE',''),
 (NULL,'arche','main_translationrelation',1,'arche','version',1,'version','A',4,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','main_translationrelation',1,'arche','fact-id',1,'fact-id','A',58,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics',0,'arche','PRIMARY',1,'type','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics',0,'arche','PRIMARY',2,'node_id','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics',0,'arche','PRIMARY',3,'version','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','myfirstrf_p_testrelationparameter',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter',0,'arche','PRIMARY',1,'uid','A',1,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter',1,'arche','version',1,'version','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter',1,'arche','fact-id',1,'fact-id','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','myfirstrf_testresponsibilityrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_dependency',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_dependency',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_dependency',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','patterns_interfacerealization',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_interfacerealization',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_interfacerealization',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_isarelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_isarelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_isarelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_pattern',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_pattern',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_pattern',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','patterns_patternconnector',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternconnector',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternconnector',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patterncontainer',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patterncontainer',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patterncontainer',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternelement',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternelement',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternelement',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','patterns_patternelementinterface',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternelementinterface',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternelementinterface',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternitemproperty',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternitemproperty',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_patternitemproperty',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_refinement',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_refinement',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','patterns_refinement',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','performancereasoningframeworks_p_executiontime',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','performancereasoningframeworks_p_executiontime',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','performancereasoningframeworks_p_executiontime',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addfunction',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addfunction',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addfunction',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addresponsibilityrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addresponsibilityrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addresponsibilityrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addscenario',0,'arche','PRIMARY',1,'uid','A',2,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','planner_c_addscenario',1,'arche','fact-id',1,'fact-id','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addscenario',1,'arche','version',1,'version','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addtranslationrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addtranslationrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','planner_c_addtranslationrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','proc',0,'arche','PRIMARY',1,'db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','proc',0,'arche','PRIMARY',2,'name','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','proc',0,'arche','PRIMARY',3,'type','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','procs_priv',0,'arche','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','procs_priv',0,'arche','PRIMARY',2,'Db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','procs_priv',0,'arche','PRIMARY',3,'User','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','procs_priv',0,'arche','PRIMARY',4,'Routine_name','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','procs_priv',0,'arche','PRIMARY',5,'Routine_type','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','procs_priv',1,'arche','Grantor',1,'Grantor','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmamodel_p_latency',0,'arche','PRIMARY',1,'uid','A',3,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmamodel_p_latency',1,'arche','fact-id',1,'fact-id','A',3,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmamodel_p_latency',1,'arche','version',1,'version','A',3,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmamodel_subtask',0,'arche','PRIMARY',1,'uid','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmamodel_subtask',1,'arche','fact-id',1,'fact-id','A',2,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','rmamodel_subtask',1,'arche','version',1,'version','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmamodel_task',0,'arche','PRIMARY',1,'uid','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmamodel_task',1,'arche','fact-id',1,'fact-id','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmamodel_task',1,'arche','version',1,'version','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_p_executiontime',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_p_executiontime',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_p_executiontime',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_p_shared',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_p_shared',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_p_shared',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_p_taskpriority',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_resource',0,'arche','PRIMARY',1,'uid','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_resource',1,'arche','version',1,'version','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_resource',1,'arche','fact-id',1,'fact-id','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_resourceusagerelation',0,'arche','PRIMARY',1,'uid','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_resourceusagerelation',1,'arche','version',1,'version','A',2,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_resourceusagerelation',1,'arche','fact-id',1,'fact-id','A',2,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_thread',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_thread',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_thread',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_threadtouocrelation',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_unitofconcurrency',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','rmaperformancerf_unitofconcurrency',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','seeker_evaluationresult',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','seeker_evaluationresult',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','seeker_evaluationresult',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','tables_priv',0,'arche','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','tables_priv',0,'arche','PRIMARY',2,'Db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','tables_priv',0,'arche','PRIMARY',3,'User','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','tables_priv',0,'arche','PRIMARY',4,'Table_name','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','tables_priv',1,'arche','Grantor',1,'Grantor','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','tactics',0,'arche','PRIMARY',1,'type','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','tactics',0,'arche','PRIMARY',2,'node_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','tactics',0,'arche','PRIMARY',3,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_interface',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_interface',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_interface',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_leave_dependencies',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_leave_dependencies',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_leave_dependencies',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','test_model_arcs',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_model_arcs',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_model_arcs',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_model_nodes',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_model_nodes',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_model_nodes',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_model_nodes_affected',0,'arche','PRIMARY',1,'uid','A',32,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_model_nodes_affected',1,'arche','fact-id',1,'fact-id','A',16,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_model_nodes_affected',1,'arche','version',1,'version','A',10,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','test_module',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_module',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_module',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_module_dependencies',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_module_dependencies',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_module_dependencies',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_module_responsibilities',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_module_responsibilities',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_module_responsibilities',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','test_responsibilities',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_responsibilities',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_responsibilities',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_responsibility_refinement',0,'arche','PRIMARY',1,'uid','A',109,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_responsibility_refinement',1,'arche','fact-id',1,'fact-id','A',54,NULL,NULL,'','BTREE',''),
 (NULL,'arche','test_responsibility_refinement',1,'arche','version',1,'version','A',15,NULL,NULL,'','BTREE',''),
 (NULL,'arche','time_zone',0,'arche','PRIMARY',1,'Time_zone_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','time_zone_leap_second',0,'arche','PRIMARY',1,'Transition_time','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','time_zone_name',0,'arche','PRIMARY',1,'Name','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','time_zone_transition',0,'arche','PRIMARY',1,'Time_zone_id','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','time_zone_transition',0,'arche','PRIMARY',2,'Transition_time','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','time_zone_transition_type',0,'arche','PRIMARY',1,'Time_zone_id','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','time_zone_transition_type',0,'arche','PRIMARY',2,'Transition_type_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','user',0,'arche','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'arche','user',0,'arche','PRIMARY',2,'User','A',3,NULL,NULL,'','BTREE',''),
 (NULL,'arche','user_info',0,'arche','PRIMARY',1,'User','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','user_info',1,'arche','user_info_Full_name',1,'Full_name','A',NULL,NULL,NULL,'YES','BTREE',''),
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration',0,'arche','PRIMARY',1,'uid','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration',1,'arche','fact-id',1,'fact-id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration',1,'arche','version',1,'version','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','columns_priv',0,'mysql','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','columns_priv',0,'mysql','PRIMARY',2,'Db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','columns_priv',0,'mysql','PRIMARY',3,'User','A',NULL,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'mysql','columns_priv',0,'mysql','PRIMARY',4,'Table_name','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','columns_priv',0,'mysql','PRIMARY',5,'Column_name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','db',0,'mysql','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','db',0,'mysql','PRIMARY',2,'Db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','db',0,'mysql','PRIMARY',3,'User','A',1,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','db',1,'mysql','User',1,'User','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','func',0,'mysql','PRIMARY',1,'name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','help_category',0,'mysql','PRIMARY',1,'help_category_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','help_category',0,'mysql','name',1,'name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','help_keyword',0,'mysql','PRIMARY',1,'help_keyword_id','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'mysql','help_keyword',0,'mysql','name',1,'name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','help_relation',0,'mysql','PRIMARY',1,'help_keyword_id','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','help_relation',0,'mysql','PRIMARY',2,'help_topic_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','help_topic',0,'mysql','PRIMARY',1,'help_topic_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','help_topic',0,'mysql','name',1,'name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','host',0,'mysql','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','host',0,'mysql','PRIMARY',2,'Db','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','proc',0,'mysql','PRIMARY',1,'db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','proc',0,'mysql','PRIMARY',2,'name','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','proc',0,'mysql','PRIMARY',3,'type','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'mysql','procs_priv',0,'mysql','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','procs_priv',0,'mysql','PRIMARY',2,'Db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','procs_priv',0,'mysql','PRIMARY',3,'User','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','procs_priv',0,'mysql','PRIMARY',4,'Routine_name','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','procs_priv',0,'mysql','PRIMARY',5,'Routine_type','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','procs_priv',1,'mysql','Grantor',1,'Grantor','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','tables_priv',0,'mysql','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','tables_priv',0,'mysql','PRIMARY',2,'Db','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','tables_priv',0,'mysql','PRIMARY',3,'User','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','tables_priv',0,'mysql','PRIMARY',4,'Table_name','A',0,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'mysql','tables_priv',1,'mysql','Grantor',1,'Grantor','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','time_zone',0,'mysql','PRIMARY',1,'Time_zone_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','time_zone_leap_second',0,'mysql','PRIMARY',1,'Transition_time','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','time_zone_name',0,'mysql','PRIMARY',1,'Name','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','time_zone_transition',0,'mysql','PRIMARY',1,'Time_zone_id','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','time_zone_transition',0,'mysql','PRIMARY',2,'Transition_time','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','time_zone_transition_type',0,'mysql','PRIMARY',1,'Time_zone_id','A',NULL,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','time_zone_transition_type',0,'mysql','PRIMARY',2,'Transition_type_id','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','user',0,'mysql','PRIMARY',1,'Host','A',NULL,NULL,NULL,'','BTREE','');
INSERT INTO `STATISTICS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`NON_UNIQUE`,`INDEX_SCHEMA`,`INDEX_NAME`,`SEQ_IN_INDEX`,`COLUMN_NAME`,`COLLATION`,`CARDINALITY`,`SUB_PART`,`PACKED`,`NULLABLE`,`INDEX_TYPE`,`COMMENT`) VALUES 
 (NULL,'mysql','user',0,'mysql','PRIMARY',2,'User','A',3,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','user_info',0,'mysql','PRIMARY',1,'User','A',0,NULL,NULL,'','BTREE',''),
 (NULL,'mysql','user_info',1,'mysql','user_info_Full_name',1,'Full_name','A',NULL,NULL,NULL,'YES','BTREE','');
/*!40000 ALTER TABLE `STATISTICS` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`TABLES`
--

DROP TABLE IF EXISTS `TABLES`;
CREATE TEMPORARY TABLE `TABLES` (
  `TABLE_CATALOG` varchar(512) default NULL,
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `TABLE_NAME` varchar(64) NOT NULL default '',
  `TABLE_TYPE` varchar(64) NOT NULL default '',
  `ENGINE` varchar(64) default NULL,
  `VERSION` bigint(21) default NULL,
  `ROW_FORMAT` varchar(10) default NULL,
  `TABLE_ROWS` bigint(21) default NULL,
  `AVG_ROW_LENGTH` bigint(21) default NULL,
  `DATA_LENGTH` bigint(21) default NULL,
  `MAX_DATA_LENGTH` bigint(21) default NULL,
  `INDEX_LENGTH` bigint(21) default NULL,
  `DATA_FREE` bigint(21) default NULL,
  `AUTO_INCREMENT` bigint(21) default NULL,
  `CREATE_TIME` datetime default NULL,
  `UPDATE_TIME` datetime default NULL,
  `CHECK_TIME` datetime default NULL,
  `TABLE_COLLATION` varchar(64) default NULL,
  `CHECKSUM` bigint(21) default NULL,
  `CREATE_OPTIONS` varchar(255) default NULL,
  `TABLE_COMMENT` varchar(80) NOT NULL default ''
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`TABLES`
--

/*!40000 ALTER TABLE `TABLES` DISABLE KEYS */;
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'information_schema','CHARACTER_SETS','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,576,0,16661376,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=29127',''),
 (NULL,'information_schema','COLLATIONS','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,423,0,16737264,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=39662',''),
 (NULL,'information_schema','COLLATION_CHARACTER_SET_APPLICABILITY','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,387,0,16733880,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=43351',''),
 (NULL,'information_schema','COLUMNS','SYSTEM VIEW','MyISAM',0,'Dynamic',NULL,0,0,281474976710655,1024,0,NULL,'2008-08-08 10:41:40','2008-08-08 10:41:40',NULL,'utf8_general_ci',NULL,'max_rows=4682',''),
 (NULL,'information_schema','COLUMN_PRIVILEGES','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,2565,0,16757145,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=6540','');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'information_schema','KEY_COLUMN_USAGE','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,4637,0,16762755,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=3618',''),
 (NULL,'information_schema','ROUTINES','SYSTEM VIEW','MyISAM',0,'Dynamic',NULL,0,0,281474976710655,1024,0,NULL,'2008-08-08 10:41:40','2008-08-08 10:41:40',NULL,'utf8_general_ci',NULL,'max_rows=5159',''),
 (NULL,'information_schema','SCHEMATA','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,3656,0,16755448,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=4588',''),
 (NULL,'information_schema','SCHEMA_PRIVILEGES','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,2179,0,16767405,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=7699',''),
 (NULL,'information_schema','STATISTICS','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,2679,0,16770540,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=6262','');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'information_schema','TABLES','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,3641,0,16763164,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=4607',''),
 (NULL,'information_schema','TABLE_CONSTRAINTS','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,2504,0,16749256,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=6700',''),
 (NULL,'information_schema','TABLE_PRIVILEGES','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,2372,0,16748692,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=7073',''),
 (NULL,'information_schema','TRIGGERS','SYSTEM VIEW','MyISAM',0,'Dynamic',NULL,0,0,281474976710655,1024,0,NULL,'2008-08-08 10:41:40','2008-08-08 10:41:40',NULL,'utf8_general_ci',NULL,'max_rows=4304',''),
 (NULL,'information_schema','VIEWS','SYSTEM VIEW','MyISAM',0,'Dynamic',NULL,0,0,281474976710655,1024,0,NULL,'2008-08-08 10:41:40','2008-08-08 10:41:40',NULL,'utf8_general_ci',NULL,'max_rows=8479','');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'information_schema','USER_PRIVILEGES','SYSTEM VIEW','MEMORY',0,'Fixed',NULL,1986,0,16759854,0,0,NULL,NULL,NULL,NULL,'utf8_general_ci',NULL,'max_rows=8447',''),
 (NULL,'arche','__factsets__','BASE TABLE','InnoDB',10,'Compact',77,212,16384,0,32768,0,140,'2007-02-20 11:57:09',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB'),
 (NULL,'arche','__mod_solver_interface__','BASE TABLE','InnoDB',10,'Compact',1,16384,16384,0,0,0,2,'2007-02-20 11:57:09',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB'),
 (NULL,'arche','__tool_command_interface__','BASE TABLE','InnoDB',10,'Compact',3,5461,16384,0,0,0,47,'2007-02-20 11:57:09',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB'),
 (NULL,'arche','__versions__','BASE TABLE','InnoDB',10,'Compact',5,3276,16384,0,0,0,15650,'2007-02-20 11:57:09',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_arc_relation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-10-22 08:47:08',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','changeimpactmodifiabilityrf_module','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-10-22 08:47:07',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','changeimpactmodifiabilityrf_moduledependencyrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-10-22 08:47:08',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','changeimpactmodifiabilityrf_node_responsibility','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-10-22 08:47:07',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_p_booleantest','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-10-22 08:47:09',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','changeimpactmodifiabilityrf_p_costofchange','BASE TABLE','InnoDB',10,'Compact',28,585,16384,0,32768,0,33583,'2007-10-22 08:47:09',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityincoming','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,545,'2007-10-22 08:47:09',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,545,'2007-10-22 08:47:09',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','BASE TABLE','InnoDB',10,'Compact',30,546,16384,0,32768,0,34396,'2007-10-22 08:47:08',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','BASE TABLE','InnoDB',10,'Compact',22,744,16384,0,32768,0,30045,'2007-10-22 08:47:08',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','columns_priv','BASE TABLE','MyISAM',10,'Fixed',0,0,0,227994731135631359,1024,0,NULL,'2008-08-07 13:56:12','2008-08-07 13:56:12',NULL,'utf8_bin',NULL,'','Column privileges'),
 (NULL,'arche','db','BASE TABLE','MyISAM',10,'Fixed',1,438,438,123286039799267327,4096,0,NULL,'2008-08-07 13:56:12','2008-08-07 13:56:12','2008-08-07 13:56:12','utf8_bin',NULL,'','Database privileges');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','design_module','BASE TABLE','InnoDB',10,'Compact',6,2730,16384,0,32768,0,3364,'2007-02-20 11:57:09',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_moduledependencyrelation','BASE TABLE','InnoDB',10,'Compact',6,2730,16384,0,32768,0,5790,'2007-02-20 11:57:09',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_moduleinterface','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:09',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_modulerefinementrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:09',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','design_process','BASE TABLE','InnoDB',10,'Compact',2,8192,16384,0,32768,0,35,'2007-04-02 15:18:21',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_realizerelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:09',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_responsibility','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:09',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_responsibilitytomodulerelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','design_sharedresource','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_sharedresourcerelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_thread','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_unitofconcurrency','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','design_variationpoint','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_vpinputvalue','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','design_wrapper','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','externalinteraction_responsibilitydependencyrelation','BASE TABLE','InnoDB',10,'Compact',1,16384,16384,0,32768,0,46,'2007-08-10 13:43:18',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','externalinteraction_responsibilityinteractionrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-07-30 15:46:45',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','externalinteraction_responsibilityreactionrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-07-30 15:46:45',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','func','BASE TABLE','MyISAM',10,'Fixed',0,0,0,162974011515469823,1024,0,NULL,'2008-08-07 13:56:14','2008-08-07 13:56:14',NULL,'utf8_bin',NULL,'','User defined functions'),
 (NULL,'arche','help_category','BASE TABLE','MyISAM',10,'Fixed',0,0,0,163536961468891135,1024,0,NULL,'2008-08-07 13:56:14','2008-08-07 13:56:14',NULL,'utf8_general_ci',NULL,'','help categories');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','help_keyword','BASE TABLE','MyISAM',10,'Fixed',0,0,0,55450570411999231,1024,0,NULL,'2008-08-07 13:56:14','2008-08-07 13:56:14',NULL,'utf8_general_ci',NULL,'','help keywords'),
 (NULL,'arche','help_relation','BASE TABLE','MyISAM',10,'Fixed',0,0,0,2533274790395903,1024,0,NULL,'2008-08-07 13:56:14','2008-08-07 13:56:14',NULL,'utf8_general_ci',NULL,'','keyword-topic relation'),
 (NULL,'arche','help_topic','BASE TABLE','MyISAM',10,'Dynamic',0,0,0,281474976710655,1024,0,NULL,'2008-08-07 13:56:14','2008-08-07 13:56:14',NULL,'utf8_general_ci',NULL,'','help topics'),
 (NULL,'arche','host','BASE TABLE','MyISAM',10,'Fixed',0,0,0,109775240917155839,1024,0,NULL,'2008-08-07 13:56:14','2008-08-07 13:56:14',NULL,'utf8_bin',NULL,'','Host privileges;  Merged with database privileges'),
 (NULL,'arche','icmperformancerf_assemblyinstance','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-10-24 15:57:38',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','icmperformancerf_p_executiontime','BASE TABLE','InnoDB',10,'Compact',26,630,16384,0,32768,0,8921,'2007-10-24 15:57:38',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','icmperformancerf_p_taskpriority','BASE TABLE','InnoDB',10,'Compact',2,8192,16384,0,32768,0,109,'2007-10-24 15:57:38',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','icmperformancerf_responsibilityinteractionrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-10-24 15:57:38',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','icmperformancerf_responsibilityreactionrelation','BASE TABLE','InnoDB',10,'Compact',13,1260,16384,0,32768,0,2217,'2007-10-24 15:57:38',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','leave_responsibilities','VIEW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'VIEW'),
 (NULL,'arche','main_dataflow','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_dataitems','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_function','BASE TABLE','InnoDB',10,'Compact',24,682,16384,0,32768,0,1321,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_functionrefinementrelation','BASE TABLE','InnoDB',10,'Compact',4,4096,16384,0,32768,0,221,'2007-02-20 11:57:10',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','main_leafresponsibilitydependencyrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:11',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_p_analysisresult','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:11',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_responsibilities','BASE TABLE','InnoDB',10,'Compact',24,682,16384,0,32768,0,70564,'2007-02-20 11:57:11',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_responsibilityrefinementrelation','BASE TABLE','InnoDB',10,'Compact',6,2730,16384,0,32768,0,17878,'2007-02-20 11:57:11',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','main_responsibilitytoresponsibilityrelation','BASE TABLE','InnoDB',10,'Compact',45,364,16384,0,32768,0,48405,'2007-02-20 11:57:12',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_scenariorefinementrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:12',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_scenarios','BASE TABLE','InnoDB',10,'Compact',16,1024,16384,0,32768,0,28407,'2007-02-20 11:57:12',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','main_sequencerelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:12',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','main_translationrelation','BASE TABLE','InnoDB',10,'Compact',58,282,16384,0,32768,0,108030,'2007-02-20 11:57:12',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilitylearn_modifiabilitylearnvalues','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_arc_relation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_node_affected','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_node_responsibility','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_abstractiontranslator','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_costofchange','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_interfacerouter','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:13',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_response_measures','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','modifiabilityreasoningframeworks_tactics','BASE TABLE','InnoDB',10,'Compact',4,4096,16384,0,0,0,NULL,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB'),
 (NULL,'arche','myfirstrf_p_testrelationparameter','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2008-08-07 12:13:28',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','myfirstrf_p_testresponsibilityparameter','BASE TABLE','InnoDB',10,'Compact',1,16384,16384,0,32768,0,19,'2008-08-07 12:13:28',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','myfirstrf_testresponsibilityrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,5,'2008-08-07 12:13:28',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','patterns_dependency','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','patterns_interfacerealization','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','patterns_isarelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','patterns_pattern','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','patterns_patternconnector','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','patterns_patterncontainer','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:14',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','patterns_patternelement','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:15',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','patterns_patternelementinterface','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:15',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','patterns_patternitemproperty','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:15',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','patterns_refinement','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:15',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','performancereasoningframeworks_p_executiontime','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:15',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','performancereasoningframeworks_p_mutualexclusion','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:15',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','performancereasoningframeworks_p_sharedresourceasked','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:15',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','planner_c_addfunction','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:15',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','planner_c_addresponsibilityrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:16',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','planner_c_addscenario','BASE TABLE','InnoDB',10,'Compact',2,8192,16384,0,32768,0,111,'2007-02-20 11:57:16',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','planner_c_addtranslationrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:16',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','proc','BASE TABLE','MyISAM',10,'Dynamic',0,0,0,281474976710655,1024,0,NULL,'2008-08-07 13:56:21','2008-08-07 13:56:21',NULL,'utf8_general_ci',NULL,'','Stored Procedures'),
 (NULL,'arche','procs_priv','BASE TABLE','MyISAM',10,'Fixed',0,0,0,239253730204057599,1024,0,NULL,'2008-08-07 13:56:21','2008-08-07 13:56:21','2008-08-07 13:56:21','utf8_bin',NULL,'','Procedure privileges');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','rmamodel_p_latency','BASE TABLE','InnoDB',10,'Compact',3,5461,16384,0,32768,0,241,'2007-02-20 11:57:16',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','rmamodel_subtask','BASE TABLE','InnoDB',10,'Compact',12,1365,16384,0,32768,0,961,'2007-02-20 11:57:16',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','rmamodel_task','BASE TABLE','InnoDB',10,'Compact',4,4096,16384,0,32768,0,377,'2007-02-20 11:57:16',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','rmaperformancerf_p_executiontime','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-11-28 13:32:40',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_p_shared','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-12-04 16:29:23',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','rmaperformancerf_p_taskpriority','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-11-28 13:32:40',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','rmaperformancerf_resource','BASE TABLE','InnoDB',10,'Compact',11,1489,16384,0,32768,0,287,'2007-11-28 13:32:40',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','rmaperformancerf_resourceusagerelation','BASE TABLE','InnoDB',10,'Compact',12,1365,16384,0,32768,0,370,'2007-11-28 13:32:40',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','rmaperformancerf_thread','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-12-07 12:27:35',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','rmaperformancerf_threadtouocrelation','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-12-07 12:27:35',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','rmaperformancerf_unitofconcurrency','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-12-07 12:27:35',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','seeker_evaluationresult','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,243,'2007-02-20 11:57:16',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','tables_priv','BASE TABLE','MyISAM',10,'Fixed',0,0,0,239535205180768255,1024,0,NULL,'2008-08-07 13:56:22','2008-08-07 13:56:22','2008-08-07 13:56:22','utf8_bin',NULL,'','Table privileges'),
 (NULL,'arche','tactics','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,0,0,NULL,'2007-04-02 15:18:29',NULL,NULL,'latin1_swedish_ci',NULL,'','InnoDB free: 14336 kB'),
 (NULL,'arche','test_interface','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:16',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','test_leave_dependencies','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:16',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','test_model_arcs','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:16',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','test_model_nodes','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:17',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','test_model_nodes_affected','BASE TABLE','InnoDB',10,'Compact',32,512,16384,0,32768,0,36,'2007-02-20 11:57:17',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','test_module','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:17',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','test_module_dependencies','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:17',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','test_module_responsibilities','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:17',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','test_responsibilities','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:18',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','test_responsibility_refinement','BASE TABLE','InnoDB',10,'Compact',109,150,16384,0,32768,0,171,'2007-02-20 11:57:18',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','time_zone','BASE TABLE','MyISAM',10,'Fixed',0,0,0,1970324836974591,1024,0,1,'2008-08-07 13:56:23','2008-08-07 13:56:23',NULL,'utf8_general_ci',NULL,'','Time zones');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','time_zone_leap_second','BASE TABLE','MyISAM',10,'Fixed',0,0,0,3659174697238527,1024,0,NULL,'2008-08-07 13:56:23','2008-08-07 13:56:23',NULL,'utf8_general_ci',NULL,'','Leap seconds information for time zones'),
 (NULL,'arche','time_zone_name','BASE TABLE','MyISAM',10,'Fixed',0,0,0,55450570411999231,1024,0,NULL,'2008-08-07 13:56:23','2008-08-07 13:56:23',NULL,'utf8_general_ci',NULL,'','Time zone names'),
 (NULL,'arche','time_zone_transition','BASE TABLE','MyISAM',10,'Fixed',0,0,0,4785074604081151,1024,0,NULL,'2008-08-07 13:56:23','2008-08-07 13:56:23',NULL,'utf8_general_ci',NULL,'','Time zone transitions'),
 (NULL,'arche','time_zone_transition_type','BASE TABLE','MyISAM',10,'Fixed',0,0,0,10696049115004927,1024,0,NULL,'2008-08-07 13:56:24','2008-08-07 13:56:24',NULL,'utf8_general_ci',NULL,'','Time zone transition types'),
 (NULL,'arche','user','BASE TABLE','MyISAM',10,'Dynamic',3,84,252,281474976710655,2048,0,NULL,'2008-08-07 13:56:24','2008-08-07 13:56:24',NULL,'utf8_bin',NULL,'','Users and global privileges');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'arche','user_info','BASE TABLE','MyISAM',10,'Dynamic',0,0,0,281474976710655,1024,0,NULL,'2008-08-07 13:56:24','2008-08-07 13:56:24','2008-08-07 13:56:24','utf8_bin',NULL,'','Stores additional user information'),
 (NULL,'arche','v_factset','VIEW',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'VIEW'),
 (NULL,'arche','variabilityreasoningframework_variabilitymechanismresponsibility','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:18',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration','BASE TABLE','InnoDB',10,'Compact',0,0,16384,0,32768,0,1,'2007-02-20 11:57:18',NULL,NULL,'utf8_general_ci',NULL,'','InnoDB free: 14336 kB; (`version`) REFER `arche/__versions__`(`ID`) ON DELETE CA'),
 (NULL,'mysql','columns_priv','BASE TABLE','MyISAM',10,'Fixed',0,0,0,227994731135631359,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_bin',NULL,'','Column privileges');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'mysql','db','BASE TABLE','MyISAM',10,'Fixed',1,438,876,123286039799267327,4096,438,NULL,'2005-12-21 14:51:13','2007-02-20 12:25:27',NULL,'utf8_bin',NULL,'','Database privileges'),
 (NULL,'mysql','func','BASE TABLE','MyISAM',10,'Fixed',0,0,0,162974011515469823,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_bin',NULL,'','User defined functions'),
 (NULL,'mysql','help_category','BASE TABLE','MyISAM',10,'Fixed',0,0,0,163536961468891135,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','help categories'),
 (NULL,'mysql','help_keyword','BASE TABLE','MyISAM',10,'Fixed',0,0,0,55450570411999231,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','help keywords'),
 (NULL,'mysql','help_relation','BASE TABLE','MyISAM',10,'Fixed',0,0,0,2533274790395903,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','keyword-topic relation');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'mysql','help_topic','BASE TABLE','MyISAM',10,'Dynamic',0,0,0,281474976710655,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','help topics'),
 (NULL,'mysql','host','BASE TABLE','MyISAM',10,'Fixed',0,0,0,109775240917155839,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_bin',NULL,'','Host privileges;  Merged with database privileges'),
 (NULL,'mysql','proc','BASE TABLE','MyISAM',10,'Dynamic',0,0,0,281474976710655,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','Stored Procedures'),
 (NULL,'mysql','procs_priv','BASE TABLE','MyISAM',10,'Fixed',0,0,0,239253730204057599,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_bin',NULL,'','Procedure privileges'),
 (NULL,'mysql','tables_priv','BASE TABLE','MyISAM',10,'Fixed',0,0,0,239535205180768255,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_bin',NULL,'','Table privileges');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'mysql','time_zone','BASE TABLE','MyISAM',10,'Fixed',0,0,0,1970324836974591,1024,0,1,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','Time zones'),
 (NULL,'mysql','time_zone_leap_second','BASE TABLE','MyISAM',10,'Fixed',0,0,0,3659174697238527,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','Leap seconds information for time zones'),
 (NULL,'mysql','time_zone_name','BASE TABLE','MyISAM',10,'Fixed',0,0,0,55450570411999231,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','Time zone names'),
 (NULL,'mysql','time_zone_transition','BASE TABLE','MyISAM',10,'Fixed',0,0,0,4785074604081151,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','Time zone transitions'),
 (NULL,'mysql','time_zone_transition_type','BASE TABLE','MyISAM',10,'Fixed',0,0,0,10696049115004927,1024,0,NULL,'2005-12-21 14:51:13','2005-12-21 21:51:14',NULL,'utf8_general_ci',NULL,'','Time zone transition types');
INSERT INTO `TABLES` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`TABLE_TYPE`,`ENGINE`,`VERSION`,`ROW_FORMAT`,`TABLE_ROWS`,`AVG_ROW_LENGTH`,`DATA_LENGTH`,`MAX_DATA_LENGTH`,`INDEX_LENGTH`,`DATA_FREE`,`AUTO_INCREMENT`,`CREATE_TIME`,`UPDATE_TIME`,`CHECK_TIME`,`TABLE_COLLATION`,`CHECKSUM`,`CREATE_OPTIONS`,`TABLE_COMMENT`) VALUES 
 (NULL,'mysql','user','BASE TABLE','MyISAM',10,'Dynamic',3,84,252,281474976710655,2048,0,NULL,'2005-12-21 14:51:13','2008-08-07 14:04:53',NULL,'utf8_bin',NULL,'','Users and global privileges'),
 (NULL,'mysql','user_info','BASE TABLE','MyISAM',10,'Dynamic',0,0,0,281474976710655,1024,0,NULL,'2007-02-20 10:58:25','2007-02-20 11:58:25',NULL,'utf8_bin',NULL,'','Stores additional user information');
/*!40000 ALTER TABLE `TABLES` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`TABLE_CONSTRAINTS`
--

DROP TABLE IF EXISTS `TABLE_CONSTRAINTS`;
CREATE TEMPORARY TABLE `TABLE_CONSTRAINTS` (
  `CONSTRAINT_CATALOG` varchar(512) default NULL,
  `CONSTRAINT_SCHEMA` varchar(64) NOT NULL default '',
  `CONSTRAINT_NAME` varchar(64) NOT NULL default '',
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `TABLE_NAME` varchar(64) NOT NULL default '',
  `CONSTRAINT_TYPE` varchar(64) NOT NULL default ''
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`TABLE_CONSTRAINTS`
--

/*!40000 ALTER TABLE `TABLE_CONSTRAINTS` DISABLE KEYS */;
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','__factsets__','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','__mod_solver_interface__','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','__tool_command_interface__','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','__versions__','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_arc_relation','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_arc_relation','arche','changeimpactmodifiabilityrf_arc_relation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_module','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_module','arche','changeimpactmodifiabilityrf_module','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_moduledependencyrelation','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_moduledependencyrelation','arche','changeimpactmodifiabilityrf_moduledependencyrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_node_responsibility','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','FK_changeimpactmodifiabilityrf_node_responsibility','arche','changeimpactmodifiabilityrf_node_responsibility','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_p_booleantest','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_p_booleantest','arche','changeimpactmodifiabilityrf_p_booleantest','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_p_costofchange','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_p_costofchange','arche','changeimpactmodifiabilityrf_p_costofchange','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_p_probabilityincoming','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_p_probabilityincoming','arche','changeimpactmodifiabilityrf_p_probabilityincoming','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_p_probabilityoutgoing','arche','changeimpactmodifiabilityrf_p_probabilityoutgoing','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_responsibilitydependencyrelation','arche','changeimpactmodifiabilityrf_responsibilitydependencyrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','PRIMARY KEY'),
 (NULL,'arche','FK_changeimpactmodifiabilityrf_responsibilitytomodulerelation','arche','changeimpactmodifiabilityrf_responsibilitytomodulerelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','columns_priv','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','db','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','design_module','PRIMARY KEY'),
 (NULL,'arche','FK_design_module','arche','design_module','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_moduledependencyrelation','PRIMARY KEY'),
 (NULL,'arche','FK_design_moduledependencyrelation','arche','design_moduledependencyrelation','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','design_moduleinterface','PRIMARY KEY'),
 (NULL,'arche','FK_design_moduleinterface','arche','design_moduleinterface','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_modulerefinementrelation','PRIMARY KEY'),
 (NULL,'arche','FK_design_modulerefinementrelation','arche','design_modulerefinementrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_process','PRIMARY KEY'),
 (NULL,'arche','FK_design_process','arche','design_process','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_realizerelation','PRIMARY KEY'),
 (NULL,'arche','FK_design_realizerelation','arche','design_realizerelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_responsibility','PRIMARY KEY'),
 (NULL,'arche','FK_design_responsibility','arche','design_responsibility','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_responsibilitytomodulerelation','PRIMARY KEY'),
 (NULL,'arche','FK_design_responsibilitytomodulerelation','arche','design_responsibilitytomodulerelation','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','design_sharedresource','PRIMARY KEY'),
 (NULL,'arche','FK_design_sharedresource','arche','design_sharedresource','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_sharedresourcerelation','PRIMARY KEY'),
 (NULL,'arche','design_sharedresourcerelation_ibfk_1','arche','design_sharedresourcerelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_thread','PRIMARY KEY'),
 (NULL,'arche','FK_design_thread','arche','design_thread','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_unitofconcurrency','PRIMARY KEY'),
 (NULL,'arche','FK_design_unitofconcurrency','arche','design_unitofconcurrency','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_variationpoint','PRIMARY KEY'),
 (NULL,'arche','FK_design_variationpoint','arche','design_variationpoint','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','design_vpinputvalue','PRIMARY KEY'),
 (NULL,'arche','FK_design_vpinputvalue','arche','design_vpinputvalue','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','design_wrapper','PRIMARY KEY'),
 (NULL,'arche','FK_design_wrapper','arche','design_wrapper','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','externalinteraction_responsibilitydependencyrelation','PRIMARY KEY'),
 (NULL,'arche','FK_externalinteraction_responsibilitydependencyrelation','arche','externalinteraction_responsibilitydependencyrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','externalinteraction_responsibilityinteractionrelation','PRIMARY KEY'),
 (NULL,'arche','FK_externalinteraction_responsibilityinteractionrelation','arche','externalinteraction_responsibilityinteractionrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','externalinteraction_responsibilityreactionrelation','PRIMARY KEY'),
 (NULL,'arche','FK_externalinteraction_responsibilityreactionrelation','arche','externalinteraction_responsibilityreactionrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','func','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','help_category','PRIMARY KEY'),
 (NULL,'arche','name','arche','help_category','UNIQUE'),
 (NULL,'arche','PRIMARY','arche','help_keyword','PRIMARY KEY'),
 (NULL,'arche','name','arche','help_keyword','UNIQUE'),
 (NULL,'arche','PRIMARY','arche','help_relation','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','help_topic','PRIMARY KEY'),
 (NULL,'arche','name','arche','help_topic','UNIQUE'),
 (NULL,'arche','PRIMARY','arche','host','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','icmperformancerf_assemblyinstance','PRIMARY KEY'),
 (NULL,'arche','FK_icmperformancerf_assemblyinstance','arche','icmperformancerf_assemblyinstance','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','icmperformancerf_p_executiontime','PRIMARY KEY'),
 (NULL,'arche','FK_icmperformancerf_p_executiontime','arche','icmperformancerf_p_executiontime','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','icmperformancerf_p_taskpriority','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','FK_icmperformancerf_p_taskpriority','arche','icmperformancerf_p_taskpriority','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','icmperformancerf_responsibilityinteractionrelation','PRIMARY KEY'),
 (NULL,'arche','FK_icmperformancerf_responsibilityinteractionrelation','arche','icmperformancerf_responsibilityinteractionrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','icmperformancerf_responsibilityreactionrelation','PRIMARY KEY'),
 (NULL,'arche','FK_icmperformancerf_responsibilityreactionrelation','arche','icmperformancerf_responsibilityreactionrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_dataflow','PRIMARY KEY'),
 (NULL,'arche','FK_main_dataflow','arche','main_dataflow','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_dataitems','PRIMARY KEY'),
 (NULL,'arche','FK_main_dataitems_1','arche','main_dataitems','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_function','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','FK_main_function','arche','main_function','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_functionrefinementrelation','PRIMARY KEY'),
 (NULL,'arche','FK_main_functionrefinementrelation','arche','main_functionrefinementrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_leafresponsibilitydependencyrelation','PRIMARY KEY'),
 (NULL,'arche','FK_main_leafresponsibilitydependencyrelation','arche','main_leafresponsibilitydependencyrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_p_analysisresult','PRIMARY KEY'),
 (NULL,'arche','FK_main_p_analysisresult','arche','main_p_analysisresult','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_responsibilities','PRIMARY KEY'),
 (NULL,'arche','FK_main_responsibilities','arche','main_responsibilities','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_responsibilityrefinementrelation','PRIMARY KEY'),
 (NULL,'arche','FK_main_responsibilityrefinementrelation','arche','main_responsibilityrefinementrelation','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','main_responsibilitytoresponsibilityrelation','PRIMARY KEY'),
 (NULL,'arche','FK_main_responsibilitytoresponsibilityrelation','arche','main_responsibilitytoresponsibilityrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_scenariorefinementrelation','PRIMARY KEY'),
 (NULL,'arche','FK_main_scenariorefinementrelation','arche','main_scenariorefinementrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_scenarios','PRIMARY KEY'),
 (NULL,'arche','FK_main_scenarios','arche','main_scenarios','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_sequencerelation','PRIMARY KEY'),
 (NULL,'arche','FK_main_sequencerelation','arche','main_sequencerelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','main_translationrelation','PRIMARY KEY'),
 (NULL,'arche','FK_main_translationrelation','arche','main_translationrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilitylearn_modifiabilitylearnvalues','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','FK_modifiabilitylearn_modifiabilitylearnvalues','arche','modifiabilitylearn_modifiabilitylearnvalues','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_arc_relation','PRIMARY KEY'),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_arc_relation','arche','modifiabilityreasoningframeworks_arc_relation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_node_affected','PRIMARY KEY'),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_node_affected','arche','modifiabilityreasoningframeworks_node_affected','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_node_responsibility','PRIMARY KEY'),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_node_responsibility','arche','modifiabilityreasoningframeworks_node_responsibility','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_p_abstractiontranslator','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','FK_modifiabilityreasoningframeworks_p_abstractiontranslator','arche','modifiabilityreasoningframeworks_p_abstractiontranslator','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_p_costofchange','PRIMARY KEY'),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_p_costofchange','arche','modifiabilityreasoningframeworks_p_costofchange','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_p_encapsulationlevel','PRIMARY KEY'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_encapsulationlevel_ibfk_1','arche','modifiabilityreasoningframeworks_p_encapsulationlevel','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_p_interfacerouter','PRIMARY KEY'),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_p_interfacerouter','arche','modifiabilityreasoningframeworks_p_interfacerouter','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_p_preparedforchange','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','modifiabilityreasoningframeworks_p_preparedforchange_ibfk_1','arche','modifiabilityreasoningframeworks_p_preparedforchange','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_p_probabilityincoming','PRIMARY KEY'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityincoming_ibfk_1','arche','modifiabilityreasoningframeworks_p_probabilityincoming','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','PRIMARY KEY'),
 (NULL,'arche','modifiabilityreasoningframeworks_p_probabilityoutgoing_ibfk_1','arche','modifiabilityreasoningframeworks_p_probabilityoutgoing','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_response_measures','PRIMARY KEY'),
 (NULL,'arche','FK_modifiabilityreasoningframeworks_response_measures','arche','modifiabilityreasoningframeworks_response_measures','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','modifiabilityreasoningframeworks_tactics','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','myfirstrf_p_testrelationparameter','PRIMARY KEY'),
 (NULL,'arche','FK_myfirstrf_p_testrelationparameter','arche','myfirstrf_p_testrelationparameter','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','myfirstrf_p_testresponsibilityparameter','PRIMARY KEY'),
 (NULL,'arche','FK_myfirstrf_p_testresponsibilityparameter','arche','myfirstrf_p_testresponsibilityparameter','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','myfirstrf_testresponsibilityrelation','PRIMARY KEY'),
 (NULL,'arche','FK_myfirstrf_testresponsibilityrelation','arche','myfirstrf_testresponsibilityrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','patterns_dependency','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_dependency','arche','patterns_dependency','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','patterns_interfacerealization','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_interfacerealization','arche','patterns_interfacerealization','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','patterns_isarelation','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_isarelation','arche','patterns_isarelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','patterns_pattern','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_pattern','arche','patterns_pattern','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','patterns_patternconnector','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_patternconnector','arche','patterns_patternconnector','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','patterns_patterncontainer','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_patterncontainer','arche','patterns_patterncontainer','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','patterns_patternelement','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_patternelement','arche','patterns_patternelement','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','patterns_patternelementinterface','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_patternelementinterface','arche','patterns_patternelementinterface','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','patterns_patternitemproperty','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_patternitemproperty','arche','patterns_patternitemproperty','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','patterns_refinement','PRIMARY KEY'),
 (NULL,'arche','FK_patterns_refinement','arche','patterns_refinement','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','performancereasoningframeworks_p_executiontime','PRIMARY KEY'),
 (NULL,'arche','FK_performancereasoningframeworks_p_executiontime','arche','performancereasoningframeworks_p_executiontime','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','performancereasoningframeworks_p_mutualexclusion','PRIMARY KEY'),
 (NULL,'arche','FK_performancereasoningframeworks_p_mutualexclusion','arche','performancereasoningframeworks_p_mutualexclusion','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','performancereasoningframeworks_p_sharedresourceasked','PRIMARY KEY'),
 (NULL,'arche','FK_performancereasoningframeworks_p_sharedresourceasked','arche','performancereasoningframeworks_p_sharedresourceasked','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','planner_c_addfunction','PRIMARY KEY'),
 (NULL,'arche','FK_planner_c_addfunction','arche','planner_c_addfunction','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','planner_c_addresponsibilityrelation','PRIMARY KEY'),
 (NULL,'arche','FK_planner_c_addresponsibilityrelation','arche','planner_c_addresponsibilityrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','planner_c_addscenario','PRIMARY KEY'),
 (NULL,'arche','FK_planner_c_addscenario','arche','planner_c_addscenario','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','planner_c_addtranslationrelation','PRIMARY KEY'),
 (NULL,'arche','FK_planner_c_addtranslationrelation','arche','planner_c_addtranslationrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','proc','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','procs_priv','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','rmamodel_p_latency','PRIMARY KEY'),
 (NULL,'arche','FK_rmamodel_p_latency','arche','rmamodel_p_latency','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','rmamodel_subtask','PRIMARY KEY'),
 (NULL,'arche','FK_rmamodel_subtask','arche','rmamodel_subtask','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','rmamodel_task','PRIMARY KEY'),
 (NULL,'arche','FK_rmamodel_task','arche','rmamodel_task','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','rmaperformancerf_p_executiontime','PRIMARY KEY'),
 (NULL,'arche','FK_rmaperformancerf_p_executiontime','arche','rmaperformancerf_p_executiontime','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','rmaperformancerf_p_shared','PRIMARY KEY'),
 (NULL,'arche','FK_rmaperformancerf_p_shared','arche','rmaperformancerf_p_shared','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','rmaperformancerf_p_taskpriority','PRIMARY KEY'),
 (NULL,'arche','FK_rmaperformancerf_p_taskpriority','arche','rmaperformancerf_p_taskpriority','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','rmaperformancerf_resource','PRIMARY KEY'),
 (NULL,'arche','FK_rmaperformancerf_resource','arche','rmaperformancerf_resource','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','rmaperformancerf_resourceusagerelation','PRIMARY KEY'),
 (NULL,'arche','FK_rmaperformancerf_resourceusagerelation','arche','rmaperformancerf_resourceusagerelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','rmaperformancerf_thread','PRIMARY KEY'),
 (NULL,'arche','FK_rmaperformancerf_thread','arche','rmaperformancerf_thread','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','rmaperformancerf_threadtouocrelation','PRIMARY KEY'),
 (NULL,'arche','FK_rmaperformancerf_threadtouocrelation','arche','rmaperformancerf_threadtouocrelation','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','rmaperformancerf_unitofconcurrency','PRIMARY KEY'),
 (NULL,'arche','FK_rmaperformancerf_unitofconcurrency','arche','rmaperformancerf_unitofconcurrency','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','seeker_evaluationresult','PRIMARY KEY'),
 (NULL,'arche','FK_seeker_evaluationresult','arche','seeker_evaluationresult','FOREIGN KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','tables_priv','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','tactics','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','test_interface','PRIMARY KEY'),
 (NULL,'arche','FK_test_interface','arche','test_interface','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_leave_dependencies','PRIMARY KEY'),
 (NULL,'arche','FK_test_leave_dependencies','arche','test_leave_dependencies','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_model_arcs','PRIMARY KEY'),
 (NULL,'arche','FK_test_model_arcs','arche','test_model_arcs','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_model_nodes','PRIMARY KEY'),
 (NULL,'arche','FK_test_model_nodes','arche','test_model_nodes','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_model_nodes_affected','PRIMARY KEY'),
 (NULL,'arche','FK_test_model_nodes_affected','arche','test_model_nodes_affected','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_module','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','FK_test_module','arche','test_module','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_module_dependencies','PRIMARY KEY'),
 (NULL,'arche','FK_test_module_dependencies','arche','test_module_dependencies','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_module_responsibilities','PRIMARY KEY'),
 (NULL,'arche','FK_test_module_responsibilities','arche','test_module_responsibilities','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_responsibilities','PRIMARY KEY'),
 (NULL,'arche','FK_test_responsibilities','arche','test_responsibilities','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','test_responsibility_refinement','PRIMARY KEY'),
 (NULL,'arche','FK_test_responsibility_refinement','arche','test_responsibility_refinement','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','time_zone','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','time_zone_leap_second','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','time_zone_name','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'arche','PRIMARY','arche','time_zone_transition','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','time_zone_transition_type','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','user','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','user_info','PRIMARY KEY'),
 (NULL,'arche','PRIMARY','arche','variabilityreasoningframework_variabilitymechanismresponsibility','PRIMARY KEY'),
 (NULL,'arche','variabilityrf_variabilitymechanismresponsibility_ibfk_1','arche','variabilityreasoningframework_variabilitymechanismresponsibility','FOREIGN KEY'),
 (NULL,'arche','PRIMARY','arche','variabilityreasoningframework_vpconfiguration','PRIMARY KEY'),
 (NULL,'arche','variabilityreasoningframework_vpconfiguration_ibfk_1','arche','variabilityreasoningframework_vpconfiguration','FOREIGN KEY'),
 (NULL,'mysql','PRIMARY','mysql','columns_priv','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','db','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','func','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'mysql','PRIMARY','mysql','help_category','PRIMARY KEY'),
 (NULL,'mysql','name','mysql','help_category','UNIQUE'),
 (NULL,'mysql','PRIMARY','mysql','help_keyword','PRIMARY KEY'),
 (NULL,'mysql','name','mysql','help_keyword','UNIQUE'),
 (NULL,'mysql','PRIMARY','mysql','help_relation','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','help_topic','PRIMARY KEY'),
 (NULL,'mysql','name','mysql','help_topic','UNIQUE'),
 (NULL,'mysql','PRIMARY','mysql','host','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','proc','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','procs_priv','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','tables_priv','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','time_zone','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','time_zone_leap_second','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','time_zone_name','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','time_zone_transition','PRIMARY KEY');
INSERT INTO `TABLE_CONSTRAINTS` (`CONSTRAINT_CATALOG`,`CONSTRAINT_SCHEMA`,`CONSTRAINT_NAME`,`TABLE_SCHEMA`,`TABLE_NAME`,`CONSTRAINT_TYPE`) VALUES 
 (NULL,'mysql','PRIMARY','mysql','time_zone_transition_type','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','user','PRIMARY KEY'),
 (NULL,'mysql','PRIMARY','mysql','user_info','PRIMARY KEY');
/*!40000 ALTER TABLE `TABLE_CONSTRAINTS` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`TABLE_PRIVILEGES`
--

DROP TABLE IF EXISTS `TABLE_PRIVILEGES`;
CREATE TEMPORARY TABLE `TABLE_PRIVILEGES` (
  `GRANTEE` varchar(81) NOT NULL default '',
  `TABLE_CATALOG` varchar(512) default NULL,
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `TABLE_NAME` varchar(64) NOT NULL default '',
  `PRIVILEGE_TYPE` varchar(64) NOT NULL default '',
  `IS_GRANTABLE` varchar(3) NOT NULL default ''
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`TABLE_PRIVILEGES`
--

/*!40000 ALTER TABLE `TABLE_PRIVILEGES` DISABLE KEYS */;
/*!40000 ALTER TABLE `TABLE_PRIVILEGES` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`TRIGGERS`
--

DROP TABLE IF EXISTS `TRIGGERS`;
CREATE TEMPORARY TABLE `TRIGGERS` (
  `TRIGGER_CATALOG` varchar(512) default NULL,
  `TRIGGER_SCHEMA` varchar(64) NOT NULL default '',
  `TRIGGER_NAME` varchar(64) NOT NULL default '',
  `EVENT_MANIPULATION` varchar(6) NOT NULL default '',
  `EVENT_OBJECT_CATALOG` varchar(512) default NULL,
  `EVENT_OBJECT_SCHEMA` varchar(64) NOT NULL default '',
  `EVENT_OBJECT_TABLE` varchar(64) NOT NULL default '',
  `ACTION_ORDER` bigint(4) NOT NULL default '0',
  `ACTION_CONDITION` longtext,
  `ACTION_STATEMENT` longtext NOT NULL,
  `ACTION_ORIENTATION` varchar(9) NOT NULL default '',
  `ACTION_TIMING` varchar(6) NOT NULL default '',
  `ACTION_REFERENCE_OLD_TABLE` varchar(64) default NULL,
  `ACTION_REFERENCE_NEW_TABLE` varchar(64) default NULL,
  `ACTION_REFERENCE_OLD_ROW` varchar(3) NOT NULL default '',
  `ACTION_REFERENCE_NEW_ROW` varchar(3) NOT NULL default '',
  `CREATED` datetime default NULL,
  `SQL_MODE` longtext NOT NULL,
  `DEFINER` longtext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`TRIGGERS`
--

/*!40000 ALTER TABLE `TRIGGERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TRIGGERS` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`VIEWS`
--

DROP TABLE IF EXISTS `VIEWS`;
CREATE TEMPORARY TABLE `VIEWS` (
  `TABLE_CATALOG` varchar(512) default NULL,
  `TABLE_SCHEMA` varchar(64) NOT NULL default '',
  `TABLE_NAME` varchar(64) NOT NULL default '',
  `VIEW_DEFINITION` longtext NOT NULL,
  `CHECK_OPTION` varchar(8) NOT NULL default '',
  `IS_UPDATABLE` varchar(3) NOT NULL default '',
  `DEFINER` varchar(77) NOT NULL default '',
  `SECURITY_TYPE` varchar(7) NOT NULL default ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`VIEWS`
--

/*!40000 ALTER TABLE `VIEWS` DISABLE KEYS */;
INSERT INTO `VIEWS` (`TABLE_CATALOG`,`TABLE_SCHEMA`,`TABLE_NAME`,`VIEW_DEFINITION`,`CHECK_OPTION`,`IS_UPDATABLE`,`DEFINER`,`SECURITY_TYPE`) VALUES 
 (NULL,'arche','leave_responsibilities','select `res`.`uid` AS `uid`,`res`.`version` AS `version`,`res`.`fact-id` AS `fact-id`,`res`.`id` AS `id`,`res`.`name` AS `name`,`res`.`description` AS `description`,`res`.`source` AS `source`,`v`.`version_name` AS `version_name` from ((`arche`.`main_responsibilities` `res` join `arche`.`__versions__` `v` on((`v`.`ID` = `res`.`version`))) left join `arche`.`main_responsibilityrefinementrelation` `d` on(((`res`.`version` = `d`.`version`) and (`res`.`fact-id` = `d`.`parent`)))) where isnull(`d`.`parent`)','NONE','NO','root@localhost','DEFINER'),
 (NULL,'arche','v_factset','select `arche`.`__factsets__`.`factType` AS `factType`,`arche`.`__factsets__`.`setName` AS `setName` from `arche`.`__factsets__` order by `arche`.`__factsets__`.`group`,`arche`.`__factsets__`.`ID`','NONE','NO','root@localhost','DEFINER');
/*!40000 ALTER TABLE `VIEWS` ENABLE KEYS */;


--
-- Table structure for table `information_schema`.`USER_PRIVILEGES`
--

DROP TABLE IF EXISTS `USER_PRIVILEGES`;
CREATE TEMPORARY TABLE `USER_PRIVILEGES` (
  `GRANTEE` varchar(81) NOT NULL default '',
  `TABLE_CATALOG` varchar(512) default NULL,
  `PRIVILEGE_TYPE` varchar(64) NOT NULL default '',
  `IS_GRANTABLE` varchar(3) NOT NULL default ''
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `information_schema`.`USER_PRIVILEGES`
--

/*!40000 ALTER TABLE `USER_PRIVILEGES` DISABLE KEYS */;
INSERT INTO `USER_PRIVILEGES` (`GRANTEE`,`TABLE_CATALOG`,`PRIVILEGE_TYPE`,`IS_GRANTABLE`) VALUES 
 ('\'root\'@\'localhost\'',NULL,'SELECT','YES'),
 ('\'root\'@\'localhost\'',NULL,'INSERT','YES'),
 ('\'root\'@\'localhost\'',NULL,'UPDATE','YES'),
 ('\'root\'@\'localhost\'',NULL,'DELETE','YES'),
 ('\'root\'@\'localhost\'',NULL,'CREATE','YES'),
 ('\'root\'@\'localhost\'',NULL,'DROP','YES'),
 ('\'root\'@\'localhost\'',NULL,'RELOAD','YES'),
 ('\'root\'@\'localhost\'',NULL,'SHUTDOWN','YES'),
 ('\'root\'@\'localhost\'',NULL,'PROCESS','YES'),
 ('\'root\'@\'localhost\'',NULL,'FILE','YES'),
 ('\'root\'@\'localhost\'',NULL,'REFERENCES','YES'),
 ('\'root\'@\'localhost\'',NULL,'INDEX','YES'),
 ('\'root\'@\'localhost\'',NULL,'ALTER','YES'),
 ('\'root\'@\'localhost\'',NULL,'SHOW DATABASES','YES'),
 ('\'root\'@\'localhost\'',NULL,'SUPER','YES'),
 ('\'root\'@\'localhost\'',NULL,'CREATE TEMPORARY TABLES','YES'),
 ('\'root\'@\'localhost\'',NULL,'LOCK TABLES','YES'),
 ('\'root\'@\'localhost\'',NULL,'EXECUTE','YES'),
 ('\'root\'@\'localhost\'',NULL,'REPLICATION SLAVE','YES');
INSERT INTO `USER_PRIVILEGES` (`GRANTEE`,`TABLE_CATALOG`,`PRIVILEGE_TYPE`,`IS_GRANTABLE`) VALUES 
 ('\'root\'@\'localhost\'',NULL,'REPLICATION CLIENT','YES'),
 ('\'root\'@\'localhost\'',NULL,'CREATE VIEW','YES'),
 ('\'root\'@\'localhost\'',NULL,'SHOW VIEW','YES'),
 ('\'root\'@\'localhost\'',NULL,'CREATE ROUTINE','YES'),
 ('\'root\'@\'localhost\'',NULL,'ALTER ROUTINE','YES'),
 ('\'root\'@\'localhost\'',NULL,'CREATE USER','YES'),
 ('\'root\'@\'%\'',NULL,'SELECT','YES'),
 ('\'root\'@\'%\'',NULL,'INSERT','YES'),
 ('\'root\'@\'%\'',NULL,'UPDATE','YES'),
 ('\'root\'@\'%\'',NULL,'DELETE','YES'),
 ('\'root\'@\'%\'',NULL,'CREATE','YES'),
 ('\'root\'@\'%\'',NULL,'DROP','YES'),
 ('\'root\'@\'%\'',NULL,'RELOAD','YES'),
 ('\'root\'@\'%\'',NULL,'SHUTDOWN','YES'),
 ('\'root\'@\'%\'',NULL,'PROCESS','YES'),
 ('\'root\'@\'%\'',NULL,'FILE','YES'),
 ('\'root\'@\'%\'',NULL,'REFERENCES','YES'),
 ('\'root\'@\'%\'',NULL,'INDEX','YES'),
 ('\'root\'@\'%\'',NULL,'ALTER','YES'),
 ('\'root\'@\'%\'',NULL,'SHOW DATABASES','YES'),
 ('\'root\'@\'%\'',NULL,'SUPER','YES');
INSERT INTO `USER_PRIVILEGES` (`GRANTEE`,`TABLE_CATALOG`,`PRIVILEGE_TYPE`,`IS_GRANTABLE`) VALUES 
 ('\'root\'@\'%\'',NULL,'CREATE TEMPORARY TABLES','YES'),
 ('\'root\'@\'%\'',NULL,'LOCK TABLES','YES'),
 ('\'root\'@\'%\'',NULL,'EXECUTE','YES'),
 ('\'root\'@\'%\'',NULL,'REPLICATION SLAVE','YES'),
 ('\'root\'@\'%\'',NULL,'REPLICATION CLIENT','YES'),
 ('\'ArchE\'@\'%\'',NULL,'USAGE','NO');
/*!40000 ALTER TABLE `USER_PRIVILEGES` ENABLE KEYS */;

--
-- Create schema mysql
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ mysql;
USE mysql;

--
-- Table structure for table `mysql`.`columns_priv`
--

DROP TABLE IF EXISTS `columns_priv`;
CREATE TABLE `columns_priv` (
  `Host` char(60) collate utf8_bin NOT NULL default '',
  `Db` char(64) collate utf8_bin NOT NULL default '',
  `User` char(16) collate utf8_bin NOT NULL default '',
  `Table_name` char(64) collate utf8_bin NOT NULL default '',
  `Column_name` char(64) collate utf8_bin NOT NULL default '',
  `Timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `Column_priv` set('Select','Insert','Update','References') character set utf8 NOT NULL default '',
  PRIMARY KEY  (`Host`,`Db`,`User`,`Table_name`,`Column_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column privileges';

--
-- Dumping data for table `mysql`.`columns_priv`
--

/*!40000 ALTER TABLE `columns_priv` DISABLE KEYS */;
/*!40000 ALTER TABLE `columns_priv` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`db`
--

DROP TABLE IF EXISTS `db`;
CREATE TABLE `db` (
  `Host` char(60) collate utf8_bin NOT NULL default '',
  `Db` char(64) collate utf8_bin NOT NULL default '',
  `User` char(16) collate utf8_bin NOT NULL default '',
  `Select_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Insert_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Update_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Delete_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Drop_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Grant_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `References_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Index_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Alter_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_tmp_table_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Lock_tables_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_view_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Show_view_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_routine_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Alter_routine_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Execute_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  PRIMARY KEY  (`Host`,`Db`,`User`),
  KEY `User` (`User`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database privileges';

--
-- Dumping data for table `mysql`.`db`
--

/*!40000 ALTER TABLE `db` DISABLE KEYS */;
INSERT INTO `db` (`Host`,`Db`,`User`,`Select_priv`,`Insert_priv`,`Update_priv`,`Delete_priv`,`Create_priv`,`Drop_priv`,`Grant_priv`,`References_priv`,`Index_priv`,`Alter_priv`,`Create_tmp_table_priv`,`Lock_tables_priv`,`Create_view_priv`,`Show_view_priv`,`Create_routine_priv`,`Alter_routine_priv`,`Execute_priv`) VALUES 
 ('%','arche','ArchE','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y');
/*!40000 ALTER TABLE `db` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`func`
--

DROP TABLE IF EXISTS `func`;
CREATE TABLE `func` (
  `name` char(64) collate utf8_bin NOT NULL default '',
  `ret` tinyint(1) NOT NULL default '0',
  `dl` char(128) collate utf8_bin NOT NULL default '',
  `type` enum('function','aggregate') character set utf8 NOT NULL,
  PRIMARY KEY  (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User defined functions';

--
-- Dumping data for table `mysql`.`func`
--

/*!40000 ALTER TABLE `func` DISABLE KEYS */;
/*!40000 ALTER TABLE `func` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`help_category`
--

DROP TABLE IF EXISTS `help_category`;
CREATE TABLE `help_category` (
  `help_category_id` smallint(5) unsigned NOT NULL,
  `name` char(64) NOT NULL,
  `parent_category_id` smallint(5) unsigned default NULL,
  `url` char(128) NOT NULL,
  PRIMARY KEY  (`help_category_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='help categories';

--
-- Dumping data for table `mysql`.`help_category`
--

/*!40000 ALTER TABLE `help_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `help_category` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`help_keyword`
--

DROP TABLE IF EXISTS `help_keyword`;
CREATE TABLE `help_keyword` (
  `help_keyword_id` int(10) unsigned NOT NULL,
  `name` char(64) NOT NULL,
  PRIMARY KEY  (`help_keyword_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='help keywords';

--
-- Dumping data for table `mysql`.`help_keyword`
--

/*!40000 ALTER TABLE `help_keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `help_keyword` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`help_relation`
--

DROP TABLE IF EXISTS `help_relation`;
CREATE TABLE `help_relation` (
  `help_topic_id` int(10) unsigned NOT NULL,
  `help_keyword_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`help_keyword_id`,`help_topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='keyword-topic relation';

--
-- Dumping data for table `mysql`.`help_relation`
--

/*!40000 ALTER TABLE `help_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `help_relation` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`help_topic`
--

DROP TABLE IF EXISTS `help_topic`;
CREATE TABLE `help_topic` (
  `help_topic_id` int(10) unsigned NOT NULL,
  `name` char(64) NOT NULL,
  `help_category_id` smallint(5) unsigned NOT NULL,
  `description` text NOT NULL,
  `example` text NOT NULL,
  `url` char(128) NOT NULL,
  PRIMARY KEY  (`help_topic_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='help topics';

--
-- Dumping data for table `mysql`.`help_topic`
--

/*!40000 ALTER TABLE `help_topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `help_topic` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`host`
--

DROP TABLE IF EXISTS `host`;
CREATE TABLE `host` (
  `Host` char(60) collate utf8_bin NOT NULL default '',
  `Db` char(64) collate utf8_bin NOT NULL default '',
  `Select_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Insert_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Update_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Delete_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Drop_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Grant_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `References_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Index_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Alter_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_tmp_table_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Lock_tables_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_view_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Show_view_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_routine_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Alter_routine_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Execute_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  PRIMARY KEY  (`Host`,`Db`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Host privileges;  Merged with database privileges';

--
-- Dumping data for table `mysql`.`host`
--

/*!40000 ALTER TABLE `host` DISABLE KEYS */;
/*!40000 ALTER TABLE `host` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`proc`
--

DROP TABLE IF EXISTS `proc`;
CREATE TABLE `proc` (
  `db` char(64) character set utf8 collate utf8_bin NOT NULL default '',
  `name` char(64) NOT NULL default '',
  `type` enum('FUNCTION','PROCEDURE') NOT NULL,
  `specific_name` char(64) NOT NULL default '',
  `language` enum('SQL') NOT NULL default 'SQL',
  `sql_data_access` enum('CONTAINS_SQL','NO_SQL','READS_SQL_DATA','MODIFIES_SQL_DATA') NOT NULL default 'CONTAINS_SQL',
  `is_deterministic` enum('YES','NO') NOT NULL default 'NO',
  `security_type` enum('INVOKER','DEFINER') NOT NULL default 'DEFINER',
  `param_list` blob NOT NULL,
  `returns` char(64) NOT NULL default '',
  `body` longblob NOT NULL,
  `definer` char(77) character set utf8 collate utf8_bin NOT NULL default '',
  `created` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `modified` timestamp NOT NULL default '0000-00-00 00:00:00',
  `sql_mode` set('REAL_AS_FLOAT','PIPES_AS_CONCAT','ANSI_QUOTES','IGNORE_SPACE','NOT_USED','ONLY_FULL_GROUP_BY','NO_UNSIGNED_SUBTRACTION','NO_DIR_IN_CREATE','POSTGRESQL','ORACLE','MSSQL','DB2','MAXDB','NO_KEY_OPTIONS','NO_TABLE_OPTIONS','NO_FIELD_OPTIONS','MYSQL323','MYSQL40','ANSI','NO_AUTO_VALUE_ON_ZERO','NO_BACKSLASH_ESCAPES','STRICT_TRANS_TABLES','STRICT_ALL_TABLES','NO_ZERO_IN_DATE','NO_ZERO_DATE','INVALID_DATES','ERROR_FOR_DIVISION_BY_ZERO','TRADITIONAL','NO_AUTO_CREATE_USER','HIGH_NOT_PRECEDENCE') NOT NULL default '',
  `comment` char(64) character set utf8 collate utf8_bin NOT NULL default '',
  PRIMARY KEY  (`db`,`name`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Stored Procedures';

--
-- Dumping data for table `mysql`.`proc`
--

/*!40000 ALTER TABLE `proc` DISABLE KEYS */;
/*!40000 ALTER TABLE `proc` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`procs_priv`
--

DROP TABLE IF EXISTS `procs_priv`;
CREATE TABLE `procs_priv` (
  `Host` char(60) collate utf8_bin NOT NULL default '',
  `Db` char(64) collate utf8_bin NOT NULL default '',
  `User` char(16) collate utf8_bin NOT NULL default '',
  `Routine_name` char(64) collate utf8_bin NOT NULL default '',
  `Routine_type` enum('FUNCTION','PROCEDURE') collate utf8_bin NOT NULL,
  `Grantor` char(77) collate utf8_bin NOT NULL default '',
  `Proc_priv` set('Execute','Alter Routine','Grant') character set utf8 NOT NULL default '',
  `Timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`Host`,`Db`,`User`,`Routine_name`,`Routine_type`),
  KEY `Grantor` (`Grantor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Procedure privileges';

--
-- Dumping data for table `mysql`.`procs_priv`
--

/*!40000 ALTER TABLE `procs_priv` DISABLE KEYS */;
/*!40000 ALTER TABLE `procs_priv` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`tables_priv`
--

DROP TABLE IF EXISTS `tables_priv`;
CREATE TABLE `tables_priv` (
  `Host` char(60) collate utf8_bin NOT NULL default '',
  `Db` char(64) collate utf8_bin NOT NULL default '',
  `User` char(16) collate utf8_bin NOT NULL default '',
  `Table_name` char(64) collate utf8_bin NOT NULL default '',
  `Grantor` char(77) collate utf8_bin NOT NULL default '',
  `Timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `Table_priv` set('Select','Insert','Update','Delete','Create','Drop','Grant','References','Index','Alter','Create View','Show view') character set utf8 NOT NULL default '',
  `Column_priv` set('Select','Insert','Update','References') character set utf8 NOT NULL default '',
  PRIMARY KEY  (`Host`,`Db`,`User`,`Table_name`),
  KEY `Grantor` (`Grantor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table privileges';

--
-- Dumping data for table `mysql`.`tables_priv`
--

/*!40000 ALTER TABLE `tables_priv` DISABLE KEYS */;
/*!40000 ALTER TABLE `tables_priv` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`time_zone`
--

DROP TABLE IF EXISTS `time_zone`;
CREATE TABLE `time_zone` (
  `Time_zone_id` int(10) unsigned NOT NULL auto_increment,
  `Use_leap_seconds` enum('Y','N') NOT NULL default 'N',
  PRIMARY KEY  (`Time_zone_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Time zones';

--
-- Dumping data for table `mysql`.`time_zone`
--

/*!40000 ALTER TABLE `time_zone` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_zone` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`time_zone_leap_second`
--

DROP TABLE IF EXISTS `time_zone_leap_second`;
CREATE TABLE `time_zone_leap_second` (
  `Transition_time` bigint(20) NOT NULL,
  `Correction` int(11) NOT NULL,
  PRIMARY KEY  (`Transition_time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Leap seconds information for time zones';

--
-- Dumping data for table `mysql`.`time_zone_leap_second`
--

/*!40000 ALTER TABLE `time_zone_leap_second` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_zone_leap_second` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`time_zone_name`
--

DROP TABLE IF EXISTS `time_zone_name`;
CREATE TABLE `time_zone_name` (
  `Name` char(64) NOT NULL,
  `Time_zone_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Time zone names';

--
-- Dumping data for table `mysql`.`time_zone_name`
--

/*!40000 ALTER TABLE `time_zone_name` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_zone_name` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`time_zone_transition`
--

DROP TABLE IF EXISTS `time_zone_transition`;
CREATE TABLE `time_zone_transition` (
  `Time_zone_id` int(10) unsigned NOT NULL,
  `Transition_time` bigint(20) NOT NULL,
  `Transition_type_id` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`Time_zone_id`,`Transition_time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Time zone transitions';

--
-- Dumping data for table `mysql`.`time_zone_transition`
--

/*!40000 ALTER TABLE `time_zone_transition` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_zone_transition` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`time_zone_transition_type`
--

DROP TABLE IF EXISTS `time_zone_transition_type`;
CREATE TABLE `time_zone_transition_type` (
  `Time_zone_id` int(10) unsigned NOT NULL,
  `Transition_type_id` int(10) unsigned NOT NULL,
  `Offset` int(11) NOT NULL default '0',
  `Is_DST` tinyint(3) unsigned NOT NULL default '0',
  `Abbreviation` char(8) NOT NULL default '',
  PRIMARY KEY  (`Time_zone_id`,`Transition_type_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='Time zone transition types';

--
-- Dumping data for table `mysql`.`time_zone_transition_type`
--

/*!40000 ALTER TABLE `time_zone_transition_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_zone_transition_type` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `Host` char(60) collate utf8_bin NOT NULL default '',
  `User` char(16) collate utf8_bin NOT NULL default '',
  `Password` char(41) character set latin1 collate latin1_bin NOT NULL default '',
  `Select_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Insert_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Update_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Delete_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Drop_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Reload_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Shutdown_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Process_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `File_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Grant_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `References_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Index_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Alter_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Show_db_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Super_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_tmp_table_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Lock_tables_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Execute_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Repl_slave_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Repl_client_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_view_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Show_view_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_routine_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Alter_routine_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `Create_user_priv` enum('N','Y') character set utf8 NOT NULL default 'N',
  `ssl_type` enum('','ANY','X509','SPECIFIED') character set utf8 NOT NULL default '',
  `ssl_cipher` blob NOT NULL,
  `x509_issuer` blob NOT NULL,
  `x509_subject` blob NOT NULL,
  `max_questions` int(11) unsigned NOT NULL default '0',
  `max_updates` int(11) unsigned NOT NULL default '0',
  `max_connections` int(11) unsigned NOT NULL default '0',
  `max_user_connections` int(11) unsigned NOT NULL default '0',
  PRIMARY KEY  (`Host`,`User`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and global privileges';

--
-- Dumping data for table `mysql`.`user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`Host`,`User`,`Password`,`Select_priv`,`Insert_priv`,`Update_priv`,`Delete_priv`,`Create_priv`,`Drop_priv`,`Reload_priv`,`Shutdown_priv`,`Process_priv`,`File_priv`,`Grant_priv`,`References_priv`,`Index_priv`,`Alter_priv`,`Show_db_priv`,`Super_priv`,`Create_tmp_table_priv`,`Lock_tables_priv`,`Execute_priv`,`Repl_slave_priv`,`Repl_client_priv`,`Create_view_priv`,`Show_view_priv`,`Create_routine_priv`,`Alter_routine_priv`,`Create_user_priv`,`ssl_type`,`ssl_cipher`,`x509_issuer`,`x509_subject`,`max_questions`,`max_updates`,`max_connections`,`max_user_connections`) VALUES 
 ('localhost','root','*94BDCEBE19083CE2A1F959FD02F964C7AF4CFC29','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','','','','',0,0,0,0),
 ('%','root','*3E5714C6A20552134890256B82ADB562E83A5B2C','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','Y','N','N','N','N','N','','','','',0,0,0,0),
 ('%','ArchE','*02B1F9BE92E817989975114AFEDE858727C5DE54','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','N','','','','',0,0,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Table structure for table `mysql`.`user_info`
--

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `User` varchar(16) collate utf8_bin NOT NULL,
  `Full_name` varchar(60) collate utf8_bin default NULL,
  `Description` varchar(255) collate utf8_bin default NULL,
  `Email` varchar(80) collate utf8_bin default NULL,
  `Contact_information` text collate utf8_bin,
  `Icon` blob,
  PRIMARY KEY  (`User`),
  KEY `user_info_Full_name` (`Full_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Stores additional user information';

--
-- Dumping data for table `mysql`.`user_info`
--

/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;

--
-- Create schema test
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ test;
USE test;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
