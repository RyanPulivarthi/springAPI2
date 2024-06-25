ALTER TABLE product
DROP
FOREIGN KEY FKr96lk4avsidf5kex4ypc6hxmp;

CREATE TABLE test_model
(
    id   BIGINT NOT NULL,
    just INT    NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_testmodel PRIMARY KEY (id)
);

ALTER TABLE product
    ADD category_id BIGINT NULL;

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (base_id);

ALTER TABLE test_model
    ADD CONSTRAINT FK_TESTMODEL_ON_ID FOREIGN KEY (id) REFERENCES base_model (id);

ALTER TABLE product
DROP
COLUMN category_base_id;