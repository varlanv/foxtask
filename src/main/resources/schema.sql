DROP SCHEMA IF EXISTS foxtask CASCADE;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS usr;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS extra_services;
DROP TABLE IF EXISTS booking;

CREATE SCHEMA foxtask;


CREATE TABLE foxtask.category (
  id   SERIAL,
  name VARCHAR(100) UNIQUE NOT NULL,

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
  price       DECIMAL,

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
  booking_date_from TIMESTAMP,
  booking_date_to   TIMESTAMP,
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