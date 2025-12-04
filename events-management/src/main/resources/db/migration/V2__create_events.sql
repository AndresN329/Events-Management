-- ===============================
--  V2 - CREAR TABLA EVENTS
-- ===============================

CREATE TABLE events (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    capacity INT NOT NULL,
    description VARCHAR(500),

    venue_id BIGINT NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_events_venue
        FOREIGN KEY (venue_id)
        REFERENCES venues(id)
        ON DELETE CASCADE
);
