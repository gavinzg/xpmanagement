FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} xp-management.jar
ENTRYPOINT ["java","-jar","/xp-management.jar"]
