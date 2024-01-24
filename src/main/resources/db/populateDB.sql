START TRANSACTION;

INSERT INTO users VALUES (1, 'user1');
INSERT INTO users VALUES (2, 'user2');
INSERT INTO users VALUES (3, 'user3');

INSERT INTO categories VALUES (1, 'laptop');
INSERT INTO categories VALUES (2, 'desktop');
INSERT INTO categories VALUES (3, 'printer');

INSERT INTO products (id, name, category_id, price) VALUES
(1, 'laptop1', 1, 100.00),
(2, 'laptop2', 1, 200.00),
(3, 'laptop3', 1, 300.00),
(4, 'desktop1', 2, 100.00),
(5, 'desktop2', 2, 200.00),
(6, 'desktop3', 2, 300.00),
(7, 'printer1', 3, 250.00)
;

INSERT INTO store_items (product_id, quantity) VALUES
(1, 5),
(2, 5),
(3, 5),
(4, 5),
(5, 5),
(6, 5),
(7, 5)
;

INSERT INTO carts (id,cart_date,user_id) VALUES
(1,'2024-01-18',1)
;

INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
(1, 1, 5),
(1, 2, 2),
(1, 3, 1),
(1, 7, 2)
;

COMMIT;

