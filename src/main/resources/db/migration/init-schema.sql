CREATE TABLE base_model
(
    id         BIGINT NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    state      SMALLINT NULL,
    CONSTRAINT pk_basemodel PRIMARY KEY (id)
);

CREATE TABLE category
(
    base_id       BIGINT NOT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (base_id)
);

CREATE TABLE product
(
    base_id       BIGINT NOT NULL,
    name          VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    img_url       VARCHAR(255) NULL,
    price DOUBLE NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (base_id)
);

CREATE TABLE test_model
(
    id BIGINT NOT NULL,
    CONSTRAINT pk_testmodel PRIMARY KEY (id)
);

ALTER TABLE category
    ADD CONSTRAINT FK_CATEGORY_ON_BASE FOREIGN KEY (base_id) REFERENCES base_model (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_BASE FOREIGN KEY (base_id) REFERENCES base_model (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (base_id);

ALTER TABLE test_model
    ADD CONSTRAINT FK_TESTMODEL_ON_ID FOREIGN KEY (id) REFERENCES base_model (id);