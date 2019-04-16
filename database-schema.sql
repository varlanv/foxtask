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

  CONSTRAINT category_pkey PRIMARY KEY (id)
);


CREATE TABLE foxtask.extra_services (
  id      SERIAL,
  service VARCHAR(100),

  CONSTRAINT extra_services_pkey PRIMARY KEY (id)
);


CREATE TABLE foxtask.room (
  number      INT4 UNIQUE NOT NULL,
  price       DECIMAL,
  category_id INT4,

  CONSTRAINT room_pkey PRIMARY KEY (number),
  CONSTRAINT category_pkey FOREIGN KEY (category_id) REFERENCES foxtask.category (id)
);


CREATE TABLE foxtask.booking (
  id                SERIAL,
  room_id           INT4,
  extra_services_id INT4,

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