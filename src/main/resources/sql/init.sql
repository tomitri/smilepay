CREATE TABLE merchant
(
	id SERIAL PRIMARY KEY,
	create_date timestamp ,
	name varchar(255) NOT NULL,
	lastname varchar(255),
	birthdate date
);
  

CREATE TABLE product
(
  id SERIAL PRIMARY KEY,
  label varchar(255),
  unit_price numeric,
  currency varchar(255),
  weight numeric,
  height numeric
);

CREATE TABLE merchant_product
(
  id_merchant_fk  BIGINT NOT NULL REFERENCES merchant(id),
  id_product_fk  BIGINT NOT NULL REFERENCES product(id),
  affectation_date date,
  PRIMARY KEY (id_merchant_fk, id_product_fk)
);

CREATE TABLE address
(
  id SERIAL PRIMARY KEY,
  number integer,
  street varchar(255),
  zipcode varchar(255)
);

CREATE TABLE merchant_address
(
  id_merchant_fk  BIGINT NOT NULL REFERENCES merchant(id),
  id_adress_fk  BIGINT NOT NULL REFERENCES address(id),
  PRIMARY KEY (id_merchant_fk, id_adress_fk)
);

