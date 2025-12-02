-- =========================================
-- V4 - CONSTRAINTS Y MEJORAS DE INTEGRIDAD
-- =========================================

-- Asegurar capacidades positivas
ALTER TABLE venues
    ADD CONSTRAINT chk_venue_capacity CHECK (capacity > 0);
