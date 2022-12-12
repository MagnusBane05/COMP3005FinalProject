-- select books given an ISBN
SELECT * FROM books WHERE isbn = ?1;
-- select all books in orders
SELECT books.* FROM orders INNER JOIN order_books ON orders.order_number = order_books.order_number INNER JOIN books ON order_books.ISBN = books.ISBN
-- select book in orders given ISBN
SELECT books.* FROM orders INNER JOIN order_books ON orders.order_number = order_books.order_number INNER JOIN books ON order_books.ISBN = books.ISBN WHERE books.ISBN = ?1;
-- Find all orders by a user
SELECT * FROM orders WHERE user = ?1
-- Find all purchases by date and isbn
SELECT * FROM purchases WHERE date = ?1 AND book_ISBN = ?2
-- find total sales
SELECT SUM(books.price * (1 - books.sale_percentage)) AS total_sales " +
						"FROM orders " +
						"INNER JOIN order_books ON orders.order_number = order_books.order_number " +
						"INNER JOIN books ON order_books.ISBN = books.ISBN
                        
-- find sales by book
SELECT SUM(books.price * (1 - books.sale_percentage)) AS total_sales " +
						"FROM orders " +
						"INNER JOIN order_books ON orders.order_number = order_books.order_number " +
						"INNER JOIN books ON order_books.ISBN = books.ISBN " +
						"WHERE books.ISBN = ISBN
                        
-- find total expenditures
SELECT SUM(books.purchase_price * purchases.quantity) AS total_expenditures "
						+ "FROM purchases "
						+ "INNER JOIN books ON purchases.book_ISBN = books.ISBN
                        
-- find expenditures by book
SELECT SUM(books.purchase_price * purchases.quantity) AS total_expenditures "
						+ "FROM purchases "
						+ "INNER JOIN books ON purchases.book_ISBN = books.ISBN "
						+ "WHERE books.ISBN = ISBN
					
-- find all purchases
SELECT books.*, purchases.quantity "
					+ "FROM purchases "
					+ "INNER JOIN books ON purchases.book_ISBN = books.ISBN
                    
-- find books sold in the last month by ISBN
SELECT COUNT(*) "
				+ "FROM books b "
				+ "INNER JOIN order_books ob ON b.ISBN = ob.ISBN "
				+ "INNER JOIN orders o ON ob.order_number = o.order_number "
				+ "WHERE o.date >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) "
				+ "AND b.ISBN = ISBN
                
-- find user by username
SELECT u FROM User u WHERE u.username = ?1
	
