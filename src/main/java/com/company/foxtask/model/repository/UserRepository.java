package com.company.foxtask.model.repository;

import com.company.foxtask.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value =
            "DROP SCHEMA IF EXISTS foxtask CASCADE;\n" +
                    "DROP TABLE IF EXISTS category;\n" +
                    "DROP TABLE IF EXISTS usr;\n" +
                    "DROP TABLE IF EXISTS room;\n" +
                    "DROP TABLE IF EXISTS extra_services;\n" +
                    "DROP TABLE IF EXISTS booking;\n" +
                    "\n" +
                    "CREATE SCHEMA foxtask;\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE foxtask.category (\n" +
                    "  id   SERIAL,\n" +
                    "  name VARCHAR(100) UNIQUE NOT NULL,\n" +
                    "\n" +
                    "  CONSTRAINT categorya_pkey PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE foxtask.extra_services (\n" +
                    "  id    SERIAL,\n" +
                    "  name  VARCHAR(100) UNIQUE,\n" +
                    "  price DECIMAL,\n" +
                    "\n" +
                    "  CONSTRAINT extra_services_pkey PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE foxtask.room (\n" +
                    "  number      INT4 UNIQUE NOT NULL,\n" +
                    "  category_id INT4,\n" +
                    "  available   BOOLEAN,\n" +
                    "  price       DECIMAL,\n" +
                    "\n" +
                    "  CONSTRAINT room_pkey PRIMARY KEY (number),\n" +
                    "  CONSTRAINT category_pkey FOREIGN KEY (category_id) REFERENCES foxtask.category (id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE foxtask.usr (\n" +
                    "  id    SERIAL,\n" +
                    "  email VARCHAR(100),\n" +
                    "\n" +
                    "  CONSTRAINT usr_pkey PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE foxtask.booking (\n" +
                    "  id                SERIAL,\n" +
                    "  room_number       INT4,\n" +
                    "  booking_date_from TIMESTAMP,\n" +
                    "  booking_date_to   TIMESTAMP,\n" +
                    "  user_id           INT4,\n" +
                    "\n" +
                    "  CONSTRAINT booking_pkey PRIMARY KEY (id),\n" +
                    "  CONSTRAINT booking_user_fkey FOREIGN KEY (user_id) REFERENCES foxtask.usr (id),\n" +
                    "  CONSTRAINT booking_room_fkey FOREIGN KEY (room_number) REFERENCES foxtask.room (number)\n" +
                    ");\n" +
                    "\n" +
                    "\n" +
                    "CREATE TABLE foxtask.extra_services_bookings (\n" +
                    "  booking_id       INT4 REFERENCES foxtask.booking,\n" +
                    "  extra_service_id INT4 REFERENCES foxtask.extra_services,\n" +
                    "\n" +
                    "  CONSTRAINT extra_services_bookings_pkey PRIMARY KEY (booking_id, extra_service_id)\n" +
                    ");INSERT INTO foxtask.category (name) VALUES\n" +
                    "  ('STANDARD'),\n" +
                    "  ('DOUBLE'),\n" +
                    "  ('LUX');\n" +
                    "\n" +
                    "\n" +
                    "INSERT INTO foxtask.room (number, category_id, price, available) VALUES\n" +
                    "  ('1', '1', '50', true),\n" +
                    "  ('5', '3', '120', true),\n" +
                    "  ('10', '2', '75', true),\n" +
                    "  ('6', '2', '75', true),\n" +
                    "  ('15', '2', '75', true),\n" +
                    "  ('20', '1', '50', true),\n" +
                    "  ('3', '3', '120', true),\n" +
                    "  ('25', '1', '50', true),\n" +
                    "  ('21', '3', '120', true),\n" +
                    "  ('13', '1', '50', true),\n" +
                    "  ('19', '2', '75', true),\n" +
                    "  ('22', '3', '120', true);\n" +
                    "\n" +
                    "\n" +
                    "INSERT INTO foxtask.extra_services (name, price) VALUES\n" +
                    "  ('Cleaning', '10'),\n" +
                    "  ('Breakfast', '5');")
    void deleteall();

}