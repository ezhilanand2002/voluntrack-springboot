# ==========================================================================
# VolunTrack Multi-Stage Dockerfile for 100% Free Cloud Deployment
# Compatible with Render.com, Koyeb, Railway, and Fly.io
# ==========================================================================

# Stage 1: Build the Spring Boot Application
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Minimal Runtime Image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080 10000
ENTRYPOINT ["java", "-jar", "app.jar"]
