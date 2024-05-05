FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

RUN mkdir -p /app/source
COPY . /app/source

WORKDIR /app/source
RUN mvn clean install

FROM eclipse-temurin:17.0.11_9-jre-alpine AS run

COPY --from=builder /app/source/target/*.jar /app/app.jar

EXPOSE 8070
ENTRYPOINT ["java", "-jar", "/app/app.jar"]