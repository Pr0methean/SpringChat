FROM openjdk:8
ADD target/spring-chat.jar spring-chat.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "spring-chat.jar"]