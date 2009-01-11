-- DHU Online Judge database script.
--
-- ------------------------------------------------------
-- Server version	5.0.67-0ubuntu6

--
-- Create schema dhuoj
--

CREATE DATABASE IF NOT EXISTS dhuoj;
USE dhuoj;

--
-- Definition of table `dhuoj`.`problem`
--

DROP TABLE IF EXISTS `dhuoj`.`problem`;
CREATE TABLE  `dhuoj`.`problem` (
  `problem_id` int(11) NOT NULL default '1000',
  `source` varchar(100) default NULL,
  `title` varchar(200) NOT NULL default '',
  `problem_path` varchar(255) default NULL,
  `input_path` varchar(255) default NULL,
  `output_path` varchar(255) default NULL,
  `stdcode_path` varchar(255) default NULL,
  `create_date` datetime default NULL,
  `time_limit` int(11) NOT NULL default '0',
  `case_time_limit` int(11) NOT NULL default '0',
  `memory_limit` int(11) NOT NULL default '0',
  `defunct` tinyint(4) NOT NULL default '0',
  `contest_id` int(11) default NULL,
  `accepted` int(11) default '0',
  `submit` int(11) default '0',
  `error` int(11) default '0',
  `difficulty` tinyint(4) NOT NULL default '0',
  `submit_user` int(11) default '0',
  `solved_user` int(11) default '0',
  PRIMARY KEY  (`problem_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Definition of table `dhuoj`.`contest`
--

DROP TABLE IF EXISTS `dhuoj`.`contest`;
CREATE TABLE  `dhuoj`.`contest` (
  `contest_id` int(11) NOT NULL default '0',
  `title` varchar(255) default NULL,
  `start_time` datetime default NULL,
  `end_time` datetime default NULL,
  `defunct` tinyint(4) NOT NULL default '0',
  `description` text,
  `private` tinyint(4) NOT NULL default '0',
  PRIMARY KEY  (`contest_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Definition of table `dhuoj`.`solution`
--

DROP TABLE IF EXISTS `dhuoj`.`solution`;
CREATE TABLE  `dhuoj`.`solution` (
  `solution_id` int(11) NOT NULL auto_increment,
  `problem_id` int(11) NOT NULL default '0',
  `user_id` varchar(20) NOT NULL default '',
  `runtime` int(11) NOT NULL default '0',
  `memory` int(11) NOT NULL default '0',
  `submit_date` datetime NOT NULL default '0000-00-00 00:00:00',
  `result` smallint(6) NOT NULL default '0',
  `language` tinyint(4) NOT NULL default '0',
  `contest_id` int(11) NOT NULL default '0',
  `valid` tinyint(4) NOT NULL default '1',
  `code_length` int(11) NOT NULL default '0',
  PRIMARY KEY  (`solution_id`),
  KEY `uid` (`user_id`),
  KEY `pid` (`problem_id`),
  KEY `res` (`result`),
  KEY `cid` (`contest_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Definition of table `dhuoj`.`source_code`
--

DROP TABLE IF EXISTS `dhuoj`.`source_code`;
CREATE TABLE  `dhuoj`.`source_code` (
  `solution_id` int(11) NOT NULL default '0',
  `source` text,
  PRIMARY KEY  (`solution_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Definition of table `dhuoj`.`users`
--

DROP TABLE IF EXISTS `dhuoj`.`users`;
CREATE TABLE  `dhuoj`.`users` (
  `user_id` varchar(20) NOT NULL default '',
  `password` varchar(50) NOT NULL default '',
  `email` varchar(100) default NULL,
  `nick` varchar(100) NOT NULL default '',
  `school` varchar(100) NOT NULL default '',
  `submit` int(11) default '0',
  `solved` int(11) default '0',
  `defunct` tinyint(4) NOT NULL default '0',
  `ip` varchar(20) NOT NULL default '',
  `accesstime` datetime default NULL,
  `language` int(11) NOT NULL default '1',
  `reg_time` datetime default NULL,
  `role` int(11) NOT NULL default '0',
  PRIMARY KEY  (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

