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
  service       VARCHAR(100) UNIQUE,
  service_price DECIMAL,

  CONSTRAINT extra_services_pkey PRIMARY KEY (id)
);


CREATE TABLE foxtask.room (
  number      INT4 UNIQUE NOT NULL,
  category_id INT4,

  CONSTRAINT room_pkey PRIMARY KEY (number),
  CONSTRAINT category_pkey FOREIGN KEY (category_id) REFERENCES foxtask.category (id)
);


CREATE TABLE foxtask.usr (
  id         SERIAL,
  first_name VARCHAR(100),
  last_name  VARCHAR(100),
  email      VARCHAR(100),

  CONSTRAINT usr_pkey PRIMARY KEY (id)
);


CREATE TABLE foxtask.booking (
  id           SERIAL,
  room_id      INT4,
  booking_date DATE,
  user_id      INT4,

  CONSTRAINT booking_pkey PRIMARY KEY (id),
  CONSTRAINT booking_user_fkey FOREIGN KEY (user_id) REFERENCES foxtask.usr (id),
  CONSTRAINT booking_room_fkey FOREIGN KEY (room_id) REFERENCES foxtask.room (number)
);


CREATE TABLE foxtask.bookings_extra_services (
  booking_id       INT4 REFERENCES foxtask.booking,
  extra_service_id INT4 REFERENCES foxtask.extra_services,

  CONSTRAINT bookings_extra_services_pkey PRIMARY KEY (booking_id, extra_service_id)
);