# ============================
# 1) STAGE DE BUILD (MAVEN)
# ============================
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar pom y resolver dependencias
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar sin tests
RUN mvn -q clean package -DskipTests

# ============================
# 2) STAGE DE RUNTIME (JRE)
# ============================
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Copiar el jar construido
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
