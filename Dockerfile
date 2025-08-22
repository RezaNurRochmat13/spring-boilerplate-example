FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/boilerplate-0.0.1-SNAPSHOT.jar /app/boilerplate.jar

EXPOSE 8080

CMD ["java", "-jar", "boilerplate.jar"]
