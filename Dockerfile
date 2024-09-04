FROM maven:3-eclipse-temurin-21-alpine AS builder
WORKDIR /build
COPY . .
RUN mvn clean install
FROM eclipse-temurin:21-alpine
WORKDIR /app
COPY --from=builder /build/target/hotel-reservation-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8989
CMD ["java", "-jar", "app.jar"]
