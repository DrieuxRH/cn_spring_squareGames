# Use an official Maven image as the base image
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
#Adding the settings file
ADD settings.xml /root/.m2/settings.xml
# Build the application using Maven

RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
#FROM openjdk:11-jre-slim
# Set the working directory in the container
WORKDIR /app/target
# Copy the built JAR file from the previous stage to the container
# COPY --from=build /app/target/demo.jar .
# COPY . .
# Set the command to run the application
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]