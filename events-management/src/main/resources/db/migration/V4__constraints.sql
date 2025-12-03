-- =========================================
-- V4 - CONSTRAINTS Y MEJORAS DE INTEGRIDAD
-- =========================================

-- Capacidades positivas
ALTER TABLE venues
    ADD CONSTRAINT chk_venue_min_capacity CHECK (min_capacity > 0);

ALTER TABLE venues
    ADD CONSTRAINT chk_venue_max_capacity CHECK (max_capacity > 0);

-- max >= min
ALTER TABLE venues
    ADD CONSTRAINT chk_venue_capacity_range CHECK (max_capacity >= min_capacity);
