FROM openjdk:11

ARG JAR_FILE
ADD target/${JAR_FILE} /app.jar

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=docker", "/app.jar"]
