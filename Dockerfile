FROM openjdk:17
# jar file location
ARG JAR_FILE=build/libs/SpringMyBatisGradle-0.0.1-SNAPSHOT.jar
# app.jar 복사
COPY ${JAR_FILE} app.jar
# java -jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]