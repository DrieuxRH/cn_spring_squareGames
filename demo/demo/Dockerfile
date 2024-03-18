# Use an official Maven image as the base image
FROM maven:3.8.4-openjdk-11-slim AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src

ARG JAR_FILE=out/artifacts/demo_jar/demo.jar
ADD ${JAR_FILE} app/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/demo.jar"]