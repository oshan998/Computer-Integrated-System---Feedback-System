-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2021 at 04:37 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cricketfeedbackdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `AdminID` int(11) NOT NULL,
  `UserName` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminID`, `UserName`, `Password`) VALUES
(7187, 'admin', 'admin123'),
(2153, 'test', 'test123');

-- --------------------------------------------------------

--
-- Table structure for table `analytics`
--

CREATE TABLE `analytics` (
  `playerID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `Answer1Count` int(11) NOT NULL,
  `Answer2Count` int(11) NOT NULL,
  `Answer3Count` int(11) NOT NULL,
  `Answer4Count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `analytics`
--

INSERT INTO `analytics` (`playerID`, `QuestionID`, `Answer1Count`, `Answer2Count`, `Answer3Count`, `Answer4Count`) VALUES
(1, 1000, 25, 2, 1, 1),
(1, 1001, 1, 25, 2, 1),
(1, 1002, 1, 2, 23, 3),
(1, 1003, 3, 3, 2, 21),
(1, 1004, 20, 5, 3, 1),
(1, 1005, 2, 22, 4, 1),
(1, 1006, 1, 3, 22, 3),
(2, 1000, 5, 1, 1, 1),
(2, 1001, 1, 5, 1, 1),
(2, 1002, 1, 1, 5, 1),
(2, 1003, 1, 1, 1, 5),
(2, 1004, 5, 1, 1, 1),
(2, 1005, 1, 5, 1, 1),
(2, 1006, 1, 1, 5, 1),
(3, 1000, 2, 1, 1, 1),
(3, 1001, 1, 2, 1, 1),
(3, 1002, 1, 1, 2, 1),
(3, 1003, 1, 1, 1, 2),
(3, 1004, 2, 1, 1, 1),
(3, 1005, 1, 2, 1, 1),
(3, 1006, 1, 1, 2, 1),
(4, 1000, 4, 2, 1, 1),
(4, 1001, 1, 4, 2, 1),
(4, 1002, 1, 1, 5, 1),
(4, 1003, 1, 1, 2, 4),
(4, 1004, 4, 2, 1, 1),
(4, 1005, 1, 4, 2, 1),
(4, 1006, 1, 3, 3, 1),
(5, 1000, 6, 1, 1, 1),
(5, 1001, 1, 6, 1, 1),
(5, 1002, 1, 1, 6, 1),
(5, 1003, 1, 1, 1, 6),
(5, 1004, 6, 1, 1, 1),
(5, 1005, 1, 6, 1, 1),
(5, 1006, 1, 2, 5, 1),
(6, 1000, 5, 1, 1, 1),
(6, 1001, 1, 5, 1, 1),
(6, 1002, 1, 1, 5, 1),
(6, 1003, 1, 1, 1, 5),
(6, 1004, 5, 1, 1, 1),
(6, 1005, 1, 5, 1, 1),
(6, 1006, 1, 1, 5, 1),
(7, 1000, 2, 1, 1, 1),
(7, 1001, 1, 2, 1, 1),
(7, 1002, 1, 1, 2, 1),
(7, 1003, 1, 1, 1, 2),
(7, 1004, 2, 1, 1, 1),
(7, 1005, 1, 2, 1, 1),
(7, 1006, 1, 1, 2, 1),
(8, 1000, 3, 1, 1, 1),
(8, 1001, 1, 3, 1, 1),
(8, 1002, 1, 1, 3, 1),
(8, 1003, 1, 1, 1, 3),
(8, 1004, 3, 1, 1, 1),
(8, 1005, 1, 3, 1, 1),
(8, 1006, 1, 1, 3, 1),
(9, 1000, 2, 1, 1, 1),
(9, 1001, 1, 2, 1, 1),
(9, 1002, 1, 1, 2, 1),
(9, 1003, 1, 1, 1, 2),
(9, 1004, 2, 1, 1, 1),
(9, 1005, 1, 2, 1, 1),
(9, 1006, 1, 1, 2, 1),
(10, 1000, 1, 1, 1, 1),
(10, 1001, 1, 1, 1, 1),
(10, 1002, 1, 1, 1, 1),
(10, 1003, 1, 1, 1, 1),
(10, 1004, 1, 1, 1, 1),
(10, 1005, 1, 1, 1, 1),
(10, 1006, 1, 1, 1, 1),
(11, 1000, 2, 1, 1, 1),
(11, 1001, 1, 2, 1, 1),
(11, 1002, 1, 1, 2, 1),
(11, 1003, 1, 1, 1, 2),
(11, 1004, 2, 1, 1, 1),
(11, 1005, 1, 2, 1, 1),
(11, 1006, 1, 1, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

CREATE TABLE `player` (
  `playerID` int(11) NOT NULL,
  `playerName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `player`
--

INSERT INTO `player` (`playerID`, `playerName`) VALUES
(1, 'Kusal Mendes'),
(2, 'Danushka Gunathilaka'),
(3, 'Niroshan Dickwella'),
(4, 'Dasun Shanaka'),
(5, 'Angelo Mathews'),
(6, 'Dinesh Chandimal'),
(7, 'Lahiru Thirimanne'),
(8, 'Isuru Udana'),
(9, 'Wanindu Hasaranga'),
(10, 'Dimuth Karunarathna'),
(11, 'Pathum Nissanka');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `QuestionID` int(11) NOT NULL,
  `Question` varchar(100) NOT NULL,
  `Answer1` varchar(20) NOT NULL,
  `Answer2` varchar(20) NOT NULL,
  `Answer3` varchar(20) NOT NULL,
  `Answer4` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`QuestionID`, `Question`, `Answer1`, `Answer2`, `Answer3`, `Answer4`) VALUES
(1000, 'How well he completed the 2KM run?', 'Excellent', 'Good', 'Average', 'Bad'),
(1001, 'How well he completed 15 pushups?', 'Excellent', 'Good', 'Average', 'Bad'),
(1002, 'How well he completed the warmup exercises?', 'Excellent', 'Good', 'Average', 'Bad'),
(1003, 'How he looks after the exercises?', 'Excellent', 'Good', 'Average', 'Bad'),
(1004, 'How well he done the stretching exercises?', 'Excellent', 'Good', 'Average', 'Bad'),
(1005, 'How is his diet plan during training period?', 'Excellent', 'Good', 'Average', 'Bad'),
(1006, 'How is his immune in this period?', 'Excellent', 'Good', 'Average', 'Bad');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`AdminID`);

--
-- Indexes for table `analytics`
--
ALTER TABLE `analytics`
  ADD KEY `analytics_ibfk_1` (`QuestionID`),
  ADD KEY `analytics_ibfk_2` (`playerID`);

--
-- Indexes for table `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`playerID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`QuestionID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `analytics`
--
ALTER TABLE `analytics`
  ADD CONSTRAINT `analytics_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `questions` (`QuestionID`),
  ADD CONSTRAINT `analytics_ibfk_2` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
