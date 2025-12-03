-- ===============================
-- V1 - CREATE VENUES
-- ===============================

CREATE TABLE venues (
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL,
   address VARCHAR(255) NOT NULL,

   min_capacity INT NOT NULL,
   max_capacity INT NOT NULL,

   description TEXT
);
