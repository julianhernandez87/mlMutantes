FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/mlMutantes-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080