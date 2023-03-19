FROM gradle:7.5.0-jdk17 as gradle-builder
COPY src /app/src
COPY build.gradle /app
WORKDIR /app
RUN gradle build -x test

FROM openjdk:17-alpine
COPY --from=gradle-builder /app/build/libs/app-0.0.1-SNAPSHOT.jar /app-service/app-0.0.1-SNAPSHOT.jar
WORKDIR /app-service
ENTRYPOINT ["java","-jar","/app-service/app-0.0.1-SNAPSHOT.jar"]
