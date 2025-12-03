-- =========================================
-- V6 - DATA DE PRUEBA INICIAL
-- =========================================

-- ======================
-- 1. VENUES
-- ======================

INSERT INTO venues (name, min_capacity, max_capacity, address)
VALUES
    ('Centro de Convenciones Aurora', 100, 800, 'Av. Libertad #123'),
    ('Teatro Solaris', 50, 450, 'Calle Luna #45'),
    ('Estadio Nova', 5000, 20000, 'Av. Olímpica #500'),
    ('Hotel Royal Palace - Salón Dorado', 50, 300, 'Calle Real #77'),
    ('Auditorio Zenith', 200, 1200, 'Av. Central #11');

-- ======================
-- 2. EVENTS (sin cambios)
-- ======================

INSERT INTO events (name, description, category, start_date, end_date, capacity, venue_id)
VALUES
    ('Concierto Sinfónico', 'Orquesta nacional interpretando clásicos', 'Música', '2025-12-10', '2025-12-11', 600, 1),
    ('Conferencia de Tecnología', 'Tendencias en IA y computación cuántica', 'Conferencia', '2025-11-15', '2025-11-16', 450, 5),
    ('Partido de Exhibición', 'Equipo nacional vs visitantes internacionales', 'Deporte', '2025-10-01', '2025-10-01', 15000, 3),
    ('Gala Benéfica', 'Evento de recaudación de fondos', 'Caridad', '2025-09-20', '2025-09-20', 250, 4),
    ('Festival de Jazz', 'Bandas nacionales e internacionales', 'Música', '2025-08-05', '2025-08-06', 900, 2);

-- ======================
-- 3. ADMIN
-- ======================

INSERT INTO users (username, password)
VALUES ('admin', '$2a$10$e0MYzXyjpJS7Pd0RVvHwHeFXY4A2pZr5Guze8rodpAtxEvsC1iZHy');

-- ======================
-- 4. ASIGNAR ROLE_ADMIN
-- ======================

INSERT INTO user_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE username = 'admin'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
);
