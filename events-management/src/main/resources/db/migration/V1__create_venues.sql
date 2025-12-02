-- ===============================
-- V1 - CREATE VENUES
-- ===============================

CREATE TABLE venues (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    address VARCHAR(255) NOT NULL
);
