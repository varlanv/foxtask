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
  id    SERIAL,
  name  VARCHAR(100) UNIQUE,
  price DECIMAL,

  CONSTRAINT extra_services_pkey PRIMARY KEY (id)
);


CREATE TABLE foxtask.room (
  number      INT4 UNIQUE NOT NULL,
  category_id INT4,
  available   BOOLEAN,

  CONSTRAINT room_pkey PRIMARY KEY (number),
  CONSTRAINT category_pkey FOREIGN KEY (category_id) REFERENCES foxtask.category (id)
);


CREATE TABLE foxtask.usr (
  id    SERIAL,
  email VARCHAR(100),

  CONSTRAINT usr_pkey PRIMARY KEY (id)
);


CREATE TABLE foxtask.booking (
  id                SERIAL,
  room_number       INT4,
  booking_date_from DATE,
  booking_date_to   DATE,
  user_id           INT4,

  CONSTRAINT booking_pkey PRIMARY KEY (id),
  CONSTRAINT booking_user_fkey FOREIGN KEY (user_id) REFERENCES foxtask.usr (id),
  CONSTRAINT booking_room_fkey FOREIGN KEY (room_number) REFERENCES foxtask.room (number)
);


CREATE TABLE foxtask.extra_services_bookings (
  booking_id       INT4 REFERENCES foxtask.booking,
  extra_service_id INT4 REFERENCES foxtask.extra_services,

  CONSTRAINT extra_services_bookings_pkey PRIMARY KEY (booking_id, extra_service_id)
);


INSERT INTO foxtask.category (name, price) VALUES
  ('STANDARD', '50'),
  ('DOUBLE', '75'),
  ('LUX', '120');


INSERT INTO foxtask.room (number, category_id, available) VALUES
  ('1', '1', true),
  ('5', '3', true),
  ('10', '2', true),
  ('6', '2', true),
  ('15', '2', true),
  ('20', '1', true),
  ('3', '3', true),
  ('25', '1', true),
  ('21', '3', true),
  ('13', '1', true),
  ('19', '2', true),
  ('22', '3', true);


INSERT INTO foxtask.extra_services (name, price) VALUES
  ('cleaning', '10'),
  ('breakfast', '5');