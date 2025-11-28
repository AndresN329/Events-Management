ALTER TABLE events
ADD CONSTRAINT fk_event_venue
FOREIGN KEY (venue_id) REFERENCES venues(id);

CREATE INDEX idx_event_category ON events(category);
CREATE INDEX idx_event_start_date ON events(start_date);
