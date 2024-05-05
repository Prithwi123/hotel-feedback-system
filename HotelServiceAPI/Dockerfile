FROM gradle:8.7.0-jdk21-alpine AS builder

RUN mkdir -p /app/source
COPY . /app/source

RUN gradle --version

WORKDIR /app/source
RUN gradle clean build --warning-mode=all

FROM eclipse-temurin:17.0.11_9-jre-alpine AS run

COPY --from=builder /app/source/build/libs/*.jar /app/app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/app.jar"]