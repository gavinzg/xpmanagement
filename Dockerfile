FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ${JAR_FILE} demo.jar
ENTRYPOINT ["java","-jar","/xp-management.jar"]
