# VolunTrack — Volunteer & Fundraising Event Management System

VolunTrack is a full-stack non-profit web application built with **Java 17**, **Spring Boot 3**, **Spring Data JPA**, **Spring Security with JWT**, **MySQL**, and **Vanilla Frontend (HTML/CSS/JS)** served directly from Spring Boot's static resources.

---

## 🛠️ Technology Stack

- **Backend**: Java 17, Spring Boot 3, Spring Data JPA, Spring Security (JWT authentication), Maven.
- **Database**: MySQL (`spring.jpa.hibernate.ddl-auto=update` for automatic schema generation).
- **Frontend**: Plain HTML, CSS, Vanilla JavaScript served directly from `src/main/resources/static/`.
- **PDF Generation**: Client-side in-browser PDF certificates powered by `jsPDF` (CDN).
- **Analytics Charts**: `Chart.js` (CDN) for charity performance reports.
- **Payment Processing**: Isolated simulated payment engine in `DonationService.processSimulatedPayment()`.

---

## 📋 Prerequisites

1. **Java JDK 17** or higher installed.
2. **MySQL Server 8.0+** running locally on port `3306`.

---

## 🗄️ Database Setup

1. Open your MySQL client (MySQL Workbench, Command Line, or DBeaver).
2. Create the database:
   ```sql
   CREATE DATABASE IF NOT EXISTS voluntrack_db;
   ```
3. Open `src/main/resources/application.properties` and verify your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/voluntrack_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=root
   ```

---

## 🚀 How to Run the Project

### Option A: Using Maven Wrapper (No Global Maven Required)
Run this command directly in your Terminal / PowerShell inside the project directory:
```powershell
.\mvnw spring-boot:run
```

### Option B: In VS Code (Recommended)
1. Open VS Code and select `File -> Open Folder` -> `voluntrack`.
2. Ensure the **Extension Pack for Java** is installed in VS Code.
3. Open `src/main/java/com/voluntrack/VolunTrackApplication.java`.
4. Click **Run** (or press `F5` / green play button above `main()`).
5. Open your browser to:
   ```text
   http://localhost:8080
   ```

---

## 🔍 Troubleshooting Guide

### ❌ Issue: "'mvn' is not recognized as an internal or external command"
- Use the included Maven wrapper script instead:
  ```powershell
  .\mvnw spring-boot:run
  ```
- Or run `VolunTrackApplication.java` directly in VS Code.

### ❌ Issue: "Failed to fetch" on Login / Registration
- Ensure Spring Boot backend is running on `http://localhost:8080`.
- Ensure MySQL is running on port `3306` with database `voluntrack_db`.
- **Do NOT** open `login.html` directly via `file://`. Always access through `http://localhost:8080/login.html`.
