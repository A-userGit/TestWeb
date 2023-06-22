FROM maven:3-eclipse-temurin-20 as build
WORKDIR /workspace/app
COPY pom.xml .
COPY src src
RUN  mvn package spring-boot:repackage

FROM eclipse-temurin:20.0.1_9-jre-ubi9-minimal
WORKDIR /app
COPY --from=build /workspace/app/target/TestWeb.jar /app
CMD ["java","-jar", "/app/TestWeb.jar"]