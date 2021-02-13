create table IF NOT EXISTS products
(
    id     serial       not null,
    constraint products_pkey
        primary key (id),
    name   varchar(255) not null,
    constraint uk_o61fmio5yukmmiqgnxf8pnavn
        unique (name),
    price  double precision,
    constraint products_price_check
        check (price >= (1)),
    status varchar(255)
);

create table IF NOT EXISTS orders
(
    id         serial not null,
    constraint orders_pkey
        primary key (id),
    created_at varchar(255),
    status     varchar(255),
    user_id    bigint not null
);

create table IF NOT EXISTS order_item
(
    id         serial not null,
    constraint order_item_pkey
        primary key (id),
    quantity   int
        check (quantity >= 1),
    order_id   bigint
        references orders,
    product_id bigint
        references products
);

INSERT into products (name, price, status)
VALUES ('Apples', 505, 'OUT_OF_STOCK');
INSERT into products (name, price, status)
VALUES ('Banana', 400, 'OUT_OF_STOCK');
INSERT into products (name, price, status)
VALUES ('Nut', 600, 'OUT_OF_STOCK');

INSERT into orders (user_id, status, created_at)
VALUES (1, 'NEW', '2021-02-13 12:55');
INSERT into orders (user_id, status, created_at)
VALUES (2, 'PAID', '2021-02-13 13:19');
INSERT into orders (user_id, status, created_at)
VALUES (3, 'IN_PROGRESS', '2021-02-13 13:20');
INSERT into orders (user_id, status, created_at)
VALUES (4, 'MUST_DELETE', '2021-02-13 13:47');

INSERT into order_item (order_id, product_id, quantity)
VALUES (2, 2, 1000);
INSERT into order_item (order_id, product_id, quantity)
VALUES (2, 1, 1200);
INSERT into order_item (order_id, product_id, quantity)
VALUES (3, 1, 1500);

