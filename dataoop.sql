-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 01, 2021 lúc 03:20 PM
-- Phiên bản máy phục vụ: 10.4.17-MariaDB
-- Phiên bản PHP: 8.0.2

CREATE DATABASE dataoop;
USE dataoop;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dataoop`
--
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `Id` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Gia` int(11) NOT NULL,
  `ThoiGian` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`Id`, `UserId`, `Gia`, `ThoiGian`) VALUES
(2, 3, 83579000, '2021-05-01'),
(3, 3, 53500000, '2021-05-01'),
(4, 3, 35970000, '2021-05-01'),
(5, 3, 7990000, '2021-05-01');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `device`
--

CREATE TABLE `device` (
  `id` int(11) NOT NULL,
  `Ten` varchar(255) NOT NULL,
  `HangSanXuat` varchar(255) NOT NULL,
  `Model` varchar(50) NOT NULL,
  `KichThuoc` float DEFAULT NULL,
  `ThoiLuongPin` int(11) DEFAULT NULL,
  `DoPhanGiaiCamera` float DEFAULT NULL,
  `CPU` varchar(50) DEFAULT NULL,
  `RAM` int(3) DEFAULT NULL,
  `OCUNG` varchar(50) DEFAULT NULL,
  `Gia` int(11) NOT NULL,
  `CONLAI` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `device`
--

INSERT INTO `device` (`id`, `Ten`, `HangSanXuat`, `Model`, `KichThuoc`, `ThoiLuongPin`, `DoPhanGiaiCamera`, `CPU`, `RAM`, `OCUNG`, `Gia`, `CONLAI`) VALUES
(1, 'ACER SWIFT 3', 'ACER', 'SF315', NULL, NULL, NULL, 'COREI5 8th', 8, 'SSD 256', 18999000, 16),
(2, 'IPHONE 6 PLUS', 'APPLE', '6 Plus', 7.5, 2915, 8, NULL, NULL, NULL, 3500000, 24),
(3, 'DELL XPS 13 7390', 'DELL', '7390', NULL, NULL, NULL, '10210U', 8, 'SSD 256', 31800000, 20),
(4, 'VINSMART JOY 4', 'VINSMART', 'JOY 4', 6.53, 5000, 13, NULL, NULL, NULL, 3299000, 30),
(5, 'Iphone X', 'Apple', 'X', 6.5, 5500, 15, NULL, NULL, NULL, 25000000, 13),
(6, 'LENOVO THINKBOOK 15IIL', 'Lenovo', 'Thinkbook 15iil', NULL, NULL, NULL, 'i3 1005G1', 4, 'SSD 512', 11690000, 20),
(7, 'ACER ASPIRE 7 A715', 'ACER', 'A715', NULL, NULL, NULL, '5500U', 8, 'SSD 256', 19990000, 14),
(8, 'SAMSUNG GALAXY M51', 'SAMSUNG', 'M51', 6.7, 7000, 64, NULL, NULL, NULL, 7990000, 7),
(9, 'IPHONE 12 PRO MAX', 'APPLE', '12 PRO MAX', 6.7, 3687, 12, NULL, NULL, NULL, 32290000, 9);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `Ten` varchar(255) NOT NULL,
  `MatKhau` varchar(255) NOT NULL,
  `isAdmin` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `Ten`, `MatKhau`, `isAdmin`) VALUES
(1, 'admin', 'admin', 1),
(2, 'Thai An', '123', 0),
(3, 'oop2021', '2021', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `UserId` (`UserId`);

--
-- Chỉ mục cho bảng `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `device`
--
ALTER TABLE `device`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
