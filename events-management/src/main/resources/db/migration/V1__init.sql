CREATE TABLE venues (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    category VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    start_date DATE NOT NULL,
    capacity INT NOT NULL,
    venue_id BIGINT NOT NULL
);
