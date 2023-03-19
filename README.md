# WEATHER-ANALYZER

Technology stack:
- Spring-boot
- Spring WEB
- Spring Data JPA
- Gradle 
- Postgres
- Docker-compose
- Java 17
- Spring validation
- Liquibase
- SQL

Starting app:
- In file application.properties you need write your url for db, port and other settings
-  If you don't have docker, execute this command ```./gradlew build && java -jar build/libs/clevertec-0.0.1-SNAPSHOT.jar```
- If you have docker, execute this command ```docker-compose up```

Endpoints:
- http://localhost:8090/weather - working with weather. Return actual weather 
- http://localhost:8090/weather/avarage - working with weather. Return average weather according to the given interval
