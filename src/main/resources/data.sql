INSERT into products (name, price, status) VALUES('Apples', 505, 'OUT_OF_STOCK');
INSERT into products (name, price, status) VALUES('Banana', 400, 'OUT_OF_STOCK');
INSERT into products (name, price, status) VALUES('Nut', 600, 'OUT_OF_STOCK');

INSERT into orders (user_id, status) VALUES (1, 'New');
INSERT into orders (user_id, status) VALUES (2, 'Paid');
INSERT into orders (user_id, status) VALUES (3, 'In progress');
INSERT into orders (user_id, status) VALUES (4, 'Paid');

INSERT into order_item (order_id, product_id, quantity) VALUES(2, 2, 1000);
INSERT into order_item (order_id, product_id, quantity) VALUES(2, 1, 1200);
INSERT into order_item (order_id, product_id, quantity) VALUES(3, 1, 1500);

select products.name, count(product_id), sum(quantity) as sum_quant  from products
            INNER JOIN order_item oi on products.id = oi.product_id
            GROUP BY name
            ORDER BY sum(quantity) DESC