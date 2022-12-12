DROP TABLE IF EXISTS `purchases`;
DROP TABLE IF EXISTS `order_books`;
DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `publishers`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `publishers` (
	`name` varchar(45) PRIMARY KEY,
    `address` varchar(45),
    `email` varchar(45) UNIQUE,
    `phone_number` varchar(10),
    `banking_account` varchar(12) UNIQUE NOT NULL
);

CREATE TABLE `users` (
	`username` varchar(20) PRIMARY KEY,
    `password` varchar(255) NOT NULL,
    `role` varchar(10) NOT NULL
);
            
CREATE TABLE `orders` (
	`order_number` int PRIMARY KEY AUTO_INCREMENT,
    `billing_info` varchar(45) NOT NULL,
    `shipping_info` varchar(45) NOT NULL,
    `date` date NOT NULL,
    `user` varchar(20) NOT NULL,
    FOREIGN KEY (`user`) REFERENCES users(`username`)
);

CREATE TABLE `books` (
  `name` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `ISBN` varchar(13) PRIMARY KEY,
  `genre` varchar(20),
  `num_pages` int,
  `quantity` int DEFAULT 0,
  `price` numeric(5,2) NOT NULL,
  `purchase_price` numeric(5,2) NOT NULL,
  `publisher` varchar(45) NOT NULL,
  `sale_percentage` numeric(3,3),
  FOREIGN KEY (`publisher`) REFERENCES publishers(`name`)
);
            
CREATE TABLE `order_books` (
	`order_number` int,
    `ISBN` varchar(13),
    PRIMARY KEY (`order_number`, `ISBN`),
    FOREIGN KEY (`order_number`) REFERENCES orders(`order_number`),
    FOREIGN KEY (`ISBN`) REFERENCES books(`ISBN`)
);

CREATE TABLE `purchases` (
	`date` date,
    `book_ISBN` varchar(13),
    `quantity` int NOT NULL,
    PRIMARY KEY (`date`, `book_ISBN`),
    FOREIGN KEY (`book_ISBN`) REFERENCES books(`ISBN`)
);