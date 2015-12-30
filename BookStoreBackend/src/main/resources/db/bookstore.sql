-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.45 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for bookstore
DROP DATABASE IF EXISTS `bookstore`;
CREATE DATABASE IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bookstore`;


-- Dumping structure for table bookstore.book
DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `price` float(10,2) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `book_category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Index 2` (`title`,`author`),
  KEY `FK_BOOK_CATEGORY` (`book_category_id`),
  CONSTRAINT `FK_BOOK_CATEGORY` FOREIGN KEY (`book_category_id`) REFERENCES `book_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.book: ~5 rows (approximately)
DELETE FROM `book`;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `title`, `author`, `price`, `publisher`, `book_category_id`) VALUES
	(1, 'Pro Spring', 'Rob Harrop', 30.50, 'Apress', 1),
	(2, 'Object Oriented Perl', 'Damian Conway', 40.00, 'Manning', 2),
	(3, 'Struts in Action', 'Ted Husted', 40.00, 'Manning', 1),
	(4, 'Python in a Nutshell', 'Alex Martelli', 35.00, 'Oreilly', 2),
	(5, 'Python Cookbook', 'Alex Martelli', 35.00, 'Oreilly', 2);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


-- Dumping structure for table bookstore.book_category
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE IF NOT EXISTS `book_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.book_category: ~2 rows (approximately)
DELETE FROM `book_category`;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` (`id`, `name`) VALUES
	(1, 'J2EE'),
	(2, 'Scripting');
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;


-- Dumping structure for table bookstore.city
DROP TABLE IF EXISTS `city`;
CREATE TABLE IF NOT EXISTS `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_city_country` (`country_id`),
  CONSTRAINT `FK_city_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.city: ~6 rows (approximately)
DELETE FROM `city`;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`id`, `name`, `country_id`) VALUES
	(1, 'Sibiu', 1),
	(2, 'Bucuresti', 1),
	(3, 'Cluj', 1),
	(4, 'Munich', 2),
	(5, 'Berlin', 2),
	(6, 'Paris', 3);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;


-- Dumping structure for table bookstore.country
DROP TABLE IF EXISTS `country`;
CREATE TABLE IF NOT EXISTS `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.country: ~4 rows (approximately)
DELETE FROM `country`;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` (`id`, `name`) VALUES
	(1, 'ROMANIA'),
	(2, 'GERMANY'),
	(3, 'FRANCE'),
	(4, 'ENGLAND');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;


-- Dumping structure for table bookstore.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`user_name`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.user: ~2 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `user_name`, `password`) VALUES
	(2, 'admin', 'admin'),
	(3, 'newUser', '123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table bookstore.user_profile
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE IF NOT EXISTS `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  `pin` int(11) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_profile_country` (`country_id`),
  KEY `FK_user_profile_city` (`city_id`),
  KEY `FK_user_profile_user` (`user_id`),
  CONSTRAINT `FK_user_profile_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_profile_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_profile_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore.user_profile: ~1 rows (approximately)
DELETE FROM `user_profile`;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` (`id`, `first_name`, `last_name`, `email_address`, `address`, `city_id`, `country_id`, `pin`, `phone_number`, `user_id`) VALUES
	(1, 'Admin', 'Admin', 'admin@tst.com', 'home', 1, 1, 0, '0744-223294', 2);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;


-- Dumping database structure for bookstore_test
DROP DATABASE IF EXISTS `bookstore_test`;
CREATE DATABASE IF NOT EXISTS `bookstore_test` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bookstore_test`;


-- Dumping structure for table bookstore_test.book
DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `price` float(10,2) NOT NULL,
  `publisher` varchar(50) NOT NULL,
  `book_category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Index 2` (`title`,`author`),
  KEY `FK_BOOK_CATEGORY` (`book_category_id`),
  CONSTRAINT `FK_BOOK_CATEGORY` FOREIGN KEY (`book_category_id`) REFERENCES `book_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore_test.book: ~5 rows (approximately)
DELETE FROM `book`;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`, `title`, `author`, `price`, `publisher`, `book_category_id`) VALUES
	(1, 'Pro Spring', 'Rob Harrop', 30.50, 'Apress', 1),
	(2, 'Object Oriented Perl', 'Damian Conway', 40.00, 'Manning', 2),
	(3, 'Struts in Action', 'Ted Husted', 40.00, 'Manning', 1),
	(4, 'Python in a Nutshell', 'Alex Martelli', 35.00, 'Oreilly', 2),
	(5, 'Python Cookbook', 'Alex Martelli', 35.00, 'Oreilly', 2);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


-- Dumping structure for table bookstore_test.book_category
DROP TABLE IF EXISTS `book_category`;
CREATE TABLE IF NOT EXISTS `book_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore_test.book_category: ~2 rows (approximately)
DELETE FROM `book_category`;
/*!40000 ALTER TABLE `book_category` DISABLE KEYS */;
INSERT INTO `book_category` (`id`, `name`) VALUES
	(1, 'J2EE'),
	(2, 'Scripting');
/*!40000 ALTER TABLE `book_category` ENABLE KEYS */;


-- Dumping structure for table bookstore_test.city
DROP TABLE IF EXISTS `city`;
CREATE TABLE IF NOT EXISTS `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_city_country` (`country_id`),
  CONSTRAINT `FK_city_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore_test.city: ~6 rows (approximately)
DELETE FROM `city`;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`id`, `name`, `country_id`) VALUES
	(1, 'Sibiu', 1),
	(2, 'Bucuresti', 1),
	(3, 'Cluj', 1),
	(4, 'Munich', 2),
	(5, 'Berlin', 2),
	(6, 'Paris', 3);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;


-- Dumping structure for table bookstore_test.country
DROP TABLE IF EXISTS `country`;
CREATE TABLE IF NOT EXISTS `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore_test.country: ~4 rows (approximately)
DELETE FROM `country`;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` (`id`, `name`) VALUES
	(1, 'ROMANIA'),
	(2, 'GERMANY'),
	(3, 'FRANCE'),
	(4, 'ENGLAND');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;


-- Dumping structure for table bookstore_test.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 2` (`user_name`,`password`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore_test.user: ~2 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `user_name`, `password`) VALUES
	(12, 'tst', 'tst'),
	(2, 'user', 'user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table bookstore_test.user_profile
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE IF NOT EXISTS `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `country_id` bigint(20) NOT NULL,
  `pin` int(11) NOT NULL,
  `phone_number` varchar(50) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index 5` (`user_id`),
  KEY `FK_user_profile_country` (`country_id`),
  KEY `FK_user_profile_city` (`city_id`),
  KEY `FK_user_profile_user` (`user_id`),
  CONSTRAINT `FK_user_profile_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_profile_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_user_profile_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- Dumping data for table bookstore_test.user_profile: ~1 rows (approximately)
DELETE FROM `user_profile`;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` (`id`, `first_name`, `last_name`, `email_address`, `address`, `city_id`, `country_id`, `pin`, `phone_number`, `user_id`) VALUES
	(1, 'Tst', 'Lst', 'admin@tst.com', 'Home', 1, 1, 0, '071-2235', 12);
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
