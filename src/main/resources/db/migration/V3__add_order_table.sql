CREATE TABLE IF NOT EXISTS orders
(
    order_id              BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    order_num             BINARY(16)      NOT NULL,
    total_price           INT UNSIGNED    NOT NULL,
    order_status          VARCHAR(255)    NOT NULL,
    receiver_name         CHAR(130)        NOT NULL,
    receiver_phone_num    CHAR(60)        NOT NULL,
    receiver_zipcode      CHAR(52)         NOT NULL,
    receiver_address_main VARCHAR(255)    NOT NULL,
    receiver_address_sub  VARCHAR(255)    NOT NULL,
    user_id               BIGINT UNSIGNED NOT NULL,
    created_at            TIMESTAMP       NOT NULL,
    modified_at           TIMESTAMP       NOT NULL,

    PRIMARY KEY (order_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE IF NOT EXISTS order_product
(
    order_product_id BIGINT UNSIGNED   NOT NULL AUTO_INCREMENT,
    product_id       BIGINT UNSIGNED   NOT NULL,
    order_id         BIGINT UNSIGNED   NOT NULL,
    order_quantity   SMALLINT UNSIGNED NOT NULL,
    order_price      INT UNSIGNED      NOT NULL,
    created_at       TIMESTAMP         NOT NULL,
    modified_at      TIMESTAMP         NOT NULL,

    PRIMARY KEY (order_product_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id),
    FOREIGN KEY (order_id) REFERENCES orders (order_id)
);