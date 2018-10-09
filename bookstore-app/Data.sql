CREATE TABLE book_catalog_book (
    uuid character varying(255) NOT NULL,
    author character varying(255),
    genre character varying(255),
    title character varying(255)
);

CREATE TABLE rating_product (
    uuid character varying(255) NOT NULL,
    rating integer
);

CREATE TABLE recommendation_product (
    uuid character varying(255) NOT NULL,
    category character varying(255),
    rating integer
);

CREATE TABLE api_gateway_cqrs_qbook (
    uuid character varying(255) NOT NULL,
    author character varying(255),
    genre character varying(255),
    title character varying(255),
    rating integer
);

INSERT INTO book_catalog_book (uuid, author, genre, title) VALUES ('666a0868-e99e-41c3-89f7-bd3700301511', 'Stephen King', 'HORROR', 'It');
INSERT INTO book_catalog_book (uuid, author, genre, title) VALUES ('22a41249-f656-4da6-a0e1-982be2cabffc', 'Stephen King', 'HORROR', 'The Shining');
INSERT INTO book_catalog_book (uuid, author, genre, title) VALUES ('ded3c977-286a-4860-8467-577686f74f2a', 'Stephen King', 'HORROR', 'Misery');
INSERT INTO book_catalog_book (uuid, author, genre, title) VALUES ('4a2e7653-ea40-4bd1-94b7-b199c361c939', 'Frank Herbert', 'SF', 'Dune');
INSERT INTO book_catalog_book (uuid, author, genre, title) VALUES ('12a1bc37-5020-4c52-8d1e-301b2dd18e36', 'Stanislaw Lem', 'SF', 'Solaris');
INSERT INTO book_catalog_book (uuid, author, genre, title) VALUES ('167f35ad-e982-40a0-a66e-5c26a310f4d5', 'Isaac Asimov', 'SF', 'Foundation');

INSERT INTO rating_product (uuid, rating) VALUES ('666a0868-e99e-41c3-89f7-bd3700301511', 1);
INSERT INTO rating_product (uuid, rating) VALUES ('22a41249-f656-4da6-a0e1-982be2cabffc', 3);
INSERT INTO rating_product (uuid, rating) VALUES ('ded3c977-286a-4860-8467-577686f74f2a', 5);
INSERT INTO rating_product (uuid, rating) VALUES ('4a2e7653-ea40-4bd1-94b7-b199c361c939', 1);
INSERT INTO rating_product (uuid, rating) VALUES ('12a1bc37-5020-4c52-8d1e-301b2dd18e36', 3);
INSERT INTO rating_product (uuid, rating) VALUES ('167f35ad-e982-40a0-a66e-5c26a310f4d5', 5);

INSERT INTO recommendation_product (uuid, category, rating) VALUES ('666a0868-e99e-41c3-89f7-bd3700301511', 'HORROR', 1);
INSERT INTO recommendation_product (uuid, category, rating) VALUES ('22a41249-f656-4da6-a0e1-982be2cabffc', 'HORROR', 3);
INSERT INTO recommendation_product (uuid, category, rating) VALUES ('ded3c977-286a-4860-8467-577686f74f2a', 'HORROR', 5);
INSERT INTO recommendation_product (uuid, category, rating) VALUES ('4a2e7653-ea40-4bd1-94b7-b199c361c939', 'SF', 1);
INSERT INTO recommendation_product (uuid, category, rating) VALUES ('12a1bc37-5020-4c52-8d1e-301b2dd18e36', 'SF', 3);
INSERT INTO recommendation_product (uuid, category, rating) VALUES ('167f35ad-e982-40a0-a66e-5c26a310f4d5', 'SF', 5);

INSERT INTO api_gateway_cqrs_qbook (uuid, author, genre, title, rating) VALUES ('666a0868-e99e-41c3-89f7-bd3700301511', 'Stephen King', 'HORROR', 'It', 1);
INSERT INTO api_gateway_cqrs_qbook (uuid, author, genre, title, rating) VALUES ('22a41249-f656-4da6-a0e1-982be2cabffc', 'Stephen King', 'HORROR', 'The Shining', 3);
INSERT INTO api_gateway_cqrs_qbook (uuid, author, genre, title, rating) VALUES ('ded3c977-286a-4860-8467-577686f74f2a', 'Stephen King', 'HORROR', 'Misery', 5);
INSERT INTO api_gateway_cqrs_qbook (uuid, author, genre, title, rating) VALUES ('4a2e7653-ea40-4bd1-94b7-b199c361c939', 'Frank Herbert', 'SF', 'Dune', 1);
INSERT INTO api_gateway_cqrs_qbook (uuid, author, genre, title, rating) VALUES ('12a1bc37-5020-4c52-8d1e-301b2dd18e36', 'Stanislaw Lem', 'SF', 'Solaris', 3);
INSERT INTO api_gateway_cqrs_qbook (uuid, author, genre, title, rating) VALUES ('167f35ad-e982-40a0-a66e-5c26a310f4d5', 'Isaac Asimov', 'SF', 'Foundation', 5);

ALTER TABLE ONLY book_catalog_book
    ADD CONSTRAINT book_catalog_book_pkey PRIMARY KEY (uuid);

ALTER TABLE ONLY rating_product
    ADD CONSTRAINT rating_product_pkey PRIMARY KEY (uuid);

ALTER TABLE ONLY recommendation_product
    ADD CONSTRAINT recommendation_product_pkey PRIMARY KEY (uuid);

ALTER TABLE ONLY api_gateway_cqrs_qbook
    ADD CONSTRAINT api_gateway_cqrs_qbook_pkey PRIMARY KEY (uuid);

