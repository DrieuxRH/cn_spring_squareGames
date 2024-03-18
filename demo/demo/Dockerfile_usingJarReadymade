FROM openjdk:21
ARG JAR_FILE=out/artifacts/demo_jar/demo.jar
ADD ${JAR_FILE} app/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/demo.jar"]