FROM openjdk:8-jre-alpine
COPY  target/freenow_server_applicant_test-1.0.0-SNAPSHOT.jar /server.jar
EXPOSE 8080
CMD ["/usr/bin/java","-jar","/server.jar"]