-- V3__ajustes.sql

-- Restricciones adicionales
-- CORRECCIÓN 1: Cambiar 'Event' a 'events'
-- CORRECCIÓN 2: Cambiar 'date' a 'start_date' (el nombre de la columna en V1)
ALTER TABLE events
ADD CONSTRAINT chk_event_date CHECK (start_date >= CURRENT_DATE);

-- Datos de ejemplo (opcional)

-- CORRECCIÓN 3: Cambiar 'Venue' a 'venues'
INSERT INTO venues (name, address, capacity) VALUES
('Auditorio Central', 'Calle 123', 500),
('Teatro Principal', 'Avenida 456', 300);