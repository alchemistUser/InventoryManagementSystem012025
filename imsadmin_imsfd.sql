-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 09, 2025 at 01:22 AM
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
(11, 'T-Shirt', 'Cotton T-shirt in various colors', 19.99, 100, 20, b'1', 'Clothing'),
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
(1, 11, 'Sale', 2, '2025-02-04 02:27:58', 'REF-4bdaa5d7-0c05-41a5-aca9-00e97f1679f9', NULL, NULL),
(2, 8, 'Sale', 2, '2025-02-04 02:29:07', 'REF-be379d36-fb77-4b7d-8a9d-73e7d260ea21', NULL, NULL),
(3, 8, 'Sale', 2, '2025-02-04 02:41:36', 'REF-21a90588-4d8f-4e19-b457-d3022d39123c', NULL, NULL),
(4, 8, 'Sale', 1, '2025-02-04 02:42:37', 'REF-776316f3-f926-4951-91d6-4739f27f4156', NULL, NULL),
(6, 8, 'Sale', 1, '2025-02-04 03:06:42', 'REF-0292acea-2e3e-41d9-8ee5-573bb9b6ba55', NULL, NULL),
(7, 8, 'Sale', 1, '2025-02-04 03:09:55', 'REF-ee60d9dc-4bce-41bc-b511-62f0204e151f', NULL, NULL),
(9, 8, 'Sale', 100, '2025-02-04 03:11:01', 'REF-5bb2003f-c4cc-4e73-a6bc-d17d32659fa9', NULL, NULL),
(10, 8, 'Restock', 13, '2025-02-04 03:11:05', 'REF-53bda42d-40f1-4eda-b197-ebb283d3246d', NULL, 'John Doe'),
(11, 8, 'Sale', 1, '2025-02-04 03:17:17', 'REF-b6507028-7185-4eca-88f8-8556966fa085', NULL, NULL),
(12, 8, 'Restock', 2, '2025-02-04 03:17:29', 'REF-1b9ddcca-da06-41cd-a4bc-06a9a7833cf3', NULL, 'John Doe'),
(13, 8, 'Sale', 1, '2025-02-04 03:18:26', 'REF-ba307ab2-8047-4fbf-bc75-1f142395f8e8', NULL, NULL),
(14, 8, 'Restock', 3, '2025-02-04 03:18:30', 'REF-6b5168b5-0ff5-4dd6-8a66-1d6cf9e1909e', NULL, 'John Doe'),
(15, 8, 'Sale', 1, '2025-02-04 03:19:03', 'REF-79819eab-3728-4caf-8baa-1c05056d8281', NULL, NULL),
(16, 8, 'Restock', 1, '2025-02-04 03:19:40', 'REF-b78b1c15-c27f-4577-80ec-cfdbccf0950d', NULL, 'John Doe'),
(17, 8, 'Sale', 2, '2025-02-04 03:21:06', 'REF-dc7e91b2-122b-4b04-a9fc-5224614c26bc', NULL, NULL),
(18, 8, 'Sale', 1, '2025-02-04 03:21:27', 'REF-8f71dc91-f24c-4fc0-b410-89b2ee3ebe11', NULL, NULL),
(19, 8, 'Sale', 1, '2025-02-04 03:28:47', 'REF-74896b52-5bc2-4fb5-99d6-37d80f7a274a', NULL, NULL),
(20, 8, 'Restock', 1, '2025-02-04 03:28:50', 'REF-a00a91f3-59bf-4482-8deb-66cbe30149a7', NULL, 'John Doe'),
(21, 8, 'Sale', 1, '2025-02-04 03:31:49', 'REF-6a27a54b-9114-4d69-8684-57c8f24600e3', NULL, NULL),
(22, 8, 'Restock', 1, '2025-02-04 03:31:54', 'REF-587e0bc3-4e77-4c9e-a57f-707fc62b60d8', NULL, 'John Doe');

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
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

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
