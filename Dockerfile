FROM openjdk:11
EXPOSE 8080
ADD acid-docker.jar acid-docker.jar
ENTRYPOINT ["java","-jar","/acid-docker.jar"]
