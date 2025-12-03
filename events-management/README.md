# 📄 **US6 – Deployment with Docker & Flyway**

## 🚀 Overview
This document describes the implementation of **User Story 6**, which focuses on deploying the Events Management backend application using **Docker**, **Docker Compose**, and **Flyway** for automated database migrations and initialization.

## ✅ **1. User Story Description**
As a developer,  
I want to deploy the application using Docker,  
so that the backend and MySQL database run in isolated, reproducible environments and the schema is automatically created with initial seed data.

## ✅ **2. Scope**
This user story includes:

- Creating a **Dockerfile** to build and package the Spring Boot application.
- Creating a **docker-compose.yml** to orchestrate:
    - MySQL 8 database service
    - Spring Boot backend service
- Configuring **Flyway** migrations.
- Automatically seeding roles, admin user, venues, and events.
- Verifying the deployment through the Swagger UI.

## ✅ **3. Architecture Overview**

```
Docker Compose
│
├── Service: db
│     ├── Image: mysql:8
│     ├── Port mapping: 3307 → 3306
│     ├── Persistent volume: dbdata
│     └── Flyway migrations applied at startup
│
└── Service: app
      ├── Built using multi-stage Dockerfile
      ├── Runtime: Eclipse Temurin Java 21 JRE
      ├── Exposes port 8080
      └── Connects to db container
```

## ✅ **4. Main Files Created**

### 🔹 **Dockerfile**
A multi-stage Dockerfile containing:
- **Build stage:** Maven + JDK 21 for compiling the project
- **Runtime stage:** Lightweight Temurin 21 JRE image
- `app.jar` copied as the final artifact

### 🔹 **docker-compose.yml**
Defines two services:

#### Database (db)
- Uses `mysql:8`
- Initializes database `events_db`
- Exposes port `3307`
- Persists data with `dbdata` volume

#### Application (app)
- Built from the Dockerfile
- Connects to the database using environment variables
- Starts Spring Boot on port `8080`

### 🔹 **Flyway Migrations**
Migrations executed automatically:

| Version | Description |
|--------|-------------|
| **V1** | Create `venues` table |
| **V2** | Create `events` table |
| **V3** | Create `users`, `roles`, and `user_roles` tables |
| **V4** | Add integrity constraints |
| **V5** | Seed default roles |
| **V6** | Seed venues, events, and default admin user |

All migrations were successfully applied in order.

## ✅ **5. Deployment Process**

1. Installed and configured **Docker Desktop**.
2. Implemented and refined the Dockerfile.
3. Created the docker-compose.yml.
4. Ran the deployment using:

```bash
docker compose down
docker volume rm events-management_dbdata
docker compose up --build
```

5. Fixed errors in migrations:
    - Missing columns
    - Seed inconsistencies
    - Constraints mismatches
6. Validated that:
    - MySQL container initialized properly.
    - Flyway applied all migrations.
    - Backend started with no dependency errors.

## ✅ **6. Final Results**

The system now:

- Builds via Docker using a stable multi-stage process.
- Runs MySQL + Spring Boot in containers.
- Automatically creates database schema and seed data via Flyway.
- Successfully exposes the API documentation at:

👉 **http://localhost:8080/swagger-ui.html**

US6 is fully implemented and validated.
