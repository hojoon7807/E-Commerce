CREATE TABLE IF NOT EXISTS user
(
    user_id     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username    CHAR(130)        NOT NULL,
    password    VARCHAR(255)    NOT NULL,
    email       VARCHAR(255)    NOT NULL UNIQUE,
    phone_num   CHAR(60)        NOT NULL,
    user_role   VARCHAR(255)    NOT NULL,
    user_status VARCHAR(255)    NOT NULL,
    created_at  TIMESTAMP       NOT NULL,
    modified_at TIMESTAMP       NOT NULL,

    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS address
(
    address_id   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    zipcode      CHAR(52)        NOT NULL,
    address_main VARCHAR(255)    NOT NULL,
    address_sub  VARCHAR(255)    NOT NULL,
    is_default   TINYINT(1)      NOT NULL,
    user_id      BIGINT UNSIGNED NOT NULL,

    PRIMARY KEY (address_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id)
);