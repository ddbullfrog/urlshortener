FROM openjdk:8-jre-alpine

ENV JAVA_OPTS=""

EXPOSE 8080

# add directly the jar
ADD *.jar /app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
