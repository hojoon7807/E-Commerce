CREATE TABLE IF NOT EXISTS payment
(
    payment_id     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    payment_key    VARCHAR(255)    NOT NULL,
    payment_amount INT UNSIGNED    NOT NULL,

    PRIMARY KEY (payment_id)
);

ALTER TABLE orders
    ADD COLUMN payment_id BIGINT UNSIGNED;

ALTER TABLE orders
    ADD CONSTRAINT payment_id
        FOREIGN KEY (payment_id)
            REFERENCES payment (payment_id)