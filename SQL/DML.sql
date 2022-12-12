INSERT INTO `publishers`
VALUES 		('Tor Books', '120 Broadway, New York, NY', 'torpublicity@tor.com', '8816574430', '572948174823'),
			('Chilton Books', 'New York City', 'chiltonpublishing@chb.com', '8125649900', '784127490454');
INSERT INTO `users`
VALUES		('admin', '$2a$10$YXw6fzz.LINgSbAtBsq8/OYIt7aF3AbjvStMK4E7zXY3t8BNS7SeC', 'ADMIN'),
			('customer', '$2a$10$mv91OStKJm5sgQpZoGXGNeTzQ6AqeK4azlYQS7qLxv2dydFbvcgJK', 'CUSTOMER');
INSERT INTO `books`
VALUES		('The Eye of the World', 'Robert Jordan', 9781250832368, 'Fantasy', 782, 1, 28.70, 20.00, 'Tor Books', 0.1),
			('Dune', 'Franmk Herbert', 9780441013593, 'Science Fiction', 412, 5, 12.01, 5.00, 'Chilton Books', 0.05);