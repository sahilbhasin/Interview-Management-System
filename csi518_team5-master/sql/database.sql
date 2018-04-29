UNLOCK TABLES;

DROP database IF EXISTS `csi518_interview_mgmt`;

DROP user IF EXISTS `team5`;
DROP user IF EXISTS `team5`@`localhost`;
CREATE user `team5`@`localhost` IDENTIFIED BY 'icsi518';

CREATE database `csi518_interview_mgmt`;

GRANT ALL PRIVILEGES ON `csi518_interview_mgmt`.* TO `team5`@`localhost`;


USE `csi518_interview_mgmt`;


CREATE TABLE `jobs` (
  `idjobs` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `salary` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idjobs`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


LOCK TABLES `jobs` WRITE;
INSERT INTO `jobs` VALUES (1,'Windows Systems Administrator','Administrator for a larger MS Windows Server Farm',NULL,NULL),(2,'Associate Director, Network Services','Manager of enterprise networking team.',NULL,NULL),(3,'TA','Teaching Assistant, Computer Science',NULL,NULL);
UNLOCK TABLES;


CREATE TABLE `organization` (
  `idorganization` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(45) DEFAULT NULL,
  `contact_name` varchar(45) DEFAULT NULL,
  `contact_email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idorganization`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `organization` WRITE;
INSERT INTO `organization` VALUES (1,'SUNY Albany','University at Albany','(518) 442-5555','Nobody','nobody@albany.edu');
UNLOCK TABLES;


CREATE TABLE `roles` (
  `idroles` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idroles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `roles` WRITE;
INSERT INTO `roles` VALUES (1,'user'),(2,'recruiter'),(3,'manager'),(4,'admin');
UNLOCK TABLES;

CREATE TABLE `skills` (
  `idskills` int(11) NOT NULL AUTO_INCREMENT,
  `skillname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idskills`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;




CREATE TABLE `users` (
  `idusers` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `password` varchar(250) NOT NULL,
  PRIMARY KEY (`idusers`),
  KEY `role_idx` (`role`),
  CONSTRAINT `role` FOREIGN KEY (`role`) REFERENCES `roles` (`idroles`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'admin',NULL,NULL,NULL,4,'password'),(2,'ed9107','Eric','Dombroski','edombroski@albany.edu',1,'password'),(3,'zc745369','Zane','Coonrad','zcoonrad@albany.edu',1,'password'),(4,'sg437253','Sparsh','Garg','sgarg2@albany.edu',1,'password'),(5,'sb963339','Sahil','Basin','sbhasin@albany.edu',1,'password'),(6,'cw154497','Chunpai','Wang','cw154497@albany.edu',1,'password'),(7,'eu664771','Emre','Ugurlu','eugurlu@albany.edu',1,'password'),(8,'dp819361','Devansh','Parikh','dparikh@albany.edu',1,'password'),(9,'mgrtest','Manager','Test','mgrtest@albany.edu',3,'password'),(10,'recruitertest','Recruiter','Test','rectest@albany.edu',2,'password');
UNLOCK TABLES;


CREATE TABLE `interviews` (
  `idinterviews` int(11) NOT NULL AUTO_INCREMENT,
  `recruiterid` int(11) NOT NULL,
  `candidateid` int(11) NOT NULL,
  `round` varchar(45) NOT NULL DEFAULT '1',
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `score` varchar(45) DEFAULT NULL,
  `feedback` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idinterviews`),
  KEY `recruiterid_idx` (`recruiterid`),
  KEY `candidateid_idx` (`candidateid`),
  CONSTRAINT `candidateid` FOREIGN KEY (`candidateid`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `recruiterid` FOREIGN KEY (`recruiterid`) REFERENCES `users` (`idusers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `resumes` (
  `idresumes` int(11) NOT NULL AUTO_INCREMENT,
  `resume` mediumblob,
  `filename` varchar(255) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `filetype` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idresumes`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `applications` (
  `idapplications` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `job` int(11) DEFAULT NULL,
  `resume` int(11) DEFAULT NULL,
  PRIMARY KEY (`idapplications`),
  KEY `userid_idx` (`user`,`job`),
  KEY `jobid_idx` (`job`),
  KEY `resume_idx` (`resume`),
  CONSTRAINT `job` FOREIGN KEY (`job`) REFERENCES `jobs` (`idjobs`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resume` FOREIGN KEY (`resume`) REFERENCES `resumes` (`idresumes`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user` FOREIGN KEY (`user`) REFERENCES `users` (`idusers`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


CREATE TABLE `applications_skills` (
  `idapplications_skills` int(11) NOT NULL AUTO_INCREMENT,
  `application` int(11) DEFAULT NULL,
  `skill` int(11) DEFAULT NULL,
  PRIMARY KEY (`idapplications_skills`),
  KEY `application_idx` (`application`),
  KEY `skills_idx` (`skill`),
  CONSTRAINT `application` FOREIGN KEY (`application`) REFERENCES `applications` (`idapplications`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `skills` FOREIGN KEY (`skill`) REFERENCES `skills` (`idskills`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

