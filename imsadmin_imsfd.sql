-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 15, 2025 at 09:14 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `imsadmin_imsfd`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`category_id`, `category_name`) VALUES
(6, 'Electronics'),
(7, 'Furniture'),
(8, 'Clothing'),
(9, 'Toys'),
(10, 'Books'),
(11, 'Sports'),
(12, 'Home Appliances'),
(13, 'Beauty & Health'),
(14, 'Food & Beverages'),
(15, 'Automotive'),
(16, 'Jewelry'),
(17, 'Music'),
(18, 'Pet Supplies'),
(19, 'Gardening'),
(20, 'Office Supplies'),
(21, 'Tools & Hardware'),
(22, 'Baby Products'),
(23, 'Stationery'),
(24, 'Art Supplies'),
(25, 'Shoes');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `quantity_in_stock` int(11) DEFAULT 0,
  `reorder_level` int(11) DEFAULT 0,
  `status` bit(1) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `name`, `description`, `price`, `quantity_in_stock`, `reorder_level`, `status`, `category`) VALUES
(8, 'Smartphone', 'Latest model of Smartphone', 300.00, 12, 10, b'1', 'Electronics'),
(9, 'Laptop', 'High-performance laptop', 999.99, 30, 5, b'1', 'Electronics'),
(10, 'Sofa', 'Comfortable 3-seater sofa', 499.99, 15, 3, b'1', 'Furniture'),
(11, 'T-Shirt', 'Cotton T-shirt in various colors', 19.99, 99, 20, b'1', 'Clothing'),
(12, 'Ball', 'Durable soccer ball', 12.99, 200, 50, b'1', 'Sports'),
(13, 'Headphones', 'Noise-canceling headphones', 79.99, 60, 10, b'1', 'Electronics'),
(14, 'Dining Table', 'Wooden dining table for 6', 299.99, 20, 5, b'1', 'Furniture'),
(15, 'Jacket', 'Winter jacket with fur lining', 89.99, 80, 20, b'1', 'Clothing'),
(16, 'Lego Set', '1000-piece Lego set', 49.99, 150, 30, b'1', 'Toys'),
(17, 'Novel', 'Bestselling fiction novel', 15.99, 300, 60, b'1', 'Books'),
(18, 'Tennis Racket', 'Professional tennis racket', 129.99, 50, 15, b'1', 'Sports'),
(19, 'Blender', 'High-speed blender for smoothies', 99.99, 70, 10, b'1', 'Home Appliances'),
(20, 'Perfume', 'Premium fragrance for men', 49.99, 120, 30, b'1', 'Beauty & Health'),
(21, 'Coffee Mug', 'Ceramic coffee mug', 9.99, 200, 50, b'1', 'Home Appliances'),
(22, 'Car Seat Cover', 'Premium car seat cover set', 59.99, 60, 10, b'1', 'Automotive'),
(23, 'Necklace', 'Gold-plated necklace', 199.99, 40, 10, b'1', 'Jewelry'),
(24, 'Guitar', 'Electric guitar with accessories', 249.99, 20, 5, b'1', 'Music'),
(25, 'Dog Leash', 'Durable dog leash', 14.99, 150, 30, b'1', 'Pet Supplies'),
(26, 'Shovels', 'Heavy-duty shovel for gardening', 19.99, 80, 20, b'1', 'Gardening'),
(27, 'Office Chair', 'Ergonomic office chair', 139.99, 90, 20, b'0', 'Office Supplies');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE `suppliers` (
  `supplier_id` int(11) NOT NULL,
  `supplier_name` varchar(255) NOT NULL,
  `contact_no` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `supplier_company_name` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`supplier_id`, `supplier_name`, `contact_no`, `email`, `supplier_company_name`, `status`) VALUES
(7, 'John Doe', '123-456-7890', 'john@example.com', 'Doe Enterprises', b'1'),
(8, 'Jane Smith', '098-765-4321', 'jane@example.com', 'Smith Co.', b'1'),
(9, 'Alice Green', '456-123-7890', 'alice@example.com', 'Green Goods', b'1'),
(10, 'Bob White', '321-654-9870', 'bob@example.com', 'White Wholesale', b'1'),
(11, 'Charlie Brown', '555-123-4567', 'charlie@example.com', 'Brown Industries', b'1'),
(12, 'Daisy Lee', '678-543-2109', 'daisy@example.com', 'Lee Distribution', b'1'),
(13, 'Ethan King', '123-555-9870', 'ethan@example.com', 'King Corporation', b'1'),
(14, 'Fiona Wong', '789-456-1230', 'fiona@example.com', 'Wong Solutions', b'1'),
(15, 'George Miller', '234-678-3456', 'george@example.com', 'Miller Enterprises', b'1'),
(16, 'Hannah White', '345-789-4561', 'hannah@example.com', 'White Supplies', b'1'),
(17, 'Ivy Black', '456-890-5672', 'ivy@example.com', 'Black Ventures', b'1'),
(18, 'Jack Wilson', '567-901-6783', 'jack@example.com', 'Wilson Supplies', b'1'),
(19, 'Kathy Taylor', '678-234-7894', 'kathy@example.com', 'Taylor Products', b'1'),
(20, 'Liam Harris', '789-345-8905', 'liam@example.com', 'Harris Co.', b'1'),
(21, 'Mia Walker', '890-456-9016', 'mia@example.com', 'Walker Trading', b'1'),
(22, 'Noah Scott', '901-567-0127', 'noah@example.com', 'Scott Manufacturing', b'1'),
(23, 'Olivia Adams', '234-678-1234', 'olivia@example.com', 'Adams Supplies', b'1'),
(24, 'Peter Carter', '345-789-2345', 'peter@example.com', 'Carter Group', b'1'),
(25, 'Quinn Turner', '456-890-3456', 'quinn@example.com', 'Turner Enterprises', b'1'),
(26, 'Riley Evans', '567-901-4567', 'riley@example.com', 'Evans Distributors', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transaction_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `transaction_type` varchar(50) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `transaction_date` datetime DEFAULT current_timestamp(),
  `reference_no` varchar(255) DEFAULT NULL,
  `handled_by` int(11) DEFAULT NULL,
  `supplier_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transaction_id`, `product_id`, `transaction_type`, `quantity`, `transaction_date`, `reference_no`, `handled_by`, `supplier_name`) VALUES
(1, 8, 'Sale', 2, '2025-02-01 10:00:00', '5dd813e1-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(2, 9, 'Sale', 1, '2025-02-01 11:00:00', '5dd82b5d-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(3, 10, 'Sale', 3, '2025-02-01 12:00:00', '5dd82cba-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(4, 11, 'Sale', 10, '2025-02-01 13:00:00', '5dd82d2e-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(5, 12, 'Sale', 5, '2025-02-01 14:00:00', '5dd82d8c-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(6, 13, 'Sale', 2, '2025-02-01 15:00:00', '5dd82de2-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(7, 14, 'Sale', 1, '2025-02-01 16:00:00', '5dd82e3b-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(8, 15, 'Sale', 4, '2025-02-01 17:00:00', '5dd82e98-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(9, 16, 'Sale', 6, '2025-02-01 18:00:00', '5dd82ef4-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(10, 17, 'Sale', 20, '2025-02-01 19:00:00', '5dd82f46-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(11, 8, 'Sale', 3, '2025-02-02 10:00:00', '5dd82f95-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(12, 9, 'Sale', 2, '2025-02-02 11:00:00', '5dd82fec-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(13, 10, 'Sale', 1, '2025-02-02 12:00:00', '5dd83041-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(14, 11, 'Sale', 5, '2025-02-02 13:00:00', '5dd83091-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(15, 12, 'Sale', 10, '2025-02-02 14:00:00', '5dd830e5-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(16, 13, 'Sale', 1, '2025-02-02 15:00:00', '5dd83137-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(17, 14, 'Sale', 2, '2025-02-02 16:00:00', '5dd83188-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(18, 15, 'Sale', 3, '2025-02-02 17:00:00', '5dd831e2-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(19, 16, 'Sale', 4, '2025-02-02 18:00:00', '5dd83246-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(20, 17, 'Sale', 15, '2025-02-02 19:00:00', '5dd83310-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(21, 8, 'Sale', 1, '2025-02-03 10:00:00', '5dd83365-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(22, 9, 'Sale', 3, '2025-02-03 11:00:00', '5dd833b9-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(23, 10, 'Sale', 2, '2025-02-03 12:00:00', '5dd83483-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(24, 11, 'Sale', 8, '2025-02-03 13:00:00', '5dd88a51-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(25, 12, 'Sale', 6, '2025-02-03 14:00:00', '5dd88b68-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(26, 13, 'Sale', 1, '2025-02-03 15:00:00', '5dd88c1d-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(27, 14, 'Sale', 1, '2025-02-03 16:00:00', '5dd88cc6-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(28, 15, 'Sale', 2, '2025-02-03 17:00:00', '5dd88d5b-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(29, 16, 'Sale', 3, '2025-02-03 18:00:00', '5dd88df2-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(30, 17, 'Sale', 10, '2025-02-03 19:00:00', '5dd88e92-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(31, 8, 'Sale', 4, '2025-02-04 10:00:00', '5dd88f2c-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(32, 9, 'Sale', 1, '2025-02-04 11:00:00', '5dd88fc7-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(33, 10, 'Sale', 2, '2025-02-04 12:00:00', '5dd8906b-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(34, 11, 'Sale', 7, '2025-02-04 13:00:00', '5dd89105-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(35, 12, 'Sale', 8, '2025-02-04 14:00:00', '5dd891a8-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(36, 13, 'Sale', 2, '2025-02-04 15:00:00', '5dd89272-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(37, 14, 'Sale', 1, '2025-02-04 16:00:00', '5dd8930d-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(38, 15, 'Sale', 3, '2025-02-04 17:00:00', '5dd893a9-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(39, 16, 'Sale', 5, '2025-02-04 18:00:00', '5dd89442-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(40, 17, 'Sale', 12, '2025-02-04 19:00:00', '5dd894d3-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(41, 8, 'Sale', 2, '2025-02-05 10:00:00', '5dd8956b-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(42, 9, 'Sale', 1, '2025-02-05 11:00:00', '5dd89603-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(43, 10, 'Sale', 3, '2025-02-05 12:00:00', '5dd89699-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(44, 11, 'Sale', 6, '2025-02-05 13:00:00', '5dd898c1-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(45, 12, 'Sale', 4, '2025-02-05 14:00:00', '5dd89a76-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(46, 13, 'Sale', 1, '2025-02-05 15:00:00', '5dd89af5-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(47, 14, 'Sale', 2, '2025-02-05 16:00:00', '5dd89b56-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(48, 15, 'Sale', 4, '2025-02-05 17:00:00', '5dd89bb1-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(49, 16, 'Sale', 3, '2025-02-05 18:00:00', '5dd89c17-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(50, 17, 'Sale', 8, '2025-02-05 19:00:00', '5dd89c77-ebc1-11ef-9715-40c2ba0d89b2', NULL, NULL),
(51, 8, 'Restock', 50, '2025-02-01 09:00:00', '5dde672d-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'John Doe'),
(52, 9, 'Restock', 20, '2025-02-01 10:00:00', '5dde7ce8-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Jane Smith'),
(53, 10, 'Restock', 10, '2025-02-01 11:00:00', '5dde7dc3-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Alice Green'),
(54, 11, 'Restock', 100, '2025-02-01 12:00:00', '5dde7e38-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Bob White'),
(55, 12, 'Restock', 200, '2025-02-01 13:00:00', '5dde7e9d-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Charlie Brown'),
(56, 13, 'Restock', 30, '2025-02-01 14:00:00', '5dde7efc-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Daisy Lee'),
(57, 14, 'Restock', 15, '2025-02-01 15:00:00', '5dde7f5d-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Ethan King'),
(58, 15, 'Restock', 50, '2025-02-01 16:00:00', '5dde7fbe-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Fiona Wong'),
(59, 16, 'Restock', 100, '2025-02-01 17:00:00', '5dde8021-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'George Miller'),
(60, 17, 'Restock', 300, '2025-02-01 18:00:00', '5dde807e-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Hannah White'),
(61, 8, 'Restock', 30, '2025-02-02 09:00:00', '5dde80dd-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'John Doe'),
(62, 9, 'Restock', 15, '2025-02-02 10:00:00', '5dde813c-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Jane Smith'),
(63, 10, 'Restock', 5, '2025-02-02 11:00:00', '5dde819b-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Alice Green'),
(64, 11, 'Restock', 80, '2025-02-02 12:00:00', '5dde81f8-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Bob White'),
(65, 12, 'Restock', 150, '2025-02-02 13:00:00', '5dde8255-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Charlie Brown'),
(66, 13, 'Restock', 20, '2025-02-02 14:00:00', '5dde82b0-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Daisy Lee'),
(67, 14, 'Restock', 10, '2025-02-02 15:00:00', '5dde830b-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Ethan King'),
(68, 15, 'Restock', 40, '2025-02-02 16:00:00', '5dde8367-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Fiona Wong'),
(69, 16, 'Restock', 90, '2025-02-02 17:00:00', '5dde83ca-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'George Miller'),
(70, 17, 'Restock', 250, '2025-02-02 18:00:00', '5dde8426-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Hannah White'),
(71, 8, 'Restock', 40, '2025-02-03 09:00:00', '5dde8482-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'John Doe'),
(72, 9, 'Restock', 25, '2025-02-03 10:00:00', '5dde84e2-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Jane Smith'),
(73, 10, 'Restock', 8, '2025-02-03 11:00:00', '5dde8541-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Alice Green'),
(74, 11, 'Restock', 90, '2025-02-03 12:00:00', '5dde859d-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Bob White'),
(75, 12, 'Restock', 180, '2025-02-03 13:00:00', '5dde85f8-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Charlie Brown'),
(76, 13, 'Restock', 25, '2025-02-03 14:00:00', '5dde8655-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Daisy Lee'),
(77, 14, 'Restock', 12, '2025-02-03 15:00:00', '5dde86b0-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Ethan King'),
(78, 15, 'Restock', 60, '2025-02-03 16:00:00', '5dde870c-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Fiona Wong'),
(79, 16, 'Restock', 110, '2025-02-03 17:00:00', '5dde876e-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'George Miller'),
(80, 17, 'Restock', 280, '2025-02-03 18:00:00', '5dde87c8-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Hannah White'),
(81, 8, 'Restock', 35, '2025-02-04 09:00:00', '5dde8829-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'John Doe'),
(82, 9, 'Restock', 18, '2025-02-04 10:00:00', '5dde8884-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Jane Smith'),
(83, 10, 'Restock', 7, '2025-02-04 11:00:00', '5dde88e0-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Alice Green'),
(84, 11, 'Restock', 70, '2025-02-04 12:00:00', '5dde893d-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Bob White'),
(85, 12, 'Restock', 160, '2025-02-04 13:00:00', '5dde8998-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Charlie Brown'),
(86, 13, 'Restock', 22, '2025-02-04 14:00:00', '5dde89f2-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Daisy Lee'),
(87, 14, 'Restock', 11, '2025-02-04 15:00:00', '5dde8a50-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Ethan King'),
(88, 15, 'Restock', 45, '2025-02-04 16:00:00', '5dde8aa9-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Fiona Wong'),
(89, 16, 'Restock', 95, '2025-02-04 17:00:00', '5dde8b06-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'George Miller'),
(90, 17, 'Restock', 260, '2025-02-04 18:00:00', '5dde8b62-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Hannah White'),
(91, 8, 'Restock', 45, '2025-02-05 09:00:00', '5dde8bc1-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'John Doe'),
(92, 9, 'Restock', 22, '2025-02-05 10:00:00', '5dde8c1e-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Jane Smith'),
(93, 10, 'Restock', 6, '2025-02-05 11:00:00', '5dde8c79-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Alice Green'),
(94, 11, 'Restock', 85, '2025-02-05 12:00:00', '5dde8cda-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Bob White'),
(95, 12, 'Restock', 170, '2025-02-05 13:00:00', '5dde8d36-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Charlie Brown'),
(96, 13, 'Restock', 28, '2025-02-05 14:00:00', '5dde8d94-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Daisy Lee'),
(97, 14, 'Restock', 13, '2025-02-05 15:00:00', '5dde8df0-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Ethan King'),
(98, 15, 'Restock', 55, '2025-02-05 16:00:00', '5dde8e4f-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Fiona Wong'),
(99, 16, 'Restock', 105, '2025-02-05 17:00:00', '5dde8eab-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'George Miller'),
(100, 17, 'Restock', 290, '2025-02-05 18:00:00', '5dde8f0a-ebc1-11ef-9715-40c2ba0d89b2', NULL, 'Hannah White'),
(101, 8, 'Sale', 5, '2025-01-01 10:00:00', 'REF-123e4567-e89b-12d3-a456-426614174000', 1, NULL),
(102, 9, 'Restock', 10, '2025-01-02 11:15:00', 'REF-223e4567-e89b-12d3-a456-426614174001', 2, 'John Doe'),
(103, 10, 'Sale', 3, '2025-01-03 12:30:00', 'REF-323e4567-e89b-12d3-a456-426614174002', 3, NULL),
(104, 11, 'Restock', 20, '2025-01-04 13:45:00', 'REF-423e4567-e89b-12d3-a456-426614174003', 4, 'Jane Smith'),
(105, 12, 'Sale', 7, '2025-01-05 14:00:00', 'REF-523e4567-e89b-12d3-a456-426614174004', 5, NULL),
(106, 13, 'Restock', 15, '2025-01-06 15:15:00', 'REF-623e4567-e89b-12d3-a456-426614174005', 6, 'Alice Green'),
(107, 14, 'Sale', 2, '2025-01-07 16:30:00', 'REF-723e4567-e89b-12d3-a456-426614174006', 7, NULL),
(108, 15, 'Restock', 25, '2025-01-08 17:45:00', 'REF-823e4567-e89b-12d3-a456-426614174007', 8, 'Bob White'),
(109, 16, 'Sale', 8, '2025-01-09 18:00:00', 'REF-923e4567-e89b-12d3-a456-426614174008', 9, NULL),
(110, 17, 'Restock', 30, '2025-01-10 19:15:00', 'REF-a23e4567-e89b-12d3-a456-426614174009', 10, 'Charlie Brown'),
(111, 18, 'Sale', 4, '2025-01-11 20:30:00', 'REF-b23e4567-e89b-12d3-a456-426614174010', 11, NULL),
(112, 19, 'Restock', 12, '2025-01-12 21:45:00', 'REF-c23e4567-e89b-12d3-a456-426614174011', 12, 'Daisy Lee'),
(113, 20, 'Sale', 6, '2025-01-13 22:00:00', 'REF-d23e4567-e89b-12d3-a456-426614174012', 13, NULL),
(114, 21, 'Restock', 18, '2025-01-14 23:15:00', 'REF-e23e4567-e89b-12d3-a456-426614174013', 14, 'Ethan King'),
(115, 22, 'Sale', 9, '2025-01-15 00:30:00', 'REF-f23e4567-e89b-12d3-a456-426614174014', 15, NULL),
(116, 23, 'Restock', 22, '2025-01-16 01:45:00', 'REF-023e4567-e89b-12d3-a456-426614174015', 16, 'Fiona Wong'),
(117, 24, 'Sale', 1, '2025-01-17 02:00:00', 'REF-123e4567-e89b-12d3-a456-426614174016', 17, NULL),
(118, 25, 'Restock', 14, '2025-01-18 03:15:00', 'REF-223e4567-e89b-12d3-a456-426614174017', 18, 'George Miller'),
(119, 26, 'Sale', 10, '2025-01-19 04:30:00', 'REF-323e4567-e89b-12d3-a456-426614174018', 19, NULL),
(120, 27, 'Restock', 28, '2025-01-20 05:45:00', 'REF-423e4567-e89b-12d3-a456-426614174019', 20, 'Hannah White'),
(121, 8, 'Sale', 3, '2025-01-21 06:00:00', 'REF-523e4567-e89b-12d3-a456-426614174020', 1, NULL),
(122, 9, 'Restock', 16, '2025-01-22 07:15:00', 'REF-623e4567-e89b-12d3-a456-426614174021', 2, 'Ivy Black'),
(123, 10, 'Sale', 5, '2025-01-23 08:30:00', 'REF-723e4567-e89b-12d3-a456-426614174022', 3, NULL),
(124, 11, 'Restock', 19, '2025-01-24 09:45:00', 'REF-823e4567-e89b-12d3-a456-426614174023', 4, 'Jack Wilson'),
(125, 12, 'Sale', 7, '2025-01-25 10:00:00', 'REF-923e4567-e89b-12d3-a456-426614174024', 5, NULL),
(126, 13, 'Restock', 21, '2025-01-26 11:15:00', 'REF-a23e4567-e89b-12d3-a456-426614174025', 6, 'Kathy Taylor'),
(127, 14, 'Sale', 2, '2025-01-27 12:30:00', 'REF-b23e4567-e89b-12d3-a456-426614174026', 7, NULL),
(128, 15, 'Restock', 24, '2025-01-28 13:45:00', 'REF-c23e4567-e89b-12d3-a456-426614174027', 8, 'Liam Harris'),
(129, 16, 'Sale', 8, '2025-01-29 14:00:00', 'REF-d23e4567-e89b-12d3-a456-426614174028', 9, NULL),
(130, 17, 'Restock', 17, '2025-01-30 15:15:00', 'REF-e23e4567-e89b-12d3-a456-426614174029', 10, 'Mia Walker'),
(131, 18, 'Sale', 4, '2025-01-31 16:30:00', 'REF-f23e4567-e89b-12d3-a456-426614174030', 11, NULL),
(132, 19, 'Restock', 20, '2025-02-01 17:45:00', 'REF-023e4567-e89b-12d3-a456-426614174031', 12, 'Noah Scott'),
(133, 20, 'Sale', 6, '2025-02-02 18:00:00', 'REF-123e4567-e89b-12d3-a456-426614174032', 13, NULL),
(134, 21, 'Restock', 23, '2025-02-03 19:15:00', 'REF-223e4567-e89b-12d3-a456-426614174033', 14, 'Olivia Adams'),
(135, 22, 'Sale', 9, '2025-02-04 20:30:00', 'REF-323e4567-e89b-12d3-a456-426614174034', 15, NULL),
(136, 23, 'Restock', 26, '2025-02-05 21:45:00', 'REF-423e4567-e89b-12d3-a456-426614174035', 16, 'Peter Carter'),
(137, 24, 'Sale', 1, '2025-02-06 22:00:00', 'REF-523e4567-e89b-12d3-a456-426614174036', 17, NULL),
(138, 25, 'Restock', 15, '2025-02-07 23:15:00', 'REF-623e4567-e89b-12d3-a456-426614174037', 18, 'Quinn Turner'),
(139, 26, 'Sale', 10, '2025-02-08 00:30:00', 'REF-723e4567-e89b-12d3-a456-426614174038', 19, NULL),
(140, 27, 'Restock', 29, '2025-02-09 01:45:00', 'REF-823e4567-e89b-12d3-a456-426614174039', 20, 'Riley Evans'),
(141, 8, 'Sale', 3, '2025-02-10 02:00:00', 'REF-923e4567-e89b-12d3-a456-426614174040', 1, NULL),
(142, 9, 'Restock', 18, '2025-02-11 03:15:00', 'REF-a23e4567-e89b-12d3-a456-426614174041', 2, 'John Doe'),
(143, 10, 'Sale', 5, '2025-02-12 04:30:00', 'REF-b23e4567-e89b-12d3-a456-426614174042', 3, NULL),
(144, 11, 'Restock', 20, '2025-02-13 05:45:00', 'REF-c23e4567-e89b-12d3-a456-426614174043', 4, 'Jane Smith'),
(145, 12, 'Sale', 7, '2025-02-14 06:00:00', 'REF-d23e4567-e89b-12d3-a456-426614174044', 5, NULL),
(146, 13, 'Restock', 22, '2025-02-15 07:15:00', 'REF-e23e4567-e89b-12d3-a456-426614174045', 6, 'Alice Green'),
(147, 14, 'Sale', 2, '2025-02-16 08:30:00', 'REF-f23e4567-e89b-12d3-a456-426614174046', 7, NULL),
(148, 15, 'Restock', 25, '2025-02-17 09:45:00', 'REF-023e4567-e89b-12d3-a456-426614174047', 8, 'Bob White'),
(149, 16, 'Sale', 8, '2025-02-18 10:00:00', 'REF-123e4567-e89b-12d3-a456-426614174048', 9, NULL),
(150, 17, 'Restock', 19, '2025-02-19 11:15:00', 'REF-223e4567-e89b-12d3-a456-426614174049', 10, 'Charlie Brown'),
(151, 18, 'Sale', 4, '2025-02-20 12:30:00', 'REF-323e4567-e89b-12d3-a456-426614174050', 11, NULL),
(152, 19, 'Restock', 21, '2025-02-21 13:45:00', 'REF-423e4567-e89b-12d3-a456-426614174051', 12, 'Daisy Lee'),
(153, 20, 'Sale', 6, '2025-02-22 14:00:00', 'REF-523e4567-e89b-12d3-a456-426614174052', 13, NULL),
(154, 21, 'Restock', 24, '2025-02-23 15:15:00', 'REF-623e4567-e89b-12d3-a456-426614174053', 14, 'Ethan King'),
(155, 22, 'Sale', 9, '2025-02-24 16:30:00', 'REF-723e4567-e89b-12d3-a456-426614174054', 15, NULL),
(156, 23, 'Restock', 27, '2025-02-25 17:45:00', 'REF-823e4567-e89b-12d3-a456-426614174055', 16, 'Fiona Wong'),
(157, 24, 'Sale', 1, '2025-02-26 18:00:00', 'REF-923e4567-e89b-12d3-a456-426614174056', 17, NULL),
(158, 25, 'Restock', 16, '2025-02-27 19:15:00', 'REF-a23e4567-e89b-12d3-a456-426614174057', 18, 'George Miller'),
(159, 26, 'Sale', 10, '2025-02-28 20:30:00', 'REF-b23e4567-e89b-12d3-a456-426614174058', 19, NULL),
(160, 27, 'Restock', 30, '2025-03-01 21:45:00', 'REF-c23e4567-e89b-12d3-a456-426614174059', 20, 'Hannah White');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) DEFAULT NULL,
  `full_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `role`, `full_name`) VALUES
(1, 'admin', 'adminpass', 'Admin', 'Administrator'),
(2, 'sales1', 'salespass1', 'Sales', 'Sales Person 1'),
(3, 'sales2', 'salespass2', 'Sales', 'Sales Person 2'),
(4, 'manager1', 'managerpass1', 'Manager', 'Manager 1'),
(5, 'manager2', 'managerpass2', 'Manager', 'Manager 2'),
(6, 'clerk1', 'clerkpass1', 'Clerk', 'Clerk 1'),
(7, 'clerk2', 'clerkpass2', 'Clerk', 'Clerk 2'),
(8, 'stock1', 'stockpass1', 'Stock', 'Stock Manager 1'),
(9, 'stock2', 'stockpass2', 'Stock', 'Stock Manager 2'),
(10, 'user1', 'userpass1', 'User', 'User 1'),
(11, 'user2', 'userpass2', 'User', 'User 2'),
(12, 'user3', 'userpass3', 'User', 'User 3'),
(13, 'user4', 'userpass4', 'User', 'User 4'),
(14, 'user5', 'userpass5', 'User', 'User 5'),
(15, 'user6', 'userpass6', 'User', 'User 6'),
(16, 'user7', 'userpass7', 'User', 'User 7'),
(17, 'user8', 'userpass8', 'User', 'User 8'),
(18, 'user9', 'userpass9', 'User', 'User 9'),
(19, 'user10', 'userpass10', 'User', 'User 10'),
(20, 'user11', 'userpass11', 'User', 'User 11');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`category_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supplier_id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `product_id` (`product_id`),
  ADD KEY `handled_by` (`handled_by`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supplier_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=161;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`handled_by`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
