-- =========================================
-- V3 - TABLAS PARA AUTENTICACIÓN Y ROLES
-- =========================================

-- Tabla de usuarios
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,

    PRIMARY KEY (id)
);

-- Tabla de roles
CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,

    PRIMARY KEY (id)
);

-- Tabla intermedia usuario-role (ManyToMany)
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    PRIMARY KEY (user_id, role_id),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_user_roles_roles
        FOREIGN KEY (role_id)
        REFERENCES roles(id)
        ON DELETE CASCADE
);
