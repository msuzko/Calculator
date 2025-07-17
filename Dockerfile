FROM openjdk:17-jdk-slim-buster
COPY ./build/libs/Calculator-0.0.1-SNAPSHOT.jar /app/start.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/start.jar"]