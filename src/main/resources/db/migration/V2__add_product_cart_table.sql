CREATE TABLE IF NOT EXISTS product
(
    product_id      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id         BIGINT UNSIGNED NOT NULL,
    product_name    VARCHAR(255)    NOT NULL,
    product_content TEXT            NOT NULL,
    product_status  VARCHAR(50)     NOT NULL,
    price           INT UNSIGNED    NOT NULL,
    stock           INT UNSIGNED    NOT NULL,
    created_at      TIMESTAMP       NOT NULL,
    modified_at     TIMESTAMP       NOT NULL,

    PRIMARY KEY (product_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE IF NOT EXISTS cart
(
    cart_id     BIGINT UNSIGNED   NOT NULL AUTO_INCREMENT,
    product_id  BIGINT UNSIGNED   NOT NULL,
    user_id     BIGINT UNSIGNED   NOT NULL,
    product_quantity    SMALLINT UNSIGNED NOT NULL,
    created_at  TIMESTAMP         NOT NULL,
    modified_at TIMESTAMP         NOT NULL,

    PRIMARY KEY (cart_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);