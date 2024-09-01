# Use a imagem oficial do Maven para construir o artefato
FROM maven:3.8.5-openjdk-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use uma imagem JRE leve para rodar o artefato
FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY --from=build /app/target/hotel-reservation-system.jar hotel-reservation-system.jar
ENTRYPOINT ["java","-jar","/hotel-reservation-system.jar"]
