-- tables

CREATE TABLE web_bookstore_books
(
  bookid character varying(255) NOT NULL,
  calendaryear integer,
  description character varying(255),
  firstname character varying(255),
  inventory integer,
  onsale boolean,
  price double precision,
  surname character varying(255),
  title character varying(255),
  CONSTRAINT web_bookstore_books_pkey PRIMARY KEY (bookid)
)
