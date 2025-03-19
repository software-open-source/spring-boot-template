FROM openjdk:21
EXPOSE 8080
COPY target/spring-boot-template.jar spring-boot-template.jar
ENTRYPOINT ["java","-jar","/spring-boot-template.jar"]