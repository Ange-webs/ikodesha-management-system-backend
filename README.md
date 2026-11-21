 Ikodesha Management System (Backend)

 Student: UWIMBABAZI Ange
 Student ID: 27629
 Project: Ikodesha Management System
 Course: Web Technology and Internet

  How to run the API

1. Install Java 17 and PostgreSQL.
2. In PostgreSQL create the database:
   CREATE DATABASE ikodesha;
3. Open `src/main/resources/application.properties` and set your PostgreSQL
   username and password in `spring.datasource.username` and `spring.datasource.password`.
4. From the project folder (the one that contains `pom.xml`) run:
   .\mvnw.cmd spring-boot:run
5. The API runs on http://localhost:8083
6. Import `ikodesha-management-system-midterm.postman_collection.json` into Postman to test every requirement.