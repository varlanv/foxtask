DROP SCHEMA IF EXISTS foxtask CASCADE;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS usr;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS extra_services;
DROP TABLE IF EXISTS booking;

CREATE SCHEMA foxtask;


CREATE TABLE foxtask.category (
  id    SERIAL,
  name  VARCHAR(100) UNIQUE NOT NULL,
  price DECIMAL,

  CONSTRAINT categorya_pkey PRIMARY KEY (id)
);


CREATE TABLE foxtask.extra_services (
  id            SERIAL,
  service       VARCHAR(100),
  service_price DECIMAL,

  CONSTRAINT extra_services_pkey PRIMARY KEY (id)
);


CREATE TABLE foxtask.room (
  number      INT4 UNIQUE NOT NULL,
  category_id INT4,

  CONSTRAINT room_pkey PRIMARY KEY (number),
  CONSTRAINT category_pkey FOREIGN KEY (category_id) REFERENCES foxtask.category (id)
);


CREATE TABLE foxtask.booking (
  id                SERIAL,
  room_id           INT4,
  extra_services_id INT4,
  booking_date      DATE,

  CONSTRAINT booking_pkey PRIMARY KEY (id),
  CONSTRAINT booking_room_fkey FOREIGN KEY (room_id) REFERENCES foxtask.room (number),
  CONSTRAINT booking_extra_services_fkey FOREIGN KEY (extra_services_id) REFERENCES foxtask.extra_services (id)
);

CREATE TABLE foxtask.usr (
  id          SERIAL,
  firstname   VARCHAR(100),
  lastname    VARCHAR(100),
  bookings_id INT4,

  CONSTRAINT usr_pkey PRIMARY KEY (id),
  CONSTRAINT usr_bookings_fkey FOREIGN KEY (bookings_id) REFERENCES foxtask.booking (id)
);


INSERT INTO foxtask.category (name, price) VALUES
  ('STANDARD', '50'),
  ('DOUBLE', '75'),
  ('LUX', '120');


INSERT INTO foxtask.room (number, category_id) VALUES
  ('1', '1'),
  ('5', '3'),
  ('10', '2'),
  ('6', '2'),
  ('15', '2'),
  ('20', '1'),
  ('3', '3'),
  ('25', '1'),
  ('21', '3'),
  ('13', '1'),
  ('19', '2'),
  ('22', '3');


INSERT INTO foxtask.extra_services (service, service_price) VALUES
  ('cleaning', '10'),
  ('breakfast', '5');