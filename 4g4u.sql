-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2017 at 04:46 PM
-- Server version: 5.6.25
-- PHP Version: 5.5.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `4g4u`
--

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE IF NOT EXISTS `feedback` (
  `id` int(11) NOT NULL,
  `ticket_id` int(11) DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  `feedback_added_at` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `ticket_id`, `stars`, `comments`, `feedback_added_at`) VALUES
(0, 5, 5, 'Awesome!!', 1498471321),
(1, 2, 3, 'Hmm.. not bad!', 1498472468);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE IF NOT EXISTS `tickets` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `subcat` varchar(50) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  `last_modified` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`id`, `user_id`, `title`, `department`, `category`, `subcat`, `description`, `status`, `created_at`, `last_modified`, `email`, `phone`) VALUES
(0, 0, 'Test', 'Billing', 'Equipment Issue', 'Damaged Equipment', 'My descr', 'open', NULL, NULL, NULL, NULL),
(1, 1, 'Another Test', 'Billing', 'Equipment Issue', 'Damaged Equipment', 'My descrwww', 'closed', NULL, NULL, NULL, NULL),
(2, 1, 'My Ticket', 'Billing', 'Equipment Issue', 'Damaged Equipment', 'Microsoft', 'closed', 1498443029, 1498472468, 'arjun2504@gmail.com', ''),
(3, 0, 'Another TT', 'Billing', 'Equipment Issue', 'Damaged Equipment', 'MacBook pro', 'open', 1498448288, 1498448288, 'arjun2504@gmail.com', ''),
(4, 1, 'New Ticket', 'Infrastructure', 'Equipment Issue', 'Damaged Equipment', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin ullamcorper libero ut gravida sollicitudin. Suspendisse potenti. Donec tellus tellus, egestas quis vehicula sed, gravida nec enim.', 'awaiting', 1498454714, 1498454714, 'arjun2504@gmail.com', '9443676221'),
(5, 1, 'My Issue', 'Billing', 'Equipment Issue', 'Damaged Equipment', 'Detailed information is here!', 'closed', 1498466792, 1498471321, 'arjun250@gmail.com', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` char(32) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `is_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `email`, `password`, `phone`, `is_admin`) VALUES
(0, 'Arjun', 'RR', 'arjun2504@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', '9443676221', 1),
(1, 'Arjun', 'RR', 'arjun250@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, 0),
(2, 'Arjun', 'RR', 'arjun25@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ticket_id` (`ticket_id`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
